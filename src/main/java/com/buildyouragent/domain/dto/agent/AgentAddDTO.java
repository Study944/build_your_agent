package com.buildyouragent.domain.dto.agent;

import lombok.Data;

/**
 * 智能体新增请求
 */
@Data
public class AgentAddDTO {

    /**
     * 智能体名称
     */
    private String name;

    /**
     * 智能体描述
     */
    private String description;

    /**
     * 系统提示语
     */
    private String systemPrompt;

    // TODO 链接的工具和知识库
}