package org.absolu.youtube.pojo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaylistItems implements Serializable {
	private static final long serialVersionUID = -930465557204565380L;
	private List<Item> items;

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[ ");
		for (Item item : items) {
			sb.append(item.toString());
			sb.append(", ");
		}
		sb.append(" ]");
		return sb.toString();
	}
}
