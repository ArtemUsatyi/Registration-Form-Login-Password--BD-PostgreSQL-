package ru;

import java.sql.*;

public class ConnectionBD {
    private static final String URL = "jdbc:postgresql://localhost:5432/register_bd";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "Postgres";
    private static final Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    static {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String enterUser(String login, String password) {
        String text;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT password_user FROM register_user WHERE login_user = ?");
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();
            if (password.equals(resultSet.getString("password_user"))) text = "yes";
            else text = "no";
        } catch (SQLException e) {
            text = e.getMessage();
        }
        return text;
    }

    public String insertUser(String login, String password) {
        String text;
        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO register_user(login_user, password_user) VALUES (?,?)");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
            text = "yes";
        } catch (SQLException e) {
            text = e.getMessage();
        }
        return text;
    }

    public void deleteUser(String login) {
        try {
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM register_user WHERE login_user=?");
            preparedStatement.setString(1, login);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void updateLogin(String login, String newLogin) {
        try {
            preparedStatement = connection.prepareStatement(
                    "UPDATE register_user SET login_user=? WHERE login_user=?");
            preparedStatement.setString(1, newLogin);
            preparedStatement.setString(2, login);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePassword(String login, String newPassword) {
        try {
            preparedStatement = connection.prepareStatement(
                    "UPDATE register_user SET password_user=? WHERE login_user=?");
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, login);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public StringBuilder selectUsers(String enterLogin) {
        StringBuilder strBuilder = new StringBuilder();
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT login_user FROM register_user WHERE login_user!=? ORDER BY login_user ASC;");
            preparedStatement.setString(1,enterLogin);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                strBuilder.append(resultSet.getString("login_user") + "\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return strBuilder;
    }
}
