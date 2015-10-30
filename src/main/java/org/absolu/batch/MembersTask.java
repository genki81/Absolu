package org.absolu.batch;

import java.util.Date;
import java.util.TimerTask;

import org.absolu.WicketApplication;
import org.absolu.battle.api.pojo.Guilde;
import org.absolu.battle.api.pojo.Membre;
import org.absolu.battle.api.pojo.Personnage;
import org.absolu.battle.api.utils.BattleApiUtils;
import org.absolu.dao.CharacterDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MembersTask extends TimerTask {
	private final static Logger logger = LoggerFactory.getLogger(MembersTask.class);
	private final BattleApiUtils battleApiUtils;

	private final CharacterDao cDao;

	public MembersTask(WicketApplication application) {
		battleApiUtils = new BattleApiUtils(application);
		cDao = new CharacterDao();
	}

	@Override
	public void run() {
		Date now = new Date();
		System.out.println("Time is :" + now);
		cDao.cleanCharacters();
		Guilde g = battleApiUtils.getGuilde();
		for (Membre m : g.getMembers()) {
			logger.info("Mise à jour du personnage " + m.getCharacter().getName() + "-" + m.getCharacter().getRealm());
			Personnage p = battleApiUtils.getPersonnage(m.getCharacter().getName(), m.getCharacter().getRealm());
			cDao.saveCharacter(p);
			break;
		}
	}
}