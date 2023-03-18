package cobas.coding.apicollection.core.settings.repository;

import cobas.coding.apicollection.core.settings.model.ApiCollectionSettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiCollectionSettingsRepository extends JpaRepository<ApiCollectionSettings, Long> {
}