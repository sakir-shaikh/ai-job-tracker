// src/components/JobTable.jsx
import { Table, Badge, Group, Button } from "@mantine/core";
import { getStatusColor } from "../utils/styleHelpers";
import { AppStrings } from "../constants/strings";

function JobTable({ jobs, onEdit, onDelete, search }) {
  const filteredJobs = jobs.filter((job) => job.company.toLowerCase().includes(search.toLowerCase()));
  return (
    <Table striped highlightOnHover withTableBorder>
      <Table.Thead>
        <Table.Tr>
          <Table.Th>{AppStrings.company}</Table.Th>
          <Table.Th>{AppStrings.title}</Table.Th>
          <Table.Th>{AppStrings.status}</Table.Th>
          <Table.Th>{AppStrings.location}</Table.Th>
          <Table.Th>{AppStrings.dateApplied}</Table.Th>
          <Table.Th>{AppStrings.actions}</Table.Th>
        </Table.Tr>
      </Table.Thead>
      <Table.Tbody>
        {filteredJobs.map((job) => (
          <Table.Tr key={job.id}>
            <Table.Td style={{ fontWeight: 500 }}>{job.company}</Table.Td>
            <Table.Td>{job.title}</Table.Td>
            <Table.Td>
              <Badge color={getStatusColor(job.status)} variant="light">
                {job.status}
              </Badge>
            </Table.Td>
            <Table.Td>{job.location}</Table.Td>
            <Table.Td>{job.dateApplied}</Table.Td>
            <Table.Td>
              <Group gap="xs">
                <Button
                  variant="subtle"
                  size="xs"
                  onClick={() => onEdit(job)}
                >
                  {AppStrings.editAction}
                </Button>
                <Button
                  variant="subtle"
                  color="red"
                  size="xs"
                  onClick={() => onDelete(job.id)}
                >
                  {AppStrings.deleteAction}
                </Button>
              </Group>
            </Table.Td>
          </Table.Tr>
        ))}
      </Table.Tbody>
    </Table>
  );
}

export default JobTable;
