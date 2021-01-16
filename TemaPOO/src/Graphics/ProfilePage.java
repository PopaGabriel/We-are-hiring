package Graphics;

import Apllication.Education;
import Apllication.Experience;
import Apllication.Language;
import Apllication.User;
import Graphics.Renderers.EducationRenderer;
import Graphics.Renderers.ExperienceRenderer;
import Graphics.Renderers.LanguageRenderer;

import javax.print.attribute.standard.JobMediaSheetsCompleted;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;

public class ProfilePage extends JFrame implements ActionListener {
    User user;

    private JLabel nameLabel;
    private JLabel firstNameLabel;
    private JLabel genderLabel;
    private JLabel emailLabel;
    private JLabel phoneNumberLabel;
    private JLabel birthDateLabel;
    private JLabel educationTitle;
    private JLabel experienceTitle;
    private JLabel meanGpaLabel;

    private JPanel expAndEduPanel;
    private JPanel meanGpaPanel;
    private JPanel pageStack;
    private JPanel pagePane;

    JList<Language> languagesList;

    JScrollPane languageScroll;

    public ProfilePage(User user) {
        super("Profile Page");
        this.user = user;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setBackground(Color.GREEN);

        pagePane = new JPanel(new GridLayout(1,2));
        pagePane.setBackground(Color.lightGray);
        pageStack = new JPanel(new GridLayout(7,1));
        pageStack.setBorder(new EmptyBorder(10, 15, 15, 15));
        addInfoPanel();
        addExpAEduInfo();

        add(pagePane);

        setVisible(true);
    }
    public void addInfoPanel() {

        nameLabel = new JLabel("Name: "+user.resume.information.getName());
        setBackground(Color.BLUE);
        pageStack.add(nameLabel);

        firstNameLabel = new JLabel("First Name: "+user.resume.information.getFirstName());
        pageStack.add(firstNameLabel);

        genderLabel = new JLabel("Gender: " + user.resume.information.getGender());
        pageStack.add(genderLabel);

        emailLabel = new JLabel("Email: " + user.resume.information.getEmail());
        pageStack.add(emailLabel);

        phoneNumberLabel = new JLabel("PhoneNumber: "+user.resume.information.getPhoneNumber());
        pageStack.add(phoneNumberLabel);

        birthDateLabel = new JLabel("Birthdate: " + user.resume.information.getBirthDate());
        pageStack.add(birthDateLabel);

        languagesList = new JList(user.resume.information.getLanguages().toArray());
        languagesList.setCellRenderer(new LanguageRenderer());
        languageScroll = new JScrollPane(languagesList);
        pageStack.add(languageScroll);

        pagePane.add(pageStack);
    }
    public void addExpAEduInfo() {
        expAndEduPanel = new JPanel(new GridLayout(4,1,10,10));
        expAndEduPanel.setBorder(new EmptyBorder(10, 15, 15, 15));

        meanGpaPanel = new JPanel(new GridLayout(1,2));
        educationTitle = new JLabel("Education List");
        educationTitle.setFont(new Font("Why though",Font.BOLD,12));
        meanGpaPanel.add(educationTitle);

        meanGpaLabel = new JLabel("" + user.meanGPA());
        meanGpaPanel.add(meanGpaLabel);
        expAndEduPanel.add(meanGpaPanel);

        DefaultListModel<Education> listEdu = new DefaultListModel<>();
        for(Education education : user.resume.historyEducation)
            listEdu.addElement(education);
        JList eduList = new JList(listEdu);
        eduList.setCellRenderer(new EducationRenderer());

        JScrollPane scrollPaneEducation = new JScrollPane(eduList);
        expAndEduPanel.add(scrollPaneEducation);

        experienceTitle = new JLabel("Experience List");
        expAndEduPanel.add(experienceTitle);

        DefaultListModel<Experience> listExp = new DefaultListModel<Experience>();
        for(Experience experience : user.resume.historyExperience)
            listExp.addElement(experience);
        JList expList = new JList(listExp);
        expList.setCellRenderer(new ExperienceRenderer());

        JScrollPane scrollPaneExperience = new JScrollPane(expList);
        expAndEduPanel.add(scrollPaneExperience);

        pagePane.add(expAndEduPanel);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
