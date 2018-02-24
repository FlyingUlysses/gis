//==============================================================================
// YxxfPrtView.java
//
// Main View for Printer
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfPrtView.java,v 1.9 2003/05/08 11:24:02 ekarlo Exp $
// $Log: YxxfPrtView.java,v $
// Revision 1.9  2003/05/08 11:24:02  ekarlo
// Remove warnings.
//
// Revision 1.8  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.7  2002/10/30 02:22:59  ekarlo
// Rearrange initialization of option defaults.
//
// Revision 1.6  2002-09-28 23:20:55-06  ekarlo
// Change prefix of entity print options to "list".
//
// Revision 1.5  2002-09-26 01:51:24-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.4  2001-05-21 02:34:03-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.3  2001-05-20 02:42:58-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.2  2001-05-17 23:33:06-06  ekarlo
// Development check-in.
//
// Revision 1.1  2001-05-17 02:52:28-06  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;

import java.awt.*;

import com.ysystems.lib.yutil.*;


/**
 * Main View for Printer.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfPrtView extends YxxfPrtContainer
{
    //==========================================================================
    /**
     * Properties
     */
    private
    YutilProperties             props_PrtView       = new YutilProperties();
    //==========================================================================


    //==========================================================================
    /**
     * Constructor
     */
    public
    YxxfPrtView()
    {
        super();
    }

    /**
     * Constructor
     * @param D The Drawing.
     */
    public
    YxxfPrtView(Yxxf D, Graphics jgc)
    {
        super(D, jgc);
    }

    /**
     * Constructor
     * @param D The Drawing.
     */
    public
    YxxfPrtView(Yxxf D, Graphics jgc, int x, int y, int width, int height)
    {
        super(D, jgc, x, y, width, height);
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
        props_PrtView.setProperties(args, scanout);
    }

    /**
     * Set properties from argv array.
     * @param argv The Properties collection.
     */
    public
    void setProperties(String argv[])
    {
        props_PrtView.setProperties(argv);
    }

    /**
     * Set properties from argprops YutilProperties.
     * @param argprops The Properties collection.
     */
    public
    void setProperties(YutilProperties argprops)
    {
        props_PrtView.setProperties(argprops);
    }

    /**
     * Get properties.
     * @return The properties.
     */
    public
    YutilProperties getProperties()
    {
        return props_PrtView;
    }
    //==========================================================================

    


    /**
     * Intialize the Main Panel for the Viewer (no-op).
     */
    public
    void commandInit()
    {
//d     // Display local params
//d     System.out.println("++++YxxfPrtView:props_PrtView.e BEG");
//d     for (Enumeration e = props_PrtView.propertyNames(); e.hasMoreElements(); )
//d     {
//d         String key = (String)e.nextElement();
//d         String val = props_PrtView.getProperty(key);
//d         System.out.println("++++YxxfPrtView:key=[" + key + "],val=[" + val + "]");
//d     }
//d     System.out.println("++++YxxfPrtView:props_PrtView.e END");
    }


    /**
     * Start the Main Panel for the Viewer.
     */
    public
    void commandStart()
    {
    }


    //==========================================================================
    /**
     * Intercept Panel.paint.
     * @param jgc The Java Graphics object.
     */
    public
    void paint(Graphics jgc)
    {
        // System.out.println("++++YxxfPrtView:paint(" + jgc + ")");
    }
    //==========================================================================
}

