//package Ex1;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Iterator;
//
//
//abstract public class Functions_GUI implements functions {
//
//    Collection<functions> fs;
//
//    public Functions_GUI() {
//        fs = new ArrayList<functions>();
//        }
//    public void initFromFile(String file) throws IOException {
//        String line = "";
//        function f=null;
//        BufferedReader br = new BufferedReader(new FileReader(file));
//        while ((line = br.readLine()) != null) {
//            String str = new String(line);
//            f.initFromString(str);
//            fs.add((Ex1.functions) f);
//
//        }
//        Iterator<functions> it=fs.iterator();
//        while(it.hasNext())
//        {
//            functions ft=it.next();
//            System.out.println(ft.toString());
//        }
//        br.close();
//
//    }
//
//    @Override
//    public void saveToFile(String file) throws IOException {
//
//    }
//
//    @Override
//    public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
//
//    }
//
//    @Override
//    public void drawFunctions(String json_file) {
//
//    }
//    public static void main(String[] args) throws IOException {
//        String str="file";
//        Functions_GUI f;
//        f.initFromFile(str);
//    }
//    }
