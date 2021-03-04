package com.fh.controller;

import com.fh.model.LogData;
import com.fh.service.LogService;
import com.fh.utils.PageBean;
import com.fh.utils.ResultData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("api/log")
public class LogController {

    @Resource
    private LogService logService;

    @PostMapping ("addLogData")
    public ResultData addLogData(LogData logData){
        return logService.addLogData(logData);
    }
    @PostMapping("queryLogData")
    public ResultData queryLogData(PageBean pageBean){
        return logService.queryLogData(pageBean);
    }

}
