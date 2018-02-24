//==============================================================================
// YxxfDrwTilemode0DrawT.java
//
// Tilemode0 Draw Thread
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfDrwTilemode0DrawT.java,v 1.20 2003/04/14 12:38:41 ekarlo Exp $
// $Log: YxxfDrwTilemode0DrawT.java,v $
// Revision 1.20  2003/04/14 12:38:41  ekarlo
// Update source file header for OSI release.
//
// Revision 1.19  2002/10/22 01:07:33  ekarlo
// Move code to predraw methods.
//
// Revision 1.18  2002-09-26 01:51:22-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.17  2002-09-12 23:39:24-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.16  2001-05-21 02:34:06-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.15  2001-05-20 02:43:02-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.14  2001-05-11 22:58:42-06  ekarlo
// Make var name consistent.
//
// Revision 1.13  2000-10-17 01:44:06-06  ekarlo
// Change package paths to lower case.
//
// Revision 1.12  1999-10-22 01:28:04-06  ekarlo
// API rework - phase 1.
//
// Revision 1.11  1999-10-06 19:52:22-06  walter
// Added JavaDoc comments.
//
// Revision 1.10  1999-07-09 14:10:50-06  ekarlo
// Rearrange package names.
//
// Revision 1.9  1999/07/09  15:18:26  ekarlo
// Improve param properties scan.
//
// Revision 1.8  1999/06/20  22:34:10  ekarlo
// Rearrange package names.
//
// Revision 1.7  1999/06/15  04:57:27  ekarlo
// User Interface - phase 1.
//
// Revision 1.6  1999/02/09  14:51:44  ekarlo
// Deactivate console print.
//
// Revision 1.5  1999/02/08  05:09:51  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.4  1999/01/28  04:25:11  ekarlo
// Text - phase 4.
//
// Revision 1.3  1998/08/19  01:17:45  ekarlo
// Rename classes to change "Panel" to "View" in name.
//
// Revision 1.2  1998/02/02  19:09:41  ekarlo
// Implement paper space - phase 2.
//
// Revision 1.1  1997/12/26  21:34:39  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


import java.awt.*;
import java.util.*;

import com.ysystems.lib.yutil.*;

/**
 * Tilemode0 Draw Thread.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfDrwTilemode0DrawT extends Thread
{
    //==========================================================================
    /**
     * Properties
     */
    private
    YutilProperties             props_DrwTilemode0DrawT
                                                = new YutilProperties();
    //==========================================================================


    //==========================================================================
    // Drawing items

    /**
     * Tilemode 0 View being drawn into.
     */
    private
    YxxfDrwTilemode0View        tilemode0view   = null;

    /**
     * Paper space Viewport.
     */
    private
    YxxfEntViewport             viewportpspace  = null;

    /**
     * Graphics context.
     */
    private
    YxxfGfxContext              gc              = null;

    /**
     * Insert to draw.
     */
    private
    YxxfEntInsert               ins             = null;
    //==========================================================================


    //==========================================================================
    /**
     * Constructor (empty)
     */
    public
    YxxfDrwTilemode0DrawT()
    {
    }

    /**
     * Constructor.
     * @param tilemode0view The Tilemode0 View.
     * @param gc The Graphics Context.
     */
    public
    YxxfDrwTilemode0DrawT(YxxfDrwTilemode0View tilemode0view, YxxfGfxContext gc)
    {
        setTilemode0View(tilemode0view);
        setGC(gc);
    }

    /**
     * Set the Tilemode0 View.
     * @param tilemode0view The Tilemode0 View.
     */
    public
    void setTilemode0View(YxxfDrwTilemode0View tilemode0view)
    {
        this.tilemode0view = tilemode0view;
    }

    /**
     * Set the Graphics Context.
     * @param gc The Graphics Context.
     */
    public
    void setGC(YxxfGfxContext gc)
    {
        this.gc = gc;
    }
    //==========================================================================


    //==========================================================================
    // Set properties

    /**
     * Set properties from args string.
     * @param args The properties.
     * @param scanout true if the argument string must be parsed.
     */
    public
    void setProperties(String args, boolean scanout)
    {
        props_DrwTilemode0DrawT.setProperties(args, scanout);
    }

    /**
     * Set properties from argv array.
     * @param argv The properties.
     */
    public
    void setProperties(String argv[])
    {
        props_DrwTilemode0DrawT.setProperties(argv);
    }

    /**
     * Set properties from argprops YutilProperties.
     * @param argprops The properties.
     */
    public
    void setProperties(YutilProperties argprops)
    {
        props_DrwTilemode0DrawT.setProperties(argprops);
    }
    //==========================================================================


    /**
     * Run the thread.
     */
    public
    void run()
    {
//d     System.out.println("++++++++++++++YxxfDrwTilemode0DrawT:BEG run()");

        // Wait for full drawing setup
        gc.getDrawing().waitDrawingReady();
        
        while (true)
        {
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

//d     System.out.println("++++++++++++++YxxfDrwTilemode0DrawT:END run()");
    }


    /**
     * Redraw the Tilemode0 view.
     */
    public
    void do_redraw()
    {
        Enumeration E_viewport;
        
        // Go
//d     System.out.println("VV++++++++++++YxxfDrwTilemode0DrawT:DOING_REDRAW and go");
        gc.commandGC_set_doing_drawing();

        // Calculate display positions of all viewports
        calcViewportListLocationSize();
            
        // Setup graphics context
        tilemode0view.setupGC_predraw1();

        // draw background in entire tilemode 0 view
        gc.drawBackground();


        // View loop
//d     System.out.println("VV++++++++++++YxxfDrwTilemode0DrawT:BEG DRAW VIEW LOOP");
//d     System.out.println("VV++++++++++++YxxfDrwTilemode0DrawT:tilemode0view.bounds()=" + tilemode0view.bounds());

        E_viewport = gc.getDrawing().secEntities.entViewportList.elements();
        while (E_viewport.hasMoreElements())
        {
            YxxfEntViewport viewport = (YxxfEntViewport)E_viewport.nextElement();
//d         System.out.println("VV++++++++++++YxxfDrwTilemode0DrawT:viewport.vpid=" + viewport.vpid + ",viewport.vpstatus=" + viewport.vpstatus);

            // Draw only if active
            if (viewport.vpstatus <= 0)
            {
                continue;
            }

            ins = tilemode0view.setupGC_predraw2(viewport);

            // draw display extent lines
            if (props_DrwTilemode0DrawT.getProperty_boolean("drawdspminmax"))
                gc.drawDspminmax_viewport();

            // draw it
//d         System.out.println("VV++++++++++++YxxfDrwTilemode0DrawT:DRAW strt,doing=" + gc.doing);
            ins.draw(gc);
//d         System.out.println("VV++++++++++++YxxfDrwTilemode0DrawT:DRAW done,doing=" + gc.doing);

            // Check if draw was broken by a redraw command
            if (gc.gc_doing != YxxfGfxContext.GC_DOING_DRAWING)
                break; // out of view loop
        }
//d     System.out.println("VV++++++++++++YxxfDrwTilemode0DrawT:END DRAW VIEW LOOP");

        tilemode0view.setupGC_postdraw();

        // Check if draw ended normally
        if (gc.gc_doing == YxxfGfxContext.GC_DOING_DRAWING)
            gc.commandGC_set_doing_waiting();

//d     System.out.println("VV++++++++++++YxxfDrwTilemode0DrawT:END draw()");
    }


    /**
     * Calculate location and size of all displayed Viewports.
     * The tilemode 0 view is a single draw space.
     * Each individual Viewport is calculated within that draw space.
     */
    public
    void calcViewportListLocationSize()
    {
        Enumeration E;

        
        //
        // Scan entire viewport list and find the paper space viewport
        //
        E = gc.getDrawing().secEntities.entViewportList.elements();
        while (E.hasMoreElements())
        {
            YxxfEntViewport viewport = (YxxfEntViewport)E.nextElement();
            if (viewport.vpid == 1)
            {   // PSpace viewport
                viewportpspace = viewport; // save ref
                break;
            }
        }
        // TODO: check for paper space viewport not found

        
        // The tilemode 0 view is a single draw space.
        // Each individual viewport is calculated within that draw space.

        Dimension tilemode0viewsize = tilemode0view.size();
        
        
        //
        // PSpace viewport
        // Ref to PSpace viewport was set at constructor time
        //
        
        // Determine view size relative to aspect of screen window

        // First try scaling the drawing x dimension to the full size of the
        // window x dimension.
        viewportpspace.vpDspScale = (double)(tilemode0viewsize.width - 1) / viewportpspace.vpwidth;

        // Now check if the drawing y dimension will fit in the window y dimension.
        if ((int)Math.ceil(viewportpspace.vpheight * viewportpspace.vpDspScale) > (tilemode0viewsize.height - 1))
        {   // (B)
            // If it does not fit (too big) then scale the drawing y dimension to the
            // full size of the window y dimension and use that.
            viewportpspace.vpDspScale = (double)(tilemode0viewsize.height - 1) / viewportpspace.vpheight;

            viewportpspace.vpDspLocation.x  = 0;
            viewportpspace.vpDspLocation.y  = 0;

            viewportpspace.vpDspSize.width  = (int)Math.ceil(viewportpspace.vpwidth * viewportpspace.vpDspScale);                
            viewportpspace.vpDspSize.height = tilemode0viewsize.height;
        } else
        {   // (A)
            viewportpspace.vpDspLocation.x  = 0;
            viewportpspace.vpDspLocation.y  = tilemode0viewsize.height - (int)Math.ceil(viewportpspace.vpheight * viewportpspace.vpDspScale);
            
            viewportpspace.vpDspSize.width  = tilemode0viewsize.width;
            viewportpspace.vpDspSize.height = (int)Math.ceil(viewportpspace.vpheight * viewportpspace.vpDspScale);
        }
    
    
    
    
        //
        // MSpace viewports.
        // Scan for all active Model Space viewports
        // and calculate their locations.
        // MSpace viewports are relative to PSpace viewport
        //
        
        YxxfEntViewport viewportmspace;
        
        E = gc.getDrawing().secEntities.entViewportList.elements();
        while (E.hasMoreElements())
        {
            viewportmspace = (YxxfEntViewport)E.nextElement();

            // Use only if active
            if (viewportmspace.vpstatus <= 0)
            {
                continue;
            }

            if (viewportmspace.vpid != 1) // An MSpace viewport
            {
                viewportmspace.vpDspLocation.x = viewportpspace.vpDspLocation.x +
                    (int)Math.floor(
                                     (
                                       (viewportmspace.vpcp.x - (viewportmspace.vpwidth / 2.0)) -
                                       (viewportpspace.vpcp.x - (viewportpspace.vpwidth / 2.0))
                                     ) * viewportpspace.vpDspScale
                                   );
                                    
                viewportmspace.vpDspLocation.y = viewportpspace.vpDspLocation.y +
                    (int)Math.floor( 
                                     ( 
                                       -(viewportmspace.vpcp.y + (viewportmspace.vpheight / 2.0)) -
                                       -(viewportpspace.vpcp.y + (viewportpspace.vpheight / 2.0)) 
                                     ) * viewportpspace.vpDspScale 
                                   );

                viewportmspace.vpDspSize.width  = (int)Math.ceil(viewportmspace.vpwidth  * viewportpspace.vpDspScale);
                viewportmspace.vpDspSize.height = (int)Math.ceil(viewportmspace.vpheight * viewportpspace.vpDspScale);
            }
        }        
    }
}

