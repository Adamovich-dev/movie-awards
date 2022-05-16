package com.adamovichdev.movieawards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class MovieAwardsApplication {

//	@Autowired
//	private UserRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(MovieAwardsApplication.class, args);
	}

	@PostConstruct
	public void initUsers() {
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		String pwd2 = "pwd2";
//		String encode = encoder.encode(pwd2);
//		User user2 = new User();
//		user2.setUserName("user2");
//		user2.setPassword(encode);
//
//		String pwd3 = "pwd3";
//		String encode3 = encoder.encode(pwd3);
//		User user3 = new User();
//		user3.setUserName("user3");
//		user3.setPassword(encode3);
//
//		repository.saveAll(List.of(user2, user3));
//		System.out.println("id ползователя = " + user2.getId() + " pswd = "+ encode);
//		System.out.println("id ползователя = " + user3.getId() + " pswd = "+ encode3);
	}
}
