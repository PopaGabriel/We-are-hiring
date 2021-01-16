package Graphics.Renderers;

import Apllication.Language;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class LanguageRenderer extends JPanel implements ListCellRenderer<Language> {
    JLabel languageLevelLabel = new JLabel();
    JLabel languageNameLabel = new JLabel();

    @Override
    public Component getListCellRendererComponent(JList<? extends Language> list, Language language, int index, boolean isSelected, boolean cellHasFocus) {

        setLayout(new GridLayout(1,2));
        setBorder(new LineBorder(Color.orange, 1, true));

        languageNameLabel.setText(language.getNameOfLanguage());
        add(languageNameLabel);

        languageLevelLabel.setText("Level: "+language.getLevel());
        add(languageLevelLabel);

        if(isSelected)
            setBackground(Color.CYAN);
        else
            setBackground(Color.GRAY);

        return this;
    }
}
