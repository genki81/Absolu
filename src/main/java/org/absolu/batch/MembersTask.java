package org.absolu.batch;

import java.util.Date;
import java.util.TimerTask;

import org.absolu.WicketApplication;
import org.absolu.battle.api.pojo.Guilde;
import org.absolu.battle.api.pojo.Membre;
import org.absolu.battle.api.pojo.Personnage;
import org.absolu.battle.api.utils.BattleApiUtils;
import org.absolu.dao.CharacterDao;
import org.absolu.spring.SpringContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class MembersTask extends TimerTask {
	private final static Logger logger = LoggerFactory.getLogger(MembersTask.class);

	private final CharacterDao cDao;

	private final MailSender mailSender;
	private final SimpleMailMessage templateMessage;

	public MembersTask(WicketApplication application) {
		cDao = new CharacterDao();
		mailSender = (MailSender) SpringContext.getApplicationContext().getBean("mailSender");
		templateMessage = (SimpleMailMessage) SpringContext.getApplicationContext().getBean("templateMessage");

	}

	@Override
	public void run() {
		Date now = new Date();
		StringBuffer sb = new StringBuffer();
		sb.append("Début :" + now + "\n");
		try {
			cDao.cleanCharacters();
			Guilde g = BattleApiUtils.getGuilde();
			for (Membre m : g.getMembers()) {
				sb.append("Mise à jour du personnage " + m.getCharacter().getName() + "-" + m.getCharacter().getRealm()
						+ "\n");
				Personnage p = BattleApiUtils.getPersonnage(m.getCharacter().getName(), m.getCharacter().getRealm());
				if (p != null) {
					cDao.saveCharacter(p);
				}
			}
		} catch (Exception e) {
			sb.append("Erreur : " + e.getMessage() + "\n");
		}
		now = new Date();
		sb.append("Fin :" + now + "\n");

		SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
		msg.setText(sb.toString());
		logger.info(sb.toString());
		try {
			this.mailSender.send(msg);
		} catch (MailException ex) {
			logger.error(ex.getMessage());
		}
	}

}