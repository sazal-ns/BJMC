package com.ns.siddiqui.sazal.bjmc_v20;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.ns.siddiqui.sazal.bjmc_v20.model.Item;
import com.ns.siddiqui.sazal.bjmc_v20.model.ListCategoryModel;
import com.ns.siddiqui.sazal.bjmc_v20.model.ListItemModel;
import com.ns.siddiqui.sazal.bjmc_v20.model.Quantity;
import com.ns.siddiqui.sazal.bjmc_v20.model.SupplierListModel;
import com.ns.siddiqui.sazal.bjmc_v20.model.TransportMode;
import com.ns.siddiqui.sazal.bjmc_v20.model.UserModel;


import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PurchaseActivity extends AppCompatActivity implements View.OnClickListener {

   private EditText billNoEditText,supplierEditText,supplierCodeEditText,licenceNoEditText,poEditText,psEditText,
            distEditText,quantityTenderEditText,lotEditText,driverMobileEditText,importDateTimeEditText,
            unloadDateEditText,moistureTimeEditText,basisRateEditText,percentEditText,qntlEditText,rateEditText,valueEditText,
            sattledRateEditText,totalValueEditText,quintalEditText,kgEditText;
    private  AutoCompleteTextView transModeEditText;
    private   Spinner qualitySpinner,gradSpinner;
    private   Button addButton,saveButton,calculateButton,saveApprovedButton,addAcButton;
    private   TextView percentTextView,qntlTextView,rateTextView,valueTextView,slTextView,totalQuintalTextView,kgTextView;

    private LinearLayout layout;

    private String url= "http://juteapp.flowermillsbd.org";
    private final String listItem = "/list/item";
    private final String categoryList = "/list/category";
    private final String supplierList = "/supplier/list";
    private final String submit = "/jute/purchase";
    private final String bearer = "Bearer ";

    private ArrayList<ListItemModel> qualityList= new ArrayList<>();
    private ArrayList<ListCategoryModel> listCategory = new ArrayList<>();
    private ArrayList<SupplierListModel> supplier = new ArrayList<>();

    private String[] qualityListString= new String[4];
    private String[] categoryListString = new String[6];
    private String quality;
    private String category;
    private String qualityID;
    private String catagoryID;

    private int selected;
    private String dateTimeData;

    private int f12i=1;
    private  List<EditText> allEds;
    private List<EditText> allEds2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(UserModel.getMill_name());

        init();

    }

    private void init() {

        billNoEditText = (EditText) findViewById(R.id.billNoEditText);
        supplierEditText = (EditText) findViewById(R.id.supplierEditText);
        supplierCodeEditText = (EditText) findViewById(R.id.supplierCodeEditText);
        licenceNoEditText = (EditText) findViewById(R.id.licenceNoEditText);
        poEditText = (EditText) findViewById(R.id.poEditText);
        psEditText = (EditText) findViewById(R.id.psEditText);
        distEditText = (EditText) findViewById(R.id.distEditText);
        quantityTenderEditText = (EditText) findViewById(R.id.quantityTenderEditText);
        lotEditText = (EditText) findViewById(R.id.lotEditText);
        driverMobileEditText = (EditText) findViewById(R.id.driverMobileEditText);
        importDateTimeEditText = (EditText) findViewById(R.id.importDateTimeEditText);
        unloadDateEditText = (EditText) findViewById(R.id.unloadDateEditText);
        moistureTimeEditText = (EditText) findViewById(R.id.moistureTimeEditText);
        basisRateEditText = (EditText) findViewById(R.id.basisRateEditText);
        percentEditText = (EditText) findViewById(R.id.percentEditText);
        qntlEditText = (EditText) findViewById(R.id.qntlEditText);
        rateEditText = (EditText) findViewById(R.id.rateEditText);
        valueEditText = (EditText) findViewById(R.id.valueEditText);
        sattledRateEditText = (EditText) findViewById(R.id.sattledRateEditText);
        totalValueEditText = (EditText) findViewById(R.id.totalValueEditText);
        quintalEditText = (EditText) findViewById(R.id.quintalEditText);
        kgEditText = (EditText) findViewById(R.id.kgEditText);

        transModeEditText = (AutoCompleteTextView) findViewById(R.id.transModeEditText);

        qualitySpinner = (Spinner) findViewById(R.id.qualitySpinner);
        gradSpinner = (Spinner) findViewById(R.id.gradSpinner);

        addButton = (Button) findViewById(R.id.addButton);
        saveButton = (Button) findViewById(R.id.saveButton);
        calculateButton = (Button) findViewById(R.id.calculateButton);
        saveApprovedButton = (Button) findViewById(R.id.saveApprovedButton);
        addAcButton = (Button) findViewById(R.id.addAcButton);

        percentTextView = (TextView) findViewById(R.id.percentTextView);
        qntlTextView = (TextView) findViewById(R.id.qntlTextView);
        rateTextView = (TextView) findViewById(R.id.rateTextView);
        valueTextView = (TextView) findViewById(R.id.valueTextView);
        slTextView = (TextView) findViewById(R.id.slTextView);
        totalQuintalTextView = (TextView) findViewById(R.id.totalQuintalTextView);
        kgTextView = (TextView) findViewById(R.id.kgTextView);
        layout = (LinearLayout) findViewById(R.id.qsbnewCommer);

        supplierEditText.setOnClickListener(this);
        addButton.setOnClickListener(this);
        saveButton.setOnClickListener(this);
        calculateButton.setOnClickListener(this);

        unloadDateEditText.setOnClickListener(this);
        importDateTimeEditText.setOnClickListener(this);


        qualitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                quality = qualityList.get(i).getItem_name();
                qualityID = qualityList.get(i).getId();
                //Toast.makeText(adapterView.getContext(), "Selected: " + item+"ID "+ id, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        gradSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                category = listCategory.get(i).getCateory_name();
                catagoryID = listCategory.get(i).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        allEds = new ArrayList();
        allEds2 = new ArrayList();

        setJsonData();
    }

    private void setJsonData() {

        setListItem();
        setSupplier();
        setCategory();

        Log.d("#####SETJSONDATA#######","Log from setJsonData");

        ArrayAdapter<TransportMode> transportModeArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, TransportMode.values());
        transModeEditText.setAdapter(transportModeArrayAdapter);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.supplierEditText:
                new MaterialDialog.Builder(this)
                        .title("Select Supplier")
                        .items(supplier)
                        .itemsCallbackSingleChoice(4, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                supplierEditText.setText(text);
                                supplierCodeEditText.setText(supplier.get(which).getSupplier_code());
                                licenceNoEditText.setText(supplier.get(which).getLicence_no());
                                poEditText.setText(supplier.get(which).getPost_name());
                                psEditText.setText(supplier.get(which).getThana_name());
                                distEditText.setText(supplier.get(which).getDist_name());
                                selected = which;
                                return true;
                            }
                        })
                        .show();
                break;
            case R.id.unloadDateEditText:
               getCurrentDate(unloadDateEditText, false);
                break;
            case R.id.importDateTimeEditText:
                getCurrentDate(importDateTimeEditText, true);
                break;
            case R.id.addButton:
                addViewQSB();
                break;
            case R.id.saveButton:
                getAllData();
                break;

        }
    }

    private void getAllData(){
        final Gson gson = new Gson();

        final List<Item> itemList = new ArrayList<>();
        final List<Quantity> quantityList = new ArrayList<>();
        Item item = new Item();
        Quantity quantity = new Quantity();

        item.setTxtacquantity("1000");
        item.setTxtgred("this is grad");
        item.setTxtrate("100");
        item.setTxtvalue("1000");
        item.setTxtpercentage("10");
        itemList.add(item);

        quantity.setTxtquantity("100");
        quantity.setTxtquantityKg("10000");
        quantity.setTxtquantityMn("00");
        quantityList.add(quantity);


        StringRequest request = new StringRequest(Request.Method.POST, url + submit,  new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(PurchaseActivity.this,response,Toast.LENGTH_LONG).show();
                Log.e("RES",response);
                // VolleyLog.d("Save Data", "Response: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PurchaseActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                VolleyLog.d("Save Data", "Error: " + error.getMessage());
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap();
                params.put("txt_bill_no", billNoEditText.getText().toString());
                params.put("cbo_mill_id", UserModel.getMill_id());
                params.put("cbo_pp_id", supplier.get(selected).getPurchase_point_id());
                params.put("cbo_warehouse_id", "1");
                params.put("cbo_item_id", qualityID);
                params.put("txt_purchase_date", systemDate());
                params.put("cbo_supplier_id", supplier.get(selected).getId());
                params.put("txt_quality", quality);
                params.put("txt_lot", lotEditText.getText().toString());
                params.put("txt_markes", "no markes");
                params.put("txt_remarks", "no remarks");
                params.put("sum_qnty", "100"); // need update
                params.put("sum_perc", "50"); // need update
                params.put("sum_act_qnty", "80"); // need update
                params.put("avg_rate", "50"); // need update
                params.put("sum_value", "20"); // need update
                params.put("txt_basis_x_bottom", category);
                params.put("txt_sattled_rate", "1000"); // need update
                params.put("txt_total_value", "1500"); // need update
                params.put("txt_quality_tender", quantityTenderEditText.getText().toString());
                params.put("cbo_transport_type", transModeEditText.getText().toString().trim());
                params.put("txt_transport_licence", "nothing");
                params.put("txt_driving_licence", "Nothing");
                params.put("txt_unload_date", unloadDateEditText.getText().toString());
                params.put("txt_driver_mob", driverMobileEditText.getText().toString());
                params.put("moisture_claim_rcv", moistureTimeEditText.getText().toString());
                params.put("purchase_date_time", importDateTimeEditText.getText().toString());
                params.put("is_cutting", "0");
                params.put("update_id", UserModel.getId());

                params.put("quantity",gson.toJson(quantityList));
                params.put("items", gson.toJson(itemList));
                Log.e("params", params+ BuildConfig.VERSION_NAME);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers = new HashMap<>();
                headers.put("Authorization", bearer + UserModel.getToken());
                return headers;
            }};

        Volley.newRequestQueue(this).add(request);

    }

    public String systemDate(){
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return df.format(new java.util.Date());
    }

    private void addViewQSB() {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT));

        TextView textView = new TextView(this);
        textView.setText("2");
        textView.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            textView.setGravity(View.TEXT_ALIGNMENT_CENTER);
        }
        textView.setPadding(5,5,5,5);
        //noinspection ResourceType
        textView.setId(f12i);


       // LinearLayout copy = new LinearLayout(this);
       // copy.setId(R.id.qsbnewCommer);
        linearLayout.addView(textView);
        //linearLayout.addView(copy);
        LinearLayout mainLL = (LinearLayout) findViewById(R.id.qsbnewCommer);
        mainLL.addView(linearLayout);

        /*LinearLayout lin_layout = new LinearLayout(this);
        lin_layout.setLayoutParams(new Toolbar.LayoutParams(-1, -2));
        lin_layout.setOrientation(LinearLayout.HORIZONTAL);
        lin_layout.setGravity(16);
        Toolbar.LayoutParams paramText = new Toolbar.LayoutParams(0, -2, 1);
        paramText.gravity = 17;
        paramText.leftMargin = 5;
        TextView textView = new TextView(this);
        textView.setText(BuildConfig.VERSION_NAME);
        textView.setLayoutParams(paramText);
        Toolbar.LayoutParams paramA = new Toolbar.LayoutParams(0, 52, 1);
        paramA.gravity = 17;
        paramA.leftMargin = 10;
        EditText edittext = new EditText(this);
        edittext.setHint(" Quintal");
        edittext.setPadding(5, 5, 5, 5);
        edittext.setTextColor(SupportMenu.CATEGORY_MASK);
        edittext.setLayoutParams(paramA);
        Toolbar.LayoutParams paramB = new Toolbar.LayoutParams(0, 52, 1);
        paramB.gravity = 17;
        paramB.leftMargin = 18;
        EditText edittext2 = new EditText(this);
        edittext2.setHint(" Kg");
        edittext2.setPadding(5, 5, 5, 5);
        edittext2.setLayoutParams(paramB);
        edittext2.setTextColor(SupportMenu.CATEGORY_MASK);
        edittext2.setFocusable(false);
        *//*edittext.addTextChangedListener(new AnonymousClass16(edittext, edittext2));
        edittext2.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
                try {
                    String v_kg = CuttingPurchasesActivity.this.row_edt_txt_kg.getText().toString();
                    float c = 0.0f;
                    for (int i = 0; i < CuttingPurchasesActivity.this.allEds2.size(); i++) {
                        int id = ((EditText) CuttingPurchasesActivity.this.allEds2.get(i)).getId();
                        String edittestvalue2 = ((EditText) CuttingPurchasesActivity.this.allEds2.get(i)).getText().toString();
                        c += Float.parseFloat(edittestvalue2);
                        Log.e(" EditText=>c", c + "ID==========>");
                        float pp = c + Float.parseFloat(v_kg);
                        CuttingPurchasesActivity.this.row_edt_txt_quintal_kg_sum.setText(String.valueOf(pp));
                        Log.e(" EditText=>pp", pp + "pp");
                        Toast.makeText(CuttingPurchasesActivity.this.getApplicationContext(), "Edit Text" + edittestvalue2, 0).show();
                        Log.v("Value Dynamically", "Edit Text" + edittestvalue2);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
        });*//*
        Button btn_get = new Button(this);
        btn_get.setText("x");
        Toolbar.LayoutParams paramBtn = new Toolbar.LayoutParams(0, 60, 1);
        paramBtn.gravity = 17;
        paramBtn.leftMargin = 15;
        btn_get.setPadding(10, 10, 10, 10);
        btn_get.setLayoutParams(paramBtn);
        btn_get.setTextColor(SupportMenu.CATEGORY_MASK);
       // btn_get.setOnClickListener(new AnonymousClass18(edittext, edittext2, lin_layout, btn_get, textView));
        //noinspection ResourceType
        edittext.setId(f12i);
        //noinspection ResourceType
        edittext2.setId(f12i);
        //noinspection ResourceType
        btn_get.setId(f12i);
        //noinspection ResourceType
        textView.setId(f12i);
        allEds.add(edittext);
        allEds2.add(edittext2);
        lin_layout.addView(textView);
        lin_layout.addView(edittext);
        lin_layout.addView(edittext2);
        lin_layout.addView(btn_get);
        layout.addView(lin_layout);
        f12i+=1;*/
    }


    private void getCurrentDate(final EditText editText, boolean timeNeed){
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        if (timeNeed) getCurrentTime(editText,true);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        editText.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        dateTimeData = editText.getText().toString();
                    }
                }, mYear, mMonth, mDay);

        datePickerDialog.show();

    }

    private void getCurrentTime(final EditText editText, final boolean fromDate){
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        if (fromDate)
                        editText.setText(dateTimeData +" "+ hourOfDay + ":" + minute);
                        else editText.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }


    public void setSupplier() {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url + supplierList, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i=0; i< response.length(); i++){
                        JSONObject jsonObject = response.getJSONObject(i);

                            SupplierListModel supplierListModel = new SupplierListModel();

                            supplierListModel.setId(jsonObject.getString("id"));
                            supplierListModel.setSupplier_name(jsonObject.getString("supplier_name"));
                            supplierListModel.setLicence_no(jsonObject.getString("licence_no"));
                            supplierListModel.setSupplier_code(jsonObject.getString("supplier_code"));
                            supplierListModel.setContact_person(jsonObject.getString("contact_person"));
                            supplierListModel.setContact_number(jsonObject.getString("contact_number"));
                            supplierListModel.setPost_id(jsonObject.getString("post_id"));
                            supplierListModel.setThana_id(jsonObject.getString("thana_id"));
                            supplierListModel.setDist_id(jsonObject.getString("dist_id"));
                            supplierListModel.setSupplier_type(jsonObject.getString("supplier_type"));
                            supplierListModel.setPurchase_point_id(jsonObject.getString("purchase_point_id"));
                            //supplierListModel.setInserted_by(jsonObject.getString("inserted_by"));
                            //supplierListModel.setInsert_date(jsonObject.getString("insert_date"));
                            //supplierListModel.setUpdate_by(jsonObject.getString("update_by"));
                            //supplierListModel.setUpdate_date(jsonObject.getString("update_date"));
                            //supplierListModel.setStatus_active(jsonObject.getString("status_active"));
                            //supplierListModel.setIs_deleted(jsonObject.getString("is_deleted"));
                            supplierListModel.setPost_name(jsonObject.getString("post_name"));
                            supplierListModel.setThana_name(jsonObject.getString("thana_name"));
                            supplierListModel.setDist_name(jsonObject.getString("dist_name"));

                            supplier.add(supplierListModel);
                        }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PurchaseActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                VolleyLog.d("Get Supplier Data", "Error: " + error.getMessage());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", bearer + UserModel.getToken());

                return headers;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }

    public void setCategory() {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url + categoryList, null , new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i= 0; i< response.length(); i++){
                    ListCategoryModel categoryList = new ListCategoryModel();
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        categoryList.setId(jsonObject.getString("id"));
                        categoryList.setCateory_name(jsonObject.getString("cateory_name"));
                        listCategory.add(categoryList);

                        categoryListString[i]= jsonObject.getString("cateory_name");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(PurchaseActivity.this,
                        android.R.layout.simple_spinner_item ,categoryListString);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                gradSpinner.setAdapter(adapter);
                gradSpinner.setSelection(2);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PurchaseActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                VolleyLog.d("Get Category Data", "Error: " + error.getMessage());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", bearer + UserModel.getToken());

                return headers;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }

    public void setListItem(){
        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url + listItem, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {

                    ListItemModel listItemModel = new ListItemModel();
                    listItemModel.setId("0");
                    listItemModel.setItem_name("Select");
                    qualityList.add(listItemModel);
                    qualityListString[0]="Select";
                    for (int i= 0; i< response.length(); i++){
                        JSONObject jsonObject = response.getJSONObject(i);
                        listItemModel = new ListItemModel();
                        listItemModel.setId(jsonObject.getString("id"));
                        listItemModel.setItem_name(jsonObject.getString("item_name"));
                        qualityList.add(listItemModel);

                        qualityListString[i+1] = jsonObject.getString("item_name");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(PurchaseActivity.this,
                        android.R.layout.simple_spinner_item ,qualityListString);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                qualitySpinner.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PurchaseActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                VolleyLog.d("Get item Data", "Error: " + error.getMessage());
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", bearer+ UserModel.getToken());

                return headers;
            }
        };

        Volley.newRequestQueue(this).add(request);


    }
}
