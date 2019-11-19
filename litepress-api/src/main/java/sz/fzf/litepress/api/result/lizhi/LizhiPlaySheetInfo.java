package sz.fzf.litepress.api.result.lizhi;

import java.util.Date;

/*
 * band":"1367391","collect":false,"cover":"http://cdnimg103.lizhi.fm/audio_cover/2018/09/01/2689909258716258823_160x160.jpg",
 * "createTime":1525442577,"id":"2667635973481609793","intro":"凡凡小朋友用自己的方式朗读古诗！","name":"凡凡读古诗",
 * "owner":"凡凡故事屋@34055490","ownerCover":"http://cdnimg103.lizhi.fm/user/2016/12/24/2575542998392455170.png",
 * "playCount":7,"showType":0,"userId":"2540285708286522412","voiceNum":113
 */
public class LizhiPlaySheetInfo {
	private String band;
	private String name;
	private String cover;
	private String ownerCover;
	private Date createTime;
	private int voiceNum;
	private String userId;
	private String intro;
	private String id;
	public String getBand() {
		return band;
	}
	public void setBand(String band) {
		this.band = band;
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
	public String getOwnerCover() {
		return ownerCover;
	}
	public void setOwnerCover(String ownerCover) {
		this.ownerCover = ownerCover;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getVoiceNum() {
		return voiceNum;
	}
	public void setVoiceNum(int voiceNum) {
		this.voiceNum = voiceNum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	

}
