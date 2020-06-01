package com.sample.cwi.filters;

import com.sample.cwi.domains.SessionData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.Request;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.HashMap;
import java.util.Map;

import static com.sample.cwi.utils.SessionUtil.CSRF_TOKEN;
import static com.sample.cwi.utils.SessionUtil.getSessionData;
import static spark.Spark.halt;

public class CsrfFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsrfFilter.class);

    public CsrfFilter() {
    }

    public void generateCsrfToken(Request request){
        SessionData sessionData = getSessionData(request, false);
        if(sessionData != null){
            request.attribute(CSRF_TOKEN, sessionData.getSessionId());
            sessionData.setCsrfToken(sessionData.getSessionId());
        }
    }

    public void checkCsrfToken(Request request, FreeMarkerEngine freeMarkerEngine){

        if(!request.requestMethod().equalsIgnoreCase("post")){
            // Not required for GET since its not changing server status
            return;
        }

        String sessionCsrf = null;
        String requestCsrf = request.queryParams(CSRF_TOKEN);

        SessionData sessionData = getSessionData(request, false);

        if(sessionData != null) {
            sessionCsrf = sessionData.getCsrfToken();

            if(requestCsrf == null || sessionCsrf == null || !requestCsrf.equalsIgnoreCase(sessionCsrf)){
                LOGGER.warn("CsrfToken validation failed");
                Map<String, Object> responseMpa = new HashMap<>();
                responseMpa.put("body", "/spark/template/freemarker/errors/generic-error.ftl");
                halt(401, freeMarkerEngine.render(new ModelAndView(responseMpa, "spark/template/freemarker/common-page.ftl")));
            }
        }
    }
}
