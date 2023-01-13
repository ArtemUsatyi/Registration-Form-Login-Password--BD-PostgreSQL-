package ru;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnterGUI {
    private JFrame frame;
    private JPanel panel;
    private JPanel panelTop;
    private JLabel labelTopText;
    private JLabel labelLogin;
    private JLabel labelPassword;
    private JLabel labelError;
    private JTextField textLogin;
    private JTextField textPassword;
    private JTextArea area;
    private JScrollPane scrollPane;
    private JButton buttonEditLogin;
    private JButton buttonEditPassword;
    private JButton buttonExit;
    private JButton buttonDelete;
    private String enterLogin;
    private String enterPassword;

    public EnterGUI(String enterLogin, String enterPassword) {
        this.enterLogin = enterLogin;
        this.enterPassword = enterPassword;
    }

    public void enterSystem() {
        frame = new JFrame("Вы вошли в систему");
        panel = new JPanel();
        panelTop = new JPanel();
        labelTopText = new JLabel("Окно пользователя, " + enterLogin);
        labelLogin = new JLabel("Ваш логин:    ");
        labelPassword = new JLabel("Ваш пароль: ");
        labelError = new JLabel("");
        textLogin = new JTextField(enterLogin, 20);
        textPassword = new JTextField(enterPassword, 20);
        buttonExit = new JButton("Выйти");
        buttonEditLogin = new JButton("Изм.");
        buttonEditPassword = new JButton("Изм.");
        buttonDelete = new JButton("Удалить");
        buttonExit.setFocusPainted(false);
        buttonEditPassword.setFocusPainted(false);
        buttonEditLogin.setFocusPainted(false);
        buttonDelete.setFocusPainted(false);
        area = new JTextArea();
        area.append("Пользователи в системе:" + "\n");
        ConnectionBD connectionBD = new ConnectionBD();
        area.append(connectionBD.selectUsers(enterLogin).toString());

        scrollPane = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(360, 200));

        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                AuthorizationGUI AuthorizationGUI = new AuthorizationGUI();
                AuthorizationGUI.authorization();
            }
        });

        buttonEditLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = 0;
                if (enterLogin.equals(textLogin.getText())) {
                    JOptionPane.showMessageDialog(frame,
                            "Логин совпадает!",
                            "Редактирование Логина пользователя",
                            JOptionPane.INFORMATION_MESSAGE);
                } else result = JOptionPane.showConfirmDialog(frame,
                        "Вы точно хотите изменить логин?",
                        "Редактирование Логина пользователя",
                        JOptionPane.YES_NO_OPTION);
                if (result == 0) {
                    connectionBD.updateLogin(enterLogin, textLogin.getText());
                    enterLogin = textLogin.getText();
                    labelTopText.setText("Окно пользователя, " + enterLogin);
                }
            }

        });

        buttonEditPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = 0;
                if (enterPassword.equals(textPassword.getText())) {
                    JOptionPane.showMessageDialog(frame,
                            "Пароли совпадают!",
                            "Редактирование Пароля пользователя",
                            JOptionPane.INFORMATION_MESSAGE);
                } else result = JOptionPane.showConfirmDialog(frame,
                        "Вы точно хотите изменить пароль?",
                        "Редактирование Пароля пользователя",
                        JOptionPane.YES_NO_OPTION);
                if (result == 0) {
                    connectionBD.updatePassword(enterLogin, textPassword.getText());
                    enterPassword = textPassword.getText();
                }
            }
        });

        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(frame,
                        "Вы точно хотите удалить Логин и Пароль?",
                        "Удаление пользователя",
                        JOptionPane.YES_NO_OPTION);
                if (result == 0) {
                    connectionBD.deleteUser(enterLogin);
                    frame.dispose();
                    AuthorizationGUI authorizationGUI = new AuthorizationGUI();
                    authorizationGUI.authorization();
                }
            }
        });

        panelTop.add(labelTopText);
        panelTop.setLayout(new FlowLayout(FlowLayout.CENTER));

        panel.add(labelLogin);
        panel.add(textLogin);
        panel.add(buttonEditLogin);
        panel.add(labelPassword);
        panel.add(textPassword);
        panel.add(buttonEditPassword);
        panel.add(buttonExit);
        panel.add(buttonDelete);
        //panel.add(labelError);
        panel.add(scrollPane);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.getContentPane().add(BorderLayout.NORTH, panelTop);
        frame.setSize(400, 380);                    // Размер окна
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // Закрытие формы "на крестик"
        frame.setResizable(true);                               // Редактировать форму запрещено
        frame.setLocationRelativeTo(null);                      // Позиция окна в центре экрана
        frame.setVisible(true);                                 // Отображение формы
    }
}
