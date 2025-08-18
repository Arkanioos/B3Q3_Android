package maxm.androidb3.androidb3.features.student.model;

import maxm.androidb3.androidb3.features.common.model.ListItem;
import maxm.androidb3.androidb3.features.student.data.entity.StudentEntity;

import java.io.Serializable;

public class Student implements Serializable, ListItem {
    private long id = -1;
    private String firstName = null;
    private String lastName = null;
    private String registration = null;
    private long yearId = -1;

    public Student(long id, String firstName, String lastName, String registration, long yearId){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.registration = registration;
        this.yearId = yearId;
    }

    public Student(String firstName, String lastName, String registration, long yearId){
        this.firstName = firstName;
        this.lastName = lastName;
        this.registration = registration;
        this.yearId = yearId;
    }

    public StudentEntity toEntity(){
        return new StudentEntity(firstName, lastName, registration, yearId);
    }

    public void setId(long id) { this.id = id; }
    public long getId() throws IllegalStateException{
        if(id < 0) throw new IllegalStateException("Id not defined");
        return id;
    }

    @Override
    public String toString(){ return firstName + " " + lastName + " (" + registration + ")"; }
}
