package Graphics;

import Apllication.*;
import Graphics.Renderers.CompanyRenderer;
import Graphics.Renderers.UserRenderer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPage extends JFrame implements ActionListener, ListSelectionListener {

    JPanel mainBody;
    JPanel optionsPanel;
    JPanel listPanel;
    JPanel usersPanel;
    JPanel companiesPanel;

    DefaultListModel<Company> companyModel;
    DefaultListModel<User> userModel;

    JList<User> userJList = new JList<>();
    JList<Company> companyJList;

    JButton showCompany;
    JButton showProfile;
    JButton deleteUser;
    JButton deleteCompany;

    public AdminPage() {
        super("AdminPage");

        setSize(new Dimension(400, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainBody = new JPanel();
        mainBody.setLayout(new BoxLayout(mainBody, BoxLayout.X_AXIS));


        listPanel =  new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

        addUserPanel();
        addCompanyPanel();
        addOptionsPanel();

        mainBody.add(listPanel);
        mainBody.add(optionsPanel);

        add(mainBody);
        setVisible(true);
    }
    public void addUserPanel() {
        Application app = Application.getInstance();
        companiesPanel = new JPanel();
        companiesPanel.setLayout(new BoxLayout(companiesPanel, BoxLayout.X_AXIS));
        companiesPanel.add(new JLabel("Users: "));

        companyJList = new JList<>();
        companyModel = new DefaultListModel<>();
        for(int i = 0; i < app.companyList.size(); i++)
            companyModel.addElement(app.companyList.get(i));
        companyJList.setModel(companyModel);
        companyJList.setCellRenderer(new CompanyRenderer());
        companyJList.addListSelectionListener(this);

        companiesPanel.add(new JScrollPane(companyJList));
        companiesPanel.setBorder(new EmptyBorder(20,5,10,5));
        companiesPanel.setMaximumSize(new Dimension(400,300));
        listPanel.add(companiesPanel);
    }
    public void addCompanyPanel() {
        Application app = Application.getInstance();
        usersPanel = new JPanel();
        usersPanel.setLayout(new BoxLayout(usersPanel, BoxLayout.X_AXIS));

        usersPanel.add(new JLabel("Users: "));
        userModel = new DefaultListModel<>();
        for(int i = 0; i < app.userList.size(); i++)
            userModel.addElement(app.userList.get(i));
        userJList.setModel(userModel);
        userJList.setCellRenderer(new UserRenderer());
        userJList.addListSelectionListener(this);

        usersPanel.add(new JScrollPane(userJList));
        usersPanel.setMaximumSize(new Dimension(400,300));
        usersPanel.setBorder(new EmptyBorder(20,5,10,5));

        listPanel.add(usersPanel);
    }
    public void addOptionsPanel() {

        optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));

        showCompany = new JButton("Show Company");
        showCompany.addActionListener(this);

        showProfile = new JButton("Show Profile");
        showProfile.addActionListener(this);

        deleteCompany = new JButton("Delete Company");
        deleteCompany.addActionListener(this);

        deleteUser = new JButton("Delete User");
        deleteUser.addActionListener(this);

        optionsPanel.add(showCompany);
        optionsPanel.add(Box.createRigidArea(new Dimension(40,40)));
        optionsPanel.add(showProfile);
        optionsPanel.add(Box.createRigidArea(new Dimension(40,40)));
        optionsPanel.add(deleteCompany);
        optionsPanel.add(Box.createRigidArea(new Dimension(40,40)));
        optionsPanel.add(deleteUser);
        optionsPanel.add(Box.createRigidArea(new Dimension(40,40)));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(showCompany)) {
            new CompanyPage(companyJList.getSelectedValue());
            setVisible(false);
        }
        if(e.getSource().equals(showProfile))
            new ProfilePage(userJList.getSelectedValue());
    }
    //So i can only have just one item active from both lists
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(!e.getSource().equals(userJList))
            userJList.clearSelection();
        else
            companyJList.clearSelection();
    }
}
