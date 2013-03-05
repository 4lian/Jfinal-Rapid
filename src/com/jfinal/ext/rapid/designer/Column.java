package com.jfinal.ext.rapid.designer;

public class Column {

    /** 是否为主键 */
    boolean primaryKey;

    /** 字段名 */
    String  name;

    /** 类型 */
    String  type;

    /** 精度 */
    int     digits;

    /** 长度 */
    int     dataSize;

    /** 描述 */
    String  comment ;

    /** 是否可空 */
    boolean nullable;

    public boolean getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDigits() {
        return digits;
    }

    public void setDigits(int digits) {
        this.digits = digits;
    }

    public int getDataSize() {
        return dataSize;
    }

    public void setDataSize(int dataSize) {
        this.dataSize = dataSize;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean getNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    @Override
    public String toString() {
        return "Column [PrimaryKey=" + primaryKey + ", name=" + name + ", type=" + type + ", digits=" + digits
               + ", dataSize=" + dataSize + ", comment=" + comment + ", nullable=" + nullable + "]";
    }

}
