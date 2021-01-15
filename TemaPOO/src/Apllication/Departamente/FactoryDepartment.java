package Apllication.Departamente;

public class FactoryDepartment {
    public Department factory (String type) {
        if(type.compareTo("IT") == 0)
            return new IT();
        if(type.compareTo("Finance") == 0)
            return new Finance();
        if(type.compareTo("Management") == 0)
            return new Management();
        if(type.compareTo("Marketing") == 0)
            return new Marketing();
        return null;
    }
}