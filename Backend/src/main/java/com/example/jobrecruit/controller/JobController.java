package com.example.jobrecruit.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobrecruit.dto.JobDTO;
import com.example.jobrecruit.entity.Job;
import com.example.jobrecruit.exception.ResourceNotFoundException;
import com.example.jobrecruit.service.JobService;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs() {
        List<Job> jobs = jobService.getAllJobs();
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        Optional<Job> optionalJob = jobService.getJobById(id);
        return optionalJob.map(job -> new ResponseEntity<>(job, HttpStatus.OK))
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with ID: " + id));
    }

    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody JobDTO jobDTO) {
        Job createdJob = jobService.createJob(jobDTO);
        return new ResponseEntity<>(createdJob, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id, @RequestBody JobDTO jobDTO) {
        Optional<Job> optionalJob = jobService.updateJob(id, jobDTO);
        return optionalJob.map(job -> new ResponseEntity<>(job, HttpStatus.OK))
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with ID: " + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        boolean deleted = jobService.deleteJob(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        throw new ResourceNotFoundException("Job not found with ID: " + id);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Job>> searchJobs(
            @RequestParam(required = false) String jobTitle,
            @RequestParam(required = false) String company,
            @RequestParam(required = false) String location
    ) {
        List<Job> jobs;
        if (jobTitle != null) {
            jobs = jobService.searchByJobTitle(jobTitle);
        } else if (company != null) {
            jobs = jobService.searchByCompany(company);
        } else if (location != null) {
            jobs = jobService.searchByLocation(location);
        } else {
            throw new IllegalArgumentException("Invalid search parameters");
        }
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}

