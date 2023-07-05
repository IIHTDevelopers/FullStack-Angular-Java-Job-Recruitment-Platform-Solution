import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { JobService } from '../../service/job.service';
import { Job } from 'src/app/model/job';
@Component({
  selector: 'app-job',
  templateUrl: './job.component.html',
  styleUrls: ['./job.component.css']
})
export class JobComponent implements OnInit {
  jobForm!: FormGroup;
  jobs!: Job[];
  selectedJob!: Job;
  isEditing: boolean = false;
  selectedJobs!: Job[];
  searchForm!: FormGroup;
  constructor(private formBuilder: FormBuilder, private jobService: JobService) { }

  ngOnInit(): void {
    this.jobForm = this.formBuilder.group({
      jobTitle: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(50)]],
      description: ['', Validators.required],
      company: ['', Validators.required],
      location: ['', Validators.required],
      employmentType: ['', Validators.required],
      salaryRange: [''],
      skillsRequired: [''],
      qualifications: [''],
      contactEmail: ['', Validators.required]
    });

    this.searchForm = this.formBuilder.group({
      jobTitle: [''],
      company: [''],
      location: ['']
    });
    this.loadJobs();
  }

  loadJobs(): void {
    this.jobService.getAllJobs().subscribe(
      jobs => {
        this.jobs = jobs;
      }
      // ,
      // error => {
      //   console.log('Error loading jobs:', error);
      // }
    );
  }

  createJob(): void {
    if (this.jobForm.invalid) {
      return;
    }

    const newJob: Job = this.jobForm.value;
    this.jobService.createJob(newJob).subscribe(
      job => {
        this.jobs.push(job);
        this.resetForm();
      }
      // ,
      // error => {
      //   console.log('Error creating job:', error);
      // }
    );
  }
  // //not using in this app
  getJobById(id: number): void {
    this.jobService.getJobById(id).subscribe(
      (j: Job) => {
        //console.log('Product:', j);
      }
      // ,
      // (error) => {
      //   console.error('Error:', error);
      // }
    );
  }

  editJob(job: Job): void {
    this.selectedJob = job;
    this.jobForm.patchValue(job);//arranging values into the form to edit
    this.isEditing = true;
  }

  updateJob(): void {
    if (this.jobForm.invalid) {
      return;
    }

    const updatedJob: Job = this.jobForm.value;
    this.jobService.updateJob(this.selectedJob.id, updatedJob).subscribe(
      job => {
        const index = this.jobs.findIndex(j => j.id === job.id);
        if (index !== -1) {
          this.jobs[index] = job;
          this.resetForm();
        }
      }
      // ,
      // error => {
      //   console.log('Error updating job:', error);
      // }
    );
  }

  deleteJob(job: Job): void {
    //if (confirm('Are you sure you want to delete this job?')) {
    this.jobService.deleteJob(job.id).subscribe(
      () => {
        const index = this.jobs.findIndex(j => j.id === job.id);
        if (index !== -1) {
          this.jobs.splice(index, 1);
        }
      }
      // ,
      // error => {
      //   console.log('Error deleting job:', error);
      // }
    );
    // }
  }

  searchJobs(): void {
    if (this.searchForm.invalid) {
      return;
    }

    // const searchParams = this.searchForm.value.jobTitle;
    // const searchParams = this.searchForm.value;


    const searchQuery: any = {};

    if (this.searchForm.value.jobTitle) {
      searchQuery.jobTitle = this.searchForm.value.jobTitle;
    }

    if (this.searchForm.value.company) {
      searchQuery.company = this.searchForm.value.company;
    }

    if (this.searchForm.value.location) {
      searchQuery.location = this.searchForm.value.location;
    }

    this.jobService.searchJobs(searchQuery).subscribe(
      jobs => {
        // this.jobs = jobs;
        this.selectedJobs = jobs;
      }
      // ,
      // error => {
      //   console.log('Error searching jobs:', error);
      // }
    );
  }

  resetForm(): void {
    this.jobForm.reset();
    // this.selectedJob = null;
    this.isEditing = false;//show Create Job button again
  }
}
