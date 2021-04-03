package Graphics;

import Apllication.Company;
import Apllication.Departamente.Department;
import Apllication.Employee;
import Apllication.Job;
import Graphics.Renderers.EmployeeRenderer;
import Graphics.Renderers.JobsRenderer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CompanyPage extends JFrame implements ListSelectionListener, ActionListener {
    Company company;
    ArrayList<JList<Employee>> listsEmployees = new ArrayList<>();
    ArrayList<JList<Job>> listsJobs = new ArrayList<>();

    JTextField nameOfDepartment;
    JTextField numberEmpl;
    JTextField budgetDep;
    JTextField openJobs;

    JLabel totalBudget = new JLabel();

    JPanel pagePanel;
    JPanel departmentPanel;
    JPanel depPanelAux;
    JPanel infoDepartment;
    JPanel actionPanel;
    JPanel interiorDetailsPanel;

    JList<Job> jobsJList;
    JList<Employee> employeeJList;

    JButton seeProfile = new JButton();
    JButton seeManagerProfile = new JButton();

    public CompanyPage(Company company) {
        super(company.nameOfCompany);
        this.company = company;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        pagePanel = new JPanel(new GridLayout(1,2));
        actionPanel = new JPanel(new GridLayout(7,1));

        //Here i add the title
        actionPanel.add(new JLabel("Name of Comapany: " + company.nameOfCompany));
        addDepartments();

        add(pagePanel);
        pagePanel.add(actionPanel);

        setVisible(true);
    }
    public void addDepartments() {
        departmentPanel = new JPanel(new GridLayout(company.departmenteArrayList.size(), 1));
        for(int i = 0; i < company.departmenteArrayList.size(); i++) {
            Department department = company.departmenteArrayList.get(i);
            depPanelAux = new JPanel(new GridLayout(2,1));
            interiorDetailsPanel = new JPanel(new GridLayout(1,2));
            infoDepartment = new JPanel(new FlowLayout(FlowLayout.LEFT));

            nameOfDepartment = new JTextField("Name: " + department.getClass().
                    getName().replace("Apllication.Departamente.",""));
            numberEmpl = new JTextField("Number employees: " + department.
                    getEmployees().size());
            openJobs = new JTextField("Number open jobs: " + department.
                    getJobs().size());
            budgetDep = new JTextField("Budget department: " + department.getTotalSalaryBudget());


            infoDepartment.add(nameOfDepartment);
            infoDepartment.add(numberEmpl);
            infoDepartment.add(openJobs);
            infoDepartment.add(budgetDep);
            depPanelAux.add(infoDepartment);

            addListEmployees(department);
            addListJobs(department);

            depPanelAux.add(interiorDetailsPanel);
            departmentPanel.add(depPanelAux);

        }
            addButtonsAndInfo();
            pagePanel.add(departmentPanel);
    }
    public void addListEmployees(Department department) {
        employeeJList  = new JList<>();
        listsEmployees.add(employeeJList);
        DefaultListModel<Employee> employeeModel = new DefaultListModel<>();
        for (int i = 0; i < department.getEmployees().size(); i++)
            employeeModel.addElement(department.getEmployees().get(i));
        employeeJList.setModel(employeeModel);
        employeeJList.setCellRenderer(new EmployeeRenderer());
        employeeJList.addListSelectionListener(this);
        interiorDetailsPanel.add(new JScrollPane(employeeJList));
    }
    public void addListJobs(Department department) {
        jobsJList = new JList<>();
        listsJobs.add(jobsJList);
        DefaultListModel<Job> jobsModel = new DefaultListModel<>();
        for (int i = 0; i < department.getJobs().size(); i++)
            jobsModel.addElement(department.getJobs().get(i));
        jobsJList.setModel(jobsModel);
        jobsJList.setCellRenderer(new JobsRenderer());
        jobsJList.addListSelectionListener(this);
        interiorDetailsPanel.add(new JScrollPane(jobsJList));
    }
    public void addButtonsAndInfo() {
        double totalBudg = 0.0;

        seeProfile.setText("See profile page");
        seeProfile.addActionListener(this);
        actionPanel.add(seeProfile);

        seeManagerProfile.setText("See Managers Profile page");
        seeManagerProfile.addActionListener(this);
        actionPanel.add(seeManagerProfile);

        for(Department department : company.departmenteArrayList)
            totalBudg += department.getTotalSalaryBudget();
        totalBudget.setText("Total budget is: " + totalBudg);
        actionPanel.add(totalBudget);

    }

    //if i choose another element i unchoose the others
    @Override
    public void valueChanged(ListSelectionEvent e) {
        for (JList<Employee> jList : listsEmployees)
            if(!e.getSource().equals(jList))
                jList.clearSelection();
            for(JList<Job> jobJList : listsJobs)
                if(!e.getSource().equals(jobJList))
                    jobJList.clearSelection();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JList<Employee> jlist = null;
        for(int i = 0 ; i < listsEmployees.size(); i++)
            if(listsEmployees.get(i).getSelectedIndex() != -1){
                 jlist = listsEmployees.get(i);
                break;
            }
//        System.out.println(index);
        if(e.getSource().equals(seeProfile)) {
            if(jlist != null) {
                new ProfilePage(jlist.getSelectedValue());
                return;
            }
        }
        if(e.getSource().equals(seeManagerProfile)) {
            new ProfilePage(company.manager);
            return;
        }
    }
}
