package br.ufba.matc89;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MedidasActivity extends Activity {

ListView listView1;
    
    String[] pages = {"01/01/2013", "02/02/2013", "03/03/2013"};
     
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medidas);
         
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,android.R.id.text1,pages);
         
        listView1 = (ListView)findViewById(R.id.listView1);
        listView1.setAdapter(aa);
         
        listView1.setOnItemClickListener(new OnItemClickListener() {
           
        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
 
                openMedidaCorporal();
        }
        });
    } 
    
       public void openMedidaCorporal()
        {
    	   Intent intent = new Intent(this,MedidaAdd.class);
           startActivity(intent); 	
        }

}
