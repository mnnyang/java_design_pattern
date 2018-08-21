package 桥接模式;

/**
 * 桥接（Bridge）是用于把抽象化与实现化解耦，使得二者可以独立变化。
 * 这种类型的设计模式属于结构型模式，它通过提供抽象化和实现化之间的桥接结构，来实现二者的解耦。
 * <p>
 * 意图：将抽象部分与实现部分分离，使它们都可以独立的变化。
 * <p>
 * 如何解决：把这种多角度分类分离出来，让它们独立变化，减少它们之间耦合。
 * <p>
 * 根据实际需要对形状和颜色进行组合
 * <p>
 * 对于有两个变化维度（即两个变化的原因）的系统，采用方案二来进行设计系统中类的个数更少，且系统扩展更为方便。设计方案二即是桥接模式的应用。
 * 桥接模式将继承关系转换为关联关系，从而降低了类与类之间的耦合，减少了代码编写量。
 */

interface Draw {
    void drawCircle(int radius, int x, int y);
}

class RedCircle implements Draw {
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: red, radius: "
                + radius + ", x: " + x + ", " + y + "]");
    }
}

class GreenCircle implements Draw {
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: green, radius: "
                + radius + ", x: " + x + ", " + y + "]");
    }
}

abstract class Shape {
    protected Draw drawAPI;

    protected Shape(Draw draw) {
        this.drawAPI = draw;
    }

    public abstract void draw();
}

class Circle extends Shape {
    private int x, y, radius;

    protected Circle(int x, int y, int radius, Draw draw) {
        super(draw);
        this.x = x;
        this.y = y;

        this.radius = radius;
    }

    @Override
    public void draw() {
        drawAPI.drawCircle(radius, x, y);
    }
}


public class Main {
    public static void main(String[] args) {
        Shape redCircle = new Circle(100, 100, 10, new RedCircle());
        Shape greenCircle = new Circle(100, 100, 100, new GreenCircle());

        redCircle.draw();
        greenCircle.draw();
    }
}
