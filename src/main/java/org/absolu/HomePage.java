package org.absolu;

import org.absolu.battle.api.pojo.Guilde;
import org.absolu.battle.api.utils.BattleApiUtils;
import org.absolu.dao.GuildeDao;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 4825705566528840788L;

	private final GuildeDao gDao;

	public HomePage(final PageParameters parameters) {
		super(parameters);
		gDao = new GuildeDao();

		Guilde g = BattleApiUtils.getGuilde();
		gDao.saveGuilde(g);

		BattleApiUtils.handleEmblem(((WicketApplication) getApplication()).getRealPathRoot(), g);

		IModel<Guilde> gModel = new Model<Guilde>();
		gModel.setObject(g);

		add(new HeaderPanel("headerPanel", gModel));
		add(new MainPanel("mainPanel", gModel));
		add(new YoutubePanel("youtubePanel"));
		add(new FooterPanel("footerPanel", gModel));
	}

}
