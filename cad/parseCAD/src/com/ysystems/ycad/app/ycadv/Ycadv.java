//==============================================================================
// Ycadv.java
//
// Main class for the Ycad viewer Applet/Application.
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/app/ycadv/Ycadv.java,v 1.51 2003/05/17 12:42:21 ekarlo Exp $
// $Log: Ycadv.java,v $
// Revision 1.51  2003/05/17 12:42:21  ekarlo
// Add option to control width of border around views.
//
// Revision 1.50  2003/05/16 05:12:03  ekarlo
// Add options to control border view colors.
// Add methods to set current view.
//
// Revision 1.49  2003/05/08 08:46:21  ekarlo
// Remove warnings.
//
// Revision 1.48  2003/04/14 12:37:26  ekarlo
// Update source file header for OSI release.
//
// Revision 1.47  2002/11/06 06:39:46  ekarlo
// Change comments.
//
// Revision 1.46  2002-10-03 03:03:50-06  ekarlo
// Make painting/updating methods consistent.
//
// Revision 1.45  2002-09-28 23:20:55-06  ekarlo
// Change prefix of entity print options to "list".
//
// Revision 1.44  2002-09-27 02:36:36-06  ekarlo
// Fix missing method error on MSIE with built in VM.
//
// Revision 1.43  2002-09-25 13:36:27-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.42  2002-09-12 15:14:16-06  ekarlo
// Imprinter development check-in.
// Update documentation.
//
// Revision 1.41  2001-05-18 23:14:28-06  ekarlo
// Print test.
//
// Revision 1.40  2001-05-11 22:58:38-06  ekarlo
// Make var name consistent.
//
// Revision 1.39  2001-05-11 22:52:11-06  ekarlo
// Fix comments.
//
// Revision 1.38  2000-10-17 02:04:15-06  ekarlo
// Fix path names in comment.
//
// Revision 1.37  2000-10-17 01:44:32-06  ekarlo
// Change package paths to lower case.
//
// Revision 1.36  2000-10-16 17:49:34-06  ekarlo
// Browser printing test code.
//
// Revision 1.35  1999-10-22 01:27:51-06  ekarlo
// API rework - phase 1.
//
// Revision 1.34  1999-09-27 10:29:39-06  ekarlo
// Add JavaDoc comments.
//
// Revision 1.33  1999-09-08 13:15:04-06  walter
// Add JavaDoc comments.
//
// Revision 1.32  1999-07-28 03:33:06-06  ekarlo
// Add JavaDoc comment showing usage.
//
// Revision 1.31  1999/07/25  19:57:24  ekarlo
// Remove unused code.
//
// Revision 1.30  1999/07/09  20:02:53  ekarlo
// Rearrange package names.
//
// Revision 1.29  1999/07/09  15:10:01  ekarlo
// Improve param properties scan.
//
// Revision 1.28  1999/07/08  03:01:15  ekarlo
// Fix shape load.
//
// Revision 1.27  1999/06/20  22:32:14  ekarlo
// Rearrange package names.
//
// Revision 1.26  1999/06/15  04:49:26  ekarlo
// User Interface - phase 1.
//
// Revision 1.25  1999/02/09  14:46:12  ekarlo
// Deactivate console print.
//
// Revision 1.24  1999/02/08  04:50:54  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.23  1999/01/28  04:09:22  ekarlo
// Text - phase 4.
//
// Revision 1.22  1998/12/21  15:21:50  ekarlo
// Text - phase 3.
//
// Revision 1.21  1998/11/24  19:29:38  ekarlo
// Text - phase 2.
//
// Revision 1.20  1998/08/24  20:38:08  ekarlo
// Add status display.
//
// Revision 1.19  1998/08/19  01:13:14  ekarlo
// Rename classes to change "Panel" to "View" in name.
//
// Revision 1.18  1998/02/12  17:43:41  ekarlo
// Change parameters used to specify input dxf file.
//
// Revision 1.17  1997/12/26  21:19:25  ekarlo
// Implement paper space - phase 1.
// Rename properties object.
// Move buttons panel to Yxxf.
//
// Revision 1.16  1997/08/30  14:01:30  ekarlo
// Redo initialization action.
// Change user properties.
//
// Revision 1.15  1997/07/23  14:12:48  ekarlo
// Move get thread out of main thread.
//
// Revision 1.14  1997/07/22  12:55:11  ekarlo
// Changes for renamed classes.
//
// Revision 1.13  1997/07/21  20:41:59  ekarlo
// MVC-VH rework - phase 2.
// Some error checking for invalid file or url.
//
// Revision 1.12  1997/07/13  17:02:51  ekarlo
// MVC-VH rework - phase 1.
//
// Revision 1.11  1997/06/11  15:34:20  ekarlo
// Make Ydxf object passive rather than thread.
// Change calls to moved command line set methods.
// Remove test() method.
//
// Revision 1.10  1996/10/26  21:41:35  ekarlo
// Show exit button only if running as application and
// do not show test button.
//
// Revision 1.9  1996/10/25  23:37:20  ekarlo
// Change parameter processing for width, height, file, url.
//
// Revision 1.8  1996/09/27  09:23:03  ekarlo
// Add text - v1.
// Implement application command line arguments.
//
// Revision 1.7  1996/09/26  17:52:07  ekarlo
// Add user parameters:
//     vport, vportnum
//     printlayers, printvports, printblocks
//     drawdspminmax, drawextminmax, drawlimminmax
//
// Revision 1.6  1996/09/12  16:30:19  ekarlo
// 1) Redo user parameter processing and add bgcolor parameter.
// 2) Change comments.
//
// Revision 1.5  1996/08/18  02:02:38  ekarlo
// Minor reformat.
//
// Revision 1.4  1996/08/14  19:24:31  ekarlo
// Add note.
//
// Revision 1.3  1996/08/13  01:39:03  ekarlo
// 1) Add test button.
// 2) Limited use of VPORT - change variable name.
//
// Revision 1.2  1996/07/30  06:07:01  ekarlo
// 1) Change default applet size.
// 2) Turn off automatic redraw (test).
// 3) Add comment.
//
// Revision 1.1  1996/07/02  01:11:56  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.app.ycadv;


import java.applet.*;
import java.awt.*;
import java.util.*;

import com.ysystems.lib.yutil.*;
import com.ysystems.ycad.lib.ydxf.*;
import com.ysystems.ycad.lib.yxxf.*;


/**
 * Main class for the Ycad viewer Applet/Application.
 * <p>
 * <h4>SYNOPSIS</h4>
 * To run as an Applet in an html file (Consult an html reference for the
 * full syntax of the <code>&lt;APPLET&gt;</code> tag):
 * <p>
 * <pre>
 *   &lt;APPLET code="com.ysystems.ycad.app.ycadv.Ycadv.class" width=<i>pixels</i> height=<i>pixels</i>&gt;
 *   &lt;PARAM name=&quot;properties&quot; value=&quot;<i>properties_value</i>&quot;
 *   &lt;/APPLET&gt;
 * </pre>
 * <p>
 * To run as an Application using the Java interpreter:
 * <p>
 * <pre>
 *   java com.ysystems.ycad.app.ycadv.Ycadv <i>command_line_values</i>
 * </pre>
 * <p>
 * Applet <i>properties_value</i> and Application <i>command_line_values</i>:
 * <p>
 * Note: The syntax for the <i>properties_value</i> and the <i>command_line_values</i>
 * are identical. 
 * <p>
 * <pre>
 *   baseurl=<i>URL (default - see table below)</i>
 *
 *   src=<i>URL or file name (see below) (no default)</i>
 *   srcfile=<i>file name (no default)</i>
 *   srcurl=<i>URL (no default)</i>
 *
 *   stylesrc<i>style_name</i>=<i>URL (no default)</i>
 *   stylesrcfile<i>style_name</i>=<i>file name (no default)</i>
 *   stylesrcurl<i>style_name</i>=<i>URL (no default)</i>
 *
 *   width=<i>application window width in pixels (default 640)</i>
 *   height=<i>application window height in pixels (default 480)</i>
 *
 *   bgcolor=<i>#aarrggbb</i>
 *       <i>(default #000000)</i>
 *   fgcolor=<i>#aarrggbb | aci | acihighinverse</i>
 *       <i>(default aci)</i>
 *   printbgcolor=<i>#aarrggbb | bgcolor</i>
 *       <i>(default #FFFFFF)</i>
 *   printfgcolor=<i>#aarrggbb | fgcolor | aci | acihighinverse</i>
 *       <i>(default acihighinverse)</i>
 *
 *   borderwidth=<i>viewport border width in pixels</i>
 *       <i>(default 2, use 0 for no border)</i>
 *   borderactivecolor=<i>#aarrggbb</i>
 *       <i>(default #FFFF00)</i>
 *   borderinactivecolor=<i>#aarrggbb</i>
 *       <i>(default #0000FF)</i>
 *
 *   listheader=<i>boolean (default false)</i>
 *   listlayers=<i>boolean (default false)</i>
 *   listltypes=<i>boolean (default false)</i>
 *   listvports=<i>boolean (default false)</i>
 *   liststyles=<i>boolean (default false)</i>
 *   listblocks=<i>boolean (default false)</i>
 *   listentities=<i>boolean (default false)</i>
 *
 *   drawdspminmax=<i>boolean (default false)</i>
 *   drawextminmax=<i>boolean (default false)</i>
 *   drawlimminmax=<i>boolean (default false)</i>
 *
 *   showtoolbar=<i>boolean (default true)</i>
 * </pre>
 * <p>
 * <h4>DESCRIPTION</h4>
 * All values are optional except one of <code>src</code>, <code>srcfile</code> or <code>srcurl</code>
 * is required.
 * <p>
 * Rules for <code>baseurl</code>, <code>src</code>, <code>srcfile</code>, <code>srcurl</code>:
 * <p>
 * <pre>
 *  Argument         Applet                      Application
 *  ---------------- --------------------------- ------------------------
 *  baseurl
 *    user set       user set value              user set value
 *    or
 *    null           applet.getDocumentBase()    null
 * 
 *  src              URL(baseurl, src)           FileInputStream(src)
 *  srcfile          FileInputStream(src)        FileInputStream(src)
 *  srcurl           URL(baseurl, src)           URL(baseurl, src)
 * </pre>
 * <p>
 * The <code>stylesrc</code>, <code>stylesrcfile</code>, <code>stylesrcurl</code>
 * values associate a STYLE table entry with a <code>SHX</code> (compiled shape) file and follow
 * the same rules.  the <i>style_name</i> identifies the particular STYLE table
 * entry.  If <i>style_name</i> is missing it will indicate the default <code>SHX</code> file
 * to use for entries not otherwise specified (see examples).
 * <p>
 * <code>bgcolor=#rrggbb</code> where #rrbbgg is a hexadecimal (base 16) red-green-blue triplet used to specify the background color.
 * <p>
 * The <code>list...</code> values will list drawing values on the Java console.
 * <p>
 * <code>listheader</code> lists selected HEADER section values<br>
 * <code>listlayers</code> lists LAYERS table<br>
 * <code>listltypes</code> lists LTYPES table<br>
 * <code>listvports</code> lists VPORTS table and VIEWPORT entities<br>
 * <code>liststyles</code> lists STYLES table<br>
 * <code>listblocks</code> lists BLOCKS section names<br>
 * <code>listentities</code> lists ENTITIES section stats<br>
 * <p>
 * The <code>draw...max</code> values will draw on screen boxes at the selected boundaries.
 * <p>
 * <code>drawdspminmax</code> draws a box around the display window in color 12<br>
 * <code>drawextminmax</code> draws a box around the extent limits in yellow(from HEADER section)<br>
 * <code>drawlimminmax</code> draws a box around the drawing limits in red(from HEADER section)<br>
 * <p>
 * <code>showtoolbar</code> show/not show toolbar<br>
 * <p>
 * <h4>EXAMPLES</h4>
 * Note: long lines are wrapped for readability.
 * <p>
 * Running as Applet in html file:
 * <pre>
 *   &lt;APPLET code="com.ysystems.ycad.app.ycadv.Ycadv.class" width=640 height=480&gt;
 *   &lt;PARAM name=&quot;properties&quot; value=&quot;src=dat/blk3.dxf
 *      bgcolor=#0000FF listlayers=true showtoolbar=false&quot;
 *   &lt;/APPLET&gt;
 * </pre>
 * <p>
 * <pre>
 *   &lt;APPLET code="com.ysystems.ycad.app.ycadv.Ycadv.class" width=640 height=480&gt;
 *   &lt;PARAM name=&quot;properties&quot; value=&quot;src=dat/tut2.dxf
 *      stylesrcSTANDARD=dat/fonts/text05.shx stylesrc=dat/fonts/romans.shx
 *      bgcolor=#0000FF listlayers=true showtoolbar=false&quot;
 *   &lt;/APPLET&gt;
 * </pre>
 * Running as Application from command line:
 * <pre>
 *   java com.ysystems.ycad.app.ycadv.Ycadv width=320 height=100
 *      srcfile=/home/test01/dat/vallyhouse.dxf
 * </pre>
 *
 * @author Ed Karlo - Y Systems, LLC
 */
public class Ycadv extends Applet
{
    //==========================================================================
    /**
     * Assume we are an Applet unless set false in the Application constructor.
     */
    private
    boolean                     runningAsApplet         = true;

    /**
     * Arguments if running as application.
     */
    private
    String                      argv[];
    //==========================================================================


    //==========================================================================
    // Constructors

    /**
     * Constructor - Applet (empty).
     */
    public
    Ycadv()
    {
//d     System.out.println("++Ycadv:APPLET CONSTRUCTOR");
    }

    /**
     * Constructor - Application.
     * @param argv Array of arguments used by main().
     */
    public
    Ycadv(String argv[])
    {
//d     System.out.println("++Ycadv:APPLICATION CONSTRUCTOR");
        runningAsApplet = false;
        this.argv = argv;
    }
    //==========================================================================


    //==========================================================================
    // Properties

    /** 
     * All user supplied properties.
     */
    private
    YutilProperties             props_user              = new YutilProperties();

    /**
     * Application window width.
     */
    private
    int                         param_width             = 0;
    /**
     * Application window height.
     */
    private
    int                         param_height            = 0;
    //==========================================================================


    //==========================================================================
    // Drawing

    /**
     * Main drawing.
     */
    private
    Yxxf                        D                       = null;
    //==========================================================================


    //==========================================================================
    /**
     * Get Handler.
     */
    private
    YdxfGetHandler              gethandler              = null;
    //==========================================================================


    //==========================================================================
    // View

    /**
     * View Handler.
     */
    public
    YxxfDrwViewHandler          vhandler                = null;

    /**
     * Viewer.
     */
    private
    YxxfDrwView                 viewer                  = null;
    //==========================================================================


    //==========================================================================
    /**
     * Called when applet is loaded (before the first time start() is called).
     *<UL>
     *   <LI> Set up drawing:
     *   <LI> - Create drawing
     *   <LI> - Create I/O handler and I/O name in drawing
     *   <LI> - Create Get handler
     *   <LI> Process properties - sets I/O name values
     *   <LI> Set up view handler
     *   <LI> - Create view handler
     *   <LI> - Get viewer panel from view handler and add to Applet
     *   <LI> - Force press of view handler start button
     *   <LI> Start Input with drawing - viewer is rolling
     *</UL>
     */
    public
    void init()
    {
        System.out.println("++Ycadv:init|BEG");
//d     System.out.println("++Ycadv:init|Ycadv=[" + this + "]");
//d     if (runningAsApplet)
//d     {
//d         System.out.println("++Ycadv:init|runningAsApplet=true");
//d     }
//d     else
//d     {
//d         System.out.println("++Ycadv:init|runningAsApplet=false");
//d     }


        // ============================================================
        // Set up drawing
        //
        // Create drawing
        // Create I/O handler and I/O name in drawing
        // Create Get handler
        // ============================================================

        // Create drawing
        D = new Yxxf();

        // Create I/O Handler
        if (runningAsApplet)
            D.iohandler = new YutilIOHandler(this);
        else
            D.iohandler = new YutilIOHandler();

        // Create empty main drawing ioname
        D.ioname = new YutilIOHandlerName("main");


        // Create Get Handler
        gethandler =  new YdxfGetHandler();




        // ============================================================
        // Set props_user - sets I/O name values
        // ============================================================
        processProperties();




        // ============================================================
        // Create view handler
        // Get viewer panel from view handler and add to Applet
        // ============================================================
//d     System.out.println("++Ycadv:init|setting up view");

        //
        // Create view handler (Observer)
        //
        vhandler = new YxxfDrwViewHandler();

        // Set parameters in view handler
        // Copy in all properties from props_user that are not used here
        for (Enumeration ep = props_user.propertyNames(); ep.hasMoreElements(); )
        {
            String key = ((String)ep.nextElement()).toLowerCase();
            String keylower = key.toLowerCase();

            if (!keylower.equals("width") &&
                !keylower.equals("height") &&
                !keylower.equals("baseurl") &&
                !keylower.equals("src") &&
                !keylower.equals("srcfile") &&
                !keylower.equals("srcurl") &&
                !keylower.startsWith("stylesrc") &&
                !keylower.startsWith("stylesrcfile") &&
                !keylower.startsWith("stylesrcurl"))
            {
                String val = key + "=" + props_user.getProperty(key);
                vhandler.setProperties(val, false);
            }
        }
        vhandler.commandViewHandler_command_init(D);
        vhandler.commandViewHandler_command_start();


        //
        // Get viewer panel from view handler and add to Applet
        //
        setLayout(new GridBagLayout());

        viewer = vhandler.getViewer();

        // Position and add the viewer to Applet
        YutilAWT.constrain(this, viewer,
            0, // gridx
            GridBagConstraints.RELATIVE, // gridy

            GridBagConstraints.REMAINDER, // gridwidth
            GridBagConstraints.REMAINDER, // gridheight

            GridBagConstraints.BOTH, // fill
            GridBagConstraints.NORTHWEST, // anchor

            1, 1, // weightx, weighty
            0, 0, // ipadx, ipady
            0, 0, 0, 0); // Insets T,L,B,R

        validate();

//d     System.out.println("++Ycadv:init|viewer:validate");


        //
        // Force press of Start button
        //
        vhandler.commandViewHandler_toolbar_start();




        // ============================================================
        // Start Input
        // ============================================================
        // Start get drawing
//d     System.out.println("++Ycadv:init|BEG gethandler.commandGetStart");
        gethandler.commandGetStart(D);
//d     System.out.println("++Ycadv:init|END gethandler.commandGetStart");

        System.out.println("++Ycadv:init|END");
    }


    /**
     * Called after init() and each time the applet becomes visible.
     */
    public
    void start()
    {
//d     System.out.println("++Ycadv:BEG START");
//d     System.out.println("++Ycadv:END START");
    }


    /**
     * Called before destroy() and each time the applet becomes invisible.
     */
    public
    void stop()
    {
//d     System.out.println("++Ycadv:BEG STOP");
//d     System.out.println("++Ycadv:END STOP");
    }


    /**
     * Called when applet is being eliminated.
     */
    public
    void destroy()
    {
//d     System.out.println("++Ycadv:BEG DESTROY");
//d     System.out.println("++Ycadv:END DESTROY");
    }
    //==========================================================================


    //==========================================================================
    // Painting
    //==========================================================================
    /**
     * Deactivate paint(jgc).
     * @param jgc The Java Graphics object.
     */
    public
    void paint(Graphics jgc)
    {
        // System.out.println("++Ycadv:paint   (" + jgc + "),isEn=" + isEnabled() + ",isSh=" + isShowing() + ",isVa=" + isValid() + ",isVi=" + isVisible());
    }


//  /**
//   * Passthru paintAll(jgc).
//   * @param jgc The Java Graphics object.
//   */
//  public
//  void paintAll(Graphics jgc)
//  {
//      System.out.println("++Ycadv:paintAll(" + jgc + "),isEn=" + isEnabled() + ",isSh=" + isShowing() + ",isVa=" + isValid() + ",isVi=" + isVisible());
//      super.paintAll(jgc);
//  }


//  /**
//   * Passthru repaint().
//   * @param jgc The Java Graphics object.
//   */
//  public
//  void repaint()
//  {
//      System.out.println("++Ycadv:repaint (),isEn=" + isEnabled() + ",isSh=" + isShowing() + ",isVa=" + isValid() + ",isVi=" + isVisible());
//      super.repaint();
//  }


//  /**
//   * Passthru repaint(x, y, width, height).
//   * @param x X coordinate.
//   * @param y Y coordinate.
//   * @param width Width.
//   * @param height Height.
//   */
//  public
//  void repaint(int x, int y, int width, int height)
//  {
//      System.out.println("++Ycadv:repaint (" + x + "," + y + "," + width + "," + height + "),isEn=" + isEnabled() + ",isSh=" + isShowing() + ",isVa=" + isValid() + ",isVi=" + isVisible());
//      super.repaint(x, y, width, height);
//  }


//  /**
//   * Passthru repaint(tm).
//   * @param tm Time in milliseconds.
//   */
//  public
//  void repaint(long tm)
//  {
//      System.out.println("++Ycadv:repaint (" + tm + "),isEn=" + isEnabled() + ",isSh=" + isShowing() + ",isVa=" + isValid() + ",isVi=" + isVisible());
//      super.repaint(tm);
//  }


//  /**
//   * Passthru repaint(tm, x, y, width, height).
//   * @param tm Time in milliseconds.
//   * @param x X coordinate.
//   * @param y Y coordinate.
//   * @param width Width.
//   * @param height Height.
//   */
//  public
//  void repaint(long tm, int x, int y, int width, int height)
//  {
//      System.out.println("++Ycadv:repaint (" + tm + "," + x + "," + y + "," + width + "," + height + "),isEn=" + isEnabled() + ",isSh=" + isShowing() + ",isVa=" + isValid() + ",isVi=" + isVisible());
//      super.repaint(tm, x, y, width, height);
//  }


    /**
     * Intercept update(jgc).
     * @param jgc The Java Graphics object.
     */
    public
    void update(Graphics jgc)
    {
        // System.out.println("++Ycadv:update  (" + jgc + "),isEn=" + isEnabled() + ",isSh=" + isShowing() + ",isVa=" + isValid() + ",isVi=" + isVisible());
        paint(jgc);
    }
    //==========================================================================


    //==========================================================================
    // Printing
    //==========================================================================
    /**
     * Hook for print().
     * @param jpc The Java Graphics object for printing.
     */
    public void print(Graphics jpc)
    {
        System.out.println("++Ycadv:print,jpc=" + jpc);
        super.print(jpc);
    }

    /**
     * Hook for printAll().
     * @param jpc The Java Graphics object for printing.
     */
    public void printAll(Graphics jpc)
    {
        System.out.println("++Ycadv:printAll,jpc=" + jpc);
        super.printAll(jpc);
    }
    //==========================================================================


    //==========================================================================
    /**
     * Get user parameter width value.
     * @return The width
     */
    public
    int getParamWidth()
    {
        return param_width;
    }


    /**
     * Get user parameter height value.
     * @return The height
     */
    public
    int getParamHeight()
    {
        return param_height;
    }
    //==========================================================================


    //==========================================================================
    /**
     * Process properties.
     *<UL>
     *  <LI>Set defaults (file and url have none).
     *  <LI>Load properties.
     *  <LI>Set local params.
     *  <LI>All user params examined - do post processing of source names.
     *</UL>
     */
    private
    void processProperties()
    {
        // Set defaults (file and url have none)
        props_user.setPropertiesDefaults("width=640", false);
        props_user.setPropertiesDefaults("height=480", false);


        // Load properties
        if (runningAsApplet)
        {   // Process Applet properties

            // Load from properties param
            props_user.setProperties(getParameter("properties"), true);
        }
        else
        {   // Process Application properties

            // Load from command line arguments array
            props_user.setProperties(argv);
        }


        // Set local params
        System.out.println("++Ycadv:processProperties|props_user BEG");
        for (Enumeration e = props_user.propertyNames(); e.hasMoreElements(); )
        {
            String key = (String)e.nextElement();
            String val = props_user.getProperty(key);
            System.out.println("++Ycadv:processProperties|key=[" + key + "],val=[" + val + "]");

            String keylower = key.toLowerCase();


            // Application window size
            if (keylower.equals("width"))
            {
                param_width = props_user.getProperty_int(key);
            }
            else
            if (keylower.equals("height"))
            {
                param_height = props_user.getProperty_int(key);
            }
            else



            //
            // Main drawing source
            //
            if (keylower.equals("baseurl"))
            {   // Base URL for all sources
                D.ioname.baseurl = val;
            }
            else


            if (keylower.equals("src"))
            {
                D.ioname.src = val;
            }
            else

            if (keylower.equals("srcfile"))
            {
                D.ioname.srcfile = val;
            }
            else

            if (keylower.equals("srcurl"))
            {
                D.ioname.srcurl = val;
            }
            else


            //
            // Style shape source
            //
            if (keylower.startsWith("stylesrc") ||
                keylower.startsWith("stylesrcfile") ||
                keylower.startsWith("stylesrcurl"))
            {
                String stylename = ""; // Default style name is empty string

                // Extract style name from key name
                if (keylower.startsWith("stylesrc"))
                {
                    if (keylower.length() > 8)
                        stylename = keylower.substring(8).toUpperCase();
                }
                else
                if (keylower.startsWith("stylesrcfile"))
                {
                    if (keylower.length() > 12)
                        stylename = keylower.substring(12).toUpperCase();
                }
                else
                if (keylower.startsWith("stylesrcurl"))
                {
                    if (keylower.length() > 11)
                        stylename = keylower.substring(11).toUpperCase();
                }


                // Find matching style element
                YxxfTblStyle style = D.secTables.findStyle_add(stylename);

                if (style.shape == null)
                {
                    style.shape = new YxxfShape(); // all styles will use shapes
                }

                if (style.shape.ioname == null)
                {   // not set yet, create
                    style.shape.ioname = new YutilIOHandlerName(stylename);
                }


                // Set proper value
                if (keylower.startsWith("stylesrc"))
                    style.shape.ioname.src = val;
                else
                if (keylower.startsWith("stylesrcfile"))
                    style.shape.ioname.srcfile = val;
                else
                if (keylower.startsWith("stylesrcurl"))
                    style.shape.ioname.srcurl = val;
            }

        }
        System.out.println("++Ycadv:processProperties|props_user END");


        // All user params examined - do post processing of source names
//d     System.out.println("++Ycadv:processProperties|D.ioname=[" + D.ioname + "]");

        // For each style
        // 1) Set iohandler in each style to match main iohandler
        // 2) Set baseurl in each style ioname to match main ioname
//d     System.out.println("++Ycadv:processProperties|style ioname.e_ioname BEG");
        for (Enumeration e_ioname = D.secTables.tblStyle.elements(); e_ioname.hasMoreElements(); ) // eieio
        {
            YxxfTblStyle style = (YxxfTblStyle)e_ioname.nextElement();

            if (style.shape == null)
            {
                continue;
            }

            if (style.shape.ioname == null)
            {
                continue;
            }

            // 1)
            style.shape.iohandler = D.iohandler;

            // 2)
            style.shape.ioname.baseurl = D.ioname.baseurl;
//d         System.out.println("++Ycadv:processProperties|style ioname:name=[" + style.getName() + "],ioname=[" + style.shape.ioname + "]");
        }
//d     System.out.println("++Ycadv:processProperties|style ioname.e_ioname END");
    }
    //==========================================================================




    //==========================================================================
    /**
     * The main routine for execution as an application.
     *<UL>
     *  <LI>Instantiate the Applet (processes argv).
     *  <LI>Create a Frame for the Applet.
     *  <LI>Add the Applet to the Frame (Frame's use BorderLayout).
     *  <LI>Init and start the Applet.
     *  <LI>Resize the Frame according to user parameters.
     *  <LI>Show and start the Applet.
     *<UL>
     * @param argv Command line arguments.
     */
    public static void main(String argv[]){
        String[]filename = new String[1]; 

        filename[0] = "src=C:/Users/k/Desktop/cadd/color.dxf";
        
        // Instantiate the Applet (processes argv)
        Ycadv vapplet = new Ycadv(filename);


        // Create a Frame for the Applet
        //YcadvFrame vframe = new YcadvFrame("Ycadv", vapplet);

        // Add the Applet to the Frame (Frame's use BorderLayout)
        //vframe.add("Center", vapplet);


        // Init and start the Applet
        // TODO: bail if init errors
        vapplet.init();

        // Resize the Frame according to user parameters
//d     System.out.println("++Ycadv:w=" + vapplet.getParamWidth() + ",h=" + vapplet.getParamHeight());
       // vframe.resize(vapplet.getParamWidth(), vapplet.getParamHeight()); // calls Component.resize(int width, int height)

        // Show and start the Applet
       // vframe.show();
        //vapplet.start();
    }
    //==========================================================================
}




/**
 * Extend Frame to handle the window-close event.
 */
class YcadvFrame extends Frame
{
    /**
     * An applet.
     */
    Ycadv vapplet;

    public YcadvFrame(String s, Ycadv vapplet)
    {
        super(s);
        this.vapplet = vapplet;
    }

    /**
     * Handle close events by simply exiting.
     * @return false = failure
     */
    public boolean handleEvent(Event e)
    {
//d     System.out.println("++YcadvFrame:handleEvent" + e);
        if (e.id == Event.WINDOW_DESTROY)
        {
            vapplet.stop();
            vapplet.destroy();
            System.exit(0);
        }
        return false;
    }


    /**
     * Deactivate paint(jgc).
     * @param jgc The Java Graphics object.
     */
    public
    void paint(Graphics jgc)
    {
        // System.out.println("++YcadvFrame:paint   (" + jgc + "),isEn=" + isEnabled() + ",isSh=" + isShowing() + ",isVa=" + isValid() + ",isVi=" + isVisible());
    }


//  /**
//   * Passthru paintAll(jgc).
//   * @param jgc The Java Graphics object.
//   */
//  public
//  void paintAll(Graphics jgc)
//  {
//      System.out.println("++YcadvFrame:paintAll(" + jgc + "),isEn=" + isEnabled() + ",isSh=" + isShowing() + ",isVa=" + isValid() + ",isVi=" + isVisible());
//      super.paintAll(jgc);
//  }


//  /**
//   * Passthru repaint().
//   * @param jgc The Java Graphics object.
//   */
//  public
//  void repaint()
//  {
//      System.out.println("++YcadvFrame:repaint (),isEn=" + isEnabled() + ",isSh=" + isShowing() + ",isVa=" + isValid() + ",isVi=" + isVisible());
//      super.repaint();
//  }


//  /**
//   * Passthru repaint(x, y, width, height).
//   * @param x X coordinate.
//   * @param y Y coordinate.
//   * @param width Width.
//   * @param height Height.
//   */
//  public
//  void repaint(int x, int y, int width, int height)
//  {
//      System.out.println("++YcadvFrame:repaint (" + x + "," + y + "," + width + "," + height + "),isEn=" + isEnabled() + ",isSh=" + isShowing() + ",isVa=" + isValid() + ",isVi=" + isVisible());
//      super.repaint(x, y, width, height);
//  }


//  /**
//   * Passthru repaint(tm).
//   * @param tm Time in milliseconds.
//   */
//  public
//  void repaint(long tm)
//  {
//      System.out.println("++YcadvFrame:repaint (" + tm + "),isEn=" + isEnabled() + ",isSh=" + isShowing() + ",isVa=" + isValid() + ",isVi=" + isVisible());
//      super.repaint(tm);
//  }


//  /**
//   * Passthru repaint(tm, x, y, width, height).
//   * @param tm Time in milliseconds.
//   * @param x X coordinate.
//   * @param y Y coordinate.
//   * @param width Width.
//   * @param height Height.
//   */
//  public
//  void repaint(long tm, int x, int y, int width, int height)
//  {
//      System.out.println("++YcadvFrame:repaint (" + tm + "," + x + "," + y + "," + width + "," + height + "),isEn=" + isEnabled() + ",isSh=" + isShowing() + ",isVa=" + isValid() + ",isVi=" + isVisible());
//      super.repaint(tm, x, y, width, height);
//  }


    /**
     * Intercept update(jgc).
     * @param jgc The Java Graphics object.
     */
    public
    void update(Graphics jgc)
    {
        // System.out.println("++YcadvFrame:update  (" + jgc + "),isEn=" + isEnabled() + ",isSh=" + isShowing() + ",isVa=" + isValid() + ",isVi=" + isVisible());
        paint(jgc);
    }
}
