package com.solid.patterns.singleton;

import java.util.HashMap;
import java.util.Map;

public class UserSession {

    private static Map<String, UserSession> sessions = new HashMap<>();

    private String userId;
    private String sessionData;

    private UserSession(String userId) {
        this.userId = userId;

        this.sessionData = "Données pour l'utilisateur : " + userId;
    }

    public static UserSession getInstance(String userId) {
        if (!sessions.containsKey(userId)) {
            sessions.put(userId, new UserSession(userId));
        }
        return sessions.get(userId);

    }

}
