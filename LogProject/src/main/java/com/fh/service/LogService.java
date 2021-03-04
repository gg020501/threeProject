package com.fh.service;

import com.fh.model.LogData;
import com.fh.utils.PageBean;
import com.fh.utils.ResultData;

public interface LogService {
    ResultData queryLogData(PageBean pageBean);

    ResultData addLogData(LogData logData);
}
