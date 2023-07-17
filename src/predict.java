import java.io.*;
import java.util.*;

public class predict {

    // public static double get_predict_scores(int[][] matrix){
    // double max = matrix[0][0];
    // int predict_key = 0;
    // for(int i = 0; i < matrix.length; i++){
    // for(int j = 0; j < matrix[0].length; j++){
    // if(matrix[i][j] > max){
    // max = matrix[i][j];
    // predict_key = j;
    // }
    // }
    // }
    // return predict_key;
    // }

    public static double calculate_sigma(float[][] matrix, int key) {
        // int key_r = transition_probability_matrix.get_key(r);
        double sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            sum += matrix[key][i] * key;

        }
        double average = sum / matrix.length;
        double VX = 0;
        for (int i = 0; i < matrix.length; i++) {
            VX += matrix[key][i] * key * key - average * average;

        }
        double sigma = Math.sqrt(VX);
        return sigma;
    }

    public static int predict_score(double[] arr_score, int num_pred, float[][] matrix) {
        // File train = new
        // File("/home/duc/Java_Project/Do_an_3/src/loop_training.csv");
        // File w5_thus = new
        // File("/home/duc/Java_Project/Do_an_3/src/LOOP_w5_thur_attack.csv");
        // int[][] matrix_train = transition_probability_matrix.create_matrix(train);
        // List<Double> array_loop = read_csv.write_array(w5_thus);
        // double pre_score = get_predict_scores(matrix_train);
        double pre_number = arr_score[0];
        double now_number = arr_score[1];

        int key1 = transition_probability_matrix.get_key(pre_number);
        int key2 = transition_probability_matrix.get_key(now_number);

        int[] arr = {key1, key2};

        int key_median = median_filter.median(arr);

        int key = transition_probability_matrix.get_key_2(pre_number, now_number);
        double sigma = calculate_sigma(matrix, key);
        double e = Math.abs(key_median - num_pred);
        double b = e / sigma;
        double temp = b * matrix[key][key];
        matrix[key][key] = (int) temp;

        double sum_row = 0;
        for (int col = 0; col < matrix.length; col++) {
            sum_row += matrix[key][col]; // Cộng giá trị vào tổng
        }

        for (int col = 0; col < matrix.length; col++) {
            matrix[key][col] = (int) (matrix[key][col] * 100 / sum_row);
        }

        float max = matrix[key][0];
        int pred = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[key][i] > max) {
                max = matrix[key][i];
                pred = i;

            }
        }

        // get pred score
        return pred % 20;

    }

    // public static int predict_score_level5(double[] arr_score, int num_pred, float[][] matrix) {
    //     // File train = new
    //     // File("/home/duc/Java_Project/Do_an_3/src/loop_training.csv");
    //     // File w5_thus = new
    //     // File("/home/duc/Java_Project/Do_an_3/src/LOOP_w5_thur_attack.csv");
    //     // int[][] matrix_train = transition_probability_matrix.create_matrix(train);
    //     // List<Double> array_loop = read_csv.write_array(w5_thus);
    //     // double pre_score = get_predict_scores(matrix_train);
    //     double number1 = arr_score[0];
    //     double number2 = arr_score[1];
    //     double number3 = arr_score[2];
    //     double number4 = arr_score[3];
    //     double number5 = arr_score[4];

    //     int key1 = transition_probability_matrix.get_key(number1);
    //     int key2 = transition_probability_matrix.get_key(number2);
    //     int key3 = transition_probability_matrix.get_key(number3);
    //     int key4 = transition_probability_matrix.get_key(number4);
    //     int key5 = transition_probability_matrix.get_key(number5);

    //     int[] arr = {key1, key2, key3, key4, key5};

    //     int key_median = median_filter.median(arr);

    //     int key = transition_probability_matrix.get_key_5(number1, number2, number3, number4, number5);
    //     double sigma = calculate_sigma(matrix, key);
    //     double e = Math.abs(key_median - num_pred);
    //     double b = e / sigma;
    //     double temp = b * matrix[key][key];
    //     matrix[key][key] = (int) temp;

    //     double sum_row = 0;
    //     for (int col = 0; col < matrix.length; col++) {
    //         sum_row += matrix[key][col]; // Cộng giá trị vào tổng
    //     }

    //     for (int col = 0; col < matrix.length; col++) {
    //         matrix[key][col] = (int) (matrix[key][col] * 100 / sum_row);
    //     }

    //     float max = matrix[key][0];
    //     int pred = 0;
    //     for (int i = 0; i < matrix.length; i++) {
    //         if (matrix[key][i] > max) {
    //             max = matrix[key][i];
    //             pred = i;

    //         }
    //     }

    //     // get pred score
    //     return pred % 20;

    //     // for(int i = 1; i < array_loop.size(); i++){
    //     // double now_score = array_loop.get(i);
    //     // int key = transition_probability_matrix.get_key_2(pre_number ,now_score);

    //     // double e = Math.abs(now_key - pre_score);
    //     // double b =

    //     // System.out.println(e);
    //     // }
    // }

    public static void main(String[] args) {
        File train = new File("/home/duc/Java_Project/Do_an_3/src/loop_training.csv");
        File test1 = new File("/home/duc/Java_Project/Do_an_3/src/LOOP_w5_thur_attack.csv");
        File test2 = new File("/home/duc/Java_Project/Do_an_3/src/LOOP_W5_Thurs_testing_normal.csv");

        float[][] matrix_train = transition_probability_matrix.create_matrix(train, 2);
        // int[][] matrix_train_lv2 =
        // transition_probability_matrix.create_matrix_b2(matrix_train_lv1);

        List<Double> array_loop = read_csv.write_array(test1);
        double pre_index = array_loop.get(0);
        int anomaly_count = 0;
        int num_pred = 1;
        for (int i = 1; i < array_loop.size(); i++) {
            double now_index = array_loop.get(i);
            int now_key = transition_probability_matrix.get_key(now_index);
            double[] arr = { pre_index, now_index };

            int pred = predict_score(arr, num_pred, matrix_train);

            if (now_key > num_pred) {
                System.out.println("Error:" + " threshold = " + pred + " now_index = " + now_index);
                System.out.println("Count = " + anomaly_count);
                anomaly_count += 1;
            }
            num_pred = pred;

            pre_index = now_index;

        }

    }

}
