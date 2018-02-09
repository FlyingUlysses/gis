//==============================================================================
// YxxfDrwTilemode0View.java
//
// Tilemode 0 paper space setup
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfDrwTilemode0View.java,v 1.26 2003/06/03 09:53:15 ekarlo Exp $
// $Log: YxxfDrwTilemode0View.java,v $
// Revision 1.26  2003/06/03 09:53:15  ekarlo
// Fix xoffset= and yoffset= Prt imprinter funtionality.
//
// Revision 1.25  2003/05/26 02:58:10  ekarlo
// Add methods to turn rendering on and off.
// Improve calculation of extents.
//
// Revision 1.24  2003/05/08 11:24:03  ekarlo
// Remove warnings.
//
// Revision 1.23  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.22  2002/10/22 02:55:52  ekarlo
// Don't draw zero length wide polyline segments.
// Calculation was in error (not possible).
//
// Revision 1.21  2002-10-03 03:03:50-06  ekarlo
// Make painting/updating methods consistent.
//
// Revision 1.20  2002-09-28 02:27:27-06  ekarlo
// Fix missing method error on MSIE with built in VM.
//
// Revision 1.19  2002-09-26 01:51:20-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.18  2002-09-12 23:39:23-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.17  2001-05-20 02:43:07-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.16  2001-05-18 23:14:26-06  ekarlo
// Print test.
//
// Revision 1.15  2001-05-11 22:58:41-06  ekarlo
// Make var name consistent.
//
// Revision 1.14  2000-10-17 01:44:06-06  ekarlo
// Change package paths to lower case.
//
// Revision 1.13  2000-10-16 17:49:33-06  ekarlo
// Browser printing test code.
//
// Revision 1.12  1999-09-15 10:15:10-06  walter
// Added JavaDoc comments.
//
// Revision 1.11  1999-08-10 08:05:26-06  ekarlo
// Remove unused var warning.
//
// Revision 1.10  1999/07/09  20:10:50  ekarlo
// Rearrange package names.
//
// Revision 1.9  1999/07/09  15:18:26  ekarlo
// Improve param properties scan.
//
// Revision 1.8  1999/07/05  01:00:03  ekarlo
// Fix comment.
//
// Revision 1.7  1999/06/20  22:34:10  ekarlo
// Rearrange package names.
//
// Revision 1.6  1999/06/15  04:57:27  ekarlo
// User Interface - phase 1.
//
// Revision 1.5  1999/02/14  07:33:28  ekarlo
// Merge view controllers into views.
//
// Revision 1.4  1999/02/09  14:51:44  ekarlo
// Deactivate console print.
//
// Revision 1.3  1998/11/24  19:33:19  ekarlo
// Text - phase 2.
//
// Revision 1.2  1998/02/02  19:09:41  ekarlo
// Implement paper space - phase 2.
//
// Revision 1.1  1997/12/26  21:33:52  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


import java.awt.*;

import com.ysystems.lib.yutil.*;


/**
 * Tilemode 0 paper space setup.
 *
 * Paper Space/Model Space (floating viewports).<br>
 *
 * Single view with one draw thread.
 * Draw thread renders paper space and each model space sequentially.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfDrwTilemode0View extends Canvas
{
    //==========================================================================
    /**
     * Properties.
     */
    private 
    YutilProperties             props_DrwTilemode0View  
                                                = new YutilProperties();
    //==========================================================================


    //==========================================================================
    // display items

    /**
     * Graphics context for this view.
     */
    private
    YxxfGfxContext              gc              = new YxxfGfxContext();

    // Draw thread for this view
    private
    YxxfDrwTilemode0DrawT       Tdraw           = null;
    //==========================================================================


    //==========================================================================
    // Constructors

    /**
     * Constructor (empty)
     */
    public
    YxxfDrwTilemode0View()
    {
    }

    /**
     * Constructor
     * @param D The drawing
     */
    public
    YxxfDrwTilemode0View(Yxxf D)
    {
        setDrawing(D);
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
     * Set properties from args string
     * @param args The collection of properties.
     * @param scanout TODO
     */
    public
    void setProperties(String args, boolean scanout)
    {
        props_DrwTilemode0View.setProperties(args, scanout);
    }

    /**
     * Set properties from array
     * @param argv The collection of properties.
     */
    public
    void setProperties(String argv[])
    {
        props_DrwTilemode0View.setProperties(argv);
    }

    /**
     * Set properties from argprops YutilProperties.
     * @param args The collection of properties.
     */
    public
    void setProperties(YutilProperties argprops)
    {
        props_DrwTilemode0View.setProperties(argprops);
    }
    //==========================================================================


    //==========================================================================
    /**
     * Initialize Tilemode0 View and create a new draw thread.
     */
    public
    void commandTilemode0View_init()
    {
//d     // Display local params
//d     System.out.println("++++++++++++YxxfDrwTilemode0View:props_DrwTilemode0View.e BEG");
//d     for (Enumeration e = props_DrwTilemode0View.propertyNames(); e.hasMoreElements(); )
//d     {
//d         String key = (String)e.nextElement();
//d         String val = props_DrwTilemode0View.getProperty(key);
//d         System.out.println("++++++++++++YxxfDrwTilemode0View:key=[" + key + "],val=[" + val + "]");
//d     }
//d     System.out.println("++++++++++++YxxfDrwTilemode0View:props_DrwTilemode0View.e END");

        //
        // Create draw thread
        //
        Tdraw = new YxxfDrwTilemode0DrawT(this, gc);
        Tdraw.setProperties(props_DrwTilemode0View); // Set properties
    }
    //==========================================================================


    //==========================================================================
    /**
     * Start draw thread.
     */
    public
    void commandTilemode0View_start()
    {
        //
        // Start draw thread
        //
        Tdraw.start();
    }
    //==========================================================================


    //==========================================================================
    /**
     * Repaint the view.
     */
    public
    void commandTilemode0View_redraw()
    {
        repaint();
    }
    //==========================================================================


    //==========================================================================
    /**
     * Wait for the Tilemode0 View draw to complete.
     */
    public
    void commandTilemode0View_wait_for_complete()
    {
        gc.commandGC_wait_for_complete();
    }
    //==========================================================================


    //==========================================================================
    /**
     * Set rendering flag.
     */
    public
    void commandTilemode0View_set_rendering(boolean arg_val)
    {
        gc.setRenderingJGCFlag(arg_val);
    }
    //==========================================================================


    //==========================================================================
    /**
     * Get the location of the view.
     * @return The location.
     */
    public
    Point location()
    {
//d     System.out.println("++++++++++++YxxfDrwTilemode0View:location");
        Container parent = getParent();

        if (parent == null)
            return super.location();

//      Dimension parentsize = parent.size();
        
        return new Point(0, 0);
    }


    /**
     * Get the preferred size of the view.
     * @return The size.
     */
    public
    Dimension preferredSize()
    {
//d     System.out.println("++++++++++++YxxfDrwTilemode0View:preferredSize");
        Container parent = getParent();

        if (parent == null)
            return super.preferredSize();

        Dimension parentsize = parent.size();
                       
        return new Dimension(parentsize.width,
                             parentsize.height);
    }


    /**
     * Get the preferred size of the view.
     * @return The size.
     */
    public
    Dimension minimumSize()
    {
//d     System.out.println("++++++++++++YxxfDrwTilemode0View:minimumSize");
        return preferredSize();
    }
    //==========================================================================


    //==========================================================================
    /**
     * Resize the view.
     * @param w The width.
     * @param h The height.
     */
    public
    void resize(int w, int h)
    {
//d     System.out.println("++++++++++++YxxfDrwTilemode0View:resize(" + w + "," + h + ")");
        super.resize(w, h);
    }


    /**
     * Reshape the view.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param w The width.
     * @param h The height.
     */
    public
    void reshape(int x, int y, int w, int h)
    {
//d     System.out.println("++++++++++++YxxfDrwTilemode0View:reshape(" + x + "," + y + "," + w + "," + h + ")");
        super.reshape(x, y, w, h);
    }


//d public
//d void layout()
//d {
//d     System.out.println("++++++++++++YxxfDrwTilemode0View:layout(" + ")");
//d     System.out.println("                                :layout|isEn=" + isEnabled() +
//d                                                               ",isSh=" + isShowing() +
//d                                                               ",isVa=" + isValid() +
//d                                                               ",isVi=" + isVisible() );
//d     super.layout();
//d }
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
        // System.out.println("++++++++YxxfDrwTilemode0View:paint   (" + jgc + "),isEn=" + isEnabled() + ",isSh=" + isShowing() + ",isVa=" + isValid() + ",isVi=" + isVisible());
        gc.commandGC_set_doing_redraw();
    }


//  /**
//   * Passthru paintAll(jgc).
//   * @param jgc The Java Graphics object.
//   */
//  public
//  void paintAll(Graphics jgc)
//  {
//      System.out.println("++++++++YxxfDrwTilemode0View:paintAll(" + jgc + "),isEn=" + isEnabled() + ",isSh=" + isShowing() + ",isVa=" + isValid() + ",isVi=" + isVisible());
//      super.paintAll(jgc);
//  }


//  /**
//   * Passthru repaint().
//   * @param jgc The Java Graphics object.
//   */
//  public
//  void repaint()
//  {
//      System.out.println("++++++++YxxfDrwTilemode0View:repaint (),isEn=" + isEnabled() + ",isSh=" + isShowing() + ",isVa=" + isValid() + ",isVi=" + isVisible());
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
//      System.out.println("++++++++YxxfDrwTilemode0View:repaint (" + x + "," + y + "," + width + "," + height + "),isEn=" + isEnabled() + ",isSh=" + isShowing() + ",isVa=" + isValid() + ",isVi=" + isVisible());
//      super.repaint(x, y, width, height);
//  }


//  /**
//   * Passthru repaint(tm).
//   * @param tm Time in milliseconds.
//   */
//  public
//  void repaint(long tm)
//  {
//      System.out.println("++++++++YxxfDrwTilemode0View:repaint (" + tm + "),isEn=" + isEnabled() + ",isSh=" + isShowing() + ",isVa=" + isValid() + ",isVi=" + isVisible());
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
//      System.out.println("++++++++YxxfDrwTilemode0View:repaint (" + tm + "," + x + "," + y + "," + width + "," + height + "),isEn=" + isEnabled() + ",isSh=" + isShowing() + ",isVa=" + isValid() + ",isVi=" + isVisible());
//      super.repaint(tm, x, y, width, height);
//  }


    /**
     * Intercept update(jgc).
     * @param jgc The Java Graphics object.
     */
    public
    void update(Graphics jgc)
    {
        // System.out.println("++++++++YxxfDrwTilemode0View:update  (" + jgc + "),isEn=" + isEnabled() + ",isSh=" + isShowing() + ",isVa=" + isValid() + ",isVi=" + isVisible());
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
        System.out.println("++++++++YxxfDrwTilemode0View:print,jpc=" + jpc + ",jpc.getClipRect()=" + jpc_cliprect);

        // Create view
        YxxfPrtView
            prt_view = new YxxfPrtView(getDrawing(), jpc,
                                       (int)jpc_cliprect.x,
                                       (int)jpc_cliprect.y,
                                       (int)jpc_cliprect.width,
                                       (int)jpc_cliprect.height);
        YxxfPrtTilemode0View
            prt_tilemode0view = new YxxfPrtTilemode0View(getDrawing(), jpc,
                                                         (int)jpc_cliprect.x,
                                                         (int)jpc_cliprect.y,
                                                         (int)jpc_cliprect.width,
                                                         (int)jpc_cliprect.height);
        prt_view.add(prt_tilemode0view);

        prt_tilemode0view.setProperties(this.props_DrwTilemode0View);


        // Set print background color
        String str_printbgcolor = props_DrwTilemode0View.getProperty("printbgcolor");
        if (str_printbgcolor == null)
        {   // not set - make it white
            prt_tilemode0view.setProperties("bgcolor=#FFFFFF", false);
        }
        else
        if (str_printbgcolor.equals("bgcolor"))
        {   // match bgcolor
            String str_bgcolor = props_DrwTilemode0View.getProperty("bgcolor");
            if (str_bgcolor == null)
                prt_tilemode0view.setProperties("bgcolor=#000000", false);
            else
                prt_tilemode0view.setProperties("bgcolor=" + str_bgcolor, false);
        }
        else
        {   // explicit value
            prt_tilemode0view.setProperties("bgcolor=" + str_printbgcolor, false);
        }


        // Set print foreground palette
        String str_printfgcolor = props_DrwTilemode0View.getProperty("printfgcolor");
        if (str_printfgcolor == null)
        {   // depends on value of fgcolor
            String str_fgcolor = props_DrwTilemode0View.getProperty("fgcolor");
            if (str_fgcolor == null)
                prt_tilemode0view.setProperties("fgcolor=acihighinverse", false);
            else
                prt_tilemode0view.setProperties("fgcolor=" + str_fgcolor, false);
        }
        else
        if (str_printfgcolor.equals("fgcolor"))
        {   // match fgcolor
            String str_fgcolor = props_DrwTilemode0View.getProperty("fgcolor");
            if (str_fgcolor == null)
                prt_tilemode0view.setProperties("fgcolor=aci", false);
            else
                prt_tilemode0view.setProperties("fgcolor=" + str_fgcolor, false);
        }
        else
        {   // explicit value
            prt_tilemode0view.setProperties("fgcolor=" + str_printfgcolor, false);
        }


        // Render and wait
        prt_tilemode0view.commandTilemode0View_init();
        prt_tilemode0view.commandTilemode0View_start();
        prt_tilemode0view.commandTilemode0View_redraw();
        prt_tilemode0view.commandTilemode0View_wait_for_complete();

        // ToDo: stop thread
    }


    /**
     * Hook for printAll().
     * @param jpc The Java Graphics object for printing.
     */
    public
    void printAll(Graphics jpc)
    {
        System.out.println("++++++++YxxfDrwTilemode0View:printAll,jpc=" + jpc);
        super.printAll(jpc);
    }
    //==========================================================================


    //==========================================================================
    /**
     * Prepare GC for drawing (pre1).
     */
    public
    void setupGC_predraw1()
    {
        // Set the Java Graphics Context
        gc.setJGC(this.getGraphics());
        gc.setSharedJGCFlag(false);
        gc.setDspwinXY(0, 0);
        gc.setDspwin(this.size());

        // Sync JGC
        gc.syncJGCtoDspwin();


        // Set background color
        String str_bgcolor = props_DrwTilemode0View.getProperty("bgcolor");
        if (str_bgcolor == null)
            gc.setBGColor("#000000");
        else
            gc.setBGColor(str_bgcolor);


        // Set foreground palette
        String str_fgcolor = props_DrwTilemode0View.getProperty("fgcolor");
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
    }

    /**
     * Prepare GC for drawing (pre2).
     */
    public
    YxxfEntInsert setupGC_predraw2(YxxfEntViewport viewport)
    {
        // Set viewport
        gc.setViewport(viewport);
        gc.setDspwin(viewport.vpDspLocation.x, viewport.vpDspLocation.y,
                     viewport.vpDspSize.width, viewport.vpDspSize.height);

        // Sync JGC
        gc.syncJGCtoDspwin();


        // Set space
        YxxfEntInsert ins;
        if (viewport.vpid == 1)
            ins = gc.getDrawing().secEntities.insPSpace;
        else
            ins = gc.getDrawing().secEntities.insMSpace;
        gc.setupInsertInit(ins);
        gc.initModelStack();


        // Calc transformation and scale to fit window
        gc.calcdsp_viewport();

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

