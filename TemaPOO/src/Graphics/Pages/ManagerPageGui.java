package Graphics.Pages;

import Apllication.*;
import Apllication.Departamente.Department;
import Graphics.Pages.ActionListeners.FactoryActionListener;
import Graphics.Pages.ActionListeners.NextButtonListener;
import Graphics.Pages.ActionListeners.PrevButtonListener;
import Graphics.Renderers.RequestRenderer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerPageGui extends JFrame implements ActionListener {
    private JPanel PanelMain;

    private JButton seeUserProfileButton;
    private JButton seeRecruiterProfileButton;
    private JButton processButton;
    private JButton acceptRequestButton;
    private JButton deleteRequestButton;
    private JButton seeCompany;
    private Manager manager;

    private JScrollPane scrollRequest;
    private JButton prevButton;
    private JButton nextButton;
    private JList<Request<Job, User>> jListRequest;
    private DefaultListModel<Request<Job, User>> requestModel;

    public ManagerPageGui(Manager manager) {
        super("Manager Page");
        setContentPane(PanelMain);
        this.manager = manager;

        jListRequest = new JList<>();
        requestModel = new DefaultListModel<>();
        for(Request<Job, User> request : manager.getRequests())
            requestModel.addElement(request);
        jListRequest.setModel(requestModel);
        jListRequest.setCellRenderer(new RequestRenderer());
        scrollRequest.setViewportView(jListRequest);

        seeUserProfileButton.addActionListener(this);
        seeRecruiterProfileButton.addActionListener(this);
        processButton.addActionListener(this);
        acceptRequestButton.addActionListener(this);
        deleteRequestButton.addActionListener(this);
        seeCompany.addActionListener(this);
        nextButton.addActionListener(FactoryActionListener.
                factory("Next",this));
        prevButton.addActionListener(FactoryActionListener.
                factory("Prev", this));

        setVisible(true);
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Application app = Application.getInstance();
        PagesList pagesList = PagesList.getInstance();

        if (e.getSource().equals(seeRecruiterProfileButton))
            if (jListRequest.getSelectedValue() != null) {
                pagesList.clearPagesStack(pagesList.getPagesArray().
                        indexOf(this));
                pagesList.add(new ProfilePageGui(jListRequest.
                        getSelectedValue().getValue2()));
                setVisible(false);
                return;
            }
        if (e.getSource().equals(seeUserProfileButton))
            if (jListRequest.getSelectedValue() != null) {
                pagesList.clearPagesStack(pagesList.getPagesArray()
                        .indexOf(this));
                pagesList.add(new ProfilePageGui(jListRequest.
                        getSelectedValue().getValue1()));
                setVisible(false);
                return;
            }
            if (e.getSource().equals(seeCompany)) {
                pagesList.clearPagesStack(pagesList.getPagesArray().
                        indexOf(this));
                pagesList.add(new CompanyPageGui(app.getCompany(
                        manager.getCompanyName())));
                setVisible(false);
                return;
            }
        if (e.getSource().equals(processButton))
            for (Job job : app.getCompany
                    (manager.getCompanyName()).getJobs()) {
                manager.process(job);
                requestModel.clear();
            }
        if (e.getSource().equals(acceptRequestButton))
            if (jListRequest.getSelectedValue() != null) {
                manager.hire(jListRequest.getSelectedValue());
                requestModel.remove(jListRequest.getSelectedIndex());
                return;
            }
        if (e.getSource().equals(deleteRequestButton)) {
                manager.getRequests().remove(jListRequest.getSelectedValue());
                requestModel.remove(jListRequest.getSelectedIndex());
            }
    }
}
