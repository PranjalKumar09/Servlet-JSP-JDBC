
Date & time api
    java.util

    Joda Time API

    joda.org
    joda.time

    (as developed by joda)

WE should print current date
    Local Date

    LocalDate date = LocalDate.now();
    LocalTime time = LocalTime.now();


    now is factory static methods


    just we import.time.*


    int dd = date.getDayOfMonth();
    int mm = date.getMonthValue();
    int yy = date.getYear();

    int h = time.getHour();
    int m = time.getMintue();
    int s = time.getSecond();
    int n = time.getNano();


    LocalDateTime
    .of -> whenver there is of we must pass some value

    to get into different name:
    sop(%d-%d-%d, dd, mm , yy)

Period classes
    Difference .now()

