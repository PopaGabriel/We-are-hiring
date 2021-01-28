package Apllication;

import Apllication.Departamente.Department;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TreeSet;

public class Manager extends Employee {
    private TreeSet<Request<Job, User>> jobApplications;

    public Manager() {
        super();
        jobApplications = new TreeSet<>();
    }

        /*
       this method gets a request and hires the user from that request
       in the company, it decrements the number of posions remaining for that
       particular job and also adds the new employee to the required department
        */
    public void hire(Request<Job, User> request) {
        Application app = Application.getInstance();
        Company company = app.getCompany(getCompanyName());

        //i see if the user isn't employed yet
        if(app.contains(request.getValue1())) {
            //i verify if the job has positions open
            //or if it is still open
            if (request.getKey().getNoPositions() == 0) {
                request.getKey().setFlag(0);
                request.getKey().getUsers().clear();
                return;
            }
            //this method permits me to decrement the value
            // of noPositions
            request.getKey().decNoPos();
            //I remove the user from the jobs list
            request.getKey().remove(request.getValue1());
            // I convert the user to an employee
            Employee employee = request.getValue1().convert();
            employee.add(new Experience.ExperienceBuilder()
                    .position(request.getKey().getPos())
                    .name(request.getKey().getNameOfCompany())
                    .endDate("null")
                    .startDate(LocalDate.now())
                    .build());
            employee.setSalary(request.getSal());
            employee.setCompanyName(getCompanyName());
            //I add him to the department
            company.getDepartment(request.getKey())
                    .add(employee);
            //I remove him from the user list
            app.remove(request.getValue1());
            //I remove the request from the request list
            jobApplications.remove(request);
            //I remove the user from all the companies he observs
            for (Company company1 : app.getCompanies())
                company1.removeObserver(request.getValue1());
            //I send him a happy notification
            request.getValue1().update(new Notifi(request.getKey(), 1));
        }
    }

    /*
    the process method parses through all the request and chooses the ones that
    are the same as the job that it gets. I build an array of requests that
    meet that requirement, because of the way i built the Request tree set
    (with the highest scores at the top) as i go through the request list i
     can send them to be hired if the job is still open and it still has
     open positions. At the end i make a final verification if the job has been
     closed because it had already filled all its request i will remove
     all the requests for that job that are still not accepted.
     */
    public void process(Job job) {
        Application app = Application.getInstance();
        ArrayList<Request<Job, User>> requestsList = new ArrayList<>();
        //here i search through all the requests
        for (Request<Job, User> request : jobApplications)
            if (request.getKey().equals(job)) {
                requestsList.add(request);
                if (request.getNoPos() > 0 && request.getFlag() == 1)
                    if (app.contains(request.getValue1()))
                        hire(request);
            }
        //here is where i delete the job requests if the job has been filled
        if(job.getNoPositions() <= 0) {
            job.setFlag(0);
            for (Request<Job, User> request : requestsList) {
                jobApplications.remove(request);
                request.getValue1().update(new Notifi(job, -1));
            }
        }
    }

    //getter setter section
    public void add(Request<Job, User> request) {
        jobApplications.add(request);
    }
    public void remove(Request<Job, User> request){jobApplications.remove(request);}
    public TreeSet<Request<Job, User>> getRequests() {
        return jobApplications;
    }
    @Override
    //Trebuie refacut
    public String toString() {
        return super.toString();
    }
}
