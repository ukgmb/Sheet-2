/*
 * Copyright (c) 2025, KASTEL. All rights reserved.
 */

package a1.view;

/**
 * This interface represents a keyword that can be used to identify a user's input.
 * @param <T> the type of the value that is handled by the user's input
 * @param <S> the type of arguments
 *     
 * @author ukgmb
 */
public interface Keyword<T, S> extends Provider<T, S> {

    /**
     * Returns whether the keyword matches the given input.
     * @param input the input to be checked
     * @return whether the keyword matches the input
     */
    boolean matches(String input);
}
