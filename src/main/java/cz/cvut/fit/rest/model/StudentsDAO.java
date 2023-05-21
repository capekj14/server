package cz.cvut.fit.rest.model;

import java.util.*;
import java.util.function.Consumer;

public class StudentsDAO {

    public static final StudentsDAO inst = new StudentsDAO();

    private HashMap<Integer, Student> students = new HashMap<>();

    public List<Student> allStudents() {
        return new ArrayList(students.values());
    }

    public int findMaxKey(){
        if(students.isEmpty()){
            return 0;
        }
        Map.Entry<Integer, Student> maxEntry = null;
        for (var entry : students.entrySet()) {
            if (maxEntry == null || entry.getKey() > maxEntry.getKey()) {
                maxEntry = entry;
            }
        }
        return maxEntry.getKey();
    }
    public Student getStudentById(int id) {
        if(!students.containsKey(id))
            throw new IllegalArgumentException();
        return students.get(id);
    }

    public Student deleteStudentById(int id) {
        return students.remove(id);
    }

    public Student addStudent(String name) {
        int id = findMaxKey() + 1; //mock generovani ID
        Student stud = new Student(id, name);
        return students.put(id, stud);
    }

    public void deleteStudentByName(String name) {
        Consumer<Student> studentConsumer = (student) -> students.remove(student.getId());
        students.values().stream()
                .filter(e -> e.getName().equals(name))
                .findFirst()
                .ifPresent(studentConsumer);
    }

    public Student updateStudent(int id, String name) {
        Student s = students.get(id);
        s.setName(name);
        return s;
    }
}
