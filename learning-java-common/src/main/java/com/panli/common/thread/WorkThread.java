package com.panli.common.thread;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * @author lipan
 * @date 2020-09-23
 */
public class WorkThread implements Runnable {

    private BatchHandler batchHandler;
    private List<?> list;

    public WorkThread(List<?> list, BatchHandler batchHandler) {
        this.list = list;
        this.batchHandler = batchHandler;
    }

    @Override
    public void run() {
        if (CollectionUtils.isNotEmpty(list)) {
            for (Object obj : list) {
                batchHandler.handle(obj);
            }
        }
    }
}
