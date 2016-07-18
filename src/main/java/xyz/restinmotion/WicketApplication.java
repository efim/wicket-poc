package xyz.restinmotion;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import xyz.restinmotion.data.Repository;
import xyz.restinmotion.data.UserData;
import xyz.restinmotion.view.pages.MainPage;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see xyz.restinmotion.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return MainPage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -10);
		Date reasonalbleBirthDate = calendar.getTime();

		Repository.getRepository().addUserData(
				new UserData("name1", "surname1", "non-enum =)", reasonalbleBirthDate, 36.6,
						Arrays.asList(new String[] {"Babyes", "Also non-enum =)"}), "Terminate"));

		Repository.getRepository().addUserData(
				new UserData("hello", "world", "non-enum =)", reasonalbleBirthDate, 36.6,
						Arrays.asList(new String[] {"Babyes", "Also non-enum =)"}), "Terminate"));


		// add your configuration here
	}
}
