package abschluss.view;

/**
 * This instance provides an instance with its given arguments.
 * @param <T> The instance to provide
 * @param <S> The arguments to give
 *
 * @author ukgmb
 * @author Programmieren-Team
 */
public interface Provider<T, S> {

    /**
     * Constructs a new {@link T} instance with the given arguments.
     * @param arguments The arguments to be used to construct the {@link T} instance.
     * @return the constructed {@link T} instance
     * @throws InvalidArgumentException if parsing the arguments fails
     */
    T provide(S arguments) throws InvalidArgumentException;
}
