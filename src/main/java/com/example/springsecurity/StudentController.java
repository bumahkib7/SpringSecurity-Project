package com.example.springsecurity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {



	private static final List<Student> STUDENTS = Arrays.asList(
			new Student(1, "John Doe"),
			new Student(2, "Jane Doe"),
			new Student(3, "Jack Doe")
	);
	@GetMapping("/{studentId}")
	public Student getStudent(@PathVariable("studentId") Integer studentId) {

		return STUDENTS.stream()
				.filter(student -> studentId.equals(student.getStudentId()))
				.findFirst()
				.orElseThrow(() -> new IllegalStateException("Student " + studentId + "not found"));
	}

}
