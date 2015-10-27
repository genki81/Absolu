package org.absolu.decoration;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.model.AbstractReadOnlyModel;

public class ClasseClassAttributeAppender extends AttributeAppender {

	private static final long serialVersionUID = 7368422455345604032L;
	public static final String ATTRIBUTE_NAME = "class";
	public static final String VALUE_SEPARATOR = " ";

	public ClasseClassAttributeAppender(final int classId, final String rootClassName) {
		super(ATTRIBUTE_NAME, new AbstractReadOnlyModel<String>() {
			private static final long serialVersionUID = -5397170503495284910L;

			@Override
			public String getObject() {
				// Our algorithm
				return rootClassName + classId;
			}
		}, VALUE_SEPARATOR);
	}

}
