package com.eigirdasmil.dviraciunuoma;

import com.android.volley.VolleyError;

public interface VolleyCallback {
    void onSuccess(String result);
    void onError(VolleyError result);
}
