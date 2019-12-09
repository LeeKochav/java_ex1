package Ex1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;



import static org.junit.jupiter.api.Assertions.*;

class ComplexFunctionTest {

    Polynom p1;
    Polynom p2;
    Polynom p3;
    ComplexFunction cf;
    ComplexFunction cf1;
    ComplexFunction cf2;

    @BeforeEach
    void init()
    {
        String s1 = "3.1+2.4x^2-x^4";
        String s2 = "5+13x-3.3x+0.1x^5";
        String[] s3 = {"x-1","x-2", "x-3", "x-4"};
        p1 = new Polynom(s1);
        p2 = new Polynom(s2);
        p3 = new Polynom(s3[0]);
        for(int i=1;i<s3.length;i++) {
            try {
                p3.multiply(new Polynom(s3[i]));
            }
            catch (RuntimeException error)
            {
                System.out.println(error.getMessage());
            }
        }
        cf = new ComplexFunction("plus", p1,p2);
        cf1 = new ComplexFunction("div", p1,p3);
        cf2 = new ComplexFunction(p1);

    }

    @Test
    void plus() {
      cf.plus(p3);
      String actual=cf.toString();
      String expected="plus(plus(-1.0x^4+2.4x^2+3.1,+0.1x^5+9.7x^1+5.0),+1.0x^4-10.0x^3+35.0x^2-50.0x^1+24.0)";
      assertEquals(expected,actual);
    }

    @Test
    void mul() {
        cf.mul(p3);
        String actual=cf.toString();
        String expected="mul(plus(-1.0x^4+2.4x^2+3.1,+0.1x^5+9.7x^1+5.0),+1.0x^4-10.0x^3+35.0x^2-50.0x^1+24.0)";
        assertEquals(expected,actual);
    }

    @Test
    void div() {
        cf.div(p3);
        String actual=cf.toString();
        String expected="div(plus(-1.0x^4+2.4x^2+3.1,+0.1x^5+9.7x^1+5.0),+1.0x^4-10.0x^3+35.0x^2-50.0x^1+24.0)";
        assertEquals(expected,actual);
    }

    @Test
    void max() {
        cf.max(p3);
        String actual=cf.toString();
        String  expected="max(plus(-1.0x^4+2.4x^2+3.1,+0.1x^5+9.7x^1+5.0),+1.0x^4-10.0x^3+35.0x^2-50.0x^1+24.0)";
        assertEquals(expected,actual);
    }

    @Test
    void min() {
        cf.min(p3);
        String actual=cf.toString();
        String  expected="min(plus(-1.0x^4+2.4x^2+3.1,+0.1x^5+9.7x^1+5.0),+1.0x^4-10.0x^3+35.0x^2-50.0x^1+24.0)";
        assertEquals(expected,actual);
    }

    @Test
    void comp() {
        cf.comp(p3);
        String actual=cf.toString();
        String  expected="comp(plus(-1.0x^4+2.4x^2+3.1,+0.1x^5+9.7x^1+5.0),+1.0x^4-10.0x^3+35.0x^2-50.0x^1+24.0)";
        assertEquals(expected,actual);
    }

    @Test
    void left() {
        assertEquals(cf.left().toString(),cf1.left().toString());
        function leftBeforePlus=cf2.left();
        cf2.plus(p1);
        function leftAfterPlus=cf2.left();
        assertEquals(leftBeforePlus.toString(),leftAfterPlus.toString());
    }

    @Test
    void right() {
        assertNotEquals(cf.right().toString(),cf1.right().toString());
        try {
            System.out.println(cf2.right().toString());
            fail("Function right did not throw exception when right function was null");
        }
        catch(RuntimeException error)
        {
            System.out.println("Function right did throw exception when right function was null, message: "+error.getMessage());
        }
    }

    @Test
    void getOp() {
        assertNotEquals(cf.getOp(),cf1.getOp());
        cf1.plus(p2);
        assertEquals(cf.getOp(),cf1.getOp());
    }

    @Test
    void f() {
        assertEquals(19.299999999999997,cf.f(1));
        ComplexFunction cf3=new ComplexFunction(Operation.Error,p2,p3);
        assertEquals(4.5,cf2.f(1));
        try
        {
            double y=cf3.f(1);
        }
        catch (RuntimeException error)
        {
            System.out.println("Cannot operate on cf3 "+error.getMessage());
        }

    }

    @Test
    void initFromString() {
        int expected=1;
        int notCreated=0;
        int actual=0;
        function func=null;
        String [] cfs={"mul(plus(3x,4x^4),div(60x^2,5))","error(plus(3x,2),div(60x^2,5))","","blabla(3x)","plus(3x,4a)","max(4x^2,4))"};
        for(int i=0; i<cfs.length; i++)
        {
            try{
                func=cf.initFromString(cfs[i]);
            }
            catch(RuntimeException error)
            {
                notCreated++;
                System.out.println(error.getMessage()+" "+cfs[i]);
            }
        }
        actual=cfs.length-notCreated;
        assertEquals(expected,actual);
    }

    @Test
    void copy() {
        function func=cf.copy();
        function func1=cf1.copy();
        assertEquals(cf.toString(),func.toString());
        assertNotEquals(func.toString(),func1.toString());
    }

    @Test
    void testToString() {
        String s=cf.toString();
        function f=cf.initFromString(s);
        assertEquals(cf.toString(),f.toString());
        String s2=cf2.toString();
        function f2=cf2.initFromString(s2);
        Polynom p=new Polynom("-1.0x^4+2.4x^2+3.1");
        assertEquals(f2.toString(),p.toString());

    }

    @Test
    void testEquals() {
        cf2.plus(p2);
        Polynom p=new Polynom("3x^3");
        Monom m1 = new Monom(2,2);
        Monom m2 = new Monom(3,3);
        ComplexFunction cf3 = new ComplexFunction("mul",m1,m2);
        ComplexFunction cf4 = new ComplexFunction("mul",m1,p);
        assertTrue(cf2.equals(cf));
        assertFalse(cf2.equals(cf1));
        assertTrue(cf3.equals(cf4));
    }
}