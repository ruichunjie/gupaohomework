package com.gp.orm.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:ChunJieRen
 * @Date:2019/4/25 18:49
 * @Description: 分页对象 包含当前页对象及分页信息
 */
public class Page<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final int DEFAULT_PAGE_SIZE = 20;
    /**每页记录*/
    private int pageSize = DEFAULT_PAGE_SIZE;
    /**当前页第一条数据在List中的位置，从0开始*/
    private long start;
    /**当前页存放的记录*/
    private List<T> rows;
    /**总记录数*/
    private long total;

    /**
     * @Author ChunJie Ren
     * @Description 构建空页
     * @Date 10:03 AM 2019/4/26
     * @Param
     * @return
     */
    public Page(){
        this(0,0,DEFAULT_PAGE_SIZE,new ArrayList<T>());
    }

    /**
     * @Author ChunJie Ren
     * @Description 默认构造
     * @Date 10:02 AM 2019/4/26
     * @Param 
     * @return 
     */
    public Page(long start, long totalSize, int pageSize, List<T> rows){

        this.pageSize = pageSize;
        this.start = start;
        this.total = totalSize;
        this.rows = rows;
    }

    /**
     * @Author ChunJie Ren
     * @Description 每页数据容量
     * @Date 10:09 AM 2019/4/26
     * @Param
     * @return
     */
    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    /**
     * @Author ChunJie Ren
     * @Description 取当前页的记录
     * @Date 10:10 AM 2019/4/26
     * @Param
     * @return
     */
    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    /**
     * @Author ChunJie Ren
     * @Description 取总记录数
     * @Date 10:04 AM 2019/4/26
     * @Param
     * @return
     */
    public long getTotal() {
        return total;
    }

    /**
     * @Author ChunJie Ren
     * @Description 设置总数
     * @Date 10:04 AM 2019/4/26
     * @Param
     * @return
     */
    public void setTotal(long total) {
        this.total = total;
    }

    /**
     * @Author ChunJie Ren
     * @Description 获取总页数
     * @Date 10:09 AM 2019/4/26
     * @Param
     * @return
     */
    public long getTotalPageCount(){
        if(total%pageSize == 0){
            return total/pageSize;
        }else {
            return total/pageSize + 1;
        }
    }

    /**
     * @Author ChunJie Ren
     * @Description 取当前页码
     * @Date 10:11 AM 2019/4/26
     * @Param
     * @return
     */
    public long getPageNo(){
        return start/pageSize +1;
    }

    /**
     * @Author ChunJie Ren
     * @Description 该页是否有下一页
     * @Date 10:13 AM 2019/4/26
     * @Param 
     * @return 
     */
    public boolean hasNextPage(){
        return this.getPageNo() < this.getTotalPageCount();
    }

    /**
     * @Author ChunJie Ren
     * @Description 该页是否有上一页
     * @Date 10:17 AM 2019/4/26
     * @Param
     * @return
     */
    public boolean hasPreviousPage(){
        return this.getPageNo() > 1;
    }

    /**
     * @Author ChunJie Ren
     * @Description 获取该页第一条数据
     * @Date 10:21 AM 2019/4/26
     * @Param
     * @return
     */
    public static int getStartOfPage(int pageNo, int pageSize){
        return (pageNo-1) * pageSize;
    }

    /**
     * @Author ChunJie Ren
     * @Description 获取该页第一条数据 页数默认
     * @Date 10:20 AM 2019/4/26
     * @Param
     * @return
     */
    protected static int getStartOfPage(int pageNo){
        return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
    }
}
