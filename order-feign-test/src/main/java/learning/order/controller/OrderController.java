package learning.order.controller;

import com.alibaba.fastjson.JSON;
import learning.order.BigdecimalTest;
import learning.order.generator.domain.SysUser;
import learning.order.generator.service.SysUserService;
import learning.order.aspect.AnnotationLogAspect;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author ：Puyb
 * @date ：Created in 2022/12/13 13:40
 * @description：
 */
@RestController
@RequestMapping("/order")
public class OrderController {

//    @Autowired
//    ScockFeignService scockFeignService;
//    @RequestMapping("/add")
//    public String addOrder(){
//        System.out.println("下单");
//        String msg = restTemplate.getForObject("http://localhost:8081/stock/reduct", String.class);
//        System.out.println(msg);
//        return "下单";
//    }

    @Resource
    private SysUserService sysUserService;
    @Resource
    private RedisTemplate redisTemplate;
    @RequestMapping("/addOrderTest")
    @AnnotationLogAspect(description = "测试",value = "addOrderTest")
    public String addOrderTest(){
        List<SysUser> list = sysUserService.list();
        Object o = JSON.toJSON(list);
        String s = JSON.toJSONString(list);
        List<SysUser> sysUsers = JSON.parseArray(s, SysUser.class);
        redisTemplate.opsForValue().set("sysUsers", sysUsers);
        List<SysUser> sysUsers1 = (List<SysUser>) redisTemplate.opsForValue().get("sysUsers");
        SysUser sysUser = sysUsers1.get(0);
        System.out.println("下单");
//        String reduct = scockFeignService.reduct();
//        System.out.println(reduct);
        return "下单";
    }
    @RequestMapping("/test2")
    @AnnotationLogAspect(description = "测试",value = "addOrderTest")
    public BigdecimalTest test2(){
        BigdecimalTest bigdecimalTest = new BigdecimalTest();
        BigDecimal bigDecimal = new BigDecimal("3");
        bigdecimalTest.setVar1(bigDecimal);
        bigdecimalTest.setVar2(new BigDecimal("0"));
        bigdecimalTest.setVar3(new BigDecimal("45645.4565648945"));
        return bigdecimalTest;
    }
}
