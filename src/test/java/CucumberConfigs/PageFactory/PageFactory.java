package CucumberConfigs.PageFactory;
import java.lang.reflect.Constructor;

public class PageFactory {

    public static  <T> T getInstance(Class<T> clazz,Object...args) {
        try{
            Constructor [] constructors = clazz.getDeclaredConstructors();
            for(Constructor c:constructors){
                if(c.getParameters().length==args.length){
                    return (T) c.newInstance(args);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
