package Apllication;

import java.util.ArrayList;

public class Job {
    String nameOfJob;
    String nameOfCompany;
    int flag;
    int noPositions;
    Double salary;
    Constraint constraintEducation;
    Constraint constraintExperience;
    Constraint constraintFinalGpa;
    //Users that applied for this job
    ArrayList<User> userArrayList;

    //Lipseste numele companiei si numele jobului pentru ca nu sunt bagate inca
    public Job() {
        flag = 1;
        constraintEducation = null;
        constraintExperience = null;
        constraintFinalGpa = null;
        userArrayList = new ArrayList<>();
    }

    public void apply(User user) {

        Application app = Application.getInstance();
        Company company;
        Recruiter recruiter;

        if (user == null)
            return;
        //System.out.println(meetsRequirments(user)+" "+user.resume.information.getName() + " " + nameOfJob);
        if (meetsRequirments(user) && flag == 1) {
            company = app.getCompany(nameOfCompany);
            userArrayList.add(user);
            recruiter = company.getRecruiter(user);
            recruiter.evaluate(this, user);
        }
    }

    //Lipseste conditie pentru educatie
    public Boolean meetsRequirments(User user) {
        int timeExperience = 0, timeEdu = 0;
        double finalGpa;

        if (constraintExperience != null) {
            timeExperience = user.getExperienceTime();
            if (constraintExperience.inferiorLimit != null)
                if (constraintExperience.inferiorLimit > timeExperience)
                    return false;
            if (constraintExperience.superiorLimit != null)
                if (constraintExperience.superiorLimit < timeExperience)
                    return false;
        }
        if (constraintEducation != null) {
            timeEdu = user.getGraduationYear();
            if (constraintEducation.inferiorLimit != null)
                if (constraintEducation.inferiorLimit > timeEdu)
                    return false;
            if (constraintEducation.superiorLimit != null)
                if (constraintEducation.superiorLimit < timeEdu)
                    return false;
        }

        if (constraintFinalGpa != null) {
            finalGpa = user.meanGPA();
            if (constraintFinalGpa.inferiorLimit != null)
                if (constraintFinalGpa.inferiorLimit > finalGpa)
                    return false;
            if (constraintFinalGpa.superiorLimit != null)
                if (constraintFinalGpa.superiorLimit < finalGpa)
                    return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "\nJob{" +
                "nameOfJob='" + nameOfJob + '\'' +
                ", nameOfCompany='" + nameOfCompany + '\'' +
                ", flag=" + flag +
                ", noPositions=" + noPositions +
                ", salary=" + salary +
                ", constraintEducation=" + constraintEducation +
                ", constraintExperience=" + constraintExperience +
                ", constraintFinalGpa=" + constraintFinalGpa +
                ", userArrayList=" + userArrayList +
                "}\n";
    }
}
