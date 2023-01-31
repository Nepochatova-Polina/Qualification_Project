package com.example.epamfinalproject.Controllers.Listeners;

import com.example.epamfinalproject.Utility.Constants;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebListener;
import java.util.concurrent.atomic.AtomicInteger;

@WebListener
public class SessionListener implements HttpSessionListener {
    private static final Logger log = LogManager.getLogger(SessionListener.class);
    private final AtomicInteger activeSessions;

    public SessionListener() {
        activeSessions = new AtomicInteger();
    }

    @Override
    public void sessionCreated(final HttpSessionEvent event) {
        log.debug(Constants.COMMAND_STARTS);
        activeSessions.incrementAndGet();
        log.debug(Constants.COMMAND_FINISHED);
    }
    @Override
    public void sessionDestroyed(final HttpSessionEvent event) {
        log.debug(Constants.COMMAND_STARTS);
        activeSessions.decrementAndGet();
        log.debug(Constants.COMMAND_FINISHED);
    }
}
