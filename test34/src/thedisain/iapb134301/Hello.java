package thedisain.iapb134301;

import java.util.*;

public class Hello {
    public static int y=0, x= 100;
    public static void main(String[] args) {
            int x=0;
            int z=x+0;
            double[] ap = {9,4,5,3,7,6,8,2};
            System.out.println(ap[2]);
            System.out.println("main x,y,z: "+x+" "+y+" "+z);
            z= world(hello(x), y);
            System.out.println("main x,y,z: "+x+" "+y+" "+z);
            System.out.println(f("asdasd","as"));
            System.out.println(dash("A___B__C___D"));
            System.out.println(math("0 - 5"));
            System.out.println(math2("1 - 1"));
            System.out.println(min(ap,0));
            Stack st = new Stack();
            System.out.println("---STACK TEST----");
            showpush(st,1);
            showpush(st,2);
            showpush(st,3);
            showpush(st,2);
            showpop(st);
            showpop(st);
            showpop(st);
            showpop(st);
            System.out.println("---STACK TEST END---");
            }
    public static int hello(int x) {
            int y = 0;
            ++x;
            while(x < 100) {
            if(y>2) continue;
            break;
            }
            System.out.println("hello x,y: "+(x - 2)+" "+y);
            return x - 2;
            }
    public static int world(int x, int z) {
            int y=0;
            y--;
            while(y++<=3) z+=y; // from -1 to 3 is 4 steps. 1+2+3+4 = 10 !!
            System.out.println("world x,z++,z: "+x+" "+z+" "+z);
            return z;
            }

    public static String dash(String s) {
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            if ((s.charAt(i) == '_') && (result.charAt(result.length() - 1) != '_')) {
                result += "_";
            } else
            if ((s.charAt(i) == '_') && (result.charAt(result.length() - 1) == '_')) {
                // do nothing... duplicate
            } else {
                result += s.charAt(i);
            }
        }
        return result;
    }
    public static String f(String s, String f) {
        if (s.length() < f.length()) return s;
        if (s.startsWith(f)) {
            return "(" + f + ")" + f(s.substring(f.length()), f);

        }
        return s.charAt(0) + f(s.substring(1), f);
    }
    public static int math(String s) {
        int result = 0;
        String[] mem = s.split("\\s+");
        if(mem[1].equals("+")){
            result = Integer.parseInt(mem[0]) + Integer.parseInt(mem[2]);
        }
        if((mem[1].equals("-"))){
            result = Integer.parseInt(mem[0]) - Integer.parseInt(mem[2]);
        }
        return result;

    }
    public static double min(double[] elements, int index) {
        if(index == elements.length -1){
            return elements[index];
        }
        double val = min(elements, index+1);

        if (elements[index] < val)
            return elements[index];
        else return val;
    }
    public static void showpush(Stack st, int a){
        st.push(a);
        System.out.println("push(" + a + ")");
        System.out.println("stack: " + st);
    }
    static void showpop(Stack st){
        System.out.print("POP! -> ");
        Integer a = (Integer) st.pop();
        System.out.println(a);
        System.out.println("stack: " + st);
    }
    static int math2(String s){
        String a = "";
        String b = "";
        boolean add = true;
        int result = 0;
        for(int i = 0; i < s.length(); i++){
            if(((s.charAt(i) != ' ') && (s.charAt(i) != '+') &&(s.charAt(i) != '-') && (a.equals(""))))
                a += s.charAt(i);
            if((s.charAt(i) != ' ') && (s.charAt(i) != '+') && (s.charAt(i) != '-') && (s.charAt(s.length() - 1) == ' ')) {
                b += s.charAt(i);
            }
            if(s.charAt(i) == '+')
                add = true;
            if(s.charAt(i) == '-')
                add = false;
        }
        int c = Integer.parseInt(a);
        int d = Integer.parseInt(b);
        if(!add)
            result = c - d;
        if(add)
            result = c + d;
        return result;
    }
}