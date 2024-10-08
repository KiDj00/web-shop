package rs.webshop.dto.product;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResult implements Serializable {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
}