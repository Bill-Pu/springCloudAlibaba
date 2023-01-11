//package learning.order.seata.order.aspect;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.Signature;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
///**
// * @author ：Puyb
// * @date ：Created in 2022/12/29 20:52
// * @description：
// */
//@Aspect
//@Component
//@Order(2)
//public class LogAspect {
//    @Pointcut("execution(* learning.order.orderfeign.controller.OrderController.addOrderTest(..))")
//    public void myAspect(){
//
//    }
//    @Before("myAspect()")
//    public void before(JoinPoint joinPoint){
//        Object[] args = joinPoint.getArgs();
//        for (Object arg : args) {
//            System.out.println(arg.toString());
//        }
//        Signature signature = joinPoint.getSignature();
//        System.out.println("controller before");
//    }
//}
