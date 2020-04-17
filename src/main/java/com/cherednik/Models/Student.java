package com.cherednik.Models;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
@Proxy(lazy = false)

public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String studentName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "student_group",
            joinColumns = @JoinColumn(name = "studentsGroup_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private List<Group> groups = new ArrayList<>();

    public Student() {
    }

    public Student(String studentName) {
        this.studentName = studentName;
    }

    public void addGroup(Group group) {
        groups.add(group);
        group.getStudents().add(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + studentName + '\'' +
                ", groups=" + groups +
                '}';
    }
}
