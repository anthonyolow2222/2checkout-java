package com.twocheckout.model;

import java.util.HashMap;

import com.google.gson.Gson;
import com.twocheckout.TwocheckoutApi;
import com.twocheckout.TwocheckoutOption;
import com.twocheckout.TwocheckoutResponse;

public class ProductOption {
    private long option_id;
    private String option_name;
    private ProductOptionValue[] option_values;

    public long getOptionId() {
        return option_id;
    }
    public void setOptionId(long option_id) {
        this.option_id = option_id;
    }
    public String getOptionName() {
        return option_name;
    }
    public void setOptionName(String option_name) {
        this.option_name = option_name;
    }
    public ProductOptionValue[] getOptionValues() {
        return option_values;
    }
    public void setOption_values(ProductOptionValue[] option_values) {
        this.option_values = option_values;
    }

    public TwocheckoutResponse update() throws Exception {
        HashMap<String, String> args = new HashMap<String, String>();
        args.put("option_id", String.valueOf(option_id));
        args.put("option_name", option_name);

        for(int i = 0;i< option_values.length; i++){
            args.put("option_value_id", String.valueOf(option_values[i].getOptionValueId()));
            args.put("option_value_name", String.valueOf(option_values[i].getOptionValueName()));
            args.put("option_value_surcharge", String.valueOf(option_values[i].getOptionValueSurcharge()));
        }

        String urlSuffix = "products/update_option";
        String response = TwocheckoutApi.post(urlSuffix, args);
        TwocheckoutResponse responseObj = new Gson().fromJson(response, TwocheckoutResponse.class);
        return responseObj;
    }

    public TwocheckoutResponse delete() throws Exception {
        HashMap<String, String> args = new HashMap<String, String>();
        args.put("option_id", String.valueOf(option_id));
        String urlSuffix = "products/delete_option";
        String response = TwocheckoutApi.post(urlSuffix, args);
        TwocheckoutResponse responseObj = new Gson().fromJson(response, TwocheckoutResponse.class);
        return responseObj;
    }
    
    public ProductOption refresh() throws Exception {
        HashMap<String, String> args = new HashMap<String, String>();
        args.put("option_id", String.valueOf(option_id));
        String urlSuffix = "products/detail_option";
        String response = TwocheckoutApi.get(urlSuffix, args);
        TwocheckoutOption responseObj = new Gson().fromJson(response, TwocheckoutOption.class);
        return responseObj.option[0];
    }
}