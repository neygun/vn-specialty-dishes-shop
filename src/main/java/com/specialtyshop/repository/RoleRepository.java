package com.specialtyshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.specialtyshop.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	public Role findByName(String name);
}
