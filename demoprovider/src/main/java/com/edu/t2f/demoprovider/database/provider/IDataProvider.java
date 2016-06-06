package com.edu.t2f.demoprovider.database.provider;

import com.edu.t2f.demoprovider.model.Country;

import java.util.List;


/**
 * Created by Dzumi on 6/3/2016.
 */
public interface IDataProvider {
    //interface này được dùng để khai báo các phương thức sử dụng để truy xuất DB
    //hoặc dump data


    List<Country> getCountries();
    long insertCountry(Country country);

    // TODO: khai báo thêm các phương thức cần thiết khác 
}
