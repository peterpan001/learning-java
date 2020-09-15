package com.panli.common.utils;

import com.panli.common.domain.Page;

/**
 * @author lipan
 * @date 2020-09-14
 */
public class PageUtils {

    public static int getPageNum(int pageNum) {
        Page page = new Page();
        if (pageNum == 0)
            return page.getPageNum();
        return pageNum;
    }

    public static int getOffset(int pageSize, int pageNum) {
        int offset = (pageNum - 1) * pageSize;
        return offset;
    }

    public static int getPageSize(int pageSize) {
        Page page = new Page();
        if (0 != pageSize) {
            return pageSize;
        } else {
            return page.getPageSize();
        }
    }

    public static int getTotalPage(int totalCount, int pageSize) {
        if (totalCount % pageSize == 0) {
            return totalCount / pageSize;
        } else {
            return totalCount / pageSize + 1;
        }
    }
}
