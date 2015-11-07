package org.absolu;

import java.util.Map;

import org.absolu.battle.api.pojo.Guilde;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FooterPanel extends Panel {
	private static final long serialVersionUID = -8528250360853009285L;
	private static final Logger logger = LoggerFactory.getLogger(FooterPanel.class);

	public FooterPanel(final String id, IModel<Guilde> model) {
		super(id, model);
		Guilde g = (Guilde) getDefaultModelObject();
		final WebMarkupContainer divtest = new WebMarkupContainer("ftDiv");
		divtest.add(new AttributeAppender("style", "background-color: #"
				+ g.getEmblem().getBackgroundColor().substring(2)));
		divtest.setOutputMarkupId(true);

		Form<FooterPanel> form = new Form<FooterPanel>("formTest");
		Button bTest = new Button("testButton") {
			private static final long serialVersionUID = 7579032437203363216L;

			@Override
			public void onSubmit() {
				logger.info("button2.onSubmit executed");
				LoggerContext logContext = (LoggerContext) LogManager.getContext(false);
				Map<String, LoggerConfig> map = logContext.getConfiguration().getLoggers();
				logger.debug("Avant");
				for (LoggerConfig c : map.values()) {
					if (Level.OFF.intLevel() == c.getLevel().intLevel()) {
						c.setLevel(Level.DEBUG);
					} else {
						c.setLevel(Level.OFF);
					}
				}
				logger.debug("Après");
			}
		};
		bTest.setDefaultFormProcessing(false);
		form.add(bTest);

		divtest.add(form);

		add(divtest);
	}
}
