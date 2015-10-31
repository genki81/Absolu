package org.absolu.youtube.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item implements Serializable {
	private static final long serialVersionUID = 7767330913821543612L;
	private Snippet snippet;
	private ContentDetails contentDetails;

	public Snippet getSnippet() {
		return snippet;
	}

	public void setSnippet(Snippet snippet) {
		this.snippet = snippet;
	}

	public ContentDetails getContentDetails() {
		return contentDetails;
	}

	public void setContentDetails(ContentDetails contentDetails) {
		this.contentDetails = contentDetails;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(getSnippet().getTitle()).append("/").append(getSnippet().getPublishedAt());
		return sb.toString();
	}
}
