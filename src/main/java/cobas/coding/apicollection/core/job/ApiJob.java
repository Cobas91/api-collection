package cobas.coding.apicollection.core.job;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiJob {
    private String serviceName;
    private Runnable job;
}
