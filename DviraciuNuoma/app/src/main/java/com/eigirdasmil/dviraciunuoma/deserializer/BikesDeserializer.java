package com.eigirdasmil.dviraciunuoma.deserializer;

import android.os.Build;
import androidx.annotation.RequiresApi;
import com.eigirdasmil.dviraciunuoma.ds.Bikes;
import com.eigirdasmil.dviraciunuoma.ds.DviracioStatusasEnum;
import com.eigirdasmil.dviraciunuoma.ds.DviracioTipasEnum;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.UUID;

public class BikesDeserializer implements JsonDeserializer<Bikes> {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public Bikes deserialize(JsonElement json, Type type,
                            JsonDeserializationContext context) throws JsonParseException {

        JsonObject jobject = json.getAsJsonObject();
        Bikes bike = new Bikes();
        bike.id = UUID.fromString(jobject.get("id").getAsString());
        bike.dviracioPavadinimas = jobject.get("dviracioPavadinimas").getAsString();
        bike.dviracioTipas = DviracioTipasEnum.fromInteger(jobject.get("dviracioTipas").getAsInt());
        bike.dviracioKaina = jobject.get("dviracioKaina").getAsDouble();
        bike.dviracioSpecifikacija = jobject.get("dviracioSpecifikacija").getAsString();
        bike.dviracioStatusas = DviracioStatusasEnum.fromInteger(jobject.get("dviracioStatusas").getAsInt());


        return bike;

    }
}
