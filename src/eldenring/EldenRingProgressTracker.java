package eldenring;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EldenRingProgressTracker implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Area> areas;
    private transient Scanner sc;

    public EldenRingProgressTracker() {
        areas = new ArrayList<>();
        sc = new Scanner(System.in);
        initializeAreas();
    }

    private void initializeAreas() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("areas.dat"))) {
            areas = (List<Area>) ois.readObject();
        } catch (FileNotFoundException e) {
            areas.add(new Area("West Limgrave", List.of(
                    new Task("Buy essencial gear"),
                    new Task("Obtain Limgrave West Map Fragment"),
                    new Task("Obtain Whetstone Knife"),
                    new Task("Unlock Torrent & Spirit Bell"),
                    new Task("Do Boc the Seamster's Quest"),
                    new Task("Defeat Night's Cavalry"),
                    new Task("Find Sorceress Sellen (if you're a mage)"),
                    new Task("Talk to Roderika"),
                    new Task("Find Ashes of War Merchant"),
                    new Task("Meet Potboy (Alexander)"),
                    new Task("Talk to D, Hunter of the Dead"),
                    new Task("Complete Murkwater Cave")
            ), "Description ================"
            ));
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Data not found, Initializing with default data.");
        }
    }

    private void saveAreas() {
        try (ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream("areas.dat"))) {
            oss.writeObject(areas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearMenu() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void showMainMenu() {
        while (true) {
            clearMenu();
            System.out.println("========== MAIN MENU ==========");
            System.out.println("[ 1 ] - Routes");
            System.out.println("[ 2 ] - Quests");
            System.out.println("[ 3 ] - Exit");
            System.out.println("===============================");
            System.out.print(">> ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    showAreas();
                    break;
                case 3:
                    saveAreas();
                    System.out.println("Data saved successfully. Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Try again!");
            }

        }
    }

    private void showAreas() {
        while (true) {
            clearMenu();
            System.out.println("========== AREAS ==========");

            for (int i = 0; i < areas.size(); i++) {
                System.out.println(("[ " + (i + 1) + " ] " + areas.get(i).getName()));
            }

            System.out.println("[ " + (areas.size() + 1) + " ] Back");
            System.out.print(">> ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice > 0 && choice <= areas.size()) {
                showAreaDetails(areas.get(choice - 1));
            } else if (choice == areas.size() + 1) {
                return;
            } else {
                System.out.println("Invalid option. Try again!");
            }


        }
    }

    private void showAreaDetails(Area area) {
        while (true) {
            clearMenu();
            System.out.println("========== " + area.getName() + " ==========");
            System.out.println("[ 1 ] See diminished route");
            System.out.println("[ 2 ] See detailed route");
            System.out.println("[ 3 ] Back");
            System.out.println("============================================");
            System.out.print(">> ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    showTasks(area);
                    break;
                case 2:
                    showDetailedDescription(area);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option. Try again!");
            }

        }
    }

    private void showTasks(Area area) {
        while (true) {
            clearMenu();
            System.out.println("========== Tasks in " + area.getName() + " ==========");
            List<Task> tasks = area.getTasks();

            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("[ " + (i + 1) + " ] " + tasks.get(i));
            }

            System.out.println("[ " + (tasks.size() + 1) + " ] Back");
            System.out.print("Choose a task to check/uncheck (number): ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice > 0 && choice <= tasks.size()) {
                tasks.get(choice - 1).toggleCompleted();
            } else if (choice == tasks.size() + 1) {
                return;
            } else {
                System.out.println("Invalid option. Try again!");
            }
        }
    }

    private void showDetailedDescription(Area area) {
        clearMenu();
        System.out.println("========== Detailed description of " + area.getName() + " ==========");
        System.out.println(area.getDetailedDescription());
        System.out.println("Press enter to return...");
        sc.nextLine();
    }
}
