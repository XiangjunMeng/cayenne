/*****************************************************************
 *   Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 ****************************************************************/

package org.apache.cayenne.modeler.dialog.pref;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.AbstractTableModel;

import org.apache.cayenne.modeler.util.CayenneController;
import org.apache.cayenne.modeler.util.FileFilters;
import org.apache.cayenne.pref.CayennePreferenceEditor;
import org.apache.cayenne.pref.PreferenceEditor;

/**
 */
public class ClasspathPreferences extends CayenneController {

    protected ClasspathPreferencesView view;
    protected List<String> classPathEntries;
    protected ClasspathTableModel tableModel;
    protected CayennePreferenceEditor editor;

    public ClasspathPreferences(PreferenceDialog parentController) {
        super(parentController);

        this.view = new ClasspathPreferencesView();

        PreferenceEditor editor = parentController.getEditor();
        if (editor instanceof CayennePreferenceEditor) {
            this.editor = (CayennePreferenceEditor) editor;
        }

        String[] arr = null;
        try {
            arr = getClassLoader().keys();
        }
        catch (BackingStoreException e) {
        }
        this.classPathEntries = new ArrayList<String>();
        for (int i = 0; i < arr.length; i++) {
            classPathEntries.add(arr[i]);
        }

        this.tableModel = new ClasspathTableModel();

        initBindings();
    }

    public Component getView() {
        return view;
    }

    protected Preferences getClassLoader() {
        return getApplication().getPreferencesNode(this.getClass(), "");
    }

    protected void initBindings() {
        view.getTable().setModel(tableModel);

        view.getAddDirButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                addClassDirectoryAction();
            }
        });

        view.getRemoveEntryButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                removeEntryAction();
            }
        });

        view.getAddJarButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                addJarOrZipAction();
            }
        });
    }

    protected void addJarOrZipAction() {
        chooseClassEntry(
                FileFilters.getClassArchiveFilter(),
                "Select JAR or ZIP File.",
                JFileChooser.FILES_ONLY);
    }

    protected void addClassDirectoryAction() {
        chooseClassEntry(
                null,
                "Select Java Class Directory.",
                JFileChooser.DIRECTORIES_ONLY);
    }

    protected void removeEntryAction() {
        int selected = view.getTable().getSelectedRow();
        if (selected < 0) {
            return;
        }

        addRemovedPreferences((String) classPathEntries.get(selected));
        classPathEntries.remove(selected);
        tableModel.fireTableRowsDeleted(selected, selected);
    }

    protected void chooseClassEntry(FileFilter filter, String title, int selectionMode) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(selectionMode);
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        chooser.setAcceptAllFileFilterUsed(true);

        getLastDirectory().updateChooser(chooser);

        if (filter != null) {
            chooser.addChoosableFileFilter(filter);
        }

        chooser.setDialogTitle(title);

        File selected = null;
        int result = chooser.showOpenDialog(view);
        if (result == JFileChooser.APPROVE_OPTION) {
            selected = chooser.getSelectedFile();
        }

        if (selected != null) {
            // store last dir in preferences
            getLastDirectory().updateFromChooser(chooser);

            int len = classPathEntries.size();
            String key = selected.getAbsolutePath();
            addChangedPreferences(key, "");
            classPathEntries.add(key);
            tableModel.fireTableRowsInserted(len, len);
        }
    }

    public void addChangedPreferences(String key, String value) {
        Map<String, String> map = editor.getChangedPreferences().get(getClassLoader());
        if (map == null) {
            map = new HashMap<String, String>();
        }
        map.put(key, value);
        editor.getChangedPreferences().put(getClassLoader(), map);
    }
    
    public void addRemovedPreferences(String key) {
        Map<String, String> map = editor.getRemovedPreferences().get(getClassLoader());
        if (map == null) {
            map = new HashMap<String, String>();
        }
        map.put(key, "");
        editor.getRemovedPreferences().put(getClassLoader(), map);
    }

    class ClasspathTableModel extends AbstractTableModel {

        public int getColumnCount() {
            return 1;
        }

        public int getRowCount() {
            return classPathEntries.size();
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            return classPathEntries.get(rowIndex);
        }

        public String getColumnName(int column) {
            return "Custom ClassPath";
        }
    }

}
