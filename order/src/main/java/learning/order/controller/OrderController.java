package learning.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
    @RequestMapping("/add")
    public String addOrder(){
        System.out.println("下单");
        String msg = restTemplate.getForObject("http://localhost:8081/stock/reduct", String.class);
        System.out.println(msg);
        return "下单";
    }
}
