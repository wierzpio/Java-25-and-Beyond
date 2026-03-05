package ch09_syntax_java_22_25.jep467_markdown_documentation_comments;

/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
/// **FETT**  \
/// *kursiv*  \
/// _kursiv_  \
/// _**FETT und KURSIV**_\
/// `code-font` \
/// _**`code-font FETT und KURSIV`**_ \
///
/// Mehrzeiliger Sourcecode:
/// ```
/// public static int max(int a, int b){
///    return (a >= b) ? a : b;
///}
///```
public class MarkDownComment2 {


    public static void main(String[] args) {

    }


    /// Returns the greater of two `int` values. That is, the
    /// result is the argument closer to the value of
    /// [Integer#MAX_VALUE]. If the arguments have the same value,
    /// the result is that same value.
    ///
    /// @param a an argument.
    /// @param b another argument.
    /// @return the larger of `a` and `b`.
    public static int max(int a, int b) {
        return (a >= b) ? a : b;
    }

    /// _italics_
    /// **BOLD**
    ///
    /// - Bullet Point A
    /// * Bullet Point B
    ///
    /// 1. Numbered Point 1
    /// 1. Numbered Point 2
    ///
    /// Normal Link: [Object#equals(Object)], [System#identityHashCode(Object)]
    ///
    /// @see Object#equals(Object)
    /// @see System#identityHashCode(Object)
    public static void referencing(String[] args) {
    }
}
