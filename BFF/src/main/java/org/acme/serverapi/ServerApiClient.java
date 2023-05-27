package org.acme.serverapi;

import org.acme.model.Course;
import org.acme.model.Lesson;
import org.acme.model.User;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

@RegisterRestClient(configKey = "professor-api")
public interface ServerApiClient {

  @GET
  @Path("/courses")
  List<Course> getAllCourses();

  @GET
  @Path("/account/{id}")
  User getUserInfo(Long id);

  @GET
  @Path("/course/{id}")
  Course getCourseInfoByID(Long id);

  @GET
  @Path("/lesson/{id}")
  Lesson getLessonInfoByID(Long id);

  @POST
  @Path("/new-course")
  Course createNewCourse(Course course);

  @POST
  @Path("/new-lesson")
  Course createNewLesson(Lesson lesson);
}
