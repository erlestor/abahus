package rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Class for running the AbahusApplication
@SpringBootApplication
public class AbahusApplication {

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				// if using gitpod. you need to change the url to your gitpod url
				// it should be in the same format as https://3000-white-coyote-7xo3ngjz.ws.gitpod.stud.ntnu.no
				registry.addMapping("/**").allowedMethods("*").allowedOrigins("http://localhost:3000");
			}
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(AbahusApplication.class, args);
	}
    
}