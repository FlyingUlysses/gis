//==============================================================================
// YxxfPrtDrawingView.java
//
// Viewer drawing Panel
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfPrtDrawingView.java,v 1.11 2003/06/03 09:53:14 ekarlo Exp $
// $Log: YxxfPrtDrawingView.java,v $
// Revision 1.11  2003/06/03 09:53:14  ekarlo
// Fix xoffset= and yoffset= Prt imprinter funtionality.
//
// Revision 1.10  2003/05/26 02:58:10  ekarlo
// Add methods to turn rendering on and off.
// Improve calculation of extents.
//
// Revision 1.9  2003/05/17 12:42:20  ekarlo
// Add option to control width of border around views.
//
// Revision 1.8  2003/05/16 05:12:02  ekarlo
// Add options to control border view colors.
// Add methods to set current view.
//
// Revision 1.7  2003/05/08 11:24:02  ekarlo
// Remove warnings.
//
// Revision 1.6  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.5  2002/09/26 07:51:21  ekarlo
// Imprinter development check-in.
//
// Revision 1.4  2001-05-21 02:34:04-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.3  2001-05-20 02:42:59-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.2  2001-05-17 23:33:05-06  ekarlo
// Development check-in.
//
// Revision 1.1  2001-05-17 02:52:25-06  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


import java.awt.*;
import java.util.*;

import com.ysystems.lib.yutil.*;


/**
 * Viewer drawing Panel.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfPrtDrawingView extends YxxfPrtContainer
{
    //==========================================================================
    /**
     * Properties
     */
    private
    YutilProperties             props_PrtDrawingView
                                                    = new YutilProperties();
    //==========================================================================


    //==========================================================================
    // display items

    /**
     * Draw thread for this view.
     */
    private
    YxxfPrtDrawingDrawT         vmain               = null;
    //==========================================================================


    //==========================================================================
    /**
     * View Handler.
     */
    private
    YxxfPrtViewHandler          vhandler        = null;
    //==========================================================================


    //==========================================================================

    /**
     * Constructor (empty)
     */
    private
    YxxfPrtDrawingView()
    {
    }

    /**
     * Constructor 
     * @param D The Drawing object
     * @param jgc The Java Graphics Context
     */
    public
    YxxfPrtDrawingView(Yxxf D, Graphics jgc, YxxfPrtViewHandler vhandler)
    {
//d     System.out.println("++++YxxfPrtDrawingView:BEG constructor2");

        setDrawing(D);
        setJGC(jgc);
        setViewHandler(vhandler);

//d     System.out.println("++++YxxfPrtDrawingView:END constructor2");
    }

    /**
     * Constructor 
     * @param D The Drawing object
     * @param jgc The Java Graphics Context
     */
    public
    YxxfPrtDrawingView(Yxxf D, Graphics jgc, YxxfPrtViewHandler vhandler,
                       int x, int y, int width, int height)
    {
//d     System.out.println("++++YxxfPrtDrawingView:BEG constructor3");

        setDrawing(D);
        setJGC(jgc);
        setViewHandler(vhandler);
        getGC().setDspwinX(x);
        getGC().setDspwinY(y);
        getGC().setDspwinWidth(width);
        getGC().setDspwinHeight(height);

//d     System.out.println("++++YxxfPrtDrawingView:END constructor3");
    }


    /**
     * Set the value of the controlling Viewhandler.
     * @param vhandler The Viewhandler.
     */
    public
    void setViewHandler(YxxfPrtViewHandler vhandler)
    {
        this.vhandler = vhandler;
    }

    /**
     * Get the Viewhandler.
     * @return The Viewhandler.
     */
    public
    YxxfPrtViewHandler getViewHandler()
    {
        return vhandler;
    }
    //==========================================================================


    //==========================================================================
    // Set properties

    /**
     * Set properties from args string.
     * @param args The property list.
     * @param scanout TODO
     */
    public
    void setProperties(String args, boolean scanout)
    {
        props_PrtDrawingView.setProperties(args, scanout);
    }

    /**
     * Set properties list.
     * @param argv The property list.
     */
    public
    void setProperties(String argv[])
    {
        props_PrtDrawingView.setProperties(argv);
    }

    /**
     * Set properties list.
     * @param argprops The property list.
     */
    public
    void setProperties(YutilProperties argprops)
    {
        props_PrtDrawingView.setProperties(argprops);
    }
    //==========================================================================


    //==========================================================================
    /**
     * Initialize drawing view.
     */
    public
    void commandDrawingView_init()
    {
//d     // Display local params
//d     System.out.println("++++++YxxfPrtDrawingView:props_PrtDrawingView.e BEG");
//d     for (Enumeration e = props_PrtDrawingView.propertyNames(); e.hasMoreElements(); )
//d     {
//d         String key = (String)e.nextElement();
//d         String val = props_PrtDrawingView.getProperty(key);
//d         System.out.println("++++++YxxfPrtDrawingView:key=[" + key + "],val=[" + val + "]");
//d     }
//d     System.out.println("++++++YxxfPrtDrawingView:props_PrtDrawingView.e END");


        // Set layout manager for views
        // Default is YxxfPrtViewLayout

        
        // Create draw thread
        vmain = new YxxfPrtDrawingDrawT(this, gc, vhandler);
        vmain.setProperties(props_PrtDrawingView); // Set properties
    }
    //==========================================================================


    //==========================================================================
    /**
     * Start Drawing view in a new thread.
     */
    public
    void commandDrawingView_start()
    {
        // Start draw thread
        new Thread(vmain).start();
    }
    //==========================================================================


    //==========================================================================
    /**
     * Redraw the Drawing View.
     */
    public
    void commandDrawingView_redraw()
    {
        paint(getJGC());
    }
    //==========================================================================


    //==========================================================================
    /**
     * Wait for the Drawing View draw to complete.
     */
    public
    void commandDrawingView_wait_for_complete()
    {
        gc.commandGC_wait_for_complete();
    }
    //==========================================================================


    //==========================================================================
    /**
     * Set rendering flag.
     */
    public
    void commandDrawingView_set_rendering(boolean arg_val)
    {
        gc.setRenderingJGCFlag(arg_val);
    }
    //==========================================================================


    //==========================================================================
    /**
     * Clears and draws borders depending on curr and last views.
     */
    public
    void drawCurrViewBorder(Graphics jgc)
    {
        if (gc.getDrawing().secHeader.tilemode == 1)
        {
            if (jgc == null)
                return;

            int borderwidth           = props_PrtDrawingView.getProperty_int("borderwidth");
            Color borderactivecolor   = props_PrtDrawingView.getProperty_Color("borderactivecolor");
            Color borderinactivecolor = props_PrtDrawingView.getProperty_Color("borderinactivecolor");

            if (vhandler.getCurrView() == null)
            {   // no curr
                if (vhandler.getLastView() != null)
                {   // clear last if set
                    Rectangle lastbnds = vhandler.getLastView().getBounds();
                    if (gc.getRenderingJGCFlag())
                        YxxfGfxContext.drawBorder(jgc, borderwidth, borderinactivecolor,
                                                  lastbnds.x, lastbnds.y, lastbnds.width, lastbnds.height);
                }
            }
            else

            {
                if (vhandler.getLastView() == null)
                {   // no last draw curr
                    Rectangle currbnds = vhandler.getCurrView().getBounds();
                    if (gc.getRenderingJGCFlag())
                        YxxfGfxContext.drawBorder(jgc, borderwidth, borderactivecolor,
                                                  currbnds.x, currbnds.y, currbnds.width, currbnds.height);
                }
                else

                if (vhandler.getCurrView() != vhandler.getLastView())
                {   // clear last
                    Rectangle lastbnds = vhandler.getLastView().getBounds();
                    if (gc.getRenderingJGCFlag())
                        YxxfGfxContext.drawBorder(jgc, borderwidth, borderinactivecolor,
                                                  lastbnds.x, lastbnds.y, lastbnds.width, lastbnds.height);

                    // draw curr
                    Rectangle currbnds = vhandler.getCurrView().getBounds();
                    if (gc.getRenderingJGCFlag())
                        YxxfGfxContext.drawBorder(jgc, borderwidth, borderactivecolor,
                                                  currbnds.x, currbnds.y, currbnds.width, currbnds.height);
                }
            }

            vhandler.setLastView(vhandler.getCurrView()); // set last drawn
        } else

        if (gc.getDrawing().secHeader.tilemode == 0)
        {
        }
    }


    /**
     * Draws a border around all views and hilites the current selection.
     */
    public
    void drawAllViewBorders(Graphics jgc)
    {
        if (jgc == null)
            return;

        if (gc.getDrawing().secHeader.tilemode == 1)
        {
            Enumeration E;
            YxxfPrtTilemode1View tilemode1view;

            int borderwidth           = props_PrtDrawingView.getProperty_int("borderwidth");
            Color borderactivecolor   = props_PrtDrawingView.getProperty_Color("borderactivecolor");
            Color borderinactivecolor = props_PrtDrawingView.getProperty_Color("borderinactivecolor");

            // spin thru views - don't draw curr view
            E = vhandler.tilemode1viewlist.elements();
            while (E.hasMoreElements())
            {
                tilemode1view = (YxxfPrtTilemode1View)E.nextElement();
                if (tilemode1view != vhandler.getCurrView())
                {
                    Rectangle viewbnds = tilemode1view.getBounds();
                    if (gc.getRenderingJGCFlag())
                        YxxfGfxContext.drawBorder(jgc, borderwidth, borderinactivecolor,
                                                  viewbnds.x, viewbnds.y, viewbnds.width, viewbnds.height);
                }
            }

            // Draw curr view hilite border last if set
            if (vhandler.getCurrView() != null)
            {
                Rectangle currbnds = vhandler.getCurrView().getBounds();
                if (gc.getRenderingJGCFlag())
                    YxxfGfxContext.drawBorder(jgc, borderwidth, borderactivecolor,
                                              currbnds.x, currbnds.y, currbnds.width, currbnds.height);
            }

            vhandler.setLastView(vhandler.getCurrView()); // set last drawn
        } else

        if (gc.getDrawing().secHeader.tilemode == 0)
        {
        }
    }
    //==========================================================================


    //==========================================================================
    /**
     * Intercept Panel.paint().
     * @param jgc The Java Graphics object.
     */
    public
    void paint(Graphics jgc)
    {
        // System.out.println("++++YxxfPrtDrawingView:paint(" + jgc + ")");

        gc.commandGC_set_doing_redraw();
    }
    //==========================================================================


    //==========================================================================
    /**
     * Prepare GC for drawing (pre).
     */
    public
    void setupGC_predraw()
    {
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

