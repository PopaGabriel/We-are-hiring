package Apllication;

import java.util.ArrayList;

public class Employee extends User {
    private String companyName;
    private Double salary;

    public Employee() {
        super();
    }

    //converts an employee to a recruiter
    public Recruiter convert() {
        Recruiter recruiter = new Recruiter();
        recruiter.setResume(getRes());
        recruiter.setSalary(salary);
        recruiter.setCompanyName(companyName);
        recruiter.setNotifiStack(getNotifiStack());
        for (int i = 0; i < getFriends().size();) {
            recruiter.add(getFriends().get(i));
            getFriends().get(i).remove(this);
        }

        return recruiter;
    }

    public Manager convertToM() {
        Manager manager = new Manager();
        manager.setResume(getRes());
        manager.setSalary(salary);
        manager.setCompanyName(companyName);
        manager.setNotifiStack(getNotifiStack());
        //possible error incoming here because of the foreach
        //combined with remove
        for (Consumer consumer : getFriends()) {
            consumer.remove(this);
            consumer.add(manager);
        }
        return manager;
    }

    //this method is used when you fire an employee/recruiter from a cpmpany
    //because he will be added tot the user pool
    public User convertToU() {
        User user = new User();
        user.setResume(getRes());
        user.setNotifiStack(getNotifiStack());

        for (int i = 0; i < getFriends().size();) {
            user.add(getFriends().get(i));
            getFriends().get(i).remove(this);
        }
        return user;
    }

    //setter and getter section
    public void setSalary(Double sal) {
        salary = sal;
    }
    public Double getSal() {
        return salary;
    }
    public String getCompName() {
        return  companyName;
    }
    public void setCompanyName(String compName) {
        companyName = compName;
    }
    public String getCompanyName() {return companyName;}
    /*
    this returns the current job
    this works because the current job is always
     the first element in the tree set
    */
    public Experience getCurrentJob() {
        return getHisExp().first();
    }


    public String toString() {
        return " CompanyName " + companyName + " Salary " + salary + " "
                + super.toString();
    }
}
