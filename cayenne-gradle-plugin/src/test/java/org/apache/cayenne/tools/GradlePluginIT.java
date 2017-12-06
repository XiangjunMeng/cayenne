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

package org.apache.cayenne.tools;

import org.gradle.testkit.runner.GradleRunner;
import org.junit.Test;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class GradlePluginIT extends BaseTaskIT {

    private void testDbImportWithGradleVersion(String version) throws Exception {
        String dbUrl = "jdbc:derby:" + projectDir.getAbsolutePath() + "/build/" + version.replace('.', '_');
        dbUrl += ";create=true";
        GradleRunner runner = createRunner("dbimport_simple_db", "cdbimport", "--info", "-PdbUrl=" + dbUrl);
        runner.withGradleVersion(version);
        runner.build();
    }

    private void testCgenWithGradleVersion(String version) throws Exception {
        GradleRunner runner = createRunner(
                "cgen_default_config",
                "cgen",
                "-PdataMap=" + URLDecoder.decode(getClass().getResource("test_datamap.map.xml").getFile(), "UTF-8")
        );
        runner.withGradleVersion(version);
        runner.build();
    }

    @Test
    public void testGradleVersionsCompatibility() throws Exception {

        String[] versions;

        // Old gradle versions will fail on Java 9
        if (System.getProperty("java.version").startsWith("9")) {
            versions = new String[]{"4.3.1", "4.3"};
        } else {
            versions = new String[]{"4.3", "4.0", "3.5", "3.3", "3.0", "2.12", "2.8"};
        }

        List<String> failedVersions = new ArrayList<>();
        for(String version : versions) {
            try {
                testDbImportWithGradleVersion(version);
                testCgenWithGradleVersion(version);
            } catch(Throwable th) {
                failedVersions.add(version);
            }
        }

        StringBuilder versionString = new StringBuilder("Failed versions:");
        for(String version : failedVersions) {
            versionString.append(" ").append(version);
        }
        assertTrue(versionString.toString(), failedVersions.isEmpty());
    }
}
