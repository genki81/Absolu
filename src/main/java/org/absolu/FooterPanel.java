package org.absolu;

import org.absolu.battle.api.pojo.Guilde;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FooterPanel extends Panel {
	private static final long serialVersionUID = -8528250360853009285L;
	private static final Logger logger = LoggerFactory.getLogger(FooterPanel.class);

	public FooterPanel(final String id, IModel<Guilde> model) {
		super(id, model);
		Guilde g = (Guilde) getDefaultModelObject();
		final WebMarkupContainer divtest = new WebMarkupContainer("ftDiv");
		divtest.add(new AttributeAppender("style", "background-color: #"
				+ g.getEmblem().getBackgroundColor().substring(2)));
		divtest.setOutputMarkupId(true);
		add(divtest);
	}
}
