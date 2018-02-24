//==============================================================================
// YdxfGetHandler.java
//
// Handle get of main drawing and associated fonts
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/ydxf/YdxfGetHandler.java,v 1.16 2003/05/08 09:38:16 ekarlo Exp $
// $Log: YdxfGetHandler.java,v $
// Revision 1.16  2003/05/08 09:38:16  ekarlo
// Remove warnings.
//
// Revision 1.15  2003/04/14 12:37:55  ekarlo
// Update source file header for OSI release.
//
// Revision 1.14  2002/09/25 19:05:56  ekarlo
// Remove thread priority boost.
// Unknown effectiveness plus Mozilla/Linux had security exception.
//
// Revision 1.13  2000-10-17 01:44:22-06  ekarlo
// Change package paths to lower case.
//
// Revision 1.12  1999-10-22 01:27:50-06  ekarlo
// API rework - phase 1.
//
// Revision 1.11  1999-10-21 21:42:59-06  walter
// Added JavaDoc comments.
//
// Revision 1.10  1999-07-09 14:09:43-06  ekarlo
// Rearrange package names.
//
// Revision 1.9  1999/07/08  03:03:47  ekarlo
// Fix shape load.
//
// Revision 1.8  1999/06/27  08:28:14  ekarlo
// Rename class.
//
// Revision 1.7  1999/06/20  22:32:38  ekarlo
// Rearrange package names.
//
// Revision 1.6  1999/06/15  04:51:59  ekarlo
// Set get thread priority higher.
// IO class rearrangment.
// Rename input thread class.
//
// Revision 1.5  1999/02/09  14:47:49  ekarlo
// Deactivate console print.
//
// Revision 1.4  1999/02/08  04:56:30  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.3  1999/01/28  04:13:33  ekarlo
// Text - phase 4.
//
// Revision 1.2  1998/12/22  14:43:16  ekarlo
// Renames - add "Get" to name.
//
// Revision 1.1  1998/12/21  15:29:45  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.ydxf;


import java.util.*;

import com.ysystems.ycad.lib.yxxf.*;
import com.ysystems.ycad.lib.yshx.*;


/**
 * Handle get of main drawing and associated fonts.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YdxfGetHandler
{
    //==========================================================================
    // Drawing reference

    /**
     * Main drawing
     */
    private
    Yxxf                        main_D              = null;
    //==========================================================================


    //==========================================================================
    // Input
    
    /**
     * Main get thread
     */
    private
    YdxfGetT                    main_getT           = null;
    //==========================================================================


    //==========================================================================
    /**
     * Constructor (empty)
     */
    public
    YdxfGetHandler()
    {
//d     System.out.println("+++YdxfGetHandler:CONSTRUCTOR 1");
    }
    //==========================================================================


    //==========================================================================
    //

    /**
     * Start get thread for main drawing
     */
    public
    void commandGetStart(Yxxf arg_main_D)
    {
//d     System.out.println("+++YdxfGetHandler:commandGetStart BEG");

        // Store parameters
        main_D = arg_main_D;


        // Note: Due to an apparent bug in IE 4.0 (version 4.72.2106.9)
        // only one InputStream can be open at any time or the ClassLoader fails.
        // This is not a problem with IE 3.0 or NS 3 or NS 4.  It would be nice
        // to open the main drawing and handle any errors before loading the fonts
        // but this causes more than one InputStream to be open at a time.  Thus,
        // all the fonts are loaded then the main drawing is loaded.
        // Each InputStream is closed after load is finished.
        // The original design used callbacks from other parts of the get or
        // draw sequence.  Many days were spent trying to discover the problem
        // until the following one-thing-at-a-time method was developed.
        // Not the best but it seems to work pending further investigation.

        // Get all the style shape files before main drawing start
        getStyleShapes();
        

        // Open main drawing InputStream
        main_D.iohandler.openInputStream(main_D.ioname);

        if (main_D.ioname.is == null)
        {
            System.out.println("+++YdxfGetHandler:commandGetStart|ERROR - cannot open main input");
            // Create empty drawing
            main_D.setDrawingEmpty();
            main_D.setDrawingReady(true);
            main_D.setDrawingComplete(true);
            return;
        }


        // Start main drawing input
//d     System.out.println("+++YdxfGetHandler:commandGetStart|starting get thread");

        // Start main get thread
        main_getT = new YdxfGetT(YdxfGetBuffer.GET_TYPE_MAIN, main_D.ioname.is, main_D);
        main_getT.start();


//d     System.out.println("+++YdxfGetHandler:commandGetStart END");
    }
    //==========================================================================


    //==========================================================================
    /**
     * Get all fonts and shapes.
     *
     * Use previously set main_D.
     *
     * Run through all style table entries.
     * It is assumed that all entries have been set from user parameters for load
     * and main drawing has not started loading yet.
     */
    private
    void getStyleShapes()
    {
//d     System.out.println("+++YdxfGetHandler:getStyles BEG");

        // Scan style table for loadable shapes
        for (Enumeration e_ioname = main_D.secTables.tblStyle.elements(); e_ioname.hasMoreElements(); ) // eieio
        {
            YxxfTblStyle style = (YxxfTblStyle)e_ioname.nextElement();

//d         System.out.println("+++YdxfGetHandler:getStyleShapes|getting shape [" + style.getName() + "]");

            if (style.shape == null)
            {
                continue;
            }

            if (style.shape.ioname == null)
            {
                continue;
            }

            // otherwise, attempt to load it
            YshxGetHandler shapegethandler = new YshxGetHandler();
            style.shape.setShapeEmpty();
            style.shape.setShapeReady(true);
            shapegethandler.commandGetStart(style.shape);

            style.shape.waitShapeLoaded();
        }            


//d     System.out.println("+++YdxfGetHandler:getStyleShapes END");
    }
    //==========================================================================
}

