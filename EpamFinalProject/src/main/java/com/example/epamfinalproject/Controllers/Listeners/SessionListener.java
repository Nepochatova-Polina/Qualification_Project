package com.example.epamfinalproject.Controllers.Listeners;

import com.example.epamfinalproject.Utility.Constants;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class SessionListener implements HttpSessionListener {
    private static final Logger log = LogManager.getLogger(SessionListener.class);
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        log.debug(Constants.COMMAND_STARTS);
        log.debug("-- HttpSessionListener#sessionCreated invoked --");
        HttpSession session = se.getSession();
        log.debug("session id: " + session.getId());
        session.setMaxInactiveInterval(5);//in seconds
        log.debug(Constants.COMMAND_FINISHED);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        log.debug(Constants.COMMAND_STARTS);
       log.debug("-- HttpSessionListener#sessionDestroyed invoked --");
        log.debug(Constants.COMMAND_FINISHED);
    }
}
