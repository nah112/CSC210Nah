public class BooleanLogic {

    public static boolean And(boolean a, boolean b) {
        return a && b;
    }

    public static boolean Or(boolean a, boolean b) {
        return a || b;
    }

    public static boolean Not(boolean a) {
        return !a;
    }

    public static boolean Xor(boolean a, boolean b) {
        return a ^ b;
    }

    public static void main(String[] args) {
        System.out.println("AND(true,true) = " + And(true,true));
        System.out.println("OR(true,false) = " + Or(true,false));
        System.out.println("NOT(true) = " + Not(true));
        System.out.println("XOR(true,false) = " + Xor(true,false));
    }
}
