package learning.order;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import learning.order.controller.BigDecimalSerializer;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ：Puyb
 * @date ：Created in 2023/1/5 13:49
 * @description：
 */
@Data
public class BigdecimalTest {
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal var1;
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal var2;
    @JsonSerialize(using = BigDecimalSerializer.class)

    private BigDecimal var3;
}
