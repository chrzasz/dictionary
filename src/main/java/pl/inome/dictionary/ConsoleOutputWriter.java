package pl.inome.dictionary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.inome.dictionary.formatter.TextFormatter;

@Component
class ConsoleOutputWriter {

    private TextFormatter textFormatter;

    @Autowired
    public ConsoleOutputWriter(TextFormatter textFormatter) {
        this.textFormatter = textFormatter;
    }

    void println(String text) {
        System.out.println(textFormatter.format(text));
    }
}
