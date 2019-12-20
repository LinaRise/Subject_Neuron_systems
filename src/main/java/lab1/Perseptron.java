package lab1;

import java.util.List;

public class Perseptron {

  double outer; //выходной нейрон
  double[] w;
  List<Data> data;
  double[] x;
  int round;
  double out;

  public Perseptron(List<Data> data) {
    this.data = data;
    System.out.println(data.size());
    round = (int) (data.size() * 0.8);  //берем дл яобучения 80% значений
    w = new double[2];
    w[0] = 0.1;
    w[1] = 0.1;
  }

  static double sigmoid(double x) {
    return 1 / (1 + Math.exp(-x));
  }


  private void cy() {
    outer = 0;//обнуление перменноый после каждого выхода
    for (int i = 0; i < x.length; i++) {
      outer += x[i] * w[i];
    }
    if (sigmoid(outer)>0.5)
      outer = 1;
    else
      outer = 0;


  }




  public void study() {
    double globalError;
    int m = 0;
    for (int iter = 0; iter < 100; iter++) {
      globalError = 0;
      //начинаем подавать на вход шаблоны для обучения сети
      for (int i = 0; i < round; i++) {
        x = new double[]{data.get(i).time, data.get(i).closeSum};
        cy(); //сигнал
        double error = data.get(i).groupNumber - outer; //насколько большая ошибка
        globalError += Math.abs(error);
        //корректировка весов
        for (int j = 0; j < x.length; j++) {
          w[j] += 0.1 * error * x[j];//изменение шаг, ошибка, вход сигнал( на случай если он было 0)
        }
        System.out.println("номер эры = " + m + ", ошибки = " + globalError / data.size() + "%");
        m++;

      }
      System.out.println("всего эры = " + m);
    }
  }

  void test() {
    study();
    double percent = 0;
    for (int i = round; i < data.size(); i++) {
      x = new double[]{data.get(i).time, data.get(i).closeSum};
      System.out.print(x[1] + "\t" + x[0] + ";" + data.get(i).groupNumber + "\t\t");
      cy();
      System.out.println("отгадывает, что первое больше, чем второе: " + outer);
      double er = data.get(i).groupNumber - outer;
      if (er != 0)
        percent += 1;
    }
    System.out.println("процент ошибок = " + percent / (data.size() - round)*100 + "%");
    System.out.println("весы : " + w[0] + ", " + w[1]);

  }

}