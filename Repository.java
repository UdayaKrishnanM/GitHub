package com.example.Springfita;

import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<EmployeeEntity,Integer> {
}
