package experiments;

public enum TriangleType {
    EQUILATERAL("EQUILATERAL"), //Равносторонний
    ISOSCELES("ISOSCELES"), // Равнобедренный
    SCALENE("SCALENE"), //Разносторонний
    INVALID("INVALID"); //Неправильный

    private final String type;

    TriangleType(String type){
        this.type = type;
    }

    @Override
    public String toString() {
        return "TriangleType{" +
                "type='" + type + '\'' +
                '}';
    }
}
