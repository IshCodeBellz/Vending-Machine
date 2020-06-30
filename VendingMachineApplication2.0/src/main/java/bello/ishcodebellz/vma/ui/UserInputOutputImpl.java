package bello.ishcodebellz.vma.ui;

import java.util.Scanner;

public class UserInputOutputImpl implements UserInputOutput {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {
        System.out.print(prompt);
        return Double.parseDouble(scanner.nextLine());
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double d;
        do {
            print(prompt);
            d = Double.parseDouble(scanner.nextLine());
            if (d < min || d > max) {
                System.out.println("Invalid input. Enter a number from " + min + " to " + max);
            } else {
                return d;
            }
        } while (true);
    }

    @Override
    public float readFloat(String prompt) {
        print(prompt);
        return Float.parseFloat(scanner.nextLine());
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float f;
        do {
            print(prompt);
            f = Float.parseFloat(scanner.nextLine());
            if (f < min || f > max) {
                System.out.println("Invalid input. Enter a number from " + min + " to " + max);
            } else {
                return f;
            }
        } while (true);
    }

    @Override
    public int readInt(String prompt) {
        print(prompt);
        return Integer.parseInt(scanner.nextLine());
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int i;
        do {
            print(prompt);
            i = Integer.parseInt(scanner.nextLine());
            if (i < min || i > max) {
                System.out.println("Invalid input. Enter a number from " + min + " to " + max);
            } else {
                return i;
            }
        } while (true);
    }

    @Override
    public long readLong(String prompt) {
        print(prompt);
        return Long.parseLong(scanner.nextLine());
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long l;
        do {
            print(prompt);
            l = Long.parseLong(scanner.nextLine());
            if (l < min || l > max) {
                System.out.println("Invalid input. Enter a number from " + min + " to " + max);
            } else {
                return l;
            }
        } while (true);
    }

    @Override
    public String readString(String prompt) {
        print(prompt);
        return scanner.nextLine();
    }

    @Override
    public boolean readYesOrNo(String prompt) {
        print(prompt);
        String input;
        do {
            input = readString("Enter y/n:");
        } while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n"));
        return (input.equalsIgnoreCase("y"));
    }
}
