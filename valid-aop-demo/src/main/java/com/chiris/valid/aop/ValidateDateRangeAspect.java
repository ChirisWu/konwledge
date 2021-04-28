package com.chiris.valid.aop;


import com.chiris.valid.annotation.ValidateDateRange;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 日期范围校验切面
 */
@Aspect
@Component
public class ValidateDateRangeAspect {


    @Pointcut("execution(* com.chiris.valid.controller.*.*(..)) && @annotation(com.chiris.valid.annotation.ValidateDateRange)")
    public void pointcut() {

    }

    @Around(value = "pointcut() && @annotation(validator)", argNames = "proceedingJoinPoint, validator")
    public Object validate(ProceedingJoinPoint proceedingJoinPoint, ValidateDateRange validator) throws Throwable {

        Object[] argValues = proceedingJoinPoint.getArgs();
        String[] argNames = ((CodeSignature) proceedingJoinPoint.getSignature()).getParameterNames();
        Date begin = null, end = null;
        for (int i = 0; i < argNames.length; i++) {
            if (argNames[i].equals(validator.beginDateArgName())) {
                begin = (Date) argValues[i];
                continue;
            }
            if (argNames[i].equals(validator.endDateArgName())) {
                end = (Date) argValues[i];
            }
        }
        if (begin != null && end != null) {
            if (invalidateDateRange(validator, begin, end)) {
                String msg = "params is invalid check your begin_date and end_date, cause must be  end - begin > " + validator.mostMoth() +
                        " month and just can get data after " + validator.earliestMonth() + " month ago";
               throw new RuntimeException("bad request");
            }

        }
        return proceedingJoinPoint.proceed();
    }

    private boolean invalidateDateRange(ValidateDateRange validator, Date begin, Date end) {
        /**
         * 校验逻辑
         */
        return  true;

    }


}
