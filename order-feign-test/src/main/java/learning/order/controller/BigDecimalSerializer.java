package learning.order.controller;



import com.alibaba.nacos.shaded.io.grpc.internal.JsonUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author ：Puyb
 * @date ：Created in 2023/1/5 13:52
 * @description：
 */
public class BigDecimalSerializer extends JsonSerializer<BigDecimal> {
    public BigDecimalSerializer() {
    }
    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value != null) {
            BigDecimal number = value.setScale(2, BigDecimal.ROUND_HALF_UP);
            gen.writeNumber(number);
        } else {
            gen.writeNumber(value);
        }

        System.out.println();
    }
}
