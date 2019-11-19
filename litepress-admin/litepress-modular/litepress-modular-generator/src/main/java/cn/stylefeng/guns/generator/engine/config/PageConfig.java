package cn.stylefeng.guns.generator.engine.config;

/**
 * 页面 模板生成的配置
 *
 * @author fengshuonan
 * @date 2017-05-07 22:12
 */
public class PageConfig {

    private ContextConfig contextConfig;

    private String pagePathTemplate;
    private String pageAddPathTemplate;
    private String pageEditPathTemplate;
    private String pageJsPathTemplate;
    //private String pageInfoJsPathTemplate;
    private String pageEditJsPathTemplate;
    private String pageAddJsPathTemplate;

    public void init() {
        pagePathTemplate = "/src/main/webapp/pages/modular/" + contextConfig.getModuleName() + "/{}/{}.html";
        pageAddPathTemplate = "/src/main/webapp/pages/modular/" + contextConfig.getModuleName() + "/{}/{}_add.html";
        pageEditPathTemplate = "/src/main/webapp/pages/modular/" + contextConfig.getModuleName() + "/{}/{}_edit.html";
        pageJsPathTemplate = "/src/main/webapp/assets/modular/" + contextConfig.getModuleName() + "/{}/{}.js";
        //pageInfoJsPathTemplate = "/src/main/webapp/assets/modular/" + contextConfig.getModuleName() + "/{}/{}_info.js";
        pageEditJsPathTemplate = "/src/main/webapp/assets/modular/" + contextConfig.getModuleName() + "/{}/{}_edit.js";
        pageAddJsPathTemplate = "/src/main/webapp/assets/modular/" + contextConfig.getModuleName() + "/{}/{}_add.js";
    }

    public String getPagePathTemplate() {
        return pagePathTemplate;
    }

    public void setPagePathTemplate(String pagePathTemplate) {
        this.pagePathTemplate = pagePathTemplate;
    }

    public String getPageJsPathTemplate() {
        return pageJsPathTemplate;
    }

    public void setPageJsPathTemplate(String pageJsPathTemplate) {
        this.pageJsPathTemplate = pageJsPathTemplate;
    }

    public String getPageAddPathTemplate() {
        return pageAddPathTemplate;
    }

    public void setPageAddPathTemplate(String pageAddPathTemplate) {
        this.pageAddPathTemplate = pageAddPathTemplate;
    }

    public String getPageEditPathTemplate() {
        return pageEditPathTemplate;
    }

    public void setPageEditPathTemplate(String pageEditPathTemplate) {
        this.pageEditPathTemplate = pageEditPathTemplate;
    }


    public ContextConfig getContextConfig() {
        return contextConfig;
    }

    public void setContextConfig(ContextConfig contextConfig) {
        this.contextConfig = contextConfig;
    }

	public String getPageEditJsPathTemplate() {
		return pageEditJsPathTemplate;
	}

	public void setPageEditJsPathTemplate(String pageEditJsPathTemplate) {
		this.pageEditJsPathTemplate = pageEditJsPathTemplate;
	}

	public String getPageAddJsPathTemplate() {
		return pageAddJsPathTemplate;
	}

	public void setPageAddJsPathTemplate(String pageAddJsPathTemplate) {
		this.pageAddJsPathTemplate = pageAddJsPathTemplate;
	}
}
