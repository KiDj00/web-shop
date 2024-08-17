package rs.webshop.dao;

import rs.webshop.domain.User;

import java.util.Optional;

public interface UserDAO extends AbstractDAO<User, Long> {
    public Optional<User> findByUsername (String username);
}
