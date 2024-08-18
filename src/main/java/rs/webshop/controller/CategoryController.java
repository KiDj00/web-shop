package rs.webshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.webshop.domain.Category;
import rs.webshop.dto.category.CategoryInfo;
import rs.webshop.dto.category.CategoryResult;
import rs.webshop.dto.category.CreateCategoryCmd;
import rs.webshop.dto.category.UpdateCategoryCmd;
import rs.webshop.exception.ServiceException;
import rs.webshop.service.CategoryService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public Category save(@RequestBody @Valid CreateCategoryCmd cmd) throws ServiceException {
        return categoryService.save(cmd);
    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @ResponseBody
    public List<CategoryResult> findAll() {
        return categoryService.findAll();
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public CategoryInfo findById(@PathVariable long id) {
        return categoryService.findById(id);
    }

    @PutMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid UpdateCategoryCmd cmd) throws ServiceException {
        categoryService.update(cmd);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws ServiceException {
        categoryService.delete(id);
    }

}
