
package com.ananops.discovery.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * The class AnanOps cloud eureka application.
 *
 * @author ananops.com@gmail.com
 */
@EnableEurekaServer
@SpringBootApplication
public class AnanOpsEurekaApplication {

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(AnanOpsEurekaApplication.class, args);
	}
}
