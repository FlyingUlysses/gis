//==============================================================================
// YxxfEntInsert.java
//
// INSERT entity
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfEntInsert.java,v 1.28 2003/05/08 11:24:02 ekarlo Exp $
// $Log: YxxfEntInsert.java,v $
// Revision 1.28  2003/05/08 11:24:02  ekarlo
// Remove warnings.
//
// Revision 1.27  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.26  2002/09/25 19:37:48  ekarlo
// Use better Vector clear method plus Old Navigator complained.
//
// Revision 1.25  2002-09-12 14:03:27-06  ekarlo
// MTEXT development check-in.
//
// Revision 1.24  2001-05-20 02:43:09-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.23  2000-10-17 01:43:55-06  ekarlo
// Change package paths to lower case.
//
// Revision 1.22  1999-11-24 23:05:38-07  ekarlo
// Fix insert calc timing problem.
//
// Revision 1.21  1999-09-22 22:55:34-06  walter
// Added JavaDoc comments.
//
// Revision 1.20  1999-08-07 15:51:28-06  ekarlo
// Fix problem with calc scale/rotate interaction.
//
// Revision 1.19  1999/07/09  20:10:50  ekarlo
// Rearrange package names.
//
// Revision 1.18  1999/06/20  22:34:10  ekarlo
// Rearrange package names.
//
// Revision 1.17  1999/06/15  04:59:25  ekarlo
// User Interface - phase 1.
//
// Revision 1.16  1999/02/08  05:11:14  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.15  1998/08/21  20:20:06  ekarlo
// Eliminate use of global work arrays in matrix calculations
// and use local doubles instead of arrays.
//
// Revision 1.14  1998/07/12  00:08:15  ekarlo
// Rename var.
//
// Revision 1.13  1997/12/13  18:51:36  ekarlo
// Move work matrix arrays out of YxxfGfxMatrix Objects.
//
// Revision 1.12  1997/08/30  14:16:35  ekarlo
// Change matrix initialization.
// Remove debug code.
// Change redraw action.
//
// Revision 1.11  1997/07/21  22:40:07  ekarlo
// Make fields public for static get.
//
// Revision 1.10  1996/10/26  00:42:38  ekarlo
// Correct color and layer handling.
//
// Revision 1.9  1996/09/26  02:02:31  ekarlo
// Implement proper color activity.
//
// Revision 1.8  1996/09/13  05:58:05  ekarlo
// Use new base entity class structure.
//
// Revision 1.7  1996/08/25  05:26:19  ekarlo
// Draw ellipse - continuing.
//
// Revision 1.6  1996/08/19  15:50:05  ekarlo
// Reimplement block base point translation.
//
// Revision 1.5  1996/08/19  04:51:50  ekarlo
// Redo insert method.  Calculate insert transform once during get.
//
// Revision 1.4  1996/08/18  02:17:49  ekarlo
// 1) Begin change to use new transmat.
// 2) Use new rotate axes methods.
//
// Revision 1.3  1996/08/13  02:23:55  ekarlo
// 1) Default xtruDir to (0,0,1).
// 2) Add 3D transform.
//
// Revision 1.2  1996/07/30  06:51:47  ekarlo
// Use point class.
//
// Revision 1.1  1996/07/02  02:20:20  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


import java.util.*;


/**
 * INSERT entity.
 * @author Ed Karlo - Y Systems, LLC
 */

public class YxxfEntInsert extends YxxfEntHeader implements YxxfEnt
{
    /**
     * code   2 - Block name.
     *            Set to block table reference.
     */
    public
    YxxfEntBlock                block;

    /**
     * code  10,20,30 - Insertion point (in OCS).
     */
    public
    YxxfGfxPointW               inspnt          = new YxxfGfxPointW();

    /**
     * code  41,42,43 - Scale factor. (optional; default = (1, 1, 1)).
     */
    public
    YxxfGfxPointW               scale           = new YxxfGfxPointW(1, 1, 1);

    /**
     * code  44 - Column spacing. (optional default = 0).
     */
    public
    double                      colSpacing      = 0.0;

    /**
     * code  45 - Row spacing. (optional default = 0).
     */
    public
    double                      rowSpacing      = 0.0;

    /**
     * code  50 - Rotation angle. (optional; default = 0).
     */
    public
    double                      rotang          = 0.0;

    /**
     * code  66 - Variable attributes - follow flag. (optional; default = 0); if the
     *            value of attributes-follow flag is 1, a series of Attribute
     *            entities is expected to follow the insert, terminated by a
     *            SEQEND entity.
     */
    public
    int                         attFollow       = 0;

    /**
     * code  70 - Column count. (optional default = 1).
     */
    public
    int                         colCount        = 1;

    /**
     * code  71 - Row count. (optional default = 1).
     */
    public
    int                         rowCount        = 1;

    /**
     * code 210,220,230 - Extrusion direction. 
     *          Present if the extrusion direction is
     *          not parallel to the world Z axis.
     */
    public
    YxxfGfxPointW               xtruDir         = new YxxfGfxPointW(0, 0, 1);


    // Calculated items

    /**
     * Transmat for insert. Computed during get or load.
     */
    public
    YxxfGfxMatrix               M_insert        = null;

    /**
     *            Insertion point (transmat).
     */
    public
    YxxfGfxPointW               Ip              = new YxxfGfxPointW();


    /**
     * Attrib entities list.
     */
    private
    Vector                      attribEntities  = new Vector();


    /**
     * Constructor (empty).
     */
    public
    YxxfEntInsert()
    {
    }


    /**
     * Construct insert and set block.
     * @param block The table block.
     */
    public
    YxxfEntInsert(YxxfEntBlock block)
    {
        setBlock(block);
    }


    /**
     * Set block
     * @param block The table block.
     */
    public
    void setBlock(YxxfEntBlock block)
    {
        this.block = block;
    }


    /**
     * Draw insert.
     * @param gc The graphics context.
     */
    // TODO: implement MINSERT
    // TODO: implement XREF
    public
    void draw(YxxfGfxContext gc)
    {
        if (gc.setupInsertPush(this) == false)
            return;

        gc.pushModelStack(M_insert, Ip, block.basepnt);


        for (int nextToDraw = 0; ; )
        {
            YxxfEnt ent = (YxxfEnt)block.nextEntity(nextToDraw);
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


        //
        // Run the attrib list
        //
        //
        // Run the attrib list
        //
        for (Enumeration E = attribEntities.elements(); E.hasMoreElements(); )
        {
            if (gc.gc_doing != YxxfGfxContext.GC_DOING_DRAWING)
                break;

            ((YxxfEntAttrib)E.nextElement()).draw(gc);
        }


        // Restore insert stack
        gc.popInsertStack();
    }


    /**
     * Calculate insert transmat.
     * @param D The drawing.
     */
    public
    void calc(Yxxf D)
    {
        //
        // connect
        //
        hdr_calc(D);


        //
        // setup insert transform
        //
        YxxfGfxPointW Ax = new YxxfGfxPointW();
        YxxfGfxPointW Ay = new YxxfGfxPointW();
        YxxfGfxPointW Az = new YxxfGfxPointW();

        YxxfGfxPointW B0 = new YxxfGfxPointW();
        YxxfGfxPointW Bz = new YxxfGfxPointW();

//      YxxfGfxPointW Ip = new YxxfGfxPointW();
//      YxxfGfxPointW Bp = new YxxfGfxPointW();


        // Initialize insert matrix
        if (M_insert == null)
            M_insert = new YxxfGfxMatrix();
        else
            M_insert.mtxSetIdentity();


        // Calculate Ax, Ay, Az axes from xtruDir using Arbitrary Axis Algorithm
        Az.set(xtruDir);
        Az.normalize();
        YxxfGfxPointW.calcAAA(Ax, Ay, Az);

        // Apply new rotation
        M_insert.mtxRotateAxes_World_to_Local(Ax, Ay, Az);


        // Calculate insertion point Ip from inspnt
        // Applies the current insert rotation
        Ip.set(inspnt);
        M_insert.mtxTransformPoint(Ip);


        // Apply new scale
        M_insert.mtxSetIdentity();
        M_insert.mtxScale(scale, YxxfGfxPointW.W0);


        // Reapply new rotation
        M_insert.mtxRotateAxes_World_to_Local(Ax, Ay, Az);


        // Apply twist
        M_insert.mtxTransformPoint(B0.set(YxxfGfxPointW.W0));
        M_insert.mtxTransformPoint(Bz.set(YxxfGfxPointW.Wz));
        Bz.normalize();

        M_insert.mtxRotate(B0, Bz, rotang * (Math.PI / 180.0));


        // Calculate block base point Bp from basepnt
        // Applies the current insert rotation, scale and twist
//      Bp.set(block.basepnt);
//      M_insert.mtxTransformPoint(Bp);


        // Apply new translate (insertion point)
//      M_insert.mtxTranslate(Ip);
//      M_insert.mtxTranslateInverse(Bp);
    }




    /**
     * Add a drawing attribute to the list.
     * @param attrib The drawing attribute.
     */
    public
    void addAttrib(YxxfEntAttrib attrib)
    {
        attribEntities.addElement(attrib);
    }


    /**
     * Get the number of entries in the attribute list.
     * @return The attribute list size.
     */
    public
    int getAttribEntitiesSize()
    {
        return attribEntities.size();
    }


    /**
     * Clear drawing attributes list.
     */
    public
    void clearAttrib()
    {
        attribEntities.removeAllElements();
    }
}

