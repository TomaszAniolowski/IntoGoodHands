package pl.coderslab.charity.user;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Locale;

@Controller
public class UserController {

    private UserService userService;
    private SessionLocaleResolver sessionLocaleResolver;

    public UserController(UserService userService, SessionLocaleResolver sessionLocaleResolver) {
        this.userService = userService;
        this.sessionLocaleResolver = sessionLocaleResolver;
    }

    @GetMapping("/register")
    public String displayRegisterForm(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        try {
            customUser.getUser();
        } catch (NullPointerException e) {
            model.addAttribute("user", new User());
            return "register";
        }

        return "redirect:/";
    }

    @PostMapping("/create-user")
    public String createUser(@Valid User user, BindingResult result) {
        if (result.hasErrors())
            return "register";

        if(userService.checkUser(user))
            userService.saveUser(user);
        else {
            result.addError(new FieldError("user", "id", "User with such an email address or username already exists. Check your mailbox."));
            return "register";
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String displayLoginForm(@AuthenticationPrincipal CurrentUser customUser) {
        try {
            customUser.getUser();
        } catch (NullPointerException e) {
            return "login";
        }
        return "redirect:/";
    }

    @PostMapping("/setloc")
    public void setUsersLocale(@AuthenticationPrincipal CurrentUser user, HttpServletRequest request, HttpServletResponse response){
        sessionLocaleResolver.setLocale(request, response, Locale.forLanguageTag(user.getUser().getLocale()));
    }
}
