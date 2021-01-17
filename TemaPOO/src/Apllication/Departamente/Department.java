package Apllication.Departamente;

import Apllication.Employee;
import Apllication.Job;

import java.util.ArrayList;

public abstract class Department {
    public ArrayList<Employee> employeeArrayList;
    public ArrayList<Job> openJobs;

    public Department() {
        employeeArrayList = new ArrayList<>();
        openJobs = new ArrayList<>();
    }
    public abstract Double getTotalSalaryBudget();

    public void add(Job job) {
        openJobs.add(job);
    }

    public ArrayList<Job> getJobs() {
        return openJobs;
    }

    public void add(Employee employee) {
        employeeArrayList.add(employee);
    }

    public void remove(Employee employee) {
        employeeArrayList.remove(employee);
    }

    public ArrayList<Employee> getEmployees() {
        return employeeArrayList;
    }

    @Override
    public String toString() {
        return "{ employeeArrayList=" + employeeArrayList +
                ", openJobs= " + openJobs +
                "}\n";
    }
}

