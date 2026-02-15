// src/pages/HomePage.jsx
import { useState } from "react";
import {
  Container,
  Title,
  Group,
  Button,
  Loader,
  Alert,
  TextInput,
} from "@mantine/core";
import { useDisclosure } from "@mantine/hooks";
import JobTable from "../components/JobTable";
import JobForm from "../components/JobForm";
import { useJobs } from "../hooks/useJobs";
import { AppStrings, JobFormStrings } from "../constants/strings";
import Stats from "./Stats";

function HomePage() {
  const { jobs, loading, error, addJob, editJob, removeJob } = useJobs();
  const [opened, { open, close }] = useDisclosure(false);
  const [selectedJob, setSelectedJob] = useState(null);
  const [formError, setFormError] = useState("");
  const [formLoading, setFormLoading] = useState(false);
  const [search, setSearch] = useState("");

  const handleAddClick = () => {
    setSelectedJob(null);
    open();
  };

  const handleEditClick = (job) => {
    setSelectedJob(job);
    open();
  };

  const handleFormSubmit = async (values) => {
    setFormLoading(true);
    setFormError("");
    try {
      if (selectedJob) {
        await editJob(selectedJob.id, values);
      } else {
        await addJob(values);
      }
      close();
    } catch (err) {
      console.error("Error saving job:", err);
      setFormError(JobFormStrings.saveError);
    } finally {
      setFormLoading(false);
    }
  };

  return (
    <Container size="lg" py="xl">
      <Stats jobs={jobs} />
      <Group position="apart" mb="lg">
        <Title order={1}>{AppStrings.appTitle}</Title>
        <Button onClick={handleAddClick}>{AppStrings.addJobButton}</Button>
    
        <TextInput
          placeholder="Search by Company..."
          mb="md"
          icon="🔍"
          value={search}
          onChange={(event) => setSearch(event.currentTarget.value)}
        />
      </Group>

      {error && (
        <Alert color="red" mb="md">
          {error}
        </Alert>
      )}

      {loading ? (
        <Loader />
      ) : (
        <JobTable
          jobs={jobs}
          onEdit={handleEditClick}
          onDelete={removeJob}
          search={search}
        />
      )}
      <JobForm
        opened={opened}
        close={close}
        onSubmit={handleFormSubmit}
        job={selectedJob}
        loading={formLoading}
        error={formError}
        setError={setFormError}
      />
    </Container>
  );
}

export default HomePage;
