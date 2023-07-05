package com.example.jobrecruit.exception;

import com.example.jobrecruit.controller.JobController;
import com.example.jobrecruit.dto.JobDTO;
import com.example.jobrecruit.entity.Job;
import com.example.jobrecruit.service.JobService;
import com.example.jobrecruit.utils.MasterData;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static com.example.jobrecruit.utils.MasterData.getJob;
import static com.example.jobrecruit.utils.MasterData.getJobDTO;
import static com.example.jobrecruit.utils.TestUtils.currentTest;
import static com.example.jobrecruit.utils.TestUtils.exceptionTestFile;
import static com.example.jobrecruit.utils.TestUtils.testReport;
import static com.example.jobrecruit.utils.TestUtils.yakshaAssert;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@WebMvcTest(JobController.class)
@AutoConfigureMockMvc
public class JobExceptionTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    JobService jobService;

    @AfterAll
    public static void afterAll() {
        testReport();
    }

    @Test
    public void testCreateJobInvalidDataException() throws Exception {
        JobDTO jobDTO = MasterData.getJobDTO();
        jobDTO.setJobTitle(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/jobs")
                .content(MasterData.asJsonString(jobDTO)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        yakshaAssert(currentTest(),
                (result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
                exceptionTestFile);

    }

    @Test
    public void testUpdateJobInvalidDataException() throws Exception {
        JobDTO jobDTO = MasterData.getJobDTO();
        jobDTO.setJobTitle(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/jobs/" + jobDTO.getId())
                .content(MasterData.asJsonString(jobDTO)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        yakshaAssert(currentTest(),
                (result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
                exceptionTestFile);
    }

    @Test
    public void testGetJobByIdResourceNotFoundException() throws Exception {
        Job job = getJob();
        ErrorResponse exResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                "Job not found with ID: " + job.getId());

        when(this.jobService.getJobById(job.getId())).thenReturn(Optional.empty());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/jobs/" + job.getId())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        yakshaAssert(currentTest(),
                (result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
                exceptionTestFile);
    }

    @Test
    public void testUpdateJobByIdResourceNotFoundException() throws Exception {
        Job job = getJob();
        JobDTO jobDTO = getJobDTO();
        ErrorResponse exResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                "Job not found with ID: " + job.getId());

        when(this.jobService.updateJob(eq(job.getId()), any())).thenReturn(Optional.empty());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/jobs/" + job.getId())
                .content(MasterData.asJsonString(jobDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        yakshaAssert(currentTest(),
                (result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
                exceptionTestFile);

    }

    @Test
    public void testDeleteJobByIdResourceNotFoundException() throws Exception {
        Job job = getJob();
        ErrorResponse exResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                "Job not found with ID: " + job.getId());
        when(this.jobService.deleteJob(job.getId())).thenReturn(false);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/jobs/" + job.getId())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        yakshaAssert(currentTest(),
                (result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
                exceptionTestFile);

    }

}
