//==============================================================================
// YxxfEntCircle.java
//
// CIRCLE entity
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfEntCircle.java,v 1.23 2003/05/08 11:24:02 ekarlo Exp $
// $Log: YxxfEntCircle.java,v $
// Revision 1.23  2003/05/08 11:24:02  ekarlo
// Remove warnings.
//
// Revision 1.22  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.21  2000/10/17 07:43:57  ekarlo
// Change package paths to lower case.
//
// Revision 1.20  1999-09-15 10:08:28-06  walter
// Added JavaDoc comments.
//
// Revision 1.19  1999-08-10 08:07:03-06  ekarlo
// Remove unused var warning.
//
// Revision 1.18  1999/07/09  20:10:50  ekarlo
// Rearrange package names.
//
// Revision 1.17  1999/06/29  19:53:22  ekarlo
// User Interface - phase 2.
//
// Revision 1.16  1999/06/20  22:34:10  ekarlo
// Rearrange package names.
//
// Revision 1.15  1999/02/08  05:11:02  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.14  1998/08/21  20:20:06  ekarlo
// Eliminate use of global work arrays in matrix calculations
// and use local doubles instead of arrays.
//
// Revision 1.13  1998/07/12  00:07:21  ekarlo
// Linetype, thickness - done.
//
// Revision 1.12  1997/12/13  18:51:36  ekarlo
// Move work matrix arrays out of YxxfGfxMatrix Objects.
//
// Revision 1.11  1997/08/30  14:14:38  ekarlo
// Change matrix initialization.
//
// Revision 1.10  1997/07/21  22:40:07  ekarlo
// Make fields public for static get.
//
// Revision 1.9  1996/10/26  00:42:38  ekarlo
// Correct color and layer handling.
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
// Revision 1.5  1996/08/31  02:32:13  ekarlo
// Draw ellipse - working - needs optimization.
//
// Revision 1.4  1996/08/27  12:03:17  ekarlo
// Draw ellipse - continuing.
//
// Revision 1.3  1996/08/25  05:26:19  ekarlo
// Draw ellipse - continuing.
//
// Revision 1.2  1996/08/18  02:16:32  ekarlo
// Begin change to use new transmat.
//
// Revision 1.1  1996/07/02  02:20:20  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


/**
 * CIRCLE entity.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfEntCircle extends YxxfEntHeader implements YxxfEnt
{
    /**
     * code  10,20,30
     *            Center (in OCS).
     */
    public
    YxxfGfxPointW               center          = new YxxfGfxPointW();

    /**
     * code  39 - Thickness (optional; default = 0).
     */
    public
    double                      thickness       = 0.0;

    /**
     * code  40 - Radius.
     */
    public
    double                      radius          = 0.0;

    /**
     * code 210,220,230
     *            Extrusion direction. Present if the extrusion direction is
     *            not parallel to the world Z axis.
     */
    public
    YxxfGfxPointW               xtruDir         = new YxxfGfxPointW(0, 0, 1);


    // Calculated items

    /**
     * Transmat for circle - computed during get or load.
     */
    private
    YxxfGfxMatrix               M_circle        = null;


    /**
     * Draw circle
     * @param gc The Graphics context.
     */
    public
    void draw(YxxfGfxContext gc)
    {
        if (gc.setupEntity(this, M_circle) == false)
            return;

        // Determine type of circle (line type, orientation)
        // (Circles do not have width)
        if (gc.getEntity_ltype_isContinuous())
        {   // Continuous line
            if (gc.check_xtrudir_parallel_to_view())
            {   // Direct face on view - thickness ignored
                gc.drawCircle__ECS__cont__flat__wid_none(center, radius, 0.0, Math.PI + Math.PI);
            }
            else
            {   // Face off view
                if (thickness == 0.0)
                {   // Face off view - no thickness - 2D wire frame only
                    gc.drawCircle__ECS__cont__flat__wid_none(center, radius, 0.0, Math.PI + Math.PI);
                }
                else
                {   // Face off view - with thickness - 3D wire frame only
                    gc.drawCircle__ECS__cont__thck__wid_none(center, radius, 0.0, Math.PI + Math.PI, thickness);
                }
            }
        }
        else
        {  // Pattern line
            if (gc.check_xtrudir_parallel_to_view())
            {   // Direct face on view - thickness ignored
                gc.drawCircle__ECS__ltyp__flat__wid_none(center, radius, 0.0, Math.PI + Math.PI);
            }
            else
            {   // Face off view
                if (thickness == 0.0)
                {   // Face off view - no thickness - 2D wire frame only
                    gc.drawCircle__ECS__ltyp__flat__wid_none(center, radius, 0.0, Math.PI + Math.PI);
                }
                else
                {   // Face off view - with thickness - 3D wire frame only
                    gc.drawCircle__ECS__ltyp__thck__wid_none(center, radius, 0.0, Math.PI + Math.PI, thickness);
                }
            }
        }
    }


    /**
     * Calculate the circle transmat.
     * @param D The drawing
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
        // setup circle transform
        //

        // Initialize circle matrix
        if (M_circle == null)
            M_circle = new YxxfGfxMatrix();
        else
            M_circle.mtxSetIdentity();


        // Calculate Ax, Ay, Az axes from xtruDir using Arbitrary Axis Algorithm
        Az.set(xtruDir);
        Az.normalize();
        YxxfGfxPointW.calcAAA(Ax, Ay, Az);
        //System.out.println("CIRCLE:after calcAAA");
        //System.out.println("CIRCLE:    Ax =" + Ax + ",dist=" + Ax.distance());
        //System.out.println("CIRCLE:    Ay =" + Ay + ",dist=" + Ay.distance());
        //System.out.println("CIRCLE:    Az =" + Az + ",dist=" + Az.distance());


        // Apply rotation
        M_circle.mtxRotateAxes_World_to_Local(Ax, Ay, Az);


//      // Calculate Cp from center
//      // Applies the current center rotation
//      Cp.set(center);
//      M_circle.mtxTransformPoint(Cp);
//      //System.out.println("CIRCLE:    center =" + center + ",dist=" + center.distance());
//      //System.out.println("CIRCLE:    Cp     =" + Cp + ",dist=" + Cp.distance());
//
//
//      // Apply translate (center point)
//      M_circle.mtxTranslate(Cp);
    }
}

