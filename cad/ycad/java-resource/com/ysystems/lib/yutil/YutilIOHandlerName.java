//==============================================================================
// YutilIOHandlerName.java

// Structure class - name for Input or Output Stream
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/lib/yutil/YutilIOHandlerName.java,v 1.7 2003/05/08 08:46:25 ekarlo Exp $
// $Log: YutilIOHandlerName.java,v $
// Revision 1.7  2003/05/08 08:46:25  ekarlo
// Remove warnings.
//
// Revision 1.6  2003/04/14 12:37:04  ekarlo
// Update source file header for OSI release.
//
// Revision 1.5  2000/10/17 07:44:09  ekarlo
// Change package paths to lower case.
//
// Revision 1.4  1999-09-08 13:14:30-06  walter
// Add JavaDoc comments.
//
// Revision 1.3  1999-06-20 16:33:43-06  ekarlo
// Rearrange package names.
//
// Revision 1.2  1999/01/28  04:23:42  ekarlo
// Text - phase 4.
//
// Revision 1.1  1998/12/21  15:39:13  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.lib.yutil;


import java.io.*;


/**
 * Structure class - name for Input or Output Stream.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YutilIOHandlerName
{
    //==========================================================================
    // Name Strings
    /**
     * Name
     */
    public String              name                     = null;

    /**
     * Base URL
     */
    public String              baseurl                  = null;

    /**
     * TODO
     */
    public int                 gettype                  = 0;

    /**
     * TODO
     */
    public String              src                      = null;

    /**
     * TODO
     */
    public String              srcfile                  = null;

    /**
     * TODO
     */
    public String              srcurl                   = null;

    /**
     * TODO
     */
    public int                 puttype                  = 0;

    /**
     * TODO
     */
    public String              dst                      = null;

    /**
     * TODO
     */
    public String              dstfile                  = null;

    /**
     * TODO
     */
    public String              dsturl                   = null;
    //==========================================================================


    //==========================================================================
    /**
     * Associated InputStream.
     */
    public InputStream         is                       = null;
    //==========================================================================


    //==========================================================================
    /**
     * Associated OutputStream.
     */
    public OutputStream        os                       = null;
    //==========================================================================


    //==========================================================================
    /**
     * Constructor (empty).
     */
    public
    YutilIOHandlerName()
    {
    }
    
    /**
     * Constructor.
     * @param argstr_name TODO
     */
    public
    YutilIOHandlerName(String argstr_name)
    {
        name        = argstr_name;
    }
    //==========================================================================
    /**
     * The String value of the YutilIOHandlerName.
     */
    public
    String toString()
    {
        return
              "YutilIOHandlerName=[" + name + "|" + baseurl + "|" + src + "|" + srcfile + "|" + srcurl + "|" + is + "|" + os + "]";
    }
}

