package br.ce.wcaquino.matchers;

import java.util.Calendar;

public class MatchersProject {
	public static DayWeekMatcher dayWeek(int day) {
		return new DayWeekMatcher(day);
	}
	public static DayWeekMatcher dayMonday() {
		return new DayWeekMatcher(Calendar.MONDAY);
	}


}
