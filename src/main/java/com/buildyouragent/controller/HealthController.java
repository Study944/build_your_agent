package com.buildyouragent.controller;

import com.buildyouragent.common.BaseResponse;
import com.buildyouragent.common.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {

    @GetMapping("")
    public BaseResponse<String> health() {
        return ResultUtil.success("ok");
    }

}
