package org.apache.cayenne.testdo.inheritance_vertical.auto;

import org.apache.cayenne.exp.Property;
import org.apache.cayenne.testdo.inheritance_vertical.IvRoot;

/**
 * Class _IvSub1 was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _IvSub1 extends IvRoot {

    private static final long serialVersionUID = 1L; 

    public static final String ID_PK_COLUMN = "ID";

    public static final Property<String> SUB1NAME = new Property<String>("sub1Name");

    public void setSub1Name(String sub1Name) {
        writeProperty("sub1Name", sub1Name);
    }
    public String getSub1Name() {
        return (String)readProperty("sub1Name");
    }

}