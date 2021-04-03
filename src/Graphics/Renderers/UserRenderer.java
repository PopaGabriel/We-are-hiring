package Graphics.Renderers;

import Apllication.User;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class UserRenderer extends JPanel implements ListCellRenderer<User> {
    private final JLabel name = new JLabel();
    private final JLabel totalScore = new JLabel();
    @Override
    public Component getListCellRendererComponent(JList<? extends User> list, User user, int index, boolean isSelected, boolean cellHasFocus) {

        setLayout(new GridLayout(2,1));
        setBorder(new LineBorder(StaticColorsPalet.getColorCellCorner()
                , 1, true));

        name.setText("Name: " + user.getName() +" "+user.getFirstName());
        totalScore.setText("Total Score: " + user.getTotalScore());

        add(name);
        add(totalScore);

        if(isSelected)
            setBackground(StaticColorsPalet.getColorCellSelect());
        else
            setBackground(StaticColorsPalet.getColorCellIdle());

        return this;
    }
}
