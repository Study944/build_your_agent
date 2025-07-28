package com.buildyouragent.domain.vo.agent;

import lombok.Data;

import java.util.Date;

/**
 * 智能体查询响应
 */
@Data
public class AgentVO {
    /**
     * 智能体id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

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

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 关联的工具id列表
     */
    private String toolIds;

    /**
     * 关联的知识库id列表
     */
    private String knowledgeIds;
}