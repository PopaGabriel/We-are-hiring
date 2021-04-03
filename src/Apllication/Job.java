package Apllication;

import java.util.ArrayList;

public class Job {
    private String position;
    private String nameOfCompany;
    private int flag;
    private int noPositions;
    private Double salary;
    Constraint constraintEducation;
    Constraint constraintExperience;
    Constraint constraintFinalGpa;
    //Users that applied for this job
    private ArrayList<User> userArrayList;

    public Job(JobBuilder jobBuilder) {
        position = jobBuilder.position;
        flag = jobBuilder.flag;
        noPositions = jobBuilder.noPositions;
        salary = jobBuilder.salary;
        constraintEducation = jobBuilder.constraintEducation;
        constraintExperience = jobBuilder.constraintExperience;
        constraintFinalGpa = jobBuilder.constraintFinalGpa;
        userArrayList = jobBuilder.userArrayList;
    }
    /*
    with this method a user can apply to a job
    it looks for a recruiter the is farthest away from
    social ring of the user and it sends to that recruiter
    the user and checks its requirements
    */
    public void apply(User user) {
        if (user == null)
            return;
        Application app = Application.getInstance();
        Company company = app.getCompany(getNameOfCompany());
        if (flag != 0) {
            getUsers().add(user);
            company.getRecruiter(user).evaluate(this, user);
        }

    }

    //this method checks all constraints for a user
    public Boolean meetsRequirments(User user) {
        if(!constraintExperience.meetsConstraint((double)
                user.getExperienceTime()))
            return false;
        if(!constraintFinalGpa.meetsConstraint(user.meanGPA()))
            return false;
        return constraintEducation.meetsConstraint((double)
                user.getGraduationYear());
    }

    //getter setter section
    public String getNameOfCompany() {
        return nameOfCompany;
    }
    public String getPos() {
        return  position;
    }
    public int getNoPositions() {return noPositions;}
    public int getFlag() {
        return flag;
    }
    public Double getSal() {
        return salary;
    }
    public ArrayList<User> getUsers() {
        return userArrayList;
    }
    public void setPosition(String pos) {
        position = pos;
    }
    public void setNameOfCompany(String nameOfComp) {
        nameOfCompany = nameOfComp;
    }
    public void setNoPositions(int noPositions) {
        this.noPositions = noPositions;
    }
    public void setFlag(int flag) {
        this.flag = flag;
    }
    public void setSalary(Double sal) {
        salary = sal;
    }
    public void setUserArrayList(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }
    public void remove(User user) {
        userArrayList.remove(user);
    }
    //this method decrements the value of the noPositions
    public void decNoPos() {
        noPositions--;
    }

    public static class JobBuilder {
        private String position;
        private String nameCompany;
        private int flag;
        private int noPositions;
        private Double salary;
        Constraint constraintEducation;
        Constraint constraintExperience;
        Constraint constraintFinalGpa;
        //Users that applied for this job
        private ArrayList<User> userArrayList;
        public JobBuilder () {
            userArrayList = new ArrayList<>();
        }
        public JobBuilder position(String pos) {
            position = pos;
            return this;
        }
        public JobBuilder name(String nameCompany) {
            this.nameCompany = nameCompany;
            return this;
        }
        public JobBuilder flag(String val) {
            flag = Integer.parseInt(val);
            return this;
        }
        public JobBuilder flag(int val) {
            flag = val;
            return this;
        }
        public JobBuilder noPos(int noPositions) {
            this.noPositions = noPositions;
            return this;
        }
        public JobBuilder noPos(String noPositions) {
            this.noPositions = Integer.parseInt(noPositions);
            return this;
        }
        public JobBuilder salary(Double salary) {
            this.salary = salary;
            return this;
        }
        public JobBuilder salary(String salary) {
            this.salary = Double.parseDouble(salary);
            return this;
        }
        public JobBuilder makeConEdu (Constraint constraint) {
            constraintEducation = constraint;
            return this;
        }
        public JobBuilder makeConExp (Constraint constraint) {
            constraintExperience = constraint;
            return this;
        }
        public JobBuilder makeConGpa(Constraint constraint) {
            constraintFinalGpa = constraint;
            return this;
        }
        public JobBuilder userList(ArrayList<User> userList) {
            userArrayList = userList;
            return this;
        }
        public Job build() {
            return new Job(this);
        }

    }
    @Override
    public String toString() {
        return "\nJob{" +
                "position='" + position + '\'' +
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
