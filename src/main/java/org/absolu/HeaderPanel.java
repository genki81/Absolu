package org.absolu;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
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

		WebMarkupContainer container = new WebMarkupContainer("canvas");
		container.setOutputMarkupId(true);
		// container.add(new AttributeAppender("width", 120));
		// container.add(new AttributeAppender("height", 120));
		hdDiv.add(container);
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		// response.renderOnLoadJavaScript(getJavascriptCall());
		response.render(OnDomReadyHeaderItem.forScript(getJavascriptCall()));

		// include js file
		// response.renderJavaScriptReference();
	}

	private String getJavascriptCall() {
		// MyData data = getModel().getObject();
		StringBuilder sb = new StringBuilder();
		sb.append("var tabardDraw = function() { ").append("var tabard = new GuildTabard('guild-tabard', { ")
		.append("'ring': 'alliance', ").append("'bg': [ 0, 45 ], ").append("'border': [ 0, 16 ], ")
		.append("'emblem': [ 100, 16 ] ").append("}); }; ");

		sb.append("tabardDraw();");

		return sb.toString();
	}

}
