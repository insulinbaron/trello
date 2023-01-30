package utils;


import models.User;

import java.sql.*;

public final class DBUtils {
    private static final String URL = "db.url";
    private static final String USERNAME = "db.username";
    private static final String PASSWORD = "db.password";

    private DBUtils(){}

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

    public static User getUser(String login, String pass){
        String str = "SELECT * FROM users WHERE email='%s' AND pass = MD5('%s')";
        User user = null;

        try(Connection connection = DBUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(String.format(str,login, pass))) {
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
