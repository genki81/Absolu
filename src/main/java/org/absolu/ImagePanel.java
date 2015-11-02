package org.absolu;

import org.absolu.battle.api.constants.BattleApiConstants;
import org.absolu.decoration.RoleAttributeAppender;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;

public class ImagePanel extends Panel {
	private static final long serialVersionUID = 9033615772648772161L;

	public ImagePanel(String id, final String icon, final String role) {
		super(id);

		Image imSpec = new Image("speMembre", (String) null);
		imSpec.add(new AttributeModifier("src", new AbstractReadOnlyModel<String>() {
			private static final long serialVersionUID = -1086531175105775913L;

			@Override
			public String getObject() {
				return BattleApiConstants.buildSpecIconUrl(icon);
			}
		}));
		imSpec.setOutputMarkupId(true);

		add(imSpec);

		Label roleMembre = new Label("roleMembre");
		roleMembre.add(new RoleAttributeAppender(role, "icon-"));
		add(roleMembre);

	}
}