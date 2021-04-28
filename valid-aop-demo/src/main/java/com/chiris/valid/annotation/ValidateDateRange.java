package com.chiris.valid.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 用于controller检验时间范围的参数,
 * 开始日期与结束日期参数名 必须与 beginDateArgName 和 endDateArgName 的值一致，否则不会起到参数校验的作用
 * @see com.tencent.tgit.web.aop.ValidateDateRangeAspect
 */
@Documented
@Retention(RUNTIME)
@Target(ElementType.METHOD)
public @interface ValidateDateRange {
    /**
     * 最大时间范围，月
     */
    int mostMoth();

    /**
     * 最早可以是几个月以前
     */
    int earliestMonth() default 12;

    /**
     * 开始时间的方法参数名
     */
    String beginDateArgName() default "beginDate";


    /**
     * 结束时间的方法参数名
     */
    String endDateArgName() default "endDate";

}
