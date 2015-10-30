package org.absolu.dao;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class GenericDao {
	@SpringBean(name = "mongoConnection")
	protected MongoConnection connection;

	public GenericDao() {
		Injector.get().inject(this);
	}
}
