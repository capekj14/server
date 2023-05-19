package cz.cvut.fit.rest.model;


import cz.cvut.fit.rest.model.Subject;

import java.util.*;

public class SubjectsDAO {

    public static final SubjectsDAO inst = new SubjectsDAO();

    private NavigableMap<Integer, Subject> subjects = new TreeMap<>();

    public List<Subject> allSubjects() {
        return new ArrayList(subjects.values());
    }

    public Subject getSubjectById(int id) {
        return subjects.get(id);
    }

    public Subject addSubject(Subject subject) {
        return subjects.put(subject.getId(), subject);
    }

    public Subject deleteSubjectById(int id) {
        return subjects.remove(id);
    }

    /*public Subject replaceSubject(Subject subject) {
        return subjects.replace(subject.getId(), subject);
    }*/
}
