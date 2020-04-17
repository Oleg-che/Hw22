package com.cherednik.Models;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
@Proxy(lazy = false)

public class Group {
    @Id
    @Column
    private int id;

    @Column
    private String groupName;

    @ManyToMany(mappedBy = "groups", fetch = FetchType.EAGER)
    private List<Student> students = new ArrayList<>();

    public Group() {
    }

    public Group(int id, String groupName) {
        this.id = id;
        this.groupName = groupName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + groupName + '\'' +
                ", students=" + students +
                '}';
    }
}
