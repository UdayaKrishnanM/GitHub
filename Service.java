package com.example.Springfita;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    Repository emprep;

    public EmployeeEntity empsave(EmployeeEntity emp) {
        return emprep.save(emp);
    }

    public List<EmployeeEntity> saveAllRecords(List<EmployeeEntity> emp) {
        return emprep.saveAll(emp);
    }

    public void deleteByID(int id) {
        emprep.deleteById(id);
        System.out.println("Deleted successfully");
    }

    public List<EmployeeEntity> listRecords() {
        return emprep.findAll();
    }

    public EmployeeEntity getByUniqueID(int id) {
        if (emprep.findById(id).isPresent())
            return emprep.findById(id).get();
        return null;
    }

    //study to understand this method -> emp is the obj you hard code in postman(frontend) , emptemp is the obj that is got by id in the db
    //if the user wants to update (change) the particular aspects like dept or salary or gender or name
    //to do that user just wants to send the id and an emp object with what he wants to change and setting others entity to null/0(zero)
    //eg:{  name:null,
//          salary:12000,
//          dept:null,
//          gender: null;
//      } so in this emp sent user just wants to update the salary
    public EmployeeEntity updateById(int id,EmployeeEntity emp){
        if (emprep.findById(id).isPresent()) {
            EmployeeEntity emptemp = emprep.findById(id).get();
            emptemp.setDept(emp.getDept() != null ? emp.getDept() : emptemp.getDept());
            emptemp.setSalary(emp.getSalary() != 0 ? emp.getSalary() : emptemp.getSalary());
            emptemp.setDept(emp.getName() != null ? emp.getName() : emptemp.getName());
            //in db due to the usage of enum it maybe saved a 01 or 1 something
            emptemp.setGender(emp.getGender() != null ? emp.getGender() : emptemp.getGender());
            return emprep.save(emptemp);
        }
        return null;
    }

}
