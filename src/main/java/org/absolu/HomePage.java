package org.absolu;

import org.absolu.battle.api.constants.BattleApiConstants;
import org.absolu.battle.api.pojo.Guilde;
import org.absolu.battle.api.pojo.Membre;
import org.absolu.dao.GuildeDao;
import org.absolu.decoration.AlternateRowCssClassAttributeAppender;
import org.absolu.decoration.ClasseClassAttributeAppender;
import org.absolu.decoration.RoleAttributeAppender;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(HomePage.class);

	private GuildeDao gDao;

	public HomePage(final PageParameters parameters) {
		super(parameters);
		gDao = new GuildeDao();

		Guilde g = ((WicketApplication) getApplication()).getBattleApiUtils().getGuilde();
		((WicketApplication) getApplication()).setGuilde(g);
		gDao.saveGuilde(g);

		add(new Label("nbMembres", g != null ? g.getMembers().size() : 0));

		ListView<Membre> members = new ListView<Membre>("listeMembres", g.getMembers()) {
			private static final long serialVersionUID = 4152985529724487708L;

			@Override
			protected void populateItem(ListItem<Membre> item) {
				item.add(new AlternateRowCssClassAttributeAppender(item.getIndex(), "evenRow", "oddRow"));

				final Membre membre = item.getModelObject();
				// TODO
				/*
				 * membre.setCharacter(((WicketApplication)
				 * getApplication()).getBattleApiUtils().getPersonnage(
				 * membre.getCharacter().getName(),
				 * membre.getCharacter().getRealm()));
				 */

				Label lNom = new Label("nomMembre", new AbstractReadOnlyModel<String>() {
					private static final long serialVersionUID = -5315385723861780092L;

					@Override
					public String getObject() {
						return membre.getCharacter().getName();
					}
				});
				lNom.add(new ClasseClassAttributeAppender(membre.getCharacter().getClasse(), "color-c"));
				item.add(lNom);

				item.add(new Label("classeMembre", new AbstractReadOnlyModel<String>() {
					private static final long serialVersionUID = -4445801448919143673L;

					@Override
					public String getObject() {
						return ((WicketApplication) getApplication()).getBattleApiUtils()
								.getClasseById(membre.getCharacter().getClasse()).getName();
					}
				}));

				item.add(new Label("rangMembre", new AbstractReadOnlyModel<Integer>() {
					private static final long serialVersionUID = -1600772145055858201L;

					@Override
					public Integer getObject() {
						return membre.getRank();
					}
				}));

				final String icon;
				final String role;
				if (membre != null && membre.getCharacter() != null && membre.getCharacter().getSpec() != null) {
					icon = membre.getCharacter().getSpec().getIcon();
					role = membre.getCharacter().getSpec().getRole();
				} else {
					icon = "";
					role = "";
				}
				Image imSpec = new Image("speMembre", (String) null);
				imSpec.add(new AttributeModifier("src", new AbstractReadOnlyModel<String>() {
					private static final long serialVersionUID = -1086531175105775913L;

					@Override
					public String getObject() {
						return BattleApiConstants.buildSpecIconUrl(icon);
					}
				}));
				imSpec.setOutputMarkupId(true);
				item.add(imSpec);

				Label roleMembre = new Label("roleMembre");
				roleMembre.add(new RoleAttributeAppender(role, "icon-"));
				item.add(roleMembre);
			}
		};
		add(members);

	}
}
