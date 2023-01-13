package ru;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterGUI {
    private JFrame frame;
    private JPanel panel;
    private JPanel panelTop;
    private JLabel labelTopText;
    private JLabel labelLogin;
    private JLabel labelPassword;
    private JLabel labelPasswordRepeat;
    private JLabel labelError;
    private JTextField textLogin;
    private JTextField textPassword;
    private JTextField textPasswordRepeat;
    private JButton buttonRegister;
    private JButton buttonBack;

    public void register() {
        frame = new JFrame("Регистрация");
        panel = new JPanel();
        panelTop = new JPanel();
        labelTopText = new JLabel("Зарегистрироваться в системе");
        labelLogin = new JLabel("Введите новый логин:         ");
        labelPassword = new JLabel("Введите новый пароль:      ");
        labelPasswordRepeat = new JLabel("Введите повторно пароль: ");
        labelError = new JLabel("");
        textLogin = new JTextField(20);
        textPassword = new JTextField(20);
        textPasswordRepeat = new JTextField(20);

        buttonBack = new JButton("Назад");
        buttonRegister = new JButton("Зарегистрировать");
        buttonRegister.setFocusPainted(false);
        buttonBack.setFocusPainted(false);

        buttonRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textPassword.getText().isEmpty() && textPasswordRepeat.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frame,
                            "Пароли не должны быть пустыми",
                            "Регистрация пользователя",
                            JOptionPane.INFORMATION_MESSAGE);
                } else if (textPassword.getText().equals(textPasswordRepeat.getText())) {
                    ConnectionBD connectionBD = new ConnectionBD();
                    String answer = connectionBD.insertUser(textLogin.getText(), textPassword.getText());
                    if (answer == "yes")
                        JOptionPane.showMessageDialog(frame,
                                "Добавлен в базу новый пользователь",
                                "Регистрация пользователя",
                                JOptionPane.INFORMATION_MESSAGE);
                    else JOptionPane.showMessageDialog(frame,
                            answer,
                            "Регистрация пользователя",
                            JOptionPane.WARNING_MESSAGE);
                } else JOptionPane.showMessageDialog(frame,
                        "Пароли не совпадают." + "\n" + "Проверьте правильность введеных паролей",
                        "Регистрация пользователя",
                        JOptionPane.INFORMATION_MESSAGE);
                ;
            }
        });

        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                AuthorizationGUI authorizationGUI = new AuthorizationGUI();
                authorizationGUI.authorization();
            }
        });

        panelTop.add(labelTopText);
        panelTop.setLayout(new FlowLayout(FlowLayout.CENTER));

        panel.add(labelLogin);
        panel.add(textLogin);
        panel.add(labelPassword);
        panel.add(textPassword);
        panel.add(labelPasswordRepeat);
        panel.add(textPasswordRepeat);
        panel.add(buttonBack);
        panel.add(buttonRegister);
        panel.add(labelError);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.getContentPane().add(BorderLayout.NORTH, panelTop);
        frame.setSize(400, 220);                    // Размер окна
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // Закрытие формы "на крестик"
        frame.setResizable(true);                               // Редактировать форму запрещено
        frame.setLocationRelativeTo(null);                      // Позиция окна в центре экрана
        frame.setVisible(true);                                 // Отображение формы
    }
}
