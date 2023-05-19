package cz.cvut.fit.rest.resources;

import cz.cvut.fit.rest.model.Student;
import cz.cvut.fit.rest.model.StudentsDAO;
import cz.cvut.fit.rest.model.Subject;
import cz.cvut.fit.rest.model.SubjectsDAO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/subjects")
public class SubjectsResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public List<Subject> allSubjects() {
        return SubjectsDAO.inst.allSubjects();
    }
}