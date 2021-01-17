package Graphics.Renderers;

import Apllication.Employee;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class EmployeeRenderer extends JPanel implements ListCellRenderer<Employee> {
    JLabel name = new JLabel();
    JLabel salary = new JLabel();
    JLabel position = new JLabel();
    JLabel experience = new JLabel();

    @Override
    public Component getListCellRendererComponent(JList<? extends Employee> list, Employee empl, int index, boolean isSelected, boolean cellHasFocus) {
        setLayout(new GridLayout(4,1));
        setBorder(new LineBorder(Color.CYAN, 1, true));

        name.setText("Name: " + empl.resume.information.getName()+" "
                + empl.resume.information.getFirstName());
        add(name);

        salary.setText("Salaray: "+ empl.salary);
        add(salary);

        //Current job is always first
        position.setText("Position: " + empl.resume.historyExperience.first().position);
        add(position);

        experience.setText("Work experience: " + empl.getExperienceTime());
        add(experience);

        if(isSelected)
            setBackground(Color.ORANGE);
        else
            setBackground(Color.GRAY);
        return this;
    }
}
