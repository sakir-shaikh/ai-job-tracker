
export const AppStrings = {
  // App Titles and Headers
  appTitle: "🚀 My AI Job Tracker",
  addJobButton: "+ Add Job",

  // Job Table Headers
  company: "Company",
  title: "Title",
  status: "Status",
  location: "Location",
  dateApplied: "Date Applied",
  actions: "Actions",

  // Alerts and Confirmations
  deleteConfirmation: "Are you sure you want to delete this job? This cannot be undone.",
  deleteFailed: "Failed to delete job.",
  loadError: "Could not load jobs. Is the Backend running?",

  // Job Actions
  editAction: "Edit ✏️",
  deleteAction: "Delete 🗑️",
};

export const JobFormStrings = {
  // Modal Titles
  addJobTitle: "Add New Job",
  editJobTitle: "Edit Job",

  // Form Labels
  companyLabel: "Company",
  jobTitleLabel: "Job Title",
  locationLabel: "Location",
  statusLabel: "Status",
  jobLinkLabel: "Job Link",
  descriptionLabel: "Description",

  // Form Placeholders
  companyPlaceholder: "Google",
  jobTitlePlaceholder: "Backend Engineer",
  jobLinkPlaceholder: "https://...",
  descriptionPlaceholder: "Notes...",

  // Form Validation
  companyRequired: "Company is required",
  titleRequired: "Title is required",
  locationRequired: "Location is required",

  // Buttons
  saveButton: "Save Job",
  updateButton: "Update Job",

  // Notifications
  saveError: "Failed to save job. Please try again.",
};

export const JobStatus = {
  APPLIED: "APPLIED",
  INTERVIEWING: "INTERVIEWING",
  OFFER: "OFFER",
  REJECTED: "REJECTED",
};

export const LocationOptions = ["REMOTE", "ON-SITE", "HYBRID"];
export const StatusOptions = [
  JobStatus.APPLIED,
  JobStatus.INTERVIEWING,
  JobStatus.OFFER,
  JobStatus.REJECTED,
];

export const StatsStrings = {
  totalApplications: "Total Applications",
  interviews: "Interviews",
  offers: "Offers",
};