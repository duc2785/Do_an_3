import java.io.*;
import java.util.*;

public class predict {
    public static void main(String[] args) {
        File train = new File("/home/duc/Java_Project/Do_an_3/src/loop_training.csv");
        File w5_thus = new File("/home/duc/Java_Project/Do_an_3/src/LOOP_w5_thur_attack.csv");
        int[][] matrix_train = transition_probability_matrix.create_matrix(train);
        List<Double> array_loop = read_csv.write_array(w5_thus);
        double pre_index = array_loop.get(0);

        for(int i = 1; i < array_loop.size(); i++){
            // int pre_key = transition_probability_matrix.get_key(pre_index);
            // int now_key = transition_probability_matrix.get_key(array_loop.get(i));
            double e = Math.abs(array_loop.get(i) - pre_index);
            System.out.println(e);
        }

    }

}
