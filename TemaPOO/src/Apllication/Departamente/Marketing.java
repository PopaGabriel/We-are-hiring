package Apllication.Departamente;

import Apllication.Employee;

public class Marketing extends Department {
    public Integer taxe;

    public Marketing() {
        super();
    }

    @Override
    public Double getTotalSalaryBudget() {
        double budget = 0.0;
        for(Employee employee : getEmployees()) {
            if(employee.salary > 5000.0)
                budget += employee.salary * 100 / 90;
            else if (employee.salary < 3000)
                budget += employee.salary;
            else
                budget += employee.salary * 100 / 84;
        }
        return budget;
    }

}
