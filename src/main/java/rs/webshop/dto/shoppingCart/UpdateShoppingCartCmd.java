package rs.webshop.dto.shoppingCart;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.webshop.domain.Status;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateShoppingCartCmd {
    private Long id;
    private String name;
    private Status status;
    private BigDecimal price;
    private UserDto userDto;
}
