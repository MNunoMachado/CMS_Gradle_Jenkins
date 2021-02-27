/*
 * Copyright 2008 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package pt.isep.cms.client;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * <p>
 * A widget used to show panels in the ContentPanel.
 * </p>
 */
public abstract class ContentWidget extends SimpleLayoutPanel implements HasValueChangeHandlers<String> {

	/**
	 * Get the simple filename of a class.
	 * 
	 * @param c
	 *            the class
	 */
	protected static String getSimpleName(Class<?> c) {
		String name = c.getName();
		return name.substring(name.lastIndexOf(".") + 1);
	}

	/**
	 * A description of the panel.
	 */
	private final SafeHtml description;

	/**
	 * The name of the panel.
	 */
	private final String name;

	/**
	 * The view that holds the name, description, and panel.
	 */
	private ContentWidgetView view;

	/**
	 * Construct a {@link ContentWidget}.
	 *
	 * ATB
	 * 
	 * @param name
	 *            the text name of the panel
	 * @param description
	 *            a text description of the panel
	 */
	public ContentWidget(String name, String description) {
		this(name, SafeHtmlUtils.fromString(description));
	}

	/**
	 * Construct a {@link ContentWidget}.
	 * 
	 * ATB
	 * 
	 * @param name
	 *            the text name of the panel
	 * @param description
	 *            a safe html description of the panel
	 */
	public ContentWidget(String name, SafeHtml description) {
		this.name = name;
		this.description = description;
	}

	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {
		return addHandler(handler, ValueChangeEvent.getType());
	}

	/**
	 * Get the description of this panel.
	 * 
	 * @return a description for this panel
	 */
	public final SafeHtml getDescription() {
		return description;
	}

	/**
	 * Get the name of this panel to use as a title.
	 * 
	 * @return a name for this panel
	 */
	public final String getName() {
		return name;
	}

	/**
	 * Check if the widget should have margins.
	 * 
	 * @return true to use margins, false to flush against edges
	 */
	public boolean hasMargins() {
		return true;
	}

	/**
	 * Check if the widget should be wrapped in a scrollable div.
	 * 
	 * @return true to use add scrollbars, false not to
	 */
	public boolean hasScrollableContent() {
		return true;
	}

	/**
	 * When the widget is first initialized, this method is called. If it returns a
	 * Widget, the widget will be added as the first tab. Return null to disable the
	 * first tab.
	 * 
	 * @return the widget to add to the first tab
	 */
	public abstract Widget onInitialize();

	@Override
	protected void onLoad() {
		if (view == null) {
			view = new ContentWidgetView(hasMargins(), hasScrollableContent());
			view.setName(getName());
			view.setDescription(getDescription());
			setWidget(view);
		}
		Widget widget = onInitialize(); // (ATB)
		if (widget != null) {
			view.setCwPanel(widget);
		}

		// (ATB) removed
		// ensureWidgetInitialized();
		super.onLoad();
	}
}
