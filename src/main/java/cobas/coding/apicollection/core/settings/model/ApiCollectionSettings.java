package cobas.coding.apicollection.core.settings.model;

import cobas.coding.apicollection.core.settings.enums.Services;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "API_COLLECTION_SETTINGS")
public class ApiCollectionSettings {
    @Id
    private Long id;
    @Column(name = "ACTIVE_SERVICES")
    private List<Services> activeServices;

}
