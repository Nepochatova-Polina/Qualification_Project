package com.example.epamfinalproject.Controllers.Commands;

import com.example.epamfinalproject.Controllers.MessageKeys;
import com.example.epamfinalproject.Controllers.Paths;
import com.example.epamfinalproject.Database.FieldKey;
import com.example.epamfinalproject.Entities.Enums.UserRole;
import com.example.epamfinalproject.Entities.User;
import com.example.epamfinalproject.Services.UserService;
import com.example.epamfinalproject.Utility.Encryptor;
import com.example.epamfinalproject.Utility.Validation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class SignUpCommand implements Command{
    private static final Logger log = LogManager.getLogger(SignUpCommand.class);
    @Override
    public String execute(HttpServletRequest request) {
        log.debug("Command Starts");
        String password = request.getParameter(FieldKey.PASSWORD);
        String confirmPassword = request.getParameter(FieldKey.CONFIRM_PASSWORD);

        if(!password.equals(confirmPassword)){
            request.getSession().setAttribute("message", MessageKeys.SIGN_UP_CONFIRMATION_FAILED);
            log.trace("Password Confirmation Failed");
        }

        User user = new User.UserBuilder()
                .firstName(request.getParameter(FieldKey.FIRST_NAME))
                .lastName(request.getParameter(FieldKey.LAST_NAME))
                .login(request.getParameter(FieldKey.LOGIN))
                .password(Encryptor.encrypt(password))
                .role(UserRole.CLIENT)
                .build();
        UserService userService = new UserService();

        if(!Validation.validateUserFields(user)){
            request.getSession().setAttribute("message", MessageKeys.SIGN_UP_INVALID);
            log.trace("Invalid User Parameters");
            return "redirect:" + Paths.SIGN_UP_PAGE;
        }
        if(userService.getUserByLogin(user.getLogin()) != null){
            request.getSession().setAttribute("message", MessageKeys.SIGN_UP_EXISTS);
            log.trace("User already exists");
            return "redirect:" + Paths.LOGIN_PAGE;
        }
            userService.registerUser(user);
        log.debug("Command finished");

        return "redirect:" + Paths.CLIENT_PAGE;
    }

}
