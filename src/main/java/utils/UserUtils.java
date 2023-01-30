//package utils;
//
//import models.User;
//
//public class UserUtils {
//    private static final String login = PropertiesUtils.get("user.login");
//    private static final String password = PropertiesUtils.get("user.password");
//    public static User initUser(){
//        User user;
//        user = DBUtils.getUser(login, password);
//        if (user != null){
//            user.setPassword(password);
//            return user;
//        } else {
//            throw new RuntimeException("Пользователь не найден");
//        }
//    }
//}
