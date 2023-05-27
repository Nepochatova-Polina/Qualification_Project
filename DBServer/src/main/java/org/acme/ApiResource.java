package org.acme;

import io.smallrye.mutiny.Multi;
import org.acme.dbapi.DbResource;
import org.acme.model.Course;
import org.acme.model.Lesson;
import org.acme.model.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("api/v1")
@ApplicationScoped
public class ApiResource {

  @Inject DbResource dbResource;

  @GET
  @Path("/courses")
  @Produces(MediaType.APPLICATION_JSON)
  public Multi<Course> getCourses() {
    return dbResource.getAllCourses();
  }

  @GET
  @Path("/account/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public User getUserInfoByID(@PathParam("id") Long id) {
    return dbResource.getUserInfoByID(id);
  }
  @GET
  @Path("/course/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Course getCourseInfoByID(@PathParam("id") Long id) {
    return dbResource.getCourseInfoByID(id);
  }
  @GET
  @Path("/lesson/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Lesson getLessonInfoByID(@PathParam("id") Long id) {
    return dbResource.getLessonInfoByID(id);
  }
  @POST
  @Path("/new-course")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Course createCourse(Course course){
    return dbResource.createCourse(course);
  }

}
