//==============================================================================
// YxxfPrtTilemode0View.java
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfPrtTilemode0View.java,v 1.11 2003/06/03 09:53:14 ekarlo Exp $
// $Log: YxxfPrtTilemode0View.java,v $
// Revision 1.11  2003/06/03 09:53:14  ekarlo
// Fix xoffset= and yoffset= Prt imprinter funtionality.
//
// Revision 1.10  2003/05/26 02:58:10  ekarlo
// Add methods to turn rendering on and off.
// Improve calculation of extents.
//
// Revision 1.9  2003/05/08 11:24:03  ekarlo
// Remove warnings.
//
// Revision 1.8  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.7  2002/10/22 01:21:38  ekarlo
// Change comments.
//
// Revision 1.6  2002-09-26 01:51:23-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.5  2002-09-12 23:39:23-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.4  2001-05-21 02:34:06-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.3  2001-05-20 14:38:11-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.2  2001-05-20 02:42:55-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.1  2001-05-17 02:52:26-06  ekarlo
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
public class YxxfPrtTilemode0View extends YxxfPrtComponent
{
    //==========================================================================
    /**
     * Properties.
     */
    private 
    YutilProperties             props_PrtTilemode0View  
                                                = new YutilProperties();
    //==========================================================================


    //==========================================================================
    // display items

    //
    // Graphics context is in parent component
    //
    // YxxfGfxContext gc

    // Draw thread for this view
    private
    YxxfPrtTilemode0DrawT       Tdraw           = null;
    //==========================================================================


    //==========================================================================
    // Constructors

    /**
     * Constructor (empty)
     */
    public
    YxxfPrtTilemode0View()
    {
    }

    /**
     * Constructor
     * @param D The drawing
     */
    public
    YxxfPrtTilemode0View(Yxxf D, Graphics jgc)
    {
        setDrawing(D);
        setJGC(jgc);
    }


    /**
     * Constructor
     * @param D The drawing
     */
    public
    YxxfPrtTilemode0View(Yxxf D, Graphics jgc,
                         int x, int y, int width, int height)
    {
        setDrawing(D);
        setJGC(jgc);
        getGC().setDspwin(x, y, width, height);
    }


    /**
     * Set the value of the Drawing.
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
        props_PrtTilemode0View.setProperties(args, scanout);
    }

    /**
     * Set properties from array
     * @param argv The collection of properties.
     */
    public
    void setProperties(String argv[])
    {
        props_PrtTilemode0View.setProperties(argv);
    }

    /**
     * Set properties from argprops YutilProperties.
     * @param args The collection of properties.
     */
    public
    void setProperties(YutilProperties argprops)
    {
        props_PrtTilemode0View.setProperties(argprops);
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
//d     System.out.println("++++++++++++YxxfPrtTilemode0View:props_PrtTilemode0View.e BEG");
//d     for (Enumeration e = props_PrtTilemode0View.propertyNames(); e.hasMoreElements(); )
//d     {
//d         String key = (String)e.nextElement();
//d         String val = props_PrtTilemode0View.getProperty(key);
//d         System.out.println("++++++++++++YxxfPrtTilemode0View:key=[" + key + "],val=[" + val + "]");
//d     }
//d     System.out.println("++++++++++++YxxfPrtTilemode0View:props_PrtTilemode0View.e END");

        //
        // Create draw thread
        //
        Tdraw = new YxxfPrtTilemode0DrawT(this, gc);
        Tdraw.setProperties(props_PrtTilemode0View); // Set properties
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
        paint(getJGC());
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
    Point getLocation()
    {
//d     System.out.println("++++++++++++YxxfPrtTilemode0View:getLocation");
        YxxfPrtContainer parent = getParent();

        if (parent == null)
            return super.getLocation();

//      Dimension parentsize = parent.getSize();
        
        return new Point(0, 0);
    }


    /**
     * Get the preferred size of the view.
     * @return The size.
     */
    public
    Dimension getMinimumSize()
    {
//d     System.out.println("++++++++++++YxxfPrtTilemode0View:getMinimumSize");
        return getPreferredSize();
    }


    /**
     * Get the preferred size of the view.
     * @return The size.
     */
    public
    Dimension getPreferredSize()
    {
//d     System.out.println("++++++++++++YxxfPrtTilemode0View:getPreferredSize");
        YxxfPrtContainer parent = getParent();

        if (parent == null)
            return super.getPreferredSize();

        Dimension parentsize = parent.getSize();

        return new Dimension(parentsize.width,
                             parentsize.height);
    }
    //==========================================================================


    //==========================================================================
    // Painting
    //==========================================================================
    /**
     * Draw the view.
     * @param jgc The Java Graphics object.
     */
    public
    void paint(Graphics jgc)
    {
//d     System.out.println("++++++++++++YxxfPrtTilemode0View:paint(" + jgc + ")");

        gc.commandGC_set_doing_redraw();
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
        gc.setDspwinXY(0, 0);
        gc.setDspwin(parent.getSize());

        // Sync JGC
        this.validateLayout();


        // Set background color
        String str_bgcolor = props_PrtTilemode0View.getProperty("bgcolor");
        if (str_bgcolor == null)
            gc.setBGColor("#000000");
        else
            gc.setBGColor(str_bgcolor);


        // Set foreground palette
        String str_fgcolor = props_PrtTilemode0View.getProperty("fgcolor");
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
        this.validateLayout();


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
    }
    //==========================================================================
}

