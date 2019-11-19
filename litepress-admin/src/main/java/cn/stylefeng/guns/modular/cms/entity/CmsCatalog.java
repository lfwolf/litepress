package cn.stylefeng.guns.modular.cms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author felix
 * @since 2019-11-04
 */
@TableName("cms_catalog")
public class CmsCatalog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 封面图片
     */
    private String cover;

    /**
     * 名称
     */
    private String name;

    /**
     * 链接地址
     */
    private String url;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "CmsCatalog{" +
        "id=" + id +
        ", cover=" + cover +
        ", name=" + name +
        ", url=" + url +
        "}";
    }
}
