package com.buildyouragent.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 智能体实体
 */
@Data
public class AgentAddDTO {
    /**
     * 智能体id
     */
    private Long id;

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