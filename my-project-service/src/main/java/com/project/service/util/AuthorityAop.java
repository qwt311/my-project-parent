package com.project.service.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by qiaowentao on 2016/11/2.
 */

public class AuthorityAop {

    private final static Logger logger = LoggerFactory.getLogger(AuthorityAop.class);

    public Object checkValidity(ProceedingJoinPoint jp) throws Throwable {
        logger.info("------------------验证合法性----------------");

        logger.info("执行目标方法之前，模拟开始事务...");
        // 获取目标方法原始的调用参数
        Object[] args = jp.getArgs();
        if(args != null && args.length > 1) {
            // 修改目标方法的第一个参数
            //args[0] = "【增加的前缀】" + args[0];
        }
        // 以改变后的参数去执行目标方法，并保存目标方法执行后的返回值
        Object rvt = jp.proceed(args);
        logger.info("执行目标方法之后，模拟结束事务...");
        // 如果rvt的类型是Integer，将rvt改为它的平方
        if(rvt != null && rvt instanceof Integer)
            rvt = (Integer)rvt * (Integer)rvt;
        return rvt;
    }

    public void addLog(JoinPoint j){
        logger.info("------------------主业务完成后添加日志----------------");
        Object obj[] = j.getArgs();
        for(Object o :obj){
            System.out.println(o);
        }
        logger.info("========addLog=="+j.getSignature().getName());//这个是获得方法名
    }

}
