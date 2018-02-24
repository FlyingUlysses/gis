//==============================================================================
// YxxfDrwTilemode1DrawT.java
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfDrwTilemode1DrawT.java,v 1.36 2003/06/04 11:07:16 ekarlo Exp $
// $Log: YxxfDrwTilemode1DrawT.java,v $
// Revision 1.36  2003/06/04 11:07:16  ekarlo
// Don't do unnecessary redraws when zooming to extents or when rendering is turned off.
//
// Revision 1.35  2003/05/08 11:24:02  ekarlo
// Remove warnings.
//
// Revision 1.34  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.33  2002/10/12 07:09:34  ekarlo
// Fix zoom to calc extents command.
//
// Revision 1.32  2002-09-26 01:51:22-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.31  2002-09-12 23:39:25-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.30  2001-05-21 02:34:02-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.29  2001-05-20 02:43:02-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.28  2001-05-11 22:58:41-06  ekarlo
// Make var name consistent.
//
// Revision 1.27  2000-10-17 01:44:05-06  ekarlo
// Change package paths to lower case.
//
// Revision 1.26  1999-10-22 01:28:04-06  ekarlo
// API rework - phase 1.
//
// Revision 1.25  1999-10-06 19:53:08-06  walter
// Added JavaDoc comments.
//
// Revision 1.24  1999-07-09 14:10:50-06  ekarlo
// Rearrange package names.
//
// Revision 1.23  1999/07/09  15:21:07  ekarlo
// Improve param properties scan.
//
// Revision 1.22  1999/07/06  02:45:37  ekarlo
// Add calc and zoom extents.
//
// Revision 1.21  1999/06/20  22:34:10  ekarlo
// Rearrange package names.
//
// Revision 1.20  1999/06/15  04:57:44  ekarlo
// User Interface - phase 1.
//
// Revision 1.19  1999/02/09  14:52:05  ekarlo
// Deactivate console print.
//
// Revision 1.18  1999/02/08  05:09:51  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.17  1999/01/28  04:25:27  ekarlo
// Text - phase 4.
//
// Revision 1.16  1998/02/02  19:10:19  ekarlo
// Implement paper space view - phase 2.
//
// Revision 1.15  1997/12/26  21:34:54  ekarlo
// Implement paper space - phase 1.
// Rename properties object.
//
// Revision 1.14  1997/08/30  14:10:11  ekarlo
// Redo redraw action.
//
// Revision 1.13  1997/07/23  14:24:21  ekarlo
// Move get thread out of main thread.
// Remove progress indicators.
//
// Revision 1.12  1997/07/21  22:35:08  ekarlo
// MVC-VH rework - phase 2.
//
// Revision 1.11  1997/07/18  12:43:36  ekarlo
// Rename from YxxfDrwVportViewT.java to YxxfDrwVportDrawT.java
//
// Revision 1.10  1997/07/18  11:39:16  ekarlo
// Redraw code changes.
//
// Revision 1.9  1997/07/15  14:47:15  ekarlo
// Rename from YxxfDrwVPortViewT.java to YxxfDrwVportViewT.java
//
// Revision 1.8  1997/07/13  18:01:16  ekarlo
// MVC-VH rework - phase 1.
//
// Revision 1.7  1997/06/20  19:57:16  ekarlo
// Rename from YdxfTDraw to YdxfDrwVPortViewT.java
//
// Revision 1.6  1997/06/11  15:36:21  ekarlo
// Make Ydxf object passive rather than thread.
// Add print stats code (from YdxfTGet.java).
//
// Revision 1.5  1996/10/26  00:09:46  ekarlo
// Correct color and layer handling.
//
// Revision 1.4  1996/09/26  01:44:57  ekarlo
// Initialize current insert values.
//
// Revision 1.3  1996/08/13  01:50:33  ekarlo
// Change drawing of debug indicators and extent lines.
//
// Revision 1.2  1996/07/30  06:33:18  ekarlo
// Call function to draw status indicators.
//
// Revision 1.1  1996/07/02  01:58:28  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


import com.ysystems.lib.yutil.*;


/**
 * Tilemode1 Draw Thread.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfDrwTilemode1DrawT extends Thread
{
    //==========================================================================
    /**
     * Properties
     */
    private
    YutilProperties             props_DrwTilemode1DrawT
                                                = new YutilProperties();
    //==========================================================================


    //==========================================================================
    // Drawing items

    /**
     * Tilemode 1 view being drawn into.
     */
    private
    YxxfDrwTilemode1View        tilemode1view   = null;

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
    YxxfDrwTilemode1DrawT()
    {
    }

    /**
     * Constructor
     * @param tilemode1view The Tilemode1 View.
     * @param gc The Graphics Context.
     */
    public
    YxxfDrwTilemode1DrawT(YxxfDrwTilemode1View tilemode1view, YxxfGfxContext gc)
    {
        setTilemode1View(tilemode1view);
        setGC(gc);
    }

    /**
     * Set the Tilemode1 View.
     * @param tilemode1view The Tilemode1 View.
     */
    public
    void setTilemode1View(YxxfDrwTilemode1View tilemode1view)
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
        props_DrwTilemode1DrawT.setProperties(args, scanout);
    }

    /**
     * Set properties from argv array.
     * @param args The properties.
     */
    public
    void setProperties(String argv[])
    {
        props_DrwTilemode1DrawT.setProperties(argv);
    }

    /**
     * Set properties from argprops YutilProperties.
     * @param The properties.
     */
    public
    void setProperties(YutilProperties argprops)
    {
        props_DrwTilemode1DrawT.setProperties(argprops);
    }
    //==========================================================================


    /**
     * Run the thread.
     */
    public
    void run()
    {
//d     System.out.println("++++++++++++++YxxfDrwTilemode1DrawT:BEG run()");

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


            // If not draw then continue to wait
            if (gc.gc_doing != YxxfGfxContext.GC_DOING_REDRAW &&
                gc.gc_doing != YxxfGfxContext.GC_DOING_RBL &&
                gc.gc_doing != YxxfGfxContext.GC_DOING_RBB)
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


            // Rubber Band Line
            if (gc.gc_doing == YxxfGfxContext.GC_DOING_RBL)
            {
                do_rbl();
                continue;
            }


            // Rubber Band Box
            if (gc.gc_doing == YxxfGfxContext.GC_DOING_RBB)
            {
                do_rbb();
                continue;
            }
        }

//d     System.out.println("++++++++++++++YxxfDrwTilemode1DrawT:END run()");
    }


    /**
     * Redraw the Tilemode1 view.
     */
    public
    void do_redraw()
    {
//d     System.out.println("++++++++++++++YxxfDrwTilemode1DrawT:DOING_REDRAW and go");
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
        if (props_DrwTilemode1DrawT.getProperty_boolean("drawdspminmax"))
            gc.drawDspminmax_vport();


        // draw it
        ins.draw(gc);
        // System.out.println("++++++++++++++YxxfDrwTilemode1DrawT:DRAW done,doing=" + gc.doing);


        // restore state of rendering flag
        gc.setRenderingJGCFlag(save_renderingjgcflag);


        // Check if draw ended normally
        if (gc.gc_doing == YxxfGfxContext.GC_DOING_DRAWING)
        {   // still set to drawing - ended ok
            if (gc.gc_doing_flag_calc_extents)
            {   // calc extents run
                gc.gc_doing_flag_calc_extents = false;
                gc.gc_doing_flag_calc_extents_valid = true;

                // System.out.println("++++++++++++++YxxfDrwTilemode1DrawT:gc_doing_flag_calc_extents=" + gc.gc_doing_flag_calc_extents);
                // System.out.println("++++++++++++++YxxfDrwTilemode1DrawT:gc_doing_flag_calc_extents_valid=" + gc.gc_doing_flag_calc_extents_valid);
                // System.out.println("++++++++++++++YxxfDrwTilemode1DrawT:gc_doing_flag_zoom_to_calc_extents=" + gc.gc_doing_flag_zoom_to_calc_extents);
                // System.out.println("++++++++++++++YxxfDrwTilemode1DrawT:extmin=" + gc.calc_extmin);
                // System.out.println("++++++++++++++YxxfDrwTilemode1DrawT:extmax=" + gc.calc_extmax);

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
        // System.out.println("++++++++++++++YxxfDrwTilemode1DrawT:END:DOING_REDRAW and go");
    }


    /**
     * Draw the rubber band line.
     */
    public
    void do_rbl()
    {
        while (true)
        {
            // Rubber band line - begin
            if (gc.gc_rbl_doing == YxxfGfxContext.GC_RBL_DOING_BEG)
            {   // rbl_beg
                gc.drawRBL_beg();
            }
            else


            // Rubber band line - move
            if (gc.gc_rbl_doing == YxxfGfxContext.GC_RBL_DOING_DRAG)
            {   // rbl_drag
                gc.drawRBL_drag();
            }
            else


            // Rubber band line - end
            if (gc.gc_rbl_doing == YxxfGfxContext.GC_RBL_DOING_END)
            {   // rbl_end
                gc.drawRBL_end();
                return;
            }

            gc.commandGC_wait();
        }
    }


    /**
     * Draw the rubber band box.
     */
    public
    void do_rbb()
    {
        while (true)
        {
            // Rubber band box - begin
            if (gc.gc_rbb_doing == YxxfGfxContext.GC_RBB_DOING_BEG)
            {   // rbb_beg
                gc.drawRBB_beg();
            }
            else


            // Rubber band box - move
            if (gc.gc_rbb_doing == YxxfGfxContext.GC_RBB_DOING_DRAG)
            {   // rbb_drag
                gc.drawRBB_drag();
            }
            else


            // Rubber band box - end
            if (gc.gc_rbb_doing == YxxfGfxContext.GC_RBB_DOING_END)
            {   // rbb_end
                gc.drawRBB_end();
                return;
            }

            gc.commandGC_wait();
        }
    }
}

