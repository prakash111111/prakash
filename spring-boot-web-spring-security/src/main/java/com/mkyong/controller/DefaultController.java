package com.mkyong.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.mkyong.service.StudentService;
import com.mkyong.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class DefaultController {

	public static final Logger logger = LoggerFactory.getLogger(DefaultController.class);

	@Autowired(required=true)
	StudentService studentService;
	
    @GetMapping("/")
    public String home1() {
        return "/home";
    }

    @GetMapping("/home")
    public String home() {
        return "/home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "/admin";
    }

    @GetMapping("/user")
    public String user() {
        return "/user";
    }

    @GetMapping("/about")
    public String about() {
        return "/about";
    }
    @RequestMapping(value = "/register/", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody Student student, UriComponentsBuilder ucBuilder) {
		logger.info("Creating User : {}", student);

		if (studentService.isUserExist(student)) {
			logger.error("Unable to create. A User with name {} already exist", student.getUsername());
			return new ResponseEntity(new CustomErrorType("Unable to create. A User with name " + 
					student.getUsername() + " already exist."),HttpStatus.CONFLICT);
		}
		studentService.saveUser(student);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/student/{id}").buildAndExpand(student.getUsername()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}


    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }

}
