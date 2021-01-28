package Graphics.Pages.ActionListeners;

import javax.swing.*;

public class FactoryActionListener {
    public static ActionListenerAbstract factory(String type, JFrame page) {
        if (type.compareTo("Next") == 0)
            return new NextButtonListener(page);
        if (type.compareTo("Prev") == 0)
            return new PrevButtonListener(page);
        return null;
    }
}
