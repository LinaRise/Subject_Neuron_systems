package lab1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

  static final File fileFull = new File("data2.txt");
  //коэф линии тренда
  static double a, b;

  public static void main(String[] args) {


    //считываем файл в список
    List<Data> dataList = new ArrayList<>();
    try {
      BufferedReader reader = new BufferedReader(new FileReader(fileFull));
      int time = 1;
      String line = reader.readLine();
      while (line != null) {

        dataList.add(new Data(time, Double.parseDouble(line)));
        line = reader.readLine();
        time++;
      }
      getTrendData(dataList);
      reader.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
    for (int i = 0; i < dataList.size(); i++) {
      System.out.println(dataList.get(i).time + ";" + dataList.get(i).closeSum + ";" + dataList.get(i).groupNumber);
    }
//    System.out.println(dataList.size());
    new Perseptron(dataList).test();
//    System.out.println("a = " + a + "; b= " + b);
  }

  public static void getTrendData(List<Data> data) {

    //переменные
    double x = 0, y = 0, xy = 0, x2 = 0;

    //считаем
    for (Data aData : data) {
      x += aData.time;
      y += aData.closeSum;
      x2 += Math.pow(aData.time, 2);
      xy += aData.time * aData.closeSum;
    }
    x /= data.size();
    y /= data.size();
    x2 /= data.size();
    xy /= data.size();
    b = (xy - x * y) / (x2 - Math.pow(x, 2));
    a = y - b * x;
    System.out.println("a = " + a + "; b= " + b);


    for (int i = 0; i < data.size(); i++) {
      data.get(i).trendCloseSum = a + b * data.get(i).time;
//      System.out.println( "!!!"+data.get(i).trendCloseSum);
      //смотрим к какой группе относится
      data.get(i).groupNumber = data.get(i).closeSum < data.get(i).trendCloseSum ? 0 : 1;
//      System.out.println("!!!" + data.get(i).isTrendCloseSumBigger);
    }
  }

}