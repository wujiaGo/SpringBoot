package com.org.pool;

import com.org.security.ResultCode;
import com.org.util.JsonResult;

/**
 * @Author:
 * @Description:
 * @Date
 */
public class ResultTool {

    public static JsonResult success() {
        return new JsonResult(true);
    }

    public static <T> JsonResult<T> success(T data) {
        return new JsonResult(true, data);
    }

    public static JsonResult fail() {
        return new JsonResult(false);
    }

    public static JsonResult fail(ResultCode resultEnum) {
        return new JsonResult(false, resultEnum);
    }

}

