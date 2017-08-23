package com.perficient.adobe.digital.core.sightly.models;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jcr.Session;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Model(
        adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class SampleSlingModel {

    @Self
    private Resource resource;

    // Inject a property name whose name does NOT match the Model field name
    // Since the Default inject strategy is OPTIONAL (set on the @Model), we can mark injections as @Required. @Optional can be used if the default strategy is REQUIRED.
    @Inject
    @Named("jcr:title") @Required
    private String title;

    // Inject a fields whose property name DOES match the model field name
    @Inject @Optional
    private String pageTitle;

    // Mark as Optional
    @Inject @Optional
    private String navTitle;

    // Provide a default value if the property name does not exist
    @Inject @Named("jcr:description") @Default(values = "No description provided")
    private String description;

    // Various data types can be injected
    @Inject @Named("jcr:created")
    private Calendar createdAt;

    @Inject
    boolean navRoot;

    // Inject OSGi services
    @Inject
    private QueryBuilder queryBuilder;

    // Injection will occur over all Injectors based on Ranking;
    // Force an Injector using @Source(..)
    // If an Injector is not working; ensure you are using the latest version of Sling Models
    @Inject @Source("sling-object")
    private ResourceResolver resourceResolver;

    // Internal state populated via @PostConstruct logic
    private long size;
    private Page page;

    @PostConstruct
    // PostConstructs are called after all the injection has occurred, but before the Model object is returned for use.
    private void init() {
        // Note that @PostConstruct code will always be executed on Model instantiation.
        // If the work done in PostConstruct is expensive and not always used in the consumption of the model, it is
        // better to lazy-execute the logic in the getter and persist the result in  model state if it is requested again.
        page = resourceResolver.adaptTo(PageManager.class).getContainingPage(resource);

        final Map<String, String> map = new HashMap<String, String>();
        // Injected fields can be used to define logic
        map.put("path", page.getPath());
        map.put("type", "cq:Page");

        Query query = queryBuilder.createQuery(PredicateGroup.create(map), resourceResolver.adaptTo(Session.class));
        final SearchResult result = query.getResult();
        this.size = result.getHits().size();
    }

    /**
     * This getter wraps business logic around how an logic data point (title) is represented for this resource.
     *
     * @return The Page Title if exists, with fallback to the jcr:title
     */
    public String getTitle() {
//        return StringUtils.defaultIfEmpty(pageTitle, title);
        return pageTitle.isEmpty() ? title : pageTitle;
    }

    /**
     * This getter exposes data Injected into the Model and allows parameterized manipulation of the output.
     *
     * @param truncateAt length at which to truncate
     * @return the truncated description.
     */
    public String getDescription(int truncateAt) {
        return this.description.substring(0, truncateAt) + "...";
    }

    /**
     * Default implementation of the parameterizable getDescription(..).
     *
     * @return the truncated description.
     */
    public String getDescription() {
        return this.getDescription(10);
    }

    /**
     * This getter exposes the work of a @PostConstruct method.
     *
     * @return the number of cq:Pages that exist under this resource.
     */
    public long getSize() {
        return this.size;
    }

    /**
     * @return the created at Calendar value.
     */
    public Calendar getCreatedAt() {
        return createdAt;
    }

    /**
     * @return the resource path to this content. Does not include the extension.
     */
    public String getPath() {
        return page.getPath();
    }
}
