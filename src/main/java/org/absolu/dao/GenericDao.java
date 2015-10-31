package org.absolu.dao;

import java.io.Serializable;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class GenericDao implements Serializable {
	private static final long serialVersionUID = -4515057380924821591L;

	@SpringBean(name = "mongoConnection")
	protected MongoConnection connection;

	public GenericDao() {
		Injector.get().inject(this);
	}
}
