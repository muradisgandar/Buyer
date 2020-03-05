package main;

import main.tcpconnection.TCPClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BuyerApplication {

	public static void main(String[] args) throws Exception{
		SpringApplication.run(BuyerApplication.class, args);
                TCPClient.startConnection();
	}

}
