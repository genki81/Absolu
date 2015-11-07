package org.absolu.youtube;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.absolu.youtube.pojo.Channel;
import org.absolu.youtube.pojo.Item;
import org.absolu.youtube.pojo.PlaylistItems;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

public class YoutubeFeed implements Serializable {
	private static final long serialVersionUID = 7520054289626100922L;
	private final static Logger LOGGER = LogManager.getLogger(YoutubeFeed.class);
	private final static String ABSOLU_CHANNEL_ID = "UCpYI1yr-kZaut6Iw_InrYLw";
	private final static String ELEMETH_CHANNEL_ID = "UCo2pZbiSILOhFxJ-REiFkvw";

	private final static List<String> CHANNELS = Collections.unmodifiableList(Arrays.asList(new String[] {
			ABSOLU_CHANNEL_ID, ELEMETH_CHANNEL_ID }));

	private final static String API_KEY = "AIzaSyAOg1yb4Qh44z5gi3jJU7C3ajWtjiWu15E";
	private final static String YOUTUBE_PL_ID_URL = "https://www.googleapis.com/youtube/v3/channels?part=contentDetails&key="
			+ API_KEY + "&id=";
	private final static String YOUTUBE_VID_URL = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet,contentDetails&key="
			+ API_KEY + "&playlistId=";
	private final static String YOUTUBE_URL = "https://www.youtube.com/watch?v=";

	private String queryYoutube(String url) {
		StringBuilder builder = new StringBuilder();
		HttpClient client = HttpClientBuilder.create().build();
		try {
			HttpGet httpGet = new HttpGet(URIUtil.encodeQuery(url));
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(content, "UTF-8"));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			} else {
				LOGGER.error("Failed to read channel");
			}
		} catch (IOException e) {
			LOGGER.error(e);
		}
		return builder.toString();
	}

	public PlaylistItems getPlaylistItems() {
		PlaylistItems pi = new PlaylistItems();
		pi.setItems(new ArrayList<Item>());
		for (String channel : CHANNELS) {
			Channel c = getChannel(channel);
			PlaylistItems tmp = new PlaylistItems();
			for (Item i : c.getItems()) {
				if (i.getContentDetails() != null && i.getContentDetails().getRelatedPlaylists() != null
						&& i.getContentDetails().getRelatedPlaylists().getUploads() != null) {
					String json = queryYoutube(YOUTUBE_VID_URL
							+ i.getContentDetails().getRelatedPlaylists().getUploads());
					LOGGER.debug(json);
					try {
						ObjectMapper mapper = new ObjectMapper();
						ObjectReader reader = mapper.readerFor(PlaylistItems.class);
						tmp = reader.readValue(json);
						pi.getItems().addAll(tmp.getItems());
						break;
					} catch (Exception e) {
						LOGGER.error(e);
					}
				}
			}
		}
		LOGGER.debug(pi.toString());
		Collections.sort(pi.getItems(), new Comparator<Item>() {

			@Override
			public int compare(Item i1, Item i2) {
				String d1 = "";
				String d2 = "";
				if (i1 != null && i1.getSnippet() != null && i1.getSnippet().getPublishedAt() != null) {
					d1 = i1.getSnippet().getPublishedAt();
				}
				if (i2 != null && i2.getSnippet() != null && i2.getSnippet().getPublishedAt() != null) {
					d2 = i2.getSnippet().getPublishedAt();
				}
				return d2.compareTo(d1);
			}
		});
		LOGGER.debug(pi.toString());
		return pi;
	}

	private Channel getChannel(String channel) {
		String json = queryYoutube(YOUTUBE_PL_ID_URL + channel);
		LOGGER.debug(json);
		Channel c = new Channel();
		try {
			ObjectMapper mapper = new ObjectMapper();
			ObjectReader reader = mapper.readerFor(Channel.class);
			c = reader.readValue(json);

		} catch (Exception e) {
			LOGGER.error(e);
		}
		return c;
	}

	public String generateLink(Item i) {
		return YOUTUBE_URL
				+ ((i != null && i.getContentDetails() != null && i.getContentDetails().getVideoId() != null) ? i
						.getContentDetails().getVideoId() : "");
	}
}
