// src/utils/styleHelpers.js
import { JobStatus } from "../constants/strings";

/**
 * Returns a color based on the job status.
 * @param {string} status - The job status.
 * @returns {string} The color for the status.
 */
export const getStatusColor = (status) => {
  switch (status) {
    case JobStatus.APPLIED:
      return "blue";
    case JobStatus.INTERVIEWING:
      return "yellow";
    case JobStatus.REJECTED:
      return "red";
    case JobStatus.OFFER:
      return "green";
    default:
      return "gray";
  }
};
