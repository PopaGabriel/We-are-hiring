package Apllication.Departamente;

import Apllication.Employee;

import java.time.LocalDate;
import java.time.Period;

public class Finance extends Department {
    public Integer taxe;

    public Finance() {
        super();
    }

    //because we don't know at which department
    //the worker actually worked at
    //we will only asses the current job experience
    @Override
    public Double getTotalSalaryBudget() {
        double budget = 0.0;
        for(Employee employee : getEmployees()) {
            LocalDate timeStart = employee.resume.historyExperience.first().startDate;
            LocalDate timeEnd =  employee.resume.historyExperience.first().endDate;
            if(timeEnd == null)
                timeEnd = LocalDate.now();
            if(Period.between(timeStart,timeEnd).getYears() >= 1)
                budget+= employee.salary * 100 / 84;
            else
                budget += employee.salary * 100 / 90;
        }
        return budget;
    }


}
