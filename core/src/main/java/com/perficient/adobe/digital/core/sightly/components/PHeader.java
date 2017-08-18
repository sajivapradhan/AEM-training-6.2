package com.perficient.adobe.digital.core.sightly.components;

import com.adobe.acs.commons.widgets.MultiFieldPanelFunctions;
import com.adobe.cq.sightly.WCMUsePojo;
import org.apache.sling.api.resource.ValueMap;

import java.util.List;
import java.util.Map;

public class PHeader extends WCMUsePojo {

    private String logoUrl;
    private String logoPath;
    private String logoAltText;

    private String section1Label;
    private String section1URL;
    private List<Map<String, String>> nav1Items;

    private String section2Label;
    private String section2URL;
    private List<Map<String, String>> nav2Items;

    private String section3Label;
    private String section3URL;
    private List<Map<String, String>> nav3Items;

    private String section4Label;
    private String section4URL;
    private List<Map<String, String>> nav4Items;

    private String section5Label;
    private String section5URL;
    private List<Map<String, String>> nav5Items;

    private List<Map<String, String>> utilityNavItems;

    //Logo Image
    public static String LOGO_IMAGE_PROP = "logoImage";
    public static String LOGO_IMAGE_URL_PROP = "logoImageURL";
    public static String LOGO_ALT_TEXT_PROP = "logoAltText";

    //Component Property Defaults
    public static String DEFAULT_LOGO_IMAGE = "/etc/designs/digital/img/perficient-logo.png";
    public static String DEFAULT_LOGO_IMAGE_URL = "http://www.perficient.com/services/perficient-digital";
    public static String DEFAULT_LOGO_ALT_TEXT = "Perficient Digital";

    //Navigation 1 Tab
    public static String NAV_1_LABEL_PROP = "section1Label";
    public static String NAV_1_URL_PROP = "section1URL";
    public static String NAV_1_ITEMS_PROP = "nav1Items";

    //Component Property Defaults
    public static String DEFAULT_SECTION1_LABEL = "Services";
    public static String DEFAULT_SECTION1_URL = "#";

    //Navigation 2 Tab
    public static String NAV_2_LABEL_PROP = "section2Label";
    public static String NAV_2_URL_PROP = "section2URL";
    public static String NAV_2_ITEMS_PROP = "nav2Items";

    //Component Property Defaults
    public static String DEFAULT_SECTION2_LABEL = "Industries";
    public static String DEFAULT_SECTION2_URL = "#";

    //Navigation 3 Tab
    public static String NAV_3_LABEL_PROP = "section3Label";
    public static String NAV_3_URL_PROP = "section3URL";
    public static String NAV_3_ITEMS_PROP = "nav3Items";

    //Component Property Defaults
    public static String DEFAULT_SECTION3_LABEL = "Partner";
    public static String DEFAULT_SECTION3_URL = "#";

    //Navigation 4 Tab
    public static String NAV_4_LABEL_PROP = "section4Label";
    public static String NAV_4_URL_PROP = "section4URL";
    public static String NAV_4_ITEMS_PROP = "nav4Items";

    //Component Property Defaults
    public static String DEFAULT_SECTION4_LABEL = "Work";
    public static String DEFAULT_SECTION4_URL = "#";

    //Navigation 5 Tab
    public static String NAV_5_LABEL_PROP = "section5Label";
    public static String NAV_5_URL_PROP = "section5URL";
    public static String NAV_5_ITEMS_PROP = "nav5Items";

    //Component Property Defaults
    public static String DEFAULT_SECTION5_LABEL = "Insights";
    public static String DEFAULT_SECTION5_URL = "#";

    //Utility Navigation
    public static String UTIL_NAV_ITEMS_PROP = "utilNavItems";


    @Override
    public void activate() throws Exception {
        ValueMap properties = getProperties();

        setLogoPath(properties.get(LOGO_IMAGE_PROP, DEFAULT_LOGO_IMAGE));
        setLogoUrl(properties.get(LOGO_IMAGE_URL_PROP, DEFAULT_LOGO_IMAGE_URL));
        setLogoAltText(properties.get(LOGO_ALT_TEXT_PROP, DEFAULT_LOGO_ALT_TEXT));

        setSection1Label(properties.get(NAV_1_LABEL_PROP, DEFAULT_SECTION1_LABEL));
        setSection1URL(properties.get(NAV_1_URL_PROP, DEFAULT_SECTION1_URL));
        setNav1Items(MultiFieldPanelFunctions.getMultiFieldPanelValues(getResource(), NAV_1_ITEMS_PROP));

        setSection2Label(properties.get(NAV_2_LABEL_PROP, DEFAULT_SECTION2_LABEL));
        setSection2URL(properties.get(NAV_2_URL_PROP, DEFAULT_SECTION2_URL));
        setNav2Items(MultiFieldPanelFunctions.getMultiFieldPanelValues(getResource(), NAV_2_ITEMS_PROP));

        setSection3Label(properties.get(NAV_3_LABEL_PROP, DEFAULT_SECTION3_LABEL));
        setSection3URL(properties.get(NAV_3_URL_PROP, DEFAULT_SECTION3_URL));
        setNav3Items(MultiFieldPanelFunctions.getMultiFieldPanelValues(getResource(), NAV_3_ITEMS_PROP));

        setSection4Label(properties.get(NAV_4_LABEL_PROP, DEFAULT_SECTION4_LABEL));
        setSection4URL(properties.get(NAV_4_URL_PROP, DEFAULT_SECTION4_URL));
        setNav4Items(MultiFieldPanelFunctions.getMultiFieldPanelValues(getResource(), NAV_4_ITEMS_PROP));

        setSection5Label(properties.get(NAV_5_LABEL_PROP, DEFAULT_SECTION5_LABEL));
        setSection5URL(properties.get(NAV_5_URL_PROP, DEFAULT_SECTION5_URL));
        setNav5Items(MultiFieldPanelFunctions.getMultiFieldPanelValues(getResource(), NAV_5_ITEMS_PROP));

        setUtilityNavItems(MultiFieldPanelFunctions.getMultiFieldPanelValues(getResource(), UTIL_NAV_ITEMS_PROP));

    }

    public String getLogoUrl() {
        return logoUrl;
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

    public String getSection1Label() {
        return section1Label;
    }

    public void setSection1Label(String section1Label) {
        this.section1Label = section1Label;
    }

    public String getSection1URL() {
        return section1URL;
    }

    public void setSection1URL(String section1URL) {
        this.section1URL = section1URL;
    }

    public List<Map<String, String>> getNav1Items() {
        return nav1Items;
    }

    public void setNav1Items(List<Map<String, String>> nav1Items) {
        this.nav1Items = nav1Items;
    }

    public String getSection2Label() {
        return section2Label;
    }

    public void setSection2Label(String section2Label) {
        this.section2Label = section2Label;
    }

    public String getSection2URL() {
        return section2URL;
    }

    public void setSection2URL(String section2URL) {
        this.section2URL = section2URL;
    }

    public List<Map<String, String>> getNav2Items() {
        return nav2Items;
    }

    public void setNav2Items(List<Map<String, String>> nav2Items) {
        this.nav2Items = nav2Items;
    }

    public String getSection3Label() {
        return section3Label;
    }

    public void setSection3Label(String section3Label) {
        this.section3Label = section3Label;
    }

    public String getSection3URL() {
        return section3URL;
    }

    public void setSection3URL(String section3URL) {
        this.section3URL = section3URL;
    }

    public List<Map<String, String>> getNav3Items() {
        return nav3Items;
    }

    public void setNav3Items(List<Map<String, String>> nav3Items) {
        this.nav3Items = nav3Items;
    }

    public String getSection4Label() {
        return section4Label;
    }

    public void setSection4Label(String section4Label) {
        this.section4Label = section4Label;
    }

    public String getSection4URL() {
        return section4URL;
    }

    public void setSection4URL(String section4URL) {
        this.section4URL = section4URL;
    }

    public List<Map<String, String>> getNav4Items() {
        return nav4Items;
    }

    public void setNav4Items(List<Map<String, String>> nav4Items) {
        this.nav4Items = nav4Items;
    }

    public String getSection5Label() {
        return section5Label;
    }

    public void setSection5Label(String section5Label) {
        this.section5Label = section5Label;
    }

    public String getSection5URL() {
        return section5URL;
    }

    public void setSection5URL(String section5URL) {
        this.section5URL = section5URL;
    }

    public List<Map<String, String>> getNav5Items() {
        return nav5Items;
    }

    public void setNav5Items(List<Map<String, String>> nav5Items) {
        this.nav5Items = nav5Items;
    }


    public List<Map<String, String>> getUtilityNavItems() {
        return utilityNavItems;
    }

    public void setUtilityNavItems(List<Map<String, String>> utilityNavItems) {
        this.utilityNavItems = utilityNavItems;
    }
}
