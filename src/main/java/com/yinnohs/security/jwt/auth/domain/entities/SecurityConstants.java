package com.yinnohs.security.jwt.auth.domain.entities;

public class SecurityConstants {
    // Role names
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_MANAGER = "ROLE_MANAGER";
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_INSTALLER = "ROLE_INSTALLER";

    // Permission categories
    public static final String USER_READ = "USER_READ";
    public static final String USER_CREATE = "USER_CREATE";
    public static final String USER_UPDATE = "USER_UPDATE";
    public static final String USER_DELETE = "USER_DELETE";

    // Admin permissions
    public static final String ADMIN_READ = "ADMIN_READ";
    public static final String ADMIN_CREATE = "ADMIN_CREATE";
    public static final String ADMIN_UPDATE = "ADMIN_UPDATE";
    public static final String ADMIN_DELETE = "ADMIN_DELETE";

    //add permissions category below (depends on the application)
}
