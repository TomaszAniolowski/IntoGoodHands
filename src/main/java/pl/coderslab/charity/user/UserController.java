package pl.coderslab.charity.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Locale;

@Controller
public class UserController {

    private UserService userService;
    private SessionLocaleResolver sessionLocaleResolver;
    private BCryptPasswordEncoder BCryptPasswordEncoder;

    @Autowired
    public UserController(UserService userService, SessionLocaleResolver sessionLocaleResolver, org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder BCryptPasswordEncoder) {
        this.userService = userService;
        this.sessionLocaleResolver = sessionLocaleResolver;
        this.BCryptPasswordEncoder = BCryptPasswordEncoder;
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

        if (userService.checkUser(user))
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
    public void setUsersLocale(@AuthenticationPrincipal CurrentUser user, HttpServletRequest request, HttpServletResponse response) {
        sessionLocaleResolver.setLocale(request, response, Locale.forLanguageTag(user.getUser().getLocale()));
    }

    @GetMapping("/lAsA")
    public String loginAsAdmin(@AuthenticationPrincipal CurrentUser customUser, HttpServletRequest request, HttpSession session) {
        boolean admin;
        if (customUser == null)
            admin = false;
        else {
            admin = customUser.getUser().getRoles().stream().anyMatch(r -> r.getName().equals("ROLE_ADMIN"));
        }


        if (!admin) {
            return "404";
        } else {
            Boolean confirmed = (Boolean) session.getAttribute("CHECKED_ADMIN_RIGHTS");
            if (confirmed != null && confirmed) {
                return "redirect:/admin/desk";
            } else {
                return "admin/nigol";
            }
        }
    }

    @RequestMapping(value = "/lAsA", method = RequestMethod.POST)
    public String checkAdmin(@RequestParam String username,
                               @RequestParam String password,
                               @AuthenticationPrincipal CurrentUser customUser,
                               HttpSession session) {
        if (isCorrect(username, password, customUser)) {
            session.setAttribute("CHECKED_ADMIN_RIGHTS", true);
            return "redirect:/admin/desk";
        } else {
            SecurityContextHolder.clearContext();
            if (session != null) {
                session.invalidate();
            }
            return "redirect:/";
        }
    }



    private boolean isCorrect(String username,String password, CurrentUser customUser) {
        boolean firstCondition = customUser.getUser().getUsername().equals(username);
        boolean secondCondition = BCryptPasswordEncoder.matches(password, customUser.getUser().getPassword());
        boolean thirdCondition = customUser.getUser().getRoles().stream().anyMatch(r -> r.getName().equals("ROLE_ADMIN"));

        return firstCondition && secondCondition && thirdCondition;
    }
}
