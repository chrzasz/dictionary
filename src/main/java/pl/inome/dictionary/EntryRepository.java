package pl.inome.dictionary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.*;

@Repository
class EntryRepository {

    private List<Entry> entries;
    private FileService fileService;

    @Autowired
    EntryRepository(FileService fileService) {
        this.fileService = fileService;
        try {
            this.entries = fileService.readAllFile();
        } catch (IOException e) {
            entries = new ArrayList<>();
            e.printStackTrace();
        }
    }

    List<Entry> getEntries() {
        return entries;
    }

    void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    void add(Entry entry) {
        if (entry.getOriginal().isEmpty() ||
                entry.getTranslation().isEmpty())
            return;

        entries.add(entry);
        try {
            fileService.saveEntries(entries);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    boolean isEmpty() {
        return entries.isEmpty();
    }

    int size() {
        return entries.size();
    }

    Set<Entry> getRandomEntries(int testSize) {
        Random random = new Random();
        Set<Entry> randomEntries = new HashSet<>();
        while (randomEntries.size() < testSize &&
                randomEntries.size() < entries.size()) {
            randomEntries.add(entries.get(random.nextInt(entries.size())));
        }
        return randomEntries;
    }
}
