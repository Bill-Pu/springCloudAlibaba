package learning.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author ：Puyb
 * @date ：Created in 2022/12/13 13:43
 * @description：
 */
@RestController
@RequestMapping("/stock")
public class StockController {
    @Value("${server.port}")
    private String port;
    @RequestMapping(value = "/reduct")
    public String reduct(){
        System.out.println("扣减库存port"+port);
        return "扣减库存port:"+port;
    }

    @RequestMapping(value = "/reductFeign")
    public String reductFeign(){
        System.out.println("扣减库存port"+port);
        return "扣减库存port:"+port+"OpenFeign接口";
    }

}
