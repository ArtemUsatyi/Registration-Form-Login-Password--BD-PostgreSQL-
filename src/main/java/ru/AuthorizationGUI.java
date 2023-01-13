package ru;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthorizationGUI {
    private JFrame frame;
    private JPanel panel;
    private JPanel panelTop;
    private JLabel labelTopText;
    private JLabel labelLogin;
    private JLabel labelPassword;
    private JTextField textLogin;
    private JTextField textPassword;
    private JButton buttonEnter;
    private JButton buttonRegister;

    public void authorization() {
        frame = new JFrame("Вход в систему");
        panel = new JPanel();
        panelTop = new JPanel();
        labelTopText = new JLabel("Войти в систему");
        labelLogin = new JLabel("Введите логин:   ");
        labelPassword = new JLabel("Введите пароль: ");
        textLogin = new JTextField(20);
        textPassword = new JTextField(20);
        buttonEnter = new JButton("Войти");
        buttonRegister = new JButton("Зарегистрироваться");
        buttonEnter.setFocusPainted(false);
        buttonRegister.setFocusPainted(false);

        buttonRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                RegisterGUI registerSystem = new RegisterGUI();
                registerSystem.register();
            }
        });

        buttonEnter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConnectionBD connectionBD = new ConnectionBD();
                String answer = connectionBD.enterUser(textLogin.getText(), textPassword.getText());
                if (answer == "yes") {
                    frame.dispose();
                    EnterGUI enterGUI = new EnterGUI(textLogin.getText(), textPassword.getText());
                    enterGUI.enterSystem();
                } else if (answer == "no") {
                    JOptionPane.showMessageDialog(frame,
                            "Ошибка: Пароль введён не верно",
                            "Авторизация пользователя",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame,
                            "Ошибка: Логин введён не верно",
                            "Авторизация пользователя",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        panelTop.add(labelTopText);
        panelTop.setLayout(new FlowLayout(FlowLayout.CENTER));

        panel.add(labelLogin);
        panel.add(textLogin);
        panel.add(labelPassword);
        panel.add(textPassword);
        panel.add(buttonEnter);
        panel.add(buttonRegister);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.getContentPane().add(BorderLayout.NORTH, panelTop);
        frame.setSize(350, 200);                    // Размер окна
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // Закрытие формы "на крестик"
        frame.setResizable(true);                               // Редактировать форму запрещено
        frame.setLocationRelativeTo(null);                      // Позиция окна в центре экрана
        frame.setVisible(true);                                 // Отображение формы
    }
}
