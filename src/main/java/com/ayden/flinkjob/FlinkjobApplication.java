package com.ayden.flinkjob;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class FlinkjobApplication {
	public static Logger LOG = LoggerFactory.getLogger(FlinkjobApplication.class);

	
	
	public static void main(String[] args) {
			
		System.setProperty("job", "FlinkCdc");

		SpringApplication.run(FlinkjobApplication.class, args);

		

	}

}
