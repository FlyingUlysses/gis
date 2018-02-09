//==============================================================================
// YxxfEntDimension.java
//
// DIMENSION entity
//==============================================================================

//==============================================================================
// Ycad - Java CAD library
// Copyright (c) 2003 - Ed Karlo - mailto:ekarlo@ysystems.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
//==============================================================================

//==============================================================================
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfEntDimension.java,v 1.11 2003/05/08 11:24:03 ekarlo Exp $
// $Log: YxxfEntDimension.java,v $
// Revision 1.11  2003/05/08 11:24:03  ekarlo
// Remove warnings.
//
// Revision 1.10  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.9  2001/05/20 08:43:10  ekarlo
// Imprinter development check-in.
//
// Revision 1.8  2000-10-17 01:43:56-06  ekarlo
// Change package paths to lower case.
//
// Revision 1.7  1999-11-27 21:48:51-07  ekarlo
// Fix scale problem with text.
//
// Revision 1.6  1999-11-24 23:05:38-07  ekarlo
// Fix insert calc timing problem.
//
// Revision 1.5  1999-09-29 17:04:01-06  walter
// Added JavaDoc comments.
//
// Revision 1.4  1999-07-09 14:10:50-06  ekarlo
// Rearrange package names.
//
// Revision 1.3  1999/06/20  22:34:10  ekarlo
// Rearrange package names.
//
// Revision 1.2  1999/06/15  04:59:25  ekarlo
// User Interface - phase 1.
//
// Revision 1.1  1999/02/08  05:16:11  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


/**
 * DIMENSION entity.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfEntDimension extends YxxfEntHeader implements YxxfEnt
{
    /**
     * code   2 - Name of block containing the entities that make up the
     *            dimension picture.
     *            Set to block table reference.
     */
    public
    YxxfEntBlock                block;

    /**
     * code   3 - Dimension style name.
     */
    public
    String                      dimstylename;

    /**
     * code  10,20,30 -
     *            Definition point for all dimension types (in WCS).
     */
    public
    YxxfGfxPointW               defpnt          = new YxxfGfxPointW();

    /**
     * code  11,21,31 -
     *            Middle point of dimension text (in OCS).
     */
    public
    YxxfGfxPointW               midpnt          = new YxxfGfxPointW();

    /**
     * code  12,22,32 -
     *            Insertion point for clones of a dimension
     *            (Baseline and Continue) (in OCS).
     */
    public
    YxxfGfxPointW               insclonepnt     = new YxxfGfxPointW();

    /**
     * code  70 - Dimension type.
     * <UL>
     *   <LI>  0 = Rotated, horizontal, or vertical.
     *   <LI>  1 = Aligned
     *   <LI>  2 = Angular
     *   <LI>  3 = Diameter
     *   <LI>  4 = Radius
     *   <LI>  5 = Angular 3-point.
     *   <LI>  6 = Ordinate
     *   <LI> 64 = Ordinate type.
     *             This is a bit value (bit 7) used only
     *             with integer value 6.  If set (70), ordinate is X-type; 
     *             if not set (6), ordinate is Y-type.
     *   <LI>128 = This is a bit value (bit 8) added to the other group
     *             70 values if the dimension text has been positioned at a
     *             user-defined location rather than at the default location.
     * </UL>
     */
    public
    int                         flags           = 0;

    /**
     * code   1 - Dimension text explicitly entered by the user. Optional.
     *            Default is the measurement.  If null or "<>", the dimension
     *            measurement is drawn as the text, if " " (one blank
     *            space), the text is suppressed.  Anything else is drawn as
     *            the text.
     */
    public
    String                      dimtextuser     = "";

    /**
     * code  13,23,33 -
     *            Definition point for linear and angular dimensions. (in WCS).
     */
    public
    YxxfGfxPointW               deflinangpnt1   = new YxxfGfxPointW();

    /**
     * code  14,24,34 -
     *            Definition point for linear and angular dimensions. (in WCS).
     */
    public
    YxxfGfxPointW               deflinangpnt2   = new YxxfGfxPointW();

    /**
     * code  15,25,35 -
     *            Definition point for diameter, radius, and angular
     *            dimensions. (in WCS).
     */
    public
    YxxfGfxPointW               defdiaradangpnt = new YxxfGfxPointW();

    /**
     * code  16,26,36 -
     *            Point defining dimension arc for angular dimensions. (in OCS).
     */
    public
    YxxfGfxPointW               defarcangpnt    = new YxxfGfxPointW();

    /**
     * code  40 - Leader length for radius and diameter dimensions.
     */
    public
    double                      leaderlen       = 0.0;

    /**
     * code  50 - Leader length for radius and diameter dimensions.
     */
    public
    double                      dimang          = 0.0;

    /**
     * code  51 - Leader length for radius and diameter dimensions.
     */
    public
    double                      horzang         = 0.0;

    /**
     * code  52 - Leader length for radius and diameter dimensions.
     */
    public
    double                      extang          = 0.0;

    /**
     * code  53 - Leader length for radius and diameter dimensions.
     */
    public
    double                      textang         = 0.0;

    /**
     * code 210,220,230 -
     *            Extrusion direction. Present if the extrusion direction is
     *            not parallel to the world Z axis.
     */
    public
    YxxfGfxPointW               xtruDir         = new YxxfGfxPointW(0, 0, 1);


    // Calculated items

    /**
     * Transmat for dimension - computed during get or load.
     */
    public
    YxxfGfxMatrix               M_dimension     = null;

    /**
     *            Insertion point (transmat).
     */
    public
    YxxfGfxPointW               Ip              = new YxxfGfxPointW();

    /**
     *            Insertion point (calculated).
     */
    public
    YxxfGfxPointW               calc_inspnt     = new YxxfGfxPointW();

    /**
     *            Scale factor (calculated).
     */
    public
    YxxfGfxPointW               calc_scale      = new YxxfGfxPointW(1, 1, 1);

    /**
     *            Rotation angle (calculated).
     */
    public
    double                      calc_rotang     = 0.0;


    /**
     * Constructor (empty).
     */
    public
    YxxfEntDimension()
    {
    }


    /**
     * Constructor. Construct dimension and set block.
     * @param block The Entity block.
     */
    public
    YxxfEntDimension(YxxfEntBlock block)
    {
        setBlock(block);
    }


    /**
     * Set block.
     * @param The Entity block.
     */
    public
    void setBlock(YxxfEntBlock block)
    {
        this.block = block;
    }


    /**
     * Draw dimension.
     * @param gc The graphics context.
     */
    public
    void draw(YxxfGfxContext gc)
    {
        if (gc.setupInsertPush(this) == false)
            return;

        gc.pushModelStack(M_dimension, Ip, block.basepnt);


        YxxfEnt ent = null;
        int nextToDraw = 0;

        while (true)
        {
            ent = (YxxfEnt)block.nextEntity(nextToDraw);
            if (ent == null)
                break;

            if (gc.gc_doing != YxxfGfxContext.GC_DOING_DRAWING)
                break;

            // draw it
            ent.draw(gc);

            nextToDraw++;
        }


        // Restore model stack
        gc.popModelStack();


        // Restore insert stack
        gc.popInsertStack();
    }


    /**
     * Calculate dimension transmat.
     * @param D The drawing.
     */
    public
    void calc(Yxxf D)
    {
        //
        // connect
        //
        hdr_calc(D);


        YxxfGfxPointW Ax = new YxxfGfxPointW();
        YxxfGfxPointW Ay = new YxxfGfxPointW();
        YxxfGfxPointW Az = new YxxfGfxPointW();

        YxxfGfxPointW B0 = new YxxfGfxPointW();
        YxxfGfxPointW Bz = new YxxfGfxPointW();

//      YxxfGfxPointW Ip = new YxxfGfxPointW();
//      YxxfGfxPointW Bp = new YxxfGfxPointW();


        //
        // setup insert transform
        //

        // Initialize insert matrix
        if (M_dimension == null)
            M_dimension = new YxxfGfxMatrix();
        else
            M_dimension.mtxSetIdentity();


        // Calculate Ax, Ay, Az axes from xtruDir using Arbitrary Axis Algorithm
        Az.set(xtruDir);
        Az.normalize();
        YxxfGfxPointW.calcAAA(Ax, Ay, Az);


        // Apply new rotation
        M_dimension.mtxRotateAxes_World_to_Local(Ax, Ay, Az);


        // Calculate insertion point Ip from inspnt
        // Applies the current insert rotation
        calc_inspnt.set(insclonepnt);
        Ip.set(calc_inspnt);
        M_dimension.mtxTransformPoint(Ip);


        // Apply new scale
        M_dimension.mtxSetIdentity();
        M_dimension.mtxScale(calc_scale, YxxfGfxPointW.W0);


        // Reapply new rotation
        M_dimension.mtxRotateAxes_World_to_Local(Ax, Ay, Az);


        // Apply twist
        M_dimension.mtxTransformPoint(B0.set(YxxfGfxPointW.W0));
        M_dimension.mtxTransformPoint(Bz.set(YxxfGfxPointW.Wz));
        Bz.normalize();

        M_dimension.mtxRotate(B0, Bz, calc_rotang * (Math.PI / 180.0));


        // Calculate block base point Bp from basepnt
        // Applies the current insert rotation, scale and twist
//      Bp.set(block.basepnt);
//      M_dimension.mtxTransformPoint(Bp);


        // Apply new translate (insertion point)
//      M_dimension.mtxTranslate(Ip);
//      M_dimension.mtxTranslateInverse(Bp);
    }
}

