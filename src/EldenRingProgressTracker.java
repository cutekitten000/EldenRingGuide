import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Classe principal do aplicativo
public class EldenRingProgressTracker implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Area> areas;
	private transient Scanner scanner;

	public EldenRingProgressTracker() {
		areas = new ArrayList<>();
		scanner = new Scanner(System.in);
		initializeAreas();
	}

	private void initializeAreas() {
		// Carregar dados do arquivo, se existirem
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("areas.dat"))) {
			areas = (List<Area>) ois.readObject();
		} catch (FileNotFoundException e) {
			// Arquivo não encontrado, inicializa com dados padrão
			areas.add(new Area("West Limgrave", List.of(
					new Task("Buy Essential Gear"),
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
			), "Elden Ring Map: Limgrave Region starting point. Limgrave has a lot of places to visit, but only a few of them are essential. Right next to the First Step Site of Grace you'll find White Mask Varre. Exhaust his dialogue for some info about the world and to start his quest. Your first step will be to head to Church of Elleh and meet Merchant Kale, one of the Nomadic Merchants of the game. Patrolling between the starting point and the church is a Tree Sentinel boss. He will be extremely difficult at this stage of the game, so it is strongly suggested to avoid him until you unlock your horse and become stronger. Defeating him rewards you with his Golden Halberd. At the church, buy the Crafting Kit Kale offers, a Torch, and the Telescope. Also make note of the nearby upgrade bench. You can do optional cave and catacomb objectives to gain talismans and materials as needed.\n"
					+ "\n"
					+ "Head over to Gatefront Ruins and obtain the Limgrave West map fragment for the area. Also pick up the Whetstone Knife so you can use Skills in your weapon, and get Ash of War: Storm Stomp from an underground chest.\n"
					+ "\n"
					+ "Visit 3 sites of grace, or rest in the Gatefront Site of Grace to speak with Melina and unlock Torrent, your mount. After this, teleport to the Church of Elleh Site of Grace to meet Renna, and unlock the Spirit Calling Bell and get the Lone Wolf Ashes.\n"
					+ "\n"
					+ "South-east from Gatefront Ruins (past east of the telescope on the map), you'll hear a shout belonging to Boc the Seamster. He's been transformed into a small red tree, so hit the small tree once and talk to him to begin his questline (see Side Quests for other quests). This quest will take you to the Coastal Cave (southern portion of the western beach), which also unlocks access to the Church of Dragon Communion (if you continue further in the cave without exiting it via portal). Defeat the demi-humans in the Coastal Cave, then go back to Boc at the entrance to return his Sewing Needle and Tailoring Tools. South-east from Boc's first location, a Night's Cavalry will spawn during night right on the bridge. Defeating him awards the Ash of War: Repeating Thrust.\n"
					+ "\n"
					+ "If you are a mage or intend to learn sorceries, you will want to unlock Sorceress Sellen in Waypoint Ruins - follow the road south from Gatefront Ruins to find it. She's past the Mad Pumkin Head Boss fight.\n"
					+ "\n"
					+ "Head towards Stormhill (past the Gatefront grace leading west) and talk to Roderika to begin her quest and receive the Sitting Sideways gesture, alongside the Spirit Jellyfish Ashes. If you want to see Roderika's quest in its entirety, make sure to complete Stormveil Castle before visiting the Roundtable Hold for the first time. You will pick up a Golden Seed just before arriving at Stormhill Shack. There's also a Stonesword Key here that you will want to start collecting.\n"
					+ "\n"
					+ "Follow the road East from the Stormhill Shack in Stormhill and you'll come to Warmaster's Shack, where you can purchase Ashes of War from Knight Bernahl. At night, Bernahl will be gone, and a Bell Bearing Hunter boss will spawn as you enter the shack. Note that this boss may be extremely challenging at this point in the game. Defeating him awards the Bone Peddler's Bell Bearing. Similar to ashes hunter badges in previous games, Bell Bearings unlock new items in the game's primary shop when given to the Twin Maiden Husks after you arrive at Roundtable Hold. Just south from here in the hills where the trolls are, you can visit at night and be invaded by a Deathbird mini-boss and get the Blue-Feathered Branchsword talisman.\n"
					+ "\n"
					+ "Continue northeast along the road from Warmaster's Shack and turn right as you see a bridge and hear someone shouting. There is a small path to take up the cliff to your right hand side. You will meet Alexander (Potboy) and can free him to begin his quest and earn the Triumphant Delight gesture and 1x Exalted Flesh. You have to hit him with a heavy attack or several times from behind.\n"
					+ "\n"
					+ "Before we proceed, there's another dungeon nearby with useful loot, especially if you're going for an assassin type class. Return a bit west, to the barricades you passed through where you just fought the dog and commoners. Leave the barricades and head east along the road towards the bridge again. This time stick to the left of the road. Before the bridge, follow the path next to the cliff and you will find Deathtouched Catacombs. Inside there will be gathering/farm materials, upgrade materials, an Uchigatana, the talisman Assassin's Crimson Dagger and a Deathroot.\n"
					+ "\n"
					+ "Further down the road, past the bridge, you can meet D, Hunter of the Dead, and begin his quest (if you already reached the Roundtable Hold before this point, he will not show up since you would have first met him at the hold instead of near past the bridge, but quest progression remains the same).\n"
					+ "\n"
					+ "You will now want to return to the lower area of Limgrave, to go up the ravine from Agheel Lake until you find Murkwater Cave that has a special surprise, and then Murkwater Catacombs. You will get invaded here, so be careful! If you wait a bit to defeat the invader, Bloody Finger Hunter Yura will show up to help you defeat him. You can meet him back north of Murkwater Cave afterwards, where you can exhaust his dialogue to start his quest.",
					"Quest"
			));
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Nenhum dado salvo encontrado, inicializando com dados padrão.");
		}
	}

	// Salvar dados em arquivo
	private void saveAreas() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("areas.dat"))) {
			oos.writeObject(areas);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Limpar o menu
	private void clearMenu() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	// Exibir o menu principal
	private void showMainMenu() {
		while (true) {
			clearMenu();
			System.out.println("=== Menu Principal ===");
			System.out.println("1. Rotas");
			System.out.println("2. Quests");
			System.out.println("3. Sair");
			System.out.print("Escolha uma opção: ");
			int choice = scanner.nextInt();
			scanner.nextLine();  // Consumir nova linha

			switch (choice) {
				case 1:
					showAreasMenu();
					break;
				case 2:
					showQuestsMenu();
					break;
				case 3:
					saveAreas();
					System.out.println("Dados salvos. Saindo...");
					return;
				default:
					System.out.println("Opção inválida. Tente novamente.");
			}
		}
	}

	// Exibir o menu de áreas
	private void showAreasMenu() {
		while (true) {
			clearMenu();
			System.out.println("=== Áreas ===");
			for (int i = 0; i < areas.size(); i++) {
				System.out.println((i + 1) + ". " + areas.get(i).getName());
			}
			System.out.println((areas.size() + 1) + ". Voltar");
			System.out.print("Escolha uma área: ");
			int choice = scanner.nextInt();
			scanner.nextLine();  // Consumir nova linha

			if (choice > 0 && choice <= areas.size()) {
				showAreaDetails(areas.get(choice - 1));
			} else if (choice == areas.size() + 1) {
				return;
			} else {
				System.out.println("Opção inválida. Tente novamente.");
			}
		}
	}

	// Exibe os detalhes de uma área
	private void showAreaDetails(Area area) {
		while (true) {
			clearMenu();
			System.out.println("=== " + area.getName() + " ===");
			System.out.println("1. Ver Rota Resumida");
			System.out.println("2. Ver Rota Detalhada");
			System.out.println("3. Voltar");
			System.out.print("Escolha uma opção: ");
			int choice = scanner.nextInt();
			scanner.nextLine();  // Consumir nova linha

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
					System.out.println("Opção inválida. Tente novamente.");
			}
		}
	}

	// Exibe as tarefas de uma área
	private void showTasks(Area area) {
		while (true) {
			clearMenu();
			System.out.println("=== Tarefas em " + area.getName() + " ===");
			List<Task> tasks = area.getTasks();
			for (int i = 0; i < tasks.size(); i++) {
				System.out.println((i + 1) + ". " + tasks.get(i));
			}
			System.out.println((tasks.size() + 1) + ". Voltar");
			System.out.print("Escolha uma tarefa para marcar/desmarcar (número): ");
			int choice = scanner.nextInt();
			scanner.nextLine();  // Consumir nova linha

			if (choice > 0 && choice <= tasks.size()) {
				tasks.get(choice - 1).toggleCompleted();
			} else if (choice == tasks.size() + 1) {
				return;
			} else {
				System.out.println("Opção inválida. Tente novamente.");
			}
		}
	}

	// Exibe a descrição detalhada de uma área
	private void showDetailedDescription(Area area) {
		clearMenu();
		System.out.println("=== Descrição Detalhada de " + area.getName() + " ===");
		System.out.println(area.getDetailedDescription());
		System.out.println("Pressione Enter para voltar.");
		scanner.nextLine();
	}

	// Exibir o menu de quests
	private void showQuestsMenu() {
		while (true) {
			clearMenu();
			System.out.println("=== Quests ===");
			for (int i = 0; i < areas.size(); i++) {
				System.out.println((i + 1) + ". " + areas.get(i).getName());
			}
			System.out.println((areas.size() + 1) + ". Voltar");
			System.out.print("Escolha uma área para ver as quests: ");
			int choice = scanner.nextInt();
			scanner.nextLine();  // Consumir nova linha

			if (choice > 0 && choice <= areas.size()) {
				showQuests(areas.get(choice - 1));
			} else if (choice == areas.size() + 1) {
				return;
			} else {
				System.out.println("Opção inválida. Tente novamente.");
			}
		}
	}

	// Exibe as quests de uma área
	private void showQuests(Area area) {
		clearMenu();
		System.out.println("=== Quests em " + area.getName() + " ===");
		System.out.println(area.getQuestsDescription());
		System.out.println("Pressione Enter para voltar.");
		scanner.nextLine();
	}

	public static void main(String[] args) {
		EldenRingProgressTracker tracker = new EldenRingProgressTracker();
		tracker.showMainMenu();
	}
}
