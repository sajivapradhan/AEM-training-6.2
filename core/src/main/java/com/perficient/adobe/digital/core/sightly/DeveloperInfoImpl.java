package com.perficient.adobe.digital.core.sightly;

import org.apache.felix.scr.annotations.*;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Map;

@Component(immediate = true, metatype = true, label = "Digital Developer Info")
@Service(DeveloperInfo.class)
public class DeveloperInfoImpl implements DeveloperInfo {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Property(label = "Show Info", description = "Should developer information be shown?", boolValue = false)
    public static final String PROPERTY_BOOLEAN = "show.info";

    @Property(label = "Name", description = "Name of the developer")
    public static final String PROPERTY_STRING = "name";

    @Property(label = "Hobbies", description = "List your favorite hobbies", unbounded = PropertyUnbounded.ARRAY)
    public static final String PROPERTY_ARRAY = "hobbies";

    private static final String OPTION_1 = "HTL";
    private static final String OPTION_2 = "Java";
    private static final String OPTION_3 = "JSP";
    private static final String OPTION_4 = "HTML";
    private static final String OPTION_5 = "Javascript";
    @Property(label = "Language", description = "Favorite Language Preference", options = {
            @PropertyOption(name = OPTION_1, value = OPTION_1),
            @PropertyOption(name = OPTION_2, value = OPTION_2),
            @PropertyOption(name = OPTION_3, value = OPTION_3),
            @PropertyOption(name = OPTION_4, value = OPTION_4),
            @PropertyOption(name = OPTION_5, value = OPTION_5)
    })
    public static final String PROPERTY_DROPDOWN = "language.preference";

    //local variables to hold OSGI config values
    private boolean showDeveloper;
    private String developerName;
    private String[] developerHobbies;
    private String langPreference;

    @Activate
    protected void activate(Map<String, Object> properties) {
        configure(properties, "Activated");
    }

    @Modified
    protected void modified(Map<String, Object> properties) {
        configure(properties, "Modified");
    }

    @Deactivate
    protected void deactivated(Map<String, Object> properties) {
        logger.info("############Component (Deactivated) Good-bye " + developerName);
    }

    protected void configure(Map<String, Object> properties, String status) {
        showDeveloper = PropertiesUtil.toBoolean(properties.get(PROPERTY_BOOLEAN), false);
        developerName = PropertiesUtil.toString(properties.get(PROPERTY_STRING), "Developer Name");
        developerHobbies = PropertiesUtil.toStringArray(properties.get(PROPERTY_ARRAY), new String[]{"Triangles", "Circles"});
        langPreference = PropertiesUtil.toString(properties.get(PROPERTY_DROPDOWN), OPTION_1);

        logger.info("#############Component (" + status + ")" + getDeveloperInfo());
    }

    @Override
    public String getDeveloperInfo() {
        if (showDeveloper)
            return "Created by " + developerName
            + ". Hobbies include: " + Arrays.toString(developerHobbies)
            + ". Preferred programming language in AEM is: " + langPreference;
        return "";
    }
}
