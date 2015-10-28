package org.absolu.decoration;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.model.AbstractReadOnlyModel;

public class RoleAttributeAppender extends AttributeAppender {

	private static final long serialVersionUID = 7368422455345604032L;
	public static final String ATTRIBUTE_NAME = "class";
	public static final String VALUE_SEPARATOR = " ";

	public RoleAttributeAppender(final String roleName, final String rootRoleName) {
		super(ATTRIBUTE_NAME, new AbstractReadOnlyModel<String>() {
			private static final long serialVersionUID = -1707492117236624164L;

			@Override
			public String getObject() {
				return rootRoleName + roleName.toLowerCase();
			}
		}, VALUE_SEPARATOR);
	}

}
