package org.mrbag.test.BagConfigServer;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomConfigRepository {

	static final Logger log = LoggerFactory.getLogger(CustomConfigRepository.class);
	
	public static class CustomRepository implements EnvironmentRepository{

		private Map<String, String> loadTestLink(){
			Map<String, String> maps = new HashMap<String, String>();
			log.warn("Load Test method configuration");
			
			maps.put("isTest", "true");
			maps.put("conf.name", "test");
			maps.put("test", "2");
			
			return maps;
		}
		
		@Override
		public Environment findOne(String application, String profile, String label) {
			log.info(String.format("Question %s: [%s] %s", application, profile, label));
			
			PropertySource sourse = new PropertySource("cust", loadTestLink());
			Environment env = new Environment(application, profile.split(","));
			env.add(sourse);
			return env;
		}
		
	}
	
}
