package learning.order.controller;

import com.alibaba.fastjson.JSON;
import learning.order.generator.domain.SysUser;
import learning.order.generator.service.SysUserService;
import learning.order.aspect.AnnotationLogAspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ：Puyb
 * @date ：Created in 2022/12/13 13:40
 * @description：
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    RestTemplate restTemplate;
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
    @RequestMapping("/addOrderTest")
    @AnnotationLogAspect(description = "测试",value = "addOrderTest")
    public String addOrderTest(){
        List<SysUser> list = sysUserService.list();
        Object o = JSON.toJSON(list);
        String s = JSON.toJSONString(list);
        List<SysUser> sysUsers = JSON.parseArray(s, SysUser.class);
        System.out.println("下单");
        System.out.println("退费来源为移机工单的确认选择必须为”是“");
//        String reduct = scockFeignService.reduct();
//        System.out.println(reduct);
        return "下单";
    }
}
