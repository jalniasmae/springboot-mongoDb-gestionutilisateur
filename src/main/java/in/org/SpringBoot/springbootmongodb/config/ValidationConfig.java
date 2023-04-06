package in.org.SpringBoot.springbootmongodb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidationConfig {
	//  I created two beans here  1- validation Mongo EventLisnter  ,  2- the local validator factor bean
	
	// this wil be trigred before we pos any data in the data bases check if there is any new value 
	@Bean
	public ValidatingMongoEventListener validatingMongoEventListener() {
			return new ValidatingMongoEventListener(validator());
	}
	
	
	@Bean
	public LocalValidatorFactoryBean validator()
	{
		return new  LocalValidatorFactoryBean();
	}
}
