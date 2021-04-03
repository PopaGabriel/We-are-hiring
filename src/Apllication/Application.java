package Apllication;

import Apllication.Departamente.Department;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static Application instance = null;

    private final ArrayList<User> userList;
    private final ArrayList<Company> companyList;

    private Application() {
        userList = new ArrayList<>();
        companyList = new ArrayList<>();
    }

    public static Application getInstance() {
        if (instance == null)
            instance = new Application();

        return instance;
    }

    public Boolean contains(User user) {
        return userList.contains(user);
    }

    public ArrayList<Company> getCompanies() {
        return companyList;
    }

    public Company getCompany(String name) {
        for (Company company : companyList)
            if (company.getName().compareTo(name) == 0)
                return company;
        return null;
    }
    public ArrayList<Job> getJobs(List<String> jobList) {
        ArrayList<Job> jobArrayList = new ArrayList<>();
        for (String name : jobList)
            if(getCompany(name) != null)
                for (Department department : getCompany(name).getDep())
                    jobArrayList.addAll(department.getJobs());
        return jobArrayList;
    }
    public void add(Company company) {
        companyList.add(company);
    }

    public void add(User user) {
        userList.add(user);
    }
//we remove each department so all employees return to the
    //user list
    public boolean remove(Company company) {
        if(company == null)
            return false;
        if (companyList.contains(company)) {
            for (int i = 0; i < company.getDep().size();)
                company.remove(company.getDepartment(i));
            companyList.remove(company);
            return true;
        }
        return false;
    }
    //we go through their friends and eliminate their connections
    public boolean remove(User user) {
        if (userList.contains(user)) {
            for (int i = 0; i < user.getFriends().size();)
                user.getFriends(i).remove(user);
            userList.remove(user);
            return true;
        }
        return false;
    }

    //here i search through all the consumers in the application
    //and return the consumer if we find him
    public Consumer getCons(String name, String firstName) {
        Consumer consumer = null;
        //search through recruiters
        for (Company company : companyList)
            for (Recruiter recruiter : company.getRecruiters())
                if (recruiter.getName().compareTo(name) == 0) {
                    if (recruiter.getFirstName().compareTo(firstName) == 0) {
                        return recruiter;
                    }
                }

        //search through employees
        for (Company company : companyList)
            for (Department department : company.getDep())
                for (Employee employee : department.getEmployees())
                    if (employee.getName().compareTo(name) == 0)
                        if (employee.getFirstName().compareTo(firstName) == 0)
                            return employee;

        //Search through all users
        for (User user : userList)
            if (user.getName().compareTo(name) == 0)
                if (user.getFirstName().compareTo(firstName) == 0)
                    return user;

        //Search through all managers
        for (Company company : companyList)
            if (company.getMan().getName().compareTo(name) == 0)
                if(company.getMan().getFirstName().compareTo(firstName) == 0)
                    return company.getMan();

        return consumer;
    }
    public ArrayList<User> getUserList() {
        return userList;
    }

    public Employee getEmpl(String name, String firstName) {
        for (Company company : companyList)
            for (Department department : company.getDep())
                for (Employee employee : department.getEmployees())
                    if (employee.getName().compareTo(name) == 0)
                        if (employee.getFirstName().compareTo(firstName) == 0)
                            return employee;

        return null;
    }

    public Recruiter getRecr(String name, String firsName) {
        for (Company company : companyList)
            for (Recruiter recruiter : company.getRecruiters())
                if (recruiter.getName().compareTo(name) == 0)
                    if (recruiter.getFirstName().compareTo(firsName) == 0)
                        return recruiter;
        return null;
    }

    //this returns a company from the companies list
    //an index
    public Company getCompany(int i) {
        if(i > companyList.size())
            return null;
        return companyList.get(i);
    }
    //returns a user from the user list using
    //using an index
    public User getUser(int i){
        if (i > userList.size())
            return null;
        return userList.get(i);
    }
    public String toString() {
        return userList + " " + companyList;
    }
}
