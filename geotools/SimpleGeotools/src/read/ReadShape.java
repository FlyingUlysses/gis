package read;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;

import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.opengis.feature.Property;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.type.Name;
import org.opengis.feature.type.PropertyDescriptor;
import org.opengis.feature.type.PropertyType;

public class ReadShape {
    @SuppressWarnings("unused")
    public static void main(String[] args) {  
        ShapefileDataStoreFactory dataStoreFactory = new ShapefileDataStoreFactory();  
        try {  
            ShapefileDataStore sds = (ShapefileDataStore)dataStoreFactory.createDataStore(new File("C:\\Users\\k\\Desktop\\shape\\CHN_adm1.shp").toURI().toURL());  
            sds.setCharset(Charset.forName("GBK"));  
            SimpleFeatureSource featureSource = sds.getFeatureSource();  
            SimpleFeatureIterator itertor = featureSource.getFeatures().features();  
  
            while(itertor.hasNext()) {    
                SimpleFeature feature = itertor.next();    
                Iterator<Property> it = feature.getProperties().iterator();  
  
                while(it.hasNext()) {    
                    Property pro = it.next();  
                    PropertyType type = pro.getType();
                    Name name = pro.getName();
                    PropertyDescriptor descriptor = pro.getDescriptor();
                    Map<Object, Object> userData = pro.getUserData();
                    Object value = pro.getValue();
                    int i =1;//断点测试使用
                    }  
                }    
                itertor.close();    
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
}
