package writeShape;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.UIManager;

import org.geotools.data.DataUtilities;
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

/** 
*这个例子从a中读取点位置和相关属性的数据
*逗号分隔文本（CSV）文件并将其导出为新的shapefile文件。它演示了如何构建一个特征类型。
* 
注意：为了保持简单，下面的代码输入文件不应该有额外的空间或选项卡之间的字段。
*/ 
public class WriterForCSV {

    public static void main(String[] args) throws Exception {
        // 设置跨平台的外观和兼容
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

        File file = JFileDataStoreChooser.showOpenFile("csv", null);
        if (file == null) {
            return;
        }
        /* 
        *我们使用DataUtilities类创建一个FeatureType，它将描述我们的
        * shapefile中的数据。        
        * 另请参阅下面的createFeatureType方法，以获得另一种更灵活的方法。
        */ 
        final SimpleFeatureType TYPE = createFeatureType();
        System.out.println("TYPE:"+TYPE);
        /* 
        *在我们创建它们时收集功能的列表。
        */ 
        List<SimpleFeature> features = new ArrayList<>();
        
        /* 
        * GeometryFactory将用于创建每个要素的几何属性，
        *使用Point对象作为位置。
        */ 
        GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();

        SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(TYPE);

        try (BufferedReader reader = new BufferedReader(new FileReader(file)) ){
            /*数据文件的第一行是头文件*/ 
            String line = reader.readLine();
            System.out.println("Header: " + line);
            Point point = geometryFactory.createPoint(new Coordinate(116.46, 39.92));

            featureBuilder.add(point);
            featureBuilder.add("test");
            featureBuilder.add(22);
            SimpleFeature feature = featureBuilder.buildFeature(null);
            features.add(feature);

//            for (line = reader.readLine(); line != null; line = reader.readLine()) {
//                if (line.trim().length() > 0) { //跳过空行
//                    String tokens[] = line.split("\\,");
//
//                    double latitude = Double.parseDouble(tokens[0]);
//                    double longitude = Double.parseDouble(tokens[1]);
//                    String name = tokens[2].trim();
//                    int number = Integer.parseInt(tokens[3].trim());
//
//                    /*经度（= x坐标）先！*/ 
//                    Point point = geometryFactory.createPoint(new Coordinate(longitude, latitude));
//
//                    featureBuilder.add(point);
//                    featureBuilder.add(name);
//                    featureBuilder.add(number);
//                    SimpleFeature feature = featureBuilder.buildFeature(null);
//                    features.add(feature);
//                }
//            }
        }
        /* 
        *获取输出文件名并创建新的shapefile 
        */ 
        File newFile = getNewShapeFile(file);

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
            System.exit(0); // success!
        } else {
            System.out.println(typeName + " does not support read/write access");
            System.exit(1);
        }
    }
    
    /** 
    *提示用户输出shapefile的名称和路径
    * 
    * @param csvFile 
    *用于创建默认shapefile名称的输入csv文件
    * 
    * @将shapefile的名称和路径返回为新的File对象
    */ 
    private static File getNewShapeFile(File csvFile) {
        String path = csvFile.getAbsolutePath();
        String newPath = path.substring(0, path.length() - 4) + ".shp";

        JFileDataStoreChooser chooser = new JFileDataStoreChooser("shp");
        chooser.setDialogTitle("Save shapefile");
        chooser.setSelectedFile(new File(newPath));

        int returnVal = chooser.showSaveDialog(null);

        if (returnVal != JFileDataStoreChooser.APPROVE_OPTION) {
            //用户取消了对话框
            System.exit(0);
        }

        File newFile = chooser.getSelectedFile();
        if (newFile.equals(csvFile)) {
            System.out.println("Error: cannot replace " + csvFile);
            System.exit(0);
        }

        return newFile;
    }
    
    /** 
    *以下是如何使用SimpleFeatureType构建器为shapefile 
    *动态创建模式。    
    * <p> 
    *此方法是对上面主要方法（我们使用DataUtilities.createFeatureType）中使用的代码的改进，因为我们可以为    
    * FeatureType 设置坐标参考系统，为'name'设置最大字段长度，字段dddd      */ 
    private static SimpleFeatureType createFeatureType() {

        SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();
        builder.setName("Location");
        builder.setCRS(DefaultGeographicCRS.WGS84); // <-坐标参考系统

        // 往订单中填加属性
        builder.add("the_geom", Point.class);
        builder.length(15).add("Name", String.class); // <- 15名称字段
        builder.add("number",Integer.class);
        
        // 构建类型
        final SimpleFeatureType LOCATION = builder.buildFeatureType();

        return LOCATION;
    }
    

}