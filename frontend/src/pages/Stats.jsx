import { SimpleGrid, Card, Text } from "@mantine/core";
import { StatsStrings, AppConfig, JobStatus } from "../constants/strings";

function Stats({ jobs }) {
  const total = jobs.length;
  const interviews = jobs.filter(
    (j) => j.status === JobStatus.INTERVIEWING,
  ).length;
  const offers = jobs.filter((j) => j.status === JobStatus.OFFER).length;
  return (
    <SimpleGrid cols={AppConfig.STATS_COLUMNS} mb="lg">
      <Card shadow="sm" padding="lg" radius="md" withBorder>
        <Text size="xl" weight={700}>
          {total}
        </Text>
        <Text c="dimmed">{StatsStrings.totalApplications}</Text>
      </Card>
      <Card shadow="sm" padding="lg" radius="md" withBorder>
        <Text size="xl" weight={700} c="blue">
          {interviews}
        </Text>
        <Text c="blue">{StatsStrings.interviews}</Text>
      </Card>
      <Card shadow="sm" padding="lg" radius="md" withBorder>
        <Text size="xl" weight={700} c="green">
          {offers}
        </Text>
        <Text c="green">{StatsStrings.offers}</Text>
      </Card>
    </SimpleGrid>
  );
}

export default Stats;
