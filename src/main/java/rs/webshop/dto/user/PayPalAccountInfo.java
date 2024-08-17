package rs.webshop.dto.user;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.webshop.domain.Address;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PayPalAccountInfo {
    private Long id;
    private String accountNumber;
    private BigDecimal budget;
    private String language;
    private LocalDate expiresOn;
    private Address billingAddress;
}
