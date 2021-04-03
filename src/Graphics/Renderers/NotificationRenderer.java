package Graphics.Renderers;

import Apllication.Notifi;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class NotificationRenderer extends JPanel implements ListCellRenderer<Notifi>{
    private final JLabel mess = new JLabel();
    @Override
    public Component getListCellRendererComponent(JList<? extends Notifi> list, Notifi value, int index, boolean isSelected, boolean cellHasFocus) {
        setLayout(new FlowLayout());
        setBorder(new LineBorder(StaticColorsPalet.getColorCellCorner()
                , 1, true));

        mess.setText(value.getMessage());
        add(mess);

        if(isSelected)
            setBackground(StaticColorsPalet.getColorCellSelect());
        else
            setBackground(StaticColorsPalet.getColorCellIdle());
        return this;
    }
}
