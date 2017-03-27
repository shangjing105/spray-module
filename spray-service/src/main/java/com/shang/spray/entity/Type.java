package com.shang.spray.entity;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.List;

@Entity
public class Type extends BaseEntity{


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
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String info;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 上级类别
     */
    private Integer tid;
    @Transient
    private List<News> newsList;

    @Transient
    private boolean last;
    @Transient
    private String params;

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取描述
     *
     * @return info - 描述
     */
    public String getInfo() {
        return info;
    }

    /**
     * 设置描述
     *
     * @param info 描述
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * 获取排序
     *
     * @return sort - 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序
     *
     * @param sort 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取上级类别
     *
     * @return tid - 上级类别
     */
    public Integer getTid() {
        return tid;
    }

    /**
     * 设置上级类别
     *
     * @param tid 上级类别
     */
    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}