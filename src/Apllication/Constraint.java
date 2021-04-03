package Apllication;

public class Constraint {
    public Double superiorLimit;
    public Double inferiorLimit;

    public Constraint(Double low, Double high) {
        superiorLimit = high;
        inferiorLimit = low;
    }

    public Constraint() {
        superiorLimit = null;
        inferiorLimit = null;
    }

    //this method checks if the value respects the set requirements
    public Boolean meetsConstraint(Double value) {
            if (inferiorLimit != null)
                if (inferiorLimit > value)
                    return false;
            if (superiorLimit != null)
                if (superiorLimit < value)
                    return false;

        return true;
    }
    @Override
    public String toString() {
        return inferiorLimit + " - " + superiorLimit + '}';
    }
}


