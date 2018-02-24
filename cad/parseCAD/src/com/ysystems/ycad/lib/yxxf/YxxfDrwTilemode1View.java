//==============================================================================
// YxxfDrwTilemode1View.java
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfDrwTilemode1View.java,v 1.35 2003/06/04 11:07:17 ekarlo Exp $
// $Log: YxxfDrwTilemode1View.java,v $
// Revision 1.35  2003/06/04 11:07:17  ekarlo
// Don't do unnecessary redraws when zooming to extents or when rendering is turned off.
//
// Revision 1.34  2003/05/26 02:58:10  ekarlo
// Add methods to turn rendering on and off.
// Improve calculation of extents.
//
// Revision 1.33  2003/05/17 12:42:21  ekarlo
// Add option to control width of border around views.
//
// Revision 1.32  2003/05/08 11:24:03  ekarlo
// Remove warnings.
//
// Revision 1.31  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.30  2002/10/22 00:44:03  ekarlo
// Change comments.
//
// Revision 1.29  2002-10-12 01:09:34-06  ekarlo
// Fix zoom to calc extents command.
//
// Revision 1.28  2002-10-03 03:03:50-06  ekarlo
// Make painting/updating methods consistent.
//
// Revision 1.27  2002-09-28 02:27:27-06  ekarlo
// Fix missing method error on MSIE with built in VM.
//
// Revision 1.26  2002-09-27 02:36:36-06  ekarlo
// Fix missing method error on MSIE with built in VM.
//
// Revision 1.25  2002-09-26 01:51:20-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.24  2002-09-12 23:39:24-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.23  2001-05-21 04:35:47-06  ekarlo
// Imprinter development - draw all view borders.
//
// Revision 1.22  2001-05-20 02:43:06-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.21  2001-05-18 23:14:24-06  ekarlo
// Print test.
//
// Revision 1.20  2001-05-11 22:58:40-06  ekarlo
// Make var name consistent.
//
// Revision 1.19  2000-10-17 01:44:04-06  ekarlo
// Change package paths to lower case.
//
// Revision 1.18  2000-10-16 17:49:31-06  ekarlo
// Browser printing test code.
//
// Revision 1.17  1999-10-22 01:27:57-06  ekarlo
// API rework - phase 1.
//
// Revision 1.16  1999-10-06 20:05:52-06  walter
// Added JavaDoc comments.
//
// Revision 1.15  1999-07-09 14:10:50-06  ekarlo
// Rearrange package names.
//
// Revision 1.14  1999/07/09  15:21:07  ekarlo
// Improve param properties scan.
//
// Revision 1.13  1999/07/06  03:10:00  ekarlo
// Add fields to dbprint.
//
// Revision 1.12  1999/07/06  02:45:37  ekarlo
// Add calc and zoom extents.
//
// Revision 1.11  1999/06/29  19:45:17  ekarlo
// User Interface - phase 2.
//
// Revision 1.10  1999/06/20  22:34:10  ekarlo
// Rearrange package names.
//
// Revision 1.9  1999/06/15  04:57:44  ekarlo
// User Interface - phase 1.
//
// Revision 1.8  1999/02/14  07:33:28  ekarlo
// Merge view controllers into views.
//
// Revision 1.7  1999/02/09  14:52:05  ekarlo
// Deactivate console print.
//
// Revision 1.6  1998/11/24  19:46:26  ekarlo
// Text - phase 2.
//
// Revision 1.5  1998/08/19  01:15:50  ekarlo
// Rename classes to change "Panel" to "View" in name.
//
// Revision 1.4  1998/02/02  19:10:19  ekarlo
// Implement paper space view - phase 2.
//
// Revision 1.3  1997/12/26  21:36:11  ekarlo
// Implement paper space - phase 1.
// Rename properties object.
//
// Revision 1.2  1997/08/30  14:12:47  ekarlo
// Redo redraw action.
//
// Revision 1.1  1997/07/21  22:37:49  ekarlo
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
public class YxxfDrwTilemode1View extends Canvas
{
    //==========================================================================
    /**
     * Properties.
     */
    private
    YutilProperties             props_DrwTilemode1View
                                                = new YutilProperties();
    //==========================================================================


    //==========================================================================
    // Display items.

    /**
     * Graphics context for this view.
     */
    private
    YxxfGfxContext              gc              = new YxxfGfxContext();

    /**
     * Draw thread for this view.
     */
    private
    YxxfDrwTilemode1DrawT       Tdraw;
    //==========================================================================


    //==========================================================================
    /**
     * View Handler.
     */
    private
    YxxfDrwViewHandler          vhandler        = null;

    /**
     * View Monitor.
     */
    private
    YxxfDrwViewMonitor          vhandlermon     = new YxxfDrwViewMonitor();

    /**
     * View Event - Mouse Down.
     */
    private
    YxxfDrwViewHandlerEvent     vhandlerevt_command_mousedown
                                                = new YxxfDrwViewHandlerEvent(
                                                          YxxfDrwViewHandlerEvent.COMMAND_MOUSEDOWN);

    /**
     * View Event - Mouse Drag.
     */
    private
    YxxfDrwViewHandlerEvent     vhandlerevt_command_mousedrag
                                                = new YxxfDrwViewHandlerEvent(
                                                          YxxfDrwViewHandlerEvent.COMMAND_MOUSEDRAG);

    /**
     * View Event - Mouse Up.
     */
    private
    YxxfDrwViewHandlerEvent     vhandlerevt_command_mouseup
                                                = new YxxfDrwViewHandlerEvent(
                                                          YxxfDrwViewHandlerEvent.COMMAND_MOUSEUP);
    //==========================================================================


    //==========================================================================
    // Constructors

    /**
     * Constructor (empty)
     */
    public
    YxxfDrwTilemode1View()
    {
    }

    /**
     * Constructor
     * @param D The Drawing.
     * @param vport The Vport.
     * @param vhandler The View Handler.
     */
    public
    YxxfDrwTilemode1View(Yxxf D, YxxfDrwViewHandler vhandler,
                         YxxfTblVport vport)
    {
        setDrawing(D);
        setViewHandler(vhandler);
        setVport(vport);
    }


    /**
     * Set the Drawing.
     * @param D The Drawing.
     */
    public
    void setDrawing(Yxxf D)
    {
        gc.setDrawing(D);
    }

    /**
     * Get the Drawing.
     * @return The Drawing.
     */
    public
    Yxxf getDrawing()
    {
        return gc.getDrawing();
    }


    /**
     * Set the value of the controlling Viewhandler and connect the monitor observer object.
     * @param vhandler The Viewhandler.
     */
    public
    void setViewHandler(YxxfDrwViewHandler vhandler)
    {
        vhandlermon.deleteObservers();

        this.vhandler = vhandler;

        if (vhandler != null)
            vhandlermon.addObserver(vhandler);
    }

    /**
     * Get the Viewhandler.
     * @return The Viewhandler.
     */
    public
    YxxfDrwViewHandler getViewHandler()
    {
        return vhandler;
    }


    /**
     * Get the Graphics context.
     */
    public
    YxxfGfxContext getGC()
    {
        return gc;
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
        props_DrwTilemode1View.setProperties(args, scanout);
    }


    /**
     * Set properties from argv array.
     * @param args The Properties.
     */
    public
    void setProperties(String argv[])
    {
        props_DrwTilemode1View.setProperties(argv);
    }


    /**
     * Set properties from argprops YutilProperties.
     * @param argprops The Properties.
     */
    public
    void setProperties(YutilProperties argprops)
    {
        props_DrwTilemode1View.setProperties(argprops);
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
//d     System.out.println("++++++++++++YxxfDrwTilemode1View:props_DrwTilemode1View.e BEG");
//d     for (Enumeration e = props_DrwTilemode1View.propertyNames(); e.hasMoreElements(); )
//d     {
//d         String key = (String)e.nextElement();
//d         String val = props_DrwTilemode1View.getProperty(key);
//d         System.out.println("++++++++++++YxxfDrwTilemode1View:key=[" + key + "],val=[" + val + "]");
//d     }
//d     System.out.println("++++++++++++YxxfDrwTilemode1View:props_DrwTilemode1View.e END");

        //
        // Create draw thread
        //
        Tdraw = new YxxfDrwTilemode1DrawT(this, gc);
        Tdraw.setProperties(props_DrwTilemode1View); // Set properties
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
        repaint();
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

        // System.out.println("++++++++++++YxxfDrwTilemode1View:gc_doing_flag_calc_extents=" + gc.gc_doing_flag_calc_extents);
        // System.out.println("++++++++++++YxxfDrwTilemode1View:gc_doing_flag_calc_extents_valid=" + gc.gc_doing_flag_calc_extents_valid);
        // System.out.println("++++++++++++YxxfDrwTilemode1View:gc_doing_flag_zoom_to_calc_extents=" + gc.gc_doing_flag_zoom_to_calc_extents);
        // System.out.println("++++++++++++YxxfDrwTilemode1View:extmin=" + gc.calc_extmin);
        // System.out.println("++++++++++++YxxfDrwTilemode1View:extmax=" + gc.calc_extmax);

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
     * Begin forming the rubber band box on the Tilemode1 View
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param jgc The Java Graphics object.
     */
    public
    void commandTilemode1View_rbb_beg(int x, int y, Graphics jgc)
    {
        gc.commandGC_rbb_beg(x, y, jgc);
    }

    /**
     * Drag the rubber band box on the Tilemode1 View
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public
    void commandTilemode1View_rbb_drag(int x, int y)
    {
        gc.commandGC_rbb_drag(x, y);
    }

    /**
     * End the forming of the rubber band box on the Tilemode1 View
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public
    void commandTilemode1View_rbb_end(int x, int y)
    {
        gc.commandGC_rbb_end(x, y);
    }
    //==========================================================================


    //==========================================================================
    /**
     * Begin rubber band line draw.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param jgc The Java Graphics object.
     */
    public
    void commandTilemode1View_rbl_beg(int x, int y, Graphics jgc)
    {
        gc.commandGC_rbl_beg(x, y, jgc);
    }

    /**
     * Draw rubber band line as cursor is dragged.
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public
    void commandTilemode1View_rbl_drag(int x, int y)
    {
        gc.commandGC_rbl_drag(x, y);
    }

    /**
     * End rubber band line draw.
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public
    void commandTilemode1View_rbl_end(int x, int y)
    {
        gc.commandGC_rbl_end(x, y);
    }
    //==========================================================================


    //==========================================================================
    /**
     * Calculate the position of the Viewport based on the parent Viewport.
     * @return The origination point.
     */
    public
    Point location()
    {
//d     System.out.println("++++++++++++YxxfDrwTilemode1View:location");
        Container parent = getParent();

        if (parent == null)
            return super.location();

        Dimension parentsize = parent.size();

        int bw = props_DrwTilemode1View.getProperty_int("borderwidth"); // border width

        int x = (int)Math.floor(getVport().llx * (double)(parentsize.width - bw));
        int y = (int)Math.floor(getVport().ury * (double)(parentsize.height - bw));

        // System.out.println("++++++++++++YxxfDrwTilemode1View:location     ,parentsize=" + parentsize + ",x=" + x + ",y=" + y);
        return new Point (x + bw, (parentsize.height - bw) - y + bw);
    }


    /**
     * Calculate the preferred dimensions of the Viewport based
     * on the parent Viewport dimensions.
     * @return The Viewport dimensions.
     */
    public
    Dimension preferredSize()
    {
//d     System.out.println("++++++++++++YxxfDrwTilemode1View:preferredSize");
        Container parent = getParent();

        if (parent == null)
            return super.preferredSize();

        Dimension parentsize = parent.size();

        int bw = props_DrwTilemode1View.getProperty_int("borderwidth"); // border width

        int x1 = (int)Math.floor(getVport().llx * (double)(parentsize.width - bw));
        int x2 = (int)Math.floor(getVport().urx * (double)(parentsize.width - bw));

        int h1 = (int)Math.floor(getVport().lly * (double)(parentsize.height - bw));
        int h2 = (int)Math.floor(getVport().ury * (double)(parentsize.height - bw));

        // System.out.println("++++++++++++YxxfDrwTilemode1View:preferredSize,parentsize=" + parentsize + ",x1=" + x1 + ",x2=" + x2 + ",h1=" + h1 + ",h2=" + h2);
        return new Dimension(x2 - (x1 - 1) - 1 - bw, h2 - (h1 - 1) - 1 - bw);
    }


    /**
     * Calculate the minimum size of the Viewport based on the parent Viewport.
     * @return The minimum Viewport dimensions.
     */
    public
    Dimension minimumSize()
    {
//d     System.out.println("++++++++++++YxxfDrwTilemode1View:minimumSize");
        return preferredSize();
    }


//  public
//  void resize(int w, int h)
//  {
//d     System.out.println("++++++++++++YxxfDrwTilemode1View:resize(" + w + "," + h + ")");
//      super.resize(w, h);
//  }


//  public
//  void reshape(int x, int y, int w, int h)
//  {
//d     System.out.println("++++++++++++YxxfDrwTilemode1View:reshape(" + x + "," + y + "," + w + "," + h + ")");
//      super.reshape(x, y, w, h);
//  }


//d public
//d void layout()
//d {
//d     System.out.println("++++++++++++YxxfDrwTilemode1View:layout(" + ")");
//d     System.out.println("                                :layout|isEn=" + isEnabled() +
//d                                                               ",isSh=" + isShowing() +
//d                                                               ",isVa=" + isValid() +
//d                                                               ",isVi=" + isVisible() );
//d     super.layout();
//d }
    //==========================================================================


    //==========================================================================
//  public
//  boolean action(Event evt, Object arg)
//  {
//      System.out.println("++++++++++++YxxfDrwTilemode1View:IN action    ,evt=[" + evt + "]");
//
//      return false;
//  }


//  public
//  boolean gotFocus(Event evt, Object arg)
//  {
//      System.out.println("++++++++++++YxxfDrwTilemode1View:IN gotFocus  ,evt=[" + evt + "]");
//
//      return false;
//  }


//  public
//  boolean lostFocus(Event evt, Object arg)
//  {
//      System.out.println("++++++++++++YxxfDrwTilemode1View:IN lostFocus ,evt=[" + evt + "]");
//
//      return false;
//  }


    /**
     * Respond to a mouse button press event.
     * @param evt The event.
     * @param x The x coordinate of the cursor.
     * @param y The y coordinate of the cursor.
     * @return true since the event was responded to.
     */
    public
    boolean mouseDown(Event evt, int x, int y)
    {
//      System.out.println("++++++++++++YxxfDrwTilemode1View:IN mouseDown ,evt=[" + evt + "],x=" + x + ",y=" + y);
//      System.out.println("++++++++++++YxxfDrwTilemode1View:IN mouseDown x=" + x + ",y=" + y);

        vhandlermon.notifyViewHandler(vhandlerevt_command_mousedown.setArgs(this, getGraphics(), x, y));

        return true;
    }


    /**
     * Respond to a mouse drag while the button is pressed event.
     * @param evt The event.
     * @param x The x coordinate of the cursor.
     * @param y The y coordinate of the cursor.
     * @return true since the event was responded to.
     */
    public
    boolean mouseDrag(Event evt, int x, int y)
    {
//      System.out.println("++++++++++++YxxfDrwTilemode1View:IN mouseDrag ,evt=[" + evt + "],x=" + x + ",y=" + y);
//      System.out.println("++++++++++++YxxfDrwTilemode1View:IN mouseDrag ,x=" + x + ",y=" + y);

        vhandlermon.notifyViewHandler(vhandlerevt_command_mousedrag.setArgs(this, null, x, y));

        return true;
    }


    /**
     * Respond to a mouse button release event.
     * @param evt The event.
     * @param x The x coordinate of the cursor.
     * @param y The y coordinate of the cursor.
     * @return true since the event was responded to.
     */
    public
    boolean mouseUp(Event evt, int x, int y)
    {
//      System.out.println("++++++++++++YxxfDrwTilemode1View:IN mouseUp   ,evt=[" + evt + "],x=" + x + ",y=" + y);
//      System.out.println("++++++++++++YxxfDrwTilemode1View:IN mouseUp   ,x=" + x + ",y=" + y);

        vhandlermon.notifyViewHandler(vhandlerevt_command_mouseup.setArgs(this, null, x, y));

        return true;
    }


//  public
//  boolean mouseEnter(Event evt, int x, int y)
//  {
//      System.out.println("++++++++++++YxxfDrwTilemode1View:IN mouseEnter,evt=[" + evt + "],x=" + x + ",y=" + y);
//      System.out.println("++++++++++++YxxfDrwTilemode1View:IN mouseEnter,x=" + x + ",y=" + y);
//
//      return false;
//  }


//  public
//  boolean mouseExit(Event evt, int x, int y)
//  {
//      System.out.println("++++++++++++YxxfDrwTilemode1View:IN mouseExit ,evt=[" + evt + "],x=" + x + ",y=" + y);
//      System.out.println("++++++++++++YxxfDrwTilemode1View:IN mouseExit ,x=" + x + ",y=" + y);
//
//      return false;
//  }


//  public
//  boolean mouseMove(Event evt, int x, int y)
//  {
//      System.out.println("++++++++++++YxxfDrwTilemode1View:IN mouseMove ,evt=[" + evt + "],x=" + x + ",y=" + y);
//      System.out.println("++++++++++++YxxfDrwTilemode1View:IN mouseMove ,x=" + x + ",y=" + y);
//
//      return false;
//  }
    //==========================================================================


    //==========================================================================
    // Painting
    //==========================================================================
    /**
     * Intercept paint(jgc).
     * @param jgc The Java Graphics object.
     */
    public
    void paint(Graphics jgc)
    {
        // System.out.println("++++++++YxxfDrwTilemode1View:paint   (" + jgc + "),isEn=" + isEnabled() + ",isSh=" + isShowing() + ",isVa=" + isValid() + ",isVi=" + isVisible());
        gc.commandGC_set_doing_redraw();
    }


//  /**
//   * Passthru paintAll(jgc).
//   * @param jgc The Java Graphics object.
//   */
//  public
//  void paintAll(Graphics jgc)
//  {
//      System.out.println("++++++++YxxfDrwTilemode1View:paintAll(" + jgc + "),isEn=" + isEnabled() + ",isSh=" + isShowing() + ",isVa=" + isValid() + ",isVi=" + isVisible());
//      super.paintAll(jgc);
//  }


//  /**
//   * Passthru repaint().
//   * @param jgc The Java Graphics object.
//   */
//  public
//  void repaint()
//  {
//      System.out.println("++++++++YxxfDrwTilemode1View:repaint (),isEn=" + isEnabled() + ",isSh=" + isShowing() + ",isVa=" + isValid() + ",isVi=" + isVisible());
//      super.repaint();
//  }


//  /**
//   * Passthru repaint(x, y, width, height).
//   * @param x X coordinate.
//   * @param y Y coordinate.
//   * @param width Width.
//   * @param height Height.
//   */
//  public
//  void repaint(int x, int y, int width, int height)
//  {
//      System.out.println("++++++++YxxfDrwTilemode1View:repaint (" + x + "," + y + "," + width + "," + height + "),isEn=" + isEnabled() + ",isSh=" + isShowing() + ",isVa=" + isValid() + ",isVi=" + isVisible());
//      super.repaint(x, y, width, height);
//  }


//  /**
//   * Passthru repaint(tm).
//   * @param tm Time in milliseconds.
//   */
//  public
//  void repaint(long tm)
//  {
//      System.out.println("++++++++YxxfDrwTilemode1View:repaint (" + tm + "),isEn=" + isEnabled() + ",isSh=" + isShowing() + ",isVa=" + isValid() + ",isVi=" + isVisible());
//      super.repaint(tm);
//  }


//  /**
//   * Passthru repaint(tm, x, y, width, height).
//   * @param tm Time in milliseconds.
//   * @param x X coordinate.
//   * @param y Y coordinate.
//   * @param width Width.
//   * @param height Height.
//   */
//  public
//  void repaint(long tm, int x, int y, int width, int height)
//  {
//      System.out.println("++++++++YxxfDrwTilemode1View:repaint (" + tm + "," + x + "," + y + "," + width + "," + height + "),isEn=" + isEnabled() + ",isSh=" + isShowing() + ",isVa=" + isValid() + ",isVi=" + isVisible());
//      super.repaint(tm, x, y, width, height);
//  }


    /**
     * Intercept update(jgc).
     * @param jgc The Java Graphics object.
     */
    public
    void update(Graphics jgc)
    {
        // System.out.println("++++++++YxxfDrwTilemode1View:update  (" + jgc + "),isEn=" + isEnabled() + ",isSh=" + isShowing() + ",isVa=" + isValid() + ",isVi=" + isVisible());
        paint(jgc);
    }
    //==========================================================================


    //==========================================================================
    // Printing
    //==========================================================================
    /**
     * Hook for print().
     * Redraw the Viewport into Graphics Object.
     * @param jpc The Java Graphics object for printing.
     */
    public
    void print(Graphics jpc)
    {
        Rectangle jpc_cliprect = jpc.getClipRect();
        System.out.println("++++++++YxxfDrwTilemode1View:print,jpc=" + jpc + ",jpc.getClipRect()=" + jpc_cliprect);

        // Create view
        YxxfPrtTilemode1View
            prt_tilemode1view = new YxxfPrtTilemode1View(getDrawing(), jpc,
                                                         (int)jpc_cliprect.x,
                                                         (int)jpc_cliprect.y,
                                                         (int)jpc_cliprect.width,
                                                         (int)jpc_cliprect.height,
                                                         getVport());

        prt_tilemode1view.setProperties(this.props_DrwTilemode1View);


        // Set print background color
        String str_printbgcolor = props_DrwTilemode1View.getProperty("printbgcolor");
        if (str_printbgcolor == null)
        {   // not set - make it white
            prt_tilemode1view.setProperties("bgcolor=#FFFFFF", false);
        }
        else
        if (str_printbgcolor.equals("bgcolor"))
        {   // match bgcolor
            String str_bgcolor = props_DrwTilemode1View.getProperty("bgcolor");
            if (str_bgcolor == null)
                prt_tilemode1view.setProperties("bgcolor=#000000", false);
            else
                prt_tilemode1view.setProperties("bgcolor=" + str_bgcolor, false);
        }
        else
        {   // explicit value
            prt_tilemode1view.setProperties("bgcolor=" + str_printbgcolor, false);
        }


        // Set print foreground palette
        String str_printfgcolor = props_DrwTilemode1View.getProperty("printfgcolor");
        if (str_printfgcolor == null)
        {   // depends on value of fgcolor
            String str_fgcolor = props_DrwTilemode1View.getProperty("fgcolor");
            if (str_fgcolor == null)
                prt_tilemode1view.setProperties("fgcolor=acihighinverse", false);
            else
                prt_tilemode1view.setProperties("fgcolor=" + str_fgcolor, false);
        }
        else
        if (str_printfgcolor.equals("fgcolor"))
        {   // match fgcolor
            String str_fgcolor = props_DrwTilemode1View.getProperty("fgcolor");
            if (str_fgcolor == null)
                prt_tilemode1view.setProperties("fgcolor=aci", false);
            else
                prt_tilemode1view.setProperties("fgcolor=" + str_fgcolor, false);
        }
        else
        {   // explicit value
            prt_tilemode1view.setProperties("fgcolor=" + str_printfgcolor, false);
        }


        // Render and wait
        prt_tilemode1view.commandTilemode1View_init();
        prt_tilemode1view.commandTilemode1View_start();
        prt_tilemode1view.commandTilemode1View_redraw();
        prt_tilemode1view.commandTilemode1View_wait_for_complete();

        // ToDo: stop prt threads
    }

    /**
     * Hook for printAll().
     * @param jpc The Java Graphics object for printing.
     */
    public
    void printAll(Graphics jpc)
    {
        System.out.println("++++++++YxxfDrwTilemode1View:printAll,jpc=" + jpc);
        super.printAll(jpc);
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
        gc.setDspwin(this.size());
        gc.calcdsp_vport();

        // Set the Java Graphics Context
        gc.setJGC(this.getGraphics());


        // Set background color
        String str_bgcolor = props_DrwTilemode1View.getProperty("bgcolor");
        if (str_bgcolor == null)
            gc.setBGColor("#000000");
        else
            gc.setBGColor(str_bgcolor);


        // Set foreground palette
        String str_fgcolor = props_DrwTilemode1View.getProperty("fgcolor");
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
        // Shutdown graphics context for screen view
        gc.getJGC().dispose();
    }
    //==========================================================================
}


//==============================================================================
//=== Attic ====================================================================
//==============================================================================

/* ===
        jpc.setColor(Color.white);

        jpc.fillRect((int)cliprect.x,
                     (int)cliprect.y,
                     (int)cliprect.width,
                     (int)cliprect.height);

        jpc.fillRect(  0,   0, 636, 454);

        jpc.fillRect((int)cliprect.x + 4,
                     (int)cliprect.y + 4,
                     (int)cliprect.width - 8,
                     (int)cliprect.height - 8);

        jpc.setColor(Color.black);
        jpc.fillRect(  0,   0, 110, 110);

        jpc.setColor(Color.darkGray);
        jpc.fillRect(  0,   0, 100, 100);

        jpc.setColor(Color.gray);
        jpc.fillRect(  0,   0,  90,  90);

        jpc.setColor(Color.lightGray);
        jpc.fillRect(  0,   0,  80,  80);

        jpc.setColor(Color.white);
        jpc.fillRect(  0,   0,  70,  70);

        jpc.setColor(Color.red);
        jpc.fillRect(  0,   0,  60,  60);

        jpc.setColor(Color.green);
        jpc.fillRect(  0,   0,  50,  50);

        jpc.setColor(Color.blue);
        jpc.fillRect(  0,   0,  40,  40);

        jpc.setColor(Color.cyan);
        jpc.fillRect(  0,   0,  30,  30);

        jpc.setColor(Color.magenta);
        jpc.fillRect(  0,   0,  20,  20);

        jpc.setColor(Color.yellow);
        jpc.fillRect(  0,   0,  10,  10);
=== */
