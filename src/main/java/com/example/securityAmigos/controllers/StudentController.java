package com.example.securityAmigos.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    Student[] students = {new Student(1,"nabawy"),
            new Student(2,"younis"),
            new Student(3,"mohamed"),};
    public List<Student> list = new ArrayList<>(Arrays.asList(students));

    @GetMapping("/{id}")
    public Student getStudent (@PathVariable("id") long id){
        for(Student s : list){
            if(s.getStudentId() == id){
                return s;
            }
        }
        return null;
    }

}
