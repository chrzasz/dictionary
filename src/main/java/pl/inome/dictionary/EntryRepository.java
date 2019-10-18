package pl.inome.dictionary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class EntryRepository {

    private List<Entry> entries;
    private FileService fileService;

    @Autowired
    EntryRepository(FileService fileService) {
        this.fileService = fileService;
        try {
            this.entries = fileService.readAllFile();
            System.out.println("all entries:" + entries);
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

}
