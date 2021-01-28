package Apllication;

import Apllication.Exceptions.InvalidDatesException;

import java.time.LocalDate;

public class Experience implements Comparable<Experience> {
    private LocalDate startDate;
    private LocalDate endDate;
    private String position;
    private String nameOfCompany;
    private Experience(ExperienceBuilder experienceBuilder) {
        try{
            startDate = experienceBuilder.startDate;
            endDate = experienceBuilder.endDate;
            position = experienceBuilder.position;
            nameOfCompany = experienceBuilder.nameOfCompany;
            if (startDate == null)
                throw new InvalidDatesException("Start Date is null");
            if (endDate != null )
                if(startDate.isAfter(endDate))
                    throw new InvalidDatesException("Bad Dates My Guy");


        } catch (InvalidDatesException e) {
            startDate = null;
        }


    }

    //setter and getter section
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setNameOfCompany(String nameOfCompany) {
        this.nameOfCompany = nameOfCompany;
    }
    public String getNameOfCompany() {
        return nameOfCompany;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public String getPosition() {
        return position;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public static class ExperienceBuilder {

        private LocalDate startDate;

        private LocalDate endDate;
        private String position;
        private String nameOfCompany;
        public ExperienceBuilder startDate(LocalDate value) {
            startDate = value;
            return this;
        }
        public ExperienceBuilder startDate(String value) {
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
        public ExperienceBuilder endDate(String value) {
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

        public ExperienceBuilder endDate(LocalDate value) {
            endDate = value;
            return this;
        }

        public ExperienceBuilder position(String value) {
            position = value;
            return this;
        }
        public ExperienceBuilder name(String value) {
            nameOfCompany = value;
            return this;
        }
        public Experience build() {
            return new Experience(this);
        }
    }
    public String toString() {
        return "Company Name: " + nameOfCompany + "\n Work Period: "
                + startDate + " - " + endDate + "\n Position: " + position + " \n";
    }
    //i want to have the most recent one always at the top of the set
    @Override
    public int compareTo(Experience o) {
        if (endDate == null)
            return -1;
        if (o.endDate == null)
            return 1;
        if (o.endDate.compareTo(endDate) == 0)
            return (nameOfCompany.compareTo(o.nameOfCompany));
        return o.endDate.compareTo(endDate);
    }
}
