package cobas.coding.apicollection.discord;

import cobas.coding.apicollection.core.interfaces.ApiService;
import cobas.coding.apicollection.core.job.ApiJobService;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@Slf4j
public class DiscordService implements ApiService {
    @Autowired
    TaskScheduler taskScheduler;
    @Autowired
    private Environment env;

    JDA javaDiscordAssist;
    private
    @Autowired
    ApiJobService apiJobService;
    @Override
    public void registerJob() {
        apiJobService.registerJob(this::check, "Discord-Bot", new PeriodicTrigger(Duration.ofSeconds(5)));
    }

    public void check(){
        if(this.javaDiscordAssist == null || this.javaDiscordAssist.isUnavailable(10L)){
            try {
                this.javaDiscordAssist = JDABuilder.createDefault(env.getProperty("DISCORD_TOKEN")).build().awaitReady();
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        }

    }
}
