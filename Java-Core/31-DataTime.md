
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



    Resource Bundle: represents bundle of localizable resources

Resources may be stored as classes or plain text file with .properties extension

Resource bundles as <key>=<pairs>

They may represnt user-visible messages or messge pattern subsituution parameter

A default mbundle can be used itf no locale is peiied or if the resource youre trying toget is not present in language and country specific bundle

this is used for not hardcoding text, 

java.util.Resource.Bundle

in packages:
    messages.properties
    messages_en_GB.properties
    messages_ru.properties

hello= cisjkfbv [0]
...


Formatting Message Patterns
    java.text.MessageFormat class is used to 
    Format messaged by subituuion ingto text pattern 
    parse messages by extracting values out of text based on pattern, message pattern typically stored in resource bundles

    Locale locale = new Locale("en", "GB");

    ResourceBundle bundle = ResourceBundle.getBundle("resouces.messages", locale);
    // following items are alreadu formagted bh string objects
    String pattern = bundle.getString("product");
    String result = MessageFormat.format(pattern, name,price, qu3anity, bestBefore);


NumberFormat.parse() method return type is java.lang.Number. Class Number is extended by primitive wrapper classes as well as BigDecimal class . This means value is parsed
ex

CompactNumberFormat class is set up concrete format thats form decimal Number in its compact form. This compact no is designed for environment where space is limited and formatted string LDML specification. Represented of no in shorter form, based on pattern provided given locale