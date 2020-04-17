package com.cherednik;

import com.cherednik.Models.Student;

public class Main {
    public static void main(String[] args) {
        addData();
        showData();
    }
    private static void addData() {

        DataService dataService = new DataService();

        dataService.clear();

        dataService.addGroup(1,"Java");
        dataService.addGroup(2,"Jiu jitsu");
        dataService.addGroup(3,"JavaScript");

        Student student1 = new Student("Yoda");
        student1.addGroup(dataService.getGroup(1));
        student1.addGroup(dataService.getGroup(3));
        dataService.addStudent(student1);

        Student student2 = new Student("Shredder");
        student2.addGroup(dataService.getGroup(1));
        student2.addGroup(dataService.getGroup(2));
        dataService.addStudent(student2);

        Student student3 = new Student("Student");
        student3.addGroup(dataService.getGroup(2));
        student3.addGroup(dataService.getGroup(3));
        dataService.addStudent(student3);

        dataService.close();
    }

    private static void showData() {
        DataService dataService = new DataService();
        System.out.println("Students list: " + dataService.getStudentsByGroup("Jiu jitsu"));
        System.out.println("Groups list: " + dataService.getGroupsByStudent("Student"));
        dataService.close();
    }
}
