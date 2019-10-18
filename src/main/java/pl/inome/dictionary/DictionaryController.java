package pl.inome.dictionary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

@Controller
class DictionaryController {

    private EntryRepository repository;
    private Scanner scanner;

    private static final int UNDEFINED = -1;
    private static final int ADD_ENTRY = 0;
    private static final int TEST = 1;
    private static final int CLOSE_APP = 2;

    @Autowired
    DictionaryController(EntryRepository repository, Scanner scanner) {
        this.repository = repository;
        this.scanner = scanner;
    }

    void mainLoop() {
        System.out.println("Witaj w aplikacji słownika");
        int option = UNDEFINED;
        while (option != CLOSE_APP) {
            printMenu();
            option = chooseOption();
            executeOption(option);
        }
    }

    private void executeOption(int option) {
        switch (option) {
            case ADD_ENTRY:
                addEntry();
                break;
            case TEST:
                test();
                break;
            case CLOSE_APP:
                closeApp();
                break;
            default:
                System.out.println("Opcja niezdefiniowana");
        }
    }

    private void closeApp() {
        repository.setEntries(repository.getEntries());
        System.out.println("Bye Bye!");
    }

    private void addEntry() {
        System.out.println("Podaj oryginalną frazę");
        String original = scanner.nextLine();
        System.out.println("Podaj tłumaczenie");
        String translation = scanner.nextLine();
        Entry entry = new Entry(original, translation);
        repository.add(entry);
    }

    private void printMenu() {
        System.out.println("Wybierz opcję:");
        System.out.println("0 - Dodaj frazę");
        System.out.println("1 - Test");
        System.out.println("2 - Koniec programu");
    }

    private int chooseOption() {
        int option;
        try {
            option = scanner.nextInt();
        } catch (InputMismatchException e) {
            option = UNDEFINED;
        } finally {
            scanner.nextLine();
        }
        if (option > UNDEFINED && option <= CLOSE_APP)
            return option;
        else
            return UNDEFINED;
    }


    private void test() {
        if (repository.isEmpty()) {
            System.out.println("brak słówek w bazie!");
            return;
        }
        final int testSize = Math.min(repository.size(), 3);
        Set<Entry> randomEntries = repository.getRandomEntries(testSize);
        int score = 0;
        for (Entry entry : randomEntries) {
            System.out.printf("Podaj tłumaczenie dla :\"%s\"\n", entry.getOriginal());
            String translation = scanner.nextLine();
            if (entry.getTranslation().equalsIgnoreCase(translation)) {
                System.out.println("Odpowiedź poprawna");
                score++;
            } else {
                System.out.println("Odpowiedź niepoprawna - " + entry.getTranslation());
            }
        }
        System.out.printf("Twój wynik: %d/%d\n", score, testSize);
    }


}
