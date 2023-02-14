package utils;


import models.User;

import java.sql.*;

/**
 * The type Db utils.
 */
public final class DBUtils {
    private static final DBUtils INSTANCE = new DBUtils();
    private static final String URL = "db.url";
    private static final String USERNAME = "db.username";
    private static final String PASSWORD = "db.password";

    private DBUtils(){}

    /**
     * Get instance db utils.
     *
     * @return the db utils
     */
    public static DBUtils getInstance(){
        return INSTANCE;
    }


    private static Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    PropertiesUtils.get(URL),
                    PropertiesUtils.get(USERNAME),
                    PropertiesUtils.get(PASSWORD));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    /**
     * Возвращает пользователя.
     *
     * @param login the login
     * @return the user
     */
    public static User getUser(String login){
        String str = "SELECT * FROM users WHERE email='%s'";
        User user = null;

        try(Connection connection = DBUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(String.format(str,login))) {
            ResultSet result = statement.executeQuery();
            if (result.next()){
                String email = result.getString("email");
                String password = result.getString("pass");
                String api_key = result.getString("api_key");
                String api_token = result.getString("api_token");

                user = new User(email, password, api_key, api_token);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }


}
