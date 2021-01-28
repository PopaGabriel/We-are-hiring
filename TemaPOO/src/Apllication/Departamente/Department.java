package Apllication.Departamente;

import Apllication.Employee;
import Apllication.Job;
import Apllication.Recruiter;

import java.util.ArrayList;

public abstract class Department {
    public ArrayList<Employee> employeeArrayList;
    public ArrayList<Job> openJobs;

    public Department() {
        employeeArrayList = new ArrayList<>();
        openJobs = new ArrayList<>();
    }
    /*
    because we don't know at which department
    the worker actually worked at
    we will only asses the current job experience
    */
    public abstract Double getTotalSalaryBudget();

    //Setters and getters section
    public ArrayList<Employee> getEmployees() {
        return employeeArrayList;
    }
    public String getName() {
        return getClass().getName();
    }
    public ArrayList<Job> getJobs() {
        return openJobs;
    }
    public void add(Job job) {
        getJobs().add(job);
    }
    public void add(Employee employee) {
        getEmployees().add(employee);
    }
    public void remove(Employee employee) {
        getEmployees().remove(employee);
    }

    /*
        these methods were used previously but now they are not
        i kept them because they might be useful in the future
        also they are quite versatile
    */
    public Job getJob (int i) {
        if (i + 1 > openJobs.size())
            return null;
        return openJobs.get(i);
    }
    public Job getJob(String name) {
        for (Job job : openJobs)
            if (job.getPos().compareTo(name) == 0)
                return job;
        return null;
    }

    //the contains method are needed in other classes for easier verification
    public Boolean contains(Job job) {
        return openJobs.contains(job);
    }
    public Boolean contains(Employee employee) {
        return getEmployees().contains(employee);
    }

    @Override
    public String toString() {
        return "{ employeeArrayList=" + employeeArrayList +
                ", openJobs= " + openJobs +
                "}\n";
    }
}

