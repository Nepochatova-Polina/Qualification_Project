package com.example.epamfinalproject.Controllers.Servlets;

import com.example.epamfinalproject.Database.Implementations.*;
import com.example.epamfinalproject.Entities.*;
import com.example.epamfinalproject.Entities.Enums.UserRole;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@WebServlet("/multiPartServlet")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class HelloServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User_Implementation ui = new User_Implementation();
        Cruise_Implementation ci = new Cruise_Implementation();
        Order_Implementation oi = new Order_Implementation();
//        ci.getCruiseByID(1);
//        oi.createOrder(new Order(ci.getCruiseByID(1),ui.getUserByID(1)));
        ui.getUserByLogin("anna_olsef");
//        oi.getOrderByID(1);
//        ui.registerUser(new User.UserBuilder()
//                .firstName("Anna")
//                .lastName("Olsef")
//                .role(UserRole.CLIENT)
//                .login("anna_olsef")
//                .password("olga12656")
//                .build());
//        Staff_Implementation si = new Staff_Implementation();
//        si.registerStaff(new Staff("Ivanov","Ivan",1));
//        si.registerStaff(new Staff("Petrov","Piter",1));
//        ci.getCruiseByID(1);
//        ui.getClientUsers();
//        Blob blob = new
//        ui.updateUserPassport(1,Blo );
//        request.setAttribute("message", "<script>alert('Hello gold')</script>");
//        ServletContext servletContext = getServletContext();
//        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/hello.jsp");
//        requestDispatcher.forward(request, response);

//        response.setStatus(HttpServletResponse.SC_OK);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User_Implementation ui = new User_Implementation();
//        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
//        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
//        InputStream fileContent = filePart.getInputStream();
//        long size = filePart.getSize();
//        ui.updateUserPassport(1, fileContent,size);
        InputStream stream = null;
          Blob blob =  ui.getPassport(1);
        try {
           stream = blob.getBinaryStream();
           long size = blob.length();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        response.getWriter().print("The file uploaded successfully.");
    }

}
