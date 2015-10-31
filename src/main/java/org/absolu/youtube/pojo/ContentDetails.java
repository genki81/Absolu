package org.absolu.youtube.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentDetails implements Serializable {
	private static final long serialVersionUID = -2322792429171215396L;
	private RelatedPlaylists relatedPlaylists;
	private String videoId;

	public RelatedPlaylists getRelatedPlaylists() {
		return relatedPlaylists;
	}

	public void setRelatedPlaylists(RelatedPlaylists relatedPlaylists) {
		this.relatedPlaylists = relatedPlaylists;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
}
