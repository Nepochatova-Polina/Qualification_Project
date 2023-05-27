package org.acme;

import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.smallrye.common.annotation.Blocking;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.acme.model.Course;
import org.acme.model.User;
import org.acme.model.enums.Level;
import org.acme.model.enums.Role;
import org.acme.model.enums.Status;

@Path("/hello")
@ApplicationScoped
public class HelloRes {

  @Inject
  @Location("account.html")
  Template form;

  @Inject
  @Location("course.html")
  Template course;

//  @Inject
//  @Location("login.html")
//  Template login;

  @GET
  @Path("/account")
  @Produces(MediaType.TEXT_HTML)
  @Blocking
  public TemplateInstance getAccount() {
    List list = new ArrayList();
    list.add(new Course(1L,1L,"MAth", "Maths", "f", Status.PENDING, Level.FIRST));
    return form.data(
        "User", new User(1L,"Ivan", "Ivanov", "email", Role.PROFESSOR, list));
  }

  @GET
  @Path("/course")
  @Produces(MediaType.TEXT_HTML)
  @Blocking
  public TemplateInstance getcourse() {
    return course.data("students", "serverApiDto");
  }

//  @GET
//  @Path("/login")
//  @Produces(MediaType.TEXT_HTML)
//  @Blocking
//  public TemplateInstance getlogin() {
//    return login.data("students", "serverApiDto");
//  }
}
