package a1.view;

import a1.model.effects.Effect;
import a1.model.Element;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ArgumentsCommand {

    private List<String> arguments;

    public ArgumentsCommand(List<String> arguments) {
        this.arguments = arguments;
    }


    public String hello() {
        return "Hello";
    }

    public Collection<Effect> effect() {
        return new ArrayList<>();
    }

    public Element element() {
        return Element.EARTH;
    }
}
