package com.CSplashAluguel.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webConfig implements WebMvcConfigurer {

    // Metodo responsável por configurar o acesso a arquivos externos (como imagens)
    // 1. O ResourceHandler define a URL virtual que o Front-End vai chamar.
    // 2. O ResourceLocations define onde esses arquivos estão guardados no HD do servidor.
    // O prefixo "file:" avisa o Java para procurar numa pasta física real do sistema operacional.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/upload/**").addResourceLocations("file:upload/");
    }
}
