package com.opencc.designmode;

/**
 * 在 Java 中，双重检查锁定模式通常包含以下步骤：
 *
 * 检查实例是否已经被创建。
 * 如果没有创建，就加锁。
 * 在加锁区域内再次检查实例是否已经被创建。
 * 如果仍然没有创建，就创建实例。
 * 解锁。
 *
 * 在这个例子中，getInstance() 方法首先检查 instance 是否为 null。如果为 null，则进行同步操作，然后再次检查 instance 是否为 null。
 * 这是因为可能存在多个线程同时到达第一次检查点，但在第二次检查之前，另一个线程可能已经完成了实例化过程。所以，我们需要在加锁范围内进行第二次检查，
 * 以确保只有一条线程能创建实例。双重检查锁定模式的优点在于，大多数情况下，只需要进行一次非同步的快速检查，只有在必要时才进行同步操作。
 * 这减少了同步开销，提高了性能。然而，早期版本的 Java（JDK 1.1 和 JDK 1.2）存在一些问题，可能会导致双重检查锁定模式失效。
 * 从 JDK 1.5 开始，引入了 volatile 关键字来解决这些问题。在上面的例子中，instance 必须标记为 volatile，以确保其他线程能看到正确的初始化状态。
 * 否则，可能会出现所谓的“幻像”（Phantom）单例，即其他线程看到 instance 不为 null，但实际上并没有完全初始化。
 * 总的来说，双重检查锁定模式是一种有效的优化策略，但在实际应用中，应谨慎使用，因为它涉及到复杂的细节和潜在的风险。
 * 在现代 Java 版本中，推荐使用枚举、静态工厂方法或 synchronized 块来实现单例模式，除非你确实需要延迟初始化。
 */

class Singleton{
    private static volatile Singleton singleton;

    private Singleton() {

    }

    public static Singleton getInstance() {
        if (singleton == null) { //第一次检查
            synchronized (Singleton.class) {
                if (singleton == null) { //第二次检查
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}

public class SingletonTest {
    public static void main(String[] args) {
        Singleton Instance = Singleton.getInstance();
        System.out.println(Instance);
    }
}
