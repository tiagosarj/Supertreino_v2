package br.ufba.matc89;

import android.os.Bundle;
import android.app.Activity;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import br.ufba.matc89.model.Noticias;

import android.content.Intent;
import android.net.Uri;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;



public class NoticiaActivity extends Activity {

	/** Called when the activity is first created. */
	public Button lerConteudo;
	public TextView cabecalho;
	public ArrayList<Noticias> itemList = new ArrayList<Noticias>();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticia);
        
        //pegando instância do cabecalho
        cabecalho = (TextView) findViewById(R.id.TextView01);
        
        //click da listview
        final ListView listView = (ListView) findViewById(R.id.ListView01);
        listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				
				Noticias item = itemList.get(position);
				
				Intent intent = new Intent(Intent.ACTION_VIEW);
				
				intent.setData(Uri.parse(item.getUrl()));
				
				startActivity(intent);
			}
        	
        });
        
        
        //Button Click
        lerConteudo = (Button) findViewById(R.id.Button01);
        lerConteudo.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {			
				try {
					
					String url = "http://g1.globo.com/dynamo/rss2.xml";
					
					DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
					DocumentBuilder db = dbf.newDocumentBuilder();
					Document doc = db.parse(url);
					
					NodeList listItem = doc.getElementsByTagName("item");

					String[] arrayTitles = new String[listItem.getLength()];
					
					for(int x = 0; x < listItem.getLength(); x++){
						//título
						String title = listItem.item(x).getChildNodes().item(0).getChildNodes().item(0).getNodeValue();
						
						//link
						String link = listItem.item(x).getChildNodes().item(1).getChildNodes().item(0).getNodeValue();
						
						Noticias item = new Noticias();
						
						item.setTitle(title);
						item.setUrl(link);
						
						arrayTitles[x] = item.getTitle();
						
						itemList.add(item);
					}
					

					listView.setAdapter(
							new ArrayAdapter<String>(getBaseContext(), 
									android.R.layout.simple_list_item_1, arrayTitles)
					);
					
					cabecalho.setText("Leitor de conteúdo RSS - Finalizado");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG);
				}
				
			}
        });
          
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.noticia, menu);
		return true;
	}

}
