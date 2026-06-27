/**
 * Beispielprogramm für die Workshops "Best of Java 11/17 bis 20/21/22/23/24/25" / die Bücher "Java – die Neuerungen in Java 17 LTS, 18 und 19" und "Java 25 LTS"
 * Sample program for the workshops "Best of Java 11/17 to 20/21/22/23/24/25" / the books “Java – the new features in Java 17 LTS, 18, and 19” and “Java 25 LTS and Beyond”
 *
 * @author Michael Inden
 * <p>
 * Copyright 2021/2022/2023/2024/25/26 by Michael Inden
 */
// --illegal-final-field-mutation=debug,
public class ReallyFinalExample {

    static void main() throws NoSuchFieldException, IllegalAccessException {
        accessAndChangeValue(DataContainer.class);
        accessAndChangeRecord(RecordDataContainer.class);
    }

private static void accessAndChangeValue(Class<?> clazz) throws NoSuchFieldException, IllegalAccessException
{
    DataContainer obj = new DataContainer(13);
    IO.println(obj.fixedValue());  // Prints 13

    // Make final field mutable
    java.lang.reflect.Field field = clazz.getDeclaredField("fixedValue");
    field.setAccessible(true);

    // Mutate the final field in the object
    field.set(obj, 666);
    System.out.println(obj.fixedValue());  // Prints 66
}

    private static void accessAndChangeRecord(Class<?> clazz) throws NoSuchFieldException, IllegalAccessException {

        RecordDataContainer obj = new RecordDataContainer(1313);
        System.out.println(obj.fixedValue());  // Prints 1313

        java.lang.reflect.Field field = clazz.getDeclaredField("fixedValue");
        field.setAccessible(true);

        // 3. Mutate the final field in the object
        // Exception in thread "main" java.lang.IllegalAccessException: Can not set final int field reallyfinal.ReallyFinal$D.x to java.lang.Integer
        //	at java.base/jdk.internal.reflect.FieldAccessorImpl.throwFinalFieldIllegalAccessException(FieldAccessorImpl.java:132)
        //	at java.base/jdk.internal.reflect.FieldAccessorImpl.throwFinalFieldIllegalAccessException(FieldAccessorImpl.java:136)
        //	at java.base/jdk.internal.reflect.MethodHandleIntegerFieldAccessorImpl.set(MethodHandleIntegerFieldAccessorImpl.java:107)
        field.set(obj, 666);
        System.out.println(obj.fixedValue());
    }
}
