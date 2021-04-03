package Apllication;

import Apllication.Departamente.*;
import Apllication.Interfaces.Observer;
import Apllication.Interfaces.Subject;

import java.time.LocalDate;
import java.util.ArrayList;

public class Company implements Subject {
    private final String nameOfCompany;
    private Manager manager;
    private final ArrayList<Department> departmenteArrayList;
    private final ArrayList<Recruiter> recruiterArrayList;
    private ArrayList<Observer> observers;

    public Company(String nameOfCompany) {
        departmenteArrayList = new ArrayList<>();
        recruiterArrayList = new ArrayList<>();
        observers = new ArrayList<>();
        this.nameOfCompany = nameOfCompany;
        manager = new Manager();
    }

    public ArrayList<Observer> getObservers() {
        return observers;
    }
    @Override
    public void addObserver(User user) {
        if (!observers.contains(user))
            observers.add(user);
    }
    @Override
    public void removeObserver(User user) {
        getObservers().remove(user);
    }
    @Override
    public void notifyAllObservers(Notifi notifi) {
        for (Observer observer : getObservers())
            observer.update(notifi);
    }

    public void add(Recruiter recruiter) {
        getRecruiters().add(recruiter);
    }
    public void add(Department department) {
        getDep().add(department);
    }
    public void add(Employee employee, Department department) {
        department.add(employee);
    }

    //if i remove a department i have to remove all the employees from
    //that department and add them to the userlist pool
    public void remove(Department department) {
        ArrayList<Employee> emplArrAux = department.getEmployees();
        for (int i = 0; i < emplArrAux.size();)
            remove(department.getEmployees().get(i));
        getDep().remove(department);
    }
    /*
    because a recruiter is always an employee also i can just remove
    him from the recruiter array and call for his employee counterpart
    to be removed. The if is important because otherwise these two will
    continue to call each other till the end of time
    */
    public void remove(Recruiter recruiter) {
        Application app = Application.getInstance();

        if(recruiter == null)
            return;

        getRecruiters().remove(recruiter);
        remove(app.getEmpl(recruiter.getName(), recruiter.getFirstName()));
    }
    /*
    all recruiters are employees but not the other way around
    so i always have to check if there is a recruiter with
    the same name, and if there is i call for the remove
    for that recruiter, i also finish his experience with
    the current company by adding the current date instead of
    the null endDate
    */
    public void remove(Employee employee) {
        Application app = Application.getInstance();

        if(employee == null)
            return;

        for (int i = 0; i < getDep().size(); i++)
            if (getDepartment(i).getEmployees().remove(employee))
                 {
                     //I update his work experience
                     if (employee.getHisExp().first().getEndDate() == null)
                         employee.getHisExp().first().setEndDate(LocalDate.now());
                     app.add(employee.convertToU());
                     break;
                 }

        if (contains(app.getRecr(employee.getName(),
                employee.getFirstName())))
            remove(app.getRecr(employee.getName(), employee.getFirstName()));

    }

    public void move(Department source, Department destination) {
        destination.getEmployees().addAll(source.getEmployees());
        getDep().remove(source);
    }
    /*
        i search where he is currently located and i remove him from there
        whilst i also add him to the new department
    */
    public void move(Employee employee, Department newDepartment) {
        for (Department department : getDep())
            if (department.contains(employee)) {
                department.remove(employee);
                break;
            }
        newDepartment.add(employee);
    }

    public boolean contains(Department department) {
        return getDep().contains(department);
    }
    public boolean contains(Employee employee) {
        for (Department department : getDep())
            if (department.contains(employee))
                return true;
        return false;
    }
    public boolean contains(Recruiter recruiter) {
        return getRecruiters().contains(recruiter);
    }

    /*
    I go through all the recruiters in the company
    and i compare their degree in friendship
    always choosing the highest one if they are equal
    i choose the one with greater rating
    */
    public Recruiter getRecruiter(User user) {
        Recruiter auxRecruter = null;
        int miniDegreeDistance = -1, auxDegreeDistance;

        for (Recruiter recruiter : getRecruiters()) {
            auxDegreeDistance = recruiter.getDegreeInFriendship(user);
            if (miniDegreeDistance < auxDegreeDistance) {
                miniDegreeDistance = auxDegreeDistance;
                auxRecruter = recruiter;
            } else if (miniDegreeDistance == auxDegreeDistance)
                if (auxRecruter.getRat() < recruiter.getRat())
                    auxRecruter = recruiter;
        }

        return auxRecruter;
    }
    public void setObservers (ArrayList<Observer> observers) {
        this.observers = observers;
    }
    public ArrayList<Department> getDep() {
        return departmenteArrayList;
    }
    public ArrayList<Recruiter> getRecruiters() {
        return recruiterArrayList;
    }
    public String getName() {
        return nameOfCompany;
    }
    public Manager getMan() {
        return manager;
    }
    public void setMan(Manager man) {
        manager = man;
    }

    /*
     the department getters are used further into the code
     and the arguments they get differs because they are
     used for specific reasons like searching with an index or
     with a specific name, or with a specific job
    */
    public Department getDepartment(int i) {
        if (i< getDep().size())
            return getDep().get(i);
        return null;
    }
    public Department getDepartment(String nameOfDepartment) {
        for (Department department : getDep())
            if (department.getName().compareTo(nameOfDepartment) == 0)
                return department;
        return null;
    }
    public Department getDepartment(Job job) {
        for (Department department : getDep())
            if(department.contains(job))
                return department;
        return null;
    }

    //returns all jobs from the company
    public ArrayList<Job> getJobs() {
        ArrayList<Job> jobs = new ArrayList<>();
        for (Department department : getDep())
            jobs.addAll(department.getJobs());
        return jobs;
    }

    /*
    because I don't need to see every single time
    the content of a department when i call toString
    also this should actually go through more departments and possibilities
     */
    public String getNamesDepartments() {
        StringBuilder listOfDepartmentsNames = new StringBuilder();
        for (Object object : getDep())
            if (object instanceof IT)
                listOfDepartmentsNames.append("IT ").append(object);
            else if (object instanceof Marketing)
                listOfDepartmentsNames.append("Marketing ").append(object);
            else if (object instanceof Management)
                listOfDepartmentsNames.append("Management ").append(object);
            else if(object instanceof Finance)
                listOfDepartmentsNames.append("Finance ").append(object);
        return listOfDepartmentsNames.toString();
    }
    @Override
    public String toString() {
        return "Apllication.Company{" +
                " nameOfCompany= " + nameOfCompany + '\'' +
                ", manager=" + manager +
                ", recruiters={" + recruiterArrayList +
                "}, department={\n" + getNamesDepartments() +
                '}';
    }
}