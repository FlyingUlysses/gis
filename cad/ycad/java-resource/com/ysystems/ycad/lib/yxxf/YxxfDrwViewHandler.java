//==============================================================================
// YxxfDrwViewHandler.java
//
// View Handler
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfDrwViewHandler.java,v 1.50 2003/05/26 02:58:10 ekarlo Exp $
// $Log: YxxfDrwViewHandler.java,v $
// Revision 1.50  2003/05/26 02:58:10  ekarlo
// Add methods to turn rendering on and off.
// Improve calculation of extents.
//
// Revision 1.49  2003/05/19 05:31:19  ekarlo
// Fix off-by-one (top and left) view error.
//
// Revision 1.48  2003/05/17 12:42:21  ekarlo
// Add option to control width of border around views.
//
// Revision 1.47  2003/05/16 05:12:02  ekarlo
// Add options to control border view colors.
// Add methods to set current view.
//
// Revision 1.46  2003/05/08 11:24:03  ekarlo
// Remove warnings.
//
// Revision 1.45  2003/05/02 09:03:47  ekarlo
// Add zoom percent value methods.
//
// Revision 1.44  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.43  2002/10/30 02:22:58  ekarlo
// Rearrange initialization of option defaults.
//
// Revision 1.42  2002-10-29 18:15:49-07  ekarlo
// Fix showtoolbar option.
//
// Revision 1.41  2002-10-21 18:24:47-06  ekarlo
// Add fields to liststats for tilemode 0.
//
// Revision 1.40  2002-10-13 13:30:02-06  ekarlo
// Fix initial zoom to calc extents when no vport present.
//
// Revision 1.39  2002-09-28 23:20:53-06  ekarlo
// Change prefix of entity print options to "list".
//
// Revision 1.38  2002-09-27 02:36:35-06  ekarlo
// Fix missing method error on MSIE with built in VM.
//
// Revision 1.37  2002-09-26 02:05:47-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.36  2002-09-12 15:11:11-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.35  2001-07-04 22:46:13-06  ekarlo
// Do case insensitive compare for active vport.
// Found in A2000 test file during Coventor evaluation.
//
// Revision 1.34  2001-05-21 04:35:46-06  ekarlo
// Imprinter development - draw all view borders.
//
// Revision 1.33  2001-05-17 17:28:10-06  ekarlo
// Rename command class.
//
// Revision 1.32  2001-05-11 22:58:45-06  ekarlo
// Make var name consistent.
//
// Revision 1.31  2000-10-17 01:44:02-06  ekarlo
// Change package paths to lower case.
//
// Revision 1.30  1999-10-22 01:27:52-06  ekarlo
// API rework - phase 1.
//
// Revision 1.29  1999-09-28 11:08:33-06  walter
// Add JavaDoc comments.
//
// Revision 1.28  1999-09-22 13:30:48-06  ekarlo
// Fix comment.
//
// Revision 1.27  1999-09-15 10:07:02-06  walter
// Added JavaDoc comments.
//
// Revision 1.26  1999-08-10 08:08:14-06  ekarlo
// Remove unused var warning.
//
// Revision 1.25  1999/07/25  23:23:40  ekarlo
// Fix timing problem.
//
// Revision 1.24  1999/07/09  20:10:50  ekarlo
// Rearrange package names.
//
// Revision 1.23  1999/07/09  15:21:07  ekarlo
// Improve param properties scan.
//
// Revision 1.22  1999/07/07  03:15:49  ekarlo
// Create default vport if none found and zoom to extents.
//
// Revision 1.21  1999/07/06  03:10:00  ekarlo
// Add fields to dbprint.
//
// Revision 1.20  1999/07/06  02:45:37  ekarlo
// Add calc and zoom extents.
//
// Revision 1.19  1999/06/29  19:44:34  ekarlo
// Move dbprint command to general toolbar line.
//
// Revision 1.18  1999/06/20  22:34:10  ekarlo
// Rearrange package names.
//
// Revision 1.17  1999/06/15  04:58:33  ekarlo
// User Interface - phase 1.
//
// Revision 1.16  1999/02/14  07:33:28  ekarlo
// Merge view controllers into views.
//
// Revision 1.15  1999/02/09  14:50:20  ekarlo
// Deactivate console print.
//
// Revision 1.14  1999/02/08  05:10:53  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.13  1999/01/28  04:26:01  ekarlo
// Text - phase 4.
//
// Revision 1.12  1998/12/21  15:43:26  ekarlo
// Text - phase 3.
//
// Revision 1.11  1998/11/24  19:51:08  ekarlo
// Text - phase 2.
//
// Revision 1.10  1998/08/25  18:15:06  ekarlo
// Add status display.
//
// Revision 1.9  1998/08/24  20:41:06  ekarlo
// Add status display.
//
// Revision 1.8  1998/08/19  01:18:43  ekarlo
// Rename classes to change "Panel" to "View" in name.
//
// Revision 1.7  1998/02/02  19:01:52  ekarlo
// Implement paper space - phase 2.
// Rename view handler.
//
// Revision 1.6  1997/12/26  21:35:29  ekarlo
// Implement paper space - phase 1.
// Rename properties object.
//
// Revision 1.5  1997/08/30  14:10:37  ekarlo
// Add Vport Handler event processing.
// Redo redraw action.
// Change user properties.
//
// Revision 1.4  1997/07/21  22:35:50  ekarlo
// MVC-VH rework - phase 2.
//
// Revision 1.3  1997/07/18  11:36:03  ekarlo
// Rename from YxxfDrwViewHandler.java to YxxfDrwVportHandler.java
//
// Revision 1.2  1997/07/18  11:31:58  ekarlo
// Redraw code changes.
//
// Revision 1.1  1997/07/13  17:58:38  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


import java.awt.*;
import java.util.*;

import com.ysystems.lib.yutil.*;


/**
 * View Handler is responsible for responding to events that are generated by
 * the toolbar and the drawing.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfDrwViewHandler implements Observer
{
    //==========================================================================
    /**
     * Properties
     */
    private
    YutilProperties             props_DrwViewHandler
                                                = new YutilProperties();
    //==========================================================================


    //==========================================================================
    // Views

    /**
     * Drawing Container.
     */
    private YxxfDrwView         viewer          = null;

    /**
     * Toolbar part of viewer.
     */
    private YxxfDrwToolbarView  vtoolbar        = null;

    /**
     * Drawing part of viewer.
     */
    private YxxfDrwDrawingView  vdrawing        = null;


    /**
     * Tilemode 0 view list - created here
     * Contains list of all paper space views.
     */
    public
    Vector                      tilemode0viewlist
                                                = new Vector();

    /**
     * Tilemode 1 view list contains all model space views.
     */
    public
    Vector                      tilemode1viewlist
                                                = new Vector();

    /**
     * Current active view.
     * Not initially set but first in list is active unless
     * user selected otherwise.
     */
    private
    YxxfDrwTilemode1View        tilemode1viewcurr
                                                = null;

    /**
     * Last active drawn view.
     * Not initially set.
     */
    private
    YxxfDrwTilemode1View        tilemode1viewlast
                                                = null;
    //==========================================================================


    //==========================================================================
    // Constructors

    /**
     * Constructor (empty)
     */
    public
    YxxfDrwViewHandler()
    {
        setDefaultProperties();
    }

    /**
     * Set the value of the Drawing.
     * @param D The Drawing.
     */
    public
    void setDrawing(Yxxf D)
    {
        viewer.getGC().setDrawing(D);
    }

    /**
     * Get the Drawing.
     * @return The Drawing.
     */
    public
    Yxxf getDrawing()
    {
        return viewer.getGC().getDrawing();
    }

    /**
     * Get drawing viewer.
     * @return The Viewer.
     */
    public
    YxxfDrwView getViewer()
    {
        return viewer;
    }
    //==========================================================================


    //==========================================================================
    // Set properties

    /**
     * Set initial default properties.
     */
    private
    void setDefaultProperties()
    {
        props_DrwViewHandler.setPropertiesDefaults("bgcolor=#000000", false);
        props_DrwViewHandler.setPropertiesDefaults("fgcolor=aci", false);

        props_DrwViewHandler.setPropertiesDefaults("printbgcolor=#FFFFFF", false);
        props_DrwViewHandler.setPropertiesDefaults("printfgcolor=acihighinverse", false);

        props_DrwViewHandler.setPropertiesDefaults("borderwidth=2", false);
        props_DrwViewHandler.setPropertiesDefaults("borderactivecolor=#FFFF00", false);
        props_DrwViewHandler.setPropertiesDefaults("borderinactivecolor=#0000FF", false);

        props_DrwViewHandler.setPropertiesDefaults("listheader=false", false);

        props_DrwViewHandler.setPropertiesDefaults("listlayers=false", false);
        props_DrwViewHandler.setPropertiesDefaults("listltypes=false", false);
        props_DrwViewHandler.setPropertiesDefaults("listvports=false", false);
        props_DrwViewHandler.setPropertiesDefaults("liststyles=false", false);
        props_DrwViewHandler.setPropertiesDefaults("listblocks=false", false);

        props_DrwViewHandler.setPropertiesDefaults("listentities=false", false);

        props_DrwViewHandler.setPropertiesDefaults("drawdspminmax=false", false);
        props_DrwViewHandler.setPropertiesDefaults("drawextminmax=false", false);
        props_DrwViewHandler.setPropertiesDefaults("drawlimminmax=false", false);

        props_DrwViewHandler.setPropertiesDefaults("showtoolbar=true", false);
    }

    /**
     * Set properties from args string.
     * @param args The collection of properties.
     * @param scanout Scan for multiple arguments.
     */
    public
    void setProperties(String args, boolean scanout)
    {
        props_DrwViewHandler.setProperties(args, scanout);
    }

    /**
     * Set properties from argv array.
     * @param argv The collection of properties.
     */
    public
    void setProperties(String argv[])
    {
        props_DrwViewHandler.setProperties(argv);
    }

    /**
     * Set properties from argprops YutilProperties
     * @param argprops The collection of properties.
     */
    public
    void setProperties(YutilProperties argprops)
    {
        props_DrwViewHandler.setProperties(argprops);
    }
    //==========================================================================


    //==========================================================================
    // Drawing view ready wait/set/get

    /**
     * Drawing view ready flag
     */
    private
    boolean                     drawingViewReady = false;

    /**
     * Wait until drawing is complete.
     */
    public synchronized
    void waitDrawingViewReady()
    {
        if (drawingViewReady)
            return;

        while (true)
        {
            try { wait(); }
            catch(InterruptedException e) { System.out.println(e); }

            if (drawingViewReady)
                return;
        }
    }


    /**
     * Set the value of the complete flag and tell listeners it's changed.
     * @param complete The value of the complete flag
     * @return true if complete 
     */
    public synchronized
    boolean setDrawingViewReady(boolean drawingViewReady)
    {
        this.drawingViewReady = drawingViewReady;

        // Mark lists as complete
//      secEntities.insPSpace.block.setBlockComplete(this.complete);
//      secEntities.insMSpace.block.setBlockComplete(this.complete);

        // Notify it's good to go
//      secEntities.insPSpace.block.drawNotify();
//      secEntities.insMSpace.block.drawNotify();

        notifyAll();

        return this.drawingViewReady;
    }


    /**
     * Get the value of complete flag
     * @return true if complete
     */
    public // synchronized
    boolean getDrawingViewReady()
    {
        return drawingViewReady;
    }
    //==========================================================================


    //==========================================================================
    /**
     * Observer method,
     * called from various places when view changes.
     * <br>
     * <UL>
     *    <LI> Tilemode 1
     *    <UL>
     *       <LI>At beginning of entities section.
     *
     *       <LI>For each active vport:
     *       <UL>
     *          <LI>1) Create tilemode 1 view
     *          <LI>2) init view
     *          <LI>3) start view
     *       </UL>
     *       <LI>Do command Redraw
     *    </UL>
     *
     *    <LI> Tilemode 0<br>
     *    <UL>
     *       <LI>At end of entities section.
     *
     *       <LI>For each active viewport:
     *       <UL>
     *          <LI>1) Create tilemode 0 view.
     *       </UL>
     *       <LI>Start single draw thread.
     *       <UL>
     *          <LI>Do command init.
     *          <LI>Do command start.
     *          <LI>Do command Redraw.
     *       </UL>
     *     </UL>
     * </UL>
     * @param obs The Observable.
     * @param arg The Drawing View Handler Event.
     */
    public
    void update(Observable obs, Object arg)
    {
        // Get event type
        YxxfDrwViewHandlerEvent vhevt = (YxxfDrwViewHandlerEvent)arg;
        int type = vhevt.getType();
        // System.out.println("++++++++++YxxfDrwViewHandler:BEG obs update,type=" + type);


        // Initialize View Handler
        if (type == YxxfDrwViewHandlerEvent.COMMAND_INIT)
        {
            vtoolbar.commandShowText("BEG Command Init");

            Object obj = vhevt.getObj1();
            commandViewHandler_command_init((Yxxf)obj);

            vtoolbar.commandShowText("END Command Init");
        }
        else


        // Start View Handler
        if (type == YxxfDrwViewHandlerEvent.COMMAND_START)
        {
            vtoolbar.commandShowText("BEG Command Start");

            commandViewHandler_command_start();

            vtoolbar.commandShowText("END Command Start");
        }
        else

        // Initialize Drawing
        if (type == YxxfDrwViewHandlerEvent.COMMAND_INIT_DRAWING)
        {
            vtoolbar.commandShowText("BEG Command Init Drawing");

            commandViewHandler_command_init_drawing();

            vtoolbar.commandShowText("END Command Init Drawing");
        }
        else

        // Start Drawing
        if (type == YxxfDrwViewHandlerEvent.COMMAND_START_DRAWING)
        {
            vtoolbar.commandShowText("BEG Command Start Drawing");

            commandViewHandler_command_start_drawing();

            vtoolbar.commandShowText("END Command Start Drawing");
        }
        else

        // List stats
        if (type == YxxfDrwViewHandlerEvent.COMMAND_LIST_STATS)
        {
            commandViewHandler_command_list_stats();
        }
        else

        // Set rendering
        if (type == YxxfDrwViewHandlerEvent.COMMAND_SET_RENDERING)
        {
            commandViewHandler_command_set_rendering((vhevt.getInt1() != 0) ? true : false);
        }
        else




        // Set current view by number
        if (type == YxxfDrwViewHandlerEvent.COMMAND_SET_CURR_VIEW_NUM)
        {
            commandViewHandler_command_set_curr_view_num(vhevt.getInt1());
        }
        else

        // Set current view by view
        if (type == YxxfDrwViewHandlerEvent.COMMAND_SET_CURR_VIEW)
        {
            commandViewHandler_command_set_curr_view((YxxfDrwTilemode1View)vhevt.getObj1());
        }
        else

        // Set and highlight current view
        if (type == YxxfDrwViewHandlerEvent.COMMAND_HILITE_CURR_VIEW)
        {
            commandViewHandler_command_hilite_curr_view((YxxfDrwTilemode1View)vhevt.getObj1());
        }
        else

        // Highlight all views
        if (type == YxxfDrwViewHandlerEvent.COMMAND_HILITE_ALL_VIEWS)
        {
            commandViewHandler_command_hilite_all_views();
        }
        else

        // Mouse down - set current view and hilite
        if (type == YxxfDrwViewHandlerEvent.COMMAND_MOUSEDOWN)
        {
            commandViewHandler_command_mousedown(vhevt.getInt1(), vhevt.getInt2(),
                                                 (YxxfDrwTilemode1View)vhevt.getObj1(), (Graphics)vhevt.getObj2());
        }
        else

        // Mouse drag
        if (type == YxxfDrwViewHandlerEvent.COMMAND_MOUSEDRAG)
        {
            commandViewHandler_command_mousedrag(vhevt.getInt1(), vhevt.getInt2());
        }
        else

        // Mouse up
        if (type == YxxfDrwViewHandlerEvent.COMMAND_MOUSEUP)
        {
            commandViewHandler_command_mouseup(vhevt.getInt1(), vhevt.getInt2());
        }
        else




        // Start
        if (type == YxxfDrwViewHandlerEvent.TOOLBAR_START)
        {
            commandViewHandler_toolbar_start();
        }
        else

        // Redraw
        if (type == YxxfDrwViewHandlerEvent.TOOLBAR_REDRAW)
        {
            commandViewHandler_toolbar_redraw();
        }
        else

        // Restore
        if (type == YxxfDrwViewHandlerEvent.TOOLBAR_RESTORE)
        {
            commandViewHandler_toolbar_restore();
        }
        else

        // Calc Extents
        if (type == YxxfDrwViewHandlerEvent.TOOLBAR_CALC_EXTENTS)
        {
            commandViewHandler_toolbar_calc_extents();
        }
        else

        // List Stats
        if (type == YxxfDrwViewHandlerEvent.TOOLBAR_LISTSTATS)
        {
            commandViewHandler_toolbar_liststats();
        }
        else

        // Print
        if (type == YxxfDrwViewHandlerEvent.TOOLBAR_PRINT)
        {
            commandViewHandler_toolbar_print();
        }
        else




        // Zoom In
        if (type == YxxfDrwViewHandlerEvent.TOOLBAR_ZOOM_IN)
        {
            commandViewHandler_toolbar_zoom_in();
        }
        else

        // Zoom Out
        if (type == YxxfDrwViewHandlerEvent.TOOLBAR_ZOOM_OUT)
        {
            commandViewHandler_toolbar_zoom_out();
        }
        else

        // Zoom to Header Extents
        if (type == YxxfDrwViewHandlerEvent.TOOLBAR_ZOOM_TO_HEAD_EXTENTS)
        {
            commandViewHandler_toolbar_zoom_to_head_extents();
        }
        else

        // Zoom to Calc Extents
        if (type == YxxfDrwViewHandlerEvent.TOOLBAR_ZOOM_TO_CALC_EXTENTS)
        {
            commandViewHandler_toolbar_zoom_to_calc_extents();
        }
        else




        // Pan Left
        if (type == YxxfDrwViewHandlerEvent.TOOLBAR_PAN_L)
        {
            commandViewHandler_toolbar_pan_l();
        }
        else

        // Pan Right
        if (type == YxxfDrwViewHandlerEvent.TOOLBAR_PAN_R)
        {
            commandViewHandler_toolbar_pan_r();
        }
        else

        // Pan Up
        if (type == YxxfDrwViewHandlerEvent.TOOLBAR_PAN_U)
        {
            commandViewHandler_toolbar_pan_u();
        }
        else

        // Pan Down
        if (type == YxxfDrwViewHandlerEvent.TOOLBAR_PAN_D)
        {
            commandViewHandler_toolbar_pan_d();
        }
        else




        // Rotate x plus
        if (type == YxxfDrwViewHandlerEvent.TOOLBAR_ROTATE_X_P)
        {
            commandViewHandler_toolbar_rotate_x_p();
        }
        else

        // Rotate x minus
        if (type == YxxfDrwViewHandlerEvent.TOOLBAR_ROTATE_X_M)
        {
            commandViewHandler_toolbar_rotate_x_m();
        }
        else

        // Rotate y plus
        if (type == YxxfDrwViewHandlerEvent.TOOLBAR_ROTATE_Y_P)
        {
            commandViewHandler_toolbar_rotate_y_p();
        }
        else

        // Rotate y minus
        if (type == YxxfDrwViewHandlerEvent.TOOLBAR_ROTATE_Y_M)
        {
            commandViewHandler_toolbar_rotate_y_m();
        }
        else

        // Rotate z plus
        if (type == YxxfDrwViewHandlerEvent.TOOLBAR_ROTATE_Z_P)
        {
            commandViewHandler_toolbar_rotate_z_p();
        }
        else

        // Rotate z minus
        if (type == YxxfDrwViewHandlerEvent.TOOLBAR_ROTATE_Z_M)
        {
            commandViewHandler_toolbar_rotate_z_m();
        }
        else




        // Rotate x value
        if (type == YxxfDrwViewHandlerEvent.TOOLBAR_ROTATE_X_VALUE)
        {
            commandViewHandler_toolbar_rotate_x_value(vhevt.getDbl1());
        }
        else

        // Rotate y value
        if (type == YxxfDrwViewHandlerEvent.TOOLBAR_ROTATE_Y_VALUE)
        {
            commandViewHandler_toolbar_rotate_y_value(vhevt.getDbl1());
        }
        else

        // Rotate z vlaue
        if (type == YxxfDrwViewHandlerEvent.TOOLBAR_ROTATE_Z_VALUE)
        {
            commandViewHandler_toolbar_rotate_z_value(vhevt.getDbl1());
        }
        else




        // Beginning of header section
        if (type == YxxfDrwViewHandlerEvent.GET_SECHEADER_BEG)
        {
            vtoolbar.commandShowText("BEG HEADER Section");
        }
        else

        // Ending of header section
        if (type == YxxfDrwViewHandlerEvent.GET_SECHEADER_END)
        {
            vtoolbar.commandShowText("END HEADER Section");
        }
        else

        // Beginning of tables section
        if (type == YxxfDrwViewHandlerEvent.GET_SECTABLES_BEG)
        {
            vtoolbar.commandShowText("BEG TABLES Section");
        }
        else

        // Ending of tables section
        if (type == YxxfDrwViewHandlerEvent.GET_SECTABLES_END)
        {
            vtoolbar.commandShowText("END TABLES Section");
        }
        else

        // Beginning of table
        if (type == YxxfDrwViewHandlerEvent.GET_TBLNAME_BEG)
        {
            Object obj = vhevt.getObj1();
            vtoolbar.commandShowText("BEG TABLE " + ((obj != null) ? (String)obj : ""));
        }
        else

        // Ending of table
        if (type == YxxfDrwViewHandlerEvent.GET_TBLNAME_END)
        {
            // ect obj = vhevt.getObj1();
            // olbar.commandShowText("END TABLE " + ((obj != null) ? (String)obj : ""));
        }
        else

        // Beginning of blocks section
        if (type == YxxfDrwViewHandlerEvent.GET_SECBLOCKS_BEG)
        {
            vtoolbar.commandShowText("BEG BLOCKS Section");
        }
        else

        // Ending of blocks section
        if (type == YxxfDrwViewHandlerEvent.GET_SECBLOCKS_END)
        {
            vtoolbar.commandShowText("END BLOCKS Section");
        }
        else

        // Beginning of block
        if (type == YxxfDrwViewHandlerEvent.GET_BLKNAME_BEG)
        {
            Object obj = vhevt.getObj1();
            vtoolbar.commandShowText("BEG BLOCK " + ((obj != null) ? (String)obj : ""));
        }
        else

        // Ending of block
        if (type == YxxfDrwViewHandlerEvent.GET_BLKNAME_END)
        {
            // ect obj = vhevt.getObj1();
            // olbar.commandShowText("END BLOCK " + ((obj != null) ? (String)obj : ""));
        }
        else

        // Beginning of entities section
        if (type == YxxfDrwViewHandlerEvent.GET_SECENTITIES_BEG)
        {
            vtoolbar.commandShowText("BEG ENTITIES Section");
        }
        else

        // End of entities section
        if (type == YxxfDrwViewHandlerEvent.GET_SECENTITIES_END)
        {
            vtoolbar.commandShowText("END ENTITIES Section");
        }
        else

        // End of File
        if (type == YxxfDrwViewHandlerEvent.GET_DRAWING_EOF)
        {
            vtoolbar.commandShowText("EOF  PSpace=" + getDrawing().secEntities.insPSpace.block.size() +
                                        "  MSpace=" + getDrawing().secEntities.insMSpace.block.size());
        }


        // System.out.println("++++++++++YxxfDrwViewHandler:END obs update,type=" + type);
    }
    //==========================================================================




    //==========================================================================
    /**
     * Initialize this View Handler.
     */
    public
    void commandViewHandler_command_init(Yxxf D)
    {
//d     System.out.println("++++++++++YxxfDrwViewHandler:BEG commandViewHandler_command_init");

        //
        // Create viewer Container - Drawing reference stored in viewer
        //
        viewer = new YxxfDrwView(D, this);

        // Set parameters in viewer
        // Copy in all properties from props_DrwViewHandler
        for (Enumeration ep = props_DrwViewHandler.propertyNames(); ep.hasMoreElements(); )
        {
            String key = (String)ep.nextElement();
            viewer.setProperties(key + "=" + props_DrwViewHandler.getProperty(key), false);
        }

        viewer.commandInit();


        //
        // Create toolbar view
        //
        vtoolbar = new YxxfDrwToolbarView();
//d     System.out.println("++++++++++YxxfDrwViewHandler:init|vtoolbar:a=[" + vtoolbar + "]");

        // Set parameters in vtoolbar
        // Only bgcolor used
        for (Enumeration ep = props_DrwViewHandler.propertyNames(); ep.hasMoreElements(); )
        {
            String key = (String)ep.nextElement();

            if (key.equalsIgnoreCase("bgcolor"))
            {
                String val = key + "=" + props_DrwViewHandler.getProperty(key);
                vtoolbar.setProperties(val, false);
                break;
            }
        }

        vtoolbar.commandInit();


        //
        // Create drawing view container
        //
        vdrawing = new YxxfDrwDrawingView(getDrawing(), this);
//d     System.out.println("++++++++++YxxfDrwViewHandler:init|vdrawing:a=[" + vdrawing + "]");

        // Set parameters in vdrawing
        // Copy in all properties from props_DrwViewHandler
        for (Enumeration ep = props_DrwViewHandler.propertyNames(); ep.hasMoreElements(); )
        {
            String key = (String)ep.nextElement();

            String val = key + "=" + props_DrwViewHandler.getProperty(key);
            vdrawing.setProperties(val, false);
        }

        vdrawing.commandDrawingView_init();


//d     System.out.println("++++++++++YxxfDrwViewHandler:END commandViewHandler_command_init");
    }
    //==========================================================================


    //==========================================================================
    /**
     * Start this View Handler.
     */
    public
    void commandViewHandler_command_start()
    {
//d     System.out.println("++++++++++YxxfDrwViewHandler:BEG commandViewHandler_command_start");

        // Add components to viewer
        viewer.setLayout(new GridBagLayout());


        // Position the tool bar - add only if option is set
        if (props_DrwViewHandler.getProperty_boolean("showtoolbar"))
        {
            YutilAWT.constrain(viewer, vtoolbar,
                0, // gridx
                0, // gridy

                GridBagConstraints.REMAINDER, // gridwidth
                GridBagConstraints.RELATIVE, // gridheight

                GridBagConstraints.HORIZONTAL, // fill
                GridBagConstraints.NORTHWEST, // anchor

                0, 0, // weightx, weighty
                0, 0, // ipadx, ipady
                0, 0, 0, 0); // Insets T,L,B,R
        }


        // Position the drawing area
        YutilAWT.constrain(viewer, vdrawing,
            0, // gridx
            GridBagConstraints.RELATIVE, // gridy

            GridBagConstraints.REMAINDER, // gridwidth
            GridBagConstraints.REMAINDER, // gridheight

            GridBagConstraints.BOTH, // fill
            GridBagConstraints.NORTHWEST, // anchor

            1, 1, // weightx, weighty
            0, 0, // ipadx, ipady
            0, 0, 0, 0); // Insets T,L,B,R


        // Validate
        viewer.validate();


        // Start it up
        vtoolbar.commandStart();
        viewer.commandStart();


        // Connect this view handler to the toolbar
        // Receive events from toolbar
        vtoolbar.setViewHandler(this);

        // Connect drawing to view handler
        // Receive events from drawing
        getDrawing().addObserver(this);

//d     System.out.println("++++++++++YxxfDrwViewHandler:END commandViewHandler_command_start");
    }
    //==========================================================================


    //==========================================================================
    /**
     * Init drawing.
     */
    public
    void commandViewHandler_command_init_drawing()
    {
//d     System.out.println("++++++++++YxxfDrwViewHandler:BEG commandViewHandler_command_init");

        // --- Display extent box if requested
        if (props_DrwViewHandler.getProperty_boolean("drawextminmax"))
        {
            YxxfEntxCommand cmd = new YxxfEntxCommand(YxxfEntxCommand.COMMAND_DRAWEXTMINMAX);
            cmd.hdr_layer = getDrawing().secTables.findLayer_add("**MODEL_SPACE_ROOT_LAYER");
            cmd.hdr_aci = YxxfGfxPalette.ACI_YELLOW;
            cmd.calc(getDrawing());
            getDrawing().secEntities.insMSpace.block.addEntity(cmd);
        }

        // --- Display limit box if requested
        if (props_DrwViewHandler.getProperty_boolean("drawlimminmax"))
        {
            YxxfEntxCommand cmd = new YxxfEntxCommand(YxxfEntxCommand.COMMAND_DRAWLIMMINMAX);
            cmd.hdr_layer = getDrawing().secTables.findLayer_add("**MODEL_SPACE_ROOT_LAYER");
            cmd.hdr_aci = YxxfGfxPalette.ACI_RED;
            cmd.calc(getDrawing());
            getDrawing().secEntities.insMSpace.block.addEntity(cmd);
        }

//d     System.out.println("++++++++++YxxfDrwViewHandler:END commandViewHandler_command_init");
    }
    //==========================================================================


    //==========================================================================
    /**
     * Start drawing.
     */
    public
    void commandViewHandler_command_start_drawing()
    {
//d     System.out.println("++++++++++YxxfDrwViewHandler:BEG commandViewHandler_command_start_drawing");

        if (getDrawing().secHeader.tilemode == 1)
        {
//d         System.out.println("++++++++++YxxfDrwViewHandler:t1:BEG Vport size=" + getDrawing().secTables.tblVport.size());

            // --- Scan entire vport table and create vport views for each active vport
            Enumeration E = getDrawing().secTables.tblVport.elements();
            YxxfTblVport vport;
            while (E.hasMoreElements())
            {
                vport = (YxxfTblVport)E.nextElement();
//d             System.out.println("++++++++++YxxfDrwViewHandler:t1:Vport =====================================");
//d             System.out.println(vport);
//d             System.out.println("++++++++++YxxfDrwViewHandler:t1:Vport =====================================");
//d             System.out.println("++++++++++YxxfDrwViewHandler:t1:get_vport_table");

                // Use only if "*ACTIVE"
                if (!vport.name.equalsIgnoreCase("*ACTIVE"))
                {
//d                 System.out.println("++++++++++YxxfDrwViewHandler:t1:update called vport.name=[" + vport.name + "],skipped");
                    continue;
                }
//d             System.out.println("++++++++++YxxfDrwViewHandler:t1:update called vport.name=[" + vport.name + "]");

//d             getDrawing().genPoint(getDrawing().secEntities.insMSpace.block, YxxfGfxPalette.ACI_WHITE, vport.vtgt);

                // Create view
                YxxfDrwTilemode1View tilemode1view = new YxxfDrwTilemode1View(getDrawing(), this, vport);
                tilemode1view.setProperties(props_DrwViewHandler);

                tilemode1view.commandTilemode1View_init();

                vdrawing.add(tilemode1view); // Add to drawing view

                // Add to tilemode 1 view list
                tilemode1viewlist.addElement(tilemode1view);
            }


            // Check if any active, valid vports found and
            // create one if necessary.
            if (tilemode1viewlist.size() == 0)
            {
//d             System.out.println("++++++++++YxxfDrwViewHandler:NO VPORT FOUND - GENERATING EXTENTS VIEW");

                // Generate vport and set default values for display
                vport = new YxxfTblVport("*YCAD-GENERATED");
                vport.llx = 0.0;
                vport.lly = 0.0;
                vport.urx = 1.0;
                vport.ury = 1.0;
                vport.vtgt.set(0, 0, 0);
                vport.vdir.set(YxxfGfxPointW.Wz);
                vport.vheight = 1.0;
                vport.vaspect = 1.0;

                getDrawing().secTables.tblVport.addElement(vport);

                // Create view
                YxxfDrwTilemode1View tilemode1view = new YxxfDrwTilemode1View(getDrawing(), this, vport);
                tilemode1view.setProperties(props_DrwViewHandler);

                tilemode1view.commandTilemode1View_init();
                tilemode1view.getGC().gc_doing_flag_calc_extents = true;
                tilemode1view.getGC().gc_doing_flag_zoom_to_calc_extents = true;

                vdrawing.add(tilemode1view); // Add to drawing view

                // Add to tilemode 1 view list
                tilemode1viewlist.addElement(tilemode1view);
            }


            // Set current to first view
            if (tilemode1viewlist.size() > 0)
                setCurrView((YxxfDrwTilemode1View)tilemode1viewlist.elementAt(0));
            else
                setCurrView(null);
            setLastView(null);


            // Start views
            Enumeration E1 = tilemode1viewlist.elements();
            while (E1.hasMoreElements())
            {
                YxxfDrwTilemode1View tilemode1view = (YxxfDrwTilemode1View)E1.nextElement();
                tilemode1view.commandTilemode1View_start();
            }

            //
            // Validate and Layout entire tree
            //
            vdrawing.validate();


//d         System.out.println("++++++++++YxxfDrwViewHandler:t1:END");
        }

//d     System.out.println("++++++++++YxxfDrwViewHandler:END commandViewHandler_get_secentities_beg");

//d     System.out.println("++++++++++YxxfDrwViewHandler:BEG commandViewHandler_get_secentities_end");

        if (getDrawing().secHeader.tilemode == 0)
        {
//d         System.out.println("++++++++++YxxfDrwViewHandler:t0:BEG Viewport size=" + getDrawing().secEntities.entViewportList.size());

            {
                // Create the single view
                YxxfDrwTilemode0View tilemode0view = new YxxfDrwTilemode0View(getDrawing());
                tilemode0view.setProperties(props_DrwViewHandler);

                tilemode0view.commandTilemode0View_init();

                vdrawing.add(tilemode0view); // Add to drawing view

                // Add to tilemode 0 view list
                tilemode0viewlist.addElement(tilemode0view);
            }

            // Start views
            Enumeration E1 = tilemode0viewlist.elements();
            while (E1.hasMoreElements())
            {
                YxxfDrwTilemode0View tilemode0view = (YxxfDrwTilemode0View)E1.nextElement();
                tilemode0view.commandTilemode0View_start();
            }

//w         commandViewHandlerRedraw();
            vdrawing.validate();

//d         System.out.println("++++++++++YxxfDrwViewHandler:t0:END");
        }

        setDrawingViewReady(true);

//d     System.out.println("++++++++++YxxfDrwViewHandler:END commandViewHandler_command_start_drawing");
    }
    //==========================================================================


    //==========================================================================
    /**
     * Waits until all draw threads are idle.
     */
    public
    void commandViewHandler_command_wait_for_complete()
    {
        // System.out.println("++++++++++YxxfDrwViewHandler:BEG commandViewHandler_command_wait_for_complete,listsize=" +
        //                    tilemode1viewlist.size());

        // int doingsum = YxxfGfxContext.GC_DOING_WAITING;

        if (getDrawing().secHeader.tilemode == 1)
        {
            vdrawing.commandDrawingView_wait_for_complete();

            Enumeration E;
            YxxfDrwTilemode1View tilemode1view;

            // Wait for each view to complete
            E = tilemode1viewlist.elements();
            while (E.hasMoreElements())
            {
                tilemode1view = (YxxfDrwTilemode1View)E.nextElement();
                tilemode1view.commandTilemode1View_wait_for_complete();
            }
        } else

        if (getDrawing().secHeader.tilemode == 0)
        {
            Enumeration E;
            YxxfDrwTilemode0View tilemode0view;

            // Wait for each view to complete
            E = tilemode0viewlist.elements();
            while (E.hasMoreElements())
            {
                tilemode0view = (YxxfDrwTilemode0View)E.nextElement();
                tilemode0view.commandTilemode0View_wait_for_complete();
            }
        }

        // System.out.println("++++++++++YxxfDrwViewHandler:END commandViewHandler_command_wait_for_complete");
    }
    //==========================================================================


    //==========================================================================
    /**
     * List stats.
     */
    public
    void commandViewHandler_command_list_stats()
    {
//d     System.out.println("++++++++++YxxfDrwViewHandler:BEG list_stats,ready=" + getDrawing().getDrawingReady() + ",complete=" + getDrawing().getDrawingComplete());

        // header section
        if (props_DrwViewHandler.getProperty_boolean("listheader"))
        {
            System.out.println("++++++++++YxxfDrwViewHandler:BEG HEADER section");
            getDrawing().secHeader.listHeader();
            System.out.println("++++++++++YxxfDrwViewHandler:END HEADER section");
        }

        // tables section
        if (props_DrwViewHandler.getProperty_boolean("listvports"))
        {
            System.out.println("++++++++++YxxfDrwViewHandler:BEG VPORTs table");
            getDrawing().secTables.listVports();
            getDrawing().secEntities.listViewports();
            System.out.println("++++++++++YxxfDrwViewHandler:END VPORTs table");
        }

        if (props_DrwViewHandler.getProperty_boolean("listltypes"))
        {
            System.out.println("++++++++++YxxfDrwViewHandler:BEG LTYPEs table");
            getDrawing().secTables.listLtypes();
            System.out.println("++++++++++YxxfDrwViewHandler:END LTYPEs table");
        }

        if (props_DrwViewHandler.getProperty_boolean("listlayers"))
        {
            System.out.println("++++++++++YxxfDrwViewHandler:BEG LAYERs table");
            getDrawing().secTables.listLayers();
            System.out.println("++++++++++YxxfDrwViewHandler:END LAYERs table");
        }

        if (props_DrwViewHandler.getProperty_boolean("liststyles"))
        {
            System.out.println("++++++++++YxxfDrwViewHandler:BEG STYLEs table");
            getDrawing().secTables.listStyles();
            System.out.println("++++++++++YxxfDrwViewHandler:END STYLEs table");
        }

        // blocks section
        if (props_DrwViewHandler.getProperty_boolean("listblocks"))
        {
            System.out.println("++++++++++YxxfDrwViewHandler:BEG BLOCKs section");
            getDrawing().secBlocks.listBlocks();
            System.out.println("++++++++++YxxfDrwViewHandler:END BLOCKs section");
        }

        // entities section
        if (props_DrwViewHandler.getProperty_boolean("listentities"))
        {
            System.out.println("++++++++++YxxfDrwViewHandler:BEG ENTITIES section");
            getDrawing().secEntities.listEntities();
            System.out.println("++++++++++YxxfDrwViewHandler:END ENTITIES section");
        }

        // Show running threads
//d     printThreadTree();

//d     System.out.println("++++++++++YxxfDrwViewHandler:END list_stats");
    }
    //==========================================================================


    //==========================================================================
    /**
     * Turn rendering on / off.
     */
    public
    void commandViewHandler_command_set_rendering(boolean arg_val)
    {
        // System.out.println("++++++++++YxxfDrwViewHandler:__:BEG commandViewHandler_command_set_rendering");

        if (getDrawing().secHeader.tilemode == 1)
        {
            Enumeration E;
            YxxfDrwTilemode1View tilemode1view;

            if (tilemode1viewlist.size() == 0)
                return;

            // Set rendering for all components
            vdrawing.commandDrawingView_set_rendering(arg_val);

            E = tilemode1viewlist.elements();
            while (E.hasMoreElements())
            {
                tilemode1view = (YxxfDrwTilemode1View)E.nextElement();
                tilemode1view.commandTilemode1View_set_rendering(arg_val);
            }
        } else

        if (getDrawing().secHeader.tilemode == 0)
        {
            Enumeration E;
            YxxfDrwTilemode0View tilemode0view;

            if (tilemode0viewlist.size() == 0)
                return;

            // Redraw all components
            E = tilemode0viewlist.elements();
            while (E.hasMoreElements())
            {
                tilemode0view = (YxxfDrwTilemode0View)E.nextElement();
                tilemode0view.commandTilemode0View_set_rendering(arg_val);
            }
        }

//d     System.out.println("++++++++++YxxfDrwViewHandler:END commandViewHandler_command_set_rendering");
    }
    //==========================================================================


    //==========================================================================
    /**
     * Sets the current viewport by number.  Invalid number (like -1) sets view to null.
     * @param currviewnum The tilemode 1 view number.
     */
    public
    void commandViewHandler_command_set_curr_view_num(int currviewnum)
    {
        if (currviewnum >= 0 && currviewnum < tilemode1viewlist.size())
            setCurrView((YxxfDrwTilemode1View)tilemode1viewlist.elementAt(currviewnum));
        else
            setCurrView(null);
    }

    /**
     * Sets the current viewport by view reference.
     * @param currview The tilemode 1 view (may be null).
     */
    public
    void commandViewHandler_command_set_curr_view(YxxfDrwTilemode1View currview)
    {
        setCurrView(currview);
    }

    /**
     * Sets the current view port and highlights it.
     * @param currview The tilemode 1 view.
     */
    public
    void commandViewHandler_command_hilite_curr_view(YxxfDrwTilemode1View currview)
    {
        setCurrView(currview);
        vdrawing.drawCurrViewBorder(vdrawing.getGraphics());
    }

    /**
     * Draws a highlighted border around all views.
     */
    public
    void commandViewHandler_command_hilite_all_views()
    {
        vdrawing.commandDrawingView_redraw();
    }

    /**
     * Setter for current viewport.
     * @param The tilemode 1 view.
     */
    public
    void setCurrView(YxxfDrwTilemode1View currview)
    {
        tilemode1viewcurr = currview;
    }

    /**
     * Getter for current viewport.
     * @return nextview The tilemode 1 view.
     */
    public
    YxxfDrwTilemode1View getCurrView()
    {
        return tilemode1viewcurr;
    }

    /**
     * Setter for last drawn viewport.
     * @param nextview The tilemode 1 view.
     */
    public
    void setLastView(YxxfDrwTilemode1View lastview)
    {
        tilemode1viewlast = lastview;
    }

    /**
     * Getter for last drawn viewport.
     * @return The tilemode 1 view.
     */
    public
    YxxfDrwTilemode1View getLastView()
    {
        return tilemode1viewlast;
    }
    //==========================================================================


    //==========================================================================
    /**
     * Handle a mousedown event based on current toolbar setting.
     * @param x The x coordinate of the cursor.
     * @param y The y coordinate of the cursor.
     * @param jgc The Java Graphics object.
     */
    public
    void commandViewHandler_command_mousedown(int x, int y, YxxfDrwTilemode1View tilemode1view, Graphics jgc)
    {
        if (getDrawing().secHeader.tilemode == 1)
        {
            commandViewHandler_command_hilite_curr_view(tilemode1view);

            // handle depending on current toolbar setting
            if (vtoolbar.getCommandBar() == YxxfDrwToolbarView.CMD_I_ZOOM)
            {
                if (tilemode1viewcurr != null)
                    tilemode1viewcurr.commandTilemode1View_rbb_beg(x, y, jgc);
            }
            else
            if (vtoolbar.getCommandBar() == YxxfDrwToolbarView.CMD_I_PAN)
            {
                if (tilemode1viewcurr != null)
                    tilemode1viewcurr.commandTilemode1View_rbl_beg(x, y, jgc);
            }
        } else

        if (getDrawing().secHeader.tilemode == 0)
        {
        }
    }

    /**
     * Handle a mousedrag event based on current toolbar setting.
     * @param x The x coordinate of the cursor.
     * @param y The y coordinate of the cursor.
     */
    public
    void commandViewHandler_command_mousedrag(int x, int y)
    {
        if (getDrawing().secHeader.tilemode == 1)
        {
            // handle depending on current toolbar setting
            if (vtoolbar.getCommandBar() == YxxfDrwToolbarView.CMD_I_ZOOM)
            {
                if (tilemode1viewcurr != null)
                    tilemode1viewcurr.commandTilemode1View_rbb_drag(x, y);
            }
            else
            if (vtoolbar.getCommandBar() == YxxfDrwToolbarView.CMD_I_PAN)
            {
                if (tilemode1viewcurr != null)
                    tilemode1viewcurr.commandTilemode1View_rbl_drag(x, y);
            }
        } else

        if (getDrawing().secHeader.tilemode == 0)
        {
        }
    }

    /**
     * Handle a mouseup event based on current toolbar setting.
     * @param x The x coordinate of the cursor.
     * @param y The y coordinate of the cursor.
     */
    public
    void commandViewHandler_command_mouseup(int x, int y)
    {
        if (getDrawing().secHeader.tilemode == 1)
        {
            // handle depending on current toolbar setting
            if (vtoolbar.getCommandBar() == YxxfDrwToolbarView.CMD_I_ZOOM)
            {
                if (tilemode1viewcurr != null)
                    tilemode1viewcurr.commandTilemode1View_rbb_end(x, y);
            }
            else
            if (vtoolbar.getCommandBar() == YxxfDrwToolbarView.CMD_I_PAN)
            {
                if (tilemode1viewcurr != null)
                    tilemode1viewcurr.commandTilemode1View_rbl_end(x, y);
            }
        } else

        if (getDrawing().secHeader.tilemode == 0)
        {
        }
    }
    //==========================================================================


    //==========================================================================
    /**
     * Start the view.
     */
    public
    void commandViewHandler_toolbar_start()
    {
        vtoolbar.setCommandBar(YxxfDrwToolbarView.CMD_I_TEXT);

        vdrawing.commandDrawingView_start();
    }
    //==========================================================================


    //==========================================================================
    /**
     * Redraw the view.
     */
    public
    void commandViewHandler_toolbar_redraw()
    {
//d     System.out.println("++++++++++YxxfDrwViewHandler:__:BEG commandViewHandlerRedraw");

        if (getDrawing().secHeader.tilemode == 1)
        {
            Enumeration E;
            YxxfDrwTilemode1View tilemode1view;

//d         System.out.println("++++++++++YxxfDrwViewHandler:t1:    commandRedraw:vdrawing tilemode1viewlist.size()=" +
//d                            tilemode1viewlist.size());
            if (tilemode1viewlist.size() == 0)
                return;

            // Redraw view borders
            commandViewHandler_command_hilite_all_views();

            // Redraw all views
            E = tilemode1viewlist.elements();
            while (E.hasMoreElements())
            {
                tilemode1view = (YxxfDrwTilemode1View)E.nextElement();
                tilemode1view.commandTilemode1View_redraw();
            }

//d         System.out.println("++++++++++YxxfDrwViewHandler:t1:END commandRedraw");
        } else

        if (getDrawing().secHeader.tilemode == 0)
        {
            Enumeration E;
            YxxfDrwTilemode0View tilemode0view;

//d         System.out.println("++++++++++YxxfDrwViewHandler:t0:    commandRedraw:vdrawing tilemode0viewlist.size()=" +
//d                            tilemode0viewlist.size());
            if (tilemode0viewlist.size() == 0)
                return;

            // Redraw all components
            E = tilemode0viewlist.elements();
            while (E.hasMoreElements())
            {
                tilemode0view = (YxxfDrwTilemode0View)E.nextElement();
                tilemode0view.commandTilemode0View_redraw();
            }

//d         System.out.println("++++++++++YxxfDrwViewHandler:t0:END commandRedraw");
        }

//d     System.out.println("++++++++++YxxfDrwViewHandler:END commandViewHandlerRedraw");
    }
    //==========================================================================


    //==========================================================================
    /**
     * Restore the view.
     */
    public
    void commandViewHandler_toolbar_restore()
    {
        if (getDrawing().secHeader.tilemode == 1)
        {
            if (tilemode1viewcurr != null)
                tilemode1viewcurr.commandTilemode1View_restore();
        } else

        if (getDrawing().secHeader.tilemode == 0)
        {
        }
    }
    //==========================================================================


    //==========================================================================
    /**
     * Calculate the extents.
     */
    public
    void commandViewHandler_toolbar_calc_extents()
    {
        if (getDrawing().secHeader.tilemode == 1)
        {
            if (tilemode1viewcurr != null)
                tilemode1viewcurr.commandTilemode1View_calc_extents();
        } else

        if (getDrawing().secHeader.tilemode == 0)
        {
        }
    }
    //==========================================================================


    //==========================================================================
    /**
     * Display the state of the View Handler.
     */
    public
    void commandViewHandler_toolbar_liststats()
    {
        System.out.println("========================================================================");
        System.out.println("== YCAD LISTSTATS - YxxfDrwViewHandler");
        System.out.println("========================================================================");

        System.out.println("DRAWING");
        System.out.println("  header.extmin   = " + getDrawing().secHeader.extmin);
        System.out.println("  header.extmax   = " + getDrawing().secHeader.extmax);
        System.out.println("  header.tilemode = " + getDrawing().secHeader.tilemode);

        if (getDrawing().secHeader.tilemode == 1)
        {
            System.out.println("");

            if (tilemode1viewcurr == null)
            {
                System.out.println("NO ACTIVE VIEW SELECTED");
            }
            else
            {
                System.out.println("ACTIVE VIEW GC");
                System.out.println("  gc.gc_doing     = " + tilemode1viewcurr.getGC().gc_doing);
                System.out.println("  gc.gc_rbl_doing = " + tilemode1viewcurr.getGC().gc_rbl_doing);
                System.out.println("  gc.gc_rbb_doing = " + tilemode1viewcurr.getGC().gc_rbb_doing);

                System.out.println("");
                System.out.println("  gc.dspwin       = " + tilemode1viewcurr.getGC().getDspwin());
                System.out.println("  gc.calcextmin   = " + tilemode1viewcurr.getGC().calc_extmin);
                System.out.println("  gc.calcextmax   = " + tilemode1viewcurr.getGC().calc_extmax);

                System.out.println("");

                System.out.println("ACTIVE VIEW VPORT");
                System.out.println("  vp.ll[x y]      = [" + tilemode1viewcurr.getVport().llx + " " +
                                                             tilemode1viewcurr.getVport().lly + "]");
                System.out.println("  vp.ur[x y]      = [" + tilemode1viewcurr.getVport().urx + " " +
                                                             tilemode1viewcurr.getVport().ury + "]");
                System.out.println("  vp.vtgt         = "  + tilemode1viewcurr.getVport().vtgt);
                System.out.println("  vp.vdir         = "  + tilemode1viewcurr.getVport().vdir);
                System.out.println("  vp.viewtwistang = "  + tilemode1viewcurr.getVport().viewtwistang);
                System.out.println("  vp.vheight      = "  + tilemode1viewcurr.getVport().vheight);
                System.out.println("  vp.vaspect      = "  + tilemode1viewcurr.getVport().vaspect);
                System.out.println("  vp.vcp[x y]     = [" + tilemode1viewcurr.getVport().vcpx + " " +
                                                             tilemode1viewcurr.getVport().vcpy + "]");

                // printThreadTree();
            }

        } else

        if (getDrawing().secHeader.tilemode == 0)
        {
            Enumeration E1 = tilemode0viewlist.elements();
            while (E1.hasMoreElements())
            {
                YxxfDrwTilemode0View tilemode0view = (YxxfDrwTilemode0View)E1.nextElement();
                System.out.println("VIEW GC");
                System.out.println("  gc.gc_doing     = " + tilemode0view.getGC().gc_doing);
                System.out.println("  gc.gc_rbl_doing = " + tilemode0view.getGC().gc_rbl_doing);
                System.out.println("  gc.gc_rbb_doing = " + tilemode0view.getGC().gc_rbb_doing);

                System.out.println("");
                System.out.println("  gc.dspwin       = " + tilemode0view.getGC().getDspwin());
                System.out.println("  gc.calcextmin   = " + tilemode0view.getGC().calc_extmin);
                System.out.println("  gc.calcextmax   = " + tilemode0view.getGC().calc_extmax);
            }
        }
 
        System.out.println("========================================================================");
    }
    //==========================================================================


    //==========================================================================
    /**
     * Print.
     */
    public
    void commandViewHandler_toolbar_print()
    {
        System.out.println("++++++++++YxxfDrwViewHandler:print|BEG");

        Frame frame = new Frame();
        Properties props = new Properties();
        PrintJob pj = frame.getToolkit().getPrintJob(frame, "Ycadv", props);
        
        if (pj == null) // Cancelled
        {
            System.out.println("++++++++++YxxfDrwViewHandler:print|Cancelled");
            System.out.println("++++++++++YxxfDrwViewHandler:print|END");
            return;
        }

        Dimension pagedim = pj.getPageDimension();
        int       pageres = pj.getPageResolution();
        Rectangle vbounds = vdrawing.bounds();
        System.out.println("++++++++++YxxfDrwViewHandler:print|pagedim=[" + pagedim + "]");
        System.out.println("++++++++++YxxfDrwViewHandler:print|pageres=[" + pageres + "]");
        System.out.println("++++++++++YxxfDrwViewHandler:print|vdrawing.bounds=[" + vbounds + "]");

        Graphics jpc = pj.getGraphics();
        System.out.println("++++++++++YxxfDrwViewHandler:print|jpc=[" + jpc + "]");


        //
        // Create print view handler
        //
        YxxfPrtViewHandler vprthandler = new YxxfPrtViewHandler();

        // Set parameters in view handler
        int halfinch = (int)Math.round( (double).5 * (double)pageres );
        int calc_x = halfinch;
        int calc_y = halfinch;
        int calc_w = (int)Math.round( (double)pagedim.width - (double)halfinch - (double)halfinch );
        int calc_h = (int)Math.round( (double)calc_w /
                                      ((double)vbounds.width / (double)vbounds.height) );

        vprthandler.setProperties("xoffset=" + calc_x, false);
        vprthandler.setProperties("yoffset=" + calc_y, false);
        vprthandler.setProperties("width="   + calc_w, false);
        vprthandler.setProperties("height="  + calc_h, false);

        vprthandler.setProperties("sharedjgc=true", false);

        // Set print background color
        String str_printbgcolor = props_DrwViewHandler.getProperty("printbgcolor");
        if (str_printbgcolor == null)
            vprthandler.setProperties("bgcolor=#FFFFFF", false);
        else
        if (str_printbgcolor.equals("bgcolor"))
        {
            String str_bgcolor = props_DrwViewHandler.getProperty("bgcolor");
            if (str_bgcolor == null)
                vprthandler.setProperties("bgcolor=#000000", false);
            else
                vprthandler.setProperties("bgcolor=" + str_bgcolor, false);
        }
        else
            vprthandler.setProperties("bgcolor=" + str_printbgcolor, false);

        // Set print foreground palette
        String str_printfgcolor = props_DrwViewHandler.getProperty("printfgcolor");
        if (str_printfgcolor == null)
            vprthandler.setProperties("fgcolor=acihighinverse", false);
        else
        if (str_printfgcolor.equals("fgcolor"))
        {
            String str_fgcolor = props_DrwViewHandler.getProperty("fgcolor");
            if (str_fgcolor == null)
                vprthandler.setProperties("fgcolor=aci", false);
            else
            if (str_fgcolor.equals("aci"))
                vprthandler.setProperties("fgcolor=aci", false);
            else
            if (str_fgcolor.equals("acihighinverse"))
                vprthandler.setProperties("fgcolor=acihighinverse", false);
            else
                vprthandler.setProperties("fgcolor=" + str_fgcolor, false);
        }
        else
        if (str_printfgcolor.equals("aci"))
            vprthandler.setProperties("fgcolor=aci", false);
        else
        if (str_printfgcolor.equals("acihighinverse"))
            vprthandler.setProperties("fgcolor=acihighinverse", false);
        else
            vprthandler.setProperties("fgcolor=" + str_printfgcolor, false);


        // Start view handler
        vprthandler.commandViewHandler_command_init(getDrawing(), jpc);
        vprthandler.commandViewHandler_command_start();


        // ============================================================
        // Wait for view setup
        // Draw
        // Wait for draw complete
        // ============================================================
        vprthandler.waitDrawingViewReady();
        vprthandler.commandViewHandler_toolbar_redraw();
        vprthandler.commandViewHandler_command_wait_for_complete();

        System.out.println("++++++++++YxxfDrwViewHandler:print|complete");


        jpc.dispose(); // Send page to printer

        pj.end(); // End print job

        System.out.println("++++++++++YxxfDrwViewHandler:print|END");
    }
    //==========================================================================


    //==========================================================================
    /**
     * Zoom in the view 10 percent.
     */
    public
    void commandViewHandler_toolbar_zoom_in()
    {
        if (getDrawing().secHeader.tilemode == 1)
        {
            if (tilemode1viewcurr != null)
                tilemode1viewcurr.commandTilemode1View_zoom_in(10);
        } else

        if (getDrawing().secHeader.tilemode == 0)
        {
        }
    }

    /**
     * Zoom in the view a percent value.
     */
    public
    void commandViewHandler_toolbar_zoom_in(double zoompct)
    {
        if (getDrawing().secHeader.tilemode == 1)
        {
            if (tilemode1viewcurr != null)
                tilemode1viewcurr.commandTilemode1View_zoom_in(zoompct);
        } else

        if (getDrawing().secHeader.tilemode == 0)
        {
        }
    }

    /**
     * Zoom out the view 10 percent.
     */
    public
    void commandViewHandler_toolbar_zoom_out()
    {
        if (getDrawing().secHeader.tilemode == 1)
        {
            if (tilemode1viewcurr != null)
                tilemode1viewcurr.commandTilemode1View_zoom_out(10);
        } else

        if (getDrawing().secHeader.tilemode == 0)
        {
        }
    }

    /**
     * Zoom out the view a percent value.
     */
    public
    void commandViewHandler_toolbar_zoom_out(double zoompct)
    {
        if (getDrawing().secHeader.tilemode == 1)
        {
            if (tilemode1viewcurr != null)
                tilemode1viewcurr.commandTilemode1View_zoom_out(zoompct);
        } else

        if (getDrawing().secHeader.tilemode == 0)
        {
        }
    }

    /**
     * Zoom to head extents.
     */
    public
    void commandViewHandler_toolbar_zoom_to_head_extents()
    {
        if (getDrawing().secHeader.tilemode == 1)
        {
            if (tilemode1viewcurr != null)
                tilemode1viewcurr.commandTilemode1View_zoom_to_head_extents();
        } else

        if (getDrawing().secHeader.tilemode == 0)
        {
        }
    }
    /**
     * Zoom to calculated extents.
     */
    public
    void commandViewHandler_toolbar_zoom_to_calc_extents()
    {
        if (getDrawing().secHeader.tilemode == 1)
        {
            if (tilemode1viewcurr != null)
                tilemode1viewcurr.commandTilemode1View_zoom_to_calc_extents();
        } else

        if (getDrawing().secHeader.tilemode == 0)
        {
        }
    }
    //==========================================================================


    //==========================================================================
    /**
     * Pan left in view.
     */
    public
    void commandViewHandler_toolbar_pan_l()
    {
        if (getDrawing().secHeader.tilemode == 1)
        {
            if (tilemode1viewcurr != null)
                tilemode1viewcurr.commandTilemode1View_pan_l(10);
        } else

        if (getDrawing().secHeader.tilemode == 0)
        {
        }
    }

    /**
     * Pan right in view.
     */
    public
    void commandViewHandler_toolbar_pan_r()
    {
        if (getDrawing().secHeader.tilemode == 1)
        {
            if (tilemode1viewcurr != null)
                tilemode1viewcurr.commandTilemode1View_pan_r(10);
        } else

        if (getDrawing().secHeader.tilemode == 0)
        {
        }
    }

    /**
     * Pan up in view.
     */
    public
    void commandViewHandler_toolbar_pan_u()
    {
        if (getDrawing().secHeader.tilemode == 1)
        {
            if (tilemode1viewcurr != null)
                tilemode1viewcurr.commandTilemode1View_pan_u(10);
        } else

        if (getDrawing().secHeader.tilemode == 0)
        {
        }
    }

    /**
     * Pan down in view.
     */
    public
    void commandViewHandler_toolbar_pan_d()
    {
        if (getDrawing().secHeader.tilemode == 1)
        {
            if (tilemode1viewcurr != null)
                tilemode1viewcurr.commandTilemode1View_pan_d(10);
        } else

        if (getDrawing().secHeader.tilemode == 0)
        {
        }
    }
    //==========================================================================


    //==========================================================================
    /**
     * Rotate x_p.
     */
    public
    void commandViewHandler_toolbar_rotate_x_p()
    {
        if (getDrawing().secHeader.tilemode == 1)
        {
            if (tilemode1viewcurr != null)
                tilemode1viewcurr.commandTilemode1View_rotate_x_p();
        } else

        if (getDrawing().secHeader.tilemode == 0)
        {
        }
    }

    /**
     * Rotate x_m.
     */
    public
    void commandViewHandler_toolbar_rotate_x_m()
    {
        if (getDrawing().secHeader.tilemode == 1)
        {
            if (tilemode1viewcurr != null)
                tilemode1viewcurr.commandTilemode1View_rotate_x_m();
        } else

        if (getDrawing().secHeader.tilemode == 0)
        {
        }
    }

    /**
     * Rotate y_p.
     */
    public
    void commandViewHandler_toolbar_rotate_y_p()
    {
        if (getDrawing().secHeader.tilemode == 1)
        {
            if (tilemode1viewcurr != null)
                tilemode1viewcurr.commandTilemode1View_rotate_y_p();
        } else

        if (getDrawing().secHeader.tilemode == 0)
        {
        }
    }

    /**
     * Rotate y_m.
     */
    public
    void commandViewHandler_toolbar_rotate_y_m()
    {
        if (getDrawing().secHeader.tilemode == 1)
        {
            if (tilemode1viewcurr != null)
                tilemode1viewcurr.commandTilemode1View_rotate_y_m();
        } else

        if (getDrawing().secHeader.tilemode == 0)
        {
        }
    }

    /**
     * Rotate z_p.
     */
    public
    void commandViewHandler_toolbar_rotate_z_p()
    {
        if (getDrawing().secHeader.tilemode == 1)
        {
            if (tilemode1viewcurr != null)
                tilemode1viewcurr.commandTilemode1View_rotate_z_p();
        } else

        if (getDrawing().secHeader.tilemode == 0)
        {
        }
    }

    /**
     * Rotate z_m.
     */
    public
    void commandViewHandler_toolbar_rotate_z_m()
    {
        if (getDrawing().secHeader.tilemode == 1)
        {
            if (tilemode1viewcurr != null)
                tilemode1viewcurr.commandTilemode1View_rotate_z_m();
        } else

        if (getDrawing().secHeader.tilemode == 0)
        {
        }
    }

    /**
     * Rotate x value.
     */
    public
    void commandViewHandler_toolbar_rotate_x_value(double value)
    {
        if (getDrawing().secHeader.tilemode == 1)
        {
            if (tilemode1viewcurr != null)
                tilemode1viewcurr.commandTilemode1View_rotate_x_value(value);
        } else

        if (getDrawing().secHeader.tilemode == 0)
        {
        }
    }

    /**
     * Rotate y value.
     */
    public
    void commandViewHandler_toolbar_rotate_y_value(double value)
    {
        if (getDrawing().secHeader.tilemode == 1)
        {
            if (tilemode1viewcurr != null)
                tilemode1viewcurr.commandTilemode1View_rotate_y_value(value);
        } else

        if (getDrawing().secHeader.tilemode == 0)
        {
        }
    }

    /**
     * Rotate z value.
     */
    public
    void commandViewHandler_toolbar_rotate_z_value(double value)
    {
        if (getDrawing().secHeader.tilemode == 1)
        {
            if (tilemode1viewcurr != null)
                tilemode1viewcurr.commandTilemode1View_rotate_z_value(value);
        } else

        if (getDrawing().secHeader.tilemode == 0)
        {
        }
    }
    //==========================================================================



//  //==========================================================================
//  /**
//   * Print the current ThreadTree - for debugging
//   */
//  public
//  void printThreadTree()
//  {
//      System.out.println("==== Running Threads BEG ====\n");
//
//      Thread t = Thread.currentThread();
//      ThreadGroup tg = t.getThreadGroup();
//      while (tg.getParent() != null)
//          tg = tg.getParent();
//
//      printThreadTreeAction(tg, "");
// 
//      System.out.println("==== Running Threads END ====\n");
//  }
//
//  /**
//   * Print ThreadTree action - prints threadgroup and threads
//   */
//  private
//  void printThreadTreeAction(ThreadGroup tg, String indent)
//  {
//      System.out.println("TG|" + indent + tg + ",activeCount=" + tg.activeCount() + ",activeGroupCount=" + tg.activeGroupCount());
//      indent = indent + "    ";
//      Thread[] mem = new Thread[tg.activeCount()];
//      tg.enumerate(mem, false);
//      for (int i = 0; i < mem.length; ++i)
//          System.out.println("T |" + indent + mem[i]);
//      ThreadGroup[] child = new ThreadGroup[tg.activeGroupCount()];
//      tg.enumerate(child);
//      for (int i = 0; i < child.length; ++i)
//          printThreadTreeAction(child[i], indent + "    ");
//  }
//  //==========================================================================
}

