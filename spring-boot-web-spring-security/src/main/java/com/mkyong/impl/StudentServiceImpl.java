package com.mkyong.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mkyong.controller.Student;
import com.mkyong.service.StudentService;
import com.mkyong.user.repository.StudentRepository;

@Service("studentService")
@Transactional
public class StudentServiceImpl implements StudentService{

	@Autowired
	StudentRepository studentRepository;
	
	public Student findByName(String username) {
		return studentRepository.findByName(username);
	}
	
	@Override
	public boolean isUserExist(Student student) {
		return findByName(student.getUsername()) != null;
	}

	@Override
	public void saveUser(Student student) {
		studentRepository.save(student);
		
	}

}
