package sz.fzf.litepress.api.model;

import java.util.Date;

public class PostModel {
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int[] getCategories() {
		return categories;
	}
	public void setCategories(int[] categories) {
		this.categories = categories;
	}
	public int getMediaid() {
		return mediaid;
	}
	public void setMediaid(int mediaid) {
		this.mediaid = mediaid;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	private int id;
	private Date date;
	private String title;
	private String content;
	private int[] categories;
	private int mediaid;
	private String link;

}
