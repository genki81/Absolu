package org.absolu;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;

import org.absolu.batch.MembersTask;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 *
 * @see org.absolu.Start#main(String[])
 */
public class WicketApplication extends WebApplication {
	private final static Logger logger = LoggerFactory.getLogger(WicketApplication.class);

	private String realPathRoot;

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
		logger.info("Initialisation de WicketApplication");
		logger.info("Spring");
		getComponentInstantiationListeners().add(new SpringComponentInjector(this));
		logger.info("Le reste");
		realPathRoot = getServletContext().getRealPath("/");
		initTasks();
	}

	private void initTasks() {
		logger.info("Initialisation des Tasks");
		Timer time = new Timer();
		MembersTask mt = new MembersTask(this);

		GregorianCalendar cal = new GregorianCalendar();
		// cal.setTimeInMillis(cal.getTimeInMillis() + 15000);

		if (cal.get(Calendar.HOUR_OF_DAY) >= 20) {
			cal.setTimeInMillis(cal.getTimeInMillis() + 86400000);
		}
		cal.set(Calendar.HOUR_OF_DAY, 20);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		Date next = new Date(cal.getTimeInMillis());
		time.schedule(mt, next, 1000 * 60 * 60 * 24);
	}

	public String getRealPathRoot() {
		return realPathRoot;
	}

}
