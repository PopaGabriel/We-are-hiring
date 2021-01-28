package Graphics.Pages;

import Apllication.Application;
import Apllication.Company;
import Apllication.PagesList;
import Apllication.User;
import Graphics.Pages.ActionListeners.FactoryActionListener;
import Graphics.Renderers.CompanyRenderer;
import Graphics.Renderers.UserRenderer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPageGUui extends JFrame implements ListSelectionListener, ActionListener {
    private JPanel panel1;

    private JScrollPane jScrollCompanies;
    private JScrollPane jScrollusers;

    private DefaultListModel<Company> companiesModel;
    private DefaultListModel<User> usersModel;

    private JList<Company> companiesJlist;
    private JList<User> userJList;

    private JButton seeUserProfileButton;
    private JButton seeCompanyPageButton;
    private JButton deleteCompanyButton;
    private JButton deleteUserButton;
    private JButton prevButton;
    private JButton nextButton;
    private JButton showUser;
    private JTextField nameField1;
    private JTextField firstNameField2;

    public AdminPageGUui() {
        super("Admin Page");
        setContentPane(panel1);

        Application app = Application.getInstance();

        //here i add the companies list
        companiesJlist = new JList<>();
        companiesModel = new DefaultListModel<>();
        for(int i = 0; i < app.getCompanies().size(); i++)
            companiesModel.addElement(app.getCompany(i));
        companiesJlist.setModel(companiesModel);
        companiesJlist.setCellRenderer(new CompanyRenderer());
        companiesJlist.addListSelectionListener(this);
        jScrollCompanies.setViewportView(companiesJlist);

        //here i add the user list
        usersModel = new DefaultListModel<>();
        userJList = new JList<>();
        for (int i = 0; i < app.getUserList().size(); i++)
            usersModel.addElement(app.getUser(i));
        userJList.setModel(usersModel);
        userJList.setCellRenderer(new UserRenderer());
        userJList.addListSelectionListener(this);
        jScrollusers.setViewportView(userJList);

        // I add the action listeners of the buttons
        seeUserProfileButton.addActionListener(this);
        seeCompanyPageButton.addActionListener(this);
        deleteCompanyButton.addActionListener(this);
        deleteUserButton.addActionListener(this);
        showUser.addActionListener(this);
        nextButton.addActionListener(FactoryActionListener.
                factory("Next",this));
        prevButton.addActionListener(FactoryActionListener.
                factory("Prev",this));

        pack();
        setVisible(true);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        //this is used so that i can only have selected one item at all times
        if (!e.getSource().equals(userJList))
            userJList.clearSelection();
        if (!e.getSource().equals(companiesJlist))
            companiesJlist.clearSelection();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PagesList pagesList = PagesList.getInstance();
        Application app = Application.getInstance();

        //when i click the button i clear all the pages
        // created after the current one
        if (e.getSource().equals(seeUserProfileButton))
            if (userJList.getSelectedValue() != null) {
                pagesList.clearPagesStack(pagesList.getPagesArray().indexOf(this));
                setVisible(false);
                pagesList.add(new ProfilePageGui(userJList.getSelectedValue()));
            }
        //when i click the button i clear all the pages
        // created after the current one
        if (e.getSource().equals(seeCompanyPageButton))
            if (companiesJlist.getSelectedValue() != null) {
                pagesList.clearPagesStack(pagesList.getPagesArray().indexOf(this));
                setVisible(false);
                pagesList.add(new CompanyPageGui(companiesJlist.getSelectedValue()));
            }
        //deletes a company
        if (e.getSource().equals(deleteCompanyButton))
            if (companiesJlist.getSelectedValue() != null) {
                pagesList.clearPagesStack(pagesList.getPagesArray().indexOf(this));
                app.remove(companiesJlist.getSelectedValue());
                companiesModel.removeElement(companiesJlist.getSelectedValue());

                //I update the Jlist of Users
                usersModel.clear();
                for(int i = 0; i < app.getUserList().size(); i++)
                    usersModel.addElement(app.getUserList().get(i));
                userJList.setModel(usersModel);
            }
        //this deletes a user from the user list
        if (e.getSource().equals(deleteUserButton))
            if (userJList.getSelectedValue() != null) {
                app.remove(userJList.getSelectedValue());
                usersModel.removeElement(userJList.getSelectedValue());
            }
        //when i click the button i clear all the pages
        // created after the current one
        if (e.getSource().equals(showUser))
            if (nameField1.getText().compareTo("") != 0)
                if (firstNameField2.getText().compareTo("") != 0) {
                    pagesList.clearPagesStack(pagesList.getPagesArray().indexOf(this));
                    setVisible(false);
                    pagesList.add(new ProfilePageGui(app.getCons(nameField1.getText(),
                            firstNameField2.getText())));
                }

    }
}
