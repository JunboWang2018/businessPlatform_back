package com.agriculture.platform.controller.data;

import com.agriculture.platform.common.constant.RespJson;
import com.agriculture.platform.common.constant.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author Junbo Wang
 * @description 该类封装了常用的返回操作，以及统一异常处理（将异常转换为JSON输出）。
 * @date 2019/5/2
 */
public class BaseDataController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseDataController.class);

    /**
     * 返回指定消息码，并返回消息
     * @param code
     * @param message
     * @return
     */
    protected RespJson responseMsg(int code, String message) {
        return new RespJson(code, message);
    }

    /**
     * 返回数据
     * @param data
     * @return
     */
    protected RespJson responseData(Object data) {
        return new RespJson(data);
    }

    /**
     * 以列表形式返回数据
     * @param data
     * @return
     */
 /*   protected <T> ListJson responseList(List<T> data) {
        return new ListJson<T>(data);
    }*/

    /**
     * 包装异步处理任务，默认超时时间为 30s；
     * 处理超时时返回JSON错误码。
     *
     * @param callable
     * @return
     */
    protected WebAsyncTask<RespJson> webAsync(Callable<RespJson> callable) {
        return webAsync(30000L, callable);
    }

    /**
     * 包装异步处理任务；
     * 处理超时时返回JSON错误码。
     *
     * @param timeout 超时时间
     * @param callable
     * @return
     */
    protected WebAsyncTask<RespJson> webAsync(long timeout, Callable<RespJson> callable) {
        WebAsyncTask<RespJson> task = new WebAsyncTask<RespJson>(timeout, callable);
        task.onTimeout(onWebAsyncTimeout);
        return task;
    }

    // 异步处理超时处理方法
    private final Callable<RespJson> onWebAsyncTimeout = new Callable<RespJson>() {
        @Override
        public RespJson call() {
            return responseMsg(Result.EXEC_TIMEOUT.getCode(), Result.EXEC_TIMEOUT.getMessage());
        }
    };

    /**
     * 处理参数错误
     * @param e
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public RespJson handleIllegalArgumentException(IllegalArgumentException e) {
        LOGGER.error(e.getMessage(), e);
        return responseMsg(Result.PARAM_ERR.getCode(), e.getMessage());
    }

    /**
     * 统一处理未处理的异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RespJson handleAllException(Exception e) {
        LOGGER.error(e.getMessage(), e);
        return responseMsg(Result.FAILED.getCode(), e.getMessage());
    }
}
