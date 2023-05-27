package org.acme.dbapi;

import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Tuple;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.acme.model.Course;
import org.acme.model.Lesson;
import org.acme.model.Test.Test;
import org.acme.model.User;
import org.acme.model.enums.Status;
import org.jboss.logging.Logger;

@ApplicationScoped
public class DbResource {
  private static final Logger logger = Logger.getLogger(DbResource.class);
  private static final String STARTS = "Get request starts";

  @Inject PgPool client;

  public Course getCourseInfoByID(Long id) {
    logger.info(STARTS);
    return client
        .preparedQuery(
            "select * from courses inner join course_level cl on cl.id = courses.course_level\n"
                + "inner join course_status cs on cs.id = courses.course_status\n"
                + "inner join lessons l on courses.id = l.course_id\n"
                + "inner join user_course uc on courses.id = uc.course_id\n"
                + "inner join users u on u.id = uc.user_id\n"
                + "inner join tests t on courses.id = t.course_id"
                + " where courses.id = $1")
        .execute(Tuple.of(id))
        .onItem()
        .transform(Course::fromSet)
        .await()
        .indefinitely();
  }

  public User getUserInfoByID(Long id) {
    logger.info(STARTS);
    return client
        .preparedQuery(
            "select * from users inner join user_roles ur on ur.id = users.role_id inner join"
                + " courses c on users.id = c.professor_id inner join course_level cl on cl.id ="
                + " c.course_level inner join course_status cs on cs.id = c.course_status where"
                + " users.id = $1")
        .execute(Tuple.of(id))
        .onItem()
        .transform(User::from)
        .await()
        .indefinitely();
  }

  public Lesson getLessonInfoByID(Long id) {
    logger.info(STARTS);
    return client
        .preparedQuery(
            "select * from lessons inner join materials m on lessons.id = m.lesson_id where"
                + " lessons.id = $1")
        .execute(Tuple.of(id))
        .onItem()
        .transform(Lesson::fromRowSet)
        .await()
        .indefinitely();
  }

  public Test getTestInfoByID(Long id) {
    logger.info(STARTS);
    return client
        .preparedQuery(
            "select * from tests inner join questions q on tests.id = q.test_id inner join answers"
                + " a on q.id = a.question_id  where tests.id = $1")
        .execute(Tuple.of(id))
        .onItem()
        .transform(Test::fromRowSet)
        .await()
        .indefinitely();
  }

  public Multi<Course> getAllCourses() {
    logger.info(STARTS);
    return client
        .query(
            "select * from courses inner join course_status cs on cs.id = courses.course_status"
                + (" inner join course_level cl on cl.id = courses.course_level limit 9"))
        .execute()
        .onItem()
        .transformToMulti(set -> Multi.createFrom().iterable(set))
        .onItem()
        .transform(Course::shortDescription)
        .onFailure()
        .invoke(e -> logger.error("DB error", e));
  }

  public Course createCourse(Course course) {
    logger.info(STARTS);
    int levelID = 0;
    int statusID;
    switch (course.getLevel()) {
      case ZERO:
        levelID = 1;
        break;
      case FIRST:
        levelID = 2;
        break;
      case SECOND:
        levelID = 3;
        break;
      case THIRD:
        levelID = 4;
        break;
    }
    if (course.getStatus() == Status.PENDING) {
      statusID = 1;
    } else {
      statusID = 2;
    }
    return client
        .preparedQuery(
            "insert into courses(name, annotation, course_status, professor_id, course_level)"
                + " values ($1,$2,$3,$4,$5,$6) returning *")
        .execute(
            Tuple.of(
                course.getName(),
                course.getAnnotation(),
                statusID,
                course.getProfessorID(),
                levelID))
        .onItem()
        .transform(Course::fromSet)
        .await()
        .indefinitely();
  }
}
