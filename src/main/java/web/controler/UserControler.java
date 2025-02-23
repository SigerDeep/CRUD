package web.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;


@Controller
@RequestMapping("/users")
public class UserControler {

    @Autowired
    private UserService userService;

    @GetMapping()
    public String getAllUsers(ModelMap model) {
        model.addAttribute("users", userService.listUsers());
        return "users";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable int id, ModelMap model) {
        model.addAttribute("user", userService.getUserById(id));
        return "userPage";
    }

    @GetMapping("/{id}/edit")
    public String pageForUpdateUser(@PathVariable int id, ModelMap model) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable int id) {
        userService.updateUserNameById(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        userService.dropUserById(id);
        return "redirect:/users";
    }

    @GetMapping("/new")
    public String addUser(ModelMap model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/users";
    }


}
