//==============================================================================
// YutilShapeChar.java
//
// Single char description in shape
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfShapeChar.java,v 1.7 2003/04/14 12:38:42 ekarlo Exp $
// $Log: YxxfShapeChar.java,v $
// Revision 1.7  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.6  2000/10/17 07:43:40  ekarlo
// Change package paths to lower case.
//
// Revision 1.5  1999-09-08 13:17:53-06  walter
// Add JavaDoc comments.
//
// Revision 1.4  1999-07-09 14:10:50-06  ekarlo
// Rearrange package names.
//
// Revision 1.3  1999/06/20  22:34:10  ekarlo
// Rearrange package names.
//
// Revision 1.2  1999/02/08  05:11:30  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.1  1999/01/28  04:30:49  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


/**
 * Single char description in shape.
 * May be used in Hashtables as both key and val.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfShapeChar
{
    //==========================================================================
    /**
     * The value of a character in shape. TODO
     */
    private
    char                        value;

    /**
     * The description of the character. TODO
     */
    private
    String                      desc;

    /**
     * The shape attribute.
     */
    private
    char[]                      geom;
    //==========================================================================


    //==========================================================================
    // Constructors

    /**
     * Constructor (empty)
     */
    public
    YxxfShapeChar()
    {
    }

    /**
     * Constructor
     * @param value The shape char value.
     */
    public
    YxxfShapeChar(char value)
    {
        this.value = value;
    }

    /**
     * Constructor 
     * @param value The shape char value.
     * @param desc The description of the shape char.
     * @param geom The shape attribute.
     */
    public
    YxxfShapeChar(char value, String desc, char[] geom)
    {
        this.value = value;
        this.desc = desc;
        this.geom = geom;
    }
    //==========================================================================


    /**
     * Set the shape char value.
     * @param value A shape char value.
     * @return A shape char value.
     */
    public
    char setValue(char value)
    {
        return this.value = value;
    }
    /**
     * Get the shape char value.
     * @return A shape char value.
     */
    public
    char getValue()
    {
        return value;
    }


    /**
     * Set the description of the shape char.
     * @param desc The description of the shape char.
     * @return The description of the shape char.
     */
    public
    String setDesc(String desc)
    {
        return this.desc = desc;
    }
    /**
     * Get the description of the shape char.
     * @return The description of the shape char.
     */
    public
    String getDesc()
    {
        return desc;
    }


    /**
     * Set the attribute of the shape char.
     * @param geom The attribute of the shape char.
     * @return The attribute of the shape char.
     */
    public
    char[] setGeom(char[] geom)
    {
        return this.geom = geom;
    }
    /**
     * Get the attribute of the shape char.
     * @return The attribute of the shape char.
     */
    public
    char[] getGeom()
    {
        return geom;
    }


    /**
     * Generate a hashcode for this shape char.
     * @return The hashcode.
     */
    public
    int hashCode()
    {
        return (int)value;
    }


    /**
     * Compare this shape char to an Object of type YxxfShapeChar.
     * @return true if the values are the same.
     */
    public
    boolean equals(Object obj)
    {
        if ((obj != null) && (obj instanceof YxxfShapeChar))
        {
            return value == ((YxxfShapeChar)obj).getValue();
        }
        return false;
    }


    /**
     * Stringify this shape char.
     * @return The value.
     */
    public
    String toString()
    {
        char buf[] = { value };
        return String.valueOf(buf);
    }
}

