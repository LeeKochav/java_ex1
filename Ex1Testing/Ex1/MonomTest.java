package Ex1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MonomTest {

   static ArrayList<Monom> monoms;

   @BeforeAll
   static void init()
   {
       monoms=new ArrayList<>();
        String [] monomStr={"-7","9x","x^2","5x^4"};
        for(int i=0; i<monomStr.length; i++)
        {
            try {
                monoms.add(new Monom(monomStr[i]));
            }
            catch(RuntimeException error){
                System.out.println(error.getMessage()+" "+monomStr[i]);
            }
        }


   }
    @ Test
    void initFromString() {
        Monom m=new Monom(0,0);
        String []monomStrTest={"-34","-x","-3.5x^4","-7a3x","-x^a","-3.5x^-6"};
        int expected=3;
        int actual=monomStrTest.length;

        for(int i=0; i<monomStrTest.length; i++)
        {
            try {
                function f=m.initFromString(monomStrTest[i]);
            }
            catch(RuntimeException error){
               actual--;
                System.out.println(error.getMessage()+" "+monomStrTest[i]);
            }
        }
        assertEquals(expected,actual);
    }

    @Test
    void add() {
        String actual;
        String expected;
        Monom m=new Monom("12.5");
        Monom m2=new Monom("3x");
        Monom m3=new Monom("-41.5");
        m.add(m2);
        actual=m.toString();
        expected=m.toString();
        assertEquals(expected,actual);
        m.add(m3);
        actual=m.toString();
        expected="-29.0";
        assertEquals(expected,actual);
    }


    @Test
    void derivative() {
       boolean check;
        ArrayList<Monom> monomsDer =new ArrayList<>();
        for(int i=0; i<monoms.size(); i++)
        {
            Monom m=new Monom(monoms.get(i));
            Monom t=new Monom(monoms.get(i).derivative());
            monomsDer.add(t);
            System.out.println(m.toString() + "  Vs  " + t.toString());
            check=m.toString().equals(t.toString());
            assertFalse(check);
        }
    }
    @Test
    void isZero() {
       int expected=0;
       int actual=0;
        for(int i=0; i<monoms.size(); i++)
        {
            if(monoms.get(i).isZero())
            {
                actual++;
            }
        }
        assertEquals(expected,actual);
    }

    @Test
    void f() {
        double check;
        for(int i=0; i<monoms.size(); i++)
        {
            check=monoms.get(i).f(2);
            System.out.println(monoms.get(i).toString() + "  f(x) " + check);
            assertTrue(check!=0);
        }
    }


    @Test
    void substract() {
        String actual;
        String expected;
        Monom m=new Monom("12.5");
        Monom m2=new Monom("3x^4");
        Monom m3=new Monom("0.5x^4");
        m.substract(m2);
        actual=m.toString();
        expected=m.toString();
        assertEquals(expected,actual);
        m2.add(m3);
        actual=m2.toString();
        expected="3.5x^4";
        assertEquals(expected,actual);
    }

    @Test
    void multiply() {
       Monom m=new Monom("2x");
       int prev;
       int curr;
        boolean check;
        for(int i=0; i<monoms.size(); i++)
        {
            prev=monoms.get(i).get_power();
            monoms.get(i).multiply(m);
            curr=monoms.get(i).get_power();
           assertEquals(prev+1,curr);
        }
    }

    @Test
    void testToString() {
       Monom m=new Monom(3.5,5);
       String actual=m.toString();
       String expected="3.5x^5";
       assertEquals(expected,actual);

    }

    @Test
    void copy() {
       String m;
       function f;
       for(int i=0; i<monoms.size(); i++)
       {
           m=monoms.get(i).toString();
           f=monoms.get(i).copy();
           System.out.println(m+ " Vs  "+f.toString());
           assertEquals(m,f.toString());

       }
    }

    @Test
    void testEquals() {
       Monom m=new Monom("-7");
       assertTrue(m.equals(new Monom(-7,0)));
       assertTrue(m.equals(new Monom("-7")));
       assertFalse(m.equals(new Monom("-7x")));
       assertFalse(m.equals("blabla"));
       assertFalse(m.equals(-7));
    }
}