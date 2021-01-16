package Graphics;

import Apllication.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MeniuPage extends JFrame implements ActionListener {

    JButton adminButton;
    JButton managerButton;
    JButton profilePageButton;

    JLabel welcomeLabel;
    public MeniuPage() {
        Application app = Application.getInstance();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(300, 200));
        getContentPane().setBackground(Color.white);

        JPanel jPanel = new JPanel(new GridLayout(4,1));

        welcomeLabel = new JLabel("We are Hiring!", SwingConstants.CENTER);
        jPanel.add(welcomeLabel);

        adminButton = new JButton("Admin Account");
        adminButton.setBounds(75,50,100,30);
        adminButton.addActionListener(this);
        jPanel.add(adminButton);

        profilePageButton = new JButton("Profile Page");
        profilePageButton.setBounds(75,50,100,30);
        profilePageButton.addActionListener(this);
        jPanel.add(profilePageButton);

        managerButton = new JButton("Manager Button");
        managerButton.setBounds(75,50,100,30);
        managerButton.addActionListener(this);
        jPanel.add(managerButton);

        add(jPanel);

        setVisible(true);

    }
    @Override
    public void actionPerformed (ActionEvent e) {
        if(e.getSource().equals(adminButton))
            System.out.println("Yep1");
    if(e.getSource().equals(managerButton))
            System.out.println("Yep2");
    if(e.getSource().equals(profilePageButton))
            System.out.println("Yep3");
    }
}
