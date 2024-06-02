package com.example.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.dao.EmptyResultDataAccessException;
import java.util.*;
import com.example.school.model.StudentRowMapper;
import com.example.school.model.Student;
import com.example.school.repository.StudentRepository;
// Write your code here

@Service
public class StudentH2Service implements StudentRepository {

    @Autowired
    public JdbcTemplate jd;

    @Override
    public ArrayList<Student> allStudent() {
        List<Student> all = jd.query("SELECT * FROM STUDENT", new StudentRowMapper());
        ArrayList<Student> AllStudents = new ArrayList<>(all);
        return AllStudents;
    }

    @Override
    public Student eachStudent(int studentId) {
        try {
            Student each = jd.queryForObject("SELECT * FROM STUDENT WHERE studentId = ?", new StudentRowMapper(),
                    studentId);
            return each;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}