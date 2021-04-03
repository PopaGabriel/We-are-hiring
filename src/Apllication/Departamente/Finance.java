package Apllication.Departamente;

import Apllication.Employee;

import java.time.LocalDate;
import java.time.Period;

public class Finance extends Department {
    public Integer taxe;
    // i verify if the employee has more or one year experience
    //i also use the same approximation used for the experience
    // time in general, if he has more or equal to three months
    // experience i approximate it as 1 year
    public Finance() {
        super();
    }
    @Override
    public Double getTotalSalaryBudget() {
        double budget = 0.0;
        for(Employee employee : getEmployees()) {
            LocalDate timeStart = employee.getHisExp().first().getStartDate();
            LocalDate timeEnd =  employee.getHisExp().first().getEndDate();
            if (timeEnd == null) {
                if (Period.between(timeStart,LocalDate.now()).getYears() >= 1
                        || Period.between(timeStart, LocalDate.now()).getMonths() >= 3)
                    budget+= employee.getSal() * 100 / 84;
                else
                    budget += employee.getSal() * 100 / 90;
            } else if (Period.between(timeStart,timeEnd).getYears() >= 1
                    || Period.between(timeStart, LocalDate.now()).getMonths() >= 3)
                budget += employee.getSal() * 100 / 84;
            else
                budget += employee.getSal() * 100 / 90;
        }
        return budget;
    }


}
