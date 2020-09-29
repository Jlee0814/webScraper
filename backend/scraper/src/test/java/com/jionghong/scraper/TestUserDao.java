package com.jionghong.scraper;

import com.jionghong.scraper.entity.Event;
import com.jionghong.scraper.service.EventService;
import com.jionghong.scraper.utils.MultiHtmlParseUtil;
import com.jionghong.scraper.utils.SingleHtmlParseUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@SpringBootTest
public class TestUserDao {
    @Autowired
    private EventService eventService;
    @Test
    public void testInsert() throws IOException, ParseException {
        List<Event> events = new SingleHtmlParseUtil().parseHtml("https://www.computerworld.com/article/3313417/tech-event-calendar-2020-upcoming-shows-conferences-and-it-expos.html");
        for(Event e:events){
            eventService.save(e);
        }
    }
    @Test
    public void testInsertMultiHtml() throws IOException, ParseException {
        MultiHtmlParseUtil multiHtmlParseUtil = new MultiHtmlParseUtil();
        multiHtmlParseUtil.putUrlToQueue("https://www.computerworld.com/article/3313417/tech-event-calendar-2020-upcoming-shows-conferences-and-it-expos.html");
        multiHtmlParseUtil.parseHtml();
        List<Event> sycList = multiHtmlParseUtil.getSycList();
        for(Event e:sycList){
            eventService.save(e);
        }
    }
}
