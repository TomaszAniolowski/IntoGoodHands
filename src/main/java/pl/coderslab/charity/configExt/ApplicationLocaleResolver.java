package pl.coderslab.charity.configExt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import pl.coderslab.charity.user.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Configuration
public class ApplicationLocaleResolver extends SessionLocaleResolver {

    @Autowired
    private UserService userService;

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        super.setLocale(request, response, locale);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = securityContext.getAuthentication().getName();
        userService.setLocale(username, locale);
    }


}
