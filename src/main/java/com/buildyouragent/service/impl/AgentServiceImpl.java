package com.buildyouragent.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buildyouragent.common.ThrowUtil;
import com.buildyouragent.common.UserContext;
import com.buildyouragent.domain.dto.agent.AgentAddDTO;
import com.buildyouragent.domain.entity.Agent;
import com.buildyouragent.domain.vo.agent.AgentAddVO;
import com.buildyouragent.exception.ErrorCode;
import com.buildyouragent.service.AgentService;
import com.buildyouragent.mapper.AgentMapper;
import org.springframework.stereotype.Service;

/**
 * 智能体服务接口实现类
 */
@Service
public class AgentServiceImpl extends ServiceImpl<AgentMapper, Agent>
    implements AgentService{

    @Override
    public AgentAddVO addAgent(AgentAddDTO agentAddDTO) {
        // 创建智能体
        Agent agent = BeanUtil.copyProperties(agentAddDTO, Agent.class);
        agent.setUserId(UserContext.getUserId());
        // 保存
        boolean save = save(agent);
        ThrowUtil.throwIf(!save, ErrorCode.OPERATION_ERROR);
        return BeanUtil.copyProperties(agent, AgentAddVO.class);
    }

    @Override
    public String removeAgent(Long agentId) {
        Agent agent = this.getById(agentId);
        ThrowUtil.throwIf(agent == null, ErrorCode.NOT_FOUND_ERROR, "智能体不存在");
        ThrowUtil.throwIf(!agent.getUserId().equals(UserContext.getUserId()), ErrorCode.NO_AUTH_ERROR);
        boolean remove = removeById(agentId);
        ThrowUtil.throwIf(!remove, ErrorCode.OPERATION_ERROR);
        return "删除成功";
    }
}




