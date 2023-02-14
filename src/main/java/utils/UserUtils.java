package utils;

import models.User;

/**
 * The type User utils.
 */
public class UserUtils {
    private static final String login = PropertiesUtils.get("user.login");
    private static final String password = PropertiesUtils.get("user.password");

    /**
     * Инициализация пользователя.
     *
     * @return the user
     */
    public static User initUser(){
        User user;
        user = DBUtils.getUser(login);
        if (user != null){
            user.setPassword(password);
            return user;
        } else {
            throw new RuntimeException("Пользователь не найден");
        }
    }




}
