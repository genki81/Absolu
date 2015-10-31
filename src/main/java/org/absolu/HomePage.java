package org.absolu;

import org.absolu.battle.api.pojo.Guilde;
import org.absolu.dao.GuildeDao;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 4825705566528840788L;
	private Component headerPanel;
	private Component mainPanel;
	private Component youtubePanel;
	private Component footerPanel;

	private final GuildeDao gDao;

	public HomePage(final PageParameters parameters) {
		super(parameters);
		gDao = new GuildeDao();

		Guilde g = ((WicketApplication) getApplication()).getBattleApiUtils().getGuilde();
		((WicketApplication) getApplication()).setGuilde(g);
		gDao.saveGuilde(g);

		add(headerPanel = new HeaderPanel("headerPanel"));
		add(mainPanel = new MainPanel("mainPanel"));
		add(youtubePanel = new YoutubePanel("youtubePanel"));
		add(footerPanel = new FooterPanel("footerPanel"));
	}

}
