import {
  Modal,
  Button,
  TextInput,
  Select,
  Group,
  Textarea,
  Notification,
} from "@mantine/core";
import { useForm } from "@mantine/form";
import { useEffect } from "react";
import {
  JobFormStrings,
  LocationOptions,
  StatusOptions,
} from "../constants/strings";

function JobForm({ opened, close, onSubmit, job, loading, error, setError }) {
  const form = useForm({
    initialValues: {
      company: "",
      title: "",
      status: "APPLIED",
      location: "REMOTE",
      description: "",
      jobLink: "",
    },
    validate: {
      company: (value) =>
        value.length < 1 ? JobFormStrings.companyRequired : null,
      title: (value) => (value.length < 1 ? JobFormStrings.titleRequired : null),
      location: (value) =>
        value.length < 1 ? JobFormStrings.locationRequired : null,
    },
  });

  useEffect(() => {
    if (job) {
      form.setValues({
        company: job.company,
        title: job.title,
        status: job.status,
        location: job.location,
        description: job.description || "",
        jobLink: job.jobLink || "",
      });
    } else {
      form.reset();
    }
  }, [job, opened]); // Depend on 'opened' to reset form when modal is re-opened for 'Add'

  const handleSubmit = (values) => {
    onSubmit(values);
  };

  return (
    <>
      {error && (
        <Notification color="red" onClose={() => setError("")}>
          {error}
        </Notification>
      )}
      <Modal
        opened={opened}
        onClose={close}
        title={job ? JobFormStrings.editJobTitle : JobFormStrings.addJobTitle}
        centered
      >
        <form onSubmit={form.onSubmit(handleSubmit)}>
          <TextInput
            label={JobFormStrings.companyLabel}
            placeholder={JobFormStrings.companyPlaceholder}
            withAsterisk
            {...form.getInputProps("company")}
            mb="sm"
          />
          <TextInput
            label={JobFormStrings.jobTitleLabel}
            placeholder={JobFormStrings.jobTitlePlaceholder}
            withAsterisk
            {...form.getInputProps("title")}
            mb="sm"
          />
          <Select
            label={JobFormStrings.locationLabel}
            data={LocationOptions}
            withAsterisk
            {...form.getInputProps("location")}
            mb="sm"
          />

          <Select
            label={JobFormStrings.statusLabel}
            data={StatusOptions}
            {...form.getInputProps("status")}
            mb="sm"
          />

          <TextInput
            label={JobFormStrings.jobLinkLabel}
            placeholder={JobFormStrings.jobLinkPlaceholder}
            {...form.getInputProps("jobLink")}
            mb="sm"
          />
          <Textarea
            label={JobFormStrings.descriptionLabel}
            placeholder={JobFormStrings.descriptionPlaceholder}
            {...form.getInputProps("description")}
            mb="md"
          />

          <Group position="right">
            <Button type="submit" loading={loading}>
              {job ? JobFormStrings.updateButton : JobFormStrings.saveButton}
            </Button>
          </Group>
        </form>
      </Modal>
    </>
  );
}

export default JobForm;
