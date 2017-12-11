/*package com.example.mytestdemo.json;

import java.util.ArrayList;
import java.util.List;

import com.example.mytestdemo.R;
import com.example.mytestdemo.entity.CityBean;
import com.example.mytestdemo.entity.ProvinceBean;
import com.example.mytestdemo.utils.ProvinceCityUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.Toast;


public class ChangeInfoActivity extends Activity implements OnCheckedChangeListener{

	private Spinner provinceSpinner;
	private Spinner citySpinner;
	private RadioGroup radioGroup;
	
	private ProvinceCityUtil dataUtil;
	private List<String> provinceList;
	private List<String> cityList;
	private List<ProvinceBean> listProvinceBeans;
	private List<CityBean> listCityBeans;
	private ArrayAdapter<String> provinceAdapter;
	private ArrayAdapter<String> cityAdapter;
	private boolean isProvinceFirstSelected = true;
	private boolean isCityFirstSelected = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_info);
		init();
	}
	
	private void init(){
		dataUtil = new ProvinceCityUtil(this);
		provinceSpinner = (Spinner)findViewById(R.id.change_info_province_spinner);
		citySpinner = (Spinner)findViewById(R.id.change_info_city_spinner);
		radioGroup = (RadioGroup)findViewById(R.id.change_info_sex_radio_group);
		
		provinceSpinner.setOnItemSelectedListener(provinceSelectedListener);
		citySpinner.setOnItemSelectedListener(citySelectedListener);
		radioGroup.setOnCheckedChangeListener(this);
		
		initProvinceSpinner();
		initCitySpinner(1);
	}
	
	private void initProvinceSpinner(){
		provinceList = new ArrayList<String>();
		getProvinces();
		if(listProvinceBeans!=null && listProvinceBeans.size()>0){
			for(int i=0; i<listProvinceBeans.size(); i++){
				provinceList.add(listProvinceBeans.get(i).getName());
			}
		}
		provinceAdapter = new ArrayAdapter<String>(this, R.layout.spinner_textview_item, provinceList);
		provinceAdapter.setDropDownViewResource(R.layout.spinner_textview_dropdown_item);
		provinceSpinner.setAdapter(provinceAdapter);
		
	}
	
	private void initCitySpinner(int provinceId){
		cityList = new ArrayList<String>();
		cityList.addAll(getCityList(provinceId));
		cityAdapter = new ArrayAdapter<String>(this, R.layout.spinner_textview_item, cityList);
		cityAdapter.setDropDownViewResource(R.layout.spinner_textview_dropdown_item);
		citySpinner.setAdapter(cityAdapter);
	}
	
	private List<String> getCityList(long provinceId){
		List<String> cityListStrings = new ArrayList<String>();
		listCityBeans = getCities(provinceId);	
		if(listCityBeans!=null && listCityBeans.size()>0){
			for(int i=0; i<listCityBeans.size(); i++){
				cityListStrings.add(listCityBeans.get(i).getName());
			}
		}
		return cityListStrings;
	}
		
	
	private void getProvinces(){
		listProvinceBeans = new ArrayList<ProvinceBean>();
		listProvinceBeans = dataUtil.getProvinceList();
	}
	
	private List<CityBean> getCities(long pid){
		List<CityBean> listCities = new ArrayList<CityBean>();
		listCities = dataUtil.getCityList(pid);
		return listCities;
	}

	private OnItemSelectedListener provinceSelectedListener = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			if(isProvinceFirstSelected){
				isProvinceFirstSelected = false;
				return;
			}
			String provinceName = listProvinceBeans.get(position).getName();
			long pid = listProvinceBeans.get(position).getId();
			Toast.makeText(getApplicationContext(), provinceName, Toast.LENGTH_SHORT).show();
			if(cityList!=null && cityList.size()>0){
				cityList.clear();
			}
			cityList.addAll(getCityList(pid));
			cityAdapter.notifyDataSetChanged();
			citySpinner.setSelection(0);
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub
			
		}
	};
	
	private OnItemSelectedListener citySelectedListener = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			if(isCityFirstSelected){
				isCityFirstSelected = false;
				return;
			}
			String cityName = cityList.get(position);
			Toast.makeText(getApplicationContext(), cityName, Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub
			
		}
	};

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		switch (checkedId) {
		case R.id.change_info_sex_man:
			Toast.makeText(getApplicationContext(), "男", Toast.LENGTH_SHORT).show();
			break;
			
		case R.id.change_info_sex_woman:
			Toast.makeText(getApplicationContext(), "女", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
	}
	
}
*/