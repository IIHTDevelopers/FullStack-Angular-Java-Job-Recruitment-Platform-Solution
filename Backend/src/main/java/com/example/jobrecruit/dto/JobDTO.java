package com.example.jobrecruit.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class JobDTO {
    private Long id;

    @NotNull
    @Size(min = 3, max = 50)
    private String jobTitle;

    @NotNull
    private String description;

    @NotNull
    private String company;

    @NotNull
    private String location;

    @NotNull
    private String employmentType;

    private String salaryRange;

    private String skillsRequired;

    private String qualifications;

    @NotNull
    @Email
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

    public JobDTO(Long id, String jobTitle, String description, String company, String location, String employmentType,
                  String salaryRange, String skillsRequired, String qualifications, String contactEmail) {
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

    public JobDTO() {
        super();
    }

}
