package com.shang.spray.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Funny extends BaseEntity{

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
     * 所属类别
     */
    private Integer typeId;

    /**
     * 标题
     */
    private String title;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 作者
     */
    private String author;

    /**
     * 来源id
     */
    @OneToOne
    @JoinColumn(name = "sources_id")
    private Sources sources;

    /**
     * 标签
     */
    private String label;

    /**
     * 点赞
     */
    private Integer thumbUp;

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
     * 内容
     */
    private String content;


    /**
     * 获取所属类别
     *
     * @return type_id - 所属类别
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * 设置所属类别
     *
     * @param typeId 所属类别
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
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

    /**
     * 获取作者
     *
     * @return author - 作者
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置作者
     *
     * @param author 作者
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    public Sources getSources() {
        return sources;
    }

    public void setSources(Sources sources) {
        this.sources = sources;
    }

    /**
     * 获取标签
     *
     * @return label - 标签
     */
    public String getLabel() {
        return label;
    }

    /**
     * 设置标签
     *
     * @param label 标签
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * 获取点赞
     *
     * @return thumb_up - 点赞
     */
    public Integer getThumbUp() {
        return thumbUp;
    }

    /**
     * 设置点赞
     *
     * @param thumbUp 点赞
     */
    public void setThumbUp(Integer thumbUp) {
        this.thumbUp = thumbUp;
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
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
    }
}