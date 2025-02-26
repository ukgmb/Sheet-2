package a1.view.configurator;

import a1.model.Monster;
import a1.view.InvalidArgumentException;
import a1.view.Provider;

/**
 * Enum represents all keywords which provide{@link Monster}.
 *
 * @author ukgmb
 */
public enum ProviderMonster implements Provider<Monster, ArgumentsConfiguration> {

    /**
     * Represents the monster keyword in config to declare a monster.
     */
    MONSTER(arguments -> new Monster(arguments.parseName(), arguments.parseElement(), arguments.parsePosInt(),
            arguments.parsePosInt(), arguments.parsePosInt(), arguments.parsePosInt(), arguments.parseActions()));

    private final Provider<Monster, ArgumentsConfiguration> provider;

    ProviderMonster(Provider<Monster, ArgumentsConfiguration> provider) {
        this.provider = provider;
    }

    @Override
    public Monster provide(ArgumentsConfiguration arguments) throws InvalidArgumentException {
        return this.provider.provide(arguments);
    }


}
