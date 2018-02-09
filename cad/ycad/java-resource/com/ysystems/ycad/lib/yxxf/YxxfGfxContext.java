//==============================================================================
// YxxfGfxContext.java
//
// Graphics Context and Transformation Methods
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfGfxContext.java,v 1.67 2003/06/03 09:53:15 ekarlo Exp $
// $Log: YxxfGfxContext.java,v $
// Revision 1.67  2003/06/03 09:53:15  ekarlo
// Fix xoffset= and yoffset= Prt imprinter funtionality.
//
// Revision 1.66  2003/05/26 02:58:10  ekarlo
// Add methods to turn rendering on and off.
// Improve calculation of extents.
//
// Revision 1.65  2003/05/19 05:31:19  ekarlo
// Fix off-by-one (top and left) view error.
//
// Revision 1.64  2003/05/17 12:42:20  ekarlo
// Add option to control width of border around views.
//
// Revision 1.63  2003/05/08 11:24:03  ekarlo
// Remove warnings.
//
// Revision 1.62  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.61  2002/10/22 02:55:52  ekarlo
// Don't draw zero length wide polyline segments.
// Calculation was in error (not possible).
//
// Revision 1.60  2002-10-12 01:09:34-06  ekarlo
// Fix zoom to calc extents command.
//
// Revision 1.59  2002-10-10 22:54:22-06  ekarlo
// Rearrange order of main public items.
//
// Revision 1.58  2002-09-26 02:05:47-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.57  2002-09-12 23:39:26-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.56  2002-09-12 15:08:07-06  ekarlo
// Imprinter development check-in.
// Remove use of Properties.
// Change background access.
// Change palette access.
//
// Revision 1.55  2001-05-21 02:33:59-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.54  2001-05-20 02:43:02-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.53  2001-05-17 23:44:42-06  ekarlo
// Add getter for jgc.
//
// Revision 1.52  2001-05-11 22:58:42-06  ekarlo
// Make var name consistent.
//
// Revision 1.51  2000-10-17 01:43:47-06  ekarlo
// Change package paths to lower case.
//
// Revision 1.50  1999-11-27 21:11:05-07  ekarlo
// Fix scale problem with text.
//
// Revision 1.49  1999-11-24 23:05:36-07  ekarlo
// Fix insert calc timing problem.
//
// Revision 1.48  1999-10-22 01:27:53-06  ekarlo
// API rework - phase 1.
//
// Revision 1.47  1999-09-15 10:10:14-06  walter
// Added JavaDoc comments.
//
// Revision 1.46  1999-08-11 19:54:49-06  ekarlo
// Fix circle linetype scale problem.
//
// Revision 1.45  1999/08/10  14:10:57  ekarlo
// Fix arc linetype not showing.
// Remove unused var warning.
//
// Revision 1.44  1999/07/28  09:31:29  ekarlo
// Check for degenerate polygon draw.
// Fix solid draw.
//
// Revision 1.43  1999/07/25  23:24:37  ekarlo
// Fix comment.
//
// Revision 1.42  1999/07/12  00:49:21  ekarlo
// Fix comments.
//
// Revision 1.41  1999/07/09  20:10:50  ekarlo
// Rearrange package names.
//
// Revision 1.40  1999/07/09  15:21:31  ekarlo
// Improve param properties scan.
//
// Revision 1.39  1999/07/07  03:23:15  ekarlo
// Fix comments.
//
// Revision 1.38  1999/07/06  02:45:37  ekarlo
// Add calc and zoom extents.
//
// Revision 1.37  1999/06/29  19:54:51  ekarlo
// User Interface - phase 2.
//
// Revision 1.36  1999/06/20  22:34:10  ekarlo
// Rearrange package names.
//
// Revision 1.35  1999/06/15  04:59:25  ekarlo
// User Interface - phase 1.
//
// Revision 1.34  1999/02/08  05:11:21  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.33  1999/01/28  04:29:02  ekarlo
// Text - phase 4.
//
// Revision 1.32  1998/11/24  19:53:50  ekarlo
// Text - phase 2.
//
// Revision 1.31  1998/08/21  20:21:55  ekarlo
// Eliminate use of global work arrays in matrix calculations
// and use local doubles instead of arrays.
//
// Revision 1.30  1998/07/25  17:40:37  ekarlo
// Fix wide, linetyped polyarc display.
//
// Revision 1.29  1998/07/20  13:00:58  ekarlo
// Add tolerance to line type pattern calculations.
//
// Revision 1.28  1998/07/12  00:13:32  ekarlo
// Linetype, thickness, width - done.
//
// Revision 1.27  1998/02/12  17:55:42  ekarlo
// Implement simple line types for lines.
//
// Revision 1.26  1998/02/02  19:11:11  ekarlo
// Implement paper space - phase 2.
//
// Revision 1.25  1997/12/26  21:37:49  ekarlo
// Implement paper space - phase 1.
// Rename properties object.
//
// Revision 1.24  1997/12/13  18:59:21  ekarlo
// Move work matrix arrays out of YxxfGfxMatrix Objects.
//
// Revision 1.23  1997/09/11  21:03:21  ekarlo
// Fix view twist.
//
// Revision 1.22  1997/08/30  14:18:56  ekarlo
// Redo redraw action.
// Add linetype setup.
// Change user properties.
// New drawline methods.
//
// Revision 1.21  1997/07/23  14:24:52  ekarlo
// Remove progress indicators.
//
// Revision 1.20  1997/07/21  22:50:17  ekarlo
// MVC-VH rework - phase 2.
// Move out stats print code.
//
// Revision 1.19  1997/07/13  18:16:25  ekarlo
// MVC-VH rework - phase 1.
//
// Revision 1.18  1997/06/11  15:38:54  ekarlo
// Add command line set methods (moved from Ydxf.java).
//
// Revision 1.17  1996/10/26  00:44:23  ekarlo
// 1) Changes for parameter processing.
// 2) Correct color and layer handling.
//
// Revision 1.16  1996/09/27  09:34:03  ekarlo
// Add text - v1.
//
// Revision 1.15  1996/09/26  18:10:04  ekarlo
// Implement thickness.
// Add user parameters:
//     vport, vportnum
//     printlayers, printvports, printblocks
//     drawdspminmax, drawextminmax, drawlimminmax
//
// Revision 1.14  1996/09/26  02:04:34  ekarlo
// 1) Implement proper color activity.
// 2) Correct comment.
// 3) Draw default status indicators in background color.
//
// Revision 1.13  1996/09/13  06:00:37  ekarlo
// 1) Use new base entity class structure.
// 2) Redo user parameter processing and add bgcolor parameter.
// 3) Begin new color code.
//
// Revision 1.12  1996/09/01  01:46:43  ekarlo
// Remove unused code.
//
// Revision 1.11  1996/08/31  02:32:13  ekarlo
// Draw ellipse - working - needs optimization.
//
// Revision 1.10  1996/08/27  12:03:17  ekarlo
// Draw ellipse - continuing.
//
// Revision 1.9  1996/08/25  05:26:19  ekarlo
// Draw ellipse - continuing.
//
// Revision 1.8  1996/08/19  15:50:05  ekarlo
// Reimplement block base point translation.
//
// Revision 1.7  1996/08/19  04:52:21  ekarlo
// 1) Redo insert method.
// 2) Move calcAAA() to YxxfGfxPointW().
//
// Revision 1.6  1996/08/18  02:19:29  ekarlo
// 1) Begin change to use new transmat.
// 2) Cleanup unused code.
// 3) Apply view twist.
//
// Revision 1.5  1996/08/14  19:33:35  ekarlo
// 1) Changes to view transform.
// 2) Add display transform.
// 3) Deactivate drawing of extent, limit, vport lines.
//
// Revision 1.4  1996/08/13  02:25:14  ekarlo
// 1) Add 3D transform and use vport.
// 2) Redo matrix code.
// 3) Add drawCross().
// 4) Change drawing of indicators and extent lines.
// 5) Draw box around limit and vport area.
//
// Revision 1.3  1996/07/30  07:04:19  ekarlo
// 1) Use point class.
// 2) Functions to draw status indicators.
// 3) Changes to 3D code.
// 4) Own drawLine().
//
// Revision 1.2  1996/07/06  20:29:33  ekarlo
// 1) Reactivate clone code.
// 2) Begin 3d transform code.
//
// Revision 1.1  1996/07/02  02:20:22  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


import java.awt.*;

import com.ysystems.lib.yutil.*;


/**
 * Graphics Context and Transformation Methods.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfGfxContext
{
    //==========================================================================
    /**
     * Constructor (empty).
     */
    public YxxfGfxContext()
    {
    }
    //==========================================================================




    //==========================================================================
    // Structure items.

    /**
     * The parent of this Graphics Context.
     * Topmost GC parent is null.
     * @see #setParentGC
     * @see #getParentGC
     * @see #getTopGC
     */
    private
    YxxfGfxContext              parentGC        = null;

    /**
     * Set parentGC.
     * @param parentGC The parent of this GC.
     */
    public
    void setParentGC(YxxfGfxContext parentGC)
    {
        this.parentGC = parentGC;
    }

    /**
     * Get parentGC.
     * @return The parentGC.
     */
    public
    YxxfGfxContext getParentGC()
    {
        return parentGC;
    }

    /**
     * Get topmost GC.
     * @return The topmost GC.
     */
    public
    YxxfGfxContext getTopGC()
    {
        for (YxxfGfxContext tmpgc = this; ; tmpgc = tmpgc.getParentGC())
        {
            if (tmpgc.getParentGC() == null)
                return tmpgc;
        }
    }
    //==========================================================================




    //==========================================================================
    // Drawing reference items

    /**
     * Drawing
     */
    private
    Yxxf                        D               = null;

    /**
     * Set drawing reference.
     * @param D The drawing reference.
     */
    public
    void setDrawing(Yxxf D)
    {
        this.D = D;
    }

    /**
     * Get drawing reference.
     * @return The drawing reference.
     */
    public
    Yxxf getDrawing()
    {
        return D;
    }
    //==========================================================================




    //==========================================================================
    // VPORT or VIEWPORT settings

    /**
     * Vport table entry for this context - tilemode 1.
     */
    private
    YxxfTblVport                vport;

     /**
      * Unmodified copy of Vport to allow view restore.
      */
    private
    YxxfTblVport                vport_orig;


    /**
     * Viewport entity entry for this context - tilemode 0.
     */
    private
    YxxfEntViewport             viewport;


    /**
     * Set Vport for tilemode 1.
     * @param vport The Vport.
     */
    public
    void setVport(YxxfTblVport vport)
    {
        this.vport = vport;
    }

    /**
     * Get Vport.
     * @return The Vport.
     */
    public
    YxxfTblVport getVport()
    {
        return vport;
    }

    /**
     * Set Original Vport for tilemode 1.
     * @param vport_orit The Vport.
     */
    public
    void setVportOrig(YxxfTblVport vport_orig)
    {
        this.vport_orig = vport_orig;
    }

    /**
     * Get Original Vport.
     */
    public
    YxxfTblVport getVportOrig()
    {
        return vport_orig;
    }


    /**
     * Set Viewport for tilemode 0.
     * @param viewport The Viewport.
     */
    public
    void setViewport(YxxfEntViewport viewport)
    {
        this.viewport = viewport;
    }

    /**
     * Get Viewport.
     * @return The Viewport.
     */
    public
    YxxfEntViewport getViewport()
    {
        return viewport;
    }
    //==========================================================================




    //==========================================================================
    // Java Graphics Context (jgc)

    /**
     * Java Graphics Context for this Graphics Context.
     */
    private
    Graphics                    jgc;

    /**
     * Java Graphics Context shared flag.
     * If true then jgc is shared with other GCs.
     */
    private
    boolean                     sharedjgcflag   = false;

    /**
     * Java Graphics Context rendering flag.
     * If true then jgc rendering is done.
     */
    private
    boolean                     renderingjgcflag   = true;

    /**
     * Current jgc translation value.
     */
    private
    Point                       jgctranslate    = new Point();


    /**
     * Set Java Graphics Context
     * @param jgc The context.
     */
    public
    void setJGC(Graphics jgc)
    {
        this.jgc = jgc;
        jgctranslate.setLocation(0, 0);
    }

    /**
     * Get Java Graphics Context.
     * @return The context.
     */
    public
    Graphics getJGC()
    {
        return jgc;
    }


    /**
     * Set Java Graphics Context shared flag
     * @param sharedjgcflag Boolean flag.
     */
    public
    void setSharedJGCFlag(boolean sharedjgcflag)
    {
        this.sharedjgcflag = sharedjgcflag;
    }

    /**
     * Get Java Graphics Context shared flag.
     * @return Current sharedjgcflag value.
     */
    public
    boolean getSharedJGCFlag()
    {
        return sharedjgcflag;
    }


    /**
     * Set Java Graphics Context rendering flag
     * @param sharedjgcflag Boolean flag.
     */
    public
    void setRenderingJGCFlag(boolean renderingjgcflag)
    {
        this.renderingjgcflag = renderingjgcflag;
    }

    /**
     * Get Java Graphics Context rendering flag.
     * @return Current renderingjgcflag value.
     */
    public
    boolean getRenderingJGCFlag()
    {
        return renderingjgcflag;
    }


    /**
     * Applies display window translation and size to the proper JGC.
     */
    public
    void syncJGCtoDspwin()
    {
        if (this.getSharedJGCFlag())
        {
            YxxfGfxContext topgc = getTopGC();

            topgc.getJGC().setClip(null); // reset clip

            topgc.getJGC().translate(-topgc.jgctranslate.x, -topgc.jgctranslate.y); // reset translate
            topgc.getJGC().translate(this.getDspwinX_absolute(), this.getDspwinY_absolute()); // set translate
            topgc.jgctranslate.x = this.getDspwinX_absolute();
            topgc.jgctranslate.y = this.getDspwinY_absolute();

            topgc.getJGC().setClip(0, 0, this.getDspwinWidth(), this.getDspwinHeight()); // set clip
        }
        else
        {
            this.getJGC().setClip(null); // reset clip

            this.getJGC().translate(-this.jgctranslate.x, -this.jgctranslate.y); // reset translate
            this.getJGC().translate(this.getDspwinX_absolute(), this.getDspwinY_absolute()); // set translate
            this.jgctranslate.x = this.getDspwinX_absolute();
            this.jgctranslate.y = this.getDspwinY_absolute();

            this.getJGC().setClip(0, 0, this.getDspwinWidth(), this.getDspwinHeight()); // set clip

            // Rectangle parentclip = null; // parentGC.getJGC().getClipBounds();
            // if (parentclip == null)
            // {
            //     this.getJGC().setClip(0, 0, this.getDspwinWidth(), this.getDspwinHeight());
            // }
            // else
            // {
            //     this.getJGC().setClip(parentclip);
            //     this.getJGC().clipRect(0, 0, this.getDspwinWidth(), this.getDspwinHeight());
            // }
        }

        // System.out.println("gc.syncJGCtoDspwin:gc=[\n" + this + "]");
    }
    //==========================================================================




    //==========================================================================
    // Display Window position and size

    /**
     * Drawing window position and size (pixels).
     */
    private
    Rectangle                   dspwin          = new Rectangle();


    /**
     * Set display window x and y from ints.
     * @param x X.
     * @param x Y.
     */
    public
    void setDspwinXY(int x, int y)
    {
        dspwin.x = x;
        dspwin.y = y;
    }

    /**
     * Set display window width and height from ints.
     * @param width Width.
     * @param height Height.
     */
    public
    void setDspwinWidthHeight(int width, int height)
    {
        dspwin.width = width;
        dspwin.height = height;
    }

    /**
     * Set display window x and y from a Point.
     * @param p Point.
     */
    public
    void setDspwin(Point p)
    {
        dspwin.x = p.x;
        dspwin.y = p.y;
    }

    /**
     * Set display window width and height from a Dimension.
     * @param d Dimension.
     */
    public
    void setDspwin(Dimension d)
    {
        dspwin.width = d.width;
        dspwin.height = d.height;
    }

    /**
     * Set display window x, y, width and height from ints.
     * @param x X.
     * @param y Y.
     * @param width Width.
     * @param height Height.
     */
    public
    void setDspwin(int x, int y, int width, int height)
    {
        dspwin.x = x;
        dspwin.y = y;
        dspwin.width = width;
        dspwin.height = height;
    }

    /**
     * Set display window x, y, width and height from a Rectangle.
     * @param r Rectangle.
     */
    public
    void setDspwin(Rectangle r)
    {
        dspwin.x = r.x;
        dspwin.y = r.y;
        dspwin.width = r.width;
        dspwin.height = r.height;
    }

    /**
     * Set display window x.
     * @param x X.
     */
    public
    void setDspwinX(int x)
    {
        dspwin.x = x;
    }

    /**
     * Set display window y.
     * @param y Y.
     */
    public
    void setDspwinY(int y)
    {
        dspwin.y = y;
    }

    /**
     * Set display window width.
     * @param width Width.
     */
    public
    void setDspwinWidth(int width)
    {
        dspwin.width = width;
    }

    /**
     * Set display window height.
     * @param height Height.
     */
    public
    void setDspwinHeight(int height)
    {
        dspwin.height = height;
    }

    /**
     * Get display window x and y as a Point.
     */
    public
    Point getDspwinXY()
    {
        return new Point(dspwin.x, dspwin.y);
    }

    /**
     * Get display window width and height as a Dimension.
     */
    public
    Dimension getDspwinWidthHeight()
    {
        return new Dimension(dspwin.width, dspwin.height);
    }

    /**
     * Get display window x, y, width and height as a Rectangle.
     */
    public
    Rectangle getDspwin()
    {
        return new Rectangle(dspwin.x, dspwin.y, dspwin.width, dspwin.height);
    }

    /**
     * Get display window x.
     */
    public
    int getDspwinX()
    {
        return dspwin.x;
    }

    /**
     * Get display window y.
     */
    public
    int getDspwinY()
    {
        return dspwin.y;
    }

    /**
     * Get display window width.
     */
    public
    int getDspwinWidth()
    {
        return dspwin.width;
    }

    /**
     * Get display window height.
     */
    public
    int getDspwinHeight()
    {
        return dspwin.height;
    }


    /**
     * Get absolute display window x.
     */
    public
    int getDspwinX_absolute()
    {
        if (parentGC == null)
            return dspwin.x;
        else
            return parentGC.getDspwinX_absolute() + dspwin.x;
    }

    /**
     * Get absolute display window y.
     */
    public
    int getDspwinY_absolute()
    {
        if (parentGC == null)
            return dspwin.y;
        else
            return parentGC.getDspwinY_absolute() + dspwin.y;
    }
    //==========================================================================




    //==========================================================================
    /**
     * Current background as Color.
     */
    private
    Color               curr_bgcolor_Color          = Color.black;

    /**
     * Current background as String.
     */
    private
    String              curr_bgcolor_String         = "#000000";

    /**
     * Set background as Color.
     */
    public
    void setBGColor(Color colval)
    {
        curr_bgcolor_Color  = colval;
        curr_bgcolor_String = YutilProperties.convertColorToString(colval);
    }

    /**
     * Set background as String.
     */
    public
    void setBGColor(String strval)
    {
        curr_bgcolor_String = strval;
        curr_bgcolor_Color  = YutilProperties.convertStringToColor(strval);
    }

    /**
     * Get background as Color.
     */
    public
    Color getBGColor_Color()
    {
        return curr_bgcolor_Color;
    }

    /**
     * Get background as String.
     */
    public
    String getBGColor_String()
    {
        return curr_bgcolor_String;
    }
    //==========================================================================




    //==========================================================================
    /**
     * Palette for this context.
     */
    private
    YxxfGfxPalette              pal             = new YxxfGfxPalette();

    /**
     * Palette array for this context (from pal).
     */
    private
    Color                       palarr[]        = pal.getPaletteArray();

    /**
     * Set palette
     * @param pal The palette.
     */
    public
    void setPalette(YxxfGfxPalette pal)
    {
        this.pal = pal;
        palarr = this.pal.getPaletteArray();
    }

    /**
     * Get palette.
     * @return The palette.
     */
    public
    YxxfGfxPalette getPalette()
    {
        return pal;
    }
    //==========================================================================




    //==========================================================================
    //
    // Work points
    //

    /**
     * Work points - world - for external use only TODO Give an explanation
     * of how these are used and the difference between workpoints world and
     * screen What do the numbers mean.
     */
    public
    YxxfGfxPointW               pw0             = new YxxfGfxPointW();
    /**
     * Work points - world - for external use only.
     */
    public
    YxxfGfxPointW               pw1             = new YxxfGfxPointW();
    /**
     * Work points - world - for external use only.
     */
    public
    YxxfGfxPointW               pw2             = new YxxfGfxPointW();
    /**
     * Work points - world - for external use only.
     */
    public
    YxxfGfxPointW               pw3             = new YxxfGfxPointW();
    /**
     * Work points - world - for external use only.
     */
    public
    YxxfGfxPointW               pw4             = new YxxfGfxPointW();
    /**
     * Work points - world - for external use only.
     */
    public
    YxxfGfxPointW               pw5             = new YxxfGfxPointW();
    /**
     * Work points - world - for external use only.
     */
    public
    YxxfGfxPointW               pw6             = new YxxfGfxPointW();
    /**
     * Work points - world - for external use only.
     */
    public
    YxxfGfxPointW               pw7             = new YxxfGfxPointW();
    /**
     * Work points - world - for external use only.
     */
    public
    YxxfGfxPointW               pw8             = new YxxfGfxPointW();

    /**
     * Work points - world - for external use only.
     */
    public
    YxxfGfxPointW               pw110           = new YxxfGfxPointW();
    /**
     * Work points - world - for external use only.
     */
    public
    YxxfGfxPointW               pw120           = new YxxfGfxPointW();
    /**
     * Work points - world - for external use only.
     */
    public
    YxxfGfxPointW               pw121           = new YxxfGfxPointW();
    /**
     * Work points - world - for external use only.
     */
    public
    YxxfGfxPointW               pw122           = new YxxfGfxPointW();
    /**
     * Work points - world - for external use only.
     */
    public
    YxxfGfxPointW               pw123           = new YxxfGfxPointW();
    /**
     * Work points - world - for external use only.
     */
    public
    YxxfGfxPointW               pw124           = new YxxfGfxPointW();
    /**
     * Work points - world - for external use only.
     */
    public
    YxxfGfxPointW               pw210           = new YxxfGfxPointW();
    /**
     * Work points - world - for external use only.
     */
    public
    YxxfGfxPointW               pw220           = new YxxfGfxPointW();
    /**
     * Work points - world - for external use only.
     */
    public
    YxxfGfxPointW               pw221           = new YxxfGfxPointW();
    /**
     * Work points - world - for external use only.
     */
    public
    YxxfGfxPointW               pw222           = new YxxfGfxPointW();
    /**
     * Work points - world - for external use only.
     */
    public
    YxxfGfxPointW               pw223           = new YxxfGfxPointW();
    /**
     * Work points - world - for external use only.
     */
    public
    YxxfGfxPointW               pw224           = new YxxfGfxPointW();
    /**
     * Work points - world - for external use only.
     */
    public
    YxxfGfxPointW               pw310           = new YxxfGfxPointW();
    /**
     * Work points - world - for external use only.
     */
    public
    YxxfGfxPointW               pw320           = new YxxfGfxPointW();
    /**
     * Work points - world - for external use only.
     */
    public
    YxxfGfxPointW               pw321           = new YxxfGfxPointW();
    /**
     * Work points - world - for external use only.
     */
    public
    YxxfGfxPointW               pw322           = new YxxfGfxPointW();
    /**
     * Work points - world - for external use only.
     */
    public
    YxxfGfxPointW               pw323           = new YxxfGfxPointW();
    /**
     * Work points - world - for external use only.
     */
    public
    YxxfGfxPointW               pw324           = new YxxfGfxPointW();

    /**
     * Work points - world - for external use only.
     */
    public
    YxxfGfxPointW               pw401           = new YxxfGfxPointW();
    /**
     * Work points - world - for external use only.
     */
    public
    YxxfGfxPointW               pw402           = new YxxfGfxPointW();
    /**
     * Work points - world - for external use only.
     */
    public
    YxxfGfxPointW               pw403           = new YxxfGfxPointW();
    /**
     * Work points - world - for external use only.
     */
    public
    YxxfGfxPointW               pw404           = new YxxfGfxPointW();


    /**
     * Work points - screen - for external use only.
     */
    public
    YxxfGfxPointS               ps0             = new YxxfGfxPointS();
    /**
     * Work points - screen - for external use only.
     */
    public
    YxxfGfxPointS               ps1             = new YxxfGfxPointS();
    /**
     * Work points - screen - for external use only.
     */
    public
    YxxfGfxPointS               ps2             = new YxxfGfxPointS();
    /**
     * Work points - screen - for external use only.
     */
    public
    YxxfGfxPointS               ps3             = new YxxfGfxPointS();
    /**
     * Work points - screen - for external use only.
     */
    public
    YxxfGfxPointS               ps4             = new YxxfGfxPointS();
    /**
     * Work points - screen - for external use only.
     */
    public
    YxxfGfxPointS               ps5             = new YxxfGfxPointS();
    /**
     * Work points - screen - for external use only.
     */
    public
    YxxfGfxPointS               ps6             = new YxxfGfxPointS();
    /**
     * Work points - screen - for external use only.
     */
    public
    YxxfGfxPointS               ps7             = new YxxfGfxPointS();
    /**
     * Work points - screen - for external use only.
     */
    public
    YxxfGfxPointS               ps8             = new YxxfGfxPointS();
    //==========================================================================




    //==========================================================================
    // Main Draw Thread Control
    //

    /**
     * High level draw state control - UNKNOWN.
     */
    public static final int     GC_DOING_UNKNOWN        =   0;

    /**
     * High level draw state control - WAIT.
     */
    public static final int     GC_DOING_WAITING        =   1;

    /**
     * High level draw state control - QUIT.
     */
    public static final int     GC_DOING_QUIT           =   2;

    /**
     * High level draw state control - STOPPED.
     */
    public static final int     GC_DOING_STOPPED        =   3;

    /**
     * High level draw state control - REDRAW.
     */
    public static final int     GC_DOING_REDRAW         =   4;
    /**
     * High level draw state control - DRAW.
     */
    public static final int     GC_DOING_DRAWING        =   5;
    /**
     * High level draw state control - TODO.
     */
    public static final int     GC_DOING_RBL            =   6;
    /**
     * High level draw state control - TODO.
     */
    public static final int     GC_DOING_RBB            =   7;

    /**
     * High level draw state control.
     */
    public
    int                         gc_doing                = GC_DOING_UNKNOWN;


    //
    // Doing flags
    //

    /**
     * Wait monitor object.
     */
    private
    Object                      gc_doing_waitMonitor    = new Object();


    /**
     * Waiter.
     */
    public
    void commandGC_wait()
    {
        synchronized (gc_doing_waitMonitor)
        {
            try { gc_doing_waitMonitor.wait(); }
            catch(InterruptedException e) { System.out.println(e); }
        }
    }

    /**
     * Waiter.
     * @param timeout Maximum period to wait. TODO
     */
    public
    void commandGC_wait(long timeout)
    {
        synchronized (gc_doing_waitMonitor)
        {
            try { gc_doing_waitMonitor.wait(timeout); }
            catch(InterruptedException e) { System.out.println(e); }
        }
    }

    /**
     * Waiter.
     */
    public
    void commandGC_wait_for_complete()
    {
        // System.out.println("gc.commandGC_wait_for_complete,doing=" + gc_doing);
        synchronized (gc_doing_waitMonitor)
        {
            if (gc_doing == YxxfGfxContext.GC_DOING_WAITING) {
                // System.out.println("gc.commandGC_wait_for_complete,x1,doing=" + gc_doing);
                return; }

            while (true)
            {
                try { gc_doing_waitMonitor.wait(); }
                catch(InterruptedException e) { System.out.println(e); }

                if (gc_doing == YxxfGfxContext.GC_DOING_WAITING) {
                    // System.out.println("gc.commandGC_wait_for_complete,x1,doing=" + gc_doing);
                    return; }
            }
        }
    }

    /**
     * Waiter.
     */
    public
    void commandGC_wait_for_complete(long timeout)
    {
        synchronized (gc_doing_waitMonitor)
        {
            if (gc_doing == YxxfGfxContext.GC_DOING_WAITING)
                return;

            while (true)
            {
                try { gc_doing_waitMonitor.wait(timeout); }
                catch(InterruptedException e) { System.out.println(e); }

                if (gc_doing == YxxfGfxContext.GC_DOING_WAITING)
                    return;
            }
        }
    }


    /**
     * Commander Set UNKNOWN.
     */
    public
    void commandGC_set_doing_unknown()
    {
        synchronized (gc_doing_waitMonitor)
        {
            gc_doing = YxxfGfxContext.GC_DOING_UNKNOWN;
            gc_doing_waitMonitor.notifyAll();
        }
    }

    /**
     * Commander Set WAITING.
     */
    public
    void commandGC_set_doing_waiting()
    {
        synchronized (gc_doing_waitMonitor)
        {
            gc_doing = YxxfGfxContext.GC_DOING_WAITING;
            gc_doing_waitMonitor.notifyAll();
        }
    }

    /**
     * Commander Set QUIT.
     */
    public
    void commandGC_set_doing_quit()
    {
        synchronized (gc_doing_waitMonitor)
        {
            gc_doing = YxxfGfxContext.GC_DOING_QUIT;
            gc_doing_waitMonitor.notifyAll();
        }
    }

    /**
     * Commander Set DRAWING.
     */
    public
    void commandGC_set_doing_drawing()
    {
        synchronized (gc_doing_waitMonitor)
        {
            gc_doing = YxxfGfxContext.GC_DOING_DRAWING;
            gc_doing_waitMonitor.notifyAll();
        }
    }

    /**
     * Commander Set REDRAW.
     */
    public
    void commandGC_set_doing_redraw()
    {
        synchronized (gc_doing_waitMonitor)
        {
            gc_doing = YxxfGfxContext.GC_DOING_REDRAW;
            gc_doing_waitMonitor.notifyAll();
        }
    }
    //==========================================================================




    //==========================================================================
    // Calculated extents

    /**
     * Calculated Extents minimum point.
     */
    public
    YxxfGfxPointW               calc_extmin     = new YxxfGfxPointW(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);

    /**
     * Calculated Extents maximum point.
     */
    public
    YxxfGfxPointW               calc_extmax     = new YxxfGfxPointW(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);


    /**
     * calc_extents flag.
     * When true the next redraw will include calculating the
     * full drawing extents bounding box in calc_extmin and calc_extmax.
     */
    public
    boolean                     gc_doing_flag_calc_extents
                                                        = false;
    /**
     * calc_extents valid flag - (first time flag).
     * Set true when the extents fields have been calculated.
     */
    public
    boolean                     gc_doing_flag_calc_extents_valid
                                                        = false;
    /**
     * zoom_to_calc_extents flag.
     * Set true indicates zoom to extents to be done after extent
     * fields are calculated.
     */
    public
    boolean                     gc_doing_flag_zoom_to_calc_extents
                                                        = false;
    /**
     * calc_extents stack.
     */
    private
    boolean[]                   gc_doing_flag_calc_extents_stack
                                                        = new boolean[5];
    /**
     * calc_extents stack top idx.
     */
    private
    int                         gc_doing_flag_calc_extents_stack_topidx     = -1;
    /**
     * Current extent stack size.
     */
    private
    int                         gc_doing_flag_calc_extents_stack_size       = 5;
    /**
     * Extent stack size increment when expansion needed.
     */
    private final static
    int                         GC_DOING_FLAG_CALC_EXTENTS_STACK_INC        = 3;


    /**
     * Push calc_extents stack.
     * @param val TODO.
     */
    public
    boolean pushCalcExtentsStack(boolean val)
    {
        gc_doing_flag_calc_extents_stack_topidx++;

        if (gc_doing_flag_calc_extents_stack_size <= gc_doing_flag_calc_extents_stack_topidx)
        {   // expand calc_extents stack
            boolean[] new_gc_doing_flag_calc_extents_stack = new boolean[gc_doing_flag_calc_extents_stack_size + GC_DOING_FLAG_CALC_EXTENTS_STACK_INC];

            for (int i = 0; i < gc_doing_flag_calc_extents_stack_size; i++) // move old values
                new_gc_doing_flag_calc_extents_stack[i] = gc_doing_flag_calc_extents_stack[i];
            for (int i = gc_doing_flag_calc_extents_stack_size; i < gc_doing_flag_calc_extents_stack_size + GC_DOING_FLAG_CALC_EXTENTS_STACK_INC; i++) // initialize new values
                new_gc_doing_flag_calc_extents_stack[i] = false;

            // replace stack
            gc_doing_flag_calc_extents_stack = new_gc_doing_flag_calc_extents_stack;
            gc_doing_flag_calc_extents_stack_size += GC_DOING_FLAG_CALC_EXTENTS_STACK_INC;
        }

        gc_doing_flag_calc_extents_stack[gc_doing_flag_calc_extents_stack_topidx] = gc_doing_flag_calc_extents;


        // Set current
        gc_doing_flag_calc_extents = val;

        return gc_doing_flag_calc_extents_stack[gc_doing_flag_calc_extents_stack_topidx];
    }

    /**
     * Pop calc_extents stack.
     * @return true if calculated extents. TODO.
     */
    public
    boolean popCalcExtentsStack()
    {
        gc_doing_flag_calc_extents = gc_doing_flag_calc_extents_stack[gc_doing_flag_calc_extents_stack_topidx];

        gc_doing_flag_calc_extents_stack_topidx--;

        return gc_doing_flag_calc_extents;
    }
    //==========================================================================




    //==========================================================================
    // Rubber Band Line Thread Control
    //

    /**
     * Rubber Band Line state control - Not doing it.
     */
    public static final int     GC_RBL_DOING_NULL       =  20; // not doing it
    /**
     * Rubber Band Line state control - Beginning.
     */
    public static final int     GC_RBL_DOING_BEG        =  21; // mouse down
    /**
     * Rubber Band Line state control - Mouse drag.
     */
    public static final int     GC_RBL_DOING_DRAG       =  22; // mouse drag (while down)
    /**
     * Rubber Band Line state control - Ending.
     */
    public static final int     GC_RBL_DOING_END        =  23; // mouse up

    /**
     * Rubber Band Line state control.
     */
    public
    int                         gc_rbl_doing            = GC_RBL_DOING_NULL;


    /**
     * Rubber Band Line Values - active.
     */
    private
    boolean                     gc_rbl_active           = false;
    /**
     * Rubber Band Line Values - TODO.
     */
    private
    boolean                     gc_rbl_firstdraw        = true;
    /**
     * Rubber Band Line Values - Java Graphics Context.
     */
    private
    Graphics                    gc_rbl_jgc              = null;

    /**
     * Rubber Band Line Values - Beginning x coordinate.
     */
    private
    int                         gc_rbl_beg_x            = 0;
    /**
     * Rubber Band Line Values - Beginning y coordinate.
     */
    private
    int                         gc_rbl_beg_y            = 0;
    /**
     * Rubber Band Line Values - Ending x coordinate.
     */
    private
    int                         gc_rbl_end_x            = 0;
    /**
     * Rubber Band Line Values - Ending y coordinate.
     */
    private
    int                         gc_rbl_end_y            = 0;

    /**
     * Rubber Band Line Values - TODO.
     */
    private
    int                         gc_rbl_drw_beg_x        = 0;
    /**
     * Rubber Band Line Values - TODO.
     */
    private
    int                         gc_rbl_drw_beg_y        = 0;
    /**
     * Rubber Band Line Values - TODO
     */
    private
    int                         gc_rbl_drw_end_x        = 0;
    /**
     * Rubber Band Line Values - TODO
     */
    private
    int                         gc_rbl_drw_end_y        = 0;


    /**
     * Rubber Band Line commander - Beginning.
     * @param x Beginning coordinate.
     * @param y Beginning coordinate.
     * @param jgc Java Graphics Context.
     */
    public
    void commandGC_rbl_beg(int x, int y, Graphics jgc)
    {
        synchronized (gc_doing_waitMonitor)
        {
            gc_rbl_doing = YxxfGfxContext.GC_RBL_DOING_BEG;

            gc_rbl_active = true;
            gc_rbl_firstdraw = true;

            gc_rbl_jgc = jgc;
            drawRBL_setColor();

            gc_rbl_beg_x = x;
            gc_rbl_beg_y = y;

            gc_doing = YxxfGfxContext.GC_DOING_RBL;
            gc_doing_waitMonitor.notifyAll();
        }
    }

    /**
     * Rubber Band Line commander - Dragging.
     * @param x Drag coordinate.
     * @param y Drag coordinate.
     */
    public
    void commandGC_rbl_drag(int x, int y)
    {
        synchronized (gc_doing_waitMonitor)
        {
            gc_rbl_doing = YxxfGfxContext.GC_RBL_DOING_DRAG;

            gc_rbl_end_x = x;
            gc_rbl_end_y = y;

            gc_doing_waitMonitor.notifyAll();
        }
    }

    /**
     * Rubber Band Line commander - End dragging.
     * @param x Ending coordinate.
     * @param y Ending coordinate.
     */
    public
    void commandGC_rbl_end(int x, int y)
    {
        synchronized (gc_doing_waitMonitor)
        {
            gc_rbl_doing = YxxfGfxContext.GC_RBL_DOING_END;

            gc_rbl_end_x = x;
            gc_rbl_end_y = y;

            gc_doing_waitMonitor.notifyAll();
        }
    }


    /**
     * RBL Doers - Beginning TODO
     */
    public
    void drawRBL_beg()
    {
    }

    /**
     * RBL Doer - Dragging TODO
     */
    public
    void drawRBL_drag()
    {
        if (gc_rbl_firstdraw)
            gc_rbl_firstdraw = false;
        else
            drawRBL_drawLine(); // erase last line

        drawRBL_calcLine();
        drawRBL_drawLine();
    }

    /**
     * RBL Doer - Ending TODO
     */
    public
    void drawRBL_end()
    {
        if (gc_rbl_firstdraw)
        {
            synchronized (gc_doing_waitMonitor)
            {
                gc_rbl_doing = YxxfGfxContext.GC_RBL_DOING_NULL;

                gc_rbl_active = false;
                gc_rbl_firstdraw = true;

                gc_rbl_jgc.dispose();
                gc_doing = YxxfGfxContext.GC_DOING_WAITING;
                gc_doing_waitMonitor.notifyAll();
            }
        }
        else
        {
            drawRBL_drawLine(); // erase last line

            // recalc
            drawRBL_calcLine();


            vport.vcpx += (double)(gc_rbl_drw_beg_x - gc_rbl_drw_end_x) /  vscale_pixels_per_vunit;
            vport.vcpy += (double)(gc_rbl_drw_beg_y - gc_rbl_drw_end_y) / -vscale_pixels_per_vunit;


            synchronized (gc_doing_waitMonitor)
            {
                gc_rbl_doing = YxxfGfxContext.GC_RBL_DOING_NULL;

                gc_rbl_active = false;
                gc_rbl_firstdraw = true;

                gc_rbl_jgc.dispose();
                gc_doing = YxxfGfxContext.GC_DOING_REDRAW;
                gc_doing_waitMonitor.notifyAll();
            }
        }
    }


    /**
     * Set the color of the rubber band line. TODO
     */
    private
    void drawRBL_setColor()
    {
        gc_rbl_jgc.setXORMode(Color.black);
        gc_rbl_jgc.setColor(Color.white);
    }

    /**
     * Set drawing coordinates based on rubber band line.
     */
    private
    void drawRBL_calcLine()
    {
        gc_rbl_drw_beg_x = gc_rbl_beg_x;
        gc_rbl_drw_beg_y = gc_rbl_beg_y;
        gc_rbl_drw_end_x = gc_rbl_end_x;
        gc_rbl_drw_end_y = gc_rbl_end_y;
    }

    /**
     * TODO
     */
    private
    void drawRBL_drawLine()
    {
        gc_rbl_jgc.drawLine(gc_rbl_drw_beg_x, gc_rbl_drw_beg_y, gc_rbl_drw_end_x, gc_rbl_drw_end_y);
    }
    //==========================================================================




    //==========================================================================
    // Rubber Band Box Thread Control
    //

    /**
     * Rubber Band Box state control - Not doing it.
     */
    public static final int     GC_RBB_DOING_NULL       =  10; // not doing it
    /**
     * Rubber Band Box state control - Begin drag (mouse down).
     */
    public static final int     GC_RBB_DOING_BEG        =  11; // mouse down
    /**
     * Rubber Band Box state control - Mouse drag while down.
     */
    public static final int     GC_RBB_DOING_DRAG       =  12; // mouse drag (while down)
    /**
     * Rubber Band Box state control - End drag (mouse up).
     */
    public static final int     GC_RBB_DOING_END        =  13; // mouse up

    /**
     * Rubber Band Box state control.
     */
    public
    int                         gc_rbb_doing            = GC_RBB_DOING_NULL;


    /**
     * Rubber Band Box Values - Active.
     */
    private
    boolean                     gc_rbb_active           = false;
    /**
     * Rubber Band Box Values - TODO
     */
    private
    boolean                     gc_rbb_firstdraw        = true;
    /**
     * Rubber Band Box Values - Java Graphics Context.
     */
    private
    Graphics                    gc_rbb_jgc              = null;

    /**
     * Rubber Band Box Values - beginning x coordinate.
     */
    private
    int                         gc_rbb_beg_x            = 0;
    /**
     * Rubber Band Box Values - beginning y coordinate.
     */
    private
    int                         gc_rbb_beg_y            = 0;
    /**
     * Rubber Band Box Values - ending x coordinate.
     */
    private
    int                         gc_rbb_end_x            = 0;
    /**
     * Rubber Band Box Values- ending y coordinate.
     */
    private
    int                         gc_rbb_end_y            = 0;

    /**
     * Rubber Band Box Values - x coordinate. TODO
     */
    private
    int                         gc_rbb_drw_x            = 0;
    /**
     * Rubber Band Box Values- y coordinate. TODO
     */
    private
    int                         gc_rbb_drw_y            = 0;
    /**
     * Rubber Band Box Values - TODO
     */
    private
    int                         gc_rbb_drw_w            = 0;
    /**
     * Rubber Band Box Values - TODO
     */
    private
    int                         gc_rbb_drw_h            = 0;

    /**
     * Rubber Band Box Values - TODO
     */
    private
    double                      gc_rbb_wrk_vx           = 0;
    /**
     * Rubber Band Box Values - TODO
     */
    private
    double                      gc_rbb_wrk_vy           = 0;
    /**
     * Rubber Band Box Values - TODO
     */
    private
    double                      gc_rbb_wrk_vw           = 0;
    /**
     * Rubber Band Box Values - TODO
     */
    private
    double                      gc_rbb_wrk_vh           = 0;


    /**
     * Rubber Band Box Commander - Begin.
     * @param x Beginning coordinate.
     * @param y Beginning coordinate.
     * @param jgc - Java Graphics Context.
     */
    public
    void commandGC_rbb_beg(int x, int y, Graphics jgc)
    {
        synchronized (gc_doing_waitMonitor)
        {
            gc_rbb_doing = YxxfGfxContext.GC_RBB_DOING_BEG;

            gc_rbb_active = true;
            gc_rbb_firstdraw = true;

            gc_rbb_jgc = jgc;
            drawRBB_setColor();

            gc_rbb_beg_x = x;
            gc_rbb_beg_y = y;

            gc_doing = YxxfGfxContext.GC_DOING_RBB;
            gc_doing_waitMonitor.notifyAll();
        }
    }

    /**
     * Rubber Band Box Commander - Drag.
     * @param x Drag coordinate.
     * @param y Drag coordinate.
     */
    public
    void commandGC_rbb_drag(int x, int y)
    {
        synchronized (gc_doing_waitMonitor)
        {
            gc_rbb_doing = YxxfGfxContext.GC_RBB_DOING_DRAG;

            gc_rbb_end_x = x;
            gc_rbb_end_y = y;

            gc_doing_waitMonitor.notifyAll();
        }
    }

    /**
     * Rubber Band Box Commander - End the Drag.
     * @param x Ending Drag coordinate.
     * @param y Ending Drag coordinate.
     */
    public
    void commandGC_rbb_end(int x, int y)
    {
        synchronized (gc_doing_waitMonitor)
        {
            gc_rbb_doing = YxxfGfxContext.GC_RBB_DOING_END;

            gc_rbb_end_x = x;
            gc_rbb_end_y = y;

            gc_doing_waitMonitor.notifyAll();
        }
    }


    /**
     * RBB Doer - Begin.
     */
    public
    void drawRBB_beg()
    {
    }

    /**
     * RBB Doer - Drag.
     */
    public
    void drawRBB_drag()
    {
        if (gc_rbb_firstdraw)
            gc_rbb_firstdraw = false;
        else
            drawRBB_drawRect(); // erase last rect

        drawRBB_calcRect();
        drawRBB_drawRect();
    }

    /**
     * RBB Doer - Redraw RubberBanded Rectangle.
     */
    public
    void drawRBB_end()
    {
        if (gc_rbb_firstdraw)
        {
            synchronized (gc_doing_waitMonitor)
            {
                gc_rbb_doing = YxxfGfxContext.GC_RBB_DOING_NULL;

                gc_rbb_active = false;
                gc_rbb_firstdraw = true;

                gc_rbb_jgc.dispose();
                gc_doing = YxxfGfxContext.GC_DOING_WAITING;
                gc_doing_waitMonitor.notifyAll();
            }
        }
        else
        {
            drawRBB_drawRect(); // erase last rect

            // recalc
            drawRBB_calcRect();


//          System.out.println("========================================================================");
//          System.out.println("drawRBB_end:A:" +
//                             "vport.vheight=" + vport.vheight +
//                            ",vport.vaspect=" + vport.vaspect +
//                            ",vwidth=" + vwidth);
//          System.out.println("drawRBB_end:A:" +
//                             "vport.vcpx=" + vport.vcpx +
//                            ",vport.vcpy=" + vport.vcpy);
//          System.out.println("drawRBB_end:A:" +
//                             "vscale_pixels_per_vunit=" + vscale_pixels_per_vunit +
//                            ",vheight_actual=" + vheight_actual +
//                            ",vwidth_actual=" + vwidth_actual);

//          System.out.println("drawRBB_end:A:" +
//                             "x=" + gc_rbb_drw_x +
//                            ",y=" + gc_rbb_drw_y +
//                            ",w=" + gc_rbb_drw_w +
//                            ",h=" + gc_rbb_drw_h);

            gc_rbb_wrk_vx = (gc_rbb_drw_x /  vscale_pixels_per_vunit);
            gc_rbb_wrk_vy = (gc_rbb_drw_y / -vscale_pixels_per_vunit);

            gc_rbb_wrk_vx -= (vwidth  / 2.0) -
                             (1.0 / vscale_pixels_per_vunit);
            gc_rbb_wrk_vy -= (vheight / 2.0) -
                             vheight_actual +
                             (1.0 / vscale_pixels_per_vunit);

            gc_rbb_wrk_vx += vport.vcpx;
            gc_rbb_wrk_vy += vport.vcpy;

//          System.out.println("drawRBB_end:A:" +
//                             "vx=" + gc_rbb_wrk_vx +
//                            ",vy=" + gc_rbb_wrk_vy );

            gc_rbb_wrk_vw = gc_rbb_drw_w / vscale_pixels_per_vunit;
            gc_rbb_wrk_vh = gc_rbb_drw_h / vscale_pixels_per_vunit;

//          System.out.println("drawRBB_end:A:" +
//                             "vw=" + gc_rbb_wrk_vw +
//                            ",vh=" + gc_rbb_wrk_vh );

            vport.vheight = gc_rbb_wrk_vh;
            vport.vaspect = gc_rbb_wrk_vw / gc_rbb_wrk_vh;

            vport.vcpx = gc_rbb_wrk_vx + (gc_rbb_wrk_vw / 2.0);
            vport.vcpy = gc_rbb_wrk_vy - (gc_rbb_wrk_vh / 2.0);

//          System.out.println("");
//          System.out.println("drawRBB_end:B:" +
//                             "vport.vheight=" + vport.vheight +
//                            ",vport.vaspect=" + vport.vaspect +
//                            ",vwidth=" + vwidth);
//          System.out.println("drawRBB_end:B:" +
//                             "vport.vcpx=" + vport.vcpx +
//                            ",vport.vcpy=" + vport.vcpy);
//          System.out.println("drawRBB_end:B:" +
//                             "vscale_pixels_per_vunit=" + vscale_pixels_per_vunit +
//                            ",vheight_actual=" + vheight_actual +
//                            ",vwidth_actual=" + vwidth_actual);
//          System.out.println("========================================================================");


            synchronized (gc_doing_waitMonitor)
            {
                gc_rbb_doing = YxxfGfxContext.GC_RBB_DOING_NULL;

                gc_rbb_active = false;
                gc_rbb_firstdraw = true;

                gc_rbb_jgc.dispose();
                gc_doing = YxxfGfxContext.GC_DOING_REDRAW;
                gc_doing_waitMonitor.notifyAll();
            }
        }
    }


    /**
     * Calculate and draw Rubber Band color.
     */
    private
    void drawRBB_setColor()
    {
        gc_rbb_jgc.setXORMode(Color.black);
        gc_rbb_jgc.setColor(Color.white);
    }

    /**
     * Calculate rectangle coordinates.
     */
    private
    void drawRBB_calcRect()
    {
        gc_rbb_drw_x = (gc_rbb_end_x < gc_rbb_beg_x) ? gc_rbb_end_x : gc_rbb_beg_x;
        gc_rbb_drw_y = (gc_rbb_end_y < gc_rbb_beg_y) ? gc_rbb_end_y : gc_rbb_beg_y;
        gc_rbb_drw_w = Math.abs(gc_rbb_end_x - gc_rbb_beg_x);
        gc_rbb_drw_h = Math.abs(gc_rbb_end_y - gc_rbb_beg_y);
    }

    /**
     * Draw rectangle.
     */
    private
    void drawRBB_drawRect()
    {
        gc_rbb_jgc.drawRect(gc_rbb_drw_x, gc_rbb_drw_y, gc_rbb_drw_w, gc_rbb_drw_h);
    }
    //==========================================================================




    //==========================================================================
    // Matrix
    //==========================================================================

    //
    // Transformation matrices
    //

    /**
     * Display pipeline.
     */
    private
    YxxfGfxMatrix               M_entity        = new YxxfGfxMatrix(); // ECS
    /**
     * Display pipeline TODO
     */
    private
    YxxfGfxMatrix               M_model         = new YxxfGfxMatrix(); // MCS
    /**
     * Display pipeline TODO
     */
    private
    YxxfGfxMatrix               M_view          = new YxxfGfxMatrix(); // VCS
    /**
     * Display pipeline TODO
     */
    private
    YxxfGfxMatrix               M_user          = new YxxfGfxMatrix(); // LCS
    /**
     * Display pipeline TODO
     */
    private
    YxxfGfxMatrix               M_display       = new YxxfGfxMatrix(); // SCS

    /**
     * Combined matrice TODO
     */
    private
    YxxfGfxMatrix               M_ecs_to_scs    = new YxxfGfxMatrix();
    /**
     * Combined matrice TODO
     */
    private
    YxxfGfxMatrix               M_mcs_to_scs    = new YxxfGfxMatrix();
    /**
     * Combined matrice TODO
     */
    private
    YxxfGfxMatrix               M_vcs_to_scs    = new YxxfGfxMatrix();
    /**
     * Combined matrice TODO
     */
    private
    YxxfGfxMatrix               M_lcs_to_scs    = new YxxfGfxMatrix();


    //
    // Entity to Screen
    //

    /**
     * ECS_to_SCS work point.
     */
    private
    YxxfGfxPointW               pw0_ECS_to_SCS  = new YxxfGfxPointW();

    /**
     * Transform Entity (double x,y,z) to Screen (int x,y).
     * PointW to PointS - normal version.
     * @param wld Entity coordinate.
     * @param scr Screen coordinate.
     */
    private
    void ECS_to_SCS(YxxfGfxPointW wld, YxxfGfxPointS scr)
    {
        pw0_ECS_to_SCS.set(wld);

//      M_entity.mtxTransformPoint(pw0_ECS_to_SCS);
//      M_model.mtxTransformPoint(pw0_ECS_to_SCS);
//      M_view.mtxTransformPoint(pw0_ECS_to_SCS);
//      M_user.mtxTransformPoint(pw0_ECS_to_SCS);
//      M_display.mtxTransformPoint(pw0_ECS_to_SCS);
        M_ecs_to_scs.mtxTransformPoint(pw0_ECS_to_SCS);

        scr.x = (int)Math.round(pw0_ECS_to_SCS.x);
        scr.y = (int)Math.round(pw0_ECS_to_SCS.y);
    }

    /**
     * Transform Entity (double x,y,z) to Screen (int x,y).
     * PointW to PointS - calc extents version.
     * @param wld Entity coordinate.
     * @param scr Screen coordinate.
     */
    private
    void ECS_to_SCS_calc_extents(YxxfGfxPointW wld, YxxfGfxPointS scr)
    {
        pw0_ECS_to_SCS.set(wld);

        M_entity.mtxTransformPoint(pw0_ECS_to_SCS);
        M_model.mtxTransformPoint(pw0_ECS_to_SCS);
        if (calc_extmin.x > pw0_ECS_to_SCS.x) calc_extmin.x = pw0_ECS_to_SCS.x;
        if (calc_extmin.y > pw0_ECS_to_SCS.y) calc_extmin.y = pw0_ECS_to_SCS.y;
        if (calc_extmin.z > pw0_ECS_to_SCS.z) calc_extmin.z = pw0_ECS_to_SCS.z;
        if (calc_extmax.x < pw0_ECS_to_SCS.x) calc_extmax.x = pw0_ECS_to_SCS.x;
        if (calc_extmax.y < pw0_ECS_to_SCS.y) calc_extmax.y = pw0_ECS_to_SCS.y;
        if (calc_extmax.z < pw0_ECS_to_SCS.z) calc_extmax.z = pw0_ECS_to_SCS.z;
        M_view.mtxTransformPoint(pw0_ECS_to_SCS);
        M_user.mtxTransformPoint(pw0_ECS_to_SCS);
        M_display.mtxTransformPoint(pw0_ECS_to_SCS);

        scr.x = (int)Math.round(pw0_ECS_to_SCS.x);
        scr.y = (int)Math.round(pw0_ECS_to_SCS.y);
    }


    //
    // Model to Screen
    //

    /**
     * MCS_to_SCS work point.
     */
    private
    YxxfGfxPointW               pw0_MCS_to_SCS  = new YxxfGfxPointW();

    /**
     * Transform Model (double x,y,z) to Screen (int x,y).
     * PointW to PointS - normal version.
     * @param mdl Model point.
     * @param scr Screen point.
     */
    private
    void MCS_to_SCS(YxxfGfxPointW mdl, YxxfGfxPointS scr)
    {
        pw0_MCS_to_SCS.set(mdl);

//      M_model.mtxTransformPoint(pw0_MCS_to_SCS);
//      M_view.mtxTransformPoint(pw0_MCS_to_SCS);
//      M_user.mtxTransformPoint(pw0_MCS_to_SCS);
//      M_display.mtxTransformPoint(pw0_MCS_to_SCS);
        M_mcs_to_scs.mtxTransformPoint(pw0_MCS_to_SCS);

        scr.x = (int)Math.round(pw0_MCS_to_SCS.x);
        scr.y = (int)Math.round(pw0_MCS_to_SCS.y);
    }

    /**
     * Transform Model (double x,y,z) to Screen (int x,y).
     * PointW to PointS - calc_extents version.
     * @param mdl Model point.
     * @param scr Screen point.
     */
    private
    void MCS_to_SCS_calc_extents(YxxfGfxPointW mdl, YxxfGfxPointS scr)
    {
        pw0_MCS_to_SCS.set(mdl);

        M_model.mtxTransformPoint(pw0_MCS_to_SCS);
        if (calc_extmin.x > pw0_MCS_to_SCS.x) calc_extmin.x = pw0_MCS_to_SCS.x;
        if (calc_extmin.y > pw0_MCS_to_SCS.y) calc_extmin.y = pw0_MCS_to_SCS.y;
        if (calc_extmin.z > pw0_MCS_to_SCS.z) calc_extmin.z = pw0_MCS_to_SCS.z;
        if (calc_extmax.x < pw0_MCS_to_SCS.x) calc_extmax.x = pw0_MCS_to_SCS.x;
        if (calc_extmax.y < pw0_MCS_to_SCS.y) calc_extmax.y = pw0_MCS_to_SCS.y;
        if (calc_extmax.z < pw0_MCS_to_SCS.z) calc_extmax.z = pw0_MCS_to_SCS.z;
        M_view.mtxTransformPoint(pw0_MCS_to_SCS);
        M_user.mtxTransformPoint(pw0_MCS_to_SCS);
        M_display.mtxTransformPoint(pw0_MCS_to_SCS);

        scr.x = (int)Math.round(pw0_MCS_to_SCS.x);
        scr.y = (int)Math.round(pw0_MCS_to_SCS.y);
    }


    //
    // View to Screen
    //

    /**
     * VCS_to_SCS work point.
     */
    private
    YxxfGfxPointW               pw0_VCS_to_SCS  = new YxxfGfxPointW();

    /**
     * Transform View (double x,y,z) to Screen (int x,y).
     * PointW to PointS.
     * @param vew View graphics point.
     * @param scr Screen graphics point.
     */
    private
    void VCS_to_SCS(YxxfGfxPointW vew, YxxfGfxPointS scr)
    {
        pw0_VCS_to_SCS.set(vew);

//      M_view.mtxTransformPoint(pw0_VCS_to_SCS);
//      M_user.mtxTransformPoint(pw0_VCS_to_SCS);
//      M_display.mtxTransformPoint(pw0_VCS_to_SCS);
        M_vcs_to_scs.mtxTransformPoint(pw0_VCS_to_SCS);

        scr.x = (int)Math.round(pw0_VCS_to_SCS.x);
        scr.y = (int)Math.round(pw0_VCS_to_SCS.y);
    }


    //
    // User View to Screen
    //

    /**
     * LCS_to_SCS work point.
     */
    private
    YxxfGfxPointW               pw0_LCS_to_SCS  = new YxxfGfxPointW();

    /**
     * Transform User View (double x,y,z) to Screen (int x,y).
     * PointW to PointS.
     * @param usr The user graphics point.
     * @param scr The screen graphics point.
     */
    private
    void LCS_to_SCS(YxxfGfxPointW usr, YxxfGfxPointS scr)
    {
        pw0_LCS_to_SCS.set(usr);

//      M_user.mtxTransformPoint(pw0_LCS_to_SCS);
//      M_display.mtxTransformPoint(pw0_LCS_to_SCS);
        M_lcs_to_scs.mtxTransformPoint(pw0_LCS_to_SCS);

        scr.x = (int)Math.round(pw0_LCS_to_SCS.x);
        scr.y = (int)Math.round(pw0_LCS_to_SCS.y);
    }


    //
    // Display to Screen
    //

    /**
     * DCS_to_SCS work point.
     */
    private
    YxxfGfxPointW               pw0_DCS_to_SCS  = new YxxfGfxPointW();

    /**
     * Transform Display (double x,y,0) to Screen (int x,y).
     * PointW to PointS.
     * @param dsp The Display graphics point.
     * @param scr The Screen graphics point.
     */
    private
    void DCS_to_SCS(YxxfGfxPointW dsp, YxxfGfxPointS scr)
    {
        pw0_DCS_to_SCS.set(dsp);

        M_display.mtxTransformPoint(pw0_DCS_to_SCS);

        scr.x = (int)Math.round(pw0_DCS_to_SCS.x);
        scr.y = (int)Math.round(pw0_DCS_to_SCS.y);
    }


    //
    // Entity to View
    //

    /**
     * Transform Entity (double x,y,z) to View (double x,y,z).
     * PointW to PointW.
     *
     * @param wld Entity.
     * @param vew View.
     */
    public
    void ECS_to_VCS(YxxfGfxPointW wld, YxxfGfxPointW vew)
    {
        vew.set(wld);

        M_entity.mtxTransformPoint(vew);
        M_model.mtxTransformPoint(vew);
        M_view.mtxTransformPoint(vew);
    }


    //
    // Entity to Model
    //

    /**
     * Transform Entity (double x,y,z) to Model (double x,y,z).
     * PointW to PointW.
     *
     * @param wld Entity.
     * @param mdl Model.
     */
    public
    void ECS_to_MCS(YxxfGfxPointW wld, YxxfGfxPointW mdl)
    {
        mdl.set(wld);

        M_entity.mtxTransformPoint(mdl);
        M_model.mtxTransformPoint(mdl);
    }


    //
    // Entity
    //

    /**
     * Transform Entity (double x,y,z).
     * PointW to PointW.
     *
     * @param wld Entity.
     * @param ety Entity.
     */
    public
    void ECS_to_ECS(YxxfGfxPointW wld, YxxfGfxPointW ety)
    {
        ety.set(wld);

        M_entity.mtxTransformPoint(ety);
    }
    //==========================================================================




    //==========================================================================
    // Display Vport/Viewport calculation
    //==========================================================================

    /**
     * vport/viewport window size (calculated).
     * <br>
     * vport contains only vheight.<br>
     * vaspect is vwidth / vheight.
     * vheight is vwidth / vaspect.
     */
    private
    double vheight;
    /**
     * vport/viewport window size (calculated).
     * vwidth is vheight * vaspect.
     */
    private
    double vwidth;

    /**
     * Scale factor to convert view units to display units.
     * pixels per view unit.
     */
    private
    double vscale_pixels_per_vunit;

    /**
     * Height - view is placed in lower left corner of display.
     * v_actual fields are in view units expanded to actual display size.
     */
    private
    double vheight_actual;
    /**
     * Width - view is placed in lower left corner of display.
     * v_actual fields are in view units expanded to actual display size.
     */
    private
    double vwidth_actual;


    /**
     * Calculation work values.
     */
    private
    YxxfGfxPointW               calcdsp_Ax          = new YxxfGfxPointW();
    /**
     * Calculation work values.
     */
    private
    YxxfGfxPointW               calcdsp_Ay          = new YxxfGfxPointW();
    /**
     * Calculation work values.
     */
    private
    YxxfGfxPointW               calcdsp_Az          = new YxxfGfxPointW();


    /**
     * Calculate display values for tilemode 1 (tiled model space).
     * <br>
     * Calculates:<br>
     * <UL>
     *   <LI>Viewing  transform M_view.
     *   <LI>Display  transform M_display.
     *   <LI>Combined transform M_vcs_to_scs.
     * </UL>
     * Uses:<br>
     * Screen values:<br>
     * <UL>
     *   <LI>dspwin.width.
     *   <LI>dspwin.height.
     *</UL>
     *
     * Vport values:<br>
     * <UL>
     *   <LI>vcp.x,y        view center point.
     *   <LI>vdir.x,y,z     from vtgt point to camera.
     *   <LI>vtgt.x,y,z     target location.
     *   <LI>vheight        view height.
     *   <LI>vaspect        view aspect ratio (vwidth / vheigth).
     *   <LI>viewtwistang   view twist angle around view z axis.
     * </UL>
     */
    public
    void calcdsp_vport()
    {
        //
        // Setup viewing transform M_view
        //

        // Initialize view matrix
        M_view.mtxSetIdentity();

        // Translate view target to origin
        M_view.mtxTranslateInverse(vport.vtgt);

        // Calculate Ax, Ay, Az axes from vdir using Arbitrary Axis Algorithm
        calcdsp_Az.set(vport.vdir);
        calcdsp_Az.normalize();
        YxxfGfxPointW.calcAAA(calcdsp_Ax, calcdsp_Ay, calcdsp_Az);

        // Apply - rotate to correspond to view direction
        // This will cause the viewing direction to align with the normal viewing orientation.
        M_view.mtxRotateAxes_Local_to_World(calcdsp_Ax, calcdsp_Ay, calcdsp_Az);

        // Rotate - view twist angle
        M_view.mtxRotate(YxxfGfxPointW.W0, YxxfGfxPointW.Wz,
                         vport.viewtwistang * (Math.PI / 180.0));


        //
        // Setup display transform M_display
        //

        // Determine view height and width
        vheight = vport.vheight;
        vwidth  = vport.vheight * vport.vaspect;

        // Determine view size relative to aspect of screen window

        // First try scaling the drawing x dimension to the full size of the
        // window x dimension.
        vscale_pixels_per_vunit = (double)(dspwin.width - 1) / vwidth;

        // Now check if the drawing y dimension will fit in the window y dimension.
        if ((vheight * vscale_pixels_per_vunit) > (double)(dspwin.height - 1))
        {
            // If it does not fit (too big) then scale the drawing y dimension to the
            // full size of the window y dimension and use that.
            vscale_pixels_per_vunit = (double)(dspwin.height - 1) / vheight;

            vheight_actual = vheight;
            vwidth_actual  = (double)(dspwin.width - 1)  / vscale_pixels_per_vunit;
        } else
        {
            vheight_actual = (double)(dspwin.height - 1) / vscale_pixels_per_vunit;
            vwidth_actual  = vwidth;
        }


        // Initialize display matrix
        M_display.mtxSetIdentity();

        // Translate - view center point to origin
        M_display.mtxTranslateInverse(vport.vcpx,
                                      vport.vcpy,
                                      0);

        // Translate - proper display center point
        M_display.mtxTranslate((vwidth  / 2.0),                 // center on view width

                               (vheight / 2.0) -                // center on view height
                               vheight_actual,                  // Cart coords to Java coords

                               0);

        // Scale - proper display scale
        M_display.mtxScale( vscale_pixels_per_vunit,
                           -vscale_pixels_per_vunit,            // Cart coords to Java coords
                            vscale_pixels_per_vunit,
                           YxxfGfxPointW.W0);


        //
        // Recalculate combined M
        //
        M_vcs_to_scs.set(M_display);
        M_vcs_to_scs.mtxPreMultiplyApply(M_user);
        M_vcs_to_scs.mtxPreMultiplyApply(M_view);
    }


    /**
     * Calculate display values for tilemode 0 (paper space).
     *
     * <br>Calculates:<br>
     * <UL>
     *   <LI>Viewing  transform M_view.
     *   <LI>Display  transform M_display.
     *   <LI>Combined transform M_vcs_to_scs.
     * </UL>
     *
     * Uses:<br>
     *
     * Screen values:<br>
     * <UL>
     *   <LI>dspwin.width.
     *   <LI>dspwin.height.
     * </UL>
     *
     * Viewport values:<br>
     * <UL>
     *   <LI>vcp.x,y        view center point.
     *   <LI>vdir.x,y,z     from vtgt point to camera.
     *   <LI>vtgt.x,y,z     target location.
     *   <LI>vheight        view height.
     *   <LI>vaspect        view aspect ratio (vwidth / vheigth).
     *   <LI>viewtwistang   view twist angle around view z axis.
     * </UL>
     *
     * TODO: handle no viewport.
     */

    public
    void calcdsp_viewport()
    {
        //
        // Setup viewing transform M_view
        //

        // Initialize view matrix
        M_view.mtxSetIdentity();

        // Translate view target to origin
        M_view.mtxTranslateInverse(viewport.vtgt);

        // Calculate Ax, Ay, Az axes from vdir using Arbitrary Axis Algorithm
        calcdsp_Az.set(viewport.vdir);
        calcdsp_Az.normalize();
        YxxfGfxPointW.calcAAA(calcdsp_Ax, calcdsp_Ay, calcdsp_Az);

        // Apply - rotate to correspond to view direction
        // This will cause the viewing direction to align with the normal viewing orientation.
        M_view.mtxRotateAxes_Local_to_World(calcdsp_Ax, calcdsp_Ay, calcdsp_Az);

        // Rotate - view twist angle
        M_view.mtxRotate(YxxfGfxPointW.W0, YxxfGfxPointW.Wz,
                         viewport.viewtwistang * (Math.PI / 180.0));


        //
        // Setup display transform M_display
        //

        // Determine view height and width
        if (viewport.vpid == 1)
        {   // PSpace
            vheight = viewport.vpheight;
            vwidth  = viewport.vpwidth;
        }
        else
        {   // MSpace
            vheight = viewport.vheight;
            vwidth  = viewport.vheight * (viewport.vpwidth / viewport.vpheight);
        }

        // Determine scale factor based on view height
        vscale_pixels_per_vunit = (double)(dspwin.height - 1) / vheight;
//      vscale_pixels_per_vunit = viewport.vpDspScale;


        // Initialize display matrix
        M_display.mtxSetIdentity();

        // Translate - view center point to origin
        M_display.mtxTranslateInverse(viewport.vcpx,
                                      viewport.vcpy,
                                      0);

        // Translate - proper display center point
        M_display.mtxTranslate((vwidth  / 2.0),                 // center on view width
//                             (1.0 / vscale_pixels_per_vunit), // minus one pixel
                               (vheight / 2.0) -                // center on view height
                               vheight,                         // Cart coords to Java coords
//                             (1.0 / vscale_pixels_per_vunit), // minus one pixel
                               0);

        // Scale - proper display scale
        M_display.mtxScale( vscale_pixels_per_vunit,
                           -vscale_pixels_per_vunit,            // Cart coords to Java coords
                            vscale_pixels_per_vunit,
                           YxxfGfxPointW.W0);


        //
        // Recalculate combined M
        //
        M_vcs_to_scs.set(M_display);
        M_vcs_to_scs.mtxPreMultiplyApply(M_user);
        M_vcs_to_scs.mtxPreMultiplyApply(M_view);
    }
    //==========================================================================




    //==========================================================================
    // User View Matrix changers
    // Rotate x, y, z
    //==========================================================================

    /**
     * User View Matrix changer - reset.
     */
    public
    void commandGC_user_view_reset()
    {
        M_user.mtxSetIdentity();
    }

    /**
     * User View Matrix changer - rotate. TODO
     */
    public
    void commandGC_user_view_rotate_x_p()
    {
        M_user.mtxRotate(YxxfGfxPointW.W0, YxxfGfxPointW.Wx,  Math.PI / 180.0);
    }
    /**
     * User View Matrix changer - rotate. TODO
     */
    public
    void commandGC_user_view_rotate_x_m()
    {
        M_user.mtxRotate(YxxfGfxPointW.W0, YxxfGfxPointW.Wx, -Math.PI / 180.0);
    }

    /**
     * User View Matrix changer - rotate. TODO
     */
    public
    void commandGC_user_view_rotate_y_p()
    {
        M_user.mtxRotate(YxxfGfxPointW.W0, YxxfGfxPointW.Wy,  Math.PI / 180.0);
    }
    /**
     * User View Matrix changer - rotate. TODO
     */
    public
    void commandGC_user_view_rotate_y_m()
    {
        M_user.mtxRotate(YxxfGfxPointW.W0, YxxfGfxPointW.Wy, -Math.PI / 180.0);
    }

    /**
     * User View Matrix changer - rotate. TODO
     */
    public
    void commandGC_user_view_rotate_z_p()
    {
        M_user.mtxRotate(YxxfGfxPointW.W0, YxxfGfxPointW.Wz,  Math.PI / 180.0);
    }
    /**
     * User View Matrix changer - rotate. TODO
     */
    public
    void commandGC_user_view_rotate_z_m()
    {
        M_user.mtxRotate(YxxfGfxPointW.W0, YxxfGfxPointW.Wz, -Math.PI / 180.0);
    }

    /**
     * User View Matrix changer - rotate. TODO
     */
    public
    void commandGC_user_view_rotate_x_value(double value)
    {
        M_user.mtxRotate(YxxfGfxPointW.W0, YxxfGfxPointW.Wx,  value);
    }

    /**
     * User View Matrix changer - rotate. TODO
     */
    public
    void commandGC_user_view_rotate_y_value(double value)
    {
        M_user.mtxRotate(YxxfGfxPointW.W0, YxxfGfxPointW.Wy,  value);
    }

    /**
     * User View Matrix changer - rotate. TODO
     */
    public
    void commandGC_user_view_rotate_z_value(double value)
    {
        M_user.mtxRotate(YxxfGfxPointW.W0, YxxfGfxPointW.Wz,  value);
    }
    //==========================================================================




    //==========================================================================
    //
    // Set Java color using entity header
    // Reference: (1) Inside AutoCAD LT for Windows
    //            (2) Complete AutoCAD
    //
    // Status: Works on all current test cases.
    //
    //
    // === Entity color BYLAYER (value 256) - default if not otherwise set:
    // From (1):
    // Entities that have BYLAYER entity properties always assume the color and linetype of their
    // layer, *unless* they are on layer 0 and in a block inserted on another layer.
    //
    // Layer 0 is a special layer, with chameleon-like properties.  If an entity with BYLAYER
    // properties is on layer 0 and in a block, then when you insert the block, the image of the
    // entity takes on the layer properties of the layer on which the block is inserted.
    //
    // It helps to think of layer 0 as if it were called "layer BYBLOCK."  Blocks created on
    // layer 0 float to the layer current at the time of their insertion.
    //
    //
    // === Entity color BYBLOCK (value 0):
    // From (1):
    // Entities with BYBLOCK color or layer [linetype?] properties work similarly to those with
    // BYLAYER settings on layer 0.  Think of BYBLOCK settings as being not yet defined.  BYBLOCK
    // entities in blocks take on the current entity color and linetype settings at the time
    // the block is inserted instead of the settings current when the block is defined.
    // BYBLOCK entites are completely flexible, unlike those with BYLAYER settings on layer 0,
    // which are predestined to assume only the default color and linetype of the layer on
    // which the block is inserted.
    //
    // In blocks, entities with color and linetypes set BYBLOCK adopt the settings current
    // at the time of their insertion, whether the current setting is BYLAYER or explicit, or
    // even BYBLOCK.  In blocks, entities drawn on layer 0 with settings BYBLOCK act exactly
    // like primitive entities, both floating to the current layer and adopting the current
    // properties at the time of insertion.  These make good general-purpose blocks, such
    // as one-unit squares and circles that are stretched to insert as rectangles and ellipses.
    //
    //
    // === Explicit color setting (values 1 to 255):
    // If the entity color is neither BYLAYER or BYBLOCK it is an explicit color.
    // Direct lookup using that value.
    //
    //
    //==========================================================================




    //==========================================================================
    // Current entity values
    //==========================================================================

    /**
     *            Current entity layer.
     */
    private
    YxxfTblLayer                E_layer                 = new YxxfTblLayer();


    /**
     *            Current entity AutoCAD color.
     */
    private
    int                         E_acolor                = -1; // will never be set to this


    /**
     *            Current entity linetype table entry.
     */
    private
    YxxfTblLtype                E_ltype                 = new YxxfTblLtype();

    /**
     *            Current linetype dashlencount.
     *            From current ltype.
     */
    private
    int                         E_ltype_dashlencount    = 0;

    /**
     *            Current linetype patternlen.
     *            From current ltype.
     */
    private
    double                      E_ltype_patternlen      = 0.0;

    /**
     *            Current linetype lenlist.
     *            From current ltype.
     */
    private
    double[]                    E_ltype_lenlist         = new double[10];
    /**
     *             Current linetype lenlist size.
     */
    private
    int                         E_ltype_lenlist_size    = 10;


    /**
     *            Current entity linetype scale.
     *            Computed from header section and entity values.
     */
    private
    double                      E_ltypescale;


    /**
     * Setup entity.
     * <UL>
     *   <LI>(1) Determine entity layer and determine if drawable.
     *   <LI>(2) Sets E_layer to current entity layer.
     *   <LI>(3) Sets E_acolor to aci of current entity.
     *     Sets Java color.
     *   <LI>(4) Sets E_ltype to ltype of current entity.
     *   <LI>(5) Set M_entity from mtx if present.
     * </UL>
     * @param hdr The entity header.
     * @param mtx The Graphics matrix.
     * @return true if the entity is displayable. TODO
     */
    public
    boolean setupEntity(YxxfEntHeader hdr, YxxfGfxMatrix mtx)
    {
        if (!hdr.hdr_visible) // invisible
            return false;


        YxxfTblLayer loc_layer; // Insert layer


        // ========
        // Determine what layer the entity is on.
        // ========

        if (I_insert_stack_topidx <= 0)
        {   // primitive entity
            loc_layer = hdr.hdr_layer;
        }
        else
        {   // in block
            if (hdr.hdr_layer.equals(YxxfTblLayerKey.STR_LAYERNAME__0)) // entity is on layer 0
            {
                loc_layer = I_layer; // use layer of insert
            }
            else
            {
                loc_layer = hdr.hdr_layer; // use layer of entity
            }
        }


        // If the layer aci is negative the layer is off
        // and the entity is invisible.
        if (loc_layer.aci < 0) // off
            return false;

        // Check if layer is frozen
        if ((loc_layer.flags & 1) == 1) // frozen
            return false;


        // ========
        // Set entity layer
        // ========

        E_layer = loc_layer;


        // ========
        // Determine entity color and set it
        // ========

        // === BYLAYER color
        if (hdr.hdr_aci == YxxfGfxPalette.ACI_BYLAYER)
        {
            setEntity_acolor(E_layer.aci); // use color of entity layer as determined above
        }
        else

        // === BYBLOCK color
        if (hdr.hdr_aci == YxxfGfxPalette.ACI_BYBLOCK)
        {
            setEntity_acolor(I_acolor); // use color of insert
        }
        else

        // === Explicit color
        {
            setEntity_acolor(hdr.hdr_aci); // use color of entity
        }


        // ========
        // Determine entity linetype and set it
        // ========

        // === BYLAYER linetype
        if (hdr.hdr_ltype.equals(YxxfTblLtypeKey.STR_LTYPENAME__BYLAYER))
        {
            setEntity_ltype(E_layer.ltype); // use linetype of entity layer as determined above
        }
        else

        // === BYBLOCK linetype
        if (hdr.hdr_ltype.equals(YxxfTblLtypeKey.STR_LTYPENAME__BYBLOCK))
        {
            setEntity_ltype(I_ltype); // use linetype of insert
        }
        else

        // === Explicit linetype
        {
            setEntity_ltype(hdr.hdr_ltype); // use linetype of entity
        }


        // Set entity linetype scale from header ltscale multiplied by global ltscale
        E_ltypescale = hdr.hdr_ltypescale * D.secHeader.ltscale;


        // ========
        // Set M_entity from mtx
        // ========
        if (mtx != null)
        {
            M_entity.set(mtx);

            // Recalculate combined M
            //
            M_ecs_to_scs.set(M_mcs_to_scs);
            M_ecs_to_scs.mtxPreMultiplyApply(M_entity);
        }


/* ===
        if (hdr instanceof YxxfEntAttdef)
            System.out.println("setupEntity:ent=ATTDEF   ,handle=" + hdr.hdr_handle);
        else
        if (hdr instanceof YxxfEntAttrib)
            System.out.println("setupEntity:ent=ATTRIB   ,handle=" + hdr.hdr_handle);
        else
        if (hdr instanceof YxxfEntBlock)
            System.out.println("setupEntity:ent=BLOCK    ,handle=" + hdr.hdr_handle);
        else
        if (hdr instanceof YxxfEntDimension)
            System.out.println("setupEntity:ent=DIMENSION,handle=" + hdr.hdr_handle);
        else
        if (hdr instanceof YxxfEntInsert)
            System.out.println("setupEntity:ent=INSERT   ,handle=" + hdr.hdr_handle);
        else
        if (hdr instanceof YxxfEntLine)
            System.out.println("setupEntity:ent=LINE     ,handle=" + hdr.hdr_handle);
        else
        if (hdr instanceof YxxfEntPoint)
            System.out.println("setupEntity:ent=POINT    ,handle=" + hdr.hdr_handle);
        else
        if (hdr instanceof YxxfEntPolyline)
            System.out.println("setupEntity:ent=POLYLINE ,handle=" + hdr.hdr_handle);
        else
        if (hdr instanceof YxxfEntSolid)
            System.out.println("setupEntity:ent=SOLID    ,handle=" + hdr.hdr_handle);
        else
        if (hdr instanceof YxxfEntText)
            System.out.println("setupEntity:ent=TEXT     ,handle=" + hdr.hdr_handle);
        else
        if (hdr instanceof YxxfEntViewport)
            System.out.println("setupEntity:ent=VIEWPORT ,handle=" + hdr.hdr_handle);
        else
            System.out.println("setupEntity:ent=unknown  ,handle=" + hdr.hdr_handle);
=== */
        return true;
    }


    /**
     * Set Entity Matrix.
     * @param mtx The Graphics Matrix.
     * @return Always returns true. TODO
     */
    public
    boolean setEntityMatrix(YxxfGfxMatrix mtx)
    {
        // Set M_entity from mtx
        if (mtx != null)
        {
            M_entity.set(mtx);

            // Recalculate combined M
            //
            M_ecs_to_scs.set(M_mcs_to_scs);
            M_ecs_to_scs.mtxPreMultiplyApply(M_entity);
        }

        return true;
    }

    /**
     * Set Java drawing color from AutoCAD Color Index.
     * @param acolor A color.
     */
    public
    void setEntity_acolor(int acolor)
    {
        if (acolor != E_acolor)
        {
            jgc.setColor(palarr[acolor]);
            E_acolor = acolor;
        }
    }


    /**
     * Set Entity line type.
     * @param The line type.
     */
    public
    void setEntity_ltype(YxxfTblLtype ltype)
    {
        if (E_ltype != ltype)
        {
            // Lookup linetype and set ref to table entry
            E_ltype = ltype;

            E_ltype_dashlencount = E_ltype.dashlencount;
            E_ltype_patternlen = E_ltype.patternlen;
            if (E_ltype_dashlencount > E_ltype_lenlist_size)
            {   // expand lenlist
                E_ltype_lenlist = new double[E_ltype_dashlencount];
                E_ltype_lenlist_size = E_ltype_dashlencount;
            }
            for (int i = 0; i < E_ltype_dashlencount; i++)
            {
                E_ltype_lenlist[i] = ((Double)E_ltype.lenlist.elementAt(i)).doubleValue();
            }
        }
    }


    /**
     * Get whether the line type is continuous or dashed.
     * @return true if continuous.
     */
    public
    boolean getEntity_ltype_isContinuous()
    {
        return E_ltype_dashlencount == 0;
    }
    //==========================================================================




    //==========================================================================
    // Insert stacks and settings

    /**
     *            Insert stack.
     */
    private
    YxxfEntHeader[]             I_insert_stack          = new YxxfEntHeader[5];

    /**
     *            Insert layer stack.
     */
    private
    YxxfTblLayer[]              I_layer_stack           = new YxxfTblLayer[5];

    /**
     *            Insert AutoCAD color stack.
     */
    private
    int[]                       I_acolor_stack          = new int[5];

    /**
     *            Insert AutoCAD linetype stack.
     */
    private
    YxxfTblLtype[]              I_ltype_stack           = new YxxfTblLtype[5];

    /**
     *            Insert stack top idx.
     */
    private
    int                         I_insert_stack_topidx   = -1;
    /**
     * Insert stack size.
     */
    private
    int                         I_insert_stack_size     = 5;
    /**
     * Insert stack size increment.
     */
    private final static
    int                         I_INSERT_STACK_INC      = 3;


    /**
     *            Current insert.
     */
    private
    YxxfEntHeader               I_insert;

    /**
     *            Current insert layer.
     */
    private
    YxxfTblLayer                I_layer;

    /**
     *            Current insert AutoCAD color.
     */
    private
    int                         I_acolor;

    /**
     *            Current insert AutoCAD linetype.
     */
    private
    YxxfTblLtype                I_ltype;




    /**
     * Initialize insert.
     * @param The entity insert.
     */
    public
    void setupInsertInit(YxxfEntInsert ins)
    {
        I_insert    = ins;
        I_layer     = ins.hdr_layer;
        I_acolor    = ins.hdr_aci;
        I_ltype     = ins.hdr_ltype;

        I_insert_stack_topidx = -1;
    }




    /**
     * Setup and push insert stack.
     * <UL>
     *   <LI>(1) Determine insert layer and determine if drawable.
     *   <LI>(2) Push insert stack.
     *   <UL>
     *       <LI>Insert stack.
     *       <LI>Insert layer stack.
     *       <LI>Insert acolor stack.
     *       <LI>Insert ltype stack.
     *   </UL>
     *   <LI>(3) Sets I_insert to current insert.
     *   <LI>(4) Sets I_layer to current insert layer.
     *   <LI>(5) Sets I_acolor to aci of current insert.
     *   <LI>(6) Sets I_ltype to ltype of current insert.
     * </UL>
     * @param hdr The entity header.
     * @return true if visible. TODO
     */
    public
    boolean setupInsertPush(YxxfEntHeader hdr)
    {
        if (!hdr.hdr_visible) // invisible
            return false;


        YxxfTblLayer loc_layer; // Insert layer


        // ========
        // Determine what layer the insert is on.
        // ========

        if (I_insert_stack_topidx <= 0)
        {   // primitive entity
            loc_layer = hdr.hdr_layer;
        }
        else
        {   // in block
            if (hdr.hdr_layer.equals(YxxfTblLayerKey.STR_LAYERNAME__0)) // insert is on layer 0
            {
                loc_layer = I_layer; // use layer of insert
            }
            else
            {
                loc_layer = hdr.hdr_layer; // use layer of entity
            }
        }


//      // Note: Inserts insert on off layers but not frozen ones
//      //       thus this is properly commented out.
//      //       Entities do not draw on off layers.
//      //
//      // If the layer aci is negative the layer is off
//      // and the entity is invisible.
//      // Note: Inserts insert on off layers but not frozen ones
//      if (loc_layer.aci < 0) // off
//          return false;

        // Check if layer is frozen
        if ((loc_layer.flags & 1) == 1) // frozen
            return false;


        //
        // Push Insert Stack
        //
        I_insert_stack_topidx++;

        if (I_insert_stack_size <= I_insert_stack_topidx)
        {   // expand insert stack

            YxxfEntHeader[] new_insert_stack    = new YxxfEntHeader[I_insert_stack_size + I_INSERT_STACK_INC];
            for (int i = 0; i < I_insert_stack_size; i++) // move old values
                new_insert_stack[i] = I_insert_stack[i];
            I_insert_stack = new_insert_stack;

            YxxfTblLayer[]  new_layer_stack     = new YxxfTblLayer[I_insert_stack_size + I_INSERT_STACK_INC];
            for (int i = 0; i < I_insert_stack_size; i++) // move old values
                new_layer_stack[i] = I_layer_stack[i];
            I_layer_stack = new_layer_stack;

            int[]           new_acolor_stack    = new int[I_insert_stack_size + I_INSERT_STACK_INC];
            for (int i = 0; i < I_insert_stack_size; i++) // move old values
                new_acolor_stack[i] = I_acolor_stack[i];
            I_acolor_stack = new_acolor_stack;

            YxxfTblLtype[]  new_ltype_stack     = new YxxfTblLtype[I_insert_stack_size + I_INSERT_STACK_INC];
            for (int i = 0; i < I_insert_stack_size; i++) // move old values
                new_ltype_stack[i] = I_ltype_stack[i];
            I_ltype_stack = new_ltype_stack;

            I_insert_stack_size += I_INSERT_STACK_INC;
        }

        I_insert_stack[I_insert_stack_topidx]       = I_insert;

        I_layer_stack[I_insert_stack_topidx]        = I_layer;

        I_acolor_stack[I_insert_stack_topidx]       = I_acolor;

        I_ltype_stack[I_insert_stack_topidx]        = I_ltype;


        // ========
        // Set insert
        // ========

        I_insert = hdr;


        // ========
        // Set insert layer
        // ========

        I_layer = loc_layer;


        // ========
        // Determine insert color
        // ========

        // === BYLAYER color
        if (I_insert.hdr_aci == YxxfGfxPalette.ACI_BYLAYER)
        {
            I_acolor = Math.abs(I_layer.aci); // use color of insert layer
        }
        else

        // === BYBLOCK color
        if (I_insert.hdr_aci == YxxfGfxPalette.ACI_BYBLOCK)
        {
            // don't change - use color of previous insert
        }
        else

        // === Explicit color
        {
            I_acolor = I_insert.hdr_aci; // use color of insert
        }


        // ========
        // Determine insert linetype
        // ========

        // === BYLAYER linetype
        if (I_insert.hdr_ltype.equals(YxxfTblLtypeKey.STR_LTYPENAME__BYLAYER))
        {
            I_ltype = I_layer.ltype; // use linetype of insert layer
        }
        else

        // === BYBLOCK linetype
        if (I_insert.hdr_ltype.equals(YxxfTblLtypeKey.STR_LTYPENAME__BYBLOCK))
        {
            // don't change - use linetype of previous insert
        }
        else

        // === Explicit linetype
        {
            I_ltype = I_insert.hdr_ltype; // use linetype of insert
        }

        return true;
    }


    /**
     * Pop insert stack.
     */
    public
    void popInsertStack()
    {
        I_insert    = I_insert_stack[I_insert_stack_topidx];

        I_layer     = I_layer_stack[I_insert_stack_topidx];

        I_acolor    = I_acolor_stack[I_insert_stack_topidx];

        I_ltype     = I_ltype_stack[I_insert_stack_topidx];

        I_insert_stack_topidx--;
    }
    //==========================================================================




    //==========================================================================
    // Model stack

    /**
     *            Insert model stack.
     */
    private
    YxxfGfxMatrix[]             M_model_stack           = { new YxxfGfxMatrix(),
                                                            new YxxfGfxMatrix(),
                                                            new YxxfGfxMatrix(),
                                                            new YxxfGfxMatrix(),
                                                            new YxxfGfxMatrix() };

    /**
     *            Combined model to display stack.
     */
    private
    YxxfGfxMatrix[]             M_mcs_to_scs_stack      = { new YxxfGfxMatrix(),
                                                            new YxxfGfxMatrix(),
                                                            new YxxfGfxMatrix(),
                                                            new YxxfGfxMatrix(),
                                                            new YxxfGfxMatrix() };

    /**
     *            Insert model stack top idx.
     */
    private
    int                         M_model_stack_topidx    = -1;
    /**
     *            Insert model stack top idx size.
     */
    private
    int                         M_model_stack_size      = 5;
    /**
     *            Insert model stack top idx increment.
     */
    private final static
    int                         M_MODEL_STACK_INC       = 3;


    /**
     *            Insert model temp calculation matrix.
     */
    private
    YxxfGfxMatrix               M_model_temp_mtx        = new YxxfGfxMatrix();
    /**
     *            Insert model temp calculation base point.
     */
    private
    YxxfGfxPointW               M_model_temp_basepnt    = new YxxfGfxPointW();


    /**
     * Set Model Identity.
     */
    public
    void setModelIdentity()
    {
        // set current model transformation
        M_model.mtxSetIdentity();

        // Recalculate combined M
        M_mcs_to_scs.set(M_vcs_to_scs);
        M_mcs_to_scs.mtxPreMultiplyApply(M_model);
    }


    /**
     * set Model.
     * @param mtx The Graphics Matrix.
     */
    public
    void setModel(YxxfGfxMatrix mtx)
    {
        // set current model transformation
        M_model.set(mtx);

        // Recalculate combined M
        M_mcs_to_scs.set(M_vcs_to_scs);
        M_mcs_to_scs.mtxPreMultiplyApply(M_model);
    }


    /**
     * Get the current model transformation.
     * @return The model.
     */
    public
    YxxfGfxMatrix getModel()
    {
        // get current model transformation
        return M_model;
    }


    /**
     * Initialize Model Stack.
     */
    public
    void initModelStack()
    {
        M_model_stack_topidx = -1;

        M_model.mtxSetIdentity();
        M_mcs_to_scs.mtxSetIdentity();
    }


    /**
     * Push Model Stack.
     * @param mtx The Graphics Matrix.
     */
    public
    void pushModelStack(YxxfGfxMatrix mtx, YxxfGfxPointW inspnt, YxxfGfxPointW basepnt)
    {
        M_model_stack_topidx++;

        if (M_model_stack_size <= M_model_stack_topidx)
        {   // expand model stack
            YxxfGfxMatrix[] new_model_stack = new YxxfGfxMatrix[M_model_stack_size + M_MODEL_STACK_INC];

            for (int i = 0; i < M_model_stack_size; i++) // move old values
                new_model_stack[i] = M_model_stack[i];
            for (int i = M_model_stack_size; i < M_model_stack_size + M_MODEL_STACK_INC; i++) // initialize new values
                new_model_stack[i] = new YxxfGfxMatrix();

            // expand model to display stack
            YxxfGfxMatrix[] new_mcs_to_scs_stack = new YxxfGfxMatrix[M_model_stack_size + M_MODEL_STACK_INC];

            for (int i = 0; i < M_model_stack_size; i++) // move old values
                new_mcs_to_scs_stack[i] = M_mcs_to_scs_stack[i];
            for (int i = M_model_stack_size; i < M_model_stack_size + M_MODEL_STACK_INC; i++) // initialize new values
                new_mcs_to_scs_stack[i] = new YxxfGfxMatrix();

            // replace stacks
            M_model_stack = new_model_stack;
            M_mcs_to_scs_stack = new_mcs_to_scs_stack;
            M_model_stack_size += M_MODEL_STACK_INC;
        }

        M_model_stack[M_model_stack_topidx].set(M_model);
        M_mcs_to_scs_stack[M_model_stack_topidx].set(M_mcs_to_scs);


        //
        // Apply insert transformation to current model transformation
        //
        M_model_temp_mtx.set(mtx);
        M_model_temp_basepnt.set(basepnt);
        M_model_temp_mtx.mtxTransformPoint(M_model_temp_basepnt);
        M_model_temp_mtx.mtxTranslate(inspnt);
        M_model_temp_mtx.mtxTranslateInverse(M_model_temp_basepnt);
        M_model.mtxPreMultiplyApply(M_model_temp_mtx);
//      M_model.mtxPreMultiplyApply(mtx);
//      System.out.println("pushModelStack:inspnt=" + inspnt + ",basepnt=" + basepnt);


        //
        // Recalculate combined M
        //
        M_mcs_to_scs.set(M_vcs_to_scs);
        M_mcs_to_scs.mtxPreMultiplyApply(M_model);
    }


    /**
     * Pop model stack.
     */
    public
    void popModelStack()
    {
        M_model.set(M_model_stack[M_model_stack_topidx]);
        M_mcs_to_scs.set(M_mcs_to_scs_stack[M_model_stack_topidx]);

        M_model_stack_topidx--;
    }
    //==========================================================================




    //==========================================================================
    //==========================================================================
    //
    // D R A W I N G   P R I M I T I V E S
    //
    //==========================================================================
    //==========================================================================


    //==========================================================================
    // LTYPE generation notes:
    //
    // Draw patterned lines
    //
    // Draws complex line between p1 and p2
    // use linetype     - determined by global E_ltype and E_ltypescale
    // use width        - determined by args begwid and endwid
    // use thickness    - determined by args thickness and M_entityLine)
    //
    //
    // Cases:
    // 1) Simple line
    // 2) 2D solid wire frame
    // 3) 2D solid filled
    // 2) 2D solid wire frame (mitered)
    // 3) 2D solid filled (mitered)
    // 4) Simple line with extrusion
    // 5)
    //
    //
    // -----------------
    // LTYPE continuous
    //
    //   face_on_view == true <thickness ignored>
    //     width == 0
    //         Draw simple line
    //     width != 0
    //         Draw 2D solid <use fillmode> with mitered corners
    //
    //   face_on_view == false
    //     thickness == 0
    //       width == 0
    //         Draw simple line
    //       width != 0
    //         Draw 2D solid <wire frame> with mitered corners
    //     thickness != 0
    //       width == 0
    //         Draw simple line with extrusion
    //       width != 0
    //         Draw 3D solid <wire frame> with extrusion and mitered corners
    // -----------------
    //
    //
    // -----------------
    // LTYPE pattern
    //
    //   face_on_view == true <thickness ignored>
    //     width == 0
    //         Draw pattern line
    //     width != 0
    //         Draw 2D solid pattern line <use fillmode> with straight corners
    //
    //   face_on_view == false
    //     thickness == 0
    //       width == 0
    //         Draw pattern line
    //       width != 0
    //         Draw 2D solid pattern line <wire frame> with straight corners
    //     thickness != 0
    //       width == 0
    //         Draw pattern line with extrusion
    //       width != 0
    //         Draw 3D solid pattern line <wire frame> with extrusion and straight corners
    // -----------------
    //
    //
    // From AutoCAD LT for Windows 95 User's Guide:
    //
    // Use A-type alignment -
    //
    // Start and stop line draw with first dash.
    //
    // The pattern fits the line so that at least half of the first dash
    // specification begins and ends the line.  If necessary, the first and
    // last dashes are lengthened.
    //
    // Calculate number of full patterns that will fit the line and
    // any remainder.
    //
    // If a line is too short to hold even one dash-dot sequence,
    // a continous line is drawn between the endpoints.
    //
    // If a pattern line is being drawn then at least one full pattern
    // is drawn with the first dash of the first pattern truncated or
    // extended as follows:
    //
    // If the remainder/2 is <  1/2 of the size of the first dash:
    //     Center full patterns[n] + first dash on line and
    //     extend first and last dash.
    //
    // If the remainder/2 is >= 1/2 of the size of the first dash:
    //     Center full patterns[n] + first dash on line and
    //     truncate first and last dash.
    //
    //==========================================================================


    //==========================================================================
    /**
     * Calculation tolerance.
     */
    private static final double  DOUBLE_MIN_TOLERANCE   = 1e-15D;
    //==========================================================================



    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // B A C K G R O U N D
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    /**
     * Draw background.
     */
    public
    void drawBackground()
    {
        // Clear background
        jgc.setColor(curr_bgcolor_Color);
        E_acolor = -1;

        if (renderingjgcflag)
            jgc.fillRect(0, 0, dspwin.width, dspwin.height);
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++




    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // L I N E S
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    /**
     * Line point.
     */
    private
    YxxfGfxPointW               drawLine_pw0    = new YxxfGfxPointW();
    /**
     * Line point.
     */
    private
    YxxfGfxPointW               drawLine_pw1    = new YxxfGfxPointW();
    /**
     * Line point.
     */
    private
    YxxfGfxPointW               drawLine_pw2    = new YxxfGfxPointW();
    /**
     * Line point.
     */
    private
    YxxfGfxPointW               drawLine_pw3    = new YxxfGfxPointW();
    /**
     * Line point.
     */
    private
    YxxfGfxPointW               drawLine_pw4    = new YxxfGfxPointW();
    /**
     * Line point.
     */
    private
    YxxfGfxPointW               drawLine_pw10   = new YxxfGfxPointW();
    /**
     * Line point.
     */
    private
    YxxfGfxPointW               drawLine_pw11   = new YxxfGfxPointW();
    /**
     * Line point.
     */
    private
    YxxfGfxPointW               drawLine_pw12   = new YxxfGfxPointW();
    /**
     * Line point.
     */
    private
    YxxfGfxPointW               drawLine_pw13   = new YxxfGfxPointW();
    /**
     * Line point.
     */
    private
    YxxfGfxPointW               drawLine_pw14   = new YxxfGfxPointW();
    /**
     * Line point.
     */
    private
    YxxfGfxPointW               drawLine_pw20   = new YxxfGfxPointW();
    /**
     * Line point.
     */
    private
    YxxfGfxPointW               drawLine_pw21   = new YxxfGfxPointW();
    /**
     * Line point.
     */
    private
    YxxfGfxPointW               drawLine_pw22   = new YxxfGfxPointW();
    /**
     * Line point.
     */
    private
    YxxfGfxPointW               drawLine_pw23   = new YxxfGfxPointW();
    /**
     * Line point.
     */
    private
    YxxfGfxPointW               drawLine_pw24   = new YxxfGfxPointW();

    /**
     * Line point.
     */
    private
    YxxfGfxPointS               drawLine_ps1    = new YxxfGfxPointS();
    /**
     * Line point.
     */
    private
    YxxfGfxPointS               drawLine_ps2    = new YxxfGfxPointS();
    /**
     * Line point.
     */
    private
    YxxfGfxPointS               drawLine_ps3    = new YxxfGfxPointS();
    /**
     * Line point.
     */
    private
    YxxfGfxPointS               drawLine_ps4    = new YxxfGfxPointS();


    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // MCS simple lines
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    /**
     * Draw MCS simple line. TODO
     * @param p1mcs Point 1. TODO
     * @param p2mcs Point 2. TODO
     */
    public
    void drawLine__MCS__cont__flat__wid_none(YxxfGfxPointW p1mcs,
                                             YxxfGfxPointW p2mcs)
    {
        if (gc_doing_flag_calc_extents)
        {
            MCS_to_SCS_calc_extents(p1mcs, drawLine_ps1);
            MCS_to_SCS_calc_extents(p2mcs, drawLine_ps2);
        }
        else
        {
            MCS_to_SCS(p1mcs, drawLine_ps1);
            MCS_to_SCS(p2mcs, drawLine_ps2);
        }

        if (renderingjgcflag)
            jgc.drawLine(drawLine_ps1.x, drawLine_ps1.y, drawLine_ps2.x, drawLine_ps2.y);
    }


    /**
     * Draw MCS simple line. TODO
     * @param p1mcsx Point 1 x coordinate.
     * @param p1mcsy Point 1 y coordinate.
     * @param p1mcsz Point 1 z coordinate.
     * @param p2mcsx Point 2 x coordinate.
     * @param p2mcsy Point 2 y coordinate.
     * @param p2mcsz Point 2 z coordinate.
     */
    public
    void drawLine__MCS__cont__flat__wid_none(double p1mcsx, double p1mcsy, double p1mcsz,
                                             double p2mcsx, double p2mcsy, double p2mcsz)
    {
        drawLine_pw1.set(p1mcsx, p1mcsy, p1mcsz);
        drawLine_pw2.set(p2mcsx, p2mcsy, p2mcsz);

        if (gc_doing_flag_calc_extents)
        {
            MCS_to_SCS_calc_extents(drawLine_pw1, drawLine_ps1);
            MCS_to_SCS_calc_extents(drawLine_pw2, drawLine_ps2);
        }
        else
        {
            MCS_to_SCS(drawLine_pw1, drawLine_ps1);
            MCS_to_SCS(drawLine_pw2, drawLine_ps2);
        }

        if (renderingjgcflag)
            jgc.drawLine(drawLine_ps1.x, drawLine_ps1.y, drawLine_ps2.x, drawLine_ps2.y);
    }


    /**
     * Draw MCS simple line. TODO
     * @param p1mcs Point 1.
     * @param p2mcs Point 2.
     * @param thickness Line thickness.
     * @param M_entityLine TODO
     */
    public
    void drawLine__MCS__cont__thck__wid_none(YxxfGfxPointW p1mcs,
                                             YxxfGfxPointW p2mcs,
                                             double thickness,
                                             YxxfGfxMatrix M_entityLine)
    {
        drawLine_pw0.set(0, 0, thickness);
        M_entityLine.mtxTransformPoint(drawLine_pw0);
        drawLine_pw3.set(p1mcs.x + drawLine_pw0.x, p1mcs.y + drawLine_pw0.y, p1mcs.z + drawLine_pw0.z);
        drawLine_pw4.set(p2mcs.x + drawLine_pw0.x, p2mcs.y + drawLine_pw0.y, p2mcs.z + drawLine_pw0.z);

        if (gc_doing_flag_calc_extents)
        {
            MCS_to_SCS_calc_extents(p1mcs, drawLine_ps1);
            MCS_to_SCS_calc_extents(p2mcs, drawLine_ps2);
            MCS_to_SCS_calc_extents(drawLine_pw3, drawLine_ps3);
            MCS_to_SCS_calc_extents(drawLine_pw4, drawLine_ps4);
        }
        else
        {
            MCS_to_SCS(p1mcs, drawLine_ps1);
            MCS_to_SCS(p2mcs, drawLine_ps2);
            MCS_to_SCS(drawLine_pw3, drawLine_ps3);
            MCS_to_SCS(drawLine_pw4, drawLine_ps4);
        }

        if (renderingjgcflag)
        {
            jgc.drawLine(drawLine_ps1.x, drawLine_ps1.y, drawLine_ps2.x, drawLine_ps2.y);
            jgc.drawLine(drawLine_ps2.x, drawLine_ps2.y, drawLine_ps4.x, drawLine_ps4.y);
            jgc.drawLine(drawLine_ps4.x, drawLine_ps4.y, drawLine_ps3.x, drawLine_ps3.y);
            jgc.drawLine(drawLine_ps3.x, drawLine_ps3.y, drawLine_ps1.x, drawLine_ps1.y);
        }
    }


    /**
     * Draw MCS simple line. TODO
     * @param p1mcsx Point 1 x coordinate.
     * @param p1mcsy Point 1 y coordinate.
     * @param p1mcsz Point 1 z coordinate.
     * @param p2mcsx Point 2 x coordinate.
     * @param p2mcsy Point 2 y coordinate.
     * @param p2mcsz Point 2 z coordinate.
     * @param thickness Line thickness.
     * @param M_entityLine TODO
     */
    public
    void drawLine__MCS__cont__thck__wid_none(double p1mcsx, double p1mcsy, double p1mcsz,
                                             double p2mcsx, double p2mcsy, double p2mcsz,
                                             double thickness,
                                             YxxfGfxMatrix M_entityLine)
    {
        drawLine_pw1.set(p1mcsx, p1mcsy, p1mcsz);
        drawLine_pw2.set(p2mcsx, p2mcsy, p2mcsz);
        drawLine_pw0.set(0, 0, thickness);
        M_entityLine.mtxTransformPoint(drawLine_pw0);
        drawLine_pw3.set(p1mcsx + drawLine_pw0.x, p1mcsy + drawLine_pw0.y, p1mcsz + drawLine_pw0.z);
        drawLine_pw4.set(p2mcsx + drawLine_pw0.x, p2mcsy + drawLine_pw0.y, p2mcsz + drawLine_pw0.z);

        if (gc_doing_flag_calc_extents)
        {
            MCS_to_SCS_calc_extents(drawLine_pw1, drawLine_ps1);
            MCS_to_SCS_calc_extents(drawLine_pw2, drawLine_ps2);
            MCS_to_SCS_calc_extents(drawLine_pw3, drawLine_ps3);
            MCS_to_SCS_calc_extents(drawLine_pw4, drawLine_ps4);
        }
        else
        {
            MCS_to_SCS(drawLine_pw1, drawLine_ps1);
            MCS_to_SCS(drawLine_pw2, drawLine_ps2);
            MCS_to_SCS(drawLine_pw3, drawLine_ps3);
            MCS_to_SCS(drawLine_pw4, drawLine_ps4);
        }

        if (renderingjgcflag)
        {
            jgc.drawLine(drawLine_ps1.x, drawLine_ps1.y, drawLine_ps2.x, drawLine_ps2.y);
            jgc.drawLine(drawLine_ps2.x, drawLine_ps2.y, drawLine_ps4.x, drawLine_ps4.y);
            jgc.drawLine(drawLine_ps4.x, drawLine_ps4.y, drawLine_ps3.x, drawLine_ps3.y);
            jgc.drawLine(drawLine_ps3.x, drawLine_ps3.y, drawLine_ps1.x, drawLine_ps1.y);
        }
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // ECS simple lines
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    /**
     * Draw ECS simple line. TODO
     * @param p1ocs Point 1.
     * @param p2ocs Point 2.
     */
    public
    void drawLine__ECS__cont__flat__wid_none(YxxfGfxPointW p1ocs,
                                             YxxfGfxPointW p2ocs)
    {
        if (gc_doing_flag_calc_extents)
        {
            ECS_to_SCS_calc_extents(p1ocs, drawLine_ps1);
            ECS_to_SCS_calc_extents(p2ocs, drawLine_ps2);
        }
        else
        {
            ECS_to_SCS(p1ocs, drawLine_ps1);
            ECS_to_SCS(p2ocs, drawLine_ps2);
        }

        if (renderingjgcflag)
            jgc.drawLine(drawLine_ps1.x, drawLine_ps1.y, drawLine_ps2.x, drawLine_ps2.y);
    }


    /**
     * Draw ECS simple line TODO.
     * @param p1ocsx Point 1 x coordinate.
     * @param p1ocsy Point 1 y coordinate.
     * @param p1ocsz Point 1 z coordinate.
     * @param p2ocsx Point 2 x coordinate.
     * @param p2ocsy Point 2 y coordinate.
     * @param p2ocsz Point 2 z coordinate.
     */
    public
    void drawLine__ECS__cont__flat__wid_none(double p1ocsx, double p1ocsy, double p1ocsz,
                                             double p2ocsx, double p2ocsy, double p2ocsz)
    {
        drawLine_pw1.set(p1ocsx, p1ocsy, p1ocsz);
        drawLine_pw2.set(p2ocsx, p2ocsy, p2ocsz);

        if (gc_doing_flag_calc_extents)
        {
            ECS_to_SCS_calc_extents(drawLine_pw1, drawLine_ps1);
            ECS_to_SCS_calc_extents(drawLine_pw2, drawLine_ps2);
        }
        else
        {
            ECS_to_SCS(drawLine_pw1, drawLine_ps1);
            ECS_to_SCS(drawLine_pw2, drawLine_ps2);
        }

        if (renderingjgcflag)
            jgc.drawLine(drawLine_ps1.x, drawLine_ps1.y, drawLine_ps2.x, drawLine_ps2.y);
    }


    /**
     * Draw ECS simple line. TODO
     * @param p1ocs Point 1.
     * @param p2ocs Point 2.
     * @param M_entityLine TODO
     */
    public
    void drawLine__ECS__cont__flat__wid_none(YxxfGfxPointW p1ocs,
                                             YxxfGfxPointW p2ocs,
                                             YxxfGfxMatrix M_entityLine)
    {
        M_entityLine.mtxTransformPoint(drawLine_pw1.set(p1ocs));
        M_entityLine.mtxTransformPoint(drawLine_pw2.set(p2ocs));

        if (gc_doing_flag_calc_extents)
        {
            MCS_to_SCS_calc_extents(drawLine_pw1, drawLine_ps1);
            MCS_to_SCS_calc_extents(drawLine_pw2, drawLine_ps2);
        }
        else
        {
            MCS_to_SCS(drawLine_pw1, drawLine_ps1);
            MCS_to_SCS(drawLine_pw2, drawLine_ps2);
        }

        if (renderingjgcflag)
            jgc.drawLine(drawLine_ps1.x, drawLine_ps1.y, drawLine_ps2.x, drawLine_ps2.y);
    }


    /**
     * Draw ECS simple line. TODO
     * @param p1ocsx Point 1 x coordinate.
     * @param p1ocsy Point 1 y coordinate.
     * @param p1ocsz Point 1 z coordinate.
     * @param p2ocsx Point 2 x coordinate.
     * @param p2ocsy Point 2 y coordinate.
     * @param p2ocsz Point 2 z coordinate.
     * @param M_entityLine TODO
     */
    public
    void drawLine__ECS__cont__flat__wid_none(double p1ocsx, double p1ocsy, double p1ocsz,
                                             double p2ocsx, double p2ocsy, double p2ocsz,
                                             YxxfGfxMatrix M_entityLine)
    {
        M_entityLine.mtxTransformPoint(drawLine_pw1.set(p1ocsx, p1ocsy, p1ocsz));
        M_entityLine.mtxTransformPoint(drawLine_pw2.set(p2ocsx, p2ocsy, p2ocsz));

        if (gc_doing_flag_calc_extents)
        {
            MCS_to_SCS_calc_extents(drawLine_pw1, drawLine_ps1);
            MCS_to_SCS_calc_extents(drawLine_pw2, drawLine_ps2);
        }
        else
        {
            MCS_to_SCS(drawLine_pw1, drawLine_ps1);
            MCS_to_SCS(drawLine_pw2, drawLine_ps2);
        }

        if (renderingjgcflag)
            jgc.drawLine(drawLine_ps1.x, drawLine_ps1.y, drawLine_ps2.x, drawLine_ps2.y);
    }


    /**
     * Draw ECS simple line. TODO
     * @param p1ocs Point 1.
     * @param p2ocs Point 2.
     * @param thickness The line thickness.
     */
    public
    void drawLine__ECS__cont__thck__wid_none(YxxfGfxPointW p1ocs,
                                             YxxfGfxPointW p2ocs,
                                             double thickness)
    {
        drawLine_pw3.set(p1ocs.x, p1ocs.y, p1ocs.z + thickness);
        drawLine_pw4.set(p2ocs.x, p2ocs.y, p2ocs.z + thickness);

        if (gc_doing_flag_calc_extents)
        {
            ECS_to_SCS_calc_extents(p1ocs, drawLine_ps1);
            ECS_to_SCS_calc_extents(p2ocs, drawLine_ps2);
            ECS_to_SCS_calc_extents(drawLine_pw3, drawLine_ps3);
            ECS_to_SCS_calc_extents(drawLine_pw4, drawLine_ps4);
        }
        else
        {
            ECS_to_SCS(p1ocs, drawLine_ps1);
            ECS_to_SCS(p2ocs, drawLine_ps2);
            ECS_to_SCS(drawLine_pw3, drawLine_ps3);
            ECS_to_SCS(drawLine_pw4, drawLine_ps4);
        }

        if (renderingjgcflag)
        {
            jgc.drawLine(drawLine_ps1.x, drawLine_ps1.y, drawLine_ps2.x, drawLine_ps2.y);
            jgc.drawLine(drawLine_ps2.x, drawLine_ps2.y, drawLine_ps4.x, drawLine_ps4.y);
            jgc.drawLine(drawLine_ps4.x, drawLine_ps4.y, drawLine_ps3.x, drawLine_ps3.y);
            jgc.drawLine(drawLine_ps3.x, drawLine_ps3.y, drawLine_ps1.x, drawLine_ps1.y);
        }
    }


    /**
     * Draw ECS simple line. TODO
     * @param p1ocsx Point 1 x coordinate.
     * @param p1ocsy Point 1 y coordinate.
     * @param p1ocsz Point 1 z coordinate.
     * @param p2ocsx Point 2 x coordinate.
     * @param p2ocsy Point 2 y coordinate.
     * @param p2ocsz Point 2 z coordinate.
     * @param thickness The line thickness.
     */
    public
    void drawLine__ECS__cont__thck__wid_none(double p1ocsx, double p1ocsy, double p1ocsz,
                                             double p2ocsx, double p2ocsy, double p2ocsz,
                                             double thickness)
    {
        drawLine_pw1.set(p1ocsx, p1ocsy, p1ocsz);
        drawLine_pw2.set(p2ocsx, p2ocsy, p2ocsz);
        drawLine_pw3.set(p1ocsx, p1ocsy, p1ocsz + thickness);
        drawLine_pw4.set(p2ocsx, p2ocsy, p2ocsz + thickness);

        if (gc_doing_flag_calc_extents)
        {
            ECS_to_SCS_calc_extents(drawLine_pw1, drawLine_ps1);
            ECS_to_SCS_calc_extents(drawLine_pw2, drawLine_ps2);
            ECS_to_SCS_calc_extents(drawLine_pw3, drawLine_ps3);
            ECS_to_SCS_calc_extents(drawLine_pw4, drawLine_ps4);
        }
        else
        {
            ECS_to_SCS(drawLine_pw1, drawLine_ps1);
            ECS_to_SCS(drawLine_pw2, drawLine_ps2);
            ECS_to_SCS(drawLine_pw3, drawLine_ps3);
            ECS_to_SCS(drawLine_pw4, drawLine_ps4);
        }

        if (renderingjgcflag)
        {
            jgc.drawLine(drawLine_ps1.x, drawLine_ps1.y, drawLine_ps2.x, drawLine_ps2.y);
            jgc.drawLine(drawLine_ps2.x, drawLine_ps2.y, drawLine_ps4.x, drawLine_ps4.y);
            jgc.drawLine(drawLine_ps4.x, drawLine_ps4.y, drawLine_ps3.x, drawLine_ps3.y);
            jgc.drawLine(drawLine_ps3.x, drawLine_ps3.y, drawLine_ps1.x, drawLine_ps1.y);
        }
    }


    /**
     * Draw ECS simple line. TODO
     * @param p1ocs Point 1.
     * @param p2ocs Point 2.
     * @param thickness Line thickness.
     * @param M_entityLine TODO
     */
    public
    void drawLine__ECS__cont__thck__wid_none(YxxfGfxPointW p1ocs,
                                             YxxfGfxPointW p2ocs,
                                             double thickness,
                                             YxxfGfxMatrix M_entityLine)
    {
        M_entityLine.mtxTransformPoint(drawLine_pw1.set(p1ocs));
        M_entityLine.mtxTransformPoint(drawLine_pw2.set(p2ocs));
        M_entityLine.mtxTransformPoint(drawLine_pw3.set(p1ocs.x, p1ocs.y, p1ocs.z + thickness));
        M_entityLine.mtxTransformPoint(drawLine_pw4.set(p2ocs.x, p2ocs.y, p2ocs.z + thickness));

        if (gc_doing_flag_calc_extents)
        {
            MCS_to_SCS_calc_extents(drawLine_pw1, drawLine_ps1);
            MCS_to_SCS_calc_extents(drawLine_pw2, drawLine_ps2);
            MCS_to_SCS_calc_extents(drawLine_pw3, drawLine_ps3);
            MCS_to_SCS_calc_extents(drawLine_pw4, drawLine_ps4);
        }
        else
        {
            MCS_to_SCS(drawLine_pw1, drawLine_ps1);
            MCS_to_SCS(drawLine_pw2, drawLine_ps2);
            MCS_to_SCS(drawLine_pw3, drawLine_ps3);
            MCS_to_SCS(drawLine_pw4, drawLine_ps4);
        }

        if (renderingjgcflag)
        {
            jgc.drawLine(drawLine_ps1.x, drawLine_ps1.y, drawLine_ps2.x, drawLine_ps2.y);
            jgc.drawLine(drawLine_ps2.x, drawLine_ps2.y, drawLine_ps4.x, drawLine_ps4.y);
            jgc.drawLine(drawLine_ps4.x, drawLine_ps4.y, drawLine_ps3.x, drawLine_ps3.y);
            jgc.drawLine(drawLine_ps3.x, drawLine_ps3.y, drawLine_ps1.x, drawLine_ps1.y);
        }
    }


    /**
     * Draw ECS simple line. TODO
     * @param p1ocsx Point 1 x coordinate.
     * @param p1ocsy Point 1 y coordinate.
     * @param p1ocsz Point 1 z coordinate.
     * @param p2ocsx Point 2 x coordinate.
     * @param p2ocsy Point 2 y coordinate.
     * @param p2ocsz Point 2 z coordinate.
     * @param thickness The line thickness.
     * @param M_entityLine TODO
     */
    public
    void drawLine__ECS__cont__thck__wid_none(double p1ocsx, double p1ocsy, double p1ocsz,
                                             double p2ocsx, double p2ocsy, double p2ocsz,
                                             double thickness,
                                             YxxfGfxMatrix M_entityLine)
    {
        M_entityLine.mtxTransformPoint(drawLine_pw1.set(p1ocsx, p1ocsy, p1ocsz));
        M_entityLine.mtxTransformPoint(drawLine_pw2.set(p2ocsx, p2ocsy, p2ocsz));
        M_entityLine.mtxTransformPoint(drawLine_pw3.set(p1ocsx, p1ocsy, p1ocsz + thickness));
        M_entityLine.mtxTransformPoint(drawLine_pw4.set(p2ocsx, p2ocsy, p2ocsz + thickness));

        if (gc_doing_flag_calc_extents)
        {
            MCS_to_SCS_calc_extents(drawLine_pw1, drawLine_ps1);
            MCS_to_SCS_calc_extents(drawLine_pw2, drawLine_ps2);
            MCS_to_SCS_calc_extents(drawLine_pw3, drawLine_ps3);
            MCS_to_SCS_calc_extents(drawLine_pw4, drawLine_ps4);
        }
        else
        {
            MCS_to_SCS(drawLine_pw1, drawLine_ps1);
            MCS_to_SCS(drawLine_pw2, drawLine_ps2);
            MCS_to_SCS(drawLine_pw3, drawLine_ps3);
            MCS_to_SCS(drawLine_pw4, drawLine_ps4);
        }

        if (renderingjgcflag)
        {
            jgc.drawLine(drawLine_ps1.x, drawLine_ps1.y, drawLine_ps2.x, drawLine_ps2.y);
            jgc.drawLine(drawLine_ps2.x, drawLine_ps2.y, drawLine_ps4.x, drawLine_ps4.y);
            jgc.drawLine(drawLine_ps4.x, drawLine_ps4.y, drawLine_ps3.x, drawLine_ps3.y);
            jgc.drawLine(drawLine_ps3.x, drawLine_ps3.y, drawLine_ps1.x, drawLine_ps1.y);
        }
    }


    /**
     * Draw ECS simple line. TODO
     * @param p1ocs Point 1.
     * @param p2ocs Point 2.
     * @param begwid Beginning width.
     * @param endwid Ending width.
     */
    public
    void drawLine__ECS__cont__flat__wid_fill(YxxfGfxPointW p1ocs,
                                             YxxfGfxPointW p2ocs,
                                             double begwid, double endwid)
    {
        // find unit vector along line
        // pw10 will be length of 1
        drawLine_pw10.set(p2ocs.x - p1ocs.x, p2ocs.y - p1ocs.y, p2ocs.z - p1ocs.z);
        drawLine_pw10.normalize();

        // find width vector
        drawLine_pw20.crossProduct(drawLine_pw10, YxxfGfxPointW.Wz);
        drawLine_pw20.normalize();

        begwid /= 2.0;
        endwid /= 2.0;

        // Solid fill - 2D
        drawLine_pw21.set(p1ocs.x + (drawLine_pw20.x * begwid),
                          p1ocs.y + (drawLine_pw20.y * begwid),
                          p1ocs.z + (drawLine_pw20.z * begwid));
        drawLine_pw22.set(p1ocs.x - (drawLine_pw20.x * begwid),
                          p1ocs.y - (drawLine_pw20.y * begwid),
                          p1ocs.z - (drawLine_pw20.z * begwid));
        drawLine_pw23.set(p2ocs.x + (drawLine_pw20.x * endwid),
                          p2ocs.y + (drawLine_pw20.y * endwid),
                          p2ocs.z + (drawLine_pw20.z * endwid));
        drawLine_pw24.set(p2ocs.x - (drawLine_pw20.x * endwid),
                          p2ocs.y - (drawLine_pw20.y * endwid),
                          p2ocs.z - (drawLine_pw20.z * endwid));

        if (gc_doing_flag_calc_extents)
        {
            ECS_to_SCS_calc_extents(drawLine_pw21, drawSolid_ps1);
            ECS_to_SCS_calc_extents(drawLine_pw22, drawSolid_ps2);
            ECS_to_SCS_calc_extents(drawLine_pw23, drawSolid_ps3);
            ECS_to_SCS_calc_extents(drawLine_pw24, drawSolid_ps4);
        }
        else
        {
            ECS_to_SCS(drawLine_pw21, drawSolid_ps1);
            ECS_to_SCS(drawLine_pw22, drawSolid_ps2);
            ECS_to_SCS(drawLine_pw23, drawSolid_ps3);
            ECS_to_SCS(drawLine_pw24, drawSolid_ps4);
        }

        drawSolid_xPoints[0] = drawSolid_ps1.x;
        drawSolid_xPoints[1] = drawSolid_ps3.x;
        drawSolid_xPoints[2] = drawSolid_ps4.x;
        drawSolid_xPoints[3] = drawSolid_ps2.x;
        drawSolid_yPoints[0] = drawSolid_ps1.y;
        drawSolid_yPoints[1] = drawSolid_ps3.y;
        drawSolid_yPoints[2] = drawSolid_ps4.y;
        drawSolid_yPoints[3] = drawSolid_ps2.y;

        if (renderingjgcflag)
            jgc.fillPolygon(drawSolid_xPoints, drawSolid_yPoints, 4);
    }


    /**
     * Draw ECS simple line. TODO
     * @param p1ocs Point 1.
     * @param p2ocs Point 2.
     * @param begwid Beginning width.
     * @param endwid Ending width.
     */
    public
    void drawLine__ECS__cont__flat__wid_wire(YxxfGfxPointW p1ocs,
                                             YxxfGfxPointW p2ocs,
                                             double begwid, double endwid)
    {
        // find unit vector along line
        // pw10 will be length of 1
        drawLine_pw10.set(p2ocs.x - p1ocs.x, p2ocs.y - p1ocs.y, p2ocs.z - p1ocs.z);
        drawLine_pw10.normalize();

        // find width vector
        drawLine_pw20.crossProduct(drawLine_pw10, YxxfGfxPointW.Wz);
        drawLine_pw20.normalize();

        begwid /= 2.0;
        endwid /= 2.0;

        // Wire frame - 2D
        drawLine_pw21.set(p1ocs.x + (drawLine_pw20.x * begwid),
                          p1ocs.y + (drawLine_pw20.y * begwid),
                          p1ocs.z + (drawLine_pw20.z * begwid));
        drawLine_pw22.set(p1ocs.x - (drawLine_pw20.x * begwid),
                          p1ocs.y - (drawLine_pw20.y * begwid),
                          p1ocs.z - (drawLine_pw20.z * begwid));
        drawLine_pw23.set(p2ocs.x + (drawLine_pw20.x * endwid),
                          p2ocs.y + (drawLine_pw20.y * endwid),
                          p2ocs.z + (drawLine_pw20.z * endwid));
        drawLine_pw24.set(p2ocs.x - (drawLine_pw20.x * endwid),
                          p2ocs.y - (drawLine_pw20.y * endwid),
                          p2ocs.z - (drawLine_pw20.z * endwid));

        drawLine__ECS__cont__flat__wid_none(drawLine_pw21, drawLine_pw22);
        drawLine__ECS__cont__flat__wid_none(drawLine_pw22, drawLine_pw24);
        drawLine__ECS__cont__flat__wid_none(drawLine_pw24, drawLine_pw23);
        drawLine__ECS__cont__flat__wid_none(drawLine_pw23, drawLine_pw21);
    }


    /**
     * Draw ECS simple line. TODO
     * @param p1ocs Point 1.
     * @param p2ocs Point 2.
     * @param thickness Line thickness.
     * @param begwid Beginning width.
     * @param endwid Ending width.
     */
    public
    void drawLine__ECS__cont__thck__wid_wire(YxxfGfxPointW p1ocs,
                                            YxxfGfxPointW p2ocs,
                                            double thickness,
                                            double begwid, double endwid)
    {
        // find unit vector along line
        // pw10 will be length of 1
        drawLine_pw10.set(p2ocs.x - p1ocs.x, p2ocs.y - p1ocs.y, p2ocs.z - p1ocs.z);
        drawLine_pw10.normalize();

        // find width vector
        drawLine_pw20.crossProduct(drawLine_pw10, YxxfGfxPointW.Wz);
        drawLine_pw20.normalize();

        begwid /= 2.0;
        endwid /= 2.0;

        // Wire frame - 3D
        drawLine_pw21.set(p1ocs.x + (drawLine_pw20.x * begwid),
                          p1ocs.y + (drawLine_pw20.y * begwid),
                          p1ocs.z + (drawLine_pw20.z * begwid));
        drawLine_pw22.set(p1ocs.x - (drawLine_pw20.x * begwid),
                          p1ocs.y - (drawLine_pw20.y * begwid),
                          p1ocs.z - (drawLine_pw20.z * begwid));
        drawLine_pw23.set(p2ocs.x + (drawLine_pw20.x * endwid),
                          p2ocs.y + (drawLine_pw20.y * endwid),
                          p2ocs.z + (drawLine_pw20.z * endwid));
        drawLine_pw24.set(p2ocs.x - (drawLine_pw20.x * endwid),
                          p2ocs.y - (drawLine_pw20.y * endwid),
                          p2ocs.z - (drawLine_pw20.z * endwid));

        // base
        drawLine__ECS__cont__flat__wid_none(drawLine_pw21, drawLine_pw22);
        drawLine__ECS__cont__flat__wid_none(drawLine_pw22, drawLine_pw24);
        drawLine__ECS__cont__flat__wid_none(drawLine_pw24, drawLine_pw23);
        drawLine__ECS__cont__flat__wid_none(drawLine_pw23, drawLine_pw21);

        // sides
        drawLine__ECS__cont__flat__wid_none(drawLine_pw21.x, drawLine_pw21.y, drawLine_pw21.z,
                                            drawLine_pw21.x, drawLine_pw21.y, drawLine_pw21.z + thickness);
        drawLine__ECS__cont__flat__wid_none(drawLine_pw22.x, drawLine_pw22.y, drawLine_pw22.z,
                                            drawLine_pw22.x, drawLine_pw22.y, drawLine_pw22.z + thickness);
        drawLine__ECS__cont__flat__wid_none(drawLine_pw23.x, drawLine_pw23.y, drawLine_pw23.z,
                                            drawLine_pw23.x, drawLine_pw23.y, drawLine_pw23.z + thickness);
        drawLine__ECS__cont__flat__wid_none(drawLine_pw24.x, drawLine_pw24.y, drawLine_pw24.z,
                                            drawLine_pw24.x, drawLine_pw24.y, drawLine_pw24.z + thickness);

        // top
        drawLine__ECS__cont__flat__wid_none(drawLine_pw21.x, drawLine_pw21.y, drawLine_pw21.z + thickness,
                                            drawLine_pw22.x, drawLine_pw22.y, drawLine_pw22.z + thickness);
        drawLine__ECS__cont__flat__wid_none(drawLine_pw22.x, drawLine_pw22.y, drawLine_pw22.z + thickness,
                                            drawLine_pw24.x, drawLine_pw24.y, drawLine_pw24.z + thickness);
        drawLine__ECS__cont__flat__wid_none(drawLine_pw24.x, drawLine_pw24.y, drawLine_pw24.z + thickness,
                                            drawLine_pw23.x, drawLine_pw23.y, drawLine_pw23.z + thickness);
        drawLine__ECS__cont__flat__wid_none(drawLine_pw23.x, drawLine_pw23.y, drawLine_pw23.z + thickness,
                                            drawLine_pw21.x, drawLine_pw21.y, drawLine_pw21.z + thickness);
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // MCS complex lines
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    /**
     * Draw MCS complex line.
     * @param p1 Point 1
     * @param p2 Point 2
     */
    public
    void drawLine__MCS__ltyp__flat__wid_none(YxxfGfxPointW p1,
                                             YxxfGfxPointW p2)
    {
        if (E_ltype_dashlencount == 0)
        {   // Continuous line
            drawLine__MCS__cont__flat__wid_none(p1, p2);
        }
        else

        {   // Patterned line

            // Determine length of line
            double linlen = Math.sqrt( (p2.x - p1.x) * (p2.x - p1.x) +
                                       (p2.y - p1.y) * (p2.y - p1.y) +
                                       (p2.z - p1.z) * (p2.z - p1.z) );

            // Determine length of pattern
            double patlen = E_ltype_patternlen * E_ltypescale;

            if ((linlen + DOUBLE_MIN_TOLERANCE) < patlen)
            {   // line is too short to hold even one pattern,
                // draw continuous line
                drawLine__MCS__cont__flat__wid_none(p1, p2);
                return;
            }

            // calculate pattern values
            int    linlenmod = (int)Math.floor((linlen + DOUBLE_MIN_TOLERANCE) / patlen); // whole pattern units
            double linlenrem = (linlen + DOUBLE_MIN_TOLERANCE) - (patlen * linlenmod); // size of any remainder

            double dshlenone = E_ltype_lenlist[0] * E_ltypescale;

            double linbeg = (linlenrem - dshlenone) / 2.0; // actual line beg

            // find unit vector along line
            // pw10 will be length of 1
            drawLine_pw10.set(p2.x - p1.x, p2.y - p1.y, p2.z - p1.z);
            drawLine_pw10.normalize();


            //
            // Draw pattern
            //   --- Draw first pattern (first dash from beginning of line)
            //   --- Draw subsequent whole patterns
            //   --- Draw last pattern (first dash only to end of line)
            //
            double linpos = linbeg;
            double seglen, segbeg, segend;
            double patpos;


            // --- Draw first pattern (first dash from beginning of line)

            seglen = E_ltype_lenlist[0] * E_ltypescale;

            segend = linpos + seglen;

            // Set end point
            drawLine_pw14.set(p1.x + (drawLine_pw10.x * segend),
                              p1.y + (drawLine_pw10.y * segend),
                              p1.z + (drawLine_pw10.z * segend));

            // Draw it
            drawLine__MCS__cont__flat__wid_none(p1, drawLine_pw14);

            // Increment
            patpos = seglen;


            // Draw rest of pattern
            for (int j = 1; j < E_ltype_dashlencount; j++)
            {
                seglen = E_ltype_lenlist[j] * E_ltypescale;

                if (seglen < 0.0)
                {   // Gap

                    // Increment
                    patpos += -seglen;
                }
                else
                {   // Draw

                    segbeg = linpos + patpos;
                    segend = segbeg + seglen;

                    // Set beg point
                    drawLine_pw13.set(p1.x + (drawLine_pw10.x * segbeg),
                                      p1.y + (drawLine_pw10.y * segbeg),
                                      p1.z + (drawLine_pw10.z * segbeg));

                    // Set end point
                    drawLine_pw14.set(p1.x + (drawLine_pw10.x * segend),
                                      p1.y + (drawLine_pw10.y * segend),
                                      p1.z + (drawLine_pw10.z * segend));

                    // Draw it
                    drawLine__MCS__cont__flat__wid_none(drawLine_pw13, drawLine_pw14);

                    // Increment
                    patpos += seglen;
                }
            }

            // Increment
            linpos += patlen;


            // --- Draw subsequent whole patterns

            // Increment along line in patlen units starting with second pattern run
            for (int i = 1; i < linlenmod; i++)
            {   // Whole pattern runs

                // Increment thru pattern
                patpos = 0;
                for (int j = 0; j < E_ltype_dashlencount; j++)
                {
                    seglen = E_ltype_lenlist[j] * E_ltypescale;

                    if (seglen < 0.0)
                    {   // Gap

                        // Increment
                        patpos += -seglen;
                    }
                    else
                    {   // Draw

                        segbeg = linpos + patpos;
                        segend = segbeg + seglen;

                        // Set beg point
                        drawLine_pw13.set(p1.x + (drawLine_pw10.x * segbeg),
                                          p1.y + (drawLine_pw10.y * segbeg),
                                          p1.z + (drawLine_pw10.z * segbeg));

                        // Set end point
                        drawLine_pw14.set(p1.x + (drawLine_pw10.x * segend),
                                          p1.y + (drawLine_pw10.y * segend),
                                          p1.z + (drawLine_pw10.z * segend));


                        // Draw it
                        drawLine__MCS__cont__flat__wid_none(drawLine_pw13, drawLine_pw14);

                        // Increment
                        patpos += seglen;
                    }
                }

                // Increment
                linpos += patlen;
            } // end of whole pattern runs


            // --- Draw last pattern (first dash only to end of line)

            // Set beg point
            drawLine_pw13.set(p1.x + (drawLine_pw10.x * linpos),
                              p1.y + (drawLine_pw10.y * linpos),
                              p1.z + (drawLine_pw10.z * linpos));

            // Draw it
            drawLine__MCS__cont__flat__wid_none(drawLine_pw13, p2);

        } // end of patterned line
    }


    /**
     * Draw MCS complex line.
     * @param p1 Point 1.
     * @param p2 Point 2.
     * @param thickness The line thickness.
     * @param M_entityLine TODO
     */
    public
    void drawLine__MCS__ltyp__thck__wid_none(YxxfGfxPointW p1,
                                             YxxfGfxPointW p2,
                                             double thickness,
                                             YxxfGfxMatrix M_entityLine)
    {
        if (E_ltype_dashlencount == 0)
        {   // Continuous line
            drawLine__MCS__cont__thck__wid_none(p1, p2, thickness, M_entityLine);
        }
        else

        {   // Patterned line

            // Determine length of line
            double linlen = Math.sqrt( (p2.x - p1.x) * (p2.x - p1.x) +
                                       (p2.y - p1.y) * (p2.y - p1.y) +
                                       (p2.z - p1.z) * (p2.z - p1.z) );

            // Determine length of pattern
            double patlen = E_ltype_patternlen * E_ltypescale;

            if ((linlen + DOUBLE_MIN_TOLERANCE) < patlen)
            {   // line is too short to hold even one pattern,
                // draw continuous line
                drawLine__MCS__cont__thck__wid_none(p1, p2, thickness, M_entityLine);
                return;
            }

            // calculate pattern values
            int    linlenmod = (int)Math.floor((linlen + DOUBLE_MIN_TOLERANCE) / patlen); // whole pattern units
            double linlenrem = (linlen + DOUBLE_MIN_TOLERANCE) - (patlen * linlenmod); // size of any remainder

            double dshlenone = E_ltype_lenlist[0] * E_ltypescale;

            double linbeg = (linlenrem - dshlenone) / 2.0; // actual line beg

            // find unit vector along line
            // pw10 will be length of 1
            drawLine_pw10.set(p2.x - p1.x, p2.y - p1.y, p2.z - p1.z);
            drawLine_pw10.normalize();


            //
            // Draw pattern
            //   --- Draw first pattern (first dash from beginning of line)
            //   --- Draw subsequent whole patterns
            //   --- Draw last pattern (first dash only to end of line)
            //
            double linpos = linbeg;
            double seglen, segbeg, segend;
            double patpos;


            // --- Draw first pattern (first dash from beginning of line)

            seglen = E_ltype_lenlist[0] * E_ltypescale;

            segend = linpos + seglen;

            // Set end point
            drawLine_pw14.set(p1.x + (drawLine_pw10.x * segend),
                              p1.y + (drawLine_pw10.y * segend),
                              p1.z + (drawLine_pw10.z * segend));

            // Draw it
            drawLine__MCS__cont__thck__wid_none(p1, drawLine_pw14, thickness, M_entityLine);

            // Increment
            patpos = seglen;


            // Draw rest of pattern
            for (int j = 1; j < E_ltype_dashlencount; j++)
            {
                seglen = E_ltype_lenlist[j] * E_ltypescale;

                if (seglen < 0.0)
                {   // Gap

                    // Increment
                    patpos += -seglen;
                }
                else
                {   // Draw

                    segbeg = linpos + patpos;
                    segend = segbeg + seglen;

                    // Set beg point
                    drawLine_pw13.set(p1.x + (drawLine_pw10.x * segbeg),
                                      p1.y + (drawLine_pw10.y * segbeg),
                                      p1.z + (drawLine_pw10.z * segbeg));

                    // Set end point
                    drawLine_pw14.set(p1.x + (drawLine_pw10.x * segend),
                                      p1.y + (drawLine_pw10.y * segend),
                                      p1.z + (drawLine_pw10.z * segend));

                    // Draw it
                    drawLine__MCS__cont__thck__wid_none(drawLine_pw13, drawLine_pw14, thickness, M_entityLine);

                    // Increment
                    patpos += seglen;
                }
            }

            // Increment
            linpos += patlen;


            // --- Draw subsequent whole patterns

            // Increment along line in patlen units starting with second pattern run
            for (int i = 1; i < linlenmod; i++)
            {   // Whole pattern runs

                // Increment thru pattern
                patpos = 0;
                for (int j = 0; j < E_ltype_dashlencount; j++)
                {
                    seglen = E_ltype_lenlist[j] * E_ltypescale;

                    if (seglen < 0.0)
                    {   // Gap

                        // Increment
                        patpos += -seglen;
                    }
                    else
                    {   // Draw

                        segbeg = linpos + patpos;
                        segend = segbeg + seglen;

                        // Set beg point
                        drawLine_pw13.set(p1.x + (drawLine_pw10.x * segbeg),
                                          p1.y + (drawLine_pw10.y * segbeg),
                                          p1.z + (drawLine_pw10.z * segbeg));

                        // Set end point
                        drawLine_pw14.set(p1.x + (drawLine_pw10.x * segend),
                                          p1.y + (drawLine_pw10.y * segend),
                                          p1.z + (drawLine_pw10.z * segend));


                        // Draw it
                        drawLine__MCS__cont__thck__wid_none(drawLine_pw13, drawLine_pw14, thickness, M_entityLine);

                        // Increment
                        patpos += seglen;
                    }
                }

                // Increment
                linpos += patlen;
            } // end of whole pattern runs


            // --- Draw last pattern (first dash only to end of line)

            // Set beg point
            drawLine_pw13.set(p1.x + (drawLine_pw10.x * linpos),
                              p1.y + (drawLine_pw10.y * linpos),
                              p1.z + (drawLine_pw10.z * linpos));

            // Draw it
            drawLine__MCS__cont__thck__wid_none(drawLine_pw13, p2, thickness, M_entityLine);

        } // end of patterned line
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // ECS complex lines
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    /**
     * Draw ECS complex line.
     * @param p1 Point 1
     * @param p2 Point 2
     */
    public
    void drawLine__ECS__ltyp__flat__wid_none(YxxfGfxPointW p1,
                                             YxxfGfxPointW p2)
    {
        // Determine length of line
        double linlen = Math.sqrt( (p2.x - p1.x) * (p2.x - p1.x) +
                                   (p2.y - p1.y) * (p2.y - p1.y) +
                                   (p2.z - p1.z) * (p2.z - p1.z) );

        // Determine length of pattern
        double patlen = E_ltype_patternlen * E_ltypescale;

        if ((linlen + DOUBLE_MIN_TOLERANCE) < patlen)
        {   // line is too short to hold even one pattern,
            // draw continuous line
            drawLine__ECS__cont__flat__wid_none(p1, p2);
            return;
        }

        // calculate pattern values
        int    linlenmod = (int)Math.floor((linlen + DOUBLE_MIN_TOLERANCE) / patlen); // whole pattern units
        double linlenrem = (linlen + DOUBLE_MIN_TOLERANCE) - (patlen * linlenmod); // size of any remainder

        double dshlenone = E_ltype_lenlist[0] * E_ltypescale;

        double linbeg = (linlenrem - dshlenone) / 2.0; // actual line beg

        // find unit vector along line
        // pw10 will be length of 1
        drawLine_pw10.set(p2.x - p1.x, p2.y - p1.y, p2.z - p1.z);
        drawLine_pw10.normalize();


        //
        // Draw pattern
        //   --- Draw first pattern (first dash from beginning of line)
        //   --- Draw subsequent whole patterns
        //   --- Draw last pattern (first dash only to end of line)
        //
        double linpos = linbeg;
        double seglen, segbeg, segend;
        double patpos;


        // --- Draw first pattern (first dash from beginning of line)

        seglen = E_ltype_lenlist[0] * E_ltypescale;

        segend = linpos + seglen;

        // Set end point
        drawLine_pw14.set(p1.x + (drawLine_pw10.x * segend),
                          p1.y + (drawLine_pw10.y * segend),
                          p1.z + (drawLine_pw10.z * segend));

        // Draw it
        drawLine__ECS__cont__flat__wid_none(p1, drawLine_pw14);

        // Increment
        patpos = seglen;


        // Draw rest of pattern
        for (int j = 1; j < E_ltype_dashlencount; j++)
        {
            seglen = E_ltype_lenlist[j] * E_ltypescale;

            if (seglen < 0.0)
            {   // Gap

                // Increment
                patpos += -seglen;
            }
            else
            {   // Draw

                segbeg = linpos + patpos;
                segend = segbeg + seglen;

                // Set beg point
                drawLine_pw13.set(p1.x + (drawLine_pw10.x * segbeg),
                                  p1.y + (drawLine_pw10.y * segbeg),
                                  p1.z + (drawLine_pw10.z * segbeg));

                // Set end point
                drawLine_pw14.set(p1.x + (drawLine_pw10.x * segend),
                                  p1.y + (drawLine_pw10.y * segend),
                                  p1.z + (drawLine_pw10.z * segend));

                // Draw it
                drawLine__ECS__cont__flat__wid_none(drawLine_pw13, drawLine_pw14);

                // Increment
                patpos += seglen;
            }
        }

        // Increment
        linpos += patlen;


        // --- Draw subsequent whole patterns

        // Increment along line in patlen units starting with second pattern run
        for (int i = 1; i < linlenmod; i++)
        {   // Whole pattern runs

            // Increment thru pattern
            patpos = 0;
            for (int j = 0; j < E_ltype_dashlencount; j++)
            {
                seglen = E_ltype_lenlist[j] * E_ltypescale;

                if (seglen < 0.0)
                {   // Gap

                    // Increment
                    patpos += -seglen;
                }
                else
                {   // Draw

                    segbeg = linpos + patpos;
                    segend = segbeg + seglen;

                    // Set beg point
                    drawLine_pw13.set(p1.x + (drawLine_pw10.x * segbeg),
                                      p1.y + (drawLine_pw10.y * segbeg),
                                      p1.z + (drawLine_pw10.z * segbeg));

                    // Set end point
                    drawLine_pw14.set(p1.x + (drawLine_pw10.x * segend),
                                      p1.y + (drawLine_pw10.y * segend),
                                      p1.z + (drawLine_pw10.z * segend));


                    // Draw it
                    drawLine__ECS__cont__flat__wid_none(drawLine_pw13, drawLine_pw14);

                    // Increment
                    patpos += seglen;
                }
            }

            // Increment
            linpos += patlen;
        } // end of whole pattern runs


        // --- Draw last pattern (first dash only to end of line)

        // Set beg point
        drawLine_pw13.set(p1.x + (drawLine_pw10.x * linpos),
                          p1.y + (drawLine_pw10.y * linpos),
                          p1.z + (drawLine_pw10.z * linpos));

        // Draw it
        drawLine__ECS__cont__flat__wid_none(drawLine_pw13, p2);
    }


    /**
     * Draw ECS complex line.
     * @param p1 Point 1.
     * @param p2 Point 2.
     * @param thickness The thickness.
     */
    public
    void drawLine__ECS__ltyp__thck__wid_none(YxxfGfxPointW p1,
                                             YxxfGfxPointW p2,
                                             double thickness)
    {
        // Determine length of line
        double linlen = Math.sqrt( (p2.x - p1.x) * (p2.x - p1.x) +
                                   (p2.y - p1.y) * (p2.y - p1.y) +
                                   (p2.z - p1.z) * (p2.z - p1.z) );

        // Determine length of pattern
        double patlen = E_ltype_patternlen * E_ltypescale;

        if ((linlen + DOUBLE_MIN_TOLERANCE) < patlen)
        {   // line is too short to hold even one pattern,
            // draw continuous line
            drawLine__ECS__cont__thck__wid_none(p1, p2, thickness);
            return;
        }

        // calculate pattern values
        int    linlenmod = (int)Math.floor((linlen + DOUBLE_MIN_TOLERANCE) / patlen); // whole pattern units
        double linlenrem = (linlen + DOUBLE_MIN_TOLERANCE) - (patlen * linlenmod); // size of any remainder

        double dshlenone = E_ltype_lenlist[0] * E_ltypescale;

        double linbeg = (linlenrem - dshlenone) / 2.0; // actual line beg

        // find unit vector along line
        // pw10 will be length of 1
        drawLine_pw10.set(p2.x - p1.x, p2.y - p1.y, p2.z - p1.z);
        drawLine_pw10.normalize();


        //
        // Draw pattern
        //   --- Draw first pattern (first dash from beginning of line)
        //   --- Draw subsequent whole patterns
        //   --- Draw last pattern (first dash only to end of line)
        //
        double linpos = linbeg;
        double seglen, segbeg, segend;
        double patpos;


        // --- Draw first pattern (first dash from beginning of line)

        seglen = E_ltype_lenlist[0] * E_ltypescale;

        segend = linpos + seglen;

        // Set end point
        drawLine_pw14.set(p1.x + (drawLine_pw10.x * segend),
                          p1.y + (drawLine_pw10.y * segend),
                          p1.z + (drawLine_pw10.z * segend));

        // Draw it
        drawLine__ECS__cont__thck__wid_none(p1, drawLine_pw14, thickness);

        // Increment
        patpos = seglen;


        // Draw rest of pattern
        for (int j = 1; j < E_ltype_dashlencount; j++)
        {
            seglen = E_ltype_lenlist[j] * E_ltypescale;

            if (seglen < 0.0)
            {   // Gap

                // Increment
                patpos += -seglen;
            }
            else
            {   // Draw

                segbeg = linpos + patpos;
                segend = segbeg + seglen;

                // Set beg point
                drawLine_pw13.set(p1.x + (drawLine_pw10.x * segbeg),
                                  p1.y + (drawLine_pw10.y * segbeg),
                                  p1.z + (drawLine_pw10.z * segbeg));

                // Set end point
                drawLine_pw14.set(p1.x + (drawLine_pw10.x * segend),
                                  p1.y + (drawLine_pw10.y * segend),
                                  p1.z + (drawLine_pw10.z * segend));

                // Draw it
                drawLine__ECS__cont__thck__wid_none(drawLine_pw13, drawLine_pw14, thickness);

                // Increment
                patpos += seglen;
            }
        }

        // Increment
        linpos += patlen;


        // --- Draw subsequent whole patterns

        // Increment along line in patlen units starting with second pattern run
        for (int i = 1; i < linlenmod; i++)
        {   // Whole pattern runs

            // Increment thru pattern
            patpos = 0;
            for (int j = 0; j < E_ltype_dashlencount; j++)
            {
                seglen = E_ltype_lenlist[j] * E_ltypescale;

                if (seglen < 0.0)
                {   // Gap

                    // Increment
                    patpos += -seglen;
                }
                else
                {   // Draw

                    segbeg = linpos + patpos;
                    segend = segbeg + seglen;

                    // Set beg point
                    drawLine_pw13.set(p1.x + (drawLine_pw10.x * segbeg),
                                      p1.y + (drawLine_pw10.y * segbeg),
                                      p1.z + (drawLine_pw10.z * segbeg));

                    // Set end point
                    drawLine_pw14.set(p1.x + (drawLine_pw10.x * segend),
                                      p1.y + (drawLine_pw10.y * segend),
                                      p1.z + (drawLine_pw10.z * segend));


                    // Draw it
                    drawLine__ECS__cont__thck__wid_none(drawLine_pw13, drawLine_pw14, thickness);

                    // Increment
                    patpos += seglen;
                }
            }

            // Increment
            linpos += patlen;
        } // end of whole pattern runs


        // --- Draw last pattern (first dash only to end of line)

        // Set beg point
        drawLine_pw13.set(p1.x + (drawLine_pw10.x * linpos),
                          p1.y + (drawLine_pw10.y * linpos),
                          p1.z + (drawLine_pw10.z * linpos));

        // Draw it
        drawLine__ECS__cont__thck__wid_none(drawLine_pw13, p2, thickness);
    }


    /**
     * Draw ECS complex line.
     * @param p1 Point 1.
     * @param p2 Point 2.
     * @param begwid Beginning line width.
     * @param endwid Ending line width.
     */
    public
    void drawLine__ECS__ltyp__flat__wid_fill(YxxfGfxPointW p1,
                                             YxxfGfxPointW p2,
                                             double begwid, double endwid)
    {
        // find unit vector along line
        // pw10 will be length of 1
        drawLine_pw10.set(p2.x - p1.x, p2.y - p1.y, p2.z - p1.z);
        drawLine_pw10.normalize();

        // find width vector
        drawLine_pw20.crossProduct(drawLine_pw10, YxxfGfxPointW.Wz);
        drawLine_pw20.normalize();

        begwid /= 2.0;
        endwid /= 2.0;


        // Determine length of line
        double linlen = Math.sqrt( (p2.x - p1.x) * (p2.x - p1.x) +
                                   (p2.y - p1.y) * (p2.y - p1.y) +
                                   (p2.z - p1.z) * (p2.z - p1.z) );

        // Determine length of pattern
        double patlen = E_ltype_patternlen * E_ltypescale;

        if ((linlen + DOUBLE_MIN_TOLERANCE) < patlen)
        {   // line is too short to hold even one pattern,
            // draw continuous line
            drawLine_pw21.set(p1.x + (drawLine_pw20.x * begwid),
                              p1.y + (drawLine_pw20.y * begwid),
                              p1.z + (drawLine_pw20.z * begwid));
            drawLine_pw22.set(p1.x - (drawLine_pw20.x * begwid),
                              p1.y - (drawLine_pw20.y * begwid),
                              p1.z - (drawLine_pw20.z * begwid));
            drawLine_pw23.set(p2.x + (drawLine_pw20.x * endwid),
                              p2.y + (drawLine_pw20.y * endwid),
                              p2.z + (drawLine_pw20.z * endwid));
            drawLine_pw24.set(p2.x - (drawLine_pw20.x * endwid),
                              p2.y - (drawLine_pw20.y * endwid),
                              p2.z - (drawLine_pw20.z * endwid));

            if (gc_doing_flag_calc_extents)
            {
                ECS_to_SCS_calc_extents(drawLine_pw21, drawSolid_ps1);
                ECS_to_SCS_calc_extents(drawLine_pw22, drawSolid_ps2);
                ECS_to_SCS_calc_extents(drawLine_pw23, drawSolid_ps3);
                ECS_to_SCS_calc_extents(drawLine_pw24, drawSolid_ps4);
            }
            else
            {
                ECS_to_SCS(drawLine_pw21, drawSolid_ps1);
                ECS_to_SCS(drawLine_pw22, drawSolid_ps2);
                ECS_to_SCS(drawLine_pw23, drawSolid_ps3);
                ECS_to_SCS(drawLine_pw24, drawSolid_ps4);
            }

            drawSolid_xPoints[0] = drawSolid_ps1.x;
            drawSolid_xPoints[1] = drawSolid_ps3.x;
            drawSolid_xPoints[2] = drawSolid_ps4.x;
            drawSolid_xPoints[3] = drawSolid_ps2.x;
            drawSolid_yPoints[0] = drawSolid_ps1.y;
            drawSolid_yPoints[1] = drawSolid_ps3.y;
            drawSolid_yPoints[2] = drawSolid_ps4.y;
            drawSolid_yPoints[3] = drawSolid_ps2.y;

            if (renderingjgcflag)
                jgc.fillPolygon(drawSolid_xPoints, drawSolid_yPoints, 4);
            return;
        }

        // calculate pattern values
        int    linlenmod = (int)Math.floor((linlen + DOUBLE_MIN_TOLERANCE) / patlen); // whole pattern units
        double linlenrem = (linlen + DOUBLE_MIN_TOLERANCE) - (patlen * linlenmod); // size of any remainder

        double dshlenone = E_ltype_lenlist[0] * E_ltypescale;

        double linbeg = (linlenrem - dshlenone) / 2.0; // actual line beg


        //
        // Draw pattern
        //   --- Draw first pattern (first dash from beginning of line)
        //   --- Draw subsequent whole patterns
        //   --- Draw last pattern (first dash only to end of line)
        //
        double linpos = linbeg;
        double seglen, segbeg, segend;
        double segbegwid, segendwid;
        double patpos;


        // --- Draw first pattern (first dash from beginning of line)

        seglen = E_ltype_lenlist[0] * E_ltypescale;

        segend = linpos + seglen;

        segendwid = ((segend / linlen) * (endwid - begwid)) + begwid;

        // Set end point
        drawLine_pw14.set(p1.x + (drawLine_pw10.x * segend),
                          p1.y + (drawLine_pw10.y * segend),
                          p1.z + (drawLine_pw10.z * segend));

        // Draw it
        drawLine_pw21.set(p1.x + (drawLine_pw20.x * begwid),
                          p1.y + (drawLine_pw20.y * begwid),
                          p1.z + (drawLine_pw20.z * begwid));
        drawLine_pw22.set(p1.x - (drawLine_pw20.x * begwid),
                          p1.y - (drawLine_pw20.y * begwid),
                          p1.z - (drawLine_pw20.z * begwid));
        drawLine_pw23.set(drawLine_pw14.x + (drawLine_pw20.x * segendwid),
                          drawLine_pw14.y + (drawLine_pw20.y * segendwid),
                          drawLine_pw14.z + (drawLine_pw20.z * segendwid));
        drawLine_pw24.set(drawLine_pw14.x - (drawLine_pw20.x * segendwid),
                          drawLine_pw14.y - (drawLine_pw20.y * segendwid),
                          drawLine_pw14.z - (drawLine_pw20.z * segendwid));

        if (gc_doing_flag_calc_extents)
        {
            ECS_to_SCS_calc_extents(drawLine_pw21, drawSolid_ps1);
            ECS_to_SCS_calc_extents(drawLine_pw22, drawSolid_ps2);
            ECS_to_SCS_calc_extents(drawLine_pw23, drawSolid_ps3);
            ECS_to_SCS_calc_extents(drawLine_pw24, drawSolid_ps4);
        }
        else
        {
            ECS_to_SCS(drawLine_pw21, drawSolid_ps1);
            ECS_to_SCS(drawLine_pw22, drawSolid_ps2);
            ECS_to_SCS(drawLine_pw23, drawSolid_ps3);
            ECS_to_SCS(drawLine_pw24, drawSolid_ps4);
        }

        drawSolid_xPoints[0] = drawSolid_ps1.x;
        drawSolid_xPoints[1] = drawSolid_ps3.x;
        drawSolid_xPoints[2] = drawSolid_ps4.x;
        drawSolid_xPoints[3] = drawSolid_ps2.x;
        drawSolid_yPoints[0] = drawSolid_ps1.y;
        drawSolid_yPoints[1] = drawSolid_ps3.y;
        drawSolid_yPoints[2] = drawSolid_ps4.y;
        drawSolid_yPoints[3] = drawSolid_ps2.y;

        if (renderingjgcflag)
            jgc.fillPolygon(drawSolid_xPoints, drawSolid_yPoints, 4);

        // Increment
        patpos = seglen;


        // Draw rest of pattern
        for (int j = 1; j < E_ltype_dashlencount; j++)
        {
            seglen = E_ltype_lenlist[j] * E_ltypescale;

            if (seglen < 0.0)
            {   // Gap

                // Increment
                patpos += -seglen;
            }
            else
            {   // Draw

                segbeg = linpos + patpos;
                segend = segbeg + seglen;

                segbegwid = ((segbeg / linlen) * (endwid - begwid)) + begwid;
                segendwid = ((segend / linlen) * (endwid - begwid)) + begwid;

                // Set beg point
                drawLine_pw13.set(p1.x + (drawLine_pw10.x * segbeg),
                                  p1.y + (drawLine_pw10.y * segbeg),
                                  p1.z + (drawLine_pw10.z * segbeg));

                // Set end point
                drawLine_pw14.set(p1.x + (drawLine_pw10.x * segend),
                                  p1.y + (drawLine_pw10.y * segend),
                                  p1.z + (drawLine_pw10.z * segend));

                // Draw it
                drawLine_pw21.set(drawLine_pw13.x + (drawLine_pw20.x * segbegwid),
                                  drawLine_pw13.y + (drawLine_pw20.y * segbegwid),
                                  drawLine_pw13.z + (drawLine_pw20.z * segbegwid));
                drawLine_pw22.set(drawLine_pw13.x - (drawLine_pw20.x * segbegwid),
                                  drawLine_pw13.y - (drawLine_pw20.y * segbegwid),
                                  drawLine_pw13.z - (drawLine_pw20.z * segbegwid));
                drawLine_pw23.set(drawLine_pw14.x + (drawLine_pw20.x * segendwid),
                                  drawLine_pw14.y + (drawLine_pw20.y * segendwid),
                                  drawLine_pw14.z + (drawLine_pw20.z * segendwid));
                drawLine_pw24.set(drawLine_pw14.x - (drawLine_pw20.x * segendwid),
                                  drawLine_pw14.y - (drawLine_pw20.y * segendwid),
                                  drawLine_pw14.z - (drawLine_pw20.z * segendwid));

                if (gc_doing_flag_calc_extents)
                {
                    ECS_to_SCS_calc_extents(drawLine_pw21, drawSolid_ps1);
                    ECS_to_SCS_calc_extents(drawLine_pw22, drawSolid_ps2);
                    ECS_to_SCS_calc_extents(drawLine_pw23, drawSolid_ps3);
                    ECS_to_SCS_calc_extents(drawLine_pw24, drawSolid_ps4);
                }
                else
                {
                    ECS_to_SCS(drawLine_pw21, drawSolid_ps1);
                    ECS_to_SCS(drawLine_pw22, drawSolid_ps2);
                    ECS_to_SCS(drawLine_pw23, drawSolid_ps3);
                    ECS_to_SCS(drawLine_pw24, drawSolid_ps4);
                }

                drawSolid_xPoints[0] = drawSolid_ps1.x;
                drawSolid_xPoints[1] = drawSolid_ps3.x;
                drawSolid_xPoints[2] = drawSolid_ps4.x;
                drawSolid_xPoints[3] = drawSolid_ps2.x;
                drawSolid_yPoints[0] = drawSolid_ps1.y;
                drawSolid_yPoints[1] = drawSolid_ps3.y;
                drawSolid_yPoints[2] = drawSolid_ps4.y;
                drawSolid_yPoints[3] = drawSolid_ps2.y;

                if (renderingjgcflag)
                    jgc.fillPolygon(drawSolid_xPoints, drawSolid_yPoints, 4);

                // Increment
                patpos += seglen;
            }
        }

        // Increment
        linpos += patlen;


        // --- Draw subsequent whole patterns

        // Increment along line in patlen units starting with second pattern run
        for (int i = 1; i < linlenmod; i++)
        {   // Whole pattern runs

            // Increment thru pattern
            patpos = 0;
            for (int j = 0; j < E_ltype_dashlencount; j++)
            {
                seglen = E_ltype_lenlist[j] * E_ltypescale;

                if (seglen < 0.0)
                {   // Gap

                    // Increment
                    patpos += -seglen;
                }
                else
                {   // Draw

                    segbeg = linpos + patpos;
                    segend = segbeg + seglen;

                    segbegwid = ((segbeg / linlen) * (endwid - begwid)) + begwid;
                    segendwid = ((segend / linlen) * (endwid - begwid)) + begwid;

                    // Set beg point
                    drawLine_pw13.set(p1.x + (drawLine_pw10.x * segbeg),
                                      p1.y + (drawLine_pw10.y * segbeg),
                                      p1.z + (drawLine_pw10.z * segbeg));

                    // Set end point
                    drawLine_pw14.set(p1.x + (drawLine_pw10.x * segend),
                                      p1.y + (drawLine_pw10.y * segend),
                                      p1.z + (drawLine_pw10.z * segend));


                    // Draw it
                    drawLine_pw21.set(drawLine_pw13.x + (drawLine_pw20.x * segbegwid),
                                      drawLine_pw13.y + (drawLine_pw20.y * segbegwid),
                                      drawLine_pw13.z + (drawLine_pw20.z * segbegwid));
                    drawLine_pw22.set(drawLine_pw13.x - (drawLine_pw20.x * segbegwid),
                                      drawLine_pw13.y - (drawLine_pw20.y * segbegwid),
                                      drawLine_pw13.z - (drawLine_pw20.z * segbegwid));
                    drawLine_pw23.set(drawLine_pw14.x + (drawLine_pw20.x * segendwid),
                                      drawLine_pw14.y + (drawLine_pw20.y * segendwid),
                                      drawLine_pw14.z + (drawLine_pw20.z * segendwid));
                    drawLine_pw24.set(drawLine_pw14.x - (drawLine_pw20.x * segendwid),
                                      drawLine_pw14.y - (drawLine_pw20.y * segendwid),
                                      drawLine_pw14.z - (drawLine_pw20.z * segendwid));

                    if (gc_doing_flag_calc_extents)
                    {
                        ECS_to_SCS_calc_extents(drawLine_pw21, drawSolid_ps1);
                        ECS_to_SCS_calc_extents(drawLine_pw22, drawSolid_ps2);
                        ECS_to_SCS_calc_extents(drawLine_pw23, drawSolid_ps3);
                        ECS_to_SCS_calc_extents(drawLine_pw24, drawSolid_ps4);
                    }
                    else
                    {
                        ECS_to_SCS(drawLine_pw21, drawSolid_ps1);
                        ECS_to_SCS(drawLine_pw22, drawSolid_ps2);
                        ECS_to_SCS(drawLine_pw23, drawSolid_ps3);
                        ECS_to_SCS(drawLine_pw24, drawSolid_ps4);
                    }

                    drawSolid_xPoints[0] = drawSolid_ps1.x;
                    drawSolid_xPoints[1] = drawSolid_ps3.x;
                    drawSolid_xPoints[2] = drawSolid_ps4.x;
                    drawSolid_xPoints[3] = drawSolid_ps2.x;
                    drawSolid_yPoints[0] = drawSolid_ps1.y;
                    drawSolid_yPoints[1] = drawSolid_ps3.y;
                    drawSolid_yPoints[2] = drawSolid_ps4.y;
                    drawSolid_yPoints[3] = drawSolid_ps2.y;

                    if (renderingjgcflag)
                        jgc.fillPolygon(drawSolid_xPoints, drawSolid_yPoints, 4);

                    // Increment
                    patpos += seglen;
                }
            }

            // Increment
            linpos += patlen;
        } // end of whole pattern runs


        // --- Draw last pattern (first dash only to end of line)

        segbegwid = ((linpos / linlen) * (endwid - begwid)) + begwid;

        // Set beg point
        drawLine_pw13.set(p1.x + (drawLine_pw10.x * linpos),
                          p1.y + (drawLine_pw10.y * linpos),
                          p1.z + (drawLine_pw10.z * linpos));

        // Draw it
        drawLine_pw21.set(drawLine_pw13.x + (drawLine_pw20.x * segbegwid),
                          drawLine_pw13.y + (drawLine_pw20.y * segbegwid),
                          drawLine_pw13.z + (drawLine_pw20.z * segbegwid));
        drawLine_pw22.set(drawLine_pw13.x - (drawLine_pw20.x * segbegwid),
                          drawLine_pw13.y - (drawLine_pw20.y * segbegwid),
                          drawLine_pw13.z - (drawLine_pw20.z * segbegwid));
        drawLine_pw23.set(p2.x + (drawLine_pw20.x * endwid),
                          p2.y + (drawLine_pw20.y * endwid),
                          p2.z + (drawLine_pw20.z * endwid));
        drawLine_pw24.set(p2.x - (drawLine_pw20.x * endwid),
                          p2.y - (drawLine_pw20.y * endwid),
                          p2.z - (drawLine_pw20.z * endwid));

        if (gc_doing_flag_calc_extents)
        {
            ECS_to_SCS_calc_extents(drawLine_pw21, drawSolid_ps1);
            ECS_to_SCS_calc_extents(drawLine_pw22, drawSolid_ps2);
            ECS_to_SCS_calc_extents(drawLine_pw23, drawSolid_ps3);
            ECS_to_SCS_calc_extents(drawLine_pw24, drawSolid_ps4);
        }
        else
        {
            ECS_to_SCS(drawLine_pw21, drawSolid_ps1);
            ECS_to_SCS(drawLine_pw22, drawSolid_ps2);
            ECS_to_SCS(drawLine_pw23, drawSolid_ps3);
            ECS_to_SCS(drawLine_pw24, drawSolid_ps4);
        }

        drawSolid_xPoints[0] = drawSolid_ps1.x;
        drawSolid_xPoints[1] = drawSolid_ps3.x;
        drawSolid_xPoints[2] = drawSolid_ps4.x;
        drawSolid_xPoints[3] = drawSolid_ps2.x;
        drawSolid_yPoints[0] = drawSolid_ps1.y;
        drawSolid_yPoints[1] = drawSolid_ps3.y;
        drawSolid_yPoints[2] = drawSolid_ps4.y;
        drawSolid_yPoints[3] = drawSolid_ps2.y;

        if (renderingjgcflag)
            jgc.fillPolygon(drawSolid_xPoints, drawSolid_yPoints, 4);
    }


    /**
     * Draw ECS complex line.
     * @param p1 Point 1.
     * @param p2 Point 2.
     * @param begwid Beginning line width.
     * @param endwid Ending line width.
     */
    public
    void drawLine__ECS__ltyp__flat__wid_wire(YxxfGfxPointW p1,
                                             YxxfGfxPointW p2,
                                             double begwid, double endwid)
    {
        // find unit vector along line
        // pw10 will be length of 1
        drawLine_pw10.set(p2.x - p1.x, p2.y - p1.y, p2.z - p1.z);
        drawLine_pw10.normalize();

        // find width vector
        drawLine_pw20.crossProduct(drawLine_pw10, YxxfGfxPointW.Wz);
        drawLine_pw20.normalize();

        begwid /= 2.0;
        endwid /= 2.0;


        // Determine length of line
        double linlen = Math.sqrt( (p2.x - p1.x) * (p2.x - p1.x) +
                                   (p2.y - p1.y) * (p2.y - p1.y) +
                                   (p2.z - p1.z) * (p2.z - p1.z) );

        // Determine length of pattern
        double patlen = E_ltype_patternlen * E_ltypescale;

        if ((linlen + DOUBLE_MIN_TOLERANCE) < patlen)
        {   // line is too short to hold even one pattern,
            // draw continuous line
            drawLine_pw21.set(p1.x + (drawLine_pw20.x * begwid),
                              p1.y + (drawLine_pw20.y * begwid),
                              p1.z + (drawLine_pw20.z * begwid));
            drawLine_pw22.set(p1.x - (drawLine_pw20.x * begwid),
                              p1.y - (drawLine_pw20.y * begwid),
                              p1.z - (drawLine_pw20.z * begwid));
            drawLine_pw23.set(p2.x + (drawLine_pw20.x * endwid),
                              p2.y + (drawLine_pw20.y * endwid),
                              p2.z + (drawLine_pw20.z * endwid));
            drawLine_pw24.set(p2.x - (drawLine_pw20.x * endwid),
                              p2.y - (drawLine_pw20.y * endwid),
                              p2.z - (drawLine_pw20.z * endwid));

            drawLine__ECS__cont__flat__wid_none(drawLine_pw21, drawLine_pw22);
            drawLine__ECS__cont__flat__wid_none(drawLine_pw22, drawLine_pw24);
            drawLine__ECS__cont__flat__wid_none(drawLine_pw24, drawLine_pw23);
            drawLine__ECS__cont__flat__wid_none(drawLine_pw23, drawLine_pw21);
            return;
        }

        // calculate pattern values
        int    linlenmod = (int)Math.floor((linlen + DOUBLE_MIN_TOLERANCE) / patlen); // whole pattern units
        double linlenrem = (linlen + DOUBLE_MIN_TOLERANCE) - (patlen * linlenmod); // size of any remainder

        double dshlenone = E_ltype_lenlist[0] * E_ltypescale;

        double linbeg = (linlenrem - dshlenone) / 2.0; // actual line beg


        //
        // Draw pattern
        //   --- Draw first pattern (first dash from beginning of line)
        //   --- Draw subsequent whole patterns
        //   --- Draw last pattern (first dash only to end of line)
        //
        double linpos = linbeg;
        double seglen, segbeg, segend;
        double segbegwid, segendwid;
        double patpos;


        // --- Draw first pattern (first dash from beginning of line)

        seglen = E_ltype_lenlist[0] * E_ltypescale;

        segend = linpos + seglen;

        segendwid = ((segend / linlen) * (endwid - begwid)) + begwid;

        // Set end point
        drawLine_pw14.set(p1.x + (drawLine_pw10.x * segend),
                          p1.y + (drawLine_pw10.y * segend),
                          p1.z + (drawLine_pw10.z * segend));

        // Draw it
        drawLine_pw21.set(p1.x + (drawLine_pw20.x * begwid),
                          p1.y + (drawLine_pw20.y * begwid),
                          p1.z + (drawLine_pw20.z * begwid));
        drawLine_pw22.set(p1.x - (drawLine_pw20.x * begwid),
                          p1.y - (drawLine_pw20.y * begwid),
                          p1.z - (drawLine_pw20.z * begwid));
        drawLine_pw23.set(drawLine_pw14.x + (drawLine_pw20.x * segendwid),
                          drawLine_pw14.y + (drawLine_pw20.y * segendwid),
                          drawLine_pw14.z + (drawLine_pw20.z * segendwid));
        drawLine_pw24.set(drawLine_pw14.x - (drawLine_pw20.x * segendwid),
                          drawLine_pw14.y - (drawLine_pw20.y * segendwid),
                          drawLine_pw14.z - (drawLine_pw20.z * segendwid));

        drawLine__ECS__cont__flat__wid_none(drawLine_pw21, drawLine_pw22);
        drawLine__ECS__cont__flat__wid_none(drawLine_pw22, drawLine_pw24);
        drawLine__ECS__cont__flat__wid_none(drawLine_pw24, drawLine_pw23);
        drawLine__ECS__cont__flat__wid_none(drawLine_pw23, drawLine_pw21);

        // Increment
        patpos = seglen;


        // Draw rest of pattern
        for (int j = 1; j < E_ltype_dashlencount; j++)
        {
            seglen = E_ltype_lenlist[j] * E_ltypescale;

            if (seglen < 0.0)
            {   // Gap

                // Increment
                patpos += -seglen;
            }
            else
            {   // Draw

                segbeg = linpos + patpos;
                segend = segbeg + seglen;

                segbegwid = ((segbeg / linlen) * (endwid - begwid)) + begwid;
                segendwid = ((segend / linlen) * (endwid - begwid)) + begwid;

                // Set beg point
                drawLine_pw13.set(p1.x + (drawLine_pw10.x * segbeg),
                                  p1.y + (drawLine_pw10.y * segbeg),
                                  p1.z + (drawLine_pw10.z * segbeg));

                // Set end point
                drawLine_pw14.set(p1.x + (drawLine_pw10.x * segend),
                                  p1.y + (drawLine_pw10.y * segend),
                                  p1.z + (drawLine_pw10.z * segend));

                // Draw it
                drawLine_pw21.set(drawLine_pw13.x + (drawLine_pw20.x * segbegwid),
                                  drawLine_pw13.y + (drawLine_pw20.y * segbegwid),
                                  drawLine_pw13.z + (drawLine_pw20.z * segbegwid));
                drawLine_pw22.set(drawLine_pw13.x - (drawLine_pw20.x * segbegwid),
                                  drawLine_pw13.y - (drawLine_pw20.y * segbegwid),
                                  drawLine_pw13.z - (drawLine_pw20.z * segbegwid));
                drawLine_pw23.set(drawLine_pw14.x + (drawLine_pw20.x * segendwid),
                                  drawLine_pw14.y + (drawLine_pw20.y * segendwid),
                                  drawLine_pw14.z + (drawLine_pw20.z * segendwid));
                drawLine_pw24.set(drawLine_pw14.x - (drawLine_pw20.x * segendwid),
                                  drawLine_pw14.y - (drawLine_pw20.y * segendwid),
                                  drawLine_pw14.z - (drawLine_pw20.z * segendwid));

                drawLine__ECS__cont__flat__wid_none(drawLine_pw21, drawLine_pw22);
                drawLine__ECS__cont__flat__wid_none(drawLine_pw22, drawLine_pw24);
                drawLine__ECS__cont__flat__wid_none(drawLine_pw24, drawLine_pw23);
                drawLine__ECS__cont__flat__wid_none(drawLine_pw23, drawLine_pw21);

                // Increment
                patpos += seglen;
            }
        }

        // Increment
        linpos += patlen;


        // --- Draw subsequent whole patterns

        // Increment along line in patlen units starting with second pattern run
        for (int i = 1; i < linlenmod; i++)
        {   // Whole pattern runs

            // Increment thru pattern
            patpos = 0;
            for (int j = 0; j < E_ltype_dashlencount; j++)
            {
                seglen = E_ltype_lenlist[j] * E_ltypescale;

                if (seglen < 0.0)
                {   // Gap

                    // Increment
                    patpos += -seglen;
                }
                else
                {   // Draw

                    segbeg = linpos + patpos;
                    segend = segbeg + seglen;

                    segbegwid = ((segbeg / linlen) * (endwid - begwid)) + begwid;
                    segendwid = ((segend / linlen) * (endwid - begwid)) + begwid;

                    // Set beg point
                    drawLine_pw13.set(p1.x + (drawLine_pw10.x * segbeg),
                                      p1.y + (drawLine_pw10.y * segbeg),
                                      p1.z + (drawLine_pw10.z * segbeg));

                    // Set end point
                    drawLine_pw14.set(p1.x + (drawLine_pw10.x * segend),
                                      p1.y + (drawLine_pw10.y * segend),
                                      p1.z + (drawLine_pw10.z * segend));


                    // Draw it
                    drawLine_pw21.set(drawLine_pw13.x + (drawLine_pw20.x * segbegwid),
                                      drawLine_pw13.y + (drawLine_pw20.y * segbegwid),
                                      drawLine_pw13.z + (drawLine_pw20.z * segbegwid));
                    drawLine_pw22.set(drawLine_pw13.x - (drawLine_pw20.x * segbegwid),
                                      drawLine_pw13.y - (drawLine_pw20.y * segbegwid),
                                      drawLine_pw13.z - (drawLine_pw20.z * segbegwid));
                    drawLine_pw23.set(drawLine_pw14.x + (drawLine_pw20.x * segendwid),
                                      drawLine_pw14.y + (drawLine_pw20.y * segendwid),
                                      drawLine_pw14.z + (drawLine_pw20.z * segendwid));
                    drawLine_pw24.set(drawLine_pw14.x - (drawLine_pw20.x * segendwid),
                                      drawLine_pw14.y - (drawLine_pw20.y * segendwid),
                                      drawLine_pw14.z - (drawLine_pw20.z * segendwid));

                    drawLine__ECS__cont__flat__wid_none(drawLine_pw21, drawLine_pw22);
                    drawLine__ECS__cont__flat__wid_none(drawLine_pw22, drawLine_pw24);
                    drawLine__ECS__cont__flat__wid_none(drawLine_pw24, drawLine_pw23);
                    drawLine__ECS__cont__flat__wid_none(drawLine_pw23, drawLine_pw21);

                    // Increment
                    patpos += seglen;
                }
            }

            // Increment
            linpos += patlen;
        } // end of whole pattern runs


        // --- Draw last pattern (first dash only to end of line)

        segbegwid = ((linpos / linlen) * (endwid - begwid)) + begwid;

        // Set beg point
        drawLine_pw13.set(p1.x + (drawLine_pw10.x * linpos),
                          p1.y + (drawLine_pw10.y * linpos),
                          p1.z + (drawLine_pw10.z * linpos));

        // Draw it
        drawLine_pw21.set(drawLine_pw13.x + (drawLine_pw20.x * segbegwid),
                          drawLine_pw13.y + (drawLine_pw20.y * segbegwid),
                          drawLine_pw13.z + (drawLine_pw20.z * segbegwid));
        drawLine_pw22.set(drawLine_pw13.x - (drawLine_pw20.x * segbegwid),
                          drawLine_pw13.y - (drawLine_pw20.y * segbegwid),
                          drawLine_pw13.z - (drawLine_pw20.z * segbegwid));
        drawLine_pw23.set(p2.x + (drawLine_pw20.x * endwid),
                          p2.y + (drawLine_pw20.y * endwid),
                          p2.z + (drawLine_pw20.z * endwid));
        drawLine_pw24.set(p2.x - (drawLine_pw20.x * endwid),
                          p2.y - (drawLine_pw20.y * endwid),
                          p2.z - (drawLine_pw20.z * endwid));

        drawLine__ECS__cont__flat__wid_none(drawLine_pw21, drawLine_pw22);
        drawLine__ECS__cont__flat__wid_none(drawLine_pw22, drawLine_pw24);
        drawLine__ECS__cont__flat__wid_none(drawLine_pw24, drawLine_pw23);
        drawLine__ECS__cont__flat__wid_none(drawLine_pw23, drawLine_pw21);
    }


    /**
     * Draw ECS complex line.
     * @param p1 Point 1.
     * @param p2 Point 2.
     * @param thickness Line thickness.
     * @param begwid Beginning line width.
     * @param endwid Ending line width.
     */
    public
    void drawLine__ECS__ltyp__thck__wid_wire(YxxfGfxPointW p1,
                                             YxxfGfxPointW p2,
                                             double thickness,
                                             double begwid, double endwid)
    {
        // find unit vector along line
        // pw10 will be length of 1
        drawLine_pw10.set(p2.x - p1.x, p2.y - p1.y, p2.z - p1.z);
        drawLine_pw10.normalize();

        // find width vector
        drawLine_pw20.crossProduct(drawLine_pw10, YxxfGfxPointW.Wz);
        drawLine_pw20.normalize();

        begwid /= 2.0;
        endwid /= 2.0;


        // Determine length of line
        double linlen = Math.sqrt( (p2.x - p1.x) * (p2.x - p1.x) +
                                   (p2.y - p1.y) * (p2.y - p1.y) +
                                   (p2.z - p1.z) * (p2.z - p1.z) );

        // Determine length of pattern
        double patlen = E_ltype_patternlen * E_ltypescale;

        if ((linlen + DOUBLE_MIN_TOLERANCE) < patlen)
        {   // line is too short to hold even one pattern,
            // draw continuous line
            drawLine_pw21.set(p1.x + (drawLine_pw20.x * begwid),
                              p1.y + (drawLine_pw20.y * begwid),
                              p1.z + (drawLine_pw20.z * begwid));
            drawLine_pw22.set(p1.x - (drawLine_pw20.x * begwid),
                              p1.y - (drawLine_pw20.y * begwid),
                              p1.z - (drawLine_pw20.z * begwid));
            drawLine_pw23.set(p2.x + (drawLine_pw20.x * endwid),
                              p2.y + (drawLine_pw20.y * endwid),
                              p2.z + (drawLine_pw20.z * endwid));
            drawLine_pw24.set(p2.x - (drawLine_pw20.x * endwid),
                              p2.y - (drawLine_pw20.y * endwid),
                              p2.z - (drawLine_pw20.z * endwid));

            // base
            drawLine__ECS__cont__flat__wid_none(drawLine_pw21, drawLine_pw22);
            drawLine__ECS__cont__flat__wid_none(drawLine_pw22, drawLine_pw24);
            drawLine__ECS__cont__flat__wid_none(drawLine_pw24, drawLine_pw23);
            drawLine__ECS__cont__flat__wid_none(drawLine_pw23, drawLine_pw21);

            // sides
            drawLine__ECS__cont__flat__wid_none(drawLine_pw21.x, drawLine_pw21.y, drawLine_pw21.z,
                                                drawLine_pw21.x, drawLine_pw21.y, drawLine_pw21.z + thickness);
            drawLine__ECS__cont__flat__wid_none(drawLine_pw22.x, drawLine_pw22.y, drawLine_pw22.z,
                                                drawLine_pw22.x, drawLine_pw22.y, drawLine_pw22.z + thickness);
            drawLine__ECS__cont__flat__wid_none(drawLine_pw23.x, drawLine_pw23.y, drawLine_pw23.z,
                                                drawLine_pw23.x, drawLine_pw23.y, drawLine_pw23.z + thickness);
            drawLine__ECS__cont__flat__wid_none(drawLine_pw24.x, drawLine_pw24.y, drawLine_pw24.z,
                                                drawLine_pw24.x, drawLine_pw24.y, drawLine_pw24.z + thickness);

            // top
            drawLine__ECS__cont__flat__wid_none(drawLine_pw21.x, drawLine_pw21.y, drawLine_pw21.z + thickness,
                                                drawLine_pw22.x, drawLine_pw22.y, drawLine_pw22.z + thickness);
            drawLine__ECS__cont__flat__wid_none(drawLine_pw22.x, drawLine_pw22.y, drawLine_pw22.z + thickness,
                                                drawLine_pw24.x, drawLine_pw24.y, drawLine_pw24.z + thickness);
            drawLine__ECS__cont__flat__wid_none(drawLine_pw24.x, drawLine_pw24.y, drawLine_pw24.z + thickness,
                                                drawLine_pw23.x, drawLine_pw23.y, drawLine_pw23.z + thickness);
            drawLine__ECS__cont__flat__wid_none(drawLine_pw23.x, drawLine_pw23.y, drawLine_pw23.z + thickness,
                                                drawLine_pw21.x, drawLine_pw21.y, drawLine_pw21.z + thickness);
            return;
        }

        // calculate pattern values
        int    linlenmod = (int)Math.floor((linlen + DOUBLE_MIN_TOLERANCE) / patlen); // whole pattern units
        double linlenrem = (linlen + DOUBLE_MIN_TOLERANCE) - (patlen * linlenmod); // size of any remainder

        double dshlenone = E_ltype_lenlist[0] * E_ltypescale;

        double linbeg = (linlenrem - dshlenone) / 2.0; // actual line beg


        //
        // Draw pattern
        //   --- Draw first pattern (first dash from beginning of line)
        //   --- Draw subsequent whole patterns
        //   --- Draw last pattern (first dash only to end of line)
        //
        double linpos = linbeg;
        double seglen, segbeg, segend;
        double segbegwid, segendwid;
        double patpos;


        // --- Draw first pattern (first dash from beginning of line)

        seglen = E_ltype_lenlist[0] * E_ltypescale;

        segend = linpos + seglen;

        segendwid = ((segend / linlen) * (endwid - begwid)) + begwid;

        // Set end point
        drawLine_pw14.set(p1.x + (drawLine_pw10.x * segend),
                          p1.y + (drawLine_pw10.y * segend),
                          p1.z + (drawLine_pw10.z * segend));

        // Draw it
        drawLine_pw21.set(p1.x + (drawLine_pw20.x * begwid),
                          p1.y + (drawLine_pw20.y * begwid),
                          p1.z + (drawLine_pw20.z * begwid));
        drawLine_pw22.set(p1.x - (drawLine_pw20.x * begwid),
                          p1.y - (drawLine_pw20.y * begwid),
                          p1.z - (drawLine_pw20.z * begwid));
        drawLine_pw23.set(drawLine_pw14.x + (drawLine_pw20.x * segendwid),
                          drawLine_pw14.y + (drawLine_pw20.y * segendwid),
                          drawLine_pw14.z + (drawLine_pw20.z * segendwid));
        drawLine_pw24.set(drawLine_pw14.x - (drawLine_pw20.x * segendwid),
                          drawLine_pw14.y - (drawLine_pw20.y * segendwid),
                          drawLine_pw14.z - (drawLine_pw20.z * segendwid));

        // base
        drawLine__ECS__cont__flat__wid_none(drawLine_pw21, drawLine_pw22);
        drawLine__ECS__cont__flat__wid_none(drawLine_pw22, drawLine_pw24);
        drawLine__ECS__cont__flat__wid_none(drawLine_pw24, drawLine_pw23);
        drawLine__ECS__cont__flat__wid_none(drawLine_pw23, drawLine_pw21);

        // sides
        drawLine__ECS__cont__flat__wid_none(drawLine_pw21.x, drawLine_pw21.y, drawLine_pw21.z,
                                            drawLine_pw21.x, drawLine_pw21.y, drawLine_pw21.z + thickness);
        drawLine__ECS__cont__flat__wid_none(drawLine_pw22.x, drawLine_pw22.y, drawLine_pw22.z,
                                            drawLine_pw22.x, drawLine_pw22.y, drawLine_pw22.z + thickness);
        drawLine__ECS__cont__flat__wid_none(drawLine_pw23.x, drawLine_pw23.y, drawLine_pw23.z,
                                            drawLine_pw23.x, drawLine_pw23.y, drawLine_pw23.z + thickness);
        drawLine__ECS__cont__flat__wid_none(drawLine_pw24.x, drawLine_pw24.y, drawLine_pw24.z,
                                            drawLine_pw24.x, drawLine_pw24.y, drawLine_pw24.z + thickness);

        // top
        drawLine__ECS__cont__flat__wid_none(drawLine_pw21.x, drawLine_pw21.y, drawLine_pw21.z + thickness,
                                            drawLine_pw22.x, drawLine_pw22.y, drawLine_pw22.z + thickness);
        drawLine__ECS__cont__flat__wid_none(drawLine_pw22.x, drawLine_pw22.y, drawLine_pw22.z + thickness,
                                            drawLine_pw24.x, drawLine_pw24.y, drawLine_pw24.z + thickness);
        drawLine__ECS__cont__flat__wid_none(drawLine_pw24.x, drawLine_pw24.y, drawLine_pw24.z + thickness,
                                            drawLine_pw23.x, drawLine_pw23.y, drawLine_pw23.z + thickness);
        drawLine__ECS__cont__flat__wid_none(drawLine_pw23.x, drawLine_pw23.y, drawLine_pw23.z + thickness,
                                            drawLine_pw21.x, drawLine_pw21.y, drawLine_pw21.z + thickness);

        // Increment
        patpos = seglen;


        // Draw rest of pattern
        for (int j = 1; j < E_ltype_dashlencount; j++)
        {
            seglen = E_ltype_lenlist[j] * E_ltypescale;

            if (seglen < 0.0)
            {   // Gap

                // Increment
                patpos += -seglen;
            }
            else
            {   // Draw

                segbeg = linpos + patpos;
                segend = segbeg + seglen;

                segbegwid = ((segbeg / linlen) * (endwid - begwid)) + begwid;
                segendwid = ((segend / linlen) * (endwid - begwid)) + begwid;

                // Set beg point
                drawLine_pw13.set(p1.x + (drawLine_pw10.x * segbeg),
                                  p1.y + (drawLine_pw10.y * segbeg),
                                  p1.z + (drawLine_pw10.z * segbeg));

                // Set end point
                drawLine_pw14.set(p1.x + (drawLine_pw10.x * segend),
                                  p1.y + (drawLine_pw10.y * segend),
                                  p1.z + (drawLine_pw10.z * segend));

                // Draw it
                drawLine_pw21.set(drawLine_pw13.x + (drawLine_pw20.x * segbegwid),
                                  drawLine_pw13.y + (drawLine_pw20.y * segbegwid),
                                  drawLine_pw13.z + (drawLine_pw20.z * segbegwid));
                drawLine_pw22.set(drawLine_pw13.x - (drawLine_pw20.x * segbegwid),
                                  drawLine_pw13.y - (drawLine_pw20.y * segbegwid),
                                  drawLine_pw13.z - (drawLine_pw20.z * segbegwid));
                drawLine_pw23.set(drawLine_pw14.x + (drawLine_pw20.x * segendwid),
                                  drawLine_pw14.y + (drawLine_pw20.y * segendwid),
                                  drawLine_pw14.z + (drawLine_pw20.z * segendwid));
                drawLine_pw24.set(drawLine_pw14.x - (drawLine_pw20.x * segendwid),
                                  drawLine_pw14.y - (drawLine_pw20.y * segendwid),
                                  drawLine_pw14.z - (drawLine_pw20.z * segendwid));

                // base
                drawLine__ECS__cont__flat__wid_none(drawLine_pw21, drawLine_pw22);
                drawLine__ECS__cont__flat__wid_none(drawLine_pw22, drawLine_pw24);
                drawLine__ECS__cont__flat__wid_none(drawLine_pw24, drawLine_pw23);
                drawLine__ECS__cont__flat__wid_none(drawLine_pw23, drawLine_pw21);

                // sides
                drawLine__ECS__cont__flat__wid_none(drawLine_pw21.x, drawLine_pw21.y, drawLine_pw21.z,
                                                    drawLine_pw21.x, drawLine_pw21.y, drawLine_pw21.z + thickness);
                drawLine__ECS__cont__flat__wid_none(drawLine_pw22.x, drawLine_pw22.y, drawLine_pw22.z,
                                                    drawLine_pw22.x, drawLine_pw22.y, drawLine_pw22.z + thickness);
                drawLine__ECS__cont__flat__wid_none(drawLine_pw23.x, drawLine_pw23.y, drawLine_pw23.z,
                                                    drawLine_pw23.x, drawLine_pw23.y, drawLine_pw23.z + thickness);
                drawLine__ECS__cont__flat__wid_none(drawLine_pw24.x, drawLine_pw24.y, drawLine_pw24.z,
                                                    drawLine_pw24.x, drawLine_pw24.y, drawLine_pw24.z + thickness);

                // top
                drawLine__ECS__cont__flat__wid_none(drawLine_pw21.x, drawLine_pw21.y, drawLine_pw21.z + thickness,
                                                    drawLine_pw22.x, drawLine_pw22.y, drawLine_pw22.z + thickness);
                drawLine__ECS__cont__flat__wid_none(drawLine_pw22.x, drawLine_pw22.y, drawLine_pw22.z + thickness,
                                                    drawLine_pw24.x, drawLine_pw24.y, drawLine_pw24.z + thickness);
                drawLine__ECS__cont__flat__wid_none(drawLine_pw24.x, drawLine_pw24.y, drawLine_pw24.z + thickness,
                                                    drawLine_pw23.x, drawLine_pw23.y, drawLine_pw23.z + thickness);
                drawLine__ECS__cont__flat__wid_none(drawLine_pw23.x, drawLine_pw23.y, drawLine_pw23.z + thickness,
                                                    drawLine_pw21.x, drawLine_pw21.y, drawLine_pw21.z + thickness);

                // Increment
                patpos += seglen;
            }
        }

        // Increment
        linpos += patlen;


        // --- Draw subsequent whole patterns

        // Increment along line in patlen units starting with second pattern run
        for (int i = 1; i < linlenmod; i++)
        {   // Whole pattern runs

            // Increment thru pattern
            patpos = 0;
            for (int j = 0; j < E_ltype_dashlencount; j++)
            {
                seglen = E_ltype_lenlist[j] * E_ltypescale;

                if (seglen < 0.0)
                {   // Gap

                    // Increment
                    patpos += -seglen;
                }
                else
                {   // Draw

                    segbeg = linpos + patpos;
                    segend = segbeg + seglen;

                    segbegwid = ((segbeg / linlen) * (endwid - begwid)) + begwid;
                    segendwid = ((segend / linlen) * (endwid - begwid)) + begwid;

                    // Set beg point
                    drawLine_pw13.set(p1.x + (drawLine_pw10.x * segbeg),
                                      p1.y + (drawLine_pw10.y * segbeg),
                                      p1.z + (drawLine_pw10.z * segbeg));

                    // Set end point
                    drawLine_pw14.set(p1.x + (drawLine_pw10.x * segend),
                                      p1.y + (drawLine_pw10.y * segend),
                                      p1.z + (drawLine_pw10.z * segend));

                    // Draw it
                    drawLine_pw21.set(drawLine_pw13.x + (drawLine_pw20.x * segbegwid),
                                      drawLine_pw13.y + (drawLine_pw20.y * segbegwid),
                                      drawLine_pw13.z + (drawLine_pw20.z * segbegwid));
                    drawLine_pw22.set(drawLine_pw13.x - (drawLine_pw20.x * segbegwid),
                                      drawLine_pw13.y - (drawLine_pw20.y * segbegwid),
                                      drawLine_pw13.z - (drawLine_pw20.z * segbegwid));
                    drawLine_pw23.set(drawLine_pw14.x + (drawLine_pw20.x * segendwid),
                                      drawLine_pw14.y + (drawLine_pw20.y * segendwid),
                                      drawLine_pw14.z + (drawLine_pw20.z * segendwid));
                    drawLine_pw24.set(drawLine_pw14.x - (drawLine_pw20.x * segendwid),
                                      drawLine_pw14.y - (drawLine_pw20.y * segendwid),
                                      drawLine_pw14.z - (drawLine_pw20.z * segendwid));

                    // base
                    drawLine__ECS__cont__flat__wid_none(drawLine_pw21, drawLine_pw22);
                    drawLine__ECS__cont__flat__wid_none(drawLine_pw22, drawLine_pw24);
                    drawLine__ECS__cont__flat__wid_none(drawLine_pw24, drawLine_pw23);
                    drawLine__ECS__cont__flat__wid_none(drawLine_pw23, drawLine_pw21);

                    // sides
                    drawLine__ECS__cont__flat__wid_none(drawLine_pw21.x, drawLine_pw21.y, drawLine_pw21.z,
                                                        drawLine_pw21.x, drawLine_pw21.y, drawLine_pw21.z + thickness);
                    drawLine__ECS__cont__flat__wid_none(drawLine_pw22.x, drawLine_pw22.y, drawLine_pw22.z,
                                                        drawLine_pw22.x, drawLine_pw22.y, drawLine_pw22.z + thickness);
                    drawLine__ECS__cont__flat__wid_none(drawLine_pw23.x, drawLine_pw23.y, drawLine_pw23.z,
                                                        drawLine_pw23.x, drawLine_pw23.y, drawLine_pw23.z + thickness);
                    drawLine__ECS__cont__flat__wid_none(drawLine_pw24.x, drawLine_pw24.y, drawLine_pw24.z,
                                                        drawLine_pw24.x, drawLine_pw24.y, drawLine_pw24.z + thickness);

                    // top
                    drawLine__ECS__cont__flat__wid_none(drawLine_pw21.x, drawLine_pw21.y, drawLine_pw21.z + thickness,
                                                        drawLine_pw22.x, drawLine_pw22.y, drawLine_pw22.z + thickness);
                    drawLine__ECS__cont__flat__wid_none(drawLine_pw22.x, drawLine_pw22.y, drawLine_pw22.z + thickness,
                                                        drawLine_pw24.x, drawLine_pw24.y, drawLine_pw24.z + thickness);
                    drawLine__ECS__cont__flat__wid_none(drawLine_pw24.x, drawLine_pw24.y, drawLine_pw24.z + thickness,
                                                        drawLine_pw23.x, drawLine_pw23.y, drawLine_pw23.z + thickness);
                    drawLine__ECS__cont__flat__wid_none(drawLine_pw23.x, drawLine_pw23.y, drawLine_pw23.z + thickness,
                                                        drawLine_pw21.x, drawLine_pw21.y, drawLine_pw21.z + thickness);

                    // Increment
                    patpos += seglen;
                }
            }

            // Increment
            linpos += patlen;
        } // end of whole pattern runs


        // --- Draw last pattern (first dash only to end of line)

        segbegwid = ((linpos / linlen) * (endwid - begwid)) + begwid;

        // Set beg point
        drawLine_pw13.set(p1.x + (drawLine_pw10.x * linpos),
                          p1.y + (drawLine_pw10.y * linpos),
                          p1.z + (drawLine_pw10.z * linpos));

        // Draw it
        drawLine_pw21.set(drawLine_pw13.x + (drawLine_pw20.x * segbegwid),
                          drawLine_pw13.y + (drawLine_pw20.y * segbegwid),
                          drawLine_pw13.z + (drawLine_pw20.z * segbegwid));
        drawLine_pw22.set(drawLine_pw13.x - (drawLine_pw20.x * segbegwid),
                          drawLine_pw13.y - (drawLine_pw20.y * segbegwid),
                          drawLine_pw13.z - (drawLine_pw20.z * segbegwid));
        drawLine_pw23.set(p2.x + (drawLine_pw20.x * endwid),
                          p2.y + (drawLine_pw20.y * endwid),
                          p2.z + (drawLine_pw20.z * endwid));
        drawLine_pw24.set(p2.x - (drawLine_pw20.x * endwid),
                          p2.y - (drawLine_pw20.y * endwid),
                          p2.z - (drawLine_pw20.z * endwid));

        // base
        drawLine__ECS__cont__flat__wid_none(drawLine_pw21, drawLine_pw22);
        drawLine__ECS__cont__flat__wid_none(drawLine_pw22, drawLine_pw24);
        drawLine__ECS__cont__flat__wid_none(drawLine_pw24, drawLine_pw23);
        drawLine__ECS__cont__flat__wid_none(drawLine_pw23, drawLine_pw21);

        // sides
        drawLine__ECS__cont__flat__wid_none(drawLine_pw21.x, drawLine_pw21.y, drawLine_pw21.z,
                                            drawLine_pw21.x, drawLine_pw21.y, drawLine_pw21.z + thickness);
        drawLine__ECS__cont__flat__wid_none(drawLine_pw22.x, drawLine_pw22.y, drawLine_pw22.z,
                                            drawLine_pw22.x, drawLine_pw22.y, drawLine_pw22.z + thickness);
        drawLine__ECS__cont__flat__wid_none(drawLine_pw23.x, drawLine_pw23.y, drawLine_pw23.z,
                                            drawLine_pw23.x, drawLine_pw23.y, drawLine_pw23.z + thickness);
        drawLine__ECS__cont__flat__wid_none(drawLine_pw24.x, drawLine_pw24.y, drawLine_pw24.z,
                                            drawLine_pw24.x, drawLine_pw24.y, drawLine_pw24.z + thickness);

        // top
        drawLine__ECS__cont__flat__wid_none(drawLine_pw21.x, drawLine_pw21.y, drawLine_pw21.z + thickness,
                                            drawLine_pw22.x, drawLine_pw22.y, drawLine_pw22.z + thickness);
        drawLine__ECS__cont__flat__wid_none(drawLine_pw22.x, drawLine_pw22.y, drawLine_pw22.z + thickness,
                                            drawLine_pw24.x, drawLine_pw24.y, drawLine_pw24.z + thickness);
        drawLine__ECS__cont__flat__wid_none(drawLine_pw24.x, drawLine_pw24.y, drawLine_pw24.z + thickness,
                                            drawLine_pw23.x, drawLine_pw23.y, drawLine_pw23.z + thickness);
        drawLine__ECS__cont__flat__wid_none(drawLine_pw23.x, drawLine_pw23.y, drawLine_pw23.z + thickness,
                                            drawLine_pw21.x, drawLine_pw21.y, drawLine_pw21.z + thickness);
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++




    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // C U R V E S
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    /**
     * Circle point.
     */
    private
    YxxfGfxPointW               drawCircle_pw1  = new YxxfGfxPointW();
    /**
     * Circle point.
     */
    private
    YxxfGfxPointW               drawCircle_pw2  = new YxxfGfxPointW();
    /**
     * Circle point.
     */
    private
    YxxfGfxPointW               drawCircle_pw3  = new YxxfGfxPointW();
    /**
     * Circle point.
     */
    private
    YxxfGfxPointW               drawCircle_pw4  = new YxxfGfxPointW();
    /**
     * Circle point.
     */
    private
    YxxfGfxPointW               drawCircle_pw21 = new YxxfGfxPointW();
    /**
     * Circle point.
     */
    private
    YxxfGfxPointW               drawCircle_pw22 = new YxxfGfxPointW();
    /**
     * Circle point.
     */
    private
    YxxfGfxPointW               drawCircle_pw23 = new YxxfGfxPointW();
    /**
     * Circle point.
     */
    private
    YxxfGfxPointW               drawCircle_pw24 = new YxxfGfxPointW();

    /**
     * Circle point.
     */
    private
    YxxfGfxPointS               drawCircle_ps1  = new YxxfGfxPointS();
    /**
     * Circle point.
     */
    private
    YxxfGfxPointS               drawCircle_ps2  = new YxxfGfxPointS();
    /**
     * Circle point.
     */
    private
    YxxfGfxPointS               drawCircle_ps3  = new YxxfGfxPointS();
    /**
     * Circle point.
     */
    private
    YxxfGfxPointS               drawCircle_ps4  = new YxxfGfxPointS();
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // ECS simple circles
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    /**
     * Draw ECS simple circle.
     * @param center The circle center point.
     * @param radius The circle radius.
     * @param begang TODO
     * @param swgang TODO
     */
    public
    void drawCircle__ECS__cont__flat__wid_none(YxxfGfxPointW center,
                                               double radius,
                                               double begang, double swgang)
    {
        // Estimate size on display
        ECS_to_SCS(drawCircle_pw1.set(0, 0, 0),      drawCircle_ps1); // origin
        ECS_to_SCS(drawCircle_pw2.set(radius, 0, 0), drawCircle_ps2); // x
        ECS_to_SCS(drawCircle_pw3.set(0, radius, 0), drawCircle_ps3); // y
        ECS_to_SCS(drawCircle_pw4.set(0, 0, radius), drawCircle_ps4); // z
        double xdist = (double)Math.sqrt( ((double)drawCircle_ps2.x - (double)drawCircle_ps1.x) *
                                          ((double)drawCircle_ps2.x - (double)drawCircle_ps1.x) +
                                          ((double)drawCircle_ps2.y - (double)drawCircle_ps1.y) *
                                          ((double)drawCircle_ps2.y - (double)drawCircle_ps1.y) );
        double ydist = (double)Math.sqrt( ((double)drawCircle_ps3.x - (double)drawCircle_ps1.x) *
                                          ((double)drawCircle_ps3.x - (double)drawCircle_ps1.x) +
                                          ((double)drawCircle_ps3.y - (double)drawCircle_ps1.y) *
                                          ((double)drawCircle_ps3.y - (double)drawCircle_ps1.y) );
        double zdist = (double)Math.sqrt( ((double)drawCircle_ps4.x - (double)drawCircle_ps1.x) *
                                          ((double)drawCircle_ps4.x - (double)drawCircle_ps1.x) +
                                          ((double)drawCircle_ps4.y - (double)drawCircle_ps1.y) *
                                          ((double)drawCircle_ps4.y - (double)drawCircle_ps1.y) );
        // Um (?), take the three values and get radius in pixels
        double rdist = Math.sqrt(xdist * xdist + ydist * ydist + zdist * zdist);
        double sdist = rdist / 2.0;
//      System.out.println("drawCircle_ECS_simple:xdist=" + xdist + ",ydist=" + ydist + ",zdist=" + zdist);
//      System.out.println("drawCircle_ECS_simple:rdist=" + rdist + ",sdist=" + sdist);
        if (sdist < 6.0) sdist = 6.0;


        //
        // Calculate and draw
        //
        double swglen, seglen, delta;

        // Determine arc length and segment length and delta
        if (swgang < 0.0)
        { // CW
            swglen = -swgang;
            delta  = -Math.PI / sdist;
        }
        else
        { // CCW
            swglen = swgang;
            delta  = Math.PI / sdist;
        }
        seglen =  Math.PI / sdist;


        if ((swglen + DOUBLE_MIN_TOLERANCE) < seglen)
        {   // line is too short to hold even one segment,
            // draw continuous line
            drawCircle_pw1.set(radius * Math.cos(begang) + center.x,
                               radius * Math.sin(begang) + center.y,
                               center.z);
            drawCircle_pw2.set(radius * Math.cos(begang + swgang) + center.x,
                               radius * Math.sin(begang + swgang) + center.y,
                               center.z);
            drawLine__ECS__cont__flat__wid_none(drawCircle_pw1, drawCircle_pw2);
            return;
        }


        // calculate segment values
        int    swglenmod = (int)Math.floor((swglen + DOUBLE_MIN_TOLERANCE) / seglen); // whole segment units
        double swglenrem = (swglen + DOUBLE_MIN_TOLERANCE) - (seglen * swglenmod); // size of any remainder

        double linbeg = begang + (swglenrem - delta) / 2.0; // actual line beg
        double linpos = linbeg;


        //
        // Draw arc segments
        //   --- Draw first segment (from beginning of line)
        //   --- Draw subsequent whole segments
        //   --- Draw last segment (to end of line)
        //


        // --- Draw first segment (from beginning of line)

        drawCircle_pw1.set(radius * Math.cos(begang) + center.x,
                           radius * Math.sin(begang) + center.y,
                           center.z);
        drawCircle_pw2.set(radius * Math.cos(linpos + delta) + center.x,
                           radius * Math.sin(linpos + delta) + center.y,
                           center.z);
        drawLine__ECS__cont__flat__wid_none(drawCircle_pw1, drawCircle_pw2);

        linpos += delta;


        // --- Draw subsequent whole segments

        // Increment along line in seglen units starting with second segment run
        for (int i = 1; i < swglenmod; i++)
        {   // Whole segment runs
            drawCircle_pw1.set(radius * Math.cos(linpos) + center.x,
                               radius * Math.sin(linpos) + center.y,
                               center.z);
            drawCircle_pw2.set(radius * Math.cos(linpos + delta) + center.x,
                               radius * Math.sin(linpos + delta) + center.y,
                               center.z);
            drawLine__ECS__cont__flat__wid_none(drawCircle_pw1, drawCircle_pw2);

            linpos += delta;
        } // end of whole segment runs


        // --- Draw last segment (to end of line)

        drawCircle_pw1.set(radius * Math.cos(linpos) + center.x,
                           radius * Math.sin(linpos) + center.y,
                           center.z);
        drawCircle_pw2.set(radius * Math.cos(begang + swgang) + center.x,
                           radius * Math.sin(begang + swgang) + center.y,
                           center.z);
        drawLine__ECS__cont__flat__wid_none(drawCircle_pw1, drawCircle_pw2);
    }


    /**
     * Draw ECS simple circle.
     * @param center The circle center point.
     * @param radius The circle radius.
     * @param begang TODO
     * @param swgang TODO
     * @param thickness The line thickness
     */
    public
    void drawCircle__ECS__cont__thck__wid_none(YxxfGfxPointW center,
                                               double radius,
                                               double begang, double swgang,
                                               double thickness)
    {
        // Estimate size on display
        ECS_to_SCS(drawCircle_pw1.set(0, 0, 0),      drawCircle_ps1); // origin
        ECS_to_SCS(drawCircle_pw2.set(radius, 0, 0), drawCircle_ps2); // x
        ECS_to_SCS(drawCircle_pw3.set(0, radius, 0), drawCircle_ps3); // y
        ECS_to_SCS(drawCircle_pw4.set(0, 0, radius), drawCircle_ps4); // z
        double xdist = (double)Math.sqrt( ((double)drawCircle_ps2.x - (double)drawCircle_ps1.x) *
                                          ((double)drawCircle_ps2.x - (double)drawCircle_ps1.x) +
                                          ((double)drawCircle_ps2.y - (double)drawCircle_ps1.y) *
                                          ((double)drawCircle_ps2.y - (double)drawCircle_ps1.y) );
        double ydist = (double)Math.sqrt( ((double)drawCircle_ps3.x - (double)drawCircle_ps1.x) *
                                          ((double)drawCircle_ps3.x - (double)drawCircle_ps1.x) +
                                          ((double)drawCircle_ps3.y - (double)drawCircle_ps1.y) *
                                          ((double)drawCircle_ps3.y - (double)drawCircle_ps1.y) );
        double zdist = (double)Math.sqrt( ((double)drawCircle_ps4.x - (double)drawCircle_ps1.x) *
                                          ((double)drawCircle_ps4.x - (double)drawCircle_ps1.x) +
                                          ((double)drawCircle_ps4.y - (double)drawCircle_ps1.y) *
                                          ((double)drawCircle_ps4.y - (double)drawCircle_ps1.y) );
        // Um (?), take the three values and get radius in pixels
        double rdist = Math.sqrt(xdist * xdist + ydist * ydist + zdist * zdist);
        double sdist = rdist / 2.0;
//      System.out.println("drawCircle_ECS_simple:xdist=" + xdist + ",ydist=" + ydist + ",zdist=" + zdist);
//      System.out.println("drawCircle_ECS_simple:rdist=" + rdist + ",sdist=" + sdist);
        if (sdist < 6.0) sdist = 6.0;


        //
        // Calculate and draw
        //
        double swglen, seglen, delta;

        // Determine arc length and segment length and delta
        if (swgang < 0.0)
        { // CW
            swglen = -swgang;
            delta  = -Math.PI / sdist;
        }
        else
        { // CCW
            swglen = swgang;
            delta  = Math.PI / sdist;
        }
        seglen =  Math.PI / sdist;


        if ((swglen + DOUBLE_MIN_TOLERANCE) < seglen)
        {   // line is too short to hold even one segment,
            // draw continuous line
            drawCircle_pw1.set(radius * Math.cos(begang) + center.x,
                               radius * Math.sin(begang) + center.y,
                               center.z);
            drawCircle_pw2.set(radius * Math.cos(begang + swgang) + center.x,
                               radius * Math.sin(begang + swgang) + center.y,
                               center.z);
            drawLine__ECS__cont__flat__wid_none(drawCircle_pw1, drawCircle_pw2); // base
            drawCircle_pw3.set(drawCircle_pw1.x, drawCircle_pw1.y, drawCircle_pw1.z + thickness);
            drawCircle_pw4.set(drawCircle_pw2.x, drawCircle_pw2.y, drawCircle_pw2.z + thickness);
            drawLine__ECS__cont__flat__wid_none(drawCircle_pw1, drawCircle_pw3); // beg side
            drawLine__ECS__cont__flat__wid_none(drawCircle_pw3, drawCircle_pw4); // top
            drawLine__ECS__cont__flat__wid_none(drawCircle_pw2, drawCircle_pw4); // end side
            return;
        }


        // calculate segment values
        int    swglenmod = (int)Math.floor((swglen + DOUBLE_MIN_TOLERANCE) / seglen); // whole segment units
        double swglenrem = (swglen + DOUBLE_MIN_TOLERANCE) - (seglen * swglenmod); // size of any remainder

        double linbeg = begang + (swglenrem - delta) / 2.0; // actual line beg
        double linpos = linbeg;


        //
        // Draw arc segments
        //   --- Draw first segment (from beginning of line)
        //   --- Draw subsequent whole segments
        //   --- Draw last segment (to end of line)
        //


        // --- Draw first segment (from beginning of line)

        drawCircle_pw1.set(radius * Math.cos(begang) + center.x,
                           radius * Math.sin(begang) + center.y,
                           center.z);
        drawCircle_pw2.set(radius * Math.cos(linpos + delta) + center.x,
                           radius * Math.sin(linpos + delta) + center.y,
                           center.z);
        drawLine__ECS__cont__flat__wid_none(drawCircle_pw1, drawCircle_pw2); // base
        drawCircle_pw3.set(drawCircle_pw1.x, drawCircle_pw1.y, drawCircle_pw1.z + thickness);
        drawCircle_pw4.set(drawCircle_pw2.x, drawCircle_pw2.y, drawCircle_pw2.z + thickness);
        drawLine__ECS__cont__flat__wid_none(drawCircle_pw1, drawCircle_pw3); // beg side
        drawLine__ECS__cont__flat__wid_none(drawCircle_pw3, drawCircle_pw4); // top

        linpos += delta;


        // --- Draw subsequent whole segments

        // Increment along line in seglen units starting with second segment run
        for (int i = 1; i < swglenmod; i++)
        {   // Whole segment runs
            drawCircle_pw1.set(radius * Math.cos(linpos) + center.x,
                               radius * Math.sin(linpos) + center.y,
                               center.z);
            drawCircle_pw2.set(radius * Math.cos(linpos + delta) + center.x,
                               radius * Math.sin(linpos + delta) + center.y,
                               center.z);
            drawLine__ECS__cont__flat__wid_none(drawCircle_pw1, drawCircle_pw2); // base
            drawCircle_pw3.set(drawCircle_pw1.x, drawCircle_pw1.y, drawCircle_pw1.z + thickness);
            drawCircle_pw4.set(drawCircle_pw2.x, drawCircle_pw2.y, drawCircle_pw2.z + thickness);
            drawLine__ECS__cont__flat__wid_none(drawCircle_pw1, drawCircle_pw3); // beg side
            drawLine__ECS__cont__flat__wid_none(drawCircle_pw3, drawCircle_pw4); // top

            linpos += delta;
        } // end of whole segment runs


        // --- Draw last segment (to end of line)

        drawCircle_pw1.set(radius * Math.cos(linpos) + center.x,
                           radius * Math.sin(linpos) + center.y,
                           center.z);
        drawCircle_pw2.set(radius * Math.cos(begang + swgang) + center.x,
                           radius * Math.sin(begang + swgang) + center.y,
                           center.z);
        drawLine__ECS__cont__flat__wid_none(drawCircle_pw1, drawCircle_pw2); // base
        drawCircle_pw3.set(drawCircle_pw1.x, drawCircle_pw1.y, drawCircle_pw1.z + thickness);
        drawCircle_pw4.set(drawCircle_pw2.x, drawCircle_pw2.y, drawCircle_pw2.z + thickness);
        drawLine__ECS__cont__flat__wid_none(drawCircle_pw1, drawCircle_pw3); // beg side
        drawLine__ECS__cont__flat__wid_none(drawCircle_pw3, drawCircle_pw4); // top
        drawLine__ECS__cont__flat__wid_none(drawCircle_pw2, drawCircle_pw4); // end side
    }


    public
    void drawCircle__ECS__cont__flat__wid_fill(YxxfGfxPointW center,
                                               double radius,
                                               double begang, double swgang,
                                               double begwid, double endwid)
    {
        // Estimate size on display
        ECS_to_SCS(drawCircle_pw1.set(0, 0, 0),      drawCircle_ps1); // origin
        ECS_to_SCS(drawCircle_pw2.set(radius, 0, 0), drawCircle_ps2); // x
        ECS_to_SCS(drawCircle_pw3.set(0, radius, 0), drawCircle_ps3); // y
        ECS_to_SCS(drawCircle_pw4.set(0, 0, radius), drawCircle_ps4); // z
        double xdist = (double)Math.sqrt( ((double)drawCircle_ps2.x - (double)drawCircle_ps1.x) *
                                          ((double)drawCircle_ps2.x - (double)drawCircle_ps1.x) +
                                          ((double)drawCircle_ps2.y - (double)drawCircle_ps1.y) *
                                          ((double)drawCircle_ps2.y - (double)drawCircle_ps1.y) );
        double ydist = (double)Math.sqrt( ((double)drawCircle_ps3.x - (double)drawCircle_ps1.x) *
                                          ((double)drawCircle_ps3.x - (double)drawCircle_ps1.x) +
                                          ((double)drawCircle_ps3.y - (double)drawCircle_ps1.y) *
                                          ((double)drawCircle_ps3.y - (double)drawCircle_ps1.y) );
        double zdist = (double)Math.sqrt( ((double)drawCircle_ps4.x - (double)drawCircle_ps1.x) *
                                          ((double)drawCircle_ps4.x - (double)drawCircle_ps1.x) +
                                          ((double)drawCircle_ps4.y - (double)drawCircle_ps1.y) *
                                          ((double)drawCircle_ps4.y - (double)drawCircle_ps1.y) );
        // Um (?), take the three values and get radius in pixels
        double rdist = Math.sqrt(xdist * xdist + ydist * ydist + zdist * zdist);
        double sdist = rdist / 2.0;
////    System.out.println("drawCircle__ECS__cont__flat__wid_fill:radius=" + radius + ",begang=" + begang + ",swgang=" + swgang);
////    System.out.println("drawCircle__ECS__cont__flat__wid_fill:xdist=" + xdist + ",ydist=" + ydist + ",zdist=" + zdist);
////    System.out.println("drawCircle__ECS__cont__flat__wid_fill:rdist=" + rdist + ",sdist=" + sdist);
        if (sdist < 6.0) sdist = 6.0;


        //
        // Calculate and draw
        //
        double swglen, seglen, delta;

        // Determine arc length and segment length and delta
        if (swgang < 0.0)
        { // CW
            swglen = -swgang;
            delta  = -Math.PI / sdist;
        }
        else
        { // CCW
            swglen = swgang;
            delta  = Math.PI / sdist;
        }
        seglen =  Math.PI / sdist;


        // Width calculation
        begwid /= 2.0;
        endwid /= 2.0;


        if ((swglen + DOUBLE_MIN_TOLERANCE) < seglen)
        {   // line is too short to hold even one segment,
            // draw continuous line
            drawCircle_pw21.set((radius + begwid) * Math.cos(begang) + center.x,
                                (radius + begwid) * Math.sin(begang) + center.y,
                                center.z);
            drawCircle_pw22.set((radius - begwid) * Math.cos(begang) + center.x,
                                (radius - begwid) * Math.sin(begang) + center.y,
                                center.z);
            drawCircle_pw23.set((radius + endwid) * Math.cos(begang + swgang) + center.x,
                                (radius + endwid) * Math.sin(begang + swgang) + center.y,
                                center.z);
            drawCircle_pw24.set((radius - endwid) * Math.cos(begang + swgang) + center.x,
                                (radius - endwid) * Math.sin(begang + swgang) + center.y,
                                center.z);
            if (gc_doing_flag_calc_extents)
            {
                ECS_to_SCS_calc_extents(drawCircle_pw21, drawSolid_ps1);
                ECS_to_SCS_calc_extents(drawCircle_pw22, drawSolid_ps2);
                ECS_to_SCS_calc_extents(drawCircle_pw23, drawSolid_ps3);
                ECS_to_SCS_calc_extents(drawCircle_pw24, drawSolid_ps4);
            }
            else
            {
                ECS_to_SCS(drawCircle_pw21, drawSolid_ps1);
                ECS_to_SCS(drawCircle_pw22, drawSolid_ps2);
                ECS_to_SCS(drawCircle_pw23, drawSolid_ps3);
                ECS_to_SCS(drawCircle_pw24, drawSolid_ps4);
            }

            drawSolid_xPoints[0] = drawSolid_ps1.x;
            drawSolid_xPoints[1] = drawSolid_ps3.x;
            drawSolid_xPoints[2] = drawSolid_ps4.x;
            drawSolid_xPoints[3] = drawSolid_ps2.x;
            drawSolid_yPoints[0] = drawSolid_ps1.y;
            drawSolid_yPoints[1] = drawSolid_ps3.y;
            drawSolid_yPoints[2] = drawSolid_ps4.y;
            drawSolid_yPoints[3] = drawSolid_ps2.y;

            if (renderingjgcflag)
                jgc.fillPolygon(drawSolid_xPoints, drawSolid_yPoints, 4);
            return;
        }


        // calculate segment values
        int    swglenmod = (int)Math.floor((swglen + DOUBLE_MIN_TOLERANCE) / seglen); // whole segment units
        double swglenrem = (swglen + DOUBLE_MIN_TOLERANCE) - (seglen * swglenmod); // size of any remainder

        double linbeg = begang + (swglenrem - delta) / 2.0; // actual line beg
        double linpos = linbeg;


        //
        // Draw arc segments
        //   --- Draw first segment (from beginning of line)
        //   --- Draw subsequent whole segments
        //   --- Draw last segment (to end of line)
        //
        double segbegwid, segendwid;


        // --- Draw first segment (from beginning of line)

        segendwid = (((linpos + delta - begang) / swgang) * (endwid - begwid)) + begwid;

        drawCircle_pw21.set((radius + begwid) * Math.cos(begang) + center.x,
                            (radius + begwid) * Math.sin(begang) + center.y,
                            center.z);
        drawCircle_pw22.set((radius - begwid) * Math.cos(begang) + center.x,
                            (radius - begwid) * Math.sin(begang) + center.y,
                            center.z);
        drawCircle_pw23.set((radius + segendwid) * Math.cos(linpos + delta) + center.x,
                            (radius + segendwid) * Math.sin(linpos + delta) + center.y,
                            center.z);
        drawCircle_pw24.set((radius - segendwid) * Math.cos(linpos + delta) + center.x,
                            (radius - segendwid) * Math.sin(linpos + delta) + center.y,
                            center.z);
        if (gc_doing_flag_calc_extents)
        {
            ECS_to_SCS_calc_extents(drawCircle_pw21, drawSolid_ps1);
            ECS_to_SCS_calc_extents(drawCircle_pw22, drawSolid_ps2);
            ECS_to_SCS_calc_extents(drawCircle_pw23, drawSolid_ps3);
            ECS_to_SCS_calc_extents(drawCircle_pw24, drawSolid_ps4);
        }
        else
        {
            ECS_to_SCS(drawCircle_pw21, drawSolid_ps1);
            ECS_to_SCS(drawCircle_pw22, drawSolid_ps2);
            ECS_to_SCS(drawCircle_pw23, drawSolid_ps3);
            ECS_to_SCS(drawCircle_pw24, drawSolid_ps4);
        }

        drawSolid_xPoints[0] = drawSolid_ps1.x;
        drawSolid_xPoints[1] = drawSolid_ps3.x;
        drawSolid_xPoints[2] = drawSolid_ps4.x;
        drawSolid_xPoints[3] = drawSolid_ps2.x;
        drawSolid_yPoints[0] = drawSolid_ps1.y;
        drawSolid_yPoints[1] = drawSolid_ps3.y;
        drawSolid_yPoints[2] = drawSolid_ps4.y;
        drawSolid_yPoints[3] = drawSolid_ps2.y;

        if (renderingjgcflag)
            jgc.fillPolygon(drawSolid_xPoints, drawSolid_yPoints, 4);

        linpos += delta;


        // --- Draw subsequent whole segments

        // Increment along line in seglen units starting with second segment run
        for (int i = 1; i < swglenmod; i++)
        {   // Whole segment runs
            segbegwid = (((linpos - begang) / swgang) * (endwid - begwid)) + begwid;
            segendwid = (((linpos + delta - begang) / swgang) * (endwid - begwid)) + begwid;

            drawCircle_pw21.set((radius + segbegwid) * Math.cos(linpos) + center.x,
                                (radius + segbegwid) * Math.sin(linpos) + center.y,
                                center.z);
            drawCircle_pw22.set((radius - segbegwid) * Math.cos(linpos) + center.x,
                                (radius - segbegwid) * Math.sin(linpos) + center.y,
                                center.z);
            drawCircle_pw23.set((radius + segendwid) * Math.cos(linpos + delta) + center.x,
                                (radius + segendwid) * Math.sin(linpos + delta) + center.y,
                                center.z);
            drawCircle_pw24.set((radius - segendwid) * Math.cos(linpos + delta) + center.x,
                                (radius - segendwid) * Math.sin(linpos + delta) + center.y,
                                center.z);
            if (gc_doing_flag_calc_extents)
            {
                ECS_to_SCS_calc_extents(drawCircle_pw21, drawSolid_ps1);
                ECS_to_SCS_calc_extents(drawCircle_pw22, drawSolid_ps2);
                ECS_to_SCS_calc_extents(drawCircle_pw23, drawSolid_ps3);
                ECS_to_SCS_calc_extents(drawCircle_pw24, drawSolid_ps4);
            }
            else
            {
                ECS_to_SCS(drawCircle_pw21, drawSolid_ps1);
                ECS_to_SCS(drawCircle_pw22, drawSolid_ps2);
                ECS_to_SCS(drawCircle_pw23, drawSolid_ps3);
                ECS_to_SCS(drawCircle_pw24, drawSolid_ps4);
            }

            drawSolid_xPoints[0] = drawSolid_ps1.x;
            drawSolid_xPoints[1] = drawSolid_ps3.x;
            drawSolid_xPoints[2] = drawSolid_ps4.x;
            drawSolid_xPoints[3] = drawSolid_ps2.x;
            drawSolid_yPoints[0] = drawSolid_ps1.y;
            drawSolid_yPoints[1] = drawSolid_ps3.y;
            drawSolid_yPoints[2] = drawSolid_ps4.y;
            drawSolid_yPoints[3] = drawSolid_ps2.y;

            if (renderingjgcflag)
                jgc.fillPolygon(drawSolid_xPoints, drawSolid_yPoints, 4);

            linpos += delta;
        } // end of whole segment runs


        // --- Draw last segment (to end of line)

        segbegwid = (((linpos - begang) / swgang) * (endwid - begwid)) + begwid;

        drawCircle_pw21.set((radius + segbegwid) * Math.cos(linpos) + center.x,
                            (radius + segbegwid) * Math.sin(linpos) + center.y,
                            center.z);
        drawCircle_pw22.set((radius - segbegwid) * Math.cos(linpos) + center.x,
                            (radius - segbegwid) * Math.sin(linpos) + center.y,
                            center.z);
        drawCircle_pw23.set((radius + endwid) * Math.cos(begang + swgang) + center.x,
                            (radius + endwid) * Math.sin(begang + swgang) + center.y,
                            center.z);
        drawCircle_pw24.set((radius - endwid) * Math.cos(begang + swgang) + center.x,
                            (radius - endwid) * Math.sin(begang + swgang) + center.y,
                            center.z);
        if (gc_doing_flag_calc_extents)
        {
            ECS_to_SCS_calc_extents(drawCircle_pw21, drawSolid_ps1);
            ECS_to_SCS_calc_extents(drawCircle_pw22, drawSolid_ps2);
            ECS_to_SCS_calc_extents(drawCircle_pw23, drawSolid_ps3);
            ECS_to_SCS_calc_extents(drawCircle_pw24, drawSolid_ps4);
        }
        else
        {
            ECS_to_SCS(drawCircle_pw21, drawSolid_ps1);
            ECS_to_SCS(drawCircle_pw22, drawSolid_ps2);
            ECS_to_SCS(drawCircle_pw23, drawSolid_ps3);
            ECS_to_SCS(drawCircle_pw24, drawSolid_ps4);
        }

        drawSolid_xPoints[0] = drawSolid_ps1.x;
        drawSolid_xPoints[1] = drawSolid_ps3.x;
        drawSolid_xPoints[2] = drawSolid_ps4.x;
        drawSolid_xPoints[3] = drawSolid_ps2.x;
        drawSolid_yPoints[0] = drawSolid_ps1.y;
        drawSolid_yPoints[1] = drawSolid_ps3.y;
        drawSolid_yPoints[2] = drawSolid_ps4.y;
        drawSolid_yPoints[3] = drawSolid_ps2.y;

        if (renderingjgcflag)
            jgc.fillPolygon(drawSolid_xPoints, drawSolid_yPoints, 4);
    }


    /**
     * Draw ECS simple circle.
     * @param center The circle center point.
     * @param radius The circle radius.
     * @param begang TODO
     * @param swgang TODO
     * @param begwid The beginning line width.
     * @param endwid The ending line width.
     */
    public
    void drawCircle__ECS__cont__flat__wid_wire(YxxfGfxPointW center,
                                               double radius,
                                               double begang, double swgang,
                                               double begwid, double endwid)
    {
        // Estimate size on display
        ECS_to_SCS(drawCircle_pw1.set(0, 0, 0),      drawCircle_ps1); // origin
        ECS_to_SCS(drawCircle_pw2.set(radius, 0, 0), drawCircle_ps2); // x
        ECS_to_SCS(drawCircle_pw3.set(0, radius, 0), drawCircle_ps3); // y
        ECS_to_SCS(drawCircle_pw4.set(0, 0, radius), drawCircle_ps4); // z
        double xdist = (double)Math.sqrt( ((double)drawCircle_ps2.x - (double)drawCircle_ps1.x) *
                                          ((double)drawCircle_ps2.x - (double)drawCircle_ps1.x) +
                                          ((double)drawCircle_ps2.y - (double)drawCircle_ps1.y) *
                                          ((double)drawCircle_ps2.y - (double)drawCircle_ps1.y) );
        double ydist = (double)Math.sqrt( ((double)drawCircle_ps3.x - (double)drawCircle_ps1.x) *
                                          ((double)drawCircle_ps3.x - (double)drawCircle_ps1.x) +
                                          ((double)drawCircle_ps3.y - (double)drawCircle_ps1.y) *
                                          ((double)drawCircle_ps3.y - (double)drawCircle_ps1.y) );
        double zdist = (double)Math.sqrt( ((double)drawCircle_ps4.x - (double)drawCircle_ps1.x) *
                                          ((double)drawCircle_ps4.x - (double)drawCircle_ps1.x) +
                                          ((double)drawCircle_ps4.y - (double)drawCircle_ps1.y) *
                                          ((double)drawCircle_ps4.y - (double)drawCircle_ps1.y) );
        // Um (?), take the three values and get radius in pixels
        double rdist = Math.sqrt(xdist * xdist + ydist * ydist + zdist * zdist);
        double sdist = rdist / 2.0;
//      System.out.println("drawCircle_ECS_simple:xdist=" + xdist + ",ydist=" + ydist + ",zdist=" + zdist);
//      System.out.println("drawCircle_ECS_simple:rdist=" + rdist + ",sdist=" + sdist);
        if (sdist < 6.0) sdist = 6.0;


        //
        // Calculate and draw
        //
        double swglen, seglen, delta;

        // Determine arc length and segment length and delta
        if (swgang < 0.0)
        { // CW
            swglen = -swgang;
            delta  = -Math.PI / sdist;
        }
        else
        { // CCW
            swglen = swgang;
            delta  = Math.PI / sdist;
        }
        seglen =  Math.PI / sdist;


        // Width calculation
        begwid /= 2.0;
        endwid /= 2.0;


        if ((swglen + DOUBLE_MIN_TOLERANCE) < seglen)
        {   // line is too short to hold even one segment,
            // draw continuous line
            drawCircle_pw21.set((radius + begwid) * Math.cos(begang) + center.x,
                                (radius + begwid) * Math.sin(begang) + center.y,
                                center.z);
            drawCircle_pw22.set((radius - begwid) * Math.cos(begang) + center.x,
                                (radius - begwid) * Math.sin(begang) + center.y,
                                center.z);
            drawCircle_pw23.set((radius + endwid) * Math.cos(begang + swgang) + center.x,
                                (radius + endwid) * Math.sin(begang + swgang) + center.y,
                                center.z);
            drawCircle_pw24.set((radius - endwid) * Math.cos(begang + swgang) + center.x,
                                (radius - endwid) * Math.sin(begang + swgang) + center.y,
                                center.z);
            drawLine__ECS__cont__flat__wid_none(drawCircle_pw21, drawCircle_pw22);
            drawLine__ECS__cont__flat__wid_none(drawCircle_pw22, drawCircle_pw24);
            drawLine__ECS__cont__flat__wid_none(drawCircle_pw24, drawCircle_pw23);
            drawLine__ECS__cont__flat__wid_none(drawCircle_pw23, drawCircle_pw21);
            return;
        }


        // calculate segment values
        int    swglenmod = (int)Math.floor((swglen + DOUBLE_MIN_TOLERANCE) / seglen); // whole segment units
        double swglenrem = (swglen + DOUBLE_MIN_TOLERANCE) - (seglen * swglenmod); // size of any remainder

        double linbeg = begang + (swglenrem - delta) / 2.0; // actual line beg
        double linpos = linbeg;


        //
        // Draw arc segments
        //   --- Draw first segment (from beginning of line)
        //   --- Draw subsequent whole segments
        //   --- Draw last segment (to end of line)
        //
        double segbegwid, segendwid;


        // --- Draw first segment (from beginning of line)

        segendwid = (((linpos + delta - begang) / swgang) * (endwid - begwid)) + begwid;

        drawCircle_pw21.set((radius + begwid) * Math.cos(begang) + center.x,
                            (radius + begwid) * Math.sin(begang) + center.y,
                            center.z);
        drawCircle_pw22.set((radius - begwid) * Math.cos(begang) + center.x,
                            (radius - begwid) * Math.sin(begang) + center.y,
                            center.z);
        drawCircle_pw23.set((radius + segendwid) * Math.cos(linpos + delta) + center.x,
                            (radius + segendwid) * Math.sin(linpos + delta) + center.y,
                            center.z);
        drawCircle_pw24.set((radius - segendwid) * Math.cos(linpos + delta) + center.x,
                            (radius - segendwid) * Math.sin(linpos + delta) + center.y,
                            center.z);
        drawLine__ECS__cont__flat__wid_none(drawCircle_pw21, drawCircle_pw22);
        drawLine__ECS__cont__flat__wid_none(drawCircle_pw22, drawCircle_pw24);
//      drawLine__ECS__cont__flat__wid_none(drawCircle_pw24, drawCircle_pw23);
        drawLine__ECS__cont__flat__wid_none(drawCircle_pw23, drawCircle_pw21);

        linpos += delta;


        // --- Draw subsequent whole segments

        // Increment along line in seglen units starting with second segment run
        for (int i = 1; i < swglenmod; i++)
        {   // Whole segment runs
            segbegwid = (((linpos - begang) / swgang) * (endwid - begwid)) + begwid;
            segendwid = (((linpos + delta - begang) / swgang) * (endwid - begwid)) + begwid;

            drawCircle_pw21.set((radius + segbegwid) * Math.cos(linpos) + center.x,
                                (radius + segbegwid) * Math.sin(linpos) + center.y,
                                center.z);
            drawCircle_pw22.set((radius - segbegwid) * Math.cos(linpos) + center.x,
                                (radius - segbegwid) * Math.sin(linpos) + center.y,
                                center.z);
            drawCircle_pw23.set((radius + segendwid) * Math.cos(linpos + delta) + center.x,
                                (radius + segendwid) * Math.sin(linpos + delta) + center.y,
                                center.z);
            drawCircle_pw24.set((radius - segendwid) * Math.cos(linpos + delta) + center.x,
                                (radius - segendwid) * Math.sin(linpos + delta) + center.y,
                                center.z);
            drawLine__ECS__cont__flat__wid_none(drawCircle_pw21, drawCircle_pw22);
            drawLine__ECS__cont__flat__wid_none(drawCircle_pw22, drawCircle_pw24);
//          drawLine__ECS__cont__flat__wid_none(drawCircle_pw24, drawCircle_pw23);
            drawLine__ECS__cont__flat__wid_none(drawCircle_pw23, drawCircle_pw21);

            linpos += delta;
        } // end of whole segment runs


        // --- Draw last segment (to end of line)

        segbegwid = (((linpos - begang) / swgang) * (endwid - begwid)) + begwid;

        drawCircle_pw21.set((radius + segbegwid) * Math.cos(linpos) + center.x,
                            (radius + segbegwid) * Math.sin(linpos) + center.y,
                            center.z);
        drawCircle_pw22.set((radius - segbegwid) * Math.cos(linpos) + center.x,
                            (radius - segbegwid) * Math.sin(linpos) + center.y,
                            center.z);
        drawCircle_pw23.set((radius + endwid) * Math.cos(begang + swgang) + center.x,
                            (radius + endwid) * Math.sin(begang + swgang) + center.y,
                            center.z);
        drawCircle_pw24.set((radius - endwid) * Math.cos(begang + swgang) + center.x,
                            (radius - endwid) * Math.sin(begang + swgang) + center.y,
                            center.z);
        drawLine__ECS__cont__flat__wid_none(drawCircle_pw21, drawCircle_pw22);
        drawLine__ECS__cont__flat__wid_none(drawCircle_pw22, drawCircle_pw24);
        drawLine__ECS__cont__flat__wid_none(drawCircle_pw24, drawCircle_pw23);
        drawLine__ECS__cont__flat__wid_none(drawCircle_pw23, drawCircle_pw21);
    }


    /**
     * Draw ECS simple circle.
     * @param center The circle center point.
     * @param radius The circle radius.
     * @param begang TODO
     * @param swgang TODO
     * @param thickness The line thickness.
     * @param begwid The beginning line width.
     * @param endwid The ending line width
     */
    public
    void drawCircle__ECS__cont__thck__wid_wire(YxxfGfxPointW center,
                                               double radius,
                                               double begang, double swgang,
                                               double thickness,
                                               double begwid, double endwid)
    {
        // Estimate size on display
        ECS_to_SCS(drawCircle_pw1.set(0, 0, 0),      drawCircle_ps1); // origin
        ECS_to_SCS(drawCircle_pw2.set(radius, 0, 0), drawCircle_ps2); // x
        ECS_to_SCS(drawCircle_pw3.set(0, radius, 0), drawCircle_ps3); // y
        ECS_to_SCS(drawCircle_pw4.set(0, 0, radius), drawCircle_ps4); // z
        double xdist = (double)Math.sqrt( ((double)drawCircle_ps2.x - (double)drawCircle_ps1.x) *
                                          ((double)drawCircle_ps2.x - (double)drawCircle_ps1.x) +
                                          ((double)drawCircle_ps2.y - (double)drawCircle_ps1.y) *
                                          ((double)drawCircle_ps2.y - (double)drawCircle_ps1.y) );
        double ydist = (double)Math.sqrt( ((double)drawCircle_ps3.x - (double)drawCircle_ps1.x) *
                                          ((double)drawCircle_ps3.x - (double)drawCircle_ps1.x) +
                                          ((double)drawCircle_ps3.y - (double)drawCircle_ps1.y) *
                                          ((double)drawCircle_ps3.y - (double)drawCircle_ps1.y) );
        double zdist = (double)Math.sqrt( ((double)drawCircle_ps4.x - (double)drawCircle_ps1.x) *
                                          ((double)drawCircle_ps4.x - (double)drawCircle_ps1.x) +
                                          ((double)drawCircle_ps4.y - (double)drawCircle_ps1.y) *
                                          ((double)drawCircle_ps4.y - (double)drawCircle_ps1.y) );
        // Um (?), take the three values and get radius in pixels
        double rdist = Math.sqrt(xdist * xdist + ydist * ydist + zdist * zdist);
        double sdist = rdist / 2.0;
//      System.out.println("drawCircle_ECS_simple:xdist=" + xdist + ",ydist=" + ydist + ",zdist=" + zdist);
//      System.out.println("drawCircle_ECS_simple:rdist=" + rdist + ",sdist=" + sdist);
        if (sdist < 6.0) sdist = 6.0;


        //
        // Calculate and draw
        //
        double swglen, seglen, delta;

        // Determine arc length and segment length and delta
        if (swgang < 0.0)
        { // CW
            swglen = -swgang;
            delta  = -Math.PI / sdist;
        }
        else
        { // CCW
            swglen = swgang;
            delta  = Math.PI / sdist;
        }
        seglen =  Math.PI / sdist;


        // Width calculation
        begwid /= 2.0;
        endwid /= 2.0;


        if ((swglen + DOUBLE_MIN_TOLERANCE) < seglen)
        {   // line is too short to hold even one segment,
            // draw continuous line
            drawCircle_pw21.set((radius + begwid) * Math.cos(begang) + center.x,
                                (radius + begwid) * Math.sin(begang) + center.y,
                                center.z);
            drawCircle_pw22.set((radius - begwid) * Math.cos(begang) + center.x,
                                (radius - begwid) * Math.sin(begang) + center.y,
                                center.z);
            drawCircle_pw23.set((radius + endwid) * Math.cos(begang + swgang) + center.x,
                                (radius + endwid) * Math.sin(begang + swgang) + center.y,
                                center.z);
            drawCircle_pw24.set((radius - endwid) * Math.cos(begang + swgang) + center.x,
                                (radius - endwid) * Math.sin(begang + swgang) + center.y,
                                center.z);
            // base
            drawLine__ECS__cont__flat__wid_none(drawCircle_pw21, drawCircle_pw22);
            drawLine__ECS__cont__flat__wid_none(drawCircle_pw22, drawCircle_pw24);
            drawLine__ECS__cont__flat__wid_none(drawCircle_pw24, drawCircle_pw23);
            drawLine__ECS__cont__flat__wid_none(drawCircle_pw23, drawCircle_pw21);
            // sides
            drawLine__ECS__cont__flat__wid_none(drawCircle_pw21.x, drawCircle_pw21.y, drawCircle_pw21.z,
                                                drawCircle_pw21.x, drawCircle_pw21.y, drawCircle_pw21.z + thickness);
            drawLine__ECS__cont__flat__wid_none(drawCircle_pw22.x, drawCircle_pw22.y, drawCircle_pw22.z,
                                                drawCircle_pw22.x, drawCircle_pw22.y, drawCircle_pw22.z + thickness);
            drawLine__ECS__cont__flat__wid_none(drawCircle_pw23.x, drawCircle_pw23.y, drawCircle_pw23.z,
                                                drawCircle_pw23.x, drawCircle_pw23.y, drawCircle_pw23.z + thickness);
            drawLine__ECS__cont__flat__wid_none(drawCircle_pw24.x, drawCircle_pw24.y, drawCircle_pw24.z,
                                                drawCircle_pw24.x, drawCircle_pw24.y, drawCircle_pw24.z + thickness);
            // top
            drawLine__ECS__cont__flat__wid_none(drawCircle_pw21.x, drawCircle_pw21.y, drawCircle_pw21.z + thickness,
                                                drawCircle_pw22.x, drawCircle_pw22.y, drawCircle_pw22.z + thickness);
            drawLine__ECS__cont__flat__wid_none(drawCircle_pw22.x, drawCircle_pw22.y, drawCircle_pw22.z + thickness,
                                                drawCircle_pw24.x, drawCircle_pw24.y, drawCircle_pw24.z + thickness);
            drawLine__ECS__cont__flat__wid_none(drawCircle_pw24.x, drawCircle_pw24.y, drawCircle_pw24.z + thickness,
                                                drawCircle_pw23.x, drawCircle_pw23.y, drawCircle_pw23.z + thickness);
            drawLine__ECS__cont__flat__wid_none(drawCircle_pw23.x, drawCircle_pw23.y, drawCircle_pw23.z + thickness,
                                                drawCircle_pw21.x, drawCircle_pw21.y, drawCircle_pw21.z + thickness);
            return;
        }


        // calculate segment values
        int    swglenmod = (int)Math.floor((swglen + DOUBLE_MIN_TOLERANCE) / seglen); // whole segment units
        double swglenrem = (swglen + DOUBLE_MIN_TOLERANCE) - (seglen * swglenmod); // size of any remainder

        double linbeg = begang + (swglenrem - delta) / 2.0; // actual line beg
        double linpos = linbeg;


        //
        // Draw arc segments
        //   --- Draw first segment (from beginning of line)
        //   --- Draw subsequent whole segments
        //   --- Draw last segment (to end of line)
        //
        double segbegwid, segendwid;


        // --- Draw first segment (from beginning of line)

        segendwid = (((linpos + delta - begang) / swgang) * (endwid - begwid)) + begwid;

        drawCircle_pw21.set((radius + begwid) * Math.cos(begang) + center.x,
                            (radius + begwid) * Math.sin(begang) + center.y,
                            center.z);
        drawCircle_pw22.set((radius - begwid) * Math.cos(begang) + center.x,
                            (radius - begwid) * Math.sin(begang) + center.y,
                            center.z);
        drawCircle_pw23.set((radius + segendwid) * Math.cos(linpos + delta) + center.x,
                            (radius + segendwid) * Math.sin(linpos + delta) + center.y,
                            center.z);
        drawCircle_pw24.set((radius - segendwid) * Math.cos(linpos + delta) + center.x,
                            (radius - segendwid) * Math.sin(linpos + delta) + center.y,
                            center.z);
        // base
        drawLine__ECS__cont__flat__wid_none(drawCircle_pw21, drawCircle_pw22);
        drawLine__ECS__cont__flat__wid_none(drawCircle_pw22, drawCircle_pw24);
//      drawLine__ECS__cont__flat__wid_none(drawCircle_pw24, drawCircle_pw23);
        drawLine__ECS__cont__flat__wid_none(drawCircle_pw23, drawCircle_pw21);
        // sides
        drawLine__ECS__cont__flat__wid_none(drawCircle_pw21.x, drawCircle_pw21.y, drawCircle_pw21.z,
                                            drawCircle_pw21.x, drawCircle_pw21.y, drawCircle_pw21.z + thickness);
        drawLine__ECS__cont__flat__wid_none(drawCircle_pw22.x, drawCircle_pw22.y, drawCircle_pw22.z,
                                            drawCircle_pw22.x, drawCircle_pw22.y, drawCircle_pw22.z + thickness);
//      drawLine__ECS__cont__flat__wid_none(drawCircle_pw23.x, drawCircle_pw23.y, drawCircle_pw23.z,
//                                          drawCircle_pw23.x, drawCircle_pw23.y, drawCircle_pw23.z + thickness);
//      drawLine__ECS__cont__flat__wid_none(drawCircle_pw24.x, drawCircle_pw24.y, drawCircle_pw24.z,
//                                          drawCircle_pw24.x, drawCircle_pw24.y, drawCircle_pw24.z + thickness);
        // top
        drawLine__ECS__cont__flat__wid_none(drawCircle_pw21.x, drawCircle_pw21.y, drawCircle_pw21.z + thickness,
                                            drawCircle_pw22.x, drawCircle_pw22.y, drawCircle_pw22.z + thickness);
        drawLine__ECS__cont__flat__wid_none(drawCircle_pw22.x, drawCircle_pw22.y, drawCircle_pw22.z + thickness,
                                            drawCircle_pw24.x, drawCircle_pw24.y, drawCircle_pw24.z + thickness);
//      drawLine__ECS__cont__flat__wid_none(drawCircle_pw24.x, drawCircle_pw24.y, drawCircle_pw24.z + thickness,
//                                          drawCircle_pw23.x, drawCircle_pw23.y, drawCircle_pw23.z + thickness);
        drawLine__ECS__cont__flat__wid_none(drawCircle_pw23.x, drawCircle_pw23.y, drawCircle_pw23.z + thickness,
                                            drawCircle_pw21.x, drawCircle_pw21.y, drawCircle_pw21.z + thickness);

        linpos += delta;


        // --- Draw subsequent whole segments

        // Increment along line in seglen units starting with second segment run
        for (int i = 1; i < swglenmod; i++)
        {   // Whole segment runs
            segbegwid = (((linpos - begang) / swgang) * (endwid - begwid)) + begwid;
            segendwid = (((linpos + delta - begang) / swgang) * (endwid - begwid)) + begwid;

            drawCircle_pw21.set((radius + segbegwid) * Math.cos(linpos) + center.x,
                                (radius + segbegwid) * Math.sin(linpos) + center.y,
                                center.z);
            drawCircle_pw22.set((radius - segbegwid) * Math.cos(linpos) + center.x,
                                (radius - segbegwid) * Math.sin(linpos) + center.y,
                                center.z);
            drawCircle_pw23.set((radius + segendwid) * Math.cos(linpos + delta) + center.x,
                                (radius + segendwid) * Math.sin(linpos + delta) + center.y,
                                center.z);
            drawCircle_pw24.set((radius - segendwid) * Math.cos(linpos + delta) + center.x,
                                (radius - segendwid) * Math.sin(linpos + delta) + center.y,
                                center.z);
            // base
            drawLine__ECS__cont__flat__wid_none(drawCircle_pw21, drawCircle_pw22);
            drawLine__ECS__cont__flat__wid_none(drawCircle_pw22, drawCircle_pw24);
//          drawLine__ECS__cont__flat__wid_none(drawCircle_pw24, drawCircle_pw23);
            drawLine__ECS__cont__flat__wid_none(drawCircle_pw23, drawCircle_pw21);
            // sides
            drawLine__ECS__cont__flat__wid_none(drawCircle_pw21.x, drawCircle_pw21.y, drawCircle_pw21.z,
                                                drawCircle_pw21.x, drawCircle_pw21.y, drawCircle_pw21.z + thickness);
            drawLine__ECS__cont__flat__wid_none(drawCircle_pw22.x, drawCircle_pw22.y, drawCircle_pw22.z,
                                                drawCircle_pw22.x, drawCircle_pw22.y, drawCircle_pw22.z + thickness);
//          drawLine__ECS__cont__flat__wid_none(drawCircle_pw23.x, drawCircle_pw23.y, drawCircle_pw23.z,
//                                              drawCircle_pw23.x, drawCircle_pw23.y, drawCircle_pw23.z + thickness);
//          drawLine__ECS__cont__flat__wid_none(drawCircle_pw24.x, drawCircle_pw24.y, drawCircle_pw24.z,
//                                              drawCircle_pw24.x, drawCircle_pw24.y, drawCircle_pw24.z + thickness);
            // top
            drawLine__ECS__cont__flat__wid_none(drawCircle_pw21.x, drawCircle_pw21.y, drawCircle_pw21.z + thickness,
                                                drawCircle_pw22.x, drawCircle_pw22.y, drawCircle_pw22.z + thickness);
            drawLine__ECS__cont__flat__wid_none(drawCircle_pw22.x, drawCircle_pw22.y, drawCircle_pw22.z + thickness,
                                                drawCircle_pw24.x, drawCircle_pw24.y, drawCircle_pw24.z + thickness);
//          drawLine__ECS__cont__flat__wid_none(drawCircle_pw24.x, drawCircle_pw24.y, drawCircle_pw24.z + thickness,
//                                              drawCircle_pw23.x, drawCircle_pw23.y, drawCircle_pw23.z + thickness);
            drawLine__ECS__cont__flat__wid_none(drawCircle_pw23.x, drawCircle_pw23.y, drawCircle_pw23.z + thickness,
                                                drawCircle_pw21.x, drawCircle_pw21.y, drawCircle_pw21.z + thickness);

            linpos += delta;
        } // end of whole segment runs


        // --- Draw last segment (to end of line)

        segbegwid = (((linpos - begang) / swgang) * (endwid - begwid)) + begwid;

        drawCircle_pw21.set((radius + segbegwid) * Math.cos(linpos) + center.x,
                            (radius + segbegwid) * Math.sin(linpos) + center.y,
                            center.z);
        drawCircle_pw22.set((radius - segbegwid) * Math.cos(linpos) + center.x,
                            (radius - segbegwid) * Math.sin(linpos) + center.y,
                            center.z);
        drawCircle_pw23.set((radius + endwid) * Math.cos(begang + swgang) + center.x,
                            (radius + endwid) * Math.sin(begang + swgang) + center.y,
                            center.z);
        drawCircle_pw24.set((radius - endwid) * Math.cos(begang + swgang) + center.x,
                            (radius - endwid) * Math.sin(begang + swgang) + center.y,
                            center.z);
        // base
        drawLine__ECS__cont__flat__wid_none(drawCircle_pw21, drawCircle_pw22);
        drawLine__ECS__cont__flat__wid_none(drawCircle_pw22, drawCircle_pw24);
        drawLine__ECS__cont__flat__wid_none(drawCircle_pw24, drawCircle_pw23);
        drawLine__ECS__cont__flat__wid_none(drawCircle_pw23, drawCircle_pw21);
        // sides
        drawLine__ECS__cont__flat__wid_none(drawCircle_pw21.x, drawCircle_pw21.y, drawCircle_pw21.z,
                                            drawCircle_pw21.x, drawCircle_pw21.y, drawCircle_pw21.z + thickness);
        drawLine__ECS__cont__flat__wid_none(drawCircle_pw22.x, drawCircle_pw22.y, drawCircle_pw22.z,
                                            drawCircle_pw22.x, drawCircle_pw22.y, drawCircle_pw22.z + thickness);
        drawLine__ECS__cont__flat__wid_none(drawCircle_pw23.x, drawCircle_pw23.y, drawCircle_pw23.z,
                                            drawCircle_pw23.x, drawCircle_pw23.y, drawCircle_pw23.z + thickness);
        drawLine__ECS__cont__flat__wid_none(drawCircle_pw24.x, drawCircle_pw24.y, drawCircle_pw24.z,
                                            drawCircle_pw24.x, drawCircle_pw24.y, drawCircle_pw24.z + thickness);
        // top
        drawLine__ECS__cont__flat__wid_none(drawCircle_pw21.x, drawCircle_pw21.y, drawCircle_pw21.z + thickness,
                                            drawCircle_pw22.x, drawCircle_pw22.y, drawCircle_pw22.z + thickness);
        drawLine__ECS__cont__flat__wid_none(drawCircle_pw22.x, drawCircle_pw22.y, drawCircle_pw22.z + thickness,
                                            drawCircle_pw24.x, drawCircle_pw24.y, drawCircle_pw24.z + thickness);
        drawLine__ECS__cont__flat__wid_none(drawCircle_pw24.x, drawCircle_pw24.y, drawCircle_pw24.z + thickness,
                                            drawCircle_pw23.x, drawCircle_pw23.y, drawCircle_pw23.z + thickness);
        drawLine__ECS__cont__flat__wid_none(drawCircle_pw23.x, drawCircle_pw23.y, drawCircle_pw23.z + thickness,
                                            drawCircle_pw21.x, drawCircle_pw21.y, drawCircle_pw21.z + thickness);
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // ECS complex circles
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    /**
     * Draw ECS complex circle.
     * @param center The circle center point.
     * @param radius The circle radius.
     * @param begang TODO
     * @param swgang TODO
     */
    public
    void drawCircle__ECS__ltyp__flat__wid_none(YxxfGfxPointW center,
                                               double radius,
                                               double begang, double swgang)
    {
        // Determine arc length
        double linlen;
        if (swgang < 0.0)
        { // CW
//          linlen = -swgang * radius * (((YxxfEntInsert)I_insert).scale.x * radius) +
//                                      (((YxxfEntInsert)I_insert).scale.y * radius) / 2.0;
            linlen = -swgang * ( (Math.abs(((YxxfEntInsert)I_insert).scale.x) * radius) +
                                 (Math.abs(((YxxfEntInsert)I_insert).scale.y) * radius) );
        }
        else
        { // CCW
//          linlen =  swgang * radius * (((YxxfEntInsert)I_insert).scale.x * radius) +
//                                      (((YxxfEntInsert)I_insert).scale.y * radius) / 2.0;
            linlen =  swgang * ( (Math.abs(((YxxfEntInsert)I_insert).scale.x) * radius) +
                                 (Math.abs(((YxxfEntInsert)I_insert).scale.y) * radius) );
        }

        // Determine length of pattern
        double patlen = E_ltype_patternlen * E_ltypescale;
////    if (radius > 200) {
////    System.out.println("drawCircle__ECS__ltyp__flat__wid_none|swgang=" + swgang + ",radius=" + radius);
////    System.out.println("                                     |scale.x=" + ((YxxfEntInsert)I_insert).scale.x + ",scale.y=" + ((YxxfEntInsert)I_insert).scale.y);
////    System.out.println("                                     |linlen=" + linlen + ",patlen=" + patlen);
////    }

        if ((linlen + DOUBLE_MIN_TOLERANCE) < patlen)
        {   // arc is too short to hold even one pattern,
            // draw continuous arc
            drawCircle__ECS__cont__flat__wid_none(center, radius, begang, swgang);
            return;
        }

        // calculate pattern values
        int    linlenmod = (int)Math.floor((linlen + DOUBLE_MIN_TOLERANCE) / patlen); // whole pattern units
        double linlenrem = (linlen + DOUBLE_MIN_TOLERANCE) - (patlen * linlenmod); // size of any remainder

        double dshlenone = E_ltype_lenlist[0] * E_ltypescale;

        double linbeg = (linlenrem - dshlenone) / 2.0; // actual line beg


        //
        // Draw pattern
        //   --- Draw first pattern (first dash from beginning of line)
        //   --- Draw subsequent whole patterns
        //   --- Draw last pattern (first dash only to end of line)
        //
        double linpos = linbeg;
        double seglen, segbeg, segend;
        double patpos;


        // --- Draw first pattern (first dash from beginning of line)

        seglen = E_ltype_lenlist[0] * E_ltypescale;

        segend = linpos + seglen;

        drawCircle__ECS__cont__flat__wid_none(center, radius,
                                              begang,
                                              (swgang * segend / linlen));

        // Increment
        patpos = seglen;


        // Draw rest of pattern
        for (int j = 1; j < E_ltype_dashlencount; j++)
        {
            seglen = E_ltype_lenlist[j] * E_ltypescale;

            if (seglen < 0.0)
            {   // Gap

                // Increment
                patpos += -seglen;
            }
            else
            {   // Draw

                segbeg = linpos + patpos;
                segend = segbeg + seglen;

                drawCircle__ECS__cont__flat__wid_none(center, radius,
                                                      begang + (swgang * segbeg / linlen),
                                                      (swgang * segend / linlen) - (swgang * segbeg / linlen));

                // Increment
                patpos += seglen;
            }
        }

        // Increment
        linpos += patlen;


        // --- Draw subsequent whole patterns

        // Increment along line in patlen units starting with second pattern run
        for (int i = 1; i < linlenmod; i++)
        {   // Whole pattern runs

            // Increment thru pattern
            patpos = 0;
            for (int j = 0; j < E_ltype_dashlencount; j++)
            {
                seglen = E_ltype_lenlist[j] * E_ltypescale;

                if (seglen < 0.0)
                {   // Gap

                    // Increment
                    patpos += -seglen;
                }
                else
                {   // Draw

                    segbeg = linpos + patpos;
                    segend = segbeg + seglen;

                    drawCircle__ECS__cont__flat__wid_none(center, radius,
                                                          begang + (swgang * segbeg / linlen),
                                                          (swgang * segend / linlen) - (swgang * segbeg / linlen));

                    // Increment
                    patpos += seglen;
                }
            }

            // Increment
            linpos += patlen;
        } // end of whole pattern runs


        // --- Draw last pattern (first dash only to end of line)

        drawCircle__ECS__cont__flat__wid_none(center, radius,
                                              begang + (swgang * linpos / linlen),
                                              swgang - (swgang * linpos / linlen));
    }


    /**
     * Draw ECS complex circle.
     * @param center The circle center point.
     * @param radius The circle radius.
     * @param begang TODO
     * @param swgang TODO
     * @param thickness The line thickness.
     */
    public
    void drawCircle__ECS__ltyp__thck__wid_none(YxxfGfxPointW center,
                                               double radius,
                                               double begang, double swgang,
                                               double thickness)
    {
        // Determine arc length
        double linlen;
        if (swgang < 0.0)
        { // CW
//          linlen = -swgang * radius * (((YxxfEntInsert)I_insert).scale.x * radius) +
//                                      (((YxxfEntInsert)I_insert).scale.y * radius) / 2.0;
            linlen = -swgang * ( (Math.abs(((YxxfEntInsert)I_insert).scale.x) * radius) +
                                 (Math.abs(((YxxfEntInsert)I_insert).scale.y) * radius) );
        }
        else
        { // CCW
//          linlen =  swgang * radius * (((YxxfEntInsert)I_insert).scale.x * radius) +
//                                      (((YxxfEntInsert)I_insert).scale.y * radius) / 2.0;
            linlen =  swgang * ( (Math.abs(((YxxfEntInsert)I_insert).scale.x) * radius) +
                                 (Math.abs(((YxxfEntInsert)I_insert).scale.y) * radius) );
        }

        // Determine length of pattern
        double patlen = E_ltype_patternlen * E_ltypescale;
////    if (radius > 200) {
////    System.out.println("drawCircle__ECS__ltyp__thck__wid_none|swgang=" + swgang + ",radius=" + radius);
////    System.out.println("                                     |scale.x=" + ((YxxfEntInsert)I_insert).scale.x + ",scale.y=" + ((YxxfEntInsert)I_insert).scale.y);
////    System.out.println("                                     |linlen=" + linlen + ",patlen=" + patlen);
////    }

        if ((linlen + DOUBLE_MIN_TOLERANCE) < patlen)
        {   // arc is too short to hold even one pattern,
            // draw continuous arc
            drawCircle__ECS__cont__thck__wid_none(center, radius, begang, swgang, thickness);
            return;
        }

        // calculate pattern values
        int    linlenmod = (int)Math.floor((linlen + DOUBLE_MIN_TOLERANCE) / patlen); // whole pattern units
        double linlenrem = (linlen + DOUBLE_MIN_TOLERANCE) - (patlen * linlenmod); // size of any remainder

        double dshlenone = E_ltype_lenlist[0] * E_ltypescale;

        double linbeg = (linlenrem - dshlenone) / 2.0; // actual line beg


        //
        // Draw pattern
        //   --- Draw first pattern (first dash from beginning of line)
        //   --- Draw subsequent whole patterns
        //   --- Draw last pattern (first dash only to end of line)
        //
        double linpos = linbeg;
        double seglen, segbeg, segend;
        double patpos;


        // --- Draw first pattern (first dash from beginning of line)

        seglen = E_ltype_lenlist[0] * E_ltypescale;

        segend = linpos + seglen;

        drawCircle__ECS__cont__thck__wid_none(center, radius,
                                              begang,
                                              (swgang * segend / linlen),
                                              thickness);

        // Increment
        patpos = seglen;


        // Draw rest of pattern
        for (int j = 1; j < E_ltype_dashlencount; j++)
        {
            seglen = E_ltype_lenlist[j] * E_ltypescale;

            if (seglen < 0.0)
            {   // Gap

                // Increment
                patpos += -seglen;
            }
            else
            {   // Draw

                segbeg = linpos + patpos;
                segend = segbeg + seglen;

                drawCircle__ECS__cont__thck__wid_none(center, radius,
                                                      begang + (swgang * segbeg / linlen),
                                                      (swgang * segend / linlen) - (swgang * segbeg / linlen),
                                                      thickness);

                // Increment
                patpos += seglen;
            }
        }

        // Increment
        linpos += patlen;


        // --- Draw subsequent whole patterns

        // Increment along line in patlen units starting with second pattern run
        for (int i = 1; i < linlenmod; i++)
        {   // Whole pattern runs

            // Increment thru pattern
            patpos = 0;
            for (int j = 0; j < E_ltype_dashlencount; j++)
            {
                seglen = E_ltype_lenlist[j] * E_ltypescale;

                if (seglen < 0.0)
                {   // Gap

                    // Increment
                    patpos += -seglen;
                }
                else
                {   // Draw

                    segbeg = linpos + patpos;
                    segend = segbeg + seglen;

                    drawCircle__ECS__cont__thck__wid_none(center, radius,
                                                          begang + (swgang * segbeg / linlen),
                                                          (swgang * segend / linlen) - (swgang * segbeg / linlen),
                                                          thickness);

                    // Increment
                    patpos += seglen;
                }
            }

            // Increment
            linpos += patlen;
        } // end of whole pattern runs


        // --- Draw last pattern (first dash only to end of line)

        drawCircle__ECS__cont__thck__wid_none(center, radius,
                                              begang + (swgang * linpos / linlen),
                                              swgang - (swgang * linpos / linlen),
                                              thickness);
    }


    /**
     * Draw ECS complex circle.
     * @param center The circle center point.
     * @param radius The circle radius.
     * @param begang TODO
     * @param swgang TODO
     * @param begwid The beginning line width.
     * @param endwid The ending line width.
     */
    public
    void drawCircle__ECS__ltyp__flat__wid_fill(YxxfGfxPointW center,
                                               double radius,
                                               double begang, double swgang,
                                               double begwid, double endwid)
    {
        // Limit widths to center
        if (begwid > (radius + radius)) begwid = radius + radius;
        if (endwid > (radius + radius)) endwid = radius + radius;

        // Determine arc length
        double linlen;
        if (swgang < 0.0)
        { // CW
//          linlen = -swgang * radius * (((YxxfEntInsert)I_insert).scale.x * radius) +
//                                      (((YxxfEntInsert)I_insert).scale.y * radius) / 2.0;
            linlen = -swgang * ( (Math.abs(((YxxfEntInsert)I_insert).scale.x) * radius) +
                                 (Math.abs(((YxxfEntInsert)I_insert).scale.y) * radius) );
        }
        else
        { // CCW
//          linlen =  swgang * radius * (((YxxfEntInsert)I_insert).scale.x * radius) +
//                                      (((YxxfEntInsert)I_insert).scale.y * radius) / 2.0;
            linlen =  swgang * ( (Math.abs(((YxxfEntInsert)I_insert).scale.x) * radius) +
                                 (Math.abs(((YxxfEntInsert)I_insert).scale.y) * radius) );
        }

        // Determine length of pattern
        double patlen = E_ltype_patternlen * E_ltypescale;
////    if (radius > 200) {
////    System.out.println("drawCircle__ECS__ltyp__flat__wid_fill|swgang=" + swgang + ",radius=" + radius);
////    System.out.println("                                     |scale.x=" + ((YxxfEntInsert)I_insert).scale.x + ",scale.y=" + ((YxxfEntInsert)I_insert).scale.y);
////    System.out.println("                                     |linlen=" + linlen + ",patlen=" + patlen);
////    }

        if ((linlen + DOUBLE_MIN_TOLERANCE) < patlen)
        {   // arc is too short to hold even one pattern,
            // draw continuous arc
            drawCircle__ECS__cont__flat__wid_fill(center, radius, begang, swgang, begwid, endwid);
            return;
        }


        // calculate pattern values
        int    linlenmod = (int)Math.floor((linlen + DOUBLE_MIN_TOLERANCE) / patlen); // whole pattern units
        double linlenrem = (linlen + DOUBLE_MIN_TOLERANCE) - (patlen * linlenmod); // size of any remainder

        double dshlenone = E_ltype_lenlist[0] * E_ltypescale;

        double linbeg = (linlenrem - dshlenone) / 2.0; // actual line beg
////    if (radius > 200) {
////    System.out.println("                                     |linlenmod=" + linlenmod + ",linlenrem=" + linlenrem);
////    }


        //
        // Draw pattern
        //   --- Draw first pattern (first dash from beginning of line)
        //   --- Draw subsequent whole patterns
        //   --- Draw last pattern (first dash only to end of line)
        //
        double linpos = linbeg;
        double seglen, segbeg, segend;
        double segbegwid, segendwid;
        double patpos;


        // --- Draw first pattern (first dash from beginning of line)

        seglen = E_ltype_lenlist[0] * E_ltypescale;
        segend = linpos + seglen;

        segendwid = ((segend / linlen) * (endwid - begwid)) + begwid;

        drawCircle__ECS__cont__flat__wid_fill(center, radius,
                                              begang,
                                              swgang * (segend / linlen),
                                              begwid, segendwid);

        // Increment
        patpos = seglen;


        // Draw rest of pattern
        for (int j = 1; j < E_ltype_dashlencount; j++)
        {
            seglen = E_ltype_lenlist[j] * E_ltypescale;

            if (seglen < 0.0)
            {   // Gap

                // Increment
                patpos += -seglen;
            }
            else
            {   // Draw

                segbeg = linpos + patpos;
                segend = segbeg + seglen;

                segbegwid = ((segbeg / linlen) * (endwid - begwid)) + begwid;
                segendwid = ((segend / linlen) * (endwid - begwid)) + begwid;

                drawCircle__ECS__cont__flat__wid_fill(center, radius,
                                                      begang + (swgang * segbeg / linlen),
                                                      (swgang * segend / linlen) - (swgang * segbeg / linlen),
                                                      segbegwid, segendwid);

                // Increment
                patpos += seglen;
            }
        }

        // Increment
        linpos += patlen;


        // --- Draw subsequent whole patterns

        // Increment along line in patlen units starting with second pattern run
        for (int i = 1; i < linlenmod; i++)
        {   // Whole pattern runs

            // Increment thru pattern
            patpos = 0;
            for (int j = 0; j < E_ltype_dashlencount; j++)
            {
                seglen = E_ltype_lenlist[j] * E_ltypescale;

                if (seglen < 0.0)
                {   // Gap

                    // Increment
                    patpos += -seglen;
                }
                else
                {   // Draw

                    segbeg = linpos + patpos;
                    segend = segbeg + seglen;

                    segbegwid = ((segbeg / linlen) * (endwid - begwid)) + begwid;
                    segendwid = ((segend / linlen) * (endwid - begwid)) + begwid;

                    drawCircle__ECS__cont__flat__wid_fill(center, radius,
                                                          begang + (swgang * segbeg / linlen),
                                                          (swgang * segend / linlen) - (swgang * segbeg / linlen),
                                                          segbegwid, segendwid);

                    // Increment
                    patpos += seglen;
                }
            }

            // Increment
            linpos += patlen;
        } // end of whole pattern runs


        // --- Draw last pattern (first dash only to end of line)

        segbegwid = ((linpos / linlen) * (endwid - begwid)) + begwid;

        drawCircle__ECS__cont__flat__wid_fill(center, radius,
                                              begang + (swgang * linpos / linlen),
                                              swgang - (swgang * linpos / linlen),
                                              segbegwid, endwid);
    }


    /**
     * Draw ECS complex circle.
     * @param center The circle center point.
     * @param radius The circle radius.
     * @param begang TODO
     * @param swgang TODO
     * @param begwid The beginning line width.
     * @param endwid The ending line width.
     */
    public
    void drawCircle__ECS__ltyp__flat__wid_wire(YxxfGfxPointW center,
                                               double radius,
                                               double begang, double swgang,
                                               double begwid, double endwid)
    {
        // Limit widths to center
        if (begwid > (radius + radius)) begwid = radius + radius;
        if (endwid > (radius + radius)) endwid = radius + radius;

        // Determine arc length
        double linlen;
        if (swgang < 0.0)
        { // CW
//          linlen = -swgang * radius * (((YxxfEntInsert)I_insert).scale.x * radius) +
//                                      (((YxxfEntInsert)I_insert).scale.y * radius) / 2.0;
            linlen = -swgang * ( (Math.abs(((YxxfEntInsert)I_insert).scale.x) * radius) +
                                 (Math.abs(((YxxfEntInsert)I_insert).scale.y) * radius) );
        }
        else
        { // CCW
//          linlen =  swgang * radius * (((YxxfEntInsert)I_insert).scale.x * radius) +
//                                      (((YxxfEntInsert)I_insert).scale.y * radius) / 2.0;
            linlen =  swgang * ( (Math.abs(((YxxfEntInsert)I_insert).scale.x) * radius) +
                                 (Math.abs(((YxxfEntInsert)I_insert).scale.y) * radius) );
        }

        // Determine length of pattern
        double patlen = E_ltype_patternlen * E_ltypescale;
////    if (radius > 200) {
////    System.out.println("drawCircle__ECS__ltyp__flat__wid_wire|swgang=" + swgang + ",radius=" + radius);
////    System.out.println("                                     |scale.x=" + ((YxxfEntInsert)I_insert).scale.x + ",scale.y=" + ((YxxfEntInsert)I_insert).scale.y);
////    System.out.println("                                     |linlen=" + linlen + ",patlen=" + patlen);
////    }

        if ((linlen + DOUBLE_MIN_TOLERANCE) < patlen)
        {   // arc is too short to hold even one pattern,
            // draw continuous arc
            drawCircle__ECS__cont__flat__wid_wire(center, radius, begang, swgang, begwid, endwid);
            return;
        }

        // calculate pattern values
        int    linlenmod = (int)Math.floor((linlen + DOUBLE_MIN_TOLERANCE) / patlen); // whole pattern units
        double linlenrem = (linlen + DOUBLE_MIN_TOLERANCE) - (patlen * linlenmod); // size of any remainder

        double dshlenone = E_ltype_lenlist[0] * E_ltypescale;

        double linbeg = (linlenrem - dshlenone) / 2.0; // actual line beg


        //
        // Draw pattern
        //   --- Draw first pattern (first dash from beginning of line)
        //   --- Draw subsequent whole patterns
        //   --- Draw last pattern (first dash only to end of line)
        //
        double linpos = linbeg;
        double seglen, segbeg, segend;
        double segbegwid, segendwid;
        double patpos;


        // --- Draw first pattern (first dash from beginning of line)

        seglen = E_ltype_lenlist[0] * E_ltypescale;
        segend = linpos + seglen;

        segendwid = ((segend / linlen) * (endwid - begwid)) + begwid;

        drawCircle__ECS__cont__flat__wid_wire(center, radius,
                                              begang,
                                              (swgang * segend / linlen),
                                              begwid, segendwid);

        // Increment
        patpos = seglen;


        // Draw rest of pattern
        for (int j = 1; j < E_ltype_dashlencount; j++)
        {
            seglen = E_ltype_lenlist[j] * E_ltypescale;

            if (seglen < 0.0)
            {   // Gap

                // Increment
                patpos += -seglen;
            }
            else
            {   // Draw

                segbeg = linpos + patpos;
                segend = segbeg + seglen;

                segbegwid = ((segbeg / linlen) * (endwid - begwid)) + begwid;
                segendwid = ((segend / linlen) * (endwid - begwid)) + begwid;

                drawCircle__ECS__cont__flat__wid_wire(center, radius,
                                                      begang + (swgang * segbeg / linlen),
                                                      (swgang * segend / linlen) - (swgang * segbeg / linlen),
                                                      segbegwid, segendwid);

                // Increment
                patpos += seglen;
            }
        }

        // Increment
        linpos += patlen;


        // --- Draw subsequent whole patterns

        // Increment along line in patlen units starting with second pattern run
        for (int i = 1; i < linlenmod; i++)
        {   // Whole pattern runs

            // Increment thru pattern
            patpos = 0;
            for (int j = 0; j < E_ltype_dashlencount; j++)
            {
                seglen = E_ltype_lenlist[j] * E_ltypescale;

                if (seglen < 0.0)
                {   // Gap

                    // Increment
                    patpos += -seglen;
                }
                else
                {   // Draw

                    segbeg = linpos + patpos;
                    segend = segbeg + seglen;

                    segbegwid = ((segbeg / linlen) * (endwid - begwid)) + begwid;
                    segendwid = ((segend / linlen) * (endwid - begwid)) + begwid;

                    drawCircle__ECS__cont__flat__wid_wire(center, radius,
                                                          begang + (swgang * segbeg / linlen),
                                                          (swgang * segend / linlen) - (swgang * segbeg / linlen),
                                                          segbegwid, segendwid);

                    // Increment
                    patpos += seglen;
                }
            }

            // Increment
            linpos += patlen;
        } // end of whole pattern runs


        // --- Draw last pattern (first dash only to end of line)

        segbegwid = ((linpos / linlen) * (endwid - begwid)) + begwid;

        drawCircle__ECS__cont__flat__wid_wire(center, radius,
                                              begang + (swgang * linpos / linlen),
                                              swgang - (swgang * linpos / linlen),
                                              begwid, endwid);
    }


    /**
     * Draw ECS complex circle.
     * @param center The circle center point.
     * @param radius The circle radius.
     * @param begang TODO
     * @param swgang TODO
     * @param thickness The line thickness. TODO
     * @param begwid The beginning line width.
     * @param endwid The ending line width.
     */
    public
    void drawCircle__ECS__ltyp__thck__wid_wire(YxxfGfxPointW center,
                                               double radius,
                                               double begang, double swgang,
                                               double thickness,
                                               double begwid, double endwid)
    {
        // Limit widths to center
        if (begwid > (radius + radius)) begwid = radius + radius;
        if (endwid > (radius + radius)) endwid = radius + radius;

        // Determine arc length
        double linlen;
        if (swgang < 0.0)
        { // CW
//          linlen = -swgang * radius * (((YxxfEntInsert)I_insert).scale.x * radius) +
//                                      (((YxxfEntInsert)I_insert).scale.y * radius) / 2.0;
            linlen = -swgang * ( (Math.abs(((YxxfEntInsert)I_insert).scale.x) * radius) +
                                 (Math.abs(((YxxfEntInsert)I_insert).scale.y) * radius) );
        }
        else
        { // CCW
//          linlen =  swgang * radius * (((YxxfEntInsert)I_insert).scale.x * radius) +
//                                      (((YxxfEntInsert)I_insert).scale.y * radius) / 2.0;
            linlen =  swgang * ( (Math.abs(((YxxfEntInsert)I_insert).scale.x) * radius) +
                                 (Math.abs(((YxxfEntInsert)I_insert).scale.y) * radius) );
        }

        // Determine length of pattern
        double patlen = E_ltype_patternlen * E_ltypescale;
////    if (radius > 200) {
////    System.out.println("drawCircle__ECS__ltyp__thck__wid_wire|swgang=" + swgang + ",radius=" + radius);
////    System.out.println("                                     |scale.x=" + ((YxxfEntInsert)I_insert).scale.x + ",scale.y=" + ((YxxfEntInsert)I_insert).scale.y);
////    System.out.println("                                     |linlen=" + linlen + ",patlen=" + patlen);
////    }

        if ((linlen + DOUBLE_MIN_TOLERANCE) < patlen)
        {   // arc is too short to hold even one pattern,
            // draw continuous arc
            drawCircle__ECS__cont__thck__wid_wire(center, radius, begang, swgang,
                                                  thickness,
                                                  begwid, endwid);
            return;
        }

        // calculate pattern values
        int    linlenmod = (int)Math.floor((linlen + DOUBLE_MIN_TOLERANCE) / patlen); // whole pattern units
        double linlenrem = (linlen + DOUBLE_MIN_TOLERANCE) - (patlen * linlenmod); // size of any remainder

        double dshlenone = E_ltype_lenlist[0] * E_ltypescale;

        double linbeg = (linlenrem - dshlenone) / 2.0; // actual line beg


        //
        // Draw pattern
        //   --- Draw first pattern (first dash from beginning of line)
        //   --- Draw subsequent whole patterns
        //   --- Draw last pattern (first dash only to end of line)
        //
        double linpos = linbeg;
        double seglen, segbeg, segend;
        double segbegwid, segendwid;
        double patpos;


        // --- Draw first pattern (first dash from beginning of line)

        seglen = E_ltype_lenlist[0] * E_ltypescale;
        segend = linpos + seglen;

        segendwid = ((segend / linlen) * (endwid - begwid)) + begwid;

        drawCircle__ECS__cont__thck__wid_wire(center, radius,
                                              begang,
                                              (swgang * segend / linlen),
                                              thickness,
                                              begwid, segendwid);

        // Increment
        patpos = seglen;


        // Draw rest of pattern
        for (int j = 1; j < E_ltype_dashlencount; j++)
        {
            seglen = E_ltype_lenlist[j] * E_ltypescale;

            if (seglen < 0.0)
            {   // Gap

                // Increment
                patpos += -seglen;
            }
            else
            {   // Draw

                segbeg = linpos + patpos;
                segend = segbeg + seglen;

                segbegwid = ((segbeg / linlen) * (endwid - begwid)) + begwid;
                segendwid = ((segend / linlen) * (endwid - begwid)) + begwid;

                drawCircle__ECS__cont__thck__wid_wire(center, radius,
                                                      begang + (swgang * segbeg / linlen),
                                                      (swgang * segend / linlen) - (swgang * segbeg / linlen),
                                                      thickness,
                                                      segbegwid, segendwid);

                // Increment
                patpos += seglen;
            }
        }

        // Increment
        linpos += patlen;


        // --- Draw subsequent whole patterns

        // Increment along line in patlen units starting with second pattern run
        for (int i = 1; i < linlenmod; i++)
        {   // Whole pattern runs

            // Increment thru pattern
            patpos = 0;
            for (int j = 0; j < E_ltype_dashlencount; j++)
            {
                seglen = E_ltype_lenlist[j] * E_ltypescale;

                if (seglen < 0.0)
                {   // Gap

                    // Increment
                    patpos += -seglen;
                }
                else
                {   // Draw

                    segbeg = linpos + patpos;
                    segend = segbeg + seglen;

                    segbegwid = ((segbeg / linlen) * (endwid - begwid)) + begwid;
                    segendwid = ((segend / linlen) * (endwid - begwid)) + begwid;

                    drawCircle__ECS__cont__thck__wid_wire(center, radius,
                                                          begang + (swgang * segbeg / linlen),
                                                          (swgang * segend / linlen) - (swgang * segbeg / linlen),
                                                          thickness,
                                                          segbegwid, segendwid);

                    // Increment
                    patpos += seglen;
                }
            }

            // Increment
            linpos += patlen;
        } // end of whole pattern runs


        // --- Draw last pattern (first dash only to end of line)

        segbegwid = ((linpos / linlen) * (endwid - begwid)) + begwid;

        drawCircle__ECS__cont__thck__wid_wire(center, radius,
                                              begang + (swgang * linpos / linlen),
                                              swgang - (swgang * linpos / linlen),
                                              thickness,
                                              segbegwid, endwid);
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++




    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // P O L Y G O N
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    /**
     * Polygon point.
     */
    private
    YxxfGfxPointS               drawPolygon_ps1 = new YxxfGfxPointS();
    /**
     * Polygon point.
     */
    private
    YxxfGfxPointS               drawPolygon_ps2 = new YxxfGfxPointS();
    /**
     * Polygon point.
     */
    private
    YxxfGfxPointS               drawPolygon_ps3 = new YxxfGfxPointS();
    /**
     * Polygon point.
     */
    private
    YxxfGfxPointS               drawPolygon_ps4 = new YxxfGfxPointS();

    /**
     * Array of Polygon x coordinates.
     */
    private
    int                         drawPolygon_xPoints[] = { 0, 0, 0, 0 };
    /**
     * Array of Polygon y coordinates.
     */
    private
    int                         drawPolygon_yPoints[] = { 0, 0, 0, 0 };

    /**
     * Draw filled polygon - 4 points.
     * Use global M_entity.
     * @param p1 Polygon point.
     * @param p2 Polygon point.
     * @param p3 Polygon point.
     * @param p4 Polygon point.
     */
    public
    void fillPolygon_ECS(YxxfGfxPointW p1, YxxfGfxPointW p2, YxxfGfxPointW p3, YxxfGfxPointW p4)
    {
        if (gc_doing_flag_calc_extents)
        {
            ECS_to_SCS_calc_extents(p1, drawPolygon_ps1);
            ECS_to_SCS_calc_extents(p2, drawPolygon_ps2);
            ECS_to_SCS_calc_extents(p3, drawPolygon_ps3);
            ECS_to_SCS_calc_extents(p4, drawPolygon_ps4);
        }
        else
        {
            ECS_to_SCS(p1, drawPolygon_ps1);
            ECS_to_SCS(p2, drawPolygon_ps2);
            ECS_to_SCS(p3, drawPolygon_ps3);
            ECS_to_SCS(p4, drawPolygon_ps4);
        }

//      System.out.println("fillPolygon_ECS:==========================================="); // DebugEval

        // Check for degenerate polygons

/* ===
        if ((drawPolygon_ps1.x == drawPolygon_ps2.x && drawPolygon_ps1.y == drawPolygon_ps2.y) &&
            (drawPolygon_ps1.x == drawPolygon_ps3.x && drawPolygon_ps1.y == drawPolygon_ps3.y) &&
            (drawPolygon_ps1.x == drawPolygon_ps4.x && drawPolygon_ps1.y == drawPolygon_ps4.y))
        {   // single point
            System.out.println("fillPolygon_ECS:single point");
            if (renderingjgcflag)
                jgc.drawLine(drawPolygon_ps1.x, drawPolygon_ps1.y, drawPolygon_ps1.x, drawPolygon_ps1.y);
        }
        else

        if ((drawPolygon_ps1.x == drawPolygon_ps2.x && drawPolygon_ps1.y == drawPolygon_ps2.y) &&
            (drawPolygon_ps1.x != drawPolygon_ps4.x || drawPolygon_ps1.y != drawPolygon_ps4.y) &&
            (drawPolygon_ps3.x == drawPolygon_ps4.x && drawPolygon_ps3.y == drawPolygon_ps4.y))
        {   // single line
            System.out.println("fillPolygon_ECS:single line 1-4");
            if (renderingjgcflag)
                jgc.drawLine(drawPolygon_ps1.x, drawPolygon_ps1.y, drawPolygon_ps4.x, drawPolygon_ps4.y);
        }
        else

        if ((drawPolygon_ps1.x == drawPolygon_ps4.x && drawPolygon_ps1.y == drawPolygon_ps4.y) &&
            (drawPolygon_ps1.x != drawPolygon_ps3.x || drawPolygon_ps1.y != drawPolygon_ps3.y) &&
            (drawPolygon_ps2.x == drawPolygon_ps3.x && drawPolygon_ps2.y == drawPolygon_ps3.y))
        {   // single line
            System.out.println("fillPolygon_ECS:single line 1-3");
            if (renderingjgcflag)
                jgc.drawLine(drawPolygon_ps1.x, drawPolygon_ps1.y, drawPolygon_ps3.x, drawPolygon_ps3.y);
        }
        else

        if ((drawPolygon_ps1.x == drawPolygon_ps3.x && drawPolygon_ps1.y == drawPolygon_ps3.y) &&
            (drawPolygon_ps1.x != drawPolygon_ps3.x || drawPolygon_ps1.y != drawPolygon_ps3.y) &&
            (drawPolygon_ps2.x == drawPolygon_ps4.x && drawPolygon_ps2.y == drawPolygon_ps4.y))
        {   // single line
            System.out.println("fillPolygon_ECS:single line 2-4");
            if (renderingjgcflag)
                jgc.drawLine(drawPolygon_ps2.x, drawPolygon_ps2.y, drawPolygon_ps4.x, drawPolygon_ps4.y);
        }
        else

        if ((drawPolygon_ps2.x == drawPolygon_ps4.x && drawPolygon_ps1.y == drawPolygon_ps4.y) &&
            (drawPolygon_ps2.x != drawPolygon_ps3.x || drawPolygon_ps2.y != drawPolygon_ps3.y) &&
            (drawPolygon_ps1.x == drawPolygon_ps3.x && drawPolygon_ps1.y == drawPolygon_ps3.y))
        {   // single line
            System.out.println("fillPolygon_ECS:single line 2-3");
            if (renderingjgcflag)
               jgc.drawLine(drawPolygon_ps2.x, drawPolygon_ps2.y, drawPolygon_ps3.x, drawPolygon_ps3.y);
        }
        else
=== */

        if (drawPolygon_ps1.x == drawPolygon_ps2.x &&
            drawPolygon_ps1.x == drawPolygon_ps3.x &&
            drawPolygon_ps1.x == drawPolygon_ps4.x)
        {   // single line special x
//          System.out.println("fillPolygon_ECS:single line special x");
            if (renderingjgcflag)
                jgc.drawLine(drawPolygon_ps1.x,
                             Math.min(Math.min(drawPolygon_ps1.y, drawPolygon_ps2.y), Math.min(drawPolygon_ps3.y, drawPolygon_ps4.y)),
                             drawPolygon_ps1.x,
                             Math.max(Math.max(drawPolygon_ps1.y, drawPolygon_ps2.y), Math.max(drawPolygon_ps3.y, drawPolygon_ps4.y)));
        }
        else

        if (drawPolygon_ps1.y == drawPolygon_ps2.y &&
            drawPolygon_ps1.y == drawPolygon_ps3.y &&
            drawPolygon_ps1.y == drawPolygon_ps4.y)
        {   // single line special y
//          System.out.println("fillPolygon_ECS:single line special y");
            if (renderingjgcflag)
                jgc.drawLine(Math.min(Math.min(drawPolygon_ps1.x, drawPolygon_ps2.x), Math.min(drawPolygon_ps3.x, drawPolygon_ps4.x)),
                             drawPolygon_ps1.y,
                             Math.max(Math.max(drawPolygon_ps1.x, drawPolygon_ps2.x), Math.max(drawPolygon_ps3.x, drawPolygon_ps4.x)),
                             drawPolygon_ps1.y);
        }
        else

        {
//          System.out.println("fillPolygon_ECS:full poly");
            drawPolygon_xPoints[0] = drawPolygon_ps1.x;
            drawPolygon_xPoints[1] = drawPolygon_ps3.x;
            drawPolygon_xPoints[2] = drawPolygon_ps4.x;
            drawPolygon_xPoints[3] = drawPolygon_ps2.x;
            drawPolygon_yPoints[0] = drawPolygon_ps1.y;
            drawPolygon_yPoints[1] = drawPolygon_ps3.y;
            drawPolygon_yPoints[2] = drawPolygon_ps4.y;
            drawPolygon_yPoints[3] = drawPolygon_ps2.y;

            if (renderingjgcflag)
            {
                jgc.fillPolygon(drawPolygon_xPoints, drawPolygon_yPoints, 4);
//              jgc.drawPolygon(drawPolygon_xPoints, drawPolygon_yPoints, 4);
//              jgc.drawLine(drawPolygon_ps1.x, drawPolygon_ps1.y, drawPolygon_ps2.x, drawPolygon_ps2.y);
//              jgc.drawLine(drawPolygon_ps2.x, drawPolygon_ps2.y, drawPolygon_ps4.x, drawPolygon_ps4.y);
//              jgc.drawLine(drawPolygon_ps4.x, drawPolygon_ps4.y, drawPolygon_ps3.x, drawPolygon_ps3.y);
//              jgc.drawLine(drawPolygon_ps3.x, drawPolygon_ps3.y, drawPolygon_ps1.x, drawPolygon_ps1.y);
            }
        }
//      System.out.println("fillPolygon_ECS:ps1=" + drawPolygon_ps1);
//      System.out.println("fillPolygon_ECS:ps2=" + drawPolygon_ps2);
//      System.out.println("fillPolygon_ECS:ps3=" + drawPolygon_ps3);
//      System.out.println("fillPolygon_ECS:ps4=" + drawPolygon_ps4);
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++




    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // S O L I D
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    /**
     * Solid point.
     */
    private
    YxxfGfxPointW               drawSolid_pw1   = new YxxfGfxPointW();
    /**
     * Solid point.
     */
    private
    YxxfGfxPointW               drawSolid_pw2   = new YxxfGfxPointW();
    /**
     * Solid point.
     */
    private
    YxxfGfxPointW               drawSolid_pw3   = new YxxfGfxPointW();
    /**
     * Solid point.
     */
    private
    YxxfGfxPointW               drawSolid_pw4   = new YxxfGfxPointW();
    /**
     * Solid point.
     */
    private
    YxxfGfxPointW               drawSolid_pw5   = new YxxfGfxPointW();
    /**
     * Solid point.
     */
    private
    YxxfGfxPointW               drawSolid_pw6   = new YxxfGfxPointW();
    /**
     * Solid point.
     */
    private
    YxxfGfxPointW               drawSolid_pw7   = new YxxfGfxPointW();
    /**
     * Solid point.
     */
    private
    YxxfGfxPointW               drawSolid_pw8   = new YxxfGfxPointW();

    /**
     * Solid point.
     */
    private
    YxxfGfxPointS               drawSolid_ps1   = new YxxfGfxPointS();
    /**
     * Solid point.
     */
    private
    YxxfGfxPointS               drawSolid_ps2   = new YxxfGfxPointS();
    /**
     * Solid point.
     */
    private
    YxxfGfxPointS               drawSolid_ps3   = new YxxfGfxPointS();
    /**
     * Solid point.
     */
    private
    YxxfGfxPointS               drawSolid_ps4   = new YxxfGfxPointS();

    /**
     * Array of solid x points.
     */
    private
    int                         drawSolid_xPoints[] = { 0, 0, 0, 0 };
    /**
     * Array of solid y points.
     */
    private
    int                         drawSolid_yPoints[] = { 0, 0, 0, 0 };

    /**
     * Draw solid - 3 points.
     * Use global M_entity.
     * @param p1 Point 1.
     * @param p2 Point 2.
     * @param p3 Point 3.
     * @param thickness TODO
     */
    public
    void drawSolid_ECS(YxxfGfxPointW p1, YxxfGfxPointW p2, YxxfGfxPointW p3,
                       double thickness)
    {
        if (check_xtrudir_parallel_to_view())
        {   // Direct face on view
            if (D.secHeader.fillmode != 0)
            {   // Solid fill - 2D
                if (gc_doing_flag_calc_extents)
                {
                    ECS_to_SCS_calc_extents(p1, drawSolid_ps1);
                    ECS_to_SCS_calc_extents(p2, drawSolid_ps2);
                    ECS_to_SCS_calc_extents(p3, drawSolid_ps3);
                }
                else
                {
                    ECS_to_SCS(p1, drawSolid_ps1);
                    ECS_to_SCS(p2, drawSolid_ps2);
                    ECS_to_SCS(p3, drawSolid_ps3);
                }

                drawSolid_xPoints[0] = drawSolid_ps1.x;
                drawSolid_xPoints[1] = drawSolid_ps2.x;
                drawSolid_xPoints[2] = drawSolid_ps3.x;
                drawSolid_yPoints[0] = drawSolid_ps1.y;
                drawSolid_yPoints[1] = drawSolid_ps2.y;
                drawSolid_yPoints[2] = drawSolid_ps3.y;

                if (renderingjgcflag)
                    jgc.fillPolygon(drawSolid_xPoints, drawSolid_yPoints, 3);
            }
            else
            {   // Wire frame - 2D
                drawLine__ECS__cont__flat__wid_none(p1, p2);
                drawLine__ECS__cont__flat__wid_none(p2, p3);
                drawLine__ECS__cont__flat__wid_none(p3, p1);
            }
        }
        else
        {   // Angle view
            if (thickness != 0.0)
            {   // Wire frame - 3D
                // base
                drawLine__ECS__cont__flat__wid_none(p1, p2);
                drawLine__ECS__cont__flat__wid_none(p2, p3);
                drawLine__ECS__cont__flat__wid_none(p3, p1);

                // sides
                drawLine__ECS__cont__flat__wid_none(p1.x, p1.y, p1.z,
                                                    p1.x, p1.y, p1.z + thickness);
                drawLine__ECS__cont__flat__wid_none(p2.x, p2.y, p2.z,
                                                    p2.x, p2.y, p2.z + thickness);
                drawLine__ECS__cont__flat__wid_none(p3.x, p3.y, p3.z,
                                                    p3.x, p3.y, p3.z + thickness);

                // top
                drawLine__ECS__cont__flat__wid_none(p1.x, p1.y, p1.z + thickness,
                                                    p2.x, p2.y, p2.z + thickness);
                drawLine__ECS__cont__flat__wid_none(p2.x, p2.y, p2.z + thickness,
                                                    p3.x, p3.y, p3.z + thickness);
                drawLine__ECS__cont__flat__wid_none(p3.x, p3.y, p3.z + thickness,
                                                    p1.x, p1.y, p1.z + thickness);
            }
            else
            {   // Wire frame - 2D
                drawLine__ECS__cont__flat__wid_none(p1, p2);
                drawLine__ECS__cont__flat__wid_none(p2, p3);
                drawLine__ECS__cont__flat__wid_none(p3, p1);
            }
        }
    }


    /**
     * Draw solid - 4 points.
     * Use global M_entity.
     * @param p1 Point 1.
     * @param p2 Point 2.
     * @param p3 Point 3.
     * @param p4 Point 4.
     * @param thickness TODO
     */
    public
    void drawSolid_ECS(YxxfGfxPointW p1, YxxfGfxPointW p2, YxxfGfxPointW p3, YxxfGfxPointW p4,
                       double thickness)
    {
        if (check_xtrudir_parallel_to_view())
        {   // Direct face on view
            if (D.secHeader.fillmode != 0)
            {   // Solid fill - 2D
                if (gc_doing_flag_calc_extents)
                 {
                     ECS_to_SCS_calc_extents(p1, drawSolid_ps1);
                     ECS_to_SCS_calc_extents(p2, drawSolid_ps2);
                     ECS_to_SCS_calc_extents(p3, drawSolid_ps3);
                     ECS_to_SCS_calc_extents(p4, drawSolid_ps4);
                 }
                 else
                 {
                     ECS_to_SCS(p1, drawSolid_ps1);
                     ECS_to_SCS(p2, drawSolid_ps2);
                     ECS_to_SCS(p3, drawSolid_ps3);
                     ECS_to_SCS(p4, drawSolid_ps4);
                 }

                drawSolid_xPoints[0] = drawSolid_ps1.x;
                drawSolid_xPoints[1] = drawSolid_ps3.x;
                drawSolid_xPoints[2] = drawSolid_ps4.x;
                drawSolid_xPoints[3] = drawSolid_ps2.x;
                drawSolid_yPoints[0] = drawSolid_ps1.y;
                drawSolid_yPoints[1] = drawSolid_ps3.y;
                drawSolid_yPoints[2] = drawSolid_ps4.y;
                drawSolid_yPoints[3] = drawSolid_ps2.y;

                if (renderingjgcflag)
                    jgc.fillPolygon(drawSolid_xPoints, drawSolid_yPoints, 4);
            }
            else
            {   // Wire frame - 2D
                drawLine__ECS__cont__flat__wid_none(p1, p2);
                drawLine__ECS__cont__flat__wid_none(p2, p4);
                drawLine__ECS__cont__flat__wid_none(p4, p3);
                drawLine__ECS__cont__flat__wid_none(p3, p1);
            }
        }
        else
        {   // Angle view
            if (thickness != 0.0)
            {   // Wire frame - 3D
                // base
                drawLine__ECS__cont__flat__wid_none(p1, p2);
                drawLine__ECS__cont__flat__wid_none(p2, p4);
                drawLine__ECS__cont__flat__wid_none(p4, p3);
                drawLine__ECS__cont__flat__wid_none(p3, p1);

                // sides
                drawLine__ECS__cont__flat__wid_none(p1.x, p1.y, p1.z,
                                                    p1.x, p1.y, p1.z + thickness);
                drawLine__ECS__cont__flat__wid_none(p2.x, p2.y, p2.z,
                                                    p2.x, p2.y, p2.z + thickness);
                drawLine__ECS__cont__flat__wid_none(p3.x, p3.y, p3.z,
                                                    p3.x, p3.y, p3.z + thickness);
                drawLine__ECS__cont__flat__wid_none(p4.x, p4.y, p4.z,
                                                    p4.x, p4.y, p4.z + thickness);

                // top
                drawLine__ECS__cont__flat__wid_none(p1.x, p1.y, p1.z + thickness,
                                                    p2.x, p2.y, p2.z + thickness);
                drawLine__ECS__cont__flat__wid_none(p2.x, p2.y, p2.z + thickness,
                                                    p4.x, p4.y, p4.z + thickness);
                drawLine__ECS__cont__flat__wid_none(p4.x, p4.y, p4.z + thickness,
                                                    p3.x, p3.y, p3.z + thickness);
                drawLine__ECS__cont__flat__wid_none(p3.x, p3.y, p3.z + thickness,
                                                    p1.x, p1.y, p1.z + thickness);
            }
            else
            {   // Wire frame - 2D
                drawLine__ECS__cont__flat__wid_none(p1, p2);
                drawLine__ECS__cont__flat__wid_none(p2, p4);
                drawLine__ECS__cont__flat__wid_none(p4, p3);
                drawLine__ECS__cont__flat__wid_none(p3, p1);
            }
        }
    }


    /**
     * Draw solid - 3 points.
     * @param p1 Point 1.
     * @param p2 Point 2.
     * @param p3 Point 3.
     * @param thickness TODO
     * @param arg_M_entity Use argument for M_entity.
     */
    public
    void drawSolid_ECS(YxxfGfxPointW p1, YxxfGfxPointW p2, YxxfGfxPointW p3,
                       double thickness, YxxfGfxMatrix arg_M_entity)
    {
        arg_M_entity.mtxTransformPoint(drawSolid_pw1.set(p1));
        arg_M_entity.mtxTransformPoint(drawSolid_pw2.set(p2));
        arg_M_entity.mtxTransformPoint(drawSolid_pw3.set(p3));

        if (check_xtrudir_parallel_to_view(arg_M_entity))
        {   // Direct face on view
            if (D.secHeader.fillmode != 0)
            {   // Solid fill - 2D
                if (gc_doing_flag_calc_extents)
                {
                    MCS_to_SCS_calc_extents(drawSolid_pw1, drawSolid_ps1);
                    MCS_to_SCS_calc_extents(drawSolid_pw2, drawSolid_ps2);
                    MCS_to_SCS_calc_extents(drawSolid_pw3, drawSolid_ps3);
                }
                else
                {
                    MCS_to_SCS(drawSolid_pw1, drawSolid_ps1);
                    MCS_to_SCS(drawSolid_pw2, drawSolid_ps2);
                    MCS_to_SCS(drawSolid_pw3, drawSolid_ps3);
                }

                drawSolid_xPoints[0] = drawSolid_ps1.x;
                drawSolid_xPoints[1] = drawSolid_ps2.x;
                drawSolid_xPoints[2] = drawSolid_ps3.x;
                drawSolid_yPoints[0] = drawSolid_ps1.y;
                drawSolid_yPoints[1] = drawSolid_ps2.y;
                drawSolid_yPoints[2] = drawSolid_ps3.y;

                if (renderingjgcflag)
                    jgc.fillPolygon(drawSolid_xPoints, drawSolid_yPoints, 3);
            }
            else
            {   // Wire frame - 2D
                drawLine__MCS__cont__flat__wid_none(drawSolid_pw1, drawSolid_pw2);
                drawLine__MCS__cont__flat__wid_none(drawSolid_pw2, drawSolid_pw3);
                drawLine__MCS__cont__flat__wid_none(drawSolid_pw3, drawSolid_pw1);
            }
        }
        else
        {   // Angle view
            if (thickness != 0.0)
            {   // Wire frame - 3D
                arg_M_entity.mtxTransformPoint(drawSolid_pw5.set(p1.x, p1.y, p1.z + thickness));
                arg_M_entity.mtxTransformPoint(drawSolid_pw6.set(p2.x, p2.y, p2.z + thickness));
                arg_M_entity.mtxTransformPoint(drawSolid_pw7.set(p3.x, p3.y, p3.z + thickness));

                // base
                drawLine__MCS__cont__flat__wid_none(drawSolid_pw1, drawSolid_pw2);
                drawLine__MCS__cont__flat__wid_none(drawSolid_pw2, drawSolid_pw3);
                drawLine__MCS__cont__flat__wid_none(drawSolid_pw3, drawSolid_pw1);

                // sides
                drawLine__MCS__cont__flat__wid_none(drawSolid_pw1, drawSolid_pw5);
                drawLine__MCS__cont__flat__wid_none(drawSolid_pw2, drawSolid_pw6);
                drawLine__MCS__cont__flat__wid_none(drawSolid_pw3, drawSolid_pw7);

                // top
                drawLine__MCS__cont__flat__wid_none(drawSolid_pw5, drawSolid_pw6);
                drawLine__MCS__cont__flat__wid_none(drawSolid_pw6, drawSolid_pw7);
                drawLine__MCS__cont__flat__wid_none(drawSolid_pw7, drawSolid_pw5);
            }
            else
            {   // Wire frame - 2D
                drawLine__MCS__cont__flat__wid_none(drawSolid_pw1, drawSolid_pw2);
                drawLine__MCS__cont__flat__wid_none(drawSolid_pw2, drawSolid_pw3);
                drawLine__MCS__cont__flat__wid_none(drawSolid_pw3, drawSolid_pw1);
            }
        }
    }


    /**
     * Draw solid - 4 points.
     * @param p1 Point 1.
     * @param p2 Point 2.
     * @param p3 Point 3.
     * @param p4 Point 4.
     * @param arg_M_entity Use argument for M_entity.
     */
    public
    void drawSolid_ECS(YxxfGfxPointW p1, YxxfGfxPointW p2, YxxfGfxPointW p3, YxxfGfxPointW p4,
                       double thickness, YxxfGfxMatrix arg_M_entity)
    {
        arg_M_entity.mtxTransformPoint(drawSolid_pw1.set(p1));
        arg_M_entity.mtxTransformPoint(drawSolid_pw2.set(p2));
        arg_M_entity.mtxTransformPoint(drawSolid_pw3.set(p3));
        arg_M_entity.mtxTransformPoint(drawSolid_pw4.set(p4));

        if (check_xtrudir_parallel_to_view(arg_M_entity))
        {   // Direct face on view
            if (D.secHeader.fillmode != 0)
            {   // Solid fill - 2D
                if (gc_doing_flag_calc_extents)
                {
                    MCS_to_SCS_calc_extents(drawSolid_pw1, drawSolid_ps1);
                    MCS_to_SCS_calc_extents(drawSolid_pw2, drawSolid_ps2);
                    MCS_to_SCS_calc_extents(drawSolid_pw3, drawSolid_ps3);
                    MCS_to_SCS_calc_extents(drawSolid_pw4, drawSolid_ps4);
                }
                else
                {
                    MCS_to_SCS(drawSolid_pw1, drawSolid_ps1);
                    MCS_to_SCS(drawSolid_pw2, drawSolid_ps2);
                    MCS_to_SCS(drawSolid_pw3, drawSolid_ps3);
                    MCS_to_SCS(drawSolid_pw4, drawSolid_ps4);
                }

                drawSolid_xPoints[0] = drawSolid_ps1.x;
                drawSolid_xPoints[1] = drawSolid_ps3.x;
                drawSolid_xPoints[2] = drawSolid_ps4.x;
                drawSolid_xPoints[3] = drawSolid_ps2.x;
                drawSolid_yPoints[0] = drawSolid_ps1.y;
                drawSolid_yPoints[1] = drawSolid_ps3.y;
                drawSolid_yPoints[2] = drawSolid_ps4.y;
                drawSolid_yPoints[3] = drawSolid_ps2.y;

                if (renderingjgcflag)
                   jgc.fillPolygon(drawSolid_xPoints, drawSolid_yPoints, 4);
            }
            else
            {   // Wire frame - 2D
                drawLine__MCS__cont__flat__wid_none(drawSolid_pw1, drawSolid_pw2);
                drawLine__MCS__cont__flat__wid_none(drawSolid_pw2, drawSolid_pw4);
                drawLine__MCS__cont__flat__wid_none(drawSolid_pw4, drawSolid_pw3);
                drawLine__MCS__cont__flat__wid_none(drawSolid_pw3, drawSolid_pw1);
            }
        }
        else
        {   // Angle view
            if (thickness != 0.0)
            {   // Wire frame - 3D
                arg_M_entity.mtxTransformPoint(drawSolid_pw5.set(p1.x, p1.y, p1.z + thickness));
                arg_M_entity.mtxTransformPoint(drawSolid_pw6.set(p2.x, p2.y, p2.z + thickness));
                arg_M_entity.mtxTransformPoint(drawSolid_pw7.set(p3.x, p3.y, p3.z + thickness));
                arg_M_entity.mtxTransformPoint(drawSolid_pw8.set(p4.x, p4.y, p4.z + thickness));

                // base
                drawLine__MCS__cont__flat__wid_none(drawSolid_pw1, drawSolid_pw2);
                drawLine__MCS__cont__flat__wid_none(drawSolid_pw2, drawSolid_pw4);
                drawLine__MCS__cont__flat__wid_none(drawSolid_pw4, drawSolid_pw3);
                drawLine__MCS__cont__flat__wid_none(drawSolid_pw3, drawSolid_pw1);

                // sides
                drawLine__MCS__cont__flat__wid_none(drawSolid_pw1, drawSolid_pw5);
                drawLine__MCS__cont__flat__wid_none(drawSolid_pw2, drawSolid_pw6);
                drawLine__MCS__cont__flat__wid_none(drawSolid_pw3, drawSolid_pw7);
                drawLine__MCS__cont__flat__wid_none(drawSolid_pw4, drawSolid_pw8);

                // top
                drawLine__MCS__cont__flat__wid_none(drawSolid_pw5, drawSolid_pw6);
                drawLine__MCS__cont__flat__wid_none(drawSolid_pw6, drawSolid_pw8);
                drawLine__MCS__cont__flat__wid_none(drawSolid_pw8, drawSolid_pw7);
                drawLine__MCS__cont__flat__wid_none(drawSolid_pw7, drawSolid_pw5);
            }
            else
            {   // Wire frame - 2D
                drawLine__MCS__cont__flat__wid_none(drawSolid_pw1, drawSolid_pw2);
                drawLine__MCS__cont__flat__wid_none(drawSolid_pw2, drawSolid_pw4);
                drawLine__MCS__cont__flat__wid_none(drawSolid_pw4, drawSolid_pw3);
                drawLine__MCS__cont__flat__wid_none(drawSolid_pw3, drawSolid_pw1);
            }
        }
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++




    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // B O X
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    /**
     * Box point.
     */
    private
    YxxfGfxPointW               drawBox_xyz     = new YxxfGfxPointW();
    /**
     * Box point.
     */
    private
    YxxfGfxPointW               drawBox_Xyz     = new YxxfGfxPointW();
    /**
     * Box point.
     */
    private
    YxxfGfxPointW               drawBox_XYz     = new YxxfGfxPointW();
    /**
     * Box point.
     */
    private
    YxxfGfxPointW               drawBox_xYz     = new YxxfGfxPointW();
    /**
     * Box point.
     */
    private
    YxxfGfxPointW               drawBox_xyZ     = new YxxfGfxPointW();
    /**
     * Box point.
     */
    private
    YxxfGfxPointW               drawBox_XyZ     = new YxxfGfxPointW();
    /**
     * Box point.
     */
    private
    YxxfGfxPointW               drawBox_XYZ     = new YxxfGfxPointW();
    /**
     * Box point.
     */
    private
    YxxfGfxPointW               drawBox_xYZ     = new YxxfGfxPointW();

    /**
     * Box point.
     */
    private
    YxxfGfxPointW               drawBox_pmin    = new YxxfGfxPointW();
    /**
     * Box point.
     */
    private
    YxxfGfxPointW               drawBox_pmax    = new YxxfGfxPointW();

    /**
     * Draw box.
     * Use global M_entity.
     * @param pmin Minimum point.
     * @param pmax Maximum point.
     */
    public
    void drawBox_ECS(YxxfGfxPointW pmin, YxxfGfxPointW pmax)
    {
        // set corner points
        drawBox_xyz.set(pmin.x, pmin.y, pmin.z);
        drawBox_Xyz.set(pmax.x, pmin.y, pmin.z);
        drawBox_XYz.set(pmax.x, pmax.y, pmin.z);
        drawBox_xYz.set(pmin.x, pmax.y, pmin.z);

        drawBox_xyZ.set(pmin.x, pmin.y, pmax.z);
        drawBox_XyZ.set(pmax.x, pmin.y, pmax.z);
        drawBox_XYZ.set(pmax.x, pmax.y, pmax.z);
        drawBox_xYZ.set(pmin.x, pmax.y, pmax.z);

        // Draw box
        drawLine__ECS__cont__flat__wid_none(drawBox_xyz, drawBox_Xyz);
        drawLine__ECS__cont__flat__wid_none(drawBox_Xyz, drawBox_XYz);
        drawLine__ECS__cont__flat__wid_none(drawBox_XYz, drawBox_xYz);
        drawLine__ECS__cont__flat__wid_none(drawBox_xYz, drawBox_xyz);

        drawLine__ECS__cont__flat__wid_none(drawBox_xyz, drawBox_xyZ);
        drawLine__ECS__cont__flat__wid_none(drawBox_Xyz, drawBox_XyZ);
        drawLine__ECS__cont__flat__wid_none(drawBox_XYz, drawBox_XYZ);
        drawLine__ECS__cont__flat__wid_none(drawBox_xYz, drawBox_xYZ);

        drawLine__ECS__cont__flat__wid_none(drawBox_xyZ, drawBox_XyZ);
        drawLine__ECS__cont__flat__wid_none(drawBox_XyZ, drawBox_XYZ);
        drawLine__ECS__cont__flat__wid_none(drawBox_XYZ, drawBox_xYZ);
        drawLine__ECS__cont__flat__wid_none(drawBox_xYZ, drawBox_xyZ);
    }


    /**
     * Draw box.
     * @param pmin Minimum point.
     * @param pmax Maximum point.
     * @param arg_M_entity Use argument for M_entity.
     */
    public
    void drawBox_ECS(YxxfGfxPointW pmin, YxxfGfxPointW pmax,
                     YxxfGfxMatrix arg_M_entity)
    {
        arg_M_entity.mtxTransformPoint(drawBox_pmin.set(pmin));
        arg_M_entity.mtxTransformPoint(drawBox_pmax.set(pmax));

        // set corner points
        drawBox_xyz.set(drawBox_pmin.x, drawBox_pmin.y, drawBox_pmin.z);
        drawBox_Xyz.set(drawBox_pmax.x, drawBox_pmin.y, drawBox_pmin.z);
        drawBox_XYz.set(drawBox_pmax.x, drawBox_pmax.y, drawBox_pmin.z);
        drawBox_xYz.set(drawBox_pmin.x, drawBox_pmax.y, drawBox_pmin.z);

        drawBox_xyZ.set(drawBox_pmin.x, drawBox_pmin.y, drawBox_pmax.z);
        drawBox_XyZ.set(drawBox_pmax.x, drawBox_pmin.y, drawBox_pmax.z);
        drawBox_XYZ.set(drawBox_pmax.x, drawBox_pmax.y, drawBox_pmax.z);
        drawBox_xYZ.set(drawBox_pmin.x, drawBox_pmax.y, drawBox_pmax.z);

        // Draw box
        drawLine__MCS__cont__flat__wid_none(drawBox_xyz, drawBox_Xyz);
        drawLine__MCS__cont__flat__wid_none(drawBox_Xyz, drawBox_XYz);
        drawLine__MCS__cont__flat__wid_none(drawBox_XYz, drawBox_xYz);
        drawLine__MCS__cont__flat__wid_none(drawBox_xYz, drawBox_xyz);

        drawLine__MCS__cont__flat__wid_none(drawBox_xyz, drawBox_xyZ);
        drawLine__MCS__cont__flat__wid_none(drawBox_Xyz, drawBox_XyZ);
        drawLine__MCS__cont__flat__wid_none(drawBox_XYz, drawBox_XYZ);
        drawLine__MCS__cont__flat__wid_none(drawBox_xYz, drawBox_xYZ);

        drawLine__MCS__cont__flat__wid_none(drawBox_xyZ, drawBox_XyZ);
        drawLine__MCS__cont__flat__wid_none(drawBox_XyZ, drawBox_XYZ);
        drawLine__MCS__cont__flat__wid_none(drawBox_XYZ, drawBox_xYZ);
        drawLine__MCS__cont__flat__wid_none(drawBox_xYZ, drawBox_xyZ);
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++




    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // T E X T
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    /**
     * Text drawer.
     */
    private
    YxxfGfxText         drawText_textdrawer         = new YxxfGfxText();

    /**
     * Shape char drawer.
     */
    private
    YxxfGfxShapeChar    drawText_shapechardrawer    = new YxxfGfxShapeChar();

    /**
     * Work shape char.
     */
    private
    YxxfShapeChar       drawText_lookupshapechar    = new YxxfShapeChar();

    /**
     * Curr work point.
     */
    private
    YxxfGfxPointW       drawText_currpnt            = new YxxfGfxPointW();
    /**
     * Next work point.
     */
    private
    YxxfGfxPointW       drawText_nextpnt            = new YxxfGfxPointW();


    /**
     * Draw text.
     * @param text The text.
     * @param shape TODO
     * @param textvert TODO
     */
    public
    void drawText_ECS(String text, YxxfShape shape, boolean textvert)
    {
        drawText_shapechardrawer.setShape(shape);
        drawText_shapechardrawer.setTextvert(textvert && shape.getShapeDesc_vert_mode_ok());

        drawText_currpnt.set(0, 0, 0);
        drawText_nextpnt.set(0, 0, 0);

        drawText_textdrawer.draw(this, text,
                                 shape, drawText_shapechardrawer,
                                 drawText_lookupshapechar, drawText_currpnt, drawText_nextpnt);
    }


    /**
     * Draw ECS string.
     * @param s1 The String.
     * @param p1 The starting point. TODO
     */
    public
    void drawString_ECS(String s1, YxxfGfxPointW p1)
    {
        if (gc_doing_flag_calc_extents)
        {   // ToDo - need better extents calc
            ECS_to_SCS_calc_extents(p1, ps1);
        }
        else
        {
            ECS_to_SCS(p1, ps1);
        }
        if (renderingjgcflag)
            jgc.drawString(s1, ps1.x, ps1.y);
    }


    /**
     * Draw MCS string.
     * @param s1 The String.
     * @param p1 The starting point. TODO
     */
    public
    void drawString_MCS(String s1, YxxfGfxPointW p1)
    {
        if (gc_doing_flag_calc_extents)
        {   // ToDo - need better extents calc
            MCS_to_SCS_calc_extents(p1, ps1);
        }
        else
        {
            MCS_to_SCS(p1, ps1);
        }
        if (renderingjgcflag)
            jgc.drawString(s1, ps1.x, ps1.y);
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++




    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // P O I N T S
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    /**
     * Draw ECS cross.
     * @param p1 The center point. TODO
     */
    public
    void drawCross_ECS(YxxfGfxPointW p1)
    {
        if (gc_doing_flag_calc_extents)
        {   // ToDo - need better extents calc
            ECS_to_SCS_calc_extents(p1, ps0);
        }
        else
        {
            ECS_to_SCS(p1, ps0);
        }

        ps1.set(ps0.x - 2, ps0.y);
        ps2.set(ps0.x + 2, ps0.y);
        ps3.set(ps0.x, ps0.y - 2);
        ps4.set(ps0.x, ps0.y + 2);

        if (renderingjgcflag)
        {
            jgc.drawLine(ps1.x, ps1.y, ps2.x, ps2.y);
            jgc.drawLine(ps3.x, ps3.y, ps4.x, ps4.y);
        }
    }


    /**
     * Draw MCS cross.
     * @param p1 The center point. TODO
     */
    public
    void drawCross_MCS(YxxfGfxPointW p1)
    {
        if (gc_doing_flag_calc_extents)
        {   // ToDo - need better extents calc
            MCS_to_SCS_calc_extents(p1, ps0);
        }
        else
        {
            MCS_to_SCS(p1, ps0);
        }

        ps1.set(ps0.x - 4, ps0.y);
        ps2.set(ps0.x + 4, ps0.y);
        ps3.set(ps0.x, ps0.y - 4);
        ps4.set(ps0.x, ps0.y + 4);

        if (renderingjgcflag)
        {
            jgc.drawLine(ps1.x, ps1.y, ps2.x, ps2.y);
            jgc.drawLine(ps3.x, ps3.y, ps4.x, ps4.y);
        }
    }


    /**
     * Draw VCS cross.
     * @param p1 The center point. TODO
     */
    public
    void drawCross_VCS(YxxfGfxPointW p1)
    {
        VCS_to_SCS(p1, ps0);

        ps1.set(ps0.x - 4, ps0.y);
        ps2.set(ps0.x + 4, ps0.y);
        ps3.set(ps0.x, ps0.y - 4);
        ps4.set(ps0.x, ps0.y + 4);

        if (renderingjgcflag)
        {
            jgc.drawLine(ps1.x, ps1.y, ps2.x, ps2.y);
            jgc.drawLine(ps3.x, ps3.y, ps4.x, ps4.y);
        }
    }


    /**
     * Draw DCS cross.
     * @param p1 The center point. TODO
     */
    public
    void drawCross_DCS(YxxfGfxPointW p1)
    {
        DCS_to_SCS(p1, ps0);

        ps1.set(ps0.x - 2, ps0.y);
        ps2.set(ps0.x + 2, ps0.y);
        ps3.set(ps0.x, ps0.y - 2);
        ps4.set(ps0.x, ps0.y + 2);

        if (renderingjgcflag)
        {
            jgc.drawLine(ps1.x, ps1.y, ps2.x, ps2.y);
            jgc.drawLine(ps3.x, ps3.y, ps4.x, ps4.y);
        }
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++




    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // Draw display borders
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    /**
     * TODO
     */
    public
    void drawDspminmax_vport()
    {
        // Draw box around window drawing area
        setEntity_acolor(12); // kind of reddish
        if (renderingjgcflag)
        {
            jgc.drawLine(0             , dspwin.height-1, dspwin.width-1, dspwin.height-1);
            jgc.drawLine(dspwin.width-1, dspwin.height-1, dspwin.width-1, 0              );
            jgc.drawLine(dspwin.width-1, 0              , 0             , 0              );
            jgc.drawLine(0             , 0              , 0             , dspwin.height-1);
        }

/* ===
        // Draw cross at view center point
        setEntity_acolor(12); // kind of reddish
        drawCross_DCS(new YxxfGfxPointW(vport.vcpx, vport.vcpy, 0));

        // Draw cross at view target point
        setEntity_acolor(YxxfGfxPalette.ACI_BLUE);
//      drawCross_VCS(vport.vtgt);
        drawCross_VCS(YxxfGfxPointW.W0);

        // Draw box around view drawing area
        setEntity_acolor(YxxfGfxPalette.ACI_CYAN);
        DCS_to_SCS(new YxxfGfxPointW(- (vwidth  / 2.0),
                                     + (vheight / 2.0), 0), ps0);
        DCS_to_SCS(new YxxfGfxPointW(+ (vwidth  / 2.0),
                                     + (vheight / 2.0), 0), ps1);
        DCS_to_SCS(new YxxfGfxPointW(+ (vwidth  / 2.0),
                                     - (vheight / 2.0), 0), ps2);
        DCS_to_SCS(new YxxfGfxPointW(- (vwidth  / 2.0),
                                     - (vheight / 2.0), 0), ps3);
        if (renderingjgcflag)
        {
            jgc.drawLine(ps0.x + 0, ps0.y + 1, ps1.x - 1, ps1.y + 1); // top
            jgc.drawLine(ps1.x - 1, ps1.y + 1, ps2.x - 1, ps2.y - 1); // right
            jgc.drawLine(ps2.x - 1, ps2.y - 1, ps3.x + 0, ps3.y - 1); // bottom
            jgc.drawLine(ps3.x + 0, ps3.y - 1, ps0.x + 0, ps0.y + 1); // left
        }
==== */
    }

    /**
     * TODO
     */
    public
    void drawDspminmax_viewport()
    {
        // Draw box around window drawing area
        setEntity_acolor(12); // kind of reddish
        if (renderingjgcflag)
        {
            jgc.drawLine(0             , dspwin.height-1, dspwin.width-1, dspwin.height-1);
            jgc.drawLine(dspwin.width-1, dspwin.height-1, dspwin.width-1, 0              );
            jgc.drawLine(dspwin.width-1, 0              , 0             , 0              );
            jgc.drawLine(0             , 0              , 0             , dspwin.height-1);
        }

/* ===
        // Draw cross at view center point
        setEntity_acolor(12); // kind of reddish
        drawCross_DCS(new YxxfGfxPointW(viewport.vpcp.x, viewport.vpcp.y, 0));

        // Draw cross at view target point
        setEntity_acolor(YxxfGfxPalette.ACI_BLUE);
//      drawCross_VCS(vport.vtgt);
        drawCross_VCS(YxxfGfxPointW.W0);

        // Draw box around view drawing area
        setEntity_acolor(YxxfGfxPalette.ACI_CYAN);
        DCS_to_SCS(new YxxfGfxPointW(- (vwidth  / 2.0),
                                     + (vheight / 2.0), 0), ps0);
        DCS_to_SCS(new YxxfGfxPointW(+ (vwidth  / 2.0),
                                     + (vheight / 2.0), 0), ps1);
        DCS_to_SCS(new YxxfGfxPointW(+ (vwidth  / 2.0),
                                     - (vheight / 2.0), 0), ps2);
        DCS_to_SCS(new YxxfGfxPointW(- (vwidth  / 2.0),
                                     - (vheight / 2.0), 0), ps3);
        if (renderingjgcflag)
        {
            jgc.drawLine(ps0.x + 0, ps0.y + 1, ps1.x - 1, ps1.y + 1); // top
            jgc.drawLine(ps1.x - 1, ps1.y + 1, ps2.x - 1, ps2.y - 1); // right
            jgc.drawLine(ps2.x - 1, ps2.y - 1, ps3.x + 0, ps3.y - 1); // bottom
            jgc.drawLine(ps3.x + 0, ps3.y - 1, ps0.x + 0, ps0.y + 1); // left
        }
==== */
    }


    /**
     * Draw a border - static utility method - uses no GC values.
     * Draws the border outside the passed location and size.
     * @param arg_jgc The Java Graphics object.
     * @param arg_borderwidth Border width.
     * @param arg_color The color.
     * @param arg_x The border inner base location - upper left x.
     * @param arg_y The border inner base location - upper left y.
     * @param arg_width The border inner base dimension - width.
     * @param arg_height The border inner base dimension - height.
     */
    public static
    void drawBorder(Graphics arg_jgc,
                    int arg_borderwidth,
                    Color arg_color,
                    int arg_x, int arg_y, int arg_width, int arg_height)
    {
        arg_jgc.setColor(arg_color);

        if (arg_borderwidth <= 0)
            return;

        if (arg_borderwidth == 1)
        {
            arg_jgc.drawLine(arg_x - 1            , arg_y - 1             ,
                             arg_x + arg_width    , arg_y - 1             ); // top
            arg_jgc.drawLine(arg_x - 1            , arg_y - 1             ,
                             arg_x - 1            , arg_y + arg_height    ); // left
            arg_jgc.drawLine(arg_x - 1            , arg_y + arg_height    ,
                             arg_x + arg_width    , arg_y + arg_height    ); // bottom
            arg_jgc.drawLine(arg_x + arg_width    , arg_y + arg_height    ,
                             arg_x + arg_width    , arg_y - 1             ); // right
        }
        else


        if (arg_borderwidth == 2)
        {
            arg_jgc.drawLine(arg_x - 1            , arg_y - 1             ,
                             arg_x + arg_width    , arg_y - 1             ); // top
            arg_jgc.drawLine(arg_x - 1            , arg_y - 1             ,
                             arg_x - 1            , arg_y + arg_height    ); // left
            arg_jgc.drawLine(arg_x - 1            , arg_y + arg_height    ,
                             arg_x + arg_width    , arg_y + arg_height    ); // bottom
            arg_jgc.drawLine(arg_x + arg_width    , arg_y + arg_height    ,
                             arg_x + arg_width    , arg_y - 1             ); // right
    
            arg_jgc.drawLine(arg_x - 2            , arg_y - 2             ,
                             arg_x + arg_width + 1, arg_y - 2             ); // top
            arg_jgc.drawLine(arg_x - 2            , arg_y - 2             ,
                             arg_x - 2            , arg_y + arg_height + 1); // left
            arg_jgc.drawLine(arg_x - 2            , arg_y + arg_height + 1,
                             arg_x + arg_width + 1, arg_y + arg_height + 1); // bottom
            arg_jgc.drawLine(arg_x + arg_width + 1, arg_y + arg_height + 1,
                             arg_x + arg_width + 1, arg_y - 2             ); // right
        }
        else

        if (arg_borderwidth == 3)
        {
            arg_jgc.drawLine(arg_x - 1            , arg_y - 1             ,
                             arg_x + arg_width    , arg_y - 1             ); // top
            arg_jgc.drawLine(arg_x - 1            , arg_y - 1             ,
                             arg_x - 1            , arg_y + arg_height    ); // left
            arg_jgc.drawLine(arg_x - 1            , arg_y + arg_height    ,
                             arg_x + arg_width    , arg_y + arg_height    ); // bottom
            arg_jgc.drawLine(arg_x + arg_width    , arg_y + arg_height    ,
                             arg_x + arg_width    , arg_y - 1             ); // right
    
            arg_jgc.drawLine(arg_x - 2            , arg_y - 2             ,
                             arg_x + arg_width + 1, arg_y - 2             ); // top
            arg_jgc.drawLine(arg_x - 2            , arg_y - 2             ,
                             arg_x - 2            , arg_y + arg_height + 1); // left
            arg_jgc.drawLine(arg_x - 2            , arg_y + arg_height + 1,
                             arg_x + arg_width + 1, arg_y + arg_height + 1); // bottom
            arg_jgc.drawLine(arg_x + arg_width + 1, arg_y + arg_height + 1,
                             arg_x + arg_width + 1, arg_y - 2             ); // right
    
            arg_jgc.drawLine(arg_x - 3            , arg_y - 3             ,
                             arg_x + arg_width + 2, arg_y - 3             ); // top
            arg_jgc.drawLine(arg_x - 3            , arg_y - 3             ,
                             arg_x - 3            , arg_y + arg_height + 2); // left
            arg_jgc.drawLine(arg_x - 3            , arg_y + arg_height + 2,
                             arg_x + arg_width + 2, arg_y + arg_height + 2); // bottom
            arg_jgc.drawLine(arg_x + arg_width + 2, arg_y + arg_height + 2,
                             arg_x + arg_width + 2, arg_y - 3             ); // right
        }
        else

        {   // width is 4 or greater - use filled rects
            // top
            arg_jgc.fillRect(arg_x - arg_borderwidth,
                             arg_y - arg_borderwidth,
                             arg_borderwidth + arg_width + arg_borderwidth,
                             arg_borderwidth);

            // left
            arg_jgc.fillRect(arg_x - arg_borderwidth,
                             arg_y,
                             arg_borderwidth,
                             arg_height);

            // bottom
            arg_jgc.fillRect(arg_x - arg_borderwidth,
                             arg_y + arg_height,
                             arg_borderwidth + arg_width + arg_borderwidth,
                             arg_borderwidth);

            // right
            arg_jgc.fillRect(arg_x + arg_width,
                             arg_y,
                             arg_borderwidth,
                             arg_height);
        }
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


    //==========================================================================
    // End of drawing primitives
    //==========================================================================




    //==========================================================================
    // Miscellaneous calculation methods
    //==========================================================================

    //==========================================================================
    //
    // Given two line segments in 2-D space, rapidly determine whether they
    // intersect or not, and determine the point of intersection.
    //
    // Code adapted from:
    //     _Graphics Gems, Faster Line Segment Intersection, III.199, III.500_
    //

    /**
     * Line segments don't intersect.
     */
    public final static         int CALCLINEX_DONT_INTERSECT    = 0;
    /**
     * Line segments do intersect.
     */
    public final static         int CALCLINEX_DO_INTERSECT      = 1;
    /**
     * Line segments are collinear.
     */
    public final static         int CALCLINEX_COLLINEAR         = 2;


    /**
     *
     * Given two line segments in 2-D space, rapidly determine whether they
     * intersect or not, and determine the point of intersection.
     *
     * Code adapted from:<br>
     *     <i>_Graphics Gems, Faster Line Segment Intersection, III.199, III.500_</i>
     * @param p1 Beginning of line 1. TODO
     * @param p2 End of line 1. TODO
     * @param p3 Beginning of line 2. TODO
     * @param p4 End of line 2. TODO
     * @param pret Out argument, intersection coordinates.
     * @return Either don't intersect, do intersect or collinear.
     */
    public
    int calcLineSegmentsIntersect(YxxfGfxPointW p1, YxxfGfxPointW p2,
                                  YxxfGfxPointW p3, YxxfGfxPointW p4,
                                  YxxfGfxPointW pret)
    {
        double Ax, Bx, Cx, Ay, By, Cy, d, e, f;
        double x1lo, x1hi, y1lo, y1hi;


        // X bound box test
        Ax = p2.x - p1.x;
        Bx = p3.x - p4.x;
        if (Ax < 0.0)
            { x1lo = p2.x; x1hi = p1.x; }
        else
            { x1hi = p2.x; x1lo = p1.x; }
        if (Bx > 0.0)
            { if (x1hi < p4.x || p3.x < x1lo) return CALCLINEX_DONT_INTERSECT; }
        else
            { if (x1hi < p3.x || p4.x < x1lo) return CALCLINEX_DONT_INTERSECT; }


        // Y bound box test
        Ay = p2.y - p1.y;
        By = p3.y - p4.y;
        if (Ay < 0.0)
            { y1lo = p2.y; y1hi = p1.y; }
        else
            { y1hi = p2.y; y1lo = p1.y; }
        if (By > 0.0)
            { if (y1hi < p4.y || p3.y < y1lo) return CALCLINEX_DONT_INTERSECT; }
        else
            { if (y1hi < p3.y || p4.y < y1lo) return CALCLINEX_DONT_INTERSECT; }


        Cx = p1.x - p3.x;
        Cy = p1.y - p3.y;

        f = Ay * Bx - Ax * By;                                  // Both denominators

        d = By * Cx - Bx * Cy;                                  // Alpha numerator

        if (f > 0.0)                                            // Alpha tests
            { if (d < 0.0 || d > f) return CALCLINEX_DONT_INTERSECT; }
        else
            { if (d > 0.0 || d < f) return CALCLINEX_DONT_INTERSECT; }

        e = Ax * Cy - Ay * Cx;                                  // Beta numerator

        if (f > 0.0)                                            // Beta tests
            { if (e < 0.0 || e > f) return CALCLINEX_DONT_INTERSECT; }
        else
            { if (e > 0.0 || e < f) return CALCLINEX_DONT_INTERSECT; }

        if (f == 0.0)
            return CALCLINEX_COLLINEAR; // pret is undefined


        // Compute intersection coordinates
        pret.x = p1.x + (d / f) * Ax;
        pret.y = p1.y + (d / f) * Ay;

        return CALCLINEX_DO_INTERSECT;
    }


    /**
     *
     * @param p1 Beginning of line 1. TODO
     * @param p2 End of line 1. TODO
     * @param p3 Beginning of line 2. TODO
     * @param p4 End of line 2. TODO
     * @param pret Out argument, intersection coordinates.
     * @return Either don't intersect, do intersect or collinear.
     */
    public
    int calcLinesIntersect(YxxfGfxPointW p1, YxxfGfxPointW p2,
                           YxxfGfxPointW p3, YxxfGfxPointW p4,
                           YxxfGfxPointW pret)
    {
        double Ax, Bx, Cx, Ay, By, Cy, d, f;

        // X bound box
        Ax = p2.x - p1.x;
        Bx = p3.x - p4.x;

        // Y bound box
        Ay = p2.y - p1.y;
        By = p3.y - p4.y;

        Cx = p1.x - p3.x;
        Cy = p1.y - p3.y;

        f = Ay * Bx - Ax * By;

        d = By * Cx - Bx * Cy;

        if (d == 0.0)
        {
            // pret is undefined
            if (f == 0.0)
                return CALCLINEX_COLLINEAR;

            return CALCLINEX_DONT_INTERSECT; // parallel
        }

        if (f == 0.0)
            return CALCLINEX_COLLINEAR; // pret is undefined


        // Compute intersection coordinates
        pret.x = p1.x + (d / f) * Ax;
        pret.y = p1.y + (d / f) * Ay;

        return CALCLINEX_DO_INTERSECT;
    }
    //==========================================================================




    //==========================================================================
    /**
     * Parallel line calculation point.
     */
    private
    YxxfGfxPointW               calcLineP_pw0   = new YxxfGfxPointW();
    /**
     * Parallel line calculation point.
     */
    private
    YxxfGfxPointW               calcLineP_pw1   = new YxxfGfxPointW();
    /**
     * Parallel line calculation point.
     */
    private
    YxxfGfxPointW               calcLineP_pw2   = new YxxfGfxPointW();

    /**
     * Calculate if two lines are parallel.
     * @param p1 TODO
     * @param p2 TODO
     * @param p3 TODO
     * @param p4 TODO
     * @return true if parallel.
     */
    public
    boolean calcLinesParallel(YxxfGfxPointW p1, YxxfGfxPointW p2,
                              YxxfGfxPointW p3, YxxfGfxPointW p4)
    {
        calcLineP_pw1.set(p2.x - p1.x, p2.y - p1.y, p2.z - p1.z);
        calcLineP_pw1.normalize();
        calcLineP_pw2.set(p4.x - p3.x, p4.y - p3.y, p4.z - p3.z);
        calcLineP_pw2.normalize();

        calcLineP_pw0.crossProduct(calcLineP_pw1, calcLineP_pw2);

        if (calcLineP_pw0.x == 0.0 &&
            calcLineP_pw0.y == 0.0 &&
            calcLineP_pw0.z == 0.0)
            return true; // Parallel

        return false;
    }
    //==========================================================================




    //==========================================================================
    /**
     * Calculate if two doubles are either positive or negative.
     * @param a A number.
     * @param b A number.
     * @return true if the same.
     */
    public static
    boolean
    calcSameSigns(double a, double b)
    {
        if (a >= 0.0)
        {
            if (b >= 0.0)
                return true;
            else
                return false;
        }
        else
        {
            if (b < 0.0)
                return true;
            else
                return false;
        }
    }
    //==========================================================================




    //==========================================================================

    //
    // Distance
    //

    /**
     * Find distance between two points - unscaled.
     * @param x1 The x coordinate of point 1.
     * @param y1 The y coordinate of point 1.
     * @param x2 The x coordinate of point 2.
     * @param y1 The y coordinate of point 2.
     * @return The distance.
     */
    public static
    double calcdis(double x1, double y1, double x2, double y2)
    {
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }


    /**
     * Find distance between two points - screen.
     * @param x1 The x coordinate of point 1.
     * @param y1 The y coordinate of point 1.
     * @param x2 The x coordinate of point 2.
     * @param y1 The y coordinate of point 2.
     * @return The distance.
     */
    public static
    int calcdis_SCS(int x1, int y1, int x2, int y2)
    {
        return (int)Math.round(Math.sqrt((double)((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1))));
    }
    //==========================================================================




    //==========================================================================
    //

    /**
     * Extrusion point.
     */
    private
    YxxfGfxPointW               check_xptv_pw0  = new YxxfGfxPointW();
    /**
     * Extrusion point.
     */
    private
    YxxfGfxPointW               check_xptv_pw1  = new YxxfGfxPointW();
    /**
     * Extrusion point.
     */
    private
    YxxfGfxPointW               check_xptv_pw2  = new YxxfGfxPointW();
    /**
     * Extrusion point.
     */
    private
    YxxfGfxPointW               check_xptv_pw3  = new YxxfGfxPointW();

    /**
     * Determine if the extrusion direction is parallel to
     * the current view direction.
     * <pre>
     * SOLID draw:
     *     XTRUDIR      FILLMODE    DRAWN
     *     --------     --------    --------------------
     *     par          ON          Solid fill - 2D
     *     par          OFF         Wire frame - 2D
     *     not par      ON          Wire frame - 3D
     *     not par      OFF         Wire frame - 2D
     * </pre>
     * @return true if parallel.
     */
    public
    boolean check_xtrudir_parallel_to_view()
    {
        check_xptv_pw0.set(YxxfGfxPointW.W0);
        M_entity.mtxTransformPoint(check_xptv_pw0);
        M_model.mtxTransformPoint(check_xptv_pw0);
        M_view.mtxTransformPoint(check_xptv_pw0);
        M_user.mtxTransformPoint(check_xptv_pw0);

        check_xptv_pw1.set(YxxfGfxPointW.Wz);
        M_entity.mtxTransformPoint(check_xptv_pw1);
        M_model.mtxTransformPoint(check_xptv_pw1);
        M_view.mtxTransformPoint(check_xptv_pw1);
        M_user.mtxTransformPoint(check_xptv_pw1);

        check_xptv_pw2.set(check_xptv_pw1.x - check_xptv_pw0.x,
                           check_xptv_pw1.y - check_xptv_pw0.y,
                           check_xptv_pw1.z - check_xptv_pw0.z);
        check_xptv_pw2.normalize();

        if (check_xptv_pw2.x == 0.0 &&
            check_xptv_pw2.y == 0.0 &&
            check_xptv_pw2.z == 1.0)
        {
            return true;
        }
        else
        {
            return false;
        }

        // check_xptv_pw3.crossProduct(YxxfGfxPointW.Wz, check_xptv_pw2);

        // System.out.println("DRAW SOLID W0=" + check_xptv_pw0 +
        //                              ",Wz=" + check_xptv_pw1 +
        //                              ",Zn=" + check_xptv_pw2 +
        //                              ",Cp=" + check_xptv_pw3);
    }

    /**
     * Determine if the extrusion direction is parallel to
     * the current view direction.
     * @param arg_M_entity TODO
     * @return true if parallel.
     */
    public
    boolean check_xtrudir_parallel_to_view(YxxfGfxMatrix arg_M_entity)
    {
        check_xptv_pw0.set(YxxfGfxPointW.W0);
        arg_M_entity.mtxTransformPoint(check_xptv_pw0);
        M_model.mtxTransformPoint(check_xptv_pw0);
        M_view.mtxTransformPoint(check_xptv_pw0);
        M_user.mtxTransformPoint(check_xptv_pw0);

        check_xptv_pw1.set(YxxfGfxPointW.Wz);
        arg_M_entity.mtxTransformPoint(check_xptv_pw1);
        M_model.mtxTransformPoint(check_xptv_pw1);
        M_view.mtxTransformPoint(check_xptv_pw1);
        M_user.mtxTransformPoint(check_xptv_pw1);

        check_xptv_pw2.set(check_xptv_pw1.x - check_xptv_pw0.x,
                           check_xptv_pw1.y - check_xptv_pw0.y,
                           check_xptv_pw1.z - check_xptv_pw0.z);
        check_xptv_pw2.normalize();

        if (check_xptv_pw2.x == 0.0 &&
            check_xptv_pw2.y == 0.0 &&
            check_xptv_pw2.z == 1.0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    //==========================================================================




    //==========================================================================

    //
    // Angles
    //

    /**
     * Find angle of 2D vector in plane.
     * @param x1 TODO
     * @param y1 TODO
     * @param x2 TODO
     * @param y2 TODO
     * @return return angle of vector with x axis in radians.
     */
    //
    public static
    double calcangle(double x1, double y1, double x2, double y2)
    {
        double tmpx = x2 - x1;
        double tmpy = y2 - y1;
        double hypt = Math.sqrt(tmpx * tmpx + tmpy * tmpy);

        if (tmpx < 0)
        {
            if (tmpy < 0)
            {   // 3rd quad
                return Math.PI + Math.acos(-tmpx / hypt);
            }
            else
            {   // 2nd quad
                return Math.PI - Math.acos(-tmpx / hypt);
            }
        }
        else
        {
            if (tmpy < 0)
            {   // 4th quad
                return (2.0 * Math.PI) - Math.acos(tmpx / hypt);
            }
            else
            {   // 1st quad
                return Math.acos(tmpx / hypt);
            }
        }
    }
    //==========================================================================




    //==========================================================================
    // Other
    //==========================================================================

    /**
     * Stringify the graphics context.
     * @return The graphics context stack description.
     */
    public
    String toString()
    {
        String retstr;

        retstr  = "=== GC BEG ===================================================\n";

        int i = 0;
        for (YxxfGfxContext tmpgc = this; ; i++)
        {
            retstr += "    YxxfGfxContext beg ==> " + i + " <========\n";

//          retstr += "    YxxfGfxContext D                    = "      + tmpgc.D + "\n";
//          retstr += "    YxxfGfxContext vport=["                      + tmpgc.vport + "]\n";
//          retstr += "    YxxfGfxContext viewport=[\n"                 + tmpgc.viewport + "]\n";

            retstr += "    YxxfGfxContext sharedjgcflag        = "      + tmpgc.sharedjgcflag + "\n";
            retstr += "    YxxfGfxContext jgc                  = "      + tmpgc.jgc + "\n";
            retstr += "    YxxfGfxContext jgc.getClipBounds    = "      + tmpgc.jgc.getClipBounds() + "\n";
            retstr += "    YxxfGfxContext jgctranslate         = ("     + tmpgc.jgctranslate.x + " " + tmpgc.jgctranslate.y + ")" + "\n";

            retstr += "    YxxfGfxContext dspwin               = "      + tmpgc.dspwin + "\n";

//          retstr += "    YxxfGfxContext bgcolor              = "      + tmpgc.curr_bgcolor_String + "\n";
//          retstr += "    YxxfGfxContext pal                  = "      + tmpgc.pal;

            retstr += "    YxxfGfxContext vh,vw                = ("     + tmpgc.vheight + " " + tmpgc.vwidth + ")\n";
            retstr += "    YxxfGfxContext vscale_pixels        = "      + tmpgc.vscale_pixels_per_vunit + "\n";
            retstr += "    YxxfGfxContext vh,vw actual         = ("     + tmpgc.vheight_actual + " " + tmpgc.vwidth_actual + ")\n";

//          retstr += "    YxxfGfxContext mstack_topidx        = "      + tmpgc.M_model_stack_topidx + "\n";
//          retstr += "    YxxfGfxContext M_vcs_to_scs=[\n"             + tmpgc.M_vcs_to_scs + "]\n";
//          retstr += "    YxxfGfxContext M_entity    =[\n"             + tmpgc.M_entity + "]\n";
//          retstr += "    YxxfGfxContext M_model     =[\n"             + tmpgc.M_model + "]\n";
//          retstr += "    YxxfGfxContext M_view      =[\n"             + tmpgc.M_view + "]\n";
//          retstr += "    YxxfGfxContext M_user      =[\n"             + tmpgc.M_user + "]\n";
//          retstr += "    YxxfGfxContext M_display   =[\n"             + tmpgc.M_display + "]\n";

            retstr += "    YxxfGfxContext end ========\n";

            if ((tmpgc = tmpgc.getParentGC()) == null)
                break;

            retstr += "    YxxfGfxContext\n";
        }

        retstr += "=== GC END ===================================================\n";

        return retstr;
    }
    //==========================================================================
}

