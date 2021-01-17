package Graphics.Renderers;

import Apllication.Company;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CompanyRenderer extends JPanel implements ListCellRenderer<Company> {
    JLabel name = new JLabel();
    JLabel openJobs = new JLabel();
    JLabel nrEmployees = new JLabel();
    @Override
    public Component getListCellRendererComponent(JList<? extends Company> list, Company comp, int index, boolean isSelected, boolean cellHasFocus) {
        setLayout(new GridLayout(3,1));
        setBorder(new LineBorder(Color.CYAN, 1, true));

        name.setText("Name of company: "+comp.nameOfCompany);
        add(name);

        openJobs.setText("Number of open jobs: " + comp.getJobs().size());
        add(openJobs);

        int employeesNumber = 0;
        for(int i = 0; i < comp.departmenteArrayList.size(); i++)
            employeesNumber += comp.departmenteArrayList
                    .get(i).employeeArrayList.size();
            nrEmployees.setText("Number of employees: " + employeesNumber);
            add(nrEmployees);

            if(isSelected)
                setBackground(Color.ORANGE);
            else
                setBackground(Color.GRAY);
        return this;
    }
}
