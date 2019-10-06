package com.pradheep.web.common;

public class ApplicationConstants {
	public static int PAGE_SIZE = 3;
	public static String PARA_START_DELIMITER = "##$";
	public static String PARA_END_DELIMITER = "###";
	public static String SECRET_KEY_CAPTCHA = "secretkey_captcha";

	// -----------------------------------------//
	// Pagination
	public static int NUMBER_OF_RECORDS_PER_PAGE = 3;
	// --------------------------------------//
	public static int USER_ALREADY_EXISTS = 1;
	public static int NEW_USER_ADDED = 2;

	public static String FROM_EMAIL_ADDRESS = "administrator@praiseyourredeemer.org";

	public static String ENG_LANG = "English";
	public static String TA_LANG = "Tamil";

	public static int MAX_QUESTIONS_PER_QUIZ = 10;

	public static int MAX_TIME_NORMAL_QUIZ = 300;// Seconds
	public static int MAX_TIME_ADVANCED_QUIZ = 150;// Seconds

	public static String NOTIFICATION_JOB_COMPLETE = "Complete";
	public static String NOTIFICATION_JOB_YET_TO_START = "Yet to start";
	public static String NOTIFICATION_JOB_IN_PROGRESS = "InProgress";

}
