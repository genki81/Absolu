package org.absolu;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FooterPanel extends Panel {
	private static final long serialVersionUID = -8528250360853009285L;
	private static final Logger logger = LoggerFactory.getLogger(FooterPanel.class);

	public FooterPanel(final String id) {
		super(id);
		final WebMarkupContainer divtest = new WebMarkupContainer("ftDiv");
		divtest.add(new AttributeAppender("style", "background-color: #"
				+ ((WicketApplication) getApplication()).getGuilde().getEmblem().getBackgroundColor().substring(2)));
		divtest.setOutputMarkupId(true);
		add(divtest);
	}
}
