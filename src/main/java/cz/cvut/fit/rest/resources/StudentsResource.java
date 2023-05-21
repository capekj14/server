package cz.cvut.fit.rest.resources;

import cz.cvut.fit.rest.model.Student;
import cz.cvut.fit.rest.model.StudentsDAO;
import cz.cvut.fit.rest.model.SubjectsDAO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/students")
public class StudentsResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> allStudents() {
        return StudentsDAO.inst.allStudents();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    //@Consumes(MediaType.APPLICATION_JSON)
    public List<Student> addStudent(String name) {
        StudentsDAO.inst.addStudent(name);
        return StudentsDAO.inst.allStudents();
    }

    @PUT
    @Path("/{id}")
    public Student updateStudent(@PathParam("id") int id, String name) {
        try {
            StudentsDAO.inst.getStudentById(id);
        }
        catch(IllegalArgumentException e) {
            System.out.println("Student doesnt exist");
            return StudentsDAO.inst.getStudentById(id);
        }

        Student s = StudentsDAO.inst.updateStudent(id, name);
        SubjectsDAO.inst.updateStudentOnSubjects(s);
        return StudentsDAO.inst.getStudentById(id);
    }

    @DELETE
    @Path("/{id}")
    public List<Student> deleteStudent(@PathParam("id") int id) {
        try {
            StudentsDAO.inst.getStudentById(id);
        }
        catch(IllegalArgumentException e) {
            System.out.println("Student doesnt exist");
            return StudentsDAO.inst.allStudents();
        }

        StudentsDAO.inst.deleteStudentById(id);
        SubjectsDAO.inst.deleteStudentFromSubjects(id);
        return StudentsDAO.inst.allStudents();
    }

}

