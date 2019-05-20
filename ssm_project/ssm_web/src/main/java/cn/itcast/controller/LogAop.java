package cn.itcast.controller;

import cn.itcast.domain.SysLog;
import cn.itcast.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {
    private Date visitTime;
    private Class clazz;
    private Method method;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysLogService sysLogService;

    @Before("execution(* cn.itcast.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();
        clazz = jp.getTarget().getClass();
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        if (args == null || args.length == 0) {
            method = clazz.getMethod(methodName);
        } else {
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            clazz.getMethod(methodName, classArgs);
        }
    }

    @After("execution(* cn.itcast.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        if (clazz != null && method != null && clazz != LogAop.class) {
            RequestMapping annotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (annotation != null) {
                String[] values = annotation.value();
                RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                if (requestMapping != null) {
                    String[] methodValue = requestMapping.value();
                    String url = values[0] + methodValue[0];
                    String ip = request.getRemoteAddr();
                    SecurityContext context = SecurityContextHolder.getContext();
                    String username = ((User) context.getAuthentication().getPrincipal()).getUsername();
                    Long time = new Date().getTime() - visitTime.getTime();
                    SysLog sysLog = new SysLog();
                    sysLog.setExecutionTime(time);
                    sysLog.setVisitTime(visitTime);
                    sysLog.setMethod("[类名] " + clazz.getName() + "[方法名] " + method.getName());
                    sysLog.setUrl(url);
                    sysLog.setIp(ip);
                    sysLog.setUsername(username);
                    sysLogService.save(sysLog);
                }
            }
        }
    }
}
