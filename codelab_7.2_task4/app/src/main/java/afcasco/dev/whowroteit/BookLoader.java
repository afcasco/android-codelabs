package afcasco.dev.whowroteit;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class BookLoader extends AsyncTaskLoader<String> {

    private String mQueryString;

    public BookLoader(@NonNull Context context, String mQueryString) {
        super(context);
        this.mQueryString = mQueryString;
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.Companion.getBookInfo(mQueryString);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
