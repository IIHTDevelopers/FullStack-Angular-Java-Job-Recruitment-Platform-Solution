import { ComponentFixture, TestBed } from "@angular/core/testing";
import { FormBuilder, FormsModule, ReactiveFormsModule } from "@angular/forms";
import { JobComponent } from "./job.component";
import { JobService } from "../../service/job.service";
import { HttpClientModule } from "@angular/common/http";
import {
  HttpClientTestingModule,
  HttpTestingController,
} from "@angular/common/http/testing";
import { Job } from "src/app/model/job";
import { of } from 'rxjs';

describe("JobComponent", () => {
  let component: JobComponent;
  let fixture: ComponentFixture<JobComponent>;
  let serviceMock: any;
  let productService: JobService;
  const job: Job = {
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

  let mockService = {
    getAllJobs: () => {
      return of([job]);
    },
    getJobById: () => {
      return of([job]);
    },
    deleteJob: (id: number | string) => {
      return of(job);
    },
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [JobComponent],
      imports: [
        FormsModule,
        ReactiveFormsModule,
        HttpClientModule,
        HttpClientTestingModule,
      ],
      providers: [
        FormBuilder,
        JobService,
        HttpTestingController,
        { provide: JobService, useValue: mockService },
      ],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(JobComponent);
    component = fixture.componentInstance;
    productService = TestBed.inject(JobService);
    fixture.detectChanges();
  });

  describe("business", () => {
    it("should create the job component", () => {
      expect(component).toBeTruthy();
    });
    it("should declare jobForm & searchForm", () => {
      expect(component.jobForm).toBeDefined();
      expect(component.searchForm).toBeDefined();
    });
    it("should initialize the jobForm", () => {
      const jobForm = {
        jobTitle: "",
        description: "",
        company: "",
        location: "",
        employmentType: "",
        salaryRange: "",
        skillsRequired: "",
        qualifications: "",
        contactEmail: "",
      };
      expect(component.jobForm.value).toEqual(jobForm);
    });
    it("should initialize the searchForm", () => {
      const searchForm = {
        jobTitle: "",
        company: "",
        location: "",
      };
      expect(component.searchForm.value).toEqual(searchForm);
    });
    it("should validate the jobTitle field in the jobForm", () => {
      const c = component.jobForm.controls["jobTitle"];
      c.setValue("Developer");
      expect(c.valid).toBeTruthy();
      c.setValue("");
      expect(c.invalid).toBeTruthy();
      c.setValue("De");
      expect(c.invalid).toBeTruthy();
    });
    it("should validate the description field in the jobForm", () => {
      const c = component.jobForm.controls["description"];
      c.setValue("Python Developer");
      expect(c.valid).toBeTruthy();
      c.setValue("");
      expect(c.invalid).toBeTruthy();
    });
    it("should validate the company field in the jobForm", () => {
      const c = component.jobForm.controls["company"];
      c.setValue("Wipro");
      expect(c.valid).toBeTruthy();
      c.setValue("");
      expect(c.invalid).toBeTruthy();
    });
    it("should validate the location field in the jobForm", () => {
      const c = component.jobForm.controls["location"];
      c.setValue("Hyderabad");
      expect(c.valid).toBeTruthy();
      c.setValue("");
      expect(c.invalid).toBeTruthy();
    });
    it("should validate the employmentType field in the jobForm", () => {
      const c = component.jobForm.controls["employmentType"];
      c.setValue("Permanent");
      expect(c.valid).toBeTruthy();
      c.setValue("");
      expect(c.invalid).toBeTruthy();
    });
    it("should validate the contactEmail field in the jobForm", () => {
      const c = component.jobForm.controls["contactEmail"];
      c.setValue("wipro@gmail.com");
      expect(c.valid).toBeTruthy();
      c.setValue("");
      expect(c.invalid).toBeTruthy();
    });

    it("should validate the salaryRange field in the jobForm", () => {
      const c = component.jobForm.controls["salaryRange"];
      c.setValue("8-10 LPA");
      expect(c.valid).toBeTruthy();
      c.setValue("");
      expect(c.valid).toBeTruthy();
    });
    it("should validate the skillsRequired field in the jobForm", () => {
      const c = component.jobForm.controls["skillsRequired"];
      c.setValue("wipro@gmail.com");
      expect(c.valid).toBeTruthy();
      c.setValue("");
      expect(c.valid).toBeTruthy();
    });
    it("should validate the qualifications field in the jobForm", () => {
      const c = component.jobForm.controls["qualifications"];
      c.setValue("B.Tech");
      expect(c.valid).toBeTruthy();
      c.setValue("");
      expect(c.valid).toBeTruthy();
    });
  });

  describe("boundary", () => {
    it("should invalidate the jobForm when jobTitle length is greater than 50", () => {
      const form = component.jobForm;
      form.controls["jobTitle"].setValue(
        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa  bbbb ccccccccccc   ddddddddddddddd eeeee ffffffffffffffffffffff gggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg"
      );
      expect(form.invalid).toBeTruthy();
      expect(form.controls["jobTitle"].errors?.["maxlength"]).toBeTruthy();
    });
    it("should invalidate the jobForm when jobTitle length is less than 3", () => {
      const form = component.jobForm;
      form.controls["jobTitle"].setValue("De");
      expect(form.invalid).toBeTruthy();
      expect(form.controls["jobTitle"].errors?.["minlength"]).toBeTruthy();
    });

    it("should validate the jobForm on valid values to all fields", () => {
      component.jobForm.controls["jobTitle"].setValue("Developer");
      component.jobForm.controls["description"].setValue("Python Developer");
      component.jobForm.controls["company"].setValue("Wipro");
      component.jobForm.controls["location"].setValue("Hyderabad");
      component.jobForm.controls["employmentType"].setValue("Permanent");
      component.jobForm.controls["salaryRange"].setValue("8-12 LPA");
      component.jobForm.controls["skillsRequired"].setValue("Python,Django");
      component.jobForm.controls["qualifications"].setValue("B.Tech");
      component.jobForm.controls["contactEmail"].setValue("wipro@gmail.com");
      expect(component.jobForm.valid).toBeTruthy();
    });
    it("should disable the submit button when the jobForm is invalid", () => {
      component.jobForm.controls["jobTitle"].setValue("");
      component.jobForm.controls["description"].setValue("Python Developer");
      component.jobForm.controls["company"].setValue("Wipro");
      component.jobForm.controls["location"].setValue("Hyderabad");
      component.jobForm.controls["employmentType"].setValue("Permanent");
      component.jobForm.controls["salaryRange"].setValue("8-12 LPA");
      component.jobForm.controls["skillsRequired"].setValue("Python,Django");
      component.jobForm.controls["qualifications"].setValue("B.Tech");
      component.jobForm.controls["contactEmail"].setValue("wipro@gmail.com");
      fixture.detectChanges();
      const submitButton = fixture.nativeElement.querySelector(
        'button[type="submit"]'
      );
      expect(submitButton.disabled).toBe(true);
    });
    it("should enable the submit button when the jobForm is valid", () => {
      component.jobForm.controls["jobTitle"].setValue("Developer");
      component.jobForm.controls["description"].setValue("Python Developer");
      component.jobForm.controls["company"].setValue("Wipro");
      component.jobForm.controls["location"].setValue("Hyderabad");
      component.jobForm.controls["employmentType"].setValue("Permanent");
      component.jobForm.controls["salaryRange"].setValue("8-12 LPA");
      component.jobForm.controls["skillsRequired"].setValue("Python,Django");
      component.jobForm.controls["qualifications"].setValue("B.Tech");
      component.jobForm.controls["contactEmail"].setValue("wipro@gmail.com");
      fixture.detectChanges();
      const submitButton = fixture.nativeElement.querySelector(
        'button[type="submit"]'
      );
      expect(submitButton.disabled).toBe(false);
    });
  });

  describe("exception", () => {
    it("should invalidate the jobForm when empty", () => {
      component.jobForm.controls["jobTitle"].setValue("");
      component.jobForm.controls["description"].setValue("");
      component.jobForm.controls["company"].setValue("");
      component.jobForm.controls["location"].setValue("");
      component.jobForm.controls["employmentType"].setValue("");
      component.jobForm.controls["salaryRange"].setValue("");
      component.jobForm.controls["skillsRequired"].setValue("");
      component.jobForm.controls["qualifications"].setValue("");
      component.jobForm.controls["contactEmail"].setValue("");
      expect(component.jobForm.valid).toBeFalsy();
    });
    it("jobTitle field should show required error when no value passed", () => {
      const c = component.jobForm.controls["jobTitle"];
      expect(c.valid).toBeFalsy();
      c.setValue("");
      expect(c.hasError("required")).toBeTruthy();
    });
    it("description field should show required error when no value passed", () => {
      const c = component.jobForm.controls["description"];
      expect(c.valid).toBeFalsy();
      c.setValue("");
      expect(c.hasError("required")).toBeTruthy();
    });
    it("company field should show required error when no value passed", () => {
      const c = component.jobForm.controls["company"];
      expect(c.valid).toBeFalsy();
      c.setValue("");
      expect(c.hasError("required")).toBeTruthy();
    });
    it("location field should show required error when no value passed", () => {
      const c = component.jobForm.controls["location"];
      expect(c.valid).toBeFalsy();
      c.setValue("");
      expect(c.hasError("required")).toBeTruthy();
    });

    it("employmentType field should show required error when no value passed", () => {
      const c = component.jobForm.controls["employmentType"];
      expect(c.valid).toBeFalsy();
      c.setValue("");
      expect(c.hasError("required")).toBeTruthy();
    });
    it("contactEmail field should show required error when no value passed", () => {
      const c = component.jobForm.controls["contactEmail"];
      expect(c.valid).toBeFalsy();
      c.setValue("");
      expect(c.hasError("required")).toBeTruthy();
    });

    it("salaryRange field should not show required error when no value passed", () => {
      const c = component.jobForm.controls["salaryRange"];
      expect(c.valid).toBeTruthy();
      c.setValue("");
      expect(c.hasError("required")).toBeFalsy();
    });
    it("skillsRequired field should not show required error when no value passed", () => {
      const c = component.jobForm.controls["skillsRequired"];
      expect(c.valid).toBeTruthy();
      c.setValue("");
      expect(c.hasError("required")).toBeFalsy();
    });
    it("qualifications field should not show required error when no value passed", () => {
      const c = component.jobForm.controls["qualifications"];
      expect(c.valid).toBeTruthy();
      c.setValue("");
      expect(c.hasError("required")).toBeFalsy();
    });

  });

  describe("business", () => {
    it('should get all properties', () => {
      component.jobs = [];
      jest.spyOn(mockService, 'getAllJobs').mockReturnValue(of([job]));
      component.loadJobs();
      expect(mockService.getAllJobs).toBeCalledTimes(1);
      expect(component.jobs.length).toBeGreaterThan(0);
      expect(Array.isArray(component.jobs)).toBe(true);
    })
    it('should get job by id', () => {
      jest.spyOn(mockService, 'getJobById')
      component.getJobById(1);
      expect(mockService.getJobById).toBeCalledTimes(1);
      expect(mockService.getJobById).toBeCalledWith(1);
    })
    it('should delete job by id', () => {
      const job: Job = {
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
      jest.spyOn(mockService, 'deleteJob').mockReturnValue(of(job));
      component.deleteJob(job);
      expect(mockService.deleteJob).toBeCalledTimes(1);
      expect(mockService.deleteJob).toBeCalledWith(1);
    })
  });
});







