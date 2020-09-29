package com.jionghong.scraper.utils;
import com.jionghong.scraper.entity.Event;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class MultiHtmlParseUtil {

    private static volatile Queue<String> queue = new LinkedBlockingDeque<>();
    private static volatile ArrayList<Event> eventsList = new ArrayList<>();
    private static List<Event> sycList = Collections.synchronizedList(eventsList);

    public void putUrlToQueue(String url){
        queue.offer(url);
    }
    public List<Event> getSycList(){
        return sycList;
    }
    public void parseHtml(){
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new MyThread());
        executor.shutdown();
        while(!executor.isTerminated()){}//isTerminated：after shutdown() invoke，return true after all the tasks complete
    }
    public static void work(){
        String url = queue.poll();
        Document document = null;
        try {
            document = Jsoup.parse(new URL(url),30000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element element = document.getElementById("cwsearchabletable");
        Elements websiteLogo = document.getElementsByClass("logo");
        String websiteName = websiteLogo.get(0).text();
        //System.out.println(websiteName);
        Elements elements =  element.getElementsByTag("tr");
        for(int i=1;i<elements.size();i++){
            Element e = elements.get(i);
            String eventName = e.getElementsByTag("th").text();
            String eventDate = e.getElementsByTag("td").eq(1).text();
            String eventLocation  = e.getElementsByTag("td").eq(3).text();
            Event event = new Event();
            event.setWebsiteName(websiteName);
            event.setEventName(eventName);
            event.setEventDate(eventDate);
            event.setEventLocation(eventLocation);
            sycList.add(event);
        }

    }

    public static class MyThread implements Runnable{

        private static Lock lock = new ReentrantLock();
        public void run(){
            lock.lock();
            while(queue.size()>0){
                work();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            lock.unlock();
        }
    }
}

