package Graphics;

import Apllication.Application;
import Apllication.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUp extends JFrame implements ActionListener {

    public JLabel labelName;
    public JLabel labelFirstName;
    public JLabel labelEmail;
    public JLabel labelPhoneNumber;
    public JLabel labelBirthDateDay;
    public JLabel labelBirthDateMonth;
    public JLabel labelBirthDateYear;
    public JLabel labelGender;

    public JTextField textName;
    public JTextField textFirstName;
    public JTextField textEmail;
    public JTextField textPhoneNumber;
    public JTextField textBirthYear;
    public JTextField textBirthMonth;
    public JTextField textBirthDay;
    public JTextField textGender;

    public JPanel Panel1;
    public JPanel panelBirthDate;
    public JButton Button1;


    public SignUp(String text){
        super(text);
        Application app = Application.getInstance();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(300,200));
        getContentPane().setBackground(Color.white);

        Panel1 = new JPanel(new GridLayout(13,1));
        panelBirthDate = new JPanel(new GridLayout(1,6));
        fillPanelBirthDate(panelBirthDate);

        labelName = new JLabel("Name: ");
        labelFirstName = new JLabel("First Name: ");
        labelGender = new JLabel("Gender: ");
        labelEmail = new JLabel("Email ");
        labelPhoneNumber = new JLabel("Phone number ");

        textName = new JTextField(20);
        textFirstName =  new JTextField(20);
        textPhoneNumber =  new JTextField(20);
        textEmail =  new JTextField(20);
        textGender =  new JTextField(20);

        Panel1.add(labelName);
        Panel1.add(textName);

        Panel1.add(labelFirstName);
        Panel1.add(textFirstName);

        Panel1.add(labelEmail);
        Panel1.add(textEmail);

        Panel1.add(labelPhoneNumber);
        Panel1.add(textPhoneNumber);

        Panel1.add(panelBirthDate);

        Panel1.add(labelGender);
        Panel1.add(textGender);

        Button1 = new JButton("SignUp");
        Button1.addActionListener(this);
        Panel1.add(Button1);

        add(Panel1);
        show();
        pack();
    }
    public void fillPanelBirthDate(JPanel panelBirthDate){

        labelBirthDateYear = new JLabel("Year: ");
        labelBirthDateMonth = new JLabel("Month: ");
        labelBirthDateDay = new JLabel("Day: ");
        textBirthYear = new JTextField("",20);
        textBirthMonth = new JTextField("",20);
        textBirthDay = new JTextField("",20);
        panelBirthDate.add(labelBirthDateYear);
        panelBirthDate.add(textBirthYear);
        panelBirthDate.add(labelBirthDateMonth);
        panelBirthDate.add(textBirthMonth);
        panelBirthDate.add(labelBirthDateDay);
        panelBirthDate.add(textBirthDay);

    }
    public String makeBirthDate(){
        return textBirthDay.getText() + " " +textBirthMonth.getText() +" "+ textBirthYear.getText();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        User user = new User(textName.getText(),textFirstName.getText(),textEmail.getText(),textPhoneNumber.getText()
                            ,makeBirthDate(), textGender.getText());
        Application app = Application.getInstance();
        app.userList.add(user);
        //System.out.println(app);
    }
}

