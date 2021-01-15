package Tests;

import Apllication.*;
import Apllication.Departamente.Department;
import Apllication.Exceptions.InvalidDatesException;
import org.json.simple.parser.ParseException;
import java.io.IOException;

class SignUpTest {
    public static void main(String[] args) throws IOException, InvalidDatesException, ParseException {
        Application app = Application.getInstance();
        app.deSerialiseCompany("C:\\Users\\Catalin\\IdeaProjects\\TemaPOO\\src\\Apllication\\InputFiles\\CompanyInputFile.json");
        app.deSerialiseConsumers("C:\\Users\\Catalin\\IdeaProjects\\TemaPOO\\src\\Apllication\\InputFiles\\consumers.json");
        app.deSerialiseFriendLinks("C:\\Users\\Catalin\\IdeaProjects\\TemaPOO\\src\\Apllication\\InputFiles\\FriendshipGrapf.json");
        app.deSerialiseJobReq();
//        for(User user : app.userList)
//            System.out.println(user.getTotalScore());

        for(Company company : app.companyList)
            for(Department department : company.departmenteArrayList)
                for(Job job : department.getJobs())
                    company.manager.process(job);

        for(Company company : app.companyList)
            System.out.println(company.observers);

//        System.out.println(app.userList);

//        System.out.println(app.companyList);
    }
}
