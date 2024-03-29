package Ex1;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sun.org.apache.xerces.internal.xs.XSTypeDefinition;
import netscape.javascript.JSObject;
import sun.security.jgss.GSSCaller;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.awt.color.*;

public class Functions_GUI implements functions {

    private ArrayList<function> funcs;

    public Functions_GUI() {
        funcs = new ArrayList<function>();
        }

    public void initFromFile(String file) throws IOException {
        String line = "";
        ComplexFunction cf=new ComplexFunction();
        BufferedReader br = new BufferedReader(new FileReader(file));
        while ((line = br.readLine()) != null) {
            String str = new String(line);
            function f=cf.initFromString(str);
            this.funcs.add(f);
        }
        br.close();
    }

    @Override
    public void saveToFile(String file) throws IOException {
        PrintWriter pw = new PrintWriter(new File(file));
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<this.funcs.size(); i++)
        {
            sb.append(this.funcs.get(i).toString()+"\n");
        }
        pw.write(sb.toString());
        pw.close();

    }

    @Override
    public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {

        if(width<0||height<0||resolution<0)
        {
            return;
        }
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(rx.get_min(), rx.get_max());
        StdDraw.setYscale(ry.get_min(), ry.get_max());

        ///// x axis
        StdDraw.setPenColor(Color.LIGHT_GRAY);
        StdDraw.setPenRadius(0.003);
        for (double i = ry.get_min(); i <= ry.get_max(); i +=1) {
            StdDraw.line(rx.get_min(), i, rx.get_max(), i);
        }
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setPenRadius(0.005);
        StdDraw.line(rx.get_min(), 0, rx.get_max(), 0);
        StdDraw.setFont(new Font("TimesRoman", Font.BOLD, 15));
        double rxis = rx.get_max() - rx.get_min();
        double x_steps = rxis / resolution;
        double ryis = ry.get_max() - ry.get_min();
        if(rxis<0||ryis<0)
        {
            return;
        }
        for (double i = rx.get_min(); i <= rx.get_max(); i +=1) {
            StdDraw.text(i + 0.01, -0.1, Integer.toString((int) (i)));
        }

        ///// y axis
        StdDraw.setPenColor(Color.LIGHT_GRAY);
        StdDraw.setPenRadius(0.003);
        for (double i = rx.get_min(); i <= rx.get_max(); i +=1) {
            StdDraw.line(i, ry.get_min(), i, ry.get_max());
        }
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setPenRadius(0.005);
        StdDraw.line(0, ry.get_min(), 0, ry.get_max());
        StdDraw.setFont(new Font("TimesRoman", Font.BOLD, 15));
        for (double i = ry.get_min(); i <= ry.get_max(); i += 1) {
            double y_i = i;
            StdDraw.text(-0.1, y_i+0.01, Integer.toString((int) (y_i)));
        }

        //draw functions
        Random rand= new Random();
        for (int i = 0; i < this.funcs.size(); i++) {
            function fx = this.funcs.get(i);
            int r =rand.nextInt(256);
            int g = rand.nextInt(256);
            int b = rand.nextInt(256);
            Color color = new Color(r, g, b);
            StdDraw.setPenColor(color);
            StdDraw.setPenRadius(0.005);
            System.out.println(i+". fx= "+fx.toString()+", color: "+color.toString());
            for (double j = rx.get_min(); j <= rx.get_max(); j += x_steps) {
                double x_step = j;
                double x_nextStep = j + x_steps;
                StdDraw.line(j, fx.f(j), x_nextStep, fx.f(x_nextStep));
            }
        }
    }


    @Override
    public void drawFunctions(String json_file) {
        Gson gson=new Gson();
        JsonObject obj=null;
        int width=0;
        int height=0;
        int resolution=0;
        double [] rangeX=new double[2];
        double [] rangeY=new double[2];
        Range rx=null;
        Range ry=null;
        try {
             obj=gson.fromJson(new FileReader(json_file), JsonObject.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
             width = obj.get("Width").getAsInt();
             height = obj.get("Height").getAsInt();
             resolution = obj.get("Resolution").getAsInt();
            rangeX = gson.fromJson(obj.get("Range_X"), double[].class);
            rangeY = gson.fromJson(obj.get("Range_Y"), double[].class);
            rx=new Range(rangeX[0],rangeX[1]);
            ry=new Range(rangeY[0],rangeY[1]);
            }
        catch(Exception e)
        {
            System.out.println("The draw function will use the default values , due to invalid input : "+e.getMessage());
            width=1000;
            height=1200;
            resolution=100;
            rx=new Range(-10,10);
            ry=new Range(-5,15);
        }
        drawFunctions(width,height,rx,ry,resolution);
    }

    @Override
    public int size() {
        return this.funcs.size();
    }

    @Override
    public boolean isEmpty() {
        return this.funcs.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.funcs.contains(o);
    }

    @Override
    public Iterator<function> iterator() {
        return this.funcs.iterator();
    }

    @Override
    public Object[] toArray() {
        return this.funcs.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return this.funcs.toArray(a);
    }

    @Override
    public boolean add(function function) {
        return this.funcs.add(function);
    }


    @Override
    public boolean remove(Object o) {
        return this.funcs.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return this.funcs.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends function> c) {
        return this.funcs.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return this.funcs.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return this.funcs.retainAll(c);
    }

    @Override
    public void clear() {
        this.funcs.clear();
    }

    public ArrayList<function> getFuncs() {
        return funcs;
    }
}
