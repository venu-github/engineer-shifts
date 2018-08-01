package com.engineer.shifts.controller;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.engineer.shifts.exception.ShiftsGenerateException;
import com.engineer.shifts.model.Engineer;
import com.engineer.shifts.model.Shift;
import com.engineer.shifts.util.Utils;

@RestController
public class ShiftsGenerationController {

@RequestMapping(value = "/shifts/generate", method = RequestMethod.POST)
public List<Shift> generateShifts(@RequestParam(name="startDate", required=true) String startDate, @RequestParam(name="endDate", required=true) String endDate, 
		@RequestParam(name="shiftsPerDay", required=true) int shiftsPerDay) throws ShiftsGenerateException {
	List<Shift> shifts = new ArrayList<>();
	List<Engineer> engineers = new ArrayList<>();
	List<Engineer> shiftAllotatedEngineers;
	Map<Engineer, Integer> engineerShiftCountMap = new LinkedHashMap<>();
	try {
		for (int i = 1; i <= 10; i++) {
			engineers.add(new Engineer(i));
		}
		LocalDate shiftStartDate = Utils.parseDate(startDate);
		LocalDate shiftEndDate = Utils.parseDate(endDate);
		for (LocalDate date = shiftStartDate; !date.isAfter(shiftEndDate); date = date.plusDays(1)) {
			Shift shift = new Shift();
			Map<LocalDate, List<Engineer>> shiftEngineersPerDay = new LinkedHashMap<>();
			shiftAllotatedEngineers = new ArrayList<>();
			Engineer engineer = null;
			for (int i = 0; i < shiftsPerDay; i++) {
				Set<Engineer> unavilableEngineers = new LinkedHashSet<>();
				while (unavilableEngineers.size() != engineers.size()) {
					engineer = Utils.getEngineer(engineers);
					if (Utils.isAlreadyOnShift(shiftAllotatedEngineers, engineer)) {
						unavilableEngineers.add(engineer);
						continue;
					}
					if (Utils.isOnYesterdayShift(shifts, engineer)) {
						unavilableEngineers.add(engineer);
						continue;
					}
					if (engineerShiftCountMap.values().stream().mapToInt(Integer::intValue).sum()  < engineers.size() * shiftsPerDay) {
						if (Utils.isTwoShiftsCompleted(engineerShiftCountMap, engineer)) {
							unavilableEngineers.add(engineer);
							continue;
						}
					}
					shiftAllotatedEngineers.add(engineer);
					Utils.updateEngineerShiftMap(engineerShiftCountMap, engineer);
					break;
				}
			}
			shift.setShiftEngineers(shiftAllotatedEngineers);
			shiftEngineersPerDay.put(date, shiftAllotatedEngineers);
			shift.setShiftEngineersPerDay(shiftEngineersPerDay);
			shifts.add(shift);
			}
	} catch (Exception e) {
        e.printStackTrace();
        throw new ShiftsGenerateException(e.getMessage());
	}
	return shifts;
}
}
