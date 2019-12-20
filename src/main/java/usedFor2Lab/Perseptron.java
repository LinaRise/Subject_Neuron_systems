package usedFor2Lab;

import lab2.Data;

import java.util.List;

//public class Perseptron {
//  double[] inputN;
//  final double learningRate = 0.1;
//  double y;
//  double[][] wih;//нейронные связи между первым слоем и скрытым
//  double[] why;//нейроны между скрытым слоем и выходным
//  double[] hiddenN;
//  double[][] pat = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
//  double[] r = {0, 1, 1, 0};
//  List<Data> data;
//  int round;
//  int outer;
//
//  public Perseptron(List<Data> data) {
//    this.data = data;
//    round = (int) (data.size() * 0.8);
//    System.out.println(data.size());
//    inputN = new double[2];
//    hiddenN = new double[2];
//    wih = new double[inputN.length][hiddenN.length];
//    why = new double[hiddenN.length];
//
//
//    init();
//    study();
////    System.out.println(pat.length+"'!!!!!");
////    System.out.println(pat[0].length+"'!!!!!");
//
//  }
//
//  void test(){
//    double percent = 0;
//    for (int i = round; i < data.size()-1; i++) {
//      double minT, maxT = 0, minS, maxS = 0;
//
//      for (int j = 0; j < data.size(); j++) {
//        if (data.get(j).time >= maxT) {
//          maxT = data.get(j).time;
//        }
//        if (data.get(j).closeSum >= maxS) {
//          maxS = data.get(j).closeSum;
//        }
//      }
//      minS = maxS;
//      minT = maxT;
//      for (int j = 0; j < data.size(); j++) {
//        if (data.get(j).time <= minT) {
//          minT = data.get(j).time;
//        }
//        if (data.get(j).closeSum <= minS) {
//          minS = data.get(j).closeSum;
//        }
//      }
//
//      inputN[0] = (data.get(i).time - minT) / (maxT - minT);
//      inputN[1] = (data.get(i).closeSum - minS) / (maxS - minS);
//      cy();
//      if (y > 0.5)
//        y = 1;
//      else y = 0;
////      System.out.println("y=" + y);
//      System.out.print(inputN[1] + "\t" + inputN[0] + ";" + data.get(i).groupNumber + "\t\t");
//      System.out.println("отгадывает, что первое больше, чем второе: " + y);
//      double er = data.get(i).groupNumber - y;
//      if (er != 0)
//        percent += 1;
//    }
//    System.out.println("процент ошибок = " + percent / (data.size() - round) * 100 + "%");
//  }
//
//
//
//
//
//  static double sigmoid(double x) {
//    return 1 / (1 + Math.exp(-x));
//  }
//
//  static double revSigmoid(double x) {
//    return (1 / (1 + Math.exp(-x))) * (1 - (1 / (1 + Math.exp(-x))));
//  }
//
//
//  public void init() {
//    System.out.println("Начальные значения весов первого слоя");
//    for (int i = 0; i < wih.length; i++) {
//      for (int j = 0; j < wih[i].length; j++) {
//        wih[i][j] = Math.random() * 0.3 + 0.1;
//        System.out.println("wxh[" + i + "][" + j + "]=" + wih[i][j]);
//      }
//    }
//    System.out.println("Начальные значения весов второго слоя");
//    for (int i = 0; i < why.length; i++) {
//      why[i] = Math.random() * 0.3 + 0.1;
//      System.out.println("why[" + i + "]=" + why[i]);
//    }
//  }
//
//  public void cy() {
//    //высчитываем значения в скрытых узлах
//    for (int i = 0; i < hiddenN.length; i++) {
//      hiddenN[i] = 0;
//      for (int j = 0; j < inputN.length; j++) {
//        hiddenN[i] += inputN[j] * wih[j][i];
//      }
//      hiddenN[i] = sigmoid(hiddenN[i]);
////      if (sigmoid(hiddenN[i]) > 0.5)
////        hiddenN[i] = 1;
////      else
////        hiddenN[i] = 0;
//    }
////высчтиываем выходнон значение
//    y = 0;
//    outer = 0;
//    for (int i = 0; i < hiddenN.length; i++) {
//      //сумма (результатов скрытого слоя на весовой коэффициент)
//      y += hiddenN[i] * why[i];
//    }
//    y = sigmoid(y);
//    if (sigmoid(y) > 0.5)
//      outer = 1;
//    else
//      outer = 0;
//
//  }
//
//  public void study() {
//    //массив ошибок
//    double[] err = new double[hiddenN.length];
//    double gEr;
//    double m = 0;
//    double minT, maxT = 0, minS, maxS = 0;
//    //опред min для нормализации данных
//    for (int j = 0; j < round; j++) {
//      if (data.get(j).time >= maxT) {
//        maxT = data.get(j).time;
//      }
//      if (data.get(j).closeSum >= maxS) {
//        maxS = data.get(j).closeSum;
//      }
//    }
//    minS = maxS;
//    minT = maxT;
//    //опред min  для нормализации данных
//    for (int j = 0; j < round; j++) {
//      if (data.get(j).time <= minT) {
//        minT = data.get(j).time;
//      }
//      if (data.get(j).closeSum <= minS) {
//        minS = data.get(j).closeSum;
//      }
//    }
//
//    for (int iter = 0; iter < 100; iter++) {
//      gEr = 0;
//      //цикл перебора данных
//      for (int p = 0; p < round; p++) {
//        //нормализация данных
//        inputN[0] = (data.get(p).time - minT) / (maxT - minT);
//        inputN[1] = (data.get(p).closeSum - minS) / (maxS - minS);
////        inputN[0] = data.get(p).time;
////        inputN[1] = data.get(p).closeSum;
//        cy();
//        //локальная ошибка = ожидаемое - полученное
//        double lEr = data.get(p).groupNumber - y;
//        gEr += Math.abs(lEr);
//        //метод обратного распространения ошибки
//        //здесь берется ошибка локальная которая была на выходее вообще
//        for (int i = 0; i < hiddenN.length; i++)
//          //локальная ошибка * на вес от скрытого слоя к входному нейрону
//          err[i] = lEr * why[i];
//        //подсчет корректировки весов на первом слое
//        for (int i = 0; i < inputN.length; i++) {
//          for (int j = 0; j < hiddenN.length; j++) {
//            //сумма(скорость обучения * ошибка с предыдущего слоя(err[i]) * входы примера * производную от текущего)
//            wih[i][j] += learningRate * err[j] * revSigmoid(hiddenN[i]) * inputN[i];
//          }
//        }
//        for (int i = 0; i < hiddenN.length; i++)
//          why[i] += learningRate * lEr * hiddenN[i] * revSigmoid(hiddenN[i]);
//
//      }
//      m++;
//    }
//
//    System.out.println("Количество итераций равно " + m);
//  }
//
//
////  void test() {
////    study();
////    double percent = 0;
////    for (int i = round; i < data.size(); i++) {
////      inputN = new double[]{data.get(i).time, data.get(i).closeSum};
////      System.out.print(inputN[1] + "\t" + inputN[0] + ";" + data.get(i).groupNumber + "\t\t");
////      cy();
////      System.out.println("отгадывает, что первое больше, чем второе: " + outer);
////      double er = data.get(i).groupNumber - outer;
////      if (er != 0)
////        percent += 1;
////    }
////    System.out.println("процент ошибок = " + percent / (data.size() - round) * 100 + "%");
////
////  }
//
//}
//
