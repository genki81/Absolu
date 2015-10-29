package org.absolu;

import java.util.List;

import javax.servlet.ServletContext;

import org.absolu.battle.api.pojo.Classe;
import org.absolu.battle.api.pojo.Guilde;
import org.absolu.battle.api.pojo.Race;
import org.absolu.battle.api.utils.BattleApiUtils;
import org.absolu.dao.MongoDao;
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

	private MongoDao mongoDao;

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

		ServletContext sc = getServletContext();
		String mongoHost = sc.getInitParameter("mongoHost");
		String mongoSPort = sc.getInitParameter("mongoPort");
		String mongoUser = sc.getInitParameter("mongoUser");
		String mongoPwd = sc.getInitParameter("mongoPwd");
		String mongoDbName = sc.getInitParameter("mongoDb");

		mongoDao = new MongoDao(mongoHost, mongoSPort, mongoUser, mongoPwd, mongoDbName);
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

	public MongoDao getMongoDao() {
		return mongoDao;
	}

}
