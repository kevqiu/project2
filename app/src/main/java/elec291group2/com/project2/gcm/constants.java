package elec291group2.com.project2.gcm;

/**
 *  Class for GCM-related constants.
 *
 */
public class constants {
    // Used for error dialog message on play services check.
    public static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    // Sender ID used to connect from device to GCM
    public static final String GCM_SENDER_ID =
            "107441647513";

    /*
     * Push notification registration output dialogs
     */
    public static final String BROADCAST_REGISTRATION_COMPLETE =
            "broadcast-registration-complete";

    public static final String MESSAGE_GCM_SERVER_ERROR =
            "Failed to connect to Google Cloud Messaging server";

    public static final String MESSAGE_APP_SERVER_ERROR =
            "Failed to register device to home server. Please ensure that the server information " +
                    "is filled out correctly.";

    public static final String MESSAGE_REGISTRATION_SUCCESS =
            "Successfully registered to push notification server: ";

    public static final String MESSAGE_PLAY_SERVICES_ERROR =
        "Failed to activate notifications due to Google Play Services error: ";
}
