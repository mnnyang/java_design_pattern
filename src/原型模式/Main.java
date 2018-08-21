package 原型模式;

import java.util.Hashtable;

/**
 * 原型模式（Prototype Pattern）是用于创建重复的对象，同时又能保证性能。
 * 这种类型的设计模式属于创建型模式。它提供了一种创建对象的最佳方式。
 * <p>
 * 这种模式是实现了一个原型接口，该接口用于创建当前对象的克隆。
 * 当直接创建对象的代价比较大时，则采用这种模式。
 * 例如，一个对象需要在一个高代价的数据库操作之后被创建。我们可以缓存该对象，
 * 在下一个请求时返回它的克隆，在需要的时候更新数据库，以此来减少数据库调用。
 * <p>
 * 意图：用原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象。
 * <p>
 * 主要解决：在运行期建立和删除原型。
 *
 * 应用实例： 1、细胞分裂。 2、JAVA 中的 Object clone() 方法。
 * <p>
 * 优点： 1、性能提高。 2、逃避构造函数的约束。
 * 使用场景： 1、资源优化场景。 2、类初始化需要消化非常多的资源，这个资源包括数据、硬件资源等。
 * 3、性能和安全要求的场景。 4、通过 new 产生一个对象需要非常繁琐的数据准备或访问权限，则可以使用原型模式。
 * 5、一个对象多个修改者的场景。
 * 6、一个对象需要提供给其他对象访问，而且各个调用者可能都需要修改其值时，可以考虑使用原型模式拷贝多个对象供调用者使用。
 * 7、在实际项目中，原型模式很少单独出现，一般是和工厂方法模式一起出现，通过 clone 的方法创建一个对象，然后由工厂方法提供给调用者。
 *   原型模式已经与 Java 融为浑然一体，大家可以随手拿来使用。
 *
 * 注意事项：与通过对一个类进行实例化来构造新对象不同的是，原型模式是通过拷贝一个现有对象生成新对象的。
 * 浅拷贝实现 Cloneable，重写，深拷贝是通过实现 Serializable 读取二进制流。
 */

abstract class Shape implements Cloneable {
    private String id;
    protected String type;

    abstract void draw();

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 注意这里的 clone() is public
     * 关于 Cloneable CLoneTest.java 说明。
     *
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return clone;
    }
}

class Rectangle extends Shape {

    public Rectangle() {
        type = "Rectangle";
    }

    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}

class Square extends Shape {

    public Square() {
        type = "Square";
    }

    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}

class Circle extends Shape {

    public Circle() {
        type = "Circle";
    }

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}

/**
 * 一个需要从数据库获取的实体类
 */
class ShapeCache {
    private static Hashtable<String, Shape> shapeMap = new Hashtable<String, Shape>();

    public static Shape getShape(String shapeId) {
        Shape cachedShape = shapeMap.get(shapeId);

        /*以克隆的方式传递出去*/
        return (Shape) cachedShape.clone();
    }

    public static void loadCache() {

        /*模拟已经从数据库获取数据*/
        Circle circle = new Circle();
        circle.setId("1");
        shapeMap.put(circle.getId(), circle);

        Square square = new Square();
        square.setId("2");
        shapeMap.put(square.getId(), square);

        Rectangle rectangle = new Rectangle();
        rectangle.setId("3");

        shapeMap.put(rectangle.getId(), rectangle);
    }

}

public class Main {
    public static void main(String[] args) {
        ShapeCache.loadCache();

        Shape clonedShape = (Shape) ShapeCache.getShape("1");
        System.out.println("Shape : " + clonedShape.getType());

        Shape clonedShape2 = (Shape) ShapeCache.getShape("2");
        System.out.println("Shape : " + clonedShape2.getType());

        Shape clonedShape3 = (Shape) ShapeCache.getShape("3");
        System.out.println("Shape : " + clonedShape3.getType());
    }
}


