package sz.fzf.litepress.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="cms_banner")
@Entity
public class BannerEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    /**
     * 简称
     */
    private String name;

    /**
     * 横幅图片
     */
    private String cover;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
