package com.shang.spray.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Beautiful extends BaseEntity{

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
        for (Beautiful.StatusEnum statusEnum : Beautiful.StatusEnum.values()) {
            if (statusEnum.getCode().equals(this.status)) {
                return statusEnum.getName();
            }
        }
        return null;
    }

    /**
     * 类型id
     */
    private Integer typeId;

    /**
     * 名称
     */
    private String title;

    /**
     * 链接
     */
    private String link;

    /**
     * 来源
     */
    @OneToOne
    @JoinColumn(name = "sources_id")
    private Sources sources;

    /**
     * 作者
     */
    private String author;

    private String label;

    /**
     * 是否置顶
     */
    private Boolean placedTop;

    /**
     * 是否推荐
     */
    private Boolean recommend;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 专辑id(暂未使用)
     */
    private Integer albumid;


    /**
     * 获取类型id
     *
     * @return type_id - 类型id
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * 设置类型id
     *
     * @param typeId 类型id
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * 获取名称
     *
     * @return title - 名称
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置名称
     *
     * @param title 名称
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取链接
     *
     * @return link - 链接
     */
    public String getLink() {
        return link;
    }

    /**
     * 设置链接
     *
     * @param link 链接
     */
    public void setLink(String link) {
        this.link = link;
    }

    public Sources getSources() {
        return sources;
    }

    public void setSources(Sources sources) {
        this.sources = sources;
    }

    /**
     * @return label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * 获取是否置顶
     *
     * @return placed_top - 是否置顶
     */
    public Boolean getPlacedTop() {
        return placedTop;
    }

    /**
     * 设置是否置顶
     *
     * @param placedTop 是否置顶
     */
    public void setPlacedTop(Boolean placedTop) {
        this.placedTop = placedTop;
    }

    /**
     * 获取是否推荐
     *
     * @return recommend - 是否推荐
     */
    public Boolean getRecommend() {
        return recommend;
    }

    /**
     * 设置是否推荐
     *
     * @param recommend 是否推荐
     */
    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
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

    /**
     * 获取专辑id(暂未使用)
     *
     * @return albumid - 专辑id(暂未使用)
     */
    public Integer getAlbumid() {
        return albumid;
    }

    /**
     * 设置专辑id(暂未使用)
     *
     * @param albumid 专辑id(暂未使用)
     */
    public void setAlbumid(Integer albumid) {
        this.albumid = albumid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}