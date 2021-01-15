package Apllication;

import Apllication.Employee;
import Apllication.Job;

public class Recruiter extends Employee {
    public Double rating;
    //this is exclusevely for testing purposes
    public Recruiter(String s){
        super();
        this.resume.information.setName(s);
        rating = 5.0;
    }
    public Recruiter(){
        super();
        rating = 5.0;
    }
    public int evaluate(Job job, User user){

        Application app = Application.getInstance();
        Company company = app.getCompany(companyName);
        Double score = rating * user.getTotalScore();
        rating += 0.1;
        company.manager.add(new Request(job, user, this, score));
        return (int) (rating *user.getTotalScore());
    }
}
