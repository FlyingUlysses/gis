//==============================================================================
// YxxfEntVertex.java
//
// VERTEX entity
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfEntVertex.java,v 1.15 2003/05/08 11:24:03 ekarlo Exp $
// $Log: YxxfEntVertex.java,v $
// Revision 1.15  2003/05/08 11:24:03  ekarlo
// Remove warnings.
//
// Revision 1.14  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.13  2001/10/10 03:37:28  ekarlo
// Update copyright.
//
// Revision 1.12  2000-10-17 01:43:50-06  ekarlo
// Change package paths to lower case.
//
// Revision 1.11  1999-09-29 17:05:36-06  walter
// Added JavaDoc comments.
//
// Revision 1.10  1999-07-09 14:10:50-06  ekarlo
// Rearrange package names.
//
// Revision 1.9  1999/06/20  22:34:10  ekarlo
// Rearrange package names.
//
// Revision 1.8  1999/02/08  05:11:21  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.7  1998/07/12  00:12:45  ekarlo
// 1) Add width coded flags.
// 2) Use polyline matrix.
//
// Revision 1.6  1997/08/30  14:15:28  ekarlo
// Precalculate.
//
// Revision 1.5  1997/07/21  22:41:47  ekarlo
// Make fields public for static get.
//
// Revision 1.4  1996/09/13  05:59:24  ekarlo
// Use new base entity class structure.
//
// Revision 1.3  1996/08/31  02:32:13  ekarlo
// Draw ellipse - working - needs optimization.
//
// Revision 1.2  1996/07/30  06:55:01  ekarlo
// Use point class.
//
// Revision 1.1  1996/07/02  02:20:21  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


/**
 * VERTEX entity.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfEntVertex extends YxxfEntHeader implements YxxfEnt
{
    /**
     * code  10,20,30 -
     *            Location point. (in OCS when 2D, and WCS when 3D).
     */
    public
    YxxfGfxPointW               pnt             = new YxxfGfxPointW();

    /**
     * code  40 - Starting width. (optional; default is 0).
     */
    public
    double                      begwidth        = 0.0;
    /**
     * Starting width coded - true if code 40 is not just default.
     */
    public
    boolean                     begwidth_set    = false;
    /**
     * code  41 - Ending width. (optional; default is 0).
     */
    public
    double                      endwidth        = 0.0;
    /**
     *            Ending width coded - true if code 41 is not just default.
     */
    public
    boolean                     endwidth_set    = false;

    /**
     * code  42 - Bulge (optional; default is 0). The bulge is the tangent of
     *            one fourth the included angle for an arc segment made
     *            negative if the arc goes clockwise from the start point to
     *            the endpoint.  A bulge of 0 indicates a straight segment,
     *            and a bulge of 1 is a semicircle.
     */
    public
    double                      bulge           = 0.0;

    /**
     * code  50 - Curve fit tangent direction.
     */
    public
    double                      tandir          = 0.0;

    /**
     * code  70 - Vertex flags.
     * <UL>
     *   <LI>  1 = Extra vertex created by curve-fitting.
     *   <LI>  2 = Curve-fit tangent defined for this vertex.  A curve-fit
     *             tangent direction of 0 may be omitted from DXF output
     *             but is significant if this bit is set.
     *   <LI>  4 = Not used.
     *   <LI>  8 = Spline vertex created by spline-fitting.
     *   <LI> 16 = Spline frame control point.
     *   <LI> 32 = 3D polyline vertex.
     *   <LI> 64 = 3D polygon mesh.
     *   <LI>128 = Polyface mesh vertex.
     * </UL>
     */
    public
    int                         flags           = 0;

    /**
     * code  71 - Polyface mesh vertex index.
     *            Optional. Present only if nonzero.
     */
    public
    int                         meshidx1        = 0;
    /**
     * code  72 - Polyface mesh vertex index.
     *            Optional. Present only if nonzero.
     */
    public
    int                         meshidx2        = 0;
    /**
     * code  73 - Polyface mesh vertex index.
     *            Optional. Present only if nonzero.
     */
    public
    int                         meshidx3        = 0;
    /**
     * code  74 - Polyface mesh vertex index.
     *            Optional. Present only if nonzero.
     */
    public
    int                         meshidx4        = 0;



    // Calculated items

    /**
     * Transmat for vertex - computed during get or load.
     */
    public
    YxxfGfxPointW               center          = null;
    /**
     * radius TODO.
     */
    public
    double                      radius          = 0.0;
    /**
     * Begin angle.
     */
    public
    double                      begang          = 0.0;
    /**
     * TODO.
     */
    public
    double                      swgang          = 0.0;

    /**
     * Draw vertex. Disabled. Draw code in polyline.
     * @param gc The Graphics context.
     */
    public
    void draw(YxxfGfxContext gc)
    {
        // draw code in polyline
    }


    /**
     * Calculate point transmat.
     * @param D The drawing.
     */
    public
    void calc(Yxxf D)
    {
        //
        // connect
        //
        hdr_calc(D);

        // calculation code in polyline
    }
}



