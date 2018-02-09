//==============================================================================
// YxxfPrtViewMonitor.java
//
// Observable monitor Object for sending Events to the View Handler
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfPrtViewMonitor.java,v 1.2 2003/04/14 12:38:42 ekarlo Exp $
// $Log: YxxfPrtViewMonitor.java,v $
// Revision 1.2  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.1  2001/05/17 08:52:23  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


import java.util.*;


/**
 * Observable monitor object for sending Events to the View Handler.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfPrtViewMonitor extends Observable
{
    //==========================================================================
    /**
     * Constructor (empty)
     */
    public
    YxxfPrtViewMonitor()
    {
    }
    //==========================================================================


    /**
     * Notify observers of View Handler.
     * @param vhevt The View Handler event.
     */
    public
    void notifyViewHandler(YxxfDrwViewHandlerEvent vhevt)
    {
        setChanged();
        notifyObservers(vhevt);
    }
}

