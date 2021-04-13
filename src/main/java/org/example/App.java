package org.example;
import java.io.*;
import java.util.*;

public class App {

    private static int count = 0;
    private static int counter = 0;
    private static double compare = 0;

    public static double checkIndex(List<Double> data, int indexBegin, int indexEnd){
         if(indexBegin < 0 || indexEnd >= data.size()){
             System.out.println("Please enter a valid index");
             System.out.println("++++++++++++++++");
             return -1;
         }

        return 0;
    }

    public static double Compare(int winLength, int i){

                if(count < 1){
                    counter = i;
                }
                count++;
                if(count >= winLength){
                    System.out.println("The first index where data has meet criteria:  " + counter);
                    System.out.println("++++++++++++++++");

                    return counter;
                }

                return -1;
    }

    public static double searchContinuityAboveValue(List<Double> data, int indexBegin, int indexEnd,  double threshold, int winLength) {

        if(checkIndex(data,indexBegin, indexEnd) < 0){
            return -1;
        }

        count = 0;

        for(int i = indexBegin; i <= indexEnd; i++){

            if(data.get(i) > threshold){
               compare =   Compare(winLength, i);

               if(compare > -1){
                   return compare;
               }
            }
            else {
                count = 0;
            }
        }
        return -1;
    }

    public static double backSearchContinuityWithinRange(List<Double> data, int indexBegin, int indexEnd,  double thresholdLo, double thresholdHi, int winLength){

        count = 0;

        if(checkIndex(data,indexBegin, indexEnd) < 0){
            return -1;
        }

        for(int i = indexBegin+1; i-- >=indexEnd;){

                if(data.get(i) > thresholdLo && data.get(i) < thresholdHi){
                    compare =   Compare(winLength, i);

                    if(compare > -1){
                        return compare;
                    }
                }
                else {
                    count = 0;
                }
            }
        return -1;
    }

    public static double searchContinuityAboveValueTwoSignals(List<Double> data1, List<Double> data2, int indexBegin,
                                                       int indexEnd, double threshold1, double threshold2, int winLength){
       count = 0;

       //Assuming that data1 and data2 are of the same length
        if(checkIndex(data1,indexBegin, indexEnd) < 0){
            return -1;
        }

        for(int i = indexBegin; i <= indexEnd; i++){

            if(data1.get(i) > threshold1 && data2.get(i) > threshold2){
                    compare =   Compare(winLength, i);

                    if(compare > -1){
                        return compare;
                    }
                }
                else {
                    count = 0;
                }
            }
        return -1;
    }


    public static List<Integer> searchMultiContinuityWithinRange(List<Double> data, int indexBegin, int indexEnd,
                                     double thresholdLo, double thresholdHi, int winLength){
            int index = 0;
            count = 0;
            ArrayList<Integer> list = new ArrayList<>();

            if(checkIndex(data,indexBegin, indexEnd) < 0){
                    return null;
                }

             for(int i = indexBegin; i <= indexEnd; i++){
                if(data.get(i) > thresholdLo && data.get(i) < thresholdHi){

                    compare =   Compare(winLength, i);
                    if(compare > -1){

                        count = 0;
                        if(index<1){
                            list.add(counter);
                        }
                        index = counter + winLength-1;
                    }
                }
                else {
                    count = 0;
                }
        }

        if(list.size()>0){
           list.add(index);
            System.out.println("The starting and ending points of the entire continuity are : " + list);
            return list;
        }
        return null;
    }

    public static void main(String[] args) throws IOException {

        String filename = "latestSwing.csv";
        ArrayList<Double> test = new ArrayList<>();


        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        String[] cols;

        ArrayList<Double> timestamp = new ArrayList<>();
        ArrayList<Double> ax = new ArrayList<>();
        ArrayList<Double> ay = new ArrayList<>();
        ArrayList<Double> az = new ArrayList<>();
        ArrayList<Double> wx = new ArrayList<>();
        ArrayList<Double> wy = new ArrayList<>();
        ArrayList<Double> wz = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            cols = line.split(",");
            timestamp.add(Double.parseDouble(cols[0]));
            ax.add(Double.parseDouble(cols[1]));
            ay.add(Double.parseDouble(cols[2]));
            az.add(Double.parseDouble(cols[3]));
            wx.add(Double.parseDouble(cols[4]));
            wy.add(Double.parseDouble(cols[5]));
            wz.add(Double.parseDouble(cols[6]));
        }

         double j;

        for (double i = 0; i <= 50; i++) {

            test.add(i);
        }

        searchContinuityAboveValue(test, 1, 55, 6, 4);
       backSearchContinuityWithinRange(test, 10, 5,  1, 20, 3);
       searchContinuityAboveValueTwoSignals(test, test, 25, 40, 19, 19, 5);
       searchMultiContinuityWithinRange(timestamp, 0, 100, 1249, 98646,20 );

        }

    }


//Reading Real File Data

//        System.out.println(ax);