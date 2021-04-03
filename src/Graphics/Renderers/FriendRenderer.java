package Graphics.Renderers;

import Apllication.Consumer;
import Apllication.Employee;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class FriendRenderer extends JPanel implements ListCellRenderer<Consumer> {
    private final JLabel name = new JLabel();
    private final JLabel pos = new JLabel();
    @Override
    public Component getListCellRendererComponent(JList<? extends Consumer> list, Consumer con, int index, boolean isSelected, boolean cellHasFocus) {
        setLayout(new FlowLayout());
        setBorder(new LineBorder(StaticColorsPalet.getColorCellCorner()
                , 1, true));


        name.setText(con.getName() + " - " +con.getFirstName());
        if (con.getClass().getName().compareTo("Apllication.Employee") == 0)
            pos.setText("Position: " + con.getHisExp().first().getPosition());
        else if (con.getClass().getName().compareTo("Apllication.Recruiter") == 0)
            pos.setText("Position: " + con.getHisExp().first().getPosition());
        else if (con.getClass().getName().compareTo("Apllication.Manager") == 0)
            pos.setText("Position: " + con.getHisExp().first().getPosition());
        else
            pos.setText("Unemployed");

        add(name);
        add(pos);

        if(isSelected)
            setBackground(StaticColorsPalet.getColorCellSelect());
        else
            setBackground(StaticColorsPalet.getColorCellIdle());

        return this;
    }
}
