package Apllication;

import java.util.ArrayList;
import java.util.TreeSet;

public class Manager extends Employee {
    TreeSet<Request<Job, User>> jobApplications;
    public Manager() {
        super();
        /*
        I use TreeSet because i can add elements
        without having to sort them every time
        by making a custom comparator
        */
        jobApplications = new TreeSet<>();
    }
    public void add(Request<Job, User> request) {
        jobApplications.add(request);
    }
    public String showJobAppl() {
        StringBuilder stringAux = new StringBuilder();
        for(Request<Job, User> request : jobApplications)
            stringAux.append("\nStart of requests:\n").append(request.getKey().nameOfCompany).
                    append(" ").
                    append(request.getKey().nameOfJob).
                    append(" ").
                    append(request.getValue1().resume.information.getName())
                    .append(" ").append(request.getScore()).
                    append(request.getValue2().resume.information.getName());
        return String.valueOf(stringAux);
    }
    public void process(Job job) {

        Application app = Application.getInstance();
        ArrayList<Request<Job, User>> lista_cereri = new ArrayList<>();
        Employee employee;
        Company company = app.getCompany(companyName);

        for(Request<Job, User> request : jobApplications)
            if(request.getKey().equals(job)) {
//                System.out.println(job.nameOfJob+"\n"+request.getValue1()+" "+companyName+" "+request.getValue1().getTotalScore());
                lista_cereri.add(request);
                if(request.getKey().noPositions > 0
                        && request.getKey().flag == 1) {
                    if(app.userList.contains(request.getValue1())) {

                        employee = request.getValue1().convert();
                        company.add(employee, company.getDepartment
                                ("Apllication.Departamente.IT"));
                        job.noPositions--;
                        job.userArrayList.remove(request.getValue1());
                    }
                }
            }
        job.flag = 0;
        for(Request<Job, User> request : lista_cereri)
            jobApplications.remove(request);
    }
    @Override
    //Trebuie refacut
    public String toString() {
        return super.toString()/* showJobAppl()*/;
    }
}
