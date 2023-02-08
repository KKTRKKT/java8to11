package me.kktrkkt.java8to11.codecoverage;

public class Moim {
    int maxNumberOfAttendess;

    int numberOfEnrollment;

    public boolean isEnrollmentFull(){
        if(maxNumberOfAttendess == 0){
            return false;
        }

        return maxNumberOfAttendess <= numberOfEnrollment;
    }
}
