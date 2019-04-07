package com.projectboard.projectboard.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projectboard.projectboard.domain.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

	//getById doesn't exist - could use findByID, but this is to demonstrate a custom get
	//Project getById(Long id);
	
	Project findByProjectIdentifier(String projectId);
}
