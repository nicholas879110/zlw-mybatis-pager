package com.zlw.mybatis.pager.util;

import com.zlw.commons.core.ResultData;
import com.zlw.commons.paging.InnerPageQuery;
import com.zlw.commons.paging.Pager;
import com.zlw.commons.paging.PagerQuery;
import com.zlw.mybatis.pager.Page;
import com.zlw.mybatis.pager.PageHelper;

public class PageQueryUtil {
    /**
     * 分页查询工具
     *
     * @param pagerQuery
     * @param innerPageQuery
     * @param <T>
     * @return
     */
    public static <T> ResultData<Pager<T>> queryByPage(PagerQuery pagerQuery, InnerPageQuery innerPageQuery) {
        int pageNum = 1;
        if (pagerQuery.getPageNum() > 0 && pagerQuery.getPageSize() > 0) {
            pageNum = pagerQuery.getPageNum();
        }
        PageHelper.startPage(pageNum, pagerQuery.getPageSize());
        ResultData resultData = (ResultData) innerPageQuery.innerQuery();
        Page<T> page = (Page) resultData.getData();
        Pager<T> pager = new Pager<T>(page.getTotal(), page.getResult(), page.getPageNum(), page.getPageSize(), page.getPages());//convert page to pager
        resultData.setData(pager);
        return resultData;
    }


}