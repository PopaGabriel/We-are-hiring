package Graphics.Renderers;

import Apllication.User;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class UserRenderer extends JPanel implements ListCellRenderer<User> {
    JLabel name = new JLabel();
    JLabel finalGPA = new JLabel();
    JLabel totalScore = new JLabel();
    @Override
    public Component getListCellRendererComponent(JList<? extends User> list, User user, int index, boolean isSelected, boolean cellHasFocus) {

        setLayout(new GridLayout(3,1));
        setBorder(new LineBorder(Color.CYAN, 1, true));

        name.setText("Name: " + user.resume.information.getName() +" "+user.resume.information.getFirstName());
        add(name);

        finalGPA.setText("Final GPA: " + user.meanGPA());
        add(finalGPA);

        totalScore.setText("Total Score: " + user.getTotalScore());
        add(totalScore);

        if(isSelected)
            setBackground(Color.ORANGE);
        else
            setBackground(Color.GRAY);

        return this;
    }
}
