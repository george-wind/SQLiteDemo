package ni.george.sqlitetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import ni.george.sqlitetest.DBUtils.SearchDbManager;
import ni.george.sqlitetest.bean.SearchHistoryBean;

public class MainActivity extends AppCompatActivity {
    private TextView tvResult;
    private EditText etName;
    private EditText etNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult= (TextView) findViewById(R.id.result);
        etName= (EditText) findViewById(R.id.et_name);
        etNumber= (EditText) findViewById(R.id.et_number);
    }

    public void insert(View view){
        String name=etName.getText().toString();
        long number=System.currentTimeMillis();
        SearchDbManager dbManager=new SearchDbManager(this);
        SearchHistoryBean user=new SearchHistoryBean(name,number);
        dbManager.insertSearchInfo("site_live_history", user);
        doQuery();
    }

    public void query(View view){
       doQuery();
    }

    public void delete(View view){
        SearchDbManager dbManager=new SearchDbManager(this);
        dbManager.clear("site_live_history");
        doQuery();
    }

    private void doQuery(){
        SearchDbManager dbManager=new SearchDbManager(this);
        ArrayList<SearchHistoryBean> users=dbManager.quearySearchHistory("site_live_history");
        StringBuilder sb=new StringBuilder();
        for (SearchHistoryBean user:users) {
            sb.append(user.toString() + "\n");

        }
        tvResult.setText(sb.toString());
    }
}
