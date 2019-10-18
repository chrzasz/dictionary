package pl.inome.dictionary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Repository
public class EntryRepository {

    private List<Entry> entries;
    private FileService fileService;

    @Autowired
    EntryRepository(FileService fileService) {
        this.fileService = fileService;
        try {
            this.entries = fileService.readAllFile();
        } catch (IOException e) {
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
