package com.gp.orm.common.framework;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:ChunJieRen
 * @Date:2019/4/26 10:40
 * @Description: 构造查询条件
 */
public final class QueryRule implements Serializable {

    private static final long serialVersionUID = -1L;
    private static final int ASC_ORDER =101;
    private static final int DESC_ORDER =102;
    private static final int LIKE =1;
    private static final int IN =2;
    private static final int NOTIN =3;
    private static final int BETWEEN =4;
    private static final int EQ =5;
    private static final int NOTEQ =6;
    private static final int GT =7;
    private static final int GE =8;
    private static final int LT =9;
    private static final int LE =10;
    private static final int ISNULL =11;
    private static final int ISNOTNULL =12;
    private static final int ISEMPTY =13;
    private static final int ISNOTEMPTY =14;
    private static final int AND =201;
    private static final int OR =202;
    private List<Rule> ruleList = new ArrayList<Rule>();
    private List<QueryRule> queryRuleList = new ArrayList<QueryRule>();
    private String propertyName;

    private QueryRule(){}

    private QueryRule(String propertyName){
        this.propertyName =propertyName;
    }

    public static QueryRule getInstance(){
        return new QueryRule();
    }

    /**
     * @Author ChunJie Ren
     * @Description 添加升序规则
     * @Date 11:00 AM 2019/4/26
     * @Param
     * @return
     */
    public QueryRule addAscOrder(String propertyName){
        this.ruleList.add(new Rule(ASC_ORDER,propertyName));
        return this;
    }

    /**
     * @Author ChunJie Ren
     * @Description 添加降序规则
     * @Date 11:02 AM 2019/4/26
     * @Param
     * @return
     */
    public QueryRule addDescOrder(String propertyName){
        this.ruleList.add(new Rule(DESC_ORDER,propertyName));
        return this;
    }

    /**
     * @Author ChunJie Ren
     * @Description 添加Null规则
     * @Date 11:13 AM 2019/4/26
     * @Param
     * @return
     */
    public QueryRule andIsNull(String propertyName) {
        this.ruleList.add(new Rule(ISNULL, propertyName).setAndOr(AND));
        return this;
    }

    /**
     * @Author ChunJie Ren
     * @Description 添加非null规则
     * @Date 11:13 AM 2019/4/26
     * @Param
     * @return
     */
    public QueryRule andIsNotNull(String propertyName) {
        this.ruleList.add(new Rule(ISNOTNULL, propertyName).setAndOr(AND));
        return this;
    }
    
    /**
     * @Author ChunJie Ren
     * @Description 添加空规则
     * @Date 11:14 AM 2019/4/26
     * @Param 
     * @return 
     */
    public QueryRule andIsEmpty(String propertyName) {
        this.ruleList.add(new Rule(ISEMPTY, propertyName).setAndOr(AND));
        return this;
    }
    
    /**
     * @Author ChunJie Ren
     * @Description 添加非空规则
     * @Date 11:14 AM 2019/4/26
     * @Param 
     * @return 
     */
    public QueryRule andIsNotEmpty(String propertyName) {
        this.ruleList.add(new Rule(ISNOTEMPTY, propertyName).setAndOr(AND));
        return this;
    }

    /**
     * @Author ChunJie Ren
     * @Description 添加LIKE规则
     * @Date 11:14 AM 2019/4/26
     * @Param
     * @return
     */
    public QueryRule andLike(String propertyName, Object value) {
        this.ruleList.add(new Rule(LIKE, propertyName, new Object[] { value }).setAndOr(AND));
        return this;
    }

    /**
     * @Author ChunJie Ren
     * @Description 添加相等规则
     * @Date 11:15 AM 2019/4/26
     * @Param
     * @return
     */
    public QueryRule andEqual(String propertyName, Object value) {
        this.ruleList.add(new Rule(EQ, propertyName, new Object[] { value }).setAndOr(AND));
        return this;
    }

    /**
     * @Author ChunJie Ren
     * @Description 添加之间规则
     * @Date 11:15 AM 2019/4/26
     * @Param
     * @return
     */
    public QueryRule andBetween(String propertyName, Object... values) {
        this.ruleList.add(new Rule(BETWEEN, propertyName, values).setAndOr(AND));
        return this;
    }
    
    /**
     * @Author ChunJie Ren
     * @Description 添加IN规则
     * @Date 11:16 AM 2019/4/26
     * @Param 
     * @return 
     */
    public QueryRule andIn(String propertyName, List<Object> values) {
        this.ruleList.add(new Rule(IN, propertyName, new Object[] { values }).setAndOr(AND));
        return this;
    }
    
    /**
     * @Author ChunJie Ren
     * @Description 添加IN规则
     * @Date 11:16 AM 2019/4/26
     * @Param 
     * @return 
     */
    public QueryRule andIn(String propertyName, Object... values) {
        this.ruleList.add(new Rule(IN, propertyName, values).setAndOr(AND));
        return this;
    }

    /**
     * @Author ChunJie Ren
     * @Description 添加AND NOT IN规则
     * @Date 11:20 AM 2019/4/26
     * @Param
     * @return
     */
    public QueryRule andNotIn(String propertyName, List<Object> values) {
        this.ruleList.add(new Rule(NOTIN, propertyName, new Object[] { values }).setAndOr(AND));
        return this;
    }

    /**
     * @Author ChunJie Ren
     * @Description 添加OR NOt IN 规则
     * @Date 11:20 AM 2019/4/26
     * @Param
     * @return
     */
    public QueryRule orNotIn(String propertyName, Object... values) {
        this.ruleList.add(new Rule(NOTIN, propertyName, values).setAndOr(OR));
        return this;
    }

    /**
     * @Author ChunJie Ren
     * @Description 添加 AND NOT Equal 规则
     * @Date 11:22 AM 2019/4/26
     * @Param
     * @return
     */
    public QueryRule andNotEqual(String propertyName, Object value) {
        this.ruleList.add(new Rule(NOTEQ, propertyName, new Object[] { value }).setAndOr(AND));
        return this;
    }

    /**
     * @Author ChunJie Ren
     * @Description 添加 AND Greater than 规则
     * @Date 11:22 AM 2019/4/26
     * @Param
     * @return
     */
    public QueryRule andGreaterThan(String propertyName, Object value) {
        this.ruleList.add(new Rule(GT, propertyName, new Object[] { value }).setAndOr(AND));
        return this;
    }

    /**
     * @Author ChunJie Ren
     * @Description 添加 AND GREATER Equal 规则
     * @Date 11:23 AM 2019/4/26
     * @Param
     * @return
     */
    public QueryRule andGreaterEqual(String propertyName, Object value) {
        this.ruleList.add(new Rule(GE, propertyName, new Object[] { value }).setAndOr(AND));
        return this;
    }

    /**
     * @Author ChunJie Ren
     * @Description 添加AND Less Than 规则
     * @Date 11:24 AM 2019/4/26
     * @Param
     * @return
     */
    public QueryRule andLessThan(String propertyName, Object value) {
        this.ruleList.add(new Rule(LT, propertyName, new Object[] { value }).setAndOr(AND));
        return this;
    }

    /**
     * @Author ChunJie Ren
     * @Description 添加 ANDLESSEqual 规则
     * @Date 11:24 AM 2019/4/26
     * @Param 
     * @return 
     */
    public QueryRule andLessEqual(String propertyName, Object value) {
        this.ruleList.add(new Rule(LE, propertyName, new Object[] { value }).setAndOr(AND));
        return this;
    }

    /**
     * @Author ChunJie Ren
     * @Description 添加 OR IS  Null规则
     * @Date 11:24 AM 2019/4/26
     * @Param
     * @return
     */
    public QueryRule orIsNull(String propertyName) {
        this.ruleList.add(new Rule(ISNULL, propertyName).setAndOr(OR));
        return this;
    }

    /**
     * @Author ChunJie Ren
     * @Description 添加 OR IS Not null规则
     * @Date 11:25 AM 2019/4/26
     * @Param
     * @return
     */
    public QueryRule orIsNotNull(String propertyName) {
        this.ruleList.add(new Rule(ISNOTNULL, propertyName).setAndOr(OR));
        return this;
    }

    /**
     * @Author ChunJie Ren
     * @Description 添加 OR IS Empty规则
     * @Date 11:25 AM 2019/4/26
     * @Param
     * @return
     */
    public QueryRule orIsEmpty(String propertyName) {
        this.ruleList.add(new Rule(ISEMPTY, propertyName).setAndOr(OR));
        return this;
    }

    /**
     * @Author ChunJie Ren
     * @Description 添加OR IS NOt Empty
     * @Date 11:26 AM 2019/4/26
     * @Param 
     * @return 
     */
    public QueryRule orIsNotEmpty(String propertyName) {
        this.ruleList.add(new Rule(ISNOTEMPTY, propertyName).setAndOr(OR));
        return this;
    }

    /**
     * @Author ChunJie Ren
     * @Description 添加 OR LIKE规则
     * @Date 11:26 AM 2019/4/26
     * @Param
     * @return
     */
    public QueryRule orLike(String propertyName, Object value) {
        this.ruleList.add(new Rule(LIKE, propertyName, new Object[] { value }).setAndOr(OR));
        return this;
    }

    /**
     * @Author ChunJie Ren
     * @Description 添加 OREqual规则
     * @Date 11:27 AM 2019/4/26
     * @Param
     * @return
     */
    public QueryRule orEqual(String propertyName, Object value) {
        this.ruleList.add(new Rule(EQ, propertyName, new Object[] { value }).setAndOr(OR));
        return this;
    }
    
    /**
     * @Author ChunJie Ren
     * @Description 添加ORBetween 规则
     * @Date 11:27 AM 2019/4/26
     * @Param 
     * @return 
     */
    public QueryRule orBetween(String propertyName, Object... values) {
        this.ruleList.add(new Rule(BETWEEN, propertyName, values).setAndOr(OR));
        return this;
    }

    /**
     * @Author ChunJie Ren
     * @Description 添加 OR IN 规则
     * @Date 11:27 AM 2019/4/26
     * @Param
     * @return
     */
    public QueryRule orIn(String propertyName, List<Object> values) {
         this.ruleList.add(new Rule(IN, propertyName, new Object[] { values }).setAndOr(OR));
         return this;
    }

    /**
     * @Author ChunJie Ren
     * @Description 添加 OR IN 规则
     * @Date 11:27 AM 2019/4/26
     * @Param
     * @return
     */
    public QueryRule orIn(String propertyName, Object... values) {
        this.ruleList.add(new Rule(IN, propertyName, values).setAndOr(OR));
        return this;
    }

    /**
     * @Author ChunJie Ren
     * @Description 添加 OR NOT Equal 规则
     * @Date 11:28 AM 2019/4/26
     * @Param
     * @return
     */
    public QueryRule orNotEqual(String propertyName, Object value) {
        this.ruleList.add(new Rule(NOTEQ, propertyName, new Object[] { value }).setAndOr(OR));
        return this;
    }

    /**
     * @Author ChunJie Ren
     * @Description 添加OR creater than 规则
     * @Date 11:29 AM 2019/4/26
     * @Param
     * @return
     */
    public QueryRule orGreaterThan(String propertyName, Object value) {
        this.ruleList.add(new Rule(GT, propertyName, new Object[] { value   }).setAndOr(OR));
        return this;
    }

    /**
     * @Author ChunJie Ren
     * @Description 添加 OR creater Equal 规则
     * @Date 11:29 AM 2019/4/26
     * @Param
     * @return
     */
    public QueryRule orGreaterEqual(String propertyName, Object value) {
        this.ruleList.add(new Rule(GE, propertyName, new Object[] { value   }).setAndOr(OR));
        return this;
    }

    /**
     * @Author ChunJie Ren
     * @Description 添加ORLESSTHAN规则
     * @Date 11:29 AM 2019/4/26
     * @Param
     * @return
     */
    public QueryRule orLessThan(String propertyName, Object value) {
        this.ruleList.add(new Rule(LT, propertyName, new Object[] { value   }).setAndOr(OR));
        return this;
    }

    /**
     * @Author ChunJie Ren
     * @Description 添加ORLESSEqual规则
     * @Date 11:30 AM 2019/4/26
     * @Param
     * @return
     */
    public QueryRule orLessEqual(String propertyName, Object value) {
        this.ruleList.add(new Rule(LE, propertyName, new Object[] { value   }).setAndOr(OR));
        return this;
    }

    protected class Rule implements Serializable{
        private static final long serialVersionUID = 1L;
        /**规则类型*/
        private int type;
        private String property_name;
        private Object[] values;
        private int andOr = AND;

        public Rule(int paramInt, String paramString){
            this.type = paramInt;
            this.property_name = paramString;
        }

        public Rule(int paramInt, String paramString, Object[] paramArrayOfObject){
            this.type = paramInt;
            this.property_name = paramString;
            this.values = paramArrayOfObject;
        }

        public Rule setAndOr(int andOr){
            this.andOr = andOr;
            return this;
        }
        public int getAndOr(){
            return this.andOr;
        }

        public int getType() {
            return type;
        }

        public String getProperty_name() {
            return property_name;
        }

        public Object[] getValues() {
            return values;
        }

    }
}
