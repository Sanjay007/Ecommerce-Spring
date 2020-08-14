package com.ecom.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import com.ecom.app.model.Role;
import com.ecom.app.model.RoleName;
import com.ecom.app.repository.RoleRepository;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EntityScan(basePackageClasses = { EcommApplication.class, Jsr310JpaConverters.class })
public class EcommApplication {
	
	@Autowired
	RoleRepository roleRepository;

	@PostConstruct
	void init() {
		Role r = new Role();
		r.setName(RoleName.ROLE_USER);
		roleRepository.save(r);

		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	public static void main(String[] args) {

		SpringApplication.run(EcommApplication.class, args);
	}
}
