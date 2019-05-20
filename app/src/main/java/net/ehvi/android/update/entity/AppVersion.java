package net.ehvi.android.update.entity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.Exclude;

/**
 * Created by makata on 10/28/17.
 */

public class AppVersion extends FirebaseModel {
    private static final String FIRESTORE_DOCUMENT_NAME = "appVersion";
//     const versionName = appVersion.versionName;
//    const versionCode = appVersion.versionCode;
//    const updateSummary = appVersion.updateSummary;
//    const updateSeverity = appVersion.updateSeverity;
//
//    Integer currentVersion;
    Integer versionCode;
    String versionName;
    String updateLevel;//Critical,Minor,BigVersion, Point
    String updateSummary;//Critical,Minor,BigVersion, Point

    public static CollectionReference getCollectionReference() {
        return getFirestoreInstance().collection(FIRESTORE_DOCUMENT_NAME);
    }

    public static String createDocumentUid() {
        return getCollectionReference().document().getId();

    }

    @Override @Exclude
    public String getTitle() {
        return "Current Version: " + versionName +" (Code: "+versionCode+")";
    }
    //

    @Override @Exclude
    public String getSubTitle() {
        return "Level: " + updateLevel;
    }



    public Integer getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getUpdateLevel() {
        return updateLevel;
    }

    public void setUpdateLevel(String updateLevel) {
        this.updateLevel = updateLevel;
    }

    public String getUpdateSummary() {
        return updateSummary;
    }

    public void setUpdateSummary(String updateSummary) {
        this.updateSummary = updateSummary;
    }

    public enum UpdateLevel {
        Critical, MinorUpgrade, BigFeature, NewFeature
    }
}
