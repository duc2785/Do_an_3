import java.io.*;
import java.util.*;

public class read_csv {

    public static List<Double> write_array(File filename) {
        List<Double> array_train = new ArrayList<>();
        try {
            // File loop = new File("/home/duc/Java_Project/Do_an_3/src/loop_training.csv");
            Scanner sc_loop = new Scanner(filename);
            sc_loop.useDelimiter(",");

            while (sc_loop.hasNext()) {
                String index = sc_loop.nextLine();
                Double index_double = Double.valueOf(index);
                double index_value = index_double.doubleValue();
                array_train.add(index_value);
            }
            sc_loop.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return array_train;
    }

    public static void devide_threshold(File name_file) {

        // HashMap
        HashMap<String, List<Double>> scores = new HashMap<String, List<Double>>();

        try {
            // File loop = new File("/home/duc/Java_Project/Do_an_3/src/loop_training.csv");
            Scanner sc_loop = new Scanner(name_file);
            sc_loop.useDelimiter(",");

            while (sc_loop.hasNext()) {
                String index = sc_loop.nextLine();
                Double index_double = Double.valueOf(index);
                double index_value = index_double.doubleValue();
                double key;

                key = (index_value * 20);
                // System.out.println(key);
                int key_int = Double.valueOf(key).intValue();
                String key_name = String.format("%d", key_int);

                // Lưu scores vào các khoảng
                if (scores.containsKey(key_name)) {
                    // Key đã tồn tại
                    List<Double> exist_key = scores.get(key_name);
                    exist_key.add(index_value);
                } else {
                    // Key chưa tồn tại
                    List<Double> newList = new ArrayList<>();
                    newList.add(index_value);
                    scores.put(key_name, newList);
                }

            }

            for (HashMap.Entry<String, List<Double>> entry : scores.entrySet()) {
                System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
            }

            sc_loop.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        File loop_training = new File("/home/duc/Java_Project/Do_an_3/src/loop_training.csv");
        devide_threshold(loop_training);        
        List<Double> array = write_array(loop_training);
        System.out.println(array);
    }

}
