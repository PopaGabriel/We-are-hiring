package Graphics.Pages.ActionListeners;

import Apllication.PagesList;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PrevButtonListener extends ActionListenerAbstract {
    JFrame page;
    public PrevButtonListener(JFrame page) {
        this.page = page;
    }
    /*
     when i click the button i look if there is a page that has
     been added earlier. If there exists a page before this than I make
     the current page invisible and the previous one visible
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        PagesList pagesList = PagesList.getInstance();
            if (pagesList.hasPrev(pagesList.getPagesArray().indexOf(page))) {
                pagesList.getPagesArray().get(pagesList.getPagesArray().
                        indexOf(page) - 1).setVisible(true);
                page.setVisible(false);
            }
    }
}