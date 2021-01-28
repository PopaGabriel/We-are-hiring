package Graphics.Renderers;

import Apllication.Company;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CompanyRenderer extends JPanel implements ListCellRenderer<Company> {
    private final JLabel name = new JLabel();

    @Override
    public Component getListCellRendererComponent(JList<? extends Company> list, Company comp, int index, boolean isSelected, boolean cellHasFocus) {
        setLayout(new FlowLayout());
        setBorder(new LineBorder(StaticColorsPalet.getColorCellCorner(),
                1, true));

        name.setText("Name of company: "+comp.getName());
        add(name);

        if(isSelected)
            setBackground(StaticColorsPalet.getColorCellSelect());
        else
            setBackground(StaticColorsPalet.getColorCellIdle());

        return this;
    }
}
