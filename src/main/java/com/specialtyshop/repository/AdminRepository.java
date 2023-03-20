package com.specialtyshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.specialtyshop.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

}
