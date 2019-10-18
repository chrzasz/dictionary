package pl.inome.dictionary;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EntryRepository {

    private List<Entry> entries;

    EntryRepository() {
        this.entries = new ArrayList<>();
        entries.add(new Entry("aaa", "aaa"));
        System.out.println("all entries:" + entries);
    }

    List<Entry> getEntries() {
        return entries;
    }

    void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

}
