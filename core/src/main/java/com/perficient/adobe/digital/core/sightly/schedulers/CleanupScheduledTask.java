package com.perficient.adobe.digital.core.sightly.schedulers;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.util.Dictionary;

@Component(immediate = true, metatype = true, label = "Training Cleanup Service")
@Service(value = Runnable.class)
@Property(name = "scheduler.expression", value = "*/30 * * * * ?") // Every 30 seconds
public class CleanupScheduledTask implements Runnable {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Reference
    private SlingRepository repository;

    @Property(label = "Path", description = "Delete this path", value = "/tmp/mypath")
    public static final String CLEANUP_PATH = "cleanupPath";
    private String cleanupPath;

    protected void activate(ComponentContext componentContext){
        configure(componentContext.getProperties());
    }

    protected void configure(Dictionary<?, ?> properties) {
        this.cleanupPath = String.valueOf(properties.get(CLEANUP_PATH));
        LOGGER.info("configure: cleanupPath='{}''", this.cleanupPath);
    }
    @Override
    public void run() {
        LOGGER.info("running now-Cleanup path: "+this.cleanupPath);
        Session session = null;
        try {

            session = repository.loginService("training", null);
//            session = repository.loginService(null, repository.getDefaultWorkspace());
            //session = repository.login(new SimpleCredentials("admin","admin".toCharArray()));

            LOGGER.info("Logged in with: "+session.getUserID()+" & Is session Live: "+session.isLive());

            if(session.itemExists(cleanupPath)) {
                session.removeItem(cleanupPath);
                LOGGER.info("node deleted");
                session.save();
            }
        }
        catch (RepositoryException e) {
            LOGGER.error("exception during cleanup", e);
        } finally {
            if (session != null) {
                session.logout();
            }
        }
    }
}
