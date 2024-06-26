package com.pinde.sci.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @ClassName: LogAspect
 * @Description: 日志记录AOP实现
 * Created by shij on 2016-03-07.
 */
@Aspect
public class SysCtrlLogger {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     *
     * @Title：doSysLogForCtrl
     * @Description: 方法调用前触发
     *  记录开始时间
     * @author shij
     * @date 2016年03月07日
     * @param joinPoint
     */
    @After("execution(* com.pinde.sci.ctrl..*.*(..))")
    public void doSysLogForCtrl(JoinPoint joinPoint) {
        logger.debug(joinPoint.getSignature().toString());
    }

}
