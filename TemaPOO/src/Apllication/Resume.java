package Apllication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TreeSet;

public class Resume {
    //I used treeset to add objects because
    //they need to be added in a specific way(descending or ascending)
    //and the set permits me to build a specific comparator so I don't have
    //to sort every time i add an element
    Information information;
    TreeSet<Education> historyEducation;
    TreeSet<Experience> historyExperience;

    //        public Resume(String name, String firstName, String email,
//                      String phoneNumber, String birthDate, String gender){
//            information = new Information(name, firstName, email,
//                     phoneNumber, birthDate, gender);
//            historyEducation = new TreeSet<Education>();
//            historyExperience = new TreeSet<Experience>();
//        }
    public String toString(){
        return information + " " + historyEducation + historyExperience;
    }
    public Resume(ResumeBuilder resumeBuilder){
        information = resumeBuilder.information;
        historyEducation = resumeBuilder.historyEducation;
        historyExperience = resumeBuilder.historyExperience;
    }
    public static class ResumeBuilder{
        Information information;
        TreeSet<Education> historyEducation;
        TreeSet<Experience> historyExperience;
        public ResumeBuilder() {
            information = new Information();
            historyEducation = new TreeSet<Education>();
            historyExperience = new TreeSet<Experience>();
        }
        public ResumeBuilder name(String name) {
            this.information.setName(name);
            return this;
        }
        public ResumeBuilder firstName(String firstName) {
            this.information.setFirstName(firstName);
            return this;
        }
        public ResumeBuilder email(String email) {
            this.information.setEmail(email);
            return this;
        }
        public ResumeBuilder phoneNumber(String phoneNumber) {
            this.information.setPhoneNumber(phoneNumber);
            return this;
        }
        public ResumeBuilder birthDate(String birthDate) {
            this.information.setBirthDate(birthDate);
            return this;
        }
        public ResumeBuilder gender(String gender) {
            this.information.setGender(gender);
            return this;
        }
        public ResumeBuilder language(ArrayList<Language> languages) {
            this.information.setLanguages(languages);
            return this;
        }
        public ResumeBuilder Experience(TreeSet<Experience> experiences) {
            historyExperience = experiences;
            return this;
        }
        public ResumeBuilder Education(TreeSet<Education> educations) {
            historyEducation = educations;
            return this;
        }
        public Resume build() {
            return new Resume(this);
        }
    }
}
