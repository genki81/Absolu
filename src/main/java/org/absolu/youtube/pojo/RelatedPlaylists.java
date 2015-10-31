package org.absolu.youtube.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RelatedPlaylists implements Serializable {
	private static final long serialVersionUID = 4338384978241002L;
	private String uploads;

	public String getUploads() {
		return uploads;
	}

	public void setUploads(String uploads) {
		this.uploads = uploads;
	}
}
