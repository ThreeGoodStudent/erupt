package xyz.erupt.annotation.sub_field.sub_edit;

import java.beans.Transient;

/**
 * @author liyuepeng
 * @date 2019-01-23.
 */
public @interface AttachmentType {

    /**
     * 单位KB
     */
    long size() default 0;

    String path() default "";

    String[] fileTypes() default {};

    Type type() default Type.BASE;

    int maxLimit() default 1;

    @Transient
    ImageType imageType() default @ImageType;

    //当maxLimit大于1且SaveMode为SINGLE_COLUMN使用
    String fileSeparator() default "|";

    enum Type {
        BASE,
        IMAGE,
    }

    @interface ImageType {
        //宽高使用长度为2的数组，第一位是最小宽高限制，第二位是最大宽高限制
        int[] width() default {};

        int[] height() default {};
    }

}