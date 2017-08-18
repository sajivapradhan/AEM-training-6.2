package com.perficient.adobe.digital.core.sightly.components;

import com.adobe.cq.sightly.WCMUsePojo;
import org.apache.sling.api.resource.ValueMap;

import java.util.Calendar;

public class PFooter extends WCMUsePojo {

    private String logoUrl;
    private String logoPath;
    private String logoAltText;
    private String privacyPolicyText;
    private String privacyPolicyURL;
    private String slogan;

    //Component properties
    public static String LOGO_IMAGE_PROP = "logoImage";
    public static String LOGO_IMAGE_URL_PROP = "logoImageURL";
    public static String LOGO_ALT_TEXT_PROP = "logoAltText";
    public static String PRIVACY_POLICY_TEXT_PROP = "privacyPolicyText";
    public static String PRIVACY_POLICY_URL_PROP = "privacyPolicyURL";
    public static String SLOGAN_PROP = "slogan";

    //Component Property Defaults
    public static String DEFAULT_LOGO_IMAGE = "/etc/designs/digital/img/perficient-logo-grey.png";
    public static String DEFAULT_LOGO_IMAGE_URL = "http://www.perficient.com/services/perficient-digital";
    public static String DEFAULT_LOGO_ALT_TEXT = "Perficient, Inc";
    public static String DEFAULT_PRIVACY_POLICY_TEXT = "Privacy Policy";
    public static String DEFAULT_PRIVACY_POLICY_URL = "http://www.perficient.com";
    public static String DEFAULT_SLOGAN = "Vision. Execution. Value.";

    @Override
    public void activate() throws Exception {
        ValueMap properties = getProperties();

        setLogoPath(properties.get(LOGO_IMAGE_PROP, DEFAULT_LOGO_IMAGE));
        setLogoUrl(properties.get(LOGO_IMAGE_URL_PROP, DEFAULT_LOGO_IMAGE_URL));
        setLogoAltText(properties.get(LOGO_ALT_TEXT_PROP, DEFAULT_LOGO_ALT_TEXT));
        setPrivacyPolicyText(properties.get(PRIVACY_POLICY_TEXT_PROP, DEFAULT_PRIVACY_POLICY_TEXT));
        setPrivacyPolicyURL(properties.get(PRIVACY_POLICY_URL_PROP, DEFAULT_PRIVACY_POLICY_URL));
        setSlogan(properties.get(SLOGAN_PROP, DEFAULT_SLOGAN));
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
        if (path.startsWith("/content/digital")) {
            path = path.concat(".html");
        }

        return path;
    }

    public String getLogoUrl() {
        return createUrl(logoUrl);
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getLogoAltText() {
        return logoAltText;
    }

    public void setLogoAltText(String logoAltText) {
        this.logoAltText = logoAltText;
    }

    public String getPrivacyPolicyText() {
        return privacyPolicyText;
    }

    public void setPrivacyPolicyText(String privacyPolicyText) {
        this.privacyPolicyText = privacyPolicyText;
    }

    public String getPrivacyPolicyURL() {
        return createUrl(privacyPolicyURL);
    }

    public void setPrivacyPolicyURL(String privacyPolicyURL) {
        this.privacyPolicyURL = privacyPolicyURL;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }
}
