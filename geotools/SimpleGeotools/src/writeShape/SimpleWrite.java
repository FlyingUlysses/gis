package writeShape;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.geotools.data.DefaultTransaction;
import org.geotools.data.Transaction;
import org.geotools.data.collection.ListFeatureCollection;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.data.simple.SimpleFeatureStore;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.geotools.swing.data.JFileDataStoreChooser;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

public class SimpleWrite {
    
    public static void main(String[] args) throws IOException {
        SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();
        builder.setName("Location");
        builder.setCRS(DefaultGeographicCRS.WGS84); // <-坐标参考系统

        // 往订单中填加属性
        builder.add("the_geom", Point.class);
        builder.length(15).add("Name", String.class); // <- 15名称字段
        builder.add("number",Integer.class);
        
        // 构建类型
        final SimpleFeatureType TYPE = builder.buildFeatureType();
        
        List<SimpleFeature> features = new ArrayList<>();
        
        /* 
         * GeometryFactory将用于创建每个要素的几何属性，
         *使用Point对象作为位置。
         */ 
         GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();

         SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(TYPE);
        
        Point point = geometryFactory.createPoint(new Coordinate(116.46, 39.92));
        featureBuilder.add(point);
        featureBuilder.add("test");
        featureBuilder.add(22);
        SimpleFeature feature = featureBuilder.buildFeature(null);
        features.add(feature);
        
        /* 
         *获取输出文件名并创建新的shapefile 
         */ 
        String path = "D://test//";
        String newPath =path+ "11.shp";
        JFileDataStoreChooser chooser = new JFileDataStoreChooser("shp");
        chooser.setDialogTitle("Save shapefile");
        chooser.setSelectedFile(new File(newPath));
        File newFile = chooser.getSelectedFile();
        
        ShapefileDataStoreFactory dataStoreFactory = new ShapefileDataStoreFactory();
        Map<String, Serializable> params = new HashMap<>();
        params.put("url", newFile.toURI().toURL());
        params.put("create spatial index", Boolean.TRUE);
        ShapefileDataStore newDataStore = (ShapefileDataStore) dataStoreFactory.createNewDataStore(params);
        /* 
         * TYPE用作模板来描述文件内容
         */ 
         newDataStore.createSchema(TYPE);
         /*
          * 将特征写入shapefile 
          */
         Transaction transaction = new DefaultTransaction("create");

         String typeName = newDataStore.getTypeNames()[0];
         SimpleFeatureSource featureSource = newDataStore.getFeatureSource(typeName);
         SimpleFeatureType SHAPE_TYPE = featureSource.getSchema();
         
         /* 
          * Shapefile格式有一些限制：
          *  - “the_geom”始终是第一个，用于几何属性名称
          *  - “the_geom”必须是Point，MultiPoint，MuiltiLineString，MultiPolygon类型
          *  - 属性名称的长度是有限的
          *  - 不支持所有数据类型Timestamp表示为Date）
          * 
          *每个数据存储都有不同的限制，因此请检查生成的SimpleFeatureType。
          */ 
          System.out.println("SHAPE:"+SHAPE_TYPE);
          
          if (featureSource instanceof SimpleFeatureStore) {
              SimpleFeatureStore featureStore = (SimpleFeatureStore) featureSource;
              /* 
              * SimpleFeatureStore有一个方法来添加来自
              * SimpleFeatureCollection对象的特征，所以我们使用ListFeatureCollection 
              *类来包装我们的特征列表。
              */ 
              SimpleFeatureCollection collection = new ListFeatureCollection(TYPE, features);
              featureStore.setTransaction(transaction);
              try {
                  featureStore.addFeatures(collection);
                  transaction.commit();
              } catch (Exception problem) {
                  problem.printStackTrace();
                  transaction.rollback();
              } finally {
                  transaction.close();
              }
          }
    }
}
