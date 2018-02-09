//==============================================================================
// YcadTest.java
//
// Test bed
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/app/ycadtest/YcadTest.java,v 1.6 2003/05/08 08:46:24 ekarlo Exp $
// $Log: YcadTest.java,v $
// Revision 1.6  2003/05/08 08:46:24  ekarlo
// Remove warnings.
//
// Revision 1.5  2003/04/14 12:37:26  ekarlo
// Update source file header for OSI release.
//
// Revision 1.4  2002/11/06 06:40:12  ekarlo
// Change comments.
// Reorder methods.
//
// Revision 1.3  2001-10-17 23:16:42-06  ekarlo
// Change package paths to lower case.
//
// Revision 1.2  1999-10-22 01:27:58-06  ekarlo
// API rework - phase 1.
//
// Revision 1.1  1999-10-17 11:33:35-06  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.app.ycadtest;


import java.applet.*;
import java.awt.*;
import java.util.*;

import com.ysystems.lib.yutil.*;
import com.ysystems.ycad.lib.yxxf.*;


/**
 * Main class for the Ycad DXF viewer Applet/Application test bed.
 * <p>
 * <h4>SYNOPSIS</h4>
 * To run as an Applet in an html file (Consult an html reference for the
 * full syntax of the <code>&lt;APPLET&gt;</code> tag):
 * <p>
 * <pre>
 *   &lt;APPLET code="com.ysystems.ycad.app.ycadtest.YcadTest.class" width=<i>pixels</i> height=<i>pixels</i>&gt;
 *   &lt;PARAM name=&quot;properties&quot; value=&quot;<i>properties_value</i>&quot;
 *   &lt;/APPLET&gt;
 * </pre>
 * <p>
 * To run as an Application using the Java interpreter:
 * <p>
 * <pre>
 *   java com.ysystems.ycad.app.ycadv.ycadv <i>command_line_values</i>
 * </pre>
 * <p>
 * Applet <i>properties_value</i> and Application <i>command_line_values</i>:
 * <p>
 * Note: The syntax for the <i>properties_value</i> and the <i>command_line_values</i>
 * are identical. 
 * <p>
 * <pre>
 *   bgcolor=<i>#rrggbb (default #000000)</i>
 *   listheader=<i>boolean (default false)</i>
 *   listlayers=<i>boolean (default false)</i>
 *   listltypes=<i>boolean (default false)</i>
 *   listvports=<i>boolean (default false)</i>
 *   liststyles=<i>boolean (default false)</i>
 *   listblocks=<i>boolean (default false)</i>
 *   listentities=<i>boolean (default false)</i>
 *   drawdspminmax=<i>boolean (default false)</i>
 *   drawextminmax=<i>boolean (default false)</i>
 *   drawlimminmax=<i>boolean (default false)</i>
 *   showtoolbar=<i>boolean (default true)</i>
 * </pre>
 * <p>
 * <h4>DESCRIPTION</h4>
 * All values are optional.
 * <p>
 * <code>bgcolor=#rrggbb</code> where #rrbbgg is a hexadecimal (base 16) red-green-blue triplet used to specify the background color.
 * <p>
 * The <code>list...</code> values will list drawing values on the system console.
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
 *   &lt;APPLET code="com.ysystems.ycad.app.ycadtest.YcadTest.class" width=640 height=480&gt;
 *   &lt;PARAM name=&quot;properties&quot; value=&quot;bgcolor=#0000FF
 *      listlayers=true showtoolbar=false&quot;
 *   &lt;/APPLET&gt;
 * </pre>
 * <p>
 * Running as Application from command line:
 * <pre>
 *   java com.ysystems.ycad.app.ycadtest.YcadTest width=320 height=100
 *      bgcolor=#FF0000
 * </pre>
 *
 * @author Ed Karlo - Y Systems, LLC
 */
public class YcadTest extends Applet
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
    YcadTest()
    {
//d     System.out.println("++YcadTest:APPLET CONSTRUCTOR");
    }

    /**
     * Constructor - Application.
     * @param argv Array of arguments used by main().
     */
    public
    YcadTest(String argv[])
    {
//d     System.out.println("++YcadTest:APPLICATION CONSTRUCTOR");
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

    private
    YcadTestCreateDrawingT      D_builder               = null;
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
    private YxxfDrwView         viewer                  = null;
    //==========================================================================


    //==========================================================================
    /**
     * Called when applet is loaded (before the first time start() is called).
     *<UL>
     *   <LI> Set up drawing
     *   <LI> Create I/O handler and name
     *   <LI> Create Get handler
     *   <LI> Process properties
     *   <LI> Set up viewer
     *   <LI> Start Input
     *   <LI> Start viewer rolling
     *</UL>
     */
    public
    void init()
    {
        System.out.println("++YcadTest:init|BEG");
//d     System.out.println("++YcadTest:init|YcadTest=[" + this + "]");
//d     if (runningAsApplet)
//d     {
//d         System.out.println("++YcadTest:init|runningAsApplet=true");
//d     }
//d     else
//d     {
//d         System.out.println("++YcadTest:init|runningAsApplet=false");
//d     }


        // ============================================================
        // Set up drawing
        // ============================================================

        // Create drawing
        D = new Yxxf();




        // ============================================================
        // Set props_user
        // ============================================================
        processProperties();




        // ============================================================
        // Create view handler
        // Get viewer panel from view handler and add to Applet
        // ============================================================
//d     System.out.println("++YcadTest:init|setting up view");

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
                !keylower.equals("height"))
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

//d     System.out.println("++Ycadv:init|viewer:printState=[" + viewer.printState() + "]");


        //
        // Force press of Start button
        //
        vhandler.commandViewHandler_toolbar_start();




        // ============================================================
        // Create test drawing
        // ============================================================
//d     System.out.println("++YcadTest:init|create start");

        // Start create drawing
//d     System.out.println("++YcadTest:init|BEG createTestDrawing");
        D_builder = new YcadTestCreateDrawingT(D, vhandler);
        D_builder.start();
//d     System.out.println("++YcadTest:init|END createTestDrawing");

        System.out.println("++YcadTest:init|END");
    }


    /**
     * Called after init() and each time the applet becomes visible.
     */
    public
    void start()
    {
//d     System.out.println("++YcadTest:BEG START");
//d     System.out.println("++YcadTest:END START");
    }


    /**
     * Called before destroy() and each time the applet becomes invisible.
     */
    public
    void stop()
    {
//d     System.out.println("++YcadTest:BEG STOP");
//d     System.out.println("++YcadTest:END STOP");
    }


    /**
     * Called when applet is being eliminated.
     */
    public
    void destroy()
    {
//d     System.out.println("++YcadTest:BEG DESTROY");
//d     System.out.println("++YcadTest:END DESTROY");
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
     *  <LI>Set defaults.
     *  <LI>Load properties.
     *  <LI>Set local params.
     *  <LI>All user params examined - do post processing of source names.
     *</UL>
     */
    private
    void processProperties()
    {
        // Set defaults
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
        System.out.println("++YcadTest:processProperties|props_user.e BEG");
        for (Enumeration e = props_user.propertyNames(); e.hasMoreElements(); )
        {
            String key = (String)e.nextElement();
            String val = props_user.getProperty(key);
            System.out.println("++YcadTest:processProperties|key=[" + key + "],val=[" + val + "]");

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
        }
        System.out.println("++YcadTest:processProperties|props_user.e END");
    }
    //==========================================================================




    //==========================================================================
    /**
     * Create test drawing.
     */
    private
    void createTestDrawing(Yxxf D)
    {
        System.out.println("++YcadTest:createTestDrawing|BEG");

        // Set up vport table entry
        /* set up vport
        Vport =====================================
        YxxfTblVport name=[*ACTIVE]
        YxxfTblVport handle=[]
        YxxfTblVport subClassMarker=[]
        YxxfTblVport flags=0
        YxxfTblVport llx=0.0
        YxxfTblVport lly=0.0
        YxxfTblVport urx=1.0
        YxxfTblVport ury=1.0
        YxxfTblVport vcpx=6.347947
        YxxfTblVport vcpy=4.5
        YxxfTblVport sbpx=0.0
        YxxfTblVport sbpy=0.0
        YxxfTblVport ssx=0.125
        YxxfTblVport ssy=0.125
        YxxfTblVport gsx=0.25
        YxxfTblVport gsy=0.25
        YxxfTblVport vdir=YxxfGfxPointW[0.0 0.0 1.0]
        YxxfTblVport vtgt=YxxfGfxPointW[0.0 0.0 0.0]
        YxxfTblVport vheight=9.0
        YxxfTblVport vaspect=1.410655
        YxxfTblVport lensLength=50.0
        YxxfTblVport fcp=0.0
        YxxfTblVport bcp=0.0
        YxxfTblVport snaprotang=0.0
        YxxfTblVport viewtwistang=0.0
        YxxfTblVport status=0
        YxxfTblVport id=0
        YxxfTblVport viewmode=0
        YxxfTblVport circleZoomPercent=100
        YxxfTblVport fastZoomSetting=1
        YxxfTblVport ucsicon=0
        YxxfTblVport snaponoff=1
        YxxfTblVport gridonoff=1
        YxxfTblVport snapStyle=0
        YxxfTblVport snapIsopair=0
        Vport =====================================
        */
        YxxfTblVport new_vport = new YxxfTblVport();

        new_vport.name          = "*ACTIVE";

        new_vport.llx           = 0.0;
        new_vport.lly           = 0.0;
        new_vport.urx           = 1.0;
        new_vport.ury           = 1.0;

        new_vport.vcpx          = 6.347947;
        new_vport.vcpy          = 4.5;

        new_vport.vdir          = new YxxfGfxPointW(0.0, 0.0, 1.0);
        new_vport.vtgt          = new YxxfGfxPointW(0.0, 0.0, 0.0);

        new_vport.vheight       = 9.0;
        new_vport.vaspect       = 1.410655;
        new_vport.lensLength    = 50.0;

        D.secTables.tblVport.addElement(new_vport);




        // Create entities
        /*
        --------------------------------------------------------------------------------
        |    |   0|SOLID
        |    |   5|22
        |    | 100|AcDbEntity
        |    |   8|0
        |    | 100|AcDbTrace
        |    |  10|1.061847
        |    |  20|9.248579
        |    |  30|0.0
        |    |  11|2.258626
        |    |  21|9.248579
        |    |  31|0.0
        |    |  12|2.258626
        |    |  22|8.134292
        |    |  32|0.0
        |    |  13|1.097047
        |    |  23|8.18121
        |    |  33|0.0
        --------------------------------------------------------------------------------
        */

        D.setDrawingReady(true);

        // Set entities list
        YxxfEntBlock blkMSpace = D.secBlocks.findBlock("*MODEL_SPACE");

        genSolid(blkMSpace, YxxfGfxPalette.ACI_RED,
            new YxxfGfxPointW(4.0, 2.5, 0.0),
            new YxxfGfxPointW(8.0, 2.5, 0.0),
            new YxxfGfxPointW(8.0, 6.5, 0.0),
            new YxxfGfxPointW(4.0, 6.5, 0.0) );

        D.setDrawingComplete(true);

        System.out.println("++YcadTest:createTestDrawing|END");
    }
    //==========================================================================




    //==========================================================================
    // Generate ================================================================
    //==========================================================================
    /**
     * TODO Describe method
     * @param blk TODO
     * @param aci TODO
     * @param pmin TODO
     * @param pmax TODO
     */
    public
    void genBox(YxxfEntBlock blk, int aci, YxxfGfxPointW pmin, YxxfGfxPointW pmax)
    {
        // corner points
        YxxfGfxPointW p_xyz         = new YxxfGfxPointW(pmin.x, pmin.y, pmin.z);
        YxxfGfxPointW p_Xyz         = new YxxfGfxPointW(pmax.x, pmin.y, pmin.z);
        YxxfGfxPointW p_XYz         = new YxxfGfxPointW(pmax.x, pmax.y, pmin.z);
        YxxfGfxPointW p_xYz         = new YxxfGfxPointW(pmin.x, pmax.y, pmin.z);

        YxxfGfxPointW p_xyZ         = new YxxfGfxPointW(pmin.x, pmin.y, pmax.z);
        YxxfGfxPointW p_XyZ         = new YxxfGfxPointW(pmax.x, pmin.y, pmax.z);
        YxxfGfxPointW p_XYZ         = new YxxfGfxPointW(pmax.x, pmax.y, pmax.z);
        YxxfGfxPointW p_xYZ         = new YxxfGfxPointW(pmin.x, pmax.y, pmax.z);


        // line segments
        YxxfEntLine l_xyz_Xyz       = new YxxfEntLine(p_xyz, p_Xyz);
        YxxfEntLine l_Xyz_XYz       = new YxxfEntLine(p_Xyz, p_XYz);
        YxxfEntLine l_XYz_xYz       = new YxxfEntLine(p_XYz, p_xYz);
        YxxfEntLine l_xYz_xyz       = new YxxfEntLine(p_xYz, p_xyz);

        YxxfEntLine l_xyz_xyZ       = new YxxfEntLine(p_xyz, p_xyZ);
        YxxfEntLine l_Xyz_XyZ       = new YxxfEntLine(p_Xyz, p_XyZ);
        YxxfEntLine l_XYz_XYZ       = new YxxfEntLine(p_XYz, p_XYZ);
        YxxfEntLine l_xYz_xYZ       = new YxxfEntLine(p_xYz, p_xYZ);

        YxxfEntLine l_xyZ_XyZ       = new YxxfEntLine(p_xyZ, p_XyZ);
        YxxfEntLine l_XyZ_XYZ       = new YxxfEntLine(p_XyZ, p_XYZ);
        YxxfEntLine l_XYZ_xYZ       = new YxxfEntLine(p_XYZ, p_xYZ);
        YxxfEntLine l_xYZ_xyZ       = new YxxfEntLine(p_xYZ, p_xyZ);


        // set color
        l_xyz_Xyz.hdr_aci = aci;
        l_Xyz_XYz.hdr_aci = aci;
        l_XYz_xYz.hdr_aci = aci;
        l_xYz_xyz.hdr_aci = aci;

        l_xyz_xyZ.hdr_aci = aci;
        l_Xyz_XyZ.hdr_aci = aci;
        l_XYz_XYZ.hdr_aci = aci;
        l_xYz_xYZ.hdr_aci = aci;

        l_xyZ_XyZ.hdr_aci = aci;
        l_XyZ_XYZ.hdr_aci = aci;
        l_XYZ_xYZ.hdr_aci = aci;
        l_xYZ_xyZ.hdr_aci = aci;


        // add to block list
        l_xyz_Xyz.calc(D); blk.addEntity(l_xyz_Xyz);
        l_Xyz_XYz.calc(D); blk.addEntity(l_Xyz_XYz);
        l_XYz_xYz.calc(D); blk.addEntity(l_XYz_xYz);
        l_xYz_xyz.calc(D); blk.addEntity(l_xYz_xyz);

        l_xyz_xyZ.calc(D); blk.addEntity(l_xyz_xyZ);
        l_Xyz_XyZ.calc(D); blk.addEntity(l_Xyz_XyZ);
        l_XYz_XYZ.calc(D); blk.addEntity(l_XYz_XYZ);
        l_xYz_xYZ.calc(D); blk.addEntity(l_xYz_xYZ);

        l_xyZ_XyZ.calc(D); blk.addEntity(l_xyZ_XyZ);
        l_XyZ_XYZ.calc(D); blk.addEntity(l_XyZ_XYZ);
        l_XYZ_xYZ.calc(D); blk.addEntity(l_XYZ_xYZ);
        l_xYZ_xyZ.calc(D); blk.addEntity(l_xYZ_xyZ);
    }


    /**
     * TODO Describe method.
     * @param blk TODO
     * @param aci TODO
     * @param pnt TODO
     */
    public
    void genPoint(YxxfEntBlock blk, int aci, YxxfGfxPointW pnt)
    {
        // point entity
        YxxfEntPoint new_point = new YxxfEntPoint();

        // set location
        new_point.pnt.set(pnt);

        // set color
        new_point.hdr_aci = aci;

        // add to block list
        new_point.calc(D);
        blk.addEntity(new_point);
    }


    /**
     * TODO Describe method.
     * @param blk TODO
     * @param aci TODO
     * @param pnt TODO
     */
    public
    void genSolid(YxxfEntBlock blk, int aci, YxxfGfxPointW pnt1,
                                             YxxfGfxPointW pnt2,
                                             YxxfGfxPointW pnt3,
                                             YxxfGfxPointW pnt4)
    {
        //solid entity
        YxxfEntSolid new_solid = new YxxfEntSolid();

        // set location
        new_solid.pnt1.set(pnt1);
        new_solid.pnt3.set(pnt2);
        new_solid.pnt4.set(pnt3);
        new_solid.pnt2.set(pnt4);

        // set color
        new_solid.hdr_aci = aci;

        // add to block list
        new_solid.calc(D);
        blk.addEntity(new_solid);
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
    public static
    void main(String argv[])
    {
        // Instantiate the Applet (processes argv)
        YcadTest vapplet = new YcadTest(argv);


        // Create a Frame for the Applet
        YcadTestFrame vframe = new YcadTestFrame("YcadTest", vapplet);

        // Add the Applet to the Frame (Frame's use BorderLayout)
        vframe.add("Center", vapplet);


        // Init and start the Applet
        // TODO: bail if init errors
        vapplet.init();

        // Resize the Frame according to user parameters
//d     System.out.println("++YcadTest:w=" + vapplet.getParamWidth() + ",h=" + vapplet.getParamHeight());
        vframe.resize(vapplet.getParamWidth(), vapplet.getParamHeight()); // calls Component.resize(int width, int height)

        // Show and start the Applet
        vframe.show();
        vapplet.start();
    }
    //==========================================================================
}




/**
 * Extend Frame to handle the window-close event.
 */
class YcadTestFrame extends Frame
{
    /**
     * An applet.
     */
    YcadTest vapplet;

    public YcadTestFrame(String s, YcadTest vapplet)
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
//d     System.out.println("++YcadTestFrame:handleEvent" + e);
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
