package idus.api.workchronos.infra.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class GlobalCorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Aplica CORS para todas as rotas
                .allowedOrigins("*") // Permite todas as origens
                .allowedMethods("*") // Permite todos os métodos HTTP
                .allowedHeaders("*"); // Permite todos os cabeçalhos
    }
}
