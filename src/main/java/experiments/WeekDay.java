package experiments;

public enum WeekDay {
    SUNDAY("SUN"),
    MONDAY("MON"),
    TUESDAY("TUE"),
    WEDNESDAY("WED"),
    THURSDAY("THU"),
    FRIDAY("FRI"),
    SATURDAY("SAT");

    private final String name;
    WeekDay(String name){
        this.name = name;
    }
    public String getname(){
        return name;
    }
}
