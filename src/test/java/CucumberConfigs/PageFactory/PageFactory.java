package CucumberConfigs.PageFactory;

import java.lang.reflect.Constructor;

public class PageFactory {

    public static  <T> T getInstance(Class<T> clazz,Object...args) {
        Object pageObjectModel = null;
        try {
            Constructor[] constructors = clazz.getDeclaredConstructors();
            for (Constructor c : constructors) {
                if (c.getParameters().length == args.length) {
                    pageObjectModel = (T) c.newInstance(args);
                    return (T) c.newInstance(args);
                }
            }
            if(pageObjectModel==null)
                throw new NoPageObjectFound("No such Page Object Model Class found !!!!");
        }catch (NoPageObjectFound customException){
            System.out.println(customException.getMessage());
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
