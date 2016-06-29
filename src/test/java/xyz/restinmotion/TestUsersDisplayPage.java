package xyz.restinmotion;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import xyz.restinmotion.view.pages.UsersDisplayPage;

/**
 * Simple test using the WicketTester
 */
public class TestUsersDisplayPage
{
	private WicketTester tester;

	@Before
	public void setUp()
	{
		tester = new WicketTester(new WicketApplication());
	}

	@Test
	public void homepageRendersSuccessfully()
	{
		//start and render the test page
		tester.startPage(UsersDisplayPage.class);

		//assert rendered page class
		tester.assertRenderedPage(UsersDisplayPage.class);
	}
}
