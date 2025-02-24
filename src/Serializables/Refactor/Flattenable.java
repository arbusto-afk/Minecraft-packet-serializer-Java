package Serializables.Refactor;

import Serializables.Types.Pair;

import java.util.ArrayList;
import java.util.List;

public interface Flattenable {

    default Flattenable clone(){
        throw new RuntimeException("Clone not implemented for: "+this.getClass().getName());
    };

    default String extractLast(String input) {
        if (input == null || input.isEmpty()) {
            return ""; // Handle null or empty input
        }
        int lastDotIndex = input.lastIndexOf('.');
        return (lastDotIndex != -1) ? input.substring(lastDotIndex + 1) : input;
    }

    default Flattenable[] flatten() {
        return new Flattenable[]{this};
    }

    default String stringify(String name) { return this.getClass().getSimpleName() + "\n";}

    //Desc, str
    default Pair<String, String>[] fsArr(String name){ return new Pair[]{new Pair<>("-", this.getClass().getSimpleName())};}

    default String fieldNamesAsCommaSeparatedString(String name) {
        String s;
        if(this instanceof ContainerBuildable cb){
            s = cb.stringify();
        } else if(this instanceof ContainerField cf){
            s = cf.stringify();
        } else {
            s = this.stringify(name);
        }
        List<String> fields = new ArrayList<>();
        for(String subs : s.split("\n")){
            if(!subs.startsWith("//") && !subs.startsWith("\t//") && !subs.startsWith("\t\t//")) {
                String[] words = subs.split(" ");
                if (words[words.length - 1].length() == 0) {
                    System.out.println();
                } else {
                    fields.add(words[words.length - 1].substring(0, words[words.length - 1].length() - 1));
                }
            }
        }
        return String.join(", ", fields);
    }
}
