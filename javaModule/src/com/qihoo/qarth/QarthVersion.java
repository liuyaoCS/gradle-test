/*
 * Copyright (C) 2018 Qihoo, Inc.
 *
 * This software is licensed under the terms of the GNU General Public
 * License version 2, as published by the Free Software Foundation, and
 * may be copied, distributed, and modified under those terms.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
*/

package com.qihoo.qarth;

import com.qihoo.qarth.annotations.KeepAll;

@KeepAll
public class QarthVersion {
    private static final String TAG = "QarthVersion";
    public static final String QARTH_VERSION = "0.0.1";

    /**
     * compare patch file version and patch version
     *
     * @param qc the patch loader param
     * @return  0   equals
     *          -1  patch version < patch file version
     *          1   patch version > patch file version
     */
    public static int compareVersion(QarthContext qc) {
        if (qc.qarthVersion == null)
            return -1;
        String patchFileVersion = qc.qarthVersion;
        String[] fileVersions = patchFileVersion.split("\\.");
        String[] patchVersion = QARTH_VERSION.split("\\.");
        if (!checkPatchFileVersionFormat(fileVersions)) {
            QarthLog.e(TAG, "patch file version format err");
            return -1;
        }
        for(int i = 0; i < patchVersion.length; i++) {
            int patchVersionValue = Integer.parseInt(patchVersion[i]);
            int fileVersionValue = Integer.parseInt(fileVersions[i]);
            if (patchVersionValue < fileVersionValue)
                return -1;
            else if (patchVersionValue > fileVersionValue)
                return 1;
        }
        return 0;
    }

    private static boolean checkPatchFileVersionFormat(String[] patchFileVersions) {
        if (patchFileVersions.length != 3)
            return false;
        for (String string: patchFileVersions) {
            try {
                int value = Integer.parseInt(string);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
