package cobas.coding.apicollection.memes;

import cobas.coding.apicollection.core.RepositoryProvider;
import cobas.coding.apicollection.core.interfaces.ApiService;
import cobas.coding.apicollection.core.job.ApiJobService;
import cobas.coding.apicollection.memes.model.MemeImages;
import cobas.coding.apicollection.memes.repository.MemeImagesRepository;
import cobas.coding.apicollection.memes.response.MemeApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Service
@Slf4j
public class MemeService implements ApiService {
	@Autowired
	ApiJobService apiJobService;

	@Autowired
	RepositoryProvider repositoryProvider;
	private final String memeUrl = "https://meme-api.com/gimme";

	private final RestTemplate restTemplate = new RestTemplate();

	private final PictureDownloader downloader = new PictureDownloader("./data/memes/", "jpeg");

	private void sendRequest(){
		ResponseEntity<MemeApiResponse> response = restTemplate.getForEntity(this.memeUrl, MemeApiResponse.class);
		if(response.getStatusCode() == HttpStatus.OK){
			if(response.getBody() != null){
				byte[] imageArray = downloader.load(response.getBody().getUrl(), response.getBody().getTitle());
				saveToDatabase(imageArray);
			}
		}
	}

	@Override
	public void registerJob(){
		apiJobService.registerJob(this::sendRequest, "Meme Download", new PeriodicTrigger(Duration.ofSeconds(5)));
	}

	private void saveToDatabase(byte[] imageArray) {
		repositoryProvider.getRepo(MemeImagesRepository.class).save(MemeImages.builder().image(imageArray).build());
	}
}
