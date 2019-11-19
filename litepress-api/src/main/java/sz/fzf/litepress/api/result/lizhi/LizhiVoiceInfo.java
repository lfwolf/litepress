package sz.fzf.litepress.api.result.lizhi;

public class LizhiVoiceInfo {
	public String getBand() {
		return band;
	}
	public void setBand(String band) {
		this.band = band;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getVoiceTrack() {
		return voiceTrack;
	}
	public void setVoiceTrack(String voiceTrack) {
		this.voiceTrack = voiceTrack;
	}
	public String getVoiceId() {
		return voiceId;
	}
	public void setVoiceId(String voiceId) {
		this.voiceId = voiceId;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private String band;
	private String cover;
	private String voiceTrack;
	private String voiceId;
	private int duration;
	private String name;

}
