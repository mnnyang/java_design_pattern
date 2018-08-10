package 单例模式;

/**
 * Created by xxyangyoulin on 2018/7/29.
 */

/**
 * 1.
 * 懒汉式
 * 懒加载 - 线程不安全
 */
class Singleton {
    private static Singleton instance;
    private Singleton (){}

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

/**
 * 2.
 * 懒汉式
 * 懒加载 -线程安全
 */
class Singleton2 {
    private static Singleton2 instance;
    private Singleton2 (){}
    public static synchronized Singleton2 getInstance() {
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }
}

/**
 * 3.
 * 饿汉式
 * 非懒加载 - 线程安全
 */
class Singleton3 {
    private static Singleton3 instance = new Singleton3();
    private Singleton3 (){}
    public static Singleton3 getInstance() {
        return instance;
    }
}

/**
 * 4.
 * 双检锁
 * 懒加载 - 线程安全
 * 这种方式采用双锁机制，安全且在多线程情况下能保持高性能。
 *
 * 注释：
 * volatile具备两种特性，
 * 第一就是保证共享变量对所有线程的可见性。将一个共享变量声明为volatile后，会有以下效应：
 1.当写一个volatile变量时，JMM会把该线程对应的本地内存中的变量强制刷新到主内存中去；
 2.这个写会操作会导致其他线程中的缓存无效。
 3.轻量级锁。
 这样Singleton赋值之后，其他线程将会立即得知。
 */
class Singleton4 {
    private volatile static Singleton4 Singleton4;
    private Singleton4 (){}
    public static Singleton4 getSingleton4() {
        if (Singleton4 == null) {
            synchronized (Singleton4.class) {
                if (Singleton4 == null) {
                    Singleton4 = new Singleton4();
                }
            }
        }
        return Singleton4;
    }
}

/**
 * 5.
 * 登记式/静态内部类
 * 懒加载 - 线程安全
 */
 class Singleton5 {
    private static class Singleton5Holder {
        private static final Singleton5 INSTANCE = new Singleton5();
    }
    private Singleton5 (){}
    public static Singleton5 getInstance() {
        return Singleton5Holder.INSTANCE;
    }
}

/**
 * 6.
 * 枚举
 * 非懒加载 - 线程安全
 * 这种实现方式还没有被广泛采用，但这是实现单例模式的最佳方法。
 * 它更简洁，自动支持序列化机制，绝对防止多次实例化。
 这种方式是 Effective Java 作者 Josh Bloch 提倡的方式，
 它不仅能避免多线程同步问题，而且还自动支持序列化机制，
 防止反序列化重新创建新的对象，绝对防止多次实例化。
 不过，由于 JDK1.5 之后才加入 enum 特性，
 用这种方式写不免让人感觉生疏，在实际工作中，也很少用。
 */
 enum Singleton6 {
    INSTANCE;
    public void whateverMethod() {
        System.out.println("S6");
    }
}

/**
经验之谈：一般情况下，不建议使用第 1 种和第 2 种懒汉方式，建议使用第 3 种饿汉方式。
        只有在要明确实现 lazy loading 效果时，才会使用第 5 种登记方式。
        如果涉及到反序列化创建对象时，可以尝试使用第 6 种枚举方式。
        如果有其他特殊的需求，可以考虑使用第 4 种双检锁方式。
*/
public class Main {
    public static void main(String[] args) {
        Singleton6 singleton6 = Singleton6.INSTANCE;
        singleton6.whateverMethod();
    }
}
