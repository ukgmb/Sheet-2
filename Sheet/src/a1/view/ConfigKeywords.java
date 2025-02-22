package a1.view;

/**
 * Enum represents all keywords for commands handled by {@code Configurator}.
 *
 * @author ukgmb
 */
public enum ConfigKeywords implements Keyword<Configurator> {

    /**
     *
     */
    ACTION(),
    MONSTER();

    private final CommandProvider<Configurator> provider;

    ConfigKeywords(CommandProvider<Configurator> provider) {
        this.provider = provider;
    }

    @Override
    public Command<Configurator> provide(ConfigArguments arguments) throws InvalidArgumentException{
        return provider.provide(arguments);
    }

    @Override
    public boolean matches(String keyword) {
        return name().toLowerCase().equals(keyword);
    }


}
