package com.example.androidb3.features.studentSelection.data.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.androidb3.features.studentSelection.model.Student;
import com.example.androidb3.features.yearSelection.data.entity.YearEntity;

@Entity(
        tableName = "student_table",
        foreignKeys = @ForeignKey(
                entity = YearEntity.class,
                parentColumns = "id",      // colonne dans year_table
                childColumns = "yearId",   // colonne dans class_table
                onDelete = ForeignKey.CASCADE // supprime les classes si l'année est supprimée
        )
)
public class StudentEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String firstName;
    public String lastName;
    public String registration;
    public long yearId;

    public long getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getRegistration() { return registration; }
    public void setRegistration(String registration) { this.registration = registration; }

    public StudentEntity(){}

    public StudentEntity(String firstName, String lastName, String registration, long yearId){
        this.firstName = firstName;
        this.lastName = lastName;
        this.registration = registration;
        this.yearId = yearId;
    }

    public Student toModel(){
        return new Student(firstName, lastName, registration, yearId);
    }
}
