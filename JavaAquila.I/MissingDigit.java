import java.util.Scanner;

public class MissingDigit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the expression:");
        String expr = sc.nextLine();

        expr = expr.replaceAll(" ", "");

        for (int i = 0; i <= 9; i++) {
            String testExpr = expr.replace("?", Integer.toString(i));
            String[] parts = testExpr.split("[+=]");

            if (parts.length == 3) {
                try {
                    int A = Integer.parseInt(parts[0]);
                    int B = Integer.parseInt(parts[1]);
                    int C = Integer.parseInt(parts[2]);

                    if (A + B == C) {
                        System.out.println("The missing digit will be: " + i);
                        return;
                    }
                } catch (NumberFormatException e) {
                    continue;
                }
            }
        }
        System.out.println("No valid digit found.");
    }
}