package org.absolu.ajax;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.absolu.battle.api.pojo.display.PersonnageDisplay;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.springframework.util.StringUtils;

public class PersonnageDisplayProvider extends SortableDataProvider<PersonnageDisplay, String> {

	private static final long serialVersionUID = -3545343466421312745L;

	class SortableDataProviderComparator implements Comparator<PersonnageDisplay>, Serializable {
		private static final long serialVersionUID = -7407895308171058855L;

		@Override
		public int compare(final PersonnageDisplay o1, final PersonnageDisplay o2) {
			PropertyModel<String> model1 = new PropertyModel<String>(o1, getSort().getProperty());
			PropertyModel<String> model2 = new PropertyModel<String>(o2, getSort().getProperty());

			int result;
			if ("level".equals(getSort().getProperty()) || "iLvl".equals(getSort().getProperty())) {
				int i1 = (StringUtils.isEmpty(model1.getObject())) ? 0 : Integer.valueOf(model1.getObject());
				int i2 = (StringUtils.isEmpty(model2.getObject())) ? 0 : Integer.valueOf(model2.getObject());
				result = Integer.compare(i1, i2);
			} else {
				result = model1.getObject().compareTo(model2.getObject());
			}

			if (!getSort().isAscending()) {
				result = -result;
			}

			return result;
		}
	}

	private List<PersonnageDisplay> list = new ArrayList<PersonnageDisplay>();
	private final SortableDataProviderComparator comparator = new SortableDataProviderComparator();

	public PersonnageDisplayProvider(List<PersonnageDisplay> list) {
		// The default sorting
		setSort("name", SortOrder.ASCENDING);

		this.list = list;
	}

	@Override
	public IModel<PersonnageDisplay> model(final PersonnageDisplay object) {
		return new AbstractReadOnlyModel<PersonnageDisplay>() {
			private static final long serialVersionUID = -5141683923591794437L;

			@Override
			public PersonnageDisplay getObject() {
				return object;
			}
		};
	}

	@Override
	public long size() {
		return list.size();
	}

	@Override
	public Iterator<? extends PersonnageDisplay> iterator(long first, long count) {
		// In this example the whole list gets copied, sorted and sliced; in
		// real applications typically your database would deliver a sorted and
		// limited list

		// Get the data
		List<PersonnageDisplay> newList = new ArrayList<PersonnageDisplay>(list);

		// Sort the data
		Collections.sort(newList, comparator);

		// Return the data for the current page - this can be determined only
		// after sorting
		return newList.subList(new Long(first).intValue(), new Long(first + count).intValue()).iterator();
	}

}