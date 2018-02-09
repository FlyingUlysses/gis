//==============================================================================
// YxxfDrwDrawingDrawT.java
//
// Viewer drawing panel draw thread
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfDrwDrawingDrawT.java,v 1.30 2003/05/16 05:12:02 ekarlo Exp $
// $Log: YxxfDrwDrawingDrawT.java,v $
// Revision 1.30  2003/05/16 05:12:02  ekarlo
// Add options to control border view colors.
// Add methods to set current view.
//
// Revision 1.29  2003/05/08 11:24:02  ekarlo
// Remove warnings.
//
// Revision 1.28  2003/04/14 12:38:41  ekarlo
// Update source file header for OSI release.
//
// Revision 1.27  2002/09/29 05:20:54  ekarlo
// Change prefix of entity print options to "list".
//
// Revision 1.26  2002-09-26 01:51:22-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.25  2001-05-21 02:34:01-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.24  2001-05-20 02:43:07-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.23  2000-10-17 01:44:07-06  ekarlo
// Change package paths to lower case.
//
// Revision 1.22  1999-10-22 01:28:00-06  ekarlo
// API rework - phase 1.
//
// Revision 1.21  1999-09-08 13:18:15-06  walter
// Add JavaDoc comments.
//
// Revision 1.20  1999-07-09 14:10:50-06  ekarlo
// Rearrange package names.
//
// Revision 1.19  1999/07/09  15:18:26  ekarlo
// Improve param properties scan.
//
// Revision 1.18  1999/06/20  22:34:10  ekarlo
// Rearrange package names.
//
// Revision 1.17  1999/06/15  04:56:43  ekarlo
// User Interface - phase 1.
//
// Revision 1.16  1999/02/09  14:50:20  ekarlo
// Deactivate console print.
//
// Revision 1.15  1999/02/08  05:09:51  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.14  1998/08/24  20:39:33  ekarlo
// Add status display.
//
// Revision 1.13  1998/08/19  01:14:22  ekarlo
// Rename classes to change "Panel" to "View" in name.
//
// Revision 1.12  1998/07/12  00:06:32  ekarlo
// Rearrange print.
//
// Revision 1.11  1998/02/12  17:52:02  ekarlo
// Add ltscale print.
//
// Revision 1.10  1998/02/02  19:00:40  ekarlo
// Implement papaer space - phase 2.
// Rename view handler.
//
// Revision 1.9  1997/12/26  21:30:13  ekarlo
// Implement paper space - phase 1.
// Rename properties object.
// Implement user param printentities.
//
// Revision 1.8  1997/12/20  18:32:05  ekarlo
// Print tilemode value.
// Print viewports.
//
// Revision 1.7  1997/12/07  20:05:05  ekarlo
// Experiment with get/draw thread concurrency.
//
// Revision 1.6  1997/08/30  14:08:04  ekarlo
// Change user properties.
//
// Revision 1.5  1997/07/23  14:42:38  ekarlo
// Add user parameter printheader.
//
// Revision 1.4  1997/07/23  14:19:28  ekarlo
// Move get thread out of main thread.
//
// Revision 1.3  1997/07/22  12:49:07  ekarlo
// Rename from YcadvAppMainT.java to YxxfDrwMainDrawT.java.
//
// Revision 1.2  1997/07/21  20:43:05  ekarlo
// MVC-VH rework - phase 2.
//
// Revision 1.1  1997/07/13  17:04:45  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


import com.ysystems.lib.yutil.*;


/**
 * Viewer drawing panel draw thread.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfDrwDrawingDrawT implements Runnable
{
    //==========================================================================
    /**
     * Properties
     */
    private
    YutilProperties             props_DrwDrawingDrawT
                                                = new YutilProperties();
    //==========================================================================


    //==========================================================================
    // Drawing items

    /**
     * Drawing view
     */
    private
    YxxfDrwDrawingView          vdrawing        = null;

    /**
     * Graphics context
     */
    private
    YxxfGfxContext              gc              = null;
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
     * View Event - COMMAND_INIT_DRAWING.
     */
    private
    YxxfDrwViewHandlerEvent     vhandlerevt_command_init_drawing
                                                = new YxxfDrwViewHandlerEvent(
                                                          YxxfDrwViewHandlerEvent.COMMAND_INIT_DRAWING);

    /**
     * View Event - COMMAND_START_DRAWING.
     */
    private
    YxxfDrwViewHandlerEvent     vhandlerevt_command_start_drawing
                                                = new YxxfDrwViewHandlerEvent(
                                                          YxxfDrwViewHandlerEvent.COMMAND_START_DRAWING);

    /**
     * View Event - COMMAND_LIST_STATS.
     */
    private
    YxxfDrwViewHandlerEvent     vhandlerevt_command_list_stats
                                                = new YxxfDrwViewHandlerEvent(
                                                          YxxfDrwViewHandlerEvent.COMMAND_LIST_STATS);

    /**
     * View Event - GET_DRAWING_EOF.
     */
    private
    YxxfDrwViewHandlerEvent     vhandlerevt_command_get_drawing_eof
                                                = new YxxfDrwViewHandlerEvent(
                                                          YxxfDrwViewHandlerEvent.GET_DRAWING_EOF);
    //==========================================================================


    //==========================================================================
    /**
     * Constructor (empty)
     */
    public
    YxxfDrwDrawingDrawT()
    {
    }

    /**
     * Constructor
     * @param D Drawing
     * @param vdrawing DrawingView
     * @param gc Graphics context
     * @param vhandler View Handler
     */
    public
    YxxfDrwDrawingDrawT(YxxfDrwDrawingView vdrawing, YxxfGfxContext gc, YxxfDrwViewHandler vhandler)
    {
        setDrawingView(vdrawing);
        setGC(gc);
        setViewHandler(vhandler);
    }

    /**
     * Set reverence to a DrawingView.
     * @param vdrawing DrawingView
     * @return The DrawingView
     */
    public
    void setDrawingView(YxxfDrwDrawingView vdrawing)
    {
        this.vdrawing = vdrawing;
    }

    /**
     * Set Graphics Context.
     * @param gc GfxContext
     * @return The GfxContext
     */
    public
    void setGC(YxxfGfxContext gc)
    {
        this.gc = gc;
    }

    /**
     * Set the value of the Viewhandler and connect the monitor observer object.
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
    //==========================================================================


    //==========================================================================
    // Set properties

    /**
     * Set properties.
     * @param args A collection of Properties.
     * @param scanout TODO
     */
    public
    void setProperties(String args, boolean scanout)
    {
        props_DrwDrawingDrawT.setProperties(args, scanout);
    }

    /**
     * Set properties.
     * @param argv A collection of Properties.
     */
    public
    void setProperties(String argv[])
    {
        props_DrwDrawingDrawT.setProperties(argv);
    }

    /**
     * Set properties.
     * @param argprops A collection of Properties.
     */
    public
    void setProperties(YutilProperties argprops)
    {
        props_DrwDrawingDrawT.setProperties(argprops);
    }
    //==========================================================================


    /**
     * Implements Runnable.
     */
    public
    void run()
    {
        // System.out.println("++++++++YxxfDrwDrawingDrawT:BEG run()");

        // Wait for full drawing setup
        gc.getDrawing().waitDrawingReady();
        // System.out.println("++++++++YxxfDrwDrawingDrawT:BEG rp 01,tilemode=" + gc.getDrawing().secHeader.tilemode);

        if (gc.getDrawing().secHeader.tilemode == 1)
        {
            vhandlermon.notifyViewHandler(vhandlerevt_command_init_drawing.setArgs(null, null, 0, 0));
            vhandlermon.notifyViewHandler(vhandlerevt_command_start_drawing.setArgs(null, null, 0, 0));
        }


        if (gc.getDrawing().secHeader.tilemode == 0)
        {
            // Wait for full drawing load
            gc.getDrawing().waitDrawingComplete();

            vhandlermon.notifyViewHandler(vhandlerevt_command_init_drawing.setArgs(null, null, 0, 0));
            vhandlermon.notifyViewHandler(vhandlerevt_command_start_drawing.setArgs(null, null, 0, 0));
        }

        gc.getDrawing().waitDrawingComplete();

        vhandlermon.notifyViewHandler(vhandlerevt_command_list_stats.setArgs(null, null, 0, 0));
        vhandlermon.notifyViewHandler(vhandlerevt_command_get_drawing_eof.setArgs(null, null, 0, 0));



        while (true)
        {
            // System.out.println("++++++++YxxfDrwDrawingDrawT:doing=" + gc.gc_doing);

            // Wait and go wait some more
            if (gc.gc_doing == YxxfGfxContext.GC_DOING_WAITING)
            {
                gc.commandGC_wait();
                continue;
            }


            // Quit
            if (gc.gc_doing == YxxfGfxContext.GC_DOING_QUIT)
            {
                break;
            }


            // If not redraw then continue to wait
            if (gc.gc_doing != YxxfGfxContext.GC_DOING_REDRAW)
            {
                gc.commandGC_set_doing_waiting();
                continue;
            }


            // Full redraw
            if (gc.gc_doing == YxxfGfxContext.GC_DOING_REDRAW)
            {
                do_redraw();
                continue;
            }
        }

//d     System.out.println("++++++++YxxfDrwDrawingDrawT:END run()");
    }


    /**
     * Redraw the Drawing view.
     */
    public
    void do_redraw()
    {
        // System.out.println("++++++++YxxfDrwDrawingDrawT:BEG:DOING_REDRAW and go");
        gc.commandGC_set_doing_drawing();

        // Setup graphics context
        vdrawing.setupGC_predraw();


        // draw it
        vdrawing.drawAllViewBorders(gc.getJGC());


        // Check if draw ended normally
        if (gc.gc_doing == YxxfGfxContext.GC_DOING_DRAWING)
        {
            gc.commandGC_set_doing_waiting();
        }

        // Shutdown graphics context
        vdrawing.setupGC_postdraw();
        // System.out.println("++++++++YxxfDrwDrawingDrawT:END:DOING_REDRAW and go");
    }
}

