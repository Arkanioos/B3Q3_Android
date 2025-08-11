package maxm.androidb3.androidb3.features.studentSelection.model;

import maxm.androidb3.androidb3.features.common.model.ListItem;
import maxm.androidb3.androidb3.features.studentSelection.data.entity.StudentEntity;

import java.io.Serializable;

public class Student implements Serializable, ListItem {
    private String firstName = null;
    private String lastName = null;
    private String registration = null;
    private long yearId = -1;

    public Student(String firstName, String lastName, String registration, long yearId){
        this.firstName = firstName;
        this.lastName = lastName;
        this.registration = registration;
        this.yearId = yearId;
    }

    public StudentEntity toEntity(){
        return new StudentEntity(firstName, lastName, registration, yearId);
    }

    @Override
    public String toString(){ return firstName + " " + lastName + " (" + registration + ")"; }
}
