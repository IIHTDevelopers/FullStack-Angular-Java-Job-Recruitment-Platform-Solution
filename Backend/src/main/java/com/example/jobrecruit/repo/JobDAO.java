package com.example.jobrecruit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jobrecruit.entity.Job;

@Repository
public interface JobDAO extends JpaRepository<Job, Long> {
	// Custom query methods for search operations
	List<Job> findByJobTitleContainingIgnoreCase(String jobTitle);

	List<Job> findByCompanyContainingIgnoreCase(String company);

	List<Job> findByLocationContainingIgnoreCase(String location);
}
