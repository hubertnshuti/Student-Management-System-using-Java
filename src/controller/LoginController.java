package controller;

import service.UserService;

public class LoginController {

    private final UserService userService;

    public LoginController() {
        this.userService = new UserService();
    }

    public boolean login(String username, String password) {
        return userService.loginUser(username, password);
    }

    public String validateLoginInput(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            return "Username is required.";
        }

        if (password == null || password.trim().isEmpty()) {
            return "Password is required.";
        }

        return "VALID";
    }
}