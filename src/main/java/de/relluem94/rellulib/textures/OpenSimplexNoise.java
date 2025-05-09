package de.relluem94.rellulib.textures;

public class OpenSimplexNoise {

    private static final double STRETCH_CONSTANT_2D = -0.211324865405187;    //(1/Math.sqrt(2+1)-1)/2;
    private static final double SQUISH_CONSTANT_2D = 0.366025403784439;      //(Math.sqrt(2+1)-1)/2;
    private static final double STRETCH_CONSTANT_3D = -1.0 / 6;              //(1/Math.sqrt(3+1)-1)/3;
    private static final double SQUISH_CONSTANT_3D = 1.0 / 3;                //(Math.sqrt(3+1)-1)/3;
    private static final double STRETCH_CONSTANT_4D = -0.138196601125011;    //(1/Math.sqrt(4+1)-1)/4;
    private static final double SQUISH_CONSTANT_4D = 0.309016994374947;      //(Math.sqrt(4+1)-1)/4;

    private static final double NORM_CONSTANT_2D = 47;
    private static final double NORM_CONSTANT_3D = 103;
    private static final double NORM_CONSTANT_4D = 30;

    private static final long DEFAULT_SEED = 0;

    protected final short[] perm;
    protected final short[] permGradIndex3D;

    protected OpenSimplexNoise() {
        this(DEFAULT_SEED);
    }

    public OpenSimplexNoise(short[] perm) {
        this.perm = perm;
        permGradIndex3D = new short[256];

        for (int i = 0; i < 256; i++) {
            //Since 3D has 24 gradients, simple bitmask won't work, so precompute modulo array.
            permGradIndex3D[i] = (short) ((perm[i] % (gradients3D.length / 3)) * 3);
        }
    }

    //Initializes the class using a permutation array generated from a 64-bit seed.
    //Generates a proper permutation (i.e. doesn't merely perform N successive pair swaps on a base array)
    //Uses a simple 64-bit LCG.
    protected OpenSimplexNoise(long seed) {
        perm = new short[256];
        permGradIndex3D = new short[256];
        short[] source = new short[256];
        for (short i = 0; i < 256; i++) {
            source[i] = i;
        }
        seed = seed * 6364136223846793005L + 1442695040888963407L;
        seed = seed * 6364136223846793005L + 1442695040888963407L;
        seed = seed * 6364136223846793005L + 1442695040888963407L;
        for (int i = 255; i >= 0; i--) {
            seed = seed * 6364136223846793005L + 1442695040888963407L;
            int r = (int) ((seed + 31) % (i + 1));
            if (r < 0) {
                r += (i + 1);
            }
            perm[i] = source[r];
            permGradIndex3D[i] = (short) ((perm[i] % (gradients3D.length / 3)) * 3);
            source[r] = source[i];
        }
    }

    //2D OpenSimplex Noise.
    protected double eval(double x, double y) {

        //Place input coordinates onto grid.
        double stretchOffset = (x + y) * STRETCH_CONSTANT_2D;
        double xs = x + stretchOffset;
        double ys = y + stretchOffset;

        //Floor to get grid coordinates of rhombus (stretched square) super-cell origin.
        int xsb = fastFloor(xs);
        int ysb = fastFloor(ys);

        //Skew out to get actual coordinates of rhombus origin. We'll need these later.
        double squishOffset = (xsb + ysb) * SQUISH_CONSTANT_2D;
        double xb = xsb + squishOffset;
        double yb = ysb + squishOffset;

        //Compute grid coordinates relative to rhombus origin.
        double xins = xs - xsb;
        double yins = ys - ysb;

        //Sum those together to get a value that determines which region we're in.
        double inSum = xins + yins;

        //Positions relative to origin point.
        double dx0 = x - xb;
        double dy0 = y - yb;

        //We'll be defining these inside the next block and using them afterwards.
        double dxExt;
        double dyExt;
        int xsvExt;
        int ysvExt;

        double value = 0;

        //Contribution (1,0)
        double dx1 = dx0 - 1 - SQUISH_CONSTANT_2D;
        double dy1 = dy0 - 0 - SQUISH_CONSTANT_2D;
        double attn1 = 2 - dx1 * dx1 - dy1 * dy1;
        if (attn1 > 0) {
            attn1 *= attn1;
            value += attn1 * attn1 * extrapolate(xsb + 1, ysb, dx1, dy1);
        }

        //Contribution (0,1)
        double dx2 = dx0 - 0 - SQUISH_CONSTANT_2D;
        double dy2 = dy0 - 1 - SQUISH_CONSTANT_2D;
        double attn2 = 2 - dx2 * dx2 - dy2 * dy2;
        if (attn2 > 0) {
            attn2 *= attn2;
            value += attn2 * attn2 * extrapolate(xsb, ysb + 1, dx2, dy2);
        }

        if (inSum <= 1) { //We're inside the triangle (2-Simplex) at (0,0)
            double zins = 1 - inSum;
            if (zins > xins || zins > yins) { //(0,0) is one of the closest two triangular vertices
                if (xins > yins) {
                    xsvExt = xsb + 1;
                    ysvExt = ysb - 1;
                    dxExt = dx0 - 1;
                    dyExt = dy0 + 1;
                } else {
                    xsvExt = xsb - 1;
                    ysvExt = ysb + 1;
                    dxExt = dx0 + 1;
                    dyExt = dy0 - 1;
                }
            } else { //(1,0) and (0,1) are the closest two vertices.
                xsvExt = xsb + 1;
                ysvExt = ysb + 1;
                dxExt = dx0 - 1 - 2 * SQUISH_CONSTANT_2D;
                dyExt = dy0 - 1 - 2 * SQUISH_CONSTANT_2D;
            }
        } else { //We're inside the triangle (2-Simplex) at (1,1)
            double zins = 2 - inSum;
            if (zins < xins || zins < yins) { //(0,0) is one of the closest two triangular vertices
                if (xins > yins) {
                    xsvExt = xsb + 2;
                    ysvExt = ysb;
                    dxExt = dx0 - 2 - 2 * SQUISH_CONSTANT_2D;
                    dyExt = dy0 - 2 * SQUISH_CONSTANT_2D;
                } else {
                    xsvExt = xsb;
                    ysvExt = ysb + 2;
                    dxExt = dx0 - 2 * SQUISH_CONSTANT_2D;
                    dyExt = dy0 - 2 - 2 * SQUISH_CONSTANT_2D;
                }
            } else { //(1,0) and (0,1) are the closest two vertices.
                dxExt = dx0;
                dyExt = dy0;
                xsvExt = xsb;
                ysvExt = ysb;
            }
            xsb += 1;
            ysb += 1;
            dx0 = dx0 - 1 - 2 * SQUISH_CONSTANT_2D;
            dy0 = dy0 - 1 - 2 * SQUISH_CONSTANT_2D;
        }

        //Contribution (0,0) or (1,1)
        double attn0 = 2 - dx0 * dx0 - dy0 * dy0;
        if (attn0 > 0) {
            attn0 *= attn0;
            value += attn0 * attn0 * extrapolate(xsb, ysb, dx0, dy0);
        }

        //Extra Vertex
        double attn_ext = 2 - dxExt * dxExt - dyExt * dyExt;
        if (attn_ext > 0) {
            attn_ext *= attn_ext;
            value += attn_ext * attn_ext * extrapolate(xsvExt, ysvExt, dxExt, dyExt);
        }

        return value / NORM_CONSTANT_2D;
    }

    //3D OpenSimplex Noise.
    protected double eval(double x, double y, double z) {

        //Place input coordinates on simplistic honeycomb.
        double stretchOffset = (x + y + z) * STRETCH_CONSTANT_3D;
        double xs = x + stretchOffset;
        double ys = y + stretchOffset;
        double zs = z + stretchOffset;

        //Floor to get simplectic honeycomb coordinates of rhombohedron (stretched cube) super-cell origin.
        int xsb = fastFloor(xs);
        int ysb = fastFloor(ys);
        int zsb = fastFloor(zs);

        //Skew out to get actual coordinates of rhombohedron origin. We'll need these later.
        double squishOffset = (xsb + ysb + zsb) * SQUISH_CONSTANT_3D;
        double xb = xsb + squishOffset;
        double yb = ysb + squishOffset;
        double zb = zsb + squishOffset;

        //Compute simplectic honeycomb coordinates relative to rhombohedral origin.
        double xins = xs - xsb;
        double yins = ys - ysb;
        double zins = zs - zsb;

        //Sum those together to get a value that determines which region we're in.
        double inSum = xins + yins + zins;

        //Positions relative to origin point.
        double dx0 = x - xb;
        double dy0 = y - yb;
        double dz0 = z - zb;

        //We'll be defining these inside the next block and using them afterwards.
        double dxExt0;
        double dyExt0;
        double dzExt0;
        double dxExt1;
        double dyExt1;
        double dzExt1;
        int xsvExt0;
        int ysvExt0;
        int zsvExt0;
        int xsvExt1;
        int ysvExt1;
        int zsvExt1;

        double value = 0;
        if (inSum <= 1) { //We're inside the tetrahedron (3-Simplex) at (0,0,0)

            //Determine which two of (0,0,1), (0,1,0), (1,0,0) are closest.
            byte aPoint = 0x01;
            double aScore = xins;
            byte bPoint = 0x02;
            double bScore = yins;
            if (aScore >= bScore && zins > bScore) {
                bScore = zins;
                bPoint = 0x04;
            } else if (aScore < bScore && zins > aScore) {
                aScore = zins;
                aPoint = 0x04;
            }

            //Now we determine the two lattice points not part of the tetrahedron that may contribute.
            //This depends on the closest two tetrahedral vertices, including (0,0,0)
            double wins = 1 - inSum;
            if (wins > aScore || wins > bScore) { //(0,0,0) is one of the closest two tetrahedral vertices.
                byte c = (bScore > aScore ? bPoint : aPoint); //Our other closest vertex is the closest out of a and b.

                if ((c & 0x01) == 0) {
                    xsvExt0 = xsb - 1;
                    xsvExt1 = xsb;
                    dxExt0 = dx0 + 1;
                    dxExt1 = dx0;
                } else {
                    xsvExt0 = xsvExt1 = xsb + 1;
                    dxExt0 = dxExt1 = dx0 - 1;
                }

                if ((c & 0x02) == 0) {
                    ysvExt0 = ysvExt1 = ysb;
                    dyExt0 = dyExt1 = dy0;
                    if ((c & 0x01) == 0) {
                        ysvExt1 -= 1;
                        dyExt1 += 1;
                    } else {
                        ysvExt0 -= 1;
                        dyExt0 += 1;
                    }
                } else {
                    ysvExt0 = ysvExt1 = ysb + 1;
                    dyExt0 = dyExt1 = dy0 - 1;
                }

                if ((c & 0x04) == 0) {
                    zsvExt0 = zsb;
                    zsvExt1 = zsb - 1;
                    dzExt0 = dz0;
                    dzExt1 = dz0 + 1;
                } else {
                    zsvExt0 = zsvExt1 = zsb + 1;
                    dzExt0 = dzExt1 = dz0 - 1;
                }
            } else { //(0,0,0) is not one of the closest two tetrahedral vertices.
                byte c = (byte) (aPoint | bPoint); //Our two extra vertices are determined by the closest two.

                if ((c & 0x01) == 0) {
                    xsvExt0 = xsb;
                    xsvExt1 = xsb - 1;
                    dxExt0 = dx0 - 2 * SQUISH_CONSTANT_3D;
                    dxExt1 = dx0 + 1 - SQUISH_CONSTANT_3D;
                } else {
                    xsvExt0 = xsvExt1 = xsb + 1;
                    dxExt0 = dx0 - 1 - 2 * SQUISH_CONSTANT_3D;
                    dxExt1 = dx0 - 1 - SQUISH_CONSTANT_3D;
                }

                if ((c & 0x02) == 0) {
                    ysvExt0 = ysb;
                    ysvExt1 = ysb - 1;
                    dyExt0 = dy0 - 2 * SQUISH_CONSTANT_3D;
                    dyExt1 = dy0 + 1 - SQUISH_CONSTANT_3D;
                } else {
                    ysvExt0 = ysvExt1 = ysb + 1;
                    dyExt0 = dy0 - 1 - 2 * SQUISH_CONSTANT_3D;
                    dyExt1 = dy0 - 1 - SQUISH_CONSTANT_3D;
                }

                if ((c & 0x04) == 0) {
                    zsvExt0 = zsb;
                    zsvExt1 = zsb - 1;
                    dzExt0 = dz0 - 2 * SQUISH_CONSTANT_3D;
                    dzExt1 = dz0 + 1 - SQUISH_CONSTANT_3D;
                } else {
                    zsvExt0 = zsvExt1 = zsb + 1;
                    dzExt0 = dz0 - 1 - 2 * SQUISH_CONSTANT_3D;
                    dzExt1 = dz0 - 1 - SQUISH_CONSTANT_3D;
                }
            }

            //Contribution (0,0,0)
            double attn0 = 2 - dx0 * dx0 - dy0 * dy0 - dz0 * dz0;
            if (attn0 > 0) {
                attn0 *= attn0;
                value += attn0 * attn0 * extrapolate(xsb, ysb, zsb, dx0, dy0, dz0);
            }

            //Contribution (1,0,0)
            double dx1 = dx0 - 1 - SQUISH_CONSTANT_3D;
            double dy1 = dy0 - 0 - SQUISH_CONSTANT_3D;
            double dz1 = dz0 - 0 - SQUISH_CONSTANT_3D;
            double attn1 = 2 - dx1 * dx1 - dy1 * dy1 - dz1 * dz1;
            if (attn1 > 0) {
                attn1 *= attn1;
                value += attn1 * attn1 * extrapolate(xsb + 1, ysb, zsb, dx1, dy1, dz1);
            }

            //Contribution (0,1,0)
            double dx2 = dx0 - 0 - SQUISH_CONSTANT_3D;
            double dy2 = dy0 - 1 - SQUISH_CONSTANT_3D;
            double attn2 = 2 - dx2 * dx2 - dy2 * dy2 - dz1 * dz1;
            if (attn2 > 0) {
                attn2 *= attn2;
                value += attn2 * attn2 * extrapolate(xsb, ysb + 1, zsb, dx2, dy2, dz1);
            }

            //Contribution (0,0,1)
            double dz3 = dz0 - 1 - SQUISH_CONSTANT_3D;
            double attn3 = 2 - dx2 * dx2 - dy1 * dy1 - dz3 * dz3;
            if (attn3 > 0) {
                attn3 *= attn3;
                value += attn3 * attn3 * extrapolate(xsb, ysb, zsb + 1, dx2, dy1, dz3);
            }
        } else if (inSum >= 2) { //We're inside the tetrahedron (3-Simplex) at (1,1,1)

            //Determine which two tetrahedral vertices are the closest, out of (1,1,0), (1,0,1), (0,1,1) but not (1,1,1).
            byte aPoint = 0x06;
            double aScore = xins;
            byte bPoint = 0x05;
            double bScore = yins;
            if (aScore <= bScore && zins < bScore) {
                bScore = zins;
                bPoint = 0x03;
            } else if (aScore > bScore && zins < aScore) {
                aScore = zins;
                aPoint = 0x03;
            }

            //Now we determine the two lattice points not part of the tetrahedron that may contribute.
            //This depends on the closest two tetrahedral vertices, including (1,1,1)
            double wins = 3 - inSum;
            if (wins < aScore || wins < bScore) { //(1,1,1) is one of the closest two tetrahedral vertices.
                byte c = (bScore < aScore ? bPoint : aPoint); //Our other closest vertex is the closest out of a and b.

                if ((c & 0x01) != 0) {
                    xsvExt0 = xsb + 2;
                    xsvExt1 = xsb + 1;
                    dxExt0 = dx0 - 2 - 3 * SQUISH_CONSTANT_3D;
                    dxExt1 = dx0 - 1 - 3 * SQUISH_CONSTANT_3D;
                } else {
                    xsvExt0 = xsvExt1 = xsb;
                    dxExt0 = dxExt1 = dx0 - 3 * SQUISH_CONSTANT_3D;
                }

                if ((c & 0x02) != 0) {
                    ysvExt0 = ysvExt1 = ysb + 1;
                    dyExt0 = dyExt1 = dy0 - 1 - 3 * SQUISH_CONSTANT_3D;
                    if ((c & 0x01) != 0) {
                        ysvExt1 += 1;
                        dyExt1 -= 1;
                    } else {
                        ysvExt0 += 1;
                        dyExt0 -= 1;
                    }
                } else {
                    ysvExt0 = ysvExt1 = ysb;
                    dyExt0 = dyExt1 = dy0 - 3 * SQUISH_CONSTANT_3D;
                }

                if ((c & 0x04) != 0) {
                    zsvExt0 = zsb + 1;
                    zsvExt1 = zsb + 2;
                    dzExt0 = dz0 - 1 - 3 * SQUISH_CONSTANT_3D;
                    dzExt1 = dz0 - 2 - 3 * SQUISH_CONSTANT_3D;
                } else {
                    zsvExt0 = zsvExt1 = zsb;
                    dzExt0 = dzExt1 = dz0 - 3 * SQUISH_CONSTANT_3D;
                }
            } else { //(1,1,1) is not one of the closest two tetrahedral vertices.
                byte c = (byte) (aPoint & bPoint); //Our two extra vertices are determined by the closest two.

                if ((c & 0x01) != 0) {
                    xsvExt0 = xsb + 1;
                    xsvExt1 = xsb + 2;
                    dxExt0 = dx0 - 1 - SQUISH_CONSTANT_3D;
                    dxExt1 = dx0 - 2 - 2 * SQUISH_CONSTANT_3D;
                } else {
                    xsvExt0 = xsvExt1 = xsb;
                    dxExt0 = dx0 - SQUISH_CONSTANT_3D;
                    dxExt1 = dx0 - 2 * SQUISH_CONSTANT_3D;
                }

                if ((c & 0x02) != 0) {
                    ysvExt0 = ysb + 1;
                    ysvExt1 = ysb + 2;
                    dyExt0 = dy0 - 1 - SQUISH_CONSTANT_3D;
                    dyExt1 = dy0 - 2 - 2 * SQUISH_CONSTANT_3D;
                } else {
                    ysvExt0 = ysvExt1 = ysb;
                    dyExt0 = dy0 - SQUISH_CONSTANT_3D;
                    dyExt1 = dy0 - 2 * SQUISH_CONSTANT_3D;
                }

                if ((c & 0x04) != 0) {
                    zsvExt0 = zsb + 1;
                    zsvExt1 = zsb + 2;
                    dzExt0 = dz0 - 1 - SQUISH_CONSTANT_3D;
                    dzExt1 = dz0 - 2 - 2 * SQUISH_CONSTANT_3D;
                } else {
                    zsvExt0 = zsvExt1 = zsb;
                    dzExt0 = dz0 - SQUISH_CONSTANT_3D;
                    dzExt1 = dz0 - 2 * SQUISH_CONSTANT_3D;
                }
            }

            //Contribution (1,1,0)
            double dx3 = dx0 - 1 - 2 * SQUISH_CONSTANT_3D;
            double dy3 = dy0 - 1 - 2 * SQUISH_CONSTANT_3D;
            double dz3 = dz0 - 0 - 2 * SQUISH_CONSTANT_3D;
            double attn3 = 2 - dx3 * dx3 - dy3 * dy3 - dz3 * dz3;
            if (attn3 > 0) {
                attn3 *= attn3;
                value += attn3 * attn3 * extrapolate(xsb + 1, ysb + 1, zsb, dx3, dy3, dz3);
            }

            //Contribution (1,0,1)
            double dy2 = dy0 - 0 - 2 * SQUISH_CONSTANT_3D;
            double dz2 = dz0 - 1 - 2 * SQUISH_CONSTANT_3D;
            double attn2 = 2 - dx3 * dx3 - dy2 * dy2 - dz2 * dz2;
            if (attn2 > 0) {
                attn2 *= attn2;
                value += attn2 * attn2 * extrapolate(xsb + 1, ysb, zsb + 1, dx3, dy2, dz2);
            }

            //Contribution (0,1,1)
            double dx1 = dx0 - 0 - 2 * SQUISH_CONSTANT_3D;
            double attn1 = 2 - dx1 * dx1 - dy3 * dy3 - dz2 * dz2;
            if (attn1 > 0) {
                attn1 *= attn1;
                value += attn1 * attn1 * extrapolate(xsb, ysb + 1, zsb + 1, dx1, dy3, dz2);
            }

            //Contribution (1,1,1)
            dx0 = dx0 - 1 - 3 * SQUISH_CONSTANT_3D;
            dy0 = dy0 - 1 - 3 * SQUISH_CONSTANT_3D;
            dz0 = dz0 - 1 - 3 * SQUISH_CONSTANT_3D;
            double attn0 = 2 - dx0 * dx0 - dy0 * dy0 - dz0 * dz0;
            if (attn0 > 0) {
                attn0 *= attn0;
                value += attn0 * attn0 * extrapolate(xsb + 1, ysb + 1, zsb + 1, dx0, dy0, dz0);
            }
        } else { //We're inside the octahedron (Rectified 3-Simplex) in between.
            double aScore;
            byte aPoint;
            boolean aIsFurtherSide;
            double bScore;
            byte bPoint;
            boolean bIsFurtherSide;

            //Decide between point (0,0,1) and (1,1,0) as closest
            double p1 = xins + yins;
            if (p1 > 1) {
                aScore = p1 - 1;
                aPoint = 0x03;
                aIsFurtherSide = true;
            } else {
                aScore = 1 - p1;
                aPoint = 0x04;
                aIsFurtherSide = false;
            }

            //Decide between point (0,1,0) and (1,0,1) as closest
            double p2 = xins + zins;
            if (p2 > 1) {
                bScore = p2 - 1;
                bPoint = 0x05;
                bIsFurtherSide = true;
            } else {
                bScore = 1 - p2;
                bPoint = 0x02;
                bIsFurtherSide = false;
            }

            //The closest out of the two (1,0,0) and (0,1,1) will replace the furthest out of the two decided above, if closer.
            double p3 = yins + zins;
            if (p3 > 1) {
                double score = p3 - 1;
                if (aScore <= bScore && aScore < score) {
                    aPoint = 0x06;
                    aIsFurtherSide = true;
                } else if (aScore > bScore && bScore < score) {
                    bPoint = 0x06;
                    bIsFurtherSide = true;
                }
            } else {
                double score = 1 - p3;
                if (aScore <= bScore && aScore < score) {
                    aPoint = 0x01;
                    aIsFurtherSide = false;
                } else if (aScore > bScore && bScore < score) {
                    bPoint = 0x01;
                    bIsFurtherSide = false;
                }
            }

            //Where each of the two closest points are determines how the extra two vertices are calculated.
            if (aIsFurtherSide == bIsFurtherSide) {
                if (aIsFurtherSide) { //Both closest points on (1,1,1) side

                    //One of the two extra points is (1,1,1)
                    dxExt0 = dx0 - 1 - 3 * SQUISH_CONSTANT_3D;
                    dyExt0 = dy0 - 1 - 3 * SQUISH_CONSTANT_3D;
                    dzExt0 = dz0 - 1 - 3 * SQUISH_CONSTANT_3D;
                    xsvExt0 = xsb + 1;
                    ysvExt0 = ysb + 1;
                    zsvExt0 = zsb + 1;

                    //Other extra point is based on the shared axis.
                    byte c = (byte) (aPoint & bPoint);
                    if ((c & 0x01) != 0) {
                        dxExt1 = dx0 - 2 - 2 * SQUISH_CONSTANT_3D;
                        dyExt1 = dy0 - 2 * SQUISH_CONSTANT_3D;
                        dzExt1 = dz0 - 2 * SQUISH_CONSTANT_3D;
                        xsvExt1 = xsb + 2;
                        ysvExt1 = ysb;
                        zsvExt1 = zsb;
                    } else if ((c & 0x02) != 0) {
                        dxExt1 = dx0 - 2 * SQUISH_CONSTANT_3D;
                        dyExt1 = dy0 - 2 - 2 * SQUISH_CONSTANT_3D;
                        dzExt1 = dz0 - 2 * SQUISH_CONSTANT_3D;
                        xsvExt1 = xsb;
                        ysvExt1 = ysb + 2;
                        zsvExt1 = zsb;
                    } else {
                        dxExt1 = dx0 - 2 * SQUISH_CONSTANT_3D;
                        dyExt1 = dy0 - 2 * SQUISH_CONSTANT_3D;
                        dzExt1 = dz0 - 2 - 2 * SQUISH_CONSTANT_3D;
                        xsvExt1 = xsb;
                        ysvExt1 = ysb;
                        zsvExt1 = zsb + 2;
                    }
                } else {//Both closest points on (0,0,0) side

                    //One of the two extra points is (0,0,0)
                    dxExt0 = dx0;
                    dyExt0 = dy0;
                    dzExt0 = dz0;
                    xsvExt0 = xsb;
                    ysvExt0 = ysb;
                    zsvExt0 = zsb;

                    //Other extra point is based on the omitted axis.
                    byte c = (byte) (aPoint | bPoint);
                    if ((c & 0x01) == 0) {
                        dxExt1 = dx0 + 1 - SQUISH_CONSTANT_3D;
                        dyExt1 = dy0 - 1 - SQUISH_CONSTANT_3D;
                        dzExt1 = dz0 - 1 - SQUISH_CONSTANT_3D;
                        xsvExt1 = xsb - 1;
                        ysvExt1 = ysb + 1;
                        zsvExt1 = zsb + 1;
                    } else if ((c & 0x02) == 0) {
                        dxExt1 = dx0 - 1 - SQUISH_CONSTANT_3D;
                        dyExt1 = dy0 + 1 - SQUISH_CONSTANT_3D;
                        dzExt1 = dz0 - 1 - SQUISH_CONSTANT_3D;
                        xsvExt1 = xsb + 1;
                        ysvExt1 = ysb - 1;
                        zsvExt1 = zsb + 1;
                    } else {
                        dxExt1 = dx0 - 1 - SQUISH_CONSTANT_3D;
                        dyExt1 = dy0 - 1 - SQUISH_CONSTANT_3D;
                        dzExt1 = dz0 + 1 - SQUISH_CONSTANT_3D;
                        xsvExt1 = xsb + 1;
                        ysvExt1 = ysb + 1;
                        zsvExt1 = zsb - 1;
                    }
                }
            } else { //One point on (0,0,0) side, one point on (1,1,1) side
                byte c1;
                byte c2;
                if (aIsFurtherSide) {
                    c1 = aPoint;
                    c2 = bPoint;
                } else {
                    c1 = bPoint;
                    c2 = aPoint;
                }

                //One contribution is a permutation of (1,1,-1)
                if ((c1 & 0x01) == 0) {
                    dxExt0 = dx0 + 1 - SQUISH_CONSTANT_3D;
                    dyExt0 = dy0 - 1 - SQUISH_CONSTANT_3D;
                    dzExt0 = dz0 - 1 - SQUISH_CONSTANT_3D;
                    xsvExt0 = xsb - 1;
                    ysvExt0 = ysb + 1;
                    zsvExt0 = zsb + 1;
                } else if ((c1 & 0x02) == 0) {
                    dxExt0 = dx0 - 1 - SQUISH_CONSTANT_3D;
                    dyExt0 = dy0 + 1 - SQUISH_CONSTANT_3D;
                    dzExt0 = dz0 - 1 - SQUISH_CONSTANT_3D;
                    xsvExt0 = xsb + 1;
                    ysvExt0 = ysb - 1;
                    zsvExt0 = zsb + 1;
                } else {
                    dxExt0 = dx0 - 1 - SQUISH_CONSTANT_3D;
                    dyExt0 = dy0 - 1 - SQUISH_CONSTANT_3D;
                    dzExt0 = dz0 + 1 - SQUISH_CONSTANT_3D;
                    xsvExt0 = xsb + 1;
                    ysvExt0 = ysb + 1;
                    zsvExt0 = zsb - 1;
                }

                //One contribution is a permutation of (0,0,2)
                dxExt1 = dx0 - 2 * SQUISH_CONSTANT_3D;
                dyExt1 = dy0 - 2 * SQUISH_CONSTANT_3D;
                dzExt1 = dz0 - 2 * SQUISH_CONSTANT_3D;
                xsvExt1 = xsb;
                ysvExt1 = ysb;
                zsvExt1 = zsb;
                if ((c2 & 0x01) != 0) {
                    dxExt1 -= 2;
                    xsvExt1 += 2;
                } else if ((c2 & 0x02) != 0) {
                    dyExt1 -= 2;
                    ysvExt1 += 2;
                } else {
                    dzExt1 -= 2;
                    zsvExt1 += 2;
                }
            }

            //Contribution (1,0,0)
            double dx1 = dx0 - 1 - SQUISH_CONSTANT_3D;
            double dy1 = dy0 - 0 - SQUISH_CONSTANT_3D;
            double dz1 = dz0 - 0 - SQUISH_CONSTANT_3D;
            double attn1 = 2 - dx1 * dx1 - dy1 * dy1 - dz1 * dz1;
            if (attn1 > 0) {
                attn1 *= attn1;
                value += attn1 * attn1 * extrapolate(xsb + 1, ysb, zsb, dx1, dy1, dz1);
            }

            //Contribution (0,1,0)
            double dx2 = dx0 - 0 - SQUISH_CONSTANT_3D;
            double dy2 = dy0 - 1 - SQUISH_CONSTANT_3D;
            double attn2 = 2 - dx2 * dx2 - dy2 * dy2 - dz1 * dz1;
            if (attn2 > 0) {
                attn2 *= attn2;
                value += attn2 * attn2 * extrapolate(xsb, ysb + 1, zsb, dx2, dy2, dz1);
            }

            //Contribution (0,0,1)
            double dz3 = dz0 - 1 - SQUISH_CONSTANT_3D;
            double attn3 = 2 - dx2 * dx2 - dy1 * dy1 - dz3 * dz3;
            if (attn3 > 0) {
                attn3 *= attn3;
                value += attn3 * attn3 * extrapolate(xsb, ysb, zsb + 1, dx2, dy1, dz3);
            }

            //Contribution (1,1,0)
            double dx4 = dx0 - 1 - 2 * SQUISH_CONSTANT_3D;
            double dy4 = dy0 - 1 - 2 * SQUISH_CONSTANT_3D;
            double dz4 = dz0 - 0 - 2 * SQUISH_CONSTANT_3D;
            double attn4 = 2 - dx4 * dx4 - dy4 * dy4 - dz4 * dz4;
            if (attn4 > 0) {
                attn4 *= attn4;
                value += attn4 * attn4 * extrapolate(xsb + 1, ysb + 1, zsb, dx4, dy4, dz4);
            }

            //Contribution (1,0,1)
            double dy5 = dy0 - 0 - 2 * SQUISH_CONSTANT_3D;
            double dz5 = dz0 - 1 - 2 * SQUISH_CONSTANT_3D;
            double attn5 = 2 - dx4 * dx4 - dy5 * dy5 - dz5 * dz5;
            if (attn5 > 0) {
                attn5 *= attn5;
                value += attn5 * attn5 * extrapolate(xsb + 1, ysb, zsb + 1, dx4, dy5, dz5);
            }

            //Contribution (0,1,1)
            double dx6 = dx0 - 0 - 2 * SQUISH_CONSTANT_3D;
            double attn6 = 2 - dx6 * dx6 - dy4 * dy4 - dz5 * dz5;
            if (attn6 > 0) {
                attn6 *= attn6;
                value += attn6 * attn6 * extrapolate(xsb, ysb + 1, zsb + 1, dx6, dy4, dz5);
            }
        }

        //First extra vertex
        double attnExt0 = 2 - dxExt0 * dxExt0 - dyExt0 * dyExt0 - dzExt0 * dzExt0;
        if (attnExt0 > 0) {
            attnExt0 *= attnExt0;
            value += attnExt0 * attnExt0 * extrapolate(xsvExt0, ysvExt0, zsvExt0, dxExt0, dyExt0, dzExt0);
        }

        //Second extra vertex
        double attnExt1 = 2 - dxExt1 * dxExt1 - dyExt1 * dyExt1 - dzExt1 * dzExt1;
        if (attnExt1 > 0) {
            attnExt1 *= attnExt1;
            value += attnExt1 * attnExt1 * extrapolate(xsvExt1, ysvExt1, zsvExt1, dxExt1, dyExt1, dzExt1);
        }

        return value / NORM_CONSTANT_3D;
    }

    //4D OpenSimplex Noise.
    protected double eval(double x, double y, double z, double w) {

        //Place input coordinates on simplectic honeycomb.
        double stretchOffset = (x + y + z + w) * STRETCH_CONSTANT_4D;
        double xs = x + stretchOffset;
        double ys = y + stretchOffset;
        double zs = z + stretchOffset;
        double ws = w + stretchOffset;

        //Floor to get simplectic honeycomb coordinates of rhombo-hypercube super-cell origin.
        int xsb = fastFloor(xs);
        int ysb = fastFloor(ys);
        int zsb = fastFloor(zs);
        int wsb = fastFloor(ws);

        //Skew out to get actual coordinates of stretched rhombo-hypercube origin. We'll need these later.
        double squishOffset = (xsb + ysb + zsb + wsb) * SQUISH_CONSTANT_4D;
        double xb = xsb + squishOffset;
        double yb = ysb + squishOffset;
        double zb = zsb + squishOffset;
        double wb = wsb + squishOffset;

        //Compute simplectic honeycomb coordinates relative to rhombo-hypercube origin.
        double xins = xs - xsb;
        double yins = ys - ysb;
        double zins = zs - zsb;
        double wins = ws - wsb;

        //Sum those together to get a value that determines which region we're in.
        double inSum = xins + yins + zins + wins;

        //Positions relative to origin point.
        double dx0 = x - xb;
        double dy0 = y - yb;
        double dz0 = z - zb;
        double dw0 = w - wb;

        //We'll be defining these inside the next block and using them afterwards.
        double dxExt0, dyExt0, dzExt0, dw_ext0;
        double dxExt1, dyExt1, dzExt1, dw_ext1;
        double dxExt2, dyExt2, dz_ext2, dw_ext2;
        int xsvExt0, ysvExt0, zsvExt0, wsvExt0;
        int xsvExt1, ysvExt1, zsvExt1, wsvExt1;
        int xsvExt2, ysvExt2, zsv_ext2, wsv_ext2;

        double value = 0;
        if (inSum <= 1) { //We're inside the pentachoron (4-Simplex) at (0,0,0,0)

            //Determine which two of (0,0,0,1), (0,0,1,0), (0,1,0,0), (1,0,0,0) are closest.
            byte aPoint = 0x01;
            double aScore = xins;
            byte bPoint = 0x02;
            double bScore = yins;
            if (aScore >= bScore && zins > bScore) {
                bScore = zins;
                bPoint = 0x04;
            } else if (aScore < bScore && zins > aScore) {
                aScore = zins;
                aPoint = 0x04;
            }
            if (aScore >= bScore && wins > bScore) {
                bScore = wins;
                bPoint = 0x08;
            } else if (aScore < bScore && wins > aScore) {
                aScore = wins;
                aPoint = 0x08;
            }

            //Now we determine the three lattice points not part of the pentachoron that may contribute.
            //This depends on the closest two pentachoron vertices, including (0,0,0,0)
            double uins = 1 - inSum;
            if (uins > aScore || uins > bScore) { //(0,0,0,0) is one of the closest two pentachoron vertices.
                byte c = (bScore > aScore ? bPoint : aPoint); //Our other closest vertex is the closest out of a and b.
                if ((c & 0x01) == 0) {
                    xsvExt0 = xsb - 1;
                    xsvExt1 = xsvExt2 = xsb;
                    dxExt0 = dx0 + 1;
                    dxExt1 = dxExt2 = dx0;
                } else {
                    xsvExt0 = xsvExt1 = xsvExt2 = xsb + 1;
                    dxExt0 = dxExt1 = dxExt2 = dx0 - 1;
                }

                if ((c & 0x02) == 0) {
                    ysvExt0 = ysvExt1 = ysvExt2 = ysb;
                    dyExt0 = dyExt1 = dyExt2 = dy0;
                    if ((c & 0x01) == 0x01) {
                        ysvExt0 -= 1;
                        dyExt0 += 1;
                    } else {
                        ysvExt1 -= 1;
                        dyExt1 += 1;
                    }
                } else {
                    ysvExt0 = ysvExt1 = ysvExt2 = ysb + 1;
                    dyExt0 = dyExt1 = dyExt2 = dy0 - 1;
                }

                if ((c & 0x04) == 0) {
                    zsvExt0 = zsvExt1 = zsv_ext2 = zsb;
                    dzExt0 = dzExt1 = dz_ext2 = dz0;
                    if ((c & 0x03) != 0) {
                        if ((c & 0x03) == 0x03) {
                            zsvExt0 -= 1;
                            dzExt0 += 1;
                        } else {
                            zsvExt1 -= 1;
                            dzExt1 += 1;
                        }
                    } else {
                        zsv_ext2 -= 1;
                        dz_ext2 += 1;
                    }
                } else {
                    zsvExt0 = zsvExt1 = zsv_ext2 = zsb + 1;
                    dzExt0 = dzExt1 = dz_ext2 = dz0 - 1;
                }

                if ((c & 0x08) == 0) {
                    wsvExt0 = wsvExt1 = wsb;
                    wsv_ext2 = wsb - 1;
                    dw_ext0 = dw_ext1 = dw0;
                    dw_ext2 = dw0 + 1;
                } else {
                    wsvExt0 = wsvExt1 = wsv_ext2 = wsb + 1;
                    dw_ext0 = dw_ext1 = dw_ext2 = dw0 - 1;
                }
            } else { //(0,0,0,0) is not one of the closest two pentachoron vertices.
                byte c = (byte) (aPoint | bPoint); //Our three extra vertices are determined by the closest two.

                if ((c & 0x01) == 0) {
                    xsvExt0 = xsvExt2 = xsb;
                    xsvExt1 = xsb - 1;
                    dxExt0 = dx0 - 2 * SQUISH_CONSTANT_4D;
                    dxExt1 = dx0 + 1 - SQUISH_CONSTANT_4D;
                    dxExt2 = dx0 - SQUISH_CONSTANT_4D;
                } else {
                    xsvExt0 = xsvExt1 = xsvExt2 = xsb + 1;
                    dxExt0 = dx0 - 1 - 2 * SQUISH_CONSTANT_4D;
                    dxExt1 = dxExt2 = dx0 - 1 - SQUISH_CONSTANT_4D;
                }

                if ((c & 0x02) == 0) {
                    ysvExt0 = ysvExt1 = ysvExt2 = ysb;
                    dyExt0 = dy0 - 2 * SQUISH_CONSTANT_4D;
                    dyExt1 = dyExt2 = dy0 - SQUISH_CONSTANT_4D;
                    if ((c & 0x01) == 0x01) {
                        ysvExt1 -= 1;
                        dyExt1 += 1;
                    } else {
                        ysvExt2 -= 1;
                        dyExt2 += 1;
                    }
                } else {
                    ysvExt0 = ysvExt1 = ysvExt2 = ysb + 1;
                    dyExt0 = dy0 - 1 - 2 * SQUISH_CONSTANT_4D;
                    dyExt1 = dyExt2 = dy0 - 1 - SQUISH_CONSTANT_4D;
                }

                if ((c & 0x04) == 0) {
                    zsvExt0 = zsvExt1 = zsv_ext2 = zsb;
                    dzExt0 = dz0 - 2 * SQUISH_CONSTANT_4D;
                    dzExt1 = dz_ext2 = dz0 - SQUISH_CONSTANT_4D;
                    if ((c & 0x03) == 0x03) {
                        zsvExt1 -= 1;
                        dzExt1 += 1;
                    } else {
                        zsv_ext2 -= 1;
                        dz_ext2 += 1;
                    }
                } else {
                    zsvExt0 = zsvExt1 = zsv_ext2 = zsb + 1;
                    dzExt0 = dz0 - 1 - 2 * SQUISH_CONSTANT_4D;
                    dzExt1 = dz_ext2 = dz0 - 1 - SQUISH_CONSTANT_4D;
                }

                if ((c & 0x08) == 0) {
                    wsvExt0 = wsvExt1 = wsb;
                    wsv_ext2 = wsb - 1;
                    dw_ext0 = dw0 - 2 * SQUISH_CONSTANT_4D;
                    dw_ext1 = dw0 - SQUISH_CONSTANT_4D;
                    dw_ext2 = dw0 + 1 - SQUISH_CONSTANT_4D;
                } else {
                    wsvExt0 = wsvExt1 = wsv_ext2 = wsb + 1;
                    dw_ext0 = dw0 - 1 - 2 * SQUISH_CONSTANT_4D;
                    dw_ext1 = dw_ext2 = dw0 - 1 - SQUISH_CONSTANT_4D;
                }
            }

            //Contribution (0,0,0,0)
            double attn0 = 2 - dx0 * dx0 - dy0 * dy0 - dz0 * dz0 - dw0 * dw0;
            if (attn0 > 0) {
                attn0 *= attn0;
                value += attn0 * attn0 * extrapolate(xsb, ysb, zsb, wsb, dx0, dy0, dz0, dw0);
            }

            //Contribution (1,0,0,0)
            double dx1 = dx0 - 1 - SQUISH_CONSTANT_4D;
            double dy1 = dy0 - 0 - SQUISH_CONSTANT_4D;
            double dz1 = dz0 - 0 - SQUISH_CONSTANT_4D;
            double dw1 = dw0 - 0 - SQUISH_CONSTANT_4D;
            double attn1 = 2 - dx1 * dx1 - dy1 * dy1 - dz1 * dz1 - dw1 * dw1;
            if (attn1 > 0) {
                attn1 *= attn1;
                value += attn1 * attn1 * extrapolate(xsb + 1, ysb, zsb, wsb, dx1, dy1, dz1, dw1);
            }

            //Contribution (0,1,0,0)
            double dx2 = dx0 - 0 - SQUISH_CONSTANT_4D;
            double dy2 = dy0 - 1 - SQUISH_CONSTANT_4D;
            double attn2 = 2 - dx2 * dx2 - dy2 * dy2 - dz1 * dz1 - dw1 * dw1;
            if (attn2 > 0) {
                attn2 *= attn2;
                value += attn2 * attn2 * extrapolate(xsb, ysb + 1, zsb, wsb, dx2, dy2, dz1, dw1);
            }

            //Contribution (0,0,1,0)
            double dz3 = dz0 - 1 - SQUISH_CONSTANT_4D;
            double attn3 = 2 - dx2 * dx2 - dy1 * dy1 - dz3 * dz3 - dw1 * dw1;
            if (attn3 > 0) {
                attn3 *= attn3;
                value += attn3 * attn3 * extrapolate(xsb, ysb, zsb + 1, wsb, dx2, dy1, dz3, dw1);
            }

            //Contribution (0,0,0,1)
            double dw4 = dw0 - 1 - SQUISH_CONSTANT_4D;
            double attn4 = 2 - dx2 * dx2 - dy1 * dy1 - dz1 * dz1 - dw4 * dw4;
            if (attn4 > 0) {
                attn4 *= attn4;
                value += attn4 * attn4 * extrapolate(xsb, ysb, zsb, wsb + 1, dx2, dy1, dz1, dw4);
            }
        } else if (inSum >= 3) { //We're inside the pentachoron (4-Simplex) at (1,1,1,1)
            //Determine which two of (1,1,1,0), (1,1,0,1), (1,0,1,1), (0,1,1,1) are closest.
            byte aPoint = 0x0E;
            double aScore = xins;
            byte bPoint = 0x0D;
            double bScore = yins;
            if (aScore <= bScore && zins < bScore) {
                bScore = zins;
                bPoint = 0x0B;
            } else if (aScore > bScore && zins < aScore) {
                aScore = zins;
                aPoint = 0x0B;
            }
            if (aScore <= bScore && wins < bScore) {
                bScore = wins;
                bPoint = 0x07;
            } else if (aScore > bScore && wins < aScore) {
                aScore = wins;
                aPoint = 0x07;
            }

            //Now we determine the three lattice points not part of the pentachoron that may contribute.
            //This depends on the closest two pentachoron vertices, including (0,0,0,0)
            double uins = 4 - inSum;
            if (uins < aScore || uins < bScore) { //(1,1,1,1) is one of the closest two pentachoron vertices.
                byte c = (bScore < aScore ? bPoint : aPoint); //Our other closest vertex is the closest out of a and b.

                if ((c & 0x01) != 0) {
                    xsvExt0 = xsb + 2;
                    xsvExt1 = xsvExt2 = xsb + 1;
                    dxExt0 = dx0 - 2 - 4 * SQUISH_CONSTANT_4D;
                    dxExt1 = dxExt2 = dx0 - 1 - 4 * SQUISH_CONSTANT_4D;
                } else {
                    xsvExt0 = xsvExt1 = xsvExt2 = xsb;
                    dxExt0 = dxExt1 = dxExt2 = dx0 - 4 * SQUISH_CONSTANT_4D;
                }

                if ((c & 0x02) != 0) {
                    ysvExt0 = ysvExt1 = ysvExt2 = ysb + 1;
                    dyExt0 = dyExt1 = dyExt2 = dy0 - 1 - 4 * SQUISH_CONSTANT_4D;
                    if ((c & 0x01) != 0) {
                        ysvExt1 += 1;
                        dyExt1 -= 1;
                    } else {
                        ysvExt0 += 1;
                        dyExt0 -= 1;
                    }
                } else {
                    ysvExt0 = ysvExt1 = ysvExt2 = ysb;
                    dyExt0 = dyExt1 = dyExt2 = dy0 - 4 * SQUISH_CONSTANT_4D;
                }

                if ((c & 0x04) != 0) {
                    zsvExt0 = zsvExt1 = zsv_ext2 = zsb + 1;
                    dzExt0 = dzExt1 = dz_ext2 = dz0 - 1 - 4 * SQUISH_CONSTANT_4D;
                    if ((c & 0x03) != 0x03) {
                        if ((c & 0x03) == 0) {
                            zsvExt0 += 1;
                            dzExt0 -= 1;
                        } else {
                            zsvExt1 += 1;
                            dzExt1 -= 1;
                        }
                    } else {
                        zsv_ext2 += 1;
                        dz_ext2 -= 1;
                    }
                } else {
                    zsvExt0 = zsvExt1 = zsv_ext2 = zsb;
                    dzExt0 = dzExt1 = dz_ext2 = dz0 - 4 * SQUISH_CONSTANT_4D;
                }

                if ((c & 0x08) != 0) {
                    wsvExt0 = wsvExt1 = wsb + 1;
                    wsv_ext2 = wsb + 2;
                    dw_ext0 = dw_ext1 = dw0 - 1 - 4 * SQUISH_CONSTANT_4D;
                    dw_ext2 = dw0 - 2 - 4 * SQUISH_CONSTANT_4D;
                } else {
                    wsvExt0 = wsvExt1 = wsv_ext2 = wsb;
                    dw_ext0 = dw_ext1 = dw_ext2 = dw0 - 4 * SQUISH_CONSTANT_4D;
                }
            } else { //(1,1,1,1) is not one of the closest two pentachoron vertices.
                byte c = (byte) (aPoint & bPoint); //Our three extra vertices are determined by the closest two.

                if ((c & 0x01) != 0) {
                    xsvExt0 = xsvExt2 = xsb + 1;
                    xsvExt1 = xsb + 2;
                    dxExt0 = dx0 - 1 - 2 * SQUISH_CONSTANT_4D;
                    dxExt1 = dx0 - 2 - 3 * SQUISH_CONSTANT_4D;
                    dxExt2 = dx0 - 1 - 3 * SQUISH_CONSTANT_4D;
                } else {
                    xsvExt0 = xsvExt1 = xsvExt2 = xsb;
                    dxExt0 = dx0 - 2 * SQUISH_CONSTANT_4D;
                    dxExt1 = dxExt2 = dx0 - 3 * SQUISH_CONSTANT_4D;
                }

                if ((c & 0x02) != 0) {
                    ysvExt0 = ysvExt1 = ysvExt2 = ysb + 1;
                    dyExt0 = dy0 - 1 - 2 * SQUISH_CONSTANT_4D;
                    dyExt1 = dyExt2 = dy0 - 1 - 3 * SQUISH_CONSTANT_4D;
                    if ((c & 0x01) != 0) {
                        ysvExt2 += 1;
                        dyExt2 -= 1;
                    } else {
                        ysvExt1 += 1;
                        dyExt1 -= 1;
                    }
                } else {
                    ysvExt0 = ysvExt1 = ysvExt2 = ysb;
                    dyExt0 = dy0 - 2 * SQUISH_CONSTANT_4D;
                    dyExt1 = dyExt2 = dy0 - 3 * SQUISH_CONSTANT_4D;
                }

                if ((c & 0x04) != 0) {
                    zsvExt0 = zsvExt1 = zsv_ext2 = zsb + 1;
                    dzExt0 = dz0 - 1 - 2 * SQUISH_CONSTANT_4D;
                    dzExt1 = dz_ext2 = dz0 - 1 - 3 * SQUISH_CONSTANT_4D;
                    if ((c & 0x03) != 0) {
                        zsv_ext2 += 1;
                        dz_ext2 -= 1;
                    } else {
                        zsvExt1 += 1;
                        dzExt1 -= 1;
                    }
                } else {
                    zsvExt0 = zsvExt1 = zsv_ext2 = zsb;
                    dzExt0 = dz0 - 2 * SQUISH_CONSTANT_4D;
                    dzExt1 = dz_ext2 = dz0 - 3 * SQUISH_CONSTANT_4D;
                }

                if ((c & 0x08) != 0) {
                    wsvExt0 = wsvExt1 = wsb + 1;
                    wsv_ext2 = wsb + 2;
                    dw_ext0 = dw0 - 1 - 2 * SQUISH_CONSTANT_4D;
                    dw_ext1 = dw0 - 1 - 3 * SQUISH_CONSTANT_4D;
                    dw_ext2 = dw0 - 2 - 3 * SQUISH_CONSTANT_4D;
                } else {
                    wsvExt0 = wsvExt1 = wsv_ext2 = wsb;
                    dw_ext0 = dw0 - 2 * SQUISH_CONSTANT_4D;
                    dw_ext1 = dw_ext2 = dw0 - 3 * SQUISH_CONSTANT_4D;
                }
            }

            //Contribution (1,1,1,0)
            double dx4 = dx0 - 1 - 3 * SQUISH_CONSTANT_4D;
            double dy4 = dy0 - 1 - 3 * SQUISH_CONSTANT_4D;
            double dz4 = dz0 - 1 - 3 * SQUISH_CONSTANT_4D;
            double dw4 = dw0 - 3 * SQUISH_CONSTANT_4D;
            double attn4 = 2 - dx4 * dx4 - dy4 * dy4 - dz4 * dz4 - dw4 * dw4;
            if (attn4 > 0) {
                attn4 *= attn4;
                value += attn4 * attn4 * extrapolate(xsb + 1, ysb + 1, zsb + 1, wsb, dx4, dy4, dz4, dw4);
            }

            //Contribution (1,1,0,1)
            double dz3 = dz0 - 3 * SQUISH_CONSTANT_4D;
            double dw3 = dw0 - 1 - 3 * SQUISH_CONSTANT_4D;
            double attn3 = 2 - dx4 * dx4 - dy4 * dy4 - dz3 * dz3 - dw3 * dw3;
            if (attn3 > 0) {
                attn3 *= attn3;
                value += attn3 * attn3 * extrapolate(xsb + 1, ysb + 1, zsb, wsb + 1, dx4, dy4, dz3, dw3);
            }

            //Contribution (1,0,1,1)
            double dy2 = dy0 - 3 * SQUISH_CONSTANT_4D;
            double attn2 = 2 - dx4 * dx4 - dy2 * dy2 - dz4 * dz4 - dw3 * dw3;
            if (attn2 > 0) {
                attn2 *= attn2;
                value += attn2 * attn2 * extrapolate(xsb + 1, ysb, zsb + 1, wsb + 1, dx4, dy2, dz4, dw3);
            }

            //Contribution (0,1,1,1)
            double dx1 = dx0 - 3 * SQUISH_CONSTANT_4D;
            double attn1 = 2 - dx1 * dx1 - dy4 * dy4 - dz4 * dz4 - dw3 * dw3;
            if (attn1 > 0) {
                attn1 *= attn1;
                value += attn1 * attn1 * extrapolate(xsb, ysb + 1, zsb + 1, wsb + 1, dx1, dy4, dz4, dw3);
            }

            //Contribution (1,1,1,1)
            dx0 = dx0 - 1 - 4 * SQUISH_CONSTANT_4D;
            dy0 = dy0 - 1 - 4 * SQUISH_CONSTANT_4D;
            dz0 = dz0 - 1 - 4 * SQUISH_CONSTANT_4D;
            dw0 = dw0 - 1 - 4 * SQUISH_CONSTANT_4D;
            double attn0 = 2 - dx0 * dx0 - dy0 * dy0 - dz0 * dz0 - dw0 * dw0;
            if (attn0 > 0) {
                attn0 *= attn0;
                value += attn0 * attn0 * extrapolate(xsb + 1, ysb + 1, zsb + 1, wsb + 1, dx0, dy0, dz0, dw0);
            }
        } else if (inSum <= 2) { //We're inside the first dispentachoron (Rectified 4-Simplex)
            double aScore;
            byte aPoint;
            boolean aIsBiggerSide = true;
            double bScore;
            byte bPoint;
            boolean bIsBiggerSide = true;

            //Decide between (1,1,0,0) and (0,0,1,1)
            if (xins + yins > zins + wins) {
                aScore = xins + yins;
                aPoint = 0x03;
            } else {
                aScore = zins + wins;
                aPoint = 0x0C;
            }

            //Decide between (1,0,1,0) and (0,1,0,1)
            if (xins + zins > yins + wins) {
                bScore = xins + zins;
                bPoint = 0x05;
            } else {
                bScore = yins + wins;
                bPoint = 0x0A;
            }

            //Closer between (1,0,0,1) and (0,1,1,0) will replace the further of a and b, if closer.
            if (xins + wins > yins + zins) {
                double score = xins + wins;
                if (aScore >= bScore && score > bScore) {
                    bScore = score;
                    bPoint = 0x09;
                } else if (aScore < bScore && score > aScore) {
                    aScore = score;
                    aPoint = 0x09;
                }
            } else {
                double score = yins + zins;
                if (aScore >= bScore && score > bScore) {
                    bScore = score;
                    bPoint = 0x06;
                } else if (aScore < bScore && score > aScore) {
                    aScore = score;
                    aPoint = 0x06;
                }
            }

            double p1 = 2 - inSum + xins;
            if (aScore >= bScore && p1 > bScore) {
                bScore = p1;
                bPoint = 0x01;
                bIsBiggerSide = false;
            } else if (aScore < bScore && p1 > aScore) {
                aScore = p1;
                aPoint = 0x01;
                aIsBiggerSide = false;
            }

            double p2 = 2 - inSum + yins;
            if (aScore >= bScore && p2 > bScore) {
                bScore = p2;
                bPoint = 0x02;
                bIsBiggerSide = false;
            } else if (aScore < bScore && p2 > aScore) {
                aScore = p2;
                aPoint = 0x02;
                aIsBiggerSide = false;
            }

            double p3 = 2 - inSum + zins;
            if (aScore >= bScore && p3 > bScore) {
                bScore = p3;
                bPoint = 0x04;
                bIsBiggerSide = false;
            } else if (aScore < bScore && p3 > aScore) {
                aScore = p3;
                aPoint = 0x04;
                aIsBiggerSide = false;
            }

            double p4 = 2 - inSum + wins;
            if (aScore >= bScore && p4 > bScore) {
                bPoint = 0x08;
                bIsBiggerSide = false;
            } else if (aScore < bScore && p4 > aScore) {
                aPoint = 0x08;
                aIsBiggerSide = false;
            }

            //Where each of the two closest points are determines how the extra three vertices are calculated.
            if (aIsBiggerSide == bIsBiggerSide) {
                if (aIsBiggerSide) { //Both closest points on the bigger side
                    byte c1 = (byte) (aPoint | bPoint);
                    byte c2 = (byte) (aPoint & bPoint);
                    if ((c1 & 0x01) == 0) {
                        xsvExt0 = xsb;
                        xsvExt1 = xsb - 1;
                        dxExt0 = dx0 - 3 * SQUISH_CONSTANT_4D;
                        dxExt1 = dx0 + 1 - 2 * SQUISH_CONSTANT_4D;
                    } else {
                        xsvExt0 = xsvExt1 = xsb + 1;
                        dxExt0 = dx0 - 1 - 3 * SQUISH_CONSTANT_4D;
                        dxExt1 = dx0 - 1 - 2 * SQUISH_CONSTANT_4D;
                    }

                    if ((c1 & 0x02) == 0) {
                        ysvExt0 = ysb;
                        ysvExt1 = ysb - 1;
                        dyExt0 = dy0 - 3 * SQUISH_CONSTANT_4D;
                        dyExt1 = dy0 + 1 - 2 * SQUISH_CONSTANT_4D;
                    } else {
                        ysvExt0 = ysvExt1 = ysb + 1;
                        dyExt0 = dy0 - 1 - 3 * SQUISH_CONSTANT_4D;
                        dyExt1 = dy0 - 1 - 2 * SQUISH_CONSTANT_4D;
                    }

                    if ((c1 & 0x04) == 0) {
                        zsvExt0 = zsb;
                        zsvExt1 = zsb - 1;
                        dzExt0 = dz0 - 3 * SQUISH_CONSTANT_4D;
                        dzExt1 = dz0 + 1 - 2 * SQUISH_CONSTANT_4D;
                    } else {
                        zsvExt0 = zsvExt1 = zsb + 1;
                        dzExt0 = dz0 - 1 - 3 * SQUISH_CONSTANT_4D;
                        dzExt1 = dz0 - 1 - 2 * SQUISH_CONSTANT_4D;
                    }

                    if ((c1 & 0x08) == 0) {
                        wsvExt0 = wsb;
                        wsvExt1 = wsb - 1;
                        dw_ext0 = dw0 - 3 * SQUISH_CONSTANT_4D;
                        dw_ext1 = dw0 + 1 - 2 * SQUISH_CONSTANT_4D;
                    } else {
                        wsvExt0 = wsvExt1 = wsb + 1;
                        dw_ext0 = dw0 - 1 - 3 * SQUISH_CONSTANT_4D;
                        dw_ext1 = dw0 - 1 - 2 * SQUISH_CONSTANT_4D;
                    }

                    //One combination is a permutation of (0,0,0,2) based on c2
                    xsvExt2 = xsb;
                    ysvExt2 = ysb;
                    zsv_ext2 = zsb;
                    wsv_ext2 = wsb;
                    dxExt2 = dx0 - 2 * SQUISH_CONSTANT_4D;
                    dyExt2 = dy0 - 2 * SQUISH_CONSTANT_4D;
                    dz_ext2 = dz0 - 2 * SQUISH_CONSTANT_4D;
                    dw_ext2 = dw0 - 2 * SQUISH_CONSTANT_4D;
                    if ((c2 & 0x01) != 0) {
                        xsvExt2 += 2;
                        dxExt2 -= 2;
                    } else if ((c2 & 0x02) != 0) {
                        ysvExt2 += 2;
                        dyExt2 -= 2;
                    } else if ((c2 & 0x04) != 0) {
                        zsv_ext2 += 2;
                        dz_ext2 -= 2;
                    } else {
                        wsv_ext2 += 2;
                        dw_ext2 -= 2;
                    }

                } else { //Both closest points on the smaller side
                    //One of the two extra points is (0,0,0,0)
                    xsvExt2 = xsb;
                    ysvExt2 = ysb;
                    zsv_ext2 = zsb;
                    wsv_ext2 = wsb;
                    dxExt2 = dx0;
                    dyExt2 = dy0;
                    dz_ext2 = dz0;
                    dw_ext2 = dw0;

                    //Other two points are based on the omitted axes.
                    byte c = (byte) (aPoint | bPoint);

                    if ((c & 0x01) == 0) {
                        xsvExt0 = xsb - 1;
                        xsvExt1 = xsb;
                        dxExt0 = dx0 + 1 - SQUISH_CONSTANT_4D;
                        dxExt1 = dx0 - SQUISH_CONSTANT_4D;
                    } else {
                        xsvExt0 = xsvExt1 = xsb + 1;
                        dxExt0 = dxExt1 = dx0 - 1 - SQUISH_CONSTANT_4D;
                    }

                    if ((c & 0x02) == 0) {
                        ysvExt0 = ysvExt1 = ysb;
                        dyExt0 = dyExt1 = dy0 - SQUISH_CONSTANT_4D;
                        if ((c & 0x01) == 0x01) {
                            ysvExt0 -= 1;
                            dyExt0 += 1;
                        } else {
                            ysvExt1 -= 1;
                            dyExt1 += 1;
                        }
                    } else {
                        ysvExt0 = ysvExt1 = ysb + 1;
                        dyExt0 = dyExt1 = dy0 - 1 - SQUISH_CONSTANT_4D;
                    }

                    if ((c & 0x04) == 0) {
                        zsvExt0 = zsvExt1 = zsb;
                        dzExt0 = dzExt1 = dz0 - SQUISH_CONSTANT_4D;
                        if ((c & 0x03) == 0x03) {
                            zsvExt0 -= 1;
                            dzExt0 += 1;
                        } else {
                            zsvExt1 -= 1;
                            dzExt1 += 1;
                        }
                    } else {
                        zsvExt0 = zsvExt1 = zsb + 1;
                        dzExt0 = dzExt1 = dz0 - 1 - SQUISH_CONSTANT_4D;
                    }

                    if ((c & 0x08) == 0) {
                        wsvExt0 = wsb;
                        wsvExt1 = wsb - 1;
                        dw_ext0 = dw0 - SQUISH_CONSTANT_4D;
                        dw_ext1 = dw0 + 1 - SQUISH_CONSTANT_4D;
                    } else {
                        wsvExt0 = wsvExt1 = wsb + 1;
                        dw_ext0 = dw_ext1 = dw0 - 1 - SQUISH_CONSTANT_4D;
                    }

                }
            } else { //One point on each "side"
                byte c1;
                byte c2;
                if (aIsBiggerSide) {
                    c1 = aPoint;
                    c2 = bPoint;
                } else {
                    c1 = bPoint;
                    c2 = aPoint;
                }

                //Two contributions are the bigger-sided point with each 0 replaced with -1.
                if ((c1 & 0x01) == 0) {
                    xsvExt0 = xsb - 1;
                    xsvExt1 = xsb;
                    dxExt0 = dx0 + 1 - SQUISH_CONSTANT_4D;
                    dxExt1 = dx0 - SQUISH_CONSTANT_4D;
                } else {
                    xsvExt0 = xsvExt1 = xsb + 1;
                    dxExt0 = dxExt1 = dx0 - 1 - SQUISH_CONSTANT_4D;
                }

                if ((c1 & 0x02) == 0) {
                    ysvExt0 = ysvExt1 = ysb;
                    dyExt0 = dyExt1 = dy0 - SQUISH_CONSTANT_4D;
                    if ((c1 & 0x01) == 0x01) {
                        ysvExt0 -= 1;
                        dyExt0 += 1;
                    } else {
                        ysvExt1 -= 1;
                        dyExt1 += 1;
                    }
                } else {
                    ysvExt0 = ysvExt1 = ysb + 1;
                    dyExt0 = dyExt1 = dy0 - 1 - SQUISH_CONSTANT_4D;
                }

                if ((c1 & 0x04) == 0) {
                    zsvExt0 = zsvExt1 = zsb;
                    dzExt0 = dzExt1 = dz0 - SQUISH_CONSTANT_4D;
                    if ((c1 & 0x03) == 0x03) {
                        zsvExt0 -= 1;
                        dzExt0 += 1;
                    } else {
                        zsvExt1 -= 1;
                        dzExt1 += 1;
                    }
                } else {
                    zsvExt0 = zsvExt1 = zsb + 1;
                    dzExt0 = dzExt1 = dz0 - 1 - SQUISH_CONSTANT_4D;
                }

                if ((c1 & 0x08) == 0) {
                    wsvExt0 = wsb;
                    wsvExt1 = wsb - 1;
                    dw_ext0 = dw0 - SQUISH_CONSTANT_4D;
                    dw_ext1 = dw0 + 1 - SQUISH_CONSTANT_4D;
                } else {
                    wsvExt0 = wsvExt1 = wsb + 1;
                    dw_ext0 = dw_ext1 = dw0 - 1 - SQUISH_CONSTANT_4D;
                }

                //One contribution is a permutation of (0,0,0,2) based on the smaller-sided point
                xsvExt2 = xsb;
                ysvExt2 = ysb;
                zsv_ext2 = zsb;
                wsv_ext2 = wsb;
                dxExt2 = dx0 - 2 * SQUISH_CONSTANT_4D;
                dyExt2 = dy0 - 2 * SQUISH_CONSTANT_4D;
                dz_ext2 = dz0 - 2 * SQUISH_CONSTANT_4D;
                dw_ext2 = dw0 - 2 * SQUISH_CONSTANT_4D;
                if ((c2 & 0x01) != 0) {
                    xsvExt2 += 2;
                    dxExt2 -= 2;
                } else if ((c2 & 0x02) != 0) {
                    ysvExt2 += 2;
                    dyExt2 -= 2;
                } else if ((c2 & 0x04) != 0) {
                    zsv_ext2 += 2;
                    dz_ext2 -= 2;
                } else {
                    wsv_ext2 += 2;
                    dw_ext2 -= 2;
                }
            }

            //Contribution (1,0,0,0)
            double dx1 = dx0 - 1 - SQUISH_CONSTANT_4D;
            double dy1 = dy0 - 0 - SQUISH_CONSTANT_4D;
            double dz1 = dz0 - 0 - SQUISH_CONSTANT_4D;
            double dw1 = dw0 - 0 - SQUISH_CONSTANT_4D;
            double attn1 = 2 - dx1 * dx1 - dy1 * dy1 - dz1 * dz1 - dw1 * dw1;
            if (attn1 > 0) {
                attn1 *= attn1;
                value += attn1 * attn1 * extrapolate(xsb + 1, ysb, zsb, wsb, dx1, dy1, dz1, dw1);
            }

            //Contribution (0,1,0,0)
            double dx2 = dx0 - 0 - SQUISH_CONSTANT_4D;
            double dy2 = dy0 - 1 - SQUISH_CONSTANT_4D;
            double attn2 = 2 - dx2 * dx2 - dy2 * dy2 - dz1 * dz1 - dw1 * dw1;
            if (attn2 > 0) {
                attn2 *= attn2;
                value += attn2 * attn2 * extrapolate(xsb, ysb + 1, zsb, wsb, dx2, dy2, dz1, dw1);
            }

            //Contribution (0,0,1,0)
            double dz3 = dz0 - 1 - SQUISH_CONSTANT_4D;
            double attn3 = 2 - dx2 * dx2 - dy1 * dy1 - dz3 * dz3 - dw1 * dw1;
            if (attn3 > 0) {
                attn3 *= attn3;
                value += attn3 * attn3 * extrapolate(xsb, ysb, zsb + 1, wsb, dx2, dy1, dz3, dw1);
            }

            //Contribution (0,0,0,1)
            double dw4 = dw0 - 1 - SQUISH_CONSTANT_4D;
            double attn4 = 2 - dx2 * dx2 - dy1 * dy1 - dz1 * dz1 - dw4 * dw4;
            if (attn4 > 0) {
                attn4 *= attn4;
                value += attn4 * attn4 * extrapolate(xsb, ysb, zsb, wsb + 1, dx2, dy1, dz1, dw4);
            }

            //Contribution (1,1,0,0)
            double dx5 = dx0 - 1 - 2 * SQUISH_CONSTANT_4D;
            double dy5 = dy0 - 1 - 2 * SQUISH_CONSTANT_4D;
            double dz5 = dz0 - 0 - 2 * SQUISH_CONSTANT_4D;
            double dw5 = dw0 - 0 - 2 * SQUISH_CONSTANT_4D;
            double attn5 = 2 - dx5 * dx5 - dy5 * dy5 - dz5 * dz5 - dw5 * dw5;
            if (attn5 > 0) {
                attn5 *= attn5;
                value += attn5 * attn5 * extrapolate(xsb + 1, ysb + 1, zsb, wsb, dx5, dy5, dz5, dw5);
            }

            //Contribution (1,0,1,0)
            double dx6 = dx0 - 1 - 2 * SQUISH_CONSTANT_4D;
            double dy6 = dy0 - 0 - 2 * SQUISH_CONSTANT_4D;
            double dz6 = dz0 - 1 - 2 * SQUISH_CONSTANT_4D;
            double dw6 = dw0 - 0 - 2 * SQUISH_CONSTANT_4D;
            double attn6 = 2 - dx6 * dx6 - dy6 * dy6 - dz6 * dz6 - dw6 * dw6;
            if (attn6 > 0) {
                attn6 *= attn6;
                value += attn6 * attn6 * extrapolate(xsb + 1, ysb, zsb + 1, wsb, dx6, dy6, dz6, dw6);
            }

            //Contribution (1,0,0,1)
            double dx7 = dx0 - 1 - 2 * SQUISH_CONSTANT_4D;
            double dy7 = dy0 - 0 - 2 * SQUISH_CONSTANT_4D;
            double dz7 = dz0 - 0 - 2 * SQUISH_CONSTANT_4D;
            double dw7 = dw0 - 1 - 2 * SQUISH_CONSTANT_4D;
            double attn7 = 2 - dx7 * dx7 - dy7 * dy7 - dz7 * dz7 - dw7 * dw7;
            if (attn7 > 0) {
                attn7 *= attn7;
                value += attn7 * attn7 * extrapolate(xsb + 1, ysb, zsb, wsb + 1, dx7, dy7, dz7, dw7);
            }

            //Contribution (0,1,1,0)
            double dx8 = dx0 - 0 - 2 * SQUISH_CONSTANT_4D;
            double dy8 = dy0 - 1 - 2 * SQUISH_CONSTANT_4D;
            double dz8 = dz0 - 1 - 2 * SQUISH_CONSTANT_4D;
            double dw8 = dw0 - 0 - 2 * SQUISH_CONSTANT_4D;
            double attn8 = 2 - dx8 * dx8 - dy8 * dy8 - dz8 * dz8 - dw8 * dw8;
            if (attn8 > 0) {
                attn8 *= attn8;
                value += attn8 * attn8 * extrapolate(xsb, ysb + 1, zsb + 1, wsb, dx8, dy8, dz8, dw8);
            }

            //Contribution (0,1,0,1)
            double dx9 = dx0 - 0 - 2 * SQUISH_CONSTANT_4D;
            double dy9 = dy0 - 1 - 2 * SQUISH_CONSTANT_4D;
            double dz9 = dz0 - 0 - 2 * SQUISH_CONSTANT_4D;
            double dw9 = dw0 - 1 - 2 * SQUISH_CONSTANT_4D;
            double attn9 = 2 - dx9 * dx9 - dy9 * dy9 - dz9 * dz9 - dw9 * dw9;
            if (attn9 > 0) {
                attn9 *= attn9;
                value += attn9 * attn9 * extrapolate(xsb, ysb + 1, zsb, wsb + 1, dx9, dy9, dz9, dw9);
            }

            //Contribution (0,0,1,1)
            double dx10 = dx0 - 0 - 2 * SQUISH_CONSTANT_4D;
            double dy10 = dy0 - 0 - 2 * SQUISH_CONSTANT_4D;
            double dz10 = dz0 - 1 - 2 * SQUISH_CONSTANT_4D;
            double dw10 = dw0 - 1 - 2 * SQUISH_CONSTANT_4D;
            double attn10 = 2 - dx10 * dx10 - dy10 * dy10 - dz10 * dz10 - dw10 * dw10;
            if (attn10 > 0) {
                attn10 *= attn10;
                value += attn10 * attn10 * extrapolate(xsb, ysb, zsb + 1, wsb + 1, dx10, dy10, dz10, dw10);
            }
        } else { //We're inside the second dispentachoron (Rectified 4-Simplex)
            double aScore;
            byte aPoint;
            boolean aIsBiggerSide = true;
            double bScore;
            byte bPoint;
            boolean bIsBiggerSide = true;

            //Decide between (0,0,1,1) and (1,1,0,0)
            if (xins + yins < zins + wins) {
                aScore = xins + yins;
                aPoint = 0x0C;
            } else {
                aScore = zins + wins;
                aPoint = 0x03;
            }

            //Decide between (0,1,0,1) and (1,0,1,0)
            if (xins + zins < yins + wins) {
                bScore = xins + zins;
                bPoint = 0x0A;
            } else {
                bScore = yins + wins;
                bPoint = 0x05;
            }

            //Closer between (0,1,1,0) and (1,0,0,1) will replace the further of a and b, if closer.
            if (xins + wins < yins + zins) {
                double score = xins + wins;
                if (aScore <= bScore && score < bScore) {
                    bScore = score;
                    bPoint = 0x06;
                } else if (aScore > bScore && score < aScore) {
                    aScore = score;
                    aPoint = 0x06;
                }
            } else {
                double score = yins + zins;
                if (aScore <= bScore && score < bScore) {
                    bScore = score;
                    bPoint = 0x09;
                } else if (aScore > bScore && score < aScore) {
                    aScore = score;
                    aPoint = 0x09;
                }
            }

            double p1 = 3 - inSum + xins;
            if (aScore <= bScore && p1 < bScore) {
                bScore = p1;
                bPoint = 0x0E;
                bIsBiggerSide = false;
            } else if (aScore > bScore && p1 < aScore) {
                aScore = p1;
                aPoint = 0x0E;
                aIsBiggerSide = false;
            }

            double p2 = 3 - inSum + yins;
            if (aScore <= bScore && p2 < bScore) {
                bScore = p2;
                bPoint = 0x0D;
                bIsBiggerSide = false;
            } else if (aScore > bScore && p2 < aScore) {
                aScore = p2;
                aPoint = 0x0D;
                aIsBiggerSide = false;
            }

            double p3 = 3 - inSum + zins;
            if (aScore <= bScore && p3 < bScore) {
                bScore = p3;
                bPoint = 0x0B;
                bIsBiggerSide = false;
            } else if (aScore > bScore && p3 < aScore) {
                aScore = p3;
                aPoint = 0x0B;
                aIsBiggerSide = false;
            }

            double p4 = 3 - inSum + wins;
            if (aScore <= bScore && p4 < bScore) {
                bPoint = 0x07;
                bIsBiggerSide = false;
            } else if (aScore > bScore && p4 < aScore) {
                aPoint = 0x07;
                aIsBiggerSide = false;
            }

            //Where each of the two closest points are determines how the extra three vertices are calculated.
            if (aIsBiggerSide == bIsBiggerSide) {
                if (aIsBiggerSide) { //Both closest points on the bigger side
                    byte c1 = (byte) (aPoint & bPoint);
                    byte c2 = (byte) (aPoint | bPoint);

                    //Two contributions are permutations of (0,0,0,1) and (0,0,0,2) based on c1
                    xsvExt0 = xsvExt1 = xsb;
                    ysvExt0 = ysvExt1 = ysb;
                    zsvExt0 = zsvExt1 = zsb;
                    wsvExt0 = wsvExt1 = wsb;
                    dxExt0 = dx0 - SQUISH_CONSTANT_4D;
                    dyExt0 = dy0 - SQUISH_CONSTANT_4D;
                    dzExt0 = dz0 - SQUISH_CONSTANT_4D;
                    dw_ext0 = dw0 - SQUISH_CONSTANT_4D;
                    dxExt1 = dx0 - 2 * SQUISH_CONSTANT_4D;
                    dyExt1 = dy0 - 2 * SQUISH_CONSTANT_4D;
                    dzExt1 = dz0 - 2 * SQUISH_CONSTANT_4D;
                    dw_ext1 = dw0 - 2 * SQUISH_CONSTANT_4D;
                    if ((c1 & 0x01) != 0) {
                        xsvExt0 += 1;
                        dxExt0 -= 1;
                        xsvExt1 += 2;
                        dxExt1 -= 2;
                    } else if ((c1 & 0x02) != 0) {
                        ysvExt0 += 1;
                        dyExt0 -= 1;
                        ysvExt1 += 2;
                        dyExt1 -= 2;
                    } else if ((c1 & 0x04) != 0) {
                        zsvExt0 += 1;
                        dzExt0 -= 1;
                        zsvExt1 += 2;
                        dzExt1 -= 2;
                    } else {
                        wsvExt0 += 1;
                        dw_ext0 -= 1;
                        wsvExt1 += 2;
                        dw_ext1 -= 2;
                    }

                    //One contribution is a permutation of (1,1,1,-1) based on c2
                    xsvExt2 = xsb + 1;
                    ysvExt2 = ysb + 1;
                    zsv_ext2 = zsb + 1;
                    wsv_ext2 = wsb + 1;
                    dxExt2 = dx0 - 1 - 2 * SQUISH_CONSTANT_4D;
                    dyExt2 = dy0 - 1 - 2 * SQUISH_CONSTANT_4D;
                    dz_ext2 = dz0 - 1 - 2 * SQUISH_CONSTANT_4D;
                    dw_ext2 = dw0 - 1 - 2 * SQUISH_CONSTANT_4D;
                    if ((c2 & 0x01) == 0) {
                        xsvExt2 -= 2;
                        dxExt2 += 2;
                    } else if ((c2 & 0x02) == 0) {
                        ysvExt2 -= 2;
                        dyExt2 += 2;
                    } else if ((c2 & 0x04) == 0) {
                        zsv_ext2 -= 2;
                        dz_ext2 += 2;
                    } else {
                        wsv_ext2 -= 2;
                        dw_ext2 += 2;
                    }
                } else { //Both closest points on the smaller side
                    //One of the two extra points is (1,1,1,1)
                    xsvExt2 = xsb + 1;
                    ysvExt2 = ysb + 1;
                    zsv_ext2 = zsb + 1;
                    wsv_ext2 = wsb + 1;
                    dxExt2 = dx0 - 1 - 4 * SQUISH_CONSTANT_4D;
                    dyExt2 = dy0 - 1 - 4 * SQUISH_CONSTANT_4D;
                    dz_ext2 = dz0 - 1 - 4 * SQUISH_CONSTANT_4D;
                    dw_ext2 = dw0 - 1 - 4 * SQUISH_CONSTANT_4D;

                    //Other two points are based on the shared axes.
                    byte c = (byte) (aPoint & bPoint);

                    if ((c & 0x01) != 0) {
                        xsvExt0 = xsb + 2;
                        xsvExt1 = xsb + 1;
                        dxExt0 = dx0 - 2 - 3 * SQUISH_CONSTANT_4D;
                        dxExt1 = dx0 - 1 - 3 * SQUISH_CONSTANT_4D;
                    } else {
                        xsvExt0 = xsvExt1 = xsb;
                        dxExt0 = dxExt1 = dx0 - 3 * SQUISH_CONSTANT_4D;
                    }

                    if ((c & 0x02) != 0) {
                        ysvExt0 = ysvExt1 = ysb + 1;
                        dyExt0 = dyExt1 = dy0 - 1 - 3 * SQUISH_CONSTANT_4D;
                        if ((c & 0x01) == 0) {
                            ysvExt0 += 1;
                            dyExt0 -= 1;
                        } else {
                            ysvExt1 += 1;
                            dyExt1 -= 1;
                        }
                    } else {
                        ysvExt0 = ysvExt1 = ysb;
                        dyExt0 = dyExt1 = dy0 - 3 * SQUISH_CONSTANT_4D;
                    }

                    if ((c & 0x04) != 0) {
                        zsvExt0 = zsvExt1 = zsb + 1;
                        dzExt0 = dzExt1 = dz0 - 1 - 3 * SQUISH_CONSTANT_4D;
                        if ((c & 0x03) == 0) {
                            zsvExt0 += 1;
                            dzExt0 -= 1;
                        } else {
                            zsvExt1 += 1;
                            dzExt1 -= 1;
                        }
                    } else {
                        zsvExt0 = zsvExt1 = zsb;
                        dzExt0 = dzExt1 = dz0 - 3 * SQUISH_CONSTANT_4D;
                    }

                    if ((c & 0x08) != 0) {
                        wsvExt0 = wsb + 1;
                        wsvExt1 = wsb + 2;
                        dw_ext0 = dw0 - 1 - 3 * SQUISH_CONSTANT_4D;
                        dw_ext1 = dw0 - 2 - 3 * SQUISH_CONSTANT_4D;
                    } else {
                        wsvExt0 = wsvExt1 = wsb;
                        dw_ext0 = dw_ext1 = dw0 - 3 * SQUISH_CONSTANT_4D;
                    }
                }
            } else { //One point on each "side"
                byte c1, c2;
                if (aIsBiggerSide) {
                    c1 = aPoint;
                    c2 = bPoint;
                } else {
                    c1 = bPoint;
                    c2 = aPoint;
                }

                //Two contributions are the bigger-sided point with each 1 replaced with 2.
                if ((c1 & 0x01) != 0) {
                    xsvExt0 = xsb + 2;
                    xsvExt1 = xsb + 1;
                    dxExt0 = dx0 - 2 - 3 * SQUISH_CONSTANT_4D;
                    dxExt1 = dx0 - 1 - 3 * SQUISH_CONSTANT_4D;
                } else {
                    xsvExt0 = xsvExt1 = xsb;
                    dxExt0 = dxExt1 = dx0 - 3 * SQUISH_CONSTANT_4D;
                }

                if ((c1 & 0x02) != 0) {
                    ysvExt0 = ysvExt1 = ysb + 1;
                    dyExt0 = dyExt1 = dy0 - 1 - 3 * SQUISH_CONSTANT_4D;
                    if ((c1 & 0x01) == 0) {
                        ysvExt0 += 1;
                        dyExt0 -= 1;
                    } else {
                        ysvExt1 += 1;
                        dyExt1 -= 1;
                    }
                } else {
                    ysvExt0 = ysvExt1 = ysb;
                    dyExt0 = dyExt1 = dy0 - 3 * SQUISH_CONSTANT_4D;
                }

                if ((c1 & 0x04) != 0) {
                    zsvExt0 = zsvExt1 = zsb + 1;
                    dzExt0 = dzExt1 = dz0 - 1 - 3 * SQUISH_CONSTANT_4D;
                    if ((c1 & 0x03) == 0) {
                        zsvExt0 += 1;
                        dzExt0 -= 1;
                    } else {
                        zsvExt1 += 1;
                        dzExt1 -= 1;
                    }
                } else {
                    zsvExt0 = zsvExt1 = zsb;
                    dzExt0 = dzExt1 = dz0 - 3 * SQUISH_CONSTANT_4D;
                }

                if ((c1 & 0x08) != 0) {
                    wsvExt0 = wsb + 1;
                    wsvExt1 = wsb + 2;
                    dw_ext0 = dw0 - 1 - 3 * SQUISH_CONSTANT_4D;
                    dw_ext1 = dw0 - 2 - 3 * SQUISH_CONSTANT_4D;
                } else {
                    wsvExt0 = wsvExt1 = wsb;
                    dw_ext0 = dw_ext1 = dw0 - 3 * SQUISH_CONSTANT_4D;
                }

                //One contribution is a permutation of (1,1,1,-1) based on the smaller-sided point
                xsvExt2 = xsb + 1;
                ysvExt2 = ysb + 1;
                zsv_ext2 = zsb + 1;
                wsv_ext2 = wsb + 1;
                dxExt2 = dx0 - 1 - 2 * SQUISH_CONSTANT_4D;
                dyExt2 = dy0 - 1 - 2 * SQUISH_CONSTANT_4D;
                dz_ext2 = dz0 - 1 - 2 * SQUISH_CONSTANT_4D;
                dw_ext2 = dw0 - 1 - 2 * SQUISH_CONSTANT_4D;
                if ((c2 & 0x01) == 0) {
                    xsvExt2 -= 2;
                    dxExt2 += 2;
                } else if ((c2 & 0x02) == 0) {
                    ysvExt2 -= 2;
                    dyExt2 += 2;
                } else if ((c2 & 0x04) == 0) {
                    zsv_ext2 -= 2;
                    dz_ext2 += 2;
                } else {
                    wsv_ext2 -= 2;
                    dw_ext2 += 2;
                }
            }

            //Contribution (1,1,1,0)
            double dx4 = dx0 - 1 - 3 * SQUISH_CONSTANT_4D;
            double dy4 = dy0 - 1 - 3 * SQUISH_CONSTANT_4D;
            double dz4 = dz0 - 1 - 3 * SQUISH_CONSTANT_4D;
            double dw4 = dw0 - 3 * SQUISH_CONSTANT_4D;
            double attn4 = 2 - dx4 * dx4 - dy4 * dy4 - dz4 * dz4 - dw4 * dw4;
            if (attn4 > 0) {
                attn4 *= attn4;
                value += attn4 * attn4 * extrapolate(xsb + 1, ysb + 1, zsb + 1, wsb, dx4, dy4, dz4, dw4);
            }

            //Contribution (1,1,0,1)
            double dz3 = dz0 - 3 * SQUISH_CONSTANT_4D;
            double dw3 = dw0 - 1 - 3 * SQUISH_CONSTANT_4D;
            double attn3 = 2 - dx4 * dx4 - dy4 * dy4 - dz3 * dz3 - dw3 * dw3;
            if (attn3 > 0) {
                attn3 *= attn3;
                value += attn3 * attn3 * extrapolate(xsb + 1, ysb + 1, zsb, wsb + 1, dx4, dy4, dz3, dw3);
            }

            //Contribution (1,0,1,1)
            double dy2 = dy0 - 3 * SQUISH_CONSTANT_4D;
            double attn2 = 2 - dx4 * dx4 - dy2 * dy2 - dz4 * dz4 - dw3 * dw3;
            if (attn2 > 0) {
                attn2 *= attn2;
                value += attn2 * attn2 * extrapolate(xsb + 1, ysb, zsb + 1, wsb + 1, dx4, dy2, dz4, dw3);
            }

            //Contribution (0,1,1,1)
            double dx1 = dx0 - 3 * SQUISH_CONSTANT_4D;
            double attn1 = 2 - dx1 * dx1 - dy4 * dy4 - dz4 * dz4 - dw3 * dw3;
            if (attn1 > 0) {
                attn1 *= attn1;
                value += attn1 * attn1 * extrapolate(xsb, ysb + 1, zsb + 1, wsb + 1, dx1, dy4, dz4, dw3);
            }

            //Contribution (1,1,0,0)
            double dx5 = dx0 - 1 - 2 * SQUISH_CONSTANT_4D;
            double dy5 = dy0 - 1 - 2 * SQUISH_CONSTANT_4D;
            double dz5 = dz0 - 0 - 2 * SQUISH_CONSTANT_4D;
            double dw5 = dw0 - 0 - 2 * SQUISH_CONSTANT_4D;
            double attn5 = 2 - dx5 * dx5 - dy5 * dy5 - dz5 * dz5 - dw5 * dw5;
            if (attn5 > 0) {
                attn5 *= attn5;
                value += attn5 * attn5 * extrapolate(xsb + 1, ysb + 1, zsb, wsb, dx5, dy5, dz5, dw5);
            }

            //Contribution (1,0,1,0)
            double dx6 = dx0 - 1 - 2 * SQUISH_CONSTANT_4D;
            double dy6 = dy0 - 0 - 2 * SQUISH_CONSTANT_4D;
            double dz6 = dz0 - 1 - 2 * SQUISH_CONSTANT_4D;
            double dw6 = dw0 - 0 - 2 * SQUISH_CONSTANT_4D;
            double attn6 = 2 - dx6 * dx6 - dy6 * dy6 - dz6 * dz6 - dw6 * dw6;
            if (attn6 > 0) {
                attn6 *= attn6;
                value += attn6 * attn6 * extrapolate(xsb + 1, ysb, zsb + 1, wsb, dx6, dy6, dz6, dw6);
            }

            //Contribution (1,0,0,1)
            double dx7 = dx0 - 1 - 2 * SQUISH_CONSTANT_4D;
            double dy7 = dy0 - 0 - 2 * SQUISH_CONSTANT_4D;
            double dz7 = dz0 - 0 - 2 * SQUISH_CONSTANT_4D;
            double dw7 = dw0 - 1 - 2 * SQUISH_CONSTANT_4D;
            double attn7 = 2 - dx7 * dx7 - dy7 * dy7 - dz7 * dz7 - dw7 * dw7;
            if (attn7 > 0) {
                attn7 *= attn7;
                value += attn7 * attn7 * extrapolate(xsb + 1, ysb, zsb, wsb + 1, dx7, dy7, dz7, dw7);
            }

            //Contribution (0,1,1,0)
            double dx8 = dx0 - 0 - 2 * SQUISH_CONSTANT_4D;
            double dy8 = dy0 - 1 - 2 * SQUISH_CONSTANT_4D;
            double dz8 = dz0 - 1 - 2 * SQUISH_CONSTANT_4D;
            double dw8 = dw0 - 0 - 2 * SQUISH_CONSTANT_4D;
            double attn8 = 2 - dx8 * dx8 - dy8 * dy8 - dz8 * dz8 - dw8 * dw8;
            if (attn8 > 0) {
                attn8 *= attn8;
                value += attn8 * attn8 * extrapolate(xsb, ysb + 1, zsb + 1, wsb, dx8, dy8, dz8, dw8);
            }

            //Contribution (0,1,0,1)
            double dx9 = dx0 - 0 - 2 * SQUISH_CONSTANT_4D;
            double dy9 = dy0 - 1 - 2 * SQUISH_CONSTANT_4D;
            double dz9 = dz0 - 0 - 2 * SQUISH_CONSTANT_4D;
            double dw9 = dw0 - 1 - 2 * SQUISH_CONSTANT_4D;
            double attn9 = 2 - dx9 * dx9 - dy9 * dy9 - dz9 * dz9 - dw9 * dw9;
            if (attn9 > 0) {
                attn9 *= attn9;
                value += attn9 * attn9 * extrapolate(xsb, ysb + 1, zsb, wsb + 1, dx9, dy9, dz9, dw9);
            }

            //Contribution (0,0,1,1)
            double dx10 = dx0 - 0 - 2 * SQUISH_CONSTANT_4D;
            double dy10 = dy0 - 0 - 2 * SQUISH_CONSTANT_4D;
            double dz10 = dz0 - 1 - 2 * SQUISH_CONSTANT_4D;
            double dw10 = dw0 - 1 - 2 * SQUISH_CONSTANT_4D;
            double attn10 = 2 - dx10 * dx10 - dy10 * dy10 - dz10 * dz10 - dw10 * dw10;
            if (attn10 > 0) {
                attn10 *= attn10;
                value += attn10 * attn10 * extrapolate(xsb, ysb, zsb + 1, wsb + 1, dx10, dy10, dz10, dw10);
            }
        }

        //First extra vertex
        double attnExt0 = 2 - dxExt0 * dxExt0 - dyExt0 * dyExt0 - dzExt0 * dzExt0 - dw_ext0 * dw_ext0;
        if (attnExt0 > 0) {
            attnExt0 *= attnExt0;
            value += attnExt0 * attnExt0 * extrapolate(xsvExt0, ysvExt0, zsvExt0, wsvExt0, dxExt0, dyExt0, dzExt0, dw_ext0);
        }

        //Second extra vertex
        double attnExt1 = 2 - dxExt1 * dxExt1 - dyExt1 * dyExt1 - dzExt1 * dzExt1 - dw_ext1 * dw_ext1;
        if (attnExt1 > 0) {
            attnExt1 *= attnExt1;
            value += attnExt1 * attnExt1 * extrapolate(xsvExt1, ysvExt1, zsvExt1, wsvExt1, dxExt1, dyExt1, dzExt1, dw_ext1);
        }

        //Third extra vertex
        double attn_ext2 = 2 - dxExt2 * dxExt2 - dyExt2 * dyExt2 - dz_ext2 * dz_ext2 - dw_ext2 * dw_ext2;
        if (attn_ext2 > 0) {
            attn_ext2 *= attn_ext2;
            value += attn_ext2 * attn_ext2 * extrapolate(xsvExt2, ysvExt2, zsv_ext2, wsv_ext2, dxExt2, dyExt2, dz_ext2, dw_ext2);
        }

        return value / NORM_CONSTANT_4D;
    }

    private double extrapolate(int xsb, int ysb, double dx, double dy) {
        int index = perm[(perm[xsb & 0xFF] + ysb) & 0xFF] & 0x0E;
        return gradients2D[index] * dx
                + gradients2D[index + 1] * dy;
    }

    private double extrapolate(int xsb, int ysb, int zsb, double dx, double dy, double dz) {
        int index = permGradIndex3D[(perm[(perm[xsb & 0xFF] + ysb) & 0xFF] + zsb) & 0xFF];
        return gradients3D[index] * dx
                + gradients3D[index + 1] * dy
                + gradients3D[index + 2] * dz;
    }

    private double extrapolate(int xsb, int ysb, int zsb, int wsb, double dx, double dy, double dz, double dw) {
        int index = perm[(perm[(perm[(perm[xsb & 0xFF] + ysb) & 0xFF] + zsb) & 0xFF] + wsb) & 0xFF] & 0xFC;
        return gradients4D[index] * dx
                + gradients4D[index + 1] * dy
                + gradients4D[index + 2] * dz
                + gradients4D[index + 3] * dw;
    }

    private static int fastFloor(double x) {
        int xi = (int) x;
        return x < xi ? xi - 1 : xi;
    }

    //Gradients for 2D. They approximate the directions to the
    //vertices of an octagon from the center.
    protected static final byte[] gradients2D = new byte[]{
        5, 2, 2, 5,
        -5, 2, -2, 5,
        5, -2, 2, -5,
        -5, -2, -2, -5,};

    //Gradients for 3D. They approximate the directions to the
    //vertices of a rhombicuboctahedron from the center, skewed so
    //that the triangular and square facets can be inscribed inside
    //circles of the same radius.
    protected static final byte[] gradients3D = new byte[]{
        -11, 4, 4, -4, 11, 4, -4, 4, 11,
        11, 4, 4, 4, 11, 4, 4, 4, 11,
        -11, -4, 4, -4, -11, 4, -4, -4, 11,
        11, -4, 4, 4, -11, 4, 4, -4, 11,
        -11, 4, -4, -4, 11, -4, -4, 4, -11,
        11, 4, -4, 4, 11, -4, 4, 4, -11,
        -11, -4, -4, -4, -11, -4, -4, -4, -11,
        11, -4, -4, 4, -11, -4, 4, -4, -11,};

    //Gradients for 4D. They approximate the directions to the
    //vertices of a disprismatotesseractihexadecachoron from the center,
    //skewed so that the tetrahedral and cubic facets can be inscribed inside
    //spheres of the same radius.
    protected static final byte[] gradients4D = new byte[]{
        3, 1, 1, 1, 1, 3, 1, 1, 1, 1, 3, 1, 1, 1, 1, 3,
        -3, 1, 1, 1, -1, 3, 1, 1, -1, 1, 3, 1, -1, 1, 1, 3,
        3, -1, 1, 1, 1, -3, 1, 1, 1, -1, 3, 1, 1, -1, 1, 3,
        -3, -1, 1, 1, -1, -3, 1, 1, -1, -1, 3, 1, -1, -1, 1, 3,
        3, 1, -1, 1, 1, 3, -1, 1, 1, 1, -3, 1, 1, 1, -1, 3,
        -3, 1, -1, 1, -1, 3, -1, 1, -1, 1, -3, 1, -1, 1, -1, 3,
        3, -1, -1, 1, 1, -3, -1, 1, 1, -1, -3, 1, 1, -1, -1, 3,
        -3, -1, -1, 1, -1, -3, -1, 1, -1, -1, -3, 1, -1, -1, -1, 3,
        3, 1, 1, -1, 1, 3, 1, -1, 1, 1, 3, -1, 1, 1, 1, -3,
        -3, 1, 1, -1, -1, 3, 1, -1, -1, 1, 3, -1, -1, 1, 1, -3,
        3, -1, 1, -1, 1, -3, 1, -1, 1, -1, 3, -1, 1, -1, 1, -3,
        -3, -1, 1, -1, -1, -3, 1, -1, -1, -1, 3, -1, -1, -1, 1, -3,
        3, 1, -1, -1, 1, 3, -1, -1, 1, 1, -3, -1, 1, 1, -1, -3,
        -3, 1, -1, -1, -1, 3, -1, -1, -1, 1, -3, -1, -1, 1, -1, -3,
        3, -1, -1, -1, 1, -3, -1, -1, 1, -1, -3, -1, 1, -1, -1, -3,
        -3, -1, -1, -1, -1, -3, -1, -1, -1, -1, -3, -1, -1, -1, -1, -3,};
}
