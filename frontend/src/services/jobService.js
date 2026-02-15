// src/services/jobService.js
import axios from "axios";
import { API_BASE_URL } from "./api";

const API_URL = `${API_BASE_URL}/jobs`;

/**
 * Fetch all jobs.
 * @returns {Promise<Array>} A promise that resolves to an array of jobs.
 */
export const getAllJobs = async () => {
  // Using a large size to fetch all jobs. Proper pagination would be implemented in a real-world scenario.
  const response = await axios.get(`${API_URL}?size=100`);
  return response.data.content;
};

/**
 * Create a new job.
 * @param {object} jobData - The job data to create.
 * @returns {Promise<object>} A promise that resolves to the created job.
 */
export const createJob = async (jobData) => {
  const response = await axios.post(API_URL, jobData);
  return response.data;
};

/**
 * Update an existing job.
 * @param {string} id - The ID of the job to update.
 * @param {object} jobData - The updated job data.
 * @returns {Promise<object>} A promise that resolves to the updated job.
 */
export const updateJob = async (id, jobData) => {
  const response = await axios.put(`${API_URL}/${id}`, jobData);
  return response.data;
};

/**
 * Delete a job by its ID.
 * @param {string} id - The ID of the job to delete.
 * @returns {Promise<void>} A promise that resolves when the job is deleted.
 */
export const deleteJob = async (id) => {
  await axios.delete(`${API_URL}/${id}`);
};
