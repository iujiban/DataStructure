import org.omg.CORBA.ExceptionList;

import java.util.*;

class minDistance {
    final static int GAP = 2;
    final static int MATCH = 0;
    final static int MISMATCH = 1;

    enum BASE { A, T, G, C }

    // recursive min distance
    static int minDistance (List<BASE> dna1, List<BASE> dna2) {
        try {
            int current = dna1.getFirst() == dna2.getFirst() ? MATCH : MISMATCH;
            int d1 = current + minDistance(dna1.getRest(), dna2.getRest());
            int d2 = GAP + minDistance(dna1.getRest(), dna2);
            int d3 = GAP + minDistance(dna1, dna2.getRest());
            return d1 < d2 ? d1 : d2;
        }
        catch (EmptyListE e) {
            if (dna1.isEmpty()) return GAP * dna2.length();
            else return GAP * dna1.length();
        }
    }

    static Map<Pair<List<BASE>,List<BASE>>,Integer> minDistanceMemo = new WeakHashMap<>();

    // memoized (top down) min distance
    static int mminDistance (List<BASE> dna11, List<BASE> dna21) {
        return minDistanceMemo.computeIfAbsent(new Pair<>(dna11, dna21), p -> {
            List<BASE> dna1 = p.getFirst();
            List<BASE> dna2 = p.getSecond();
            try {
                int current = dna1.getFirst() == dna2.getFirst() ? MATCH : MISMATCH;
                int d1 = current + mminDistance(dna1.getRest(), dna2.getRest());
                int d2 = GAP + mminDistance(dna1.getRest(), dna2);
                int d3 = GAP + mminDistance(dna1, dna2.getRest());
                return Math.min(d1,Math.min(d2,d3));
            }
            catch (EmptyListE e) {
                if (dna1.isEmpty()) return GAP * dna2.length();
                else return GAP * dna1.length();
            }
        });
    }

    // bottom up min distance
    static int buminDistance (List<BASE> dna11, List<BASE> dna21)  {
        ArrayList<BASE> dna1 = dna11.toArrayList();
        ArrayList<BASE> dna2 = dna21.toArrayList();

        Map<Pair<Integer, Integer>, Integer> hash = new HashMap<>();
        hash.put(new Pair<>(0,0),0);
        for (int i=1; i<=dna1.size();i++) {
            hash.put(new Pair<>(i,0), 2*i); // row/coulmn
        }
        for (int j=1; j<=dna2.size(); j++) {
            hash.put(new Pair<>(0,j), 2*j);
        }
        for (int i=1; i<=dna1.size(); i++) {
            for (int j=1; j<=dna2.size(); j++) {
                Pair<Integer, Integer> position = new Pair<>(i,j);
                Pair<Integer, Integer> diagonal = new Pair<>(i-1,j-1);
                Pair<Integer, Integer> up = new Pair<>(i,j-1);
                Pair<Integer,Integer> next = new Pair<>(i-1,j);

                if (dna1.get(i-1) == dna2.get(j-1)) { //DNA firstGet
                    hash.put(position, hash.get(diagonal));
                }
                else {
                    int d1 = MISMATCH + hash.get(diagonal);
                    int d2 = GAP + hash.get(next);
                    int d3 = GAP + hash.get(up);
                    int min = Math.min(d1, Math.min(d2,d3));
                    hash.put(position, min);
                    }
                }
            }
        return hash.get(new Pair<>(dna1.size(), dna2.size()));
//        int len1 = dna11.length() +1;
//        int len2 = dna21.length() +1;
//        int[][] table = new int[len1][len2];
//
//        for(int i=0; i<len2; i++) table[0][i] = i+2;
//        for(int i=0; i<len1; i++) table[i][0] = i+2;
//
//        try{
//            for(int i=1; i<len1; i++) {
//                for(int j=1;j<len2; j++) {
//                    if(dna1.get(i) == dna2.get(i)) { //DNA FIRSTGET
//                        table[i][j] = table[i-1][j-1];
//                        dna1 = dna1.; //dna1 getRest
//                    }
//                    else {
//                        int d1 = MISMATCH + table[i-1][j-1];
//                        int d2 = GAP + table[i][j-1];
//                        int d3 = GAP +table[i-1][j];
//                        int min = Math.min(d1, Math.min(d2,d3));
//                        table[i][j] = min;
//                        dna1.set(i, dna1.get(i)); //dna2.getRest
//                    }
//                }
//            }
//
//                return table[len1-1][len2-1];
//
//        }
//        catch (Exception e) {
//            return table[len1-1][len2-1];
//        }
    }
}
