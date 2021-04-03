package Tests;

import Apllication.*;
import Apllication.Departamente.Department;
import Apllication.Departamente.FactoryDepartment;
import Apllication.Exceptions.ResumeIncompleteException;
import Graphics.Pages.AdminPageGUui;
import Graphics.Pages.ManagerPageGui;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

public class Test {
    private String departmentName;
    private String companyName;

    //this method is used to create a job from a JSonObject
    public Job makeJob(JSONObject jsonObject) {

        if (jsonObject == null)
            return null;

        String[] stringAux;
        Double minEdu = null, maxEdu = null;
        Double minExp = null, maxExp = null;
        Double minGpa = null, maxGpa = null;

        stringAux = ((String) jsonObject.get("graduationYearConstraint")).split("-");
        if (stringAux[0].compareTo("null") != 0)
            minEdu = Double.parseDouble(stringAux[0]);
        if (stringAux[1].compareTo("null") != 0)
            maxEdu = Double.parseDouble(stringAux[1]);

        stringAux = ((String) jsonObject.get("experience")).split("-");
        if (stringAux[0].compareTo("null") != 0)
            minExp = Double.parseDouble(stringAux[0]);
        if (stringAux[1].compareTo("null") != 0)
            maxExp = Double.parseDouble(stringAux[1]);

        stringAux = ((String) jsonObject.get("average")).split("-");
        if (stringAux[0].compareTo("null") != 0)
            minGpa = Double.parseDouble(stringAux[0]);
        if (stringAux[1].compareTo("null") != 0)
            maxGpa = Double.parseDouble(stringAux[1]);

        return new Job.JobBuilder()
                .position((String) jsonObject.get("name"))
                .noPos((String) jsonObject.get("noPositions"))
                .salary((String) jsonObject.get("salary"))
                .flag((String)jsonObject.get("flag"))
                .makeConEdu(new Constraint(minEdu, maxEdu))
                .makeConExp(new Constraint(minExp, maxExp))
                .makeConGpa(new Constraint(minGpa, maxGpa))
                .build();
    }
    //Recruiters all are part of the IT department
    //The rest are added to their respective company and department
    public void addToCompany(Consumer consumer) {
        Application app = Application.getInstance();
        Company company = app.getCompany(consumer.getHisExp().first().getNameOfCompany());
        if (consumer.getHisExp().first().getPosition().compareTo("Recruiter") == 0) {
            company.add((Recruiter) consumer);
            company.add((Employee) consumer, company.getDepartment("Apllication.Departamente.IT"));
        }   else
            company.getDepartment(departmentName).add((Employee)consumer);
    }

    public void makeCompanies(String fileName) throws IOException, ParseException {
        Application app = Application.getInstance();
        Job job;
        FactoryDepartment factory = new FactoryDepartment();
        Company company;

        JSONParser parser = new JSONParser();
        Object object = parser.parse(new FileReader(fileName));
        JSONObject jsonObject = (JSONObject) object;
        JSONArray jsonArray = (JSONArray) jsonObject.get("Companies");
        Object jsonDepartment;
        JSONArray jobJson;
        /*
        we know we only have jobs in the It department
        if we knew we had other jobs than we would go through
        the same steps for creating the departments as
         we did for the It department
        */
        for(Object compObject : jsonArray) {
            company = new Company((String) ((JSONObject)compObject).get("name"));
            company.add(factory.factory("IT"));
            company.add(factory.factory("Marketing"));
            company.add(factory.factory("Management"));
            company.add(factory.factory("Finance"));

            jsonDepartment = ((JSONObject) compObject).get("IT");
            jobJson = (JSONArray) jsonDepartment;
            for (Object jobObject : jobJson) {
                job = makeJob((JSONObject) jobObject);
                if (job == null) {
                    continue;
                }
                job.setNameOfCompany((String) ((JSONObject)compObject).get("name"));
                company.getDepartment("Apllication.Departamente.IT").add(job);
            }
            app.getCompanies().add(company);
        }
    }

    public TreeSet<Education> makeEduHis(JSONArray jsonAEdu) {
        TreeSet<Education> eduHisTree = new TreeSet<>();
        for (Object object : jsonAEdu)
            eduHisTree.add(new Education.EduBuilder()
                    .name((String) ((JSONObject) object).get("name"))
                    .endDate((String) ((JSONObject) object).get("end_date"))
                    .startDate((String) ((JSONObject) object).get("start_date"))
                    .eduLevel((String) ((JSONObject) object).get("level"))
                    .finalGpa((Double) ((JSONObject) object).get("grade"))
                    .build());
        return eduHisTree;
    }

    public TreeSet<Experience> createExpHis(JSONArray jsonAExp) {
        TreeSet<Experience> expHisTree = new TreeSet<>();
        Experience exp;

        for (Object object : jsonAExp) {
            exp = new Experience.ExperienceBuilder()
                    .startDate((String) ((JSONObject)object).get("start_date"))
                    .endDate((String) ((JSONObject) object).get("end_date"))
                    .name((String) ((JSONObject) object).get("company"))
                    .position((String) ((JSONObject) object).get("position"))
                    .build();

            if (exp.getEndDate() == null) {
                departmentName += ((String) ((JSONObject) object).get("department"));
                companyName = ((String) ((JSONObject) object).get("company"));
            }
            expHisTree.add(exp);
        }
        return expHisTree;
    }

    public User makeUser(JSONObject objectAux) {
        String[] auxStringVector;
        ArrayList<Language> languages = new ArrayList<>();

        auxStringVector = ((String) objectAux.get("name")).split(" ");
        JSONArray jsonArrayLang = (JSONArray) objectAux.get("languages");
        JSONArray jsonArrayLev = (JSONArray) objectAux.get("languages_level");

        for (int i = 0; i < jsonArrayLang.size(); i++)
            languages.add(new Language((String) jsonArrayLang.
                    get(i), (String) jsonArrayLev.get(i)));
        User user = new User();

        user.setResume(new Resume.ResumeBuilder()
                .Education(makeEduHis((JSONArray) (objectAux).get("education")))
                .Experience(createExpHis((JSONArray) (objectAux).get("experience")))
                .name(auxStringVector[0])
                .firstName(auxStringVector[1])
                .birthDate((String) objectAux.get("date_of_birth"))
                .email((String) objectAux.get("email"))
                .gender((String) objectAux.get("genre"))
                .phoneNumber((String) objectAux.get("phone"))
                .language(languages)
                .password("Andrei")
                .build());
        try {
            if (user.getName() == null || user.getHisEdu().size() < 1)
                throw new ResumeIncompleteException("Get some education my guy!");
        } catch (ResumeIncompleteException e) {
            return null;
        }
        return user;
    }

    public void makeUsers(String fileName) throws IOException, ParseException {

        Application app = Application.getInstance();
        User user;
        Employee employee;
        Recruiter recruiter;
/*
        I instantiated a StringBuffer because i need to be able
        to change the string inside of the buffer inside a method
        while with String i cannot, because it is a immutable
*/
        departmentName = "Apllication.Departamente.";
        JSONParser parser = new JSONParser();
        Object object = parser.parse(new FileReader(fileName));

        //here i have everything from employees to recruiters and manager
        //do not change!!
        JSONObject jsonObject = (JSONObject) object;
        JSONArray jsonAux = (JSONArray) jsonObject.get("employees");
        JSONArray jSonInterestedC;

        //Here I add the employees
        for (Object objectAux : jsonAux) {
            user = makeUser((JSONObject) objectAux);
            if (user == null)
                continue;
            employee = user.convert();
            employee.setCompanyName(companyName);
            employee.setSalary(Double.parseDouble("" + (
                    ((JSONObject) objectAux).get("salary"))));
            addToCompany(employee);
            departmentName = "Apllication.Departamente.";
            companyName = "";
        }
        //Here I add recruiters
        jsonAux = (JSONArray) jsonObject.get("recruiters");
        for (Object objectAux : jsonAux) {
            user = makeUser((JSONObject) objectAux);
            if (user == null)
                continue;
            recruiter = user.convert().convert();
            recruiter.setCompanyName(companyName);
            recruiter.setSalary(Double.parseDouble(""+
                    ((JSONObject) objectAux).get("salary")));
            addToCompany(recruiter);
            companyName = "";
        }
        //Here I add the managers
        jsonAux = (JSONArray) jsonObject.get("managers");
        for (Object objectAux : jsonAux) {
            user = makeUser((JSONObject) objectAux);
            if (user == null)
                continue;
            employee = user.convert();
            employee.setCompanyName(companyName);
            employee.setSalary(Double.parseDouble("" +
                    ((JSONObject) objectAux).get("salary")));
            Company companyAux = app.getCompany(companyName);
            companyAux.setMan(employee.convertToM());
            companyName = "";
        }
        //Here i add the users
        jsonAux = (JSONArray) jsonObject.get("users");
        for (Object objectAux : jsonAux) {
            user = makeUser((JSONObject) objectAux);
            jSonInterestedC = (JSONArray) ((JSONObject) objectAux).get("interested_companies");
            if (user == null)
                continue;
            for (int i = 0; i < jSonInterestedC.size(); i++)
                user.add((String) jSonInterestedC.get(i));
            app.getUserList().add(user);
        }
        makeFriendsGraph("src\\InputFiles\\FriendshipGrapf.json");
        makeJobRequests();
    }

    public void makeFriendsGraph(String fileName)
            throws IOException, ParseException {
        Application app = Application.getInstance();
        JSONArray jsonArray, jSonFriends;
        String[] name;
        Consumer consumer;
        JSONParser parser = new JSONParser();
        Object object = parser.parse(new FileReader(fileName));
        jsonArray = (JSONArray) ((JSONObject) object).get("friend_connec");

        for (Object objectAux : jsonArray) {
            name = ((String) ((JSONObject) objectAux).get("name")).split("_");
            jSonFriends = (JSONArray) ((JSONObject) objectAux).get("friends");
            consumer = app.getCons(name[0], name[1]);
            if (consumer != null)
                for (Object objFriends : jSonFriends) {
                    name = ((String) objFriends).split("_");
                    consumer.add(app.getCons(name[0], name[1]));
                }
        }
    }

    public void makeJobRequests() {
        Application app = Application.getInstance();
        ArrayList<Job> jobs;
        for (User user : app.getUserList())
            for (Company company : app.getCompanies())
                if (user.contains(company.getName())) {
                    company.addObserver(user);
                    jobs = company.getJobs();
                    for (Job job : jobs)
                        job.apply(user);
                }
    }
    public static void main(String[] args) throws IOException, ParseException {
        Application app = Application.getInstance();
        PagesList pagesList = PagesList.getInstance() ;
        Test testClass = new Test();
        testClass.makeCompanies("src\\InputFiles\\CompanyInputFile.json");
        testClass.makeUsers("src\\InputFiles\\consumers.json");

        System.out.println(app);
//        for (Company company : app.getCompanies())
//            for(Department department : company.getDep())
//                for(int i = 0; i < department.getEmployees().size();)
//                    company.remove(department.getEmployees().get(i));
//        System.out.println(app.getUserList());

//        System.out.println(app.getUserList().get(0));
//        System.out.println(app.getUserList().get(0).convert());

        pagesList.add(new ManagerPageGui(app.getCompany("Google").getMan()));
        pagesList.getPagesArray().get(0).setVisible(false);
        pagesList.add(new AdminPageGUui());

    }
}
