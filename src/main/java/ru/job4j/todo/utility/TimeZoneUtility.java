package ru.job4j.todo.utility;

import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;

public class TimeZoneUtility {

    public static Map<String, String> findAllTimeZones() {
        Map<String, String> zones = new TreeMap<>();

        for (String timeId : TimeZone.getAvailableIDs()) {
            zones.put(TimeZone.getTimeZone(timeId).getID() + " : " + TimeZone.getTimeZone(timeId).getDisplayName(), timeId);
        }
        return zones;
    }

    public static String defaultTimeZone() {
        return TimeZone.getDefault().getID();
    }

    public static Collection<Task> changeTimeZone(Collection<Task> tasks, User user) {
       for (Task task : tasks) {
           task.setCreated(task.getCreated()
                   .atZone(ZoneId.of("UTC"))
                   .withZoneSameInstant(ZoneId.of(user.getTimezone())).toLocalDateTime());
        }
        return tasks;
    }

}
