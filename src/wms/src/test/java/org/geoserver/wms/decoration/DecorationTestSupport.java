package org.geoserver.wms.decoration;

/* Copyright (c) 2013 OpenPlans - www.openplans.org. All rights reserved.
 * This code is licensed under the GPL 2.0 license, available at the root
 * application directory.
 */
import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.util.HashMap;

import org.geoserver.wms.GetMapRequest;
import org.geoserver.wms.WMSMapContent;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.referencing.crs.DefaultGeographicCRS;

import com.vividsolutions.jts.geom.Envelope;

public class DecorationTestSupport {

    protected WMSMapContent createMapContent(double dpi) {
        GetMapRequest request = new GetMapRequest();
        request.setWidth(1000);
        request.setHeight(1000);
        request.setRawKvp(new HashMap<String, String>());
    
        if (dpi > 0) {
            request.getFormatOptions().put("dpi", dpi);
        }
    
        WMSMapContent map = new WMSMapContent(request);
        map.getViewport().setBounds(
                new ReferencedEnvelope(new Envelope(0, 0.01, 0, 0.01),
                        DefaultGeographicCRS.WGS84));
        return map;
    }
    
    /**
     * Checks the pixel i/j has the specified color
     * @param image
     * @param i
     * @param j
     * @param color
     */
    protected void assertPixel(BufferedImage image, int i, int j, Color color) {
        Color actual = getPixelColor(image, i, j);
        

        assertEquals(color, actual);
    }

    /**
     * Gets a specific pixel color from the specified buffered image
     * @param image
     * @param i
     * @param j
     * @param color
     * @return
     */
    protected Color getPixelColor(BufferedImage image, int i, int j) {
        ColorModel cm = image.getColorModel();
        Raster raster = image.getRaster();
        Object pixel = raster.getDataElements(i, j, null);
        
        Color actual;
        if(cm.hasAlpha()) {
            actual = new Color(cm.getRed(pixel), cm.getGreen(pixel), cm.getBlue(pixel), cm.getAlpha(pixel));
        } else {
            actual = new Color(cm.getRed(pixel), cm.getGreen(pixel), cm.getBlue(pixel), 255);
        }
        return actual;
    }
}