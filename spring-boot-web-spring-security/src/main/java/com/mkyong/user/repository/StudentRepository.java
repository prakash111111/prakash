package com.mkyong.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mkyong.controller.Student;


@Repository("studentRepository")
public interface StudentRepository extends JpaRepository<Student, Long> {
    
    Student findByName(String username);

}
