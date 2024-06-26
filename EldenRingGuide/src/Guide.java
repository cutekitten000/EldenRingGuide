import java.util.Scanner;

public class Guide {
    private static Scanner sc = new Scanner(System.in);

    public void menu() {
        clearMenu();

        while (true) {
            mainMenu();
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    showMaps();
                    break;
                default:
                    break;
            }
        }
    }

    private void mainMenu() {
        System.out.println();
        System.out.println("========== ELDEN RING GUIDE ==========");
        System.out.println();
        System.out.println("[ 1 ] - Show Routes");
        System.out.println("[ 2 ] - Show Quests");
        System.out.println("[ 3 ] - Exit");
        System.out.println();
        System.out.println("======================================");
        System.out.print(">> ");
    }

    private void showMaps() {
        clearMenu();
        int n = 0;
        System.out.println();
        System.out.println("========== Elden Ring Game Progress Route ==========");
        System.out.println();

        for (ProgressRoute route : ProgressRoute.values()) {
            n++;
            System.out.println("[ " + n + " ] - " + route);
        }
        System.out.println();
        System.out.println("====================================================");
        System.out.println();
        System.out.print(">> ");
    }

    private void clearMenu() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
