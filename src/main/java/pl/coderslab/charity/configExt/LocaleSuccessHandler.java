package pl.coderslab.charity.configExt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import pl.coderslab.charity.user.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@Component
public class LocaleSuccessHandler implements AuthenticationSuccessHandler {

    private UserService userService;
    private SessionLocaleResolver sessionLocaleResolver;

    @Autowired
    public LocaleSuccessHandler(UserService userService, SessionLocaleResolver sessionLocaleResolver) {
        this.userService = userService;
        this.sessionLocaleResolver = sessionLocaleResolver;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = securityContext.getAuthentication().getName();
        String usersLocaleTag = userService.getUsersLocale(username);
        Locale usersLocale = Locale.forLanguageTag(usersLocaleTag);
        sessionLocaleResolver.setLocale(httpServletRequest, httpServletResponse, usersLocale);

        httpServletResponse.sendRedirect("/");
    }
}
