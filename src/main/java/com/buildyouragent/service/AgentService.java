package com.buildyouragent.service;

import com.buildyouragent.domain.dto.agent.AgentAddDTO;
import com.buildyouragent.domain.entity.Agent;
import com.baomidou.mybatisplus.extension.service.IService;
import com.buildyouragent.domain.vo.agent.AgentAddVO;

/**
 * 智能体服务接口
 */
public interface AgentService extends IService<Agent> {

    AgentAddVO addAgent(AgentAddDTO agentAddDTO);

    String removeAgent(Long agentId);
}
