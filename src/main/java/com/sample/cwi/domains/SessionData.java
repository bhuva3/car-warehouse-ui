package com.sample.cwi.domains;

import java.io.Serializable;
import java.util.UUID;

public class SessionData implements Serializable {

    private static final long serialVersionUID = 124583399412571615L;
    private String sessionId;
    private String csrfToken;

    public SessionData() {
        this.sessionId = UUID.randomUUID().toString();
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getCsrfToken() {
        return csrfToken;
    }

    public void setCsrfToken(String csrfToken) {
        this.csrfToken = csrfToken;
    }


    @Override
    public String toString() {
        return "SessionData{" +
                "sessionId='" + sessionId + '\'' +
                ", csrfToken='" + csrfToken + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SessionData that = (SessionData) o;

        if (!sessionId.equals(that.sessionId)) return false;
        return csrfToken != null ? csrfToken.equals(that.csrfToken) : that.csrfToken == null;
    }

    @Override
    public int hashCode() {
        int result = sessionId.hashCode();
        result = 31 * result + (csrfToken != null ? csrfToken.hashCode() : 0);
        return result;
    }
}
