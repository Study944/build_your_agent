package com.buildyouragent.common;

import com.buildyouragent.exception.BusinessException;
import com.buildyouragent.exception.ErrorCode;

/**
 * 抛异常工具类
 */
public class ThrowUtil {

    /**
     * 断言抛异常
     * @param condition
     * @param errorCode
     */
    public static void throwIf(boolean condition, ErrorCode errorCode) {
        if (condition) throw new BusinessException(errorCode);
    }
    /**
     * 断言抛异常
     * @param condition
     * @param errorCode
     * @param message
     */
    public static void throwIf(boolean condition, ErrorCode errorCode, String message) {
        if (condition) throw new BusinessException(errorCode, message);
    }

}
