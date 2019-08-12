package pl.coderslab.charity.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String displayRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/create-user")
    public String createUser(@Valid User user, BindingResult result) {
        if (result.hasErrors())
            return "register";

        userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String displayLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

//    @PostMapping("/login")
//    public String loginUser(@Valid User user, BindingResult result) {
//        User user2 = userService.findByUserEmail(user.getEmail());
//        return "redirect:/";
//    }
}
