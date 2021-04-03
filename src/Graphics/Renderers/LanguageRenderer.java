package Graphics.Renderers;

import Apllication.Language;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class LanguageRenderer extends JPanel implements ListCellRenderer<Language> {
    private final JLabel langLabel = new JLabel();

    @Override
    public Component getListCellRendererComponent(JList<? extends Language> list, Language language, int index, boolean isSelected, boolean cellHasFocus) {
        setLayout(new FlowLayout());
        setBorder(new LineBorder(StaticColorsPalet.getColorCellCorner()
                ,1, true));

        langLabel.setText(language.getName() + " - " + language.getLevel());
        add(langLabel);

        if(isSelected)
            setBackground(StaticColorsPalet.getColorCellSelect());
        else
            setBackground(StaticColorsPalet.getColorCellIdle());

        return this;
    }
}
