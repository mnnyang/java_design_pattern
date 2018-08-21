package 适配器模式;

/**
 * 适配器模式
 * <p>
 * 应用实例： 1、美国电器 110V，中国 220V，就要有一个适配器将 110V 转化为 220V。
 * 2、JAVA JDK 1.1 提供了 Enumeration 接口，而在 1.2 中提供了 Iterator 接口，
 * 想要使用 1.2 的 JDK，则要将以前系统的 Enumeration 接口转化为 Iterator 接口，这时就需要适配器模式。
 * 3、在 LINUX 上运行 WINDOWS 程序。 4、JAVA 中的 jdbc。
 * <p>
 * 优点： 1、可以让任何两个没有关联的类一起运行。 2、提高了类的复用。 3、增加了类的透明度。 4、灵活性好。
 * <p>
 * 缺点： 1、过多地使用适配器，会让系统非常零乱，不易整体进行把握。
 * 比如，明明看到调用的是 A 接口，其实内部被适配成了 B 接口的实现，一个系统如果太多出现这种情况，无异于一场灾难。
 * 因此如果不是很有必要，可以不使用适配器，而是直接对系统进行重构。
 * 2.由于 JAVA 至多继承一个类，所以至多只能适配一个适配者类，而且目标类必须是抽象类。
 */

//充电器例子 参考 https://blog.csdn.net/zxt0601/article/details/52848004
class Voltage220 {
    public int output220V() {
        int src = 220;
        System.out.println("220v");
        return src;
    }
}

interface Voltage5 {
    int output5V();
}

/**
 * 类适配器模式
 */
class VoltageAdapter extends Voltage220 implements Voltage5 {

    @Override
    public int output5V() {
        int src = output220V();
        System.out.println("适配工作...");
        int dst = src / 44;
        System.out.println("完成输出" + dst);

        return dst;
    }
}

/**
 * 对象适配器模式
 */
class VoltageAdapter2 implements Voltage5 {

    private Voltage220 voltage220;

    public VoltageAdapter2(Voltage220 voltage220) {
        this.voltage220 = voltage220;
    }

    @Override
    public int output5V() {
        int dst = 0;
        if (voltage220 != null) {
            int src = voltage220.output220V();
            System.out.println("适配工作...");
            dst = src / 44;
            System.out.println("完成输出" + dst);
        }
        return dst;
    }
}

class Mobile {
    public void charging(Voltage5 voltage5) {
        if (voltage5.output5V() == 5) {
            System.out.println("正常充电");
        } else {
            System.out.println("电压过大，duang");
        }
    }
}

/**
 * 接口适配器模式
 * 当不需要全部实现接口提供的方法时，可先设计一个抽象类实现接口，并为该接口中每个方法提供一个默认实现（空方法），
 * 那么该抽象类的子类可有选择地覆盖父类的某些方法来实现需求，它适用于一个接口不想使用其所有的方法的情况。
 * 这个在Android中的ValueAnimator监听动画状态有使用：
 * ValueAnimator valueAnimator = ValueAnimator.ofInt(0,100);
 * valueAnimator.addListener(new Animator.AnimatorListener() {
 * <p>
 */


public class Main {
    public static void main(String[] args) {
        System.out.println("类适配器");
        Mobile mobile = new Mobile();
        mobile.charging(new VoltageAdapter());

        System.out.println("对象适配器");
        mobile.charging(new VoltageAdapter2(new Voltage220()));
    }
}