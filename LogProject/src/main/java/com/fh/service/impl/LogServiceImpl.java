package com.fh.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fh.model.LogData;
import com.fh.service.LogService;
import com.fh.utils.PageBean;
import com.fh.utils.RedisUtils;
import com.fh.utils.ResultData;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LogServiceImpl implements LogService {


    @Override
    public ResultData queryLogData(PageBean pageBean) {
        pageBean.compute();
        Long llen = RedisUtils.llen();
        List<String> lrange = RedisUtils.lrange(pageBean.getStartIndex(), pageBean.getStartIndex() + pageBean.getPageSize()-1);
        List li=new ArrayList();
        for (int i = 0; i < lrange.size(); i++) {
            String s = lrange.get(i);
            Object parse = JSONObject.parse(s);

            li.add(parse);
        }

        Map map=new HashMap();
        map.put("count",llen);
        map.put("data",li);
        return ResultData.success(map);
    }

    @Override
    public ResultData addLogData(LogData logData) {
        Date date = new Date();
        long time = date.getTime();
        logData.setId(time);
        String s = JSONObject.toJSONString(logData);
        RedisUtils.rpush(s);
        return ResultData.success(null);
    }
}
