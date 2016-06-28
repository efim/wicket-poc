package xyz.restinmotion;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class BaseLayout extends WebPage {

	protected static final String CONTENT_ID = "contentComponent";
	private static final long serialVersionUID = 1L;
	private Component headerPanel;
	private Component menuPanel;

	public BaseLayout(final PageParameters parameters) {
		super(parameters);

		this.add(new FeedbackPanel("feedback"));

		this.add(headerPanel = new HeaderPanel("headerPanel"));
		this.add(menuPanel = new NavigationPanel("navigationPanel"));
		this.add(new Label(CONTENT_ID, "hello"));
    }


	public Component getMenuPanel() {
		return menuPanel;
	}

	public Component getHeaderPanel() {
		return headerPanel;
	}
}
