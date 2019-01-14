package de.adesso.cccinw4.support;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Setzt globale Key-Value-Paare für Logzio
 */
public class LogProperties {

    /**
     * Setzt Key-Value-Paare für Logzio.
     * Die Werte werden in das System-Environment geschrieben
     * @param moduleName Der Name der loggenden Spring-Applikation
     * @param props Map mit Key-Value-Paaren
     * @throws NoSuchFieldException Irgendwas geht schief
     * @throws IllegalAccessException  Irgendwas geht schief
     */
    public static void setLogProperties(String moduleName, Map<String, String> props) throws NoSuchFieldException,
            IllegalAccessException {
        Map<String, String> env = new HashMap<>();
        env.put("applicationName", "logdemo");
        env.put("moduleName", moduleName);
        if (props != null) {
            env.putAll(props);
        }
        setEnv(env);
    }

    /**
     * Ein Hack der Werte in das Environment schreibt. Eigentlich ist das Environment Read-Only
     * @param newenv Map mit den Paaren, die ins Environment geschrieben werden sollen.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private static void setEnv(Map<String, String> newenv) throws NoSuchFieldException, IllegalAccessException {
        try {
            Class<?> processEnvironmentClass = Class.forName("java.lang.ProcessEnvironment");
            Field theEnvironmentField = processEnvironmentClass.getDeclaredField("theEnvironment");
            theEnvironmentField.setAccessible(true);
            Map<String, String> env = (Map<String, String>) theEnvironmentField.get(null);
            env.putAll(newenv);
            Field theCaseInsensitiveEnvironmentField = processEnvironmentClass.getDeclaredField("theCaseInsensitiveEnvironment");
            theCaseInsensitiveEnvironmentField.setAccessible(true);
            Map<String, String> cienv = (Map<String, String>)     theCaseInsensitiveEnvironmentField.get(null);
            cienv.putAll(newenv);
        } catch (NoSuchFieldException | ClassNotFoundException e) {
            Class[] classes = Collections.class.getDeclaredClasses();
            Map<String, String> env = System.getenv();
            for(Class cl : classes) {
                if("java.util.Collections$UnmodifiableMap".equals(cl.getName())) {
                    Field field = cl.getDeclaredField("m");
                    field.setAccessible(true);
                    Object obj = field.get(env);
                    Map<String, String> map = (Map<String, String>) obj;
                    map.clear();
                    map.putAll(newenv);
                }
            }
        }
    }
}

