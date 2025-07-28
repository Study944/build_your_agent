package com.buildyouragent.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.buildyouragent.common.BaseResponse;
import com.buildyouragent.common.ResultUtil;
import com.buildyouragent.common.ThrowUtil;
import com.buildyouragent.common.UserContext;
import com.buildyouragent.domain.dto.agent.AgentAddDTO;
import com.buildyouragent.domain.dto.agent.AgentPageDTO;
import com.buildyouragent.domain.entity.Agent;
import com.buildyouragent.domain.vo.agent.AgentAddVO;
import com.buildyouragent.domain.vo.agent.AgentVO;
import com.buildyouragent.exception.ErrorCode;
import com.buildyouragent.service.AgentService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agent")
public class AgentController {

    @Resource
    private AgentService agentService;

    /**
     * 新增智能体
     *
     * @param agentAddDTO
     */
    @PostMapping("/add")
    public BaseResponse<AgentAddVO> addAgent(@RequestBody AgentAddDTO agentAddDTO) {
        // 参数校验
        String name = agentAddDTO.getName();
        ThrowUtil.throwIf(StrUtil.isBlank(name), ErrorCode.PARAMS_ERROR, "名称不能为空");
        String systemPrompt = agentAddDTO.getSystemPrompt();
        ThrowUtil.throwIf(StrUtil.isBlank(systemPrompt), ErrorCode.PARAMS_ERROR, "系统提示不能为空");
        // 添加服务
        AgentAddVO agentAddVO = agentService.addAgent(agentAddDTO);
        return ResultUtil.success(agentAddVO);
    }

    /**
     * 删除智能体
     *
     * @param agentId
     */
    @DeleteMapping("/remove")
    public BaseResponse<String> removeAgent(@RequestParam Long agentId) {
        ThrowUtil.throwIf(agentId == null, ErrorCode.PARAMS_ERROR, "参数为空");
        String res = agentService.removeAgent(agentId);
        return ResultUtil.success(res);
    }

    /**
     * 获取智能体详细信息
     *
     * @param agentId
     */
    @GetMapping("/get")
    public BaseResponse<AgentVO> getAgent(@RequestParam Long agentId) {
        ThrowUtil.throwIf(agentId == null, ErrorCode.PARAMS_ERROR, "参数为空");
        Agent agent = agentService.getById(agentId);
        ThrowUtil.throwIf(agent == null, ErrorCode.NOT_FOUND_ERROR, "智能体不存在");
        AgentVO agentVO = BeanUtil.copyProperties(agent, AgentVO.class);
        return ResultUtil.success(agentVO);
    }

    /**
     * 获取个人智能体列表
     *
     * @return Page<AgentVO>
     */
    @PostMapping("/list/my")
    public BaseResponse<Page<AgentVO>> listAgent(@RequestBody AgentPageDTO agentPageDTO) {
        // 分页查询个人拥有的智能体列表
        Page<Agent> page = agentService.page(
                new Page<>(agentPageDTO.getPageNum(), agentPageDTO.getPageSize()),
                new LambdaQueryWrapper<Agent>()
                        // 可实现智能体名称的模糊查询
                        .like(
                                StrUtil.isNotBlank(agentPageDTO.getName()),
                                Agent::getName, agentPageDTO.getName()
                        )
                        // 实现用户id的精确查询
                        .eq(
                                Agent::getUserId, UserContext.getUserId()
                        )
        );
        // 类型转换
        List<AgentVO> agentVOList = page.getRecords().stream()
                .map(item -> BeanUtil.copyProperties(item, AgentVO.class))
                .toList();
        Page<AgentVO> agentVOPage = new Page<>();
        agentVOPage.setCurrent(page.getCurrent());
        agentVOPage.setSize(page.getSize());
        agentVOPage.setTotal(page.getTotal());
        agentVOPage.setRecords(agentVOList);
        return ResultUtil.success(agentVOPage);
    }

}
