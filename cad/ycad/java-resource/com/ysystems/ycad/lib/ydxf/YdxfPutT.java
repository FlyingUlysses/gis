//==============================================================================
// YdxfPutT.java
//
// Put thread
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/ydxf/YdxfPutT.java,v 1.12 2003/05/08 09:38:16 ekarlo Exp $
// $Log: YdxfPutT.java,v $
// Revision 1.12  2003/05/08 09:38:16  ekarlo
// Remove warnings.
//
// Revision 1.11  2003/04/14 12:37:56  ekarlo
// Update source file header for OSI release.
//
// Revision 1.10  2000/10/17 07:44:13  ekarlo
// Change package paths to lower case.
//
// Revision 1.9  1999-10-22 01:28:01-06  ekarlo
// API rework - phase 1.
//
// Revision 1.8  1999-10-06 19:50:39-06  walter
// Added JavaDoc comments.
//
// Revision 1.7  1999-07-09 14:09:43-06  ekarlo
// Rearrange package names.
//
// Revision 1.6  1999/06/27  08:28:14  ekarlo
// Rename class.
//
// Revision 1.5  1999/06/20  22:32:38  ekarlo
// Rearrange package names.
//
// Revision 1.4  1999/06/15  04:54:50  ekarlo
// Rename class.
//
// Revision 1.3  1999/02/09  14:47:49  ekarlo
// Deactivate console print.
//
// Revision 1.2  1999/02/08  04:53:14  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.1  1999/01/28  04:19:23  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.ydxf;


import java.io.*;

import com.ysystems.ycad.lib.yxxf.*;


/**
 * Put Thread responsible for the Drawing output.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YdxfPutT extends Thread
{
    /**
     * Buffer that contains Output.
     */
    private
    YdxfPutBuffer               putbfr = new YdxfPutBuffer();


    /**
     * The Drawing
     */
    private
    Yxxf                        D;


    // Misc
//  boolean                     oktostop = false;


    /**
     * Constructor (empty).
     */
    public
    YdxfPutT()
    {
    }


    /**
     * Constructor
     * @param put_type Type of output i.e. Main or Font.
     * @param os The output stream.
     * @param D The Drawing
     */
    public
    YdxfPutT(int put_type, OutputStream os, Yxxf D)
    {
        setOutput(put_type, os, D);
    }


    /**
     * Set output information
     * @param put_type The type of output i.e. Main or Font.
     * @param os The output stream.
     * @param D The Drawing.
     */
    public
    void setOutput(int put_type, OutputStream os, Yxxf D)
    {
        putbfr.setOutput(put_type, os);
        this.D = D;
    }


//  public
//  void setOktostop()
//  {
//      oktostop = true;
//  }


    /**
     * The Put Thread's run method.
     */
    public
    void run()
    {
//d     System.out.println("Tput :BEG run");

        D.waitDrawingComplete(); // Wait for full drawing load

        YdxfPut.put(putbfr, D);

        System.gc(); // Start Garbage collection

        D.setDrawingWritten(true);

//d     System.out.println("TPut :WAIT run");

//      // Wait for signal to stop - DebugX (is this still necessary?)
//      while (true)
//      {
//          if (oktostop)
//              break;
//          try { Thread.sleep(1000); }
//          catch(InterruptedException e) {}
//      }

//d     System.out.println("TPut :END run");
    }
}

