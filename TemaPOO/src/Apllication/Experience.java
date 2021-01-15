package Apllication;

import Apllication.Exceptions.InvalidDatesException;

import java.time.LocalDate;

public class Experience implements Comparable{
    public LocalDate startDate;
    public LocalDate endDate;
    public String position;
    public String nameOfCompany;
    public Experience(String startDateS, String endDateS, String position,
                      String nameOfCompany){
        this.position = position;
        this.nameOfCompany = nameOfCompany;
        try{
            //if the start date is somehow null
            if(startDateS == null)
                throw new InvalidDatesException("Start Date is null");
            //if it isn't null we allocate memory for it
            String[] startDateInput = startDateS.split("\\.");
            startDate = LocalDate.of(AuxiliarMethods.changeSToI(startDateInput[2]),
                    AuxiliarMethods.changeSToI(startDateInput[1]),
                    AuxiliarMethods.changeSToI(startDateInput[0]));
            //if he is still working at that company
            if(endDateS == null) {
                endDate = null;
                return;
            }
            //if he isn't
            String[] endDateInput = endDateS.split("\\.");
            endDate = LocalDate.of(AuxiliarMethods.changeSToI(endDateInput[2]),
                    AuxiliarMethods.changeSToI(endDateInput[1]),
                    AuxiliarMethods.changeSToI(endDateInput[0]));
            if(startDate.compareTo(endDate) > 0)
                throw new InvalidDatesException("Start date is higher than end date");
        }catch (InvalidDatesException e) {
            startDate = null;
            System.out.println("Apllication.User.Apllication.Experience Input Problems");
        }
    }
    public String toString(){
        return "Apllication.Company: " + nameOfCompany + " Work Period: "
                + startDate + " " + endDate + " Position: " + position + " \n";
    }
    @Override
    public int compareTo(Object o) {
        if(endDate == null)
            return -1;
        if(((Experience)o).endDate == null)
            return 1;
        if(((Experience)o).endDate.compareTo(endDate) == 0)
            return (nameOfCompany.compareTo(((Experience) o).nameOfCompany));
        return ((Experience)o).endDate.compareTo(endDate);
    }
}
