package Graphics.Pages;

import Apllication.*;
import Graphics.Pages.ActionListeners.FactoryActionListener;
import Graphics.Pages.ActionListeners.NextButtonListener;
import Graphics.Pages.ActionListeners.PrevButtonListener;
import Graphics.Renderers.EmployeeRenderer;
import Graphics.Renderers.JobsRenderer;
import Graphics.Renderers.UserRenderer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompanyPageGui extends JFrame implements ActionListener, ListSelectionListener {

    private Company company;
    private JPanel panel1;
    private JLabel companyBudget;
    private JTextField budgetCompanyTextField;

    private JButton nextButton;
    private JButton seeProfileEmployeeButton;
    private JButton seeProfileManagerButton;

    private JScrollPane jScrollEmployeesIT;
    private JScrollPane JscrollJobsIT;
    private JScrollPane jScrollManagement;
    private JScrollPane jScrollJobsManagement;
    private JScrollPane jScrollFinanceEmployees;
    private JScrollPane jScrolljobsFinance;
    private JScrollPane jScrollMarketingEmployees;
    private JScrollPane jScrollMarketingJobs;

    private JLabel managementBudget;
    private JLabel ItBudgetLabel;
    private JLabel FinanceLabelBuget;
    private JLabel marketingBudget;
    private JButton prevButton;

    private DefaultListModel<Job> jobsItModel;
    private DefaultListModel<Job> jobsManagementModel;
    private DefaultListModel<Job> jobsMarketingModel;
    private DefaultListModel<Job> jobsFinanceModel;
    private DefaultListModel<Employee> employeesItModel;
    private DefaultListModel<Employee> employeesManagementModel;
    private DefaultListModel<Employee> employeesFinanceModel;
    private DefaultListModel<Employee> employeesMarketingModel;

    private JList<Job> jobsITList;
    private JList<Job> jobsManagementList;
    private JList<Job> jobsFinanceList;
    private JList<Job> jobsMarketingList;
    private JList<Employee> employeeITList;
    private JList<Employee> employeeManagementList;
    private JList<Employee> employeeMarketingList;
    private JList<Employee> employeeFinanceList;

    public CompanyPageGui(Company company) {
        super("Company page: " + company.getName());
        setContentPane(panel1);
        this.company = company;
        FactoryActionListener factory = new FactoryActionListener();

        employeesItModel = new DefaultListModel<>();
        employeeITList = new JList<>();
        for (Employee employee : company.
                getDepartment("Apllication.Departamente.IT").getEmployees())
            employeesItModel.addElement(employee);
        employeeITList.setModel(employeesItModel);
        employeeITList.setCellRenderer(new EmployeeRenderer());
        employeeITList.addListSelectionListener(this);
        jScrollEmployeesIT.setViewportView(employeeITList);

        employeesManagementModel = new DefaultListModel<>();
        employeeManagementList = new JList<>();
        for (Employee employee : company.
                getDepartment("Apllication.Departamente.Management").getEmployees())
            employeesManagementModel.addElement(employee);
        employeeManagementList.setModel(employeesManagementModel);
        employeeManagementList.setCellRenderer(new EmployeeRenderer());
        employeeManagementList.addListSelectionListener(this);
        jScrollManagement.setViewportView(employeeManagementList);

        employeesMarketingModel = new DefaultListModel<>();
        employeeMarketingList = new JList<>();
        for(Employee employee : company.
                getDepartment("Apllication.Departamente.Marketing").getEmployees())
            employeesMarketingModel.addElement(employee);
        employeeMarketingList.setModel(employeesMarketingModel);
        employeeMarketingList.setCellRenderer(new EmployeeRenderer());
        employeeMarketingList.addListSelectionListener(this);
        jScrollMarketingEmployees.setViewportView(employeeMarketingList);


        employeesFinanceModel = new DefaultListModel<>();
        employeeFinanceList = new JList<>();
        for (Employee employee : company.
                getDepartment("Apllication.Departamente.Finance").
                getEmployees())
            employeesFinanceModel.addElement(employee);
        employeeFinanceList.setModel(employeesFinanceModel);
        employeeFinanceList.setCellRenderer(new EmployeeRenderer());
        employeeFinanceList.addListSelectionListener(this);
        jScrollFinanceEmployees.setViewportView(employeeFinanceList);

        jobsItModel = new DefaultListModel<>();
        jobsITList = new JList<>();
            for(Job job : company.
                    getDepartment("Apllication.Departamente.IT")
                    .getJobs())
                jobsItModel.addElement(job);
        jobsITList.setModel(jobsItModel);
        jobsITList.setCellRenderer(new JobsRenderer());
        jobsITList.addListSelectionListener(this);
        JscrollJobsIT.setViewportView(jobsITList);

        jobsFinanceModel = new DefaultListModel<>();
        jobsFinanceList = new JList<>();
        for(Job job : company.
                getDepartment("Apllication.Departamente.Finance")
                .getJobs())
            jobsFinanceModel.addElement(job);
        jobsFinanceList.setModel(jobsFinanceModel);
        jobsFinanceList.setCellRenderer(new JobsRenderer());
        jobsFinanceList.addListSelectionListener(this);
        jScrolljobsFinance.setViewportView(jobsFinanceList);

        jobsManagementModel = new DefaultListModel<>();
        jobsManagementList = new JList<>();
        for(Job job : company.
                getDepartment("Apllication.Departamente.Management")
                .getJobs())
            jobsManagementModel.addElement(job);
        jobsManagementList.setModel(jobsManagementModel);
        jobsManagementList.setCellRenderer(new JobsRenderer());
        jobsManagementList.addListSelectionListener(this);
        jScrollJobsManagement.setViewportView(jobsManagementList);

        jobsMarketingModel = new DefaultListModel<>();
        jobsMarketingList = new JList<>();
        for(Job job : company.
                getDepartment("Apllication.Departamente.Marketing")
                .getJobs())
            jobsMarketingModel.addElement(job);
        jobsMarketingList.setModel(jobsMarketingModel);
        jobsMarketingList.setCellRenderer(new JobsRenderer());
        jobsMarketingList.addListSelectionListener(this);
        jScrollMarketingJobs.setViewportView(jobsMarketingList);

        managementBudget.setText("Budget: "+company.getDepartment
                ("Apllication.Departamente.Management")
                .getTotalSalaryBudget());

        ItBudgetLabel.setText("Budget: " + company.getDepartment
                ("Apllication.Departamente.IT")
                .getTotalSalaryBudget());

        FinanceLabelBuget.setText("Budget: " + company.getDepartment
                ("Apllication.Departamente.Finance")
                .getTotalSalaryBudget());

        marketingBudget.setText("Budget: " + company.getDepartment
                ("Apllication.Departamente.Marketing")
                .getTotalSalaryBudget());

        budgetCompanyTextField.setText("" +(
                company.getDepartment("Apllication.Departamente.Marketing")
                        .getTotalSalaryBudget()+
                company.getDepartment("Apllication.Departamente.Finance")
                        .getTotalSalaryBudget() +
                company.getDepartment("Apllication.Departamente.Management")
                        .getTotalSalaryBudget() +
                company.getDepartment("Apllication.Departamente.IT")
                        .getTotalSalaryBudget())
        );

        seeProfileEmployeeButton.addActionListener(this);
        seeProfileManagerButton.addActionListener(this);
        nextButton.addActionListener(FactoryActionListener.
                factory("Next", this));
        prevButton.addActionListener(FactoryActionListener.
                factory("Prev",this));

        setVisible(true);
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PagesList pagesList = PagesList.getInstance();

        if (e.getSource().equals(seeProfileEmployeeButton)) {

            if (employeeManagementList.getSelectedValue() != null) {
                pagesList.clearPagesStack(pagesList.getPagesArray().indexOf(this));
                setVisible(false);
                pagesList.add(new ProfilePageGui (employeeManagementList.getSelectedValue()));
            }

               if (employeeMarketingList.getSelectedValue() != null) {
                   pagesList.clearPagesStack(pagesList.getPagesArray().indexOf(this));
                   setVisible(false);
                   pagesList.add(new ProfilePageGui (employeeMarketingList.getSelectedValue()));
               }

               if (employeeITList.getSelectedValue() != null) {
                    pagesList.clearPagesStack(pagesList.getPagesArray().indexOf(this));
                    setVisible(false);
                    pagesList.add(new ProfilePageGui (employeeITList.getSelectedValue()));
               }

               if (employeeFinanceList.getSelectedValue() != null) {
                   pagesList.clearPagesStack(pagesList.getPagesArray().indexOf(this));
                   setVisible(false);
                   pagesList.add(new ProfilePageGui (employeeFinanceList.getSelectedValue()));
               }

               return;
        }
        if (e.getSource().equals(seeProfileManagerButton)) {
            Application app = Application.getInstance();
            pagesList.clearPagesStack(pagesList.getPagesArray().indexOf(this));
            setVisible(false);
            pagesList.add(new ProfilePageGui (app.getCompany(company.getName()).getMan()));
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        //this is used so that i can only have selected one item at all times
        if (!e.getSource().equals(employeeFinanceList))
            employeeFinanceList.clearSelection();
        if (!e.getSource().equals(employeeITList))
            employeeITList.clearSelection();
        if (!e.getSource().equals(employeeMarketingList))
            employeeMarketingList.clearSelection();
        if (!e.getSource().equals(employeeManagementList))
            employeeManagementList.clearSelection();

        if (!e.getSource().equals(jobsFinanceList))
            jobsFinanceList.clearSelection();
        if (!e.getSource().equals(jobsManagementList))
            jobsManagementList.clearSelection();
        if (!e.getSource().equals(jobsITList))
            jobsITList.clearSelection();
        if (!e.getSource().equals(jobsMarketingList))
            jobsMarketingList.clearSelection();

    }
}
