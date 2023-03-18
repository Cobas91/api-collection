package cobas.coding.apicollection.core.job;

import cobas.coding.apicollection.core.RepositoryProvider;
import cobas.coding.apicollection.core.job.model.JobInformation;
import cobas.coding.apicollection.core.job.repository.JobInformationRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiJobService {
    @Getter
    private List<ApiJob> jobs = new ArrayList<>();
    @Autowired private TaskScheduler taskScheduler;
    @Autowired
    RepositoryProvider repositoryProvider;

    public void registerJob(Runnable job, String name, Trigger trigger){
        jobs.add(ApiJob.builder().job(job).serviceName(name).build());
        repositoryProvider.getRepo(JobInformationRepository.class).save(JobInformation.builder().message("Register Job:"+name).build());
        taskScheduler.schedule(job, trigger);
    }
}
