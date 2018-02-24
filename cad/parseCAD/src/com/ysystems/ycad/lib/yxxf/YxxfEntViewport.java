//==============================================================================
// YxxfEntViewport.java
//
// VIEWPORT entity
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfEntViewport.java,v 1.11 2003/04/14 12:38:42 ekarlo Exp $
// $Log: YxxfEntViewport.java,v $
// Revision 1.11  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.10  2002/10/22 00:20:47  ekarlo
// Add fields to stringify value.
//
// Revision 1.9  2000-10-17 01:43:49-06  ekarlo
// Change package paths to lower case.
//
// Revision 1.8  1999-09-29 17:05:49-06  walter
// Added JavaDoc comments.
//
// Revision 1.7  1999-07-09 14:10:50-06  ekarlo
// Rearrange package names.
//
// Revision 1.6  1999/06/20  22:34:10  ekarlo
// Rearrange package names.
//
// Revision 1.5  1999/02/08  05:11:21  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.4  1998/11/24  19:53:50  ekarlo
// Text - phase 2.
//
// Revision 1.3  1998/02/02  21:12:03  ekarlo
// Deactivate header handle.
//
// Revision 1.2  1998/02/02  19:11:11  ekarlo
// Implement paper space - phase 2.
//
// Revision 1.1  1997/12/26  21:37:06  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


import java.awt.*;


/**
 * VIEWPORT entity.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfEntViewport extends YxxfEntHeader implements YxxfEnt
{
    /**
     * code  10,20,30 - View center point (in WCS).
     */
    public
    YxxfGfxPointW               vpcp            = new YxxfGfxPointW();

    /**
     * code  40 - View width in paper space units.
     */
    public
    double                      vpwidth = 0.0;

    /**
     * code  41 - View height in paper space units.
     */
    public
    double                      vpheight = 0.0;

    /**
     * code  68 - Viewport status field.
     * <UL>
     *   <LI>-1 = On, but is fully offscreen, or is one of the viewports
     *            that is not active because the $MAXACTVP count is
     *            currently being exceeded.
     *   <LI> 0 = Off
     *   <LI> A positive value = On, and active.  The value indicates
     *            the order of stacking for the viewports, where 1 is the
     *            active viewport, 2 is the next, and so forth.
     * </UL>
     */
    public
    int                         vpstatus = 0;

    /**
     * code  69 - Viewport ID.  Changes each time a drawing is opened.
     *            Never saved, except for the paper space viewport, which
     *            is always 1.
     */
    public
    int                         vpid = 0;

    
    // VIEWPORT xdata
    
    /**
     * code  1010:0,1020:0,1030:0 - View target point (in WCS).
     */
    public
    YxxfGfxPointW               vtgt            = new YxxfGfxPointW();

    /**
     * code  1010:1,1020:1,1030:1 - View direction from target point (in WCS).
     */
    public
    YxxfGfxPointW               vdir            = new YxxfGfxPointW();

    /**
     * code  1040:0 - View twist angle.
     */
    public
    double                      viewtwistang = 0.0;
    /**
     * code  1040:1 - View height.
     */
    public
    double                      vheight = 0.0;  
    /**
     * code  1040:2 - View center point (in DCS) - x.
     */
    public
    double                      vcpx = 0.0;
    /**
     * code  1040:3 - View center point (in DCS) - y.
     */
    public
    double                      vcpy = 0.0;  
    /**
     * code  1040:4 - Lens length.
     */
    public
    double                      lensLength = 0.0;
    /**
     * code  1040:5 - Front clipping plane (offset from target point).
     */
    public
    double                      fcp = 0.0;
    /**
     * code  1040:6 - Back clipping plane (offset from target point).
     */
    public
    double                      bcp = 0.0;
    /**
     * code  1040:7 - Snap rotation angle.
     */
    public
    double                      snaprotang = 0.0;
    /**
     * code  1040:8 - Snap base point - x.
     */
    public
    double                      sbpx = 0.0;
    /**
     * code  1040:9 - Snap base point - y.
     */
    public
    double                      sbpy = 0.0;
    /**
     * code  1040:10 - Snap spacing - x.
     */
    public
    double                      ssx = 0.0;
    /**
     * code  1040:11 - Snap spacing - y.
     */
    public
    double                      ssy = 0.0;
    /**
     * code  1040:12 - Grid spacing - x.
     */
    public
    double                      gsx = 0.0;
    /**
     * code  1040:13 - Grid spacing - y.
     */
    public
    double                      gsy = 0.0;

    /**
     * code  1070:0 - Extended data version number.
     *                Always the integer 16.
     */
    public
    int                         xdatavers = 16;
    /**
     * code  1070:1 - View mode (see VIEWMODE system variable).
     */
    public
    int                         viewmode = 0;
    /**
     * code  1070:2 - Circle zoom percent.
     */
    public
    int                         circleZoomPercent = 0;
    /**
     * code  1070:3 - Fast zoom setting.
     */
    public
    int                         fastZoomSetting = 0;
    /**
     * code  1070:4 - UCSICON setting.
     */
    public 
    int                         ucsicon = 0;
    /**
     * code  1070:5 -Snap on/off.
     */
    public
    int                         snaponoff = 0;
    /**
     * code  1070:6 - Grid on/off.
     */
    public
    int                         gridonoff = 0;
    /**
     * code  1070:7 - Snap style.
     */
    public
    int                         snapStyle = 0;
    /**
     * code  1070:8 - Snap isopair.
     */
    public
    int                         snapIsopair = 0;
    /**
     * code  1070:9 - Hidden in plot flag.
     */
    public
    int                         hiddeninplotflag = 0;
    
        
    // Calculated items

    /**
     * Scale TODO. 
     * Calculated during system graphics update().
     */
    public
    double                      vpDspScale      = 0.0;

    /**
     * Location TODO. 
     * Calculated during system graphics update().
     */
    public
    Point                       vpDspLocation   = new Point(0, 0);

    /**
     * Size TODO. 
     * Calculated during system graphics update().
     */
    public
    Dimension                   vpDspSize       = new Dimension(0, 0);


    /**
     * Constructor (empty)
     */
    public
    YxxfEntViewport()
    {
    }


    /**
     * Stringify the Viewport.
     * @return Viewport description.
     */
    public
    String toString()
    {
        return
              "==> BEG Entity VIEWPORT" +
//          "\n    YxxfEntViewport hdr_handle        = " + hdr_handle +
            "\n    YxxfEntViewport hdr_layer.name    = " + hdr_layer.getName() +
            "\n    YxxfEntViewport hdr_space         = " + hdr_space +

            "\n    YxxfEntViewport vpcp (WCS)        = " + vpcp +
            "\n    YxxfEntViewport vpwidth (PSu)     = " + vpwidth +
            "\n    YxxfEntViewport vpheight (PSu)    = " + vpheight +
            "\n    YxxfEntViewport vpstatus          = " + vpstatus +
            "\n    YxxfEntViewport vpid              = " + vpid +
            
            "\n    YxxfEntViewport xdatavers         = " + xdatavers +
            "\n    YxxfEntViewport vtgt (WCS)        = " + vtgt +
            "\n    YxxfEntViewport vdir (WCS)        = " + vdir +
            "\n    YxxfEntViewport viewtwistang      = " + viewtwistang +
            "\n    YxxfEntViewport vheight           = " + vheight +
            "\n    YxxfEntViewport vcpx (DCS)        = " + vcpx +
            "\n    YxxfEntViewport vcpy (DCS)        = " + vcpy +
            "\n    YxxfEntViewport lensLength        = " + lensLength +
            "\n    YxxfEntViewport fcp               = " + fcp +
            "\n    YxxfEntViewport bcp               = " + bcp +
            "\n    YxxfEntViewport viewmode          = " + viewmode +
            "\n    YxxfEntViewport circleZoomPercent = " + circleZoomPercent +
            "\n    YxxfEntViewport fastZoomSetting   = " + fastZoomSetting +
            "\n    YxxfEntViewport ucsicon           = " + ucsicon +
            "\n    YxxfEntViewport snaponoff         = " + snaponoff +
            "\n    YxxfEntViewport gridonoff         = " + gridonoff +
            "\n    YxxfEntViewport snapStyle         = " + snapStyle +
            "\n    YxxfEntViewport snapIsopair       = " + snapIsopair +
            "\n    YxxfEntViewport snaprotang        = " + snaprotang +
            "\n    YxxfEntViewport sbpx              = " + sbpx +
            "\n    YxxfEntViewport sbpy              = " + sbpy +
            "\n    YxxfEntViewport ssx               = " + ssx +
            "\n    YxxfEntViewport ssy               = " + ssy +
            "\n    YxxfEntViewport gsx               = " + gsx +
            "\n    YxxfEntViewport gsy               = " + gsy +
            "\n    YxxfEntViewport hiddeninplotflag  = " + hiddeninplotflag +
            "\n    YxxfEntViewport vpDspScale        = " + vpDspScale +
            "\n    YxxfEntViewport vpDspLocation     = " + vpDspLocation +
            "\n    YxxfEntViewport vpDspSize         = " + vpDspSize +
            "\n==> END Entity VIEWPORT";
    }

    
    /**
     * Draw viewport. Disable draw method.
     * @param gc Graphics Context.
     */
    public
    void draw(YxxfGfxContext gc)
    {
    }


    /**
     * Calculate viewport transmat
     * @param D The drawing.
     */
    public
    void calc(Yxxf D)
    {
        //
        // connect
        //
        hdr_calc(D);
    }
}



