package Apllication;

import java.util.*;

abstract class Consumer {
    public Resume resume;
    public ArrayList<Consumer> friendList;

//    public Consumer(String name, String firstName, String email,
//                    String phoneNumber, String birthDate, String gender) {
//        resume = new Resume(name, firstName, email, phoneNumber,
//                birthDate, gender);
//        friendList = new ArrayList<>();
//    }
    public Consumer() {
        resume = null;
        friendList = new ArrayList<>();
    }
    public void add(Consumer consumer) {
        if(!consumer.friendList.contains(this)){
            friendList.add(consumer);
            consumer.friendList.add(this);
        }
    }
    public void remove(Consumer consumer) {
        friendList.remove(consumer);
        consumer.friendList.remove(this);
    }
    public void add(Experience experience) {
        if(experience.startDate != null)
            resume.historyExperience.add(experience);
    }
    public void add(Education education) {
        if(education.startDate != null)
            resume.historyEducation.add(education);
    }
    public Double meanGPA() {
        double sumOfGpa = 0;
        for(Education education : resume.historyEducation)
                sumOfGpa += education.finalGPA;

        return sumOfGpa / resume.historyEducation.size();
    }
    public int getGraduationYear() {
        for(Education education : resume.historyEducation)
            if(education.educationLevel.compareTo("college") == 0)
                if(education.endDate != null)
                    return education.endDate.getYear();
        return 0;
    }
    /*
    We don't want to enter into a recursion to infinity
    So we have to parse the friends lists and build a string
    with their names
    */
    public String showFriendsList() {
        String aux = "";
        for (Consumer consumer : friendList)
            aux += " " + consumer.resume.information.getName()
                    +" "+consumer.resume.information.getFirstName();
        return aux;
    }

    /*
    we parse the "graph" iteratively we have a vector
    that keeps in memory what we already have gone over
    and a vector that does the same, just that it keeps
    consumers we have to search through, the vectors
    work like a Queue in logic because of the need
    to perform a BFS
    */
    public int getDegreeInFriendship(Consumer consumer) {
        //this will represent the current consumer at which we are
        //at the moment
        Consumer consumerBase;
        //this is a vector of consumers that we still have to move through
        Vector<Consumer> consumerToParse = new Vector<>();
        //these are consumers that we moved through
        Vector<Consumer> alreadyParsed = new Vector<>();
        int depth = 0;
        //we are the first consumers to parse through
        consumerToParse.add(this);
        //the null element will be used to represent the level at which we are
        //and will be added every time we find another null value
        consumerToParse.add(null);
        while(consumerToParse.size() != 1) {
            consumerBase = consumerToParse.get(0);
            consumerToParse.remove(0);
            //we went down a level
            if(consumerBase == null) {
                consumerToParse.remove(null);
                consumerToParse.add(null);
                depth++;
                continue;
            }
            //we add to the parsed list after we verify if
            //the consumer is not the null we added for the
            //depth calculation
            alreadyParsed.add(consumerBase);
            //if we found what we searched for
            if(consumerBase.equals(consumer))
                return depth;
            else {
                for(Consumer auxConsumers : consumerBase.friendList){
                    if(!alreadyParsed.contains(auxConsumers))
                        consumerToParse.add(auxConsumers);
                }
            }
        }
        //we didn't find a path to the desired consumer
        return 10000;
    }
}
