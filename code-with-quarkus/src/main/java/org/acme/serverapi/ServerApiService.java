package org.acme.serverapi;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.acme.model.Course;
import org.acme.model.Lesson;
import org.acme.model.User;
import org.jboss.logging.Logger;

import static org.acme.Utility.Constants.TRYING_SERVER;

@ApplicationScoped
public class ServerApiService {

  private static final Logger logger = Logger.getLogger(ServerApiService.class);

  @Inject ServerApiResource serverApiResource;

  public List<Course> getAllCoursesFromServer() {
    logger.info(TRYING_SERVER);
    return serverApiResource.getAllCourses();
  }

  public User getUserInfoFromServer(Long id) {
    logger.info(TRYING_SERVER);
    return serverApiResource.getUserInfo(id);
  }
  public Course getCourseInfoByID(Long id){
    logger.info(TRYING_SERVER);
    return serverApiResource.getCourseInfoByID(id);
  }
  public Lesson getLessonInfoByID(Long id){
    logger.info(TRYING_SERVER);
    return serverApiResource.getLessonInfoByID(id);
  }

  public Course createNewCourse( Course course){
    logger.info(TRYING_SERVER);
   return serverApiResource.createNewCourse(course);
  }
  public Course createNewLesson(Lesson lesson){
    logger.info(TRYING_SERVER);
    return serverApiResource.createNewLesson(lesson);
  }
}
