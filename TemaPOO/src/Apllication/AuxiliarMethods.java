package Apllication;

public class AuxiliarMethods {
    //I parse the string and change it to int along the way
    public static int changeSToI(String s1){
        int number = 0, i = 0;
        // normally you add a zero before a single digit date
        while(s1.charAt(i) == '0')
            i++;
        for(; i < s1.length(); i++){
            number = number * 10 + s1.charAt(i) - 48;
        }
        return number;
    }
}
