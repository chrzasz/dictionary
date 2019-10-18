package pl.inome.dictionary.formatter;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
class BasicTextFormatter implements TextFormatter {

    @Override
    public String format(String text) {
        return text.toLowerCase();
    }
}
