package lab31;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Read {
  static final File fileFullStrong = new File("strong.txt");
  static final File fileFullAverage = new File("average.txt");
  static final File fileFullWeak = new File("weak.txt");

  static int clusterBelonging = -1;
  static final int clustresNum2 = 2;


  static List<Data> readData(File file) {
    List<Data> dataList = new ArrayList<>();
    int clusterBelonging = -1;
    switch (file.getName()) {
      case "strong.txt":
        clusterBelonging = 0;
        break;
      case "average.txt":
        clusterBelonging = 1;
        break;
      case "weak.txt":
        clusterBelonging = 2;
        break;
    }

    System.out.println(clusterBelonging);
//    switch (file.getName()) {
//      case "strong.txt":
//        clusterBelonging = 0;
//        break;
//      case "average.txt":
//        clusterBelonging = 1;
//        break;
//      case "weak.txt":
//        clusterBelonging = 2;
//        break;
//    }
    try {
      BufferedReader reader = new BufferedReader(new FileReader(file));
      int id = 1;
      String line = reader.readLine();
      while (line != null) {
        String[] lineParse = line.split(";");
        Data data = new Data(id, Integer.parseInt(lineParse[0].trim()), Integer.parseInt(lineParse[1].trim()), Integer.parseInt(lineParse[2].trim()),
                Integer.parseInt(lineParse[3].trim()), Integer.parseInt(lineParse[4].trim()), clusterBelonging);

        dataList.add(data);
        line = reader.readLine();
        id++;
      }
      reader.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
//    for (int i = 0; i < dataList.size(); i++) {
//      System.out.println(dataList.get(i).time + ";" + dataList.get(i).closeSum + ";" + dataList.get(i).groupNumber);
//    }
//    System.out.println(dataList.size());
//    new Perseptron(dataList).test();
//    System.out.println("a = " + a + "; b= " + b);
    return dataList;
  }


  static List<Data> randomMashUp(List<Data> dataList1, List<Data> dataList2) {
    dataList1.addAll(dataList2);
    Collections.shuffle(dataList1);
    return dataList1;
  }

  static List<Data> randomMashUp(List<Data> dataList1, List<Data> dataList2, List<Data> dataList3) {
    dataList1.addAll(dataList2);
    dataList1.addAll(dataList3);
    Collections.shuffle(dataList1);
    return dataList1;
  }

  public static void main(String[] args) {
    List<Data> dataListStrong = readData(fileFullStrong);
    List<Data> dataListWeak = readData(fileFullWeak);
    List<Data> dataListAverage = readData(fileFullAverage);

//    for (int i = 0; i < dataListAverage.size(); i++) {
////      dataListAverage.get(i).setClusterBelonging(1);
//      System.out.println(dataListAverage.get(i).getClusterBelonging());
//      System.out.println(dataListAverage.get(i));
//    }
//
//    for (int i = 0; i < dataListStrong.size(); i++) {
////      dataListAverage.get(i).setClusterBelonging(1);
//      System.out.println(dataListStrong.get(i).getClusterBelonging());
//      System.out.println(dataListStrong.get(i));
//    }

    List<Data> mashUpList;

    Scanner scanner = new Scanner(System.in);
    int clusterNumber;
    boolean flag = false;
    do {
      System.out.print("На сколько кластеров делить?(2 или 3): ");
      clusterNumber = scanner.nextInt();
      if (clusterNumber == 2 || clusterNumber == 3) flag = true;
    } while (!flag);

    if (clusterNumber == 2)
      mashUpList = randomMashUp(dataListStrong, dataListAverage);
    else
      mashUpList = randomMashUp(dataListStrong, dataListAverage, dataListWeak);

//    for (int i = 0; i < mashUpList.size(); i++) {
////      dataListAverage.get(i).setClusterBelonging(1);
//      System.out.println(mashUpList.get(i).getClusterBelonging());
//      System.out.println(mashUpList.get(i));
//    }

    Data centroid[][] = new Data[2][clusterNumber];
    Data seeCentroid[][] = new Data[2][clusterNumber];
    for (int i = 0; i < clusterNumber; i++) {
      centroid[1][i] = KMeans.returnRandomPointFromList(mashUpList);
      seeCentroid[1][i] = centroid[1][i];
      System.out.println(i + " рандомная точка центроид: " + centroid[1][i]);
      centroid[0][i] = new Data(0, 0, 0, 0, 0);
    }
    KMeans.calculateClustersBelonging(clusterNumber, mashUpList, centroid);
    System.out.println(mashUpList.size());
    for (int i = 0; i < clusterNumber; i++) {
      System.out.println(i + " рандомная точка центроид: " + seeCentroid[1][i]);
    }
//    for (int i = 0; i < mashUpList.size(); i++) {
//      System.out.println(mashUpList.get(i));
//
//    }

 // List<Data> normalizedList =  Kohonen.getNormalized(mashUpList);
//    for (int i = 0; i < normalizedList.size(); i++) {
//      System.out.println(normalizedList.get(i));
//
//    }
   // Kohonen.getKohonenMap(normalizedList,clusterNumber);



  }
}
