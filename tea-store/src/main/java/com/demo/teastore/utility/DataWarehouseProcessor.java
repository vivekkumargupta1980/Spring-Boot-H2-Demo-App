package com.demo.teastore.utility;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Fires an execution of a long running task
 */
public final class DataWarehouseProcessor {

    private static final Logger logger = LoggerFactory.getLogger(DataWarehouseProcessor.class);

    private static final int SLEEP_IN_SECONDS = 5;

    private DataWarehouseProcessor() {
    }

    public static final void processOrderUsingLongAlgorithm(Long storeOrderId) {
        try {
            logger.info("Beginnig long order processing {}", storeOrderId);
            TimeUnit.SECONDS.sleep(SLEEP_IN_SECONDS);
        } catch (Exception e) {
            //do nothing
        } finally {
            logger.info("Finished long order processing {}", storeOrderId);
        }
    }
}
