package experiments;

import io.qameta.allure.Allure;

public class Triangle {
    public static TriangleType getTriangleType(int a, int b, int c){
        Allure.step("STEP 1");
        if (a <= 0 || b <= 0 || c <= 0 || a+ b <= c || b + c <= a || c + a <= b) {
            return TriangleType.INVALID;
        }
        if (a == b && b == c) {
            return TriangleType.EQUILATERAL;
        }
        if (a == b || b == c) {
            return TriangleType.ISOSCELES;
        }
        if (a + b > c && b + c > a && c + a > b) {
            return TriangleType.SCALENE;
        }
        return TriangleType.INVALID;
    }
}
