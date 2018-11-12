package org.cola.util.geo;

public class GeoUtilTest02 {
	
	public static void main(String[] args) {

        Point src = new Point(118.69292, 25.36394);
        // 119.12198052,25.46600673
        Point dst;
        String dstStr;

//        dst = GeoUtil.convertBaidu2Earth(src);
//        dstStr = String.format("%.6f,%.6f", dst.getLon(),dst.getLat());
//        System.out.println(dstStr);
//
//        dst = GeoUtil.convertBaidu2Mars(src);
//        dstStr = String.format("%.6f,%.6f", dst.getLon(),dst.getLat());
//        System.out.println(dstStr);
//
//        dst = GeoUtil.convertEarth2Baidu(src);
//        dstStr = String.format("%.6f,%.6f", dst.getLon(),dst.getLat());
//        System.out.println(dstStr);
        
        //����GPS/�ȸ��ͼ��-�����ǣ���Ѷ(����)��ͼ�������Ƶ�ͼ���ߵµ�ͼ���ȸ���ڵ�ͼ��
        dst = GeoUtil.convertEarth2Mars(src);
        dstStr = String.format("%.6f,%.6f", dst.getLon(),dst.getLat());
        System.out.println(dstStr);

//        dst = GeoUtil.convertMapbar2Earth(src);
//        dstStr = String.format("%.6f,%.6f", dst.getLon(),dst.getLat());
//        System.out.println(dstStr);
//
//        dst = GeoUtil.convertMapbar2Mars(src);
//        dstStr = String.format("%.6f,%.6f", dst.getLon(),dst.getLat());
//        System.out.println(dstStr);
//
//        dst = GeoUtil.convertMars2Baidu(src);
//        dstStr = String.format("%.6f,%.6f", dst.getLon(),dst.getLat());
//        System.out.println(dstStr);
//
//        dst = GeoUtil.convertMars2Earth(src);
//        dstStr = String.format("%.6f,%.6f", dst.getLon(),dst.getLat());
//        System.out.println(dstStr);
	}

}
