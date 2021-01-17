package Apllication.Departamente;

import Apllication.Employee;

public class Management extends Department {
    public Integer taxe;

    public Management() {
        super();
    }
    @Override
    public Double getTotalSalaryBudget() {
        double budget = 0.0;
        for(Employee employee : getEmployees())
            budget += employee.salary * 100 / 84;
        return budget;
    }
}
