package a1.view;

import a1.model.Effect;
import a1.model.Element;

import java.util.ArrayList;
import java.util.Collection;

public class Arguments {


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
