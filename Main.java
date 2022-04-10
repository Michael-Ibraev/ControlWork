package com.company;

/*
    (+) Разработать класс «Многоугольник», который хранится в виде массива его вершин.

    (+-) Определить конструктор(+), методы для организации ввода-вывода(+) и переопределить
       операции сравнения многоугольников по площади.

    (+) Написать методы вычисления площади многоугольника(+), определения,
       принадлежит ли точка многоугольнику(+), определения, является ли многоугольник выпуклым(+).
*/

import java.util.ArrayList;
import java.util.Arrays;
import static java.lang.Math.abs;

class Point{                /*Класс "Точка", объект которого хранит две численные переменные x и y.*/
    public double x;
    public double y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }
    public double getX(){     /*Метод возвращающий x*/
        return x;
    }
    public double getY(){      /*Метод возвращающий y*/
        return y;
    }
}

class Polygon {                 /* Класс "Многоугольник", который хранится в виде массива его вершин.*/
    private Point[] polygonPoints;  /*Массив вершин многоугольник.*/

    public Polygon(ArrayList<Point> points) {
        setPoints(points);
    }

    public void setPoints(ArrayList<Point> points) {
        if (points.size() < 3) {
            return;
        }
        polygonPoints = points.toArray(new Point[0]);
    }


    public double getArea() {           /*Метод нахождения площади многоугольника по формуле Гаусса.*/
        double s = 0;
        int n = polygonPoints.length;
        for (int i = 0; i < polygonPoints.length; i++) {
            Point a = polygonPoints[i];
            Point b = polygonPoints[(i + 1) % n];
            s += a.getX() * b.getY() - b.getX() * a.getY();
        }
        return 0.5 * abs(s);
    }


    public boolean Interpoint(Point a) {        /*Метод, определяющий входит ли точка в многоугольник.*/
        boolean result = false;
        int j = polygonPoints.length - 1;
        for (int i = 0; i < polygonPoints.length; i++) {
            if ((polygonPoints[i].getY() < a.getY() && polygonPoints[j].getY() >= a.getY() || polygonPoints[j].getY() < a.getY() && polygonPoints[i].getY() >= a.getY()) &&
                    (polygonPoints[i].getX() + (a.getY() - polygonPoints[i].getY()) /
                            (polygonPoints[j].getY() - polygonPoints[i].getY()) * (polygonPoints[j].getX() - polygonPoints[i].getX()) < a.getX())) {
                result = !result;
            }
        }
        return result;
    }

    public boolean isConvex() {             /*Метод определения выпуклости многоугольника.*/
        if (polygonPoints.length < 4) {
            return true;
        }
        boolean sign = false;
        int n = polygonPoints.length;

        for(int i = 0; i < n; i++)
        {
            double dx1 = polygonPoints[(i + 2) % n].x - polygonPoints[(i + 1) % n].x;
            double dy1 = polygonPoints[(i + 2) % n].y - polygonPoints[(i + 1) % n].y;
            double dx2 = polygonPoints[i].x - polygonPoints[(i + 1) % n].x;
            double dy2 = polygonPoints[i].y - polygonPoints[(i + 1) % n].y;
            double zcrossproduct = dx1 * dy2 - dy1 * dx2;

            if (i == 0)
                sign = zcrossproduct > 0;
            else if (sign != (zcrossproduct > 0))
                return false;
        }

        return true;
    }

    public void getPoints(){        /*Метод вывода координат многоугольника.*/
        System.out.println("Многоугольник состоит из точек: ");
        for(int i = 0; i < polygonPoints.length; i++){
            System.out.println("x="+ polygonPoints[i].getX()+" y="+ polygonPoints[i].getY());
        }
    }
}

public class Main {

    public static void main(String[] args) {

        ArrayList<Point> newArr = new ArrayList<>(Arrays.asList(    /*Задаем координаты многоугольника.*/
                new Point[] {
                        new Point(2,3), new Point(5, 6),
                        new Point(7,2), new Point(2,1),
                        new Point(0.6,3.6)
                }
        ));
        Polygon e = new Polygon(newArr);               /*Создаем объект класса "Многоугольник" с координатами, заданными ранее.*/


        System.out.println("Площадь многоугольника: "+e.getArea());     /*Расчет площади многоугольника.*/


        Point a = new Point(5.79, 4.77);     /*Создаем объект класса "Точка" и определяем входит ли он в многоугольник.*/
        if (e.Interpoint(a) == true){
            System.out.println("Точка с координатами x="+a.getX()+" y="+ a.getY()+" входит в многоугольник");
        }else{
            System.out.println("Точка с координатами x="+a.getX()+" y="+ a.getY()+" не входит в многоугольник");
        }

        if (e.isConvex() == true){              /*Определение выпуклости многоугольника.*/
            System.out.println("Многоугольник выпуклый");
        }else{
            System.out.println("Многоугольник невыпуклый");
        }

        e.getPoints();                      /*Вывод координат многоугольника.*/
    }
}
