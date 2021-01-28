package Apllication;

import javax.swing.*;
import java.util.ArrayList;

/*
this page simulates a stack of pages that are added
as we go through the app
we have to have only one instance of this class
because we have a single application
and as such a single stack of pages
plus we have to have have access to the page stack
wherever we are in the application
and as such I created a Singleton of this class
*/
public class PagesList {
    public static PagesList instance = null;
    private ArrayList<JFrame> pagesArray;

    private PagesList() {
        pagesArray = new ArrayList<>();
    }

    public static PagesList getInstance() {
        if (instance == null)
            instance = new PagesList();
        return instance;
    }

    public void add (JFrame page) {
        pagesArray.add(page);
    }

    public ArrayList<JFrame> getPagesArray() {
        return pagesArray;
    }

    public void setPagesArray(ArrayList<JFrame> pagesArray) {
        this.pagesArray = pagesArray;
    }

    /*
    I add one to i because the Array numbers his elements from
    0 to n-1, but size is numbered correctly from 1 to n
    and as such the simple solution is to add 1 to i
    the method itself clears all the pages that have been created
    after an index
    */
    public void clearPagesStack(int i) {
        while(i + 1 < pagesArray.size())
            pagesArray.remove(i + 1);
    }
    /*
    I add one to i because the Array numbers his elements from
    0 to n-1, but size is numbered correctly from 1 to n
    and as such the simple solution is to add 1 to i
    */
    public Boolean hasNext(int i) {
        if (i + 1 < pagesArray.size())
            return true;
        return false;
    }
    /*
    if the current page is not the one from which we started
    the application, then it automatically means the page must
    have a previous page
    */
    public Boolean hasPrev(int i) {
        if(i > 0)
            return true;
        return false;
    }
    public String toString() {
        return pagesArray + "";
    }

}
