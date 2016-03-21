package client.view;

import client.actionListeners.LoginInSystemListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ConnectingWindow extends JFrame{
    private final String TITLEGAME = "Добро пожаловать!";
    private Box loginMainBox;
    private JLabel textProcessInWindow = new JLabel("   Пожалуйста, войдите в систему или зарегистрируйтесь!");
    private JTextField loginField;
    private JPasswordField passwordField;
    private JButton buttonLogin;
    private JButton buttonRegistration;

    
    
    public ConnectingWindow(){
    	add(textProcessInWindow);
        setSize(400, 250);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setTitle(TITLEGAME);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    public void displayWindow() {
    	createLoginPanel();
    	add(textProcessInWindow);
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

    public void setLoginListenerToButtonLogin(LoginInSystemListener listener){
        ////
        System.out.println("set Listener to button: " + listener);
        //
        buttonLogin.addActionListener(listener);
    }

    public JButton getButtonRegistration(){
        return buttonRegistration;
    }



    private void createLoginPanel() {
        loginMainBox = Box.createVerticalBox();
        Box boxLogin = Box.createHorizontalBox();
        Box boxPassword = Box.createHorizontalBox();
        Box boxButton = Box.createHorizontalBox();

        JLabel loginLabel = new JLabel("Логин:");
        JLabel passwordLabel = new JLabel("Пароль:");
        loginField = new JTextField();
        passwordField = new JPasswordField();
        buttonLogin = new JButton("Войти");
        buttonRegistration = new JButton("Зарегистрироваться");

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

        loginLabel.setPreferredSize(passwordLabel.getPreferredSize());

        loginMainBox.setBorder(new EmptyBorder(12, 12, 12, 12));
        loginMainBox.add(boxLogin);
        loginMainBox.add(boxPassword);
        loginMainBox.add(boxButton);
        loginMainBox.setSize(350, 100);

        setContentPane(loginMainBox);
    }

    public static void main(String[] args) {
        ConnectingWindow window = new ConnectingWindow();
        window.displayWindow();
    }
}
