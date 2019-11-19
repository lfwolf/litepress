package sz.fzf.litepress.api.model.wordpress;

public class WpPostArgumentModel {
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPer_page() {
		return per_page;
	}
	public void setPer_page(int per_page) {
		this.per_page = per_page;
	}
	public String getCategories() {
		return categories;
	}
	public void setCategories(String categories) {
		this.categories = categories;
	}
	public String getCategories_exclude() {
		return categories_exclude;
	}
	public void setCategories_exclude(String categories_exclude) {
		this.categories_exclude = categories_exclude;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getTags_exclude() {
		return tags_exclude;
	}
	public void setTags_exclude(String tags_exclude) {
		this.tags_exclude = tags_exclude;
	}
	int page;
	int per_page;
	String categories;
	String categories_exclude;
	String tags;
	String tags_exclude;

}
