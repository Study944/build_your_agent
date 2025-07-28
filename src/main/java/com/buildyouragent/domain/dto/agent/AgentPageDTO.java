package com.buildyouragent.domain.dto.agent;

import lombok.Data;

@Data
public class AgentPageDTO {
    /**
     * 当前页码
     */
    private Integer pageNum = 1;

    /**
     * 页面大小
     */
    private Integer pageSize = 10;

    /**
     * 智能体名称
     */
    private String name;

}
