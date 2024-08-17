package rs.webshop.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity<Long> {

    @Column(nullable = false, unique = true)
    @NotNull
    private String name;

    @Column
    private String description;

    @Column
    @NotNull
    private BigDecimal price;

    @Column(nullable = false)
    @NotNull
    private Integer quantity;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @Builder.Default
    @JoinTable(name = "CategoryProduct",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private List<Category> categories = new ArrayList<>();
}
