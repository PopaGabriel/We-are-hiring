package Graphics.Renderers;

import Apllication.Experience;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.concurrent.Flow;

public class ExperienceRenderer extends JPanel implements ListCellRenderer<Experience> {
    private final JLabel nameOfCompany = new JLabel();
    private final JLabel workPeriod = new JLabel();
    private final JLabel position = new JLabel();
    @Override
    public Component getListCellRendererComponent(JList<? extends Experience> list, Experience experience, int index, boolean isSelected, boolean cellHasFocus) {
        setLayout(new GridLayout(3,1));
        setBorder(new LineBorder(StaticColorsPalet.getColorCellCorner()
                , 1, true));

        nameOfCompany.setText("Name of the company: "+experience.getNameOfCompany());
        workPeriod.setText("\nWork Period: "+ experience.getStartDate() + " - " + experience.getEndDate());
        position.setText("\nPosition: " + experience.getPosition());

        add(nameOfCompany);
        add(position);
        add(workPeriod);


        if (isSelected)
            setBackground(StaticColorsPalet.getColorCellSelect());
        else
            setBackground(StaticColorsPalet.getColorCellIdle());
        return this;
    }
}
