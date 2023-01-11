package learning.order.seata.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "srock-service",path = "/stock")
public interface ScockFeignService {
    @RequestMapping(value = "/reductFeign")
    public String reduct();
}
