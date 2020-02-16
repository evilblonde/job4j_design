package tdd.templetizer;

import java.util.Map;

public interface Template {

    String generate(String template, Map<String, String> params) throws ValidationException;

}
