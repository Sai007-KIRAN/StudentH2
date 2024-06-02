package com.example.school.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

import com.example.school.model.Student;
import com.example.school.service.StudentH2Service;

@RestController
public class StudentController {
    @Autowired
    public StudentH2Service shs;

    @GetMapping("/students")
    public ArrayList<Student> allStudent() {
        return shs.allStudent();
    }

    @GetMapping("/students/{studentId}")
    public Student eachStudent(@PathVariable("studentId") int studentId) {

        return shs.eachStudent(studentId);
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student students) {
        return shs.addStudent(students);
    }

    @PostMapping("/students/bulk")
    public String addMultipleStudents(@RequestBody ArrayList<Student> studentsList) {
        return shs.addMultipleStudents(studentsList);
    }

    @PutMapping("/students/{studentId}")
    public Student updateStudent(@PathVariable("studentId") int studentId, @RequestBody Student updateStudent) {
        return shs.updateStudent(studentId, updateStudent);
    }

    @DeleteMapping("/students/{studentId}")
    public void deleteStudent(@PathVariable int studentId) {
        shs.deleteStudent(studentId);
    }
}