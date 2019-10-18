package pl.inome.dictionary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
class DictionaryController {

    private EntryRepository repository;
    private Scanner scanner;

    @Autowired
    DictionaryController(EntryRepository repository, Scanner scanner) {
        this.repository = repository;
        this.scanner = scanner;
    }


    void test() {
        if (repository.isEmpty()) {
            System.out.println("brak słówek w bazie!");
            return;
        }
        final int testSize = repository.size();
        int score = 0;
        for (Entry entry : repository.getEntries()) {
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
