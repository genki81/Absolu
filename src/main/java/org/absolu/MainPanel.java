package org.absolu;

import java.util.ArrayList;
import java.util.List;

import org.absolu.ajax.PersonnageDisplayProvider;
import org.absolu.battle.api.pojo.Guilde;
import org.absolu.battle.api.pojo.Membre;
import org.absolu.battle.api.pojo.Personnage;
import org.absolu.battle.api.pojo.display.PersonnageDisplay;
import org.absolu.battle.api.utils.BattleApiUtils;
import org.absolu.dao.CharacterDao;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.StringResourceModel;
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

		List<PersonnageDisplay> list = new ArrayList<PersonnageDisplay>();

		for (Membre m : g.getMembers()) {
			PersonnageDisplay pd = new PersonnageDisplay();
			Personnage p = cDao.findCharacter(m.getCharacter().getRealm(), m.getCharacter().getName());
			pd.init(m, p);
			list.add(pd);
		}

		BattleApiUtils.saveSpecImages(((WicketApplication) getApplication()).getRealPathRoot(), list);

		final PersonnageDisplayProvider personnageDisplayProvider = new PersonnageDisplayProvider(list);

		List<PropertyColumn<PersonnageDisplay, String>> columnList = new ArrayList<PropertyColumn<PersonnageDisplay, String>>();
		columnList.add(new PropertyColumn<PersonnageDisplay, String>(new StringResourceModel("nameTableHeaderLabel",
				this, null), "name", "name") {
			private static final long serialVersionUID = -7526790642463433263L;

			@Override
			public String getCssClass() {
				return "nomM";
			}

		});

		columnList.add(new PropertyColumn<PersonnageDisplay, String>(new StringResourceModel("classTableHeaderLabel",
				this, null), "classe", "classe") {
			private static final long serialVersionUID = 7017081357374826768L;

			@Override
			public String getCssClass() {
				return "classeM";
			}

		});

		columnList.add(new PropertyColumn<PersonnageDisplay, String>(new StringResourceModel("rangTableHeaderLabel",
				this, null), "rang", "rang") {
			private static final long serialVersionUID = 7192739144721938724L;

			@Override
			public String getCssClass() {
				return "rangM";
			}

		});

		columnList.add(new PropertyColumn<PersonnageDisplay, String>(new StringResourceModel("specTableHeaderLabel",
				this, null), "spec") {
			private static final long serialVersionUID = -960476384092642222L;

			@Override
			public void populateItem(Item<ICellPopulator<PersonnageDisplay>> item, String componentId,
					IModel<PersonnageDisplay> rowModel) {
				String icon = rowModel.getObject().getSpecIcon();
				String role = rowModel.getObject().getRole();
				item.add(new ImagePanel(componentId, icon, role));
			}

			@Override
			public String getCssClass() {
				return "speM";
			}

		});

		columnList.add(new PropertyColumn<PersonnageDisplay, String>(new StringResourceModel("lvlTableHeaderLabel",
				this, null), "level", "level") {
			private static final long serialVersionUID = 6311078913896962742L;

			@Override
			public String getCssClass() {
				return "niveauM";
			}

		});

		columnList.add(new PropertyColumn<PersonnageDisplay, String>(new StringResourceModel("iLvlTableHeaderLabel",
				this, null), "iLvl", "iLvl") {
			private static final long serialVersionUID = -5321671273596462765L;

			@Override
			public String getCssClass() {
				return "ilvlM";
			}

		});

		AjaxFallbackDefaultDataTable<PersonnageDisplay, String> table = new AjaxFallbackDefaultDataTable<PersonnageDisplay, String>(
				"listeMembres", columnList, personnageDisplayProvider, 500) {
			private static final long serialVersionUID = -6807614577580458469L;

			private int classId;

			@Override
			protected Item<PersonnageDisplay> newRowItem(String id, int index, IModel<PersonnageDisplay> model) {
				classId = model.getObject().getClassId();
				return super.newRowItem(id, index, model);
			}

			@Override
			protected Item<IColumn<PersonnageDisplay, String>> newCellItem(String id, int index,
					IModel<IColumn<PersonnageDisplay, String>> model) {
				Item<IColumn<PersonnageDisplay, String>> item = super.newCellItem(id, index, model);
				if (index == 0) {
					item.add(new AttributeAppender("class", "color-c" + classId, " "));
				}
				return item;
			}

		};
		table.setMarkupId("membresTable");

		add(table);

	}
}
