package com.projectboard.projectboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectboard.projectboard.domain.ProjectTask;
import com.projectboard.projectboard.repository.ProjectTaskRepository;

@Service
public class ProjectTaskService {

	@Autowired
	private ProjectTaskRepository projectTaskRepository;
	
	public ProjectTask saveOrUpdateRpojectTask (ProjectTask projectTask) {
		
		if(projectTask.getStatus() == null || projectTask.getStatus() == "") {
			projectTask.setStatus("TO_DO");
		}
		
		return projectTaskRepository.save(projectTask); 
	}
	
	public Iterable<ProjectTask> findAll () {
		return projectTaskRepository.findAll();
	}
	
	public ProjectTask getById (Long id) {
		return projectTaskRepository.getById(id);
	}
	
	public void deleteById (Long id) {
		ProjectTask projectTask = getById(id);
		projectTaskRepository.delete(projectTask);
	}
}
