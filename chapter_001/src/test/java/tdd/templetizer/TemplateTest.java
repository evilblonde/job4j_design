package tdd.templetizer;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class TemplateTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void whenOneParamThenParamApplied() throws ValidationException {
        Template template = new StringTempletizer();
        String input = "Give me the ${item}!";
        Map<String, String> values = new HashMap<>();
        values.put("item", "key");
        String expected = "Give me the key!";

        String actual = template.generate(input, values);

        assertEquals(expected, actual);
    }

    @Test
    public void whenSeveralParamsThenAllApplied() throws ValidationException {
        Template template = new StringTempletizer();
        String input = "Stand strong, ${user}, the ${target} is near! Don't let the ${enemy} get us!";
        Map<String, String> values = new HashMap<>();
        values.put("user", "CJ");
        values.put("target", "Groove Street");
        values.put("enemy", "Ballas");
        String expected = "Stand strong, CJ, the Groove Street is near! Don't let the Ballas get us!";

        String actual = template.generate(input, values);

        assertEquals(expected, actual);
    }

    @Test
    public void whenSeveralPlaceholdersThenReplaceAll() throws ValidationException {
        Template template = new StringTempletizer();
        String input = "There were three ${lastName} sisters: ${nameOne} ${lastName}, ${nameTwo} ${lastName} " +
                "and ${nameThree} ${lastName}";
        Map<String, String> values = new HashMap<>();
        values.put("nameOne", "Mary");
        values.put("nameTwo", "Joanna");
        values.put("nameThree", "Susan");
        values.put("lastName", "Hunnam");
        String expected = "There were three Hunnam sisters: Mary Hunnam, " +
                "Joanna Hunnam and Susan Hunnam";

        String actual = template.generate(input, values);

        assertEquals(expected, actual);
    }

    @Test
    public void whenNoParamsInTemplateThenThrowException() throws ValidationException {
        Template template = new StringTempletizer();
        String input = "No params given";
        Map<String, String> values = new HashMap<>();
        values.put("nameOne", "Mary");

        exceptionRule.expect(ValidationException.class);
        exceptionRule.expectMessage("Please specify at least one parameter in template");

        template.generate(input, values);
    }

    @Test
    public void whenExtraValuesThenThrowException() throws ValidationException {
        Template template = new StringTempletizer();
        String input = "Give me the ${item}!";
        Map<String, String> values = new HashMap<>();
        values.put("item", "key");
        values.put("tag", "value");

        exceptionRule.expect(ValidationException.class);
        exceptionRule.expectMessage("Found parameters with no placeholders matching them: tag{value}");

        template.generate(input, values);
    }

    @Test
    public void whenNoValueForPlaceholderThenThrowException() throws ValidationException {
        Template template = new StringTempletizer();
        String input = "${user}, give me the ${color} ${item}!";
        Map<String, String> values = new HashMap<>();
        values.put("item", "key");

        exceptionRule.expect(ValidationException.class);
        exceptionRule.expectMessage("No value found for the placeholders: [color, user]");

        template.generate(input, values);
    }
}