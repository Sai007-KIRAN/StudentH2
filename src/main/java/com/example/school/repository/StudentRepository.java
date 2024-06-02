// Write your code here

package com.example.school.repository;

import java.util.*;
import com.example.school.model.Student;

public interface StudentRepository {
    ArrayList<Student> allStudent();

    Student eachStudent(int studentId);

    Student addStudent(Student students);

}