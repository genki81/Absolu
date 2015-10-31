package org.absolu.youtube.pojo;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Snippet implements Serializable {
	private static final long serialVersionUID = 6286203377234291669L;
	private String publishedAt;
	private String title;
	private Map<String, Map<String, String>> thumbnails;

	public String getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(String publishedAt) {
		this.publishedAt = publishedAt;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Map<String, Map<String, String>> getThumbnails() {
		return thumbnails;
	}

	public void setThumbnails(Map<String, Map<String, String>> thumbnails) {
		this.thumbnails = thumbnails;
	}
}
