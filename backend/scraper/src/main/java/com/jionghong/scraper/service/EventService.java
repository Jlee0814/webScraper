package com.jionghong.scraper.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jionghong.scraper.entity.Event;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  service interface
 * </p>
 *
 * @author jionghong
 * @since 2020-09-25
 */
public interface EventService extends IService<Event> {

    IPage<Event> SelectPage(Page<Event> pageParam, Event event);
}
