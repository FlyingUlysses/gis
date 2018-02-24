//==============================================================================
// YutilCharBuffer.java
//
// Mutable character buffer with hashcode methods
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/lib/yutil/YutilCharBuffer.java,v 1.6 2003/04/14 12:37:03 ekarlo Exp $
// $Log: YutilCharBuffer.java,v $
// Revision 1.6  2003/04/14 12:37:03  ekarlo
// Update source file header for OSI release.
//
// Revision 1.5  2001/10/10 03:37:28  ekarlo
// Update copyright.
//
// Revision 1.4  2000-10-17 01:44:10-06  ekarlo
// Change package paths to lower case.
//
// Revision 1.3  1999-10-21 21:37:44-06  walter
// Added JavaDoc comments.
//
// Revision 1.2  1999-06-20 16:33:43-06  ekarlo
// Rearrange package names.
//
// Revision 1.1  1999/02/08  04:48:35  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.lib.yutil;


/**
 * Mutable character buffer with hashcode methods.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YutilCharBuffer
{
    /**
     * The char buffer
     */
    protected
    char[]                      value;
    /**
     * The number of characters in the buffer.
     */
    protected
    int                         count;


    /**
     * Constructor
     */
    public
    YutilCharBuffer()
    {
        value = new char[0];
        count = 0;
    }


    /**
     * Constructor
     * @param cap The size of the buffer.
     */
    public
    YutilCharBuffer(int cap)
    {
        value = new char[cap];
        count = 0;
    }


    /**
     * Constructor
     * @param arg_value The value to put in the buffer.
     */
    public
    YutilCharBuffer(String arg_value)
    {
        count = arg_value.length();
        value = new char[count];
        for (int i = 0; i < count; i++)
            value[i] = arg_value.charAt(i);
    }


    /**
     * Constructor
     * @param arg_value The value to put in the buffer.
     */
    public
    YutilCharBuffer(char[] arg_value)
    {
        count = arg_value.length;
        value = new char[count];
        for (int i = 0; i < count; i++)
            value[i] = arg_value[i];
    }


    /**
     * Constructor
     * @param arg_value The value to put in the buffer.
     */
    public
    YutilCharBuffer(YutilCharBuffer arg_charbuffer)
    {
        count = arg_charbuffer.count;
        value = new char[count];
        for (int i = 0; i < count; i++)
            value[i] = arg_charbuffer.value[i];
    }


    /**
     * Resize the buffer.
     * @param len The new buffer size.
     */
    public
    void setLength(int len)
    {
        if (len < 0)
            return;

        // check capacity
        if (len > count)
        {
            char[] newvalue = new char[len];
            for (int i = 0; i < count; i++)
                newvalue[i] = value[i];
            for (int i = count; i < len; i++)
                newvalue[i] = '\0';
            value = newvalue;
        }
        count = len;
    }


    /**
     * Set the value of the buffer.
     * @param arg_value The value.
     */
    public
    void setValue(char[] arg_value)
    {
        int len = arg_value.length;

        // check capacity
        if (len > value.length)
            value = new char[len];

        for (int i = 0; i < len; i++)
            value[i] = arg_value[i];

        count = len;
    }


    /**
     * Set the value of the buffer.
     * @param arg_value The value.
     */
    public
    void setValue(String arg_value)
    {
        int len = arg_value.length();

        // check capacity
        if (len > value.length)
            value = new char[len];

        for (int i = 0; i < len; i++)
            value[i] = arg_value.charAt(i);

        count = len;
    }


    /**
     * Set the value of the buffer.
     * @param arg_value The value.
     */
    public
    void setValue(StringBuffer arg_value)
    {
        int len = arg_value.length();

        // check capacity
        if (len > value.length)
            value = new char[len];

        for (int i = 0; i < len; i++)
            value[i] = arg_value.charAt(i);

        count = len;
    }


    /**
     * Set the value of the buffer.
     * @param arg_value The value.
     */
    public
    void setValue(YutilCharBuffer arg_value)
    {
        int len = arg_value.count;

        // check capacity
        if (len > value.length)
            value = new char[len];

        for (int i = 0; i < len; i++)
            value[i] = arg_value.value[i];

        count = len;
    }


    /**
     * Get the value of the buffer.
     * @return The value.
     */
    public
    String getValue()
    {
        if (count != value.length)
        {
            char[] newvalue = new char[count];
            for (int i = 0; i < count; i++)
                newvalue[i] = value[i];
            return new String(newvalue);
        }
        else
            return new String(value);
    }


    /**
     * Get the number of characters in the character collection.
     * @return The value.
     */
    public
    int length()
    {
        return count;
    }


    /**
     * Get the character at a specified position in the buffer.
     * @param index The position in the buffer.
     */
    public
    char charAt(int index)
    {
        if ((index < 0) || (index >= count))
        {
            throw new StringIndexOutOfBoundsException(index);
        }
        return value[index];
    }


    /**
     * Append a character to the character collection in the buffer.
     */
    public
    YutilCharBuffer append(char arg_char)
    {
        // check capacity
        if (value.length == count) // full
        {
            char[] newvalue = new char[count + 16];
            for (int i = 0; i < count; i++)
                newvalue[i] = value[i];
            value = newvalue;
        }

        value[count++] = arg_char;

        return this;
    }


    /**
     * Generate a hashcode of the value of the buffer.
     * @return The hashcode.
     */
    public
    int hashCode()
    {
        int hc = 0;

         for (int i = 0; i < count; i++)
            hc = (hc * 37) + value[i];

        return hc;
    }


    /**
     * Compare this value to another value.
     * @param obj An Object to compare.
     * @return true if equal.
     */
    public
    boolean equals(Object obj)
    {
        if (obj == null)
            return false;

        if (obj instanceof YutilCharBuffer)
        {
            YutilCharBuffer arg_value = (YutilCharBuffer)obj;
            if (count == arg_value.count)
            {
                for (int i = 0; i < count; i++)
                {
                    if (value[i] != arg_value.value[i])
                    {
                        return false;
                    }
                }
                return true;
            }
        }
        else

        if (obj instanceof char[])
        {
            char[] arg_value = (char[])obj;
            if (count == arg_value.length)
            {
                for (int i = 0; i < count; i++)
                {
                    if (value[i] != arg_value[i])
                    {
                        return false;
                    }
                }
                return true;
            }
        }
        else

        if (obj instanceof String)
        {
            String arg_value = (String)obj;
            if (count == arg_value.length())
            {
                for (int i = 0; i < count; i++)
                {
                    if (value[i] != arg_value.charAt(i))
                    {
                        return false;
                    }
                }
                return true;
            }
        }
        else

        if (obj instanceof StringBuffer)
        {
            StringBuffer arg_value = (StringBuffer)obj;
            if (count == arg_value.length())
            {
                for (int i = 0; i < count; i++)
                {
                    if (value[i] != arg_value.charAt(i))
                    {
                        return false;
                    }
                }
                return true;
            }
        }

        return false;
    }


    /**
     * Copy a buffer into this.
     * @param arg_charbuffer The buffer.
     */
    public
    void copyInto(YutilCharBuffer arg_charbuffer)
    {
        int len = arg_charbuffer.value.length;

        // check capacity
        if (count > len)
            arg_charbuffer.value = new char[count];

        for (int i = 0; i < count; i++)
            arg_charbuffer.value[i] = value[i];

        arg_charbuffer.count = count;
    }


    /**
     * Stringify this buffer.
     * @return This as a String.
     */
    public
    String toString()
    {
        if (count != value.length)
        {
            char[] newvalue = new char[count];
            for (int i = 0; i < count; i++)
                newvalue[i] = value[i];
            return new String(newvalue);
        }
        else
            return new String(value);
    }
}



