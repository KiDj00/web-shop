package rs.webshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rs.webshop.domain.User;
import rs.webshop.dto.user.*;
import rs.webshop.exception.DAOException;
import rs.webshop.exception.ServiceException;
import rs.webshop.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public User save(@RequestBody @Valid CreateUserCmd cmd) throws ServiceException {
        return userService.save(cmd);
    }

    @GetMapping()
    @ResponseBody
    public List<UserResult> findAll() {
        return userService.findAll();
    }

    @GetMapping(value = "/{id}")
    public UserInfo findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid UpdateUserCmd cmd) throws ServiceException {
        userService.update(cmd);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws ServiceException {
        userService.delete(id);
    }

    @PostMapping(value = "/add-new-role")
    public void addNewRole(@RequestBody @Valid UpdateUserRoleCmd cmd) throws ServiceException, DAOException {
        userService.addNewRole(cmd);
    }
}
