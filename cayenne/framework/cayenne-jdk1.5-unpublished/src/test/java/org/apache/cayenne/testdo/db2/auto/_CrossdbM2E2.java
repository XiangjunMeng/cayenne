package org.apache.cayenne.testdo.db2.auto;

/** Class _CrossdbM2E2 was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public class _CrossdbM2E2 extends org.apache.cayenne.CayenneDataObject {

    public static final String NAME_PROPERTY = "name";
    public static final String TO_M1E1_PROPERTY = "toM1E1";
    public static final String TO_M2E1_PROPERTY = "toM2E1";

    public static final String ID_PK_COLUMN = "ID";

    public void setName(String name) {
        writeProperty("name", name);
    }
    public String getName() {
        return (String)readProperty("name");
    }
    
    
    public void setToM1E1(org.apache.cayenne.testdo.db1.CrossdbM1E1 toM1E1) {
        setToOneTarget("toM1E1", toM1E1, true);
    }

    public org.apache.cayenne.testdo.db1.CrossdbM1E1 getToM1E1() {
        return (org.apache.cayenne.testdo.db1.CrossdbM1E1)readProperty("toM1E1");
    } 
    
    
    public void setToM2E1(org.apache.cayenne.testdo.db2.CrossdbM2E1 toM2E1) {
        setToOneTarget("toM2E1", toM2E1, true);
    }

    public org.apache.cayenne.testdo.db2.CrossdbM2E1 getToM2E1() {
        return (org.apache.cayenne.testdo.db2.CrossdbM2E1)readProperty("toM2E1");
    } 
    
    
}
