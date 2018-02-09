//==============================================================================
// YxxfPrtTilemode1DrawT.java
//
// Tilemode1 Draw Thread
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfPrtTilemode1DrawT.java,v 1.10 2003/06/04 11:07:18 ekarlo Exp $
// $Log: YxxfPrtTilemode1DrawT.java,v $
// Revision 1.10  2003/06/04 11:07:18  ekarlo
// Don't do unnecessary redraws when zooming to extents or when rendering is turned off.
//
// Revision 1.9  2003/05/08 11:24:03  ekarlo
// Remove warnings.
//
// Revision 1.8  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.7  2002/10/12 07:09:34  ekarlo
// Fix zoom to calc extents command.
//
// Revision 1.6  2002-09-26 01:51:20-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.5  2002-09-12 23:39:24-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.4  2001-05-21 02:33:57-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.3  2001-05-20 02:43:01-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.2  2001-05-17 23:33:04-06  ekarlo
// Development check-in.
//
// Revision 1.1  2001-05-17 02:52:27-06  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


import com.ysystems.lib.yutil.*;


/**
 * Tilemode1 Draw Thread.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfPrtTilemode1DrawT extends Thread
{
    //==========================================================================
    /**
     * Properties
     */
    private
    YutilProperties             props_PrtTilemode1DrawT
                                                = new YutilProperties();
    //==========================================================================


    //==========================================================================
    // Drawing items

    /**
     * Tilemode 1 view being drawn into.
     */
    private
    YxxfPrtTilemode1View        tilemode1view   = null;

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
    YxxfPrtTilemode1DrawT()
    {
    }

    /**
     * Constructor
     * @param tilemode1view The Tilemode1 View.
     * @param gc The Graphics Context.
     */
    public
    YxxfPrtTilemode1DrawT(YxxfPrtTilemode1View tilemode1view, YxxfGfxContext gc)
    {
        setTilemode1View(tilemode1view);
        setGC(gc);
    }

    /**
     * Set the Tilemode1 View.
     * @param tilemode1view The Tilemode1 View.
     */
    public
    void setTilemode1View(YxxfPrtTilemode1View tilemode1view)
    {
        this.tilemode1view = tilemode1view;
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
     * @param scanout true if the arguments must be parsed.
     */
    public
    void setProperties(String args, boolean scanout)
    {
        props_PrtTilemode1DrawT.setProperties(args, scanout);
    }

    /**
     * Set properties from argv array.
     * @param args The properties.
     */
    public
    void setProperties(String argv[])
    {
        props_PrtTilemode1DrawT.setProperties(argv);
    }

    /**
     * Set properties from argprops YutilProperties.
     * @param The properties.
     */
    public
    void setProperties(YutilProperties argprops)
    {
        props_PrtTilemode1DrawT.setProperties(argprops);
    }
    //==========================================================================


    /**
     * Run the thread.
     */
    public
    void run()
    {
        // System.out.println("++++++++++++++YxxfPrtTilemode1DrawT:BEG run()");

        // Wait for full drawing setup
        gc.getDrawing().waitDrawingReady();

        while (true)
        {
            // System.out.println("++++++++++++++YxxfPrtTilemode1DrawT:doing=" + gc.gc_doing);

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
            do_redraw();
        }

        // System.out.println("++++++++++++++YxxfPrtTilemode1DrawT:END run()");
    }


    /**
     * Redraw the Tilemode1 view.
     */
    public
    void do_redraw()
    {
        // System.out.println("++++++++++++++YxxfPrtTilemode1DrawT:BEG:DOING_REDRAW and go");
        gc.commandGC_set_doing_drawing();

        // Setup graphics context
        ins = tilemode1view.setupGC_predraw();


        // save state of rendering flag
        boolean save_renderingjgcflag = gc.getRenderingJGCFlag();


        // setup for calc extents run
        if (gc.gc_doing_flag_calc_extents)
        {
            gc.gc_doing_flag_calc_extents_valid = false;
            gc.calc_extmin.x = Double.POSITIVE_INFINITY;
            gc.calc_extmin.y = Double.POSITIVE_INFINITY;
            gc.calc_extmin.z = Double.POSITIVE_INFINITY;
            gc.calc_extmax.x = Double.NEGATIVE_INFINITY;
            gc.calc_extmax.y = Double.NEGATIVE_INFINITY;
            gc.calc_extmax.z = Double.NEGATIVE_INFINITY;

            // turn off rendering
            gc.setRenderingJGCFlag(false);
        }


        // draw background
        gc.drawBackground();


        // draw display extent lines
        if (props_PrtTilemode1DrawT.getProperty_boolean("drawdspminmax"))
            gc.drawDspminmax_vport();


        // draw it
        ins.draw(gc);
        // System.out.println("++++++++++++++YxxfPrtTilemode1DrawT:DRAW done,doing=" + gc.doing);


        // restore state of rendering flag
        gc.setRenderingJGCFlag(save_renderingjgcflag);


        // Check if draw ended normally
        if (gc.gc_doing == YxxfGfxContext.GC_DOING_DRAWING)
        {   // still set to drawing - ended ok
            if (gc.gc_doing_flag_calc_extents)
            {   // calc extents run
                gc.gc_doing_flag_calc_extents = false;
                gc.gc_doing_flag_calc_extents_valid = true;

                // System.out.println("++++++++++++++YxxfPrtTilemode1DrawT:gc_doing_flag_calc_extents=" + gc.gc_doing_flag_calc_extents);
                // System.out.println("++++++++++++++YxxfPrtTilemode1DrawT:gc_doing_flag_calc_extents_valid=" + gc.gc_doing_flag_calc_extents_valid);
                // System.out.println("++++++++++++++YxxfPrtTilemode1DrawT:gc_doing_flag_zoom_to_calc_extents=" + gc.gc_doing_flag_zoom_to_calc_extents);
                // System.out.println("++++++++++++++YxxfPrtTilemode1DrawT:extmin=" + gc.calc_extmin);
                // System.out.println("++++++++++++++YxxfPrtTilemode1DrawT:extmax=" + gc.calc_extmax);

                if (gc.gc_doing_flag_zoom_to_calc_extents)
                {   // zoom to calc extents set - do redraw with new values
                    gc.gc_doing_flag_zoom_to_calc_extents = false;
                    tilemode1view.setVportToCalcExtents(); // set to calc extents values

                    if (gc.getRenderingJGCFlag())
                        gc.commandGC_set_doing_redraw(); // do redraw
                    else
                        gc.commandGC_set_doing_waiting(); // else we are done
                }
                else
                {
                    gc.commandGC_set_doing_waiting(); // we are done
                }
            }
            else

            if (gc.gc_doing_flag_zoom_to_calc_extents)
            {   // redraw from calc extents - reset
                gc.gc_doing_flag_zoom_to_calc_extents = false;
                gc.commandGC_set_doing_waiting(); // we are done
            }
            else

            {
                gc.commandGC_set_doing_waiting(); // we are really done
            }
        } // else do again if didn't end still drawing


        // Shutdown graphics context
        tilemode1view.setupGC_postdraw();
        // System.out.println("++++++++++++++YxxfPrtTilemode1DrawT:END:DOING_REDRAW and go");
    }
}

