package org.cola.util.geo;



/**
 * ��CoordType.java��ʵ����������������
 * 
 * @author JianLin.Zhu 2015-9-22 ����7:26:36
 */
public enum CoordType {
    /**WGS-84 ֧�ֳ���:ƻ��**/
    EARTH("EARTH", "��������"), 
    /**GCJ-02 ֧�ֳ���:�ȸ衢�ߵ�**/
    MARS("MARS", "��������"),
    /**BD-09  ֧�ֳ���:�ٶ�**/
    BAIDU("BAIDU", "�ٶ�����"),
    /**�ѹ�       ֧�ֳ��̣��ѹ�   http://map.sogou.com/api/**/
    SOGOU("SOGOU", "�ѹ�����"),
    /**ͼ��      ֧�ֳ��̣�ͼ��  http://open.mapbar.com/**/
    MAPBAR("MAPBAR", "�ѹ�����");
 
    private String name;
    private String remark;
 
    private CoordType(String name, String remark){
        this.name = name;
        this.remark = remark;
    }
 
    public static CoordType codeOf(String name) {
        for (CoordType s : CoordType.values()) {
            if (equalsIgnoreCase(s.getName(), name)) {
                return s;
            }
        }
 
        return null;
    }
    
    private static boolean equalsIgnoreCase(String str1, String str2)
    {
        return str1 != null ? str1.equalsIgnoreCase(str2) : str2 == null;
    }
 
 
    public String getName() {
        return name;
    }
 
    public String getRemark() {
        return remark;
    }
 
    @Override
    public String toString() {
        return name;
    }
    
    public static void main(String[] args){
        System.out.println(CoordType.MARS.equals(CoordType.codeOf("Mars")));
        System.out.println(CoordType.MARS.equals(CoordType.codeOf("EARTH")));
        System.out.println(CoordType.SOGOU.equals(CoordType.codeOf("SOGOU")));
        System.out.println(CoordType.MAPBAR.equals(CoordType.codeOf("MAPBAR")));
        System.out.println(CoordType.codeOf("BAIDU"));
        System.out.println(CoordType.codeOf("mars"));
        System.out.println(CoordType.codeOf("sogou"));
        System.out.println(CoordType.codeOf("MAPBAr"));
        System.out.println(CoordType.codeOf(""));
        System.out.println(CoordType.codeOf(null));
    }
}
