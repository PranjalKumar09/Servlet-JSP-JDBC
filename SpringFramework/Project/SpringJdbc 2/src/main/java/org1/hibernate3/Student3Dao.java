package org1.hibernate3;

import java.util.List;

public interface Student3Dao {
    public int saveStudent(Student3 student);
    public Student3 getStudent(int id);
    public List<Student3> getAllStudents();
    public int updateStudent(Student3 student);
    public int deleteStudent(Student3 student);

}
