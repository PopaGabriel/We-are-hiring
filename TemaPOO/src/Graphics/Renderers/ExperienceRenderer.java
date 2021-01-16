package Graphics.Renderers;

import Apllication.Experience;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.concurrent.Flow;

public class ExperienceRenderer extends JPanel implements ListCellRenderer<Experience> {
    JLabel nameOfCompany = new JLabel();
    JLabel workPeriod = new JLabel();
    JLabel position = new JLabel();
    @Override
    public Component getListCellRendererComponent(JList<? extends Experience> list, Experience experience, int index, boolean isSelected, boolean cellHasFocus) {
        setLayout(new GridLayout(3,1));
        setBorder(new LineBorder(Color.orange, 1, true));

        nameOfCompany.setText("Name of the company: "+experience.nameOfCompany);
        add(nameOfCompany);

        workPeriod.setText("\nWork Period: "+ experience.startDate + " - " + experience.endDate);
        add(workPeriod);

        position.setText("\nPosition: " + experience.position);
        add(position);


        if (isSelected)
            setBackground(Color.CYAN);
        else
            setBackground(Color.GRAY);
        return this;
    }
}
