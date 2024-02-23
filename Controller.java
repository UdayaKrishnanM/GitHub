package com.example.Springfita;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    @Autowired
    private Service service;

//    @GetMapping("/get-record") //http://localhost:8080/get-record
//    public EmployeeEntity getrecords(){
//       EmployeeEntity obj = new EmployeeEntity(25,"Sakthi",12500,"ECE",true);
//        return obj;
//    }

    //@PathVariable just selects the particular object in the table and can make changes via that selection
    @GetMapping("/get-recordByID/{id}")
    public EmployeeEntity getRecordByID(@PathVariable int id){
        return service.getByUniqueID(id);
    }

    //this method is to post one record to database
    //@RequestBody converts JSON type from Postman(frontend) to JAVA object format and vice versa
    @PostMapping("/post-record")
    public EmployeeEntity addemp(@RequestBody EmployeeEntity emp){
        return service.empsave(emp);
    }

    //gets all the records and displays in MySQL
    @GetMapping("/getAllRecords")
    public List<EmployeeEntity> getAllRecords(){
        return service.listRecords();
    }

    //saves all the records entered manually in postman in MySQL
    //the return type is just to confirm that the records are saved
    //in postMappings return type sometimes depends ont the frontend guy on what he wants is it a message or just return the object so that I can evaluate it.
    @PostMapping("/saveAllrecords")
    public List<EmployeeEntity> saveAllRecords(List<EmployeeEntity> emp){
        return service.saveAllRecords(emp);
    }

    @DeleteMapping("/deleteByID")
    public String deleteById(int id){
        service.deleteByID(id);
        return "Deleted successfully";
    }

    @PutMapping("/updateByID/{id}")
    public EmployeeEntity updateById(@PathVariable int id,EmployeeEntity emp){
        return service.updateById(id,emp);
    }

}
