package com.mkyong.service;

import com.mkyong.controller.Student;

public interface StudentService {

	boolean isUserExist(Student student);

	void saveUser(Student student);

}
