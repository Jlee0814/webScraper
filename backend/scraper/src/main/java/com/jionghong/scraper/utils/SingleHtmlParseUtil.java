package com.jionghong.scraper.utils;
import com.jionghong.scraper.entity.Event;
import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class SingleHtmlParseUtil {

    public List<Event> parseHtml(String url) throws IOException, ParseException {
        //request https://www.computerworld.com/article/3313417/tech-event-calendar-2020-upcoming-shows-conferences-and-it-expos.html
        //String url = "https://www.computerworld.com/article/3313417/tech-event-calendar-2020-upcoming-shows-conferences-and-it-expos.html";
        Document document = Jsoup.parse(new URL(url),30000);
        Element element = document.getElementById("cwsearchabletable");
        Elements websiteLogo = document.getElementsByClass("logo");
        String websiteName = websiteLogo.get(0).text();
        System.out.println(websiteName);

        Elements elements =  element.getElementsByTag("tr");
        ArrayList<Event> eventsList = new ArrayList<>();
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
            eventsList.add(event);
        }
        return eventsList;
    }
}
