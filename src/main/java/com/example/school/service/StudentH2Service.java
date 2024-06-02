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

    @Override
    public Student addStudent(Student students) {
        jd.update("Insert INTO STUDENT(studentName, gender, standard) VALUES(?, ?, ?)", students.getStudentName(),
                students.getGender(), students.getStandard());
        Student addingStudent = jd.queryForObject(
                "SELECT * FROM STUDENT WHERE studentName = ? AND gender = ? AND standard = ?", new StudentRowMapper(),
                students.getStudentName(), students.getGender(), students.getStandard());
        return addingStudent;
    }

    @Override
    public String addMultipleStudents(ArrayList<Student> studentsList) {
        for (Student eachStudent : studentsList) {
            jd.update("insert into student(studentName,gender,standard) values (?,?,?)",
                    eachStudent.getStudentName(), eachStudent.getGender(), eachStudent.getStandard());
        }
        String responseMessage = String.format("Successfully added %d students", studentsList.size());
        return responseMessage;
    }

    @Override
    public Student updateStudent(int studentId, Student updateStudent) {
        try {
            Student existing = jd.queryForObject("SELECT * FROM STUDENT WHERE playerId = ?", new StudentRowMapper(),
                    studentId);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (updateStudent.getStudentName() != null) {
            jd.update("UPDATE STUDENT SET studentName = ? WHERE studentId = ?", updateStudent.getStudentName(),
                    studentId);
        }

        if (updateStudent.getGender() != null) {
            jd.update("UPDATE STUDENT SET gender = ? WHERE studentId = ?", updateStudent.getGender(),
                    studentId);
        }

        if (updateStudent.getStandard() != null) {
            jd.update("UPDATE STUDENT SET standard = ? WHERE studentId = ?", updateStudent.getStandard(),
                    studentId);
        }
        return eachStudent(studentId);
    }

    @Override
    public void deleteStudent(int studentId) {
        jd.update("DELETE FROM STUDENT WHERE studentId = ?", studentId);
    }

}