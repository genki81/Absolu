package org.absolu;

import org.absolu.battle.api.pojo.Faction;
import org.absolu.battle.api.pojo.Guilde;
import org.absolu.battle.api.utils.BattleApiUtils;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeaderPanel extends Panel {
	private static final long serialVersionUID = 6316596465584278406L;
	private static final Logger logger = LoggerFactory.getLogger(HeaderPanel.class);

	public HeaderPanel(final String id, IModel<Guilde> model) {
		super(id, model);

		Guilde g = (Guilde) getDefaultModelObject();

		final WebMarkupContainer hdDiv = new WebMarkupContainer("hdDiv");
		hdDiv.add(new AttributeAppender("style", "background-color: #"
				+ g.getEmblem().getBackgroundColor().substring(2)));
		hdDiv.setOutputMarkupId(true);
		add(hdDiv);

		final WebMarkupContainer hdTitle = new WebMarkupContainer("hdTitle");
		hdTitle.add(new AttributeAppender("style", "color: #" + g.getEmblem().getIconColor().substring(2)));
		hdTitle.setOutputMarkupId(true);
		hdDiv.add(hdTitle);

		WebMarkupContainer container = new WebMarkupContainer("canvas");
		container.setOutputMarkupId(true);
		container.add(new AttributeAppender("width", 120));
		container.add(new AttributeAppender("height", 120));
		hdDiv.add(container);
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		response.render(OnDomReadyHeaderItem.forScript(getJavascriptCall()));
	}

	private String getJavascriptCall() {
		Guilde g = (Guilde) getDefaultModelObject();

		String emblemColor = BattleApiUtils.getColorListFromHex(g.getEmblem().getIconColor());
		String borderColor = BattleApiUtils.getColorListFromHex(g.getEmblem().getBorderColor());
		String bgColor = BattleApiUtils.getColorListFromHex(g.getEmblem().getBackgroundColor());

		StringBuilder sb = new StringBuilder();
		sb.append("var tabardDraw = function() {\n");
		sb.append("	  var tabard = new GuildTabard(\n");
		sb.append("      'guild-tabard',\n");
		sb.append("      {\n");
		sb.append("        'ring': '").append(Faction.findById(g.getSide()).toLowerCase()).append("',\n");
		sb.append("        'bg': [ 0, ").append(bgColor).append(" ],\n");
		sb.append("        'border': [ ").append(g.getEmblem().getBorder()).append(", ").append(borderColor)
				.append(" ],\n");
		sb.append("        'emblem': [ ").append(g.getEmblem().getIcon()).append(", ").append(emblemColor)
				.append(" ]\n");
		sb.append("      }\n");
		sb.append("   );\n");
		sb.append("};\n");

		sb.append("tabardDraw();");

		return sb.toString();
	}

}
