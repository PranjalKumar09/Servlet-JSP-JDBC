package org.hibernate3;

import java.util.List;

public class Student3DaoImpl implements Student3Dao {


    @Override
    public int saveStudent(Student3 student) {
        return 0;
    }

    @Override
    public Student3 getStudent(int id) {
        return null;
    }

    @Override
    public List<Student3> getAllStudents() {
        return List.of();
    }

    @Override
    public int updateStudent(Student3 student) {
        return 0;
    }

    @Override
    public int deleteStudent(Student3 student) {
        return 0;
    }
}
