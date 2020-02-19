package tdd.templetizer;

import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringTempletizer implements Template {

    private static final Pattern KEYS = Pattern.compile("\\$\\{\\w+}");

    @Override
    public String generate(String template, Map<String, String> params) throws ValidationException {
        validateTemplate(template);

        StringBuilder builder = new StringBuilder(template);
        Matcher matcher = KEYS.matcher(builder);

        Set<String> keys = matcher.results()
                .map(m -> template.substring(m.start() + 2, m.end() - 1))
                .collect(Collectors.toSet());

        validateParams(params, keys);

        int startIndex = 0;
        while (matcher.find(startIndex)) {
            String param = builder.substring(matcher.start() + 2, matcher.end() - 1);
            String replacement = params.get(param);
            builder.replace(matcher.start(), matcher.end(), replacement);
            startIndex = matcher.start() + replacement.length();
        }

        return builder.toString();
    }

    private void validateTemplate(String template) throws ValidationException {
        if (!KEYS.matcher(template).find()) {
            throw new ValidationException("Please specify at least one parameter in template");
        }
    }

    private void validateParams(Map<String, String> params, Set<String> keys) throws ValidationException {
        if (!params.keySet().containsAll(keys)) {
            keys.removeAll(params.keySet());
            throw new ValidationException("No value found for the placeholders: " + keys);
        }
        if (!keys.containsAll(params.keySet())) {
            StringBuilder extraParameters = new StringBuilder();
            params.entrySet().stream()
                    .filter(e -> !keys.contains(e.getKey()))
                    .forEach(e -> extraParameters
                            .append(' ')
                            .append(e.getKey())
                            .append("{")
                            .append(e.getValue())
                            .append("}"));
            throw new ValidationException("Found parameters with no placeholders matching them:"
                    + extraParameters.toString());
        }
    }
}
