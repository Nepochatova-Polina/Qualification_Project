package org.acme.api;

import static org.acme.Utility.Constants.START;

import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.smallrye.common.annotation.Blocking;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.acme.model.Course;
import org.acme.model.Lesson;
import org.acme.model.User;
import org.acme.serverapi.ServerApiService;
import org.jboss.logging.Logger;

@ApplicationScoped
@Path("/api/v1")
public class ApiResource {

  private static final Logger logger = Logger.getLogger(ApiResource.class);

  @Inject ServerApiService serverApiService;

  @Inject
  @Location("main.html")
  Template main;

  @Inject
  @Location("account.html")
  Template accountTemplate;

  @Inject
  @Location("course.html")
  Template courseTemplate;

  @Inject
  @Location("lesson.html")
  Template lessonTemplate;

  @GET
  @Path("/courses")
  @Produces(MediaType.TEXT_HTML)
  @Blocking
  public TemplateInstance getCourses() {
    logger.info(START);

    List<Course> courses = serverApiService.getAllCoursesFromServer();
    return main.data("courses", courses);
  }

  @GET
  @Path("/account/{id}")
  @Produces(MediaType.TEXT_HTML)
  @Blocking
  public TemplateInstance getUserAccountInfo(@PathParam("id") Long id) {
    logger.info(START);

    User user = serverApiService.getUserInfoFromServer(id);
    logger.info(user.getFirstname());
    return accountTemplate.data("User", user);
  }

  @GET
  @Path("/course/{id}")
  @Produces(MediaType.TEXT_HTML)
  @Blocking
  public TemplateInstance getCourseInfoByID(@PathParam("id") Long id) {
    logger.info(START);

    Course course = serverApiService.getCourseInfoByID(id);
    logger.info(course.getName());
    return courseTemplate.data("Course", course);
  }

  @GET
  @Path("/lesson/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  @Blocking
  public TemplateInstance getLessonInfoByID(@PathParam("id") Long id) {
    logger.info(START);
    Lesson lesson = serverApiService.getLessonInfoByID(id);
    return lessonTemplate.data("Lesson", lesson);
  }

  @POST
  @Path("/new-course")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Blocking
  public TemplateInstance createNewCourse(Course course) {
    logger.info(START);

    course.setCode("WGfoHq");
    course.setProfessorID(3L);
    Course course1 = serverApiService.createNewCourse(course);
    return courseTemplate.data("Course", course1);
  }

  @POST
  @Path("/new-lesson")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Blocking
  public TemplateInstance createNewLesson(Lesson lesson) {
    logger.info(START);

    Course course = serverApiService.createNewLesson(lesson);
    return courseTemplate.data("Course", course);
  }
}
