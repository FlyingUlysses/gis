//==============================================================================
// YxxfTblVport.java
//
// VPORT table
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfTblVport.java,v 1.12 2003/05/08 11:24:03 ekarlo Exp $
// $Log: YxxfTblVport.java,v $
// Revision 1.12  2003/05/08 11:24:03  ekarlo
// Remove warnings.
//
// Revision 1.11  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.10  2000/10/17 07:43:36  ekarlo
// Change package paths to lower case.
//
// Revision 1.9  1999-09-08 13:17:00-06  walter
// Add JavaDoc comments.
//
// Revision 1.8  1999-07-09 14:10:50-06  ekarlo
// Rearrange package names.
//
// Revision 1.7  1999/06/20  22:34:10  ekarlo
// Rearrange package names.
//
// Revision 1.6  1999/06/15  04:59:25  ekarlo
// User Interface - phase 1.
//
// Revision 1.5  1999/01/28  04:32:33  ekarlo
// Text - phase 4.
//
// Revision 1.4  1997/07/21  22:54:49  ekarlo
// Make fields public for static get.
// Rename lower left fields.
//
// Revision 1.3  1996/08/13  02:28:09  ekarlo
// Change variable accessability.
//
// Revision 1.2  1996/07/30  07:02:15  ekarlo
// 1) Use point class.
// 2) Full print of variables.
//
// Revision 1.1  1996/07/02  02:20:23  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


/**
 * VPORT table.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfTblVport implements Cloneable
{
    /**
     * code   2 - Viewport name.
     */
    public
    String                      name = "";
    /**
     * code   5 - Handle.
     */
    public
    String                      handle = "";
    /**
     * code 100 - Subclass marker.
     */
    public
    String                      subClassMarker = "";
    /**
     * code  70 - Standard flag values.
     */
    public
    int                         flags = 0;
    /**
     * code  10 - Lower-left corner of Viewport - x.
     */
    public
    double                      llx = 0.0;
    /**
     * code  20 - Lower-left corner of Viewport - y.
     */
    public
    double                      lly = 0.0;
    /**
     * code  11 - Upper-right corner of Viewport - x.
     */
    public
    double                      urx = 0.0;
    /**
     * code  21 - Upper-right corner of Viewport - y.
     */
    public
    double                      ury = 0.0;
    /**
     * code  12 - View center point (in DCS) - x.
     */
    public
    double                      vcpx = 0.0;
    /**
     * code  22 - View center point (in DCS) - y.
     */
    public
    double                      vcpy = 0.0;
    /**
     * code  13 - Snap base point - x.
     */
    public
    double                      sbpx = 0.0;
    /**
     * code  23 - Snap base point - y.
     */
    public
    double                      sbpy = 0.0;
    /**
     * code  14 - Snap spacing - x.
     */
    public
    double                      ssx = 0.0;
    /**
     * code  24 - Snap spacing - y.
     */
    public
    double                      ssy = 0.0;
    /**
     * code  15 - Grid spacing - x.
     */
    public
    double                      gsx = 0.0;
    /**
     * code  25 - Grid spacing - y.
     */
    public
    double                      gsy = 0.0;

    /**
     * code  16,26,36 -
     *            View direction from target point (in WCS).
     */
    public
    YxxfGfxPointW               vdir            = new YxxfGfxPointW();

    /**
     * code  17,27,37 -
     *            View target point (in WCS).
     */
    public
    YxxfGfxPointW               vtgt            = new YxxfGfxPointW();

    /**
     * code  40 - View height.
     */
    public
    double                      vheight = 0.0;

    /**
     * code  41 - Viewport aspect ratio.
     */
    public
    double                      vaspect = 0.0;

    /**
     * code  42 - Lens length.
     */
    public
    double                      lensLength = 0.0;

    /**
     * code  43 - Front clipping plane (offset from target point).
     */
    public
    double                      fcp = 0.0;
    /**
     * code  44 - Back clipping plane (offset from target point).
     */
    public
    double                      bcp = 0.0;

    /**
     * code  50 - Snap rotation angle.
     */
    public
    double                      snaprotang = 0.0;

    /**
     * code  51 - View twist angle.
     */
    public
    double                      viewtwistang = 0.0;

    /**
     * code  68 - Status field (never saved).
     */
    public
    int                         status = 0;
    /**
     * code  69 - ID (never saved).
     */
    public
    int                         id = 0;
    /**
     * code  71 - View mode (see VIEWMODE system variable).
     */
    public
    int                         viewmode = 0;
    /**
     * code  72 - Circle zoom percent.
     */
    public
    int                         circleZoomPercent = 0;
    /**
     * code  73 - Fast zoom setting.
     */
    public
    int                         fastZoomSetting = 0;
    /**
     * code  74 - UCSICON setting.
     */
    public
    int                         ucsicon = 0;
    /**
     * code  75 -Snap on/off.
     */
    public
    int                         snaponoff = 0;
    /**
     * code  76 - Grid on/off.
     */
    public
    int                         gridonoff = 0;
    /**
     * code  77 - Snap style.
     */
    public
    int                         snapStyle = 0;
    /**
     * code  78 - Snap isopair.
     */
    public
    int                         snapIsopair = 0;


    /**
     * Constructor (empty)
     */
    public
    YxxfTblVport()
    {
    }


    /**
     * Constructor
     * @param vportname The Viewport name
     */
    public
    YxxfTblVport(String vportname)
    {
        name = new String(vportname);
    }

    /**
     * Copy this Viewport into another Viewport.
     * @param vpt The target Viewport.
     */
    public
    void copyInto(YxxfTblVport vpt)
    {
        vpt.name                = name;
        vpt.handle              = handle;
        vpt.subClassMarker      = subClassMarker;
        vpt.flags               = flags;
        vpt.llx                 = llx;
        vpt.lly                 = lly;
        vpt.urx                 = urx;
        vpt.ury                 = ury;
        vpt.vcpx                = vcpx;
        vpt.vcpy                = vcpy;
        vpt.sbpx                = sbpx;
        vpt.sbpy                = sbpy;
        vpt.ssx                 = ssx;
        vpt.ssy                 = ssy;
        vpt.gsx                 = gsx;
        vpt.gsy                 = gsy;
        vdir.copyInto(vpt.vdir);
        vtgt.copyInto(vpt.vtgt);
        vpt.vheight             = vheight;
        vpt.vaspect             = vaspect;
        vpt.lensLength          = lensLength;
        vpt.fcp                 = fcp;
        vpt.bcp                 = bcp;
        vpt.snaprotang          = snaprotang;
        vpt.viewtwistang        = viewtwistang;
        vpt.status              = status;
        vpt.id                  = id;
        vpt.viewmode            = viewmode;
        vpt.circleZoomPercent   = circleZoomPercent;
        vpt.fastZoomSetting     = fastZoomSetting;
        vpt.ucsicon             = ucsicon;
        vpt.snaponoff           = snaponoff;
        vpt.gridonoff           = gridonoff;
        vpt.snapStyle           = snapStyle;
        vpt.snapIsopair         = snapIsopair;
    }


    /**
     * Clone vport.
     * @return A copy of this Viewport.
     */
    public
    Object clone()
    {
        YxxfTblVport zclone = new YxxfTblVport();
        this.copyInto(zclone);
        return zclone;
    }


    /**
     * Stringify this Viewport.
     * @return The description of this Viewport.
     */
    public
    String toString()
    {
        return
              "    YxxfTblVport name=["             + name + "]" +
            "\n    YxxfTblVport handle=["           + handle + "]" +
            "\n    YxxfTblVport subClassMarker=["   + subClassMarker + "]" +
            "\n    YxxfTblVport flags="             + flags +
            "\n    YxxfTblVport llx="               + llx +
            "\n    YxxfTblVport lly="               + lly +
            "\n    YxxfTblVport urx="               + urx +
            "\n    YxxfTblVport ury="               + ury +
            "\n    YxxfTblVport vcpx="              + vcpx +
            "\n    YxxfTblVport vcpy="              + vcpy +
            "\n    YxxfTblVport sbpx="              + sbpx +
            "\n    YxxfTblVport sbpy="              + sbpy +
            "\n    YxxfTblVport ssx="               + ssx +
            "\n    YxxfTblVport ssy="               + ssy +
            "\n    YxxfTblVport gsx="               + gsx +
            "\n    YxxfTblVport gsy="               + gsy +
            "\n    YxxfTblVport vdir="              + vdir +
            "\n    YxxfTblVport vtgt="              + vtgt +
            "\n    YxxfTblVport vheight="           + vheight +
            "\n    YxxfTblVport vaspect="           + vaspect +
            "\n    YxxfTblVport lensLength="        + lensLength +
            "\n    YxxfTblVport fcp="               + fcp +
            "\n    YxxfTblVport bcp="               + bcp +
            "\n    YxxfTblVport snaprotang="        + snaprotang +
            "\n    YxxfTblVport viewtwistang="      + viewtwistang +
            "\n    YxxfTblVport status="            + status +
            "\n    YxxfTblVport id="                + id +
            "\n    YxxfTblVport viewmode="          + viewmode +
            "\n    YxxfTblVport circleZoomPercent=" + circleZoomPercent +
            "\n    YxxfTblVport fastZoomSetting="   + fastZoomSetting +
            "\n    YxxfTblVport ucsicon="           + ucsicon +
            "\n    YxxfTblVport snaponoff="         + snaponoff +
            "\n    YxxfTblVport gridonoff="         + gridonoff +
            "\n    YxxfTblVport snapStyle="         + snapStyle +
            "\n    YxxfTblVport snapIsopair="       + snapIsopair;
    }
}
