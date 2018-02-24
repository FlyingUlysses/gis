//==============================================================================
// YxxfDrwView.java
//
// Main Panel for Viewer
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfDrwView.java,v 1.26 2003/05/08 11:24:03 ekarlo Exp $
// $Log: YxxfDrwView.java,v $
// Revision 1.26  2003/05/08 11:24:03  ekarlo
// Remove warnings.
//
// Revision 1.25  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.24  2002/10/30 02:22:58  ekarlo
// Rearrange initialization of option defaults.
//
// Revision 1.23  2002-10-03 03:03:49-06  ekarlo
// Make painting/updating methods consistent.
//
// Revision 1.22  2002-09-28 23:20:55-06  ekarlo
// Change prefix of entity print options to "list".
//
// Revision 1.21  2002-09-26 01:51:23-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.20  2002-09-12 15:11:12-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.19  2001-05-18 23:14:25-06  ekarlo
// Print test.
//
// Revision 1.18  2001-05-11 22:58:46-06  ekarlo
// Make var name consistent.
//
// Revision 1.17  2000-10-17 01:44:03-06  ekarlo
// Change package paths to lower case.
//
// Revision 1.16  2000-10-16 17:49:32-06  ekarlo
// Browser printing test code.
//
// Revision 1.15  1999-10-22 01:27:53-06  ekarlo
// API rework - phase 1.
//
// Revision 1.14  1999-10-06 20:09:55-06  walter
// Added JavaDoc comments.
//
// Revision 1.13  1999-09-28 11:09:45-06  walter
// Add JavaDoc comments.
//
// Revision 1.12  1999-07-25 17:22:34-06  ekarlo
// Change bgcolor default.
//
// Revision 1.11  1999/07/09  20:10:50  ekarlo
// Rearrange package names.
//
// Revision 1.10  1999/07/09  15:21:07  ekarlo
// Improve param properties scan.
//
// Revision 1.9  1999/06/20  22:34:10  ekarlo
// Rearrange package names.
//
// Revision 1.8  1999/06/15  04:58:33  ekarlo
// User Interface - phase 1.
//
// Revision 1.7  1999/02/09  14:50:20  ekarlo
// Deactivate console print.
//
// Revision 1.6  1998/12/21  15:43:26  ekarlo
// Text - phase 3.
//
// Revision 1.5  1998/11/24  19:51:08  ekarlo
// Text - phase 2.
//
// Revision 1.4  1998/08/24  20:41:06  ekarlo
// Add status display.
//
// Revision 1.3  1998/08/19  01:18:43  ekarlo
// Rename classes to change "Panel" to "View" in name.
//
// Revision 1.2  1998/02/02  19:09:41  ekarlo
// Implement paper space - phase 2.
//
// Revision 1.1  1997/12/26  21:32:35  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


import java.awt.*;

import com.ysystems.lib.yutil.*;


/**
 * Main Panel for Viewer.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfDrwView extends Panel
{
    //==========================================================================
    /**
     * Properties
     */
    private
    YutilProperties             props_DrwView       = new YutilProperties();
    //==========================================================================


    //==========================================================================
    // display items

    /**
     * Graphics context for this view.
     */
    private
    YxxfGfxContext              gc              = new YxxfGfxContext();
    //==========================================================================


    //==========================================================================
    /**
     * View Handler.
     */
    private
    YxxfDrwViewHandler          vhandler            = null;
    //==========================================================================


    //==========================================================================
    /**
     * Constructor
     */
    public
    YxxfDrwView()
    {
    }

    /**
     * Constructor
     * @param D The Drawing.
     * @param vhandler The Viewhandler.
     */
    public
    YxxfDrwView(Yxxf D, YxxfDrwViewHandler vhandler)
    {
        setDrawing(D);
        setViewHandler(vhandler);
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
    YxxfDrwViewHandler getViewHandlerx()
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
     * @param args The Properties collection.
     * @param scanout TODO
     */
    public
    void setProperties(String args, boolean scanout)
    {
        props_DrwView.setProperties(args, scanout);
    }

    /**
     * Set properties from argv array.
     * @param argv The Properties collection.
     */
    public
    void setProperties(String argv[])
    {
        props_DrwView.setProperties(argv);
    }

    /**
     * Set properties from argprops YutilProperties.
     * @param argprops The Properties collection.
     */
    public
    void setProperties(YutilProperties argprops)
    {
        props_DrwView.setProperties(argprops);
    }
    //==========================================================================

    


    //==========================================================================
    /**
     * Intialize the Main Panel for the Viewer (no-op).
     */
    public
    void commandInit()
    {
    }


    /**
     * Start the Main Panel for the Viewer.
     */
    public
    void commandStart()
    {
//d     // Display local params
//d     System.out.println("++++YxxfDrwView:props_DrwView.e BEG");
//d     for (Enumeration e = props_DrwView.propertyNames(); e.hasMoreElements(); )
//d     {
//d         String key = (String)e.nextElement();
//d         String val = props_DrwView.getProperty(key);
//d         System.out.println("++++YxxfDrwView:key=[" + key + "],val=[" + val + "]");
//d     }
//d     System.out.println("++++YxxfDrwView:props_DrwView.e END");
    }
    //==========================================================================


    //==========================================================================
    // Painting
    //==========================================================================
    /**
     * Deactivate paint(jgc).
     * @param jgc The Java Graphics object.
     */
    public
    void paint(Graphics jgc)
    {
        // System.out.println("++++YxxfDrwView:paint   (" + jgc + "),isEn=" + isEnabled() + ",isSh=" + isShowing() + ",isVa=" + isValid() + ",isVi=" + isVisible());
    }


//  /**
//   * Passthru paintAll(jgc).
//   * @param jgc The Java Graphics object.
//   */
//  public
//  void paintAll(Graphics jgc)
//  {
//      System.out.println("++++YxxfDrwView:paintAll(" + jgc + "),isEn=" + isEnabled() + ",isSh=" + isShowing() + ",isVa=" + isValid() + ",isVi=" + isVisible());
//      super.paintAll(jgc);
//  }


//  /**
//   * Passthru repaint().
//   * @param jgc The Java Graphics object.
//   */
//  public
//  void repaint()
//  {
//      System.out.println("++++YxxfDrwView:repaint (),isEn=" + isEnabled() + ",isSh=" + isShowing() + ",isVa=" + isValid() + ",isVi=" + isVisible());
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
//      System.out.println("++++YxxfDrwView:repaint (" + x + "," + y + "," + width + "," + height + "),isEn=" + isEnabled() + ",isSh=" + isShowing() + ",isVa=" + isValid() + ",isVi=" + isVisible());
//      super.repaint(x, y, width, height);
//  }


//  /**
//   * Passthru repaint(tm).
//   * @param tm Time in milliseconds.
//   */
//  public
//  void repaint(long tm)
//  {
//      System.out.println("++++YxxfDrwView:repaint (" + tm + "),isEn=" + isEnabled() + ",isSh=" + isShowing() + ",isVa=" + isValid() + ",isVi=" + isVisible());
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
//      System.out.println("++++YxxfDrwView:repaint (" + tm + "," + x + "," + y + "," + width + "," + height + "),isEn=" + isEnabled() + ",isSh=" + isShowing() + ",isVa=" + isValid() + ",isVi=" + isVisible());
//      super.repaint(tm, x, y, width, height);
//  }


    /**
     * Intercept update(jgc).
     * @param jgc The Java Graphics object.
     */
    public
    void update(Graphics jgc)
    {
        // System.out.println("++++YxxfDrwView:update  (" + jgc + "),isEn=" + isEnabled() + ",isSh=" + isShowing() + ",isVa=" + isValid() + ",isVi=" + isVisible());
        paint(jgc);
    }
    //==========================================================================


    //==========================================================================
    // Printing
    //==========================================================================
    /**
     * Hook for print().
     * @param jpc The Java Graphics object for printing.
     */
    public void print(Graphics jpc)
    {
        System.out.println("++++YxxfDrwView:print,jpc=" + jpc);
        super.print(jpc);
    }

    /**
     * Hook for printAll().
     * @param jpc The Java Graphics object for printing.
     */
    public void printAll(Graphics jpc)
    {
        System.out.println("++++YxxfDrwView:printAll,jpc=" + jpc);
        super.printAll(jpc);
    }
    //==========================================================================
}

