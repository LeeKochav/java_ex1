package Ex1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class PolynomTest {

    Polynom p;
    Polynom_able p2;

    @BeforeEach
    void init()
    {
        p=new Polynom();
        p2=new Polynom();
        String[] monoms = {"5", "1.7x", "3x^2", "-1.5x^2"};
        for(int i=0; i<monoms.length; i++)
        {
            try {
                Monom m=new Monom(monoms[i]);
                p.add(m);
            }
            catch(RuntimeException error){
                System.out.println(error.getMessage()+" "+monoms[i]);
            }
        }
        String[] monoms2 = {"5", "13x", "4x^2", "-3", "-1.5x^2"};
        for (int i = 0; i < monoms2.length; i++) {
            try {

                Monom m = new Monom(monoms2[i]);
                p2.add(m);
            }
            catch(RuntimeException error)
            {
                System.out.println(error.getMessage()+" "+monoms2[i]);
            }
        }
    }
    @Test
    void initFromString() {
        Polynom p=new Polynom();
        String[] polynomStr = {"1x+5a^7", "8x^3+4x+2", "-x^4+3x^2", "7x+8x^2+0.5x^4","-3a"};
        int expected=3;
        int actual=polynomStr.length;
        for (int i = 0; i < polynomStr.length; i++) {

            try{
                function f=p.initFromString(polynomStr[i]);
            }
            catch(RuntimeException error)
            {
                actual--;
                System.out.println(error.getMessage()+" "+polynomStr[i]);
            }
        }
        assertEquals(expected,actual);

    }
    @Test
    void testToString() {
        String actual=p.toString();
        String expected="+1.5x^2+1.7x^1+5.0";
        assertEquals(expected,actual);
    }

    @Test
    void f() {
        double check=p.f(0);
        assertFalse(check==0);
    }

    @Test
    void add() {
        p.add(p2);
        String actual=p.toString();
        String expected="+4.0x^2+14.7x^1+7.0";
        assertEquals(expected,actual);
    }

    @Test
    void testAddMonom() {
        Monom m=new Monom(3,3);
        p.add(m);
        String actual=p.toString();
        String expected="+3.0x^3+1.5x^2+1.7x^1+5.0";
        assertEquals(expected,actual);
    }

    @Test
    void substract() {
        p.substract(p2);
        String actual=p.toString();
        String expected="-1.0x^2-11.3x^1+3.0";
        assertEquals(expected,actual);
    }


    @Test
    void testSubstractMonom() {
        Monom m=new Monom(3,4);
        p.substract(m);
        String actual=p.toString();
        String expected="-3.0x^4+1.5x^2+1.7x^1+5.0";
        assertEquals(expected,actual);
    }

    @Test
    void multiply() {
        Polynom p1=new Polynom("3x^2+5x");
        Polynom_able p3=new Polynom("4x+7");
        p1.multiply(p3);
        String actual=p1.toString();
        String expected="+12.0x^3+41.0x^2+35.0x^1";
        assertEquals(expected,actual);
    }

    @Test
    void testEquals() {
        assertTrue(p.equals(new Polynom(p.toString())));
        assertFalse(p.equals(p2));
        assertFalse(p.equals("blabla"));
        assertFalse(p.equals(-7));
    }

    @Test
    void isZero() {
        assertFalse(p.isZero());
        assertFalse(p2.isZero());
    }

    @Test
    void root() {
        double r= Integer.MAX_VALUE;
        Polynom pR=new Polynom("x^2-10x+25");
        try{
            r=p.root(-50,2,Polynom.EPSILON);
        }
        catch (RuntimeException error)
        {
            System.out.println(error.getMessage()+" "+p);
        }
        assertTrue(r==Integer.MAX_VALUE);
        try{
            r=pR.root(-5,5,Polynom.EPSILON);
        }
        catch (RuntimeException error)
        {
            System.out.println(error.getMessage()+" "+p);
        }
        assertTrue(r!= Integer.MAX_VALUE);
    }

    @Test
    void copy() {
        p2=p.copy();
        assertEquals(p.toString(),p2.toString());
    }

    @Test
    void derivative() {
        boolean check;
        Polynom_able p2Der=p2.derivative();
        Polynom_able pDer=p.derivative();
        System.out.println("Derivative compare"+p.toString() + "  Vs  " + pDer.toString());
        check=p.toString().equals(pDer.toString());
        assertFalse(check);
        System.out.println("Derivative compare"+p2.toString() + "  Vs  " + p2Der.toString());
        check=p2.toString().equals(p2Der.toString());
        assertFalse(check);
    }

    @Test
    void area() {
        Polynom pArea=new Polynom("-x^3+8");
        assertTimeout(Duration.ofMillis(100000),()-> {
            double area=pArea.area(-2, 2, Polynom.EPSILON);
            assertTrue(area!=0);
        });
    }

    @Test
    void iteretor() {
        int expected=3;
        int actual=0;
        int notCreated=0;
        String[] polynomStr = {"1+5a", "20x^2+3", "-x^4-x^2", "-x^3+8","40x^-7+3x"};
        for (int i = 0; i < polynomStr.length; i++) {

            try{
                Polynom pIt=new Polynom(polynomStr[i]);
                Iterator<Monom> it=pIt.iteretor();
            }
            catch(RuntimeException error)
            {
                notCreated++;
                System.out.println(error.getMessage()+" "+polynomStr[i]);
            }
        }
        actual=polynomStr.length-notCreated;
        assertEquals(expected,actual);

    }


    @Test
    void testMultiplyMonom() {
        Monom m=new Monom(3,4);
        p.multiply(m);
        String actual=p.toString();
        String expected="+4.5x^6+5.1x^5+15.0x^4";
        assertEquals(expected,actual);
    }
}