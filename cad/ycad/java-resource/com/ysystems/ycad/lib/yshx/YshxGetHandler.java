//==============================================================================
// YshxGetHandler.java
//
// Manages get of shape files
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yshx/YshxGetHandler.java,v 1.11 2003/05/08 09:38:17 ekarlo Exp $
// $Log: YshxGetHandler.java,v $
// Revision 1.11  2003/05/08 09:38:17  ekarlo
// Remove warnings.
//
// Revision 1.10  2003/04/14 12:38:24  ekarlo
// Update source file header for OSI release.
//
// Revision 1.9  2002/09/25 19:29:07  ekarlo
// Remove thread priority boost.
// Unknown effectiveness plus Mozilla/Linux had security exception.
//
// Revision 1.8  2000-10-17 01:44:11-06  ekarlo
// Change package paths to lower case.
//
// Revision 1.7  1999-10-21 21:35:35-06  walter
// Added JavaDoc comments.
//
// Revision 1.6  1999-07-09 14:09:02-06  ekarlo
// Rearrange package names.
//
// Revision 1.5  1999/07/08  02:59:51  ekarlo
// Fix shape load.
//
// Revision 1.4  1999/06/20  22:33:20  ekarlo
// Rearrange package names.
//
// Revision 1.3  1999/06/15  05:01:02  ekarlo
// Set get thread priority higher.
//
// Revision 1.2  1999/02/09  14:48:33  ekarlo
// Deactivate console print.
//
// Revision 1.1  1999/01/28  04:22:14  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yshx;


import com.ysystems.ycad.lib.yxxf.*;


/**
 * Manages get of shape files
 * @author Ed Karlo - Y Systems, LLC
 */
public class YshxGetHandler
{
    //==========================================================================
    /**
     * Shape reference
     */
    private
    YxxfShape                   shape               = null;
    //==========================================================================


    //==========================================================================
    // Input
    
    /**
     * Shape get thread
     */
    private
    YshxGetT                    shape_getT          = null;
    //==========================================================================


    //==========================================================================
    /**
     * Constructor (empty)
     */
    public
    YshxGetHandler()
    {
//d     System.out.println("+++YshxGetHandler:CONSTRUCTOR 1");
    }
    //==========================================================================


    //==========================================================================
    //
    /**
     * Start get thread for shape.
     * @param arg_shape The shape.
     */

    public
    void commandGetStart(YxxfShape arg_shape)
    {
//d     System.out.println("+++YshxGetHandler:commandGetStart BEG");

        // Store parameters
        shape = arg_shape;


        // Open shape InputStream
        shape.iohandler.openInputStream(shape.ioname);

        if (shape.ioname.is == null)
        {   // Create empty shape
            System.out.println("+++YshxGetHandler:commandGetStart|ERROR - cannot open shape input for [" + shape.ioname.name + "]");
            shape.setShapeEmpty();
            shape.setShapeReady(true);
            shape.setShapeLoaded(true);
            return;
        }


        // Start shape input
//d     System.out.println("+++YdxfGetHandler:commandGetStart|starting get thread");

        // Start shape get thread
        shape_getT = new YshxGetT(YxxfShape.TYPE_FONT, shape.ioname.is, shape);
        shape_getT.start();


//d     System.out.println("+++YshxGetHandler:commandGetStart END");
    }
    //==========================================================================
}

