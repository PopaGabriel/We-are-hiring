package Tests;

import Apllication.*;
import Apllication.Departamente.Department;
import org.json.simple.parser.ParseException;
import java.io.IOException;

class SignUpTest {
    public static void main(String[] args) throws IOException, InvalidDatesException, ParseException {
        Application app = Application.getInstance();
        app.deSerialiseCompany("C:\\Users\\Catalin\\IdeaProjects\\TemaPOO\\src\\Apllication\\InputFiles\\CompanyInputFile.json");
        app.deSerialiseConsumers("C:\\Users\\Catalin\\IdeaProjects\\TemaPOO\\src\\Apllication\\InputFiles\\consumers.json");
        app.deSerialiseFriendLinks("C:\\Users\\Catalin\\IdeaProjects\\TemaPOO\\src\\Apllication\\InputFiles\\FriendshipGrapf.json");
        app.deSerialiseJobReq();

        for(Company company : app.companyList)
            for(Department department : company.departmenteArrayList)
                for(Job job : department.getJobs())
                    company.manager.process(job);

//        System.out.println(app.userList);

        System.out.println(app);
    }
}
