package com.example.jobrecruit.service;

import java.util.List;
import java.util.Optional;

import com.example.jobrecruit.dto.JobDTO;
import com.example.jobrecruit.entity.Job;

public interface JobService {
    List<Job> getAllJobs();

    Optional<Job> getJobById(Long id);

    Job createJob(JobDTO jobDTO);

    Optional<Job> updateJob(Long id, JobDTO jobDTO);

    boolean deleteJob(Long id);

    List<Job> searchByJobTitle(String jobTitle);

    List<Job> searchByCompany(String company);

    List<Job> searchByLocation(String location);
}

