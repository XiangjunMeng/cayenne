package org.apache.cayenne.testdo.enum_test.auto;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.exp.Property;
import org.apache.cayenne.testdo.enum_test.Enum1;

/**
 * Class _EnumEntity1 was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _EnumEntity1 extends CayenneDataObject {

    private static final long serialVersionUID = 1L; 

    public static final String ID_PK_COLUMN = "ID";

    public static final Property<Enum1> ENUM_ATTRIBUTE = Property.create("enumAttribute", Enum1.class);

    public void setEnumAttribute(Enum1 enumAttribute) {
        writeProperty("enumAttribute", enumAttribute);
    }
    public Enum1 getEnumAttribute() {
        return (Enum1)readProperty("enumAttribute");
    }

}