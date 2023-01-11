package learning.order.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author ：Puyb
 * @date ：Created in 2022/12/30 10:09
 * @description：
 */
@Aspect
@Component
public class AnnotationAspect {
    @Pointcut(value = "@annotation(learning.order.aspect.AnnotationLogAspect)")
    public void annotationLog(){
    }
    @Around( "annotationLog()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        String description = method.getAnnotation(AnnotationLogAspect.class).description();
        String value = method.getAnnotation(AnnotationLogAspect.class).value();
        System.out.println(description+value);
        Object proceed = proceedingJoinPoint.proceed();
        return proceed;
    }

}
