package com.cherednik;

import com.cherednik.Dao.GroupDao;
import com.cherednik.Dao.StudentDao;
import com.cherednik.Models.Group;
import com.cherednik.Models.Student;

import java.util.ArrayList;
import java.util.List;

public class DataService {

    private StudentDao studentDao = new StudentDao();
    private GroupDao groupDao = new GroupDao();

    public void clear() {
        studentDao.clear();
        groupDao.clear();
    }

    public void addStudent(Student student) {
        studentDao.addStudent(student);
    }

    public void addGroup(int id, String name) {
        groupDao.addGroup(new Group(id, name));
    }

    public Group getGroup(int id){
        return groupDao.getGroup(id);
    }

    public List<String> getStudentsByGroup(String groupName){
        List <Student> students = studentDao.getStudentByGroup(groupName);
        List <String> s = new ArrayList<>();
        for (Student student :students) {
            s.add(student.getStudentName());
        }
        return s;
    }

    public List<String> getGroupsByStudent (String studentName) {
        List<Group> groups = studentDao.getGroupByStudent(studentName);
        List<String> s = new ArrayList<>();
        for (Group group : groups) {
            s.add(group.getGroupName());
        }
        return s;
    }

    void close(){
        studentDao.close();
        groupDao.close();
    }
}
