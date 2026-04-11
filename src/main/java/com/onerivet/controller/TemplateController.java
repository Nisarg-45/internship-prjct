package com.onerivet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onerivet.dto.TemplateCoverageRequestDto;
import com.onerivet.dto.TemplatePlanRequestDto;
import com.onerivet.dto.TemplateRequestDto;
import com.onerivet.dto.TemplateResponseDto;
import com.onerivet.service.TemplateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/templates")
@RequiredArgsConstructor
public class TemplateController {
	
	@Autowired
	
	private TemplateService templateService;

	@PostMapping
	public TemplateResponseDto createTemplate(@RequestBody TemplateRequestDto request) {
	    return templateService.createTemplate(request);
	}
	
	@PostMapping("/{templateId}/plans")
	public String addPlansToTemplate(
	        @PathVariable Integer templateId,
	        @RequestBody TemplatePlanRequestDto request) {

	    templateService.addPlansToTemplate(templateId, request.getPlanIds());

	    return "Plans added successfully";
	}
	
	@PostMapping("/{templateId}/coverages")
	public String addCoveragesToTemplate(
	        @PathVariable Integer templateId,
	        @RequestBody TemplateCoverageRequestDto request) {

	    templateService.addCoveragesToTemplate(templateId, request.getCoverageIds());

	    return "Coverages added successfully";
	}
}