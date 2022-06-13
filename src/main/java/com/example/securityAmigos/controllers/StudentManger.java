package com.example.securityAmigos.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/studentmanager")
public class StudentManger {
    Student[] students = {new Student(1,"nabawy"),
            new Student(2,"younis"),
            new Student(3,"mohamed"),};
    public List<Student> list = new ArrayList<>(Arrays.asList(students));

    @GetMapping("/students")
    public List<Student> getAllStudents(){
        return list;
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable("id") long id ) throws ClassNotFoundException{
        Student student = null;
        for(Student s : list)
            if(s.getStudentId() == id)
                return s;
        return student;
    }
    @PostMapping()
    public void addStudent(@RequestBody Student student){
        list.add(student);
    }
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable("id") long id){
        Student student = null;
        for(Student s : list){
            if(s.getStudentId() == id){
                student = s;
                break;
            }
        }
        if(student != null)
        list.remove(student);
    }
    @PutMapping("/{id}")
    public void updateStudent(@PathVariable("id") long id ){

    }

}
