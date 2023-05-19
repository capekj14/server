package cz.cvut.fit.rest.resources;

import cz.cvut.fit.rest.model.StudentsDAO;
import cz.cvut.fit.rest.model.Student;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.util.List;

@Path("/students")
public class StudentsResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public List<Student> allStudents() {
        return StudentsDAO.inst.allStudents();
    }

    @POST
    public void createStudent(String name, @Context UriInfo info) {
        StudentsDAO.inst.addStudent(name);
        // todo:
    }

}

