package id.co.nds.shop;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import id.co.nds.shop.generators.DateGenerator;

@SpringBootApplication
public class ShopApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(LocalDate.now(ZoneId.of("Asia/Jakarta")).toString());
		System.out.println("Date: " + new Date(System.currentTimeMillis()));
		System.out.println("Time: " + new Timestamp(System.currentTimeMillis()));
		System.out.println(DateGenerator.generateTimestamp());
	}

}
