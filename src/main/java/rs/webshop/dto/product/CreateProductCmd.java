package rs.webshop.dto.product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductCmd implements Serializable {

    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private List<CategoryDto> categories;
}
