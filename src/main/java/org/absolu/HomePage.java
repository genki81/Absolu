package org.absolu;

import org.absolu.battle.api.pojo.Guilde;
import org.absolu.battle.api.pojo.Membre;
import org.absolu.decoration.AlternateRowCssClassAttributeAppender;
import org.absolu.decoration.ClasseClassAttributeAppender;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		super(parameters);

		// add(new Label("version", getApplication().getFrameworkSettings()
		// .getVersion()));

		Guilde g = ((WicketApplication) getApplication()).getGuilde();
		add(new Label("nbMembres", g != null ? g.getMembers().size() : 0));

		ListView<Membre> members = new ListView<Membre>("listeMembres", g.getMembers()) {
			@Override
			protected void populateItem(ListItem<Membre> item) {
				item.add(new AlternateRowCssClassAttributeAppender(item.getIndex(), "evenRow", "oddRow"));

				final Membre membre = item.getModelObject();

				Label lNom = new Label("nomMembre", new AbstractReadOnlyModel<String>() {
					@Override
					public String getObject() {
						return membre.getCharacter().getName();
					}
				});
				lNom.add(new ClasseClassAttributeAppender(membre.getCharacter().getClasse(), "color-c"));
				item.add(lNom);

				item.add(new Label("classeMembre", new AbstractReadOnlyModel<String>() {
					@Override
					public String getObject() {
						return ((WicketApplication) getApplication()).getBattleApiUtils()
								.getClasseById(membre.getCharacter().getClasse()).getName();
					}
				}));

				item.add(new Label("rangMembre", new AbstractReadOnlyModel<Integer>() {
					@Override
					public Integer getObject() {
						return membre.getRank();
					}
				}));

			}
		};
		add(members);

	}
}
