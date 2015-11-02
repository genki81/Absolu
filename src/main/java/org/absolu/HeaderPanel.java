package org.absolu;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeaderPanel extends Panel {
	private static final long serialVersionUID = 6316596465584278406L;
	private static final Logger logger = LoggerFactory.getLogger(HeaderPanel.class);

	public HeaderPanel(final String id) {
		super(id);

		final WebMarkupContainer hdDiv = new WebMarkupContainer("hdDiv");
		hdDiv.add(new AttributeAppender("style", "background-color: #"
				+ ((WicketApplication) getApplication()).getGuilde().getEmblem().getBackgroundColor().substring(2)));
		hdDiv.setOutputMarkupId(true);
		add(hdDiv);

		final WebMarkupContainer hdTitle = new WebMarkupContainer("hdTitle");
		hdTitle.add(new AttributeAppender("style", "color: #"
				+ ((WicketApplication) getApplication()).getGuilde().getEmblem().getIconColor().substring(2)));
		hdTitle.setOutputMarkupId(true);
		hdDiv.add(hdTitle);
	}
}
