package org.absolu.battle.api.pojo.display;

import java.io.Serializable;

import org.absolu.battle.api.pojo.Membre;
import org.absolu.battle.api.pojo.Personnage;
import org.absolu.battle.api.utils.BattleApiUtils;

public class PersonnageDisplay implements Serializable {
	private static final long serialVersionUID = -8076647542295668770L;

	private String name = "";
	private String rang = "";
	private String classe = "";
	private int classId = -1;
	private String level = "";
	private String iLvl = "";
	private String specIcon = "";
	private String role = "";

	public void init(Membre m, Personnage p) {
		if (m != null) {
			rang = Integer.toString(m.getRank());
			if (m.getCharacter() != null) {
				name = m.getCharacter().getName();
				classId = m.getCharacter().getClasse();
				classe = BattleApiUtils.getClasseById(classId).getName();
				if (m.getCharacter().getSpec() != null) {
					specIcon = m.getCharacter().getSpec().getIcon();
					role = m.getCharacter().getSpec().getRole();
				}
			}
		}
		if (p != null) {
			level = Integer.toString(p.getLevel());
			if (p.getItems() != null) {
				iLvl = Long.toString(p.getItems().getAverageItemLevelEquipped());
			}
		}
	}

	public String getName() {
		return name;
	}

	public String getRang() {
		return rang;
	}

	public String getClasse() {
		return classe;
	}

	public String getLevel() {
		return level;
	}

	public String getiLvl() {
		return iLvl;
	}

	public int getClassId() {
		return classId;
	}

	public String getSpecIcon() {
		return specIcon;
	}

	public String getRole() {
		return role;
	}

	@Override
	public String toString() {
		return "Name " + name + " / Rang " + rang + " / Classe " + classe + " / ClassId" + classId + " / Level "
				+ level + " / iLvl " + iLvl;
	}

}
