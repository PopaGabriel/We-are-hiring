package Apllication.Departamente;

import Apllication.Employee;

public class IT extends Department {
    public Integer taxe;

    public IT() {
        super();
    }
    //no amount of money taken from the salary
    @Override
    public Double getTotalSalaryBudget() {
        Double budget = 0.0;
        for(Employee employee : getEmployees())
            budget += employee.getSal();
        return budget;
    }
}
