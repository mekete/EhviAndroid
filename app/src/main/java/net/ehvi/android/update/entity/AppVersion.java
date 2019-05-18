package net.ehvi.android.update.entity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.Exclude;

/**
 * Created by makata on 10/28/17.
 */

public class AppVersion extends FirebaseModel {
    private static final String FIRESTORE_DOCUMENT_NAME = "appVersion";
    Integer currentVersion;
    Integer latestVersionCode;
    String latestVersionName;
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
        return "Current Version: " + currentVersion;
    }
    //

    @Override @Exclude
    public String getSubTitle() {
        return "Level: " + updateLevel;
    }

    public Integer getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(Integer currentVersion) {
        this.currentVersion = currentVersion;
    }

    public Integer getLatestVersionCode() {
        return latestVersionCode;
    }

    public void setLatestVersionCode(Integer latestVersionCode) {
        this.latestVersionCode = latestVersionCode;
    }

    public String getLatestVersionName() {
        return latestVersionName;
    }

    public void setLatestVersionName(String latestVersionName) {
        this.latestVersionName = latestVersionName;
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
