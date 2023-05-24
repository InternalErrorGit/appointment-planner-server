package net.internalerror.appointmentplannerserver;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MutationState {
    MUTATION_SUCCESSFUL(false),
    MUTATION_VALID(false),
    OCCUPIED(true);


    private final boolean isError;
}
