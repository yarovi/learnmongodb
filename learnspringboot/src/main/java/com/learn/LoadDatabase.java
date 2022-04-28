package com.learn;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.stereotype.Component;

import com.learn.data.Employee;
import com.learn.data.Image;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Component
public class LoadDatabase {

	/*
	 * @Bean CommandLineRunner init(ChapterRepository repo) {
	 * 
	 * return args -> { Flux.just( new Chapter("Quick Start with java"), new
	 * Chapter("Reactive Web with Spring Boot"), new Chapter("...and more!"))
	 * .flatMap(repo::save) .subscribe(System.out::println); }; }
	 */

	private static String UPLOAD_ROOT = "upload-dir";

	/*
	 * @Bean CommandLineRunner setUp() throws IOException { return (args) -> {
	 * FileSystemUtils.deleteRecursively(new File(UPLOAD_ROOT));
	 * Files.createDirectory(Paths.get(UPLOAD_ROOT));
	 * FileCopyUtils.copy("Test file", new FileWriter(UPLOAD_ROOT +
	 * "/learning-spring-boot-cover.jpg")); FileCopyUtils.copy("Test file2", new
	 * FileWriter(UPLOAD_ROOT + "/learning-spring-boot-2nd-edition-cover.jpg"));
	 * FileCopyUtils.copy("Test file3", new FileWriter(UPLOAD_ROOT +
	 * "/bazinga.png")); }; }
	 */


	@Autowired
	ReactiveMongoOperations operations;
	
	@Bean
	CommandLineRunner init() {
		return args -> {
			operations.dropCollection(Image.class);
			operations.insert(new Image("1", "learning-spring-boot-cover.jpg"));
			operations.insert(new Image("2", "learning-spring-boot-2nd-edition-cover.jpg"));
			operations.insert(new Image("3", "bazinga.png"));
			operations.findAll(Image.class).doOnEach(image -> {
				System.out.println(image.toString());
			});
		};
	}
	
	@Bean
	CommandLineRunner init2( ) {
		return args -> {
			operations.dropCollection(Employee.class);
			Employee e1 = new Employee();
			e1.setId(UUID.randomUUID().toString());
			e1.setFirstName("Bilbo");
			e1.setLastName("Baggins");
			e1.setRole("burglar");
			operations.insert(e1);
			Employee e2 = new Employee();
			e2.setId(UUID.randomUUID().toString());
			e2.setFirstName("Frodo");
			e2.setLastName("Baggins");
			e2.setRole("ring bearer");
			operations.insert(e2);

			
		};
	}
}
