package com.whf.fileupload.enums;

import lombok.Getter;


/**
 * @author whf
 * @date 2024/03/07
 */
@Getter
public enum MessageEnum {
    /**
     * 消息枚举
     */
    FAIL(-1, "操作失败"),
    SUCCESS(200, "操作成功"),
    RECORD_NOT_EXISTED(1001, "记录不存在"),
    PARAM_NOT_NULL(1002, "参数不能为空"),
    PARAM_INVALID(1003, "参数错误"),
    UPLOAD_FILE_NOT_NULL(1004, "上传文件不能为空"),
    OVER_FILE_MAX_SIZE(1005, "超出文件大小"),
    CREATE_DIR_ERROR(1006, "创建目录失败"),
    UPLOAD_FILE_ERROR(1007, "上传文件失败"),
    ;

    MessageEnum(int value, String text) {
        this.code = value;
        this.message = text;
    }

    private final int code;

    private final String message;

    public static MessageEnum valueOf(int value) {
        MessageEnum[] enums = values();
        for (MessageEnum enumItem : enums) {
            if (value == enumItem.getCode()) {
                return enumItem;
            }
        }
        return null;
    }
}
