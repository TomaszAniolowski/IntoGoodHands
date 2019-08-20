package pl.coderslab.charity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import pl.coderslab.charity.category.CategoryConverter;
import pl.coderslab.charity.category.InstitutionConverter;
import pl.coderslab.charity.configExt.ApplicationLocaleResolver;
import pl.coderslab.charity.configExt.MyErrorPageRegistry;

import javax.annotation.PostConstruct;
import java.util.Locale;

@Configuration
@ComponentScan(basePackages = "pl.coderslab.charity")
@EnableTransactionManagement
@EnableWebMvc
@EnableJpaRepositories(basePackages = "pl.coderslab.charity")
public class AppConfig implements WebMvcConfigurer {

    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;
    private MyErrorPageRegistry myErrorPageRegistry;

    @Autowired
    public AppConfig(RequestMappingHandlerAdapter requestMappingHandlerAdapter, MyErrorPageRegistry myErrorPageRegistry) {
        this.requestMappingHandlerAdapter = requestMappingHandlerAdapter;
        this.myErrorPageRegistry = myErrorPageRegistry;
    }

    @PostConstruct
    public void init() {
        requestMappingHandlerAdapter.setIgnoreDefaultModelOnRedirect(true);
    }

    // === LOCALE

    @Bean
    public LocaleResolver localeResolver() {
        ApplicationLocaleResolver alr = new ApplicationLocaleResolver();
        alr.setDefaultLocale(Locale.forLanguageTag("pl-PL"));
        return alr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    // === VIEWCONTROLLERS

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/404").setViewName("404");
    }

    // === VIEWRESOLVER
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver =
                new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    // === VIEWRESOLVER: ENABLE OTHER FILES
    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    // === CONVERTERS
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(getCategoryConverter());
    }

    @Bean
    public CategoryConverter getCategoryConverter() {
        return new CategoryConverter();
    }

    @Bean
    public InstitutionConverter getInstitutionConverter() {
        return new InstitutionConverter();
    }
}
