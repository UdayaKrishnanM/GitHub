package com.example.Springfita;

import jakarta.persistence.*;
import lombok.Data;

@Data//need to install software lambok it will work but postman does not fetch
@Entity
@Table(name="Emp_Table")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private float salary;
    private String dept;
    @Enumerated(EnumType.STRING)//to store it as String in db instead of 01 or 1;
    private Gender gender;

    public EmployeeEntity(){

    }
    public EmployeeEntity(int ID, String name, float salary, String dept, Gender gender) {
        this.id = ID;
        this.name = name;
        this.salary = salary;
        this.dept = dept;
        this.gender = gender;
    }

    public int getID() {
        return id;
    }

    public void setID(int ID) {
        this.id = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public Gender isGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
//swagger
//embbadable embed
//loggers
