package Apllication;

public class Constraint {
        public Double superiorLimit;
        public Double inferiorLimit;
        public Constraint(Double low, Double high) {
            superiorLimit = high;
            inferiorLimit = low;
        }
        public Constraint(){
            superiorLimit = null;
            inferiorLimit = null;
        }

    @Override
    public String toString() {
        return inferiorLimit  + " - " + superiorLimit + '}';
    }
}


