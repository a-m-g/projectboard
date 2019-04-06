package com.projectboard.projectboard.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projectboard.projectboard.domain.ProjectTask;

@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {

	//getById doesn't exist - could use findByID, but this is to demonstrate a custom get
	ProjectTask getById(Long id);
}
