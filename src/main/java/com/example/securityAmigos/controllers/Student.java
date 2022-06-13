package com.example.securityAmigos.controllers;

public class Student {
    private final long studentId;
    private final String studentName;

    public long getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public Student(long studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }

}
