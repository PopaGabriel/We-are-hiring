package Graphics.Renderers;

import Apllication.Employee;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class EmployeeRenderer extends JPanel implements ListCellRenderer<Employee> {
    private final JLabel name = new JLabel();
    private final JLabel position = new JLabel();

    @Override
    public Component getListCellRendererComponent(JList<? extends Employee> list, Employee empl, int index, boolean isSelected, boolean cellHasFocus) {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBorder(new LineBorder(StaticColorsPalet.getColorCellCorner()
                , 1, true));

        name.setText("Name: " + empl.getName()+" " + empl.getFirstName());
        //Current job is always first
        position.setText("Position: " + empl.getHisExp().first().getPosition());

        add(name);
        add(position);

        if(isSelected)
            setBackground(StaticColorsPalet.getColorCellSelect());
        else
            setBackground(StaticColorsPalet.getColorCellIdle());
        return this;
    }
}
