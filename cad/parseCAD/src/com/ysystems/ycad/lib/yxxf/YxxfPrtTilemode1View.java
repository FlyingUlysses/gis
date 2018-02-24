//==============================================================================
// YxxfPrtTilemode1View.java
//
// Creates tilemode 1 views
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfPrtTilemode1View.java,v 1.16 2003/06/04 11:07:18 ekarlo Exp $
// $Log: YxxfPrtTilemode1View.java,v $
// Revision 1.16  2003/06/04 11:07:18  ekarlo
// Don't do unnecessary redraws when zooming to extents or when rendering is turned off.
//
// Revision 1.15  2003/06/03 09:53:15  ekarlo
// Fix xoffset= and yoffset= Prt imprinter funtionality.
//
// Revision 1.14  2003/05/26 02:58:10  ekarlo
// Add methods to turn rendering on and off.
// Improve calculation of extents.
//
// Revision 1.13  2003/05/17 12:42:21  ekarlo
// Add option to control width of border around views.
//
// Revision 1.12  2003/05/08 11:24:02  ekarlo
// Remove warnings.
//
// Revision 1.11  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.10  2002/10/22 00:44:02  ekarlo
// Change comments.
//
// Revision 1.9  2002-10-12 01:09:33-06  ekarlo
// Fix zoom to calc extents command.
//
// Revision 1.8  2002-09-26 01:51:23-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.7  2002-09-12 23:39:24-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.6  2001-05-21 04:39:40-06  ekarlo
// Imprinter development - draw all view borders.
//
// Revision 1.5  2001-05-21 02:34:04-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.4  2001-05-20 14:38:11-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.3  2001-05-20 02:42:57-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.2  2001-05-17 23:33:07-06  ekarlo
// Development check-in.
//
// Revision 1.1  2001-05-17 02:52:28-06  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


import java.awt.*;

import com.ysystems.lib.yutil.*;


/**
 * A collection of one or more nonoverlapping views in Model Space.
 * <br>Tilemode 1 views.
 * <br>Model Space (tiled viewports).
 * <br>One to N nonoverlapping views.
 * <br>Each view has its own draw thread rendering one model view.
 * <br>
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfPrtTilemode1View extends YxxfPrtComponent
{
    //==========================================================================
    /**
     * Properties.
     */
    private 
    YutilProperties             props_PrtTilemode1View
                                                = new YutilProperties();
    //==========================================================================


    //==========================================================================
    // Display items.

    //
    // Graphics context is in parent component
    //
    // YxxfGfxContext gc

    /**
     * Draw thread for this view.
     */
    private
    YxxfPrtTilemode1DrawT       Tdraw;
    //==========================================================================


    //==========================================================================
    // Constructors

    /**
     * Constructor (empty)
     */
    public
    YxxfPrtTilemode1View()
    {
    }

    /**
     * Constructor
     * @param D The Drawing.
     * @param jgc The Graphics Context.
     * @param vport The Viewport.
     */
    public
    YxxfPrtTilemode1View(Yxxf D, Graphics jgc,
                         YxxfTblVport vport)
    {
        setDrawing(D);
        setJGC(jgc);
        setVport(vport);
    }


    /**
     * Constructor
     * @param D The Drawing.
     * @param jgc The Graphics Context.
     * @param vport The Viewport.
     */
    public
    YxxfPrtTilemode1View(Yxxf D, Graphics jgc,
                         int x, int y, int width, int height,
                         YxxfTblVport vport)
    {
        setDrawing(D);
        setJGC(jgc);
        setDspwinX(x);
        setDspwinY(y);
        setDspwinWidth(width);
        setDspwinHeight(height);
        setVport(vport);
    }


    /**
     * Set the value of the Vport.
     * @param vport The Vport.
     */
    public
    void setVport(YxxfTblVport vport)
    {
        gc.setVport(vport);
        gc.setVportOrig((YxxfTblVport)vport.clone());
    }

    /**
     * Get the Vport.
     * @return The Vport.
     */
    public
    YxxfTblVport getVport()
    {
        return gc.getVport();
    }


    /**
     * Set the value of the unmodified Vport.
     * @param vportorig The unmodified Vport.
     */
    public
    void setVportOrig(YxxfTblVport vportorig)
    {
        gc.setVportOrig(vportorig);
    }

    /**
     * Get the unmodified Vport (for undo).
     * @return The unmodified Vport.
     */
    public
    YxxfTblVport getVportOrig()
    {
        return gc.getVportOrig();
    }
    //==========================================================================


    //==========================================================================
    // Set properties

    /**
     * Set properties from args string.
     * @param args The Properties.
     * @param scanout Parse the arguments flag.
     */
    public
    void setProperties(String args, boolean scanout)
    {
        props_PrtTilemode1View.setProperties(args, scanout);
    }


    /**
     * Set properties from argv array.
     * @param args The Properties.
     */
    public
    void setProperties(String argv[])
    {
        props_PrtTilemode1View.setProperties(argv);
    }


    /**
     * Set properties from argprops YutilProperties.
     * @param argprops The Properties.
     */
    public
    void setProperties(YutilProperties argprops)
    {
        props_PrtTilemode1View.setProperties(argprops);
    }
    //==========================================================================


    //==========================================================================
    /**
     * Initialize the Tilemode1 View.
     */
    public
    void commandTilemode1View_init()
    {
        // Display local params
//d     System.out.println("++++++++++++YxxfPrtTilemode1View:props_PrtTilemode1View.e BEG");
//d     for (Enumeration e = props_PrtTilemode1View.propertyNames(); e.hasMoreElements(); )
//d     {
//d         String key = (String)e.nextElement();
//d         String val = props_PrtTilemode1View.getProperty(key);
//d         System.out.println("++++++++++++YxxfPrtTilemode1View:key=[" + key + "],val=[" + val + "]");
//d     }
//d     System.out.println("++++++++++++YxxfPrtTilemode1View:props_PrtTilemode1View.e END");

        //
        // Create draw thread
        //
        Tdraw = new YxxfPrtTilemode1DrawT(this, gc);
        Tdraw.setProperties(props_PrtTilemode1View); // Set properties
    }
    //==========================================================================


    //==========================================================================
    /**
     * Start the Tilemode1 View draw thread.
     */
    public
    void commandTilemode1View_start()
    {
        //
        // Start draw thread
        //
        Tdraw.start();
    }
    //==========================================================================


    //==========================================================================
    /**
     * Redraw the Tilemode1 View.
     */
    public
    void commandTilemode1View_redraw()
    {
        paint(getJGC());
    }
    //==========================================================================


    //==========================================================================
    /**
     * Wait for the Tilemode1 View draw to complete.
     */
    public
    void commandTilemode1View_wait_for_complete()
    {
        gc.commandGC_wait_for_complete();
    }
    //==========================================================================


    //==========================================================================
    /**
     * Set rendering flag.
     */
    public
    void commandTilemode1View_set_rendering(boolean arg_val)
    {
        gc.setRenderingJGCFlag(arg_val);
    }
    //==========================================================================


    //==========================================================================
    /**
     * Undo any changes to the Tilemode1 View and redraw it.
     */
    public
    void commandTilemode1View_restore()
    {
        getVportOrig().copyInto(getVport()); // Restore original vport

        gc.commandGC_user_view_reset(); // Reset user view

        if (gc.getRenderingJGCFlag())
            commandTilemode1View_redraw();
    }
    //==========================================================================


    //==========================================================================
    /**
     * Draw the Tilemode 1 View scaled to the size of the object.
     */
    public
    void commandTilemode1View_calc_extents()
    {
        gc.gc_doing_flag_calc_extents = true;

        commandTilemode1View_redraw();
    }
    //==========================================================================


    //==========================================================================
    /**
     * Zoom in to the Tilemode1 View object and redraw it.
     */
    public
    void commandTilemode1View_zoom_in(double zoompct)
    {
        getVport().vheight *= (1.0 - (zoompct / 100.0));

        if (gc.getRenderingJGCFlag())
            commandTilemode1View_redraw();
    }
    //==========================================================================


    //==========================================================================
    /**
     * Zoom out from the Tilemode1 View object and redraw it.
     */
    public
    void commandTilemode1View_zoom_out(double zoompct)
    {
        getVport().vheight *= (1.0 + (zoompct / 100.0));

        if (gc.getRenderingJGCFlag())
            commandTilemode1View_redraw();
    }
    //==========================================================================


    //==========================================================================
    /**
     * Zoom in to the head extents of Tilemode1 View object and redraw it.
     */
    public
    void commandTilemode1View_zoom_to_head_extents()
    {
        // M_view
        getVport().vtgt.set(YxxfGfxPointW.W0);
        getVport().vdir.set(YxxfGfxPointW.Wz);
        getVport().viewtwistang = 0.0; // Degrees

        // M_user
        gc.commandGC_user_view_reset(); // Reset user view

        // M_display
        getVport().vheight = getDrawing().secHeader.extmax.y - getDrawing().secHeader.extmin.y;
        getVport().vaspect = Math.abs(getDrawing().secHeader.extmax.x - getDrawing().secHeader.extmin.x) /     // vaspect = vwidth / vheight
                             Math.abs(getDrawing().secHeader.extmax.y - getDrawing().secHeader.extmin.y);
        getVport().vcpx = (getDrawing().secHeader.extmin.x + getDrawing().secHeader.extmax.x) / 2.0;
        getVport().vcpy = (getDrawing().secHeader.extmin.y + getDrawing().secHeader.extmax.y) / 2.0;

        if (gc.getRenderingJGCFlag())
            commandTilemode1View_redraw();
    }
    //==========================================================================


    //==========================================================================
    /**
     * Zoom to the calc extents of Tilemode1 View and redraw it.
     */
    public
    void commandTilemode1View_zoom_to_calc_extents()
    {
        if (gc.gc_doing_flag_calc_extents_valid)
        {
            setVportToCalcExtents();
        }
        else
        {
            gc.gc_doing_flag_calc_extents = true; // need to calculate the extents
            gc.gc_doing_flag_zoom_to_calc_extents = true; // and zoom to them
        }

        // System.out.println("++++++++++++YxxfPrtTilemode1View:gc_doing_flag_calc_extents=" + gc.gc_doing_flag_calc_extents);
        // System.out.println("++++++++++++YxxfPrtTilemode1View:gc_doing_flag_calc_extents_valid=" + gc.gc_doing_flag_calc_extents_valid);
        // System.out.println("++++++++++++YxxfPrtTilemode1View:gc_doing_flag_zoom_to_calc_extents=" + gc.gc_doing_flag_zoom_to_calc_extents);
        // System.out.println("++++++++++++YxxfPrtTilemode1View:extmin=" + gc.calc_extmin);
        // System.out.println("++++++++++++YxxfPrtTilemode1View:extmax=" + gc.calc_extmax);

        commandTilemode1View_redraw();
    }

    /**
     * Utility method - sets the Vport to the previously calculated and valid extents.
     */
    protected
    void setVportToCalcExtents()
    {
        // M_view
        getVport().vtgt.set(YxxfGfxPointW.W0);
        getVport().vdir.set(YxxfGfxPointW.Wz);
        getVport().viewtwistang = 0.0; // Degrees

        // M_user
        gc.commandGC_user_view_reset();

        // M_display
        getVport().vheight = gc.calc_extmax.y - gc.calc_extmin.y;
        getVport().vaspect = Math.abs(gc.calc_extmax.x - gc.calc_extmin.x) /     // vaspect = vwidth / vheight
                             Math.abs(gc.calc_extmax.y - gc.calc_extmin.y);
        getVport().vcpx = (gc.calc_extmin.x + gc.calc_extmax.x) / 2.0;
        getVport().vcpy = (gc.calc_extmin.y + gc.calc_extmax.y) / 2.0;
    }
    //==========================================================================


    //==========================================================================
    /**
     * Pan left over the Tilemode1 View and redraw it.
     */
    public
    void commandTilemode1View_pan_l(double panpct)
    {
        getVport().vcpx -= (getVport().vheight * getVport().vaspect) * (panpct / 100.0);

        if (gc.getRenderingJGCFlag())
            commandTilemode1View_redraw();
    }
    //==========================================================================


    //==========================================================================
    /**
     * Pan right over the Tilemode1 View and redraw it.
     */
    public
    void commandTilemode1View_pan_r(double panpct)
    {
        getVport().vcpx += (getVport().vheight * getVport().vaspect) * (panpct / 100.0);

        if (gc.getRenderingJGCFlag())
            commandTilemode1View_redraw();
    }
    //==========================================================================


    //==========================================================================
    /**
     * Pan up over the Tilemode1 View and redraw it.
     */
    public
    void commandTilemode1View_pan_u(double panpct)
    {
        getVport().vcpy += getVport().vheight * (panpct / 100.0);

        if (gc.getRenderingJGCFlag())
            commandTilemode1View_redraw();
    }
    //==========================================================================


    //==========================================================================
    /**
     * Pan down over the Tilemode1 View and redraw it.
     */
    public
    void commandTilemode1View_pan_d(double panpct)
    {
        getVport().vcpy -= getVport().vheight * (panpct / 100.0);

        if (gc.getRenderingJGCFlag())
            commandTilemode1View_redraw();
    }
    //==========================================================================


    //==========================================================================
    /**
     * Rotate the Tilemode1 View around the x coordinate and redraw it.
     */
    public
    void commandTilemode1View_rotate_x_p()
    {
        gc.commandGC_user_view_rotate_x_p();

        if (gc.getRenderingJGCFlag())
            commandTilemode1View_redraw();
    }
    //==========================================================================


    //==========================================================================
    /**
     * Rotate the Tilemode1 View around the x coordinate and redraw it.
     */
    public
    void commandTilemode1View_rotate_x_m()
    {
        gc.commandGC_user_view_rotate_x_m();

        if (gc.getRenderingJGCFlag())
            commandTilemode1View_redraw();
    }
    //==========================================================================


    //==========================================================================
    /**
     * Rotate the Tilemode1 View around the y coordinate and redraw it.
     */
    public
    void commandTilemode1View_rotate_y_p()
    {
        gc.commandGC_user_view_rotate_y_p();

        if (gc.getRenderingJGCFlag())
            commandTilemode1View_redraw();
    }
    //==========================================================================


    //==========================================================================
    /**
     * Rotate the Tilemode1 View around the y coordinate and redraw it.
     */
    public
    void commandTilemode1View_rotate_y_m()
    {
        gc.commandGC_user_view_rotate_y_m();

        if (gc.getRenderingJGCFlag())
            commandTilemode1View_redraw();
    }
    //==========================================================================


    //==========================================================================
    /**
     * Rotate the Tilemode1 View around the z coordinate and redraw it.
     */
    public
    void commandTilemode1View_rotate_z_p()
    {
        gc.commandGC_user_view_rotate_z_p();

        if (gc.getRenderingJGCFlag())
            commandTilemode1View_redraw();
    }
    //==========================================================================


    //==========================================================================
    /**
     * Rotate the Tilemode1 View around the z coordinate and redraw it.
     */
    public
    void commandTilemode1View_rotate_z_m()
    {
        gc.commandGC_user_view_rotate_z_m();

        if (gc.getRenderingJGCFlag())
            commandTilemode1View_redraw();
    }
    //==========================================================================


    //==========================================================================
    // Rotate x, y, z using value

    /**
     * Rotate the Tilemode1 View around the x coordinate and redraw it.
     */
    public
    void commandTilemode1View_rotate_x_value(double value)
    {
        gc.commandGC_user_view_rotate_x_value(value);

        if (gc.getRenderingJGCFlag())
            commandTilemode1View_redraw();
    }

    /**
     * Rotate the Tilemode1 View around the y coordinate and redraw it.
     */
    public
    void commandTilemode1View_rotate_y_value(double value)
    {
        gc.commandGC_user_view_rotate_y_value(value);

        if (gc.getRenderingJGCFlag())
            commandTilemode1View_redraw();
    }

    /**
     * Rotate the Tilemode1 View around the z coordinate and redraw it.
     */
    public
    void commandTilemode1View_rotate_z_value(double value)
    {
        gc.commandGC_user_view_rotate_z_value(value);

        if (gc.getRenderingJGCFlag())
            commandTilemode1View_redraw();
    }
    //==========================================================================


    //==========================================================================
    /**
     * Calculate the position of the Viewport based on the parent Viewport.
     * @return The origination point.
     */
    public
    Point getLocation()
    {
//d     System.out.println("++++++++++++YxxfPrtTilemode1View:getLocation");
        YxxfPrtContainer parent = getParent();

        if (parent == null)
            return super.getLocation();

        Dimension parentsize = parent.getSize();

        int bw = props_PrtTilemode1View.getProperty_int("borderwidth"); // border width

        int x = (int)Math.floor(getVport().llx * (double)(parentsize.width - bw));
        int y = (int)Math.floor(getVport().ury * (double)(parentsize.height - bw));

//d     System.out.println("++++++++++++YxxfPrtTilemode1View:getLocation     ,parentsize=" + parentsize + ",x=" + x + ",y=" + y);
        return new Point (x + bw, (parentsize.height - bw) - y + bw);
    }


    /**
     * Calculate the preferred dimensions of the Viewport based 
     * on the parent Viewport dimensions.
     * @return The Viewport dimensions.
     */
    public
    Dimension getPreferredSize()
    {
//d     System.out.println("++++++++++++YxxfPrtTilemode1View:getPreferredSize");
        YxxfPrtContainer parent = getParent();

        if (parent == null)
            return super.getPreferredSize();

        Dimension parentsize = parent.getSize();

        int bw = props_PrtTilemode1View.getProperty_int("borderwidth"); // border width

        int x1 = (int)Math.floor(getVport().llx * (double)(parentsize.width - bw));
        int x2 = (int)Math.floor(getVport().urx * (double)(parentsize.width - bw));

        int h1 = (int)Math.floor(getVport().lly * (double)(parentsize.height - bw));
        int h2 = (int)Math.floor(getVport().ury * (double)(parentsize.height - bw));

        // System.out.println("++++++++++++YxxfDrwTilemode1View:getPreferredSize,parentsize=" + parentsize + ",x1=" + x1 + ",x2=" + x2 + ",h1=" + h1 + ",h2=" + h2);
        return new Dimension(x2 - (x1 - 1) - 1 - bw, h2 - (h1 - 1) - 1 - bw);
    }


    /**
     * Calculate the minimum size of the Viewport based on the parent Viewport.
     * @return The minimum Viewport dimensions.
     */
    public
    Dimension getMinimumSize()
    {
//d     System.out.println("++++++++++++YxxfPrtTilemode1View:getMinimumSize");
        return getPreferredSize();
    }
    //==========================================================================


    //==========================================================================
    // Painting
    //==========================================================================
    /**
     * Paint the Viewport.
     * @param jgc The Java Graphics object.
     */
    public
    void paint(Graphics jgc)
    {
        // System.out.println("++++++++++++YxxfPrtTilemode1View:paint(" + jgc + "),sharedjgc=" + props_PrtTilemode1View.getProperty_boolean("sharedjgc"));

        gc.commandGC_set_doing_redraw();
    }
    //==========================================================================


    //==========================================================================
    /**
     * Prepare GC for drawing (pre).
     * @return Selected insert.
     */
    public
    YxxfEntInsert setupGC_predraw()
    {
        // Set space
        YxxfEntInsert ins = gc.getDrawing().secEntities.insMSpace;
        gc.setupInsertInit(ins);
        gc.initModelStack();

        // calc transformation and scale to fit window
        gc.calcdsp_vport();


        // Set background color
        String str_bgcolor = props_PrtTilemode1View.getProperty("bgcolor");
        if (str_bgcolor == null)
            gc.setBGColor("#000000");
        else
            gc.setBGColor(str_bgcolor);


        // Set foreground palette
        String str_fgcolor = props_PrtTilemode1View.getProperty("fgcolor");
        if (str_fgcolor == null)
            gc.getPalette().setPalette_aci();
        else
        if (str_fgcolor.equals("aci"))
            gc.getPalette().setPalette_aci();
        else
        if (str_fgcolor.equals("acihighinverse"))
            gc.getPalette().setPalette_acihighinverse();
        else
            gc.getPalette().setPalette_singlecolor(str_fgcolor);


        return ins;
    }


    /**
     * Prepare GC for drawing (post).
     */
    public
    void setupGC_postdraw()
    {
    }
    //==========================================================================
}

