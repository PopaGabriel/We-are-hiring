package Apllication;

import java.util.ArrayList;

public class Employee extends User {
    public String companyName;
    public Double salary;

    public Employee() {
        super();
    }
    public String getCompanyName() {
        for(Experience experience : resume.historyExperience)
            if(experience.endDate == null)
                return experience.nameOfCompany;
            return null;
    }
    public Recruiter convert() {
        Recruiter recruiter = new Recruiter();
        recruiter.resume = resume;
        for(Consumer consumer : friendList) {
            consumer.add(recruiter);
            consumer.remove(this);
            //System.out.println(recruiter.showFriendsList());
        }

        recruiter.salary = salary;
        recruiter.companyName = companyName;
        return recruiter;
    }
    public Manager convertToM() {
        Manager manager = new Manager();
        manager.resume = resume;
        manager.salary = salary;
        manager.companyName = companyName;

        for(Consumer consumer : friendList) {
            consumer.remove(this);
            consumer.add(manager);
        }
        return manager;
    }
    public User convertToU() {
        User user = new User();
        Consumer consumer;
        user.resume = resume;
        ArrayList<Consumer> listAux = friendList;
        for(int i = 0; i < listAux.size(); i++) {
            consumer = friendList.get(i);
            consumer.remove(this);
            consumer.add(user);
        }
        return user;
    }
    public String toString(){
        return  " CompanyName " + companyName +
                " Salary " + salary + " " + super.toString();
    }
}
