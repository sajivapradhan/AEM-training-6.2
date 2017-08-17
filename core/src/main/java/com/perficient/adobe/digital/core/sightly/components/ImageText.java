package com.perficient.adobe.digital.core.sightly.components;

import com.adobe.cq.sightly.WCMUse;
import com.day.cq.wcm.foundation.Image;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;

public class ImageText extends WCMUse {

    private String text;
    private String imagePath;
    private String imageURL;
    private String imageAltText;
//    private String image1;
//    private String image2;
//    private String image3;

    public static String TEXT_PROP = "text";
    public static String IMAGE_PATH_PROP = "./image/fileReference";
    public static String IMAGE_URL_PROP = "./image/linkURL";
    public static String IMAGE_ALT_TEXT_PROP = "./image/alt";
//    public static String IMAGE1_PROP = "./imagetext/fileName";
//    public static String IMAGE2_PROP = "./imagetext/file1";
//    public static String IMAGE3_PROP = "./imagetext/file2";

    public static String DEFAULT_TEXT = "This is default text.";
    public static String DEFAULT_IMAGE = "";
    public static String DEFAULT_IMAGE_URL = "#";
    public static String DEFAULT_IMAGE_ALT_TEXT = "Default Image Alt Text.";

    @Override
    public void activate() throws Exception {
        ValueMap properties = getProperties();
//        Resource resource = getResource();
//        Image image = new Image(resource);

        setText(properties.get(TEXT_PROP, DEFAULT_TEXT));
        setImagePath(properties.get(IMAGE_PATH_PROP, DEFAULT_IMAGE));
        setImageURL(properties.get(IMAGE_URL_PROP, DEFAULT_IMAGE_URL));
        setImageAltText(properties.get(IMAGE_ALT_TEXT_PROP, DEFAULT_IMAGE_ALT_TEXT));
//        setImage1(properties.get(IMAGE1_PROP, DEFAULT_IMAGE));
//        setImage2(properties.get(IMAGE2_PROP, DEFAULT_IMAGE));
//        setImage3(properties.get(IMAGE3_PROP, DEFAULT_IMAGE));

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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

//    public String getImage1() {
//        return "/content/digital/en/jcr:content/content/image_text/image/file";
//    }

//    public void setImage1(String image1) {
//        this.image1 = image1;
//    }

    public String getImage2() {
        return "/etc/designs/digital/img/box-overlay-oracle.png";
    }

//    public void setImage2(String image2) {
//        this.image2 = image2;
//    }

    public String getImage3() {
        return "/etc/designs/digital/img/logo-adobe.png";
    }

//    public void setImage3(String image3) {
//        this.image3 = image3;
//    }
}
