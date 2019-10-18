package pl.inome.dictionary;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
class Starter implements CommandLineRunner {

    private DictionaryController dictionaryController;

    Starter(DictionaryController dictionaryController) {
        this.dictionaryController = dictionaryController;
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            dictionaryController.mainLoop();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
