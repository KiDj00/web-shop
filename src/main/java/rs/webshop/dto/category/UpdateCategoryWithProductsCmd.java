package rs.webshop.dto.category;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCategoryWithProductsCmd {
    private Long id;
    private String name;
    private String description;
    private List<ProductDto> products;
}
