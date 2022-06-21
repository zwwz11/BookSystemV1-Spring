package basicBookManagement.springBasic.bookService.web.controller;

import basicBookManagement.springBasic.bookService.domain.book.model.Book;
import basicBookManagement.springBasic.bookService.domain.book.service.BookService;
import basicBookManagement.springBasic.bookService.domain.user.model.User;
import basicBookManagement.springBasic.bookService.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public String users(Model model) {
        Optional<List<User>> users = userService.findAllUser();
        model.addAttribute("users", users.get());
        return "/user/users";
    }

    @GetMapping("/addUser")
    public String addUserForm() {
        return "/user/addUser";
    }

    @PostMapping("/addUser")
    public String addUser(User user, RedirectAttributes redirectAttributes) {
        Optional<User> addedUser = userService.join(user);
        redirectAttributes.addAttribute("userId", addedUser.get().getId());
        return "redirect:/user/{userId}";
    }

    @GetMapping("/{userId}")
    public String user(@PathVariable Long userId, Model model) {
        Optional<User> findedUser = userService.findUser(userId);
        model.addAttribute("user", findedUser.get());
        return "/user/user";
    }

    @GetMapping("/{userId}/edit")
    public String editForm(@PathVariable Long userId, Model model) {
        model.addAttribute("user", userService.findUser(userId).get());
        return "/user/editUser";
    }

    @PostMapping("/{userId}/edit")
    public String edit(@PathVariable Long userId, User user) {
        userService.editUser(userId, user);
        return "redirect:/user/{userId}";
    }

    @GetMapping("/{userId}/delete")
    public String delete(@PathVariable Long userId) {
        userService.deleteUser(userService.findUser(userId).get());
        return "redirect:/user/users";
    }
}
