package Apllication;

import java.util.ArrayList;
import java.util.TreeSet;

public class Resume {
/*
    I used treeset to add objects because
    they need to be added in a specific way(descending or ascending)
    and the set permits me to build a specific comparator so I don't have
    to sort every time i add an element like in the case of arrayLists
*/
    private final Information information;
    private TreeSet<Education> historyEducation;
    private TreeSet<Experience> historyExperience;

    public String toString() {
        return information + " " + historyEducation + historyExperience;
    }

    private Resume(ResumeBuilder resumeBuilder) {
        information = resumeBuilder.information;
        historyEducation = resumeBuilder.historyEducation;
        historyExperience = resumeBuilder.historyExperience;
    }
    //getter section
    public TreeSet<Experience> getHisExp() {
        return historyExperience;
    }
    public TreeSet<Education> getHisEdu() {
        return historyEducation;
    }
    public TreeSet<Experience> getHistoryExperience() {
        return historyExperience;
    }
    public Information getInfo() {
        return information;
    }

    public static class ResumeBuilder {
        private Information information = null;
        private TreeSet<Education> historyEducation = null;
        private TreeSet<Experience> historyExperience = null;

        public ResumeBuilder name(String name) {
            if (information == null)
                information = new Information();
            information.setName(name);
            return this;
        }

        public ResumeBuilder firstName(String firstName) {
            if (information == null)
                information = new Information();
            information.setFirstName(firstName);
            return this;
        }

        public ResumeBuilder email(String email) {
            if (information == null)
                information = new Information();
            information.setEmail(email);
            return this;
        }

        public ResumeBuilder phoneNumber(String phoneNumber) {
            if (information == null)
                information = new Information();
            information.setPhoneNumber(phoneNumber);
            return this;
        }

        public ResumeBuilder birthDate(String birthDate) {
            if (information == null)
                information = new Information();
            information.setBirthDate(birthDate);
            return this;
        }

        public ResumeBuilder gender(String gender) {
            if (information == null)
                information = new Information();
            information.setGender(gender);
            return this;
        }

        public ResumeBuilder language(ArrayList<Language> languages) {
            if (information == null)
                information = new Information();
            information.setLanguages(languages);
            return this;
        }
        public ResumeBuilder addLanguage(Language language) {
            if (information == null)
                information = new Information();
            information.add(language);
                return this;
        }
        public ResumeBuilder addExperience(Experience experience) {
            if (historyExperience == null)
                historyExperience = new TreeSet<>();
            historyExperience.add(experience);
            return this;
        }

        public ResumeBuilder Experience(TreeSet<Experience> experiences) {
            if (historyExperience == null)
                historyExperience = new TreeSet<>();
            historyExperience = experiences;
            return this;
        }

        public ResumeBuilder addEducation(Education education) {
            if (historyEducation == null)
                historyEducation = new TreeSet<>();
            historyEducation.add(education);
            return this;
        }
        public ResumeBuilder Education(TreeSet<Education> educations) {
            if (historyEducation == null)
                historyEducation = new TreeSet<>();
            historyEducation = educations;
            return this;
        }
        public ResumeBuilder password(String password) {
            if(information == null)
                information = new Information();
            information.setPassword(password);
            return this;
        }
        public Resume build() {
            return new Resume(this);
        }
    }
}
