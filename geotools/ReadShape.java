import net.sf.json.JSONArray;  
import org.geotools.data.shapefile.ShapefileDataStore;  
import org.geotools.data.simple.SimpleFeatureCollection;  
import org.geotools.data.simple.SimpleFeatureIterator;  
import org.geotools.data.simple.SimpleFeatureSource;  
import org.opengis.feature.Property;  
import org.opengis.feature.simple.SimpleFeature;  
  
import java.io.File;  
import java.nio.charset.Charset;  
import java.util.*; 

public class ReadShape {
    public static void main(String[] args) {
        String shpPath = "C:\\Users\\k\\Desktop\\shape\\CHN_adm1.shp";  
        System.out.println(shpPath);  
        ShapefileDataStore shpDataStore = null;  
        Calendar startTime = Calendar.getInstance();  
        List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();  
        try{  
            File file = new File (shpPath);  
            shpDataStore = new ShapefileDataStore(file.toURL());  
            //设置字符编码  
            Charset charset = Charset.forName("GBK");  
            shpDataStore.setCharset(charset);  
            String typeName = shpDataStore.getTypeNames()[0];  
            SimpleFeatureSource featureSource = null;  
            featureSource =  shpDataStore.getFeatureSource (typeName);  
            SimpleFeatureCollection result = featureSource.getFeatures();  
            SimpleFeatureIterator itertor = result.features();  
            while (itertor.hasNext())  
            {  
                Map<String,Object> data  = new HashMap<String, Object>();  
                SimpleFeature feature = itertor.next();  
                Collection<Property> p = feature.getProperties();  
                Iterator<Property> it = p.iterator();  
                while(it.hasNext()) {  
                    Property pro = it.next();  
                    String field = pro.getName().toString();  
                    String value = pro.getValue().toString();  
                    field = field.equals("the_geom")?"wkt":field;  
                    data.put(field, value);  
                }  
                list.add(data);  
            }  
            Calendar endTime = Calendar.getInstance();  
            int day = endTime.get(Calendar.DAY_OF_MONTH) - startTime.get(Calendar.DAY_OF_MONTH);  
            int hour = endTime.get(Calendar.HOUR_OF_DAY) - startTime.get(Calendar.HOUR_OF_DAY);  
            int minute = endTime.get(Calendar.MINUTE) - startTime.get(Calendar.MINUTE);  
            int second = endTime.get(Calendar.SECOND) - startTime.get(Calendar.SECOND);  
            itertor.close();  
            System.out.println("共写入" + list.size() + "条数据，耗时" + day + "天" + hour + "时" + minute + "分" + second + "秒");  
            //JSONArray jsonarray = JSONArray.fromObject(list);  
        }  
        catch(Exception e){  
            System.out.println(e.getMessage());  
        }  
    }
}
