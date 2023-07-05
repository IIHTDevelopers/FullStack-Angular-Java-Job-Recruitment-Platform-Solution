package com.example.jobrecruit.utils;

import com.example.jobrecruit.dto.JobDTO;
import com.example.jobrecruit.entity.Job;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MasterData {

    public static JobDTO getJobDTO(){
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(1L);
        jobDTO.setJobTitle("Java Developer");
        jobDTO.setDescription("Spring Framework");
        jobDTO.setCompany("TechAdemy");
        jobDTO.setLocation("Noida");
        jobDTO.setEmploymentType("PERMANENT");
        jobDTO.setSalaryRange("10 LPA");
        jobDTO.setSkillsRequired("4+ yr Exp");
        jobDTO.setQualifications("CSE");
        jobDTO.setContactEmail("random@gmail.com");
        return jobDTO;
    }

    public static Job getJob(){
        Job job = new Job();
        job.setId(1L);
        job.setJobTitle("Java Developer");
        job.setDescription("Spring Framework");
        job.setCompany("TechAdemy");
        job.setLocation("Noida");
        job.setEmploymentType("PERMANENT");
        job.setSalaryRange("10 LPA");
        job.setSkillsRequired("4+ yr Exp");
        job.setQualifications("CSE");
        job.setContactEmail("random@gmail.com");
        return job;
    }

    public static List<Job> getJobList(){
        List<Job> jobs = new ArrayList<>();

        Job job = new Job();
        job.setId(1L);
        job.setJobTitle("Java Developer");
        job.setDescription("Spring Framework");
        job.setCompany("TechAdemy");
        job.setLocation("Noida");
        job.setEmploymentType("PERMANENT");
        job.setSalaryRange("10 LPA");
        job.setSkillsRequired("4+ yr Exp");
        job.setQualifications("CSE");
        job.setContactEmail("random@gmail.com");
        jobs.add(job);

        job = new Job();
        job.setId(2L);
        job.setJobTitle(".NET Developer");
        job.setDescription("C# Framework");
        job.setCompany("TechAdemy");
        job.setLocation("Noida");
        job.setEmploymentType("PERMANENT");
        job.setSalaryRange("10 LPA");
        job.setSkillsRequired("4+ yr Exp");
        job.setQualifications("CSE");
        job.setContactEmail("random1@gmail.com");
        jobs.add(job);

        return  jobs;
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            final String jsonContent = mapper.writeValueAsString(obj);

            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String randomStringWithSize(int size) {
        String s = "";
        for (int i = 0; i < size; i++) {
            s = s + 'A';
        }
        return s;
    }
}
