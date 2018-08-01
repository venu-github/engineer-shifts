package com.engineer.shifts.model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Shift {
private List<Engineer> shiftEngineers = new ArrayList<>();
private Map<LocalDate, List<Engineer>> shiftEngineersPerDay = new LinkedHashMap<>();
public List<Engineer> getShiftEngineers() {
	return shiftEngineers;
}

public void setShiftEngineers(List<Engineer> shiftEngineers) {
	this.shiftEngineers = shiftEngineers;
}

public Map<LocalDate, List<Engineer>> getShiftEngineersPerDay() {
	return shiftEngineersPerDay;
}

public void setShiftEngineersPerDay(Map<LocalDate, List<Engineer>> shiftEngineersPerDay) {
	this.shiftEngineersPerDay = shiftEngineersPerDay;
}
}
