package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

import utils.UserTalkativeComparator;
import utils.UserSocialComparator;

public class Leaderboard extends Controller {

	public static void index() {
		String userId = session.get("logged_in_userid");
		if (userId != null) {
			List<User> users = User.findAll();
			render(users);
		} else {
			Accounts.index();
		}
	}

	public static void sortByFriends() {
		List<User> users = User.findAll();
		Collections.sort(users, new UserSocialComparator());
		render(users);

	}

	public static void sortByMessages() {
		List<User> users = User.findAll();
		Collections.sort(users, new UserTalkativeComparator());
		render(users);
	}

}