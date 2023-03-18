package cobas.coding.apicollection.memes.repository;

import cobas.coding.apicollection.memes.model.MemeImages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemeImagesRepository extends JpaRepository<MemeImages, Long> {
}