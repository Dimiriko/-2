import java.util.Scanner;
import java.util.Random;

// Класс монстра
class Monster {
    private String name;
    private int health;
    private int attackDamage;

    public Monster(String name, int health, int attackDamage) {
        this.name = name;
        this.health = health;
        this.attackDamage = attackDamage;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    // Метод для атаки монстра
    public void attackPlayer() {
        System.out.println("Монстр " + name + " атакует вас!");
    }

    // Метод для получения урона от игрока
    public void takeDamage(int damage) {
        health -= damage;
    }
    public static boolean isMonsterPresent() {
        Random random = new Random();
        return random.nextBoolean(); // Шанс 50% на появление монстра в комнате
    }
}

// Дополненный класс комнаты с монстрами
class Room {
    private String description;
    private String[] actions;
    private Monster[] monsters;

    public Room(String description, String[] actions, Monster[] monsters) {
        this.description = description;
        this.actions = actions;
        this.monsters = monsters;
    }

    public void displayDescription() {
        System.out.println(description);
        for (Monster monster : monsters) {
            System.out.println("Вы видите монстра: " + monster.getName());
        }
    }

    public Monster[] getMonsters() {
        return monsters;
    }

    public String[] getActions() {
        return actions;
    }
    public void updateRoom(boolean hasMonster) {
        if (!hasMonster) {
            // Если монстра не было или он был побежден, уменьшаем шанс его появления
            System.out.println("В этой комнате нет монстров.");
        }
    }
}


// Модифицированный класс игры с боевыми сценами и здоровьем персонажа
public class ${NAME} {
    private static int playerHealth = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Добро пожаловать в подземное царство!");
        System.out.println("Вы находитесь в комнате с тремя дверями. В какую дверь вы пойдете? (1, 2 или 3)");

        int doorChoice = scanner.nextInt();
        scanner.nextLine();

        Room[] rooms = createRooms();

        if (doorChoice >= 1 && doorChoice <= 3) {
            Room currentRoom = rooms[doorChoice - 1];
            boolean hasMonster = Monster.isMonsterPresent(); // Проверка наличия монстра в комнате
            currentRoom.updateRoom(hasMonster); // Обновление комнаты после победы или отсутствия монстра
            currentRoom.displayDescription();

            while (true) {
                String[] actions = currentRoom.getActions();
                Monster[] monsters = currentRoom.getMonsters();

                // Проверка наличия монстра в комнате
                if (hasMonster && monsters.length > 0) {
                    System.out.println("В этой комнате есть монстры! Будьте осторожны.");
                    for (Monster monster : monsters) {
                        monster.attackPlayer();
                        playerHealth -= monster.getAttackDamage();
                        System.out.println("У вас осталось здоровья: " + playerHealth);
                        if (playerHealth <= 0) {
                            System.out.println("Ваше здоровье иссякло. Игра завершена.");
                            System.exit(0);
                        }

                        System.out.println("Что вы хотите сделать? (1 - атаковать, 2 - бежать)");
                        int choice = scanner.nextInt();
                        scanner.nextLine();

                        if (choice == 1) {
                            Random random = new Random();
                            int damageToMonster = random.nextInt(15) + 10; // Рандомный урон игрока от 10 до 25
                            monster.takeDamage(damageToMonster);

                            if (monster.getHealth() <= 0) {
                                System.out.println("Монстр побежден!");

                                // Приз при победе
                                int prize = random.nextInt(3); // Рандомный приз от 0 до 2
                                switch (prize) {
                                    case 0:
                                        System.out.println("Вы получили меч!");
                                        break;
                                    case 1:
                                        System.out.println("Вы получили зелье лечения!");
                                        break;
                                    case 2:
                                        System.out.println("Вы получили щит!");
                                        break;
                                }
                            } else {
                                monster.attackPlayer();
                                System.out.println("Ваше здоровье: " + playerHealth);

                                // Продолжение боя
                                int damageToPlayer = random.nextInt(10) + 5; // Рандомный урон монстра от 5 до 15
                                playerHealth -= damageToPlayer;
                                if (playerHealth <= 0) {
                                    System.out.println("Ваше здоровье иссякло. Игра завершена.");
                                    System.exit(0);
                                }
                                System.out.println("Монстр атакует вас! Ваше здоровье: " + playerHealth);
                            }
                        } else if (choice == 2) {
                            System.out.println("Вы решили сбежать от монстра!");
                            System.out.println("Вы успешно сбежали от монстра!");

                            // Переход к следующей комнате после сбегания от монстра
                            int nextRoom = doorChoice % 3 + 1;
                            currentRoom = rooms[nextRoom - 1];
                            hasMonster = Monster.isMonsterPresent(); // Проверка наличия монстра в следующей комнате
                            currentRoom.updateRoom(hasMonster); // Обновление комнаты после победы или отсутствия монстра
                            currentRoom.displayDescription();
                        } else {
                            System.out.println("Недопустимый выбор.");
                        }
                    }
                }

                for (int i = 0; i < actions.length; i++) {
                    System.out.println((i + 1) + ". " + actions[i]);
                }
                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice >= 1 && choice <= actions.length) {
                    performAction(actions[choice - 1]);
                } else if (choice == 0) {
                    System.out.println("Игра завершена.");
                    break;
                } else {
                    System.out.println("Недопустимый выбор.");
                }
            }
        } else {
            System.out.println("Вы выбрали неправильное число. Игра закончена.");
        }

        scanner.close();
    }
    public static void performAction(String action) {
        if (action.equals("атаковать")) {
            Monster monster = new Monster("Гоблин", 50, 10);
            Random random = new Random();
            int damage = random.nextInt(15) + 10; // Рандомный урон игрока от 10 до 25

            monster.takeDamage(damage);
            if (monster.getHealth() <= 0) {
                System.out.println("Монстр побежден!");

                // Приз при победе
                int prize = random.nextInt(3); // Рандомный приз от 0 до 2
                switch (prize) {
                    case 0:
                        System.out.println("Вы получили меч!");
                        break;
                    case 1:
                        System.out.println("Вы получили зелье лечения!");
                        break;
                    case 2:
                        System.out.println("Вы получили щит!");
                        break;
                }
            } else {
                monster.attackPlayer();
                System.out.println("Ваше здоровье: " + playerHealth);

                // Продолжение боя
                int monsterDamage = random.nextInt(10) + 5; // Рандомный урон монстра от 5 до 15
                playerHealth -= monsterDamage;
                if (playerHealth <= 0) {
                    System.out.println("Ваше здоровье иссякло. Игра завершена.");
                    System.exit(0);
                }
                System.out.println("Монстр атакует вас! Ваше здоровье: " + playerHealth);
            }
        } else if (action.equals("бежать")) {
            System.out.println("Вы решили сбежать от монстра!");
            // Логика ухода от монстра
            System.out.println("Вы успешно сбежали от монстра!");
        }else if (action.equals("включить фонарь")) {
            System.out.println("Вы включили фонарь и увидели следующие двери и ключ:");
            System.out.println("1. Левая дверь");
            System.out.println("2. Правая дверь");
            System.out.println("3. Взять ключ");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Вы открыли левую дверь и продолжили путь.");
                    break;
                case 2:
                    System.out.println("Вы открыли правую дверь и увидели выход!");
                    System.out.println("Поздравляю, вы выбрались из подземелья! Игра завершена.");
                    System.exit(0);
                    break;
                case 3:
                    System.out.println("Вы взяли ключ. Теперь можете продолжить путь.");
                    break;
                default:
                    System.out.println("Недопустимый выбор.");
                    break;
            }
        } else {
            System.out.println("Недопустимый выбор.");
        }
    }
    public static Room[] createRooms() {
        String[] actionsRoom1 = { "атаковать", "бежать" };
        Monster[] monstersRoom1 = { new Monster("Гоблин", 50, 10) };
        Room room1 = new Room("Вы вошли в первую комнату. Вы видите гоблина.", actionsRoom1, monstersRoom1);

        String[] actionsRoom2 = { "включить фонарь", "идти вперед" };
        Room room2 = new Room("Вы вошли во вторую комнату. Здесь темно и вы слышите шум.", actionsRoom2, new Monster[0]);

        String[] actionsRoom3 = { "левая", "правая", "ключ" };
        Room room3 = new Room("Вы в третьей комнате. Здесь есть ключ на столе и две двери: 'левая' и 'правая'.", actionsRoom3, new Monster[0]);

        Room[] rooms = { room1, room2, room3 };
        return rooms;
    }
}