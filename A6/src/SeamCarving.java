import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class SeamCarving {
    private int[] pixels;
    private int type, height, width;

    // Field getters

    int[] getPixels() {
        return pixels;
    }

    int getType() {
        return type;
    }

    int getHeight() {
        return height;
    }

    int getWidth() {
        return width;
    }

    // Read and write images

    void readImage(String filename) throws IOException {
        BufferedImage image = ImageIO.read(new File(filename));
        type = image.getType();
        height = image.getHeight();
        width = image.getWidth();
        pixels = new int[width * height];
        image.getRGB(0, 0, width, height, pixels, 0, width);
    }

    void writeImage(String filename) throws IOException {
        BufferedImage image = new BufferedImage(width, height, type);
        image.setRGB(0, 0, width, height, pixels, 0, width);
        ImageIO.write(image, "jpg", new File(filename));
    }

    // Accessing pixels and their neighbors

    Color getColor(int h, int w) {
        int pixel = pixels[w + h * width];
        return new Color(pixel, true);
    }

    ArrayList<Position> getHVneighbors(int h, int w) {
<<<<<<< HEAD
        ArrayList<Position> list = new ArrayList<>();

        //start with horizontal direction
        //start with horizontal direction add left and right neighbor
        Position left = new Position(h,w-1);
        Position right = new Position(h,w+1);
        //start with vertical direction add bottom and top neighbor
        Position bottom = new Position(h+1, w);
        Position up = new Position(h-1, w);
        // Corner
        if (h == 0 && w== 0 ) {
            list.add(right);
            list.add(bottom);
        } else if (h == 0 && w == width-1) {
            list.add(left);
            list.add(bottom);
        } else if (h== height-1 && w== 0) {
            list.add (right);
            list.add(up);
        } else if (h == height-1 && w == width-1) {
            list.add(left);
            list.add(up);
        } else if ( h >0 && h < height-1 && w==0)  {
            list.add(right);
            list.add(bottom);
            list.add(up);
        } else if (h == 0 && w > 0 && w < width-1) {
            list.add(left);
            list.add(right);
            list.add(bottom);
        } else if (h > 0 && h < height - 1 && w == width-1) {
            list.add(left);
            list.add(bottom);
            list.add(up);
        } else if ( w > 0 && h == height -1 && w < width -1) {
            list.add(left);
            list.add(right);
            list.add(up);
        } else  {
            list.add(left);
            list.add(right);
            list.add(bottom);
            list.add(up);
        }

        // Line

        return list;
=======
        ArrayList<Position> neighbors = new ArrayList<>();

        if (w == 0) neighbors.add(new Position(h, w + 1));
        else if (w + 1 == width) neighbors.add(new Position(h, w - 1));
        else {
            neighbors.add(new Position(h, w - 1));
            neighbors.add(new Position(h, w + 1));
        }

        if (h == 0) neighbors.add(new Position(h + 1, w));
        else if (h + 1 == height) neighbors.add(new Position(h - 1, w));
        else {
            neighbors.add(new Position(h + 1, w));
            neighbors.add(new Position(h - 1, w));
        }
        return neighbors;
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }

    ArrayList<Position> getBelowNeighbors(int h, int w) {
<<<<<<< HEAD
        ArrayList<Position> list = new ArrayList<>();

        Position down = new Position(h+1,w);
        Position dRight = new Position(h+1,w+1);
        Position dLeft = new Position (h+1, w-1);

        // Corner
        if (h == 0 && w== 0 ) {
            list.add(down);
            list.add(dRight);
        } else if (h == 0 && w == width-1) {
            list.add(down);
            list.add(dLeft);
        } else if (h== height-1 && w== 0) {
            list = list; //empty list
        } else if (h == height-1 && w == width-1) {
            list = list; //empty list
        } else if ( h >0 && h < height-1 && w==0)  {
            list.add(down);
            list.add(dRight);
        } else if (h == 0 && w > 0 && w < width-1) {
            list.add(down);
            list.add(dRight);
            list.add(dLeft);
        } else if (h > 0 && h < height - 1 && w == width-1) {
            list.add(down);
            list.add(dLeft);
        } else if ( w > 0 && h == height -1 && w < width -1) {
            list = list; //empty list
        } else  {
            list.add(down);
            list.add(dRight);
            list.add(dLeft);
        }


        return list; // to write
=======
        ArrayList<Position> neighbors = new ArrayList<>();
        if (h + 1 == height) return neighbors;
        neighbors.add(new Position(h + 1, w));
        if (w == 0) {
            neighbors.add(new Position(h + 1, w + 1));
            return neighbors;
        } else if (w + 1 == width) {
            neighbors.add(new Position(h + 1, w - 1));
            return neighbors;
        } else {
            neighbors.add(new Position(h + 1, w + 1));
            neighbors.add(new Position(h + 1, w - 1));
            return neighbors;
        }
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }

    // Computing energy at given pixel

    int computeEnergy(int h, int w) {
<<<<<<< HEAD
        // get the colors of the pixel using the method getColor
        Color x = getColor(h,w);

        int computeEnergy = 0;
        int red, green, blue;

       ArrayList<Position> listofNeighbor = getHVneighbors(h,w);

        for (Position p: listofNeighbor) {
            Color neighbor = getColor(p.getFirst(), p.getSecond());

            red = (int) Math.pow((x.getRed() - neighbor.getRed()), 2);
            green = (int) Math.pow((x.getGreen() - neighbor.getGreen()), 2);
            blue = (int) Math.pow((x.getBlue() - neighbor.getBlue()), 2);

            computeEnergy +=  red + green + blue;

        }
        return computeEnergy;
    }

    /**
     * This next method is the core of our dynamic programming algorithm. We will
     * use the top-down approach with the given hash table (which you should initialize).
     * The key to the hash table is a pixel position. The value stored at each key
     * is the "seam" that starts with this pixel all the way to the bottom
     * of the image and its cost.
     *
     * The method takes the position of a pixel and returns the seam from this pixel
     * and its cost using the following steps:
     *   - compute the energy of the given pixel
     *   - get the list of neighbors below the current pixel
     *   - Base case: if the list of neighbors is empty, return the following pair:
     *       < [<h,w>], energy >
     *     the first component of the pair is a list containing just one position
     *     (the current one); the second component of the pair is the current energy.
     *   - Recursive case: we will consider each of the neighbors below the current
     *     pixel and choose the one with the cheapest seam.
     *
     */
    Map<Position, Pair<List<Position>, Integer>> hash = new HashMap<>();

    Pair<List<Position>, Integer> findSeam(int h, int w) {
        if (hash.containsKey(new Position(h,w))) {
            return hash.get(new Position(h,w));
        }

        ArrayList<Position> belowneighborList = getBelowNeighbors(h,w);

        if (belowneighborList.isEmpty()) {
            return new Pair<List<Position>, Integer>(List.singleton(new Position(h,w)), computeEnergy(h,w));
        }

        List<Position> path = new Empty<>();
        int bestEnergy = Integer.MAX_VALUE;

        for (Position p : belowneighborList) {
            Pair<List<Position>, Integer> currentEnergy = findSeam(p.getFirst(),p.getSecond());
            if (currentEnergy.getSecond() < bestEnergy) {
            bestEnergy = currentEnergy.getSecond();
            path = currentEnergy.getFirst();
            }
        }
        bestEnergy = bestEnergy + computeEnergy(h,w);
        path = new Node(new Position(h,w), path);
        Pair<List<Position>, Integer> pair = new Pair<>(path,bestEnergy);
        hash.put(new Position(h,w) ,pair);

        return pair; // to write
=======
        Color c = getColor(h, w);
        Function<Integer, Integer> sq = n -> n * n;
        int energy = 0;
        for (Position p : getHVneighbors(h, w)) {
            Color nc = getColor(p.getFirst(), p.getSecond());
            energy += sq.apply(nc.getRed() - c.getRed());
            energy += sq.apply(nc.getGreen() - c.getGreen());
            energy += sq.apply(nc.getBlue() - c.getBlue());
        }
        return energy;
    }

    // Find seam starting from (h,w) going down and return list of positions and cost
    // and then pick best seam starting from some position on the first row

    Map<Position, Pair<List<Position>, Integer>> hash = new WeakHashMap<>();

    Pair<List<Position>, Integer> findSeam(int h, int w) {
        return hash.computeIfAbsent(new Position(h, w), p -> {

                    int energy = computeEnergy(h, w);
                    ArrayList<Position> below = getBelowNeighbors(h, w);
                    if (below.isEmpty()) {
                        return new Pair<List<Position>, Integer>(new Node<Position>(new Position(h, w), new Empty<>()),
                                energy);
                    } else {
                        if (below.size() == 1) {
                            Pair<List<Position>, Integer> s = findSeam(below.get(0).getFirst(), below.get(0).getFirst());
                            return new Pair<List<Position>, Integer>(new Node<Position>(new Position(h, w), s.getFirst()),
                                    energy + s.getSecond());
                        } else if (below.size() == 2) {
                            Pair<List<Position>, Integer> s1 = findSeam(below.get(0).getFirst(), below.get(0).getSecond());
                            Pair<List<Position>, Integer> s2 = findSeam(below.get(1).getFirst(), below.get(1).getSecond());
                            if (s1.getSecond() <= s2.getSecond()) {
                                return new Pair<List<Position>, Integer>(new Node<Position>(new Position(h, w), s1.getFirst()),
                                        energy + s1.getSecond());
                            } else {
                                return new Pair<List<Position>, Integer>(new Node<Position>(new Position(h, w), s2.getFirst()),
                                        energy + s2.getSecond());
                            }
                        } else if (below.size() == 3) {
                            Pair<List<Position>, Integer> s1 = findSeam(below.get(0).getFirst(), below.get(0).getSecond());
                            Pair<List<Position>, Integer> s2 = findSeam(below.get(1).getFirst(), below.get(1).getSecond());
                            Pair<List<Position>, Integer> s3 = findSeam(below.get(2).getFirst(), below.get(2).getSecond());

                            if (s1.getSecond() <= s2.getSecond()) {
                                if (s1.getSecond() <= s3.getSecond()) {
                                    return new Pair<List<Position>, Integer>(new Node<Position>(new Position(h, w), s1.getFirst()),
                                            energy + s1.getSecond());
                                } else {
                                    return new Pair<List<Position>, Integer>(new Node<Position>(new Position(h, w), s3.getFirst()),
                                            energy + s3.getSecond());
                                }
                            } else {
                                if (s2.getSecond() <= s3.getSecond()) {
                                    return new Pair<List<Position>, Integer>(new Node<Position>(new Position(h, w), s2.getFirst()),
                                            energy + s2.getSecond());
                                } else {
                                    return new Pair<List<Position>, Integer>(new Node<Position>(new Position(h, w), s3.getFirst()),
                                            energy + s3.getSecond());
                                }
                            }
                        }
                    }
                    return null;
                });
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }


<<<<<<< HEAD
    Pair<List<Position>,Integer> bestSeam () {
        //clears the hash table
        hash.clear();
        int bestEnergy = Integer.MAX_VALUE;
//        List<Position> bestpath = new Empty<>();
        Pair<List<Position>, Integer> bestPair = null;
        // w=width -> iterate over the first row of the image, computing the seam from its position and returning the best one
        for (int w = 0; w < getWidth() ; w++) {
            System.out.println(w);
            Pair<List<Position>, Integer > current = findSeam(0,w);
            if (current.getSecond() < bestEnergy) {
                bestEnergy = current.getSecond();
//                bestpath = current.getFirst();
                bestPair = current;
                //bestPair = new Pair<>(bestpath,bestEnergy);
            }
        }
        return bestPair; // to write
    }

    /**
     * The last method puts its all together:
     *   - it finds the best seam
     *   - then it creates a new array of pixels representing an image of dimensions
     *     (height,width-1)
     *   - it then copies the old array pixels to the new arrays skipping the pixels
     *     in the seam
     *   - the method does not return anything: instead it updates the width and
     *     pixels instance variables to the new values.
     */
    void cutSeam () {
      //  Pair<List<Position>, Integer> original = bestSeam();
        List<Position> obestList = bestSeam().getFirst();
        //creates a new array of pixels representing an image of dimensions (height, width-1)

        //Making the pixel
       // int [] np = new int[getHeight() * (getWidth()-1)];
        int [] np = new int[height * (width -1)];

            for (int i=0; i<height; i++) {
                int newWidth =0;
                try {
                for (int j=0; j<width; j++) {
                    if(obestList.isEmpty()) {
                        np[i * (width-1) + newWidth] = pixels[i*(width)+j];
//                        np[i][(width-1) + newWidth]= pixels[i * (width) +j];
                    }
                    else {
                        if (obestList.getFirst().equals(new Position(i,j))) {
                        //if (best.getFirst().eqauls(new Position (h,w)))
                       // if(obestList.getFirst().getSecond() == j && obestList.getFirst().getFirst() == i) {
                            obestList = obestList.getRest();
                        }
                        else {
                            np[i * (width -1) + newWidth] = pixels[i*width+j];
//                            np[i][(width-1) + newWidth] = pixels[i*width+j];
                            newWidth = newWidth + 1;
                        }
                    }
                }
            }
            catch (EmptyListE e) {
                }
        }

        pixels = np;
        width -= 1;

=======

    Pair<List<Position>, Integer> bestSeam() {
        hash.clear();
        int cost = Integer.MAX_VALUE;
        List<Position> seam = new Empty<>();
        for (int w = 0; w < width; w++) {
            Pair<List<Position>, Integer> r = findSeam(0, w);
            if (r.getSecond() < cost) {
                seam = r.getFirst();
                cost = r.getSecond();
            }
        }
        return new Pair<>(seam, cost);
    }

    // Putting it all together; find best seam and copy pixels without that seam

    void cutSeam() {
        try {
            List<Position> seam = bestSeam().getFirst();
            int[] newPixels = new int[height * (width - 1)];
            for (int h = 0; h < height; h++) {
                int nw = 0;
                for (int w = 0; w < width; w++) {
                    if (seam.isEmpty()) {
                        newPixels[nw + h * (width - 1)] = pixels[w + h * width];
                    }
                    else {
                        Position p = seam.getFirst();
                        if (p.getFirst() == h && p.getSecond() == w) {
                            seam = seam.getRest();
                            nw--;
                        } else {
                            newPixels[nw + h * (width - 1)] = pixels[w + h * width];
                        }
                    }
                    nw++;
                }
            }
            width = width - 1;
            pixels = newPixels;
        } catch (EmptyListE e) {
            throw new Error("Bug");
        }
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }
}
