package cobas.coding.apicollection.core;

import cobas.coding.apicollection.core.settings.enums.Services;
import cobas.coding.apicollection.core.settings.model.ApiCollectionSettings;
import cobas.coding.apicollection.core.settings.repository.ApiCollectionSettingsRepository;
import cobas.coding.apicollection.discord.DiscordService;
import cobas.coding.apicollection.memes.MemeService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoreService {

    @Autowired
    RepositoryProvider repositoryProvider;

    @Autowired
    ServiceProvider serviceProvider;

    @PostConstruct
    void init(){
        createInitialSettings();
        startScheduledJobs();
    }

    private void startScheduledJobs() {
        Optional<ApiCollectionSettings> settings = (Optional) repositoryProvider.getRepo(ApiCollectionSettingsRepository.class).findById(0L);
        if(settings.isPresent()){
            if(settings.get().getActiveServices().contains(Services.MEMES)){
                serviceProvider.getService(MemeService.class).registerJob();
            }
            if(settings.get().getActiveServices().contains(Services.DISCORD)){
                serviceProvider.getService(DiscordService.class).registerJob();
            }
        }

    }

    private void createInitialSettings() {
        ApiCollectionSettings settings = new ApiCollectionSettings();
        settings.setId(0L);
        settings.setActiveServices(getActiveServices());
        repositoryProvider.getRepo(ApiCollectionSettingsRepository.class).save(settings);
    }

    private List<Services> getActiveServices(){
        return List.of(
                Services.DISCORD
        );
    }
}
