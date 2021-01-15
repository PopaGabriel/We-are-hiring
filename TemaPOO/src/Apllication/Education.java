package Apllication;

import java.time.LocalDate;
import java.util.Arrays;

public class Education implements Comparable {
    public LocalDate startDate;
    public LocalDate endDate;
    public String nameOfInstitution;
    public String educationLevel;
    public Double finalGPA;

    public Education(String startString, String endString, Double finalGPA,
                     String nameOfInstitution, String educationLevel) {
        try{
            //if he didn't even start than it is an error
            if(startString == null)
                throw new InvalidDatesException("startDate is null");

            String[] startDateInput = startString.split("\\.");
            startDate = LocalDate.of(AuxiliarMethods.changeSToI(startDateInput[2]),
                    AuxiliarMethods.changeSToI(startDateInput[1]),
                    AuxiliarMethods.changeSToI(startDateInput[0]));
            //if he is still studying
            if (endString == null) {
               endDate = null;
               this.finalGPA = finalGPA;
               this.nameOfInstitution = nameOfInstitution;
               this.educationLevel = educationLevel;
               return;
            }
            //if he finalised his studies we calculate his end date
            String[] endDateInput = endString.split("\\.");
            endDate = LocalDate.of(AuxiliarMethods.changeSToI(endDateInput[2]),
                    AuxiliarMethods.changeSToI(endDateInput[1]),
                    AuxiliarMethods.changeSToI(endDateInput[0]));
            //then we compare it to the start date
            if(startDate.compareTo(endDate) > 0)
                throw new InvalidDatesException("StartDate is higher than EndDate");

            this.nameOfInstitution = nameOfInstitution;
            this.educationLevel = educationLevel;
            this.finalGPA = finalGPA;

        } catch(InvalidDatesException e) {
            System.out.println("Bad input, try again!");
            startDate = null;
        }
    }
    public void setNameOfInstitution(String nameOfInstitutionAux){
        nameOfInstitution = nameOfInstitutionAux;
    }
    public String getNameOfInstitution(){
        return nameOfInstitution;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }
    public String getEducationLevel(){
        return educationLevel;
    }
    public void setFinalGPA(Double finalGPAAux){
        finalGPA = finalGPAAux;
    }

    public Double getFinalGPA() {
        return finalGPA;
    }
    public String toString(){
        return "Institution: " + nameOfInstitution + "Final Gpa: " + finalGPA+
                "Time passed: " + startDate+" "+ endDate + " Apllication.User.Apllication.Education level: "+
                educationLevel + "\n";
    }
    @Override
    public int compareTo(Object o) {
        //if they didn't end their studies than it should compare start dates
        if(endDate == null || ((Education)o).endDate == null)
            return startDate.compareTo(((Education)o).startDate);
        if(endDate.compareTo(((Education) o).endDate) == 0){
            return (int)(((Education) o).finalGPA - finalGPA);
        }
        return ((Education)o).endDate.compareTo(endDate);
    }

}
