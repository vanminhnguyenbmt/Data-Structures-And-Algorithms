package com.nguyenvm.array.minimum_amount_of_time_to_collect_garbage;

public class TimeToCollectGarbage {
    public int garbageCollection(String[] garbage, int[] travel) {
        int[] tmpTrucks = new int[3];
        int[] trucks = new int[3];
        int M = 0, P = 1, G = 2;

        for (int i = 0; i < garbage.length; i++) {
            if (i != 0) {
                tmpTrucks[M] += travel[i - 1];
                tmpTrucks[P] += travel[i - 1];
                tmpTrucks[G] += travel[i - 1];
            }

            char[] garbageTypes = garbage[i].toCharArray();
            for (int j = 0; j < garbageTypes.length; j++) {
                switch (garbageTypes[j]) {
                    case 'M':
                        trucks[M] += tmpTrucks[M] + 1;
                        tmpTrucks[M] = 0;
                        break;
                    case 'P':
                        trucks[P] += tmpTrucks[P] + 1;
                        tmpTrucks[P] = 0;
                        break;
                    case 'G':
                        trucks[G] += tmpTrucks[G] + 1;
                        tmpTrucks[G] = 0;
                        break;
                }
            }
        }

        int time = 0;
        for (int i = 0; i < trucks.length; i++) {
            time += trucks[i];
        }
        return time;
    }

    public static void main(String[] args) {
        String[] garbage = {"G", "P", "GP", "GG"};
        int[] travel = {2, 4, 3};
        System.out.println(new TimeToCollectGarbage().garbageCollection(garbage, travel));
    }
}
