//==============================================================================
// YshxGetT.java
//
// Get thread for fonts shx file
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yshx/YshxGetT.java,v 1.10 2003/05/08 09:38:17 ekarlo Exp $
// $Log: YshxGetT.java,v $
// Revision 1.10  2003/05/08 09:38:17  ekarlo
// Remove warnings.
//
// Revision 1.9  2003/04/14 12:38:24  ekarlo
// Update source file header for OSI release.
//
// Revision 1.8  2000/10/17 07:44:11  ekarlo
// Change package paths to lower case.
//
// Revision 1.7  1999-10-21 21:35:02-06  walter
// Added JavaDoc comments.
//
// Revision 1.6  1999-07-09 14:09:02-06  ekarlo
// Rearrange package names.
//
// Revision 1.5  1999/06/20  22:33:20  ekarlo
// Rearrange package names.
//
// Revision 1.4  1999/06/15  05:01:02  ekarlo
// Remove unused code.
//
// Revision 1.3  1999/02/09  14:48:33  ekarlo
// Deactivate console print.
//
// Revision 1.2  1999/02/08  04:46:38  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.1  1999/01/28  04:22:14  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yshx;


import java.io.*;

import com.ysystems.ycad.lib.yxxf.*;


/**
 * Get thread for fonts shx file.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YshxGetT extends Thread
{
    /**
     * Input
     */
    private
    YshxGetBuffer               getbfr = new YshxGetBuffer();


    /**
     * Shape
     */
    private
    YxxfShape                   shape;


    /**
     * Constructor (empty)
     */
    public
    YshxGetT()
    {
    }


    /**
     * Constructor
     * @param get_type The type of get i.e. Main or Font.
     * @param is The input stream.
     * @param shape The shape.
     */
    public
    YshxGetT(int get_type, InputStream is, YxxfShape shape)
    {
        setInput(get_type, is, shape);
    }


    /**
     * Set shape from the input stream.
     * @param get_type The type of get i.e. Main or Font.
     * @param is The input stream.
     * @param shape The shape.
     */
    public
    void setInput(int get_type, InputStream is, YxxfShape shape)
    {
        getbfr.setInput(get_type, is);
        this.shape = shape;
    }


    /**
     * The thread run method.
     */
    public
    void run()
    {
//d     System.out.println("++YshxGetT:BEG run");

        shape.waitShapeReady(); // Wait for full shape setup

        YshxGet.get(getbfr, shape);

        shape.setShapeLoaded(true);

        System.gc(); // Start Garbage collection

//d     System.out.println("++YshxGetT:END run");
    }
}

