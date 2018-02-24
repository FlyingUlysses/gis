//==============================================================================
// YcadTestCreateDrawing.java
//
// Create test drawing entities
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/app/ycadtest/YcadTestCreateDrawingT.java,v 1.4 2003/05/08 08:46:24 ekarlo Exp $
// $Log: YcadTestCreateDrawingT.java,v $
// Revision 1.4  2003/05/08 08:46:24  ekarlo
// Remove warnings.
//
// Revision 1.3  2003/04/14 12:37:26  ekarlo
// Update source file header for OSI release.
//
// Revision 1.2  2001/10/18 05:16:42  ekarlo
// Change package paths to lower case.
//
// Revision 1.1  1999-10-21 23:08:35-06  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.app.ycadtest;


import java.util.*;

import com.ysystems.lib.yutil.*;
import com.ysystems.ycad.lib.yxxf.*;


/**
 * Create test drawing entities.
 *
 * @author Ed Karlo - Y Systems, LLC
 */
public class YcadTestCreateDrawingT extends Thread
{
    //==========================================================================
    // Properties

    /** 
     * All user supplied properties.
     */
    private YutilProperties     props_user              = new YutilProperties();
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
     * View Handler.
     */
    public
    YxxfDrwViewHandler          vhandler                = null;
    //==========================================================================


    //==========================================================================
    // Constructors

    /**
     * Constructor.
     */
    public
    YcadTestCreateDrawingT()
    {
    }


    /**
     * Constructor.
     * @param D Drawing reference.
     */
    public
    YcadTestCreateDrawingT(Yxxf D, YxxfDrwViewHandler vhandler)
    {
        this.D = D;
        this.vhandler = vhandler;
    }
    //==========================================================================


    /**
     * Create test drawing.
     */
    public
    void run()
    {
        System.out.println("++YcadTestCreateDrawingT:create|BEG");

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

        // Set entities list
        YxxfEntBlock blkMSpace = D.secBlocks.findBlock("*MODEL_SPACE");
        gen3(blkMSpace);

        System.out.println("++YcadTestCreateDrawingT:create|END");
    }




    public
    void gen1(YxxfEntBlock blk)
    {
        D.setDrawingReady(true);
        vhandler.waitDrawingViewReady();

        genSolid(blk, YxxfGfxPalette.ACI_RED,
            new YxxfGfxPointW(4.0, 2.5, 0.0),
            new YxxfGfxPointW(8.0, 2.5, 0.0),
            new YxxfGfxPointW(8.0, 6.5, 0.0),
            new YxxfGfxPointW(4.0, 6.5, 0.0) );

        D.setDrawingComplete(true);

//      D.waitDrawingComplete();

        try { Thread.sleep(1000); } catch(InterruptedException e) {}
        vhandler.commandViewHandler_toolbar_zoom_to_calc_extents();
        try { Thread.sleep(1000); } catch(InterruptedException e) {}
        vhandler.commandViewHandler_toolbar_zoom_out();
        try { Thread.sleep(1000); } catch(InterruptedException e) {}
        vhandler.commandViewHandler_toolbar_pan_l();
        try { Thread.sleep(1000); } catch(InterruptedException e) {}
        vhandler.commandViewHandler_toolbar_pan_l();
        try { Thread.sleep(1000); } catch(InterruptedException e) {}
        vhandler.commandViewHandler_toolbar_pan_l();
    }


    public
    void gen2(YxxfEntBlock blk)
    {
        double xctr, yctr;
        double xsiz, ysiz;
        double eclr;

        Random rand = new Random();

        D.setDrawingReady(true);
        vhandler.waitDrawingViewReady();

        for (int i = 0; i < 10000; i++)
        {
            xctr = rand.nextDouble() * 9.0 * 1.410655;
            yctr = rand.nextDouble() * 9.0;

            xsiz = rand.nextDouble() * 0.4;
            ysiz = rand.nextDouble() * 0.4;

            eclr = rand.nextDouble() * 256.0;

            genSolid(blk, (int)eclr,
                new YxxfGfxPointW(xctr - xsiz, yctr - ysiz, 0.0),
                new YxxfGfxPointW(xctr + xsiz, yctr - ysiz, 0.0),
                new YxxfGfxPointW(xctr + xsiz, yctr + ysiz, 0.0),
                new YxxfGfxPointW(xctr - xsiz, yctr + ysiz, 0.0) );
        }

        D.setDrawingComplete(true);
    }




    public
    void gen3(YxxfEntBlock blk)
    {
        double xmin, ymin, zmin;
        double xsiz, ysiz, zsiz;
        double eclr;

        Random rand = new Random();

        D.setDrawingReady(true);
        vhandler.waitDrawingViewReady();

//      try { Thread.sleep(300); } catch(InterruptedException e) {}

        for (int i = 0; i < 200; i++)
        {
            xmin = rand.nextDouble() * 12.0;
            ymin = rand.nextDouble() * 9.0;
            zmin = rand.nextDouble() * 9.0;

            xsiz = rand.nextDouble() * 1.0;
            ysiz = rand.nextDouble() * 1.0;
            zsiz = rand.nextDouble() * 1.0;

            eclr = rand.nextDouble() * 256.0;

            genBox(blk, (int)eclr,
                new YxxfGfxPointW(xmin,        ymin,        zmin       ),
                new YxxfGfxPointW(xmin + xsiz, ymin + ysiz, zmin + zsiz) );
        }

        D.setDrawingComplete(true);

        vhandler.commandViewHandler_toolbar_rotate_x_value(Math.PI / 180.0 * 30.0);
        try { Thread.sleep(300); } catch(InterruptedException e) {}
        vhandler.commandViewHandler_toolbar_rotate_y_value(Math.PI / 180.0 * 30.0);
        try { Thread.sleep(300); } catch(InterruptedException e) {}

        vhandler.commandViewHandler_toolbar_pan_r();
        try { Thread.sleep(300); } catch(InterruptedException e) {}
        vhandler.commandViewHandler_toolbar_pan_r();
        try { Thread.sleep(300); } catch(InterruptedException e) {}

        vhandler.commandViewHandler_toolbar_pan_d();
        try { Thread.sleep(300); } catch(InterruptedException e) {}
        vhandler.commandViewHandler_toolbar_pan_d();
        try { Thread.sleep(300); } catch(InterruptedException e) {}
        vhandler.commandViewHandler_toolbar_pan_d();
        try { Thread.sleep(300); } catch(InterruptedException e) {}

        for (int j = 0; j < 20; j++)
        {
            vhandler.commandViewHandler_toolbar_zoom_out();
            try { Thread.sleep(80); } catch(InterruptedException e) {}
        }

        for (int j = 0; j < 60; j++)
        {
            vhandler.commandViewHandler_toolbar_zoom_in();
            try { Thread.sleep(55); } catch(InterruptedException e) {}
        }

        for (int j = 0; j < 100; j++)
        {
            vhandler.commandViewHandler_toolbar_zoom_out();
            try { Thread.sleep(20); } catch(InterruptedException e) {}
        }

        for (int j = 0; j < 43; j++)
        {
            vhandler.commandViewHandler_toolbar_zoom_in();
            try { Thread.sleep(55); } catch(InterruptedException e) {}
        }
    }




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
}
