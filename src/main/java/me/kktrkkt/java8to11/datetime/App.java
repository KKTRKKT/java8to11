package me.kktrkkt.java8to11.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class App {

    public static void main(String[] args) throws InterruptedException {
//        dateProblem();
        System.out.println("------------------현재 machine time 가져오기-------------------------");

        Instant now = Instant.now();
        // human time으로 출력된다.
        System.out.println(now); // 기준시 UTC, GMT

        System.out.println("------------------로컬 기준시 가져오기-------------------------");

        ZoneId zone = ZoneId.systemDefault();
        System.out.println(zone);
        ZonedDateTime zonedDateTime  = now.atZone(zone);
        System.out.println(zonedDateTime);

        System.out.println("------------------human time api-------------------------");

        // 로컬기준 시간이 출력된다.
        LocalDateTime localNow = LocalDateTime.now();
        System.out.println(localNow);

        // of로 원하는 시간 생성 가능
        LocalDateTime wirteTime = LocalDateTime.of(2023, Month.JANUARY, 28, 21, 23, 5);
        System.out.println(wirteTime);

        //원하는 지역의 현재시간을 얻으려면
        ZonedDateTime nowInKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        System.out.println(nowInKorea);

        System.out.println("------------------상호 호환-------------------------");

        ZonedDateTime instantToZonedDateTime = ZonedDateTime.ofInstant(now, zone);
        LocalDateTime instantToLocalDateTime = LocalDateTime.ofInstant(now, zone);
        Instant localDateTimeToInstant = localNow.toInstant(ZoneOffset.UTC);
        Instant zonedDateTimeToInstant = zonedDateTime.toInstant();

        System.out.println(instantToZonedDateTime);
        System.out.println(instantToLocalDateTime);
        System.out.println(localDateTimeToInstant);
        System.out.println(zonedDateTimeToInstant);

        System.out.println("------------------Period-------------------------");

        // 오늘부터 20일 뒤 날짜를 구한다.
        LocalDateTime nowPlus20Days = localNow.plus(20, ChronoUnit.DAYS);
        // 오늘과 20일뒤 사이의 기간을 구한다.
        Period between = Period.between(localNow.toLocalDate(), nowPlus20Days.toLocalDate());
        System.out.println(between.getDays());
        System.out.println(between.getMonths());

        // LocalDate의 until도 결과가 똑같다.
        Period until = localNow.toLocalDate().until(nowPlus20Days.toLocalDate());
        System.out.println(until.getDays());

        System.out.println("------------------Duration-------------------------");

        //Duration은 machine time을 비교함
        Instant nowPlus10Seconds = now.plusSeconds(10);
        Duration duration = Duration.between(now, nowPlus10Seconds);
        System.out.println(duration.getSeconds());

        System.out.println("------------------DateTimeFormatter-------------------------");

        DateTimeFormatter yyMMDD = DateTimeFormatter.ofPattern("yy/MM/dd");
        System.out.println(localNow.format(yyMMDD));
        System.out.println(localNow.format(DateTimeFormatter.ISO_LOCAL_TIME));

        System.out.println("------------------parse-------------------------");

        // 포멧에 맞는 스트링을 입력하면 파싱해서 날짜를 가져온다
        LocalDate parse = LocalDate.parse("23/01/28", yyMMDD);
        System.out.println(parse);

        System.out.println("------------------레거시 api 지원-------------------------");

        // Date to Instant, Instant to Date
        Date date = new Date();
        Instant dateToInstant = date.toInstant();
        Date instantToDate = Date.from(dateToInstant);

        // GregorianCalendar to ZonedDateTime, ZonedDateTime to GregorianCalendar
        GregorianCalendar calendar = new GregorianCalendar();
        ZonedDateTime gregorianCalendarToZonedDateTime = calendar.toZonedDateTime();
        GregorianCalendar zonedDateTimeToGregorianCalendar = GregorianCalendar.from(gregorianCalendarToZonedDateTime);

        ZoneId timeZoneToZoneId = TimeZone.getDefault().toZoneId();
        TimeZone zonIdToTimeZone = TimeZone.getTimeZone(timeZoneToZoneId);
        System.out.println(timeZoneToZoneId);
        System.out.println(zonIdToTimeZone.getID());
    }

    private static void dateProblem() throws InterruptedException {
        System.out.println("------------------ java.util.Date의 문제점-------------------------");
        //이름은 date지만 time을 포함하고 있다.
        Date date = new Date();
        //날짜에서 시간을 가져온다. 그리고 이 시간은 유닉스 시간이다.
        long time = date.getTime();

        System.out.println(date);
        System.out.println(time);

        //불변객체가 아니므로 thread에 안전하지 않다. mutable하다
        Thread.sleep(1000);
        Date after1Seconds = new Date();
        System.out.println(after1Seconds);
        after1Seconds.setTime(time);
        System.out.println(after1Seconds);

        System.out.println("------------------ java.util.Calendar의 문제점-------------------------");
        // 버그가 발생할 여지가 많다. 타입 안정성이 없다.
        // month가 0부터 시작하므로 jan이 아니라 feb가 나온다.
        Calendar thisYearJanuary = new GregorianCalendar(2023, 1, 27);
        System.out.println(thisYearJanuary.getTime());
    }
}
