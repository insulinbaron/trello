package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type User.
 * Class User it is real user model of the portal "trello.com"
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /**
     * email - электронная почта пользователя
     * password - пароль пользователя
     * api_key - api ключ для отправки запросов
     * api_token - token для отправки запросов
     */
    private String email;
    private String password;
    private String api_key;
    private String api_token;

    @Override
    public String toString() {
        return String.format("Account{ email='%s', password='%s', api_key='%s', api_token='%s'}",
                email, password, api_key, api_token);
    }
}
