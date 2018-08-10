package 工厂模式;

/**
 * 工厂模式
 * Created by xxyangyoulin on 2018/7/29.
 * <p>
 * 在工厂模式中，我们在创建对象时不会对客户端暴露创建逻辑，
 * 并且是通过使用一个共同的接口来指向新创建的对象。
 * <p>
 * 意图：定义一个创建对象的接口，让其子类自己决定实例化哪一个工厂类，工厂模式使其创建过程延迟到子类进行。
 * 主要解决：主要解决接口选择的问题。
 * 何时使用：我们明确地计划不同条件下创建不同实例时。
 * 如何解决：让其子类实现工厂接口，返回的也是一个抽象的产品。
 * 关键代码：创建过程在其子类执行。
 * 应用实例：
 * 1、您需要一辆汽车，可以直接从工厂里面提货，而不用去管这辆汽车是怎么做出来的，以及这个汽车里面的具体实现。
 * 2、Hibernate 换数据库只需换方言和驱动就可以。
 * <p>
 * 优点：
 * 1、一个调用者想创建一个对象，只要知道其名称就可以了。
 * 2、扩展性高，如果想增加一个产品，只要扩展一个工厂类就可以。
 * 3、屏蔽产品的具体实现，调用者只关心产品的接口。
 * <p>
 * 缺点：
 * 每次增加一个产品时，都需要增加一个具体类和对象实现工厂，使得系统中类的个数成倍增加
 * <p>
 * 注意事项：
 * 作为一种创建类模式，在任何需要生成复杂对象的地方，都可以使用工厂方法模式。
 * 有一点需要注意的地方就是复杂对象适合使用工厂模式，
 * 而简单对象，特别是只需要通过 new 就可以完成创建的对象，无需使用工厂模式。
 * 如果使用工厂模式，就需要引入一个工厂类，会增加系统的复杂度。
 */

/**
 * 抽象产品
 */
interface Shape {
    void draw();
}

/**
 * 产品1
 */
class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}

/**
 * 产品2
 */
class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}

/**
 * 产品3
 */
class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}

class ShapeFactory {
    public enum TYpe {
        CIRCLE, SQUARE, RECTANGLE
    }

    public Shape getShape(TYpe shapeType) {
        if (shapeType == null) {
            return null;
        }

        if (shapeType == TYpe.CIRCLE) {
            return new Circle();

        } else if (shapeType == TYpe.RECTANGLE) {
            return new Rectangle();

        } else if (shapeType == TYpe.SQUARE) {
            return new Square();
        }

        return null;
    }
}

public class Main {
    public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();
        factory.getShape(ShapeFactory.TYpe.CIRCLE).draw();
        factory.getShape(ShapeFactory.TYpe.SQUARE).draw();
        factory.getShape(ShapeFactory.TYpe.RECTANGLE).draw();
    }
}
