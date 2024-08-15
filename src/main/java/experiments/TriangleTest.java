package experiments;

import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TriangleTest {
    @Test
    public void equilateralTriangleTestPositive(){
        Allure.description("Equilateral Triangle test");
        Assert.assertEquals(Triangle.getTriangleType(5,5,5),TriangleType.EQUILATERAL);
    }

    @Test
    public void isoscelesTriangleTestPositive(){
        Allure.description("Isosceles Triangle test");
        Assert.assertEquals(Triangle.getTriangleType(5,4,4),TriangleType.ISOSCELES);
    }

    @Test
    public void scaleneTriangleTestPositive(){
        Allure.description("Scalene Triangle test");
        Assert.assertEquals(Triangle.getTriangleType(4,7,9),TriangleType.SCALENE);
    }

    @Test
    public void triangleTestNegative1(){
        Assert.assertEquals(Triangle.getTriangleType(0,4,7),TriangleType.INVALID);
    }

    @Test
    public void triangleTestNegative2(){
        Assert.assertEquals(Triangle.getTriangleType(-0,5,3),TriangleType.INVALID);
    }
    @Test
    public void triangleTestNegative2_2(){
        Assert.assertEquals(Triangle.getTriangleType(5,-2,3),TriangleType.INVALID);
    }

    @Test
    public void triangleTestNegative2_3(){
        Assert.assertEquals(Triangle.getTriangleType(5,3,-2),TriangleType.INVALID);
    }

    @Test
    public void triangleTestNegative3_1(){
        Assert.assertEquals(Triangle.getTriangleType(1,1,5),TriangleType.INVALID);
    }

    @Test
    public void triangleTestNegative3_2(){
        Assert.assertEquals(Triangle.getTriangleType(1,2,5),TriangleType.INVALID);
    }

}
