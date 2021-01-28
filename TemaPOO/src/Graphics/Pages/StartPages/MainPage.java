package Graphics.Pages.StartPages;

import Apllication.Application;
import Apllication.PagesList;
import Apllication.User;
import Graphics.Pages.ProfilePageGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainPage extends JFrame implements ActionListener {

    private JButton logIn;
    private JButton signUp;

    private JLabel emailLabel;
    private JLabel passwordLabel;

    private JTextField emailTextField;
    private JTextField passwordTextField;

    private JPanel pagePanel;
    private JPanel emailPanel;
    private JPanel passwordPanel;

    public MainPage() {
        super("Main Page");
        setSize(new Dimension(300,500));

        pagePanel = new JPanel();
        pagePanel.setLayout(new BoxLayout(pagePanel, BoxLayout.Y_AXIS));

        pagePanel.add(Box.createRigidArea(new Dimension(50,50)));
        pagePanel.add(new JLabel("We Are Hiring!"));

        addEmailPanel();
        addPasswordPanel();

        pagePanel.add(Box.createRigidArea(new Dimension(50,50)));

        logIn = new JButton("Log In!");
        logIn.addActionListener(this);
        pagePanel.add(Box.createRigidArea(new Dimension(10,50)));
        pagePanel.add(logIn);

        signUp = new JButton("Sign Up!");
        signUp.addActionListener(this);
        pagePanel.add(Box.createRigidArea(new Dimension(10,50)));
        pagePanel.add(signUp);

        add(pagePanel);

        setVisible(true);
    }
    public void addEmailPanel() {
        emailPanel = new JPanel();
        emailPanel.setLayout(new BoxLayout(emailPanel, BoxLayout.X_AXIS));

        emailLabel = new JLabel("Email:      ");
        emailTextField = new JTextField(20);
        emailTextField.setMaximumSize(new Dimension(200,20));

        emailPanel.add(emailLabel);
        emailPanel.add(Box.createRigidArea(new Dimension(10,0)));
        emailPanel.add(emailTextField);

        pagePanel.add(Box.createRigidArea(new Dimension(10,50)));
        pagePanel.add(emailPanel);
    }
    public void addPasswordPanel() {

        passwordPanel = new JPanel();
        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.X_AXIS));

        passwordLabel = new JLabel("Password: ");
        passwordTextField = new JTextField(20);
        passwordTextField.setMaximumSize(new Dimension(200,20));

        passwordPanel.add(passwordLabel);
        passwordPanel.add(Box.createRigidArea(new Dimension(10,0)));
        passwordPanel.add(passwordTextField);

        pagePanel.add(Box.createRigidArea(new Dimension(10,50)));
        pagePanel.add(passwordPanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Application app = Application.getInstance();
        PagesList pagesList = PagesList.getInstance();

        if (e.getSource().equals(logIn)) {
            ArrayList<User> userArrayList = app.getUserList();
            for (int i = 0; i < userArrayList.size(); i++) {
                if(userArrayList.get(i).getRes().
                        getInfo().getEmail().compareTo
                        (emailTextField.getText())==0)
                    if(userArrayList.get(i).getRes().
                            getInfo().getPassword().compareTo
                            (passwordTextField.getText())==0) {
                        setVisible(false);
                        pagesList.add(new ProfilePageGui(userArrayList.get(i)));
                        return;
                    }
            }
        }
    }
}
