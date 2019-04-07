package com.projectboard.projectboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectboard.projectboard.domain.Project;
import com.projectboard.projectboard.exceptions.ProjectIdException;
import com.projectboard.projectboard.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	public Project saveOrUpdateProject (Project project) {
		
//		if(project.getStatus() == null || project.getStatus() == "") {
//			project.setStatus("TO_DO");
//		}
		
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project); 
		} catch (Exception e) {
			throw new ProjectIdException("Project ID '" + project.getProjectIdentifier().toUpperCase() + "' already exists.");
		}

	}
	
	public Iterable<Project> findAll () {
		return projectRepository.findAll();
	}
	
	public Project findProjectIdentifier (String id) {
		
		Project project = projectRepository.findByProjectIdentifier(id.toUpperCase());
		if(project == null) {
			throw new ProjectIdException("Project ID '" + id.toUpperCase() + "' does not exist");
		}
		return projectRepository.findByProjectIdentifier(id.toUpperCase());
	}
	
	public void deleteByProjectIdentifier (String id) {
		Project project = findProjectIdentifier(id.toUpperCase());
		projectRepository.delete(project);
	}
}
