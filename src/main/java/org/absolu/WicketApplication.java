package org.absolu;

import java.util.List;

import org.absolu.battle.api.pojo.Classe;
import org.absolu.battle.api.pojo.Guilde;
import org.absolu.battle.api.pojo.Race;
import org.absolu.battle.api.utils.BattleApiUtils;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 *
 * @see org.absolu.Start#main(String[])
 */
public class WicketApplication extends WebApplication {
	private BattleApiUtils battleApiUtils;
	private List<Race> races;
	private List<Classe> classes;
	private Guilde guilde;

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage() {
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init() {
		super.init();
		battleApiUtils = new BattleApiUtils(this);
		races = battleApiUtils.getListRaces();
		classes = battleApiUtils.getListClasses();
	}

	public List<Race> getRaces() {
		return races;
	}

	public Guilde getGuilde() {
		return guilde;
	}

	public void setGuilde(Guilde guilde) {
		this.guilde = guilde;
	}

	public List<Classe> getClasses() {
		return classes;
	}

	public BattleApiUtils getBattleApiUtils() {
		return battleApiUtils;
	}

}
