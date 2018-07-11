package org.apache.cayenne.commitlog.db.auto;

import java.util.List;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.exp.Property;
import org.apache.cayenne.commitlog.db.AuditableChild1;

/**
 * Class _Auditable1 was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Auditable1 extends CayenneDataObject {

    private static final long serialVersionUID = 1L; 

    public static final String ID_PK_COLUMN = "ID";

    public static final Property<String> CHAR_PROPERTY1 = Property.create("charProperty1", String.class);
    public static final Property<List<AuditableChild1>> CHILDREN1 = Property.create("children1", List.class);

    public void setCharProperty1(String charProperty1) {
        writeProperty("charProperty1", charProperty1);
    }
    public String getCharProperty1() {
        return (String)readProperty("charProperty1");
    }

    public void addToChildren1(AuditableChild1 obj) {
        addToManyTarget("children1", obj, true);
    }
    public void removeFromChildren1(AuditableChild1 obj) {
        removeToManyTarget("children1", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<AuditableChild1> getChildren1() {
        return (List<AuditableChild1>)readProperty("children1");
    }


}