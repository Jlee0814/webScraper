package com.jionghong.scraper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jionghong.scraper.entity.Event;
import com.jionghong.scraper.mapper.EventMapper;
import com.jionghong.scraper.service.EventService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 *  service impl
 * </p>
 *
 * @author jionghong
 * @since 2020-09-25
 */
@Service
public class EventServiceImpl extends ServiceImpl<EventMapper, Event> implements EventService {

    @Override
    public IPage<Event> SelectPage(Page<Event> pageParam, Event event) {
        //show data
        //1.sort by date
        QueryWrapper<Event> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("event_date");
        //2.search
        if(event==null)
            return baseMapper.selectPage(pageParam,queryWrapper);
        String eventName=event.getEventName();
        String websiteName = event.getWebsiteName();
        String eventDate = event.getEventDate();
        String eventLocation = event.getEventLocation();
        if(!StringUtils.isEmpty(eventName)){
            queryWrapper.like("event_name",eventName);
        }
//        if(!StringUtils.isEmpty(websiteName)){
//            queryWrapper.like("website_name",websiteName);
//        }
        if(eventDate!=null){
            queryWrapper.eq("event_date",eventDate);
        }
        if(!StringUtils.isEmpty(eventLocation)){
            queryWrapper.like("event_location",eventLocation);
        }
        return baseMapper.selectPage(pageParam,queryWrapper);
    }
}
