package com.gp.orm.common.jdbc;

import com.gp.orm.common.Page;
import com.gp.orm.common.framework.QueryRule;

import java.util.List;
import java.util.Map;

/**
 * @Author:ChunJieRen
 * @Date:2019/4/26 10:31
 * @Description:
 */
public interface BaseDao<T, PK> {

    /**
     * @Author ChunJie Ren
     * @Description 获取列表
     * @Date 11:37 AM 2019/4/26
     * @Param 
     * @return 
     */
    List<T> select(QueryRule queryRule) throws Exception;

    /**
     * @Author ChunJie Ren
     * @Description 获取分页结果
     * @Date 11:38 AM 2019/4/26
     * @Param 
     * @return 
     */
    Page<?> select(QueryRule queryRule, int page, int pageSize) throws Exception;

    /**
     * @Author ChunJie Ren
     * @Description  根据SQL获取结果
     * @Date 11:40 AM 2019/4/26
     * @Param 
     * @return 
     */
    List<Map<String, Object>> selectBySql(String sql, Object... args) throws Exception;

    /**
     * @Author ChunJie Ren
     * @Description  根据SQL获取分页结果
     * @Date 11:41 AM 2019/4/26
     * @Param
     * @return
     */
    Page<Map<String,Object>> selectBySqlToPage(String sql, Object [] param, int pageNo, int pageSize) throws Exception;

    /**
     * @Author ChunJie Ren
     * @Description 删除一条记录 entity的ID不能为空， 如果为空 其他条件不能为空
     * @Date 11:42 AM 2019/4/26
     * @Param
     * @return
     */
    boolean delete(T entity) throws Exception;
    
    /**
     * @Author ChunJie Ren
     * @Description 批量删除
     * @Date 11:43 AM 2019/4/26
     * @Param 
     * @return 
     */
    int deleteAll(List<T> list) throws Exception;

    /**
     * @Author ChunJie Ren
     * @Description 插入一条记录并返回插入后的ID
     * @Date 11:44 AM 2019/4/26
     * @Param 
     * @return 
     */
    PK insertAndReturnId(T entity) throws Exception;

    /**
     * @Author ChunJie Ren
     * @Description 插入一条记录
     * @Date 11:45 AM 2019/4/26
     * @Param
     * @return
     */
    boolean insert(T entity) throws Exception;

    /**
     * @Author ChunJie Ren
     * @Description 批量插入
     * @Date 11:46 AM 2019/4/26
     * @Param
     * @return
     */
    int insertAll(List<T> list) throws Exception;

    /**
     * @Author ChunJie Ren
     * @Description 修改一条记录
     * @Date 11:47 AM 2019/4/26
     * @Param
     * @return
     */
    boolean update(T entity) throws  Exception;

}
