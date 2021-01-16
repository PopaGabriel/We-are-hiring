package Graphics;

import Apllication.Company;
import Apllication.Departamente.Department;
import Apllication.Employee;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CompanyPage extends JFrame {
    Company company;
    JLabel nameOfCompanyLabel;

    JPanel pagePanel;
    JPanel nameOfCompanyPanel;
    JPanel mainBody;

    JList<Department> departmentJList;
    JList<Employee> employeeJList;

    public CompanyPage(Company company) {
        super(company.nameOfCompany);
        this.company = company;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        pagePanel = new JPanel();
        pagePanel.setLayout(new BoxLayout(pagePanel, BoxLayout.Y_AXIS));
        //Here i add the title
        nameOfCompanyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        nameOfCompanyPanel.setBorder(new EmptyBorder(40,20,20,20));
        nameOfCompanyLabel = new JLabel(company.nameOfCompany);

        nameOfCompanyPanel.add(nameOfCompanyLabel);
        pagePanel.add(nameOfCompanyPanel);

        mainBody = new JPanel(new BoxLayout(mainBody, BoxLayout.X_AXIS));
        addDepartments();

        add(pagePanel);

        setVisible(true);
    }
    public void addDepartments() {
        System.out.println(company.departmenteArrayList);


    }
}
