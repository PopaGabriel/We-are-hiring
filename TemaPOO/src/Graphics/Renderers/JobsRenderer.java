package Graphics.Renderers;

import Apllication.Job;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class JobsRenderer extends JPanel implements ListCellRenderer<Job> {
    JLabel noPos = new JLabel();
    JLabel flag = new JLabel();
    JLabel salary = new JLabel();
    JLabel position = new JLabel();
    @Override
    public Component getListCellRendererComponent(JList<? extends Job> list, Job job, int index, boolean isSelected, boolean cellHasFocus) {
        setLayout(new GridLayout(4,1));
        setBorder(new LineBorder(Color.CYAN, 1, true));

        noPos.setText("Number position: " + job.noPositions);
        add(noPos);

        if(job.flag == 1)
            flag.setText("Status: Open");
        else
            flag.setText("Status: Closed");
        add(flag);

        salary.setText(job.salary + "");
        add(salary);

        position.setText("Position: " + job.nameOfJob);
        add(position);

        if (isSelected)
            setBackground(Color.ORANGE);
        else
            setBackground(Color.GRAY);

        return this;
    }
}
