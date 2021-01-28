package Apllication;

public class Notifi {
    private String message;

    public Notifi() {
        message = "Awkward message";
    }
    public Notifi(String message) {
        this.message = message;
    }

    //the constructor can send different messages depending on the need
    public Notifi(Job job, int news) {
        if(news == 1)
            message = "Felicitari ati fost selectat pentru "
                    + job.getPos();
        else if (news == 0)
            message = "Am adaugat un nou job de care este " +
                    "posibil sa fi interesat";
        else
            message = "Ne bucuram de interesul acordat" +
                    " dar am decis sa nu te alegem pe tine";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

