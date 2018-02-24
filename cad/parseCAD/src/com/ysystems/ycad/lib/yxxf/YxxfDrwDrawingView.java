//==============================================================================
// YxxfDrwDrawingView.java
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfDrwDrawingView.java,v 1.30 2003/05/26 02:58:10 ekarlo Exp $
// $Log: YxxfDrwDrawingView.java,v $
// Revision 1.30  2003/05/26 02:58:10  ekarlo
// Add methods to turn rendering on and off.
// Improve calculation of extents.
//
// Revision 1.29  2003/05/17 12:42:21  ekarlo
// Add option to control width of border around views.
//
// Revision 1.28  2003/05/16 05:12:02  ekarlo
// Add options to control border view colors.
// Add methods to set current view.
//
// Revision 1.27  2003/05/08 11:24:02  ekarlo
// Remove warnings.
//
// Revision 1.26  2003/04/14 12:38:41  ekarlo
// Update source file header for OSI release.
//
// Revision 1.25  2002/10/03 09:03:49  ekarlo
// Make painting/updating methods consistent.
//
// Revision 1.24  2002-09-27 02:36:35-06  ekarlo
// Fix missing method error on MSIE with built in VM.
//
// Revision 1.23  2002-09-26 01:51:21-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.22  2001-05-18 23:14:27-06  ekarlo
// Print test.
//
// Revision 1.21  2001-05-11 22:58:39-06  ekarlo
// Make var name consistent.
//
// Revision 1.20  2000-10-17 01:44:07-06  ekarlo
// Change package paths to lower case.
//
// Revision 1.19  2000-10-16 17:49:33-06  ekarlo
// Browser printing test code.
//
// Revision 1.18  1999-10-22 01:28:01-06  ekarlo
// API rework - phase 1.
//
// Revision 1.17  1999-09-08 13:19:50-06  walter
// Add JavaDoc comments.
//
// Revision 1.16  1999-07-09 14:10:50-06  ekarlo
// Rearrange package names.
//
// Revision 1.15  1999/07/09  15:18:26  ekarlo
// Improve param properties scan.
//
// Revision 1.14  1999/07/05  00:09:31  ekarlo
// Fix comment.
//
// Revision 1.13  1999/06/20  22:34:10  ekarlo
// Rearrange package names.
//
// Revision 1.12  1999/06/15  04:56:43  ekarlo
// User Interface - phase 1.
//
// Revision 1.11  1999/02/09  14:50:20  ekarlo
// Deactivate console print.
//
// Revision 1.10  1998/08/24  20:40:00  ekarlo
// Add status display.
//
// Revision 1.9  1998/08/19  01:14:22  ekarlo
// Rename classes to change "Panel" to "View" in name.
//
// Revision 1.8  1998/02/02  19:09:41  ekarlo
// Implement paper space - phase 2.
//
// Revision 1.7  1997/12/26  21:31:06  ekarlo
// Implement paper space - phase 1.
// Rename properties object.
//
// Revision 1.6  1997/08/30  14:08:29  ekarlo
// Redo initialization action.
// Redo redraw action.
//
// Revision 1.5  1997/07/23  14:42:38  ekarlo
// Add user parameter printheader.
//
// Revision 1.4  1997/07/23  14:23:23  ekarlo
// Move get thread out of main thread.
//
// Revision 1.3  1997/07/22  12:46:52  ekarlo
// Rename from YcadvAppPanel.java to YxxfDrwMainPanel.java.
//
// Revision 1.2  1997/07/21  20:43:05  ekarlo
// MVC-VH rework - phase 2.
//
// Revision 1.1  1997/07/13  17:05:13  ekarlo
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
public class YxxfDrwDrawingView extends Panel
{
    //==========================================================================
    /**
     * Properties
     */
    private
    YutilProperties             props_DrwDrawingView
                                                    = new YutilProperties();
    //==========================================================================


    //==========================================================================
    // display items

    /**
     * Graphics context for this view.
     */
    private
    YxxfGfxContext              gc              = new YxxfGfxContext();

    /**
     * Draw thread for this view.
     */
    private
    YxxfDrwDrawingDrawT         vmain               = null;
    //==========================================================================


    //==========================================================================
    /**
     * View Handler.
     */
    private
    YxxfDrwViewHandler          vhandler        = null;
    //==========================================================================


    //==========================================================================

    /**
     * Constructor (empty).
     */
    private
    YxxfDrwDrawingView()
    {
    }
    
    /**
     * Constructor.
     * @param D The Drawing object.
     * @param vhandler The Viewhandler.
     */
    public
    YxxfDrwDrawingView(Yxxf D, YxxfDrwViewHandler vhandler)
    {
//d     System.out.println("++++YxxfDrwDrawingView:BEG constructor");

        setDrawing(D);
        setViewHandler(vhandler);

//d     System.out.println("++++YxxfDrwDrawingView:END constructor");
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
     * Set the value of the controlling Viewhandler.
     * @param vhandler The Viewhandler.
     */
    public
    void setViewHandler(YxxfDrwViewHandler vhandler)
    {
        this.vhandler = vhandler;
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
        props_DrwDrawingView.setProperties(args, scanout);
    }

    /**
     * Set properties list.
     * @param argv The property list.
     */
    public
    void setProperties(String argv[])
    {
        props_DrwDrawingView.setProperties(argv);
    }

    /**
     * Set properties list.
     * @param argprops The property list.
     */
    public
    void setProperties(YutilProperties argprops)
    {
        props_DrwDrawingView.setProperties(argprops);
    }
    //==========================================================================


    //==========================================================================
    /**
     * Initialize drawing view.
     */
    public
    void commandDrawingView_init()
    {
//d     System.out.println("++++YxxfDrwDrawingView:commandDrawingView_init()");
//d     // Display local params
//d     System.out.println("++++++YxxfDrwDrawingView:props_DrwDrawingView.e BEG");
//d     for (Enumeration e = props_DrwDrawingView.propertyNames(); e.hasMoreElements(); )
//d     {
//d         String key = (String)e.nextElement();
//d         String val = props_DrwDrawingView.getProperty(key);
//d         System.out.println("++++++YxxfDrwDrawingView:key=[" + key + "],val=[" + val + "]");
//d     }
//d     System.out.println("++++++YxxfDrwDrawingView:props_DrwDrawingView.e END");


        // Set layout manager for views
        setLayout(new YxxfDrwViewLayout());


        //
        // Create draw thread
        //
        vmain = new YxxfDrwDrawingDrawT(this, gc, vhandler);
        vmain.setProperties(props_DrwDrawingView);
    }


    //==========================================================================
    /**
     * Start drawing view in a new thread.
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
        repaint();
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

            int borderwidth           = props_DrwDrawingView.getProperty_int("borderwidth");
            Color borderactivecolor   = props_DrwDrawingView.getProperty_Color("borderactivecolor");
            Color borderinactivecolor = props_DrwDrawingView.getProperty_Color("borderinactivecolor");

            if (vhandler.getCurrView() == null)
            {   // no curr
                if (vhandler.getLastView() != null)
                {   // clear last if set
                    Rectangle lastbnds = vhandler.getLastView().bounds();
                    if (gc.getRenderingJGCFlag())
                        YxxfGfxContext.drawBorder(jgc, borderwidth, borderinactivecolor,
                                                  lastbnds.x, lastbnds.y, lastbnds.width, lastbnds.height);
                }
            }
            else

            {
                if (vhandler.getLastView() == null)
                {   // no last draw curr
                    Rectangle currbnds = vhandler.getCurrView().bounds();
                    if (gc.getRenderingJGCFlag())
                        YxxfGfxContext.drawBorder(jgc, borderwidth, borderactivecolor,
                                                  currbnds.x, currbnds.y, currbnds.width, currbnds.height);
                }
                else

                if (vhandler.getCurrView() != vhandler.getLastView())
                {   // clear last
                    Rectangle lastbnds = vhandler.getLastView().bounds();
                    if (gc.getRenderingJGCFlag())
                        YxxfGfxContext.drawBorder(jgc, borderwidth, borderinactivecolor,
                                                  lastbnds.x, lastbnds.y, lastbnds.width, lastbnds.height);

                    // draw curr
                    Rectangle currbnds = vhandler.getCurrView().bounds();
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
            YxxfDrwTilemode1View tilemode1view;

            int borderwidth           = props_DrwDrawingView.getProperty_int("borderwidth");
            Color borderactivecolor   = props_DrwDrawingView.getProperty_Color("borderactivecolor");
            Color borderinactivecolor = props_DrwDrawingView.getProperty_Color("borderinactivecolor");

            // spin thru views - don't draw curr view
            E = vhandler.tilemode1viewlist.elements();
            while (E.hasMoreElements())
            {
                tilemode1view = (YxxfDrwTilemode1View)E.nextElement();
                if (tilemode1view != vhandler.getCurrView())
                {
                    Rectangle viewbnds = tilemode1view.bounds();
                    if (gc.getRenderingJGCFlag())
                        YxxfGfxContext.drawBorder(jgc, borderwidth, borderinactivecolor,
                                                  viewbnds.x, viewbnds.y, viewbnds.width, viewbnds.height);
                }
            }

            // Draw curr view hilite border last if set
            if (vhandler.getCurrView() != null)
            {
                Rectangle currbnds = vhandler.getCurrView().bounds();
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
    // Painting
    //==========================================================================
    /**
     * Intercept paint(jgc).
     * @param jgc The Java Graphics object.
     */
    public
    void paint(Graphics jgc)
    {
        // System.out.println("++++++YxxfDrwDrawingView:paint   (" + jgc + "),isEn=" + isEnabled() + ",isSh=" + isShowing() + ",isVa=" + isValid() + ",isVi=" + isVisible());
        gc.commandGC_set_doing_redraw();
    }


//  /**
//   * Passthru paintAll(jgc).
//   * @param jgc The Java Graphics object.
//   */
//  public
//  void paintAll(Graphics jgc)
//  {
//      System.out.println("++++++YxxfDrwDrawingView:paintAll(" + jgc + "),isEn=" + isEnabled() + ",isSh=" + isShowing() + ",isVa=" + isValid() + ",isVi=" + isVisible());
//      super.paintAll(jgc);
//  }


//  /**
//   * Passthru repaint().
//   * @param jgc The Java Graphics object.
//   */
//  public
//  void repaint()
//  {
//      System.out.println("++++++YxxfDrwDrawingView:repaint (),isEn=" + isEnabled() + ",isSh=" + isShowing() + ",isVa=" + isValid() + ",isVi=" + isVisible());
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
//      System.out.println("++++++YxxfDrwDrawingView:repaint (" + x + "," + y + "," + width + "," + height + "),isEn=" + isEnabled() + ",isSh=" + isShowing() + ",isVa=" + isValid() + ",isVi=" + isVisible());
//      super.repaint(x, y, width, height);
//  }


//  /**
//   * Passthru repaint(tm).
//   * @param tm Time in milliseconds.
//   */
//  public
//  void repaint(long tm)
//  {
//      System.out.println("++++++YxxfDrwDrawingView:repaint (" + tm + "),isEn=" + isEnabled() + ",isSh=" + isShowing() + ",isVa=" + isValid() + ",isVi=" + isVisible());
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
//      System.out.println("++++++YxxfDrwDrawingView:repaint (" + tm + "," + x + "," + y + "," + width + "," + height + "),isEn=" + isEnabled() + ",isSh=" + isShowing() + ",isVa=" + isValid() + ",isVi=" + isVisible());
//      super.repaint(tm, x, y, width, height);
//  }


    /**
     * Intercept update.
     * @param jgc The Java Graphics object.
     */
    public
    void update(Graphics jgc)
    {
        // System.out.println("++++++YxxfDrwDrawingView:update  (" + jgc + "),isEn=" + isEnabled() + ",isSh=" + isShowing() + ",isVa=" + isValid() + ",isVi=" + isVisible());
        paint(jgc);
    }
    //==========================================================================


    //==========================================================================
    // Printing
    //==========================================================================
    /**
     * Hook for print().
     * Draws all view borders.
     * @param jpc The Java Graphics object for printing.
     */
    public void print(Graphics jpc)
    {
        System.out.println("++++++YxxfDrwDrawingView:print,jpc=" + jpc);

        drawAllViewBorders(jpc);
    }

    /**
     * Hook for printAll().
     * @param jpc The Java Graphics object for printing.
     */
    public void printAll(Graphics jpc)
    {
        System.out.println("++++++YxxfDrwDrawingView:printAll,jpc=" + jpc);
        super.printAll(jpc);
    }
    //==========================================================================


    //==========================================================================
    /**
     * Prepare GC for drawing (pre).
     */
    public
    void setupGC_predraw()
    {
        gc.setJGC(this.getGraphics());
    }


    /**
     * Prepare GC for drawing (post).
     */
    public
    void setupGC_postdraw()
    {
        gc.getJGC().dispose();
    }
    //==========================================================================
}

