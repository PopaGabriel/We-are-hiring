package Apllication;

import Apllication.Departamente.*;
import Apllication.Interfaces.Observer;
import Apllication.Interfaces.Subject;

import java.util.ArrayList;

public class Company implements Subject {
    public String nameOfCompany;
    public Manager manager;
    public ArrayList<Department> departmenteArrayList;
    public ArrayList<Recruiter> recruiterArrayList;

    public ArrayList<Observer> observers;


    public Company(String nameOfCompany){
        departmenteArrayList = new ArrayList<>();
        recruiterArrayList = new ArrayList<>();
        observers = new ArrayList<>();
        this.nameOfCompany = nameOfCompany;
        manager = new Manager();
    }


    @Override
    public void addObserver(User user) {
        if(!observers.contains(user))
            observers.add(user);
    }

    @Override
    public void removeObserver(User user) {
        observers.remove(user);
    }

    @Override
    public void notifyAllObservers(String mess) {
        for(Observer observer : observers)
            observer.update(mess);
    }

    public void add(Recruiter recruiter){
        recruiterArrayList.add(recruiter);
    }

    public void add(Department department){
        departmenteArrayList.add(department);
    }

    public void add(Employee employee, Department department){
        department.employeeArrayList.add(employee);
    }
    public void remove(Department department){
        department.employeeArrayList.clear();
        departmenteArrayList.remove(department);
    }
    public void remove(Recruiter recruiter){
        recruiterArrayList.remove(recruiter);
    }
    public void remove(Employee employee){
        for (Department department : departmenteArrayList)
            if(department.employeeArrayList.remove(employee))
                return;
    }
    public Department getDepartment(String nameOfDep) {
        for(Department department : departmenteArrayList)
            if(department.getClass().getName().
                    compareTo(nameOfDep) == 0){
                return department;
            }
            return null;
    }
    public ArrayList<Job> getJobs(){
        ArrayList<Job> jobs = new ArrayList<>();
        for(Department department : departmenteArrayList)
            jobs.addAll(department.getJobs());
        return jobs;
    }
    public void move(Department source, Department destination) {
        destination.employeeArrayList.addAll(source.employeeArrayList);
        departmenteArrayList.remove(source);
    }
    //the if is added for efficiency remove return true if he finds
    //the object in the arraylist, and as such it is no longer
    //required to search further
    //we use a method that we defined earlier
    public void move(Employee employee, Department newDepartment){
        remove(employee);
        newDepartment.employeeArrayList.add(employee);
    }
    public boolean contains(Department department){
        return departmenteArrayList.contains(department);
    }
    public boolean contains(Employee employee) {
        for(Department department : departmenteArrayList)
            if(department.employeeArrayList.contains(employee))
                return true;
            return false;
    }
    public boolean contains(Recruiter recruiter) {
        return recruiterArrayList.contains(recruiter);
    }
    public Recruiter getRecruiter(User user){
        Recruiter auxRecruter = null;
        int miniDegreeDistance = -1, auxDegreeDistance;
        for(Recruiter recruiter : recruiterArrayList) {
            auxDegreeDistance = recruiter.getDegreeInFriendship(user);

            if(miniDegreeDistance < auxDegreeDistance){
                miniDegreeDistance = auxDegreeDistance;
                auxRecruter = recruiter;
            } else if(miniDegreeDistance == auxDegreeDistance) {
                if(auxRecruter.rating < recruiter.rating)
                    auxRecruter = recruiter;
            }
        }
        return auxRecruter;
    }
    public String getNamesDepartments(){
        String listOfDepartmentsNames = "";
        for(Object object : departmenteArrayList) {
            if(object instanceof IT)
                listOfDepartmentsNames += "IT " + object;
            if(object instanceof Marketing)
                listOfDepartmentsNames += "Marketing " + object;
            if(object instanceof Management)
                listOfDepartmentsNames += "Management " + object;
            if(object instanceof Finance)
                listOfDepartmentsNames += "Finance " + object;
        }
        return listOfDepartmentsNames;
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