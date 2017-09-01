package com.perficient.adobe.digital.core.sightly.models;

import com.adobe.acs.commons.widgets.MultiFieldPanelFunctions;
import com.adobe.cq.sightly.WCMUsePojo;
import org.apache.sling.api.resource.ValueMap;

import java.util.List;
import java.util.Map;

public class Carousel extends WCMUsePojo {

    private long playSpeed;
    private long transTime;
    private List<Map<String, String>> images;

    @Override
    public void activate() throws Exception {
        ValueMap properties = getProperties();

        setPlaySpeed(properties.get("playSpeed", 6000));
        setTransTime(properties.get("transTime", 1000));
        setImages(MultiFieldPanelFunctions.getMultiFieldPanelValues(getResource(), "images"));

    }

    public long getPlaySpeed() {
        return playSpeed;
    }

    public void setPlaySpeed(long playSpeed) {
        this.playSpeed = playSpeed;
    }

    public long getTransTime() {
        return transTime;
    }

    public void setTransTime(long transTime) {
        this.transTime = transTime;
    }

    public List<Map<String, String>> getImages() {
        return images;
    }

    public void setImages(List<Map<String, String>> images) {
        this.images = images;
    }
}
