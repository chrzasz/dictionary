package pl.inome.dictionary.formatter;

import org.springframework.stereotype.Component;

@Component
class BasicTextFormatter implements TextFormatter {

    @Override
    public String format(String text) {
        return text.toLowerCase();
    }
}
