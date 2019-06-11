package com.xx.demo.service.impl;

import com.xx.demo.entity.Event;
import com.xx.demo.mapper.EventMapper;
import com.xx.demo.service.EventService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaoxiao
 * @since 2019-03-26
 */
@Service
public class EventServiceImpl extends ServiceImpl<EventMapper, Event> implements EventService {

}
