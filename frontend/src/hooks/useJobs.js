// src/hooks/useJobs.js
import { useState, useEffect, useCallback } from "react";
import * as jobService from "../services/jobService";
import { AppStrings, AppConfig } from "../constants/strings";

export const useJobs = (searchQuery = "") => {
  const [jobs, setJobs] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [pagination, setPagination] = useState({
    page: AppConfig.DEFAULT_PAGE,
    size: AppConfig.DEFAULT_PAGE_SIZE,
    totalElements: 0,
    totalPages: 0,
  });

  const fetchJobs = useCallback(async () => {
    try {
      setLoading(true);
      const data = await jobService.getAllJobs({
        search: searchQuery,
        page: pagination.page,
        size: pagination.size,
      });
      setJobs(data.content);
      setPagination((prev) => ({
        ...prev,
        totalElements: data.totalElements,
        totalPages: data.totalPages,
      }));
    } catch (err) {
      console.error("Error fetching jobs:", err);
      setError(AppStrings.loadError);
    } finally {
      setLoading(false);
    }
  }, [searchQuery, pagination.page, pagination.size]);

  useEffect(() => {
    fetchJobs();
  }, [fetchJobs]);

  const addJob = async (jobData) => {
    // Logic removal: Backend handles dateApplied
    await jobService.createJob(jobData);
    await fetchJobs();
  };

  const editJob = async (id, jobData) => {
    await jobService.updateJob(id, jobData);
    await fetchJobs();
  };

  const removeJob = async (id) => {
    if (window.confirm(AppStrings.deleteConfirmation)) {
      try {
        await jobService.deleteJob(id);
        await fetchJobs();
      } catch (err) {
        console.error("Error deleting job:", err);
        alert(AppStrings.deleteFailed);
      }
    }
  };

  const setPage = (page) => {
    setPagination((prev) => ({ ...prev, page }));
  };

  return {
    jobs,
    loading,
    error,
    pagination,
    addJob,
    editJob,
    removeJob,
    fetchJobs,
    setPage,
  };
};
