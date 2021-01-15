package Apllication;

import java.time.LocalDate;
import java.util.ArrayList;

public class User extends Consumer {
    ArrayList<String> listWantedCompany;

    public User(String name, String firstName, String email,
                String phoneNumber, String birthDate, String gender){
        super(name, firstName, email, phoneNumber, birthDate, gender);
        listWantedCompany = new ArrayList<>();
    }
    public User(){
        super();
        listWantedCompany = new ArrayList<>();
    }
    //we have to remove him from the list of his friends
    //and add him again after that as an employee object
    public void updateFriendList(Employee employee) {
        for (int i = 0; i < friendList.size(); i++) {
            employee.add(friendList.get(i));
            friendList.get(i).remove(this);
        }
    }
    public Employee convert()
    {

        Application app = Application.getInstance();
        app.userList.remove(this);

        Employee employee = new Employee();
        employee.listWantedCompany = null;
        employee.resume = resume;
        updateFriendList(employee);

        return employee;
    }
    //Here i calculate the experience of an user
    public int getExperienceTime() {
        int totalYears = 0, years, month;
        LocalDate now;
        for (Experience experience : resume.historyExperience) {
            if (experience.endDate == null) {
                now = LocalDate.now();
                years = now.getYear() - experience.startDate.getYear();
                month = now.getMonthValue() - experience.startDate.getMonthValue();
                //if the month is lower than 0 we will just sum the year that we got as
                //a result, because a minus month represents a plus month in the other year
                if(month > 3)
                    years++;
                else if(month == 3)
                    if((now.getDayOfMonth()-experience.startDate.getDayOfMonth()) > 0)
                        years++;

            } else {
                years = experience.endDate.getYear() - experience.startDate.getYear();
                month = experience.endDate.getMonthValue() - experience.startDate.getMonthValue();

                if (month > 3)
                    years++;
                else if (month == 3)
                    if ((experience.endDate.getDayOfMonth()
                            - experience.startDate.getDayOfMonth()) >= 0)
                        years++;
            }
            totalYears += years;
        }
        return totalYears;
    }
    public Double getTotalScore(){
        return getExperienceTime() * 1.5 + meanGPA();
    }
    public String toString() {
        return  resume  +" Friends: "+showFriendsList() +" Wanted Companies: "
                +listWantedCompany+" \n";
    }
}