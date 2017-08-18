package com.perficient.adobe.digital.core.sightly.schedulers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.Property;

@Component
@Service(value = Runnable.class)
@Property( name = "scheduler.period", longValue = 10) //the period is expressed in seconds
public class ScheduledPeriodicJob implements Runnable {

    /** Default log. */
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    public void run() {
//        log.info("Executing a perodic job (job#2) through the whiteboard pattern");
    }

}
