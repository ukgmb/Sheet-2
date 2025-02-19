/*
 * Copyright (c) 2025, KASTEL. All rights reserved.
 */

package a1.view;

/**
 * This interface represents a keyword that can be used to identify a command.
 * @param <T> the type of the value that is handled by the command
 *     
 * @author Programmieren-Team
 */
public interface Keyword<T> extends CommandProvider<T> {

    /**
     * Returns whether the keyword matches the given command.
     * @param command the command to be checked
     * @return whether the keyword matches the command
     */
    boolean matches(String command);
}
