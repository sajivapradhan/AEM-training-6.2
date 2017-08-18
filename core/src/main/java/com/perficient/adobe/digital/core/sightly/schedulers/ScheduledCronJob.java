package com.perficient.adobe.digital.core.sightly.schedulers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.Property;

@Component(immediate = true, metatype = true, label = "Digital Syncup Base Task")
@Service(value = Runnable.class)
@Property( name = "scheduler.expression", value = "0/10 * * * * ?")
public class ScheduledCronJob implements Runnable {

    /** Default log. */
    protected final Logger log = LoggerFactory.getLogger(this.getClass());


    public void run() {
//        log.info("Executing a cron job (job#1) through the whiteboard pattern");
    }

}
