package tn.esprit.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;


import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableScheduling
@Configuration
@EnableSwagger2
@SpringBootApplication
@EnableAspectJAutoProxy
public class ProjetSpringApplication {
	 /**
     * Look up a required environment variable and throw an
     * IllegalArgumentException if the variable is not set.
     *
     * @param key the name of the environment variable
     * @return the value
     */

	public static void main(String[] args) {

 SpringApplication.run(ProjetSpringApplication.class, args);

	



		
		
	}

}
