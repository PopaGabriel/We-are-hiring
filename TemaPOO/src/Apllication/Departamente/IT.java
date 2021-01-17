package Apllication.Departamente;

import Apllication.Employee;

public class IT extends Department {
    public Integer taxe;

    public IT() {
        super();
    }

    @Override
    public Double getTotalSalaryBudget() {
        Double budget = 0.0;
        for(Employee employee : getEmployees())
            budget += employee.salary;
        return budget;
    }
}
