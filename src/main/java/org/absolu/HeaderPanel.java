package org.absolu;

import org.absolu.battle.api.constants.BattleApiConstants;
import org.absolu.battle.api.pojo.Embleme;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
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

		final Embleme e = ((WicketApplication) getApplication()).getGuilde().getEmblem();
		Image tabard = new Image("logo", (String) null);
		tabard.add(new AttributeModifier("src", new AbstractReadOnlyModel<String>() {
			private static final long serialVersionUID = -9017753636911116526L;

			@Override
			public String getObject() {
				return BattleApiConstants.getEmbleme(e);
			}
		}));
		tabard.setOutputMarkupId(true);
		hdDiv.add(tabard);

		final WebMarkupContainer hdTitle = new WebMarkupContainer("hdTitle");
		hdTitle.add(new AttributeAppender("style", "color: #"
				+ ((WicketApplication) getApplication()).getGuilde().getEmblem().getIconColor().substring(2)));
		hdTitle.setOutputMarkupId(true);
		hdDiv.add(hdTitle);
	}
}
