//==============================================================================
// YxxfPrtViewHandler.java
//
// View Handler for rendering using an external Graphics object
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfPrtViewHandler.java,v 1.22 2003/06/04 11:02:46 ekarlo Exp $
// $Log: YxxfPrtViewHandler.java,v $
// Revision 1.22  2003/06/04 11:02:46  ekarlo
// Insure views have valid layout when initialized.
//
// Revision 1.21  2003/06/03 09:53:14  ekarlo
// Fix xoffset= and yoffset= Prt imprinter funtionality.
//
// Revision 1.20  2003/05/26 02:58:10  ekarlo
// Add methods to turn rendering on and off.
// Improve calculation of extents.
//
// Revision 1.19  2003/05/19 05:31:19  ekarlo
// Fix off-by-one (top and left) view error.
//
// Revision 1.18  2003/05/17 12:42:20  ekarlo
// Add option to control width of border around views.
//
// Revision 1.17  2003/05/16 05:12:02  ekarlo
// Add options to control border view colors.
// Add methods to set current view.
//
// Revision 1.16  2003/05/08 11:24:03  ekarlo
// Remove warnings.
//
// Revision 1.15  2003/05/02 09:04:11  ekarlo
// Add zoom percent value methods.
//
// Revision 1.14  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.13  2002/10/30 02:22:59  ekarlo
// Rearrange initialization of option defaults.
//
// Revision 1.12  2002-09-28 23:20:53-06  ekarlo
// Change prefix of entity print options to "list".
//
// Revision 1.11  2002-09-26 02:05:47-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.10  2002-09-12 23:39:25-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.9  2002-09-12 15:11:12-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.8  2001-10-08 07:18:27-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.7  2001-07-04 22:46:12-06  ekarlo
// Do case insensitive compare for active vport.
// Found in A2000 test file during Coventor evaluation.
//
// Revision 1.6  2001-05-21 20:11:33-06  ekarlo
// Change handling of user view bounds.
//
// Revision 1.5  2001-05-21 04:39:39-06  ekarlo
// Imprinter development - draw all view borders.
//
// Revision 1.4  2001-05-21 02:34:02-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.3  2001-05-20 02:42:56-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.2  2001-05-17 23:33:06-06  ekarlo
// Development check-in.
//
// Revision 1.1  2001-05-17 02:52:29-06  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


import java.awt.*;
import java.util.*;

import com.ysystems.lib.yutil.*;


/**
 * View Handler for rendering using an external Graphics object.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfPrtViewHandler implements Observer
{
    //==========================================================================
    /**
     * Properties
     */
    private
    YutilProperties             props_PrtViewHandler
                                                = new YutilProperties();
    //==========================================================================


    //==========================================================================
    // Views

    /**
     * Dummy Container only used to hold the user GC.
     */
    private
    YxxfPrtContainer            vroot           = null;

    /**
     * Main Container.
     */
    private YxxfPrtView         viewer          = null;

    /**
     * Drawing Container in the viewer.
     */
    private YxxfPrtDrawingView  vdrawing        = null;


    /**
     * Tilemode 0 view list - created here
     * Contains list of all paper space views.
     * Added to vdrawing.
     */
    public
    Vector                      tilemode0viewlist
                                                = new Vector();

    /**
     * Tilemode 1 view list contains all model space views.
     * Added to vdrawing.
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
    YxxfPrtTilemode1View        tilemode1viewcurr
                                                = null;

    /**
     * Last active drawn view.
     * Not initially set.
     */
    private
    YxxfPrtTilemode1View        tilemode1viewlast
                                                = null;
    //==========================================================================


    //==========================================================================
    // Constructors

    /**
     * Constructor (empty)
     */
    public
    YxxfPrtViewHandler()
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
        vroot.getGC().setDrawing(D);
    }

    /**
     * Get the Drawing.
     * @return The Drawing.
     */
    public
    Yxxf getDrawing()
    {
        return vroot.getGC().getDrawing();
    }


    /**
     * Set Java Graphics Context
     * @param jgc The context.
     */
    public
    void setJGC(Graphics jgc)
    {
        vroot.gc.setJGC(jgc);
    }

    /**
     * Get Java Graphics Context.
     * @return The context.
     */
    public
    Graphics getJGC()
    {
        return vroot.gc.getJGC();
    }


    /**
     * Get drawing root.
     * @return The drawing root.
     */
    public
    YxxfPrtComponent getVroot()
    {
        return vroot;
    }


    /**
     * Get drawing viewer.
     * @return The Viewer.
     */
    public
    YxxfPrtComponent getViewer()
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
        props_PrtViewHandler.setPropertiesDefaults("bgcolor=#000000", false);
        props_PrtViewHandler.setPropertiesDefaults("fgcolor=aci", false);

        props_PrtViewHandler.setPropertiesDefaults("borderwidth=2", false);
        props_PrtViewHandler.setPropertiesDefaults("borderactivecolor=#FFFF00", false);
        props_PrtViewHandler.setPropertiesDefaults("borderinactivecolor=#0000FF", false);

        props_PrtViewHandler.setPropertiesDefaults("listheader=false", false);

        props_PrtViewHandler.setPropertiesDefaults("listlayers=false", false);
        props_PrtViewHandler.setPropertiesDefaults("listltypes=false", false);
        props_PrtViewHandler.setPropertiesDefaults("listvports=false", false);
        props_PrtViewHandler.setPropertiesDefaults("liststyles=false", false);
        props_PrtViewHandler.setPropertiesDefaults("listblocks=false", false);

        props_PrtViewHandler.setPropertiesDefaults("listentities=false", false);

        props_PrtViewHandler.setPropertiesDefaults("drawdspminmax=false", false);
        props_PrtViewHandler.setPropertiesDefaults("drawextminmax=false", false);
        props_PrtViewHandler.setPropertiesDefaults("drawlimminmax=false", false);
    }

    /**
     * Set properties from args string.
     * @param args The collection of properties.
     * @param scanout Scan for multiple arguments.
     */
    public
    void setProperties(String args, boolean scanout)
    {
        props_PrtViewHandler.setProperties(args, scanout);
    }

    /**
     * Set properties from argv array.
     * @param argv The collection of properties.
     */
    public
    void setProperties(String argv[])
    {
        props_PrtViewHandler.setProperties(argv);
    }

    /**
     * Set properties from argprops YutilProperties
     * @param argprops The collection of properties.
     */
    public
    void setProperties(YutilProperties argprops)
    {
        props_PrtViewHandler.setProperties(argprops);
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
        // System.out.println("++++++++++YxxfPrtViewHandler:BEG obs update,type=" + type);


        // Initialize View Handler
        if (type == YxxfDrwViewHandlerEvent.COMMAND_INIT)
        {
            Object obj1 = vhevt.getObj1();
            Object obj2 = vhevt.getObj2();
            commandViewHandler_command_init((Yxxf)obj1, (Graphics)obj2);
        }
        else


        // Start View Handler
        if (type == YxxfDrwViewHandlerEvent.COMMAND_START)
        {
            commandViewHandler_command_start();
        }
        else

        // Initialize Drawing
        if (type == YxxfDrwViewHandlerEvent.COMMAND_INIT_DRAWING)
        {
            commandViewHandler_command_init_drawing();
        }
        else

        // Start Drawing
        if (type == YxxfDrwViewHandlerEvent.COMMAND_START_DRAWING)
        {
            commandViewHandler_command_start_drawing();
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
            commandViewHandler_command_set_curr_view((YxxfPrtTilemode1View)vhevt.getObj1());
        }
        else

        // Set and highlight current view
        if (type == YxxfDrwViewHandlerEvent.COMMAND_HILITE_CURR_VIEW)
        {
            commandViewHandler_command_hilite_curr_view((YxxfPrtTilemode1View)vhevt.getObj1());
        }
        else

        // Highlight all views
        if (type == YxxfDrwViewHandlerEvent.COMMAND_HILITE_ALL_VIEWS)
        {
            commandViewHandler_command_hilite_all_views();
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
        }
        else

        // Ending of header section
        if (type == YxxfDrwViewHandlerEvent.GET_SECHEADER_END)
        {
        }
        else

        // Beginning of tables section
        if (type == YxxfDrwViewHandlerEvent.GET_SECTABLES_BEG)
        {
        }
        else

        // Ending of tables section
        if (type == YxxfDrwViewHandlerEvent.GET_SECTABLES_END)
        {
        }
        else

        // Beginning of table
        if (type == YxxfDrwViewHandlerEvent.GET_TBLNAME_BEG)
        {
        }
        else

        // Ending of table
        if (type == YxxfDrwViewHandlerEvent.GET_TBLNAME_END)
        {
        }
        else

        // Beginning of blocks section
        if (type == YxxfDrwViewHandlerEvent.GET_SECBLOCKS_BEG)
        {
        }
        else

        // Ending of blocks section
        if (type == YxxfDrwViewHandlerEvent.GET_SECBLOCKS_END)
        {
        }
        else

        // Beginning of block
        if (type == YxxfDrwViewHandlerEvent.GET_BLKNAME_BEG)
        {
        }
        else

        // Ending of block
        if (type == YxxfDrwViewHandlerEvent.GET_BLKNAME_END)
        {
        }
        else

        // Beginning of entities section
        if (type == YxxfDrwViewHandlerEvent.GET_SECENTITIES_BEG)
        {
        }
        else

        // End of entities section
        if (type == YxxfDrwViewHandlerEvent.GET_SECENTITIES_END)
        {
        }
        else

        // End of File
        if (type == YxxfDrwViewHandlerEvent.GET_DRAWING_EOF)
        {
        }


        // System.out.println("++++++++++YxxfPrtViewHandler:END obs update,type=" + type);
    }
    //==========================================================================


    //==========================================================================
    /**
     * Initialize this View Handler.
     */
    public
    void commandViewHandler_command_init(Yxxf D, Graphics jgc)
    {
        // System.out.println("++++++++++YxxfPrtViewHandler:BEG commandViewHandler_command_init");

        //
        // Extract bounds
        //
        int param_xoffset = props_PrtViewHandler.getProperty_int("xoffset");
        int param_yoffset = props_PrtViewHandler.getProperty_int("yoffset");
        int param_width   = props_PrtViewHandler.getProperty_int("width");
        int param_height  = props_PrtViewHandler.getProperty_int("height");


        //
        // Create dummy view handler root container
        //
        boolean param_sharedjgc = props_PrtViewHandler.getProperty_boolean("sharedjgc");
        Graphics tmpjgc;

        // vroot always hold the orignal D, jgc and dspwin values
        vroot = new YxxfPrtContainer(D, jgc,
                                     param_xoffset, param_yoffset,
                                     param_width, param_height);
        vroot.setSharedJGCFlag(param_sharedjgc);
        // System.out.println("++++++++++YxxfPrtViewHandler:init|vroot:vroot=[" + vroot + "]");
        // System.out.println("++++++++++YxxfPrtViewHandler:init|vroot:param_sharedjgc=[" + param_sharedjgc + "]");


        //
        // Create viewer container
        //
        if (param_sharedjgc)
            tmpjgc = vroot.getJGC();
        else
            tmpjgc = vroot.getJGC().create();
        viewer = new YxxfPrtView(vroot.getDrawing(), tmpjgc,
                                 0, 0,
                                 vroot.getDspwinWidth(), vroot.getDspwinHeight());
        viewer.setSharedJGCFlag(param_sharedjgc);
        // System.out.println("++++++++++YxxfPrtViewHandler:init|viewer  :a=[" + viewer + "]");

        // Set parameters in viewer
        // Copy in all properties from props_PrtViewHandler
        for (Enumeration ep = props_PrtViewHandler.propertyNames(); ep.hasMoreElements(); )
        {
            String key = (String)ep.nextElement();
            viewer.setProperties(key + "=" + props_PrtViewHandler.getProperty(key), false);
        }

        viewer.commandInit();


        //
        // Create drawing view container
        //
        if (param_sharedjgc)
            tmpjgc = vroot.getJGC();
        else
            tmpjgc = vroot.getJGC().create();
        vdrawing = new YxxfPrtDrawingView(vroot.getDrawing(), tmpjgc, this,
                                          0, 0,
                                          vroot.getDspwinWidth(), vroot.getDspwinHeight());
        vdrawing.setSharedJGCFlag(param_sharedjgc);
        // System.out.println("++++++++++YxxfPrtViewHandler:init|vdrawing:a=[" + vdrawing + "]");

        // Set parameters in vdrawing
        // Copy in all properties from props_PrtViewHandler
        for (Enumeration ep = props_PrtViewHandler.propertyNames(); ep.hasMoreElements(); )
        {
            String key = (String)ep.nextElement();

            String val = key + "=" + props_PrtViewHandler.getProperty(key);
            vdrawing.setProperties(val, false);
        }

        vdrawing.commandDrawingView_init();


        // System.out.println("++++++++++YxxfPrtViewHandler:END commandViewHandler_command_init");
    }
    //==========================================================================


    //==========================================================================
    /**
     * Start this View Handler.
     */
    public
    void commandViewHandler_command_start()
    {
        // System.out.println("++++++++++YxxfPrtViewHandler:BEG commandViewHandler_command_start");

        // Add viewer area to root container
        vroot.add(viewer);

        // Add drawing area to viewer area
        viewer.add(vdrawing);


        // Start viewer
        viewer.commandStart();

        // Start vdrawing
        vdrawing.commandDrawingView_start();


        // Connect drawing to view handler
        // Receive events from drawing
        getDrawing().addObserver(this);
        // System.out.println("++++++++++YxxfPrtViewHandler:countObservers=" + getDrawing().countObservers());

        // System.out.println("++++++++++YxxfPrtViewHandler:END commandViewHandler_command_start");
    }
    //==========================================================================


    //==========================================================================
    /**
     * Init drawing.
     */
    public
    void commandViewHandler_command_init_drawing()
    {
        // System.out.println("++++++++++YxxfPrtViewHandler:BEG commandViewHandler_command_init_drawing");

        // --- Display extent box if requested
        if (props_PrtViewHandler.getProperty_boolean("drawextminmax"))
        {
            YxxfEntxCommand cmd = new YxxfEntxCommand(YxxfEntxCommand.COMMAND_DRAWEXTMINMAX);
            cmd.hdr_layer = getDrawing().secTables.findLayer_add("**MODEL_SPACE_ROOT_LAYER");
            cmd.hdr_aci = YxxfGfxPalette.ACI_YELLOW;
            cmd.calc(getDrawing());
            getDrawing().secEntities.insMSpace.block.addEntity(cmd);
        }

        // --- Display limit box if requested
        if (props_PrtViewHandler.getProperty_boolean("drawlimminmax"))
        {
            YxxfEntxCommand cmd = new YxxfEntxCommand(YxxfEntxCommand.COMMAND_DRAWLIMMINMAX);
            cmd.hdr_layer = getDrawing().secTables.findLayer_add("**MODEL_SPACE_ROOT_LAYER");
            cmd.hdr_aci = YxxfGfxPalette.ACI_RED;
            cmd.calc(getDrawing());
            getDrawing().secEntities.insMSpace.block.addEntity(cmd);
        }

        // System.out.println("++++++++++YxxfPrtViewHandler:END commandViewHandler_command_init_drawing");
    }
    //==========================================================================


    //==========================================================================
    /**
     * Start drawing.
     */
    public
    void commandViewHandler_command_start_drawing()
    {
        // System.out.println("++++++++++YxxfPrtViewHandler:BEG commandViewHandler_command_start_drawing");

        if (getDrawing().secHeader.tilemode == 1)
        {
            // System.out.println("++++++++++YxxfPrtViewHandler:t1:BEG Vport size=" + getDrawing().secTables.tblVport.size());

            // --- Scan entire vport table and create vport views for each active vport
            Enumeration E = getDrawing().secTables.tblVport.elements();
            YxxfTblVport vport;
            while (E.hasMoreElements())
            {
                vport = (YxxfTblVport)E.nextElement();
//d             System.out.println("++++++++++YxxfPrtViewHandler:t1:Vport =====================================");
//d             System.out.println(vport);
//d             System.out.println("++++++++++YxxfPrtViewHandler:t1:Vport =====================================");
//d             System.out.println("++++++++++YxxfPrtViewHandler:t1:get_vport_table");

                // Use only if "*ACTIVE"
                if (!vport.name.equalsIgnoreCase("*ACTIVE"))
                {
                    // System.out.println("++++++++++YxxfPrtViewHandler:t1:update called vport.name=[" + vport.name + "],skipped");
                    continue;
                }
//d             System.out.println("++++++++++YxxfPrtViewHandler:t1:update called vport.name=[" + vport.name + "]");

//d             getDrawing().genPoint(getDrawing().secEntities.insMSpace.block, YxxfGfxPalette.ACI_WHITE, vport.vtgt);

                // Create view
                Graphics tmpjgc;
                if (props_PrtViewHandler.getProperty_boolean("sharedjgc"))
                    tmpjgc = vroot.getJGC();
                else
                    tmpjgc = vroot.getJGC().create();
                YxxfPrtTilemode1View tilemode1view = new YxxfPrtTilemode1View(vroot.getDrawing(), tmpjgc,
                                                                              0, 0,
                                                                              vroot.getDspwinWidth(), vroot.getDspwinHeight(),
                                                                              vport);
                tilemode1view.setSharedJGCFlag(props_PrtViewHandler.getProperty_boolean("sharedjgc"));
                tilemode1view.setProperties(props_PrtViewHandler);
                tilemode1view.commandTilemode1View_init();

                vdrawing.add(tilemode1view); // Add to drawing view

                // Add to tilemode 1 view list
                tilemode1viewlist.addElement(tilemode1view);
            }


            // Check if any active, valid vports found and
            // create one if necessary.
            if (tilemode1viewlist.size() == 0)
            {
                System.out.println("++++++++++YxxfPrtViewHandler:NO VPORT FOUND - GENERATING EXTENTS VIEW");

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
                Graphics tmpjgc;
                if (props_PrtViewHandler.getProperty_boolean("sharedjgc"))
                    tmpjgc = vroot.getJGC();
                else
                    tmpjgc = vroot.getJGC().create();
                YxxfPrtTilemode1View tilemode1view = new YxxfPrtTilemode1View(vroot.getDrawing(), tmpjgc,
                                                                              0, 0,
                                                                              vroot.getDspwinWidth(), vroot.getDspwinHeight(),
                                                                              vport);
                tilemode1view.setSharedJGCFlag(props_PrtViewHandler.getProperty_boolean("sharedjgc"));
                tilemode1view.setProperties(props_PrtViewHandler);
                tilemode1view.commandTilemode1View_init();
                tilemode1view.getGC().gc_doing_flag_zoom_to_calc_extents = true;

                vdrawing.add(tilemode1view); // Add to drawing view

                // Add to tilemode 1 view list
                tilemode1viewlist.addElement(tilemode1view);
            }


            // Set current to first view
            if (tilemode1viewlist.size() > 0)
                setCurrView((YxxfPrtTilemode1View)tilemode1viewlist.elementAt(0));
            else
                setCurrView(null);
            setLastView(null);


            // Start views
            Enumeration E1 = tilemode1viewlist.elements();
            while (E1.hasMoreElements())
            {
                YxxfPrtTilemode1View tilemode1view = (YxxfPrtTilemode1View)E1.nextElement();
                tilemode1view.commandTilemode1View_start();
            }


            //
            // Validate and Layout entire tree
            //
            vroot.validateLayout();


            // System.out.println("++++++++++YxxfPrtViewHandler:t1:END");
        }


        if (getDrawing().secHeader.tilemode == 0)
        {
//d         System.out.println("++++++++++YxxfPrtViewHandler:t0:BEG Viewport size=" + getDrawing().secEntities.entViewportList.size());

            {
                // Create the single view
                Graphics tmpjgc;
                if (props_PrtViewHandler.getProperty_boolean("sharedjgc"))
                    tmpjgc = vroot.getJGC();
                else
                    tmpjgc = vroot.getJGC().create();
                YxxfPrtTilemode0View tilemode0view = new YxxfPrtTilemode0View(vroot.getDrawing(), tmpjgc,
                                                                              0, 0,
                                                                              vroot.getDspwinWidth(), vroot.getDspwinHeight());
                tilemode0view.setSharedJGCFlag(props_PrtViewHandler.getProperty_boolean("sharedjgc"));
                tilemode0view.setProperties(props_PrtViewHandler);
                tilemode0view.commandTilemode0View_init();

                vdrawing.add(tilemode0view); // Add to drawing view

                // Add to tilemode 0 view list
                tilemode0viewlist.addElement(tilemode0view);
            }

            // Start views
            Enumeration E1 = tilemode0viewlist.elements();
            while (E1.hasMoreElements())
            {
                YxxfPrtTilemode0View tilemode0view = (YxxfPrtTilemode0View)E1.nextElement();
                tilemode0view.commandTilemode0View_start();
            }


            //
            // Validate and Layout entire tree
            //
            vroot.validateLayout();


            // System.out.println("++++++++++YxxfPrtViewHandler:t0:END");
        }

        setDrawingViewReady(true);

        // System.out.println("++++++++++YxxfPrtViewHandler:END commandViewHandler_command_start_drawing");
    }
    //==========================================================================


    //==========================================================================
    /**
     * Waits until all draw threads are idle.
     */
    public
    void commandViewHandler_command_wait_for_complete()
    {
        // System.out.println("++++++++++YxxfPrtViewHandler:BEG commandViewHandler_command_wait_for_complete,listsize=" +
        //                    tilemode1viewlist.size());

        // int doingsum = YxxfGfxContext.GC_DOING_WAITING;

        if (getDrawing().secHeader.tilemode == 1)
        {
            vdrawing.commandDrawingView_wait_for_complete();

            Enumeration E;
            YxxfPrtTilemode1View tilemode1view;

            // Wait for each view to complete
            E = tilemode1viewlist.elements();
            while (E.hasMoreElements())
            {
                tilemode1view = (YxxfPrtTilemode1View)E.nextElement();
                tilemode1view.commandTilemode1View_wait_for_complete();
            }
        } else

        if (getDrawing().secHeader.tilemode == 0)
        {
            Enumeration E;
            YxxfPrtTilemode0View tilemode0view;

            // Wait for each view to complete
            E = tilemode0viewlist.elements();
            while (E.hasMoreElements())
            {
                tilemode0view = (YxxfPrtTilemode0View)E.nextElement();
                tilemode0view.commandTilemode0View_wait_for_complete();
            }
        }

        // System.out.println("++++++++++YxxfPrtViewHandler:END commandViewHandler_command_wait_for_complete");
    }
    //==========================================================================


    //==========================================================================
    /**
     * List stats.
     */
    public
    void commandViewHandler_command_list_stats()
    {
//d     System.out.println("++++++++++YxxfPrtViewHandler:BEG list_stats,ready=" + getDrawing().getDrawingReady() + ",complete=" + getDrawing().getDrawingComplete());

        // header section
        if (props_PrtViewHandler.getProperty_boolean("listheader"))
        {
            System.out.println("++++++++++YxxfPrtViewHandler:BEG HEADER section");
            getDrawing().secHeader.listHeader();
            System.out.println("++++++++++YxxfPrtViewHandler:END HEADER section");
        }

        // tables section
        if (props_PrtViewHandler.getProperty_boolean("listvports"))
        {
            System.out.println("++++++++++YxxfPrtViewHandler:BEG VPORTs table");
            getDrawing().secTables.listVports();
            getDrawing().secEntities.listViewports();
            System.out.println("++++++++++YxxfPrtViewHandler:END VPORTs table");
        }

        if (props_PrtViewHandler.getProperty_boolean("listltypes"))
        {
            System.out.println("++++++++++YxxfPrtViewHandler:BEG LTYPEs table");
            getDrawing().secTables.listLtypes();
            System.out.println("++++++++++YxxfPrtViewHandler:END LTYPEs table");
        }

        if (props_PrtViewHandler.getProperty_boolean("listlayers"))
        {
            System.out.println("++++++++++YxxfPrtViewHandler:BEG LAYERs table");
            getDrawing().secTables.listLayers();
            System.out.println("++++++++++YxxfPrtViewHandler:END LAYERs table");
        }

        if (props_PrtViewHandler.getProperty_boolean("liststyles"))
        {
            System.out.println("++++++++++YxxfPrtViewHandler:BEG STYLEs table");
            getDrawing().secTables.listStyles();
            System.out.println("++++++++++YxxfPrtViewHandler:END STYLEs table");
        }

        // blocks section
        if (props_PrtViewHandler.getProperty_boolean("listblocks"))
        {
            System.out.println("++++++++++YxxfPrtViewHandler:BEG BLOCKs section");
            getDrawing().secBlocks.listBlocks();
            System.out.println("++++++++++YxxfPrtViewHandler:END BLOCKs section");
        }

        // entities section
        if (props_PrtViewHandler.getProperty_boolean("listentities"))
        {
            System.out.println("++++++++++YxxfPrtViewHandler:BEG ENTITIES section");
            getDrawing().secEntities.listEntities();
            System.out.println("++++++++++YxxfPrtViewHandler:END ENTITIES section");
        }

//d     System.out.println("++++++++++YxxfPrtViewHandler:END list_stats");
    }
    //==========================================================================


    //==========================================================================
    /**
     * Sets the current viewport by number.  Invalid number (like -1) sets view to null.
     * @param nextviewnum The tilemode 1 view number.
     */
    public
    void commandViewHandler_command_set_curr_view_num(int currviewnum)
    {
        if (currviewnum >= 0 && currviewnum < tilemode1viewlist.size())
            setCurrView((YxxfPrtTilemode1View)tilemode1viewlist.elementAt(currviewnum));
        else
            setCurrView(null);
    }

    /**
     * Sets the current viewport.
     * @param nextview The tilemode 1 view (may be null).
     */
    public
    void commandViewHandler_command_set_curr_view(YxxfPrtTilemode1View currview)
    {
        setCurrView(currview);
    }

    /**
     * Sets the current view port and highlights it.
     * @param currview The tilemode 1 view.
     */
    public
    void commandViewHandler_command_hilite_curr_view(YxxfPrtTilemode1View currview)
    {
        setCurrView(currview);
        vdrawing.drawCurrViewBorder(vdrawing.getJGC());
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
    void setCurrView(YxxfPrtTilemode1View currview)
    {
        this.tilemode1viewcurr = currview;
    }

    /**
     * Getter for current viewport.
     * @return nextview The tilemode 1 view.
     */
    public
    YxxfPrtTilemode1View getCurrView()
    {
        return this.tilemode1viewcurr;
    }

    /**
     * Setter for last drawn viewport.
     * @param nextview The tilemode 1 view.
     */
    public
    void setLastView(YxxfPrtTilemode1View lastview)
    {
        this.tilemode1viewlast = lastview;
    }

    /**
     * Getter for last drawn viewport.
     * @return The tilemode 1 view.
     */
    public
    YxxfPrtTilemode1View getLastView()
    {
        return this.tilemode1viewlast;
    }
    //==========================================================================


    //==========================================================================
    /**
     * Start the view.
     */
    public
    void commandViewHandler_toolbar_start()
    {
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
        // System.out.println("++++++++++YxxfPrtViewHandler:__:BEG commandViewHandlerRedraw");

        //
        // Validate and Layout entire tree
        //
        vroot.validateLayout();

        if (getDrawing().secHeader.tilemode == 1)
        {
            Enumeration E;
            YxxfPrtTilemode1View tilemode1view;

            // System.out.println("++++++++++YxxfPrtViewHandler:t1:    commandRedraw:vdrawing tilemode1viewlist.size()=" +
            //                    tilemode1viewlist.size());
            if (tilemode1viewlist.size() == 0)
                return;

            // Redraw all components
            commandViewHandler_command_hilite_all_views(); // draw borders
            if (vdrawing.getSharedJGCFlag())
                vdrawing.commandDrawingView_wait_for_complete();

            E = tilemode1viewlist.elements();
            while (E.hasMoreElements())
            {
                tilemode1view = (YxxfPrtTilemode1View)E.nextElement();
                tilemode1view.commandTilemode1View_redraw();
                if (tilemode1view.getSharedJGCFlag())
                    tilemode1view.commandTilemode1View_wait_for_complete();
            }

            // System.out.println("++++++++++YxxfPrtViewHandler:t1:END commandRedraw");
        } else

        if (getDrawing().secHeader.tilemode == 0)
        {
            Enumeration E;
            YxxfPrtTilemode0View tilemode0view;

//d         System.out.println("++++++++++YxxfPrtViewHandler:t0:    commandRedraw:vdrawing tilemode0viewlist.size()=" +
//d                            tilemode0viewlist.size());
            if (tilemode0viewlist.size() == 0)
                return;

            // Redraw all components
            E = tilemode0viewlist.elements();
            while (E.hasMoreElements())
            {
                tilemode0view = (YxxfPrtTilemode0View)E.nextElement();
                tilemode0view.commandTilemode0View_redraw();
                if (tilemode0view.getSharedJGCFlag())
                    tilemode0view.commandTilemode0View_wait_for_complete();
            }

//d         System.out.println("++++++++++YxxfPrtViewHandler:t0:END commandRedraw");
        }

//d     System.out.println("++++++++++YxxfPrtViewHandler:END commandViewHandlerRedraw");
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
        System.out.println("== YCAD LISTSTATS - YxxfPrtViewHandler");
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
                YxxfPrtTilemode0View tilemode0view = (YxxfPrtTilemode0View)E1.nextElement();
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
     * Turn rendering on / off.
     */
    public
    void commandViewHandler_command_set_rendering(boolean arg_val)
    {
        // System.out.println("++++++++++YxxfPrtViewHandler:__:BEG commandViewHandler_command_set_rendering");

        if (getDrawing().secHeader.tilemode == 1)
        {
            Enumeration E;
            YxxfPrtTilemode1View tilemode1view;

            if (tilemode1viewlist.size() == 0)
                return;

            // Set rendering for all components
            vdrawing.commandDrawingView_set_rendering(arg_val);

            E = tilemode1viewlist.elements();
            while (E.hasMoreElements())
            {
                tilemode1view = (YxxfPrtTilemode1View)E.nextElement();
                tilemode1view.commandTilemode1View_set_rendering(arg_val);
            }
        } else

        if (getDrawing().secHeader.tilemode == 0)
        {
            Enumeration E;
            YxxfPrtTilemode0View tilemode0view;

            if (tilemode0viewlist.size() == 0)
                return;

            // Redraw all components
            E = tilemode0viewlist.elements();
            while (E.hasMoreElements())
            {
                tilemode0view = (YxxfPrtTilemode0View)E.nextElement();
                tilemode0view.commandTilemode0View_set_rendering(arg_val);
            }
        }

//d     System.out.println("++++++++++YxxfPrtViewHandler:END commandViewHandler_command_set_rendering");
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
     * Rotate z_p. TODO
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
     * Rotate z_m. TODO
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
}

