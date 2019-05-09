package com.gp.orm.common.framework;

/**
 * @Author:ChunJieRen
 * @Date:2019/4/26 11:33
 * @Description:
 */
public class Order {

    /**升序还是降序*/
    private boolean ascending;
    /**字段*/
    private String propertyName;

    @Override
    public String toString(){
        return propertyName + "" + (ascending?"asc":"desc");
    }

    public Order(boolean ascending, String propertyName) {
        this.ascending = ascending;
        this.propertyName = propertyName;
    }

    public static Order asc(String propertyName){
        return new Order(true,propertyName);
    }

    public static Order desc(String propertyName){
        return new Order(false, propertyName);
    }
}
