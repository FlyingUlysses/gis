//==============================================================================
// YxxfEntLwpolyline.java
//
// LWPOLYLINE entity
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfEntLwpolyline.java,v 1.5 2003/05/08 11:24:02 ekarlo Exp $
// $Log: YxxfEntLwpolyline.java,v $
// Revision 1.5  2003/05/08 11:24:02  ekarlo
// Remove warnings.
//
// Revision 1.4  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.3  2000/10/17 07:43:54  ekarlo
// Change package paths to lower case.
//
// Revision 1.2  1999-09-22 22:56:24-06  walter
// Added JavaDoc comments.
//
// Revision 1.1  1999-08-07 15:22:11-06  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


/**
 * LWPOLYLINE entity.  It is implemented as a normal
 * polyline.
 * @author Ed Karlo - Y Systems, LLC
 */

public class YxxfEntLwpolyline extends YxxfEntHeader implements YxxfEnt
{
    /**
     * The lightweight polyline is implemented as a normal polyline.
     */
    public
    YxxfEntPolyline             pline           = new YxxfEntPolyline();


    // code  38 - Elevation (optional; default = 0)
    //            <<< in pline.pnt.z >>>
    
    // code  39 - Thickness (optional; default = 0)
    //            <<< in pline.thickness >>>
    
    /**
     * code  43 - Constant width. (optional; default is 0)<br>
     *            Not used if variable width (codes 40 and/or/41) is set.
     */
    public
    double                      constwidth      = 0.0;
    /**
     * Constant width coded. True if code 43 is not just default.
     */
    public
    boolean                     constwidth_set  = false;

    // code  70 - Polyline flag (bit-coded); default is 0
    //              1 = Closed
    //            128 = Plinegen
    //            <<< in pline.flags >>>

    /**
     * code  90 - Number of vertices.
     */
    public
    int                         numvertices     = 0;

    // code  10 - Vertex X coordinates (in OCS)
    //            multiple entries; one entry for each vertex
    //            <<< in pline.vtxEntities >>>

    // code  20 - Vertex Y coordinates (in OCS)
    //            multiple entries; one entry for each vertex
    //            <<< in pline.vtxEntities >>>

    // code  40 - Starting width (optional; default is 0)
    //            multiple entries; one entry for each vertex
    //            Not used if constant width (code 43) is set.
    //            <<< in pline.vtxEntities >>>

    // code  41 - Ending width (optional; default is 0)
    //            multiple entries; one entry for each vertex
    //            Not used if constant width (code 43) is set.
    //            <<< in pline.vtxEntities >>>

    // code  42 - Bulge (optional; default is 0).
    //            multiple entries; one entry for each vertex
    //            <<< in pline.vtxEntities >>>

    // code 210,220,230
    //            Extrusion direction; present if the extrusion direction is
    //            not parallel to the world Z axis
    //            <<< in pline.xtruDir >>>



    /**
     * Draw lwpolyline
     * @param gc The Graphics Context.
     */
    public
    void draw(YxxfGfxContext gc)
    {
        pline.draw(gc);
    }


    /**
     * Calculate lwpolyline transmat
     * @param D The Drawing.
     */
    public
    void calc(Yxxf D)
    {
        pline.calc(D);
    }
}

