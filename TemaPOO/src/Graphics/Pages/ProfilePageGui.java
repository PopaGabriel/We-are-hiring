package Graphics.Pages;

import Apllication.*;
import Graphics.Pages.ActionListeners.FactoryActionListener;
import Graphics.Pages.ActionListeners.NextButtonListener;
import Graphics.Pages.ActionListeners.PrevButtonListener;
import Graphics.Renderers.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfilePageGui extends JFrame implements ListSelectionListener, ActionListener {
    private JPanel panel1;
    private JPanel EduPanel;
    private JPanel InfoPanel;

    private JTextField nameTextField;
    private JTextField genderTextField;
    private JTextField emailTextField;
    private JTextField PhoneNumberTextField;
    private JTextField BirthDateTextField;
    private JTextField MeanTextField;

    private JLabel genderLabel;
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JLabel phoneNumberlabel;
    private JLabel BirthDate;
    private JLabel meanGpa;
    private JLabel languageLabel;
    private JLabel FriendsList;
    private JLabel experienceLabel;
    private JLabel EducationLabel;

    private JScrollPane languageScrollPane;
    private JScrollPane educationScrollPane;
    private JScrollPane experienceJScroll;
    private JScrollPane friendScrollPane;
    private JScrollPane notificationJScroll;

    private JButton prevButton;
    private JButton nextButton;
    private JButton seeProfile;
    private JButton searchUsersButton;

    private JTextField nameField1;
    private JTextField firstNameField2;
    private JPanel leftPanel;
    private JPanel rightPanel;

    private JList<Consumer> friendsJList;
    private JList<Education> educationJList;
    private JList<Experience> experienceJList;
    private JList<Language> languageJList;
    private JList<Notifi> notificationJList;

    private DefaultListModel<Consumer> friendsModel;
    private DefaultListModel<Education> educationModel;
    private DefaultListModel<Experience> experienceModel;
    private DefaultListModel<Language> languageModel;
    private DefaultListModel<Notifi> notificationDefaultListModel;


    public ProfilePageGui(Consumer consumer) {
        super(consumer.getName() + " "+consumer.getFirstName() + " "+"Profile Page");
        panel1.setBackground(StaticColorsPalet.getColorCellIdle());
        setContentPane(panel1);
        nameTextField.setText(consumer.getName() + " " + consumer.getFirstName());
        genderTextField.setText(consumer.getRes().getInfo().getGender());
        emailTextField.setText(consumer.getRes().getInfo().getEmail());
        PhoneNumberTextField.setText(consumer.getRes().getInfo().getPhoneNumber());
        BirthDateTextField.setText("" + consumer.getRes().getInfo().getBirthDate());
        MeanTextField.setText("" + consumer.meanGPA());

        languageModel = new DefaultListModel<>();
        languageJList = new JList<>();
        for(Language language : consumer.getRes().getInfo().getLanguages())
            languageModel.addElement(language);
        languageJList.setModel(languageModel);
        languageJList.setCellRenderer(new LanguageRenderer());
        languageJList.addListSelectionListener(this);
        languageScrollPane.setViewportView(languageJList);

        friendsModel = new DefaultListModel<>();
        friendsJList = new JList<>();
        for (Consumer consumerAux : consumer.getFriends())
            friendsModel.addElement(consumerAux);
        friendsJList.setModel(friendsModel);
        friendsJList.setCellRenderer(new FriendRenderer());
        friendsJList.addListSelectionListener(this);
        friendScrollPane.setViewportView(friendsJList);

        experienceModel = new DefaultListModel<>();
        experienceJList = new JList<>();
        for(Experience experience : consumer.getHisExp())
            experienceModel.addElement(experience);
        experienceJList.setModel(experienceModel);
        experienceJList.setCellRenderer(new ExperienceRenderer());
        experienceJList.addListSelectionListener(this);
        experienceJScroll.setViewportView(experienceJList);

        educationModel = new DefaultListModel<>();
        educationJList = new JList<>();
        for(Education education : consumer.getHisEdu())
            educationModel.addElement(education);
        educationJList.setModel(educationModel);
        educationJList.setCellRenderer(new EducationRenderer());
        educationJList.addListSelectionListener(this);
        educationScrollPane.setViewportView(educationJList);

        notificationDefaultListModel = new DefaultListModel<>();
        notificationJList = new JList<>();

        for(Notifi notifi : ((User)consumer).getNotifiStack())
            notificationDefaultListModel.addElement(notifi);

        notificationJList.setModel(notificationDefaultListModel);
        notificationJList.setCellRenderer(new NotificationRenderer());
        notificationJScroll.setViewportView(notificationJList);

        seeProfile.addActionListener(this);
        nextButton.addActionListener(FactoryActionListener.
                factory("Next",this));
        prevButton.addActionListener(FactoryActionListener.
                factory("Prev", this));
        searchUsersButton.addActionListener(this);

        setVisible(true);
        pack();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        //this is used so that i can only have selected one item at all times
        if (!e.getSource().equals(educationJList))
            educationJList.clearSelection();
        if (!e.getSource().equals(experienceJList))
            experienceJList.clearSelection();
        if (!e.getSource().equals(languageJList))
            languageJList.clearSelection();
        if (!e.getSource().equals(friendsJList))
            friendsJList.clearSelection();
        if(!e.getSource().equals(notificationJList))
            notificationJList.clearSelection();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PagesList pagesList = PagesList.getInstance();
        Application app = Application.getInstance();

        if (e.getSource().equals(seeProfile))
            if (friendsJList.getSelectedValue() != null) {
                pagesList.clearPagesStack(pagesList.getPagesArray().indexOf(this));
                setVisible(false);
                pagesList.add(new ProfilePageGui(friendsJList.getSelectedValue()));
            }
        if (e.getSource().equals(searchUsersButton)) {
            if (nameField1.getText().compareTo("") != 0)
                if (firstNameField2.getText().compareTo("") != 0)
                    if (app.getCons(nameField1.getText(),
                            firstNameField2.getText()) != null) {
                        pagesList.add(new ProfilePageGui(app.getCons(nameField1.getText(),
                                firstNameField2.getText())));
                        setVisible(false);
                    }
        }
    }
}
