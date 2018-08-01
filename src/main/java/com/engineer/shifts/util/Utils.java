package com.engineer.shifts.util;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.engineer.shifts.model.Engineer;
import com.engineer.shifts.model.Shift;

public class Utils {
public static Engineer getEngineer(List<Engineer> engineers) {
	Random random = new Random();
	return engineers.get(random.nextInt(engineers.size()));
}
public static boolean isAlreadyOnShift(List<Engineer> currentDateShiftEngineers, Engineer engineer) {
	return currentDateShiftEngineers.contains(engineer);
}
public static boolean isOnYesterdayShift(List<Shift> tillDateShifts, Engineer engineer) {
	return !tillDateShifts.isEmpty() && tillDateShifts.get(tillDateShifts.size()-1).getShiftEngineers().contains(engineer);
}
public static boolean isTwoShiftsCompleted(Map<Engineer, Integer> engineerShiftCountMap, Engineer engineer) {
	return engineerShiftCountMap.get(engineer) != null && engineerShiftCountMap.get(engineer) == 2;
}
public static void updateEngineerShiftMap(Map<Engineer, Integer> engineerShiftCountMap, Engineer engineer) {
	if (engineerShiftCountMap.get(engineer) != null) {
		engineerShiftCountMap.put(engineer, engineerShiftCountMap.get(engineer)+1);
	} else {
		engineerShiftCountMap.put(engineer, 1);
	}
}
public static LocalDate parseDate(String date) throws DateTimeException{
	DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
	return LocalDate.parse(date, formatter);
}
}