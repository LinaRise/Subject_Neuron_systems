package lab3;

import lab1.Perseptron;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Read {
  static final File fileFull = new File("social2.txt");
  static final int clustresNum3 = 3;
  static final int clustresNum2 = 2;


  static List<Data> readData(File file) {
    List<Data> dataList = new ArrayList<>();
    try {
      BufferedReader reader = new BufferedReader(new FileReader(fileFull));
      int id = 1;
      String line = reader.readLine();
      while (line != null) {
        String[] lineParse = line.split(";");
        Data data = new Data(id, Integer.parseInt(lineParse[0].trim()), Integer.parseInt(lineParse[1].trim()), Integer.parseInt(lineParse[2].trim()),
                Integer.parseInt(lineParse[3].trim()), Integer.parseInt(lineParse[4].trim()));

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

  public static void main(String[] args) {
    List<Data> dataList = readData(fileFull);
    System.out.print("На сколько кластеров делить?(2 или 3): ");
    Scanner scanner = new Scanner(System.in);
   int clusterNum = scanner.nextInt();

    Data centroid[][] = new Data[2][clusterNum];
    for (int i = 0; i <clusterNum; i++) {
      centroid[1][i] = KMeans.returnRandomPointFromList(dataList);
      System.out.println(i+ " рандомная точка центроид: "+centroid[1][i]);
      centroid[0][i] =new Data(0,0,0,0,0);
    }

   KAver.getCentroid(dataList,  centroid,clusterNum);
  }
}
