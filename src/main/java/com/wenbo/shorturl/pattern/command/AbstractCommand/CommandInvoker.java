package com.wenbo.shorturl.pattern.command.AbstractCommand;

import com.wenbo.shorturl.pattern.CommonRequestParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @author changwenbo
 * @date 2024/7/29 20:46
 */
@Component
public class CommandInvoker {

    // 这里可以写一些通用逻辑，比如参数校验，日志打印，报警监控等等
    public <T> T executeCommand(CommonRequestParam param, Command<T> command) {
        checkParam(param);
        return command.execute();
    }

    private void checkParam(CommonRequestParam param) {
        if (param == null || StringUtils.isEmpty(param.getCommon())) {
            throw new IllegalArgumentException("param is null");
        }
    }
}

