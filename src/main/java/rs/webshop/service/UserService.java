package rs.webshop.service;

import rs.webshop.domain.User;
import rs.webshop.dto.user.*;
import rs.webshop.exception.DAOException;
import rs.webshop.exception.ServiceException;

import java.util.List;

public interface UserService {

    User save(CreateUserCmd cmd) throws ServiceException;

    List<UserResult> findAll();

    UserInfo findById(Long id);

    void update(UpdateUserCmd cmd) throws ServiceException;

    void delete(Long id) throws ServiceException;

    User addNewRole(UpdateUserRoleCmd cmd) throws ServiceException, DAOException;

}
