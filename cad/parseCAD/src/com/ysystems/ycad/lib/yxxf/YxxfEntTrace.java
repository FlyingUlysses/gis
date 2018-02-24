//==============================================================================
// YxxfEntTrace.java
//
// TRACE entity
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfEntTrace.java,v 1.4 2003/05/08 11:24:03 ekarlo Exp $
// $Log: YxxfEntTrace.java,v $
// Revision 1.4  2003/05/08 11:24:03  ekarlo
// Remove warnings.
//
// Revision 1.3  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.2  2000/10/17 07:43:50  ekarlo
// Change package paths to lower case.
//
// Revision 1.1  1999-10-25 17:55:03-06  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


/**
 * TRACE entity.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfEntTrace extends YxxfEntHeader implements YxxfEnt
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
     *            Fourth corner.
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
     * Transmat for trace - computed during get or load.
     */
    private
    YxxfGfxMatrix               M_trace         = null;


    /**
     * Draw trace.
     * @param gc The graphics context.
     */
    public
    void draw(YxxfGfxContext gc)
    {
        if (gc.setupEntity(this, M_trace) == false)
            return;

        gc.drawSolid_ECS(pnt1, pnt2, pnt3, pnt4, thickness);
    }


    /**
     * Calculate trace transmat.
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
        // Setup trace transform
        //

        // Initialize trace matrix
        if (M_trace == null)
            M_trace = new YxxfGfxMatrix();
        else
            M_trace.mtxSetIdentity();


        // Calculate Ax, Ay, Az axes from xtruDir using Arbitrary Axis Algorithm
        Az.set(xtruDir);
        Az.normalize();
        YxxfGfxPointW.calcAAA(Ax, Ay, Az);


        // Apply rotation
        M_trace.mtxRotateAxes_World_to_Local(Ax, Ay, Az);
    }
}

