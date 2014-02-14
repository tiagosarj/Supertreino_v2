package br.ufba.matc89;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MedidaAdd extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_medida_add);
		
		//final TextWatcher nascimentoMask;
	//	final EditText dataNascimento = (EditText) findViewById(R.id.txtNascimento);
	//	nascimentoMask = Mascara.aplicarMascara("##/##/####", dataNascimento);
	//	dataNascimento.addTextChangedListener(nascimentoMask);
	//  tesntando subclipse
		findViewById(R.id.salvar).setOnClickListener(
				new View.OnClickListener() {
					@Override			
					

					public void onClick(View view) {
						//TODO implementar salvar Atleta
						openConsultaCorporal();
					}
				});
	}
	
	
    public void openConsultaCorporal()
    {
	   Intent intent = new Intent(this,MedidasActivity.class);
       startActivity(intent); 	
    }

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	//	getMenuInflater().inflate(R.menu.atleta_add, menu);
//		return true;
//	}

}
