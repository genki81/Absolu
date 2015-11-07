package org.absolu;

import java.text.ParseException;
import java.util.Date;

import org.absolu.youtube.YoutubeFeed;
import org.absolu.youtube.pojo.Item;
import org.absolu.youtube.pojo.PlaylistItems;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.springframework.format.datetime.DateFormatter;

public class YoutubePanel extends Panel {
	private static final long serialVersionUID = 3471590747420584860L;
	private static final Logger LOGGER = LogManager.getLogger(YoutubePanel.class);

	public YoutubePanel(final String id) {
		super(id);
		final YoutubeFeed yf = new YoutubeFeed();
		PlaylistItems pi = yf.getPlaylistItems();

		ListView<Item> items = new ListView<Item>("listeVideos", pi.getItems()) {
			private static final long serialVersionUID = -245304921490379975L;

			@Override
			protected void populateItem(ListItem<Item> item) {
				final Item yItem = item.getModelObject();
				item.add(new Label("descVideo", new AbstractReadOnlyModel<String>() {
					private static final long serialVersionUID = 1724376704561112788L;

					@Override
					public String getObject() {
						return yItem.getSnippet().getTitle();
					}
				}));

				item.add(new Label("dtVideo", new AbstractReadOnlyModel<String>() {
					private static final long serialVersionUID = 1724376704561112788L;

					@Override
					public String getObject() {
						DateFormatter dfIn = new DateFormatter("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
						try {
							Date d = dfIn.parse(yItem.getSnippet().getPublishedAt(), getLocale());
							DateFormatter dfOut = new DateFormatter("dd/MM/yyyy HH:mm:ss");
							return dfOut.print(d, getLocale());
						} catch (ParseException e) {
							LOGGER.error(e);
						}
						return "err";
					}
				}));

				Image tbVideo = new Image("tbVideo", (String) null);
				tbVideo.add(new AttributeModifier("src", new AbstractReadOnlyModel<String>() {
					private static final long serialVersionUID = 5957426915452923269L;

					@Override
					public String getObject() {
						return yItem.getSnippet().getThumbnails().get("default").get("url");
					}
				}));
				tbVideo.setOutputMarkupId(true);

				ExternalLink idVideo = new ExternalLink("linkVideo", new AbstractReadOnlyModel<String>() {
					private static final long serialVersionUID = 2049897899050539512L;

					@Override
					public String getObject() {
						return yf.generateLink(yItem);
					}
				});

				idVideo.add(tbVideo);
				item.add(idVideo);

			}
		};
		add(items);

		LOGGER.debug(pi.getItems().get(0).getSnippet().getThumbnails().get("default").get("url"));
	}
}
