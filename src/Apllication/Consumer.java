package Apllication;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Vector;

public abstract class Consumer {
    private Resume resume;
    private ArrayList<Consumer> friendList;

    public Consumer() {
        friendList = new ArrayList<>();
    }
    //this method adds a consumer to the friendlist
    //while also adding this object to theirs
    public void add(Consumer consumer) {
        if (!consumer.friendList.contains(this)) {
            friendList.add(consumer);
            consumer.friendList.add(this);
        }
    }

    //this method removes a consumer to the friendlist
    //while also removing this object to theirs
    public void remove(Consumer consumer) {
        friendList.remove(consumer);
        consumer.friendList.remove(this);
    }

    //this adds an experience to the users experiences list
    public void add(Experience experience) {
        if (experience.getStartDate() != null)
            getHisExp().add(experience);
    }

    //this adds an education to the users education list
    public void add(Education education) {
        if (education.getStartDate() != null)
            getHisEdu().add(education);
    }

    //this method returns the mean Gpa of the consumer
    public Double meanGPA() {
        double sumOfGpa = 0;
        for (Education education : getHisEdu())
            sumOfGpa += education.getFinalGPA();

        return sumOfGpa / getHisEdu().size();
    }

    //this method returns the year of graduation from college
    //and if they didn't graduate yet it will return 0
    public int getGraduationYear() {
        for (Education education : getHisEdu())
            if (education.getEducationLevel().compareTo("college") == 0)
                if (education.getEndDate() != null)
                    return education.getEndDate().getYear();
        return 0;
    }

    /*
    Here i calculate the experience of an user
    i sum the time periods into an auxiliary time date
    and then i aproximate the years by the number of months
    in specific with 3 months
    */
    public int getExperienceTime() {
        LocalDate dateSum = LocalDate.of(0,1,1);
        LocalDate auxiliarDate = LocalDate.of(LocalDate.now().getYear(),
                LocalDate.now().getMonthValue(),
                LocalDate.now().getDayOfMonth());
        long nrDays;
        for (Experience experience : getHisExp()) {
            if (experience.getEndDate() == null) {
                nrDays = Period.between(experience.getStartDate(), auxiliarDate).toTotalMonths();
                dateSum = dateSum.plusDays(nrDays);
            } else {
                nrDays = Period.between(experience.getStartDate(), experience.getEndDate()).toTotalMonths();
                dateSum = dateSum.plusMonths(nrDays);
                dateSum = dateSum.plusDays(experience.getStartDate().getDayOfMonth() - experience.getEndDate().getDayOfMonth());
            }
        }
        /*
        i comparre with 4 and not with 3 because my auxiliar
         date starts at 1 month, i also remove the extra day
        from the auxiliar date
        */
        dateSum = dateSum.minusDays(1);
        if (dateSum.getMonthValue() >= 4)
            return (dateSum.plusYears(1)).getYear();
        return dateSum.getYear();
    }

    /*
    We don't want to enter into a recursion to infinity
    So we have to parse the friends lists and build a string
    with their names
    */
    public String showFriends() {
        String aux = "";
        for (Consumer consumer : friendList)
            aux += " " + consumer.resume.getInfo().getName()
                    + " " + consumer.resume.getInfo().getFirstName();
        return aux;
    }
    //Setter and getter section
    public Resume getRes() {return resume;}
    public void setResume(Resume resume) {this.resume = resume;}
    //this method is used for easier access to the users name
    public String getName() {
        return resume.getInfo().getName();
    }
    //this method is used for easier access to the first name of the user
    public String getFirstName() {
        return resume.getInfo().getFirstName();
    }
    public ArrayList<Consumer> getFriends() {return friendList;}
    //this returns a consumer from the friends list using an index
    public Consumer getFriends(int i) {
        if (friendList.size() <= i) {
            return null;
        }
        return friendList.get(i);
    }

    //I use this "getter" for better easier access to the resume experience list
    public TreeSet<Experience> getHisExp() {
        return resume.getHisExp();
    }
    //I use this "getter" for better easier access to the resume education list
    public TreeSet<Education> getHisEdu() {
        return resume.getHisEdu();
    }

    /*
    I parse the "graph" iteratively, I have an arraylist
    that keeps in memory what consumers we already have gone through
    and a arraylist that keeps track of the consumers that we already parsed

    I keep track of the depth at which I am with the second while
    because we are continuously adding to the array toParse
    I have to always take the size of the array before I
    start parsing it, if i then finish moving through that
    //size I increment the depth

    If i find the searched for consumer i return the depth at which i found him
    and if not i return a very large number.
    */
    public int getDegreeInFriendship(Consumer consumer) {
        ArrayList<Consumer> friendsParsed = new ArrayList<>();
        ArrayList<Consumer> friendsToParse = new ArrayList<>();
        int depth = 0, size;
        friendsToParse.add(this);
        while(friendsToParse.size() > 0) {
            //here is where i keep track of the original size of
            // the toParse array
            size = friendsToParse.size();
            while (size > 0) {
                //if i find him
                if (friendsToParse.get(0).equals(consumer))
                    return depth;
                else {
                    //if not i keep searching
                    //i add all the consumers friends that
                    // don't already exist in the array toParse
                    size--;
                    for (Consumer consumer1 : friendsToParse.get(0).friendList) {
                        if (!friendsToParse.contains(consumer1) &&
                                !friendsParsed.contains(consumer1))
                            friendsToParse.add(consumer1);
                    }
                    //I add the consumer through which i just went over
                    //to the alreadyParsed array
                    if(!friendsParsed.contains(friendsToParse.get(0)))
                        friendsParsed.add(friendsToParse.get(0));
                    //i remove him from the array toParse
                    friendsToParse.remove(0);
                }
            }
            depth++;
        }

        return 10000;
    }
}
