package hcmute.edu.vn.foody01;

import static hcmute.edu.vn.foody01.Constants.FAILURE_RESULT;
import static hcmute.edu.vn.foody01.Constants.LOCATION_DATA_EXTRA;
import static hcmute.edu.vn.foody01.Constants.RECEIVER;
import static hcmute.edu.vn.foody01.Constants.RESULT_DATA_KEY;
import static hcmute.edu.vn.foody01.Constants.SUCCESS_RESULT;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class FetchAddressIntentService extends IntentService {

    private ResultReceiver resultReceiver;

    public FetchAddressIntentService() {
        super("FetchAddressIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(intent != null) {
            String errorMessage = "";
            resultReceiver = intent.getParcelableExtra(RECEIVER);
            Location location = intent.getParcelableExtra(LOCATION_DATA_EXTRA);
            if(location == null) {
                return;
            }
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = null;
            try {
                addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

            }catch (Exception exception){
                errorMessage = exception.getMessage();
            }
            if(addresses == null || addresses.isEmpty()) {
                deliverResultToReceiver(FAILURE_RESULT, errorMessage);
            }else{
                Address address = addresses.get(0);
                ArrayList<String> addressFragments = new ArrayList<>();
                for(int i = 0; i <= address.getMaxAddressLineIndex(); i++){
                    addressFragments.add(address.getAddressLine(i));
                }
                deliverResultToReceiver(
                        SUCCESS_RESULT,
                        TextUtils.join(
                                Objects.requireNonNull(System.getProperty("line.seperator")),
                                addressFragments
                        )
                );
            }
        }

    }

    private void deliverResultToReceiver(int resultCode, String addressMessage) {
        Bundle bundle = new Bundle();
        bundle.putString(RESULT_DATA_KEY, addressMessage);
        resultReceiver.send(resultCode, bundle);
    }
}
