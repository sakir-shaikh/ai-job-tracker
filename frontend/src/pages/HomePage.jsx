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
  Pagination,
} from "@mantine/core";
import { useDisclosure, useDebouncedValue } from "@mantine/hooks";
import JobTable from "../components/JobTable";
import JobForm from "../components/JobForm";
import { useJobs } from "../hooks/useJobs";
import { AppStrings, JobFormStrings, AppConfig } from "../constants/strings";
import Stats from "./Stats";

function HomePage() {
  const [search, setSearch] = useState("");
  const [debouncedSearch] = useDebouncedValue(search, AppConfig.SEARCH_DEBOUNCE_MS);
  const { jobs, loading, error, pagination, addJob, editJob, removeJob, setPage } = useJobs(debouncedSearch);
  const [opened, { open, close }] = useDisclosure(false);
  const [selectedJob, setSelectedJob] = useState(null);
  const [formError, setFormError] = useState("");
  const [formLoading, setFormLoading] = useState(false);

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
      <Group justify="space-between" mb="lg">
        <Title order={1}>{AppStrings.appTitle}</Title>
        <Group>
          <TextInput
            placeholder="Search by Company or Title..."
            value={search}
            onChange={(event) => {
              setSearch(event.currentTarget.value);
              setPage(AppConfig.DEFAULT_PAGE); // Reset to first page on search
            }}
          />
          <Button onClick={handleAddClick}>{AppStrings.addJobButton}</Button>
        </Group>
      </Group>

      {error && (
        <Alert color="red" mb="md">
          {error}
        </Alert>
      )}

      {loading ? (
        <Group justify="center" my="xl">
           <Loader />
        </Group>
      ) : (
        <>
          <JobTable
            jobs={jobs}
            onEdit={handleEditClick}
            onDelete={removeJob}
          />
          <Group justify="center" mt="xl">
            <Pagination 
              value={pagination.page + 1} 
              onChange={(p) => setPage(p - 1)} 
              total={pagination.totalPages} 
            />
          </Group>
        </>
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
