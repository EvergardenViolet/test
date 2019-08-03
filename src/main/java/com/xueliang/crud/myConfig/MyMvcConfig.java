package com.xueliang.crud.myConfig;


import com.xueliang.crud.component.LoginHandlerInterceptor;
import com.xueliang.crud.component.MyLocaleResolver;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer(){
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {

//            定制嵌入式的servlet容器相关规则
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                factory.setPort(8083);
            }
        };

    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/main.html").setViewName("dashboard");
        registry.addViewController("/userLogin").setViewName("login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        静态文件
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
//        将所有/emp/**访问都映射到classpath:/static/目录下
        registry.addResourceHandler("/emp/**").addResourceLocations("classpath:/static/");
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer support = new WebMvcConfigurer(){
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/","/userLogin","/asserts/**");
            }
        };
        return support;
    }

//    @Bean
//    public WebMvcConfigurationSupport webMvcConfigurationSupport(){
//        WebMvcConfigurationSupport support = new WebMvcConfigurationSupport(){
//            @Override
//            protected void addViewControllers(ViewControllerRegistry registry) {
//                registry.addViewController("/").setViewName("index");
//                registry.addViewController("/index.html").setViewName("index");
//            }
//
//            @Override
//            public void addResourceHandlers(ResourceHandlerRegistry registry) {
//                registry.addResourceHandler("/static/**").addResourceLocations("classpath:/resources/static/");
//                super.addResourceHandlers(registry);
//            }
//
//        };
//        return support;
//    }

    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }



}

