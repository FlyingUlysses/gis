//==============================================================================
// YshxGetBuffer.java
//
// Get font characters from the shx file
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
// YshxGetBuffer
//
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yshx/YshxGetBuffer.java,v 1.9 2003/05/08 09:38:17 ekarlo Exp $
// $Log: YshxGetBuffer.java,v $
// Revision 1.9  2003/05/08 09:38:17  ekarlo
// Remove warnings.
//
// Revision 1.8  2003/04/14 12:38:24  ekarlo
// Update source file header for OSI release.
//
// Revision 1.7  2000/10/17 07:44:12  ekarlo
// Change package paths to lower case.
//
// Revision 1.6  1999-10-21 21:36:06-06  walter
// Added JavaDoc comments.
//
// Revision 1.5  1999-07-09 14:09:02-06  ekarlo
// Rearrange package names.
//
// Revision 1.4  1999/06/20  22:33:20  ekarlo
// Rearrange package names.
//
// Revision 1.3  1999/06/15  05:01:02  ekarlo
// Fix comment.
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


/**
 * Get font characters from the shx file.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YshxGetBuffer
{
    /**
     * Type of get
     */
    public int                  get_type            = GET_TYPE_MAIN;
    /**
     * Type of get - Drawing.
     */
    public static final int     GET_TYPE_MAIN       = 1;
    /**
     * Type of get - Font.
     */
    public static final int     GET_TYPE_FONT       = 2;


    /**
     * read buffer
     */
    private InputStream         is;

    /**
     * The Get buffer size.
     */
    private static final int    BUFSIZ      = 2048;
    /**
     * The Get buffer.
     */
    private byte                buf[]       = new byte[BUFSIZ];
    /**
     * The number of bytes in the buffer.
     */
    private int                 buflen      = 0; // bytes in buf
    /**
     * The next unprocessed byte.
     */
    private int                 bufidx      = 0; // next unprocessed byte
    /**
     * End of file flag.
     */
    private boolean             bufeof      = false;


    /**
     * Constructor (empty)
     */
    public
    YshxGetBuffer()
    {
    }


    /**
     * Set the type of input.
     * @param get_type The input type.
     * @param is The input stream.
     */
    public void setInput(int get_type, InputStream is)
    {
        this.get_type = get_type;
        this.is = is;
    }


    /**
     * Close the input stream.
     */
    public void close()
    {
        try
        {
            is.close();
        }
        catch (IOException e)
        {
            System.out.println("YshxGetBuffer:close()|IOException");
            System.out.println("YshxGetBuffer:close()|message=[" + e.getMessage() + "]");
            e.printStackTrace();
        }
        is = null;
        bufeof = true;
    }


    /**
     * Get the next unprocessed byte.
     * @return The value of the next byte as a char.
     */
    public
    char getc()
    {
        if (bufeof)
            return '\0';

        if (bufidx < buflen)
            return (char)((char)buf[bufidx++] & (char)0x00FF);

        getBuf();

        if (bufeof)
            return '\0';

        return (char)((char)buf[bufidx++] & (char)0x00FF);
    }


    /**
     * Get the value of the end of file flag.
     */
    public
    boolean eof()
    {
        return bufeof;
    }




    //==========================================================================
    // private support functions
    //==========================================================================

    //
    // get functions
    //

    /**
     * Fill buf from input stream or set bufeof.
     */
    private
    void getBuf()
    {
        bufidx = 0;
        try
        {
            buflen = is.read(buf, 0, BUFSIZ);
        }
        catch (IOException e)
        {
            System.out.println("YshxGetBuffer:getBuf()|IOException");
            System.out.println("YshxGetBuffer:getBuf()|message=[" + e.getMessage() + "]");
            e.printStackTrace();
            buflen = -1;
        }
        if (buflen == -1)
        {
            buflen = 0;
            bufeof = true;
        }
    }
}

