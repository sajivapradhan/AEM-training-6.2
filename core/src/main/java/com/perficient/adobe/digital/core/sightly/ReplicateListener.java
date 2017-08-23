package com.perficient.adobe.digital.core.sightly;

import com.day.cq.replication.ReplicationAction;
import com.day.cq.replication.ReplicationActionType;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.event.jobs.JobManager;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

@Component(immediate = true)
@Service(value = EventHandler.class)
@Property(name = "event.topics", value = ReplicationAction.EVENT_TOPIC)
public class ReplicateListener implements EventHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String TOPIC = "Digital Training Replication Job";

    @Reference
    private JobManager jobManager;


    @Override
    public void handleEvent(final Event event) {
        ReplicationAction action = ReplicationAction.fromEvent(event);

        if (action.getType().equals(ReplicationActionType.ACTIVATE)) {
            if(action.getType() != null) {
                try {
                    HashMap<String, Object> jobprops = new HashMap<>();
                    jobprops.put("PAGE PATH", action.getPath());

                    jobManager.addJob(TOPIC, jobprops);
                    logger.info("=============TOPIC: " + TOPIC + " with payload: " + action.getPath() + " was added to the job manager");
                } catch (Exception e) {
                    logger.error("==============ERROR CREATING JOB: NO PAYLOAD WAS DEFINED");
                    e.printStackTrace();
                }
            }
        }
    }
}
