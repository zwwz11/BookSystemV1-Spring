package basicBookManagement.springBasic.bookService.domain.user.repository;

import basicBookManagement.springBasic.bookService.domain.user.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryUserRepository implements UserRepository{

    private static final Map<Long, User> store = new HashMap<>();
    private static Long seq = 0L;

    @Override
    public Optional<User> save(User user) {
        user.setId(++seq);
        store.put(seq, user);
        return Optional.of(user);
    }

    @Override
    public Optional<User> findById(Long userId) {
        User findUser = store.get(userId);
        return Optional.of(findUser);
    }

    @Override
    public Optional<List<User>> findAll() {
        List<User> users = store.values().stream().toList();
        return Optional.ofNullable(users);
    }

    @Override
    public void updateUser(Long userId, User user) {
        User findUser = store.get(userId);
        findUser.setName(user.getName());
    }

    @Override
    public void removeUser(User user) {
        store.remove(user.getId());
    }
}
