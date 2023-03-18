package cobas.coding.apicollection.core;

import cobas.coding.apicollection.core.interfaces.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class ServiceProvider {
    @Autowired
    ApplicationContext applicationContext;

    public ApiService getService(Class<? extends ApiService> serviceClass){
        return applicationContext.getBean(serviceClass);
    }
}
