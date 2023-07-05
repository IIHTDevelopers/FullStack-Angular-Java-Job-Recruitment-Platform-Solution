import { of } from 'rxjs';
import { JobService } from './job.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
describe('JobService', () => {
  let service: JobService;
  let httpClientSpy: any;
  beforeEach(() => {
    httpClientSpy = {
      get: jest.fn(),
      post: jest.fn(),
      put: jest.fn(),
      delete: jest.fn(),
    };
    service = new JobService(httpClientSpy);
  });

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [JobService]
    });
  });

  describe('business', () => {
    it('job service should be created', () => {
      expect(service).toBeTruthy();
    });

    it('should get all jobs by calling getAllJobs in service', () => {
      const res = 'some message';
      const url = 'http://127.0.0.1:8081/jobrecruitplatform/jobs';
      jest.spyOn(httpClientSpy, 'get').mockReturnValue(of(res));
      service.getAllJobs();
      expect(httpClientSpy.get).toHaveBeenCalledWith(url);
    });

    it('should get a job by calling getJobById in service', () => {
      const res = 'some message';
      const url = 'http://127.0.0.1:8081/jobrecruitplatform/jobs/1';
      jest.spyOn(httpClientSpy, 'get').mockReturnValue(of(res));
      service.getJobById(1);
      expect(httpClientSpy.get).toHaveBeenCalledWith(url);
    });


    it('should create the job by calling createJob in service', () => {
      const data = {
        id: 1,
        jobTitle: "Developer",
        description: "Python Full Stack Developer",
        company: "Wipro",
        location: "Hyderabad",
        employmentType: "Permanent",
        salaryRange: "10-12 LPA",
        skillsRequired: "Python ,Django",
        qualifications: "B.Tech",
        contactEmail: "wipro@gmail.com"
      };
      const res = 'some message';
      const url = 'http://127.0.0.1:8081/jobrecruitplatform/jobs';
      jest.spyOn(httpClientSpy, 'post').mockReturnValue(of(res));
      service.createJob(data);
      expect(httpClientSpy.post).toHaveBeenCalledWith(url, data);
    });
    it('should update the job by calling updateJob in service', () => {
      const data = {
        id: 1,
        jobTitle: "Developer",
        description: "Python Full Stack Developer",
        company: "Wipro",
        location: "Hyderabad",
        employmentType: "Permanent",
        salaryRange: "10-12 LPA",
        skillsRequired: "Python ,Django",
        qualifications: "B.Tech",
        contactEmail: "wipro@gmail.com"
      };
      const res = 'some message';
      const url = 'http://127.0.0.1:8081/jobrecruitplatform/jobs/1';
      jest.spyOn(httpClientSpy, 'put').mockReturnValue(of(res));
      service.updateJob(data.id, data);
      expect(httpClientSpy.put).toHaveBeenCalledWith(url, data);
    });
    it('should delete the job calling deleteJob() in service', () => {
      const res = 'some message';
      const API_URL = 'http://127.0.0.1:8081/jobrecruitplatform/jobs/1';
      jest.spyOn(httpClientSpy, 'delete').mockReturnValue(of(res));
      service.deleteJob(1);
      expect(httpClientSpy.delete).toHaveBeenCalledWith(API_URL);
    });
    it('should search the job with job title by calling searchJobs in service', () => {
      const res = 'some message';
      const API_URL = 'http://127.0.0.1:8081/jobrecruitplatform/jobs';
      jest.spyOn(httpClientSpy, 'get').mockReturnValue(of(res));
      service.searchJobs('Developer');
      expect(httpClientSpy.get).toHaveBeenCalledWith(API_URL + '/search', { params:'Developer' });
    });
    it('should search the job with company by calling searchJobs in service', () => {
      const res = 'some message';
      const API_URL = 'http://127.0.0.1:8081/jobrecruitplatform/jobs/search';
      jest.spyOn(httpClientSpy, 'get').mockReturnValue(of(res));
      service.searchJobs("Wipro");
      expect(httpClientSpy.get).toHaveBeenCalledWith(API_URL, { params:"Wipro" });
    });
    it('should search the job with location by calling searchJobs in service', () => {
      const res = 'some message';
      const API_URL = 'http://127.0.0.1:8081/jobrecruitplatform/jobs';
      jest.spyOn(httpClientSpy, 'get').mockReturnValue(of(res));
      service.searchJobs('Hyderabad');
      expect(httpClientSpy.get).toHaveBeenCalledWith(API_URL + '/search', { params :'Hyderabad'});
    });
  });
});


