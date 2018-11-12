package org.cola.util.geo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
 
public class GeoUtilTest {
    private Point[] points = null;
    @Before
    public void setup(){
        Point[] points = new Point[]{
                                     new Point(120.09548709, 30.26973828),
                                     new Point(120.09549138774209, 30.269738408839036),
                                     new Point(120.09443281, 30.19282078),
                                     new Point(120.19974387, 30.28998107),
                                     new Point(120.19109, 30.28086),
                                }; 
        this.points =points;
    }
    
    @Test
    public void testEartchAndMarsCoordConverter() {
        for(Point src : points){
            Point target = GeoUtil.convertEarth2Mars(src);
            Point src_ = GeoUtil.convertMars2Earth(target);
            
            double wucha = GeoUtil.getDistance(src.getLat(), src.getLon(), src_.getLat(), src_.getLon());
            System.out.println("testEartchAndMarsCoordConverter ���="+wucha);
            Assert.assertTrue(wucha<1.00);
        }
    }
    
    @Test
    public void testEarthAndBaiduCoordConverter() {
        for(Point src : points){
            Point target = GeoUtil.convertEarth2Baidu(src);
            Point src_ = GeoUtil.convertBaidu2Earth(target);
            
            double wucha = GeoUtil.getDistance(src.getLat(), src.getLon(), src_.getLat(), src_.getLon());
            System.out.println("testEarthAndBaiduCoordConverter ���="+wucha);
            Assert.assertTrue(wucha<1.00);
        }
    }
    
    @Test
    public void testMarsAndBaiduCoordConverter() {
        for(Point src : points){
            Point target = GeoUtil.convertMars2Baidu(src);
            Point src_ = GeoUtil.convertBaidu2Mars(target);
            double wucha = GeoUtil.getDistance(src.getLat(), src.getLon(), src_.getLat(), src_.getLon());
            System.out.println("testMarsAndBaiduCoordConverter ���="+wucha);
            Assert.assertTrue(wucha<1.00);
        }
    }
    
    @Test
    public void testConvertCoord(){
        for(Point src : points){
            Point target = GeoUtil.convertCoord(src, CoordType.MARS, CoordType.BAIDU);
            Point src_ = GeoUtil.convertCoord(target, CoordType.BAIDU, CoordType.MARS);
            double wucha = GeoUtil.getDistance(src.getLat(), src.getLon(), src_.getLat(), src_.getLon());
            System.out.println(CoordType.MARS+" & "+CoordType.BAIDU+"���="+wucha);
            Assert.assertTrue(wucha<1.00);
        }
        
        for(Point src : points){
            Point target = GeoUtil.convertCoord(src, CoordType.MARS, CoordType.EARTH);
            Point src_ = GeoUtil.convertCoord(target, CoordType.EARTH, CoordType.MARS);
            double wucha = GeoUtil.getDistance(src.getLat(), src.getLon(), src_.getLat(), src_.getLon());
            System.out.println(CoordType.EARTH+" & "+CoordType.MARS+"���="+wucha);
            Assert.assertTrue(wucha<1.00);
        }
        
        for(Point src : points){
            Point target = GeoUtil.convertCoord(src, CoordType.EARTH, CoordType.BAIDU);
            Point src_ = GeoUtil.convertCoord(target, CoordType.BAIDU, CoordType.EARTH);
            double wucha = GeoUtil.getDistance(src.getLat(), src.getLon(), src_.getLat(), src_.getLon());
            System.out.println(CoordType.EARTH+" & "+CoordType.BAIDU +"���="+wucha);
            Assert.assertTrue(wucha<1.00);
        }
    }
    
    @Test
    public void testMapbar2Earth(){
        Point src = new Point(108.98258,34.27071);
        Point expected = new Point(108.98525, 34.27116);
        
        Point target = GeoUtil.convertCoord(src, CoordType.MAPBAR, CoordType.EARTH);
        
        double wucha = GeoUtil.getDistance(expected.getLat(), expected.getLon(), target.getLat(), target.getLon());
        System.out.println("testMapbar2Earth���="+wucha);
        Assert.assertTrue(wucha<1.00);
    }
    
    @Test
    public void testMapbar2Mars(){
        Point src = new Point(108.98258,34.27071);
        Point target = GeoUtil.convertCoord(src, CoordType.MAPBAR, CoordType.MARS);
        
        Point expectedMars = new Point(108.99006153253407,34.26968285889569);
        
        double wucha = GeoUtil.getDistance(expectedMars.getLat(), expectedMars.getLon(), target.getLat(), target.getLon());
        System.out.println("MAPBAR���="+wucha);
        Assert.assertTrue(wucha<1.00);
    }
    
    
    @Test
    public void testMapbar2Earth2Mars(){
//        Point expected = new Point(108.98525, 34.27116);
//        
//        double[] points = CoordinateConverter.mapBar2WGS84(src.getLon(),src.getLat());//[108.98525, 34.27116]108.9842,34.26609 108.9842,34.26609
//        System.out.println("wgs="+points[0]+","+points[1]);
//        
//        double wucha = GeoUtil.getDistance(points[0], points[1], expected.getLat(), expected.getLon());
//        System.out.println("MAPBAR���="+wucha);
//        Assert.assertTrue(wucha<1.00);
//        
//        Point mars = GeoUtil.convertCoord(new Point(points[1],points[0]), CoordType.EARTH, CoordType.MARS);
//        
//        System.out.println("mars="+mars.getLon()+","+mars.getLat());
//        
//        Point expectedMars = new Point(108.99006153253407,34.26968285889569);
//        
//        wucha = GeoUtil.getDistance(expectedMars.getLat(), expectedMars.getLon(), mars.getLat(), mars.getLon());
//        System.out.println("MAPBAR���="+wucha);
//        Assert.assertTrue(wucha<1.00);
        
    }

}
