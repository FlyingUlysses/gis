//==============================================================================
// YxxfPrtDrawingDrawT.java
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfPrtDrawingDrawT.java,v 1.9 2003/05/16 05:12:02 ekarlo Exp $
// $Log: YxxfPrtDrawingDrawT.java,v $
// Revision 1.9  2003/05/16 05:12:02  ekarlo
// Add options to control border view colors.
// Add methods to set current view.
//
// Revision 1.8  2003/05/08 11:24:03  ekarlo
// Remove warnings.
//
// Revision 1.7  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.6  2002/09/29 05:20:54  ekarlo
// Change prefix of entity print options to "list".
//
// Revision 1.5  2002-09-26 01:51:20-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.4  2001-05-21 02:33:59-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.3  2001-05-20 02:43:00-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.2  2001-05-17 23:33:08-06  ekarlo
// Development check-in.
//
// Revision 1.1  2001-05-17 02:52:24-06  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;

import com.ysystems.lib.yutil.*;


/**
 * Viewer drawing panel draw thread.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfPrtDrawingDrawT implements Runnable
{
    //==========================================================================
    /**
     * Properties
     */
    private
    YutilProperties             props_PrtDrawingDrawT
                                                = new YutilProperties();
    //==========================================================================


    //==========================================================================
    // Drawing items

    /**
     * Drawing view being drawn into
     */
    private
    YxxfPrtDrawingView          vdrawing        = null;

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
    YxxfPrtViewHandler          vhandler        = null;

    /**
     * View Monitor.
     */
    private
    YxxfPrtViewMonitor          vhandlermon     = new YxxfPrtViewMonitor();

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
    YxxfPrtDrawingDrawT()
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
    YxxfPrtDrawingDrawT(YxxfPrtDrawingView vdrawing, YxxfGfxContext gc, YxxfPrtViewHandler vhandler)
    {
        setDrawingView(vdrawing);
        setGC(gc);
        setViewHandler(vhandler);
    }

    /**
     * Set reverence to a DrawingView.
     * @param vdrawing DrawingView
     */
    public
    void setDrawingView(YxxfPrtDrawingView vdrawing)
    {
        this.vdrawing = vdrawing;
    }

    /**
     * Set Graphics Context.
     * @param gc GfxContext
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
    void setViewHandler(YxxfPrtViewHandler vhandler)
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
        props_PrtDrawingDrawT.setProperties(args, scanout);
    }

    /**
     * Set properties.
     * @param argv A collection of Properties.
     */
    public
    void setProperties(String argv[])
    {
        props_PrtDrawingDrawT.setProperties(argv);
    }

    /**
     * Set properties.
     * @param argprops A collection of Properties.
     */
    public
    void setProperties(YutilProperties argprops)
    {
        props_PrtDrawingDrawT.setProperties(argprops);
    }
    //==========================================================================


    /**
     * Implements Runnable.
     */
    public
    void run()
    {
        // System.out.println("++++++++YxxfPrtDrawingDrawT:BEG run()");

        // Wait for full drawing setup
        gc.getDrawing().waitDrawingReady();
        // System.out.println("++++++++YxxfPrtDrawingDrawT:drawing ready:tilemode=" + D.secHeader.tilemode);

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
            // System.out.println("++++++++YxxfPrtDrawingDrawT:doing=" + gc.gc_doing);

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

        // System.out.println("++++++++YxxfPrtDrawingDrawT:END run()");
    }


    /**
     * Redraw the Drawing view.
     */
    public
    void do_redraw()
    {
        // System.out.println("++++++++YxxfPrtDrawingDrawT:BEG:DOING_REDRAW and go,drawtype=" + vdrawing.drawtype);
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
        // System.out.println("++++++++YxxfPrtDrawingDrawT:END:DOING_REDRAW and go");
    }
}

