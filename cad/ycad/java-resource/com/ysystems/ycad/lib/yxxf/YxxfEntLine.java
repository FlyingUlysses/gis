//==============================================================================
// YxxfEntLine.java
//
// LINE entity
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfEntLine.java,v 1.23 2003/05/08 11:24:03 ekarlo Exp $
// $Log: YxxfEntLine.java,v $
// Revision 1.23  2003/05/08 11:24:03  ekarlo
// Remove warnings.
//
// Revision 1.22  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.21  2000/10/17 07:43:54  ekarlo
// Change package paths to lower case.
//
// Revision 1.20  1999-09-29 17:04:30-06  walter
// Added JavaDoc comments.
//
// Revision 1.19  1999-07-09 14:10:50-06  ekarlo
// Rearrange package names.
//
// Revision 1.18  1999/06/29  19:53:22  ekarlo
// User Interface - phase 2.
//
// Revision 1.17  1999/06/20  22:34:10  ekarlo
// Rearrange package names.
//
// Revision 1.16  1999/02/08  05:11:14  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.15  1998/08/21  20:20:06  ekarlo
// Eliminate use of global work arrays in matrix calculations
// and use local doubles instead of arrays.
//
// Revision 1.14  1998/07/12  00:09:54  ekarlo
// Linetype, thickness - done.
//
// Revision 1.13  1997/12/13  18:51:36  ekarlo
// Move work matrix arrays out of YxxfGfxMatrix Objects.
//
// Revision 1.12  1997/08/30  14:17:34  ekarlo
// Change matrix initialization.
// Use new drawline method.
//
// Revision 1.11  1997/07/21  22:40:07  ekarlo
// Make fields public for static get.
//
// Revision 1.10  1996/10/26  00:42:38  ekarlo
// Correct color and layer handling.
//
// Revision 1.9  1996/09/27  09:30:07  ekarlo
// Remove unused code.
//
// Revision 1.8  1996/09/26  18:09:09  ekarlo
// Implement thickness.
//
// Revision 1.7  1996/09/26  02:02:31  ekarlo
// Implement proper color activity.
//
// Revision 1.6  1996/09/13  05:58:05  ekarlo
// Use new base entity class structure.
//
// Revision 1.5  1996/08/18  02:16:32  ekarlo
// Begin change to use new transmat.
//
// Revision 1.4  1996/08/14  19:32:33  ekarlo
// Add constructor with arguments to generate line.
//
// Revision 1.3  1996/07/30  06:53:00  ekarlo
// Use point class.
//
// Revision 1.2  1996/07/06  20:23:40  ekarlo
// 1) Use Point class.
// 2) Use own drawLine.
// 3) Remove unused code.
//
// Revision 1.1  1996/07/02  02:20:20  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


/**
 * LINE entity.
 */
public class YxxfEntLine extends YxxfEntHeader implements YxxfEnt
{
    /**
     * code  10,20,30 -
     *            Start point (in WCS).
     */
    public
    YxxfGfxPointW               begpnt          = new YxxfGfxPointW();

    /**
     * code  11,21,31 -
     *            End point (in WCS).
     */
    public
    YxxfGfxPointW               endpnt          = new YxxfGfxPointW();

    /**
     * code  39 - Thickness (optional; default = 0).
     */
    public
    double                      thickness       = 0.0;

    /**
     * code 210,220,230 -
     *   Extrusion direction. Present if the entity's extrusion direction
     *   is not parallel to the WCS Z axis (Optional; default = 0, 0, 1).
     */
    public
    YxxfGfxPointW               xtruDir         = new YxxfGfxPointW(0, 0, 1);


    // Calculated items

    /**
     * Transmat for line - computed during get or load
     */
    private
    YxxfGfxMatrix               M_line          = null;


    /**
     * Constructor (empty).
     */
    public
    YxxfEntLine()
    {
    }


    /**
     * Constructor Create line and set end points.
     * @param begpnt The begin point.
     * @param endpnt The end point.
     */
    public
    YxxfEntLine(YxxfGfxPointW begpnt, YxxfGfxPointW endpnt)
    {
        this.begpnt.set(begpnt);
        this.endpnt.set(endpnt);
    }


    /**
     * Draw line.
     * @param gc The graphics context.
     */
    public
    void draw(YxxfGfxContext gc)
    {
        if (gc.setupEntity(this, null) == false)
            return;

        if (thickness != 0.0)
        {
            gc.drawLine__MCS__ltyp__thck__wid_none(begpnt, endpnt, thickness, M_line);
        }
        else
        {
            gc.drawLine__MCS__ltyp__flat__wid_none(begpnt, endpnt);
        }
    }


    /**
     * Calculate line transmat.
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


        //
        // Setup line transform
        //

        // Initialize line matrix
        if (M_line == null)
            M_line = new YxxfGfxMatrix();
        else
            M_line.mtxSetIdentity();


        // Calculate Ax, Ay, Az axes from xtruDir using Arbitrary Axis Algorithm
        Az.set(xtruDir);
        Az.normalize();
        YxxfGfxPointW.calcAAA(Ax, Ay, Az);


        // Apply rotation
        M_line.mtxRotateAxes_World_to_Local(Ax, Ay, Az);
    }
}

