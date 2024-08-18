package rs.webshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.webshop.domain.Product;
import rs.webshop.dto.product.CreateProductCmd;
import rs.webshop.dto.product.ProductInfo;
import rs.webshop.dto.product.ProductResult;
import rs.webshop.dto.product.UpdateProductCmd;
import rs.webshop.exception.ServiceException;
import rs.webshop.service.ProductService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public Product save(@RequestBody @Valid CreateProductCmd cmd) throws ServiceException {
        return productService.save(cmd);
    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @ResponseBody
    public List<ProductResult> findAll() {
        return productService.findAll();
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ProductInfo findById(@PathVariable Long id){
        return productService.findById(id);
    }

    @PutMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update (@RequestBody @Valid UpdateProductCmd cmd) throws ServiceException {
        productService.update(cmd);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws ServiceException{
        productService.delete(id);
    }

    @GetMapping(value = "/name/{name}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @ResponseBody
    public List<ProductResult> findByName(@RequestParam String name) throws ServiceException {
        return productService.findByName(name);
    }

    @GetMapping(value = "/price/{price}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @ResponseBody
    public List<ProductResult> findByPrice(@PathVariable BigDecimal price) throws ServiceException {
        return productService.findByPrice(price);
    }

    @GetMapping(value = "/quantity/{quantity}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @ResponseBody
    public List<ProductResult> findByQuantity(@PathVariable int quantity) throws ServiceException {
        return productService.findByQuantity(quantity);
    }

    @GetMapping(value = "/category/{category}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @ResponseBody
    public List<ProductResult> findByCategory(@PathVariable String category) throws ServiceException {
        return productService.findByCategory(category);
    }
}
