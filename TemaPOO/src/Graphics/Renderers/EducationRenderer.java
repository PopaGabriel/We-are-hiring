package Graphics.Renderers;

import Apllication.Education;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class EducationRenderer extends JPanel implements ListCellRenderer<Education> {
    JLabel finalGpaLabel = new JLabel();
    JLabel periodLabel = new JLabel();
    JLabel educationLevelLabel = new JLabel();
    JLabel institutionNameLabel = new JLabel();

    @Override
    public Component getListCellRendererComponent(JList<? extends Education> list, Education edu, int index, boolean isSelected, boolean cellHasFocus) {
        setLayout(new GridLayout(4,1));
        setBorder(new LineBorder(Color.orange, 1, true));

        institutionNameLabel.setText("Name of Institution: "+edu.nameOfInstitution);
        add(institutionNameLabel);

        educationLevelLabel.setText("Education Level: " + edu.educationLevel);
        add(educationLevelLabel);

        finalGpaLabel.setText("Final Gpa: "+edu.finalGPA);
        add(finalGpaLabel);

        periodLabel.setText("Period: "+edu.startDate + "-" + edu.endDate);
        add(periodLabel);


        if(isSelected)
            setBackground(Color.ORANGE);
        else
            setBackground(Color.GRAY);

        return this;
    }
}
