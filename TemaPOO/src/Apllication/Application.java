package Apllication;

import Apllication.Departamente.*;
import Apllication.Exceptions.ResumeIncompleteException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

public class Application {
    public static Application instance = null;
    public ArrayList<User> userList;
    public ArrayList<Company> companyList;

    private Application(){
        userList = new ArrayList<>();
        companyList = new ArrayList<>();
    }

    public static Application getInstance(){
        if(instance == null)
            instance = new Application();

        return instance;
    }

    public ArrayList<Company> getCompanies(){
        return companyList;
    }
    public Company getCompany(String name){
        for(Company company : companyList){
            if (company.nameOfCompany.compareTo(name) == 0)
                return company;
        }
        return null;
    }
    public void add(Company company){
        companyList.add(company);
    }
    public void add(User user){
        userList.add(user);
    }
    public boolean remove(Company company) {
        if(companyList.contains(company)) {
            companyList.remove(company);
            return true;
        }
        return false;
    }
    public boolean remove(User user){
        if(userList.contains(user)){
            userList.remove(user);
            return true;
        }
        return false;
    }
    public User getUser(String name, String firstName){
        for(User user : userList){
            if(user.resume.information.
                    getName().compareTo(name) == 0)
                if(user.resume.information.getFirstName().
                        compareTo(firstName) == 0)
                    return user;
        }
        return null;
    }
    public Employee getEmpl(String name, String firstName) {
        for(Company company : companyList)
            for(Department department : company.departmenteArrayList)
                for(Employee employee : department.employeeArrayList)
                    if(employee.resume.information.
                            getName().compareTo(name) == 0)
                        if(employee.resume.information.getFirstName().
                                compareTo(firstName) == 0)
                            return employee;

        return null;
    }
    public Recruiter getRecr(String name, String firsName) {
        for (Company company : companyList)
            for (Recruiter recruiter :company.recruiterArrayList)
                if (recruiter.resume.information.getName()
                        .compareTo(name) == 0)
                    if(recruiter.resume.information.getFirstName()
                            .compareTo(firsName) == 0)
                        return recruiter;
        return null;
    }
    public Manager getMan(String name, String firstName) {
        for (Company company : companyList)
            if(company.manager.resume.information.
                    getName().compareTo(name) == 0)
                if(company.manager.resume.information.
                        getFirstName().compareTo(firstName) == 0)
                    return company.manager;
                return null;
    }

    //this method is used to create a job from a JSonObject
    public Job createJob(JSONObject jsonObject) {

        Job job = new Job();
        String[] arrayStrings;
        Double min = null, max = null;
        job.nameOfJob = (String)jsonObject.get("name");
        job.noPositions = Integer.parseInt((String)jsonObject.get("noPositions"));
        job.salary = Double.parseDouble((String)jsonObject.get("salary"));

        arrayStrings = ((String)jsonObject.get("graduationYearConstraint")).split("-");
        if(arrayStrings[0].compareTo("null") != 0)
            min = Double.parseDouble(arrayStrings[0]);
        if(arrayStrings[1].compareTo("null") != 0)
            max = Double.parseDouble(arrayStrings[1]);
        job.constraintEducation = new Constraint(min, max);

        min = null;
        max = null;
        arrayStrings = ((String)jsonObject.get("experience")).split("-");
        if(arrayStrings[0].compareTo("null") != 0)
            min = Double.parseDouble(arrayStrings[0]);
        if(arrayStrings[1].compareTo("null") != 0)
            max = Double.parseDouble(arrayStrings[1]);
        job.constraintExperience = new Constraint(min, max);

        min = null;
        max = null;
        arrayStrings = ((String)jsonObject.get("average")).split("-");
        if(arrayStrings[0].compareTo("null") != 0)
            min = Double.parseDouble(arrayStrings[0]);
        if(arrayStrings[1].compareTo("null") != 0)
            max = Double.parseDouble(arrayStrings[1]);
        job.constraintFinalGpa = new Constraint(min, max);

        return job;
    }
    public void deSerialiseCompany(String fileName) throws IOException, ParseException {

        Job job;
        FactoryDepartment factory = new FactoryDepartment();
        Company company;
        int i = 0;

        JSONParser parser = new JSONParser();
        Object object = parser.parse(new FileReader(fileName));

        JSONObject jsonAux;
        JSONObject jsonObject = (JSONObject) object;
        JSONArray jsonArray = (JSONArray) jsonObject.get("Companies");
        Iterator<JSONObject> iteratorCompanies = jsonArray.iterator();
        JSONArray iteratorDepartment;


        while (iteratorCompanies.hasNext()) {
            jsonObject = iteratorCompanies.next();
            company = new Company((String)jsonObject.get("name"));

            iteratorDepartment =  (JSONArray)jsonObject.get("IT");
            Department department = factory.factory("IT");
            if(iteratorDepartment.size() != 0)
                for(Object objectAux : iteratorDepartment) {
                    jsonAux = (JSONObject) objectAux;
                    job = createJob(jsonAux);
                    job.nameOfCompany = company.nameOfCompany;
                    department.add(job);
                }
            company.add(department);

            iteratorDepartment =  (JSONArray)jsonObject.get("Finance");
            department = factory.factory("Finance");
            if(iteratorDepartment.size() != 0) {
                for(Object objectAux : iteratorDepartment) {
                    jsonAux = (JSONObject) objectAux;
                    job = createJob(jsonAux);
                    job.nameOfCompany = company.nameOfCompany;
                    department.add(job);
                }
            }
            company.add(department);

            iteratorDepartment =  (JSONArray)jsonObject.get("Marketing");
            department = factory.factory("Marketing");
            if(iteratorDepartment.size() != 0) {
                for(Object objectAux : iteratorDepartment) {
                    jsonAux = (JSONObject) objectAux;
                    job = createJob(jsonAux);
                    job.nameOfCompany = company.nameOfCompany;
                    department.add(job);
                }
            }
            company.add(department);

            iteratorDepartment =  (JSONArray)jsonObject.get("Management");
            department = factory.factory("Management");
            if(iteratorDepartment.size() != 0) {
                for(Object objectAux : iteratorDepartment) {
                    jsonAux = (JSONObject) objectAux;
                    job = createJob(jsonAux);
                    job.nameOfCompany = company.nameOfCompany;
                    department.add(job);
                }
            }
            company.add(department);

            companyList.add(company);
        }
    }
    public User createConsumerfromJson(JSONObject objectAux,
                                       StringBuffer dep, StringBuffer comp) {

        String auxString;
        String[] auxStringVector;
        ArrayList<Language> languages = new ArrayList<>();

        JSONArray jsonArrayLang = (JSONArray) objectAux.get("languages");
        JSONArray jsonArrayLev = (JSONArray) objectAux.get("languages_level");
        JSONArray jsonArrayEdu = (JSONArray)(objectAux).get("education");
        JSONArray jsonAExp = (JSONArray)(objectAux).get("experience");

        for (int i = 0; i < jsonArrayLang.size(); i++)
            languages.add(new Language((String)jsonArrayLang.
                    get(i), (String)jsonArrayLev.get(i)));

        auxString = (String) objectAux.get("name");
        auxStringVector = auxString.split(" ");

        User user = new User();

        user.resume = new Resume.ResumeBuilder()
                .Education(createEduHis(jsonArrayEdu))
                .Experience(createExpHis(jsonAExp, dep, comp))
                .name(auxStringVector[0])
                .firstName(auxStringVector[1])
                .birthDate((String)objectAux.get("date_of_birth"))
                .email((String)objectAux.get("email"))
                .gender((String)objectAux.get("genre"))
                .phoneNumber((String)objectAux.get("phone"))
                .language(languages)
                .build();
        try {

            if(user.resume.information.getName() == null)
                throw new ResumeIncompleteException("System.out.printlnIncomplete Information");
            if(user.resume.historyEducation.size() < 1)
                throw  new ResumeIncompleteException("Get some education my guy!");

        } catch (ResumeIncompleteException e) {
            return null;
        }
        return user;
    }

    public TreeSet<Education> createEduHis(JSONArray jsonAEdu) {
        String startDate, endDate, nameOfInst, eduLev;
        Double finalGpa;
        TreeSet<Education> eduHisTree = new TreeSet<>();

        for(Object object : jsonAEdu) {
                startDate = (String)((JSONObject)object).get("start_date");
                endDate = (String)((JSONObject)object).get("end_date");
                nameOfInst =(String)((JSONObject)object).get("name");
                eduLev = (String)((JSONObject)object).get("level");
                finalGpa = (Double) ((JSONObject)object).get("grade");
                eduHisTree.add(new Education(startDate, endDate, finalGpa,
                        nameOfInst, eduLev));
        }
        return eduHisTree;
    }
    public TreeSet<Experience> createExpHis(JSONArray jsonAExp,
                                            StringBuffer dep,
                                            StringBuffer comp){
        String startDate, endDate, nameOfComp, position;
        TreeSet<Experience> expHisTree = new TreeSet<>();
        Experience exp;

        for (Object object : jsonAExp) {
            startDate = (String)((JSONObject)object).get("start_date");
            endDate = (String)((JSONObject)object).get("end_date");
            nameOfComp = (String)((JSONObject)object).get("company");
            position = (String)((JSONObject)object).get("position");
            exp = new Experience(startDate, endDate, position, nameOfComp);

            if (exp.endDate == null){
                dep.append((String) ((JSONObject)object).get("department"));
                comp.append((String) ((JSONObject)object).get("company"));
            }
            expHisTree.add(exp);
        }
        return expHisTree;
    }
    public void deSerialiseConsumers(String fileName) throws IOException, ParseException {

        Department department;
        Company company;
        Employee employee;
        User user;
        //I instantiated a StringBuffer because i need to be able
        //to change the string inside of the buffer inside a method
        //while with String i cannot, because it is a immutable
        StringBuffer dep = new StringBuffer("Apllication.Departamente.");
        StringBuffer comp = new StringBuffer();
        JSONParser parser = new JSONParser();
        Object object = parser.parse(new FileReader(fileName));

        //here i have everything from employees to recruiters and manager
        //do not change!!
        JSONObject jsonObject = (JSONObject) object;

        JSONArray jsonAEmp = (JSONArray) jsonObject.get("employees");
        JSONArray jsonARec = (JSONArray) jsonObject.get("recruiters");
        JSONArray jsonAMan = (JSONArray) jsonObject.get("managers");
        JSONArray jsonAUser = (JSONArray) jsonObject.get("users");
        JSONArray jSonInterestedC;

        //Here I add the employees
        for (Object objectAux : jsonAEmp) {
            user = createConsumerfromJson((JSONObject) objectAux, dep, comp);
            if(user == null)
                continue;

            employee = user.convert();
            employee.companyName = comp.toString();
            employee.salary = Integer.parseInt(String.valueOf(
                    ((JSONObject) objectAux).get("salary")));

//            System.out.println(employee);
            company = getCompany(employee.companyName);
            department = company.getDepartment(dep.toString());
            if(department != null)
                department.add(employee);
            dep.delete(0, dep.length());
            dep.append("Apllication.Departamente.");
            comp.delete(0, comp.length());
        }
        //Here I add recruiters
        for(Object objectAux : jsonARec) {
            user = createConsumerfromJson((JSONObject) objectAux, dep, comp);
            if(user == null)
                continue;

            employee = user.convert();
            employee.companyName = comp.toString();
            employee.salary = Integer.parseInt(String.valueOf(
                    ((JSONObject) objectAux).get("salary")));

            //Recruiters all are part of the IT department
            company = getCompany(comp.toString());
            department = company.getDepartment("Apllication.Departamente.IT");
            department.add(employee);
            company.add(employee.convert());

            comp.delete(0, comp.length());
        }
//        //Here I add the managers
        for(Object objectAux : jsonAMan) {
            user = createConsumerfromJson((JSONObject) objectAux, dep, comp);

            if (user == null)
                continue;

            employee = user.convert();
            employee.companyName = comp.toString();
            employee.salary = Integer.parseInt(String.valueOf(
                    ((JSONObject) objectAux).get("salary")));

            getCompany(comp.toString()).manager  = employee.convertToM();

            comp.delete(0, comp.length());
        }
        //Here i add the users
        for (Object objectAux : jsonAUser) {
            user = createConsumerfromJson((JSONObject) objectAux, dep, comp);
            jSonInterestedC = (JSONArray)((JSONObject) objectAux).get("interested_companies");
            if (user == null)
                continue;

            for (int i = 0; i < jSonInterestedC.size(); i++)
                user.listWantedCompany.add((String)jSonInterestedC.get(i));

            userList.add(user);
        }

    }

    public void deSerialiseFriendLinks(String fileName)
            throws IOException, ParseException {

        JSONArray jsonArray, jSonFriends;
        String[] name, nameOfFriend, friendArray;

        Employee empl, emplF;
        User user, userF;
        Recruiter recr, recrF;
        Manager man, manF;

        JSONParser parser = new JSONParser();
        Object object = parser.parse(new FileReader(fileName));
        jsonArray = (JSONArray) ((JSONObject) object).get("friend_connec");

        //I get their first and last name
        //then I get their friends list
        //and start linking them while always making sure
        //the objects exist and are not null or have been already added
        //in a previous step
        for (Object objectAux : jsonArray) {
            name = ((String) ((JSONObject) objectAux).get("name")).split("_");
            jSonFriends = (JSONArray) ((JSONObject) objectAux).get("friends");

            if (name[0].compareTo("U") == 0) {
                user = getUser(name[1], name[2]);
                if (user != null)
                    for (Object objFriends : jSonFriends) {
                        name = ((String) objFriends).split("_");
                        if (name[0].compareTo("U") == 0) {
                            userF = getUser(name[1], name[2]);
                            if (userF != null && !user.friendList.contains(userF))
                                user.add(userF);
                        } else if (name[0].compareTo("R") == 0) {
                            recrF = getRecr(name[1], name[2]);
                            if (recrF != null && !user.friendList.contains(recrF))
                                user.add(recrF);
                        } else if (name[0].compareTo("E") == 0) {
                            emplF = getEmpl(name[1], name[2]);
                            if (emplF != null && !user.friendList.contains(emplF))
                            user.add(getEmpl(name[1], name[2]));
                        } else {
                            manF = getMan(name[1], name[2]);
                            if (manF != null && !user.friendList.contains(manF))
                                user.add(manF);
                        }
                    }
            } else if (name[0].compareTo("E") == 0) {
                empl = getEmpl(name[1], name[2]);
                if (empl != null)
                    for (Object objFriends : jSonFriends) {
                        name = ((String) objFriends).split("_");
                        if (name[0].compareTo("U") == 0) {
                            userF = getUser(name[1], name[2]);
                            if (userF != null && !empl.friendList.contains(userF))
                                empl.add(userF);
                        } else if (name[0].compareTo("R") == 0) {
                            recrF = getRecr(name[1], name[2]);
                            if (recrF != null && !empl.friendList.contains(recrF))
                                empl.add(recrF);
                        } else if (name[0].compareTo("E") == 0) {
                            emplF = getEmpl(name[1], name[2]);
                            if (emplF != null && !empl.friendList.contains(emplF))
                            empl.add(getEmpl(name[1], name[2]));
                        } else {
                            manF = getMan(name[1], name[2]);
                            if (manF != null && !empl.friendList.contains(manF))
                                empl.add(manF);
                        }
                    }
            } else if (name[0].compareTo("R") == 0) {
                recr = getRecr(name[1], name[2]);
                if (recr != null)
                    for (Object objFriends : jSonFriends) {
                        name = ((String) objFriends).split("_");
                        if (name[0].compareTo("U") == 0) {
                            userF = getUser(name[1], name[2]);
                            if (userF != null && !recr.friendList.contains(userF))
                                recr.add(userF);
                        } else if (name[0].compareTo("R") == 0) {
                            recrF = getRecr(name[1], name[2]);
                            if (recrF != null && !recr.friendList.contains(recrF))
                                recr.add(recrF);
                        } else if (name[0].compareTo("E") == 0) {
                            emplF = getEmpl(name[1], name[2]);
                            if (emplF != null && !recr.friendList.contains(emplF))
                            recr.add(getEmpl(name[1], name[2]));
                        } else {
                            manF = getMan(name[1], name[2]);
                            if (manF != null && !recr.friendList.contains(manF))
                                recr.add(manF);
                        }
                    }
            } else {
                man = getMan(name[1], name[2]);
                if (man != null)
                    for (Object objFriends : jSonFriends) {
                        name = ((String) objFriends).split("_");
                        if (name[0].compareTo("U") == 0) {
                            userF = getUser(name[1], name[2]);
                            if (userF != null && !man.friendList.contains(userF))
                                man.add(userF);
                        } else if (name[0].compareTo("R") == 0) {
                            recrF = getRecr(name[1], name[2]);
                            if (recrF != null && !man.friendList.contains(recrF))
                                man.add(recrF);
                        } else if (name[0].compareTo("E") == 0) {
                            emplF = getEmpl(name[1], name[2]);
                            if (emplF != null && !man.friendList.contains(emplF))
                            man.add(getEmpl(name[1], name[2]));
                        } else {
                            manF = getMan(name[1], name[2]);
                            if (manF != null && !man.friendList.contains(manF))
                                man.add(manF);
                        }
                    }
            }
        }
    }
    public void deSerialiseJobReq() {
        ArrayList<Job> jobs;
        for (User user : userList)
            for(Company company : companyList)
                if(user.listWantedCompany.contains(company.nameOfCompany)) {
                    company.addObserver(user);
                    jobs = company.getJobs();
                    for (Job job : jobs)
                        job.apply(user);
                }
    }
    public String toString(){
        return userList +" "+ companyList;
    }
}
