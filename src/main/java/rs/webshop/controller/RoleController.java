package rs.webshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rs.webshop.domain.Role;
import rs.webshop.dto.role.CreateRoleCmd;
import rs.webshop.dto.role.RoleInfo;
import rs.webshop.dto.role.RoleResult;
import rs.webshop.dto.role.UpdateRoleCmd;
import rs.webshop.exception.ServiceException;
import rs.webshop.service.RoleService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping()
    public Role save(@RequestBody @Valid CreateRoleCmd cmd) throws ServiceException {
        return roleService.save(cmd);
    }

    @GetMapping()
    @ResponseBody
    public List<RoleResult> findAll() {
        return roleService.findAll();
    }

    @GetMapping(value = "/{id}")
    public RoleInfo findById(@PathVariable Long id) {
        return roleService.findById(id);
    }

    @PutMapping(value = "/update")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid UpdateRoleCmd cmd) throws ServiceException {
        roleService.update(cmd);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws ServiceException {
        roleService.delete(id);
    }
}
