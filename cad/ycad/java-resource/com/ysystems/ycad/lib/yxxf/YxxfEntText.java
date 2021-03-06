//==============================================================================
// YxxfEntText.java
//
// TEXT entity
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfEntText.java,v 1.23 2003/05/08 11:24:02 ekarlo Exp $
// $Log: YxxfEntText.java,v $
// Revision 1.23  2003/05/08 11:24:02  ekarlo
// Remove warnings.
//
// Revision 1.22  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.21  2000/10/17 07:43:50  ekarlo
// Change package paths to lower case.
//
// Revision 1.20  1999-11-27 21:48:50-07  ekarlo
// Fix scale problem with text.
//
// Revision 1.19  1999-11-27 21:11:05-07  ekarlo
// Fix scale problem with text.
//
// Revision 1.18  1999-09-29 17:05:15-06  walter
// Added JavaDoc comments.
//
// Revision 1.17  1999-07-09 14:10:50-06  ekarlo
// Rearrange package names.
//
// Revision 1.16  1999/07/08  02:58:44  ekarlo
// Fix shape load.
//
// Revision 1.15  1999/06/29  19:54:51  ekarlo
// User Interface - phase 2.
//
// Revision 1.14  1999/06/20  22:34:10  ekarlo
// Rearrange package names.
//
// Revision 1.13  1999/02/08  05:11:21  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.12  1999/01/28  04:29:02  ekarlo
// Text - phase 4.
//
// Revision 1.11  1998/12/21  15:43:26  ekarlo
// Text - phase 3.
//
// Revision 1.10  1998/11/24  19:53:50  ekarlo
// Text - phase 2.
//
// Revision 1.9  1998/08/21  20:21:55  ekarlo
// Eliminate use of global work arrays in matrix calculations
// and use local doubles instead of arrays.
//
// Revision 1.8  1998/07/12  00:12:05  ekarlo
// Testing.
//
// Revision 1.7  1997/12/26  21:37:25  ekarlo
// Deactivate temporarily.
//
// Revision 1.6  1997/12/13  18:59:21  ekarlo
// Move work matrix arrays out of YxxfGfxMatrix Objects.
//
// Revision 1.5  1997/12/07  20:05:43  ekarlo
// Deactivate debug print.
//
// Revision 1.4  1997/08/30  14:18:21  ekarlo
// Change matrix initialization.
// Use new drawline method.
//
// Revision 1.3  1997/07/21  22:41:47  ekarlo
// Make fields public for static get.
//
// Revision 1.2  1996/10/26  00:43:44  ekarlo
// Correct color and layer handling.
//
// Revision 1.1  1996/09/27  09:33:41  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


/**
 * TEXT entity.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfEntText extends YxxfEntHeader implements YxxfEnt
{
    /**
     * code  10,20,30 -
     *            Insertion point (in OCS).
     */
    public
    YxxfGfxPointW               inspnt          = new YxxfGfxPointW();


    /**
     * code  40 - Height.
     */
    public
    double                      height          = 0.0;

    /**
     * code   1 - Text value (the string itself).
     */
    public
    String                      text            = null;


    /**
     * code  50 - Rotation angle (optional; default = 0).
     */
    public
    double                      rotang          = 0.0;


    /**
     * code  41 - Relative X scale factor.
     *            For fit-type text (optional; default = 1.0).
     */
    public
    double                      relxscale       = 1.0;


    /**
     * code  51 - Oblique angle (optional default = 0).
     */
    public
    double                      obliqueang      = 0.0;


    /**
     * code   7 - Text style name (optional; default = "STANDARD").
     *            Set to style table reference.
     */
    public
    YxxfTblStyle                style;


    /**
     * code  71 - Text generation flags.
     * <UL>
     *   <LI>2 = Text is backward    (mirrored in X).
     *   <LI>4 = Text is upside down (mirrored in Y).
     * </UL>
     */
    public
    int                         textgenflags    = 0;


    /**
     * code  72 - Horizontal alignment (optional, default = 0).
     *            Integer codes (not bitcoded).
     * <UL>
     *   <LI>0 = Left
     *   <LI>1 = Center
     *   <LI>2 = Right
     *   <LI>3 = Aligned (if vertical alignment = 0).
     *   <LI>4 = Middle  (if vertical alignment = 0).
     *   <LI>5 = Fit     (if vertical alignment = 0).
     * </UL>
     *      See vertalignflags for clarification.
     */
    public
    int                         horzalignflags  = 0;


    /**
     * code  73 - Vertical alignment (optional, default = 0).
     *            Integer codes (not bitcoded)
     * <UL>
     *   <LI>0 = Baseline
     *   <LI>1 = Bottom
     *   <LI>2 = Middle
     *   <LI>3 = Top
     * </UL>
     *       See alnpnt for clarification.
     */
    public
    int                         vertalignflags  = 0;


    /**
     * code  11,21,31 -
     *      Alignment point.
     *      Present only if 72 or 73 group is present and nonzero.
     *      If the justification is anything other than baseline/left
     *      (groups 72 and 73 both 0), group code 11, 21, and 31 specify the
     *      alignment point (or the second alignment point for Aligned or Fit).
     *      (In OCS).
     */
    public
    YxxfGfxPointW               alnpnt          = new YxxfGfxPointW();


    /**
     * code  39 - Thickness (optional; default = 0).
     */
    public
    double                      thickness       = 0.0;


    /**
     * code 210,220,230 -
     *      Extrusion direction. Present if the entity's extrusion direction
     *      is not parallel to the WCS Z axis (Optional; default = 0, 0, 1).
     */
    public
    YxxfGfxPointW               xtruDir         = new YxxfGfxPointW(0, 0, 1);


    // Calculated items

    /**
     * Transmat for text - computed during get or load.
     */
    private
    YxxfGfxMatrix               M_text          = null;

    /**
     * Ref to associated shape.
     */
    public
    YxxfShape                   shape;

    /**
     * Characteristics of associated shape.
     */
    int                         shape_above_size        = 6;




    /**
     * Constructor (empty)
     */
    public
    YxxfEntText()
    {
    }


    /**
     * Draw text.
     * @param gc The graphics context.
     */
    public
    void draw(YxxfGfxContext gc)
    {
        if (gc.setupEntity(this, M_text) == false)
            return;

//d     System.out.println("TEXT:t=[" + text + "]");
//d     System.out.println("    :inspnt=" + inspnt + ",alnpnt=" + alnpnt);
//d     System.out.println("    :style.name=" + ((style == null) ? "NULL" : style.getName()));
//d     System.out.println("    :h=" + height + ",relxscale=" + relxscale + ",rotang=" + rotang + ",obliqueang=" + obliqueang);
//d     System.out.println("    :textgenflags=" + textgenflags + ",horzalignflags=" + horzalignflags + ",vertalignflags=" + vertalignflags);
//d     gc.ECS_to_ECS(YxxfGfxPointW.Wx, gc.pw0); System.out.println("    :Wx=" + gc.pw0);
//d     gc.ECS_to_ECS(YxxfGfxPointW.Wy, gc.pw0); System.out.println("    :Wy=" + gc.pw0);
//d     gc.ECS_to_ECS(YxxfGfxPointW.Wz, gc.pw0); System.out.println("    :Wz=" + gc.pw0);

        //
        // Draw the text
        //
        gc.drawText_ECS(text, shape, (style.flags & 4) == 4);
    }


    /**
     * Calculate text transmat.
     * @param D The drawing.
     */
    public
    void calc(Yxxf D)
    {
        //
        // connect
        //
        hdr_calc(D);

        // connect style
        if (style == null)
            style = D.secTables.findStyle_add(YxxfTblStyleKey.STR_STYLENAME__STANDARD);
        D.secTables.findStyleShape_add(style);


        YxxfGfxPointW Ax = new YxxfGfxPointW();
        YxxfGfxPointW Ay = new YxxfGfxPointW();
        YxxfGfxPointW Az = new YxxfGfxPointW();

        YxxfGfxPointW B0 = new YxxfGfxPointW();
        YxxfGfxPointW Bz = new YxxfGfxPointW();

        YxxfGfxPointW Ip = new YxxfGfxPointW();


        //
        // get text shape values
        //
        shape = style.shape;

        shape_above_size     = shape.getShapeDesc_above_size();


        //
        // setup text transform
        //

        // Initialize text matrix
        if (M_text == null)
            M_text = new YxxfGfxMatrix();
        else
            M_text.mtxSetIdentity();


        // Calculate Ax, Ay, Az axes from xtruDir using Arbitrary Axis Algorithm
        Az.set(xtruDir);
        Az.normalize();
        YxxfGfxPointW.calcAAA(Ax, Ay, Az);


        // Apply new rotation
        M_text.mtxRotateAxes_World_to_Local(Ax, Ay, Az);


        // Calculate insertion point Ip from inspnt
        // Applies the current text rotation
        Ip.set(inspnt);
        M_text.mtxTransformPoint(Ip);


        // Apply new scale
        M_text.mtxSetIdentity();
        double xscale;
        if ((textgenflags & 2) == 2) // Text is backward    (mirrored in X)
            xscale = -height / shape_above_size * relxscale;
        else
            xscale =  height / shape_above_size * relxscale;

        double yscale;
        if ((textgenflags & 4) == 4) // Text is upside down (mirrored in Y)
            yscale = -height / shape_above_size;
        else
            yscale =  height / shape_above_size;

        YxxfGfxPointW scale = new YxxfGfxPointW(xscale, yscale, 1);
        M_text.mtxScale(scale, YxxfGfxPointW.W0);


        // Reapply new rotation
        M_text.mtxRotateAxes_World_to_Local(Ax, Ay, Az);


        // Apply twist
        M_text.mtxTransformPoint(B0.set(YxxfGfxPointW.W0));
        M_text.mtxTransformPoint(Bz.set(YxxfGfxPointW.Wz));
        Bz.normalize();

        M_text.mtxRotate(B0, Bz, rotang * (Math.PI / 180.0));


        // Apply new translate (insertion point)
        M_text.mtxTranslate(Ip);
    }
}

