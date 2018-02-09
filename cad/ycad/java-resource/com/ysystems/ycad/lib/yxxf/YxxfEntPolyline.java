//==============================================================================
// YxxfEntPolyline.java
//
// POLYLINE entity
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfEntPolyline.java,v 1.39 2003/05/08 11:24:03 ekarlo Exp $
// $Log: YxxfEntPolyline.java,v $
// Revision 1.39  2003/05/08 11:24:03  ekarlo
// Remove warnings.
//
// Revision 1.38  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.37  2002/10/22 02:55:52  ekarlo
// Don't draw zero length wide polyline segments.
// Calculation was in error (not possible).
//
// Revision 1.36  2001-10-09 21:37:26-06  ekarlo
// Update copyright.
//
// Revision 1.35  2001-10-08 07:28:06-06  ekarlo
// Fix polyface mesh edge visibility problem.
//
// Revision 1.34  2001-05-20 02:43:08-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.33  2000-10-17 01:43:52-06  ekarlo
// Change package paths to lower case.
//
// Revision 1.32  1999-09-15 10:09:01-06  walter
// Added JavaDoc comments.
//
// Revision 1.31  1999-08-10 08:08:52-06  ekarlo
// Remove unused var warning.
//
// Revision 1.30  1999/08/07  22:06:29  ekarlo
// Minor reformat.
// Use $SPLFRAME.
//
// Revision 1.29  1999/07/12  00:55:12  ekarlo
// Fix comment.
//
// Revision 1.28  1999/07/09  20:10:50  ekarlo
// Rearrange package names.
//
// Revision 1.27  1999/07/08  12:12:13  ekarlo
// Fix comment.
//
// Revision 1.26  1999/06/29  19:54:51  ekarlo
// User Interface - phase 2.
//
// Revision 1.25  1999/06/20  22:34:10  ekarlo
// Rearrange package names.
//
// Revision 1.24  1999/06/15  04:59:25  ekarlo
// User Interface - phase 1.
//
// Revision 1.23  1999/02/08  05:11:14  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.22  1998/08/21  20:20:06  ekarlo
// Eliminate use of global work arrays in matrix calculations
// and use local doubles instead of arrays.
//
// Revision 1.21  1998/07/12  00:10:35  ekarlo
// 1) Add width coded flags.
// 2) Linetype, thickness, width - done.
//
// Revision 1.20  1998/02/12  19:46:10  ekarlo
// Remove debug code.
//
// Revision 1.19  1998/02/12  19:13:24  ekarlo
// - Draw simple lines when needed
// - Don't check entity setup for each vertex
// - Remove debug code
//
// Revision 1.18  1998/02/12  18:24:48  ekarlo
// Implement 3D polyline.
//
// Revision 1.17  1998/02/12  17:54:27  ekarlo
// Implement polyface mesh.
//
// Revision 1.16  1997/12/13  18:51:36  ekarlo
// Move work matrix arrays out of YxxfGfxMatrix Objects.
//
// Revision 1.15  1997/12/07  20:57:16  ekarlo
// Correct method declaration.
//
// Revision 1.14  1997/08/30  14:15:28  ekarlo
// Precalculate.
//
// Revision 1.13  1997/07/21  22:41:47  ekarlo
// Make fields public for static get.
// Make class vars local.
//
// Revision 1.12  1996/10/26  00:43:44  ekarlo
// Correct color and layer handling.
//
// Revision 1.11  1996/09/26  18:09:36  ekarlo
// Implement thickness.
// Change comments.
//
// Revision 1.10  1996/09/26  02:03:46  ekarlo
// Implement proper color activity.
//
// Revision 1.9  1996/09/14  03:28:01  ekarlo
// Connect layer in add entity methods.
//
// Revision 1.8  1996/09/13  05:59:24  ekarlo
// Use new base entity class structure.
//
// Revision 1.7  1996/09/01  01:31:35  ekarlo
// Implment 3D polygon mesh.
//
// Revision 1.6  1996/08/31  02:32:13  ekarlo
// Draw ellipse - working - needs optimization.
//
// Revision 1.5  1996/08/25  05:26:19  ekarlo
// Draw ellipse - continuing.
//
// Revision 1.4  1996/08/18  02:16:32  ekarlo
// Begin change to use new transmat.
//
// Revision 1.3  1996/08/13  02:24:41  ekarlo
// Change use of scale variable.
//
// Revision 1.2  1996/07/30  06:54:07  ekarlo
// 1) Use point class.
// 2) Use own drawLine().
//
// Revision 1.1  1996/07/02  02:20:21  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


import java.util.*;


/**
 * POLYLINE entity.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfEntPolyline extends YxxfEntHeader implements YxxfEnt
{
    /**
     * code  10,20,30
     *            A "dummy" point. The X and Y coordinates are always 0,
     *            and the Z coordinate is the polyline's elevation (in OCS
     *            when 2D, WCS when 3D).
     */
    public
    YxxfGfxPointW               pnt             = new YxxfGfxPointW();

    /**
     * code  39 - Thickness (optional; default = 0).
     */
    public
    double                      thickness       = 0.0;
    
    /**
     * code  40 - Starting width (optional; default is 0).
     */
    public
    double                      begwidth        = 0.0;
    /**
     *            Starting width coded - true if code 40 is not just default.
     */
    public
    boolean                     begwidth_set    = false;
    /**
     * code  41 - Ending width (optional; default is 0).
     */
    public
    double                      endwidth        = 0.0;
    /**
     *            Ending width coded - true if code 41 is not just default.
     */
    public
    boolean                     endwidth_set    = false;

    /**
     * code  66 - Vertices-follow flag (always 1 for a polyline).
     */
    public
    int                         vtxFollow       = 0;

    /**
     * code  70 - Polyline flag (bit-coded); default is 0.
     * <UL>
     *   <LI>  1 = This is a closed polyline (or a polygon mesh closed in
     *             the M directtion).
     *   <LI>  2 = Curve-fit vertices have been added.
     *   <LI>  4 = Spline-fit vertices have been added.
     *   <LI>  8 = This is a 3D polyline.
     *   <LI> 16 = This is a 3D polygon mesh.
     *   <LI> 32 = The polygon mesh is closed in the N direction.
     *   <LI> 64 = The polyline is a polyface mesh.
     *   <LI>128 = The linetype pattern is generated continuously
     *             around the vertices of this polyline.
     * </UL>
     */
    public
    int                         flags           = 0;

    /**
     * code  71 - Polygon mesh M vertex count (optional; default = 0).
     */
    public
    int                         meshcntM        = 0;
    /**
     * code  72 - Polygon mesh N vertex count (optional; default = 0).
     */
    public
    int                         meshcntN        = 0;

    /**
     * code  73 - Smooth surface M density (optional; default = 0).
     */
    public
    int                         smoothM         = 0;
    /**
     * code  74 - Smooth surface N density (optional; default = 0).
     */
    public
    int                         smoothN         = 0;

    /**
     * code  75 - Curves and smooth surface type (optional; default = 0).
     * <UL>
     *   <LI>0 = No smooth surface fitted.
     *   <LI>5 = Quadratic B-spline surface.
     *   <LI>6 = Cubic B-spline surface.
     *   <LI>8 = Bezier surface.
     * </UL>
     */
    public
    int                         surfType        = 0;

    /**
     * code 210,220,230
     *            Extrusion direction. Present if the extrusion direction is
     *            not parallel to the world Z axis.
     */
    public
    YxxfGfxPointW               xtruDir         = new YxxfGfxPointW(0, 0, 1);

    /**
     * Vertex entities list.
     */
    public
    Vector                      vtxEntities     = new Vector();

    /**
     * Faces list.
     * Used only for Polyface meshes.
     */
    public
    Vector                      vtxFaces        = new Vector();


    // Calculated items

    /**
     * Transmat for polyline - computed during get or load.
     */
    private
    YxxfGfxMatrix               M_polyline      = null;


    /**
     * Draw polyline.
     * Reference: <i>AutoCad Release 13 Developer's Guide</i>,
     *            <i>The AutoCAD Database Book</i>
     * @param gc The Graphics Context.
     */
    public
    void draw(YxxfGfxContext gc)
    {
        if (gc.setupEntity(this, M_polyline) == false)
            return;

        // System.out.println("POLYLINE: draw(),flags=" + flags + ",vcnt=" + vtxEntities.size());


        // This is a 3D polyline
        if ((flags & 8) == 8)
        {
            // System.out.println("POLYLINE: 3D polyline");
            draw_3D_Polyline(gc);
        } else


        // This is a 3D polygon mesh
        if ((flags & 16) == 16)
        {
            // System.out.println("POLYLINE: 3D polygon mesh");
            draw_3D_Polygon_Mesh(gc);
        } else


        // This is a 3D polyface mesh
        if ((flags & 64) == 64)
        {
            // System.out.println("POLYLINE: 3D polyface mesh");
            draw_3D_Polyface_Mesh(gc);
        } else


        // This is a 2D polyline
        {
            // System.out.println("POLYLINE: 2D polyline");
            draw_2D_Polyline(gc);
        }
    }




    /**
     * Draw 2D Polyline.
     * Determine type of 2D polyline based on
     * line type, orientation (fill or wire), thickness.
     *
     * Widths handled individually for each polyline segment
     * in draw_2D_Polyline__ methods.
     * @param gc The Graphics Context.
     */
    private void draw_2D_Polyline(YxxfGfxContext gc)
    {
        // System.out.println("POLYLINE: draw_2D_Polyline()");
        if (gc.getEntity_ltype_isContinuous())
        {   // Continuous line (ends with width may be mitered)
            // System.out.println("POLYLINE: continuous");
            if (gc.check_xtrudir_parallel_to_view())
            {   // Direct face on view - thickness ignored - 2D filled or 2D wire frame (per fillmode)
                // System.out.println("POLYLINE: face on view");
                if (gc.getDrawing().secHeader.fillmode != 0)
                {   // Solid fill - 2D
                    // System.out.println("POLYLINE: solid fill - 2D,fillmode=" + gc.getDrawing().secHeader.fillmode);
                    draw_2D_Polyline__mitr__flat__wid_fill(gc);
                }
                else
                {   // Wire frame - 2D
                    // System.out.println("POLYLINE: wire frame - 2D,fillmode=" + gc.getDrawing().secHeader.fillmode);
                    draw_2D_Polyline__mitr__flat__wid_wire(gc);
                }
            }
            else
            {   // Face off view - thickness applied - 2D or 3D wire frame only
                // System.out.println("POLYLINE: face off view");
                if (thickness == 0.0)
                {   // Wire frame - 2D
                    // System.out.println("POLYLINE: wire frame - 2D,thickness=" + thickness);
                    draw_2D_Polyline__mitr__flat__wid_wire(gc);
                }
                else
                {   // Wire frame - 3D
                    // System.out.println("POLYLINE: wire frame - 2D,thickness=" + thickness);
                    draw_2D_Polyline__mitr__thck__wid_wire(gc);
                }
            }
        }
        else
        {  // Pattern line
            // System.out.println("POLYLINE: pattern");
            if (gc.check_xtrudir_parallel_to_view())
            {   // Direct face on view - thickness ignored - 2D filled or 2D wire frame (per fillmode)
                if (gc.getDrawing().secHeader.fillmode != 0)
                {   // Solid fill - 2D
                    draw_2D_Polyline__ltyp__flat__wid_fill(gc);
                }
                else
                {   // Wire frame - 2D
                    draw_2D_Polyline__ltyp__flat__wid_wire(gc);
                }
            }
            else
            {   // Face off view - thickness applied - 2D or 3D wire frame only
                if (thickness == 0.0)
                {   // Wire frame - 2D
                    draw_2D_Polyline__ltyp__flat__wid_wire(gc);
                }
                else
                {   // Wire frame - 3D
                    draw_2D_Polyline__ltyp__thck__wid_wire(gc);
                }
            }
        }
    }


/* === CONTINUOUS draws not used
    // Draw 2D Polyline
    //
    // LTYPE continous
    // face_on_view == true
    // (thickness is irrelevant)
    // Segments with width are filled.
    //
    private void draw_2D_Polyline__cont__flat__wid_fill(YxxfGfxContext gc)
    {
        // Drawing control
        YxxfEntVertex currv, prevv, strtv;
        Enumeration   E;
        
        // Width values
        double        begwid;
        double        endwid; 

        E = vtxEntities.elements();

        if (!E.hasMoreElements())
            return;

        strtv = prevv = currv = (YxxfEntVertex)E.nextElement();

        while (E.hasMoreElements())
        {
//g         if (gc.gc_doing != YxxfGfxContext.GC_DOING_DRAWING)
//g             return;

            prevv = currv;
            currv = (YxxfEntVertex)E.nextElement();

            // Set vertex width values
            // If not set in the vertex then use the polyline defaults
            begwid = prevv.begwidth_set ? prevv.begwidth : begwidth;
            endwid = prevv.endwidth_set ? prevv.endwidth : endwidth;

            if (prevv.bulge == 0.0)
            {
                // Draw the 2D line
                if (begwid == 0.0 && endwid == 0.0)
                {
                    gc.drawLine__ECS__cont__flat__wid_none(prevv.pnt, currv.pnt);
                }
                else
                {
                    gc.drawLine__ECS__cont__flat__wid_fill(prevv.pnt, currv.pnt,
                                                           begwid, endwid);
                }
            }
            else
            {
                // Draw the 2D polyarc
                if (begwid == 0.0 && endwid == 0.0)
                {
                    gc.drawCircle__ECS__cont__flat__wid_none(prevv.center,
                                                             prevv.radius,
                                                             prevv.begang, prevv.swgang);
                }
                else
                {
                    gc.drawCircle__ECS__cont__flat__wid_fill(prevv.center,
                                                             prevv.radius,
                                                             prevv.begang, prevv.swgang,
                                                             begwid, endwid);
                }
            }
        }

        if ((flags & 1) == 1) // closed
        {
            begwid = currv.begwidth_set ? currv.begwidth : begwidth;
            endwid = currv.endwidth_set ? currv.endwidth : endwidth;

            if (currv.bulge == 0.0)
            {
                // Draw the 2D line
                if (begwid == 0.0 && endwid == 0.0)
                {
                    gc.drawLine__ECS__cont__flat__wid_none(currv.pnt, strtv.pnt);
                }
                else
                {
                    gc.drawLine__ECS__cont__flat__wid_fill(currv.pnt, strtv.pnt,
                                                           begwid, endwid);
                }
            }
            else
            {
                // Draw the 2D polyarc
                if (begwid == 0.0 && endwid == 0.0)
                {
                    gc.drawCircle__ECS__cont__flat__wid_none(currv.center,
                                                             currv.radius,
                                                             currv.begang, currv.swgang);
                }
                else
                {
                    gc.drawCircle__ECS__cont__flat__wid_fill(currv.center,
                                                             currv.radius,
                                                             currv.begang, currv.swgang,
                                                             begwid, endwid);
                }
            }
        }
    }

    
    // Draw 2D Polyline
    //
    // LTYPE continous
    // face_on_view == true
    // (thickness is irrelevant)
    // Segments with width are wire frame.
    //
    private void draw_2D_Polyline__cont__flat__wid_wire(YxxfGfxContext gc)
    {
        // Drawing control
        YxxfEntVertex currv, prevv, strtv;
        Enumeration   E;
        
        // Width values
        double        begwid;
        double        endwid; 

        E = vtxEntities.elements();

        if (!E.hasMoreElements())
            return;

        strtv = prevv = currv = (YxxfEntVertex)E.nextElement();

        while (E.hasMoreElements())
        {
//g         if (gc.gc_doing != YxxfGfxContext.GC_DOING_DRAWING)
//g             return;

            prevv = currv;
            currv = (YxxfEntVertex)E.nextElement();

            // Set vertex width values
            // If not set in the vertex then use the polyline defaults
            begwid = prevv.begwidth_set ? prevv.begwidth : begwidth;
            endwid = prevv.endwidth_set ? prevv.endwidth : endwidth;

            if (prevv.bulge == 0.0)
            {
                // Draw the 2D line
                if (begwid == 0.0 && endwid == 0.0)
                {
                    gc.drawLine__ECS__cont__flat__wid_none(prevv.pnt, currv.pnt);
                }
                else
                {
                    gc.drawLine__ECS__cont__flat__wid_wire(prevv.pnt, currv.pnt,
                                                           begwid, endwid);
                }
            }
            else
            {
                // Draw the 2D polyarc
                if (begwid == 0.0 && endwid == 0.0)
                {
                    gc.drawCircle__ECS__cont__flat__wid_none(prevv.center,
                                                             prevv.radius,
                                                             prevv.begang, prevv.swgang);
                }
                else
                {
                    gc.drawCircle__ECS__cont__flat__wid_wire(prevv.center,
                                                             prevv.radius,
                                                             prevv.begang, prevv.swgang,
                                                             begwid, endwid);
                }
            }
        }

        if ((flags & 1) == 1) // closed
        {
            begwid = currv.begwidth_set ? currv.begwidth : begwidth;
            endwid = currv.endwidth_set ? currv.endwidth : endwidth;

            if (currv.bulge == 0.0)
            {
                // Draw the 2D line
                if (begwid == 0.0 && endwid == 0.0)
                {
                    gc.drawLine__ECS__cont__flat__wid_none(currv.pnt, strtv.pnt);
                }
                else
                {
                    gc.drawLine__ECS__cont__flat__wid_wire(currv.pnt, strtv.pnt,
                                                           begwid, endwid);
                }
            }
            else
            {
                // Draw the 2D polyarc
                if (begwid == 0.0 && endwid == 0.0)
                {
                    gc.drawCircle__ECS__cont__flat__wid_none(currv.center,
                                                             currv.radius,
                                                             currv.begang, currv.swgang);
                }
                else
                {
                    gc.drawCircle__ECS__cont__flat__wid_wire(currv.center,
                                                             currv.radius,
                                                             currv.begang, currv.swgang,
                                                             begwid, endwid);
                }
            }
        }
    }

    
    // Draw 2D Polyline
    //
    // LTYPE continous
    // face_on_view == false
    // (thickness is non-zero)
    // Segments with width are always wire frame.
    //
    private void draw_2D_Polyline__cont__thck__wid_wire(YxxfGfxContext gc)
    {
        // Drawing control
        YxxfEntVertex currv, prevv, strtv;
        Enumeration   E;
        
        // Width values
        double        begwid;
        double        endwid; 

        E = vtxEntities.elements();

        if (!E.hasMoreElements())
            return;

        strtv = prevv = currv = (YxxfEntVertex)E.nextElement();

        while (E.hasMoreElements())
        {
//g         if (gc.gc_doing != YxxfGfxContext.GC_DOING_DRAWING)
//g             return;

            prevv = currv;
            currv = (YxxfEntVertex)E.nextElement();

            // Set vertex width values
            // If not set in the vertex then use the polyline defaults
            begwid = prevv.begwidth_set ? prevv.begwidth : begwidth;
            endwid = prevv.endwidth_set ? prevv.endwidth : endwidth;

            if (prevv.bulge == 0.0)
            {
                // Draw the 2D line
                if (begwid == 0.0 && endwid == 0.0)
                {
                    gc.drawLine__ECS__cont__thck__wid_none(prevv.pnt, currv.pnt,
                                                           thickness);
                }
                else
                {
                    gc.drawLine__ECS__cont__thck__wid_wire(prevv.pnt, currv.pnt,
                                                           thickness,
                                                           begwid, endwid);
                }
            }
            else
            {
                // Draw the 2D polyarc
                if (begwid == 0.0 && endwid == 0.0)
                {
                    gc.drawCircle__ECS__cont__thck__wid_none(prevv.center,
                                                             prevv.radius,
                                                             prevv.begang, prevv.swgang,
                                                             thickness);
                }
                else
                {
                    gc.drawCircle__ECS__cont__thck__wid_wire(prevv.center,
                                                             prevv.radius,
                                                             prevv.begang, prevv.swgang,
                                                             thickness,
                                                             begwid, endwid);
                }
            }
        }

        if ((flags & 1) == 1) // closed
        {
            begwid = currv.begwidth_set ? currv.begwidth : begwidth;
            endwid = currv.endwidth_set ? currv.endwidth : endwidth;

            if (currv.bulge == 0.0)
            {
                // Draw the 2D line
                if (begwid == 0.0 && endwid == 0.0)
                {
                    gc.drawLine__ECS__cont__thck__wid_none(currv.pnt, strtv.pnt,
                                                           thickness);
                }
                else
                {
                    gc.drawLine__ECS__cont__thck__wid_wire(currv.pnt, strtv.pnt,
                                                           thickness,
                                                           begwid, endwid);
                }
            }
            else
            {
                // Draw the 2D polyarc
                if (begwid == 0.0 && endwid == 0.0)
                {
                    gc.drawCircle__ECS__cont__thck__wid_none(currv.center,
                                                             currv.radius,
                                                             currv.begang, currv.swgang,
                                                             thickness);
                }
                else
                {
                    gc.drawCircle__ECS__cont__thck__wid_wire(currv.center,
                                                             currv.radius,
                                                             currv.begang, currv.swgang,
                                                             thickness,
                                                             begwid, endwid);
                }
            }
        }
    }
CONTINUOUS draws not used === */


    /**
     * Draw 2D Polyline.
     * <UL>
     *   <LI>LTYPE continous.
     *   <LI>face_on_view == true.
     *   <LI>(thickness is irrelevant)
     *   <LI>Segments with width are filled.
     *   <LI>Ends with width may be mitered.
     * </UL>
     * @param gc The Graphics Context.
     */
    private void draw_2D_Polyline__mitr__flat__wid_fill(YxxfGfxContext gc)
    {
        // System.out.println("POLYLINE: draw_2D_Polyline__mitr__flat__wid_fill()");
        int esize = vtxEntities.size();
        if (esize <= 1)
            return;
        
        // Drawing control
        YxxfEntVertex prv1v;
        YxxfEntVertex prevv = (YxxfEntVertex)vtxEntities.elementAt(0);
        YxxfEntVertex currv = (YxxfEntVertex)vtxEntities.elementAt(1);
        YxxfEntVertex cur1v;

        // Vertex width values
        // If not set in the vertex then use the polyline defaults
        double        prv1v_begwid = 0.0;
        double        prv1v_endwid = 0.0;
        double        prevv_begwid = prevv.begwidth_set ? prevv.begwidth : begwidth;
        double        prevv_endwid = prevv.endwidth_set ? prevv.endwidth : endwidth;
        double        currv_begwid = currv.begwidth_set ? currv.begwidth : begwidth;
        double        currv_endwid = currv.endwidth_set ? currv.endwidth : endwidth;
        double        cur1v_begwid = 0.0;
        double        cur1v_endwid = 0.0;

        // Actual loop size
        int lsize;

        // Check if closed
        if ((flags & 1) == 1)
        {
            prv1v = (YxxfEntVertex)vtxEntities.elementAt(esize - 1);
            prv1v_begwid = prv1v.begwidth_set ? prv1v.begwidth : begwidth;
            prv1v_endwid = prv1v.endwidth_set ? prv1v.endwidth : endwidth;
            lsize = esize + 1; // One more loop
        }
        else
        {
            prv1v = null;
            lsize = esize;
        }

        
        for (int i = 1; i < lsize; i++)
        {
//g         if (gc.gc_doing != YxxfGfxContext.GC_DOING_DRAWING)
//g             return;

            // Determine cur1v
            if (i == (esize - 1)) // Last non-closing loop
            {
                cur1v = ((flags & 1) == 1) ? (YxxfEntVertex)vtxEntities.elementAt(0) : null;
            }
            else
            if (i == esize) // Closing loop
            {
                cur1v = (YxxfEntVertex)vtxEntities.elementAt(1);
            }
            else
            {   // Middle
                cur1v = (YxxfEntVertex)vtxEntities.elementAt(i + 1);
            }
            
            if (cur1v != null)
            {
                cur1v_begwid = cur1v.begwidth_set ? cur1v.begwidth : begwidth;
                cur1v_endwid = cur1v.endwidth_set ? cur1v.endwidth : endwidth;
            }
            else
            {
                cur1v_begwid = 0.0;
                cur1v_endwid = 0.0;
            }
        
            
            // ---
            if (prevv.bulge == 0.0)
            {
                // Draw the 2D line
                if (prevv_begwid == 0.0 && prevv_endwid == 0.0)
                {
                    gc.drawLine__ECS__cont__flat__wid_none(prevv.pnt, currv.pnt);
                }
                else
                {   // Miter
                    if (calc_2D_Polyline__mitr(gc,
                                               prv1v, prv1v_begwid, prv1v_endwid,
                                               prevv, prevv_begwid, prevv_endwid,
                                               currv, currv_begwid, currv_endwid,
                                               cur1v))
                    {                                           
                        gc.fillPolygon_ECS(gc.pw1, gc.pw2, gc.pw3, gc.pw4);
                    }
                }
            }
            else
            {
                // Draw the 2D polyarc
                if (prevv_begwid == 0.0 && prevv_endwid == 0.0)
                {
                    gc.drawCircle__ECS__cont__flat__wid_none(prevv.center,
                                                             prevv.radius,
                                                             prevv.begang, prevv.swgang);
                }
                else
                {
                    gc.drawCircle__ECS__cont__flat__wid_fill(prevv.center,
                                                             prevv.radius,
                                                             prevv.begang, prevv.swgang,
                                                             prevv_begwid, prevv_endwid);
                }
            }
            // ---
            
        
            prv1v = prevv; prevv = currv; currv = cur1v;
            prv1v_begwid = prevv_begwid; prevv_begwid = currv_begwid; currv_begwid = cur1v_begwid; 
            prv1v_endwid = prevv_endwid; prevv_endwid = currv_endwid; currv_endwid = cur1v_endwid; 
        }
    }

    
    /**
     * Draw 2D Polyline.
     * <UL>
     *   <LI>LTYPE continous.
     *   <LI>face_on_view == true.
     *   <LI>(thickness is irrelevant).
     *   <LI>Segments with width are wire frame.
     *   <LI>Ends with width may be mitered.
     * </UL>
     * @param gc The Graphics Context.
     */
    private void draw_2D_Polyline__mitr__flat__wid_wire(YxxfGfxContext gc)
    {
        // System.out.println("POLYLINE: draw_2D_Polyline__mitr__flat__wid_wire()");
        int esize = vtxEntities.size();
        if (esize <= 1)
            return;
        
        // Drawing control
        YxxfEntVertex prv1v;
        YxxfEntVertex prevv = (YxxfEntVertex)vtxEntities.elementAt(0);
        YxxfEntVertex currv = (YxxfEntVertex)vtxEntities.elementAt(1);
        YxxfEntVertex cur1v;

        // Vertex width values
        // If not set in the vertex then use the polyline defaults
        double        prv1v_begwid = 0.0;
        double        prv1v_endwid = 0.0;
        double        prevv_begwid = prevv.begwidth_set ? prevv.begwidth : begwidth;
        double        prevv_endwid = prevv.endwidth_set ? prevv.endwidth : endwidth;
        double        currv_begwid = currv.begwidth_set ? currv.begwidth : begwidth;
        double        currv_endwid = currv.endwidth_set ? currv.endwidth : endwidth;
        double        cur1v_begwid = 0.0;
        double        cur1v_endwid = 0.0;

        // Actual loop size
        int lsize;

        // Check if closed
        if ((flags & 1) == 1)
        {
            prv1v = (YxxfEntVertex)vtxEntities.elementAt(esize - 1);
            prv1v_begwid = prv1v.begwidth_set ? prv1v.begwidth : begwidth;
            prv1v_endwid = prv1v.endwidth_set ? prv1v.endwidth : endwidth;
            lsize = esize + 1; // One more loop
        }
        else
        {
            prv1v = null;
            lsize = esize;
        }

        
        for (int i = 1; i < lsize; i++)
        {
//g         if (gc.gc_doing != YxxfGfxContext.GC_DOING_DRAWING)
//g             return;

            // Determine cur1v
            if (i == (esize - 1)) // Last non-closing loop
            {
                cur1v = ((flags & 1) == 1) ? (YxxfEntVertex)vtxEntities.elementAt(0) : null;
            }
            else
            if (i == esize) // Closing loop
            {
                cur1v = (YxxfEntVertex)vtxEntities.elementAt(1);
            }
            else
            {   // Middle
                cur1v = (YxxfEntVertex)vtxEntities.elementAt(i + 1);
            }
            
            if (cur1v != null)
            {
                cur1v_begwid = cur1v.begwidth_set ? cur1v.begwidth : begwidth;
                cur1v_endwid = cur1v.endwidth_set ? cur1v.endwidth : endwidth;
            }
            else
            {
                cur1v_begwid = 0.0;
                cur1v_endwid = 0.0;
            }
        
            
            // ---
            if (prevv.bulge == 0.0)
            {
                // Draw the 2D line
                if (prevv_begwid == 0.0 && prevv_endwid == 0.0)
                {
                    gc.drawLine__ECS__cont__flat__wid_none(prevv.pnt, currv.pnt);
                }
                else
                {   // Miter
                    if (calc_2D_Polyline__mitr(gc,
                                               prv1v, prv1v_begwid, prv1v_endwid,
                                               prevv, prevv_begwid, prevv_endwid,
                                               currv, currv_begwid, currv_endwid,
                                               cur1v))
                    {
                        gc.drawLine__ECS__cont__flat__wid_none(gc.pw1, gc.pw2);
                        gc.drawLine__ECS__cont__flat__wid_none(gc.pw2, gc.pw4);
                        gc.drawLine__ECS__cont__flat__wid_none(gc.pw4, gc.pw3);
                        gc.drawLine__ECS__cont__flat__wid_none(gc.pw3, gc.pw1);
                    }
                }
            }
            else
            {
                // Draw the 2D polyarc
                if (prevv_begwid == 0.0 && prevv_endwid == 0.0)
                {
                    gc.drawCircle__ECS__cont__flat__wid_none(prevv.center,
                                                             prevv.radius,
                                                             prevv.begang, prevv.swgang);
                }
                else
                {
                    gc.drawCircle__ECS__cont__flat__wid_wire(prevv.center,
                                                             prevv.radius,
                                                             prevv.begang, prevv.swgang,
                                                             prevv_begwid, prevv_endwid);
                }
            }
            // ---
            
        
            prv1v = prevv; prevv = currv; currv = cur1v;
            prv1v_begwid = prevv_begwid; prevv_begwid = currv_begwid; currv_begwid = cur1v_begwid; 
            prv1v_endwid = prevv_endwid; prevv_endwid = currv_endwid; currv_endwid = cur1v_endwid; 
        }
    }

    
    /**
     * Draw 2D Polyline.
     * <UL>
     *   <LI>LTYPE continous.
     *   <LI>face_on_view == false.
     *   <LI>(thickness is non-zero).
     *   <LI>Segments with width are always wire frame.
     *   <LI>Ends with width may be mitered.
     * </UL>
     * @param gc The Graphics Context.
     */
    private void draw_2D_Polyline__mitr__thck__wid_wire(YxxfGfxContext gc)
    {
        // System.out.println("POLYLINE: draw_2D_Polyline__mitr__thck__wid_wire()");
        int esize = vtxEntities.size();
        if (esize <= 1)
            return;
        
        // Drawing control
        YxxfEntVertex prv1v;
        YxxfEntVertex prevv = (YxxfEntVertex)vtxEntities.elementAt(0);
        YxxfEntVertex currv = (YxxfEntVertex)vtxEntities.elementAt(1);
        YxxfEntVertex cur1v;

        // Vertex width values
        // If not set in the vertex then use the polyline defaults
        double        prv1v_begwid = 0.0;
        double        prv1v_endwid = 0.0;
        double        prevv_begwid = prevv.begwidth_set ? prevv.begwidth : begwidth;
        double        prevv_endwid = prevv.endwidth_set ? prevv.endwidth : endwidth;
        double        currv_begwid = currv.begwidth_set ? currv.begwidth : begwidth;
        double        currv_endwid = currv.endwidth_set ? currv.endwidth : endwidth;
        double        cur1v_begwid = 0.0;
        double        cur1v_endwid = 0.0;

        // Actual loop size
        int lsize;

        // Check if closed
        if ((flags & 1) == 1)
        {
            prv1v = (YxxfEntVertex)vtxEntities.elementAt(esize - 1);
            prv1v_begwid = prv1v.begwidth_set ? prv1v.begwidth : begwidth;
            prv1v_endwid = prv1v.endwidth_set ? prv1v.endwidth : endwidth;
            lsize = esize + 1; // One more loop
        }
        else
        {
            prv1v = null;
            lsize = esize;
        }

        
        for (int i = 1; i < lsize; i++)
        {
//g         if (gc.gc_doing != YxxfGfxContext.GC_DOING_DRAWING)
//g             return;

            // Determine cur1v
            if (i == (esize - 1)) // Last non-closing loop
            {
                cur1v = ((flags & 1) == 1) ? (YxxfEntVertex)vtxEntities.elementAt(0) : null;
            }
            else
            if (i == esize) // Closing loop
            {
                cur1v = (YxxfEntVertex)vtxEntities.elementAt(1);
            }
            else
            {   // Middle
                cur1v = (YxxfEntVertex)vtxEntities.elementAt(i + 1);
            }
            
            if (cur1v != null)
            {
                cur1v_begwid = cur1v.begwidth_set ? cur1v.begwidth : begwidth;
                cur1v_endwid = cur1v.endwidth_set ? cur1v.endwidth : endwidth;
            }
            else
            {
                cur1v_begwid = 0.0;
                cur1v_endwid = 0.0;
            }
        
            
            // ---
            if (prevv.bulge == 0.0)
            {
                // Draw the 2D line
                if (prevv_begwid == 0.0 && prevv_endwid == 0.0)
                {
                    gc.drawLine__ECS__cont__thck__wid_none(prevv.pnt, currv.pnt,
                                                           thickness);
                }
                else
                {   // Miter
                    if (calc_2D_Polyline__mitr(gc,
                                               prv1v, prv1v_begwid, prv1v_endwid,
                                               prevv, prevv_begwid, prevv_endwid,
                                               currv, currv_begwid, currv_endwid,
                                               cur1v))
                    {
                        // base
                        gc.drawLine__ECS__cont__flat__wid_none(gc.pw1, gc.pw2);
                        gc.drawLine__ECS__cont__flat__wid_none(gc.pw2, gc.pw4);
                        gc.drawLine__ECS__cont__flat__wid_none(gc.pw4, gc.pw3);
                        gc.drawLine__ECS__cont__flat__wid_none(gc.pw3, gc.pw1);

                        // sides
                        gc.drawLine__ECS__cont__flat__wid_none(gc.pw1.x, gc.pw1.y, gc.pw1.z,
                                                            gc.pw1.x, gc.pw1.y, gc.pw1.z + thickness);
                        gc.drawLine__ECS__cont__flat__wid_none(gc.pw2.x, gc.pw2.y, gc.pw2.z,
                                                            gc.pw2.x, gc.pw2.y, gc.pw2.z + thickness);
                        gc.drawLine__ECS__cont__flat__wid_none(gc.pw3.x, gc.pw3.y, gc.pw3.z,
                                                            gc.pw3.x, gc.pw3.y, gc.pw3.z + thickness);
                        gc.drawLine__ECS__cont__flat__wid_none(gc.pw4.x, gc.pw4.y, gc.pw4.z,
                                                            gc.pw4.x, gc.pw4.y, gc.pw4.z + thickness);

                        // top
                        gc.drawLine__ECS__cont__flat__wid_none(gc.pw1.x, gc.pw1.y, gc.pw1.z + thickness,
                                                            gc.pw2.x, gc.pw2.y, gc.pw2.z + thickness);
                        gc.drawLine__ECS__cont__flat__wid_none(gc.pw2.x, gc.pw2.y, gc.pw2.z + thickness,
                                                            gc.pw4.x, gc.pw4.y, gc.pw4.z + thickness);
                        gc.drawLine__ECS__cont__flat__wid_none(gc.pw4.x, gc.pw4.y, gc.pw4.z + thickness,
                                                            gc.pw3.x, gc.pw3.y, gc.pw3.z + thickness);
                        gc.drawLine__ECS__cont__flat__wid_none(gc.pw3.x, gc.pw3.y, gc.pw3.z + thickness,
                                                            gc.pw1.x, gc.pw1.y, gc.pw1.z + thickness);
                    }
                }
            }
            else
            {
                // Draw the 2D polyarc
                if (prevv_begwid == 0.0 && prevv_endwid == 0.0)
                {
                    gc.drawCircle__ECS__cont__thck__wid_none(prevv.center,
                                                             prevv.radius,
                                                             prevv.begang, prevv.swgang,
                                                             thickness);
                }
                else
                {
                    gc.drawCircle__ECS__cont__thck__wid_wire(prevv.center,
                                                             prevv.radius,
                                                             prevv.begang, prevv.swgang,
                                                             thickness,
                                                             prevv_begwid, prevv_endwid);
                }
            }
            // ---
            
        
            prv1v = prevv; prevv = currv; currv = cur1v;
            prv1v_begwid = prevv_begwid; prevv_begwid = currv_begwid; currv_begwid = cur1v_begwid; 
            prv1v_endwid = prevv_endwid; prevv_endwid = currv_endwid; currv_endwid = cur1v_endwid; 
        }
    }


    /**
     * Draw 2D Polyline.
     * <UL>
     *   <LI>LTYPE pattern.
     *   <LI>face_on_view == true.
     *   <LI>(thickness is irrelevant).
     *   <LI>Segments with width are filled.
     * </UL>
     * @param gc The Graphics Context.
     */
    private void draw_2D_Polyline__ltyp__flat__wid_fill(YxxfGfxContext gc)
    {
        // System.out.println("POLYLINE: draw_2D_Polyline__ltyp__flat__wid_fill()");
        // Drawing control
        YxxfEntVertex currv, prevv, strtv;
        Enumeration   E;
        
        // Width values
        double        begwid;
        double        endwid; 

        E = vtxEntities.elements();

        if (!E.hasMoreElements())
            return;

        strtv = prevv = currv = (YxxfEntVertex)E.nextElement();

        while (E.hasMoreElements())
        {
//g         if (gc.gc_doing != YxxfGfxContext.GC_DOING_DRAWING)
//g             return;

            prevv = currv;
            currv = (YxxfEntVertex)E.nextElement();

            // Set vertex width values
            // If not set in the vertex then use the polyline defaults
            begwid = prevv.begwidth_set ? prevv.begwidth : begwidth;
            endwid = prevv.endwidth_set ? prevv.endwidth : endwidth;

            if (prevv.bulge == 0.0)
            {
                // Draw the 2D line
                if (begwid == 0.0 && endwid == 0.0)
                {
                    gc.drawLine__ECS__ltyp__flat__wid_none(prevv.pnt, currv.pnt);
                }
                else
                {
                    gc.drawLine__ECS__ltyp__flat__wid_fill(prevv.pnt, currv.pnt,
                                                           begwid, endwid);
                }
            }
            else
            {
                // Draw the 2D polyarc
                if (begwid == 0.0 && endwid == 0.0)
                {
                    gc.drawCircle__ECS__ltyp__flat__wid_none(prevv.center,
                                                             prevv.radius,
                                                             prevv.begang, prevv.swgang);
                }
                else
                {
                    gc.drawCircle__ECS__ltyp__flat__wid_fill(prevv.center,
                                                             prevv.radius,
                                                             prevv.begang, prevv.swgang,
                                                             begwid, endwid);
                }
            }
        }

        if ((flags & 1) == 1) // closed
        {
            begwid = currv.begwidth_set ? currv.begwidth : begwidth;
            endwid = currv.endwidth_set ? currv.endwidth : endwidth;

            if (currv.bulge == 0.0)
            {
                // Draw the 2D line
                if (begwid == 0.0 && endwid == 0.0)
                {
                    gc.drawLine__ECS__ltyp__flat__wid_none(currv.pnt, strtv.pnt);
                }
                else
                {
                    gc.drawLine__ECS__ltyp__flat__wid_fill(currv.pnt, strtv.pnt,
                                                           begwid, endwid);
                }
            }
            else
            {
                // Draw the 2D polyarc
                if (begwid == 0.0 && endwid == 0.0)
                {
                    gc.drawCircle__ECS__ltyp__flat__wid_none(currv.center,
                                                             currv.radius,
                                                             currv.begang, currv.swgang);
                }
                else
                {
                    gc.drawCircle__ECS__ltyp__flat__wid_fill(currv.center,
                                                             currv.radius,
                                                             currv.begang, currv.swgang,
                                                             begwid, endwid);
                }
            }
        }
    }

    
    /**
     * Draw 2D Polyline.
     * <UL>
     *   <LI>LTYPE pattern.
     *   <LI>face_on_view == true.
     *   <LI>(thickness is irrelevant).
     *   <LI>Segments with width are wire frame.
     * @param gc The Graphics Context.
     * </UL>
     */
    private void draw_2D_Polyline__ltyp__flat__wid_wire(YxxfGfxContext gc)
    {
        // System.out.println("POLYLINE: draw_2D_Polyline__ltyp__flat__wid_wire()");
        // Drawing control
        YxxfEntVertex currv, prevv, strtv;
        Enumeration   E;
        
        // Width values
        double        begwid;
        double        endwid; 

        E = vtxEntities.elements();

        if (!E.hasMoreElements())
            return;

        strtv = prevv = currv = (YxxfEntVertex)E.nextElement();

        while (E.hasMoreElements())
        {
//g         if (gc.gc_doing != YxxfGfxContext.GC_DOING_DRAWING)
//g             return;

            prevv = currv;
            currv = (YxxfEntVertex)E.nextElement();

            // Set vertex width values
            // If not set in the vertex then use the polyline defaults
            begwid = prevv.begwidth_set ? prevv.begwidth : begwidth;
            endwid = prevv.endwidth_set ? prevv.endwidth : endwidth;

            if (prevv.bulge == 0.0)
            {
                // Draw the 2D line
                if (begwid == 0.0 && endwid == 0.0)
                {
                    gc.drawLine__ECS__ltyp__flat__wid_none(prevv.pnt, currv.pnt);
                }
                else
                {
                    gc.drawLine__ECS__ltyp__flat__wid_wire(prevv.pnt, currv.pnt,
                                                           begwid, endwid);
                }
            }
            else
            {
                // Draw the 2D polyarc
                if (begwid == 0.0 && endwid == 0.0)
                {
                    gc.drawCircle__ECS__ltyp__flat__wid_none(prevv.center,
                                                             prevv.radius,
                                                             prevv.begang, prevv.swgang);
                }
                else
                {
                    gc.drawCircle__ECS__ltyp__flat__wid_wire(prevv.center,
                                                             prevv.radius,
                                                             prevv.begang, prevv.swgang,
                                                             begwid, endwid);
                }
            }
        }

        if ((flags & 1) == 1) // closed
        {
            begwid = currv.begwidth_set ? currv.begwidth : begwidth;
            endwid = currv.endwidth_set ? currv.endwidth : endwidth;

            if (currv.bulge == 0.0)
            {
                // Draw the 2D line
                if (begwid == 0.0 && endwid == 0.0)
                {
                    gc.drawLine__ECS__ltyp__flat__wid_none(currv.pnt, strtv.pnt);
                }
                else
                {
                    gc.drawLine__ECS__ltyp__flat__wid_wire(currv.pnt, strtv.pnt,
                                                           begwid, endwid);
                }
            }
            else
            {
                // Draw the 2D polyarc
                if (begwid == 0.0 && endwid == 0.0)
                {
                    gc.drawCircle__ECS__ltyp__flat__wid_none(currv.center,
                                                             currv.radius,
                                                             currv.begang, currv.swgang);
                }
                else
                {
                    gc.drawCircle__ECS__ltyp__flat__wid_wire(currv.center,
                                                             currv.radius,
                                                             currv.begang, currv.swgang,
                                                             begwid, endwid);
                }
            }
        }
    }

    
    /**
     * Draw 2D Polyline.
     * <UL>
     *   <LI>LTYPE pattern.
     *   <LI>face_on_view == false.
     *   <LI>(thickness is non-zero).
     *   <LI>Segments with width are always wire frame.
     * </UL>
     * @param gc The Graphics Context
     */
    private void draw_2D_Polyline__ltyp__thck__wid_wire(YxxfGfxContext gc)
    {
        // System.out.println("POLYLINE: draw_2D_Polyline__ltyp__thck__wid_wire()");
        // Drawing control
        YxxfEntVertex currv, prevv, strtv;
        Enumeration   E;
        
        // Width values
        double        begwid;
        double        endwid; 

        E = vtxEntities.elements();

        if (!E.hasMoreElements())
            return;

        strtv = prevv = currv = (YxxfEntVertex)E.nextElement();

        while (E.hasMoreElements())
        {
//g         if (gc.gc_doing != YxxfGfxContext.GC_DOING_DRAWING)
//g             return;

            prevv = currv;
            currv = (YxxfEntVertex)E.nextElement();

            // Set vertex width values
            // If not set in the vertex then use the polyline defaults
            begwid = prevv.begwidth_set ? prevv.begwidth : begwidth;
            endwid = prevv.endwidth_set ? prevv.endwidth : endwidth;

            if (prevv.bulge == 0.0)
            {
                // Draw the 2D line
                if (begwid == 0.0 && endwid == 0.0)
                {
                    gc.drawLine__ECS__ltyp__thck__wid_none(prevv.pnt, currv.pnt,
                                                           thickness);
                }
                else
                {
                    gc.drawLine__ECS__ltyp__thck__wid_wire(prevv.pnt, currv.pnt,
                                                           thickness,
                                                           begwid, endwid);
                }
            }
            else
            {
                // Draw the 2D polyarc
                if (begwid == 0.0 && endwid == 0.0)
                {
                    gc.drawCircle__ECS__ltyp__thck__wid_none(prevv.center,
                                                             prevv.radius,
                                                             prevv.begang, prevv.swgang,
                                                             thickness);
                }
                else
                {
                    gc.drawCircle__ECS__ltyp__thck__wid_wire(prevv.center,
                                                             prevv.radius,
                                                             prevv.begang, prevv.swgang,
                                                             thickness,
                                                             begwid, endwid);
                }
            }
        }

        if ((flags & 1) == 1) // closed
        {
            begwid = currv.begwidth_set ? currv.begwidth : begwidth;
            endwid = currv.endwidth_set ? currv.endwidth : endwidth;

            if (currv.bulge == 0.0)
            {
                // Draw the 2D line
                if (begwid == 0.0 && endwid == 0.0)
                {
                    gc.drawLine__ECS__ltyp__thck__wid_none(currv.pnt, strtv.pnt,
                                                           thickness);
                }
                else
                {
                    gc.drawLine__ECS__ltyp__thck__wid_wire(currv.pnt, strtv.pnt,
                                                           thickness,
                                                           begwid, endwid);
                }
            }
            else
            {
                // Draw the 2D polyarc
                if (begwid == 0.0 && endwid == 0.0)
                {
                    gc.drawCircle__ECS__ltyp__thck__wid_none(currv.center,
                                                             currv.radius,
                                                             currv.begang, currv.swgang,
                                                             thickness);
                }
                else
                {
                    gc.drawCircle__ECS__ltyp__thck__wid_wire(currv.center,
                                                             currv.radius,
                                                             currv.begang, currv.swgang,
                                                             thickness,
                                                             begwid, endwid);
                }
            }
        }
    }
    
    
    /**
     * Calculate 2D Polyline segment corner points.
     * Result points are returned in gc points pw1, pw2, pw3, pw4.<br>
     * Uses gc work points pw110, pw120, pw121, pw122, pw123, pw124.<br>
     * Uses gc work points pw210, pw220, pw221, pw222, pw223, pw224.<br>
     * Uses gc work points pw310, pw320, pw321, pw322, pw323, pw324.<br>
     * The corner points cannot be calculated if v2 and v3 are the
     * same and the method will return false.
     * @param v1 Vertex 1 - previous - may be null.
     * @param v1begwid Beginning width.
     * @param v1endwid Ending width.
     * @param v2 Vertex 2 - start of segment.
     * @param v2begwid Beginning width.
     * @param v2endwid Ending width.
     * @param v3 Vertex 3 - end of segment.
     * @param v3begwid Beginning width.
     * @param v3endwid Ending width.
     * @param v4 Vertex 4 - following - may be null.
     * @return true if return points valid.
     */
    private
    boolean 
    calc_2D_Polyline__mitr(YxxfGfxContext gc,
                           YxxfEntVertex v1, double v1_begwid, double v1_endwid,
                           YxxfEntVertex v2, double v2_begwid, double v2_endwid,
                           YxxfEntVertex v3, double v3_begwid, double v3_endwid,
                           YxxfEntVertex v4)
    {
        // System.out.println("POLYLINE: calc_2D_Polyline__mitr");
        // System.out.println("    prv1v.pnt=[" + ((v1 == null) ? null : v1.pnt) + "],begwid=" + v1_begwid + ",endwid=" + v1_endwid);
        // System.out.println("    prevv.pnt=[" + ((v2 == null) ? null : v2.pnt) + "],begwid=" + v2_begwid + ",endwid=" + v2_endwid);
        // System.out.println("    currv.pnt=[" + ((v3 == null) ? null : v3.pnt) + "],begwid=" + v3_begwid + ",endwid=" + v3_endwid);
        // System.out.println("    cur1v.pnt=[" + ((v4 == null) ? null : v4.pnt) + "]");
        
        //
        // Find unit vector along prevv-currv line (main line segment of interest)
        //

        // prepare to calculate vector along segment
        gc.pw210.set(v3.pnt.x - v2.pnt.x, v3.pnt.y - v2.pnt.y, v3.pnt.z - v2.pnt.z);
        // System.out.println("    pw210    =[" + gc.pw210 + "]");

        // if v2 and v3 are the same the corner points cannot be found
        if (gc.pw210.x == 0.0 && gc.pw210.y == 0.0 && gc.pw210.z == 0.0)
            return false;

        // pw210 will be length of 1 after normalization
        gc.pw210.normalize();
        // System.out.println("    pw210norm=[" + gc.pw210 + "]");

        // find width vector
        gc.pw220.crossProduct(gc.pw210, YxxfGfxPointW.Wz);
        gc.pw220.normalize();

        v2_begwid /= 2.0;
        v2_endwid /= 2.0;

        // find line points
        gc.pw221.set(v2.pnt.x + (gc.pw220.x * v2_begwid),
                     v2.pnt.y + (gc.pw220.y * v2_begwid),
                     v2.pnt.z + (gc.pw220.z * v2_begwid));
        gc.pw222.set(v2.pnt.x - (gc.pw220.x * v2_begwid),
                     v2.pnt.y - (gc.pw220.y * v2_begwid),
                     v2.pnt.z - (gc.pw220.z * v2_begwid));
        gc.pw223.set(v3.pnt.x + (gc.pw220.x * v2_endwid),
                     v3.pnt.y + (gc.pw220.y * v2_endwid),
                     v3.pnt.z + (gc.pw220.z * v2_endwid));
        gc.pw224.set(v3.pnt.x - (gc.pw220.x * v2_endwid),
                     v3.pnt.y - (gc.pw220.y * v2_endwid),
                     v3.pnt.z - (gc.pw220.z * v2_endwid));


        //
        // Calculate beg points pw1 and pw2
        //

        if (v2_begwid == 0.0)
        {   // no beg width
            gc.pw1.set(v2.pnt);
            gc.pw2.set(v2.pnt);
        }
        else
        
        if (v1 == null)
        {   // no previous vertex
            gc.pw1.set(gc.pw221);
            gc.pw2.set(gc.pw222);
        }
        else
        
        if (v1_endwid == 0.0)
        {   // no miter to single endpoint
            gc.pw1.set(gc.pw221);
            gc.pw2.set(gc.pw222);
        }
        else
        
        // miter the line against a wide line or arc
        
        if (v1.bulge == 0.0)
        {   // a line - calculate against previous line
            if (gc.calcLinesParallel(v1.pnt, v2.pnt, v2.pnt, v3.pnt))
            {   // no miter to parallel lines
                gc.pw1.set(gc.pw221);
                gc.pw2.set(gc.pw222);
            }
            else
            
            {   // calculate miter
                int rc; // return code from intersect calculation
            
                // Find unit vector along prv1v-prevv line
                
                // pw110 will be length of 1
                gc.pw110.set(v2.pnt.x - v1.pnt.x, v2.pnt.y - v1.pnt.y, v2.pnt.z - v1.pnt.z);
                gc.pw110.normalize();

                // find width vector
                gc.pw120.crossProduct(gc.pw110, YxxfGfxPointW.Wz);
                gc.pw120.normalize();

                v1_begwid /= 2.0;
                v1_endwid /= 2.0;

                // find line points
                gc.pw121.set(v1.pnt.x + (gc.pw120.x * v1_begwid),
                             v1.pnt.y + (gc.pw120.y * v1_begwid),
                             v1.pnt.z + (gc.pw120.z * v1_begwid));
                gc.pw122.set(v1.pnt.x - (gc.pw120.x * v1_begwid),
                             v1.pnt.y - (gc.pw120.y * v1_begwid),
                             v1.pnt.z - (gc.pw120.z * v1_begwid));
                gc.pw123.set(v2.pnt.x + (gc.pw120.x * v1_endwid),
                             v2.pnt.y + (gc.pw120.y * v1_endwid),
                             v2.pnt.z + (gc.pw120.z * v1_endwid));
                gc.pw124.set(v2.pnt.x - (gc.pw120.x * v1_endwid),
                             v2.pnt.y - (gc.pw120.y * v1_endwid),
                             v2.pnt.z - (gc.pw120.z * v1_endwid));
                             
                // calc pw1
                rc = gc.calcLinesIntersect(gc.pw121, gc.pw123,
                                           gc.pw221, gc.pw223,
                                           gc.pw1);
                if (rc != YxxfGfxContext.CALCLINEX_DO_INTERSECT)
                {
                    gc.pw1.set(gc.pw221);
                }
                           
                // calc pw2
                rc = gc.calcLinesIntersect(gc.pw122, gc.pw124,
                                           gc.pw222, gc.pw224,
                                           gc.pw2);
                if (rc != YxxfGfxContext.CALCLINEX_DO_INTERSECT)
                {
                    gc.pw2.set(gc.pw222);
                }
            }
        }
        else
        {   // an arc - calculate against radius line
            {   // calculate miter
                int rc; // return code from intersect calculation
            
                // Find line along prv1v-prevv radius line
                gc.pw120.set(Math.cos(v1.begang + v1.swgang) + v1.center.x,
                             Math.sin(v1.begang + v1.swgang) + v1.center.y,
                             v1.center.z);
                
                // calc pw1
                rc = gc.calcLinesIntersect(v1.center, gc.pw120,
                                           gc.pw221,  gc.pw223,
                                           gc.pw1);
                if (rc != YxxfGfxContext.CALCLINEX_DO_INTERSECT)
                {
                    gc.pw1.set(gc.pw221);
                }
                           
                // calc pw2
                rc = gc.calcLinesIntersect(v1.center, gc.pw120,
                                           gc.pw222,  gc.pw224,
                                           gc.pw2);
                if (rc != YxxfGfxContext.CALCLINEX_DO_INTERSECT)
                {
                    gc.pw2.set(gc.pw222);
                }
            }
        }
        


        
        //
        // Calculate end points pw3 and pw4
        //
        
        if (v2_endwid == 0.0)
        {   // no end width
            gc.pw3.set(v3.pnt);
            gc.pw4.set(v3.pnt);
        }
        else
        
        if (v4 == null)
        {   // no next vertex
            gc.pw3.set(gc.pw223);
            gc.pw4.set(gc.pw224);
        }
        else
        
        if (v3_begwid == 0.0)
        {   // no miter to single begpoint
            gc.pw3.set(gc.pw223);
            gc.pw4.set(gc.pw224);
        }
        else
                
        // miter the line against a wide line or arc
        
        if (v3.bulge == 0.0)
        {   // a line - calculate against next line
            if (gc.calcLinesParallel(v2.pnt, v3.pnt, v3.pnt, v4.pnt))
            {   // no miter to parallel lines
                gc.pw3.set(gc.pw223);
                gc.pw4.set(gc.pw224);
            }
            else
            
            {   // calculate miter
                int rc; // return code from intersect calculation
            
                //
                // Find unit vector along currv-cur1v line
                //
                
                // pw310 will be length of 1
                gc.pw310.set(v4.pnt.x - v3.pnt.x, v4.pnt.y - v3.pnt.y, v4.pnt.z - v3.pnt.z);
                gc.pw310.normalize();

                // find width vector
                gc.pw320.crossProduct(gc.pw310, YxxfGfxPointW.Wz);
                gc.pw320.normalize();

                v3_begwid /= 2.0;
                v3_endwid /= 2.0;

                // find line points
                gc.pw321.set(v3.pnt.x + (gc.pw320.x * v3_begwid),
                             v3.pnt.y + (gc.pw320.y * v3_begwid),
                             v3.pnt.z + (gc.pw320.z * v3_begwid));
                gc.pw322.set(v3.pnt.x - (gc.pw320.x * v3_begwid),
                             v3.pnt.y - (gc.pw320.y * v3_begwid),
                             v3.pnt.z - (gc.pw320.z * v3_begwid));
                gc.pw323.set(v4.pnt.x + (gc.pw320.x * v3_endwid),
                             v4.pnt.y + (gc.pw320.y * v3_endwid),
                             v4.pnt.z + (gc.pw320.z * v3_endwid));
                gc.pw324.set(v4.pnt.x - (gc.pw320.x * v3_endwid),
                             v4.pnt.y - (gc.pw320.y * v3_endwid),
                             v4.pnt.z - (gc.pw320.z * v3_endwid));
                             
                // calc pw3
                rc = gc.calcLinesIntersect(gc.pw221, gc.pw223,
                                           gc.pw321, gc.pw323,
                                           gc.pw3);
                if (rc != YxxfGfxContext.CALCLINEX_DO_INTERSECT)
                {
                    gc.pw3.set(gc.pw223);
                }
                           
                // calc pw4
                rc = gc.calcLinesIntersect(gc.pw222, gc.pw224,
                                           gc.pw322, gc.pw324,
                                           gc.pw4);
                if (rc != YxxfGfxContext.CALCLINEX_DO_INTERSECT)
                {
                    gc.pw4.set(gc.pw224);
                }
            }
        }
        else
        {   // an arc - calculate against radius line
            {   // calculate miter
                int rc; // return code from intersect calculation
            
                // Find line along currv-cur1v radius line
                gc.pw320.set(Math.cos(v3.begang) + v3.center.x,
                             Math.sin(v3.begang) + v3.center.y,
                             v3.center.z);
                
                // calc pw3
                rc = gc.calcLinesIntersect(v3.center, gc.pw320,
                                           gc.pw221,  gc.pw223,
                                           gc.pw3);
                if (rc != YxxfGfxContext.CALCLINEX_DO_INTERSECT)
                {
                    gc.pw3.set(gc.pw223);
                }
                           
                // calc pw4
                rc = gc.calcLinesIntersect(v3.center, gc.pw320,
                                           gc.pw222,  gc.pw224,
                                           gc.pw4);
                if (rc != YxxfGfxContext.CALCLINEX_DO_INTERSECT)
                {
                    gc.pw4.set(gc.pw224);
                }
            }
        }
        
        
        // System.out.println("calc_2D_Polyline__mitr pw=[" + gc.pw1 + ","+ gc.pw2 + ","+ gc.pw3 + ","+ gc.pw4 + "]");
        // System.out.println("    pw1      =[" + gc.pw1 + "]");
        // System.out.println("    pw2      =[" + gc.pw2 + "]");
        // System.out.println("    pw3      =[" + gc.pw3 + "]");
        // System.out.println("    pw4      =[" + gc.pw4 + "]");

        return true;
    }
    

    /**
     * Draw 3D Polyline.
     * @param gc The Graphics Context.
     */
    private void draw_3D_Polyline(YxxfGfxContext gc)
    {
        // Drawing control
        YxxfEntVertex currv, prevv, strtv;
        Enumeration   E;

        E = vtxEntities.elements();

        if (!E.hasMoreElements())
            return;

        strtv = prevv = currv = (YxxfEntVertex)E.nextElement();

        while (E.hasMoreElements())
        {
//g         if (gc.gc_doing != YxxfGfxContext.GC_DOING_DRAWING)
//g             return;

            prevv = currv;
            currv = (YxxfEntVertex)E.nextElement();

            gc.drawLine__MCS__cont__flat__wid_none(prevv.pnt, currv.pnt);
        }

        if ((flags & 1) == 1) // closed
        {
            gc.drawLine__MCS__cont__flat__wid_none(currv.pnt, strtv.pnt);
        }
    }


    /**
     * Draw 3D Polygon Mesh.
     * @param gc The Graphics Context.
     */
    private void draw_3D_Polygon_Mesh(YxxfGfxContext gc)
    {
        YxxfEntVertex v1 = null;
        YxxfEntVertex v2 = null;

        for (int m = 0; m < meshcntM; m++)
        {
//g         if (gc.gc_doing != YxxfGfxContext.GC_DOING_DRAWING)
//g             return;

            for (int n = 0; n < meshcntN; n++)
            {
                if (m < (meshcntM - 1)) // Draw in M direction
                {
                    v1 = (YxxfEntVertex)vtxEntities.elementAt( m      * meshcntN + n);
                    v2 = (YxxfEntVertex)vtxEntities.elementAt((m + 1) * meshcntN + n);
                    gc.drawLine__MCS__cont__flat__wid_none(v1.pnt, v2.pnt);
                }
                else
                {
                    if ((flags & 1) == 1) // closed in M
                    {
                        v1 = (YxxfEntVertex)vtxEntities.elementAt( m      * meshcntN + n);
                        v2 = (YxxfEntVertex)vtxEntities.elementAt(                     n);
                        gc.drawLine__MCS__cont__flat__wid_none(v1.pnt, v2.pnt);
                    }
                }

                if (n < (meshcntN - 1)) // Draw in N direction
                {
                    v1 = (YxxfEntVertex)vtxEntities.elementAt(m * meshcntN + n);
                    v2 = (YxxfEntVertex)vtxEntities.elementAt(m * meshcntN + n + 1);
                    gc.drawLine__MCS__cont__flat__wid_none(v1.pnt, v2.pnt);
                }
                else
                {
                    if ((flags & 32) == 32) // closed in N
                    {
                        v1 = (YxxfEntVertex)vtxEntities.elementAt( m      * meshcntN + n);
                        v2 = (YxxfEntVertex)vtxEntities.elementAt( m      * meshcntN);
                        gc.drawLine__MCS__cont__flat__wid_none(v1.pnt, v2.pnt);
                    }
                }
            }
        }
    }


    /**
     * Draw 3D Polyface Mesh.
     * @param gc The Graphics Context.
     */
    private void draw_3D_Polyface_Mesh(YxxfGfxContext gc)
    {
        // Drawing control
        YxxfEntVertex currf;
        Enumeration   E;

        E = vtxFaces.elements();

        // run the face list

        if (gc.getDrawing().secHeader.splframe != 0)
        {  // all edges visible
            while (E.hasMoreElements())
            {
    //g         if (gc.gc_doing != YxxfGfxContext.GC_DOING_DRAWING)
    //g             return;

                currf = (YxxfEntVertex)E.nextElement();

                if (currf.meshidx1 == 0 || currf.meshidx2 == 0)
                    continue;

                gc.drawLine__MCS__cont__flat__wid_none(
                    ((YxxfEntVertex)vtxEntities.elementAt(Math.abs(currf.meshidx1))).pnt,
                    ((YxxfEntVertex)vtxEntities.elementAt(Math.abs(currf.meshidx2))).pnt );

                if (currf.meshidx3 == 0)
                    continue;

                gc.drawLine__MCS__cont__flat__wid_none(
                    ((YxxfEntVertex)vtxEntities.elementAt(Math.abs(currf.meshidx2))).pnt,
                    ((YxxfEntVertex)vtxEntities.elementAt(Math.abs(currf.meshidx3))).pnt );

                if (currf.meshidx4 == 0)
                {
                    gc.drawLine__MCS__cont__flat__wid_none(
                        ((YxxfEntVertex)vtxEntities.elementAt(Math.abs(currf.meshidx3))).pnt,
                        ((YxxfEntVertex)vtxEntities.elementAt(Math.abs(currf.meshidx1))).pnt );
                }
                else
                {
                    gc.drawLine__MCS__cont__flat__wid_none(
                        ((YxxfEntVertex)vtxEntities.elementAt(Math.abs(currf.meshidx3))).pnt,
                        ((YxxfEntVertex)vtxEntities.elementAt(Math.abs(currf.meshidx4))).pnt );
                    gc.drawLine__MCS__cont__flat__wid_none(
                        ((YxxfEntVertex)vtxEntities.elementAt(Math.abs(currf.meshidx4))).pnt,
                        ((YxxfEntVertex)vtxEntities.elementAt(Math.abs(currf.meshidx1))).pnt );
                }
            }
        }
        else
        {   // check edge visibility
            while (E.hasMoreElements())
            {
    //g         if (gc.gc_doing != YxxfGfxContext.GC_DOING_DRAWING)
    //g             return;

                currf = (YxxfEntVertex)E.nextElement();

                if (currf.meshidx1 == 0 || currf.meshidx2 == 0)
                    continue;

                if (currf.meshidx1 > 0)
                    gc.drawLine__MCS__cont__flat__wid_none(
                        ((YxxfEntVertex)vtxEntities.elementAt(         currf.meshidx1 )).pnt,
                        ((YxxfEntVertex)vtxEntities.elementAt(Math.abs(currf.meshidx2))).pnt );

                if (currf.meshidx3 == 0)
                    continue;

                if (currf.meshidx2 > 0)
                    gc.drawLine__MCS__cont__flat__wid_none(
                        ((YxxfEntVertex)vtxEntities.elementAt(         currf.meshidx2 )).pnt,
                        ((YxxfEntVertex)vtxEntities.elementAt(Math.abs(currf.meshidx3))).pnt );

                if (currf.meshidx4 == 0)
                {
                    if (currf.meshidx3 > 0)
                        gc.drawLine__MCS__cont__flat__wid_none(
                            ((YxxfEntVertex)vtxEntities.elementAt(         currf.meshidx3 )).pnt,
                            ((YxxfEntVertex)vtxEntities.elementAt(Math.abs(currf.meshidx1))).pnt );
                }
                else
                {
                    if (currf.meshidx3 > 0)
                        gc.drawLine__MCS__cont__flat__wid_none(
                            ((YxxfEntVertex)vtxEntities.elementAt(         currf.meshidx3 )).pnt,
                            ((YxxfEntVertex)vtxEntities.elementAt(Math.abs(currf.meshidx4))).pnt );
                    if (currf.meshidx4 > 0)
                        gc.drawLine__MCS__cont__flat__wid_none(
                            ((YxxfEntVertex)vtxEntities.elementAt(         currf.meshidx4 )).pnt,
                            ((YxxfEntVertex)vtxEntities.elementAt(Math.abs(currf.meshidx1))).pnt );
                }
            }
        }
    }


    /**
     * Calculate polyline transmat.
     * @param D The Drawing object.
     */
    public
    void calc(Yxxf D)
    {
        //
        // connect
        //
        hdr_calc(D);


        vtxEntities.trimToSize();
        vtxFaces.trimToSize();

        if ((flags & 8) == 8)       // This is a 3D polyline
        {
            // System.out.println("POLYLINE calc: 3D polyline");
            // No calculation needed
        } else
  
        if ((flags & 16) == 16)     // This is a 3D polygon mesh
        {
            // System.out.println("POLYLINE calc: 3D polygon mesh");
            // No calculation needed
        } else
  
        if ((flags & 64) == 64)     // This is a 3D polyface mesh
        {
            // System.out.println("POLYLINE calc: polyface mesh");
            // No calculation needed
        } else
  
        {                           // This is a 2D polyline
            // System.out.println("POLYLINE calc: 2D polyline");
            calcTransform_draw_2D_Polyline();
        }
    }


    /**
     * Calculate polyline transmat for 2D Polyline.
     */
    private
    void
    calcTransform_draw_2D_Polyline()
    {
        YxxfGfxPointW Ax = new YxxfGfxPointW();
        YxxfGfxPointW Ay = new YxxfGfxPointW();
        YxxfGfxPointW Az = new YxxfGfxPointW();


        //
        // setup polyline transform
        //

        // Initialize polyline matrix
        if (M_polyline == null)
            M_polyline = new YxxfGfxMatrix();
        else
            M_polyline.mtxSetIdentity();


        // Calculate Ax, Ay, Az axes from xtruDir using Arbitrary Axis Algorithm
        Az.set(xtruDir);
        Az.normalize();
        YxxfGfxPointW.calcAAA(Ax, Ay, Az);
        //System.out.println("POLYLINE:after calcAAA");
        //System.out.println("POLYLINE:    Ax =" + Ax + ",dist=" + Ax.distance());
        //System.out.println("POLYLINE:    Ay =" + Ay + ",dist=" + Ay.distance());
        //System.out.println("POLYLINE:    Az =" + Az + ",dist=" + Az.distance());


        // Apply rotation
        M_polyline.mtxRotateAxes_World_to_Local(Ax, Ay, Az);


        // Run vertex list and calcTransform for vertices with bulge
        // Calc control
        YxxfEntVertex currv = null;
        YxxfEntVertex prevv = null;
        YxxfEntVertex strtv = null;
        Enumeration   E     = null;

        E = vtxEntities.elements();

        if (!E.hasMoreElements())
            return;

        strtv = prevv = currv = (YxxfEntVertex)E.nextElement();

        while (E.hasMoreElements())
        {
            prevv = currv;
            currv = (YxxfEntVertex)E.nextElement();

            calcTransform_vtov(prevv, currv);
        }

        if ((flags & 1) == 1) // closed
        {
            calcTransform_vtov(currv, strtv);
        }
    }


    /**
     * Calculate vertex to vertex.
     * @param v1 Vertex 1
     * @param v2 Vertex 2
     */
    private void calcTransform_vtov(YxxfEntVertex v1, YxxfEntVertex v2)
    {
        if (v1.bulge == 0.0)
        {
        }
        else
        {
            double  bulge;
            int     angdir;

            if (v1.bulge < 0.0)
            {
                bulge = -v1.bulge;
                angdir = 1; // CW
            }
            else
            {
                bulge = v1.bulge;
                angdir = 0; // CCW
            }
            //System.out.println("  Vertex:v1=[" + v1.pnt +
            //                          "],v2=[" + v2.pnt + "],v1.bulge=" + v1.bulge);

            // Find the included angle (in radians) from the bulge factor
            double incang = 4.0 * Math.atan(bulge);
            //System.out.println("        :incang_r=" + incang + ",incang_d=" + (incang / (Math.PI / 180.0)));

            // Find the length of the chord from v1 to v2
            double chord  = YxxfGfxContext.calcdis(v1.pnt.x, v1.pnt.y, v2.pnt.x, v2.pnt.y);
            //System.out.println("        :chord   =" + chord);

            // Find radius of the polyarc
            double anga   = (Math.PI / 2.0) - (incang / 2.0);
            v1.radius = (chord / 2.0) / Math.cos(anga);
            //System.out.println("        :anga_r  =" + anga + ",anga_d  =" + (anga / (Math.PI / 180.0)));
            //System.out.println("        :radius  =" + v1.radius);

            // Find center point

            // First find the angle of the chord
            double chordang  = YxxfGfxContext.calcangle(v1.pnt.x, v1.pnt.y, v2.pnt.x, v2.pnt.y);
            //System.out.println("        :cang_r  =" + chordang + ",cang_d  =" + (chordang / (Math.PI / 180.0)));

            // Then find angle of radius line
            double radang = chordang + ((angdir == 1) ? -anga : anga);
            //System.out.println("        :rang_r  =" + radang + ",rang_d  =" + (radang / (Math.PI / 180.0)));

            // Derive center point
            if (v1.center == null)
                v1.center = new YxxfGfxPointW();
            v1.center.x = v1.pnt.x + (v1.radius * Math.cos(radang));
            v1.center.y = v1.pnt.y + (v1.radius * Math.sin(radang));
            v1.center.z = v1.pnt.z;
            //System.out.println("        :center  =" + v1.center);

            // Derive beginning and swing angles
            v1.begang = YxxfGfxContext.calcangle(v1.center.x, v1.center.y, v1.pnt.x, v1.pnt.y);
            v1.swgang = (angdir == 0) ? incang : -incang;

//          // Calc the vertex transform starting with polyline transform
//          if (v1.M_vertex == null)
//              v1.M_vertex = new YxxfGfxMatrix(M_polyline);
//          else
//              if (v1.M_vertex == M_polyline)
//                  v1.M_vertex = new YxxfGfxMatrix(M_polyline);
//              else
//                  v1.M_vertex.set(M_polyline);

//          // Applies the current center rotation
//          v1.M_vertex.mtxTransformPoint(center);

//          // Apply translate (center point)
//          v1.M_vertex.mtxTranslate(center);
        }
    }




    /**
     * Add vertex entity or face entity to proper polyline list.
     * @param vtx The vertex.
     */
    public
    void addVertex(YxxfEntVertex vtx)
    {
        if ((flags & 64) == 64) // Polyline is a Polyface mesh
        {
            if ((vtx.flags & 64) == 64)
            {   // This is a polyface mesh coordinate vertex
                vtxEntities.addElement(vtx);
            }
            else
            {   // This is a polyface mesh coordinate face
                vtxFaces.addElement(vtx);
            }
        }
        else
        {   // Normal vertex
            vtxEntities.addElement(vtx);
        }
    }


    /**
     * Return size of vertex list.
     * @return The size of the vertex list.
     */
    public
    int getVtxEntitiesSize()
    {
        return vtxEntities.size();
    }


    /**
     * Return size of faces list.
     * @return The sice of the faces list.
     */
    public
    int getVtxFacesSize()
    {
        return vtxFaces.size();
    }
}

