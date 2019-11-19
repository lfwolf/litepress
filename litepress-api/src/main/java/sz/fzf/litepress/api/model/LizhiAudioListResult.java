package sz.fzf.litepress.api.model;

import java.util.List;

import sz.fzf.litepress.api.result.lizhi.LizhiPlaySheetInfo;

public class LizhiAudioListResult {
	public LizhiPlaySheetInfo getBandinfo() {
		return bandinfo;
	}
	public void setBandinfo(LizhiPlaySheetInfo bandinfo) {
		this.bandinfo = bandinfo;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getP() {
		return p;
	}
	public void setP(int p) {
		this.p = p;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public List<LizhiAudioModel> getAudios() {
		return audios;
	}
	public void setAudios(List<LizhiAudioModel> audios) {
		this.audios = audios;
	}
	private int total;
	private int p;
	private int size;
	private List<LizhiAudioModel> audios;
	private LizhiPlaySheetInfo bandinfo;

}
