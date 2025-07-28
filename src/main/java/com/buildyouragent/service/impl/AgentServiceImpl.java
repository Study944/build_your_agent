package com.buildyouragent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buildyouragent.domain.entity.Agent;
import com.buildyouragent.service.AgentService;
import com.buildyouragent.mapper.AgentMapper;
import org.springframework.stereotype.Service;

/**
 * 智能体服务接口实现类
 */
@Service
public class AgentServiceImpl extends ServiceImpl<AgentMapper, Agent>
    implements AgentService{

}




