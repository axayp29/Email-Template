package com.template.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.template.model.Doctor;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long>{

}