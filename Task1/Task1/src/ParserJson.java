import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ParserJson {


    public static void showAllInfo(String fileName, List<String> collumns){
        List<Map<String, Object>> res = parse(fileName, collumns);

        for (Map<String, Object> map:res) {
            for (String c:map.keySet()) {
                System.out.println(c+" = " +map.get(c));
            }
            System.out.println("_____________________________________");
        }
        System.out.println();
    }
    public static List<Map<String, Object>> parse(String fileName, List<String> collumns){
        List<Map<String, Object>> res = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        try(FileReader reader = new FileReader(fileName)){
            Map<String, Object> map = new TreeMap<>();
            JSONArray rootJsonObject = (JSONArray) jsonParser.parse(reader);

            for (Object o:rootJsonObject) {
                JSONObject jo = (JSONObject) o;

                for (String c:collumns) {
                    map.put(c, jo.get(c));
                }
                res.add(map);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return res;
    }
    public static List<Object> parse(String fileName, Object typePeferense){
        Class[] types = getTypesForCast(typePeferense);
        String[] fieldNames = getFieldNames(typePeferense);

        List<Object> res = new ArrayList<>();

        JSONParser jsonParser = new JSONParser();

        try(FileReader reader = new FileReader(fileName)){
            //reader.
            JSONArray jsonArr = (JSONArray) jsonParser.parse(reader);
            //System.out.println(jsonArr.getClass().equals(JSONArray.class));
            for (Object o: jsonArr) {
                JSONObject jsonObg = (JSONObject) o;
                Object[] values = new Object[fieldNames.length];
                for (int i = 0; i < values.length; i++) {
                    values[i] = jsonObg.get(fieldNames[i]);
                }

                Object instance = Class.forName(typePeferense.getClass().getName()).getConstructor(types).newInstance(values);
                res.add(instance);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        return res;
    }

    private static Class[] getTypesForCast(Object o){
        Field[] fields = o.getClass().getDeclaredFields();

        Class[] types = new Class[fields.length];

        for (int i = 0; i < types.length; i++) {
            types[i] = fields[i].getType();
        }
        return types;
    }

    private static String[] getFieldNames(Object o){
        Field[] fields = o.getClass().getDeclaredFields();

        String[] types = new String[fields.length];

        for (int i = 0; i < types.length; i++) {
            types[i] = fields[i].getName();
        }
        return types;
    }

}
