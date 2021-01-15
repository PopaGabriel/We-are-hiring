package Tests;

import Apllication.*;
import Apllication.Departamente.Department;
import org.json.simple.parser.ParseException;
import java.io.IOException;

class SignUpTest {
    public static void main(String[] args) throws IOException, ParseException {
        Application app = Application.getInstance();
        app.deSerialiseCompany("C:\\Users\\Catalin\\IdeaProjects\\TemaPOO\\src\\Apllication\\InputFiles\\CompanyInputFile.json");
        app.deSerialiseConsumers("C:\\Users\\Catalin\\IdeaProjects\\TemaPOO\\src\\Apllication\\InputFiles\\consumers.json");
        app.deSerialiseFriendLinks("C:\\Users\\Catalin\\IdeaProjects\\TemaPOO\\src\\Apllication\\InputFiles\\FriendshipGrapf.json");
        app.deSerialiseJobReq();

        for(Company company : app.companyList)
            for(Department department : company.departmenteArrayList)
                for(Job job : department.getJobs())
                    company.manager.process(job);
//        app.companyList.get(0).remove(app.companyList.get(0).getDepartment("Apllication.Departamente.IT"));
//        System.out.println(app.companyList.get(0));
//        System.out.println(app.companyList.companyLisget(0));
//                Company company = app.getCompany("Google");

//        System.out.println(app.companyList.get(0) + "Andrei de ce?\n");
//        System.out.println(app.userList);
//        System.out.println(app.companyList.get(0));
//        app.getCompany("Google").remove(app.getRecr("Jonie", "Phillip"));
//        System.out.println(app.userList);
//        for(Company company : app.companyList)
//            System.out.println(company.observers);

//        System.out.println(app.userList);

//        System.out.println(app.companyList);
    }
}
