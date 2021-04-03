package Apllication;

import java.time.LocalDate;
import java.util.ArrayList;

public class Information {
    private String name;
    private String firstName;
    private String email;
    private String phoneNumber;
    private LocalDate birthDate;
    private String gender;
    private String password;
    private ArrayList<Language> languages;

    //Getter and setter section
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        String[] birthArray = birthDate.split("\\.");
        this.birthDate = LocalDate.of(Integer.parseInt(birthArray[2]),
                Integer.parseInt(birthArray[1]),
                Integer.parseInt(birthArray[0]));
    }
    //getter and setter options
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public ArrayList<Language> getLanguages() {
        return languages;
    }
    public void setLanguages(ArrayList<Language> languages) {
        this.languages = languages;
    }
    public void add(Language language) {
        languages.add(language);
    }
    public void remove(Language language) {
        languages.remove(language);
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Information{" +
                "name='" + name +
                ", firstName='" + firstName +
                ", email='" + email +
                ", phoneNumber='" + phoneNumber +
                ", birthDate='" + birthDate +
                ", gender='" + gender +
                ", languagesKnown=" + languages +
                "}\n";
    }
}
