package 原型模式;

/**
 * clone() Cloneable 和 CloneNotSupportedException.
 * 无论目标类是否实现了Cloneable接口，只要调用到了Object.clone()，比如通过super.clone()，
 * 那么就必须处理或者抛出CloneNotSupportedException，因为Object.clone()有throws这个异常
 * <p>
 * Object.clone(), 间接或者直接调用到了：
 * 1. 检查当前类有没有应用Cloneable接口，没有将会抛出 CloneNotSupportedException
 * 2. 第一步通过之后，将会为当前类创建一个新的对象，并将原对象中的所有字段浅拷贝（直接赋值），
 * 这里就体现了重写clone()的必要性，对象的浅拷贝将会出现问题。
 * <p>
 * 重写clone()为什么必须public
 * 不重写为public直接导致该方法只能在目标内中使用，失去用武之地。
 * <p>
 * 重写clone()方法不一定需要应用Cloneable接口，因为只有执行Object.clone()方法才会做这个检查。
 */

public class CloneTest implements Cloneable {

    String name;

    public String getName() {
        return name;
    }

    public CloneTest(String name) {
        this.name = name;
    }

    @Override
    public CloneTest clone() {

        CloneTest test = null;
        try {
            test = (CloneTest) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return test;
    }

    public static void main(String[] args) {
        CloneTest test = new CloneTest("First");
        CloneTest test2 = test.clone();

        System.out.println(test.getName() + "-" + test2.getName());//First-First
        System.out.println(test.getName().equals(test2.getName()));//true

        String one = "one";
        String two = one;
        System.out.println(one.equals(two));//true
        two = "two";
        System.out.println(one.equals(two));//false

        System.out.println(one + two);//onetwo

        /**
         * clone()方法 浅拷贝的String为一个String对象
         *
         */
    }
}
