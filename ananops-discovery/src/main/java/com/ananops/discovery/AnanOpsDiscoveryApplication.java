

package com.ananops.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * The class AnanOps cloud discovery application.
 *
 * @author ananops.com@gmail.com
 */
@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class AnanOpsDiscoveryApplication {

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(AnanOpsDiscoveryApplication.class, args);
	}
}
