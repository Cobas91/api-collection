package cobas.coding.apicollection.core.job.repository;

import cobas.coding.apicollection.core.job.model.JobInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobInformationRepository extends JpaRepository<JobInformation, Long> {
}