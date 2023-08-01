package com.example.ipr.singleton;

/**
 * Метод getInstance() является статическим и предоставляет доступ к единственному экземпляру класса.
 * Если экземпляр ещё не был создан, метод создаст новый экземпляр и вернёт его.
 * В последующих вызовах метода будет возвращён уже существующий экземпляр.
 */
public class SingletonExample {
    private static SingletonExample instance;

    private SingletonExample() {
    }

    public static SingletonExample getInstance() {
        if (instance == null) {
            instance = new SingletonExample();
        }
        return instance;
    }
}
