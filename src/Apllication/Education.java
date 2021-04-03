package Apllication;

import Apllication.Exceptions.InvalidDatesException;

import java.time.LocalDate;

public class Education implements Comparable<Education> {
    private LocalDate startDate;
    private LocalDate endDate;
    private String name;
    private String eduLevel;
    private Double finalGPA;
    private Education(EduBuilder eduBuilder) {
        try {
            startDate = eduBuilder.startDate;
            endDate = eduBuilder.endDate;
            name = eduBuilder.name;
            eduLevel = eduBuilder.eduLevel;
            finalGPA = eduBuilder.finalGPA;
            if (startDate == null)
                throw  new InvalidDatesException("startDate is null");
            if (endDate != null)
                if(startDate.isAfter(endDate))
                    throw new InvalidDatesException("StartDate is higher than EndDate");
        } catch (InvalidDatesException e) {
            startDate = null;
            System.out.println("InvalidDate my guy");
        }

    }
    //getter and setter section
    public void setNameOfInstitution(String nameOfInstitutionAux) {
        name = nameOfInstitutionAux;
    }
    public String getNameOfInstitution() {
        return name;
    }
    public void setEducationLevel(String educationLevel) {
        this.eduLevel = educationLevel;
    }
    public String getEducationLevel() {
        return eduLevel;
    }
    public void setFinalGPA(Double finalGPAAux) {
        finalGPA = finalGPAAux;
    }
    public Double getFinalGPA() {
        return finalGPA;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public String getName() {
        return name;
    }

    public static class EduBuilder {
        private LocalDate startDate;
        private LocalDate endDate;
        private String name;
        private String eduLevel;
        private Double finalGPA;

        public EduBuilder() {
            startDate = null;
            endDate = null;
        }
        public EduBuilder name(String name) {
         this.name = name;
         return this;
        }
        public EduBuilder finalGpa(Double val) {
            finalGPA = val;
            return this;
        }
        public EduBuilder finalGpa(String val) {
            finalGPA = Double.parseDouble(val);
            return this;
        }
        public EduBuilder eduLevel(String val) {
            eduLevel = val;
            return this;
        }
        public EduBuilder startDate(LocalDate value) {
            startDate = value;
            return this;
        }
        public EduBuilder startDate(String value) {
            if (value == null || value.compareTo("null") == 0) {
                startDate = null;
                return this;
            }
            String[] startDateInput = value.split("\\.");
            startDate = LocalDate.of(Integer.parseInt(startDateInput[2]),
                    Integer.parseInt(startDateInput[1]),
                    Integer.parseInt(startDateInput[0]));
            return this;
        }
        public EduBuilder endDate(LocalDate value) {
            endDate = value;
            return this;
        }
        public EduBuilder endDate(String value) {
            if (value == null || value.compareTo("null") == 0) {
                endDate = null;
                return this;
            }
            String[] startDateInput = value.split("\\.");
            endDate = LocalDate.of(Integer.parseInt(startDateInput[2]),
                    Integer.parseInt(startDateInput[1]),
                    Integer.parseInt(startDateInput[0]));
            return this;
        }
        public Education build() {
            return new Education(this);
        }
    }

    public String toString() {
        return "Institution: " + name + " Final Gpa: " + finalGPA +
                " Time passed: " + startDate + " " + endDate + "\n";
    }

    //i always want to have the unfinished studies at the top
    @Override
    public int compareTo(Education o) {
        if (o.endDate == null)
            return 1;
        if (endDate == null)
            return -1;
        if (endDate.compareTo(o.endDate) == 0) {
            return (int) (o.finalGPA - finalGPA);
        }
        return o.endDate.compareTo(endDate);
    }
}
