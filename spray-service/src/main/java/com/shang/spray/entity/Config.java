package com.shang.spray.entity;

import javax.persistence.Entity;

/**
 * info:
 * Created by shang on 16/8/30.
 */
@Entity
public class Config extends BaseEntity {


    public enum StatusEnum {
        SHANGJIA(1,"上架"),
        XIAJIA(2,"下架");

        private Integer code;

        private String name;

        StatusEnum(Integer code, String name) {
            this.code = code;
            this.name = name;
        }



        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public String getStatusName() {
        for (StatusEnum statusEnum : StatusEnum.values()) {
            if (statusEnum.getCode().equals(this.status)) {
                return statusEnum.getName();
            }
        }
        return null;
    }

    /**
     * 代码
     */
    private String code;
    /**
     * 描述
     */
    private String info;
    /**
     * 内容
     */
    private String content;

    /**
     * 状态
     */
    private Integer status;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
