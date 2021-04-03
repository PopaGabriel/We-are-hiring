package Apllication;

public class Recruiter extends Employee {
    private Double rating;

    public Recruiter() {
        super();
        rating = 5.0;
    }

    public Double getRat() {
        return rating;
    }

    //he evaluates if the given user gets past the job requirements
    //and if he does he sends a request to the manager
    public int evaluate(Job job, User user) {
        Application app = Application.getInstance();

        rating += 0.1;
        if(!job.meetsRequirments(user))
            return -1;

        Company company = app.getCompany(getCompanyName());
        Double score = rating * user.getTotalScore();
        company.getMan().add(new Request<>(job, user, this, score));
        return (int) (rating * user.getTotalScore());
    }
}
