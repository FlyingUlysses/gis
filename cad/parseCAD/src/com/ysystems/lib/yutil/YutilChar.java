//==============================================================================
// YutilChar.java
//
// Simple mutable char wrapper
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/lib/yutil/YutilChar.java,v 1.6 2003/04/14 12:37:03 ekarlo Exp $
// $Log: YutilChar.java,v $
// Revision 1.6  2003/04/14 12:37:03  ekarlo
// Update source file header for OSI release.
//
// Revision 1.5  2000/10/17 07:44:10  ekarlo
// Change package paths to lower case.
//
// Revision 1.4  1999-10-21 21:38:13-06  walter
// Added JavaDoc comments.
//
// Revision 1.3  1999-06-20 16:33:43-06  ekarlo
// Rearrange package names.
//
// Revision 1.2  1999/02/08  04:49:25  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.1  1999/01/28  04:23:22  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.lib.yutil;


/**
 * Simple mutable char wrapper.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YutilChar
{
    //==========================================================================
    /**
     * The char value.
     */
    private char value;
    //==========================================================================


    //==========================================================================
    /**
     * Constructor (empty)
     */
    public
    YutilChar()
    {
    }

    /**
     * Constructor
     * @param value The value.
     */
    public
    YutilChar(char value)
    {
        this.value = value;
    }
    //==========================================================================


    /**
     * Set the value.
     * @param value The value.
     * @return The value.
     */
    public
    char setValue(char value)
    {
        return this.value = value;
    }


    /**
     * Get the value.
     * @return The value.
     */
    public
    char getValue()
    {
        return value;
    }


    /**
     * Get the hashcode for value.
     * @return The hashcode
     */
    public
    int hashCode()
    {
        return (int)value;
    }


    /**
     * Is this value equal to obj
     * @param obj The value to compare to.
     * @return true if they are the equal
     */
    public
    boolean equals(Object obj)
    {
        if ((obj != null) && (obj instanceof YutilChar))
        {
            return value == ((YutilChar)obj).getValue();
        }
        return false;
    }


    /**
     * Stringify value.
     * @return This value as a String.
     */
    public
    String toString()
    {
        char buf[] = { value };
        return String.valueOf(buf);
    }
}

