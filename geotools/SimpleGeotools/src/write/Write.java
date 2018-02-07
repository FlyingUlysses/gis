package write;

import java.io.File;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.geotools.data.FeatureWriter;
import org.geotools.data.Transaction;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.data.shapefile.files.ShpFiles;
import org.geotools.data.shapefile.shp.ShapefileReader;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.geometry.iso.topograph2D.Coordinate;
import org.geotools.referencing.crs.DefaultGeographicCRS;

import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.geometry.primitive.Point;

import com.vividsolutions.jts.geom.CoordinateSequence;
import com.vividsolutions.jts.geom.GeometryFactory;

public class Write {
    public static void main(String[] args) {
        try {  
            //创建shape文件对象  
            File file = new File("‪‪C:\\Users\\k\\Desktop\\shape\\CHN_adm1.shp");  
            Map<String, Serializable> params = new HashMap<String, Serializable>();  
            params.put( ShapefileDataStoreFactory.URLP.key, file.toURI().toURL() );  
            ShapefileDataStore ds = (ShapefileDataStore) new ShapefileDataStoreFactory().createNewDataStore(params);  
            //定义图形信息和属性信息  
            SimpleFeatureTypeBuilder tb = new SimpleFeatureTypeBuilder();  
            tb.setCRS(DefaultGeographicCRS.WGS84);  
            tb.setName("shapefile");  
            tb.add("the_geom", Point.class);  
            tb.add("ID_0", Long.class);  
            tb.add("ISO", String.class);  
            tb.add("NAME_0", String.class);  
            tb.add("ID_1", Long.class);  
            tb.add("NAME_1", String.class);  
            tb.add("VARNAME_1", String.class);  
            tb.add("NL_NAME_1", String.class);  
            tb.add("HASC_1", String.class);  
            tb.add("CC_1", String.class);  
            tb.add("TYPE_1", String.class);  
            tb.add("ENGTYPE_1", String.class);  
            tb.add("VALIDFR_1", String.class);  
            tb.add("VALIDTO_1", String.class);  
            tb.add("REMARKS_1", String.class);  
            tb.add("Shape_Leng", Double.class);  
            tb.add("Shape_Area", Double.class);  
            ds.createSchema(tb.buildFeatureType());  
            ds.setCharset(Charset.forName("GBK"));  
            //设置Writer  
            FeatureWriter<SimpleFeatureType, SimpleFeature> writer = ds.getFeatureWriter(ds.getTypeNames()[0], Transaction.AUTO_COMMIT);  
            //写下一条  
            SimpleFeature feature = writer.next();  
            feature.setAttribute("the_geom", new GeometryFactory().createPoint((CoordinateSequence) new Coordinate(116.456, 39.678)));  
            feature.setAttribute("POIID", 1234567891l);  
            feature.setAttribute("NAME_0", "某兴趣点1");  
            writer.write();  
            writer.close();  
            ds.dispose();  
              
            //读取刚写完shape文件的图形信息  
            ShpFiles shpFiles = new ShpFiles("‪‪C:\\Users\\k\\Desktop\\shape\\CHN_adm1.shp");  
            ShapefileReader reader = new ShapefileReader(shpFiles, false, true, new GeometryFactory(), false);  
            try {  
                while (reader.hasNext()) {  
                    System.out.println(reader.nextRecord().shape());      
                }  
            } finally {  
                reader.close();  
            }  
        } catch (Exception e) { e.printStackTrace(); }  
    }
}
