package com.jionghong.scraper.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jionghong.scraper.entity.Event;
import com.jionghong.scraper.service.EventService;
import com.jionghong.scraper.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  controller
 * </p>
 *
 * @author jionghong
 * @since 2020-09-25
 */
@CrossOrigin
@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping("list")
    public R listAll(){
        List<Event> list = eventService.list();
        return R.ok().data("items",list);
    }
    @GetMapping("list/{page}/{limit}")
    public R listPage(@PathVariable Long page,
                      @PathVariable Long limit,
                      Event event
                      ){
        Page<Event> pageParam = new Page<>(page, limit);
        IPage<Event> pageModel = eventService.SelectPage(pageParam,event);
        List<Event> records = pageModel.getRecords();
        long total = pageModel.getTotal();
        return R.ok().data("total",total).data("rows",records);
    }
}

