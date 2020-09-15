package com.panli.common.utils;

/**
 * @author lipan
 * @date 2020-09-09
 * @desc Yarn集群客户端工具类
 */
public class YarnClusterClientUtils {

    private static final String UNDERLINE = "_";

    // application_1576571587843_9691
    // In Hadoop 2, MapReduce job IDs are generated from YARN application IDs that are created by the YARN resource manager.
    // The format of an application ID is composed of the time that the resource manager (not the application) started
    // and an incrementing counter maintained by the resource manager to uniquely identify the application to that instance of the resource manager.
    // So the application with this ID: application_1410450250506_0003 is the third (0003; application IDs are 1-based) application run by the resource manager, which started at the time represented by the timestamp 1410450250506. The counter is formatted with leading zeros to make IDs sort nicely — in directory listings, for example. However, when the counter reaches 10000, it is not reset, resulting in longer application IDs (which don’t sort so well)

    /**
     * Yarn AppId 比较大小
     *
     * @param l
     * @param r
     * @return
     */
    public static int compareAppId(String l, String r) {
        String[] lsplits = l.split(UNDERLINE);
        Long lrm = Long.parseLong(lsplits[1]);
        long lc = Long.parseLong(lsplits[2]);

        String[] rsplits = r.split(UNDERLINE);
        long rrm = Long.parseLong(rsplits[1]);
        long rc = Long.parseLong(rsplits[2]);

        int c = Long.compare(lrm, rrm);
        return c == 0 ? Long.compare(lc, rc) : c;
    }

    public static boolean isAppId(String appId) {
        if (StringUtils.isBlank(appId))
            return false;
        return appId.trim().startsWith("application_");
    }
}
