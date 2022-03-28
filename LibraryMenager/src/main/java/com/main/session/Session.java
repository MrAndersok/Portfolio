package com.comp.session;

import com.comp.models.User;

public class Session {
    static User user=null;
    public static User getUser() {
        return user;
    }
    public static void setUser(User user) {
        Session.user = user;
    }
}

