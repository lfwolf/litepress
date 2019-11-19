package sz.fzf.litepress.api.model.wordpress;

import java.util.Date;

public class WpPostModel {
	public int getFeatured_media() {
		return featured_media;
	}
	public void setFeatured_media(int featured_media) {
		this.featured_media = featured_media;
	}
	public String getContentRendered() {
		if(this.getContent() == null ) return null;
		return this.getContent().getRendered();
	}
	public void setContentRendered(String contentRendered) {
		this.getContent().setRendered(contentRendered);
	}
	public String getTitleRendered() {
		if(this.getTitle() == null ) return null;
		return this.getTitle().getRendered();
	}
	public void setTitleRendered(String titleRendered) {
		this.getTitle().setRendered(titleRendered);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int[] getCategories() {
		return categories;
	}
	public void setCategories(int[] categories) {
		this.categories = categories;
	}
	public wptitle getTitle() {
		return title;
	}
	public void setTitle(wptitle title) {
		this.title = title;
	}
	public wpcontent getContent() {
		return content;
	}
	public void setContent(wpcontent content) {
		this.content = content;
	}
	int id;
	Date date;
	String status;
	String type;
	int categories[];
	wptitle title;
	wpcontent content;
	int featured_media;
	

	String contentRendered;
	String titleRendered;
}
class wptitle{
	public String getRendered() {
		return rendered;
	}

	public void setRendered(String rendered) {
		this.rendered = rendered;
	}

	String rendered;
}
class wpcontent{
	public String getRendered() {
		return rendered;
	}

	public void setRendered(String rendered) {
		this.rendered = rendered;
	}

	String rendered;
}