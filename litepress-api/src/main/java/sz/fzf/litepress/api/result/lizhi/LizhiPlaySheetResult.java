package sz.fzf.litepress.api.result.lizhi;

import java.util.List;

public class LizhiPlaySheetResult {
	public int getIsLastPage() {
		return isLastPage;
	}
	public void setIsLastPage(int isLastPage) {
		this.isLastPage = isLastPage;
	}
	public List<LizhiVoiceInfo> getVoices() {
		return voices;
	}
	public void setVoices(List<LizhiVoiceInfo> voices) {
		this.voices = voices;
	}
	public LizhiPlaySheetInfo getPlaySheetInfo() {
		return playSheetInfo;
	}
	public void setPlaySheetInfo(LizhiPlaySheetInfo playSheetInfo) {
		this.playSheetInfo = playSheetInfo;
	}
	private int isLastPage;
	private List<LizhiVoiceInfo> voices;
	private LizhiPlaySheetInfo playSheetInfo;

}
