package cz.cvut.fit.rest.resources;

import cz.cvut.fit.rest.model.Student;
import cz.cvut.fit.rest.model.StudentsDAO;
import cz.cvut.fit.rest.model.Subject;
import cz.cvut.fit.rest.model.SubjectsDAO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.StreamingOutput;

import java.util.HashMap;
import java.util.List;

@Path("/subjects")
public class SubjectsResource {

    SubjectsDAO inst = SubjectsDAO.inst;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Subject> getSubjects() {
        //var m =  new HashMap<Integer, Student>();
        //m.put(1,new Student(1,"jan"));
        //inst.addSubject(new Subject(1,"ag1",m));
        return inst.allSubjects();
    }

    @POST
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Subject> createSubject(String name) {
        inst.addSubject(name);
        return inst.allSubjects();
    }

    @PUT
    @Path("/{id}")
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Subject updateSubject(@PathParam("id") int id, String name) {
        try {
            inst.getSubjectById(id);
        }
        catch(IllegalArgumentException e) {
            System.out.println("Subject doesnt exist.");
            return inst.getSubjectById(id);
        }
        inst.updateSubject(id,name);
        return inst.getSubjectById(id);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Subject> deleteSubject(@PathParam("id") int id) {
        try {
            inst.getSubjectById(id);
        }
        catch(IllegalArgumentException e) {
            System.out.println("Subject doesnt exist.");
            return inst.allSubjects();
        }
        inst.deleteSubjectById(id);
        return inst.allSubjects();
    }

    @GET
    @Path("/{subjectId}/students")
    public List<Student> getStudentsFromSubject(@PathParam("subjectId") int subjectId) {
        try {
            inst.getSubjectById(subjectId);
        }
        catch(IllegalArgumentException e) {
            System.out.println("Subject doesnt exist.");
            //todo
        }
        return inst.getStudentsById(subjectId);
    }

    @POST
    @Path("/{subjectId}/students/{studentId}")
    public List<Student> addStudentToSubject(@PathParam("subjectId") int subjectId, @PathParam("studentId") int studentId) {
        try {
            inst.getSubjectById(subjectId);
        }
        catch(IllegalArgumentException e) {
            System.out.println("Cant assign to nonexistent subject");
            return inst.getStudentsById(subjectId);
        }
        Student student;
        try {
            student = StudentsDAO.inst.getStudentById(studentId);
        }
        catch(IllegalArgumentException e) {
            System.out.println("Cant assign nonexistent student to subject.");
            return inst.getStudentsById(subjectId);
        }
        inst.getSubjectById(subjectId).addStudent(student);
        return inst.getStudentsById(subjectId);
    }

    @DELETE
    @Path("/{subjectId}/students/{studentId}")
    public List<Student> deleteStudentFromSubject(@PathParam("subjectId") int subjectId, @PathParam("studentId") int studentId) {
        try {
            inst.getSubjectById(subjectId);
        }
        catch(IllegalArgumentException e) {
            System.out.println("Cant remove from nonexistent subject.");
            return inst.getStudentsById(subjectId);
        }
        Student s = new Student();
        try {
            s = StudentsDAO.inst.getStudentById(studentId);
        }
        catch(IllegalArgumentException e) {
            System.out.println("Cant remove nonexistent student.");
            return inst.getStudentsById(subjectId);
        }
        try {
            inst.getSubjectById(subjectId).getStudent(s.getId());
        }
        catch(IllegalArgumentException e) {
            System.out.println("This student doesnt goes to this subject.");
            return inst.getStudentsById(subjectId);
        }
        inst.getSubjectById(subjectId).deleteStudent(s.getId());
        return inst.getStudentsById(subjectId);
    }
}