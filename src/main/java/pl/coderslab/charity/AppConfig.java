package pl.coderslab.charity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.format.FormatterRegistry;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import pl.coderslab.charity.category.CategoryConverter;
import pl.coderslab.charity.category.InstitutionConverter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "pl.coderslab.charity")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "pl.coderslab.charity")
public class AppConfig implements WebMvcConfigurer {
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
