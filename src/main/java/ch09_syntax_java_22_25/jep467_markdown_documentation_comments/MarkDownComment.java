package ch09_syntax_java_22_25.jep467_markdown_documentation_comments; /**
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
/// ^^Mehrzeiliger Sourcecode:^^
/// ```
 /// public static int max(int a, int b) {
 ///    return (a >= b) ? a : b;
 /// }
 /// ```
/// 1. Demo
/// 1. Demo
/// 1. Demo
/// 4. Demo
/// 5. Demo
/// 4. Demo
/// 4. Demo
/// 4. Demo
/// 9. Demo
/// 10. Demo
/// 11. Demo
/// 12. Demo
///
public class MarkDownComment {

    public static void main(String[] args) {

    }


    /// Returns the greater of two `int` values. That is, the
    /// result is the argument closer to the value of
    /// [Integer#MAX_VALUE]. If the arguments have the same value,
    /// the result is that same value.
    ///
    /// @param   a   an argument.
    /// @param   b   another argument.
    /// @return  the larger of `a` and `b`.
    public static int max(int a, int b) {
        return (a >= b) ? a : b;
    }

    
/// - Punkt A
/// * Punkt B
/// - Punkt C
///
/// 1. Eintrag 1
/// 1. Eintrag 2 -- **wird automatisch nummeriert, also 2.**
/// 1. Eintrag 3
/// 2. Eintrag 4 -- **wird automatisch auf 4. geändert**
public static void lists(String[] args) {
}

/// | Latein | Griechisch |
/// |--------|------------|
/// | a      | &alpha; (alpha) |
/// | b      | &beta; (beta)  |
/// | c      | &gamma; (gamma) // &Gamma; |
/// | ...    |  ... |
/// | z      | &omega; (omega) |
public static void simpletable(String[] args) {
}

    /// | Spalte A | Spalte B | Spalte C | Spalte D |
    /// |-------|-------|-------------|-------|
    /// | one   | two   | three       | _last_ |
    /// | one   | two   | three       | **last** |
    /// |-------|-------|-------------|-------|
    /// | one   | two   | three       | _**last**_ |
    /// |-------|-------|-------------|`----`|
    /// | Euro  | &euro; | &#8364; | &#x1F44D; |
    public static void advancedtable(String[] args) {
    }

/// [java.base/] - verweist auf ein Modul \
/// [java.util] - verweist auf ein Package
///
/// Hinweis: _**`java.lang`**_ kann man im Verweis weglassen: \
/// [String] - verweist auf eine Klasse \
/// [String] - verweist auf eine Klasse \
/// [Integer] - verweist auf eine Klasse
///
/// [String#chars()] - verweist auf eine Methode \
/// [Integer#valueOf(String, int)] - verweist auf eine Methode
///
/// [String#CASE_INSENSITIVE_ORDER] - verweist auf ein Attribut \
/// [SPECIAL_ORDER][String#CASE_INSENSITIVE_ORDER] - Verweis mit Beschriftung
public static void referencing(String[] args) {
}

    /// Returns a hash code value for the object. This method is
    /// supported for the benefit of hash tables such as those provided by
    /// [java.util.HashMap].
    ///
    /// The general contract of `hashCode` is:
    ///
    ///   - Whenever it is invoked on the same object more than once during
    ///     an execution of a Java application, the `hashCode` method
    ///     must consistently return the same integer, provided no information
    ///     used in `equals` comparisons on the object is modified.
    ///     This integer need not remain consistent from one execution of an
    ///     application to another execution of the same application.
    ///   - If two objects are equal according to the
    ///     [equals][#equals(Object)] method, then calling the
    ///     `hashCode` method on each of the two objects must produce the
    ///     same integer result.
    ///   - It is _not_ required that if two objects are unequal
    ///     according to the [equals][#equals(Object)] method, then
    ///     calling the `hashCode` method on each of the two objects
    ///     must produce distinct integer results.  However, the programmer
    ///     should be aware that producing distinct integer results for
    ///     unequal objects may improve the performance of hash tables.
    ///
    /// As far as is reasonably practical, the `hashCode` method defined
    /// by class `Object` returns distinct integers for distinct objects.
    ///
    /// @return  a hash code value for this object.
    /// @see     Object#equals(Object)
    /// @see     System#identityHashCode
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
