package 建造者模式;

/**
 * 建造者模式
 * Created by xxyangyoulin on 2018/7/29.
 *
 * 使用多个简单的对象一步一步构建成一个复杂的对象。这种类型的设计模式属于创建型模式，
 * 它提供了一种创建对象的最佳方式。
 * 一个 Builder 类会一步一步构造最终的对象。该 Builder 类是独立于其他对象的。
 *
 * 意图：将一个复杂的构建与其表示相分离，使得同样的构建过程可以创建不同的表示。

 主要解决：主要解决在软件系统中，有时候面临着"一个复杂对象"的创建工作，其通常由各个部分的子对象用一定的算法构成；由于需求的变化，这个复杂对象的各个部分经常面临着剧烈的变化，但是将它们组合在一起的算法却相对稳定。

 何时使用：一些基本部件不会变，而其组合经常变化的时候。

 如何解决：将变与不变分离开。

 关键代码：建造者：创建和提供实例，导演：管理建造出来的实例的依赖关系。

 应用实例： 1、去肯德基，汉堡、可乐、薯条、炸鸡翅等是不变的，而其组合是经常变化的，生成出所谓的"套餐"。 2、JAVA 中的 StringBuilder。

 优点： 1、建造者独立，易扩展。 2、便于控制细节风险。

 缺点： 1、产品必须有共同点，范围有限制。 2、如内部变化复杂，会有很多的建造类。

 使用场景： 1、需要生成的对象具有复杂的内部结构。 2、需要生成的对象内部属性本身相互依赖。

 注意事项：与工厂模式的区别是：建造者模式更加关注与零件装配的顺序。
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 食物条
 */
interface Item{
    public String name();
    public Packing packing();
    public float price();
}

/**
 * 包装的接口
 */
interface Packing {
    public String pack();
}

/**
 * 实物包装带
 */
class Wrapper implements Packing{

    @Override
    public String pack() {
        return "Wrapper";
    }
}

/**
 * 瓶子
 */
class Bottle implements Packing{

    @Override
    public String pack() {
        return "Bottle";
    }
}

/**
 * item 接口的抽象类
 */
abstract class Burger implements Item{
    @Override
    public Packing packing() {
        return new Wrapper();
    }


    public abstract float price();
}

abstract class ColdDrink implements Item{
    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract float price();
}

/**
 * Burger ColdDrink 实体类
 */
class VegBurger extends Burger{
    @Override
    public float price() {
        return 25f;
    }

    @Override
    public String name() {
        return "Veg Burger";
    }
}

class ChickenBurger extends Burger{
    @Override
    public float price() {
        return 50.5f;
    }

    @Override
    public String name() {
        return "Chicken Burger";
    }
}

class Coke extends ColdDrink {

    @Override
    public float price() {
        return 30.0f;
    }

    @Override
    public String name() {
        return "Coke";
    }
}

class Pepsi extends ColdDrink {

    @Override
    public float price() {
        return 35.0f;
    }

    @Override
    public String name() {
        return "Pepsi";
    }
}

class Meal{
    private List<Item> items = new ArrayList<>();

    public void addItem(Item item){
        items.add(item);
    }

    public float getCost(){
        float cost = 0.0f;
        for (Item item : items) {
            cost+=item.price();
        }

        return cost;
    }

    public void showItems(){
        for (Item item : items) {
            System.out.print("Item : "+item.name());
            System.out.print(", Packing : "+item.packing().pack());
            System.out.println(", Price : "+item.price());
        }
    }
}

/**
 * 创建一个 MealBuilder 类，实际的 builder 类负责创建 Meal 对象。
 */
class MealBuilder{
    public Meal prepareVegMeal(){
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
        return meal;
    }

    public Meal prepareNonVegMeal (){
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }
}


public class Main {
    public static void main(String[] args) {
        MealBuilder mealBuilder = new MealBuilder();

        Meal vegMeal = mealBuilder.prepareVegMeal();
        System.out.println("Veg Meal");
        vegMeal.showItems();
        System.out.println("Total Cost: " +vegMeal.getCost());

        Meal nonVegMeal = mealBuilder.prepareNonVegMeal();
        System.out.println("\n\nNon-Veg Meal");
        nonVegMeal.showItems();
        System.out.println("Total Cost: " +nonVegMeal.getCost());
    }
}
