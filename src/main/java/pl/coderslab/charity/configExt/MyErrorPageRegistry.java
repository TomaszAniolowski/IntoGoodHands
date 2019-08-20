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
                setNotFoundErrorPage(),
                setInternalServerErrorPage());
    }

    private ErrorPage setNotFoundErrorPage() {
        return new ErrorPage(HttpStatus.NOT_FOUND, "/404");
    }

    private ErrorPage setInternalServerErrorPage() {
        return new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500");
    }

}