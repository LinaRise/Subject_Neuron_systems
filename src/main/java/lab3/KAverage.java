package lab3;

public class KAverage {
  public static void main(String[] args) {
    int arr[] = {2, 4, 10, 12, 3, 20, 30, 11, 25};
    int i = 0, m1, m2, n = 0;
    boolean flag; //дальше нет сымсла делать тк центры уже не меняются
    int sum1, sum2;//сумма массивов
    int a = arr[0]; //предыдущее состояние центра масс
    int b = arr[1];
    m1 = a; //перввая центроида 1 кластера
    m2 = b;

    int cluster1[] = new int[arr.length], cluster2[] = new int[arr.length];
    do {
      sum1 = 0;
      sum2 = 0;
      cluster1 = new int[arr.length];
      cluster2 = new int[arr.length];
      n++;
      int k = 0, j = 0;
      for (i = 0; i < arr.length; i++) {
        if (Math.abs(arr[i] - m1) <= Math.abs(arr[i] - m2)) {
          cluster1[k] = arr[i];
          k++;
        } else {
          cluster2[j] = arr[i];
          j++;
        }
      }

      for (i = 0; i < k; i++) {
        sum1 += cluster1[i];
      }

      for (i = 0; i < j; i++) {
        sum2 += cluster1[i];
      }
      a = m1;
      b = m2;
      m1 = sum1 / k;
      m2 = sum2 / j;

      flag = (m1 == a && m2 == b);
      System.out.println();
      System.out.println("---------------------------------");
      System.out.println("m1=" + m1 + "   m2=" + m2);
      System.out.println("после итерации " + n + "cluster 1\n");
      for (i = 0; i < cluster1.length; i++) {
        System.out.print(cluster1[i] + "\t");
      }
      System.out.println("\n");
      System.out.println("после итерации " + n + "cluster 2\n");
      for (i = 0; i < cluster2.length; i++) {
        System.out.print(cluster2[i] + "\t");
      }


    } while (!flag);

    System.out.println();
    System.out.println("----------------------------------------");
    System.out.println("Конечное значение центроидлов m1="+m1+"  m2="+m2);
    System.out.println();
    System.out.println("Конечный кластер 1\n");
    for (i = 0; i < cluster1.length; i++) {
      System.out.print(cluster1[i] + "\t");
    }
    System.out.println();
    System.out.println("Конечный кластер 2\n");
    for (i = 0; i < cluster2.length; i++) {
      System.out.print(cluster2[i] + "\t");
    }



  }
}
