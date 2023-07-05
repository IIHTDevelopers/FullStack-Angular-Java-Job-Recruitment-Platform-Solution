package com.example.jobrecruit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 50)
	private String jobTitle;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private String company;

	@Column(nullable = false)
	private String location;

	@Column(nullable = false)
	private String employmentType;

	@Column
	private String salaryRange;

	@Column
	private String skillsRequired;

	@Column
	private String qualifications;

	@Column(length = 200)
	private String contactEmail;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}

	public String getSalaryRange() {
		return salaryRange;
	}

	public void setSalaryRange(String salaryRange) {
		this.salaryRange = salaryRange;
	}

	public String getSkillsRequired() {
		return skillsRequired;
	}

	public void setSkillsRequired(String skillsRequired) {
		this.skillsRequired = skillsRequired;
	}

	public String getQualifications() {
		return qualifications;
	}

	public void setQualifications(String qualifications) {
		this.qualifications = qualifications;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public Job(Long id, @NotNull @Size(min = 3, max = 50) String jobTitle, @NotNull String description,
			@NotNull String company, @NotNull String location, @NotNull String employmentType, String salaryRange,
			String skillsRequired, String qualifications, @NotNull String contactEmail) {
		super();
		this.id = id;
		this.jobTitle = jobTitle;
		this.description = description;
		this.company = company;
		this.location = location;
		this.employmentType = employmentType;
		this.salaryRange = salaryRange;
		this.skillsRequired = skillsRequired;
		this.qualifications = qualifications;
		this.contactEmail = contactEmail;
	}

	public Job() {
		super();
	}

}
