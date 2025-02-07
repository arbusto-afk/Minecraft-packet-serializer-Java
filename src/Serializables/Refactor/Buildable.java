package Serializables.Refactor;

import Serializables.Refactor.Construct.ConstructItem;

import java.util.*;

public interface Buildable {


    default Buildable getBuildable(){
        return this;
    };
    default Buildable clone(){
        return null;
    };

    default String extractLast(String input) {
        if (input == null || input.isEmpty()) {
            return ""; // Handle null or empty input
        }
        int lastDotIndex = input.lastIndexOf('.');
        return (lastDotIndex != -1) ? input.substring(lastDotIndex + 1) : input;
    }

    default Object getClasses() {
        return this;
    }

}
