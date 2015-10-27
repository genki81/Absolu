package org.absolu.decoration;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.model.AbstractReadOnlyModel;

public class AlternateRowCssClassAttributeAppender extends AttributeAppender {

	private static final long serialVersionUID = -4348060084145567203L;
	public static final String ATTRIBUTE_NAME = "class";
	public static final String VALUE_SEPARATOR = " ";

	public AlternateRowCssClassAttributeAppender(final int index, final String evenRowCssClass,
			final String oddRowCssClass) {
		super(ATTRIBUTE_NAME, new AbstractReadOnlyModel<String>() {
			private static final long serialVersionUID = 2166092070016230752L;

			@Override
			public String getObject() {
				// Our algorithm
				return (index % 2 == 0) ? evenRowCssClass : oddRowCssClass;
			}
		}, VALUE_SEPARATOR);
	}

}