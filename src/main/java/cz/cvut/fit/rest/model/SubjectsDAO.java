package cz.cvut.fit.rest.model;


import cz.cvut.fit.rest.model.Subject;

import java.security.InvalidParameterException;
import java.util.*;

public class SubjectsDAO {

    public static final SubjectsDAO inst = new SubjectsDAO();

    private NavigableMap<Integer, Subject> subjects = new TreeMap<>();

    public List<Subject> allSubjects() {
        return new ArrayList(subjects.values());
    }

    public Subject getSubjectById(int id) {
        if(!subjects.containsKey(id))
            throw  new InvalidParameterException();
        return subjects.get(id);
    }

    public Subject addSubject(String subjectName) {
        int id = findMaxKey() + 1;
        Subject s = new Subject();
        s.setId(id);
        s.setName(subjectName);
        return subjects.put(id, s);
    }

    public Subject deleteSubjectById(int id) {
        return subjects.remove(id);
    }

    public void updateSubject(int id, String name) {
        Subject s = subjects.get(id);
        s.setName(name);
        subjects.replace(id, s);
    }

    public int findMaxKey(){
        if(subjects.isEmpty()){
            return 0;
        }
        Map.Entry<Integer, Subject> maxEntry = null;
        for (var entry : subjects.entrySet()) {
            if (maxEntry == null || entry.getKey() > maxEntry.getKey()) {
                maxEntry = entry;
            }
        }
        return maxEntry.getKey();
    }

    public List<Student> getStudentsById(int id) {
        return new ArrayList<>(subjects.get(id).getStudents().values());
    }

    public void updateStudentOnSubjects(Student student) {
        for (var entry: subjects.entrySet()) {
            if(entry.getValue().getStudents().containsKey(student.getId()))
                entry.getValue().updateStudent(student);
        }
    }

    public void deleteStudentFromSubjects(int id) {
        for (var entry: subjects.entrySet()) {
            if(entry.getValue().getStudents().containsKey(id))
                entry.getValue().deleteStudent(id);
        }
    }
    /*public Subject replaceSubject(Subject subject) {
        return subjects.replace(subject.getId(), subject);
    }*/
}
