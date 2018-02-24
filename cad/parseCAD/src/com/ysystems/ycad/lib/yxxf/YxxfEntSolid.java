//==============================================================================
// YxxfEntSolid.java
//
// SOLID entity
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfEntSolid.java,v 1.21 2003/05/08 11:24:02 ekarlo Exp $
// $Log: YxxfEntSolid.java,v $
// Revision 1.21  2003/05/08 11:24:02  ekarlo
// Remove warnings.
//
// Revision 1.20  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.19  2000/10/17 07:43:51  ekarlo
// Change package paths to lower case.
//
// Revision 1.18  1999-09-29 17:04:58-06  walter
// Added JavaDoc comments.
//
// Revision 1.17  1999-07-09 14:10:50-06  ekarlo
// Rearrange package names.
//
// Revision 1.16  1999/06/29  19:54:51  ekarlo
// User Interface - phase 2.
//
// Revision 1.15  1999/06/20  22:34:10  ekarlo
// Rearrange package names.
//
// Revision 1.14  1999/02/08  05:11:14  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.13  1998/08/21  20:20:06  ekarlo
// Eliminate use of global work arrays in matrix calculations
// and use local doubles instead of arrays.
//
// Revision 1.12  1998/07/12  00:11:40  ekarlo
// Thickness - done.
//
// Revision 1.11  1997/12/14  16:46:15  ekarlo
// Do solid fills on 0 thickness solids.
//
// Revision 1.10  1997/12/13  18:59:21  ekarlo
// Move work matrix arrays out of YxxfGfxMatrix Objects.
//
// Revision 1.9  1997/09/11  21:02:24  ekarlo
// Implement extrusion.
//
// Revision 1.8  1997/07/31  12:39:17  ekarlo
// Fix xtruDir default.
//
// Revision 1.7  1997/07/21  22:41:47  ekarlo
// Make fields public for static get.
//
// Revision 1.6  1996/10/26  00:43:44  ekarlo
// Correct color and layer handling.
//
// Revision 1.5  1996/09/26  02:03:46  ekarlo
// Implement proper color activity.
//
// Revision 1.4  1996/09/13  05:59:24  ekarlo
// Use new base entity class structure.
//
// Revision 1.3  1996/08/18  02:16:32  ekarlo
// Begin change to use new transmat.
//
// Revision 1.2  1996/07/02  07:57:20  ekarlo
// Remove invalid field.
//
// Revision 1.1  1996/07/02  02:20:21  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


/**
 * SOLID entity.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfEntSolid extends YxxfEntHeader implements YxxfEnt
{
    /**
     * code  10,20,30 -
     *            First corner.
     */
    public
    YxxfGfxPointW               pnt1            = new YxxfGfxPointW();

    /**
     * code  11,21,31 -
     *            Second corner.
     */
    public
    YxxfGfxPointW               pnt2            = new YxxfGfxPointW();

    /**
     * code  12,22,32 -
     *            Third corner.
     */
    public
    YxxfGfxPointW               pnt3            = new YxxfGfxPointW();

    /**
     * code  13,23,33 -
     *            Fourth corner. If only three corners are entered to define
     *            the SOLID, then the fourth corner coordinate is the same
     *            as the third.
     */
    public
    YxxfGfxPointW               pnt4            = new YxxfGfxPointW();

    /**
     * code  39 - Thickness (optional; default = 0).
     */
    public
    double                      thickness       = 0.0;

    /**
     * code 210,220,230 -
     *            Extrusion direction. Present if the entities extrusion
     *            direction is not parallel to the world Z axis
     *            (Optional; default = (0, 0, 1)).
     */
    public
    YxxfGfxPointW               xtruDir         = new YxxfGfxPointW(0, 0, 1);




    // Calculated items

    /**
     * Number of points in solid - 3 or 4. Calculated.
     */
    private
    int                         numpoints;

    /**
     * Transmat for solid - computed during get or load.
     */
    private
    YxxfGfxMatrix               M_solid         = null;


    /**
     * Draw solid.
     * @param gc The graphics context.
     */
    public
    void draw(YxxfGfxContext gc)
    {
        if (gc.setupEntity(this, M_solid) == false)
            return;

        if (numpoints == 3)
        {
            gc.drawSolid_ECS(pnt1, pnt2, pnt3, thickness);
        }
        else
        {
            gc.drawSolid_ECS(pnt1, pnt2, pnt3, pnt4, thickness);
        }
    }


    /**
     * Calculate solid transmat.
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


        // Set solid type (3 or 4 points)
        if (pnt3.x != pnt4.x || pnt3.y != pnt4.y || pnt3.z != pnt4.z)
        {  // 4 pointer
            numpoints = 4;
        } 
        else 
        {  // 3 pointer
            numpoints = 3;
        }

        
        //
        // Setup solid transform
        //

        // Initialize solid matrix
        if (M_solid == null)
            M_solid = new YxxfGfxMatrix();
        else
            M_solid.mtxSetIdentity();


        // Calculate Ax, Ay, Az axes from xtruDir using Arbitrary Axis Algorithm
        Az.set(xtruDir);
        Az.normalize();
        YxxfGfxPointW.calcAAA(Ax, Ay, Az);


        // Apply rotation
        M_solid.mtxRotateAxes_World_to_Local(Ax, Ay, Az);
    }
}

