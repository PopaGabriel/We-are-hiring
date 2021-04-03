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
            if(employee.getSal() > 5000.0)
                budget += employee.getSal() * 100 / 90;
            else if (employee.getSal() < 3000)
                budget += employee.getSal();
            else
                budget += employee.getSal() * 100 / 84;
        }
        return budget;
    }

}
