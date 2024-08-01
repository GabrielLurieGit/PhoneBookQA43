package experiments;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TriangleTest {
    @Test
    public void equilateraltriangleTestPositive(){
        Assert.assertEquals(Triangle.getTriangleType(5,5,5),TriangleType.ISOSCELES);
    }

    @Test
    public void triangleTestNegative1(){
        Triangle.getTriangleType(0,0,0);
    }

    @Test
    public void triangleTestNegative2(){
        Triangle.getTriangleType(-0,0,0);
    }

    @Test
    public void triangleTestNegative3(){
        Triangle.getTriangleType(1,1,5);
    }

}
