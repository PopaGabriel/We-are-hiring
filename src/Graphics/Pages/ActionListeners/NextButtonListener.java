package Graphics.Pages.ActionListeners;

import Apllication.PagesList;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class NextButtonListener extends ActionListenerAbstract {
    JFrame page;

    public NextButtonListener(JFrame page) {
            this.page = page;
    }
    /*
     when i click the button i look if there is a page that has
     been added later and hasn't been cleared by the creation
     of a new page. If there exists a page after this than i make
     the current page invisible and the next one visible
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        PagesList pagesList = PagesList.getInstance();

        if (pagesList.hasNext(pagesList.getPagesArray().indexOf(page))) {
            pagesList.getPagesArray().get(pagesList.getPagesArray().
                    indexOf(page) + 1).setVisible(true);
            page.setVisible(false);
        }
    }
}
