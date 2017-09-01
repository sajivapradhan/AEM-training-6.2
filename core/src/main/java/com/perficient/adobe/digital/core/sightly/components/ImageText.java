package com.perficient.adobe.digital.core.sightly.components;

import com.adobe.cq.sightly.WCMUsePojo;
import org.apache.sling.api.resource.ValueMap;

public class ImageText extends WCMUsePojo {

    private String text;
    private String image1;
    private String image2;
    private String image3;
    private String imageURL;
    private String imageAltText;

    public static String TEXT_PROP = "text";
    public static String IMAGE1_PROP = "./image1/fileReference";
    public static String IMAGE2_PROP = "./image2/fileReference";
    public static String IMAGE3_PROP = "./image3/fileReference";
    public static String IMAGE_URL_PROP = "./image1/linkURL";
    public static String IMAGE_ALT_TEXT_PROP = "./image1/alt";

    public static String DEFAULT_TEXT = "This is default text.";
    public static String DEFAULT_IMAGE_URL = "#";
    public static String DEFAULT_IMAGE_ALT_TEXT = "Default Image Alt Text.";
    public static String DEFAULT_IMAGE = "";
    public static String DEFAULT_IMAGE2 = "/etc/designs/digital/img/box-overlay-oracle.png";
    public static String DEFAULT_IMAGE3 = "/etc/designs/digital/img/logo-adobe.png";

    @Override
    public void activate() throws Exception {
        ValueMap properties = getProperties();

        setText(properties.get(TEXT_PROP, DEFAULT_TEXT));
        setImageURL(properties.get(IMAGE_URL_PROP, DEFAULT_IMAGE_URL));
        setImageAltText(properties.get(IMAGE_ALT_TEXT_PROP, DEFAULT_IMAGE_ALT_TEXT));
        setImage1(properties.get(IMAGE1_PROP, DEFAULT_IMAGE));
        setImage2(properties.get(IMAGE2_PROP, DEFAULT_IMAGE2));
        setImage3(properties.get(IMAGE3_PROP, DEFAULT_IMAGE3));

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getImageAltText() {
        return imageAltText;
    }

    public void setImageAltText(String imageAltText) {
        this.imageAltText = imageAltText;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }
}
