package com.perficient.adobe.digital.core.sightly;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.event.jobs.Job;
import org.apache.sling.event.jobs.consumer.JobConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@Component(immediate = true)
@Service(value = {JobConsumer.class})
@Property(name = JobConsumer.PROPERTY_TOPICS, value = "Digital Training Replication Job")
public class ReplicationLogger implements JobConsumer {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    @Override
    public JobResult process(final Job job) {
        final String pagePath = job.getProperty("PAGE_PATH").toString();

        ResourceResolver resourceResolver = null;

        try {
            Map<String, Object> serviceParams = new HashMap<>();
            serviceParams.put(ResourceResolverFactory.SUBSERVICE, "training");
            resourceResolver = resourceResolverFactory.getResourceResolver(serviceParams);
        } catch (LoginException e) {
            e.printStackTrace();
        }

        final PageManager pm = resourceResolver.adaptTo(PageManager.class);
        final Page page = pm.getContainingPage(pagePath);

        if(page != null) {
            logger.info("+++++++++++++++ ACTIVATION OF PAGE: {}", page.getTitle());
        }
        return JobResult.OK;
    }
}
