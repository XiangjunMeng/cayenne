package org.apache.cayenne.testdo.mt.auto;

/** Class _MtTable5 was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public abstract class _MtTable5 extends org.apache.cayenne.CayenneDataObject {

    public static final String TABLE4S_PROPERTY = "table4s";

    public static final String ID_PK_COLUMN = "ID";

    public void addToTable4s(org.apache.cayenne.testdo.mt.MtTable4 obj) {
        addToManyTarget("table4s", obj, true);
    }
    public void removeFromTable4s(org.apache.cayenne.testdo.mt.MtTable4 obj) {
        removeToManyTarget("table4s", obj, true);
    }
    public java.util.List getTable4s() {
        return (java.util.List)readProperty("table4s");
    }
    
    
}
