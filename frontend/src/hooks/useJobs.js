// src/hooks/useJobs.js
import { useState, useEffect, useCallback } from "react";
import * as jobService from "../services/jobService";
import { AppStrings } from "../constants/strings";

export const useJobs = () => {
  const [jobs, setJobs] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const fetchJobs = useCallback(async () => {
    try {
      setLoading(true);
      const jobsData = await jobService.getAllJobs();
      setJobs(jobsData);
    } catch (err) {
      console.error("Error fetching jobs:", err);
      setError(AppStrings.loadError);
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    fetchJobs();
  }, [fetchJobs]);

  const addJob = async (jobData) => {
    const dateApplied = new Date().toISOString().split("T")[0];
    const newJob = { ...jobData, dateApplied };
    await jobService.createJob(newJob);
    await fetchJobs(); // Refresh the job list
  };

  const editJob = async (id, jobData) => {
    const dateApplied = new Date().toISOString().split("T")[0];
    const updatedJob = { ...jobData, dateApplied };
    await jobService.updateJob(id, updatedJob);
    await fetchJobs(); // Refresh the job list
  };

  const removeJob = async (id) => {
    if (window.confirm(AppStrings.deleteConfirmation)) {
      try {
        await jobService.deleteJob(id);
        await fetchJobs(); // Refresh the job list
      } catch (err) {
        console.error("Error deleting job:", err);
        alert(AppStrings.deleteFailed);
      }
    }
  };

  return {
    jobs,
    loading,
    error,
    addJob,
    editJob,
    removeJob,
    fetchJobs, // Exposing fetchJobs in case of manual refresh is needed
  };
};
