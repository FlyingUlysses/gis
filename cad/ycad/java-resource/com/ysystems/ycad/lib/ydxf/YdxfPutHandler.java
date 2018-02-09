//==============================================================================
// YdxfPutHandler.java
//
// Put handler
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/ydxf/YdxfPutHandler.java,v 1.10 2003/05/08 09:38:16 ekarlo Exp $
// $Log: YdxfPutHandler.java,v $
// Revision 1.10  2003/05/08 09:38:16  ekarlo
// Remove warnings.
//
// Revision 1.9  2003/04/14 12:37:55  ekarlo
// Update source file header for OSI release.
//
// Revision 1.8  2000/10/17 07:44:14  ekarlo
// Change package paths to lower case.
//
// Revision 1.7  1999-10-06 19:51:47-06  walter
// Added JavaDoc comments.
//
// Revision 1.6  1999-07-09 14:09:43-06  ekarlo
// Rearrange package names.
//
// Revision 1.5  1999/06/27  08:28:14  ekarlo
// Rename class.
//
// Revision 1.4  1999/06/20  22:32:38  ekarlo
// Rearrange package names.
//
// Revision 1.3  1999/06/15  04:54:05  ekarlo
// Rename output thread class.
//
// Revision 1.2  1999/02/09  14:47:49  ekarlo
// Deactivate console print.
//
// Revision 1.1  1999/01/28  04:19:23  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.ydxf;


import com.ysystems.lib.yutil.*;
import com.ysystems.ycad.lib.yxxf.*;


/**
 * Put handler.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YdxfPutHandler
{
    //==========================================================================
    // Output
    
    /**
     * Utility for opening input and output streams.
     */
    private YutilIOHandler      iohandler           = null;

    /**
     * Main put thread.
     */
    private
    YdxfPutT                     main_putT           = null;
    //==========================================================================


    //==========================================================================
    // Drawing reference

    /**
     * Main drawing.
     */
    private
    Yxxf                        main_D              = null;
    //==========================================================================


    //==========================================================================
    // Constructors

    /**
     * Constructor (empty).
     */
    public
    YdxfPutHandler()
    {
//d     System.out.println("+++YdxfPutHandler:CONSTRUCTOR 1");
    }
    //==========================================================================


    //==========================================================================

    /**
     * Start put thread for main drawing.
     * @param arg_iohandler The utility that handles opening iostreams.
     * @param arg_main_D The Drawing.
     */
    public
    void commandPutMainStart(YutilIOHandler arg_iohandler, Yxxf arg_main_D)
    {
//d     System.out.println("+++YdxfPutHandler:commandPutMainStart BEG");

        // Store parameters
        iohandler       = arg_iohandler;
        main_D          = arg_main_D;


        // Open main drawing InputStream
        iohandler.openOutputStream(main_D.ioname);

        if (main_D.ioname.os == null)
        {
            System.out.println("+++YdxfPutHandler:commandPutMainStart|ERROR - cannot open main input");
            return;
        }


        // Start main drawing output
        if (main_putT == null)
        {
//d         System.out.println("+++YdxfPutHandler:commandPutMainStart|starting put thread");

            // Start main put thread
            main_putT = new YdxfPutT(YdxfPutBuffer.PUT_TYPE_FONT, main_D.ioname.os, main_D);
            main_putT.start();
        }

//d     System.out.println("+++YdxfPutHandler:commandPutMainStart END");
    }
    //==========================================================================
}

