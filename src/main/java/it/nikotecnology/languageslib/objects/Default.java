package it.nikotecnology.languageslib.objects;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Developed by Nikotecnology.
 * You are not allowed to decompile or resell this plugin as yours or anything else.
 *
 * @author Nikotecnology
 */
@Data
public class Default {

    private String path;
    private String message;
    private List<String> stringList;

    public Default(String path, List<String> stringList) {
        this.path = path;
        this.stringList = stringList;
    }

    public Default(String path, String message) {
        this.path = path;
        this.message = message;
    }

}
