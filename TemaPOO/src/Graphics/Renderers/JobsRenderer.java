package Graphics.Renderers;

import Apllication.Job;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class JobsRenderer extends JPanel implements ListCellRenderer<Job> {
    private final JLabel noPos = new JLabel();
    private final JLabel flag = new JLabel();
    private final JLabel salary = new JLabel();
    private final JLabel position = new JLabel();
    @Override
    public Component getListCellRendererComponent(JList<? extends Job> list, Job job, int index, boolean isSelected, boolean cellHasFocus) {
        setLayout(new GridLayout(4,1));
        setBorder(new LineBorder(StaticColorsPalet.getColorCellCorner()
                , 1, true));

        noPos.setText("Number position: " + job.getNoPositions());
        add(noPos);

        if(job.getFlag() == 1)
            flag.setText("Status: Open");
        else
            flag.setText("Status: Closed");
        add(flag);

        salary.setText(job.getSal() + "");
        add(salary);

        position.setText("Position: " + job.getPos());
        add(position);

        if (isSelected)
            setBackground(StaticColorsPalet.getColorCellSelect());
        else
            setBackground(StaticColorsPalet.getColorCellIdle());

        return this;
    }
}
