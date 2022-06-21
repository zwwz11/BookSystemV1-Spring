package basicBookManagement.springBasic.bookService.domain.user.repository;

import basicBookManagement.springBasic.bookService.domain.user.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> save(User user);
    Optional<User> findById(Long userId);
    Optional<List<User>> findAll();
    void updateUser(Long userId, User user);
    void removeUser(User user);
}
