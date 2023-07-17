import java.util.Arrays;

public class median_filter {

   public static int median(int[] values) {
        int[] sortedValues = Arrays.copyOf(values, values.length);
        Arrays.sort(sortedValues);
        
        if (sortedValues.length % 2 == 0) {
            // Nếu mảng có số phần tử là chẵn
            int midIndex1 = sortedValues.length / 2 - 1;
            int midIndex2 = sortedValues.length / 2;
            return (sortedValues[midIndex1] + sortedValues[midIndex2]) / 2;
        } else {
            // Nếu mảng có số phần tử là lẻ
            int midIndex = sortedValues.length / 2;
            return sortedValues[midIndex];
        }
    }

    public static void main(String[] args) {
        int[] input = {5, 2, 8, 3, 9, 1, 7, 6, 4};
        int output = median(input);
        System.out.println("Median: " + output);
    }
}