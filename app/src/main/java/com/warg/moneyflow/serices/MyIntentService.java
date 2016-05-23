package com.warg.moneyflow.serices;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;

import com.warg.moneyflow.util.Prefs;

import java.util.Calendar;


public class MyIntentService extends IntentService {

    private static final String ACTION_INSERT_EXPENCY = "com.warg.moneyflow.serices.action.INSERT_EXPENCY";

    private static final String EXTRA_INSERT_EXPENCY_NAME = "com.warg.moneyflow.serices.extra.INSERT_EXPENCY_NAME";
    private static final String EXTRA_INSERT_EXPENCY_VOLUME = "com.warg.moneyflow.serices.extra.INSERT_EXPENCY_VOLUME";

    public MyIntentService() {
        super("MyIntentService");
    }

    public static void startActionInsertExpency(Context context, String name, int volume) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_INSERT_EXPENCY);
        intent.putExtra(EXTRA_INSERT_EXPENCY_NAME, name);
        intent.putExtra(EXTRA_INSERT_EXPENCY_VOLUME, volume);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            switch (action) {
                case ACTION_INSERT_EXPENCY:
                    String name = intent.getStringExtra(EXTRA_INSERT_EXPENCY_NAME);
                    int volume = intent.getIntExtra(EXTRA_INSERT_EXPENCY_VOLUME, 0);
                    handleActionInsertExpency(name, volume);
                    break;
            }
        }
    }

    private void handleActionInsertExpency(String name, int volume) {
        ContentValues cv = new ContentValues();
        //cv.put(Prefs.EXPENSE_NAMES_FIELD_NAME, name);
        cv.put(Prefs.EXPENCY_FIELD_ID_PASSIVE, 1);

        String date = String.valueOf(Calendar.getInstance().getTimeInMillis());

        cv.put(Prefs.EXPENCY_FIELD_DATE, date);
        cv.put(Prefs.EXPENCY_FIELD_VOLUME, volume);
        getContentResolver().insert(Prefs.URI_EXPENSE, cv);


    }
}
