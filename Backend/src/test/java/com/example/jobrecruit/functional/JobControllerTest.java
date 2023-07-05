package com.example.jobrecruit.functional;

import com.example.jobrecruit.controller.JobController;
import com.example.jobrecruit.dto.JobDTO;
import com.example.jobrecruit.entity.Job;
import com.example.jobrecruit.service.JobService;
import com.example.jobrecruit.utils.MasterData;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
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

import java.util.List;
import java.util.Optional;

import static com.example.jobrecruit.utils.MasterData.getJob;
import static com.example.jobrecruit.utils.MasterData.getJobDTO;
import static com.example.jobrecruit.utils.MasterData.getJobList;
import static com.example.jobrecruit.utils.TestUtils.businessTestFile;
import static com.example.jobrecruit.utils.TestUtils.currentTest;
import static com.example.jobrecruit.utils.TestUtils.testReport;
import static com.example.jobrecruit.utils.TestUtils.yakshaAssert;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@WebMvcTest(JobController.class)
@AutoConfigureMockMvc
public class JobControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    JobService jobService;

    @AfterAll
    public static void afterAll() {
        testReport();
    }

    @Test
    public void testCreateJob() throws Exception {
        JobDTO jobDTO = getJobDTO();
        Job savedJob = getJob();
        when(jobService.createJob(any())).thenReturn(savedJob);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/jobs")
                .content(MasterData.asJsonString(jobDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        yakshaAssert(currentTest(),
                (result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedJob))
                        ? "true"
                        : "false"),
                businessTestFile);

    }

    @Test
    public void testCreateJobIsServiceMethodCalled() throws Exception {
        final int count[] = new int[1];
        JobDTO jobDTO = getJobDTO();
        Job savedJob = getJob();
        when(this.jobService.createJob(any())).then(new Answer<Job>() {

            @Override
            public Job answer(InvocationOnMock invocation) throws Throwable {
                // TODO Auto-generated method stub
                count[0]++;
                return savedJob;
            }
        });
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/jobs")
                .content(MasterData.asJsonString(jobDTO)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);
    }

    @Test
    public void testGetJobById() throws Exception {
        Job savedJob = getJob();
        when(this.jobService.getJobById(savedJob.getId())).thenReturn(Optional.of(savedJob));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/jobs/" + savedJob.getId())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        yakshaAssert(currentTest(),
                (result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedJob)) ? "true"
                        : "false"),
                businessTestFile);

    }

    @Test
    public void testGetJobByIdIsServiceMethodCalled() throws Exception {
        final int count[] = new int[1];
        Job savedJob = getJob();
        when(this.jobService.getJobById(savedJob.getId())).then(new Answer<Optional<Job>>() {

            @Override
            public Optional<Job> answer(InvocationOnMock invocation) throws Throwable {
                // TODO Auto-generated method stub
                count[0]++;
                return Optional.of(savedJob);
            }
        });
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/jobs/" + savedJob.getId())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

    }

    @Test
    public void testGetAllJobs() throws Exception {
        List<Job> jobs = getJobList();

        when(this.jobService.getAllJobs()).thenReturn(jobs);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/jobs")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        yakshaAssert(currentTest(),
                (result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(jobs)) ? "true"
                        : "false"),
                businessTestFile);

    }

    @Test
    public void testGetAllJobsIsServiceMethodCalled() throws Exception {
        final int count[] = new int[1];
        List<Job> jobs = getJobList();

        when(this.jobService.getAllJobs()).then(new Answer<List<Job>>() {

            @Override
            public List<Job> answer(InvocationOnMock invocation) throws Throwable {
                // TODO Auto-generated method stub
                count[0]++;
                return jobs;
            }
        });
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/jobs/")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

    }

    @Test
    public void testUpdateJob() throws Exception {
        Job job = getJob();
        JobDTO jobDTO = getJobDTO();
        job.setId(job.getId());

        when(this.jobService.updateJob(eq(job.getId()), any())).thenReturn(Optional.of(job));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/jobs/" + job.getId())
                .content(MasterData.asJsonString(jobDTO)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        yakshaAssert(currentTest(),
                (result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(job))
                        ? "true"
                        : "false"),
                businessTestFile);

    }

    @Test
    public void testUpdateJobIsServiceMethodCalled() throws Exception {
        final int count[] = new int[1];
        Job job = getJob();
        JobDTO jobDTO = getJobDTO();
        job.setId(job.getId());

        when(this.jobService.updateJob(eq(job.getId()), any())).then(new Answer<Optional<Job>>() {

            @Override
            public Optional<Job> answer(InvocationOnMock invocation) throws Throwable {
                // TODO Auto-generated method stub
                count[0]++;
                return Optional.of(job);
            }
        });
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/jobs/" + job.getId())
                .content(MasterData.asJsonString(jobDTO)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

    }

    @Test
    public void testDeleteJobById() throws Exception {
        Job job = getJob();
        when(this.jobService.deleteJob(job.getId())).thenReturn(true);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/jobs/" + job.getId())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        yakshaAssert(currentTest(),
                (result.getResponse().getContentAsString().contentEquals("") ? "true"
                        : "false"),
                businessTestFile);

    }

    @Test
    public void testDeleteJobByIdIsServiceMethodCalled() throws Exception {
        final int count[] = new int[1];
        Job job = getJob();
        when(this.jobService.deleteJob(job.getId())).then(new Answer<Boolean>() {

            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                // TODO Auto-generated method stub
                count[0]++;
                return true;
            }
        });
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/jobs/" + job.getId())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

    }

    @Test
    public void testGetSearchJobsByJobTitle() throws Exception {
        List<Job> jobs = getJobList();
        String jobTitle = "random";
        when(this.jobService.searchByJobTitle(jobTitle)).thenReturn(jobs);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/jobs/search")
                .queryParam("jobTitle", jobTitle)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        yakshaAssert(currentTest(),
                (result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(jobs)) ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testGetSearchJobsByJobTitleIsServiceMethodCalled() throws Exception {
        final int count[] = new int[1];
        List<Job> jobs = getJobList();
        String jobTitle = "random";
        when(this.jobService.searchByJobTitle(jobTitle)).then(new Answer<List<Job>>() {

            @Override
            public List<Job> answer(InvocationOnMock invocation) throws Throwable {
                // TODO Auto-generated method stub
                count[0]++;
                return jobs;
            }
        });
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/jobs/search")
                .queryParam("jobTitle", jobTitle)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);
    }

    @Test
    public void testGetSearchJobsByCompany() throws Exception {
        List<Job> jobs = getJobList();
        String company = "random";
        when(this.jobService.searchByCompany(company)).thenReturn(jobs);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/jobs/search")
                .queryParam("company", company)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        yakshaAssert(currentTest(),
                (result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(jobs)) ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testGetSearchJobsByCompanyIsServiceMethodCalled() throws Exception {
        final int count[] = new int[1];
        List<Job> jobs = getJobList();
        String company = "company";
        when(this.jobService.searchByCompany(company)).then(new Answer<List<Job>>() {

            @Override
            public List<Job> answer(InvocationOnMock invocation) throws Throwable {
                // TODO Auto-generated method stub
                count[0]++;
                return jobs;
            }
        });
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/jobs/search")
                .queryParam("company", company)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);
    }

    @Test
    public void testGetSearchJobsByLocation() throws Exception {
        List<Job> jobs = getJobList();
        String location = "random";
        when(this.jobService.searchByLocation(location)).thenReturn(jobs);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/jobs/search")
                .queryParam("location", location)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        yakshaAssert(currentTest(),
                (result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(jobs)) ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testGetSearchJobsByLocationIsServiceMethodCalled() throws Exception {
        final int count[] = new int[1];
        List<Job> jobs = getJobList();
        String location = "location";
        when(this.jobService.searchByLocation(location)).then(new Answer<List<Job>>() {

            @Override
            public List<Job> answer(InvocationOnMock invocation) throws Throwable {
                // TODO Auto-generated method stub
                count[0]++;
                return jobs;
            }
        });
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/jobs/search")
                .queryParam("location", location)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);
    }

    @Test
    public void testGetSearchJobsWithNoFilter() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/jobs/search")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        yakshaAssert(currentTest(),
                (result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true"
                        : "false"),
                businessTestFile);
    }

}
