package cloud.fesh.msgplatform.utils;

public enum CompareToEnum {
    //左边的一个集合
    LEFT,
    //右边的一个集合
    RIGHT,
    //交集
    INTERSECTION,
    //并集
    UNION,
    //右缺集, 左边多余的
    LEFT_EXCEPT,
    //左缺集，右边多余的
    RIGHT_EXCEPT,
}
