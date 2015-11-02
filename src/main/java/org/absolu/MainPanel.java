package org.absolu;

import org.absolu.battle.api.constants.BattleApiConstants;
import org.absolu.battle.api.pojo.Guilde;
import org.absolu.battle.api.pojo.Membre;
import org.absolu.battle.api.pojo.Objets;
import org.absolu.battle.api.pojo.Personnage;
import org.absolu.battle.api.utils.BattleApiUtils;
import org.absolu.dao.CharacterDao;
import org.absolu.decoration.AlternateRowCssClassAttributeAppender;
import org.absolu.decoration.ClasseClassAttributeAppender;
import org.absolu.decoration.RoleAttributeAppender;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainPanel extends Panel {
	private static final long serialVersionUID = 5865383827383917779L;

	private static final Logger logger = LoggerFactory.getLogger(MainPanel.class);

	private CharacterDao cDao;

	public MainPanel(final String id, IModel<Guilde> model) {
		super(id, model);
		cDao = new CharacterDao();

		Guilde g = (Guilde) getDefaultModelObject();

		add(new Label("nbMembres", g != null ? g.getMembers().size() : 0));

		ListView<Membre> members = new ListView<Membre>("listeMembres", g.getMembers()) {
			private static final long serialVersionUID = 4152985529724487708L;

			@Override
			protected void populateItem(ListItem<Membre> item) {
				item.add(new AlternateRowCssClassAttributeAppender(item.getIndex(), "evenRow", "oddRow"));

				final Membre membre = item.getModelObject();
				final Personnage p;
				if (membre != null && membre.getCharacter() != null) {
					Personnage pTmp = cDao.findCharacter(membre.getCharacter().getRealm(), membre.getCharacter()
							.getName());
					if (pTmp == null) {
						pTmp = BattleApiUtils.getPersonnage(membre.getCharacter().getName(), membre.getCharacter()
								.getRealm());
						if (pTmp == null) {
							pTmp = new Personnage();
							pTmp.setName(membre.getCharacter().getName());
							pTmp.setRealm(membre.getCharacter().getRealm());
							cDao.saveCharacter(pTmp);
						}
					}
					p = pTmp;
				} else {
					p = new Personnage();
					p.setItems(new Objets());
				}

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
						return BattleApiUtils.getClasseById(membre.getCharacter().getClasse()).getName();
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

				item.add(new Label("lvlMembre", new AbstractReadOnlyModel<Integer>() {
					private static final long serialVersionUID = -6668800430818030769L;

					@Override
					public Integer getObject() {
						if (p != null && p.getItems() != null) {
							return p.getLevel();
						} else {
							return null;
						}
					}
				}));

				item.add(new Label("iLvlMembre", new AbstractReadOnlyModel<Long>() {
					private static final long serialVersionUID = 3839836447777095699L;

					@Override
					public Long getObject() {
						if (p != null && p.getItems() != null) {
							return p.getItems().getAverageItemLevelEquipped();
						} else {
							return null;
						}
					}
				}));
			}
		};
		add(members);

	}
}
