package com.edu.t2f.demoprovider.database.provider;

import android.content.Context;
import android.content.res.AssetManager;

import com.edu.t2f.demoprovider.database.dao.CountryDAO;
import com.edu.t2f.demoprovider.model.Country;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class DataProvider1 implements IDataProvider {
    Context mContext;
    CountryDAO mCountryDAO;
    public DataProvider1(Context mContext) {
        this.mContext = mContext;
        mCountryDAO = new CountryDAO(mContext);
    }
    @Override
    public List<Country> getCountries() {
        String path =  mContext.getFilesDir().getAbsolutePath() + "/DBCountry";
        File file = new File(path);
        if (!file.exists()) {
            AssetManager assetManager = mContext.getAssets();
            try {
                BufferedInputStream bis = new BufferedInputStream(assetManager.open("DBCountry"));
                FileOutputStream fos = mContext.openFileOutput("DBCountry", Context.MODE_PRIVATE);

                byte[] buffer = new byte[512];
                int i = 0;
                while ((i = bis.read(buffer)) != -1) {
                    fos.write(buffer, 0, i);
                }

                bis.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return mCountryDAO.get();
    }

    @Override
    public long insertCountry(Country country) {
        return mCountryDAO.insert(country);
    }
}
