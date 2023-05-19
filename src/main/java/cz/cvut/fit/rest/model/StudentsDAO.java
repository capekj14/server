package cz.cvut.fit.rest.model;

import java.util.*;

public class StudentsDAO {

    public static final StudentsDAO inst = new StudentsDAO();

    private NavigableMap<Integer, Student> students = new TreeMap<>();

    public List<Student> allStudents () {
        return new ArrayList(students.values());
    }

    public Student getStudentById(int id) {
        return students.get(id);
    }

    public Student deleteStudentById(int id) {
        return students.remove(id);
    }

    public Student addStudent(String name) {
        int id = students.lastKey() + 1; //mock generovani ID
        Student stud = new Student(id, name);
        return students.put(id, stud);
    }
}
