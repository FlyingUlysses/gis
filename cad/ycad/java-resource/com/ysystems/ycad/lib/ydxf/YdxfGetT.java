//==============================================================================
// YdxfGetT.java
//
// Main DXF get thread
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/ydxf/YdxfGetT.java,v 1.35 2003/05/08 09:38:16 ekarlo Exp $
// $Log: YdxfGetT.java,v $
// Revision 1.35  2003/05/08 09:38:16  ekarlo
// Remove warnings.
//
// Revision 1.34  2003/04/14 12:37:55  ekarlo
// Update source file header for OSI release.
//
// Revision 1.33  2000/10/17 07:44:19  ekarlo
// Change package paths to lower case.
//
// Revision 1.32  1999-10-22 01:27:49-06  ekarlo
// API rework - phase 1.
//
// Revision 1.31  1999-10-21 21:30:46-06  walter
// Added JavaDoc comments.
//
// Revision 1.30  1999-07-09 14:09:43-06  ekarlo
// Rearrange package names.
//
// Revision 1.29  1999/06/27  08:28:14  ekarlo
// Rename class.
//
// Revision 1.28  1999/06/20  22:32:38  ekarlo
// Rearrange package names.
//
// Revision 1.27  1999/06/15  04:52:56  ekarlo
// Rename class.
//
// Revision 1.26  1999/02/09  14:47:49  ekarlo
// Deactivate console print.
//
// Revision 1.25  1999/02/08  04:56:30  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.24  1999/01/28  04:13:33  ekarlo
// Text - phase 4.
//
// Revision 1.23  1998/12/22  14:43:16  ekarlo
// Renames - add "Get" to name.
//
// Revision 1.22  1998/02/02  18:52:36  ekarlo
// Increase end loop sleep interval.
//
// Revision 1.21  1997/07/23  14:17:17  ekarlo
// Move get thread out of main thread.
// Remove progress indicators.
//
// Revision 1.20  1997/07/21  22:27:47  ekarlo
// Change for static gets.
//
// Revision 1.19  1997/07/13  18:01:50  ekarlo
// MVC-VH rework - phase 1.
// Rename class.
// Rename get class.
//
// Revision 1.18  1997/06/20  19:56:11  ekarlo
// Rename from YdxfTGet to YdxfGetInputT.
//
// Revision 1.17  1997/06/11  15:38:00  ekarlo
// Remove print stats code (moved to YxxfTDraw).
// Move out generate test methods.
//
// Revision 1.16  1996/10/26  00:09:46  ekarlo
// Correct color and layer handling.
//
// Revision 1.15  1996/09/27  09:37:03  ekarlo
// Add text - v1.
//
// Revision 1.14  1996/09/26  18:02:52  ekarlo
// Use user parameters.
//
// Revision 1.13  1996/09/26  01:43:16  ekarlo
// Implement ESpace, PSpace, MSpace.
//
// Revision 1.12  1996/09/14  03:26:01  ekarlo
// Connect layer in add entity methods.
//
// Revision 1.11  1996/09/13  06:19:04  ekarlo
// 1) Use new basse entity class structure.
// 2) Remove unused code.
//
// Revision 1.10  1996/08/31  02:38:21  ekarlo
// Draw ellipse - working - needs optimization.
//
// Revision 1.9  1996/08/25  05:30:25  ekarlo
// Draw ellipse - continuing.
//
// Revision 1.8  1996/08/19  18:37:40  ekarlo
// 1) Deactivate paper space code until done for real.
// 2) Move debug line generation until after block is set ready.
// 3) During ENDBLK processing, set ready and complete flags
//    only if not in the paper or model space blocks.
//
// Revision 1.7  1996/08/19  15:52:40  ekarlo
// Don't calculate insert transform automatically.
//
// Revision 1.6  1996/08/19  05:00:10  ekarlo
// 1) Fix status indicator.
// 2) Set loaded flag later.
// 3) Change comment.
//
// Revision 1.5  1996/08/18  02:30:18  ekarlo
// Remove unused code.
//
// Revision 1.4  1996/08/14  19:45:36  ekarlo
// 1) Add method genBox(), use to draw extent and limit lines.
// 2) Add method genPoint(), use to draw vtgt location.
// 3) Change comment.
//
// Revision 1.3  1996/08/13  01:51:33  ekarlo
// 1) Limited use of vport.
// 2) Implement POINT.
// 3) Change drawing of indicators.
//
// Revision 1.2  1996/07/30  06:36:12  ekarlo
// 1) Remove unused variable.
// 2) Call function to draw status indicators.
// 3) Add sleep loop at end of run (page fault workaround).
//
// Revision 1.1  1996/07/02  01:58:28  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.ydxf;


import java.io.*;

import com.ysystems.ycad.lib.yxxf.*;


/**
 * Main DXF get thread.
 */
public class YdxfGetT extends Thread
{
    /**
     * Input
     */
    private
    YdxfGetBuffer               getbfr = new YdxfGetBuffer();


    /**
     * Drawing
     */
    private
    Yxxf                        D;


    // Misc
//  boolean                     oktostop = false;


    /**
     * Constructor (empty)
     */
    public
    YdxfGetT()
    {
    }


    /**
     * Constructor
     * @param get_type The type of get i.e Drawing or Font.
     * @param is The input stream.
     * @param D The Drawing.
     */
    public
    YdxfGetT(int get_type, InputStream is, Yxxf D)
    {
        setInput(get_type, is, D);
    }


    /**
     * Set the input values
     * @param get_type The type of get i.e Drawing or Font.
     * @param is The input stream.
     * @param D The Drawing.
     */
    public
    void setInput(int get_type, InputStream is, Yxxf D)
    {
        getbfr.setInput(get_type, is, D);
        this.D = D;
    }


//  public
//  void setOktostop()
//  {
//      oktostop = true;
//  }


    /**
     * The Thread.run method.
     */
    public
    void run()
    {
//d     System.out.println("+++YdxfGetT:BEG run");

//      D.waitDrawingReady(); // Wait for full drawing setup

        YdxfGet.get(getbfr);

//      D.setDrawingLoaded(true);

        System.gc(); // Start Garbage collection

//d     System.out.println("+++YdxfGetT:WAIT run");

//      // Wait for signal to stop - DebugX (is this still necessary?)
//      while (true)
//      {
//          if (oktostop)
//              break;
//          try { Thread.sleep(1000); }
//          catch(InterruptedException e) {}
//      }

//d     System.out.println("+++YdxfGetT:END run");
    }
}

