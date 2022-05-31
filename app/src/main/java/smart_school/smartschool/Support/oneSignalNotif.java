//package smart_school.smartschool.Support;
//
//import android.content.Intent;
//import android.net.Uri;
//
//import android.util.Log;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.onesignal.OSNotificationAction;
//import com.onesignal.OSNotificationOpenResult;
//import com.onesignal.OneSignal;
//
//import org.json.JSONObject;
//
//
//import smart_school.smartschool.Activity.Container;
//import smart_school.smartschool.ActivityFirst;
//
//public class oneSignalNotif extends AppCompatActivity
//        implements OneSignal.NotificationOpenedHandler {
//
//
//    private Container activityFirst;
//    private String myCustomData;
//    public oneSignalNotif(Container application) {
//        this.activityFirst = application;
//    }
//
//    @Override
//    public void notificationOpened(OSNotificationOpenResult result) {
//        JSONObject data = result.notification.payload.additionalData;
//        if (data != null) {
//            myCustomData = data.optString("url", null);
//            }
//
//
//        OSNotificationAction.ActionType actionType = result.action.type;
//        if (actionType == OSNotificationAction.ActionType.ActionTaken)
//            Log.i("OneSignalExample", "Button pressed with id: " + result.action.actionID);
//        startApp();
//    }
//
//    private void startApp() {
//        Intent browserIntent = new Intent(activityFirst,Container.class);
//        browserIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
//        activityFirst.startActivity(browserIntent);
//
//        if (myCustomData != null) {
//          //  Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(myCustomData));
//           // activityFirst.startActivity(browserIntent);
//           // mainActivity.finish();
//        }
//    }
//
//}