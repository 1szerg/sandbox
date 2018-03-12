package patterns;

import java.util.Calendar;
import java.util.Date;

import static com.gmail.user0abc.sandbox.Util.prn;

/**
 * @author Sergii Ivanov
 */
public class AbstractFactory {

    public static void main(String[] args) {
        jdkClassic();
        new AbstractFactory().doAbstractFactory();
    }

    private void doAbstractFactory() {
        String name = "This is a Name to convert";
        AbstractFactoryInterface factoryInterface = new ConcreteFactoryLowerCase();
        prn(factoryInterface.createProduct().convert(name));
        factoryInterface = new ConcreteFactoryUpperCase();
        prn(factoryInterface.createProduct().convert(name));
    }

    private static void jdkClassic() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        prn("today is " + cal.toString());
    }

    interface AbstractFactoryInterface {
        ProductInterface createProduct();
    }

    interface ProductInterface {
        String convert(String name);
    }

    class ConcreteFactoryLowerCase implements AbstractFactoryInterface {
        @Override
        public ProductInterface createProduct() {
            return new ConcreteProductLowerCase();
        }
    }

    class ConcreteFactoryUpperCase implements AbstractFactoryInterface {
        @Override
        public ProductInterface createProduct() {
            return new ConcreteProductUpperCase();
        }
    }

    class ConcreteProductLowerCase implements ProductInterface {
        @Override
        public String convert(String name) {
            return String.valueOf(name).toLowerCase();
        }
    }

    class ConcreteProductUpperCase implements ProductInterface {
        @Override
        public String convert(String name) {
            return String.valueOf(name).toUpperCase();
        }
    }
}
