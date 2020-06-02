package com.sample.cwi.utils;

import com.sample.cwi.domains.SessionData;
import spark.Request;

import java.util.ArrayList;

public class SessionUtil {

    public static final String SESSION = "SESSION";
    public static final String CSRF_TOKEN = "csrfToken";
    public static final int MAX_INACTIVE_INTERVAL = 7200; // can be added in Yaml for make it configurable

    public static SessionData getSessionData(Request request, boolean createSession){
        SessionData sessionData = null;

        // will return sessionData from session
        if(null != request.session(false)){
            sessionData = request.session(false).attribute("SESSION");
        }

        // to create & return fresh session
        if(sessionData == null && createSession){
            sessionData = new SessionData();
            sessionData.setCsrfToken(sessionData.getSessionId());
            sessionData.setCartDetailList(new ArrayList<>());
            request.session(true).attribute(SESSION, sessionData);
            request.attribute(CSRF_TOKEN, sessionData.getSessionId());
            request.session(false).maxInactiveInterval(MAX_INACTIVE_INTERVAL);
        }

        return sessionData;
    }
}
