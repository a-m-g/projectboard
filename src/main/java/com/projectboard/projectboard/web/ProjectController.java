package com.projectboard.projectboard.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectboard.projectboard.domain.Project;
import com.projectboard.projectboard.service.ProjectService;
import com.projectboard.projectboard.service.ValidationService;

@RestController
@RequestMapping("/api/project")
@CrossOrigin
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ValidationService validationService;
	
	//Add @Valid which enforces a valid project obect - used in conjunction with @NotBlank
	//on the object, and BindingResult
	@PostMapping("")
	public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){
			
		//Refactored - moved to ValidationService
		//		if(result.hasErrors()) {
		//			Map<String,String> errorMap = new HashMap<String,String>();
		//			for(FieldError error : result.getFieldErrors()) {
		//				errorMap.put(error.getField(), error.getDefaultMessage());
		//			}
		//			return new ResponseEntity<Map<String,String>>(errorMap, HttpStatus.BAD_REQUEST);
		//		}
		ResponseEntity<?> errorMap = validationService.mapValidationService(result);
		if(errorMap != null) { return errorMap; }
		
		Project newPrj = projectService.saveOrUpdateProject(project);
		
		return new ResponseEntity<Project>(newPrj, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/all")
	public Iterable<Project> getAllPTs(){
		return projectService.findAll();
	}
	
	@GetMapping("/{projectId}")
	public  ResponseEntity<?> getProjectById(@PathVariable String projectId){
		Project project = projectService.findProjectIdentifier(projectId);
		return new ResponseEntity<Project>(project,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{projectId}")
	public ResponseEntity<?> deleteByProjectIdentifier(@PathVariable String projectId){
		projectService.deleteByProjectIdentifier(projectId);
		return new ResponseEntity<String>("Project Task " + projectId + " deleted", HttpStatus.OK);
	}
}
