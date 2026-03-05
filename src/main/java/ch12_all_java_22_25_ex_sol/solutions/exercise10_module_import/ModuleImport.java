package ch12_all_java_22_25_ex_sol.solutions.exercise10_module_import;

import module java.base;
import module java.desktop;

import java.util.List;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
public class ModuleImport {
    void main() {
        List<String> drinks = Stream.of("apple juice", "beer", "tee",
                "water", "milk", "coffee").toList();
        var mapping = categorizeToFirstLetter(drinks);

        IO.println("Mapping of " + LocalDate.now() + ": " + mapping);
        performUiStuff(mapping);
    }

    private static Map<String, String> categorizeToFirstLetter(List<String> values) {
        return values.stream().collect(Collectors.toMap(toUpperFirstChar(), Function.identity()));
    }

    private static Function<String, String> toUpperFirstChar() {
        return str -> str.substring(0, 1).toUpperCase();
    }

    private static void performUiStuff(Map<String, String> mapping) {
        JOptionPane.showMessageDialog(null, "Mapping of " + LocalDate.now() + ": " + mapping);

        Toolkit.getDefaultToolkit().beep();
        JOptionPane optionPane = new JOptionPane(mapping, JOptionPane.WARNING_MESSAGE);
        JDialog dialog = optionPane.createDialog("Mapping of " + LocalDate.now());
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }
}
