package Ex1;

import java.util.Stack;

public class ComplexFunction implements complex_function {

    private function fx1, fx2;
    private Operation op;

    public ComplexFunction()
    {
        this.fx1=new Polynom();
        this.fx2=new Polynom();
        this.op=Operation.None;
    }
    public ComplexFunction(function fx1) {
        if(fx1==null)
        {
            throw new RuntimeException("fx1 cannot be null");
        }
        this.fx1 = fx1.copy();
        this.fx2 = null;
        this.op = Operation.None;
    }

    public ComplexFunction(Operation op, function fx1, function fx2) {
        if(fx1==null)
        {
            throw new RuntimeException("fx1 cannot be null");
        }
        this.fx1 = fx1.copy();
        this.fx2 = fx2.copy();
        this.op = op;
    }
    public ComplexFunction(String s, function fx1, function fx2) {
        if(fx1==null)
        {
            throw new RuntimeException("fx1 cannot be null");
        }
        this.fx1 = fx1.copy();
        this.fx2 = fx2.copy();
        this.op = convert(s);
    }

    public ComplexFunction(ComplexFunction c) throws RuntimeException {
            this.fx1 = c.left();
            this.fx2 = c.right();
            this.op = c.getOp();
    }

    @Override
    public void plus(function f1) {
        if (this.fx2 == null) {
            this.fx2 = f1.copy();
            this.op = Operation.Plus;
            return;
        }
        this.fx1 = new ComplexFunction(this.getOp(), this.fx1, this.fx2);
        this.fx2 = f1.copy();
        this.op = Operation.Plus;
    }

    @Override
    public void mul(function f1) {
        if (this.fx2 == null) {
            this.fx2 = f1.copy();
            this.op = Operation.Times;
            return;
        }
        this.fx1 = new ComplexFunction(this.getOp(), this.fx1, this.fx2);
        this.fx2 = f1.copy();
        this.op = Operation.Times;

    }

    @Override
    public void div(function f1) {
        if (this.fx2 == null) {
            this.fx2 = f1.copy();
            this.op = Operation.Divid;
            return;
        }
        this.fx1 = new ComplexFunction(this.getOp(), this.fx1, this.fx2);
        this.fx2 = f1.copy();
        this.op = Operation.Divid;

    }

    @Override
    public void max(function f1) {
        if (this.fx2 == null) {
            this.fx2 = f1.copy();
            this.op = Operation.Max;
            return;
        }
        this.fx1 = new ComplexFunction(this.getOp(), this.fx1, this.fx2);
        this.fx2 = f1.copy();
        this.op = Operation.Max;

    }

    @Override
    public void min(function f1) {
        if (this.fx2 == null) {
            this.fx2 = f1.copy();
            this.op = Operation.Min;
            return;
        }
        this.fx1 = new ComplexFunction(this.getOp(), this.fx1, this.fx2);
        this.fx2 = f1.copy();
        this.op = Operation.Min;

    }

    @Override
    public void comp(function f1) {
        if (this.fx2 == null) {
            this.fx2 = f1.copy();
            this.op = Operation.Comp;
            return;
        }
        this.fx1 = new ComplexFunction(this.getOp(), this.fx1, this.fx2);
        this.fx2 = f1.copy();
        this.op = Operation.Comp;

    }

    @Override
    public function left() {
        if(this.fx1==null)
        {
            throw new NullPointerException("Complex function is not initialized with left function");
        }
        return this.fx1;
    }

    @Override
    public function right() {
        if(this.fx2==null)
        {
            throw new NullPointerException("Complex function is not initialized with right function");
        }
        return this.fx2;
    }

    @Override
    public Operation getOp() {
        return this.op;
    }

    @Override
    public double f(double x) {
        double res = 0;

        switch (this.op) {
            case Plus:
                res = this.fx1.f(x) + this.fx2.f(x);
                break;
            case Times:
                res = this.fx1.f(x) * this.fx2.f(x);
                break;
            case Divid:
                if(this.fx2.f(x)==0)
                {
                    throw new IllegalArgumentException("Cannot divide by zero");
                }
                res = this.fx1.f(x) / this.fx2.f(x);
                break;
            case Max:
                res = Math.max(this.fx1.f(x), this.fx2.f(x));
                break;
            case Min:
                res = Math.min(this.fx1.f(x), this.fx2.f(x));
                break;
            case Comp:
                res = this.fx1.f(this.fx2.f(x));
                break;
            case None:
                if (this.fx2 == null) {
                    res = this.fx1.f(x);
                } else {
                    throw new RuntimeException("No operation");
                }
                break;
            case Error:
                throw new RuntimeException("Error operation is not a valid action on complex function");
        }
        return res;
    }

    @Override
    public function initFromString(String s) {

        int indexFirstLeft=s.indexOf('(');
        int indexLastRight=s.lastIndexOf(')');
        if(indexLastRight==-1||indexFirstLeft==-1) //base case of the recursion
        {
            Polynom p=new Polynom(s);
            return p;
        }
        String operation=s.substring(0,indexFirstLeft);
        String tmp=s.substring(indexFirstLeft+1,indexLastRight); //tmp is the substring that contains f1,f2
        Stack<Character> st=new Stack<Character>(); // stack contains the opening brackets
        String f1="";
        String f2="";
        int i;
        for(i=0; i<tmp.length(); i++) //validate that the functions has the same opening brackets and closing brackets.
        {
            int indexCurrLeft=0;
            if(st.isEmpty()&&(tmp.charAt(i)==','))
            {
                i--;
                break;
            }
            if (tmp.charAt(i) == '(') {
                st.push('(');
                }
            else if (tmp.charAt(i) == ')') {
                st.pop();
                if (st.isEmpty()) {
                    break;
                }
            }
        }
        try {
            f1 = tmp.substring(0, i + 1);
            f2 = tmp.substring(i + 2, tmp.length());
        }
        catch (RuntimeException error)
        {
            throw new RuntimeException("Invalid input");
        }
        function fx1=initFromString(f1);
        function fx2=initFromString(f2);
        ComplexFunction c=new ComplexFunction(operation,fx1,fx2);
        return c;
    }

    @Override
    public function copy() {
        ComplexFunction c = new ComplexFunction(this);
        return c;
    }

    @Override
    public String toString() {
        if (this.fx2 == null) {
            return this.fx1.toString();
        }
        String ans = "";
        switch (this.op) {
            case Plus:
                ans = "plus";
                break;
            case Times:
                ans = "mul";
                break;
            case Divid:
                ans = "div";
                break;
            case Max:
                ans = "max";
                break;
            case Min:
                ans = "min";
                break;
            case Comp:
                ans = "comp";
                break;
            case None:
                break;
        }
        ans = ans + "(" + this.fx1.toString() + "," + this.fx2.toString() + ")";
        return ans;
    }

    public boolean equals(Object obj) {

        if (obj == null || this == null) {
            return false;
        }
        if (obj instanceof function) {
            function tmp = (function) obj;
            for (int i = 0; i < 1000000; i++) {
                if (this.f(i) != tmp.f(i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Convert operation given as string into the specific enum operation type
     * @param opStr
     * @return
     */
    private Operation convert(String opStr)
    {
        Operation op=null;

        switch (opStr) {
            case "plus":
                op=Operation.Plus;
                break;
            case "mul":
                op=Operation.Times;
                break;
            case "div":
                op=Operation.Divid;
                break;
            case "max":
                op=Operation.Max;
                break;
            case "min":
                op=Operation.Min;
                break;
            case "comp":
                op=Operation.Comp;
                break;
            default:
                throw new RuntimeException("Invalid input");
        }
        return op;
    }

}