package basicBookManagement.springBasic.bookService.domain.user.service;

import basicBookManagement.springBasic.bookService.domain.user.model.User;
import basicBookManagement.springBasic.bookService.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> join(User user) {
        Optional<User> savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    public Optional<User> findUser(Long userId) {
        Optional<User> findedUser = userRepository.findById(userId);
        return findedUser;
    }

    @Override
    public Optional<List<User>> findAllUser() {
        Optional<List<User>> users = userRepository.findAll();
        return users;
    }

    @Override
    public void editUser(Long userId, User user) {
        Optional<User> findedUser = userRepository.findById(userId);
        findedUser.get().setName(user.getName());
    }

    @Override
    public void deleteUser(User user) {
        userRepository.removeUser(user);
    }
}
