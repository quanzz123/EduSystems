package com.example.eduSystems.utilities;

public class Functions {
    public static int _UserId = 0;
    public static int _RoleId = 0;
    public static String _RoleName = "";
    public static String _UserName = "";
    public static String _FullName = "";
    public static String _Email = "";
    public static String _Message = "";
    public static String _MessageEmail = "";

     public static boolean checkRole(int roleId) {
        return _RoleId == roleId;
    }
    public static boolean checkRole(String roleName) {
        return _RoleName.equalsIgnoreCase(roleName);
    }
    
    public static boolean isLogin() {
        return !_Email.isEmpty() 
            && !_UserName.isEmpty() 
            && _UserId > 0;
    }
    
    // Method để set tất cả thông tin user khi login
    public static void setUserInfo(int userId, String userName, String email, 
                                   String fullName, int roleId, String roleName) {
        _UserId = userId;
        _UserName = userName;
        _Email = email;
        _FullName = fullName;
        _RoleId = roleId;
        _RoleName = roleName;
    }

    
    // Clear khi logout
    public static void clear() {
        _UserId = 0;
        _RoleId = 0;
        _RoleName = "";
        _UserName = "";
        _FullName = "";
        _Email = "";
        _Message = "";
        _MessageEmail = "";
    }

    public static int get_UserId() {
        return _UserId;
    }

    public static void set_UserId(int _UserId) {
        Functions._UserId = _UserId;
    }

    public static int get_RoleId() {
        return _RoleId;
    }

    public static void set_RoleId(int _RoleId) {
        Functions._RoleId = _RoleId;
    }

    public static String get_RoleName() {
        return _RoleName;
    }

    public static void set_RoleName(String _RoleName) {
        Functions._RoleName = _RoleName;
    }

    public static String get_UserName() {
        return _UserName;
    }

    public static void set_UserName(String _UserName) {
        Functions._UserName = _UserName;
    }

    public static String get_FullName() {
        return _FullName;
    }

    public static void set_FullName(String _FullName) {
        Functions._FullName = _FullName;
    }

    public static String get_Email() {
        return _Email;
    }

    public static void set_Email(String _Email) {
        Functions._Email = _Email;
    }

    public static String get_Message() {
        return _Message;
    }

    public static void set_Message(String _Message) {
        Functions._Message = _Message;
    }

    public static String get_MessageEmail() {
        return _MessageEmail;
    }

    public static void set_MessageEmail(String _MessageEmail) {
        Functions._MessageEmail = _MessageEmail;
    }

    
}
