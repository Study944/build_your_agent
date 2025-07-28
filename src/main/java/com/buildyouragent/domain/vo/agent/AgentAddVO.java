package com.buildyouragent.domain.vo.agent;

import lombok.Data;

import java.util.Date;

/**
 * 智能体实体
 */
@Data
public class AgentAddVO {

    /**
     * id
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


}