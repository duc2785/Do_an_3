import java.util.Arrays;

public class median_filter {

    public static double[] applyMedianFilter(double[] signal, int windowSize) {
        double[] filteredSignal = new double[signal.length];

        // Duyệt qua từng mẫu trong tín hiệu
        for (int i = 0; i < signal.length; i++) {
            // Lấy chỉ số của các mẫu trong cửa sổ
            int start = Math.max(0, i - windowSize / 2);
            int end = Math.min(signal.length - 1, i + windowSize / 2);

            // Sao chép các mẫu trong cửa sổ vào mảng tạm thời
            double[] window = new double[end - start + 1];
            System.arraycopy(signal, start, window, 0, window.length);

            // Sắp xếp mảng tạm thời để tìm giá trị trung vị
            Arrays.sort(window);

            // Lấy giá trị trung vị và gán vào tín hiệu đã lọc
            filteredSignal[i] = window[window.length / 2];
        }

        return filteredSignal;
    }
    public static void main(String[] args) {
        double[] abc= {1, 0, 0, 0.5, 1};
        double[] a = applyMedianFilter(abc, 2);

        System.out.println(a[3]);
    }
}