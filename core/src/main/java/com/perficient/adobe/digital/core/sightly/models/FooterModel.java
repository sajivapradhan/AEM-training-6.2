package com.perficient.adobe.digital.core.sightly.models;

import org.apache.felix.scr.annotations.Component;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Calendar;

@Component
@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FooterModel {

    @Inject @Named("logoImageURL")
    private String logoUrl;

    @Inject @Named("logoImage") @Default(values = "/etc/designs/digital/img/perficient-logo-grey.png")
    private String logoPath;

    @Inject @Default(values = "Perficient, Inc")
    private String logoAltText;

    @Inject @Default(values = "Privacy Policy")
    private String privacyPolicyText;

    @Inject
    private String privacyPolicyURL;

    @Inject @Default(values = "Vision. Execution. Value.")
    private String slogan;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostConstruct
    protected void sayHello() {
        logger.info("hello from footer model");
    }

    /**
     * Gets the CopyRight notice.
     *
     * @return the CopyRight notice
     */
    public String getCopyRightNotice() {
        Calendar now = Calendar.getInstance();
        String currentYear = String.valueOf(now.get(Calendar.YEAR));

        return "© " + currentYear + " Perficient, Inc. All Rights Reserved";
    }

    /**
     * Convert local path to URL
     *
     * @return the URL
     */
    private String createUrl(String path) {
        if (path != null && path.startsWith("/content/digital")) {
            path = path.concat(".html");
        }

        return path;
    }

    public String getLogoUrl() {
        return createUrl(logoUrl);
    }

    public String getLogoPath() {
        return logoPath;
    }

    public String getLogoAltText() {
        return logoAltText;
    }

    public String getPrivacyPolicyText() {
        return privacyPolicyText;
    }

    public String getPrivacyPolicyURL() {
        return createUrl(privacyPolicyURL);
    }

    public String getSlogan() {
        return slogan;
    }

}
