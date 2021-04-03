package Graphics.Renderers;

import Apllication.Education;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class EducationRenderer extends JPanel implements ListCellRenderer<Education> {
    private final JLabel periodLabel = new JLabel();
    private final JLabel educationLevelLabel = new JLabel();
    private final JLabel institutionNameLabel = new JLabel();

    @Override
    public Component getListCellRendererComponent(JList<? extends Education> list, Education edu, int index, boolean isSelected, boolean cellHasFocus) {
        setLayout(new GridLayout(3,1));
        setBorder(new LineBorder(StaticColorsPalet.getColorCellCorner()
                , 1, true));

        institutionNameLabel.setText("Name of Institution: "+edu.getName());
        educationLevelLabel.setText("Education Level: " + (""+ edu.getEducationLevel()).
                toUpperCase());
        periodLabel.setText("Period: "+edu.getStartDate()
                + "-" + edu.getEndDate());

        add(educationLevelLabel);
        add(institutionNameLabel);
        add(periodLabel);


        if(isSelected)
            setBackground(StaticColorsPalet.getColorCellSelect());
        else
            setBackground(StaticColorsPalet.getColorCellIdle());

        return this;
    }
}
