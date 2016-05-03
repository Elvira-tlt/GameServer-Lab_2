package client.view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ConnectingWindow extends JFrame{
    private final String TITLEGAME = "Добро пожаловать!";
    private Box loginMainBox;
    private JLabel textProcessInWindow = new JLabel("Пожалуйста, войдите в систему или зарегистрируйтесь!");
    private JTextField loginField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JButton buttonLogin;
    private JButton buttonRegistration;

    public ConnectingWindow(){
        prepareElementLoginPanel();
    	pack();
        setSize(400, 250);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(TITLEGAME);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    public String getLoginFromField() {
    	String textLoginField = loginField.getText();
    	return textLoginField;
    }
    
    public String getPasswordFromField() {
        String passwordFromField;
        StringBuffer textPasswordField = new StringBuffer();
    	char[] password = passwordField.getPassword();
        textPasswordField.append(password);
        passwordFromField = textPasswordField.toString();
        return passwordFromField;
    }

    public void setListenerToButtonLogin(ActionListener listener){
        buttonLogin.addActionListener(listener);
    }

    public void setListenerToButtonRegistration(ActionListener listener){
        buttonRegistration.addActionListener(listener);;
    }
    
    public void setTextToProcessInformation(TypeInformationText typeInformationText, String textToProcessInformation){
        Color colorText = Color.BLACK;
        textProcessInWindow.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        if(typeInformationText == TypeInformationText.POSITIVE) {
            textProcessInWindow.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        } else {
            colorText = Color.red;

        }
        textProcessInWindow.setText(textToProcessInformation);
        textProcessInWindow.setForeground(colorText);
    }

    private void prepareElementLoginPanel() {
        loginMainBox = Box.createVerticalBox();
        Box boxLogin = Box.createHorizontalBox();
        Box boxPassword = Box.createHorizontalBox();
        Box boxButton = Box.createHorizontalBox();
        Box boxText = Box.createHorizontalBox();

        JLabel loginLabel = new JLabel("Логин:");
        JLabel passwordLabel = new JLabel("Пароль:");

        buttonLogin = new JButton("Войти");
        buttonRegistration = new JButton("Зарегистрироваться");

        boxText.add(Box.createHorizontalStrut(15));
        boxText.add(textProcessInWindow);
        boxText.add(Box.createVerticalStrut(30));

        boxLogin.add(loginLabel);
        boxLogin.add(Box.createHorizontalStrut(6));
        boxLogin.add(loginField);

        boxPassword.add(passwordLabel);
        boxPassword.add(Box.createHorizontalStrut(6));
        boxPassword.add(passwordField);

        boxButton.add(Box.createHorizontalGlue());
        boxButton.add(buttonLogin);
        boxButton.add(Box.createHorizontalStrut(12));
        boxButton.add(buttonRegistration);
        boxButton.add(Box.createVerticalStrut(70));

        loginLabel.setPreferredSize(passwordLabel.getPreferredSize());

        loginMainBox.setBorder(new EmptyBorder(12, 12, 12, 12));
        loginMainBox.add(boxText);
        loginMainBox.add(boxLogin);
        loginMainBox.add(boxPassword);
        loginMainBox.add(boxButton);

        setContentPane(loginMainBox);

    }

    public static void main(String[] args) {
        ConnectingWindow window = new ConnectingWindow();
    }
}
