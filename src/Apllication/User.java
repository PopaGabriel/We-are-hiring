package Apllication;

import Apllication.Interfaces.Observer;

import java.util.ArrayList;

public class User extends Consumer implements Observer {
    private ArrayList<String> listWantedCompany;
    private ArrayList<Notifi> notifiStack;

    public User() {
        super();
        listWantedCompany = new ArrayList<>();
        notifiStack = new ArrayList<>();
    }

    @Override
    public void update(Notifi notifi) {
        notifiStack.add(notifi);
    }
    //Setter and getter section
    public ArrayList<String> getWantCom() {
        return listWantedCompany;
    }
    public Boolean contains(String nameOfCom) {
        return listWantedCompany.contains(nameOfCom);
    }
    public void setLWantCom(ArrayList<String> arrayList) {
        listWantedCompany = arrayList;
    }
    public ArrayList<Notifi> getNotifiStack() {
        return notifiStack;
    }
    public void setNotifiStack(ArrayList<Notifi> notifiStack) {
        this.notifiStack = notifiStack;
    }
    //i add the interested company to the array
    public void add (String nameComp) {
        if(!contains(nameComp))
            listWantedCompany.add(nameComp);
    }

    /*
        I remove him from the user list of the application
        than update his friends list and copy his old resume
        whilst making his wanted companies null
        because he no longer searching for a job
    */
    public Employee convert() {

        Application app = Application.getInstance();
        Employee employee = new Employee();
        employee.setLWantCom(null);
        employee.setResume(getRes());
        employee.setNotifiStack(getNotifiStack());

        /*
        I update his friends because if i don't
        they will have the old user as a friend
        whilst i need them to befriend the new employee
        */
        for (int i = 0; i < getFriends().size();) {
            employee.add(getFriends().get(i));
            getFriends().get(i).remove(this);
        }
        app.remove(this);

        return employee;

    }
    //i return the score using the given formula
    public Double getTotalScore() {
        return getExperienceTime() * 1.5 + meanGPA();
    }
    public String toString() {
        return getRes() + " Friends: " + showFriends() + " Wanted Comp: "
                + listWantedCompany + " \n";
    }
}