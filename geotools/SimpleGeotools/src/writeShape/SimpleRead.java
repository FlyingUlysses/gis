package writeShape;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.geotools.data.FeatureWriter;
import org.geotools.data.Transaction;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.feature.Property;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.feature.type.Name;
import org.opengis.feature.type.PropertyDescriptor;
import org.opengis.feature.type.PropertyType;
import org.opengis.geometry.primitive.Point;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;

public class SimpleRead {
    @SuppressWarnings("unused")
    public static void main(String[] args) throws IOException {  
      //创建shape文件对象  
        File file = new File("C:\\Users\\k\\Desktop\\shape\\CHN_adm3.shp");  
        Map<String, Serializable> params = new HashMap<String, Serializable>();  
        params.put( ShapefileDataStoreFactory.URLP.key, file.toURI().toURL() );  
        ShapefileDataStore ds = (ShapefileDataStore) new ShapefileDataStoreFactory().createNewDataStore(params);  
        //定义图形信息和属性信息  
        SimpleFeatureTypeBuilder tb = new SimpleFeatureTypeBuilder();  
        tb.setCRS(DefaultGeographicCRS.WGS84);  
        tb.setName("shapefile");  
        tb.add("the_geom", Point.class);  
        tb.add("POIID", Long.class);  
        tb.add("NAME", String.class);  
        SimpleFeatureType type = tb.buildFeatureType();
        ds.createSchema(tb.buildFeatureType());  
        ds.setCharset(Charset.forName("GBK"));  
        //设置Writer  
        FeatureWriter<SimpleFeatureType, SimpleFeature> writer = ds.getFeatureWriter(ds.getTypeNames()[0], Transaction.AUTO_COMMIT);  
        //写下一条  
        SimpleFeature feature = writer.next();  
        feature.setAttribute("the_geom", new GeometryFactory().createPoint(new Coordinate(116.123, 39.345)));  
        feature.setAttribute("POIID", 1234567890l);  
        feature.setAttribute("NAME", "某兴趣点1");  
        feature = writer.next();  
        feature.setAttribute("the_geom", new GeometryFactory().createPoint(new Coordinate(116.456, 39.678)));  
        feature.setAttribute("POIID", 1234567891l);  
        feature.setAttribute("NAME", "某兴趣点2");  
        writer.write();  
        writer.close();  
        ds.dispose(); 
    }  
}
