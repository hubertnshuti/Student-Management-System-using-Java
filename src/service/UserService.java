package service;

import dao.UserDAO;
import models.User;
import util.StringUtil;

public class UserService {

    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public String registerUser(String username, String password) {
        username = StringUtil.cleanText(username);
        password = StringUtil.cleanText(password);

        if (username.isEmpty() || password.isEmpty()) {
            return "Username and password are required.";
        }

        User user = new User(username, password);
        userDAO.addUser(user);

        return "User registered successfully.";
    }

    public boolean loginUser(String username, String password) {
        username = StringUtil.cleanText(username);
        password = StringUtil.cleanText(password);

        if (username.isEmpty() || password.isEmpty()) {
            return false;
        }

        return userDAO.login(username, password);
    }
}