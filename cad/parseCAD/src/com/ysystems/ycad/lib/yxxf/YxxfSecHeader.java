//==============================================================================
// YdxfSecHeader.java
//
// HEADER section
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfSecHeader.java,v 1.20 2003/04/14 12:38:42 ekarlo Exp $
// $Log: YxxfSecHeader.java,v $
// Revision 1.20  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.19  2002/10/08 00:47:49  ekarlo
// Fix Javadoc comment.
//
// Revision 1.18  2002-09-28 23:20:56-06  ekarlo
// Change prefix of entity print options to "list".
//
// Revision 1.17  2001-10-08 07:19:54-06  ekarlo
// Add $SPLFRAME to print.
//
// Revision 1.16  2000-10-17 01:43:41-06  ekarlo
// Change package paths to lower case.
//
// Revision 1.15  1999-09-08 13:16:42-06  walter
// Add JavaDoc comments.
//
// Revision 1.14  1999-08-07 16:09:56-06  ekarlo
// Add $SPLFRAME.
//
// Revision 1.13  1999/07/12  00:03:22  ekarlo
// Fix comment.
//
// Revision 1.12  1999/07/09  20:10:50  ekarlo
// Rearrange package names.
//
// Revision 1.11  1999/06/20  22:34:10  ekarlo
// Rearrange package names.
//
// Revision 1.10  1999/02/08  05:11:30  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.9  1999/01/28  04:31:28  ekarlo
// Text - phase 4.
//
// Revision 1.8  1998/07/12  00:14:28  ekarlo
// 1) Add FILLMODE.
// 2) Rearrange print.
//
// Revision 1.7  1998/02/12  17:52:47  ekarlo
// Add LTSCALE.
//
// Revision 1.6  1998/02/02  21:26:27  ekarlo
// Correct comment.
//
// Revision 1.5  1997/07/21  22:51:37  ekarlo
// Rename from YdxfSecHeader.java to YxxfSecHeader.java.
//
// Revision 1.4  1997/07/13  18:10:09  ekarlo
// MVC-VH rework - phase 1.
// Rename get class.
//
// Revision 1.3  1996/09/26  01:41:39  ekarlo
// Store header values of current properties.
//
// Revision 1.2  1996/07/30  06:26:15  ekarlo
// 1) Change comment.
// 2) Add limmin and limmax.
// 3) Use point class.
//
// Revision 1.1  1996/07/02  01:58:26  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


/**
 * HEADER section.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfSecHeader
{
    /**
     * The AutoCAD drawing database version number.
     * <UL>
     *   <LI>AC1006 = R10
     *   <LI>AC1009 = R11 and R12
     *   <LI>AC1011 = R13
     * </UL>
     */
    public
    String                      acadver                 // code   1
                                    = "";




    /**
     * Units format for angles.
     * <UL>
     *   <LI>0 = decimal degrees
     *   <LI>1 = degrees / minutes / seconds
     *   <LI>2 = grads
     *   <LI>3 = radians
     *   <LI>4 = surveyor's units
     * </UL>
     */
    public
    int                         aunits                  // code  70
                                    = 0;




    /**
     * Current entity color number.
     * <UL>
     *   <LI>0 = BYBLOCK
     *   <LI>256 = BYLAYER
     * </UL>
     */
    public
    int                         cecolor                 // code  62
                                    = 7;




    /**
     * Current layer name.
     */
    public
    String                      clayer                  // code   8
                                    = YxxfTblLayerKey.STR_LAYERNAME__0;




    /**
     * Current entity linetype name, or BYBLOCK or BYLAYER.
     */
    public
    String                      celtype                 // code   6
                                    = YxxfTblLtype.STR_LTYPENAME__BYLAYER;




    /**
     * code  10,20,30<br>
     *            X, Y, and Z drawing extents lower-left corner (in WCS).
     */
    public
    YxxfGfxPointW               extmin          = new YxxfGfxPointW();

    /**
     * code  10,20,30<br>
     *            X, Y, and Z drawing extents upper-right corner (in WCS).
     */
    public
    YxxfGfxPointW               extmax          = new YxxfGfxPointW();




    /**
     * Fill mode for solids.
     * <UL>
     *   <LI>0 = off - Objects are not filled.
     *   <LI>1 = on  - Objects are filled.
     * </UL>
     */
    public
    int                         fillmode                // code  70
                                    = 1;




    /**
     * code  10,20<br>
     *            X, Y drawing limits lower-left corner (in WCS).
     */
    public
    YxxfGfxPointW               limmin          = new YxxfGfxPointW();

    /**
     * code  10,20
     */
    public
    YxxfGfxPointW               limmax          = new YxxfGfxPointW();




    /**
     * Global linetype scale factor.
     */
    public
    double                      ltscale                 // code  40
                                    = 1.0;




    /**
     * Spline control polygon display.
     * <br>
     * 0 = Off
     * <UL>
     *   <LI>Does not display the control polygon for splines and spline-fit polylines.<br>
     *   <LI>Display the fit surface of a polygon mesh, not the defining mesh.
     *   <LI>Does not display the invisible edges of 3D faces or polyface meshes.
     * </UL>
     * 1 = On.
     * <UL>
     *   <LI>Displays the control polygon for splines and spline-fit polylines.
     *   <LI>Only the defining mesh of a surface-fit polygon mesh is displayed 
     *       (not the fit surface).
     *   <LI>Invisible edges of 3D faces or polyface meshes are displayed.
     *</UL>
     */
    public
    int                         splframe                // code  70
                                    = 0;




    /**
     * Viewport tilemode.
     * <br>
     * [From <I>AutoCAD DXF docs</I>]<br>
     * <UL>
     *    <LI>1 for previous release compatibility mode, <LI>0 otherwise.
     * </UL>
     * <br>
     * [From <I>AutoCAD programming in C/C++</I>]<br>
     * Sets how viewports can be arranged:
     * <UL>
     *   <LI>0 = (see book).
     *   <LI>1 = Viewports have to be tiled, ie they cover the whole
     *           graphics area and do not overlap.
     *   <LI>2 = Viewports in paperspace can be separate and/or overlap each 
     *           other.
     * </UL>
     */
    public
    int                         tilemode                // code  70
                                    = 1;




    /**
     * Constructor (empty)
     */
    public
    YxxfSecHeader()
    {
    }


    /**
     * List the values of the Section Header.
     */
    public
    void listHeader()
    {
        System.out.println("acadver  =[" + acadver + "]");
        System.out.println("aunits   =" + aunits);

        System.out.println("extmin   =" + extmin);
        System.out.println("extmax   =" + extmax);

        System.out.println("fillmode =" + fillmode);

        System.out.println("limmin   =" + limmin);
        System.out.println("limmax   =" + limmax);

        System.out.println("ltscale  =" + ltscale);

        System.out.println("splframe =" + splframe);

        System.out.println("tilemode =" + tilemode);
    }
}

