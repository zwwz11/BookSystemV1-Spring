package basicBookManagement.springBasic.bookService.domain.user.service;

import basicBookManagement.springBasic.bookService.domain.user.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> join(User user);
    Optional<User> findUser(Long userId);
    Optional<List<User>> findAllUser();
    void editUser(Long userId, User user);
    void deleteUser(User user);
}
