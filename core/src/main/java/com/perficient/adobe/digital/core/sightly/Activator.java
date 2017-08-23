package com.perficient.adobe.digital.core.sightly;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Activator implements BundleActivator{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        logger.info("################ Bundle Started ###################");
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        logger.info("################ Bundle Stopped ###################");
    }
}
