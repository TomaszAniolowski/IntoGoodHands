package pl.coderslab.charity.configExt;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class MyErrorPageRegistry implements ErrorPageRegistrar {

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        registry.addErrorPages(
                setNotFoundErrorPage());
    }

    private ErrorPage setNotFoundErrorPage() {
        return new ErrorPage(HttpStatus.NOT_FOUND, "/404");
    }

}