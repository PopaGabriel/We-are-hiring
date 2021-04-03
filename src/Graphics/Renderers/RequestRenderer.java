package Graphics.Renderers;

import Apllication.Job;
import Apllication.Recruiter;
import Apllication.Request;
import Apllication.User;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class RequestRenderer extends JPanel implements ListCellRenderer<Request<Job, User>> {

    private final JLabel nameUser = new JLabel();
    private final JLabel nameRecruiter = new JLabel();
    private final JLabel position = new JLabel();
    private final JLabel scoreRequest = new JLabel();

    @Override
    public Component getListCellRendererComponent(JList<? extends Request<Job, User>> list, Request<Job, User> request, int index, boolean isSelected, boolean cellHasFocus) {
        setLayout(new GridLayout(4,1));
        setBorder(new LineBorder(StaticColorsPalet.getColorCellCorner()
                , 1, true));

        nameUser.setText("Applicant name: " + request.getValue1().getName() + " " +
                request.getValue1().getFirstName());
        nameRecruiter.setText("Recruiter name: " + request.getValue2()
                .getName() + " " + request.getValue2().getFirstName());
        position.setText("Position: " + request.getKey().getPos());
        scoreRequest.setText("Final Score: " + request.getScore());

        add(scoreRequest);
        add(nameUser);
        add(position);
        add(nameRecruiter);

        if(isSelected)
            setBackground(StaticColorsPalet.getColorCellSelect());
        else
            setBackground(StaticColorsPalet.getColorCellIdle());

        return this;
    }
}
