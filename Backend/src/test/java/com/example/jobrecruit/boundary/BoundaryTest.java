package com.example.jobrecruit.boundary;

import com.example.jobrecruit.dto.JobDTO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static com.example.jobrecruit.utils.MasterData.getJobDTO;
import static com.example.jobrecruit.utils.MasterData.randomStringWithSize;
import static com.example.jobrecruit.utils.TestUtils.boundaryTestFile;
import static com.example.jobrecruit.utils.TestUtils.currentTest;
import static com.example.jobrecruit.utils.TestUtils.testReport;
import static com.example.jobrecruit.utils.TestUtils.yakshaAssert;

@ExtendWith(SpringExtension.class)
public class BoundaryTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterAll
    public static void afterAll() {
        testReport();
    }

    @Test
    public void testJobTitleNotNull() throws Exception {
        JobDTO jobDTO = getJobDTO();
        jobDTO.setJobTitle(null);
        Set<ConstraintViolation<JobDTO>> violations = validator.validate(jobDTO);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testJobTitleMinThree() throws Exception {
        JobDTO jobDTO = getJobDTO();
        jobDTO.setJobTitle(randomStringWithSize(2));
        Set<ConstraintViolation<JobDTO>> violations = validator.validate(jobDTO);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testJobTitleMaxFifty() throws Exception {
        JobDTO jobDTO = getJobDTO();
        jobDTO.setJobTitle(randomStringWithSize(51));
        Set<ConstraintViolation<JobDTO>> violations = validator.validate(jobDTO);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testDescriptionNotNull() throws Exception {
        JobDTO jobDTO = getJobDTO();
        jobDTO.setDescription(null);
        Set<ConstraintViolation<JobDTO>> violations = validator.validate(jobDTO);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testCompanyNotNull() throws Exception {
        JobDTO jobDTO = getJobDTO();
        jobDTO.setCompany(null);
        Set<ConstraintViolation<JobDTO>> violations = validator.validate(jobDTO);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testLocationNotNull() throws Exception {
        JobDTO jobDTO = getJobDTO();
        jobDTO.setLocation(null);
        Set<ConstraintViolation<JobDTO>> violations = validator.validate(jobDTO);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testEmploymentTypeNotNull() throws Exception {
        JobDTO jobDTO = getJobDTO();
        jobDTO.setEmploymentType(null);
        Set<ConstraintViolation<JobDTO>> violations = validator.validate(jobDTO);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testContactEmailNotNull() throws Exception {
        JobDTO jobDTO = getJobDTO();
        jobDTO.setContactEmail(null);
        Set<ConstraintViolation<JobDTO>> violations = validator.validate(jobDTO);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testContactEmailValidFormat() throws Exception {
        JobDTO jobDTO = getJobDTO();
        jobDTO.setContactEmail("abc");
        Set<ConstraintViolation<JobDTO>> violations = validator.validate(jobDTO);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

}
