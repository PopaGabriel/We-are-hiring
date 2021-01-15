package Apllication;

public class Employee extends User {
    public String companyName;
    public Integer salary;

    public Employee (String companyName, Integer salary) {
        super();
        this.companyName = companyName;
        this.salary = salary;
    }
    public Employee() {
        super();
        companyName = null;
        salary = null;
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
        }
        recruiter.salary = salary;
        recruiter.companyName = companyName;
        return recruiter;
    }
    public Manager convertToM() {
        Manager manager = new Manager();
        manager.resume = resume;
        manager.friendList = friendList;
        manager.salary = salary;
        manager.companyName = companyName;
        for(Consumer consumer : friendList) {
            consumer.remove(this);
            consumer.add(manager);
        }
        return manager;
    }
    public String toString(){
        return  " CompanyName " + companyName +
                " Salary " + salary + " " + super.toString();
    }
}
