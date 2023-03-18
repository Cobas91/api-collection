package cobas.coding.apicollection.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class RepositoryProvider {

    @Autowired
    ApplicationContext context;

    @SuppressWarnings("unchecked")
    public JpaRepository<Object, Long> getRepo(Class<? extends JpaRepository>  repoClass){
        return context.getBean(repoClass);
    }
}
