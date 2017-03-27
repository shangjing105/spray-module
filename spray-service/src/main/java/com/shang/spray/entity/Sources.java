package com.shang.spray.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Sources extends BaseEntity{


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
     * 来源
     */
    private String source;

    /**
     * 来源图片
     */
    @Column
    private String sourceImage;

    /**
     * 状态
     */
    private Integer status;


    public Sources() {
    }

    public Sources(Integer id) {
        this.setId(id);
    }

    /**
     * 获取来源
     *
     * @return source - 来源
     */
    public String getSource() {
        return source;
    }

    /**
     * 设置来源
     *
     * @param source 来源
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * 获取来源图片
     *
     * @return source_image - 来源图片
     */
    public String getSourceImage() {
        return sourceImage;
    }

    /**
     * 设置来源图片
     *
     * @param sourceImage 来源图片
     */
    public void setSourceImage(String sourceImage) {
        this.sourceImage = sourceImage;
    }

    /**
     * 获取状态
     *
     * @return status - 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}