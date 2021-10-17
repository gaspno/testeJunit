package br.ce.wcaquino.matchers;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import br.ce.wcaquino.utils.DataUtils;

public class DayWeekMatcher extends TypeSafeMatcher<Date>{
	
	private int day;

	public DayWeekMatcher(Integer dayWeek) {
		day=dayWeek;
	}
	
	@Override
	public void describeTo(Description description) {
		// TODO Auto-generated method stub
		Calendar dia=Calendar.getInstance();
		dia.set(Calendar.DAY_OF_WEEK, day);
		
		description.appendText(dia.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.LONG, new Locale("pt","br")));
	}

	@Override
	protected boolean matchesSafely(Date data) {
		// TODO Auto-generated method stub
		return DataUtils.verificarDiaSemana(data, day);
	}

}
