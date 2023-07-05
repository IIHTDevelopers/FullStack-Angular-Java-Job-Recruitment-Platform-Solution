package com.example.jobrecruit.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jobrecruit.dto.JobDTO;
import com.example.jobrecruit.entity.Job;
import com.example.jobrecruit.repo.JobDAO;
import com.example.jobrecruit.service.JobService;

@Service
public class JobServiceImpl implements JobService {
    private final JobDAO jobDAO;

    @Autowired
    public JobServiceImpl(JobDAO jobDAO) {
        this.jobDAO = jobDAO;
    }

    @Override
    public List<Job> getAllJobs() {
        return jobDAO.findAll();
    }

    @Override
    public Optional<Job> getJobById(Long id) {
        return jobDAO.findById(id);
    }

    @Override
    public Job createJob(JobDTO jobDTO) {
        Job job = new Job();
        // Set job properties from DTO
        job.setJobTitle(jobDTO.getJobTitle());
        job.setDescription(jobDTO.getDescription());
        job.setCompany(jobDTO.getCompany());
        job.setLocation(jobDTO.getLocation());
        job.setEmploymentType(jobDTO.getEmploymentType());
        job.setSalaryRange(jobDTO.getSalaryRange());
        job.setSkillsRequired(jobDTO.getSkillsRequired());
        job.setQualifications(jobDTO.getQualifications());
        job.setContactEmail(jobDTO.getContactEmail());

        return jobDAO.save(job);
    }

    @Override
    public Optional<Job> updateJob(Long id, JobDTO jobDTO) {
        Optional<Job> optionalJob = jobDAO.findById(id);
        if (optionalJob.isPresent()) {
            Job job = optionalJob.get();
            // Update job properties from DTO
            job.setJobTitle(jobDTO.getJobTitle());
            job.setDescription(jobDTO.getDescription());
            job.setCompany(jobDTO.getCompany());
            job.setLocation(jobDTO.getLocation());
            job.setEmploymentType(jobDTO.getEmploymentType());
            job.setSalaryRange(jobDTO.getSalaryRange());
            job.setSkillsRequired(jobDTO.getSkillsRequired());
            job.setQualifications(jobDTO.getQualifications());
            job.setContactEmail(jobDTO.getContactEmail());

            return Optional.of(jobDAO.save(job));
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteJob(Long id) {
        if (jobDAO.existsById(id)) {
            jobDAO.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Job> searchByJobTitle(String jobTitle) {
        return jobDAO.findByJobTitleContainingIgnoreCase(jobTitle);
    }

    @Override
    public List<Job> searchByCompany(String company) {
        return jobDAO.findByCompanyContainingIgnoreCase(company);
    }

    @Override
    public List<Job> searchByLocation(String location) {
        return jobDAO.findByLocationContainingIgnoreCase(location);
    }

}
