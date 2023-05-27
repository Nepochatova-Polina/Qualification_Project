package org.acme.serverapi;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import org.acme.model.Course;
import org.acme.model.Lesson;
import org.acme.model.User;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import static org.acme.Utility.Constants.RESPONSE_RECEIVED;
import static org.acme.Utility.Constants.START;

@ApplicationScoped
public class ServerApiResource {

  private static final Logger logger = Logger.getLogger(ServerApiResource.class);


  @RestClient ServerApiClient serverApiClient;

  public List<Course> getAllCourses() {
    logger.info(START);
    List<Course> courses = serverApiClient.getAllCourses();
    logger.info(RESPONSE_RECEIVED);
    return courses;
  }

  public User getUserInfo(Long id) {
    logger.info(START);
    User user = serverApiClient.getUserInfo(id);
    logger.info(RESPONSE_RECEIVED);
    return user;
  }

  public Course getCourseInfoByID(Long id) {
    logger.info(START);
    Course course = serverApiClient.getCourseInfoByID(id);
    logger.info(RESPONSE_RECEIVED);
    return course;
  }
  public Lesson getLessonInfoByID(Long id) {
    logger.info(START);
    Lesson lesson = serverApiClient.getLessonInfoByID(id);
    logger.info(RESPONSE_RECEIVED);
    return lesson;
  }

  public Course createNewCourse(Course course) {
    logger.info(START);
    Course course1 = serverApiClient.createNewCourse(course);
    logger.info(RESPONSE_RECEIVED);
    return course1;
  }
  public Course createNewLesson(Lesson lesson){
    logger.info(START);
    Course course = serverApiClient.createNewLesson(lesson);
    logger.info(RESPONSE_RECEIVED);
    return course;
  }
}
