package org.example;



import java.io.*;
import java.util.*;




public class App {


    public static double searchContinuityAboveValue(List<Double> data, int indexBegin, int indexEnd,  double threshold, int winLength) {
        int count = 0;
        int counter = 0;
        for(int i = indexBegin; i <= indexEnd; i++){

            try{
                if(data.get(i) > threshold){
                    if(count < 1){
                        counter = i;
                    }
                    count++;
                    if(count >= winLength){
                        System.out.println("The first index where data has meet criteria:  " + data.indexOf(data.get(counter)));
                        return data.indexOf(data.get(counter));
                    }
                }
                else {
                    count = 0;
                }
            }   catch (IndexOutOfBoundsException E){
                    System.out.println("Please enter a valid index");
                    E.printStackTrace();
                    break;
            }
        }

        return 0;
    }

    public static double backSearchContinuityWithinRange(List<Double> data, int indexBegin, int indexEnd,  double thresholdLo, double thresholdHi, int winLength){
        int count = 0;
        int counter = 0;

        for(int i = indexBegin+1; i-- >=indexEnd;){

            try{
                if(data.get(i) > thresholdLo && data.get(i) < thresholdHi){
                    if(count < 1){
                        counter = i;
                    }
                    count++;

                    if(count >= winLength){
//                        System.out.println(count);

                        System.out.println("The first index where data has meet criteria: " + data.indexOf(data.get(counter)));
                        return data.indexOf(data.get(counter));
                    }
                }
                else {
                    count = 0;
                }
            } catch (IndexOutOfBoundsException E){
                System.out.println("Please enter a valid index");
                E.printStackTrace();
                break;

            }
        }

        return 0;
    }

    public static double searchContinuityAboveValueTwoSignals(List<Double> data1, List<Double> data2, int indexBegin,
                                                       int indexEnd, double threshold1, double threshold2, int winLength){
        int count = 0;
        int counter = 0;
        for(int i = indexBegin; i <= indexEnd; i++){

            try{

                if(data1.get(i) > threshold1 && data2.get(i) > threshold2){

//                    System.out.println(data1.get(i) + " " + data2.get(i));
                    if(count < 1){
                        counter = i;
                    }
                    count++;

                    if(count >= winLength){
                        System.out.println("The first index where data has meet criteria: " + data1.indexOf(data1.get(counter)));
                        return data1.indexOf(data1.get(counter));
                    }
                }
                else {
                    count = 0;
                }
            } catch (IndexOutOfBoundsException E){
                System.out.println("Please enter a valid index");
                E.printStackTrace();
                break;

            }
        }
        return 0;
    }

    public static List<Integer> searchMultiContinuityWithinRange(List<Double> data, int indexBegin, int indexEnd,
                                     double thresholdLo, double thresholdHi, int winLength){
            int count = 0;
            int counter = 0;

            ArrayList<Integer> list = new ArrayList<>();
//            ArrayList<Integer>

        for(int i = indexBegin; i <= indexEnd; i++){
            try{
                if(data.get(i) > thresholdLo && data.get(i) < thresholdHi){
//                    System.out.println(data.get(i));
                    if(count < 1){
                        counter = i;
                    }
                    count++;

                    if(count >= winLength){
                        count = 0;
                        list.add(data.indexOf(data.get(counter)));
                        list.add((data.indexOf(data.get(counter) + winLength-1)));
                       // System.out.println(list);
                    }
                }
                else {
                    count = 0;
                }
            } catch (IndexOutOfBoundsException E){
                System.out.println("Please enter a valid index");
                E.printStackTrace();
                break;

            }
        }

        System.out.println("The first and last index are : " + list);
        return list;
    }

    public static void main(String[] args) throws IOException {

        String filename = "latestSwing.csv";

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

        ArrayList<Double> test = new ArrayList<>();


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
        System.out.println(ax);


        for (double i = 0; i <= 50; i++) {
            test.add(i);
        }

        //Please uncomment these for simple universal testing

       // searchContinuityAboveValue(test, 5, 10, 6, 4);
//        backSearchContinuityWithinRange(test, 10, 5,  1, 20, 3);
          //  searchContinuityAboveValueTwoSignals(test, test, 25, 40, 19, 19, 5);
        searchMultiContinuityWithinRange(test, 0, 49, 15, 21, 5);

    }
    }