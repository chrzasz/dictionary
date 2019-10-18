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
    private ConsoleOutputWriter consoleOutputWriter;

    private static final int UNDEFINED = -1;
    private static final int ADD_ENTRY = 0;
    private static final int TEST = 1;
    private static final int CLOSE_APP = 2;

    @Autowired
    DictionaryController(EntryRepository repository, Scanner scanner, ConsoleOutputWriter consoleOutputWriter) {
        this.repository = repository;
        this.scanner = scanner;
        this.consoleOutputWriter = consoleOutputWriter;
    }

    void mainLoop() {
        consoleOutputWriter.println("Witaj w aplikacji słownika");
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
                consoleOutputWriter.println("Opcja niezdefiniowana");
        }
    }

    private void closeApp() {
        repository.setEntries(repository.getEntries());
        consoleOutputWriter.println("Bye Bye!");
    }

    private void addEntry() {
        consoleOutputWriter.println("Podaj oryginalną frazę");
        String original = scanner.nextLine();
        consoleOutputWriter.println("Podaj tłumaczenie");
        String translation = scanner.nextLine();
        Entry entry = new Entry(original, translation);
        repository.add(entry);
    }

    private void printMenu() {
        consoleOutputWriter.println("Wybierz opcję:");
        consoleOutputWriter.println("0 - Dodaj frazę");
        consoleOutputWriter.println("1 - Test");
        consoleOutputWriter.println("2 - Koniec programu");
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
            consoleOutputWriter.println("brak słówek w bazie!");
            return;
        }
        final int testSize = Math.min(repository.size(), 3);
        Set<Entry> randomEntries = repository.getRandomEntries(testSize);
        int score = 0;
        for (Entry entry : randomEntries) {
            consoleOutputWriter.println(String.format("Podaj tłumaczenie dla :\"%s\"\n", entry.getOriginal()));
            String translation = scanner.nextLine();
            if (entry.getTranslation().equalsIgnoreCase(translation)) {
                consoleOutputWriter.println("Odpowiedź poprawna");
                score++;
            } else {
                consoleOutputWriter.println("Odpowiedź niepoprawna - " + entry.getTranslation());
            }
        }
        consoleOutputWriter.println(String.format("Twój wynik: %d/%d\n", score, testSize));
    }


}
